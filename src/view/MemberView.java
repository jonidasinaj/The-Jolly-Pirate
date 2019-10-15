
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import view.BoatView;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jonida
 */
public class MemberView extends JFrame{
    private model.RegisterMember regmem;
    private model.Member member;
        
    public JTable tablemembers = new JTable();
    Object[] columns = {"ID", "Name", " Personal Number"};
    DefaultTableModel model = new DefaultTableModel();
    
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
    JButton compactbutton = new JButton("Compact List");
    JButton verbosebutton = new JButton("Verbose List");
    JButton showinfo = new JButton("Show Info For a Member");
    JButton addBoatButton = new JButton("Add Boat");
    JScrollPane scroll = new JScrollPane(tablemembers);
    
    
    public LinkedList<model.Member> memberLinkedList = new LinkedList<model.Member>();
    private LinkedList<model.Boat> boatLinkedList = new LinkedList<model.Boat>();
    
    public MemberView(){
        regmem = new model.RegisterMember();
        JPanel flow1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel flow2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel memberLayout = new JPanel();
        memberLayout.setLayout(new GridLayout(2,2));
        mytextarea.setEditable(false);
        
        model.setColumnIdentifiers(columns);
        tablemembers.setModel(model);
        tablemembers.setBackground(Color.cyan);
        tablemembers.setForeground(Color.white);
        Font font = new Font("", 1, 20);
        tablemembers.setFont(font);
        tablemembers.setRowHeight(20);
        
        flow1Panel.add(scroll);
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
        flow2Panel.add(compactbutton);
        flow2Panel.add(verbosebutton);
        
        memberLayout.add(flow1Panel);
        memberLayout.add(flow2Panel);
        add(mytextarea, BorderLayout.CENTER);
        add(memberLayout, BorderLayout.SOUTH);
        
        addbutton.addActionListener(event -> addMember());
        displayAll.addActionListener(event -> regmem.readFromFile(model));
        compactbutton.addActionListener(event -> regmem.compactlist(mytextarea));
        verbosebutton.addActionListener(event -> regmem.verboselist(mytextarea));
        deletebutton.addActionListener(event -> deleteMember());
        updatebutton.addActionListener(event -> updateMemberData());
        addBoatButton.addActionListener(event -> addBoatForTheUser(memberLinkedList));
        showinfo.addActionListener(event -> search());
        
        tablemembers.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e){    
            int i = tablemembers.getSelectedRow();  
            idTextField.setText(model.getValueAt(i, 0).toString());
            nameTextField.setText(model.getValueAt(i,1).toString());
            p_numTextField.setText(model.getValueAt(i,2).toString());
        }
        }); 
    }
    
    // adds a member
    private void addMember(){   
        memberLinkedList.add(new model.Member(regmem.generateMemberID(tablemembers), nameTextField.getText(), p_numTextField.getText()));
        displayAll();
    }
    
    //deletes a member from the linked list and file also
    private void deleteMember(){
        int valueid = Integer.parseInt(idTextField.getText());
            for(int s=0; s < memberLinkedList.size(); s++){
                int currentID = memberLinkedList.get(s).getID();
                if(currentID == valueid){
                    memberLinkedList.remove(s);
                }
            }          
        regmem.deleteFromFile(tablemembers, model);  
    }
    
    //updates member data
    private void updateMemberData(){
        int valueid = Integer.parseInt(idTextField.getText());        
        for(int s=0; s < memberLinkedList.size(); s++){
            int currentID = memberLinkedList.get(s).getID();
            if(currentID == valueid){
               memberLinkedList.set(s, new model.Member(valueid, nameTextField.getText(), p_numTextField.getText()));
            }
        }
        
        int i = tablemembers.getSelectedRow();
        if(i >= 0){
            model.setValueAt(idTextField.getText(), i, 0);
            model.setValueAt(nameTextField.getText(), i, 1);
            model.setValueAt(p_numTextField.getText(), i, 2);
        }
        else{
            JOptionPane.showMessageDialog(null, "Update ERROR!");
        }
    }
    
    // Displays the data from the text file (Databse.txt) to the JTable.
    public void displayAll(){
        Object [] row = new Object[3];
        row[0] = regmem.generateMemberID(tablemembers);
        row[1] = nameTextField.getText();
        row[2] = p_numTextField.getText();
        
        model.addRow(row);
        regmem.saveToFile(tablemembers);
    }
    
    // Based on the ID entered in the Id Textfield it displays the information for the specific user.
    private void search(){
        regmem.searchForId(idTextField, mytextarea);
    }
  
    // Opens the JFrame for Boat 
    private void addBoatForTheUser(LinkedList<model.Member> mem){
        view.BoatView app2 = new view.BoatView(mem);
        app2.setVisible(true);
        app2.setVisible(true);
        app2.setSize(800,800);
        app2.setLocation(300,200);
        app2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
