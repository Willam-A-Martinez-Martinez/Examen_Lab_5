/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_lab_5;

import java.util.Calendar;

/**
 *
 * @author DELL
 */
public class Comment {
    private int postId;
    private String autor;
    private String comentario;
    private Calendar fecha;

    public Comment (int id, String autor, String comentario){
        this.postId=id;
        this.autor=autor;
        this.comentario=comentario;
        fecha=Calendar.getInstance();
    }

    public int getPostID() {
        return postId;
    }

    public String getAutor() {
        return autor;
    }

    public String getComentario() {
        return comentario;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public String print(){
        return autor+ " - "+fecha +"\n" + comentario;
    }
}
