/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author rdragotto
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class Conexion {
 // VARIABLES DE INSTANCIA
 Connection con = null;
 Statement stmt = null;
 ResultSet rs = null;

 String local;
 String puerto;
 public Connection conectar() {

    try {
        //Cargamos el Driver MySQL
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/Rdragotto_EF","root","");
        //JOptionPane.showMessageDialog(null, "conectado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,"Error "+e);
    }
    return con;
    }
// Metodo para consultas y enviar registros
 public ResultSet listar_datos(String consulta) {
    try {
        stmt = con.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
        rs = stmt.executeQuery(consulta);
    } catch (Exception ex) {
        System.out.print(consulta);
        javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    return rs;
 }
// Metodo para insertar registros
 public void insertar_datos(String tabla, String campos, String valores, int mensaje) {
    try {
        stmt = con.createStatement();
        stmt.executeUpdate("insert into " + tabla + " (" + campos + ")" + " values (" + valores + ")");
        switch (mensaje) {
            case 1:
                JOptionPane.showMessageDialog(null, "Se ha grabado exitosamente", "Atencion",
                JOptionPane.INFORMATION_MESSAGE);
            break;
                case 2:
            break;
        }
    } catch (Exception ex) {
        System.out.println("insert into" + tabla + "(" + campos + ")" + "values (" + valores + ")");
        JOptionPane.showMessageDialog(null, "Error en la operacion", "Atencion",
        JOptionPane.WARNING_MESSAGE);
        javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
    }
 }

 //para el buscador
 public boolean busqueda(String query) {
    try {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        return true;
    } catch (Exception ex) {
        ex.printStackTrace();
        return false;
    }
 }

// Metodo para actualizar registros
 public void actualizar_datos(String tabla, String camposAct, String codigo, int mensaje) {
    try {
    stmt = con.createStatement();
    stmt.executeUpdate(" update " + tabla + " set " + camposAct + " where " + codigo, mensaje);
    switch (mensaje) {
        case 1:
            JOptionPane.showMessageDialog(null, "Se ha actualizado", "Atencion",
            JOptionPane.INFORMATION_MESSAGE);
        break;
            case 2:
        break;
        case 3:
            JOptionPane.showMessageDialog(null, "Se ha anulado correctamente", "Atencion",
            JOptionPane.INFORMATION_MESSAGE);
        break;
    }
    } catch (Exception ex) {
        System.out.println(" update " + tabla + " set " + camposAct + " where " + codigo);
        javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        JOptionPane.showMessageDialog(null, "Error en la operacion",
        "Atencion", JOptionPane.WARNING_MESSAGE);
    }
 }
// Metodo para eliminar registro
 public void BorrarDatos(String tabla, String campoCodigo, String codigo) {
    try {
       stmt = con.createStatement();
       stmt.executeUpdate(" delete from " + tabla + " where " + campoCodigo + "=" + codigo);
       JOptionPane.showMessageDialog(null, "Se ha borrado exitosamente", "Atencion",
       JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception ex) {
       System.out.println(" delete from " + tabla + " where " + campoCodigo + "=" + codigo);
       javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
       JOptionPane.showMessageDialog(null, "Error en la operacion",
       "Atencion", JOptionPane.WARNING_MESSAGE);
    }
 }
// cierra la conexion con la base de datos
 public void cierraConexion() {
    try {
        con.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
 }
}
