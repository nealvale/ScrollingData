package com.vale.scrollingdata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayRepoDetailsActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public DisplayRepoDetailsActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String repo_id= intent.getStringExtra(Intent.EXTRA_TEXT);
        Log.d(TAG, "onCreate: "+repo_id);

    }
}
