package com.example.notapp.Strategy;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.notapp.MainActivity;
import com.example.notapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EstrategiaEscritor implements Estrategia{
    private Context context;

    public EstrategiaEscritor(Context context) {
        this.context = context;
    }

    @Override
    public void ejecutar() {
        FloatingActionButton floatButton = ((MainActivity) context).findViewById(R.id.floatButton);
        floatButton.setVisibility(View.VISIBLE);


        //Obtener el contenedor LinearLayout desde el diseño de la actividad
        //LinearLayout container = ((MainActivity2) context).findViewById(R.id.container);

        // Agregar el TextView al contenedor
        //container.addView(textView);
    }
}