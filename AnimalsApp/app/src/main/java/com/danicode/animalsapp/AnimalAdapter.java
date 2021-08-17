package com.danicode.animalsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalRowHolder> {

    ArrayList<Animal> animalData;
    Context context;
    MyClickInterface myClickInterface;

    public AnimalAdapter(ArrayList<Animal> animalData, Context context, MyClickInterface myClickInterface) {
        this.context = context;
        this.animalData = animalData;
        this.myClickInterface = myClickInterface;
    }

    @NonNull
    @Override
    public AnimalRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.animal_row, parent, false);
        return new AnimalRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalRowHolder holder, int position) {
        holder.txtAnimalName.setText(animalData.get(position).getName());
        holder.imgAnimal.setImageResource(animalData.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return this.animalData.size();
    }

    // Para hacer clic en la tarjeta
    interface MyClickInterface {
        void onItemClick(int position);
    }

    class AnimalRowHolder extends RecyclerView.ViewHolder {
        TextView txtAnimalName;
        ImageView imgAnimal;

        public AnimalRowHolder(@NonNull View itemView) {
            super(itemView);
            this.txtAnimalName = itemView.findViewById(R.id.txt_animal_name);
            this.imgAnimal = itemView.findViewById(R.id.img_animal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }


}
