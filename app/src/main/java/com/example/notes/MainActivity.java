package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        Adapter adapter;
        List<Note> noteList;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        DatabaseHelper  noteDatabase = new DatabaseHelper(this);
        noteList = noteDatabase.getNote();
        if(noteList.isEmpty()){
            Intent intent = new Intent(MainActivity.this, add_note.class);
            startActivity(intent);
        }
        else {

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setReverseLayout(true);
            layoutManager.setStackFromEnd(true);

            recyclerView.setLayoutManager(layoutManager);
            adapter = new Adapter(this, noteList);
            recyclerView.setAdapter(adapter);
        }

        Button addnewbtn = findViewById(R.id.addnewbtn);
        addnewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blinkButton(view);
                Intent intent = new Intent(MainActivity.this, add_note.class);
                startActivity(intent);
            }
        });
    }
    private void blinkButton(View view){
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink_animation));
    }
}