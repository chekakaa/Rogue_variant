package assignment2;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * World sets the game map, allows player to operate in the map and shows battle loop.
 * @author Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 * @create 2021-10-04 10:04
 */

/**
 * TODO: Method World() contains displaying game map and the battleloop between the player and the monster.
 * @author TODO: Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 *
 */
public class World {
    private int WIDTH_OF_WORLD;
    private int HEIGHT_OF_WORLD;
    private int monstercount;
    private int itemcount;
    private int Doing_monster;
    private int Doing_item;
    private Monster monster;
    private Item item;
    private Player player;
    private Creature creature;
    private GameLevelNotFoundException gameLevelNotFoundException;
    private int OldVersion = 0;
    public int WorldFail = 0;
    //    char [][]Landform = new char[WIDTH_OF_WORLD+1][HEIGHT_OF_WORLD+1];
    char [][]Landform = new char[GameEngine.WorldEdge][GameEngine.WorldEdge];
    private int BonusAtk;
    private boolean GameOver;
    /**
       To reset the data of world and try to use method of other class.
       @param player set position of player and get method of class "player".
       @param monster set position of monster and get method of class "monster".
       @param creature set position of creature(might be redundant but in case adding other creature in the future) and get method of class "creature".
       @param item set position of item and get method of class "item".
     */
    public World(Player player, Monster monster, Creature creature, Item item){
        this.player = player;
        this.monster = monster;
        this.monster.setxPos(monster.getxPos(monster.getNumber()),monster.getNumber());
        this.monster.setyPos(monster.getyPos(monster.getNumber()),monster.getNumber());
        this.item = item;
        this.item.setxPos(item.getxPos(item.getNumber()),item.getNumber());
        this.item.setyPos(item.getyPos(item.getNumber()),item.getNumber());
        this.player.setxPos(player.getxPos(player.getNumber()),player.getNumber());
        this.player.setyPos(player.getyPos(player.getNumber()),player.getNumber());
        this.creature = creature;
        this.creature.setxPos(creature.getxPos(creature.getNumber()),creature.getNumber());
        this.creature.setyPos(creature.getyPos(creature.getNumber()),creature.getNumber());
        gameLevelNotFoundException = new GameLevelNotFoundException();
    }

