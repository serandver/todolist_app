package com.todolist.dao;

import com.todolist.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DAOImpl implements DAO{
//    private static final String URL = "jdbc:postgresql://localhost:5432/todolist";
    private static final String URL = "jdbc:mysql://eu-cdbr-west-01.cleardb.com:3306/heroku_9e1e9dd0f20dec3";
    private static final String USERNAME = "b36f008c4cf8a4";
//    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "c0fd4343";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DAOImpl() {
        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("useSSL", "false");
        try {
//            Class.forName("org.postgresql.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, properties);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE tasks("
                + "TASK_ID INT(11) NOT NULL AUTO_INCREMENT,"
                + "TASK_TEXT VARCHAR(100) NOT NULL, "
                + "TASK_STATUS BOOLEAN, "
                + "PRIMARY KEY (TASK_ID) "
                + ")";

        try (Statement statement = connection.createStatement()){
            statement.execute(createTableSQL);
            System.out.println("Table \"tasks\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*CRUD: create new task in DB*/
    public int insertNewTaskToDB(Task task) throws SQLException {
        String insertTableSQL = "INSERT INTO tasks (TASK_TEXT) VALUES('" + task.getTextTask() + "')";
        int lastId=-1;

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
            System.out.println("New task was added!");
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            lastId = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lastId;
    }

    /*CRUD: read all tasks from DB*/
    public ArrayList<Task> readAllTasksFromDB () throws SQLException {
        String selectTableSQL = "SELECT TASK_ID, TASK_TEXT from tasks";
        ArrayList<Task> list = new ArrayList<>();
        Task task = null;

        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(selectTableSQL);

            while (rs.next()) {
                String taskId = rs.getString("TASK_ID");
                String taskText = rs.getString("TASK_TEXT");
                String taskStatus = rs.getString("TASK_STATUS");

                task=new Task(taskText);
                task.setId(Integer.parseInt(taskId));
                task.setTextStatus(Boolean.parseBoolean(taskStatus));
                list.add(task);

                System.out.println("taskId : " + taskId);
                System.out.println("taskText : " + taskText);
                System.out.println("taskStatus : " + taskText);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /*CRUD: read task by it's ID from DB*/
    public void readTaskFromDB (int id) throws SQLException {
        String textTask;
        String selectTableSQL = "SELECT TASK_ID, TASK_TEXT FROM tasks WHERE TASK_ID="+id;

        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                textTask = rs.getString("TASK_TEXT");
                System.out.println(textTask);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*CRUD: update task by it's ID in DB*/
    public void updateTaskInDB (int id, String string) throws SQLException {
        String updateTableSQL = "UPDATE tasks SET TASK_TEXT = '"+string+"' WHERE TASK_ID = "+id;

        try (Statement statement = connection.createStatement()){
            statement.execute(updateTableSQL);
            System.out.println("The task id="+id+" was updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*CRUD: remove task by it's ID from DB*/
    public void deleteTaskFromDB (int id) {
        String deleteTableSQL = "DELETE FROM tasks WHERE TASK_ID = "+id;

        try (Statement statement = connection.createStatement()){
            statement.execute(deleteTableSQL);
            System.out.println("The task id="+id+" was deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
