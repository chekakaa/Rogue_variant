package assignment2;

/**
 * Item is a new class that derived by Entity and can strengthen the player.
 * @author Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 * @create 2021-10-04 10:13
 */
public class Item extends Entity{
//    private char TypeOfItem;
    char[] TypeOfItem = new char[GameEngine.EntityQuantity];
    private int[] itemexist = new int[GameEngine.EntityQuantity];
    public char getTypeOfItem(int EntityNumber) {
        return TypeOfItem[EntityNumber];
    }
    /**
     To set the type of certain item.
     @param EntityNumber The number of this kind of entity.
     @param TypeOfItem There are three type of item as '@', '^' and '+'.
     */
    public void setTypeOfItem(char TypeOfItem,int EntityNumber) {
        this.TypeOfItem[EntityNumber] = TypeOfItem;
    }
    /**
     To judge whether there is any item.
     @param EntityNumber The number of this kind of entity.
     @return itemexist equals 1 if exists. When "eaten" by player, it disappears and turns to 0.
     */
    public int getitemexist(int EntityNumber) {
        return itemexist[EntityNumber];
    }
    public void setitemexist(int itemexist,int EntityNumber) {
        this.itemexist[EntityNumber] = itemexist;
    }
}
