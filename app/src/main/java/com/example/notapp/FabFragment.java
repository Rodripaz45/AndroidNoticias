package com.example.notapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notapp.Adapters.ArticlesAdapter;
import com.example.notapp.Models.Articles;
import com.example.notapp.Models.ArticlesSingleton;

import java.util.ArrayList;
import java.util.List;


public class FabFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Articles> articlesList;
    private ArticlesAdapter articlesAdapter;
    private ArticlesSingleton articlesSingleton = ArticlesSingleton.getInstance();

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
        getAllUsers();
        return view;
    }

    private void getAllUsers() {
        articlesList.addAll(articlesSingleton.getArticlesList());
        articlesAdapter.notifyDataSetChanged();
    }
    private void initAdapter() {
        articlesList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articlesAdapter = new ArticlesAdapter(getContext(), articlesList);
        recyclerView.setAdapter(articlesAdapter);
    }
}