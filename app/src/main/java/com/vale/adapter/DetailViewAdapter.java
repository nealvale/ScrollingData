package com.vale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vale.model.GitHubUser;
import com.vale.scrollingdata.R;

import java.util.ArrayList;

public class DetailViewAdapter extends ArrayAdapter<GitHubUser> {
    private static DetailViewHolder viewHolder;

    public DetailViewAdapter(@NonNull Context context, @NonNull ArrayList<GitHubUser> objects) {
        super(context, R.layout.git_details_view, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GitHubUser gitHubUser = getItem(position);
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.git_details_view, parent, false);
            viewHolder = new DetailViewHolder(convertView);
            viewHolder.bindDataToView(gitHubUser, null);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DetailViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
