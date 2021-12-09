package services;

import dao.ToDoDao;
import dao.ToDoDaoImpImpl;
import models.ToDo;

import java.util.List;

public class ToDoService {
    ToDoDao toDoDao;

    public ToDoService(ToDoDao toDoDao){

        this.toDoDao = toDoDao;
    }

    public ToDoService(){
        this.toDoDao = new ToDoDaoImpImpl();

    }

    public List<ToDo> getAllTodos(){

        return toDoDao.getAllToDos();


    }

    public ToDo getOneTodo(Integer id){

        return toDoDao.getOneToDo(id);
    }

    // business logic example
    public Boolean createTodo(ToDo toDo) throws NullPointerException{

        if(toDo.getTask().length() > 20 )
            return false;


        toDoDao.createToDo(toDo);
        return true;
    }

    public void competeToDo(Integer toDoId){

        toDoDao.completeAToDo(toDoId);
    }

    public void deleteToDo(Integer toDoId){

        toDoDao.deleteAToDO(toDoId);
    }

}
