package assignment1;

/**
 * @author Rui Liu
 * @create 2021-10-04 10:04
 */
import java.nio.channels.Pipe;
import java.util.Scanner;

/**
 * TODO: Method World() contains displaying game map and the battleloop between the player and the monster.
 * @author TODO: Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 *
 */
public class World {
    private int WIDTH_OF_WORLD = 6;
    private int HEIGHT_OF_WORLD = 4;

    private Player player;
    private Monster monster;

    private boolean battleOver;

    public World(Player player, Monster monster){
        this.player = player;
        this.monster = monster;
        this.player.setxPos(1);
        this.player.setyPos(1);
        this.monster.setxPos(4);
        this.monster.setyPos(2);
    }

    /**
     * enter the world
     */
    public void enterWorld(Scanner keyboard){
        printWorldMap();

        boolean returnHome = false;
        battleOver = false;
        while (!returnHome && !battleOver){
            System.out.printf("> ");
            String command = keyboard.nextLine();
            switch (command){
                case "home":{
                    System.out.printf("Returning home...%n");
                    returnHome = true;
                    break;
                }
                case "w":
                case "s":
                case "a":
                case "d":
                    step(command);
                    break;
            }
        }

    }

    /**
     * print the world map
     */
    public void printWorldMap(){
        for(int i = 0; i < HEIGHT_OF_WORLD; i++){
            for(int j = 0; j < WIDTH_OF_WORLD; j++){
                if(player.getyPos() == i && player.getxPos() == j){
                    System.out.printf("%c", player.getFirstLetterOfName());
                }
                else if(monster.getyPos() == i && monster.getxPos() == j){
                    System.out.printf("%c", monster.getFirstLetterOfName());
                }
                else{
                    System.out.print(".");
                }
                if(j == WIDTH_OF_WORLD-1){
                    System.out.printf("%n");
                }
            }
        }
        System.out.println();
    }


    private void step(String direction){
        switch (direction){
            case "w":
                goNorth();
                break;
            case "a":
                goWest();
                break;
            case "s":
                goSouth();
                break;
            case "d":
                goEast();
                break;
        }
        if(player.getxPos() == monster.getxPos() && player.getyPos() == monster.getyPos()){
            // start the battle if the player encounters the monster
            battleLoop();
        }else{
            printWorldMap();
        }
    }
    public void goWest(){
        if(player.getxPos() > 0){
            player.setxPos(player.getxPos()-1);
        }
    }

    public void goEast(){
        if(player.getxPos() < WIDTH_OF_WORLD-1){
            player.setxPos(player.getxPos()+1);
        }
    }

    public void goNorth(){
        if(player.getyPos() > 0){
            player.setyPos(player.getyPos()-1);
        }
    }

    public void goSouth(){
        if(player.getyPos() < HEIGHT_OF_WORLD-1){
            player.setyPos(player.getyPos()+1);
        }
    }

    public void battleLoop(){
        System.out.printf("%s encountered a %s!%n%n", player.getPlayerName(), monster.getMonsterName());

        while(player.getPlayerHP() > 0 && monster.getMonsterHP() > 0){
            System.out.printf("%s %d/%d | %s %d/%d%n", player.getPlayerName(), player.getPlayerHP(), player.getPlayerMaxHP(),
                    monster.getMonsterName(), monster.getMonsterHP(), monster.getMonsterMaxHP());
            int value = monster.getMonsterHP() - player.getPlayerDamage();
            monster.setMonsterHP(value);
            System.out.printf("%s attacks %s for %d damage.%n", player.getPlayerName(), monster.getMonsterName(), player.getPlayerDamage());
            if(monster.getMonsterHP() <= 0){
                System.out.printf("%s wins!%n", player.getPlayerName());
                continue;
            }
            value = player.getPlayerHP() - monster.getMonsterDamage();
            System.out.printf("%s attacks %s for %d damage.%n%n", monster.getMonsterName(), player.getPlayerName(), monster.getMonsterDamage());
            player.setPlayerHP(value);
            if(player.getPlayerHP() <= 0){
                System.out.printf("%s wins!%n", monster.getMonsterName());
                break;
            }
        }
        battleOver = true;
    }
}
