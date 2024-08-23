package com.example.fitness;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workouts")
public class Workout {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int duration;
    private int caloriesBurned;

    public Workout(String name, int duration, int caloriesBurned) {
        this.name = name;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;

    }

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getDuration(){
        return duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public int getCaloriesBurned(){
        return caloriesBurned;
    }
    public void setCaloriesBurned(int caloriesBurned){
        this.caloriesBurned = caloriesBurned;
    }


}
