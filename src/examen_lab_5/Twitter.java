/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_lab_5;

/**
 *
 * @author DELL
 */
public class Twitter extends SocialClass{

    public Twitter(String username) {
        super(username);
    }
    
    public void timeline(){
        postTL="";
        
        for (int cont = 0; cont < posts.size(); cont++) {
            postTL="\nPost 1: "+posts.get(cont);
        }
    }
    
    
}
