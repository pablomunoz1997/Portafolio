package Controlador;

import Modelo.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Vista.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.ParseConversionEvent;


public class Controlador implements ActionListener{
//VISTAS
    private vVehiculo vistaVehiculo;
    private mAdmin menuAdmin;
    private IniciarSesion iniSesion;
    private vConductor vConductor;
    private menuFuncionario menuF;
    private CheckIn checkin;
    private CheckOut checkout;
    private vUsuario cliente;
    private vDepartamento departamento;
    private vDescuentos descuento;
    private vInventario inventario;
    private vTours tour;
    private vTransportes transporte;
//MODELO
    private usuario us;
    private vehiculo vh;
    private conductor conductor;
    private departamento depa;
    private inventario inv;
    private transporte tpt;
    private tour t;
    private descuento dcto;

    public Controlador(usuario us, IniciarSesion iniSesion, vehiculo vh, vVehiculo vistaVehiculo, mAdmin menuAdmin, menuFuncionario menuF, vConductor vConductor, conductor conductor, CheckIn checkin, CheckOut checkout, vUsuario cliente, vDepartamento departamento, departamento depa,vInventario inventario, inventario inv, vTransportes transporte, vTours tour, transporte tpt, tour t, vDescuentos descuento, descuento dcto) {
        this.us = us;
        this.iniSesion = iniSesion;
        this.vConductor = vConductor;
        this.vh = vh;
        this.menuAdmin = menuAdmin;
        this.vistaVehiculo = vistaVehiculo;
        this.menuF = menuF;
        this.conductor = conductor;
        this.checkin = checkin;
        this.checkout = checkout;
        this.cliente = cliente;
        this.departamento = departamento;
        this.depa = depa;
        this.inventario = inventario;
        this.inv = inv;
        this.tour = tour;
        this.transporte = transporte;
        this.tpt = tpt;
        this.t= t;
        this.descuento = descuento;
        this.dcto = dcto;
        //BOTONES DE LA VISTA DESCUENTO
        this.descuento.btnAgregar.addActionListener(this);
        this.descuento.btnBuscar.addActionListener(this);
        this.descuento.btnEliminar.addActionListener(this);
        this.descuento.btnLimpiar.addActionListener(this);
        this.descuento.btnModificar.addActionListener(this);
        //BOTONES DE LA VISTA TOUR
        this.tour.btnAgregar.addActionListener(this);
        this.tour.btnEliminar.addActionListener(this);
        this.tour.btnLimpiar.addActionListener(this);
        this.tour.btnModificar.addActionListener(this);
        this.tour.btnMostrarTodo.addActionListener(this);
        this.tour.btnBuscar.addActionListener(this);
        //BOTONES DE LA VISTA TRANSPORTE
        this.transporte.btnAgregar.addActionListener(this);
        this.transporte.btnEliminar.addActionListener(this);
        this.transporte.btnLimpiar.addActionListener(this);
        this.transporte.btnModificar.addActionListener(this);
        this.transporte.btnBuscar.addActionListener(this);
        this.transporte.btnMostrarTodos.addActionListener(this);
        //BOTONES DE LA VISTA INVENTARIO
        this.inventario.btnAbrir.addActionListener(this);
        this.inventario.btnAgregar.addActionListener(this);
        this.inventario.btnEliminar.addActionListener(this);
        this.inventario.btnLimpiar.addActionListener(this);
        this.inventario.btnModificar.addActionListener(this);
        this.inventario.btnBuscar.addActionListener(this);
        //BOTONES DE LA VISTA INICIARSESION
        this.iniSesion.btnInicioSesion.addActionListener(this);
        //BOTONES DE LA VISTA MADMIN
        this.menuAdmin.btnCerrarSesion.addActionListener(this);
        this.menuAdmin.btnSalir.addActionListener(this);
        this.menuAdmin.btnVehiculos.addActionListener(this);
        this.menuAdmin.btnConductores.addActionListener(this);       
        this.menuAdmin.btnDepartamentos.addActionListener(this);        
        this.menuAdmin.btnDisponibilidad.addActionListener(this);
        this.menuAdmin.btnMantenimiento.addActionListener(this);
        this.menuAdmin.btnUsuarios.addActionListener(this);
        this.menuAdmin.btnInventario.addActionListener(this);
        this.menuAdmin.btnTours.addActionListener(this);
        this.menuAdmin.btnDescuentos.addActionListener(this);
        this.menuAdmin.btnTransportes.addActionListener(this);
        this.menuAdmin.btnInformes.addActionListener(this);
        this.menuAdmin.btnEstadisticas.addActionListener(this);
        //BOTONES DE LA VISTA MENUFUNCIONARIO
        this.menuF.btnCerrarSesionF.addActionListener(this);
        this.menuF.btnSalirF.addActionListener(this);
        this.menuF.checkin.addActionListener(this);
        this.menuF.checkout.addActionListener(this);
        //BOTONES DE LA VISTA TESTVEHICULO
        this.vistaVehiculo.btnAgregar.addActionListener(this);
        this.vistaVehiculo.btnEliminar.addActionListener(this);
        this.vistaVehiculo.btnLimpiar.addActionListener(this);
        this.vistaVehiculo.btnBuscar.addActionListener(this);
        this.vistaVehiculo.btnModificar.addActionListener(this);
        //BOTONES DE LA VISTA VCONDUCTOR
        this.vConductor.btnAgregarConductor.addActionListener(this);
        this.vConductor.btnActualizarConductor.addActionListener(this);
        this.vConductor.btnBuscarConductor.addActionListener(this);
        this.vConductor.btnLimpiarConductor.addActionListener(this);
        this.vConductor.btnEliminarConductor.addActionListener(this);
        //BOTONES VISTA USUARIO(CLIENTES)
        this.cliente.btnagregarC.addActionListener(this);
        this.cliente.btnbuscarC.addActionListener(this);
        this.cliente.btneliminarC.addActionListener(this);
        this.cliente.btnmodificarC.addActionListener(this);
        this.cliente.btnlimpiarC.addActionListener(this);
        //BOTONES DE LA VISTA DEPARTAMENTOS
        this.departamento.btnAgregar.addActionListener(this);
        this.departamento.btnBuscar.addActionListener(this);
        this.departamento.btnEliminar.addActionListener(this);
        this.departamento.btnLimpiar.addActionListener(this);
        this.departamento.btnModificar.addActionListener(this);
        this.departamento.mostrarTodosDepas.addActionListener(this);
    }
//METODO PARA EL BOTON INICIAR SESION, PRESION DE BOTONES
@Override
public void actionPerformed(ActionEvent e)
{
//BOTONES APRETADOS EN LA VISTA MENU ADMINISTRADOR
    if(e.getSource() ==menuAdmin.btnCerrarSesion)
    {
        menuAdmin.dispose();
        iniSesion.setVisible(true);
        iniSesion.txtRut.setText(null);
        iniSesion.txtPassword.setText(null);
    }
    if(e.getSource() ==menuAdmin.btnSalir)
    {
        System.exit(0);
    }
    if(e.getSource() == menuAdmin.btnVehiculos)
    {
        menuAdmin.add(vistaVehiculo);
        
        try 
        {
            vistaVehiculo.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        //INICIALIZAR LA TABLA
        TablaVehiculos(vistaVehiculo.tablaVehiculos);
        //HACER VISIBLE LA VISTA VEHICULO Y HACER INIVISIBLE LAS DEMAS VISTAS
        vistaVehiculo.btnEliminar.setEnabled(false);
        vistaVehiculo.setVisible(true);
        vConductor.setVisible(false);
        cliente.setVisible(false);
        departamento.setVisible(false);
        inventario.setVisible(false);
        transporte.setVisible(false);
        tour.setVisible(false);
        descuento.setVisible(false);
    }
    if(e.getSource() == menuAdmin.btnConductores)
    {
        menuAdmin.add(vConductor);
        try 
        {
            vConductor.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        //INICIALIZAR TABLA
        tablaConductores(vConductor.tablaConductores);
        //HACER VISIBLE LA VISTA CONDUCTOR
        vConductor.setVisible(true);
        vistaVehiculo.setVisible(false);
        cliente.setVisible(false);
        departamento.setVisible(false);
        inventario.setVisible(false);
        transporte.setVisible(false);
        tour.setVisible(false);
        descuento.setVisible(false);
        vConductor.btnEliminarConductor.setEnabled(false);
    }
    if(e.getSource() == menuAdmin.btnUsuarios)
    {
        menuAdmin.add(cliente);
        try 
        {
            cliente.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente.btneliminarC.setEnabled(false);
        cliente.setVisible(true);
        vConductor.setVisible(false);
        vistaVehiculo.setVisible(false);
        departamento.setVisible(false);
        inventario.setVisible(false);
        transporte.setVisible(false);
        tour.setVisible(false);
        descuento.setVisible(false);
        //LLAMAR DATOS PARA LA TABLA
        tablaClientes(cliente.tblClientes);
    }
    if(e.getSource() ==menuAdmin.btnDepartamentos)
    {
        menuAdmin.add(departamento);
        try 
        {
            departamento.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        departamento.txtID.setEnabled(false);
        departamento.setVisible(true);
        cliente.setVisible(false);
        vConductor.setVisible(false);
        vistaVehiculo.setVisible(false);
        departamento.btnEliminar.setEnabled(false);
        inventario.setVisible(false);
        transporte.setVisible(false);
        tour.setVisible(false);
        descuento.setVisible(false);
        //INICIALIZAR LA TABLA
        tablaDepartamento(departamento.tablaDepartamento);
    }
    if(e.getSource() ==menuAdmin.btnDisponibilidad)
    {
        
    }
    if(e.getSource() ==menuAdmin.btnMantenimiento)
    {
        
    }
    if(e.getSource() ==menuAdmin.btnInventario)
    {
        menuAdmin.add(inventario);
        try 
        {
            inventario.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        inventario.txtId.setEnabled(false);
        inventario.txtRutaImagen.setEnabled(false);
        inventario.setVisible(true);
        cliente.setVisible(false);
        vConductor.setVisible(false);
        vistaVehiculo.setVisible(false);
        departamento.setVisible(false);
        inventario.btnEliminar.setEnabled(false);
        inventario.txtId.setEnabled(false);
        transporte.setVisible(false);
        tour.setVisible(false);
        descuento.setVisible(false);
        //INICIAR TABLA 
        tablaInventario(inventario.tablaInventario);
        //INICIALIZAR COMBOBOX ID DEPA
        //llenar combobox de patentes
        inventario.cmbIdDepartamento.removeAllItems();
        int n = depa.listarIdDepartamento().size();
        for (int i = 0; i < n; i++) 
        {
            inventario.cmbIdDepartamento.addItem(depa.listarIdDepartamento().get(i).getId());
        }
    }
    if(e.getSource() ==menuAdmin.btnTours)
    {
        menuAdmin.add(tour);
        try 
        {
            tour.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        tour.setVisible(true);
        vistaVehiculo.setVisible(false);
        vConductor.setVisible(false);
        cliente.setVisible(false);
        departamento.setVisible(false);
        inventario.setVisible(false);
        transporte.setVisible(false);
        tour.btnEliminar.setEnabled(false);
        descuento.setVisible(false);
        //cargar tabla
        tablaTours(tour.tablaTour);
    }
    if(e.getSource() ==menuAdmin.btnDescuentos)
    {
        menuAdmin.add(descuento);
        try 
        {
            descuento.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        descuento.setVisible(true);
        tour.setVisible(false);
        vistaVehiculo.setVisible(false);
        vConductor.setVisible(false);
        cliente.setVisible(false);
        departamento.setVisible(false);
        inventario.setVisible(false);
        transporte.setVisible(false);
        descuento.btnEliminar.setEnabled(false);
        descuento.txtID.setEnabled(false);
        //INICIALIZAR LA TABLA
        tablaDescuentos(descuento.tablaDescuento);
    }
    if(e.getSource() ==menuAdmin.btnTransportes)
    {
        menuAdmin.add(transporte);
        try 
        {
            transporte.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        vistaVehiculo.setVisible(false);
        vConductor.setVisible(false);
        cliente.setVisible(false);
        departamento.setVisible(false);
        inventario.setVisible(false);
        transporte.setVisible(true);
        descuento.setVisible(false);
        tour.setVisible(false);
        //INICIALIZAR LA TABLA
        tablaTransporte(transporte.tablaTransporte);
        transporte.btnEliminar.setEnabled(false);
        //llenar combobox de la vista transporte
        vaciarCmbVistaTransporte();
        llenaCmbVistaTransporte();
    }
    if(e.getSource() ==menuAdmin.btnInformes)
    {
        
    }
    if(e.getSource() ==menuAdmin.btnEstadisticas)
    {
        
    }
//BOTONES DE LA VISTA USUARIO(CLIENTE)
    if(e.getSource() == cliente.btnagregarC)
    {
        //RECUPERAR VARIABLES DEL FORMULARIO
        //RUT
        String rutParseado = cliente.lblrutC.getText().replace(" ", "").replace("-", "").replace(".", "");
        String rutSinDv = rutParseado.substring(0, rutParseado.length()-1);
        char dv = rutParseado.charAt(rutParseado.length()-1);
        //NOMBRE
        String n = cliente.lblnombreC.getText().toLowerCase().replace(" ", "");
        String nombre =  n.substring(0, 1).toUpperCase() + n.substring(1); 
        //APELLIDOP
        String ap = cliente.lblpaternoC.getText().toLowerCase().replace(" ", "");
        String apa =  ap.substring(0, 1).toUpperCase() + ap.substring(1); 
        //APELLIDOM
        String am = cliente.lblMaternoC.getText().toLowerCase().replace(" ", "");
        String ama =  am.substring(0, 1).toUpperCase() + am.substring(1); 
        //CONTRASEÑA
        String letrasNombre2 = n.substring(0, 2);
        String letrasapellidopaterno2 = ap.substring(0, 2);
        String digitosrut4 = rutSinDv.substring(0, 4);
        //ASIGNAR VALORES A CLASE USUARIO
        us.setRut(rutSinDv+"-"+dv);
        us.setName(nombre);
        us.setLastNameF(apa);
        us.setLastNameM(ama);
        us.setUserType("cliente");
        us.setPassword(digitosrut4+letrasNombre2+letrasapellidopaterno2);
        //PREGUNTAMOS SI EL PROCEDIMIENTO ALMACENADO FUE EJECUTADO CON EXITO
        if(us.agregarCliente()==1)
        {
            JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente");
            limpiarCliente();
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error al agregar el cliente");
        }
        //RECARGAR TABLA
        limpiarTablaCliente();
        tablaClientes(cliente.tblClientes);
    }
    //BOTON BUSCAR
    if(e.getSource() == cliente.btnbuscarC)
    {
        us.setRut(cliente.txtRutBuscarCliente.getText());
        limpiarTablaCliente();
        buscarClientes(cliente.tblClientes);
    }
    //BOTON ELIMINAR
    if(e.getSource() == cliente.btneliminarC)
    {
        //RUT
        String rutParseado = cliente.lblrutC.getText().replace(" ", "").replace("-", "").replace(".", "");
        String rutSinDv = rutParseado.substring(0, rutParseado.length()-1);
        char dv = rutParseado.charAt(rutParseado.length()-1);
        //ENVIAR RUT FORMATEADO
        us.setRut(rutSinDv+"-"+dv);
        if(us.eliminarCliente()==1)
        {
            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente");
            limpiarCliente();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "El rut ingresado no existe");
        }
        //RECARGAR LA TABLA DE VEHICULOS
        limpiarTablaCliente();
        tablaClientes(cliente.tblClientes);
    }
    //BOTON LIMPIAR
    if(e.getSource() == cliente.btnlimpiarC)
    {
        limpiarCliente();
    }
    //BOTON MODIFICAR
    if(e.getSource() == cliente.btnmodificarC)
    {
        if(cliente.btnmodificarC.getText().equals("Modificar"))
        {
            int fila = cliente.tblClientes.getSelectedRow();
            if(fila>=0)
            {
                cliente.lblrutC.setText(cliente.tblClientes.getValueAt(fila, 0).toString());
                cliente.lblnombreC.setText(cliente.tblClientes.getValueAt(fila, 1).toString());
                cliente.lblpaternoC.setText(cliente.tblClientes.getValueAt(fila, 2).toString());
                cliente.lblMaternoC.setText(cliente.tblClientes.getValueAt(fila, 3).toString());
                cliente.btnmodificarC.setText("Actualizar");
                cliente.lblrutC.setEnabled(false);
                cliente.btneliminarC.setEnabled(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un registro de la tabla");
            }
        }
        else
        {
            us.setRut(cliente.lblrutC.getText());
            us.setName(cliente.lblnombreC.getText());
            us.setLastNameF(cliente.lblpaternoC.getText());
            us.setLastNameM(cliente.lblMaternoC.getText());
            //SI SE ACTUALIZO CORRECTAMENTE
            if(us.actualizarCliente()==1)
            {
                JOptionPane.showMessageDialog(null, "cliente actualizado exitosamente");
                limpiarCliente();
                limpiarTablaCliente();
                tablaClientes(cliente.tblClientes);
                //HABILITAR TEXTO DE LA PATENTE
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error al actualizar datos del cliente");
            }
        }

        //RECARGAR LA TABLA DE VEHICULOS
        limpiarTablaCliente();
        tablaClientes(cliente.tblClientes);
    }
//BOTONES APRETADOS EN LA VISTA INICIAR SESION
    if(e.getSource() == iniSesion.btnInicioSesion)
    {
        //PARSEAR RUT
        String rutParseado = iniSesion.txtRut.getText().replace(" ", "").replace("-", "").replace(".", "");
        String rutSinDv = rutParseado.substring(0, rutParseado.length()-1);
        char dv = rutParseado.charAt(rutParseado.length()-1);
        
        
        us.setRut(rutSinDv+"-"+dv);
        us.setPassword(new String(iniSesion.txtPassword.getPassword()));
        //VALIDAMOS EL INICIO DE SESION
        if(us.inicioSesion()>0)
        {
            //SI SE INICIA SESION CORRECTAMENTE SE DEBEN INICIALIZAR TODAS LAS HERRAMIENTAS
            //SI EL TIPO DE USUARIO ES UN ADMINISTRADOR, REDIRECCIONARA AQUI
            if(us.getUserType().equals("administrador"))
            {
                iniSesion.dispose();
                menuAdmin.setVisible(true);
            }
            //SI EL TIPO DE USUARIO ES FUNCIONARIO REDIRECCIONARARA AQUI
            if(us.getUserType().equals("funcionario"))
            {
                menuF.setVisible(true);
                iniSesion.dispose();
            }
        }
        //SI NO ES NINGUNA DE LAS ANTERIORES REDIRECCIONARA AQUI
        else
        {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
        }
    }
////BOTONES APRETADOS EN LA VISTA TESTVEHICULO
    if(e.getSource() == vistaVehiculo.btnAgregar)
    {
        //RECUPERAR VARIABLES DEL FORMULARIO
        vh.setPatent(vistaVehiculo.txtPatente.getText());
        vh.setBrand(vistaVehiculo.txtbrand.getText());
        vh.setColor(vistaVehiculo.txtcolor.getText());
        vh.setModel(vistaVehiculo.txtmodel.getText());
        vh.setYear(Integer.parseInt(vistaVehiculo.txtyear.getText()));
        //PREGUNTAMOS SI EL PROCEDIMIENTO ALMACENADO FUE EJECUTADO CON EXITO
        if(vh.agregarVehiculo()==1)
        {
            JOptionPane.showMessageDialog(null, "Vehiculo agregado exitosamente");
            limpiarCamposVehiculo();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error al agregar un vehiculo");
        }
        //RECARGAR LA TABLA DE VEHICULOS
        limpiarTablaVehiculos();
        TablaVehiculos(vistaVehiculo.tablaVehiculos);
    }
    //ACCIONES SI SE APRETA EL BOTON BUSCAR
    if(e.getSource() == vistaVehiculo.btnBuscar)
    {
        vh.setPatent(vistaVehiculo.txtBuscarPatente.getText());
        buscarVehiculos(vistaVehiculo.tablaVehiculos);
    }
    //ACCIONES SI SE APRETA EL BOTON ELIMINAR
    if(e.getSource() == vistaVehiculo.btnEliminar)
    {
        vh.setPatent(vistaVehiculo.txtPatente.getText());
        if(vh.eliminarVehiculo()==1)
        {
            JOptionPane.showMessageDialog(null, "Vehiculo eliminado exitosamente");
            limpiarCamposVehiculo();
            //RECARGAR LA TABLA DE VEHICULOS
            limpiarTablaVehiculos();
            TablaVehiculos(vistaVehiculo.tablaVehiculos);
        }
        if(vh.eliminarVehiculo() == 2)
        {
            JOptionPane.showMessageDialog(null, "El vehiculo se encuentra registrado en algunos transportes, por favor elimine los transportes que posean esta patente del vehiculo que se desea eliminar");
        }
    }
    if(e.getSource() == vistaVehiculo.btnModificar)
    {
        if(vistaVehiculo.btnModificar.getText().equals("Modificar"))
        {
            
            int seleccion = vistaVehiculo.tablaVehiculos.getSelectedRow();
            if(seleccion >= 0)
            {
                vistaVehiculo.txtPatente.setText(vistaVehiculo.tablaVehiculos.getValueAt(seleccion, 0).toString());
                vistaVehiculo.txtbrand.setText(vistaVehiculo.tablaVehiculos.getValueAt(seleccion, 1).toString());
                vistaVehiculo.txtcolor.setText(vistaVehiculo.tablaVehiculos.getValueAt(seleccion, 2).toString());
                vistaVehiculo.txtmodel.setText(vistaVehiculo.tablaVehiculos.getValueAt(seleccion, 3).toString());
                vistaVehiculo.txtyear.setText(vistaVehiculo.tablaVehiculos.getValueAt(seleccion, 4).toString());
                vistaVehiculo.txtPatente.setEnabled(false);
                vistaVehiculo.btnEliminar.setEnabled(true);
                vistaVehiculo.btnModificar.setText("Actualizar");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un registro en la tabla");
            }
            
        }
        else
        {
            vh.setPatent(vistaVehiculo.txtPatente.getText().toLowerCase());
            vh.setBrand(vistaVehiculo.txtbrand.getText().toLowerCase());
            vh.setModel(vistaVehiculo.txtmodel.getText().toLowerCase());
            vh.setColor(vistaVehiculo.txtcolor.getText().toLowerCase());
            vh.setYear(Integer.parseInt(vistaVehiculo.txtyear.getText()));
            if(vh.actualizarVehiculo()==1)
            {
                JOptionPane.showMessageDialog(null, "Datos del vehiculo actualizados exitosamente");
                limpiarCamposVehiculo();
                limpiarTablaVehiculos();
                TablaVehiculos(vistaVehiculo.tablaVehiculos);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "no se pudo actualizar los datos del vehiculo");
            }
        }
}
    if(e.getSource() == vistaVehiculo.btnLimpiar)
    {
        limpiarCamposVehiculo();
    }
//BOTONES DE LA VISTA CONDUCTOR
    if(e.getSource() == vConductor.btnAgregarConductor)
    {
        //RECUPERAR VARIABLES DEL FORMULARIO
        String rutParseado = vConductor.txtRutConductor.getText().replace(" ", "").replace("-", "").replace(".", "");
        String n = vConductor.txtNombreConductor.getText().toLowerCase().replace(" ", "");
        String ap = vConductor.txtApellidoPaternoConductor.getText().toLowerCase().replace(" ", "");
        String am = vConductor.txtApellidoMaternoConductor.getText().toLowerCase().replace(" ", "");
        if(vConductor.txtRutConductor.getText().length()<=10 && vConductor.txtNombreConductor.getText().length()>0 && vConductor.txtApellidoPaternoConductor.getText().length()>0 && vConductor.txtApellidoMaternoConductor.getText().length()>0)
        {
            //PARSEAR RUT
            String rutSinDv = rutParseado.substring(0, rutParseado.length()-1);
            char dv = rutParseado.charAt(rutParseado.length()-1);
            String rut = rutSinDv+"-"+dv;
            //NOMBRE
            String nombre =  n.substring(0, 1).toUpperCase() + n.substring(1); 
            //APELIIDO PATERNO    
            String apa =  ap.substring(0, 1).toUpperCase() + ap.substring(1); 
            //APELLIDO MATERNO           
            String ama =  am.substring(0, 1).toUpperCase() + am.substring(1); 
            conductor.setRut(rut.toLowerCase());
            conductor.setName(nombre);
            conductor.setLastNameF(apa);
            conductor.setLastNameM(ama);
            if(conductor.agregarConductor()==1)
            {
                JOptionPane.showMessageDialog(null, "Conductor agregado exitosamente");
                limpiarCamposConductor();
                limpiarTablaConductores();
                tablaConductores(vConductor.tablaConductores);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error al agregar al conductor");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Los datos ingresados no son validos");
        }

    }
    if(e.getSource() == vConductor.btnBuscarConductor)
    {
        conductor.setRut(vConductor.txtBuscarConductor.getText());
        limpiarTablaConductores();
        buscarConductores(vConductor.tablaConductores);
    }
    if(e.getSource() == vConductor.btnActualizarConductor)
    {
        if(vConductor.btnActualizarConductor.getText().equals("Modificar"))
        {
            int fila = vConductor.tablaConductores.getSelectedRow();
            if(fila>=0)
            {
                vConductor.txtRutConductor.setText(vConductor.tablaConductores.getValueAt(fila, 0).toString());
                vConductor.txtNombreConductor.setText(vConductor.tablaConductores.getValueAt(fila, 1).toString());
                vConductor.txtApellidoPaternoConductor.setText(vConductor.tablaConductores.getValueAt(fila, 2).toString());
                vConductor.txtApellidoMaternoConductor.setText(vConductor.tablaConductores.getValueAt(fila, 3).toString());
                vConductor.btnActualizarConductor.setText("Actualizar");
                vConductor.txtRutConductor.setEnabled(false);
                vConductor.btnEliminarConductor.setEnabled(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un conductor de la tabla");
            }
        }
        else
        {
            //RECUPERAR VARIABLES DEL FORMULARIO
            String rutParseado = vConductor.txtRutConductor.getText().replace(" ", "").replace("-", "").replace(".", "");
            String n = vConductor.txtNombreConductor.getText().toLowerCase().replace(" ", "");
            String ap = vConductor.txtApellidoPaternoConductor.getText().toLowerCase().replace(" ", "");
            String am = vConductor.txtApellidoMaternoConductor.getText().toLowerCase().replace(" ", "");
            //PARSEAR RUT
            String rutSinDv = rutParseado.substring(0, rutParseado.length()-1);
            char dv = rutParseado.charAt(rutParseado.length()-1);
            String rut = rutSinDv+"-"+dv;
            //NOMBRE
            String nombre =  n.substring(0, 1).toUpperCase() + n.substring(1); 
            //APELIIDO PATERNO    
            String apa =  ap.substring(0, 1).toUpperCase() + ap.substring(1); 
            //APELLIDO MATERNO           
            String ama =  am.substring(0, 1).toUpperCase() + am.substring(1); 
            
            conductor.setRut(rut.toLowerCase());
            conductor.setName(nombre);
            conductor.setLastNameF(apa);
            conductor.setLastNameM(ama);
            if(conductor.actualizarConductor()==1)
            {
                JOptionPane.showMessageDialog(null, "Datos del conductor actualizados exitosamente");
                limpiarCamposConductor();
                limpiarTablaConductores();
                tablaConductores(vConductor.tablaConductores);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error al Actualizar datos del conductor");
            }
            
        }
    }
    if(e.getSource() == vConductor.btnEliminarConductor)
    {
        conductor.setRut(vConductor.txtRutConductor.getText());
        if(conductor.eliminarConductor()==1)
        {
            JOptionPane.showMessageDialog(null, "Conductor eliminado correctamente");
            limpiarCamposConductor();
            limpiarTablaConductores();
            tablaConductores(vConductor.tablaConductores);
            
        }
        if(conductor.eliminarConductor() == 2)
        {
            JOptionPane.showMessageDialog(null, "El conductor no se puede eliminar porque se encuentra afiliado a transportes, por favor actualice o elimine el conductor en los registros de transportes antes de eliminar este conductor");
        }
    }
    if(e.getSource() == vConductor.btnLimpiarConductor)
    {
        limpiarCamposConductor();
        vConductor.txtRutConductor.setEnabled(true);
    }
//BOTONES DE LA VISTA MENUFUNCIONARIO
    if(e.getSource() == menuF.btnSalirF)
    {
        System.exit(0);
    }
    if(e.getSource() == menuF.btnCerrarSesionF)
    {
        menuF.dispose();
        //LIMPIAR CAMPOS
        iniSesion.txtPassword.setText("");
        iniSesion.txtRut.setText("");
        //REAPARECER INICIO DE SESION
        iniSesion.setVisible(true);
    }
    if(e.getSource() == menuF.checkin)
    {
        menuF.add(checkin);
        try 
        {
            checkin.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkin.setVisible(true);
        checkout.setVisible(false);
    }
    if(e.getSource() == menuF.checkout)
    {
        menuF.add(checkout);
        try 
        {
            checkout.setMaximum(true);
        } 
        catch (PropertyVetoException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkin.setVisible(false);
        checkout.setVisible(true);
    }
//BOTONES DE VISTA DEPARTAMENTO
    if(e.getSource() == departamento.btnLimpiar)
    {
        limpiarCamposDepartamento();
    }
    if(e.getSource() == departamento.mostrarTodosDepas)
    {
        limpiarTablaDepartamento();
        tablaDepartamento(departamento.tablaDepartamento);
    }
    if(e.getSource() == departamento.btnAgregar)
    {
        depa.setRegion((String)departamento.cmbRegion.getSelectedItem());
        depa.setAddress(departamento.txtDireccion.getText().toLowerCase());
        depa.setFloor((String)departamento.cmbPiso.getSelectedItem());
        depa.setDirr(Integer.parseInt(departamento.numeroDepa.getText()));
        depa.setPrice(Integer.parseInt(departamento.txtPrecio.getText()));
        depa.setState((String)departamento.cmbEstado.getSelectedItem());
        
        if(depa.agregarDepartamento()== true)
        {
            JOptionPane.showMessageDialog(null, "Departamento agregado exitosamente");
            limpiarCamposDepartamento();
            limpiarTablaDepartamento();
            tablaDepartamento(departamento.tablaDepartamento);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error al agregar el departamento");
        }
    }
    if(e.getSource() == departamento.btnBuscar)
    {
        depa.setRegion((String)departamento.cmbBuscarRegion.getSelectedItem());
        depa.setState((String) departamento.cmbBuscarEstado.getSelectedItem());
        limpiarTablaDepartamento();
        buscarDepartamento(departamento.tablaDepartamento);
    }
    if(e.getSource() == departamento.btnEliminar)
    {
        depa.setId(Integer.parseInt(departamento.txtID.getText()));
        if(depa.eliminarDepartamento() == 1)
        {
            JOptionPane.showMessageDialog(null, "Departamento eliminado correctamente");
            limpiarCamposDepartamento();
            limpiarTablaDepartamento();
            tablaDepartamento(departamento.tablaDepartamento);
        }
        if(depa.eliminarDepartamento() == 2)
        {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el departamento seleccionado porque aun posee un inventario, por favor elimine primero el inventario");
        }
    }
    if(e.getSource() == departamento.btnModificar)
    {
        if(departamento.btnModificar.getText().equals("Modificar"))
        {
            int fila = departamento.tablaDepartamento.getSelectedRow();
            if(fila>=0)
            {
                departamento.txtID.setText(departamento.tablaDepartamento.getValueAt(fila, 0).toString());
                departamento.cmbRegion.setSelectedItem(departamento.tablaDepartamento.getValueAt(fila, 1).toString());
                departamento.txtDireccion.setText(departamento.tablaDepartamento.getValueAt(fila, 2).toString());
                departamento.cmbPiso.setSelectedItem(departamento.tablaDepartamento.getValueAt(fila, 3).toString());
                departamento.numeroDepa.setText(departamento.tablaDepartamento.getValueAt(fila, 4).toString());
                departamento.txtPrecio.setText(departamento.tablaDepartamento.getValueAt(fila, 5).toString());
                departamento.cmbEstado.setSelectedItem(departamento.tablaDepartamento.getValueAt(fila, 6).toString());
                departamento.btnEliminar.setEnabled(true);
                departamento.btnModificar.setText("Actualizar");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un registro de la tabla");
            }
        }
        else
        {
            depa.setDirr(Integer.parseInt(departamento.numeroDepa.getText()));
            depa.setId(Integer.parseInt(departamento.txtID.getText()));
            depa.setPrice(Integer.parseInt(departamento.txtPrecio.getText()));
            depa.setRegion((String)departamento.cmbRegion.getSelectedItem());
            depa.setState((String)departamento.cmbEstado.getSelectedItem());
            depa.setFloor((String)departamento.cmbPiso.getSelectedItem());            
            depa.setAddress(departamento.txtDireccion.getText());
            if(depa.actualizarDepartamento()==1)
            {
                JOptionPane.showMessageDialog(null, "Datos del departamento actualizado correctamente");
                limpiarCamposDepartamento();
                limpiarTablaDepartamento();
                tablaDepartamento(departamento.tablaDepartamento);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar los datos del departamento");
            }
        }
    }
//BOTONES DE LA VISTA INVENTARIO
    if(e.getSource() == inventario.btnAbrir)
    {
        final JFileChooser elegirImagen = new JFileChooser();
        elegirImagen.setMultiSelectionEnabled(false);
        int o = elegirImagen.showOpenDialog(inventario.btnAbrir);
        if (o == JFileChooser.APPROVE_OPTION)
        {
           String ruta = elegirImagen.getSelectedFile().getAbsolutePath();
           inventario.txtRutaImagen.setText(ruta);
        }
    }
    if(e.getSource() == inventario.btnAgregar)
    {
        //DATOS RESTANTES
        String ruta = inventario.txtRutaImagen.getText();
        inv.setName(inventario.txtnombre.getText().toLowerCase());
        inv.setDescription(inventario.txtDescripcion.getText().toLowerCase());
        inv.setPrice(Integer.parseInt(inventario.txtPrice.getText()));
        inv.setQuantity(Integer.parseInt(inventario.txtCantidad.getText()));
        inv.setDepartment_id((int) inventario.cmbIdDepartamento.getSelectedItem());
        
        if(inv.agregarInventario(ruta)==true)
        {
            JOptionPane.showMessageDialog(null, "Inventario agregado exitosamente");
            limpiarTablaInventario();
            tablaInventario(inventario.tablaInventario);
            limpiarCamposInventario();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error al agregar inventario");
        }
    }
    if(e.getSource() == inventario.btnModificar)
    {
        if(inventario.btnModificar.getText().equals("Modificar"))
        {
            int fila = inventario.tablaInventario.getSelectedRow();
            if(fila >= 0)
            {
                inventario.txtId.setText(inventario.tablaInventario.getValueAt(fila, 0).toString());
                inventario.txtCantidad.setText(inventario.tablaInventario.getValueAt(fila, 2).toString());
                inventario.txtDescripcion.setText(inventario.tablaInventario.getValueAt(fila, 3).toString());
                inventario.cmbIdDepartamento.setSelectedItem(inventario.tablaInventario.getValueAt(fila, 5));
                inventario.txtPrice.setText(inventario.tablaInventario.getValueAt(fila, 4).toString());
                inventario.txtnombre.setText(inventario.tablaInventario.getValueAt(fila, 1).toString());
                inventario.btnModificar.setText("Actualizar");
                inventario.cmbIdDepartamento.setEnabled(false);
                inventario.btnEliminar.setEnabled(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un registro de la tabla");
            }
        }
        else
        {
            inv.setId(Integer.parseInt(inventario.txtId.getText()));
            inv.setName(inventario.txtnombre.getText().toLowerCase());
            inv.setQuantity(Integer.parseInt(inventario.txtCantidad.getText()));
            inv.setDescription(inventario.txtDescripcion.getText().toLowerCase());
            inv.setPrice(Integer.parseInt(inventario.txtPrice.getText()));
            inv.setDepartment_id((int) inventario.cmbIdDepartamento.getSelectedItem());
            if(inv.actualizarInventario()==1)
            {
                limpiarTablaInventario();
                tablaInventario(inventario.tablaInventario);
                limpiarCamposInventario();
                JOptionPane.showMessageDialog(null, "Inventario actualizado correctamente");

                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar el inventario");
            }
        }
    }
    if(e.getSource() == inventario.btnEliminar)
    {
        inv.setId(Integer.parseInt(inventario.txtId.getText()));
        if(inv.eliminarInventario()==1)
        {
            limpiarCamposInventario();
            limpiarTablaInventario();
            tablaInventario(inventario.tablaInventario);
            JOptionPane.showMessageDialog(null, "Inventario eliminado exitosamente");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error al eliminar el inventario");
        }
    }
    if(e.getSource()==inventario.btnBuscar)
    {
        if(inventario.txtIDdepartamentoBusqueda.getText().length()==0)
        {
            limpiarTablaInventario();
            tablaInventario(inventario.tablaInventario);
        }
        else
        {
            inv.setDepartment_id(Integer.parseInt(inventario.txtIDdepartamentoBusqueda.getText()));
            limpiarTablaInventario();
            buscarInventario(inventario.tablaInventario);
        }
    }
    if(e.getSource() == inventario.btnLimpiar)
    {
        limpiarCamposInventario();
    }
//BOTONES DE LA VISTA TOUR
    if(e.getSource() == tour.btnAgregar)
    {  

        //RECUPERAR DIA MES AÑO DE LA FECHA DE LLEGADA
        int diaLlegada = tour.txtFechaLlegada.getDate().getDate();
        int mesLlegada = tour.txtFechaLlegada.getDate().getMonth()+1;
        int yearLlegada = tour.txtFechaLlegada.getDate().getYear();
        String llegada = diaLlegada+"/"+mesLlegada+"/"+yearLlegada;
        //RECUPERAR DIA MES AÑO DE LA FECHA DE SALIDA
        int diaSalida = tour.txtFechaSalida.getDate().getDate();
        int mesSalida = tour.txtFechaSalida.getDate().getMonth()+1;
        int yearSalida = tour.txtFechaSalida.getDate().getYear();
        String salida = diaSalida+"/"+mesSalida+"/"+yearSalida;
        //recuperar datos
        t.setOrigen(tour.txtOrigen.getText());
        t.setDestino(tour.txtDestino.getText());
        t.setPrecioPorPersona(Integer.parseInt(tour.txtPrecio.getText()));
        t.setHoraLlegada(tour.txtHoraLlegada.getText());
        t.setHoraSalida(tour.txtHoraSalida.getText());     
        t.setFechaLlegada(llegada);
        t.setFechaSalida(salida);
        if(t.agregarTour()==true)
        {
            JOptionPane.showMessageDialog(null, "Tour Agregado exitosamente");
            limpiarTablaTour();
            tablaTours(tour.tablaTour);
            limpiarVistaTour();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el tour");
        }
    }
    if(e.getSource() == tour.btnModificar)
    {
        if(tour.btnModificar.getText().equals("Modificar"))
        {
            int fila = tour.tablaTour.getSelectedRow();
            if(fila >= 0)
            {
                try 
                {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                    Date fechaLlegada = dateFormat.parse(tour.tablaTour.getValueAt(fila, 5).toString());
                    Date fechaSalida = dateFormat.parse(tour.tablaTour.getValueAt(fila, 3).toString());
                    tour.txtID.setText(tour.tablaTour.getValueAt(fila, 0).toString());
                    tour.txtOrigen.setText(tour.tablaTour.getValueAt(fila, 1).toString());
                    tour.txtDestino.setText(tour.tablaTour.getValueAt(fila, 2).toString());
                    tour.txtFechaSalida.setDate(fechaSalida);
                    tour.txtHoraSalida.setText(tour.tablaTour.getValueAt(fila, 4).toString());
                    tour.txtFechaLlegada.setDate(fechaLlegada);
                    tour.txtHoraLlegada.setText(tour.tablaTour.getValueAt(fila, 6).toString());
                    tour.txtPrecio.setText(tour.tablaTour.getValueAt(fila, 7).toString());
                    tour.btnEliminar.setEnabled(true);
                    tour.btnModificar.setText("Actualizar");
                } 
                catch (ParseException ex) 
                {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Por favor seleccione un registro de la tabla");
            }
        }
        else
        {
            //RECUPERAR DIA MES AÑO DE LA FECHA DE LLEGADA
            int diaLlegada = tour.txtFechaLlegada.getDate().getDate();
            int mesLlegada = tour.txtFechaLlegada.getDate().getMonth()+1;
            int yearLlegada = tour.txtFechaLlegada.getDate().getYear();
            String llegada = diaLlegada+"/"+mesLlegada+"/"+yearLlegada;
            //RECUPERAR DIA MES AÑO DE LA FECHA DE SALIDA
            int diaSalida = tour.txtFechaSalida.getDate().getDate();
            int mesSalida = tour.txtFechaSalida.getDate().getMonth()+1;
            int yearSalida = tour.txtFechaSalida.getDate().getYear();
            String salida = diaSalida+"/"+mesSalida+"/"+yearSalida;
            //recuperar datos
            t.setId(Integer.parseInt(tour.txtID.getText()));
            t.setOrigen(tour.txtOrigen.getText());
            t.setDestino(tour.txtDestino.getText());
            t.setPrecioPorPersona(Integer.parseInt(tour.txtPrecio.getText()));
            t.setHoraLlegada(tour.txtHoraLlegada.getText());
            t.setHoraSalida(tour.txtHoraSalida.getText());     
            t.setFechaLlegada(llegada);
            t.setFechaSalida(salida);
            if(t.actualizarTour() == 1)
            {
                JOptionPane.showMessageDialog(null, "Tour actualizado exitosamente");
                limpiarTablaTour();
                tablaTours(tour.tablaTour);
                limpiarVistaTour();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar los datos del tour");
            }
        }
    }
    if(e.getSource() == tour.btnEliminar)
    {
        t.setId(Integer.parseInt(tour.txtID.getText()));
        
        switch (t.eliminarTour()) {
            case 1:
                JOptionPane.showMessageDialog(null, "Tour eliminado exitosamente");
                limpiarTablaTour();
                tablaTours(tour.tablaTour);
                limpiarVistaTour();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "El tour no se puede porque posee dependencias de algun registro ingresado");
                break;
            case 0:
                JOptionPane.showMessageDialog(null, "El id del tour ingresado no existe");
                break;
            default:
                break;
        }
    }
    if(e.getSource() == tour.btnMostrarTodo)
    {
        limpiarTablaTour();
        tablaTours(tour.tablaTour);
    }
    if(e.getSource() == tour.btnBuscar)
    {
        int dia = tour.txtBuscarFecha.getDate().getDate();
        int mes = tour.txtBuscarFecha.getDate().getMonth()+1;
        int year = tour.txtBuscarFecha.getDate().getYear();
        String llegada = dia+"/"+mes+"/"+year;
        t.setFechaSalida(llegada);
        
        limpiarTablaTour();
        buscarTours(tour.tablaTour);
        
    }
    if(e.getSource() == tour.btnLimpiar)
    {
        limpiarVistaTour();
    }
//BOTONES DE LA VISTA DESCUENTO
    if(e.getSource() == descuento.btnAgregar)
    {
        dcto.setNombre(descuento.txtNombre.getText().toLowerCase());
        dcto.setPorcentaje(Integer.parseInt(descuento.txtPorcentaje.getText()));
        if(dcto.agregarDescuento()==true)
        {
            JOptionPane.showMessageDialog(null, "Descuento agregado exitosamente");
            limpiarVistaDescuento();
            limpiarTablaDescuento();
            tablaDescuentos(descuento.tablaDescuento);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error al agregar descuento");
        }
    }
    if(e.getSource() == descuento.btnBuscar)
    {
        dcto.setNombre(descuento.txtBuscarNombre.getText().toLowerCase());
        //poner en la tabla lo encontrado
        limpiarTablaDescuento();
        buscarDescuento(descuento.tablaDescuento);
        
    }
    if(e.getSource() == descuento.btnEliminar)
    {
        dcto.setId(Integer.parseInt(descuento.txtID.getText()));
                switch (dcto.eliminarDescuento()) {
            case 1:
                JOptionPane.showMessageDialog(null, "Descuento eliminado exitosamente");
                limpiarVistaDescuento();
                limpiarTablaDescuento();
                tablaDescuentos(descuento.tablaDescuento);
                
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "El descuento no se puede porque posee dependencias de algun registro ingresado");
                break;
            case 0:
                JOptionPane.showMessageDialog(null, "El id del descuento ingresado no existe");
                break;
            default:
                break;
        }
        
    }
    if(e.getSource() == descuento.btnLimpiar)
    {
        limpiarVistaDescuento();
    }
    if(e.getSource() == descuento.btnModificar)
    {
        if(descuento.btnModificar.getText().equals("Modificar"))
        {
            int filaSeleccionada = descuento.tablaDescuento.getSelectedRow();
            if(filaSeleccionada >=0)
            {
                descuento.txtID.setText(descuento.tablaDescuento.getValueAt(filaSeleccionada, 0).toString());
                descuento.txtNombre.setText(descuento.tablaDescuento.getValueAt(filaSeleccionada, 1).toString());
                descuento.txtPorcentaje.setText(descuento.tablaDescuento.getValueAt(filaSeleccionada, 2).toString());
                descuento.btnModificar.setText("Actualizar");
                descuento.btnEliminar.setEnabled(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Porfavor seleccione un registro de la tabla");
            }
        }
        else
        {
            dcto.setId(Integer.parseInt(descuento.txtID.getText()));
            dcto.setNombre(descuento.txtNombre.getText().toLowerCase());
            dcto.setPorcentaje(Integer.parseInt(descuento.txtPorcentaje.getText()));
            
            if(dcto.actualizarDescuento()==1)
            {
                JOptionPane.showMessageDialog(null, "Descuento actualizar exitosamente");
                limpiarVistaDescuento();
                limpiarTablaDescuento();
                tablaDescuentos(descuento.tablaDescuento);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "El descuento no se ha podido actualizar");
            }
        }
    }
//BOTONES DE LA VISTA VTRANSPORTES
    if(e.getSource() == transporte.btnAgregar)
    {
        //recuperar fecha para formatearla
        //RECUPERAR DIA MES AÑO DE LA FECHA DE LLEGADA
        
        int dia = transporte.txtFecha.getDate().getDate();
        int mes = transporte.txtFecha.getDate().getMonth()+1;
        int year = transporte.txtFecha.getDate().getYear();
        String llegada = dia+"/"+mes+"/"+year;
        tpt.setOrigin(transporte.txtOrigen.getText().toLowerCase());
        tpt.setDestination(transporte.txtDestino.getText().toLowerCase());
        tpt.setDateTransport(llegada);
        tpt.setDepartureHour(transporte.txtHoraSalida.getText().toLowerCase());
        tpt.setArrivalHour(transporte.txtHoraLLegada.getText().toLowerCase());
        tpt.setPrice(Integer.parseInt(transporte.txtPrecio.getText()));
        tpt.setDriverRut((String) transporte.cmbRutConductor.getSelectedItem());
        tpt.setVehiclePatent((String) transporte.cmbPatente.getSelectedItem());
        
        if(tpt.agregarTransporte() == true)
        {
            JOptionPane.showMessageDialog(null, "Transporte agregado exitosamente");
            limpiarVistaTransporte();
            limpiarTablaTransporte();
            tablaTransporte(transporte.tablaTransporte);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error al agregar el transporte");
        }
        
    }
    if(e.getSource() == transporte.btnEliminar)
    {
        tpt.setId(Integer.parseInt(transporte.txtID.getText()));
        
        if(tpt.eliminarTransporte() == 1)
        {
            JOptionPane.showMessageDialog(null, "Transporte eliminado exitosamente");
            limpiarVistaTransporte();
            limpiarTablaTransporte();
            tablaTransporte(transporte.tablaTransporte);
        }
        if(tpt.eliminarTransporte() == 2)
        {
            JOptionPane.showMessageDialog(null, "El Transporte no se puede porque posee dependencias de algun registro ingresado");
        }
    }
    if(e.getSource() == transporte.btnLimpiar)
    {
        limpiarVistaTransporte();
    }
    if(e.getSource() == transporte.btnModificar)
    {
        if(transporte.btnModificar.getText().equals("Modificar"))
        {
            int fila = transporte.tablaTransporte.getSelectedRow();
            if(fila >= 0)
            {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                    Date fecha = null;
                try {
                    fecha = dateFormat.parse(transporte.tablaTransporte.getValueAt(fila, 3).toString());
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }   
                transporte.txtID.setText(transporte.tablaTransporte.getValueAt(fila, 0).toString());
                transporte.txtOrigen.setText(transporte.tablaTransporte.getValueAt(fila, 1).toString());
                transporte.txtDestino.setText(transporte.tablaTransporte.getValueAt(fila, 2).toString());
                transporte.txtFecha.setDate(fecha);
                transporte.txtHoraSalida.setText(transporte.tablaTransporte.getValueAt(fila, 4).toString());
                transporte.txtHoraLLegada.setText(transporte.tablaTransporte.getValueAt(fila, 5).toString());
                transporte.txtPrecio.setText(transporte.tablaTransporte.getValueAt(fila, 6).toString());
                transporte.cmbRutConductor.setSelectedItem(transporte.tablaTransporte.getValueAt(fila, 7).toString());
                transporte.cmbPatente.setSelectedItem(transporte.tablaTransporte.getValueAt(fila, 8).toString());
                //recuperar funcionalidad de boton eliminar
                transporte.btnEliminar.setEnabled(true);
                transporte.btnAgregar.setEnabled(false);
                transporte.btnModificar.setText("Actualizar");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No hay ningun registro seleccionado");
            }
        }
        else
        {
            //RECUPERAR DIA MES AÑO DE LA FECHA DE LLEGADA
            int dia = transporte.txtFecha.getDate().getDate();
            int mes = transporte.txtFecha.getDate().getMonth()+1;
            int year = transporte.txtFecha.getDate().getYear();
            String llegada = dia+"/"+mes+"/"+year;
            tpt.setId(Integer.parseInt(transporte.txtID.getText()));
            tpt.setOrigin(transporte.txtOrigen.getText().toLowerCase());
            tpt.setDestination(transporte.txtDestino.getText().toLowerCase());
            tpt.setDepartureHour(transporte.txtHoraSalida.getText());
            tpt.setArrivalHour(transporte.txtHoraLLegada.getText());
            tpt.setPrice(Integer.parseInt(transporte.txtPrecio.getText()));
            tpt.setDriverRut((String) transporte.cmbRutConductor.getSelectedItem());
            tpt.setVehiclePatent((String) transporte.cmbPatente.getSelectedItem());
            tpt.setDateTransport(llegada);
            
            if(tpt.actualizarTransporte() == 1)
            {
                JOptionPane.showMessageDialog(null, "Transporte actualizado exitosamente");
                limpiarVistaTransporte();
                limpiarTablaTransporte();
                tablaTransporte(transporte.tablaTransporte);
            }
            if(tpt.actualizarTransporte() == 0)
            {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar los datos del transporte");
            }
        }
    }
    if(e.getSource() == transporte.btnBuscar)
    {
            //RECUPERAR DIA MES AÑO DE LA FECHA DE LLEGADA
            int dia = transporte.txtBuscarFecha.getDate().getDate();
            int mes = transporte.txtBuscarFecha.getDate().getMonth()+1;
            int year = transporte.txtBuscarFecha.getDate().getYear();
            String buscar = dia+"/"+mes+"/"+year;
            tpt.setDateTransport(buscar);
        limpiarTablaTransporte();
        buscarTransporte(transporte.tablaTransporte);
    }
    if(e.getSource() == transporte.btnMostrarTodos)
    {
        limpiarTablaTransporte();
        tablaTransporte(transporte.tablaTransporte);
    }
    
}  
//LIMPIAR FORMULARIO VISTA TRANSPORTE
    public void limpiarVistaTransporte()
    {
        Date d= new Date();
        transporte.txtID.setText(null);
        transporte.txtOrigen.setText(null);
        transporte.txtDestino.setText(null);
        transporte.txtFecha.setDate(d);
        transporte.txtHoraSalida.setText("00:00:00");
        transporte.txtHoraLLegada.setText("00:00:00");
        transporte.txtPrecio.setText(null);
        transporte.cmbRutConductor.setSelectedIndex(0);
        transporte.cmbPatente.setSelectedIndex(0);
        transporte.btnModificar.setText("Modificar");
        transporte.btnAgregar.setEnabled(true);
        transporte.btnEliminar.setEnabled(false);
        transporte.txtBuscarFecha.setDate(null);
    }
//BUSCAR TRANSPORTE
    public void buscarTransporte(JTable tblTransporte)
    {
        DefaultTableModel model = new DefaultTableModel();
        tblTransporte.setModel(model);
        model.addColumn("ID");
        model.addColumn("Origen");
        model.addColumn("Destino");
        model.addColumn("Fecha");
        model.addColumn("Hora de salida");
        model.addColumn("Hora de llegada");
        model.addColumn("Precio");
        model.addColumn("Rut del conductor");
        model.addColumn("Patente del vehiculo");

        Object[] columna = new Object[9];
        int numeroRegistro = tpt.buscarTransportes().size();
        for (int i = 0; i < numeroRegistro; i++) {
        columna[0] = tpt.buscarTransportes().get(i).getId();
        columna[1] = tpt.buscarTransportes().get(i).getOrigin();
        columna[2] = tpt.buscarTransportes().get(i).getDestination();
        columna[3] = tpt.buscarTransportes().get(i).getDateTransport();
        columna[4] = tpt.buscarTransportes().get(i).getDepartureHour();
        columna[5] = tpt.buscarTransportes().get(i).getArrivalHour();
        columna[6] = tpt.buscarTransportes().get(i).getPrice();
        columna[7] = tpt.buscarTransportes().get(i).getDriverRut();
        columna[8] = tpt.buscarTransportes().get(i).getVehiclePatent();
        model.addRow(columna);
        }
    }
//LIMPIAR tabla transporte
    public void limpiarTablaTransporte()
    {
    try 
    {
        DefaultTableModel modelo=(DefaultTableModel) transporte.tablaTransporte.getModel();
        int filas=transporte.tablaTransporte.getRowCount();
        for (int i = 0;i<=filas; i++) 
        {
            modelo.removeRow(0);
        }
    }
    catch (Exception e) 
    {

    }
}
//LLenar tabla transporte
    public void tablaTransporte(JTable tablaTransporte)
    {
        DefaultTableModel model = new DefaultTableModel();
        tablaTransporte.setModel(model);
        model.addColumn("ID");
        model.addColumn("Origen");
        model.addColumn("Destino");
        model.addColumn("Fecha");
        model.addColumn("Hora de salida");
        model.addColumn("Hora de llegada");
        model.addColumn("Precio");
        model.addColumn("Rut del conductor");
        model.addColumn("Patente del vehiculo");

        Object[] columna = new Object[9];
        int numeroRegistro = tpt.listarTransportes().size();
        for (int i = 0; i < numeroRegistro; i++) {
        columna[0] = tpt.listarTransportes().get(i).getId();
        columna[1] = tpt.listarTransportes().get(i).getOrigin();
        columna[2] = tpt.listarTransportes().get(i).getDestination();
        columna[3] = tpt.listarTransportes().get(i).getDateTransport();
        columna[4] = tpt.listarTransportes().get(i).getDepartureHour();
        columna[5] = tpt.listarTransportes().get(i).getArrivalHour();
        columna[6] = tpt.listarTransportes().get(i).getPrice();
        columna[7] = tpt.listarTransportes().get(i).getDriverRut();
        columna[8] = tpt.listarTransportes().get(i).getVehiclePatent();
        model.addRow(columna);
        }
    }
//IMPIAR FROMULARIO DESCUENTO
    public void limpiarVistaDescuento()
    {
        descuento.txtBuscarNombre.setText(null);
        descuento.txtID.setText(null);
        descuento.txtNombre.setText(null);
        descuento.txtPorcentaje.setText(null);
        descuento.btnModificar.setText("Modificar");
        descuento.btnEliminar.setEnabled(false);
    }
//CREAR TABLA DESCUENTOS
    public void tablaDescuentos(JTable tablaDescuento)
    {
        DefaultTableModel model = new DefaultTableModel();
        tablaDescuento.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Porcentaje de descuento");

        Object[] columna = new Object[3];
        int numeroRegistro = dcto.listarDescuentos().size();
        for (int i = 0; i < numeroRegistro; i++) {
        columna[0] = dcto.listarDescuentos().get(i).getId();
        columna[1] = dcto.listarDescuentos().get(i).getNombre();
        columna[2] = dcto.listarDescuentos().get(i).getPorcentaje();
        model.addRow(columna);
        }
    }
//BUSQUEDA DESCUENTOS
    public void buscarDescuento(JTable tablaDescuento)
    {
    DefaultTableModel model = new DefaultTableModel();
    tablaDescuento.setModel(model);
    model.addColumn("ID");
    model.addColumn("Nombre");
    model.addColumn("Porcentaje de descuento");
     
    Object[] columna = new Object[3];
    int numeroRegistro = dcto.buscarDescuento().size();
    for (int i = 0; i < numeroRegistro; i++) {
    columna[0] = dcto.buscarDescuento().get(i).getId();
    columna[1] = dcto.buscarDescuento().get(i).getNombre();
    columna[2] = dcto.buscarDescuento().get(i).getPorcentaje();
    model.addRow(columna);
    }
}
    public void limpiarTablaDescuento()
    {
    try 
    {
        DefaultTableModel modelo=(DefaultTableModel) descuento.tablaDescuento.getModel();
        int filas=descuento.tablaDescuento.getRowCount();
        for (int i = 0;i<=filas; i++) 
        {
            modelo.removeRow(0);
        }
    }
    catch (Exception e) 
    {

    }
}
//LIMPIAR FORMULARIO TOUR
    public void limpiarVistaTour()
    {
        Date date = new Date();
        tour.txtID.setText(null);
        tour.txtOrigen.setText(null);
        tour.txtDestino.setText(null);
        tour.txtFechaSalida.setDate(date);
        tour.txtHoraSalida.setText("00:00:00");
        tour.txtFechaLlegada.setDate(date);
        tour.txtHoraLlegada.setText("00:00:00");
        tour.txtPrecio.setText(null);
        tour.btnEliminar.setEnabled(false);
        tour.btnModificar.setText("Modificar");
    }
    public void limpiarTablaTour()
    {
            try 
    {
        DefaultTableModel modelo=(DefaultTableModel) tour.tablaTour.getModel();
        int filas=tour.tablaTour.getRowCount();
        for (int i = 0;i<=filas; i++) 
        {
            modelo.removeRow(0);
        }
    }
    catch (Exception e) 
    {

    }
    }
//LIMPIAR FORMULARIO DE VISTA USUARIO(CLIENTE)
    public void limpiarCliente()
    {
    cliente.lblrutC.setText(null);
    cliente.lblnombreC.setText(null);
    cliente.lblpaternoC.setText(null);
    cliente.lblMaternoC.setText(null);
    cliente.lblrutC.setEnabled(true);
    cliente.btneliminarC.setEnabled(false);
    cliente.btnmodificarC.setText("Modificar");
    cliente.txtRutBuscarCliente.setText(null);
    cliente.lblrutC.setEnabled(true);
}
//LIMPIAR FORMULARIO DE LA VISTA VEHICULO
    public void limpiarCamposVehiculo()
    {
    vistaVehiculo.txtBuscarPatente.setText(null);
    vistaVehiculo.txtmodel.setText(null);
    vistaVehiculo.txtcolor.setText(null);
    vistaVehiculo.txtbrand.setText(null);
    vistaVehiculo.txtyear.setText(null);
    vistaVehiculo.txtPatente.setText(null);
    vistaVehiculo.btnModificar.setText("Modificar");
    vistaVehiculo.txtPatente.setEnabled(true);
    vistaVehiculo.btnEliminar.setEnabled(false);
}
    public void limpiarCamposConductor()
    {
    vConductor.txtApellidoMaternoConductor.setText(null);
    vConductor.txtRutConductor.setText(null);
    vConductor.txtApellidoPaternoConductor.setText(null);
    vConductor.txtNombreConductor.setText(null);
    vConductor.txtBuscarConductor.setText(null);
    vConductor.btnEliminarConductor.setEnabled(false);
    vConductor.txtRutConductor.setEnabled(true);
    vConductor.btnActualizarConductor.setText("Modificar");
}
    //VACIAR TABLA LISTA DE VEHICULOS
    public void limpiarTablaVehiculos()
    {   
    try 
    {
        DefaultTableModel modelo=(DefaultTableModel) vistaVehiculo.tablaVehiculos.getModel();
        int filas=vistaVehiculo.tablaVehiculos.getRowCount();
        for (int i = 0;i<=filas; i++) 
        {
            modelo.removeRow(0);
        }
    }
    catch (Exception e) 
    {

    }
    }
    //LLENAR TABLA LISTA DE VEHICULOS
    public void TablaVehiculos(JTable tablaV)
    {
    DefaultTableModel model = new DefaultTableModel();
    tablaV.setModel(model);
    model.addColumn("Patente");
    model.addColumn("Marca");
    model.addColumn("Color");
    model.addColumn("Modelo");
    model.addColumn("Año");
     
    Object[] columna = new Object[5];
    int numeroRegistro = vh.listarVehiculos().size();
    for (int i = 0; i < numeroRegistro; i++) {
    columna[0] = vh.listarVehiculos().get(i).getPatent();
    columna[1] = vh.listarVehiculos().get(i).getBrand();
    columna[2] = vh.listarVehiculos().get(i).getColor();
    columna[3] = vh.listarVehiculos().get(i).getModel();
    columna[4] = vh.listarVehiculos().get(i).getYear();
    model.addRow(columna);
    }
}
    public void buscarVehiculos(JTable tablaV)
    {
    DefaultTableModel model = new DefaultTableModel();
    tablaV.setModel(model);
    model.addColumn("Patente");
    model.addColumn("Marca");
    model.addColumn("Color");
    model.addColumn("Modelo");
    model.addColumn("Año");
     
    Object[] columna = new Object[5];
    int numeroRegistro = vh.buscarVehiculo().size();
    for (int i = 0; i < numeroRegistro; i++) {
    columna[0] = vh.listarVehiculos().get(i).getPatent();
    columna[1] = vh.listarVehiculos().get(i).getBrand();
    columna[2] = vh.listarVehiculos().get(i).getColor();
    columna[3] = vh.listarVehiculos().get(i).getModel();
    columna[4] = vh.listarVehiculos().get(i).getYear();
    model.addRow(columna);
    }
}
//BUSCAR CLIENTES
    public void buscarClientes(JTable tablaV)
    {
    DefaultTableModel model = new DefaultTableModel();
    tablaV.setModel(model);
    model.addColumn("Rut");
    model.addColumn("Nombre");
    model.addColumn("Apellido Paterno");
    model.addColumn("Apellido Materno");
     
    Object[] columna = new Object[4];
    int numeroRegistro = us.buscarCLientes().size();
    for (int i = 0; i < numeroRegistro; i++) {
    columna[0] = us.buscarCLientes().get(i).getRut();
    columna[1] = us.buscarCLientes().get(i).getName();
    columna[2] = us.buscarCLientes().get(i).getLastNameF();
    columna[3] = us.buscarCLientes().get(i).getLastNameM();
    model.addRow(columna);
    }
}
//BUSCAR DATOS PARA TABLA VEHICULOS
    public void tablaClientes(JTable tablaClientes)
    {
    DefaultTableModel model = new DefaultTableModel();
    tablaClientes.setModel(model);
    model.addColumn("Rut");
    model.addColumn("Nombre");
    model.addColumn("Apellido Paterno");
    model.addColumn("Apellido Materno");
     
    Object[] columna = new Object[5];
    int numeroRegistro = us.listarClientes().size();
    for (int i = 0; i < numeroRegistro; i++) 
    {
        columna[0] = us.listarClientes().get(i).getRut();
        columna[1] = us.listarClientes().get(i).getName();
        columna[2] = us.listarClientes().get(i).getLastNameF();
        columna[3] = us.listarClientes().get(i).getLastNameM();
        model.addRow(columna);
    }
}
//LIMPIAR TABLA DE CLIENTES
    public void limpiarTablaCliente()
    {
    try 
    {
        DefaultTableModel modelo=(DefaultTableModel) cliente.tblClientes.getModel();
        int filas=cliente.tblClientes.getRowCount();
        for (int i = 0;i<=filas; i++) 
        {
            modelo.removeRow(0);
        }
    }
    catch (Exception e) 
    {

    }
}
    //BUSCAR RUT DE CONDUCTORES
    public void buscarConductores(JTable tablaV)
    {
    DefaultTableModel model = new DefaultTableModel();
    tablaV.setModel(model);
    model.addColumn("Rut");
    model.addColumn("Nombre");
    model.addColumn("Apellido Paterno");
    model.addColumn("Apellido Materno");
     
    Object[] columna = new Object[4];
    int numeroRegistro = conductor.buscarConductor().size();
    for (int i = 0; i < numeroRegistro; i++) {
    columna[0] = conductor.buscarConductor().get(i).getRut();
    columna[1] = conductor.buscarConductor().get(i).getName();
    columna[2] = conductor.buscarConductor().get(i).getLastNameF();
    columna[3] = conductor.buscarConductor().get(i).getLastNameM();
    model.addRow(columna);
    }
}
    public void tablaConductores(JTable tablaConductor)
    {
    DefaultTableModel model = new DefaultTableModel();
    tablaConductor.setModel(model);
    model.addColumn("Rut");
    model.addColumn("Nombre");
    model.addColumn("Apellido Paterno");
    model.addColumn("Apellido Materno");
     
    Object[] columna = new Object[4];
    int numeroRegistro = conductor.listarConductores().size();
    for (int i = 0; i < numeroRegistro; i++) 
    {
        columna[0] = conductor.listarConductores().get(i).getRut();
        columna[1] = conductor.listarConductores().get(i).getName();
        columna[2] = conductor.listarConductores().get(i).getLastNameF();
        columna[3] = conductor.listarConductores().get(i).getLastNameM();
        model.addRow(columna);
    }
}
    public void limpiarTablaConductores()
    {   
    try 
    {
        DefaultTableModel modelo=(DefaultTableModel) vConductor.tablaConductores.getModel();
        int filas=vConductor.tablaConductores.getRowCount();
        for (int i = 0;i<=filas; i++) 
        {
            modelo.removeRow(0);
        }
    }
    catch (Exception e) 
    {

    }
}
//DEPARTAMENTO
    public void tablaDepartamento(JTable tablaClientes)
    {
        DefaultTableModel model = new DefaultTableModel();
        tablaClientes.setModel(model);
        model.addColumn("ID");
        model.addColumn("Región");
        model.addColumn("Dirección");
        model.addColumn("Piso");
        model.addColumn("N° Departamento");
        model.addColumn("Precio por dia");
        model.addColumn("Estado");
     
        Object[] columna = new Object[7];
        int numeroRegistro = depa.listarDepartamentos().size();
        for (int i = 0; i < numeroRegistro; i++) 
        {
            columna[0] = depa.listarDepartamentos().get(i).getId();
            columna[1] = depa.listarDepartamentos().get(i).getRegion();
            columna[2] = depa.listarDepartamentos().get(i).getAddress();
            columna[3] = depa.listarDepartamentos().get(i).getFloor();
            columna[4] = depa.listarDepartamentos().get(i).getDirr();
            columna[5] = depa.listarDepartamentos().get(i).getPrice();
            columna[6] = depa.listarDepartamentos().get(i).getState();
            model.addRow(columna);
        }
    }
//LIMPIAR CAMPOS DE LA VISTA DEPARTAMENTO
    public void limpiarCamposDepartamento()
    {
        departamento.cmbBuscarRegion.setSelectedIndex(0);
        departamento.txtDireccion.setText(null);
        departamento.txtID.setText(null);
        departamento.numeroDepa.setText(null);
        departamento.cmbEstado.setSelectedIndex(0);
        departamento.cmbBuscarEstado.setSelectedIndex(0);
        departamento.cmbPiso.setSelectedIndex(0);
        departamento.txtPrecio.setText(null);
        departamento.cmbRegion.setSelectedIndex(0);
        departamento.btnEliminar.setEnabled(false);
        departamento.btnModificar.setText("Modificar");
    }
    public void buscarDepartamento(JTable tablaV)
    {
    DefaultTableModel model = new DefaultTableModel();
    tablaV.setModel(model);
    model.addColumn("ID");
    model.addColumn("Región");
    model.addColumn("Dirección");
    model.addColumn("Piso");
    model.addColumn("N° Departamento");
    model.addColumn("Precio por dia");
    model.addColumn("Estado");
    
     
    Object[] columna = new Object[7];
    int numeroRegistro = depa.BuscarDepartamento().size();
    for (int i = 0; i < numeroRegistro; i++) {
    columna[0] = depa.BuscarDepartamento().get(i).getId();
    columna[1] = depa.BuscarDepartamento().get(i).getRegion();
    columna[2] = depa.BuscarDepartamento().get(i).getAddress();
    columna[3] = depa.BuscarDepartamento().get(i).getFloor();
    columna[6] = depa.BuscarDepartamento().get(i).getPrice();
    columna[5] = depa.BuscarDepartamento().get(i).getState();
    columna[4] = depa.BuscarDepartamento().get(i).getDirr();
    model.addRow(columna);
    }
}
    public void limpiarTablaDepartamento()
    {   
    try 
    {
        DefaultTableModel modelo=(DefaultTableModel) departamento.tablaDepartamento.getModel();
        int filas=departamento.tablaDepartamento.getRowCount();
        for (int i = 0;i<=filas; i++) 
        {
            modelo.removeRow(0);
        }
    }
    catch (Exception e) 
    {

    }
}
//INVENTARIO
    public void limpiarCamposInventario()
    {
        inventario.txtCantidad.setText(null);
        inventario.txtDescripcion.setText(null);
        inventario.txtIDdepartamentoBusqueda.setText(null);
        inventario.txtId.setText(null);
        inventario.cmbIdDepartamento.setSelectedIndex(0);
        inventario.txtPrice.setText(null);
        inventario.txtRutaImagen.setText(null);
        inventario.txtnombre.setText(null);
        inventario.btnEliminar.setEnabled(false);
        inventario.cmbIdDepartamento.setEnabled(true);
        inventario.btnModificar.setText("Modificar");
        
    }
    public void tablaInventario(JTable tablaInventario)
    {
        tablaInventario.setDefaultRenderer(Object.class, new tablaInstanciada());
        DefaultTableModel model = new DefaultTableModel();
        tablaInventario.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Cantidad");
        model.addColumn("Descripcion");
        model.addColumn("Precio Unitario");
        model.addColumn("ID departamento");
        model.addColumn("Imagen");

        Object[] columna = new Object[7];
        int numeroRegistro = inv.listarInventario().size();
        for (int i = 0; i < numeroRegistro; i++) 
        {
            columna[0] = inv.listarInventario().get(i).getId();
            columna[1] = inv.listarInventario().get(i).getName();
            columna[2] = inv.listarInventario().get(i).getQuantity();
            columna[3] = inv.listarInventario().get(i).getDescription();
            columna[4] = inv.listarInventario().get(i).getPrice();
            columna[5] = inv.listarInventario().get(i).getDepartment_id();
            //transformar la boto de tipo BLOB a BYTE E IMAGEN
            Blob blob = inv.listarInventario().get(i).getImage();
            try 
            {
                byte[] data = blob.getBytes(1, (int)blob.length());
                BufferedImage img = null;
                try 
                {
                    img = ImageIO.read(new ByteArrayInputStream(data));
                    
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "No se pudo cargar la imagen");
                }
                //convertir en icono
                ImageIcon icono = new ImageIcon(img);
                Image imgEscalada = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                Icon iconoEscalado = new ImageIcon(imgEscalada);
                columna[6] =new JLabel(iconoEscalado);
                model.addRow(columna);
                tablaInventario.setRowHeight(100);
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
        public void buscarInventario(JTable tablaInventario)
    {
        tablaInventario.setDefaultRenderer(Object.class, new tablaInstanciada());
        DefaultTableModel model = new DefaultTableModel();
        tablaInventario.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Cantidad");
        model.addColumn("Descripcion");
        model.addColumn("Precio Unitario");
        model.addColumn("ID departamento");
        model.addColumn("Imagen");

        Object[] columna = new Object[7];
        int numeroRegistro = inv.buscarInventario().size();
        for (int i = 0; i < numeroRegistro; i++) 
        {
            columna[0] = inv.buscarInventario().get(i).getId();
            columna[1] = inv.buscarInventario().get(i).getName();
            columna[2] = inv.buscarInventario().get(i).getQuantity();
            columna[3] = inv.buscarInventario().get(i).getDescription();
            columna[4] = inv.buscarInventario().get(i).getPrice();
            columna[5] = inv.buscarInventario().get(i).getDepartment_id();
            //transformar la boto de tipo BLOB a BYTE E IMAGEN
            Blob blob = inv.buscarInventario().get(i).getImage();
            try 
            {
                byte[] data = blob.getBytes(1, (int)blob.length());
                BufferedImage img = null;
                try 
                {
                    img = ImageIO.read(new ByteArrayInputStream(data));
                    
                }
                catch (Exception e) 
                {
                    JOptionPane.showMessageDialog(null, "No se pudo cargar la imagen");
                }
                //convertir en icono
                ImageIcon icono = new ImageIcon(img);
                Image imgEscalada = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                Icon iconoEscalado = new ImageIcon(imgEscalada);
                columna[6] =new JLabel(iconoEscalado);
                model.addRow(columna);
                tablaInventario.setRowHeight(100);
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    public void limpiarTablaInventario()
    {
    try 
    {
        DefaultTableModel modelo=(DefaultTableModel) inventario.tablaInventario.getModel();
        int filas=inventario.tablaInventario.getRowCount();
        for (int i = 0;i<=filas; i++) 
        {
            modelo.removeRow(0);
        }
    }
    catch (Exception e) 
    {

    }
}
        public void tablaTours(JTable tablaTour)
    {
    DefaultTableModel model = new DefaultTableModel();
    tablaTour.setModel(model);
    model.addColumn("ID");
    model.addColumn("Origen");
    model.addColumn("Destino");
    model.addColumn("Fecha de salida");
    model.addColumn("Hora de salida");
    model.addColumn("Fecha de llegada");
    model.addColumn("Hora de llegada");
    model.addColumn("Precio por persona");
     
    Object[] columna = new Object[8];
    int numeroRegistro = t.listarTours().size();
    for (int i = 0; i < numeroRegistro; i++) 
    {
        columna[0] = t.listarTours().get(i).getId();
        columna[1] = t.listarTours().get(i).getOrigen();
        columna[2] = t.listarTours().get(i).getDestino();
        columna[3] = t.listarTours().get(i).getFechaSalida();
        columna[4] = t.listarTours().get(i).getHoraSalida();
        columna[5] = t.listarTours().get(i).getFechaLlegada();
        columna[6] = t.listarTours().get(i).getHoraLlegada();
        columna[7] = t.listarTours().get(i).getPrecioPorPersona();
        model.addRow(columna);
    }
}
    public void llenaCmbVistaTransporte()
    {
        //llenar combobox de patentes
        int numeroRegistro = vh.listarPatenteVehiculo().size();
        for (int i = 0; i < numeroRegistro; i++) 
        {
            transporte.cmbPatente.addItem(vh.listarPatenteVehiculo().get(i).getPatent());
        }
        //LLENAR COMBOBOX DE RUT DE CONDUCTOR
        int numeroRegistros = conductor.listarRutConductor().size();
        for (int i = 0; i < numeroRegistros; i++) 
        {
            transporte.cmbRutConductor.addItem(conductor.listarRutConductor().get(i).getRut());
        }
    }
    public void vaciarCmbVistaTransporte()
    {
        transporte.cmbPatente.removeAllItems();
        transporte.cmbRutConductor.removeAllItems();
    }
    public void buscarTours(JTable buscarTours)
    {
    DefaultTableModel model = new DefaultTableModel();
    buscarTours.setModel(model);
    model.addColumn("ID");
    model.addColumn("Origen");
    model.addColumn("Destino");
    model.addColumn("Fecha de salida");
    model.addColumn("Hora de salida");
    model.addColumn("Fecha de llegada");
    model.addColumn("Hora de llegada");
    model.addColumn("Precio por persona");
     
    Object[] columna = new Object[8];
    int numeroRegistro = t.buscarTour().size();
    for (int i = 0; i < numeroRegistro; i++) 
    {
        columna[0] = t.buscarTour().get(i).getId();
        columna[1] = t.buscarTour().get(i).getOrigen();
        columna[2] = t.buscarTour().get(i).getDestino();
        columna[3] = t.buscarTour().get(i).getFechaSalida();
        columna[4] = t.buscarTour().get(i).getHoraSalida();
        columna[5] = t.buscarTour().get(i).getFechaLlegada();
        columna[6] = t.buscarTour().get(i).getHoraLlegada();
        columna[7] = t.buscarTour().get(i).getPrecioPorPersona();
        model.addRow(columna);
    }
}

}