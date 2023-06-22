package com.example.notapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
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
import com.example.notapp.Service.UserService;
import com.example.notapp.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;

    private SharedPrefsUtil sharedPrefsUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefsUtil = new SharedPrefsUtil(this);

        Log.e("Log in activity", String.valueOf(sharedPrefsUtil.getBoolean("isLoggedIn")));

        //sharedPrefsUtil.removeKey("isLoggedIn");
        Boolean isLoggedIn = sharedPrefsUtil.getBoolean("isLoggedIn");
        if (isLoggedIn) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        List<String> opciones = new ArrayList<>();
        opciones.add("Lector");
        opciones.add("Escritor");

        TextView textView = findViewById(R.id.signupText);
        String text = "Not yet registered? SignUp Now";

        SpannableString spannableString = new SpannableString(text);
        int startIndex = text.indexOf("SignUp Now");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(boldSpan, startIndex, startIndex + "SignUp Now".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ddssds");
                String email = String.valueOf(username.getText());
                String pass = String.valueOf(password.getText());
                loginUser(email, pass);
            }
        });
    }

    private void loginUser(String email, String pass) {
        Call<UserLoginResponse> call = UserService.getUserAPI().loginUser(new UserLogin(email, pass));
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Exitoso", Toast.LENGTH_LONG).show();
                    Spinner spinner = findViewById(R.id.spinner);
                    String selectedValue = spinner.getSelectedItem().toString();
                    sharedPrefsUtil.saveBoolean("isLoggedIn", true);
                    sharedPrefsUtil.saveString("rol", selectedValue);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("selectedValue", selectedValue); // Agregar el valor seleccionado como dato extra
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Login Erroneo", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "On Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}