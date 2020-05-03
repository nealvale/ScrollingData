package com.vale.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vale.model.GitRepo;
import com.vale.scrollingdata.R;

import java.util.ArrayList;

public class RepoViewAdapter extends RecyclerView.Adapter<RepoViewHolder> {
    private static final String TAG = RepoViewAdapter.class.getSimpleName();
    Activity context;
    private ArrayList<GitRepo> listofGitRepos;
    private Activity ctx;
    private IViewHolderOnclickListener listener;

    public RepoViewAdapter(Activity context, ArrayList<GitRepo> gitRepos, IViewHolderOnclickListener  onclickListener) {
        listofGitRepos = gitRepos;
        ctx = context;
        listener= onclickListener;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        switch (viewType) {
            case R.layout.gitrepo_item:
                return new RepoViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+holder.getItemId()+" position: "+position);
        GitRepo gitRepo = listofGitRepos.get(position);
        RepoViewHolder repoViewHolder = holder;
        repoViewHolder.b
        repoViewHolder.bindDataToView(gitRepo);
    }


    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: " + position);
        return R.layout.gitrepo_item;
    }

    @Override
    public int getItemCount() {
        return listofGitRepos.size();
    }
}