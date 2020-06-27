package com.vale.scrollingdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.text.Layout;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vale.adapter.IViewHolderOnclickListener;
import com.vale.adapter.RepoViewAdapter;
import com.vale.model.GitRepo;
import com.vale.viewmodel.GistViewModel;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private GistViewModel viewModel;
    private MainActivity context;
    private IViewHolderOnclickListener onclickListener;
    public static final int LOGIN_ACTIVITY_REQUEST_CODE = 1;

    private Observer<ArrayList<GitRepo>> gitRepoUpdateObserver = new Observer<ArrayList<GitRepo>>() {
        @Override
        public void onChanged(final ArrayList<GitRepo> gitRepos) {
            Log.d(TAG, "gitRepoUpdateObserver.onChanged: ");
            adapter = new RepoViewAdapter(context, gitRepos, new IViewHolderOnclickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.d(TAG, "gitRepoUpdateObserver.onItemClick: " + position);
                    Intent intent = new Intent(MainActivity.this, DisplayRepoDetailsActivity.class);
                    String message = "Selected repo";

                    intent.putExtra(Intent.EXTRA_TEXT, message).putExtra("details", gitRepos.get(position).getOwner());
                    startActivity(intent);

                }
            });
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    };

    /**
     * Called when a new error message is needs to be displayed to the user
     *
     * @param message the error message to diaplsy
     */
    private Observer<String> onError = new Observer<String>() {
        @Override
        public void onChanged(String message) {
            Log.e(TAG, "onChanged: ", new Exception(message));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_gitrepos);
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        viewModel = ViewModelProviders.of(this).get(GistViewModel.class);
        viewModel.getGitRepoLiveData().observe(this, gitRepoUpdateObserver);
        viewModel.getmErrorMessage().observe(this, onError);
        context = this;
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + "FAB clicked");
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, LOGIN_ACTIVITY_REQUEST_CODE);
            }
        });

    }

}
