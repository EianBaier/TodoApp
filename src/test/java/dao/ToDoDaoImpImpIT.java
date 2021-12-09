package dao;

import models.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
* What are integration tests?
*   -Testing the parts of the code that connect to external services
*       -this is to test the external services are behaving as expected
*
*
* What is H2?
*   -H2 is an "in memory" database. This is essentially a database meant for testing and development.
*
*
*  When you work on a project with a team, there is a limited ammount of connections that cab be made to the db at a time
* */
class ToDoDaoImpImpIT {

    ToDoDao toDoDao;

    public ToDoDaoImpImpIT(){
        this.toDoDao = new ToDoDaoImpImpl(H2Util.url, H2Util.username, H2Util.password);
    }

    @BeforeEach
    void setUp() {
        H2Util.createTable();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropTable();

    }

    @Test
    void getAllToDos() {
        //arrange
        List<ToDo>  expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1,"sweep",false));
        expectedResult.add(new ToDo(2,"mop",false));
        expectedResult.add(new ToDo(3,"vaccume",false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        //act
        List<ToDo>  actualResult = toDoDao.getAllToDos();

        //assert

        //add toString when utilizing h2
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void getOneToDo() {
        //arrange
        List<ToDo>  expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1,"sweep",false));
        expectedResult.add(new ToDo(2,"mop",false));
        expectedResult.add(new ToDo(3,"vaccume",false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));


        //act

        ToDo actualResult = toDoDao.getOneToDo(2);

        //assert
        assertEquals(expectedResult.get(1),actualResult);
    }

    @Test
    void createToDo() {
        //arrange
        List<ToDo>  expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1,"sweep",false));
        expectedResult.add(new ToDo(2,"mop",false));
        expectedResult.add(new ToDo(3,"vaccume",false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));



        //act
        Integer actualResult = toDoDao.getAllToDos().size();

        //assert

        assertEquals(expectedResult.size(),actualResult);
    }

    @Test
    void completeAToDo() {
        ToDo toDoToPass = new ToDo(1,"sweep",false);
        ToDo expectedResult = new ToDo(1,"sweep",true);
        toDoDao.createToDo(toDoToPass);


        toDoDao.completeAToDo(toDoToPass.getId());
        ToDo actualResult = toDoDao.getOneToDo(toDoToPass.getId());


        assertEquals(expectedResult,actualResult);
    }

    @Test
    void deleteAToDO() {
        List<ToDo>  expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1,"sweep",false));
        expectedResult.add(new ToDo(2,"mop",false));
        expectedResult.add(new ToDo(3,"vaccume",false));
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));


        toDoDao.deleteAToDO(2);
        expectedResult.remove(1);

        List<ToDo> actualResult = toDoDao.getAllToDos();

        assertEquals(expectedResult, actualResult);



    }
}