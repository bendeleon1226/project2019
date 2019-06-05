package com.ben;

import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "player") 

public class Player implements Serializable {  
   private static final long serialVersionUID = 1L; 
   private int id; 
   private String jerseyName; 
   private String role;  
   public Player(){} 
    
   public Player(int id, String jerseyName, String role){  
      this.id = id; 
      this.jerseyName = jerseyName; 
      this.role = role; 
   }  
   public int getId() { 
      return id; 
   }  
   @XmlElement 
   public void setId(int id) { 
      this.id = id; 
   } 
   public String getJerseyName() { 
      return jerseyName; 
   } 
   @XmlElement
   public void setJerseyName(String jerseyName) { 
      this.jerseyName = jerseyName; 
   } 
   public String getRole() { 
      return role; 
   } 
   @XmlElement 
   public void setRole(String role) { 
      this.role = role; 
   }   
} 
