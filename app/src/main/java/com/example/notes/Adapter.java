package com.example.notes;

import static android.app.ProgressDialog.show;

import android.content.Context;
import android.content.Intent;
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
    Context context;
    private DatabaseHelper databaseHelper;
    private OnNoteLongClickListener onNoteLongClickListener;

    Adapter(Context context, List<Note> noteModel, DatabaseHelper databaseHelper, OnNoteLongClickListener onNoteLongClickListener){
        this.inflater = LayoutInflater.from(context);
        this.noteModel = noteModel;
        this.context = context;
        this.databaseHelper = databaseHelper;
        this.onNoteLongClickListener = onNoteLongClickListener;


    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        int id = noteModel.get(position).getId();
        String title = noteModel.get(position).getTitle();
        String note = noteModel.get(position).getDescription();
        String time = noteModel.get(position).getNoteDate() +"  "+ noteModel.get(position).getNoteTime();

        holder.nTitle.setText(id +". " + title);
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

            itemView.setOnClickListener(view ->  {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Note clickedNote = noteModel.get(position);

                        // Create an intent to start the NoteView activity and pass the necessary data
                        Intent intent = new Intent(context, NoteView.class);


                        context.startActivity(intent);
                    Toast.makeText(itemView.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                }}
            );
            itemView.setOnLongClickListener(view ->  {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    if(onNoteLongClickListener != null){
                        onNoteLongClickListener.onNoteLongClick(position);
                    }
                }
                return true;
            });

        }
    }
    public interface OnNoteLongClickListener {
        void onNoteLongClick(int position);
    }
}
