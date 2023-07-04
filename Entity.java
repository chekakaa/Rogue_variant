package assignment2;

/**
 * Entity includes the basic class, object and method of player, monster and item.
 * @author Rui Liu, rlli2@student.unimelb.edu.au, student number: 1111181.
 * @create 2021-10-04 10:14
 */
public class Entity {
    private int[] xPos = new int[GameEngine.WorldEdge];
    private int[] yPos = new int[GameEngine.WorldEdge];
//    char[] TypeOfItem = new char[EntityNumber+1];
    public int EntityNumber;
    /**
       To get the number of entity. There are several monsters and items, and maybe will be several players' files to be held.
       @return EntityNumber The number of each entity.
     */
    public int getNumber() {
        return EntityNumber;
    }
    /**
       To set the value of the number of entity.
       @param EntityNumber The number of each entity.
     */
    public void setNumber(int EntityNumber) {
        this.EntityNumber = EntityNumber;
    }

    /**
       To get the x-coordinate of certain entity.
       @param EntityNumber The number of this kind of entity.
       @return xPos The x-coordinate of entity.
     */
    public int getxPos(int EntityNumber) {
        return xPos[EntityNumber];
    }

    public void setxPos(int xPos,int EntityNumber) {
        this.xPos[EntityNumber] = xPos;
    }

    public int getyPos(int EntityNumber) {
        return yPos[EntityNumber];
    }

    public void setyPos(int yPos, int EntityNumber) {
        this.yPos[EntityNumber] = yPos;
    }

}
