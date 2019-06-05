package com.ben;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.List;  

public class PlayerDao { 
   public List<Player> getAllPlayers(){ 
      
      List<Player> playerList = null; 
      try { 
         File file = new File("Players.dat"); 
         if (!file.exists()) { 
            Player player = new Player(10, "Ben", "PointGuard"); 
            playerList = new ArrayList<Player>(); 
            playerList.add(player); 
            savePlayerList(playerList); 
         } 
         else{ 
            FileInputStream fis = new FileInputStream(file); 
            ObjectInputStream ois = new ObjectInputStream(fis); 
            playerList = (List<Player>) ois.readObject(); 
            ois.close(); 
         } 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } catch (ClassNotFoundException e) { 
         e.printStackTrace(); 
      }   
      return playerList; 
   } 
   private void savePlayerList(List<Player> playerList){ 
      try { 
         File file = new File("Players.dat"); 
         FileOutputStream fos;  
         fos = new FileOutputStream(file); 
         ObjectOutputStream oos = new ObjectOutputStream(fos); 
         oos.writeObject(playerList); 
         oos.close(); 
      } catch (FileNotFoundException e) { 
         e.printStackTrace(); 
      } catch (IOException e) { 
         e.printStackTrace(); 
      } 
   }
   public Player getPlayer(int id){
	      List<Player> players = getAllPlayers();

	      for(Player player: players){
	         if(player.getId() == id){
	            return player;
	         }
	      }
	      return null;
	}
	public int addPlayer(Player pPlayer){
	      List<Player> playerList = getAllPlayers();
	      boolean playerExists = false;
	      for(Player player: playerList){
	         if(player.getId() == pPlayer.getId()){
	            playerExists = true;
	            break;
	         }
	      }		
	      if(!playerExists){
	         playerList.add(pPlayer);
	         savePlayerList(playerList);
	         return 1;
	      }
	      return 0;
	   }

	   public int updatePlayer(Player pPlayer){
	      List<Player> playerList = getAllPlayers();

	      for(Player player: playerList){
	         if(player.getId() == pPlayer.getId()){
	            int index = playerList.indexOf(player);
	            playerList.set(index, pPlayer);
	            savePlayerList(playerList);
	            return 1;
	         }
	      }		
	      return 0;
	   }

	   public int deletePlayer(int id){
	      List<Player> playerList = getAllPlayers();

	      for(Player player: playerList){
	         if(player.getId() == id){
	            int index = playerList.indexOf(player);			
	            playerList.remove(index);
	            savePlayerList(playerList);
	            return 1;   
	         }
	      }		
	      return 0;
	   }
}
