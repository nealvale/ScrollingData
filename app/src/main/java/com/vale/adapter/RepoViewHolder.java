package com.vale.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vale.model.GitHubUser;
import com.vale.model.GitRepo;
import com.vale.scrollingdata.R;

public class RepoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
    private TextView id;
    private TextView name;
    private TextView full_name;
    private TextView node_id;
    private ImageView avatarImage;
    private ConstraintLayout repo_items;

    public RepoViewHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.repo_id);
        name = itemView.findViewById(R.id.repo_name);
        full_name = itemView.findViewById(R.id.repo_name);
        node_id = itemView.findViewById(R.id.repo_node_id);
        avatarImage = itemView.findViewById(R.id.image_repo_user_avatar);
    }

    public void bindDataToView(GitRepo gitRepo, final IViewHolderOnclickListener listener) {
        this.id.setText(gitRepo.getId() + "");
        this.name.setText(gitRepo.getName());
        this.full_name.setText(gitRepo.getFull_name());
        this.node_id.setText(gitRepo.getNode_id());
        GitHubUser owner = gitRepo.getOwner();
        if (owner != null) {
            //Set the author avatar image
            RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_avatar_placeholder);
            Glide.with(avatarImage)
                    .load(owner.getAvatar_url())
                    .apply(options)
                    .into(avatarImage);
        }


    }

    public void bindOnclickListener() {

    }
    @Override
    public void onClick(View v) {
        int adapterPosition=getAdapterPosition();

    }
}
