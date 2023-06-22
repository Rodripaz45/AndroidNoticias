package com.example.notapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notapp.Models.Articles;
import com.example.notapp.Models.ArticlesSingleton;

import java.util.List;

public class CreateActivity extends AppCompatActivity {

    EditText titulo;
    EditText descripcion;
    EditText contenido;
    EditText autor;
    Button createNoticia;

    Articles articles;
    private ArticlesSingleton articlesSingleton = ArticlesSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        titulo = findViewById(R.id.titulo);
        descripcion = findViewById(R.id.descripcion);
        contenido = findViewById(R.id.contenido);
        autor = findViewById(R.id.autor);
        createNoticia = findViewById(R.id.createNoticia);
        createNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author = String.valueOf(autor.getText());
                String tittle = String.valueOf(titulo.getText());
                String description = String.valueOf(descripcion.getText());
                String content = String.valueOf(contenido.getText());
                articlesSingleton.addArticle(new Articles(author,tittle,description,null,null,content));
                finish();
            }
        });
    }
}