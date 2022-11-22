package com.adityaJSleepFN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.adityaJSleepFN.model.Account;
import com.adityaJSleepFN.request.BaseApiService;
import com.adityaJSleepFN.request.UtilsApi;


import org.json.simple.parser.*;
import org.json.simple.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private Button button1,button2;

    BaseApiService mApiService;
    EditText username,password;
    Context mContext;


    protected Account requestAccount(int id, String email, String password) {
        mApiService.getAccount(0,
                email,
                password
            ).enqueue(new Callback<com.adityaJSleepFN.model.Account>() {
            @Override
            public void onResponse(Call<com.adityaJSleepFN.model.Account> call,
                                   Response<com.adityaJSleepFN.model.Account> response) {
                if (response.isSuccessful()){
                    Account a;
                    a = response.body();
                    System.out.println(a.toString());
                }
            }
            @Override
            public void onFailure(Call<com.adityaJSleepFN.model.Account> call, Throwable t) {
                Toast.makeText(mContext,"no Account id = 0",Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
    public static <T> List<T> readJson(Class<T> c, String filepath)  {
        List<T> out = null;
        try(FileReader f = new FileReader(filepath)){
            JSONArray ja = new JSONArray();
            out = ja;
            JSONParser j = new JSONParser();
            T ob = (T) j.parse(f);
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return out;
    }
    protected void requestLogin(int id,String email, String password){
        EditText email1 = findViewById(R.id.email);
        EditText password1 = findViewById(R.id.password);
        mApiService.getAccount(id,email, password).enqueue(
                new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        if (response.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        }
                    }
                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApiService      = UtilsApi.getApiService();
        username         = findViewById(R.id.username);
        password         = findViewById(R.id.password);
        mContext         = this;
        Button loginButton = findViewById(R.id.loginButton);
        TextView register = findViewById(R.id.registerNow);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account a = requestAccount(0,null,null);
            }
        });
        Button b = (Button) findViewById(R.id.loginButton);
        ListView l = (ListView) findViewById(R.id.list);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = ((EditText)findViewById(R.id.email)).getText().toString();
                String getPassword = ((EditText)findViewById(R.id.password)).getText().toString();
                ArrayList<String> array1 = new ArrayList<>();
                ArrayList<String> array2 = new ArrayList<>();
                if (array1.contains(getEmail)){
                    Toast.makeText(mContext,"Already added",Toast.LENGTH_SHORT).show();
                }else if(getEmail == null || getPassword == null){
                    Toast.makeText(mContext,"Already added",Toast.LENGTH_SHORT).show();
                }
                else{
                    array1.add(getEmail);
                    array2.add(getPassword);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(LoginActivity.this,
                            android.R.layout.activity_list_item,array1);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(LoginActivity.this,
                            android.R.layout.activity_list_item,array2);
                    l.setAdapter(adapter1);
                    l.setAdapter(adapter2);
                }
            }
        });

        register.setOnClickListener(v -> {
            Intent move = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(move);
        });
//        loginButton.setOnClickListener(v -> {
//            Intent move = new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(move);
//        });

    }

}