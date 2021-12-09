package dao;

import models.ToDo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoDaoImpImpl implements  ToDoDao{

    /*
     * What is a DAO?
     *Data Access Object
     *   -The dao isolated the persistence layer from application logic
     *   -Generally there is a doa for each model
     *
     * What is JDBC?
     *
     * Java Data Base Connection
     *
     * JDBC is a technology that allows java to connect to a database
     *   -JDBC is modular. It doesnt just connect to one specific database...it can connect depending on database driver
     *
     *
     * What is needed to connect to a database using JDBC?
     *   -database url
     *   -db username
     *   -db password
     *   -specific driver for you dbms
     *
     *
     *
     * Important classes and interfaces that utilize JDBC
     *   -DRiverManager (CLass)
     *       class for managing JDBC drivers
     *
     *   -Connection(Interface)
     *       active connection with our database
     *
     *   -Statement (interface)
     *       object that represents our SQL statement
     *       - The Statement interface itself does not protect against SQL injection
     *       -PreparedStatement (interface)
     *           -protects agaisnt SQL injection
     *       -CallableStatement (interface)
     *           -used for stored procedures
     *
     *   -ResultSet (interface)
     *       object that will have our results
     *
     *
     * */
    /*jdbc:postgressql://[your endpoint here]/[the database you are trying to acess]*/
    String url;
    String username ;
    String password ;

    Logger logger = Logger.getLogger(ToDoDaoImpImpl.class);

    public ToDoDaoImpImpl(){
        this.url = "jdbc:postgresql://"+ System.getenv("AWS_RDS_ENDPOINT")+ "/tododatabase";
        this.username = System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ToDoDaoImpImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }


    @Override
    public List<ToDo> getAllToDos() {

        List<ToDo> todos = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //generate out connection
            // Connection conn = DriverManager.getConnection(url, username, password);

            // sql that we will be executing
            String sql = "SELECT * FROM todo ORDER BY id;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //execute the aql statement and return the result set
            ResultSet rs = ps.executeQuery();


            //itterate through the result set
            while(rs.next()){

                todos.add(new ToDo(rs.getInt(1),rs.getString(2), rs.getBoolean(3)));

            }

              //  conn.close(); putting the Conection in the try is called try with resouces and automatically closes it

        }catch (SQLException e){
           logger.error(e);
        }
        return todos;
    }

    @Override
    public ToDo getOneToDo(Integer toDoId) {

        ToDo toDo = null;

        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //generate out connection
            // Connection conn = DriverManager.getConnection(url, username, password);

            // sql that we will be executing
            String sql = "SELECT * FROM todo WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //we are setting the value of the question
            ps.setInt(1,toDoId);

            //execute the aql statement and return the result set
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                toDo = new ToDo(rs.getInt(1),rs.getString(2), rs.getBoolean(3));

            }

            //  conn.close(); putting the Conection in the try is called try with resouces and automatically closes it

        }catch (SQLException e){
            e.printStackTrace();
        }
        return toDo;
    }

    @Override
    public void createToDo(ToDo toDo) {



        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //generate out connection
            // Connection conn = DriverManager.getConnection(url, username, password);

            // sql that we will be executing
            String sql = "INSERT INTO todo VALUES (DEFAULT,?,DEFAULT);";
            PreparedStatement ps = conn.prepareStatement(sql);

            // set value of question mark the parameter is which question mark you want to asain
            ps.setString(1, toDo.getTask());

            //execute the update
            ps.executeUpdate();



        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    @Override
    public void completeAToDo(Integer toDoId) {

        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //generate out connection
            // Connection conn = DriverManager.getConnection(url, username, password);

            // sql that we will be executing
            String sql = "UPDATE todo SET completed = TRUE WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // set value of question mark the parameter is which question mark you want to asain
            ps.setInt(1, toDoId);

            //execute the update
            ps.executeUpdate();



        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAToDO(Integer toDoId) {

        try(Connection conn = DriverManager.getConnection(url, username, password);) {
            //generate out connection
            // Connection conn = DriverManager.getConnection(url, username, password);

            // sql that we will be executing
            String sql = "DELETE FROM todo WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // set value of question mark the parameter is which question mark you want to asain
            ps.setInt(1, toDoId);

            //execute the update
            ps.executeUpdate();



        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}
