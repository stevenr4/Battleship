

package battleship;

/**
 *
 * @author spex
 *
 * The class to hold all the variables in the game, this class is static
 * from the main class and can be called to by simply doing:
 * "Main.v.#variable_name#"
 *
 */
public class Variables {

    public boolean[] showShips;

    public String[] menuItems; // The items on the menu when the menu prints.

    public int key; //The ascii code of the key that has been pressed.

    public boolean keyPressed; //If the key has been pressed or not.

    public int selection; //Where the curser is on the menu or what ship is currently being selected.

    public boolean selected; //If the menu item has been selected or if a ship is currently selected.

    public int curserX,curserY; //The (x,y) coordinates of the curser on the battleship field.

    public boolean player1; //If the user of the instance is player1[true] or player2[false].

    public boolean player1Turn; // If it is the turn of player1[true] or player2[false].

    public int currentMap; // The currently printed and modified map.

    public Map[] maps; // The maps for all of the players.

    public int[] totalHits; // The total hits the player has caused on the other

    public final int W = 87;
    public final int UP = 38;
    public final int A = 65;
    public final int LEFT = 37;
    public final int S = 83;
    public final int DOWN = 40;
    public final int D = 68;
    public final int RIGHT = 39;
    public final int ENTER = 10;
    public final int ESC = 27;
    public final int SPACE = 32;
    Variables(){
        resetAll();
    }

    public void resetAll(){
        showShips = new boolean[2];
        showShips[0] = false;
        showShips[1] = false;
        menuItems = new String[0];
        key = 64;
        keyPressed = false;
        selection = 0;
        selected = false;
        curserX = 0;
        curserY = 0;
        player1 = true;
        player1Turn = true;
        maps = new Map[2];
        maps[0] = new Map();
        maps[1] = new Map();
        totalHits = new int[2];
        totalHits[0] = 0;
        totalHits[1] = 0;
    }
}
