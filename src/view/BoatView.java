/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import view.MemberView;
import model.Member;
import java.awt.*;
import java.io.*;
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
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
/**
 *
 * @author Jonida
 */
public class BoatView extends JFrame{
    
    private model.RegisterBoat regboat;
    private model.RegisterMember regmember;
    private LinkedList<model.Member> memb;
    private MemberView member;
       
    JTable tableboat = new JTable();
    Object[] columns = {"ID", " Type", "OwnerID", "Length"};
    DefaultTableModel model = new DefaultTableModel();
     
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
    JButton exitbutton = new JButton("Exit");  
    JScrollPane scrollboat = new JScrollPane(tableboat);
    
    private LinkedList<model.Boat> boatLinkedList = new LinkedList<model.Boat>();
    private LinkedList<model.Member> memberLinkedList = new LinkedList<model.Member>();
    
    public BoatView(LinkedList<model.Member> mem){
        regboat = new model.RegisterBoat();
        regmember = new model.RegisterMember();
        memb = new LinkedList<model.Member>();
        member = new MemberView();
        JPanel flow1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel flow2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel gridPanel = new JPanel(new GridLayout(2,2));
        mytextarea.setEditable(false);
        memberLinkedList = mem;
        
        model.setColumnIdentifiers(columns);
        tableboat.setModel(model);
        tableboat.setBackground(Color.cyan);
        tableboat.setForeground(Color.white);
        Font font = new Font("", 1, 20);
        tableboat.setFont(font);
        tableboat.setRowHeight(20);
        
        flow1Panel.add(scrollboat);
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
        flow2Panel.add(exitbutton);

        gridPanel.add(flow1Panel);
        gridPanel.add(flow2Panel);
        add(mytextarea, BorderLayout.CENTER);
        add(gridPanel, BorderLayout.SOUTH);
              
        addbutton.addActionListener(event -> addBoat());
        displayAll.addActionListener(event -> regboat.readFromFile(model));
        exitbutton.addActionListener(event -> exitBoatApplication());
        deletebutton.addActionListener(event -> deleteBoat());
        updatebutton.addActionListener(event -> updateBoatData());
        
        tableboat.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e){    
            int i = tableboat.getSelectedRow();  
            idTextField.setText(model.getValueAt(i,0).toString());
            boattypes.setSelectedItem(model.getValueAt(i,1).toString());
            memberownerTextField.setText(model.getValueAt(i,2).toString());
            lengthTextField.setText(model.getValueAt(i,3).toString());
            
        }
        });
        
    }
    
    // Adds the boat
    private void addBoat(){     
        boatLinkedList.add(new model.Boat(regboat.generateBoatID(tableboat), boattypes.getSelectedItem().toString(), Integer.parseInt(memberownerTextField.getText()), lengthTextField.getText()));
        displayBoatAll();
    }
    
    // Deletes a Boat based on the row selected in the JTable in the view and also deletes it from the text file.
    private void deleteBoat(){
        int valueid = Integer.parseInt(idTextField.getText());
        for(int s=0; s < boatLinkedList.size(); s++){
            int currentID = boatLinkedList.get(s).getBoatID();
            if(currentID == valueid){
                boatLinkedList.remove(s);
            }
        }
        regboat.deleteFromFile(tableboat, model);
    }
    
    //Updates the boat data.
    private void updateBoatData(){
        int valueid = Integer.parseInt(idTextField.getText());
        int s;
        
        for(s=0; s < boatLinkedList.size(); s++){
            int currentID = boatLinkedList.get(s).getBoatID();
            if(currentID == valueid){
               boatLinkedList.set(s, new model.Boat(valueid, boattypes.getSelectedItem().toString(), Integer.parseInt(memberownerTextField.getText()), lengthTextField.getText()));
            }
        }
            
        int i = tableboat.getSelectedRow();
        if(i >= 0){
            model.setValueAt(idTextField.getText(), i, 0);
            model.setValueAt(boattypes.getSelectedItem().toString(), i, 1);
            model.setValueAt(Integer.parseInt(memberownerTextField.getText()), i, 2);
            model.setValueAt(lengthTextField.getText(), i, 3);
        }
        else{
            JOptionPane.showMessageDialog(null, "Update ERROR!");
        }   
    }
    
    // Displays Boat information taken from the text file (DatabaseBoat.txt)
    private void displayBoatAll(){ 
        Object [] row = new Object[4];
        row[0] = regboat.generateBoatID(tableboat);
        row[1] = boattypes.getSelectedItem();
        row[2] = memberownerTextField.getText();
        row[3] = lengthTextField.getText();
        
        
        model.addRow(row);
        regboat.saveToFile(tableboat);
    }
    
    // Exits the application
    private void exitBoatApplication(){
    System.exit(0);
    }
    
}