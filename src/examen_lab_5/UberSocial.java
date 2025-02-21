package examen_lab_5;

import java.util.ArrayList;

public class UberSocial {

    ArrayList<SocialClass> cuentaSocial;

    public SocialClass buscar(String nombreUsuario) {
        return buscarRec(cuentaSocial, nombreUsuario, 0);
    }

    private SocialClass buscarRec(ArrayList<SocialClass> lista, String nombreUsuario, int cont) {
        if (cont >= lista.size()) {
            return null;
        }
        SocialClass rs = lista.get(cont);
        if (rs.username.equals(nombreUsuario)) {
            return rs;
        }

        return buscarRec(lista, nombreUsuario, cont + 1);
    }

    public boolean agregarCuenta(String nombreUsuario, String tipo) {
        if (buscar(nombreUsuario) != null) {
            return false;
        }
        SocialClass nuevaCuenta;
        if (tipo.equalsIgnoreCase("FACEBOOK")) {
            nuevaCuenta = new Facebook(nombreUsuario);
        } else if (tipo.equalsIgnoreCase("TWITTER")) {
            nuevaCuenta = new Twitter(nombreUsuario);
        } else {
            return false;
        }
        cuentaSocial.add(nuevaCuenta);
        return true;
    }

    public boolean agregarPublicacion(String usuario, String publicacion) {
        SocialClass cuenta = buscar(usuario);
        if (cuenta == null) {
            return false;
        }
        cuenta.addPost(publicacion);
        return true;
    }

    public boolean agregarAmigo(String usuario1, String usuario2) {
        SocialClass cuenta1 = buscar(usuario1);
        SocialClass cuenta2 = buscar(usuario2);
        if (cuenta1 == null || cuenta2 == null) {
            return false;
        }
        if (cuenta1.getClass() != cuenta2.getClass()) {
            return false;
        }
        boolean exito1 = cuenta1.addFriend(usuario1);
        boolean exito2 = cuenta2.addFriend(usuario2);
        return exito1 && exito2;
    }

    public boolean agregarComment(String user, int postID, String autor, String comment) {
        SocialClass cuenta = buscar(user);
        if (cuenta == null || !(cuenta instanceof Facebook)) {
            return false;
        }
        Comment nuevoComment = new Comment(postID, autor, comment);
        Facebook fb = (Facebook) cuenta;
        return fb.addComment(nuevoComment);
    }

    public String profileFrom(String user) {
        SocialClass cuenta = buscar(user);
        if (cuenta == null) {
            return "El usuario no existe";
        }
        cuenta.myProfile();
        return cuenta.getProfile();
    }
}
