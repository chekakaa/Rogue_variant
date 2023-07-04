package assignment2;

/**
 * Creature is a new class that derived by Entity ,which contains the relationship of player and monsters. They can both move, fight and have their own name.
 * @author Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 * @create 2021-10-04 10:51
 */
public class Creature extends Entity {
    private int[] MaxHP = new int[GameEngine.EntityQuantity];
    private int[] HP = new int[GameEngine.EntityQuantity];
    private int[] Damage = new int[GameEngine.EntityQuantity];
    private String[] Name = new String[GameEngine.EntityQuantity];
    private char[] firstLetterOfName = new char[GameEngine.EntityQuantity];

    /**
       To get the value of the MaxHp of certain creature.
       @param EntityNumber The number of each entity.
     */
    public int getMaxHP(int EntityNumber) {
        return MaxHP[EntityNumber];
    }

    public void setMaxHP(int MaxHP,int EntityNumber) {
        this.MaxHP[EntityNumber] = MaxHP;
    }

    public int getHP(int EntityNumber) {
        return HP[EntityNumber];
    }

    public void setHP(int HP,int EntityNumber) {
        this.HP[EntityNumber] = HP;
    }

    public int getDamage(int EntityNumber) {
        return Damage[EntityNumber];
    }

    public void setDamage(int Damage, int EntityNumber) {
        this.Damage[EntityNumber] = Damage;
    }

    public String getName(int EntityNumber) {
        return Name[EntityNumber];
    }

    public void setName(String Name,int EntityNumber) {
        this.Name[EntityNumber] = Name;
    }
    public char getFirstLetterOfName(int EntityNumber) {
        return firstLetterOfName[EntityNumber];
    }
    public void setFirstLetterOfName(char firstLetterOfName,int EntityNumber) {
        this.firstLetterOfName[EntityNumber] = firstLetterOfName;
    }
}