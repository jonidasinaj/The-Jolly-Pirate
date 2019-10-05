/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thejollypirate;

import javax.swing.JFrame;

/**
 *
 * @author J
 */
public class TheJollyPirate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        model.Member mem = new model.Member();
        view.Console app = new view.Console();
        
        app.setVisible(true);
        app.setSize(1000,1000);
        app.setLocation(100,50);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}
