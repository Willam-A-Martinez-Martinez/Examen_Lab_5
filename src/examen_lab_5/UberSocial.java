package examen_lab_5;

import java.util.ArrayList;

public class UberSocial {
    ArrayList <SocialClass> cuentaSocial;
    
    public SocialClass buscar(String nombreUsuario) {
        return buscarRec(cuentaSocial, nombreUsuario, 0);
    }
    
    private SocialClass buscarRec(ArrayList<SocialClass> lista, String nombreUsuario, int cont) {
        if (cont >= lista.size()) return null;
        SocialClass rs = lista.get(cont);
        if (rs.username.equals(nombreUsuario)) return rs;
        
        return buscarRec(lista, nombreUsuario, cont+1);
    }
    
    public boolean agregarCuenta(String nombreUsuario, String tipo) {
        if (buscar(nombreUsuario) != null)
            return false;
        SocialClass nuevaCuenta;
        if (tipo.equalsIgnoreCase("FACEBOOK"))
            nuevaCuenta = new Facebook(nombreUsuario);
        else if (tipo.equalsIgnoreCase("TWITTER"))
            nuevaCuenta = new Twitter(nombreUsuario);
        else
            return false;
        cuentaSocial.add(nuevaCuenta);
        return true;
    }
    
    public boolean agregarPublicacion(String usuario, String publicacion) {
        SocialClass cuenta = buscar(usuario);
        if (cuenta == null)
            return false;
        cuenta.addPost(publicacion);
        return true;
    }
}