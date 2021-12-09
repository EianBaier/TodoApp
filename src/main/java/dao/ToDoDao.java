package dao; //This is a persistence layer. The data persists when the program closes

import models.ToDo;

import java.util.List;

public interface ToDoDao {
    List<ToDo> getAllToDos();
    ToDo getOneToDo(Integer toDoId);
    void createToDo(ToDo toDo);
    void completeAToDo(Integer toDoId);
    void deleteAToDO(Integer toDoId);


}
