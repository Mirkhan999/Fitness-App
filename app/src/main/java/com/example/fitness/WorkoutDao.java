package com.example.fitness;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao

public interface WorkoutDao {
    @Insert
    void insert(Workout workout);
    @Update
    void update(Workout workout);
    @Delete
    void delete(Workout workout);
    @Query("SELECT * FROM workouts")
    List<Workout> getAllWorkouts();

    @Query("SELECT * FROM workouts WHERE id = :id")
    Workout getWorkoutById(int id);
}
