/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;
import oracle.sql.TIMESTAMP;

/**
 *
 * @author Omar
 */
public class transporte extends conexion{
    private int id;
    private String origin;
    private String destination;
    private String departureHour;
    private String arrivalHour;
    private int price;
    private String driverRut;
    private String vehiclePatent;
    private String dateTransport;

    public transporte() {
    }

    public transporte(int id, String origin, String destination, String departureHour, String arrivalHour, int price, String driverRut, String vehiclePatent, String dateTransport) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureHour = departureHour;
        this.arrivalHour = arrivalHour;
        this.price = price;
        this.driverRut = driverRut;
        this.vehiclePatent = vehiclePatent;
        this.dateTransport = dateTransport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureHour() {
        return departureHour;
    }

    public void setDepartureHour(String departureHour) {
        this.departureHour = departureHour;
    }

    public String getArrivalHour() {
        return arrivalHour;
    }

    public void setArrivalHour(String arrivalHour) {
        this.arrivalHour = arrivalHour;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDriverRut() {
        return driverRut;
    }

    public void setDriverRut(String driverRut) {
        this.driverRut = driverRut;
    }

    public String getVehiclePatent() {
        return vehiclePatent;
    }

    public void setVehiclePatent(String vehiclePatent) {
        this.vehiclePatent = vehiclePatent;
    }

    public String getDateTransport() {
        return dateTransport;
    }

    public void setDateTransport(String dateTransport) {
        this.dateTransport = dateTransport;
    }
    
//CRUD
    public boolean agregarTransporte()
    {

        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call INSERT_TRANSPORT(?, ?, ?, ?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setString(1, getOrigin());
            calla.setString(2, getDestination());
            calla.setString(3, getDepartureHour());
            calla.setString(4, getArrivalHour());
            calla.setInt(5, getPrice());
            calla.setString(6, getDriverRut());
            calla.setString(7, getVehiclePatent());
            calla.setString(8, getDateTransport());
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();

            return true;
        } catch (SQLException e) 
        {
            System.out.println(e);
            return false;
        }
    }
        public int actualizarTransporte()
    {
        //llave auxiliar
        int resultado = 3;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call UPDATE_TRANSPORT(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setInt(1, getId());
            calla.setString(2, getOrigin());
            calla.setString(3, getDestination());
            calla.setString(4, getDepartureHour());
            calla.setString(5, getArrivalHour());
            calla.setInt(6, getPrice());
            calla.setString(7, getDriverRut());
            calla.setString(8, getVehiclePatent());
            calla.setString(9, getDateTransport());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(10, Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            resultado = (calla.getInt(10));
            return resultado;
        } catch (SQLException e) 
        {
            System.out.println(e);
            return resultado;
        }
    }
    public int eliminarTransporte()
    {
    int resultado = 0;
    Connection con = getConnection();
    try
    {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call DELETE_TRANSPORT(?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setInt(1, getId());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(2, Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            resultado = (calla.getInt(2));
    } 
    catch (SQLException e) 
    {
            System.out.println(e);
    }
        return resultado;
}
    public ArrayList<transporte> listarTransportes()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        ArrayList listaTransportes = new ArrayList();
        transporte tpt;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call LISTAR_TRANSPORT(?)}");
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) 
            {                
                tpt = new transporte();
                tpt.setId(rs.getInt(1));
                tpt.setOrigin(rs.getString(2));
                tpt.setDestination(rs.getString(3));
                tpt.setDepartureHour(rs.getString(4));
                tpt.setArrivalHour(rs.getString(5));
                tpt.setPrice(rs.getInt(6));
                tpt.setDriverRut(rs.getString(7));
                tpt.setVehiclePatent(rs.getString(8));
                //RESCARTAR VARIABLES DE TIPO DATE
                Date fecha = rs.getDate(9);
                //CONVERTIR FECHA A STRING
                String resultadoLLegada = sdf.format(fecha);
                //ASIGNAR EL STRING EN LA VARIABLE
                tpt.setDateTransport(resultadoLLegada);
                listaTransportes.add(tpt);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaTransportes;
    }
    public ArrayList<transporte> buscarTransportes()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        ArrayList listaTransportes = new ArrayList();
        transporte tpt;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call READ_TRANSPORT(?, ?)}");
            //variable de entrada
            stmt.setString(1, getDateTransport());
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(2);
            while (rs.next()) 
            {                
                tpt = new transporte();
                tpt.setId(rs.getInt(1));
                tpt.setOrigin(rs.getString(2));
                tpt.setDestination(rs.getString(3));
                tpt.setDepartureHour(rs.getString(4));
                tpt.setArrivalHour(rs.getString(5));
                tpt.setPrice(rs.getInt(6));
                tpt.setDriverRut(rs.getString(7));
                tpt.setVehiclePatent(rs.getString(8));
                //RESCARTAR VARIABLES DE TIPO DATE
                Date fecha = rs.getDate(9);
                //CONVERTIR FECHA A STRING
                String resultadoLLegada = sdf.format(fecha);
                //ASIGNAR EL STRING EN LA VARIABLE
                tpt.setDateTransport(resultadoLLegada);
                listaTransportes.add(tpt);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaTransportes;
    }
}
