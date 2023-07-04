package assignment1;

/**
 * @author Rui Liu
 * @create 2021-10-04 10:06
 */
import java.util.Scanner;

public class GameEngine {

    public int playerExist = 0;
    public int monsterExist = 0;
    public int exitCode = 0;
    public String Command;
    public int PlayerXSite = 1;
    public int PlayerYSite = 1;
    public int MonsterXSite = 4;
    public int MonsterYSite = 2;
    public int XEdge = 5;
    public int YEdge = 3;
    public String UnnecessaryData;

    private Player player;
    private Monster monster;
    private World world;

    private Scanner keyboard;

    public static void main(String[] args) {

        // TODO: Some starter code has been provided below.
        // Edit this method as you find appropriate.

        // Creates an instance of the game engine.
        GameEngine gameEngine = new GameEngine();


        // Runs the main game loop.
        gameEngine.runGameLoop();


    }

    /**
     * Constructor of GameEngine
     */
    public GameEngine(){
        player = new Player();
        monster = new Monster();
        keyboard = new Scanner(System.in);
    }

    /*
     *  Logic for running the main game loop.
     */
    private void runGameLoop() {

        // Print out the title text.
        displayTitleText();
        System.out.println();

        // TODO: Implement your code here.

        while(exitCode == 0){
            System.out.printf("> ");
            String command = keyboard.nextLine();
            switch(command){
                case "help":
                    displayHelpText();
                    break;
                case "commands":
                    displayCommandText();
                    break;
                case "player":
                    if(playerExist == 0){
                        player.playerEstablish(keyboard);
                        playerExist = 1;
                    }else{
                        player.playerShow();
                    }
                    listenEnterKey();
                    break;
                case "monster":
                    monster.monsterEstablish(keyboard);
                    monsterExist = 1;
                    listenEnterKey();
                    break;
                case "start":
                    if(startExamine() == 0){
                        listenEnterKey();
                        break;
                    }else{
                        player.setPlayerHP(player.getPlayerMaxHP());
                        monster.setMonsterHP(monster.getMonsterMaxHP());
                        // build up a world
                        world = new World(player,monster);
                        world.enterWorld(keyboard);
                        listenEnterKey();
                        break;
                    }
                case "exit":
                    System.out.printf("Thank you for playing Rogue!%n");
                    exitCode = 1;
                    break;
            }
        }
    }

    private void listenEnterKey(){
        System.out.println();
        System.out.println("(Press enter key to return to main menu)");
        keyboard.nextLine();
        displayTitleText();
        System.out.println();
    }

    private void displayTitleText(){

        String titleText = " ____                        \n" +
                "|  _ \\ ___   __ _ _   _  ___ \n" +
                "| |_) / _ \\ / _` | | | |/ _ \\\n" +
                "|  _ < (_) | (_| | |_| |  __/\n" +
                "|_| \\_\\___/ \\__, |\\__,_|\\___|\n" +
                "COMP90041   |___/ Assignment ";

        System.out.println(titleText);
        System.out.println();
        if(playerExist == 0){
            System.out.printf("Player: [None]  | ");
        }else{
            System.out.printf("Player: %s %d/%d  | ", player.getPlayerName(), player.getPlayerHP(), player.getPlayerMaxHP());
        }

        if(monsterExist == 0){
            System.out.printf("Monster: [None]%n%n");
        }else{
            System.out.printf("Monster: %s %d/%d%n%n", monster.getMonsterName(), monster.getMonsterHP(), monster.getMonsterMaxHP());
        }
        System.out.printf("Please enter a command to continue.%n");
        System.out.printf("Type 'help' to learn how to get started.%n");
    }

    private void displayHelpText(){
        System.out.printf("Type 'commands' to list all available commands%n");
        System.out.printf("Type 'start' to start a new game%n");
        System.out.printf("Create a character, battle monsters, and find treasure!%n");
        System.out.println();
    }

    private void displayCommandText(){
        System.out.printf("help%nplayer%nmonster%nstart%nexit%n");
        System.out.println();
    }

    private int startExamine(){
        if(playerExist == 0){
            System.out.printf("No player found, please create a player with 'player' first.%n");
            return 0;
        }
        else if(monsterExist == 0){
            System.out.printf("No monster found, please create a monster with 'monster' first.%n");
            return 0;
        }
        return 1;
    }

}