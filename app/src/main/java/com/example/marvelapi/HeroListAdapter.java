package com.example.marvelapi;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.example.marvelapi.models.character.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HeroListAdapter extends RecyclerView.Adapter<HeroListAdapter.ViewRow> {
    private ArrayList<Result> heroes;
    private OnClickId onClickId;
    public HeroListAdapter(ArrayList<Result> heroes, OnClickId callback){
        this.heroes = heroes;
        onClickId = callback;
    }

    public interface OnClickId{
        public void sendId(Integer id);
    }

    @NonNull
    @Override
    public ViewRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_row, parent, false);
        return new ViewRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRow holder, int position) {
        holder.itemView.setOnClickListener(view -> {
            Log.d("APIE", "onBindViewHolder: ");
            onClickId.sendId(heroes.get(position).getId());
            Navigation.findNavController(holder.itemView).navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
        Picasso.get().load(heroes.get(position).getThumbnail().getPath().replaceFirst("http", "https")
                + "/standard_small."
                + heroes.get(position).getThumbnail().getExtension()).into(holder.image);

        holder.name.setText(heroes.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if(heroes == null){
            return 0;
        }
        else {
            return heroes.size();
        }
    }


    class ViewRow extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;

        public ViewRow(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.heroName);
            image = itemView.findViewById(R.id.heroImage);
        }
    }
}
