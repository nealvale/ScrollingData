package com.vale.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vale.data.IGitHubService;
import com.vale.model.GitRepo;
import com.vale.tools.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GitDetailsViewModel extends ViewModel {
    private static final String TAG = GitDetailsViewModel.class.getSimpleName();
    private MutableLiveData<ArrayList<GitRepo>> gitDetailsLiveData;
    private MutableLiveData<String> mErrorMessage = new MutableLiveData<>();
    private Retrofit retrofit;
    private IGitHubService iGitHubService;

    private Boolean morePagesAvailable = false;

    public GitDetailsViewModel() {
        super();
        init();
    }

    private void init() {
        gitDetailsLiveData = new MutableLiveData<>();

        retrofit = NetworkUtils.setApiCall("tets", "pwd");
        iGitHubService = retrofit.create(IGitHubService.class);
        getPublicRepos();
    }

    private void getPublicRepos() {
        Log.d(TAG, "getPublicRepos: ");
        iGitHubService.getPublicGists(0).enqueue(new Callback<List<GitRepo>>() {
            @Override
            public void onResponse(Call<List<GitRepo>> call, Response<List<GitRepo>> response) {
                if (!response.isSuccessful()) {
                    showError(NetworkUtils.onGitHubResponseError(response));
                    return;
                }
                morePagesAvailable = NetworkUtils.isNextLinkAvailable(response);
                ArrayList<GitRepo> currentList = gitDetailsLiveData.getValue();
                if (currentList == null) {
                    currentList = new ArrayList<GitRepo>();
                }
                if (response.body() != null) {
                    currentList.addAll(response.body());
                }


                gitDetailsLiveData.postValue(currentList);
                Log.d(TAG, "onResponse: " + currentList.toString());
            }

            @Override
            public void onFailure(Call<List<GitRepo>> call, Throwable t) {
                showError(t.getLocalizedMessage());
            }
        });
    }

    /**
     * Convenience method for showing an error to the user
     *
     * @param message the message to show to the user
     */
    private void showError(String message) {
        mErrorMessage.postValue(message);
    }

    public MutableLiveData<String> getmErrorMessage() {
        return mErrorMessage;
    }
}
