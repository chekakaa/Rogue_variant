package assignment2;

/**
 * Player, derived by Creature class, has the unique function: calculating the ability and having LV to get strong.
 * @author Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 * @create 2021-10-04 10:05
 */
import java.util.Locale;
import java.util.Scanner;

/**
 * TODO: Method Player() lets players to establish and consult the player message.
 * @author TODO: Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 *
 */


public class Player extends Creature {
    private int[] playerLV = new int[GameEngine.EntityQuantity];
    private int EntityNumber=0;
    private int basicHp = 17;
    private int bonusHp = 3;
//    private int playerMaxHP;
//    private int playerHP;
 //   private int playerDamage;
//    private String playerName;
//    private char firstLetterOfName;

    public void playerEstablish(Scanner keyboard,int EntityNumber){
        System.out.printf("What is your character's name?%n");
        setName(keyboard.nextLine(),EntityNumber);
        System.out.printf("Player '%s' created.%n", getName(EntityNumber));
        playerLV[EntityNumber] = 1;
        playercal(EntityNumber);
    }

    public void playerShow(int EntityNumber){
        playercal(EntityNumber);
        System.out.printf("%s (Lv. %d)%n", getName(EntityNumber), playerLV[EntityNumber]);
        System.out.printf("Damage: %d%n", getDamage(EntityNumber));
        System.out.printf("Health: %d/%d%n", getHP(EntityNumber), getMaxHP(EntityNumber));
    }
    /**
       To calculate the ability of player basic on its level.
       @param EntityNumber The number of player, in this stage, there is only one player.
     */
    public void playercal(int EntityNumber){
        setMaxHP( basicHp + bonusHp * playerLV[EntityNumber],EntityNumber);
        setHP(getMaxHP(EntityNumber),EntityNumber);
        setDamage(1+ playerLV[EntityNumber],EntityNumber);
        String tmp = getName(EntityNumber).toUpperCase(Locale.ROOT);
        setFirstLetterOfName(tmp.charAt(0),EntityNumber);
    }

    public int getPlayerLV(int EntityNumber) {
        return playerLV[EntityNumber];
    }
    public void setPlayerLV(int playerLV,int EntityNumber) {
        this.playerLV[EntityNumber] = playerLV;
    }

    /* End of setter and getter */
}
