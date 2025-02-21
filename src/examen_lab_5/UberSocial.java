package examen_lab_5;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class UberSocial {

    ArrayList<SocialClass> cuentaSocial;

    public UberSocial(){
        cuentaSocial = new ArrayList<>();
        
    }
    
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
    
    boolean exito1 = cuenta1.addFriend(usuario2);
    boolean exito2 = cuenta2.addFriend(usuario1);
    return exito1 && exito2;
        
    }

    public boolean agregarComment(String user, int postID, String autor, String comment) {
     SocialClass cuenta = buscar(user);
    if (cuenta == null) {
        System.out.println("El usuario no existe.");
        return false;
    }
    if (!(cuenta instanceof Facebook)) {
        System.out.println("La cuenta no es de Facebook.");
        return false;
    }
    Facebook fb = (Facebook) cuenta;
   
    if (fb.posts.size() == 0) {
        System.out.println("No hay publicaciones para comentar.");
        return false;
    }
    
    if (postID < 0 || postID >= fb.posts.size()) {
        System.out.println("El Post ID no es valido. Ingrese un valor entre 0 y " + (fb.posts.size() - 1) + ".");
        return false;
    }
    Comment nuevoComment = new Comment(postID, autor, comment);
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
    
    public void UberGui(){
            JFrame frame = new JFrame("UberSocial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 550);
        frame.setLocationRelativeTo(null);

        
        JTabbedPane tabbedPane = new JTabbedPane();

        
        JPanel panelCuenta = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCuenta.setBorder(new TitledBorder(new EtchedBorder(), "Crear Cuenta"));
        panelCuenta.add(new JLabel("Nombre de usuario:"));
        JTextField txtNombreUsuarioCuenta = new JTextField();
        panelCuenta.add(txtNombreUsuarioCuenta);
        panelCuenta.add(new JLabel("Tipo de cuenta:"));
        JComboBox<String> cmbTipoCuenta = new JComboBox<>(new String[]{"FACEBOOK", "TWITTER"});
        panelCuenta.add(cmbTipoCuenta);
        JButton btnAgregarCuenta = new JButton("Crear Cuenta");
        panelCuenta.add(btnAgregarCuenta);
        JLabel lblMensajeCuenta = new JLabel("");
        panelCuenta.add(lblMensajeCuenta);

        btnAgregarCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombreUsuarioCuenta.getText().trim();
                String tipo = (String) cmbTipoCuenta.getSelectedItem();
                if (nombre.isEmpty()) {
                    lblMensajeCuenta.setText("El nombre de usuario no puede estar vacio.");
                    return;
                }
                boolean resultado = agregarCuenta(nombre, tipo);
                lblMensajeCuenta.setText(resultado ? "Cuenta creada exitosamente." : "Error: Usuario ya existe o tipo invalido.");
                
                txtNombreUsuarioCuenta.setText("");
                cmbTipoCuenta.setSelectedIndex(0);
            }
        });

        
        JPanel panelPublicacion = new JPanel(new GridLayout(3, 2, 10, 10));
        panelPublicacion.setBorder(new TitledBorder(new EtchedBorder(), "Agregar Publicacion"));
        panelPublicacion.add(new JLabel("Nombre de usuario:"));
        JTextField txtNombreUsuarioPublicacion = new JTextField();
        panelPublicacion.add(txtNombreUsuarioPublicacion);
        panelPublicacion.add(new JLabel("Publicacion:"));
        JTextField txtPublicacion = new JTextField();
        panelPublicacion.add(txtPublicacion);
        JButton btnAgregarPublicacion = new JButton("Agregar Publicacion");
        panelPublicacion.add(btnAgregarPublicacion);
        JLabel lblMensajePublicacion = new JLabel("");
        panelPublicacion.add(lblMensajePublicacion);

        btnAgregarPublicacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtNombreUsuarioPublicacion.getText().trim();
                String publicacion = txtPublicacion.getText().trim();
                if (usuario.isEmpty() || publicacion.isEmpty()) {
                    lblMensajePublicacion.setText("Los campos no pueden estar vacios.");
                    return;
                }
                boolean resultado = agregarPublicacion(usuario, publicacion);
                lblMensajePublicacion.setText(resultado ? "Publicacion agregada." : "Error: Usuario no encontrado.");
                
                txtNombreUsuarioPublicacion.setText("");
                txtPublicacion.setText("");
            }
        });

       
        JPanel panelAmigos = new JPanel(new GridLayout(3, 2, 10, 10));
        panelAmigos.setBorder(new TitledBorder(new EtchedBorder(), "Agregar Amigos"));
        panelAmigos.add(new JLabel("Usuario 1:"));
        JTextField txtUsuario1 = new JTextField();
        panelAmigos.add(txtUsuario1);
        panelAmigos.add(new JLabel("Usuario 2:"));
        JTextField txtUsuario2 = new JTextField();
        panelAmigos.add(txtUsuario2);
        JButton btnAgregarAmigo = new JButton("Agregar Amigo");
        panelAmigos.add(btnAgregarAmigo);
        JLabel lblMensajeAmigo = new JLabel("");
        panelAmigos.add(lblMensajeAmigo);

        btnAgregarAmigo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario1 = txtUsuario1.getText().trim();
                String usuario2 = txtUsuario2.getText().trim();
                if (usuario1.isEmpty() || usuario2.isEmpty()) {
                    lblMensajeAmigo.setText("Los campos no pueden estar vacios.");
                    return;
                }
                boolean resultado = agregarAmigo(usuario1, usuario2);
                lblMensajeAmigo.setText(resultado ? "Amigos agregados." : "Error: Verifique usuarios y tipo de cuenta.");
               
                txtUsuario1.setText("");
                txtUsuario2.setText("");
            }
        });

       
        JPanel panelComentario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelComentario.setBorder(new TitledBorder(new EtchedBorder(), "Agregar Comentario (Facebook)"));
        panelComentario.add(new JLabel("Nombre de usuario:"));
        JTextField txtNombreUsuarioComentario = new JTextField();
        panelComentario.add(txtNombreUsuarioComentario);
        panelComentario.add(new JLabel("Post ID:"));
        JTextField txtPostID = new JTextField();
        panelComentario.add(txtPostID);
        panelComentario.add(new JLabel("Autor:"));
        JTextField txtAutorComentario = new JTextField();
        panelComentario.add(txtAutorComentario);
        panelComentario.add(new JLabel("Comentario:"));
        JTextField txtComentario = new JTextField();
        panelComentario.add(txtComentario);
        JButton btnAgregarComentario = new JButton("Agregar Comentario");
        panelComentario.add(btnAgregarComentario);
        JLabel lblMensajeComentario = new JLabel("");
        panelComentario.add(lblMensajeComentario);

        btnAgregarComentario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtNombreUsuarioComentario.getText().trim();
                String idPostStr = txtPostID.getText().trim();
                String autor = txtAutorComentario.getText().trim();
                String comentario = txtComentario.getText().trim();
                if (usuario.isEmpty() || idPostStr.isEmpty() || autor.isEmpty() || comentario.isEmpty()) {
                    lblMensajeComentario.setText("Los campos no pueden estar vacios.");
                    return;
                }
                int idPost;
                try {
                    idPost = Integer.parseInt(idPostStr);
                } catch (NumberFormatException ex) {
                    lblMensajeComentario.setText("El Post ID debe ser numerico.");
                    return;
                }
                boolean resultado = agregarComment(usuario, idPost, autor, comentario);
                lblMensajeComentario.setText(resultado ? "Comentario agregado." : "Error: Verifique usuario (debe ser Facebook) y Post ID");
                
                txtNombreUsuarioComentario.setText("");
                txtPostID.setText("");
                txtAutorComentario.setText("");
                txtComentario.setText("");
            }
        });

        
        JPanel panelPerfil = new JPanel(new BorderLayout(10, 10));
        panelPerfil.setBorder(new TitledBorder(new EtchedBorder(), "Ver Perfil"));
        JPanel panelPerfilInput = new JPanel(new FlowLayout());
        panelPerfilInput.add(new JLabel("Nombre de usuario:"));
        JTextField txtNombreUsuarioPerfil = new JTextField(15);
        panelPerfilInput.add(txtNombreUsuarioPerfil);
        JButton btnVerPerfil = new JButton("Ver Perfil");
        panelPerfilInput.add(btnVerPerfil);
        panelPerfil.add(panelPerfilInput, BorderLayout.NORTH);
        JTextArea areaPerfil = new JTextArea();
        areaPerfil.setEditable(false);
        JScrollPane scrollPerfil = new JScrollPane(areaPerfil);
        panelPerfil.add(scrollPerfil, BorderLayout.CENTER);

        btnVerPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtNombreUsuarioPerfil.getText().trim();
                if (usuario.isEmpty()) {
                    areaPerfil.setText("El nombre de usuario no puede estar vacio.");
                    return;
                }
                String perfil = profileFrom(usuario);
                areaPerfil.setText(perfil);
                
                txtNombreUsuarioPerfil.setText("");
            }
        });

        
        tabbedPane.addTab("Cuenta", panelCuenta);
        tabbedPane.addTab("Publicacion", panelPublicacion);
        tabbedPane.addTab("Amigos", panelAmigos);
        tabbedPane.addTab("Comentario", panelComentario);
        tabbedPane.addTab("Perfil", panelPerfil);

        
        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UberSocial us = new UberSocial();
                us.UberGui();
            }
        });
    }
    }

