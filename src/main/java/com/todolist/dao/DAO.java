package com.todolist.dao;

import com.todolist.model.Task;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO {
    //create new task
    int insertNewTaskToDB(Task task) throws SQLException;
    //read all tasks from database
    ArrayList<Task> readAllTasksFromDB () throws SQLException;
    //read task from database by id
    void readTaskFromDB (int id) throws SQLException;
    //update selected task
    void updateTaskInDB (int id, String string) throws SQLException;
    //delete task from database
    void deleteTaskFromDB (int id);
}
