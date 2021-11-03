package Modelo;  
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import javax.swing.JOptionPane;  
public class SqlUsuarios extends Conexion{  
    public boolean registrar(Usuarios usr) {         
        PreparedStatement ps = null;         
        Connection con2 = conectar();         
        String sql = "INSERT INTO usuario (usu, clave, nombre, correo, id_tipo) VALUES(?,?,?,?,?)";  
        try {             
            ps = con2.prepareStatement(sql);             
            ps.setString(1, usr.getusu());             
            ps.setString(2, usr.getclave());             
            ps.setString(3, usr.getnombre());             
            ps.setString(4, usr.getcorreo()); 
            ps.setInt(5, usr.getid_tipo());            
            ps.execute();             
            return true;         
        } catch (SQLException e) {             
            JOptionPane.showMessageDialog(null, e.toString());             
            return false; 
        } finally {             
            try {                 
                con2.close();             
            } catch (SQLException e) 
            {                 
                JOptionPane.showMessageDialog(null, e.toString());             
            }         
        }     
    }  
  
    public boolean login(Usuarios usr) {         
        PreparedStatement ps = null;         
        ResultSet rs = null;         
        Connection con2 = conectar();         
        String sql = "SELECT id_usu, usu, clave, nombre, id_tipo FROM usuario  WHERE usu = ? LIMIT 1";  
        try {             
            ps = con2.prepareStatement(sql);             
            ps.setString(1, usr.getusu());             
            rs = ps.executeQuery();  
            if (rs.next()) {                 
                if (usr.getclave().equals(rs.getString(3))) {                     
                    usr.setId_usu(rs.getInt(1));                     
                    usr.setnombre(rs.getString(4));   
                    usr.setid_tipo(rs.getInt(5));
                    return true;                 
                } else {                     
                    return false;                 
                }             
            }             
            return false;         
        } catch (SQLException e) {             
            JOptionPane.showMessageDialog(null, e.toString());             
            return false;         
        } finally {             
            try {                 
                con2.close();             
            } catch (SQLException e) {                 
                JOptionPane.showMessageDialog(null, e.toString());             
            }         
        }     
    }  
    public int existeUsuario(String usuario) {         
        PreparedStatement ps = null;         
        ResultSet rs   = null;         
        Connection con2 = conectar();  
        String sql = "SELECT count(id_usu) FROM usuario WHERE usu = ?"; 
         try {             
             ps = con2.prepareStatement(sql);             
             ps.setString(1, usuario);             
             rs = ps.executeQuery();  
            if (rs.next()) {                 
                return rs.getInt(1);             
            }             
            return 1;         
         } catch (SQLException e) {             
             JOptionPane.showMessageDialog(null, e.toString());             
             return 1;         
         } finally {             
             try {                 
                 con2.close();             
             } catch (SQLException e) {                 
                 JOptionPane.showMessageDialog(null, e.toString());             
             }         
         }     
    }  
    public boolean esEmail(String correo) {         
        // Patrón para validar el email         
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Zaz0-9]+)*(\\.[A-Za-z]{2,})$");         
        Matcher mather = pattern.matcher(correo);         
        return mather.find();     
    } 
} 
