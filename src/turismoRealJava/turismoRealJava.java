/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turismoRealJava;

import Controlador.Controlador;
import Modelo.conductor;
import Modelo.departamento;
import Modelo.descuento;
import Modelo.inventario;
import Modelo.tour;
import Modelo.transporte;
import Modelo.usuario;
import Modelo.vehiculo;
import Vista.*;


/**
 *
 * @author Omar
 */
public class turismoRealJava {
 
    public static void main(String[] args)
    {   
    //INICIALIZAR LAS VISTAS
    mAdmin menuAdmin = new mAdmin();
    menuFuncionario menuF = new menuFuncionario();
    vVehiculo vistaVehiculo = new vVehiculo();
    IniciarSesion frmInicioSesion = new IniciarSesion();
    vConductor vistaConductor = new vConductor();
    CheckIn checkin = new CheckIn();
    CheckOut checkout = new CheckOut();
    vUsuario cliente = new vUsuario();
    vDepartamento departamento = new vDepartamento();
    vInventario inventario = new vInventario();
    vTransportes transporte = new vTransportes();
    vTours tour = new vTours();
    vDescuentos descuento = new vDescuentos();
    //INICIALIZAR LOS MODELOS
    vehiculo vh = new vehiculo();
    usuario us = new usuario();
    conductor conduct = new conductor();
    departamento depa = new departamento();
    inventario inv = new inventario();
    transporte tpt = new transporte();
    tour t = new tour();
    descuento dcto = new descuento();
    //INICIALIZAR EL CONTROLADOR
    Controlador ctrl = new Controlador(us, frmInicioSesion, vh, vistaVehiculo, menuAdmin, menuF, vistaConductor, conduct, checkin, checkout, cliente, departamento, depa, inventario, inv, transporte, tour, tpt, t, descuento, dcto);
    //ARRANCAR LA VENTANA DE INICIAR SESION AL INICIAR EL PRYECTO
    frmInicioSesion.setVisible(true);
    }
}