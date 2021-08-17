package com.danicode.animalsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalInfoActivity extends AppCompatActivity {
    ImageView imgAnimal;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);

        this.imgAnimal = findViewById(R.id.img_animal_circle);
        this.txtName = findViewById(R.id.txt_animal);

        Intent intent = getIntent();
        this.imgAnimal.setImageResource(intent.getIntExtra("image", R.drawable.ic_launcher_foreground));
        this.txtName.setText(intent.getStringExtra("name"));
    }
}