    /**
     * enter the world
     */
    public void enterWorld(Scanner keyboard){
//        System.out.printf("%d %d %d %d %d %d",HEIGHT_OF_WORLD, WIDTH_OF_WORLD,monstercount,itemcount,player.getyPos(player.getNumber()),player.getxPos(player.getNumber()));
        printWorldMap();
//        System.out.println("hello!");
        boolean returnHome = false;
        GameOver = false;
        BonusAtk = 0;
        while (!returnHome && !GameOver){
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
                    MonsterMove();
                    step(command);
                    break;
                default:
                    MonsterMove();
                    for (int i=0; i<monstercount; i++){
                        if(player.getxPos(player.getNumber()) == monster.getxPos(i) && player.getyPos(player.getNumber()) == monster.getyPos(i)&& monster.getmonsterexist(i)==1){
                            // start the battle if the player encounters the monster
                            Doing_monster = i;
                            battleLoop();
                            break;
                        }
                    }
                    if (GameOver == false) {
                        printWorldMap();
                    }
            }
        }

    }

    /**
     * print the world map
     */
    public void printWorldMap(){
        for(int i = 0; i < HEIGHT_OF_WORLD; i++){
            for(int j = 0; j < WIDTH_OF_WORLD; j++){
                int print = 0;
                if(player.getyPos(player.getNumber()) == i && player.getxPos(player.getNumber()) == j){
                    System.out.printf("%c", player.getFirstLetterOfName(player.getNumber()));
                    print = 1;
                }
                for(int k=0; k<monstercount && print ==0; k++){
                    if(monster.getyPos(k) == i && monster.getxPos(k) == j && monster.getmonsterexist(k)==1){
                        System.out.printf("%c", monster.getFirstLetterOfName(k));
                        print = 1;
                    }}
                for(int k=0; k<itemcount && print ==0; k++){
                    if(item.getyPos(k) == i && item.getxPos(k) == j && item.getitemexist(k)==1 ){
                        System.out.printf("%c", item.getTypeOfItem(k));
                        print = 1;
                    }}
                if(print == 0){
                    System.out.printf("%c",Landform[j][i]);
                }
                if(j == WIDTH_OF_WORLD-1){
                    System.out.printf("%n");
                }
            }
        }
        System.out.println();
    }

    /**
       To let player move and handle the situation after moving.
       @param direction use "wasd" to move.
     */
    private void step(String direction){
        switch (direction){
            case "w":
                goNorth(player, player.getNumber());
                break;
            case "a":
                goWest(player, player.getNumber());
                break;
            case "s":
                goSouth(player, player.getNumber());
                break;
            case "d":
                goEast(player, player.getNumber());
                break;
        }
        for (int i=0; i<monstercount ; i++){
            if(player.getxPos(player.getNumber()) == monster.getxPos(i) && player.getyPos(player.getNumber()) == monster.getyPos(i)&& monster.getmonsterexist(i)==1){
                // start the battle if the player encounters the monster
                Doing_monster = i;
                battleLoop();
            }
        }
        for (int i=0; i<itemcount; i++){
            if(player.getxPos(player.getNumber()) == item.getxPos(i) && player.getyPos(player.getNumber()) == item.getyPos(i) && item.getitemexist(i)==1){
//                Doing_item = i;
                GetItem(i);
            }
        }
        if (GameOver == false) {
            printWorldMap();
        }

    }
    /**
       To let creature move to certain position if possible.(Same function of other three method)
       @param creature player and monster are both movable.
       @param EntityNumber to determine which player or monster to move.
     */
    public void goWest(Creature creature, int EntityNumber){
        if(creature.getxPos(EntityNumber) > 0 && Landform[creature.getxPos(EntityNumber)-1][creature.getyPos(EntityNumber)] == '.'){
            creature.setxPos(creature.getxPos(EntityNumber)-1,EntityNumber);
        }
    }

    public void goEast(Creature creature, int EntityNumber){
        if(creature.getxPos(EntityNumber) < WIDTH_OF_WORLD-1 && Landform[creature.getxPos(EntityNumber)+1][creature.getyPos(EntityNumber)] == '.'){
            creature.setxPos(creature.getxPos(EntityNumber)+1,EntityNumber);
        }
    }

    public void goNorth(Creature creature, int EntityNumber){
        if(creature.getyPos(EntityNumber) > 0 && Landform[creature.getxPos(EntityNumber)][creature.getyPos(EntityNumber)-1] == '.'){
            creature.setyPos(creature.getyPos(EntityNumber)-1,EntityNumber);
        }
    }

    public void goSouth(Creature creature, int EntityNumber){
        if(creature.getyPos(EntityNumber) < HEIGHT_OF_WORLD-1 && Landform[creature.getxPos(EntityNumber)][creature.getyPos(EntityNumber)+1] == '.'){
            creature.setyPos(creature.getyPos(EntityNumber)+1,EntityNumber);
        }
    }
    /**
     To start and print the battle.
     */
    public void battleLoop(){
        System.out.printf("%s encountered a %s!%n%n", player.getName(player.getNumber()), monster.getName(Doing_monster));

        while(player.getHP(player.getNumber()) > 0 && monster.getHP(Doing_monster) > 0){
            System.out.printf("%s %d/%d | %s %d/%d%n", player.getName(player.getNumber()), player.getHP(player.getNumber()), player.getMaxHP(player.getNumber()),
                    monster.getName(Doing_monster), monster.getHP(Doing_monster), monster.getMaxHP(Doing_monster));
            int value = monster.getHP(Doing_monster) - player.getDamage(player.getNumber()) - BonusAtk;
            monster.setHP(value,Doing_monster);
            System.out.printf("%s attacks %s for %d damage.%n", player.getName(player.getNumber()), monster.getName(Doing_monster), player.getDamage(player.getNumber()) + BonusAtk);
            if(monster.getHP(Doing_monster) <= 0){
                System.out.printf("%s wins!%n%n", player.getName(player.getNumber()));
                if (OldVersion==1){
                    GameOver = true;
                    break;
                }
                else {
                    monster.setmonsterexist(0, Doing_monster);
                    continue;
                }
            }
            value = player.getHP(player.getNumber()) - monster.getDamage(Doing_monster);
            System.out.printf("%s attacks %s for %d damage.%n", monster.getName(Doing_monster), player.getName(player.getNumber()), monster.getDamage(Doing_monster));
            player.setHP(value, player.getNumber());
            if(player.getHP(player.getNumber()) <= 0){
                System.out.printf("%s wins!%n", monster.getName(Doing_monster));
                GameOver = true;
                break;
            }
            else {
                System.out.println();
            }
        }

    }
    /**
     To get the bonus of item and delete it from the map.
     @param doing_item to determine which item to be "eaten".
     */
    public void GetItem(int doing_item){
        switch (item.getTypeOfItem(doing_item)){
            case '+':
                player.setHP(player.getMaxHP(player.getNumber()), player.getNumber());
                item.setitemexist(0,doing_item);
                System.out.printf("Healed!%n");
                break;
            case '^':
                BonusAtk ++;
                System.out.printf("Attack up!%n");
                item.setitemexist(0,doing_item);
                break;
            case '@':
                player.setPlayerLV(player.getPlayerLV(player.getNumber()) + 1, player.getNumber());
                player.playercal(player.getNumber());
                item.setitemexist(0,doing_item);
                GameOver = true;
                System.out.printf("World complete! (You leveled up!)%n");
                break;
        }
    }
