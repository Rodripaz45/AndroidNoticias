package com.example.notapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MainActivity2 extends AppCompatActivity {
    private TextView txtTitulo;
    private TextView txtDescripcion;
    private TextView txtEscritor;
    private ImageView imageView2;
    private TextView txtContenido;
    private TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtTitulo = findViewById(R.id.textView2);
        txtDescripcion = findViewById(R.id.textView3);
        txtEscritor = findViewById(R.id.textView5);
        imageView2 = findViewById(R.id.imageView2);
        txtContenido = findViewById(R.id.textView4);
        link = findViewById(R.id.link);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String descripcion = intent.getStringExtra("descripcion");
        String escritor = intent.getStringExtra("escritor");
        String imagen = intent.getStringExtra("imagen");
        String contenido = intent.getStringExtra("contenido");
        String url = intent.getStringExtra("url");
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(url);
                Intent i = new Intent(Intent.ACTION_VIEW,_link);
                startActivity(i );
            }
        });

        txtTitulo.setText(titulo);
        txtDescripcion.setText(descripcion);
        txtContenido.setText(contenido);
        txtEscritor.setText(escritor);
        Picasso.get()
                .load(imagen).placeholder(R.drawable.error).into(imageView2);

    }
}