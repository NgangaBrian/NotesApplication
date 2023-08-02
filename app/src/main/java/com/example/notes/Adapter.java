package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Note> noteModel;

    Adapter(Context context, List<Note> noteModel){
        this.inflater = LayoutInflater.from(context);
        this.noteModel = noteModel;

    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = noteModel.get(position).getTitle();
        String note = noteModel.get(position).getDescription();
        String time = noteModel.get(position).getNoteDate() +"  "+ noteModel.get(position).getNoteTime();

        holder.nTitle.setText(title);
        holder.nDescription.setText(note);
        holder.nTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return noteModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nTitle, nDescription, nTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nTitle = itemView.findViewById(R.id.nTitle);
            nDescription = itemView. findViewById(R.id.nDescription);
            nTime = itemView.findViewById(R.id.nDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
