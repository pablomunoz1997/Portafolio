package Modelo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
public class vehiculo extends conexion{
    private String patent;
    private String color;
    private String brand;
    private String model;
    private int year;
    public vehiculo() {
    }

    public vehiculo(String patent, String color, String brand, String model, int year) {
        this.patent = patent;
        this.color = color;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
            this.year = year;
    }
    //CRUD
    public int agregarVehiculo()
    {
        //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call INSERT_VEHICLE(?, ?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString("inPatent", getPatent());
            calla.setString("inColor", getColor());
            calla.setString("inBrand", getBrand());
            calla.setString("inModel", getModel());
            calla.setInt("inYear", getYear());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter("resultado", Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            resultado = (calla.getInt("resultado"));
            return resultado;
        } catch (SQLException e) 
        {
            System.out.println(e);
            return resultado;
        }
    }
    public int eliminarVehiculo()
    {
    int resultado = 0;
    Connection con = getConnection();
    try
    {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call DELETE_VEHICLE(?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString("inPatent", getPatent());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter("resultado", Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            resultado = (calla.getInt("resultado"));
            return resultado;
    } 
    catch (SQLException e) 
    {
            System.out.println(e);
            return resultado;
    }
}
    public ArrayList<vehiculo> buscarVehiculo()
    {
        ArrayList listaVehiculos = new ArrayList();
        ResultSet rs = null;
        vehiculo vh;
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call READ_VEHICLE(?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString(1, getPatent());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(2, OracleTypes.CURSOR);
            
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            rs = (ResultSet) calla.getObject(2);
            //SI SE ENCUENTRA EL RESULTADO
                while (rs.next()) 
                {                
                vh = new vehiculo();
                vh.setPatent(rs.getString("PATENT"));
                vh.setBrand(rs.getString("BRAND"));
                vh.setModel(rs.getString("MODEL"));
                vh.setColor(rs.getString("COLOR"));
                vh.setYear(Integer.parseInt(rs.getString("YEAR")));
                listaVehiculos.add(vh);
                }
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        return listaVehiculos;
    }
    public int actualizarVehiculo()
    {
            //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call UPDATE_VEHICLE(?, ?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString("newPatent", getPatent());
            calla.setString("newBrand", getBrand());
            calla.setString("newColor", getColor());
            calla.setString("newModel", getModel());
            calla.setInt("newYear", getYear());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter("resultado", Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            resultado = (calla.getInt("resultado"));
            return resultado;
        } catch (SQLException e) 
        {
            System.out.println(e);
            return resultado;
        }
    }
    public ArrayList<vehiculo> listarVehiculos()
    {
        ArrayList listaVehiculos = new ArrayList();
        vehiculo vh;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call LISTAR_VEHICLES(?)}");
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) 
            {                
                vh = new vehiculo();
                vh.setPatent(rs.getString("PATENT"));
                vh.setBrand(rs.getString("BRAND"));
                vh.setModel(rs.getString("MODEL"));
                vh.setColor(rs.getString("COLOR"));
                vh.setYear(Integer.parseInt(rs.getString("YEAR")));
                listaVehiculos.add(vh);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaVehiculos;
    }
    public ArrayList<vehiculo> listarPatenteVehiculo()
    {
        ArrayList listarPatente = new ArrayList();
        ResultSet rs = null;
        vehiculo vh;
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call LISTAR_PATENTVEHICLE(?)");
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(1, OracleTypes.CURSOR);
            
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            rs = (ResultSet) calla.getObject(1);
            //SI SE ENCUENTRA EL RESULTADO
                while (rs.next()) 
                {                
                vh = new vehiculo();
                vh.setPatent(rs.getString(1));
                listarPatente.add(vh);
                }
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        return listarPatente;
    }
}
