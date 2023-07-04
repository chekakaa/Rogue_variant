package assignment1;

/**
 * @author Rui Liu
 * @create 2021-10-04 10:05
 */
import java.util.Locale;
import java.util.Scanner;

/**
 * TODO: Method Monster() permits players to establish their own monster diy.
 * @author TODO: Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 *
 */


public class Monster {
    private String monsterName;
    private int monsterMaxHP;
    private int monsterHP;
    private int monsterDamage;
    private char firstLetterOfName;
    private int xPos;
    private int yPos;

    public void monsterEstablish(Scanner keyboard){
        System.out.printf("Monster name: ");
        monsterName = keyboard.nextLine();
        System.out.printf("Monster health: ");
        monsterMaxHP = keyboard.nextInt();
        System.out.printf("Monster damage: ");
        monsterDamage = keyboard.nextInt();
        System.out.printf("Monster '%s' created.%n",monsterName);

        String tmp = monsterName.toLowerCase(Locale.ROOT);
        firstLetterOfName = tmp.charAt(0);
        monsterHP = monsterMaxHP;
        keyboard.nextLine();
    }

    /* below is setter and getter of variables */

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public int getMonsterMaxHP() {
        return monsterMaxHP;
    }

    public void setMonsterMaxHP(int monsterMaxHP) {
        this.monsterMaxHP = monsterMaxHP;
    }

    public int getMonsterHP() {
        return monsterHP;
    }

    public void setMonsterHP(int monsterHP) {
        this.monsterHP = monsterHP;
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

    public int getMonsterDamage() {
        return monsterDamage;
    }

    public void setMonsterDamage(int monsterDamage) {
        this.monsterDamage = monsterDamage;
    }
    /* End of setter and getter */
}
