package com.example.makandong;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        com.example.makandong.DB database = new com.example.makandong.DB(getApplicationContext());

        ArrayList<com.example.makandong.Food> data = (ArrayList<com.example.makandong.Food>) database.getFoodList(getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.list_item_view);
        com.example.makandong.ListFoodAdapter adapter = new com.example.makandong.ListFoodAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public  void masukanData(){
        com.example.makandong.DB database = new com.example.makandong.DB(getApplicationContext());
        ArrayList<com.example.makandong.Food> data = com.example.makandong.ListFood.getData(getApplicationContext());

        for (com.example.makandong.Food food: data) {
            database.tambahData(food);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_item:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), com.example.makandong.MainActivity.class);
                startActivity(intent);
                this.finish();
                break;

            case R.id.tambahdata:
                masukanData();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}