
package battleship;

/**
 *
 * @author spex
 *
 * A class to hold and manage the ships on a map.
 *
 */
public class Ship {

    private int length; // The length of the ship.
    private int x; // The x location of the top/left section of the ship.
    private int y; // The y location of the top/left section of the ship.
    private boolean vertical; // If the ship is vertical or not.
    private String name;

    Ship(){
        length = 1;
        x = -6;
        y = 0;
        vertical = false;
        name = "I AM ERROR";
    }

    public void setLength(int newLength){
        length = newLength;
    }
    public void setX(int newX){
        x = newX;
    }
    public void setY(int newY){
        y = newY;
    }
    public void setVertical(boolean newVertical){
        vertical = newVertical;
    }
    public void setName(String newName){
        name = newName;
    }

    public int getLength(){
        return length;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean getVertical(){
        return vertical;
    }
    public String getName(){
        return name;
    }

    public void incY(int increment){
        y += increment;
    }
    public void incX(int increment){
        x += increment;
    }
    public void changeVertical(){
        vertical = !vertical;
    }

    public boolean occupiesSpace(int xPos, int yPos){
        for(int i = 0; i < length; i++){
            if(vertical){
                if((xPos == x) && (yPos == y + i)){
                    return true;
                }
            }else{
                if((xPos == x + i) && (yPos == y)){
                    return true;
                }
            }
        }
        return false;
    }
}
