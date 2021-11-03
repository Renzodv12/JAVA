package Modelo; 
public class Usuarios {  
    private int id_usu;     
    private String usu;    
    private String clave;     
    private String nombre;     
    private String correo;  
    private int id_tipo;
    public int getId_usu() {         
        return id_usu;     
    }  
    public void setId_usu(int id_usu) {         
        this.id_usu = id_usu;     
    }  
    public String getusu() {         
        return usu;     
    }  
    public void setusu(String usu) {         
        this.usu = usu;     
    }  
    public String getclave() {         
        return clave;     
    }  
    public void setclave(String clave) {         
        this.clave = clave;     
    } 
     public String getnombre() {         
         return nombre;     
     }  
    public void setnombre(String nombre) {         
        this.nombre = nombre;     
    }  
    public String getcorreo() {         
        return correo;     
    }  
    public void setcorreo(String correo) {         
        this.correo = correo;     
    }   
       public int getid_tipo() {         
        return id_tipo;     
    }  
    public void setid_tipo(int id_tipo) {         
        this.id_tipo= id_tipo;     
    } 
} 
