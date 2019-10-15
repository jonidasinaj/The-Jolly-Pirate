/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.*;
import java.util.*;
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
public class RegisterMember {   
    public File x;
    private LinkedList<model.Member> memberLinkedList; 
    public JTable tablemembers;
    public DefaultTableModel model;
    
    public RegisterMember(){
        x = new File("Database.txt");
        memberLinkedList = new LinkedList<model.Member>(); 
        tablemembers = new JTable();
        model = new DefaultTableModel();
    }
    
    // Checks if a id is already in the linked list
    private boolean isMemberIDInLinkedList(int idnum, JTable tablemembers){
        boolean IdInList = false;
        int valueid = idnum;
        for(int i = 0; i < tablemembers.getRowCount(); i++){
            if(Integer.parseInt(tablemembers.getValueAt(i, 0).toString()) == valueid){
                
                IdInList = true;
            }
        }
        return IdInList;    
    }
    
    //generates a unique ID, based on a random number
    public int generateMemberID(JTable tablemembers) {
        int pid = 0;
        for (int i = 1; true; i++) {
            pid = pid + (int)(Math.random()* 171717.0) % 6 + 10;	
            pid += i;
            if (isMemberIDInLinkedList(pid, tablemembers)) // If the ID already exists
                pid = 0; // Clear the ID for the next iteration
            else
                break;
        }

        return pid;
    }
    
    //saves data to the file Database.txt
    public void saveToFile(JTable tablemembers){
        try{
            FileWriter f = new FileWriter(x);
            BufferedWriter b = new BufferedWriter(f);
            
            for(int i = 0; i < tablemembers.getRowCount(); i++){
                for(int k = 0; k < tablemembers.getColumnCount(); k++){
                    b.write(tablemembers.getValueAt(i, k).toString() + " ");
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
    
    //Reads From Text File Database.txt
    public void readFromFile(DefaultTableModel model){
        
        try{
            FileReader fr = new FileReader(x);
            BufferedReader br = new BufferedReader(fr);
            //model = (DefaultTableModel)j.getModel();
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
    
    // Searches if an ID is in the text file
    public void searchForId(JTextField idTextField, JTextArea mytextarea){
        try{
            FileReader fr = new FileReader(x);
            BufferedReader br = new BufferedReader(fr);
            FileReader fr2 = new FileReader("DatabaseBoat.txt");
            BufferedReader br2 = new BufferedReader(fr2);
            Object []lines2 = br2.lines().toArray();

            Object []lines = br.lines().toArray();
            for(int i = 0; i < lines.length; i++){
               String [] rowlines = lines[i].toString().split(" ");
               if(Integer.parseInt(rowlines[0].toString()) == Integer.parseInt(idTextField.getText().toString())){
                   mytextarea.append(rowlines[0].toString() + "  " + rowlines[1].toString() + "  " + rowlines[2].toString() + "  ");
                   for(int j = 0; j < lines2.length; j++){
                        String [] rowlines2 = lines2[j].toString().split(" ");
                        if(Integer.parseInt(rowlines[0].toString()) == Integer.parseInt(rowlines2[2].toString())){
                            mytextarea.append(rowlines2[0].toString() + "  " + rowlines2[1].toString() + "  " + rowlines2[2].toString() + "  " + rowlines2[3].toString() + "\n");
                        }
                    }
                break;
               }
            }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error Loading Data: You have no data.");
        }
    }
    
    //Displays a VERBOSE LIST of the members
    public void verboselist(JTextArea mytextarea){
        try{
            FileReader fr = new FileReader(x);
            BufferedReader br = new BufferedReader(fr);
            Object []lines = br.lines().toArray();
            FileReader fr2 = new FileReader("DatabaseBoat.txt");
            BufferedReader br2 = new BufferedReader(fr2);
            
            Object []lines2 = br2.lines().toArray();
            
            for(int i = 0; i < lines.length; i++){
               String [] rowlines = lines[i].toString().split(" ");
               for(int j = 0; j < lines2.length; j++){
                   String [] rowlines2 = lines2[j].toString().split(" ");
                    if(Integer.parseInt(rowlines[0].toString()) == Integer.parseInt(rowlines2[2].toString())){
                        mytextarea.append(rowlines[1].toString() + "  " + rowlines[2].toString() + "  " + rowlines2[0].toString() + "  " + rowlines2[1].toString() + "  " + rowlines2[2].toString() + "  " + rowlines2[3].toString() + "\n");
                    }
               }
            }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error Loading Data: You have no data.");
        }
    }
    
    //Displays a COMPACT LIST of the members
    public void compactlist(JTextArea mytextarea){
        try{
            FileReader fr = new FileReader(x);
            BufferedReader br = new BufferedReader(fr);
            Object []lines = br.lines().toArray();
            FileReader fr2 = new FileReader("DatabaseBoat.txt");
            BufferedReader br2 = new BufferedReader(fr2);
            int count = 0;
            Object []lines2 = br2.lines().toArray();
            
            for(int i = 0; i < lines.length; i++){
               count =0;
               String [] rowlines = lines[i].toString().split(" ");
               for(int j = 0; j < lines2.length; j++){
                   String [] rowlines2 = lines2[j].toString().split(" ");
                    if(Integer.parseInt(rowlines[0].toString()) == Integer.parseInt(rowlines2[2].toString())){
                        count++;
                    }
               }
               mytextarea.append(rowlines[1].toString() + "  " + rowlines[2].toString() + "  " + count + "\n");
            }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error Loading Data: You have no data.");
        }
    }
    
    // Deletes from a file
    public void deleteFromFile(JTable tablemembers, DefaultTableModel model){
        int i = tablemembers.getSelectedRow();
        if(i >= 0){
            model.removeRow(i);            
            saveToFile(tablemembers);
        }
        else{
            JOptionPane.showMessageDialog(null, "Deleting ERROR");
        }
    }    
}
