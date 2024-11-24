 package project;
 import java.sql.*;

public class Connectionprovider {
    public static  Connection getCon(){
        try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp","root","bansal");
           return con;
        }
        catch(Exception e){
            return null;
        }
    }
}
