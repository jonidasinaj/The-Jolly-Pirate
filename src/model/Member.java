/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author J
 */
public class Member {
    
    private int id;
    private String name;
    private String p_num;
    private Boat owns;
    
    public Member(){
        id = 0;
        name = "";
        p_num = "";
        owns = new Boat();
    }
    
    public Member(int m_id, String m_name, String m_pnum){
        id = m_id;
        name = m_name;
        p_num = m_pnum;
    }
    
    public int getID(){
        return id;
    }
    
    public String getName(){
        return name;
    }
     
    public String getPersonalNumber(){
        return p_num;
    }
     
    public void setID(int id){
        this.id = id;
    }
    
     public void setName(String name){
        this.name = name;
    }
    
    public void setPersonalNumber(String p_num){
        this.p_num = p_num;
    }
    
    public String getBoatInfo(){
        return owns.toString();
    }
    
    @Override
    public String toString(){
        return id + "\t" + name + "\t" + p_num;
    }
}
