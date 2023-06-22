package com.example.notapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notapp.Adapters.ArticlesAdapter;
import com.example.notapp.Models.Articles;
import com.example.notapp.Service.NoticiaService;
import com.example.notapp.Service.ResponseNoticia;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SportsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Articles> articlesList;
    private ArticlesAdapter articlesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Acceder al RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);

        // Inicializar el adaptador y configurar el RecyclerView
        initAdapter();

        // Obtener los datos de la API y actualizar el adaptador
        getAllNoticia();

        return view;
    }

    private void getAllNoticia() {
        Call<ResponseNoticia> call = NoticiaService.getNoticiasApi().getAllNoticia("da0072c07f64489cb9d7bfc3cc527eac", "us", 50, "sports");
        call.enqueue(new Callback<ResponseNoticia>() {
            @Override
            public void onResponse(Call<ResponseNoticia> call, Response<ResponseNoticia> response) {
                if(response.isSuccessful()){
                    articlesList.addAll(response.body().getArticles());
                    articlesAdapter.notifyDataSetChanged();
                }else {
                    System.out.println(response.message());
                    Log.e("MainActivity", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ResponseNoticia> call, Throwable t) {
                Log.e("MainActivity", "No ingreso " + t.getMessage());
            }
        });
    }

    private void initAdapter() {
        articlesList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articlesAdapter = new ArticlesAdapter(getContext(), articlesList);
        recyclerView.setAdapter(articlesAdapter);
    }
}