package com.danicode.animalsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AnimalAdapter.MyClickInterface {

    RecyclerView recyclerView;
    ArrayList<Animal> animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = findViewById(R.id.recycler_view);
        this.animals = new ArrayList<>();
        this.animals.add(new Animal("Delfín", R.drawable.dolphin));
        this.animals.add(new Animal("León", R.drawable.lion));
        this.animals.add(new Animal("Búho", R.drawable.owl));
        this.animals.add(new Animal("Conejo", R.drawable.rabbit));
        this.animals.add(new Animal("Tigre", R.drawable.tiger));
        this.animals.add(new Animal("Tortuga", R.drawable.turtle));
        this.animals.add(new Animal("Ave", R.drawable.parrot));

        AnimalAdapter adapter = new AnimalAdapter(animals, this, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Clicked " + animals.get(position).getName(), Toast.LENGTH_SHORT);
        Intent intent = new Intent(this, AnimalInfoActivity.class);
        intent.putExtra("image", animals.get(position).getImg());
        intent.putExtra("name", animals.get(position).getName());
        startActivity(intent);
    }
}