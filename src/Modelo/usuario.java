package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class usuario extends conexion{
    private String rut;
    private String name;
    private String lastNameF;
    private String lastNameM;
    private String userType;
    private String password;

    public usuario() {
    }

    public usuario(String rut, String name, String lastNameF, String lastNameM, String userType, String password) {
        this.rut = rut;
        this.name = name;
        this.lastNameF = lastNameF;
        this.lastNameM = lastNameM;
        this.userType = userType;
        this.password = password;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//CRUD DE LA CLASE USUARIO
    public int inicioSesion()
    {
        int cantidad=0;
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call LOGIN_USERS(?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString("rutIngresado", getRut());
            calla.setString("passwords", getPassword()); 
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter("tipoUsuario",Types.VARCHAR);
            calla.registerOutParameter("clave",Types.VARCHAR);
            calla.registerOutParameter("cantidad", Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            cantidad = calla.getInt("cantidad");
            setUserType(calla.getString("tipoUsuario"));
        } catch (SQLException e) 
        {
            System.err.println(e);
        }
        return cantidad;
    }  

    public int agregarCliente()
    {
        //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call INSERT_CLIENT(?, ?, ?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString("nrut", getRut());
            calla.setString("name", getName());
            calla.setString("lastnamef", getLastNameF());
            calla.setString("lastnamem", getLastNameM());
            calla.setString("usertype", getUserType());
            calla.setString("password", getPassword());
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

    public int eliminarCliente()
    {
    int resultado = 0;
    Connection con = getConnection();
    try
    {
        //LLAMAR AL PROCEDIMIENTO ALMACENADO
        CallableStatement calla= con.prepareCall("call DELETE_CLIENT(?, ?)");
        //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
        calla.setString("deleterut", getRut());
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

public ArrayList<usuario> buscarCLientes()
    {
        ArrayList listaVehiculos = new ArrayList();
        usuario clientes;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call READ_CLIENT(? ,?)}");
            stmt.setString(1, getRut());
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(2);
            while (rs.next()) 
            {
                clientes = new usuario();
                clientes.setRut(rs.getString("RUT"));
                clientes.setName(rs.getString("USERNAME"));
                clientes.setLastNameF(rs.getString("LASTNAMEF"));
                clientes.setLastNameM(rs.getString("LASTNAMEM"));
                listaVehiculos.add(clientes);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaVehiculos;
    }

    public int actualizarCliente()
    {
        //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call UPDATE_CLIENT(?, ?, ?, ?, ?)");
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
    
    public ArrayList<usuario> listarClientes()
    {
        ArrayList listaVehiculos = new ArrayList();
        usuario clientes;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call LISTAR_CLIENTS(?)}");
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) 
            {
                clientes = new usuario();
                clientes.setRut(rs.getString("RUT"));
                clientes.setName(rs.getString("USERNAME"));
                clientes.setLastNameF(rs.getString("LASTNAMEF"));
                clientes.setLastNameM(rs.getString("LASTNAMEM"));
                clientes.setUserType(rs.getString("USERTYPE"));
                clientes.setPassword(rs.getString("PASS"));
                listaVehiculos.add(clientes);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaVehiculos;
    }
}
