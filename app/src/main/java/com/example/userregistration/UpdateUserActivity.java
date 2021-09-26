package com.example.userregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateUserActivity extends AppCompatActivity {

    EditText editTextUsernameUpdate, editTextAgeUpdate, editTextPhoneUpdate, editTextEmailUpdate, editTextPasswordUpdate, editTextAddressUpdate;
    Button buttonRegisterUpdate, buttonGetData;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        editTextUsernameUpdate = findViewById(R.id.editTextUsernameUpdate);
        editTextAgeUpdate = findViewById(R.id.editTextAgeUpdate);
        editTextPhoneUpdate = findViewById(R.id.editTextPhoneUpdate);
        editTextEmailUpdate = findViewById(R.id.editTextEmailUpdate);
        editTextPasswordUpdate = findViewById(R.id.editTextPasswordUpdate);
        editTextAddressUpdate = findViewById(R.id.editTextAddressUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        buttonRegisterUpdate = findViewById(R.id.buttonRegisterUpdate);

        buttonRegisterUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsernameUpdate.getText().toString();
                String age = editTextAgeUpdate.getText().toString();
                String phone = editTextPhoneUpdate.getText().toString();
                String email = editTextEmailUpdate.getText().toString();
                String password = editTextPasswordUpdate.getText().toString();
                String address = editTextAddressUpdate.getText().toString();

                if (!username.equals("") && !age.equals("") && !phone.equals("") && !email.equals("") && !password.equals("") && !address.equals("")) {
                    process(username, age, phone, email, password, address);
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill all Field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void process(String username, String age, String phone, String email, String password, String address) {

        Call<RegisterResponse> call = ApiController.getInstance()
                .apiSet().updateUser(id, username, age, phone, email, password, address);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse obj = response.body();
                String error = obj.getError();
                if (error.equals("000")) {
                    Toast.makeText(getApplicationContext(), "Update Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateUserActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Update Fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UpdateUserActivity.this, HomeActivity.class));
        finish();
    }

}