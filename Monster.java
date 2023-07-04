package assignment2;

/**
 * Monster is derived by Creature class and permits to establish the monster.
 * @author Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 * @create 2021-10-04 10:05
 */

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Scanner;

/**
 * TODO: Method Monster() permits players to establish their own monster diy.
 * @author TODO: Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 *
 */


public class Monster extends Creature {

//    private String monsterName;
 //   private int monsterMaxHP;
 //   private int monsterHP;
  //  private int monsterDamage;
//    private char[] firstLetterOfName = new char[5];
    private int[] monsterexist = new int[GameEngine.EntityQuantity];
    private int nameExist = 0;

    public void monsterEstablish( Scanner keyboard){
        do {
            System.out.printf("Monster name: ");
            String NameInput = keyboard.nextLine();
            if (!NameInput.equals("")){
                setName(NameInput,EntityNumber);
                nameExist = 1;
            }
        }while (nameExist == 0);
        System.out.printf("Monster health: ");
        setMaxHP(keyboard.nextInt(),EntityNumber);
        System.out.printf("Monster damage: ");
        setDamage(keyboard.nextInt(),EntityNumber);
        System.out.printf("Monster '%s' created.%n",getName(EntityNumber));

        String tmp =getName(EntityNumber).toLowerCase(Locale.ROOT);
        setFirstLetterOfName(tmp.charAt(0),EntityNumber);
        setHP(getMaxHP(EntityNumber),EntityNumber);
        keyboard.nextLine();
    }
    public int getmonsterexist(int EntityNumber) {
        return monsterexist[EntityNumber];
    }
    public void setmonsterexist(int monsterexist,int EntityNumber) {
        this.monsterexist[EntityNumber] = monsterexist;
    }

}
