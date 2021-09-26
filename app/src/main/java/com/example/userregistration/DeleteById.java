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

public class DeleteById extends AppCompatActivity {

    EditText editTextFindID;
    Button buttonFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_by_id);

        editTextFindID = findViewById(R.id.editTextFindUserByID);
        buttonFind = findViewById(R.id.buttonFind);

        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = editTextFindID.getText().toString();
                if (!userID.equals("")) {
                    process(userID);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter user Id", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void process(String userID) {

       Call<RegisterResponse> call = ApiController.getInstance()
               .apiSet()
               .delete(userID);
       call.enqueue(new Callback<RegisterResponse>() {
           @Override
           public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
               RegisterResponse obj = response.body();
               String error = obj.getError();
               if (error.equals("000"))
               {
                   Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(DeleteById.this, HomeActivity.class));
                   finish();
               }
               else
               {
                   Toast.makeText(getApplicationContext(), "Id Not Found", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(DeleteById.this, HomeActivity.class));
        finish();
    }
}
