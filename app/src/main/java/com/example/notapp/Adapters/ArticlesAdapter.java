package com.example.notapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notapp.MainActivity;
import com.example.notapp.MainActivity2;
import com.example.notapp.Models.Articles;
import com.example.notapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {
    private Context context;
    private List<Articles> articlesList;

    public ArticlesAdapter(Context context, List<Articles> articlesList) {
        this.context = context;
        this.articlesList = articlesList;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticias_model, parent, false);
        return new ArticlesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        Articles articles = articlesList.get(position);
        holder.txtTitulo.setText(articles.title + articles.url);
        holder.bindArticles(articles);
        Picasso.get()
                .load(articles.urlToImage)
                .placeholder(R.drawable.error)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (articlesList == null) {
            return 0;
        }
        return articlesList.size();
    }
    static class ArticlesViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitulo;
        private ImageView imageView;
        private Button btnLeer;
        private Articles currentArticles;
        public ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            btnLeer = itemView.findViewById(R.id.btnLeer);
            imageView = itemView.findViewById(R.id.imageView);
            btnLeer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MainActivity2.class);
                    intent.putExtra("titulo", currentArticles.title);
                    intent.putExtra("descripcion", currentArticles.description);
                    intent.putExtra("escritor", currentArticles.author);
                    intent.putExtra("contenido", currentArticles.content);
                    intent.putExtra("imagen", currentArticles.urlToImage);
                    intent.putExtra("url", currentArticles.url);
                    v.getContext().startActivity(intent);
                }
            });
        }
        public void bindArticles(Articles articles) {
            currentArticles = articles; // Asignar la noticia actual al ViewHolder
            txtTitulo.setText(articles.title + articles.url);
        }
    }
}
