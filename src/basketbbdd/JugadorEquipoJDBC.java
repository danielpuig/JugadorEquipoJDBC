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
            System.out.println("Insertados!");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }
        
    }
}
