package com.vale.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vale.model.ListDataModel;
import com.vale.scrollingdata.R;

public class ListDataHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public ListDataHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.listItems);
    }

    public void bindData(final ListDataModel listDataModel) {
        textView.setText(listDataModel.getName() + "," + listDataModel.getAddress());
    }
}
