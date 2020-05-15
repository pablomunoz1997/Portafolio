package Modelo;

import java.sql.*;

public class conexion 
{
private Connection con = null;
    public Connection getConnection()
    {
        try 
        {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
             con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "turismo", "turismoreal");   
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        return con;
    }
    
}