package com.vale.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vale.model.GitHubUser;
import com.vale.model.GitRepo;
import com.vale.scrollingdata.R;

public class DetailViewHolder {
    private TextView id;
    private TextView name;
    private TextView location;
    private TextView email;
    private ImageView avatarImage;

    public DetailViewHolder(@NonNull View detailView) {

        id = detailView.findViewById(R.id.detail_repo_id);
        name = detailView.findViewById(R.id.detail_name);
        location = detailView.findViewById(R.id.detail_location);
        email = detailView.findViewById(R.id.detail_email);
        avatarImage = detailView.findViewById(R.id.details_avatar);
    }

    public void bindDataToView(GitHubUser gitHubUser, final IViewHolderOnclickListener listener) {
        this.id.setText(gitHubUser.getId() + "");
        this.name.setText(gitHubUser.getName());
        this.email.setText(gitHubUser.getEmail());
        this.location.setText(gitHubUser.getLocation());

        if (gitHubUser.getAvatar_url() != null) {
            //Set the author avatar image
            RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_avatar_placeholder);
            Glide.with(avatarImage)
                    .load(gitHubUser.getAvatar_url())
                    .apply(options)
                    .into(avatarImage);
        }
    }

}
