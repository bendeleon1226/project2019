package com.ben;

import java.util.List; 
import javax.ws.rs.GET; 
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;  
@Path("/BenService") 

public class PlayerService {  
   PlayerDao playerDao = new PlayerDao();  
   @GET 
   @Path("/benteners") 
   @Produces(MediaType.APPLICATION_XML) 
   public List<Player> getUsers(){ 
      return playerDao.getAllPlayers(); 
   }  
}
