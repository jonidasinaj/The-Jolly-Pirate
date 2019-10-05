/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import view.Console;
import model.Member;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
/**
 *
 * @author J
 */
public class ConsoleBoat extends JFrame{
     
    JTextArea mytextarea = new JTextArea();    
    JLabel idLabel = new JLabel("Boat ID"); 
    JTextField idTextField = new JTextField(10);
    
    JLabel boatLabel = new JLabel("Boat Type");
    String[] types = {"Sailboat", "Motorsailer", "kayak/Canoe", "Other"};
    JComboBox boattypes = new JComboBox(types);
    
    JLabel memberowner = new JLabel("Member Owner"); 
    JTextField memberownerTextField = new JTextField(10);
    
    JLabel boatlength = new JLabel("Boat Length"); 
    JTextField lengthTextField = new JTextField(10);
    
    JButton addbutton = new JButton("CreateBoat");
    JButton deletebutton = new JButton("Delete");
    JButton displayAll = new JButton("Display");
    JButton updatebutton = new JButton("Update");
    JButton listbutton = new JButton("Compact List");
    //JButton exitbutton = new JButton("Exit"); 
    JButton provabutton = new JButton("Verbose List"); 
    
    private LinkedList<model.Boat> boatLinkedList = new LinkedList<model.Boat>();
    private LinkedList<model.Member> memberLinkedList = new LinkedList<model.Member>();
    
    public ConsoleBoat(LinkedList<model.Member> mem){
        JPanel flow1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel flow2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel gridPanel = new JPanel(new GridLayout(2,1));
        mytextarea.setEditable(false);
        memberLinkedList = mem;
           
        flow1Panel.add(idLabel);
        flow1Panel.add(idTextField);
        flow1Panel.add(boatLabel);
        flow1Panel.add(boattypes);
        flow1Panel.add(memberowner);
        flow1Panel.add(memberownerTextField);
        flow1Panel.add(boatlength);
        flow1Panel.add(lengthTextField);
     
        
        flow2Panel.add(addbutton);
        flow2Panel.add(deletebutton);
        flow2Panel.add(displayAll);
        flow2Panel.add(updatebutton);
        flow2Panel.add(listbutton);
        //flow2Panel.add(exitbutton);
        flow2Panel.add(provabutton);

        gridPanel.add(flow1Panel);
        gridPanel.add(flow2Panel);
        add(mytextarea, BorderLayout.CENTER);
        add(gridPanel, BorderLayout.SOUTH);
              
        addbutton.addActionListener(event -> addBoat());
        displayAll.addActionListener(event -> displayBoatAll());
        //exitbutton.addActionListener(event -> exitBoatApplication());
        deletebutton.addActionListener(event -> deleteBoat());
        updatebutton.addActionListener(event -> updateBoatData());
        listbutton.addActionListener(event -> ListData());
        provabutton.addActionListener(event -> VerboseList());
        
    }
    
    private boolean isBoatIDInLinkedList(int idnum){
        boolean IdInList = false;
        int valueid = idnum;
        for(model.Boat b : boatLinkedList){
            if(b.getBoatID() == valueid){
                
                IdInList = true;
            }
        }
        return IdInList;    
    }
    
    private void addBoat(){
                
        int valueid = Integer.parseInt(idTextField.getText());
            
        if(isBoatIDInLinkedList(valueid) == true){
            JOptionPane.showMessageDialog(null, "Error: Boat ID is not UNIQUE!");    
        }
        else{
            boatLinkedList.add(new model.Boat(Integer.parseInt(idTextField.getText()), boattypes.getSelectedItem().toString(), Integer.parseInt(memberownerTextField.getText()), lengthTextField.getText()));
            displayBoatAll();        
        }    
    }
    
    private void deleteBoat(){
        int valueid = Integer.parseInt(idTextField.getText());
    
        if(isBoatIDInLinkedList(valueid) == false){
            JOptionPane.showMessageDialog(null, "Error: Member ID is not Found!");    
        }
        else{
            for(int s=0; s < boatLinkedList.size(); s++){
                int currentID = boatLinkedList.get(s).getBoatID();
                if(currentID == valueid){
                    boatLinkedList.remove(s);
                }
            }
            
            displayBoatAll();         
        }
    }
    
    private void updateBoatData(){
        int valueid = Integer.parseInt(idTextField.getText());
    
        if(isBoatIDInLinkedList(valueid) == false){
            JOptionPane.showMessageDialog(null, "Error: Member ID is not Found!");    
        }
        else{
            for(int s=0; s < boatLinkedList.size(); s++){
                int currentID = boatLinkedList.get(s).getBoatID();
                if(currentID == valueid){
                   boatLinkedList.set(s, new model.Boat(valueid, boattypes.getSelectedItem().toString(), Integer.parseInt(memberownerTextField.getText()), lengthTextField.getText()));
                }
            }
            
            displayBoatAll();         
        }
    }
    
    private void ListData(){
        mytextarea.setText("");
        int count = 0;
        int valueid = Integer.parseInt(memberownerTextField.getText());
        for(int s=0; s < boatLinkedList.size(); s++){
            int currentID = boatLinkedList.get(s).getMemberID();
            if(currentID == valueid){
                count++;
                mytextarea.append(boatLinkedList.get(s).getBoatID() + "  " + boatLinkedList.get(s).getBoatType() + "  " + boatLinkedList.get(s).getMemberID() + " " + boatLinkedList.get(s).getBoatLength() + "\n");
            }
        }
    }
    
    private void VerboseList(){
        mytextarea.setText("");
        view.Console appl = new view.Console();
        appl.setVisible(true);
        int currentID, current;
        int c = 0;
        int valueid = Integer.parseInt(memberownerTextField.getText());
        for(int s=0; s < boatLinkedList.size(); s++){
            currentID = boatLinkedList.get(s).getMemberID();
            if(currentID == valueid){
                c++;
                mytextarea.append(boatLinkedList.get(s).getBoatID() + "  " + boatLinkedList.get(s).getBoatType() + "  " + boatLinkedList.get(s).getMemberID() + " " + lengthTextField.getText());
            }
        }
         mytextarea.append("  " + c + " boats");
            for(model.Member memb : memberLinkedList){
            current = memb.getID();
            if(current == valueid){
                mytextarea.append("  " + current + memb.getName() + "  " + memb.getPersonalNumber());
            }
        }
        
    }
    
    private void displayBoatAll(){
        
        mytextarea.setText("");
            
        for(model.Boat b : boatLinkedList){
                mytextarea.append(b + "\n");
        }
    }
     
    private void exitBoatApplication(){
    System.exit(0);
    }
    
}
