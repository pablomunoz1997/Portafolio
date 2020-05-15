package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class conductor extends conexion
{
    private String rut;
    private String name;
    private String lastNameF;
    private String lastNameM;

    public conductor() {
    }

    public conductor(String rut, String name, String lastNameF, String lastNameM) {
        this.rut = rut;
        this.name = name;
        this.lastNameF = lastNameF;
        this.lastNameM = lastNameM;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameF() {
        return lastNameF;
    }

    public void setLastNameF(String lastNameF) {
        this.lastNameF = lastNameF;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }
    
    //CRUD DE LA VISTA CONDUCTOR
    public int agregarConductor()
    {
        //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call INSERT_DRIVER(?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString("newRut", getRut());
            calla.setString("newnName", getName());
            calla.setString("newLastNameF", getLastNameF());
            calla.setString("newlastNameM", getLastNameM());
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
    public int eliminarConductor()
    {
    int resultado = 0;
    Connection con = getConnection();
    try
    {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call DELETE_DRIVER(?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString(1, getRut());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(2, Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            resultado = (calla.getInt(2));
            return resultado;
    } 
    catch (SQLException e) 
    {
            System.out.println(e);
            return resultado;
    }
}
    public int actualizarConductor()
    {
            //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call UPDATE_DRIVER(?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString("newRut", getRut());
            calla.setString("newnName", getName());
            calla.setString("newLastNameF", getLastNameF());
            calla.setString("newlastNameM", getLastNameM());
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
    public ArrayList<conductor> buscarConductor()
    {
        ArrayList buscarConductor = new ArrayList();
        ResultSet rs = null;
        conductor conduct;
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call READ_DRIVER(?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString(1, getRut());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(2, OracleTypes.CURSOR);
            
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            rs = (ResultSet) calla.getObject(2);
            //SI SE ENCUENTRA EL RESULTADO
                while (rs.next()) 
                {                
                conduct = new conductor();
                conduct.setRut(rs.getString("RUT"));
                conduct.setName(rs.getString("NAME"));
                conduct.setLastNameF(rs.getString("LASTNAMEF"));
                conduct.setLastNameM(rs.getString("LASTNAMEM"));
                buscarConductor.add(conduct);
                }
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        return buscarConductor;
    }
    public ArrayList<conductor> listarConductores()
    {
        ArrayList listaVehiculos = new ArrayList();
        conductor conduct;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call LISTAR_DRIVERS(?)}");
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) 
            {                
                conduct = new conductor();
                conduct.setRut(rs.getString("RUT"));
                conduct.setName(rs.getString("NAME"));
                conduct.setLastNameF(rs.getString("LASTNAMEF"));
                conduct.setLastNameM(rs.getString("LASTNAMEM"));
                listaVehiculos.add(conduct);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaVehiculos;
    }
        public ArrayList<conductor> listarRutConductor()
    {
        ArrayList listarRutConductor = new ArrayList();
        ResultSet rs = null;
        conductor c;
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call LISTAR_RUTDRIVER(?)");
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(1, OracleTypes.CURSOR);
            
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            rs = (ResultSet) calla.getObject(1);
            //SI SE ENCUENTRA EL RESULTADO
                while (rs.next()) 
                {                
                c = new conductor();
                c.setRut(rs.getString(1));
                listarRutConductor.add(c);
                }
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        return listarRutConductor;
    }
}
