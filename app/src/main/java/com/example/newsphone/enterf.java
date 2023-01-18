package com.example.newsphone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class enterf extends Fragment {

    String api="3cd3cc2c4be045caa48e900fc41032b1";
    ArrayList<Modal> modalArrayList;
    com.example.newsphone.adapter adapter;
    String country="in";
    private RecyclerView recyclerviewofenter;
    private String category="entertainment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.enterf, null);

        recyclerviewofenter=v.findViewById(R.id.rventer);
        modalArrayList=new ArrayList<>();
        recyclerviewofenter.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new adapter(getContext(),modalArrayList);
        recyclerviewofenter.setAdapter(adapter);


        findNews();


        return v;
    }

    private void findNews() {

        apiutiliti.apiinterface().getcategoriesnews(country,category,100,api).enqueue(new Callback<newsmain>() {
            @Override
            public void onResponse(Call<newsmain> call, Response<newsmain> response) {
                if (response.isSuccessful())
                {
                    modalArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<newsmain> call, Throwable t) {

            }
        });


    }
}
