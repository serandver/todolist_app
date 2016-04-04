package com.todolist.database;

import java.sql.SQLException;

public class Main {
    public static void main(String[] argv) {
        DBTasks dbTasks = new DBTasks();

        try {
//            dbTasks.createTable();
//            dbTasks.insertNewTaskToDB(new Task("Почистить кэш!"));
            System.out.println(dbTasks.insertNewTaskToDB(new Task("Полить вазоны")));
//            dbTasks.insertNewTaskToDB(new Task("Приготовить обед"));
//            dbTasks.insertNewTaskToDB(new Task("Погладить кота"));
//            dbTasks.readAllTasksFromDB();
//            dbTasks.readTaskFromDB(2);
//            dbTasks.updateTaskInDB(4, "дать коту пожрать");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        dbTasks.deleteTaskFromDB(4);
    }
}
