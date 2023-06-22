package com.example.notapp.Strategy;

import android.content.Context;
import android.widget.TextView;

import com.example.notapp.MainActivity;
import com.example.notapp.R;

// Implementación de la estrategia para el rol de Lector
public class EstrategiaLector implements Estrategia {
    private Context context;

    public EstrategiaLector(Context context) {
        this.context = context;
    }

    @Override
    public void ejecutar() {
        //TextView textView = ((MainActivity) context).findViewById(R.id.textView5);
        //textView.setText("Lector");

        // Obtener el contenedor LinearLayout desde el diseño de la actividad
        //LinearLayout container = ((MainActivity2) context).findViewById(R.id.container);

        // Agregar el TextView al contenedor
        //container.addView(textView);
    }
}
