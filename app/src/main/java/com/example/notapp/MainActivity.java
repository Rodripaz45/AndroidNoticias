package com.example.notapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.notapp.Strategy.Contexto;
import com.example.notapp.Strategy.EstrategiaEscritor;
import com.example.notapp.Strategy.EstrategiaLector;
import com.example.notapp.Util.SharedPrefsUtil;
import com.example.notapp.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Button btnLogOut;
    private SharedPrefsUtil sharedPrefsUtil;
    private FloatingActionButton floatButton;
    Button btnMisNoticias;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        btnLogOut = findViewById(R.id.btnLogOut);
        floatButton = findViewById(R.id.floatButton);
        btnMisNoticias = findViewById(R.id.btnMisNoticias);
        sharedPrefsUtil = new SharedPrefsUtil(MainActivity.this);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefsUtil.removeKey("isLoggedIn");
                finish();
            }
        });

        btnMisNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FabFragment());
            }
        });
        String selectedValue = sharedPrefsUtil.getString("rol");

        if (selectedValue != null) {
            if (selectedValue.equals("Lector")) {
                EstrategiaLector lector = new EstrategiaLector(this);
                Contexto contexto = new Contexto(lector);
                contexto.ejecutarEstrategia();
            } else if (selectedValue.equals("Escritor")) {
                EstrategiaEscritor escritor = new EstrategiaEscritor(this);
                Contexto contexto = new Contexto(escritor);
                contexto.ejecutarEstrategia();
                MenuItem fabMenuItem = binding.bottomNavigationView.getMenu().findItem(R.id.fab);
                fabMenuItem.setVisible(true);
            }
        } else {
            EstrategiaLector lector = new EstrategiaLector(this);
            Contexto contexto = new Contexto(lector);
            contexto.ejecutarEstrategia();
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.business:
                    replaceFragment(new BusinessFragment());
                    break;
                case R.id.science:
                    replaceFragment(new ScienceFragment());
                    break;
                case R.id.sports:
                    replaceFragment(new SportsFragment());
                    break;
                case R.id.fab:
                    replaceFragment(new FabFragment());
                    break;
            }
            return true;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_menu, menu);
        return true;
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}