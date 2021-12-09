import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ToDoDao;
import dao.ToDoDaoImpImpl;
import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;
import models.ToDo;
import services.ToDoService;

import java.util.List;

public class Main {
    //static ToDoService toDoService = new ToDoService(new ToDoDaoImpImpl());

    public static void main(String[] args) {

            Javalin app = Javalin.create(config ->{
               config.addStaticFiles("/frontend", Location.CLASSPATH);
            }).start(9000);



        //Javalin app = Javalin.create().start(9000);

        new FrontController(app);

        /*
         * middleware
         * */

        /*
         * Wht is json?
         * JSON stands for JavaScript Object Notation
         *   json is a universal format for sharing and transporting data
         *   json stores data in key value pairs
         *
         * This is what JSON code looks like
         * {
         *   "id":1,
         *   "task: "This is my task",
         *   "completed": false
         * }
         *
         * */







    }


    }



