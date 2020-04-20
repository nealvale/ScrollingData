package com.vale.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vale.model.ListDataModel;
import com.vale.scrollingdata.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ListDataAdapter extends RecyclerView.Adapter {

    private List<ListDataModel> dataModel = new ArrayList<ListDataModel>();

    public ListDataAdapter(List<ListDataModel> dataSet) {
        this.dataModel.addAll(dataSet);
    }


    @NonNull
    @Override
    public ListDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: " + parent.getId());
        Log.d(TAG, "onCreateViewHolder: " + viewType);

        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        ListDataHolder listDataHolder = new ListDataHolder(view);
        return listDataHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListDataModel data = dataModel.get(position);
        ((ListDataHolder) holder).bindData(dataModel.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.scrolling_view;
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + dataModel.size());
        return dataModel.size();
    }
}
