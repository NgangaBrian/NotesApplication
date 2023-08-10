package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    List<Note> noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        noteList = databaseHelper.getNote();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(MainActivity.this, noteList, databaseHelper, new Adapter.OnNoteLongClickListener() {
            @Override
            public void onNoteLongClick(int position) {
                MainActivity.this.onNoteLongClick(position);
            }
        });
        recyclerView.setAdapter(adapter);

        FloatingActionButton addnewbtn = findViewById(R.id.addnewbtn);
        addnewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blinkButton(view);
                Intent intent = new Intent(MainActivity.this, add_note.class);
                startActivity(intent);

            }
        });
    }



    private void onNoteLongClick (int position){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete Note");
            builder.setMessage("Are you sure you want to delete this note?");
            builder.setPositiveButton("Delete", (dialog, which) ->{
                deleteNoteFromDatabase(position);
                dialog.dismiss();
                    });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
            builder.show();

        }
        public void deleteNoteFromDatabase(int position){
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            int id = noteList.get(position).getId();
            boolean deleted = databaseHelper.deleteNoteById(id);
            if(deleted) {
                noteList.remove(position);
                adapter.notifyItemRemoved(position);
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
            }
        }

    private void blinkButton(View view){
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink_animation));
    }
}