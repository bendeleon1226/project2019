package com.webservice.test;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.ben.Player;

public class WebServiceTester  {

   private Client client;
   private String REST_SERVICE_URL = "http://localhost:8080/RestFulWSJersey/restfulws/BenService/benteners";
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String PASS = "pass";
   private static final String FAIL = "fail";

   private void init(){
      this.client = ClientBuilder.newClient();
   }

   public static void main(String[] args){
      WebServiceTester tester = new WebServiceTester();
      //initialize the tester
      tester.init();
      
      //test get all users Web Service Method
      tester.testGetAllPlayers();
      
      //test get user Web Service Method 
      tester.testGetPlayer();
      
      //test update user Web Service Method
      tester.testUpdatePlayer();
      
      //test add user Web Service Method
      tester.testAddPlayer();
      
      //test delete user Web Service Method
      tester.testDeletePlayer();
   }
   //Test: Get list of all players
   //Test: Check if list is not empty
   private void testGetAllPlayers(){
      GenericType<List<Player>> list = new GenericType<List<Player>>() {};
      List<Player> players = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .get(list);
      String result = PASS;
      if(players.isEmpty()){
         result = FAIL;
      }
      System.out.println("Test case name: testGetAllPlayers, Result: " + result );
   }
   //Test: Get Player of id 10
   //Test: Check if player is same as sample player
   private void testGetPlayer(){
      Player samplePlayer = new Player();
      samplePlayer.setId(10);

      Player player = client
         .target(REST_SERVICE_URL)
         .path("/{jerseyId}")
         .resolveTemplate("jerseyId", 10)
         .request(MediaType.APPLICATION_XML)
         .get(Player.class);
      String result = FAIL;
      if(samplePlayer != null && samplePlayer.getId() == player.getId()){
         result = PASS;
      }
      System.out.println("Test case name: testGetPlayer, Result: " + result );
   }
   //Test: Update Player of id 10
   //Test: Check if result is success XML.
   private void testUpdatePlayer(){
      Form form = new Form();
      form.param("jerseyId", "10");
      form.param("jerseyName", "steven");
      form.param("role", "center");

      String callResult = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .put(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testUpdatePlayer, Result: " + result );
   }
   //Test: Add Player of id 11
   //Test: Check if result is success XML.
   private void testAddPlayer(){
      Form form = new Form();
      form.param("jerseyId", "11");
      form.param("jerseyName", "Seven");
      form.param("role", "forward");

      String callResult = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_XML)
         .post(Entity.entity(form,
            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
            String.class);
   
      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testAddPlayer, Result: " + result );
   }
   //Test: Delete Player of id 10
   //Test: Check if result is success XML.
   private void testDeletePlayer(){
      String callResult = client
         .target(REST_SERVICE_URL)
         .path("/{jerseyId}")
         .resolveTemplate("jerseyId", 10)
         .request(MediaType.APPLICATION_XML)
         .delete(String.class);

      String result = PASS;
      if(!SUCCESS_RESULT.equals(callResult)){
         result = FAIL;
      }

      System.out.println("Test case name: testDeletePlayer, Result: " + result );
   }
}
