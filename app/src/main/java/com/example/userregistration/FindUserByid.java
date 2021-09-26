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

public class FindUserByid extends AppCompatActivity {

    EditText editTextFindID;
    Button buttonFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user_byid);

        editTextFindID = findViewById(R.id.editTextFindUserByID);
        buttonFind = findViewById(R.id.buttonFind);

        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = editTextFindID.getText().toString();
                if (!userID.equals(""))
                {
                    process(userID);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please enter user Id", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void process(String userID) {

        Call<RegisterResponse> call = ApiController.getInstance()
                .apiSet()
                .findUser(userID);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse obj = response.body();
                String error = obj.getError();
                if (error.equals("000"))
                {
                    Toast.makeText(getApplicationContext(), "User Found", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FindUserByid.this, UpdateUserActivity.class);
                    intent.putExtra("id",userID);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No user Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FindUserByid.this, HomeActivity.class));
        finish();
    }
}