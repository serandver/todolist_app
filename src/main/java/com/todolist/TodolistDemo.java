package com.todolist;

import com.todolist.dao.DAOImpl;
import com.todolist.model.Task;

import java.sql.SQLException;

public class TodolistDemo {
    public static void main(String[] argv) {
        DAOImpl dbTasks = new DAOImpl();

        try {
            dbTasks.createTable();
            dbTasks.insertNewTaskToDB(new Task("Почистить кэш!"));
            dbTasks.insertNewTaskToDB(new Task("Приготовить обед"));
            dbTasks.insertNewTaskToDB(new Task("Погладить кота"));
            dbTasks.readAllTasksFromDB();
            dbTasks.readTaskFromDB(2);
            dbTasks.updateTaskInDB(4, "дать коту пожрать");
            dbTasks.readAllTasksFromDB();
            dbTasks.deleteTaskFromDB(4);
            dbTasks.readAllTasksFromDB();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
