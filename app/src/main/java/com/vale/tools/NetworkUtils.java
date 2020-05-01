package com.vale.tools;

import android.util.Log;

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static Retrofit setApiCall(String username, String token) {
        Log.d(TAG, "setApiCall: ");
        //Check if the provided credentials are empty
//        if (username.isEmpty() || token.isEmpty()) {
//            return;
//        }

        //Store the username and token for later
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            /* ToDo   .addInterceptor(new BasicAuthInterceptor(mUsername.trim(), mToken.trim())).build(); */
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.GIT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            return retrofit;
        } catch (Exception e) {
            Log.e(TAG, "setApiCall: ", e);
        }
        return null;
    }

    public static String onGitHubResponseError(Response response) {

        if (response.code() == 403) {

            Headers headers = response.headers();
            Set<String> headerNames = headers.names();
            long rateLimitReset = 0;

            for (String headerName :
                    headerNames) {
                String headerValue = headers.get(headerName);
                if (headerValue == null) {
                    continue;
                }

                if (headerName.equals("X-RateLimit-Reset")) {
                    rateLimitReset = Long.valueOf(headers.get(headerName));
                    break;
                }
            }

            if (rateLimitReset != 0) {
                Date resetDate = new Date(rateLimitReset * 1000);
                String resetTimeText = DateFormat.getTimeInstance().format(resetDate);
                return "Uh Oh! it looks like you exceeded you API rate limit. Try again after " + resetTimeText;
            }
        }

        return response.message();
    }

    /**
     * Check a link header returned in a call to the GitHub API to see if there is a URL pointing to the next page of content
     *
     * @param response the Retrofit Response to process
     * @return TRUE is a next link was found, FALSE if not
     */
    public static boolean isNextLinkAvailable(Response response) {

        String linkHeader = response.headers().get("Link");
        if (linkHeader == null) {
            return false;
        }

        int nextLinkIndex = linkHeader.indexOf("; rel=\"next\"");
        return nextLinkIndex >= 0;

    }
}
