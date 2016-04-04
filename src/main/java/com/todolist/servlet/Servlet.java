package com.todolist.servlet;

import com.google.gson.Gson;
import com.todolist.database.DBTasks;
import com.todolist.database.Task;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class Servlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBTasks dbTasks = new DBTasks();
        ArrayList listTask=null;
        try {
            listTask = dbTasks.readAllTasksFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("listTask", listTask);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBTasks dbTasks = new DBTasks();
        String textTask = req.getParameter("textNewTask");
        Task newTask = new Task(textTask);
        int taskId=-1;
        try {
            taskId = dbTasks.insertNewTaskToDB(newTask);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         if (taskId!=-1) {
            newTask.setId(taskId);
        }
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(new Gson().toJson(newTask));
    }
}