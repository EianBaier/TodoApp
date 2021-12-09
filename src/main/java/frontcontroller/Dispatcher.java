package frontcontroller;

/*
* This is where we are going to route out endpoints to the specific methods
* */

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.ToDoController;
import io.javalin.Javalin;
import models.ToDo;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Dispatcher {

    public Dispatcher(Javalin app){

        /*
        * This is where we will route to the correct endpoint
        * */


        /*
        * REST
        *   -Representational State Transfer
        *   -Architectural standard for HTTP
        *
        *
        * Below are restful endpoints
        *
        * */

       /* app.get("/todo", ToDoController::getAllTodos); //get all todos

        app.get("/todo/{id}", ToDoController::getOneTodo); //get one todo

        app.post("/todo", ToDoController::createTodo); //create todo

        app.patch("/todo/{id}", ToDoController::updateTodo); //update todo

        app.delete("/todo/{id}", ToDoController::deleteTodo); //delete todo*/



        app.routes(() -> {
            path("todo", () ->{

                get(ToDoController::getAllTodos);
                post(ToDoController::createTodo);
                path("{id}", () ->{
                    get(ToDoController::getOneTodo);
                    patch(ToDoController::updateTodo);
                    delete(ToDoController::deleteTodo);

                });

                    });

        });



    }
}
