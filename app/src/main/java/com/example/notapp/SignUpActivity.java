package com.example.notapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notapp.Models.UserLogin;
import com.example.notapp.Service.UserLoginResponse;
import com.example.notapp.Service.UserRegisterResponse;
import com.example.notapp.Service.UserService;
import com.example.notapp.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;

    private SharedPrefsUtil sharedPrefsUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sharedPrefsUtil = new SharedPrefsUtil(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        List<String> opciones = new ArrayList<>();
        opciones.add("Lector");
        opciones.add("Escritor");

        TextView textView = findViewById(R.id.signupText);
        String text = "Already registered? SignIn Now";

        SpannableString spannableString = new SpannableString(text);
        int startIndex = text.indexOf("SignIn Now");

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, startIndex, startIndex + "SignIn Now".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("rodripaz");
                String email = String.valueOf(username.getText());
                String pass = String.valueOf(password.getText());
                registerUser(email, pass);
            }
        });
    }

    private void registerUser(String email, String pass) {
        Call<UserRegisterResponse> call = UserService.getUserAPI().registerUser(new UserLogin(email,pass));
        call.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "SignIn Exitoso", Toast.LENGTH_LONG).show();
                    Spinner spinner = findViewById(R.id.spinner);
                    String selectedValue = spinner.getSelectedItem().toString();
                    sharedPrefsUtil.saveBoolean("isLoggedIn", true);
                    sharedPrefsUtil.saveString("rol", selectedValue);
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.putExtra("selectedValue", selectedValue); // Agregar el valor seleccionado como dato extra
                    startActivity(intent);
                }else {
                    Toast.makeText(SignUpActivity.this, "SignIn Erroneo", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "On Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}