/*    public void MonsterMove(){
        for (int i=0; i<monstercount & OldVersion==0 ; i++){
            if(Math.abs(player.getxPos(player.getNumber()) - monster.getxPos(i)) <= 2 && Math.abs(player.getyPos(player.getNumber()) - monster.getyPos(i)) <= 2 && monster.getmonsterexist(i)==1){
                if(player.getxPos(player.getNumber()) == monster.getxPos(i)){
                    if (player.getyPos(player.getNumber())>monster.getyPos(i)){
                        goSouth(monster,i);
                    }
                    else {
                        goNorth(monster,i);
                    }
                }
                else if (player.getxPos(player.getNumber())>monster.getxPos(i)){
                    goEast(monster,i);
                }
                else {
                    goWest(monster,i);
                }
            }
        }
    }
*/
    /**
       To let all monster existed move towards player if nearby.
     */
    public void MonsterMove(){
        for (int i=0; i<monstercount & OldVersion==0 ; i++){
            if(Math.abs(player.getxPos(player.getNumber()) - monster.getxPos(i)) <= 2 && Math.abs(player.getyPos(player.getNumber()) - monster.getyPos(i)) <= 2 && monster.getmonsterexist(i)==1) {
                if (player.getxPos(player.getNumber())>monster.getxPos(i) && Landform[monster.getxPos(i)+1][monster.getyPos(i)] =='.'){
                    goEast(monster,i);
                }
                else if (player.getxPos(player.getNumber())<monster.getxPos(i) && Landform[monster.getxPos(i)-1][monster.getyPos(i)] =='.'){
                    goWest(monster,i);
                }
                else if (player.getyPos(player.getNumber())<monster.getyPos(i) && Landform[monster.getxPos(i)][monster.getyPos(i)-1] =='.'){
                    goNorth(monster,i);
                }
                else if (player.getyPos(player.getNumber())>monster.getyPos(i) && Landform[monster.getxPos(i)][monster.getyPos(i)+1] =='.'){
                    goSouth(monster,i);
                }
            }
        }

    }
    /**
     To load the file to set a new world to play.
     @param GameworldName The name of world file.
     @throws IOException if cannot find the target file of world.
     */
    public void SetWorld( String GameworldName){
        String WorldName =GameworldName + ".dat";
        String filePath = "C:\\Users\\chekakaa\\IdeaProjects\\assignment2\\src\\assignment2\\";
        String[] Info =new String[GameEngine.WorldEdge];
        ObjectInputStream file = null;
        int Line =0;
        try(BufferedReader fil = new BufferedReader(new InputStreamReader(new FileInputStream(filePath + WorldName)))) {
            int i=0;
            while ((Info[i]=fil.readLine())!=null){
                i++;
                Line++;
            }
        }catch (IOException e) {
            gameLevelNotFoundException.SetWorld_IOException();
            WorldFail = 1;
        }
        if (WorldFail ==0){
        String Wide[] = Info[0].split(" ");
        WIDTH_OF_WORLD = Integer.parseInt(Wide[0]);
        HEIGHT_OF_WORLD = Integer.parseInt(Wide[1]);
        for(int j=0; j<HEIGHT_OF_WORLD; j++){
            char[] Land = new char[WIDTH_OF_WORLD+1];
            Land = Info[j+1].toCharArray();
            for (int k=0; k<WIDTH_OF_WORLD; k++){
                Landform[k][j] = Land[k];
            }
        }
        String Player[] = Info[HEIGHT_OF_WORLD + 1].split(" ");
        player.setNumber(0);
        player.setxPos(Integer.parseInt(Player[1]), player.getNumber());
        player.setyPos(Integer.parseInt(Player[2]), player.getNumber());
        int IsMonster = 1;
        monstercount = 1;
        int ItemLine=0;
        while (IsMonster == 1){
            String Monster[] = Info[HEIGHT_OF_WORLD + 1 +monstercount].split(" ");
            monster.setxPos(Integer.parseInt(Monster[1]),monstercount-1);
            monster.setyPos(Integer.parseInt(Monster[2]),monstercount-1);
            monster.setName(Monster[3],monstercount-1);
            monster.setMaxHP(Integer.parseInt(Monster[4]),monstercount-1);
            monster.setHP(Integer.parseInt(Monster[4]),monstercount-1);
            monster.setDamage(Integer.parseInt(Monster[5]),monstercount-1);
            monster.setmonsterexist(1,monstercount-1);
            String tmp =monster.getName(monstercount-1).toLowerCase(Locale.ROOT);
            monster.setFirstLetterOfName(tmp.charAt(0),monstercount-1);
            String Monster1[] = Info[HEIGHT_OF_WORLD + 2 +monstercount].split(" ");
            if (Monster1[0].equals("monster") ){
                monstercount++;
            }
            else {
                IsMonster = 0;
                ItemLine = HEIGHT_OF_WORLD + 2 +monstercount;
            }
        }
        itemcount = 0;
        for(int i=ItemLine; i<Line;i++){
            String Item[] = Info[i].split(" ");
            item.setxPos(Integer.parseInt(Item[1]),itemcount);
            item.setyPos(Integer.parseInt(Item[2]),itemcount);
            char[] ItemType;
            ItemType = Item[3].toCharArray();
            item.setTypeOfItem(ItemType[0],itemcount);
            item.setitemexist(1,itemcount);
            itemcount++;
        }
        }
    }
    /**
     To initiate the old version game and set the game world map.
     */
    public void inidata(){
        HEIGHT_OF_WORLD = 4;
        WIDTH_OF_WORLD = 6;
        monstercount = 1;
        player.setNumber(0);
        monster.setNumber(0);
        OldVersion = 1;
        itemcount = 0;
        player.setxPos(1,0);
        player.setyPos(1,0);
        monster.setxPos(4,0);
        monster.setyPos(2,0);
        monster.setmonsterexist(1,0);
        for (int i=0; i<WIDTH_OF_WORLD; i++){
            for (int j=0; j<HEIGHT_OF_WORLD; j++){
                Landform[i][j] = '.';
            }
        }
    }
    public int getOldVersion() {
        return OldVersion;
    }

    public void setOldVersion(int OldVersion) {
        this.OldVersion = OldVersion;
    }
}
