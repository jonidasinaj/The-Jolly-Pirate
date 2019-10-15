/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.*;
import java.util.UUID;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Jonida
 */
public class RegisterBoat {
    
    public File y;
    private LinkedList<model.Boat> boatLinkedList;
    JTable tableboat;
    private DefaultTableModel model;
    private RegisterMember regmember;
    
    public RegisterBoat(){
        y = new File("DatabaseBoat.txt");
        boatLinkedList = new LinkedList<model.Boat>();
        tableboat = new JTable();
        model = new DefaultTableModel();
        regmember = new RegisterMember();
    }
    
    //Checks if an ID of the boat linked list is UNIQUE
    private boolean isBoatIDInLinkedList(int idnum, JTable tableboat){
        boolean IdInList = false;
        int valueid = idnum;
        for(int i = 0; i < tableboat.getRowCount(); i++){
            if(Integer.parseInt(tableboat.getValueAt(i, 0).toString()) == valueid){
                
                IdInList = true;
            }
        }
        return IdInList;    
    }
    
    //Generates a boat random id
    public int generateBoatID(JTable tableboat) {
        int bid = 0;
        for (int i = 1; true; i++) {
            bid = bid + (int)(Math.random()* 171717.0) % 6 + 10;	
            bid += i;
            if (isBoatIDInLinkedList(bid, tableboat)) // If the ID already exists
                bid = 0; // Clear the ID for the next iteration
            else	// Otherwise, return the generated ID
                break;
        }

        return bid;
    }
    // Saves to file DatabaseBoat.txt
    public void saveToFile(JTable tableboat){
        try{
            FileWriter f = new FileWriter(y);
            BufferedWriter b = new BufferedWriter(f);
            
            for(int i = 0; i < tableboat.getRowCount(); i++){
                for(int k = 0; k < tableboat.getColumnCount(); k++){
                    b.write(tableboat.getValueAt(i, k).toString() + " ");
                }
                b.newLine();
            }
            b.close();
            f.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "File doesn't exist: ERROR!");
        }
    }
 
    //Reads from file
    public void readFromFile(DefaultTableModel model){
        
        try{
            FileReader fr = new FileReader(y);
            BufferedReader br = new BufferedReader(fr);
            Object []lines = br.lines().toArray();
            for(int i = 0; i < lines.length; i++){
                String [] rowlines = lines[i].toString().split(" ");
                model.addRow(rowlines);
            }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error Loading Data: You have no data.");
        }
    }
    
    //Deletes from file
    public void deleteFromFile(JTable tableboat, DefaultTableModel model){
        int i = tableboat.getSelectedRow();
        if(i >= 0){
            model.removeRow(i);
            saveToFile(tableboat);
        }
        else{
            JOptionPane.showMessageDialog(null, "Deleting ERROR");
        }
    }
    
}
