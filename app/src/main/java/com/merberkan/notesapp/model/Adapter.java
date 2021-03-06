package com.merberkan.notesapp.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.merberkan.notesapp.NoteDetails;
import com.merberkan.notesapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> titles;
    List<String> contents;

    // It will come from main
    public Adapter(List<String> title, List<String> content){
        this.titles = title;
        this.contents = content;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.noteTitle.setText(titles.get(position));
        holder.noteContent.setText(contents.get(position));
        int code = getRandomColor();
        holder.myCardView.setCardBackgroundColor(holder.view.getResources().getColor(code,null));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteDetails.class);
                intent.putExtra("title",titles.get(position));
                intent.putExtra("content",contents.get(position));
                intent.putExtra("code",code);
                v.getContext().startActivity(intent);
            }
        });
    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.blue);
        colorCode.add(R.color.yellow);
        colorCode.add(R.color.skyblue);
        colorCode.add(R.color.lightPurple);
        colorCode.add(R.color.lightGreen);
        colorCode.add(R.color.gray);
        colorCode.add(R.color.pink);
        colorCode.add(R.color.red);
        colorCode.add(R.color.greenlight);
        colorCode.add(R.color.notgreen);

        Random randomColor = new Random();
        int index = randomColor.nextInt(colorCode.size());
        return colorCode.get((index));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView noteTitle, noteContent;
        View view;
        CardView myCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.titles);
            noteContent = itemView.findViewById(R.id.content);
            // I created view to handle click to this view in next steps
            myCardView = itemView.findViewById(R.id.noteCard);
            view = itemView;
        }
    }
}
