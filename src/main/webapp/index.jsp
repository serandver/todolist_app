<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.todolist.model.Task" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Todolist</title>
    <meta http-equiv="content-type" content="text/html" charset="utf-8" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}../resources/css/style.css"/> />
    <script type="text/javascript" src="${pageContext.request.contextPath}../resources/js/jquery-2.2.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}../resources/js/javascript.js"></script>
</head>
<body>
<div id="wrapper">
    <div id="wrapper-content">
        <div id="content">
            <h1>Todos</h1>
            <div class="new-task">
                <input type="text" id="newItem" value="What needs to be done?">
                <input type="button" id="add" value="Add" />
            </div>
            <div id="mytasks">
                <c:forEach var="task" items="${listTask}">
                    <div class="wrapper-task">
                        <div class="wrapper-checkbox">
                            <input type="checkbox"/>
                        </div>
                        <div class="task-todo">${task.getTextTask()}</div>
                        <div class="idTask">${task.getId()}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>