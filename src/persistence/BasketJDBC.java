/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Equipo;
import model.Jugador;


/**
 *
 * @author danielpuig
 */
public class BasketJDBC {
    
    private Connection conexion;

    public BasketJDBC() {
    }
    
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/basket";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
    public void insertarEquipo(Equipo e) throws SQLException {
        String insert = "insert into team values (?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, e.getNombre());
        ps.setString(2, e.getLocalidad());
        ps.setDate(3, java.sql.Date.valueOf(e.getFechaCreacion()));
        ps.executeUpdate();
        ps.close();
    }
    
    public void insertarJugador(Jugador j) throws SQLException {
        String insert = "insert into player values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, j.getNombre());
        ps.setDate(2, java.sql.Date.valueOf(j.getFechaNacimiento()));
        ps.setInt(3, j.getCanastas());
        ps.setInt(4, j.getAsistencias());
        ps.setInt(5, j.getRebotes());
        ps.setString(6, j.getPosicion());
        ps.setString(7, j.getEquipo().getNombre());
    }
    
    public void modificarCanastasAsistenciasRebotes(Jugador jugador) throws SQLException {
        String insert = "update player set nbaskets=?,nassists=?,nrebounds=? where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setInt(1, jugador.getCanastas());
        ps.setInt(2, jugador.getAsistencias());
        ps.setInt(3, jugador.getRebotes());
        ps.setString(4, jugador.getNombre());
        ps.executeUpdate();
        ps.close();
    }
    
    public Jugador obtenerJugadorPorNombre(String nombre) throws SQLException {
        String query = "SELECT * FROM player WHERE name = ?;";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setString(1, nombre);
        ResultSet rs = st.executeQuery();

        Jugador jugador = new Jugador();
        while (rs.next()) {

            jugador.setNombre(rs.getString("name"));
            jugador.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            jugador.setCanastas(rs.getInt("nbaskets"));
            jugador.setAsistencias(rs.getInt("nassists"));
            jugador.setRebotes(rs.getInt("nrebounds"));
            jugador.setPosicion(rs.getString("position"));
            Equipo equipo = new Equipo(rs.getString("team"));
            jugador.setEquipo(equipo);
        }
        rs.close();
        st.close();
        return jugador;
    }
    
    public void modificarEquipo(Jugador jugador, Equipo equipo) throws SQLException {
        String insert = "update player set team=? where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, equipo.getNombre());
        ps.setString(2, jugador.getNombre());
        ps.executeUpdate();
        ps.close();
    }
    
    public void borrarJugador(Jugador jugador) throws SQLException {
        String insert = "delete from player where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, jugador.getNombre());
        ps.executeUpdate();
        ps.close();
    }
    
    public ArrayList<Jugador> obtenerJugadoresNombreLike(String nombre) throws SQLException {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        String query = "select * from player where name like ?";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setString(1, "%"+nombre+"%");
        ResultSet rs = st.executeQuery();
        listaJugadores = buscarJugadores(rs);
        return listaJugadores;
    }
    
    public ArrayList<Jugador> obtenerJugadoresPorCanastasMayorIgual(int canastas) throws SQLException {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        String query = "select * from player where nbaskets >= ?";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setInt(1, canastas);
        ResultSet rs = st.executeQuery();
        listaJugadores = buscarJugadores(rs);
        return listaJugadores;
    }
    
    public ArrayList<Jugador> obtenerJugadoresPorAsistenciasEntre(int asisMin,int asisMax) throws SQLException {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        String query = "select * from player where nassists between ? and ?";
        PreparedStatement st = conexion.prepareStatement(query);
        st.setInt(1, asisMin);
        st.setInt(2, asisMax);
        ResultSet rs = st.executeQuery();
        listaJugadores = buscarJugadores(rs);
        return listaJugadores;
    }
    
    public ArrayList<Jugador> buscarJugadores(ResultSet rs) throws SQLException{
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        while (rs.next()) {
            Equipo equipo = new Equipo(rs.getString("team"));
            Jugador jugador = new Jugador(
                    rs.getString("name"), rs.getDate("birth").toLocalDate(),
                    rs.getInt("nbaskets"), rs.getInt("nassists"), rs.getInt("nrebounds"),
                    rs.getString("position"), equipo
            );
            listaJugadores.add(jugador);
        }
        return listaJugadores;
    }
    
}
