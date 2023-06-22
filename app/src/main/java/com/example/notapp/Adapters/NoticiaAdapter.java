package com.example.notapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notapp.MainActivity;
import com.example.notapp.Models.Noticia;
import com.example.notapp.R;

import java.util.List;


public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder> {
    private Context context;
    private List<Noticia> noticiaList;

    public NoticiaAdapter(Context context, List<Noticia> noticiaList) {
        this.context = context;
        this.noticiaList = noticiaList;
    }

    @NonNull
    @Override
    public NoticiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticias_model, parent, false);
        return new NoticiaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiaViewHolder holder, int position) {
        Noticia noticia = noticiaList.get(position);
        holder.txtTitulo.setText(noticia.titulo);
        //holder.txtDescripcion.setText(noticia.descripcion);
        //holder.txtEscritor.setText(noticia.escritor);
        holder.bindNoticia(noticia);
    }

    @Override
    public int getItemCount() {
        return noticiaList.size();
    }

    static class NoticiaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitulo;
        //private TextView txtDescripcion;
        //private TextView txtEscritor;
        private Button btnLeer;
        private Noticia currentNoticia;

        public NoticiaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            //txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            //txtEscritor = itemView.findViewById(R.id.txtEscritor);
            btnLeer = itemView.findViewById(R.id.btnLeer);
            btnLeer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.putExtra("titulo", currentNoticia.titulo);
                    intent.putExtra("descripcion", currentNoticia.descripcion);
                    intent.putExtra("escritor", currentNoticia.escritor);
                    v.getContext().startActivity(intent);
                }
            });
        }
        public void bindNoticia(Noticia noticia) {
            currentNoticia = noticia; // Asignar la noticia actual al ViewHolder
            txtTitulo.setText(noticia.titulo);
            //txtDescripcion.setText(noticia.descripcion);
            //txtEscritor.setText(noticia.escritor);
        }
    }
}

