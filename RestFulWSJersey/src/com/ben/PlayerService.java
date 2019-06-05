package com.ben;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;



//how to call from postman
//GET method
//http://localhost:8080/RestFulWSJersey/restfulws/BenService/benteners

@Path("/BenService") 

public class PlayerService {  
   PlayerDao playerDao = new PlayerDao();
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";   
   
   @GET 
   @Path("/benteners") 
   @Produces(MediaType.APPLICATION_XML) 
   public List<Player> getPlayers(){ 
      return playerDao.getAllPlayers(); 
   }

   @GET
   @Path("/benteners/{jerseyid}")
   @Produces(MediaType.APPLICATION_XML)
   public Player getPlayer(@PathParam("jerseyid") int jerseyId){
      return playerDao.getPlayer(jerseyId);
   }

   @POST
   @Path("/benteners")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createPlayer(@FormParam("jerseyId") int jerseyId,
      @FormParam("jerseyName") String jerseyName,
      @FormParam("role") String role,
      @Context HttpServletResponse servletResponse) throws IOException{
      Player player = new Player(jerseyId, jerseyName, role);
      int result = playerDao.addPlayer(player);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @PUT
   @Path("/benteners")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updatePlayer(@FormParam("jerseyId") int jerseyId,
      @FormParam("jerseyName") String jerseyName,
      @FormParam("role") String role,
      @Context HttpServletResponse servletResponse) throws IOException{
      Player player = new Player(jerseyId, jerseyName, role);
      int result = playerDao.updatePlayer(player);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @DELETE
   @Path("/benteners/{jerseyId}")
   @Produces(MediaType.APPLICATION_XML)
   public String deletePlayer(@PathParam("jerseyId") int jerseyId){
      int result = playerDao.deletePlayer(jerseyId);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @OPTIONS
   @Path("/benteners")
   @Produces(MediaType.APPLICATION_XML)
   public String getSupportedOperations(){
      return "<operations>GET, PUT, POST, DELETE</operations>";
   }

}
