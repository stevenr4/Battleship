
package battleship;

/**
 *
 * @author spex
 * 
 * The map class for the battle ship game.
 *
 */
public class Map{

    private boolean hit[][]; //If the spot has been hit or not
    
    private Ship[] ships; //The ships
    
     Map(){
        hit = new boolean[10][10];
        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                hit[x][y] = false;
            }
        }

        ships = new Ship[5];
        for(int i = 0; i < 5; i++){
            ships[i] = new Ship();
        }
        for(int i = 0; i < 5; i++){
            ships[i].setX(-6);
            ships[i].setY(i * 2);
        }
        ships[0].setLength(5);
        ships[0].setName("Aircraft Carrier");
        ships[1].setLength(4);
        ships[1].setName("Battleship");
        ships[2].setLength(3);
        ships[2].setName("Destroyer");
        ships[3].setLength(3);
        ships[3].setName("Submarine");
        ships[4].setLength(2);
        ships[4].setName("Patrol Boat");
    }

    public Ship getShip(int loc){
        if((loc >= 0) && (loc < 5)){
            return ships[loc];
        }
        return null;
    }

    public int getShipId(int x, int y){
        for(int i = 0; i < 5; i++){
            if (ships[i].occupiesSpace(x, y)){
                return i;
            }
        }
        return 5;
    }

    public boolean isShipOutOfArea(int loc){

        if((loc >= 0) && (loc < 5)){
            
            //Check Top
            if(ships[loc].getX() < 0){
                return true;
            }
            
            //Check Left
            if(ships[loc].getY() < 0){
                return true;
            }
            
            //Check Bottom
            if(ships[loc].getVertical()){
                if(ships[loc].getY() + ships[loc].getLength() - 1 > 9){
                    return true;
                }
            }else {
                if(ships[loc].getY() > 9){
                    return true;
                }
            }
            
            //Check Right
            if(!ships[loc].getVertical()){
                if(ships[loc].getX() + ships[loc].getLength() - 1 > 9){
                    return true;
                }
            }else {
                if(ships[loc].getX() > 9){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isHitHere(int x, int y){
        if((x >= 0) && (x < 10) &&
                (y >= 0) && (y < 10)){
            return hit[x][y];
        }
        return false;
    }
    
    public boolean isShipColliding(int loc){
        if((loc >= 0) && (loc < 5)){
            for(int i = 0; i < ships[loc].getLength(); i++){
                for(int a = 0; a < 5; a++){
                    if(a != loc){
                        if(ships[loc].getVertical()){
                            if(ships[a].occupiesSpace(ships[loc].getX(), ships[loc].getY() + i)){
                                return true;
                            }
                        }else{
                            if(ships[a].occupiesSpace(ships[loc].getX() + i, ships[loc].getY())){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void setHit(int x, int y, boolean value)
    {
        if((x >= 0) && (x < 10) && (y >= 0) && (y < 10)){
            hit[x][y] = value;
        }
    }

    public boolean isShipHere(int x, int y){
        for(int i = 0; i < 5; i++){
            if(ships[i].occupiesSpace(x, y)){
                return true;
            }
        }
        return false;
    }

    public void placeShips(){
        Main.v.curserX = 0;
        Main.v.curserY = 0;
        Main.v.selected = false;

        for(int i = 0; i < 5; i++){
            ships[i].setX(-6);
            ships[i].setY(i * 2);
        }

        boolean done = false;
        do{

            if(Main.v.keyPressed == true){

                Main.v.keyPressed = false;

                System.out.println(Main.v.curserY);

                if((Main.v.key == Main.v.UP) || (Main.v.key == Main.v.W)){
                    if(Main.v.curserY > 0){
                        Main.v.curserY--;
                    }
                }
                if((Main.v.key == Main.v.DOWN) || (Main.v.key == Main.v.S)){
                    if(Main.v.curserY < 9){
                        Main.v.curserY++;
                    }
                }
                if((Main.v.key == Main.v.LEFT) || (Main.v.key == Main.v.A)){
                    if(Main.v.curserX > -5){
                        Main.v.curserX--;
                    }
                }
                if((Main.v.key == Main.v.RIGHT) || (Main.v.key == Main.v.D)){
                    if(Main.v.curserX < 9){
                        Main.v.curserX++;
                    }
                }
            }

        }while(!done);

    }
}
