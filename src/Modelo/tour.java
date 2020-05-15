package Modelo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class tour extends conexion{
    private int id;
    private String origen;
    private String destino;
    private String horaSalida;
    private String horaLlegada;
    private int precioPorPersona;
    private String fechaLlegada;
    private String fechaSalida;

    public tour() {
    }

    public tour(int id, String origen, String destino, String horaSalida, String horaLlegada, int precioPorPersona, String fechaLlegada, String fechaSalida) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.precioPorPersona = precioPorPersona;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getPrecioPorPersona() {
        return precioPorPersona;
    }

    public void setPrecioPorPersona(int precioPorPersona) {
        this.precioPorPersona = precioPorPersona;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
//CRUD
    public boolean agregarTour()
    {
        //auxiliar
        int auxiliar = 0;
        CallableStatement stmt = null;
        Connection con = getConnection();
        try {
                stmt = con.prepareCall("call INSERT_TOUR(?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, getOrigen());
                stmt.setString(2, getDestino());
                stmt.setString(3, getHoraSalida());
                stmt.setString(4, getHoraLlegada());
                stmt.setInt(5, getPrecioPorPersona());
                stmt.setString(6, getFechaLlegada());
                stmt.setString(7, getFechaSalida());
                stmt.execute();
                return true;
            } 
        catch (Exception e) 
            {
                System.out.println(e);
                return false;
            }
    }   
    public int actualizarTour()
    {
        //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call UPDATE_TOUR(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setInt(1, getId());
            calla.setString(2, getOrigen());
            calla.setString(3, getDestino());
            calla.setString(4, getHoraSalida());
            calla.setString(5, getHoraLlegada());
            calla.setInt(6, getPrecioPorPersona());
            calla.setString(7, getFechaLlegada());
            calla.setString(8, getFechaSalida());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(9, Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            resultado = (calla.getInt(9));
            return resultado;
        } catch (SQLException e) 
        {
            System.out.println(e);
            return resultado;
        }
    }
    public int eliminarTour()
    {
    int resultado = 0;
    Connection con = getConnection();
    try
    {
        //LLAMAR AL PROCEDIMIENTO ALMACENADO
        CallableStatement calla= con.prepareCall("call DELETE_TOUR(?, ?)");
        //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
        calla.setInt(1, getId());
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
            return resultado =5;
    }
    }
    public ArrayList<tour> listarTours()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        ArrayList listaTours = new ArrayList();
        tour t;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call LISTAR_TOURS(?)}");
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) 
            {
                t = new tour();
                t.setId(rs.getInt(1));
                t.setOrigen(rs.getString(2));
                t.setDestino(rs.getString(3));
                t.setHoraSalida(rs.getString(4));
                t.setHoraLlegada(rs.getString(5));
                t.setPrecioPorPersona(rs.getInt(6));
                //RESCARTAR VARIABLES DE TIPO DATE
                Date fechaLLegada = rs.getDate(7);
                Date fechaSalida = rs.getDate(8);
                //CONVERTIR VARIABLES DATE A STRING
                String resultadoLLegada = sdf.format(fechaLLegada);
                String resultadoSalida = sdf.format(fechaSalida);
                //PASAR EL STRING A LA CLASE TOUR
                t.setFechaLlegada(resultadoLLegada);
                t.setFechaSalida(resultadoSalida);
                listaTours.add(t);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaTours;
    }
    public ArrayList<tour> buscarTour()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        ArrayList listaTours = new ArrayList();
        tour t;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call READ_TOUR(? ,?)}");
            //VARIABLE DE ENTRADA
            stmt.setString(1, getFechaSalida());
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(2);
            while (rs.next()) 
            {
                t = new tour();
                t.setId(rs.getInt(1));
                t.setOrigen(rs.getString(2));
                t.setDestino(rs.getString(3));
                t.setHoraSalida(rs.getString(4));
                t.setHoraLlegada(rs.getString(5));
                t.setPrecioPorPersona(rs.getInt(6));
                //RESCARTAR VARIABLES DE TIPO DATE
                Date fechaLLegada = rs.getDate(7);
                Date fechaSalida = rs.getDate(8);
                //CONVERTIR VARIABLES DATE A STRING
                String resultadoLLegada = sdf.format(fechaLLegada);
                String resultadoSalida = sdf.format(fechaSalida);
                //PASAR EL STRING A LA CLASE TOUR
                t.setFechaLlegada(resultadoLLegada);
                t.setFechaSalida(resultadoSalida);
                listaTours.add(t);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaTours;
    }
}
