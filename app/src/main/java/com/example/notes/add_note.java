package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class add_note extends AppCompatActivity {

    public EditText title, description;
    public Button add;
    String todayDate, currentTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);



        title = findViewById(R.id.titleinput);
        description = findViewById(R.id.description);

        calendar = Calendar.getInstance();
        todayDate = calendar.get(Calendar.YEAR) + "/" +calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR))+":"+ pad(calendar.get(Calendar.MINUTE));

        Log.d("Calender", "Date and Time" + todayDate+"and"+currentTime);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blinkButton(view);
                Note noteModel = new Note(title.getText().toString(), description.getText().toString(), todayDate, currentTime);
                DatabaseHelper db = new DatabaseHelper(add_note.this);
                db.AddNote(noteModel);

                Intent intent = new Intent(add_note.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(add_note.this, "Note Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String pad(int s) {
        if(s < 0)
            return  "0"+s;
        return String.valueOf(s);
    }

    private void blinkButton(View view){
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.blink_animation));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==  android.R.id.home);
        finish();
        return true;

    }

}