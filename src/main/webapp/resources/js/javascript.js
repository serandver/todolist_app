$(document).ready(function() {

    //set empty field on focus, default field on blur
    $('#newItem').focus(function() {
        if (this.value=='What needs to be done?') {this.value=''}
    });
    $('#newItem').blur(function() {
        if (this.value=='') {this.value='What needs to be done?'}
    });

    //event "add a new task on click" handler
    $("#add").click( function() {
        var textNewTask = $("#newItem").val();
        if (textNewTask == "What needs to be done?") {
            alert("Please write your task");
        }
        else if (textNewTask !== "") { function foo(result) {

        }
            $.ajax({
                type: 'POST',
                url: '/Servlet',
                data: {textNewTask: textNewTask},
                success: function (result) {
                    var res = JSON.parse(result);
                    $("#mytasks").append(
                        "<div class=\"wrapper-task\">" +
                        "<div class=\"wrapper-checkbox\">" +
                        "<input type=\"checkbox\"/>" +
                        "</div>" +
                        "<div class=\"task-todo\">" + res.textTask + "</div>" +
                        "<div class=\"idTask\">" + res.id + "</div>" +
                        "</div>"
                    )
                }
            });

            $("#newItem").val("What needs to be done?");
        }
        else {
            alert("Please write your task");
        }
    });

    //event "set a task done" handler
    $('#mytasks').on("click", "input", function() {
        $(this).parent('div').parent('div').children('.task-todo').toggleClass('itemDone');
        todolist.itemsCompletedCount += 1;
        todolist.itemsLeftCount = todolist.itemsLeftCount - 1;
    });



}); //end ready
