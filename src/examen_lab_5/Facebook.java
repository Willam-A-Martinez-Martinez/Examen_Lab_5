/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_lab_5;

import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class Facebook extends SocialClass implements Commentable {

    private ArrayList<Comment> comentarios;

    public Facebook(String username) {
        super(username);
        comentarios = new ArrayList<>();
    }

    @Override
    public void timeline() {
        for (int i = 0; i < posts.size(); i++) {
            info += "POST " + i + ":\n";
            info += posts.get(i) + "\n";
            for (Comment c : comentarios) {
                if (c.getPostID() == i) {
                    info += "   " + c.toString() + "\n";
                }
            }
            info += "\n";
        }
    }

    @Override
    public boolean addComment(Comment comment) {
        int postId = comment.getPostID();
        if (postId < 0 || postId >= posts.size()) {
            return false;
        }
        comentarios.add(comment);
        return true;
    }
}
