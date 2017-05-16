/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basketbbdd;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import model.Equipo;
import model.Jugador;
import persistence.BasketJDBC;

/**
 *
 * @author danielpuig
 */
public class JugadorEquipoJDBC {

    public static void main(String[] args) {

        Equipo stucom = new Equipo("Stucom", "Barcelona", LocalDate.now());
        Equipo ingens = new Equipo("Ingens", "Barcelona", LocalDate.now());

        Jugador dani = new Jugador("Dani", LocalDate.of(1997, Month.MAY, 30), 10, 7, 8, "pivot", stucom);
        Jugador xavi = new Jugador("Xavi", LocalDate.of(1996, Month.MARCH, 19), 8, 9, 7, "alero", stucom);
        Jugador kobe = new Jugador("Kobe", LocalDate.of(1980, Month.DECEMBER, 10), 100, 30, 30, "alero", ingens);
        Jugador lebron = new Jugador("Lebron", LocalDate.of(1980, Month.DECEMBER, 12), 126, 56, 68, "base", ingens);

        BasketJDBC basketJDBC = new BasketJDBC();

        try {
            System.out.println("Conectando a base de datos...");
            basketJDBC.conectar();
            System.out.println("Conectado con exito!");
        } catch (SQLException ex) {
            System.out.println("Error al conectar con BBDD" + ex);
        }
        //Act1
        try {
            System.out.println("Insertando Equipo Stucom...");
            basketJDBC.insertarEquipo(stucom);
            System.out.println("Insertado Correctamente");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        
        try {
            System.out.println("Insertando Equipo Ingens...");
            basketJDBC.insertarEquipo(ingens);
            System.out.println("Insertado Correctamente");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        //Act2
        try {
            System.out.println("Insertando jugador Dani...");
            basketJDBC.insertarJugador(dani);
            System.out.println("Insertado Correctamente");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        
        try {
            System.out.println("Insertando resto de jugadores(xavi/kobe/lebron)...");
            basketJDBC.insertarJugador(xavi);
            basketJDBC.insertarJugador(kobe);
            basketJDBC.insertarJugador(lebron);
            System.out.println("Insertados Correctamente");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        //Act3
        try {
            System.out.println("Modificando canastas de Dani...");
            System.out.println("Acutales datos: " + dani);
            dani.setAsistencias(40);
            dani.setCanastas(50);
            dani.setRebotes(45);
            basketJDBC.modificarCanastasAsistenciasRebotes(dani);

            System.out.println("Datos actualizados: " + basketJDBC.obtenerJugadorPorNombre("Dani"));
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        //Act4
        try {
            System.out.println("Modificando equipo de Dani de Stucom a Ingens...");
            System.out.println("Acutales datos: " + dani);
            basketJDBC.modificarEquipo(dani, ingens);
            System.out.println("Datos actualizados: " + basketJDBC.obtenerJugadorPorNombre("Dani"));
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        //Act5
        try {
            System.out.println("Borrando jugador Lebron...");
            basketJDBC.borrarJugador(lebron);
            System.out.println("Borrado Correctamente");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        //Act6
        try {
            System.out.println("Obtener datos de jugador por nombre 'kobe'...");

            System.out.println(basketJDBC.obtenerJugadorPorNombre("kobe"));
            System.out.println("Datos Obtenidos Correctamente");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        //Act7
        try {
            System.out.println("Obtener datos de jugador que contengan 'dani' en su nombre...");

            System.out.println(basketJDBC.obtenerJugadoresNombreLike("dani"));
            System.out.println("Datos Obtenidos Correctamente");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        //Act8
        try {
            System.out.println("Obteniendo jugador con canastas mayor o igual a 30..");

            System.out.println(basketJDBC.obtenerJugadoresPorCanastasMayorIgual(30));
            System.out.println("Datos Obtenidos Correctamente");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        //Act9
        try {
            System.out.println("Obteniendo jugador con asistencias entre 25 y 30..");

            System.out.println(basketJDBC.obtenerJugadoresPorAsistenciasEntre(25, 30));
            System.out.println("Datos Obtenidos Correctamente");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        
    }
}
