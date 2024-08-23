package com.example.fitness;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvWorkoutTitle;
    private Button btnStartWorkout;
    private ProgressBar progressBar;
    private boolean isWorkoutActive;
    private WorkoutDatabase workoutDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvWorkoutTitle = findViewById(R.id.tvWorkoutTitle);
        btnStartWorkout = findViewById(R.id.btnStartWorkout);
        progressBar = findViewById(R.id.progressBar);

        workoutDatabase = WorkoutDatabase.getInstance(this);
        btnStartWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWorkoutActive){
                    stopWorkout();
                } else {
                    startWorkout();
                }
            }
        });
        loadWorkouts();
    }
    private void startWorkout(){
        isWorkoutActive=true;
        progressBar.setVisibility(View.VISIBLE);
        btnStartWorkout.setText("Stop Workout");
        tvWorkoutTitle.setText("Workout in Progress");

        Workout workout = new Workout("Morning Run",30, 300);
        saveWorkout(workout);
    }
    private void stopWorkout(){
        isWorkoutActive= false;
        progressBar.setVisibility(View.GONE);
        btnStartWorkout.setText("start Workout");
        tvWorkoutTitle.setText("Workout Stopped");
    }
    private void saveWorkout(Workout workout){
        new SaveWorkoutTask().execute(workout);
    }
    private void loadWorkouts(){
        new LoadWorkoutsTask().execute();
    }
    private class SaveWorkoutTask extends AsyncTask<Workout, Void, Void> {
        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDatabase.workoutDao().insert(workouts[0]);
            return null;
        }


    }

    private class LoadWorkoutsTask extends AsyncTask<Void, Void, List<Workout>> {
        @Override
        protected List<Workout> doInBackground(Void... voids) {
            return workoutDatabase.workoutDao().getAllWorkouts();
        }

        @Override
        protected void onPostExecute(List<Workout> workouts) {
            for (Workout workout : workouts) {
                tvWorkoutTitle.setText("Workout:" + workout.getName());
            }
        }
    }
}