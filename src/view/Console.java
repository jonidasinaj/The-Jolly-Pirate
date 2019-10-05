
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import view.ConsoleBoat;
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
import java.util.ArrayList;
/**
 *
 * @author J
 */
public class Console extends JFrame{
    
    JTable table;
    
    JTextArea mytextarea = new JTextArea();    
    JLabel idLabel = new JLabel("ID"); 
    JTextField idTextField = new JTextField(10);
    JLabel nameLabel = new JLabel("Name"); 
    JTextField nameTextField = new JTextField(10);
    JLabel p_numLabel = new JLabel("Personal Number"); 
    JTextField p_numTextField = new JTextField(10);
    JLabel searchlabel = new JLabel("Search Info For Member: ");
    JTextField searchtextField = new JTextField(10);
    
    JButton addbutton = new JButton("Create");
    JButton deletebutton = new JButton("Delete");
    JButton displayAll = new JButton("Display");
    JButton updatebutton = new JButton("Update");
    JButton exitbutton = new JButton("Exit");
    
    JButton showinfo = new JButton("Show Info For a Member");
    JButton addBoatButton = new JButton("Add Boat");
    
    
    public LinkedList<model.Member> memberLinkedList = new LinkedList<model.Member>();
    private LinkedList<model.Boat> boatLinkedList = new LinkedList<model.Boat>();
    
    public Console(){
        JPanel flow1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel flow2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel gridPanel = new JPanel(new GridLayout(2,1));
        mytextarea.setEditable(false);
         
        flow1Panel.add(idLabel);
        flow1Panel.add(idTextField);
        flow1Panel.add(nameLabel);
        flow1Panel.add(nameTextField);
        flow1Panel.add(p_numLabel);
        flow1Panel.add(p_numTextField);
        flow1Panel.add(searchlabel);
        flow1Panel.add(searchtextField);
        flow1Panel.add(addBoatButton);
        
        flow2Panel.add(addbutton);
        flow2Panel.add(deletebutton);
        flow2Panel.add(displayAll);
        flow2Panel.add(updatebutton);
        flow2Panel.add(showinfo);
        flow2Panel.add(exitbutton);
            
        gridPanel.add(flow1Panel);
        gridPanel.add(flow2Panel);
        add(mytextarea, BorderLayout.CENTER);
        add(gridPanel, BorderLayout.SOUTH);
        
        addbutton.addActionListener(event -> addMember());
        displayAll.addActionListener(event -> displayAll());
        exitbutton.addActionListener(event -> exitApplication());
        deletebutton.addActionListener(event -> deleteMember());
        updatebutton.addActionListener(event -> updateMemberData());
        addBoatButton.addActionListener(event -> addBoatForTheUser(memberLinkedList));
        showinfo.addActionListener(event -> showInfoMember());
    }
    
    private boolean isMemberIDInLinkedList(int idnum){
        boolean IdInList = false;
        int valueid = idnum;
        for(model.Member memb : memberLinkedList){
            if(memb.getID() == valueid){
                
                IdInList = true;
            }
        }
        return IdInList;    
    }
    
    private void addMember(){

        int valueid = Integer.parseInt(idTextField.getText());
            
        if(isMemberIDInLinkedList(valueid) == true){
            JOptionPane.showMessageDialog(null, "Error: Member ID is not UNIQUE!");    
        }
        else{
            
            memberLinkedList.add(new model.Member(Integer.parseInt(idTextField.getText()), nameTextField.getText(), p_numTextField.getText()));
            displayAll();
            
        }
        
    }
    
    private void deleteMember(){
        int valueid = Integer.parseInt(idTextField.getText());
    
        if(isMemberIDInLinkedList(valueid) == false){
            JOptionPane.showMessageDialog(null, "Error: Member ID is not Found!");    
        }
        else{
            for(int s=0; s < memberLinkedList.size(); s++){
                int currentID = memberLinkedList.get(s).getID();
                if(currentID == valueid){
                    memberLinkedList.remove(s);
                }
            }
            
            displayAll();         
        }
    }
    
    private void updateMemberData(){
        int valueid = Integer.parseInt(idTextField.getText());
    
        if(isMemberIDInLinkedList(valueid) == false){
            JOptionPane.showMessageDialog(null, "Error: Member ID is not Found!");    
        }
        else{
            for(int s=0; s < memberLinkedList.size(); s++){
                int currentID = memberLinkedList.get(s).getID();
                if(currentID == valueid){
                   memberLinkedList.set(s, new model.Member(valueid, nameTextField.getText(), p_numTextField.getText()));
                }
            }
            
            displayAll();         
        }
    }
    
    public void displayAll(){
        
        mytextarea.setText("");
            
        for(model.Member memb : memberLinkedList){
                mytextarea.append(memb + "\n");
        }   
    }
    
    private void showInfoMember(){
    
        mytextarea.setText("");
        char nameInitial = searchtextField.getText().charAt(0);
        for(model.Member memb : memberLinkedList){
            nameInitial = searchtextField.getText().charAt(0);
            if(memb.getName().charAt(0) == nameInitial){
                mytextarea.append(memb.getName() + "\n");
            }
        }    
    }
  
    private void addBoatForTheUser(LinkedList<model.Member> mem){
    view.ConsoleBoat app2 = new view.ConsoleBoat(mem);
    app2.setVisible(true);
    app2.setVisible(true);
    app2.setSize(800,800);
    app2.setLocation(300,200);
    app2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
    
    private void exitApplication(){
    System.exit(0);
    }
    
}
