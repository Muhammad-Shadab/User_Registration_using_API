package com.example.userregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

       Call<List<FetchUserResponse>> call = ApiController.getInstance()
               .apiSet()
               .fetchUser();

       call.enqueue(new Callback<List<FetchUserResponse>>() {
           @Override
           public void onResponse(Call<List<FetchUserResponse>> call, Response<List<FetchUserResponse>> response) {
               List<FetchUserResponse> obj = response.body();
               adapter = new Adapter(obj);
               rv.setAdapter(adapter);
           }

           @Override
           public void onFailure(Call<List<FetchUserResponse>> call, Throwable t) {

           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.registrationMenu:
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.updateUserRegistration:
                startActivity(new Intent(HomeActivity.this, FindUserByid.class));
                finish();
                break;
            case R.id.deleteUserMenu:
                startActivity(new Intent(HomeActivity.this, DeleteById.class));
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}