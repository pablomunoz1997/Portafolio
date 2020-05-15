/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Omar
 */
public class descuento extends conexion{
    private int id;
    private String nombre;
    private int porcentaje;

    public descuento() {
    }

    public descuento(int id, String nombre, int porcentaje) {
        this.id = id;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
//CRUD
    public boolean agregarDescuento()
    {
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try 
        {
            stmt = con.prepareCall("{call INSERT_DISCOUNT(?,?)}");
            stmt.setString(1, getNombre());
            stmt.setInt(2, getPorcentaje());
            stmt.execute();
            
            return true;  
        } 
        catch (Exception e) 
        {
            System.out.println(e);
            return false;
        }
    }
        public int actualizarDescuento()
    {
        //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call UPDATE_DISCOUNT(?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setInt(1, getId());
            calla.setString(2, getNombre());
            calla.setInt(3, getPorcentaje());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(4, Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            resultado = (calla.getInt(4));
            return resultado;
        } catch (SQLException e) 
        {
            System.out.println(e);
            return resultado = 5;
        }
    }
        public int eliminarDescuento()
    {
        int resultado = 0;
        CallableStatement stmt = null;
        Connection con = getConnection();
        try 
        {
            stmt = con.prepareCall("{call DELETE_DISCOUNT(?,?)}");
            //DATOS DE ENTRADA
            stmt.setInt(1, getId());
            //DATOS DE SALIDA
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.execute();
            resultado = (stmt.getInt(2));
            return resultado;  
        } 
        catch (Exception e) 
        {
            System.out.println(e);
            return resultado = 5;
        }
    }
    public ArrayList<descuento> listarDescuentos()
    {
        ArrayList listaDescuentos = new ArrayList();
        descuento dcto;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call LISTAR_DISCOUNTS(?)}");
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) 
            {
                dcto = new descuento();
                dcto.setId(rs.getInt(1));
                dcto.setNombre(rs.getString(2));
                dcto.setPorcentaje(rs.getInt(3));
                listaDescuentos.add(dcto);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaDescuentos;
    }
        public ArrayList<descuento> buscarDescuento()
    {
        ArrayList listaDescuentos = new ArrayList();
        ResultSet rs = null;
        descuento dcto;
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call READ_DISCOUNT(?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString(1, getNombre());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(2, OracleTypes.CURSOR);
            
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            rs = (ResultSet) calla.getObject(2);
            //SI SE ENCUENTRA EL RESULTADO
                while (rs.next()) 
                {                
                dcto = new descuento();
                dcto.setId(rs.getInt(1));
                dcto.setNombre(rs.getString(2));
                dcto.setPorcentaje(rs.getInt(3));
                listaDescuentos.add(dcto);
                }
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        return listaDescuentos;
    }
}
