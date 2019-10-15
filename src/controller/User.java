/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import javax.swing.JFrame;
/**
 *
 * @author Jonida
 */
public class User {
    
    public void play(){
        view.MemberView app = new view.MemberView();
        
        app.setVisible(true);
        app.setSize(1000,1000);
        app.setLocation(100,50);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
