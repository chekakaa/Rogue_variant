package assignment2;
import java.io.*;
import java.util.Scanner;
/**
 * GameEngine shows the main menu of game, and provides the functions of player's archive operation.
 * @author Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 * @create 2021-10-04 10:06
 */

public class GameEngine {
    public static final int EntityQuantity = 10;
    public static final int WorldEdge = 100;
    private int playerExist = 0;
    private int monsterExist = 0;
    private int exitCode = 0;
    private Player player;
    private Monster monster;
    private World world;
    private Item item;
    private Creature creature;
    private GameLevelNotFoundException gameLevelNotFoundException;
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
      Constructor of GameEngine
     */
    public GameEngine(){
        player = new Player();
        monster = new Monster();
        item = new Item();
        creature = new Creature();
        keyboard = new Scanner(System.in);
        gameLevelNotFoundException = new GameLevelNotFoundException();
        world = new World(player, monster, creature, item);
    }

    /**
       Logic for running the main game loop.
     */
    private void runGameLoop() {

        // Print out the title text.
        displayTitleText();
        System.out.println();

        while(exitCode == 0) {
            System.out.printf("> ");
            String command = keyboard.nextLine();
            String WorldName[] = command.split(" ");
            if (WorldName.length ==2 && WorldName[0].equals("start")){
                world.setOldVersion(0);
                if (startExamine() == 0) {
                    listenEnterKey();
                }
                else {
                    String gameWorldName = WorldName[1];
                    player.setHP(player.getMaxHP(player.getNumber()),player.getNumber());
                    monster.setHP(monster.getMaxHP(monster.getNumber()),monster.getNumber());
 //                   world = new World(player, monster, creature, item);
                    world.SetWorld(gameWorldName);
                    if (world.WorldFail==0){
                        world.enterWorld(keyboard);
                    }
                    listenEnterKey();
                }
            }
            else {
                switch (command) {
                    case "help":
                        displayHelpText();
                        break;
                    case "commands":
                        displayCommandText();
                        break;
                    case "player":
                        if (playerExist == 0) {
                            player.playerEstablish(keyboard,player.getNumber());
                            playerExist = 1;
                        } else {
                            player.playerShow(player.getNumber());
                        }
                        listenEnterKey();
                        break;
                    case "monster":
                        monster.monsterEstablish(keyboard);
                        monsterExist = 1;
                        listenEnterKey();
                        break;
                    case "save":
                        if (playerExist == 0) {
                            System.out.printf("No player data to save.%n%n");
                        } else {
                            System.out.printf("Player data saved.%n%n");
                            Savedat();
                        }
                        break;
                    case "load":
                        Loaddat();
                        break;
                    case "start":
                        world.setOldVersion(1);
                        if (startExamine() == 0) {
                            listenEnterKey();
                            break;
                        } else {
                            player.setHP(player.getMaxHP(player.getNumber()),player.getNumber());
                            monster.setHP(monster.getMaxHP(monster.getNumber()),monster.getNumber());
                            // build up a world
//                            world = new World(player, monster, creature, item);
                            world.inidata();
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
    }
    /**
       Function to test enter key.
     */
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
            System.out.printf("Player: %s %d/%d  | ", player.getName(player.getNumber()), player.getHP(player.getNumber()), player.getMaxHP(player.getNumber()));
        }

        if(monsterExist == 0){
            System.out.printf("Monster: [None]%n%n");
        }else{
            System.out.printf("Monster: %s %d/%d%n%n", monster.getName(monster.getNumber()), monster.getHP(monster.getNumber()), monster.getMaxHP(monster.getNumber()));
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
        System.out.printf("help%nplayer%nmonster%nstart <game world name>%nexit%nsave%nload%n");
        System.out.println();
    }

    private int startExamine(){
        if(playerExist == 0){
            System.out.printf("No player found, please create a player with 'player' first.%n");
            return 0;
        }
        else if(monsterExist == 0 && world.getOldVersion()==1){
            System.out.printf("No monster found, please create a monster with 'monster' first.%n");
            return 0;
        }
        return 1;
    }
    /**
       To create and save the information of player.
       @throws IOException if having no player files.
     */
    private void Savedat(){
        try {
            String url = "C:\\Users\\chekakaa\\IdeaProjects\\assignment2\\src\\assignment2\\";
            String FileName = "player.dat";
            File fil = new File(url);
            if(!fil.exists()){
                fil.createNewFile();
            }
            FileWriter fileWriter=new FileWriter(url+FileName);
            fileWriter.write(player.getName(player.getNumber()) + ' ' +player.getPlayerLV(player.getNumber()) +"\n");
            fileWriter.close();
        } catch (IOException e) {
            gameLevelNotFoundException.Savedat_IOException();
        }
    }
    /**
       To load the player file and create the player.
       @throws IOException if cannot find "player.dat".
     */
    private void Loaddat(){
        String filePath = "C:\\Users\\chekakaa\\IdeaProjects\\assignment2\\src\\assignment2\\";
        String fileName = "player.dat";
        String Name1;
//        BufferedReader inputstream = null;
//        File startFile = new File(filePath + fileName);
        ObjectInputStream file = null;
        try(BufferedReader fil = new BufferedReader(new InputStreamReader(new FileInputStream(filePath + fileName)))) {
//            inputstream = new BufferedReader(new FileReader(filePath+fileName));
//            ObjectInputStream fil = new ObjectInputStream(new FileInputStream(fileName));
            Name1 = fil.readLine();
            String playinfo[] = Name1.split(" ");
            player.setName(playinfo[0],0);
            player.setPlayerLV(Integer.parseInt(playinfo[1]),0);
            player.playercal(0);
            playerExist = 1;
            System.out.printf("Player data loaded.%n%n");
        }catch (IOException e) {
            gameLevelNotFoundException.Loaddat_IOException();
        }
    }

}