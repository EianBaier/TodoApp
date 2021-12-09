package frontcontroller;

/*
* Front controller is where endpoints hit first
*
*   This is where our middlewere is going to be
*
* */

import io.javalin.Javalin;

public class FrontController {

    public FrontController(Javalin app){

        /*app.before("/protected/*", context -> {
            System.out.println("before middleware hit");

        });

        app.after("/protected/*", context -> {
            System.out.println("after middleware hit");

        });*/

        app.exception(NumberFormatException.class, ((e, context) -> {
            context.result("Invalid input....Please type a number");
        }));

        new Dispatcher(app);


    }
}
