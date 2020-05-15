
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class departamento extends conexion{
    private int id;
    private String region;
    private String address;
    private String floor;
    private int dirr;
    private int price;
    private String state;

    public departamento() {
    }

    public departamento(int id, String region, String address, String floor,int dirr, int price, String state) {
        this.id = id;
        this.region = region;
        this.address = address;
        this.floor = floor;
        this.dirr = dirr;
        this.price = price;
        this.state = state;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
    
    public int getDirr() {
        return dirr;
    }

    public void setDirr(int dirr) {
        this.dirr = dirr;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
//CRUD
    public boolean agregarDepartamento()
    {
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call INSERT_DEPARTMENT(?, ?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString(1, getRegion());
            calla.setString(2, getAddress());
            calla.setString(3, getFloor());
            calla.setInt(4, getPrice());
            calla.setString(5, getState());
            calla.setInt(6, getDirr());

            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();

            return true;
        } catch (SQLException e) 
        {
            System.out.println(e);
            return false;
        }
    }
    public int eliminarDepartamento()
    {
        int resultado = 0;
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call DELETE_DEPARTMENT(?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setInt("deleteId", getId());
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
        public int actualizarDepartamento()
    {
        //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call UPDATE_DEPARTMENT(?, ?, ?, ?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setInt("newId", getId());
            calla.setString("newRegion", getRegion());            
            calla.setString("newAddress", getAddress());            
            calla.setString("newFloor", getFloor());
            calla.setInt("newPrice", getPrice());
            calla.setString("newState", getState());
            calla.setInt("newDirr", getDirr());
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
    public ArrayList<departamento> listarDepartamentos()
    {
        ArrayList listarDepartamentos = new ArrayList();
        departamento depa;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call LISTAR_DEPARTMENTS(?)}");
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) 
            {                
                depa = new departamento();
                depa.setId(Integer.parseInt(rs.getString("id")));
                depa.setRegion(rs.getString("region"));
                depa.setAddress(rs.getString("address"));
                depa.setFloor(rs.getString("floor"));
                depa.setPrice(Integer.parseInt(rs.getString("price")));
                depa.setState(rs.getString("state"));
                depa.setDirr(Integer.parseInt(rs.getString("dirr")));
                listarDepartamentos.add(depa);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listarDepartamentos;
    }
    public ArrayList<departamento> BuscarDepartamento()
    {
        ArrayList listarDepartamentos = new ArrayList();
        departamento depa;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call READ_DEPARTMENTS(?,?,?)}");
            //ENTREADA
            stmt.setString(1, getRegion());
            stmt.setString(2, getState());
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(3, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(3);
            while (rs.next()) 
            {                
                depa = new departamento();
                depa.setId(Integer.parseInt(rs.getString("id")));
                depa.setRegion(rs.getString("region"));
                depa.setAddress(rs.getString("address"));
                depa.setFloor(rs.getString("floor"));
                depa.setPrice(Integer.parseInt(rs.getString("price")));
                depa.setState(rs.getString("state"));
                depa.setDirr(Integer.parseInt(rs.getString("dirr")));
                listarDepartamentos.add(depa);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listarDepartamentos;
    }
        public ArrayList<departamento> listarIdDepartamento()
    {
        ArrayList listarIdDepa = new ArrayList();
        departamento depa;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call LISTAR_IDDEPARTMENT(?)}");
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) 
            {                
                depa = new departamento();
                depa.setId(Integer.parseInt(rs.getString(1)));
                listarIdDepa.add(depa);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listarIdDepa;
    }
}
