/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jonida
 */
public class Boat {
    
    private int b_id;
    private String type;
    private int mid;
    private String length;
    private Member owned;
    
    public Boat(){
        b_id = 0;
        type = "";
        length = "";
    }
    public Boat(int boatID, String boat_type, int memberid, String boat_length){
        b_id = boatID;
        type = boat_type; 
        mid = memberid;
        boat_length = length;
        
    }
    
    public int getBoatID(){
        return b_id;
    }
    
    public String getBoatType(){
        return type;
    }
    
    public int getMemberID(){
        return mid;
    }
    
    public String getBoatLength(){
        return length;
    }
    
    public void setBoatID(int id){
        this.b_id = id;
    }
    
     public void setBoatType(String type){
        this.type = type;
    }
     
    public void setMemberID(int mid){
        this.mid = mid;
    }
    
    public void setBoatLength(String length){
        this.length = length;
    }
    
    public String getInfo(){
        owned = new Member();
        String result = owned.getName()+" "+owned.getPersonalNumber();
               
        return result;
    }

    @Override
    public String toString(){
        return b_id + "\t" + type + "\t" + mid + "\t" + length;
    }
}