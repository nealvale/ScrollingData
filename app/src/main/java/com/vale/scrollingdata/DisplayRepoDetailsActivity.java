package com.vale.scrollingdata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.vale.adapter.DetailViewAdapter;
import com.vale.adapter.DetailViewHolder;
import com.vale.model.GitHubUser;
import com.vale.viewmodel.GitDetailsViewModel;

import java.util.ArrayList;

public class DisplayRepoDetailsActivity extends AppCompatActivity {
    private GitDetailsViewModel viewModel;
    private static final String TAG = DisplayRepoDetailsActivity.class.getSimpleName();
    public DisplayRepoDetailsActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.git_details_view);
        Intent intent = getIntent();
        String repo_id= intent.getStringExtra(Intent.EXTRA_TEXT);
        GitHubUser user = (GitHubUser) intent.getSerializableExtra("details");
        Log.d(TAG, "onCreate: "+repo_id);
        View view = findViewById(R.id.details_layout);
        ArrayList<GitHubUser> userList = new ArrayList<GitHubUser>();
        userList.add(user);
        DetailViewAdapter viewAdapter = new DetailViewAdapter(this, userList);
        ListView listView = findViewById(R.id.detail_list_view);
        listView.setAdapter(viewAdapter);
    }
}
