/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import oracle.jdbc.OracleTypes;

public class inventario extends conexion{
    private int id;
    private String name;
    private int quantity;
    private String description;
    private int price;
    private int department_id;
    private Blob image;

    public inventario() {
    }

    public inventario(int id, String name, int quantity, String description, int price, int department_id, Blob image) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.department_id = department_id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
//CRUD
    public boolean agregarInventario(String ruta)
    {
        FileInputStream fi = null;
        PreparedStatement stmt = null;
        Connection con = getConnection();
        try 
        {
            File file = new File(ruta);
            fi = new FileInputStream(file);
            stmt = con.prepareCall("{call INSERT_INVENTORY(?,?,?,?,?,?)}");
            stmt.setString(1, getName());
            stmt.setInt(2, getQuantity());
            stmt.setString(3, getDescription());
            stmt.setInt(4, getPrice());
            stmt.setInt(5, getDepartment_id());
            stmt.setBinaryStream(6, fi);
            stmt.execute();
            
            return true;  
        } 
        catch (Exception e) 
        {
            System.out.println(e);
            return false;
        }
    }
    public int actualizarInventario()
    {
        //llave auxiliar
        int resultado = 0;
        //EJECUCION DE PROCEDIMIENTO ALMACENADO
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call UPDATE_INVENTORY(?, ?, ?, ?, ?, ?, ?)");
            //ENVIAR VARIABLES AL PROCEDIMIENTO ALMACENADO
            calla.setInt(1, getId());
            calla.setString(2, getName());
            calla.setInt(3, getQuantity());
            calla.setString(4, getDescription());
            calla.setInt(5, getPrice());
            calla.setInt(6, getDepartment_id());
            //SETEAR VARIABLES DE SALIDA DEL PROCEDIMIENTO ALMACENADO
            calla.registerOutParameter(7, Types.INTEGER);
            //EJECUTAR PROCEDIMIENTO ALMACENADO
            calla.execute();
            resultado = (calla.getInt(7));
            return resultado;
        } catch (SQLException e) 
        {
            System.out.println(e);
            return resultado;
        }
    }
    public int eliminarInventario()
    {
        int resultado = 0;
        Connection con = getConnection();
        try
        {
            //LLAMAR AL PROCEDIMIENTO ALMACENADO
            CallableStatement calla= con.prepareCall("call DELETE_INVENTORY(?, ?)");
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
            return resultado = 5;
        }
    }
    public ArrayList<inventario> listarInventario()
    {
        ArrayList listaInventario = new ArrayList();
        inventario inv;
        Connection con = getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        try 
        {
            stmt = con.prepareCall("{call LISTAR_INVENTORY(?)}");
            //VARIABLE DE SALIDA
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) 
            {
                inv = new inventario();
                inv.setId(Integer.parseInt(rs.getString("ID")));
                inv.setName(rs.getString("name"));
                inv.setQuantity(Integer.parseInt(rs.getString("quantity")));
                inv.setDescription(rs.getString("description"));
                inv.setPrice(Integer.parseInt(rs.getString("price")));
                inv.setDepartment_id(Integer.parseInt(rs.getString("department_id")));
                //RECUPERAR FOTO
                inv.setImage(rs.getBlob("image"));
                listaInventario.add(inv);
            }
        } 
        catch (Exception e) 
        {
            
        }
    return listaInventario;
    }
        public ArrayList<inventario> buscarInventario()
    {
        ArrayList listaInventario = new ArrayList();
        ResultSet rs = null;
        inventario inv;
        CallableStatement stmt = null;
        Connection con = getConnection();
        try 
        {
            stmt = con.prepareCall("{call READ_INVENTORY(?, ?)}");
            //VARIABLE DE SALIDA
            stmt.setInt(1, getDepartment_id());
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            //EJECUCION DEL PROCEDIMIENTO ALMACENADO
            stmt.execute();
            //RESULTADOS DE SALIDA
            rs = (ResultSet) stmt.getObject(2);
            while (rs.next()) 
            {
                inv = new inventario();
                inv.setId(Integer.parseInt(rs.getString("ID")));
                inv.setName(rs.getString("name"));
                inv.setQuantity(Integer.parseInt(rs.getString("quantity")));
                inv.setDescription(rs.getString("description"));
                inv.setPrice(Integer.parseInt(rs.getString("price")));
                inv.setDepartment_id(Integer.parseInt(rs.getString("department_id")));
                //RECUPERAR FOTO
                inv.setImage(rs.getBlob("image"));
                listaInventario.add(inv);
            }
        } 
        catch (Exception e) 
        {
            
        }
        return listaInventario;
    }
}
