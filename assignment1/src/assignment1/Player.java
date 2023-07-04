package assignment1;

/**
 * @author Rui Liu
 * @create 2021-10-04 10:05
 */
import java.util.Locale;
import java.util.Scanner;

/**
 * TODO: Method Player() lets players to establish and consult the player message.
 * @author TODO: Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 *
 */


public class Player {
    private int playerLV;
    private int playerMaxHP;
    private int playerHP;
    private int playerDamage;
    private String playerName;
    private char firstLetterOfName;
    private int xPos;
    private int yPos;

    public void playerEstablish(Scanner keyboard){

        System.out.printf("What is your character's name?%n");
        playerName = keyboard.nextLine();
        System.out.printf("Player '%s' created.%n", playerName);
        playerLV = 1;
        playerMaxHP = 17 + 3 * playerLV;
        playerHP = playerMaxHP;
        playerDamage = 1+ playerLV;
        String tmp = playerName.toUpperCase(Locale.ROOT);
        firstLetterOfName = tmp.charAt(0);

    }

    public void playerShow(){

        System.out.printf("%s (Lv. %d)%n", playerName, playerLV);
        System.out.printf("Damage: %d%n", playerDamage);
        System.out.printf("Health: %d/%d%n", playerHP, playerMaxHP);
    }


    /* below is setter and getter of variables */

    public int getPlayerLV() {
        return playerLV;
    }

    public void setPlayerLV(int playerLV) {
        this.playerLV = playerLV;
    }

    public int getPlayerMaxHP() {
        return playerMaxHP;
    }

    public void setPlayerMaxHP(int playerMaxHP) {
        this.playerMaxHP = playerMaxHP;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public int getPlayerDamage() {
        return playerDamage;
    }

    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public char getFirstLetterOfName() {
        return firstLetterOfName;
    }

    /* End of setter and getter */
}
