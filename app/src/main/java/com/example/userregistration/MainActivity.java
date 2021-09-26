package com.example.userregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextAge, editTextPhone, editTextEmail, editTextPassword, editTextAddress;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextAddress = findViewById(R.id.editTextAddress);

        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String age = editTextAge.getText().toString();
                String phone = editTextPhone.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String address = editTextAddress.getText().toString();

                if(!username.equals("") && !age.equals("") && !phone.equals("") && !email.equals("") && !password.equals("") && !address.equals(""))
                {
                    process(username, age, phone, email, password, address);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please fill all Field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void process(String username, String age, String phone, String email, String password, String address) {

        Call<RegisterResponse> call = ApiController.getInstance()
                .apiSet()
                .registerUser(username, age, phone, email, password, address);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse response1 = response.body();
                String error = response1.getError();
                if (error.equals("000"))
                {
                    Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                }
                if (error.equals("001"))
                {
                    Toast.makeText(getApplicationContext(), "User already Registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        finish();
    }
}