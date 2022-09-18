package com.example.marvelapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelapi.models.comics.ComicsResult;

import java.util.ArrayList;

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.ViewRow> {
    private ArrayList<ComicsResult> comicsList;

    public ComicsListAdapter(ArrayList<ComicsResult> comicsList) {
        this.comicsList = comicsList;
    }

    @NonNull
    @Override
    public ViewRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_row, parent, false);
        return new ViewRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRow holder, int position) {
        holder.title.setText(comicsList.get(position).getTitle());
        holder.description.setText(comicsList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if (comicsList == null){
            return 0;
        }
        else{
            return comicsList.size();
        }
    }

    class ViewRow extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        public ViewRow(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleView);
            description = itemView.findViewById(R.id.descriptionView);

        }
    }
}
