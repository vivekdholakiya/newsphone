package com.example.newsify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tachf extends Fragment {

    String api="3cd3cc2c4be045caa48e900fc41032b1";
    ArrayList<Modal> modalArrayList;
    com.example.newsify.adapter adapter;
    String country="in";
    private RecyclerView recyclerviewofentertechnology;
    private String category="technology";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.techf, null);

        recyclerviewofentertechnology=v.findViewById(R.id.rvtach);
        modalArrayList=new ArrayList<>();
        recyclerviewofentertechnology.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new adapter(getContext(),modalArrayList);
        recyclerviewofentertechnology.setAdapter(adapter);


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
