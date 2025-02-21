/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_lab_5;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public abstract class SocialClass {
    public ArrayList <String> friends, posts;
    public String username;
    public String postTL;
    public String info;

    public SocialClass(String username) {
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();;
        this.username = username;
    }
    
    public boolean addFriend(String user){
        if(!user.equals(username) && !friends.contains(user)){
            friends.add(user);
            return true;
        }
        return false;
    }
    
    public void addPost(String msg){
        posts.add(msg);
    }
    
    public abstract void timeline();
    
    public void myProfile(){
        info="User: "+username+"\n Timeline:";
        timeline();
        info+=postTL;
        info+="\n Amigos: "+showFriends();
    }
    
    public String showFriends(){
        String friendsTxt="";
        for (int contar = 0; contar < friends.size(); contar++) {
            if (friends.get(contar) != null && contar<10) {
                friendsTxt += "\n- " + friends.get(contar);
            }
        }
        if (friendsTxt.equals("")) {
            return "No tiene amigos agregados";
        }

        return friendsTxt;
    }
    
    public String getProfile(){
        return info;
    }
}
