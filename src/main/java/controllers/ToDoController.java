package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ToDoDaoImpImpl;
import io.javalin.http.Context;
import models.ToDo;
import services.ToDoService;

import java.util.List;

/*
* The controller is where we implement the methods
* */

public class ToDoController {

    static ToDoService toDoService = new ToDoService();


    public static void getAllTodos(Context context) throws JsonProcessingException {

        context.contentType("application/json");

        List<ToDo> toDoList = toDoService.getAllTodos();


        String jsonString = new ObjectMapper().writeValueAsString(toDoList);

        context.result(jsonString);
    }

    public static void getOneTodo(Context context) throws JsonProcessingException {

        context.contentType("application/json");


        Integer todoId = Integer.parseInt(context.pathParam("id"));

        ToDo todo = toDoService.getOneTodo(todoId);

        context.result(new ObjectMapper().writeValueAsString(todo));


    }

    public static void createTodo(Context context) throws NullPointerException{

        ToDo todo = context.bodyAsClass(ToDo.class);

        if (toDoService.createTodo(todo)) {
            context.status(201);
            context.result("todo created");
        } else {
            context.status(404);
            context.result("task was too long.... make a task less than 20 characters");
        }


    }

    public static void updateTodo(Context context) throws JsonProcessingException{


        Integer todoId = Integer.parseInt(context.pathParam("id"));

        toDoService.competeToDo(todoId);

        context.result("The task with id " + todoId + " has been completed");
    }

    public static void deleteTodo(Context context) throws JsonProcessingException{

        Integer todoId = Integer.parseInt(context.pathParam("id"));

        toDoService.deleteToDo(todoId);

        context.result("The task with id" + todoId + "has been deleted if it exists");
    }
}
