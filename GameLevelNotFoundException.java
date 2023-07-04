package assignment2;

/**
 * GameLevelNotFoundException is the class which includes different ways to handle errors.
 * @author Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 * @create 2021-10-04 10:13
 */
public class GameLevelNotFoundException {
   public void Loaddat_IOException(){
      System.out.printf("No player data found.%n%n");
   }
   public void Savedat_IOException(){
      System.out.printf("No player data to save.%n%n");
   }
   public void SetWorld_IOException(){
      System.out.printf("Map not found.%n");
   }
   }

