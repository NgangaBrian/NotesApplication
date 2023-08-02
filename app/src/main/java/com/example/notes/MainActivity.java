package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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