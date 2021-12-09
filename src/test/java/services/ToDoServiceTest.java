package services;

import dao.ToDoDao;
import models.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {

    ToDoDao toDoDao = Mockito.mock((ToDoDao.class));

    ToDoService toDoService;

    public  ToDoServiceTest(){

        this.toDoService = new ToDoService(toDoDao);
    }

    @Test
    void getAllTodos() {
        //arrange
        List<ToDo> todos = new ArrayList<>();
        todos.add(new ToDo(1,"sweep", false));
        todos.add(new ToDo(2,"mop", false));
        todos.add(new ToDo(3,"Listen to Kevin Talk", false));
        List<ToDo> expectedValues = todos;
        Mockito.when(toDoDao.getAllToDos()).thenReturn(todos);

        //act

        List<ToDo> actualResult = toDoService.getAllTodos();

        //assert
        assertEquals(expectedValues,actualResult);
    }

    @Test
    void getOneTodo() {
        //arrange
        ToDo expectedResult = new ToDo(1,"sweep",false);
        Mockito.when(toDoDao.getOneToDo(expectedResult.getId())).thenReturn(expectedResult);


        //act
        ToDo actualResult = toDoService.getOneTodo(expectedResult.getId());

        //assert

        assertEquals(expectedResult,actualResult);
    }

    @Test
    void createTodoGreaterThan20() {
        //arrange
        ToDo toDo = new ToDo(1,"sweep and this needs to be greater than 20 characters and now it is",false);

        Boolean expectedResult = false;



        //act

        Boolean actualResult = toDoService.createTodo(toDo);


        //assert

        assertFalse(actualResult);

    }

    @Test
    void createTodoLessThanOrEquals20() {
        //arrange

        ToDo toDo = new ToDo(1,"less than 20????????",false);

        //act
        Boolean actualResult = toDoService.createTodo(toDo);

        //assert

        assertTrue(actualResult);

    }

    @Test
    void competeToDo() {
        //arrange
        Integer toDoId = 1;


        //act

        toDoService.competeToDo(toDoId);


        //assert

        Mockito.verify(toDoDao).completeAToDo(toDoId);
    }

    @Test
    void deleteToDo() {

        //arrange
        Integer toDoId = 1;


        //act

        toDoService.deleteToDo(toDoId);


        //assert

        Mockito.verify(toDoDao).deleteAToDO(toDoId);
    }
}