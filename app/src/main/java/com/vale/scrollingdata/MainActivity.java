package com.vale.scrollingdata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.vale.adapter.ListDataAdapter;
import com.vale.adapter.ListDataHolder;
import com.vale.model.ListDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.listData);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListDataAdapter(generateSimpleList());
        recyclerView.setAdapter(adapter);

    }

    private List<ListDataModel> generateSimpleList() {
        List<ListDataModel> simpleViewModelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Log.d(TAG, "generateSimpleList: " + String.format(Locale.US, "Name %d", i));
            simpleViewModelList.add(new ListDataModel(String.format(Locale.US, "Name %d", i), String.format(Locale.US, "Address %d", i)));
        }

        return simpleViewModelList;
    }
}
