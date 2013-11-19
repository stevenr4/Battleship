
package battleship;

/**
 *
 * @author spex
 */

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class Printer {

    //use this as a double buffer
    BufferedImage backbuffer;
    //Create the graphics printer
    private Graphics2D g2d;
    //Declare variables
    int Width;
    int Height;

    //Assign variables
    Printer(int W, int H){
        
        Width = W;
        Height = H;

        //create the back buffer for smooth graphics
        backbuffer = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
        g2d = backbuffer.createGraphics();
    }

    public void refresh(Graphics g){
        g.drawImage(backbuffer, 0, 0, null);
    }

    public void printGame(){

        if(g2d != null){
            Map tmpMap = Main.v.maps[0];
            cls();
            if(Main.v.showShips[0]){
                printField(Color.BLUE, 50, 70);
                printShips(tmpMap, 50, 70);
            }else{
                printField(Color.CYAN, 50, 70);
            }

            printHits(tmpMap, 50, 70);

            tmpMap = Main.v.maps[1];
            if(Main.v.showShips[1]){
                printField(Color.BLUE, 500, 70);
                printShips(tmpMap, 500, 70);
            }else{
                printField(Color.CYAN, 500, 70);
            }
            
            printHits(tmpMap,500,70);
            
            
            if((Main.v.player1) && (Main.v.player1Turn)){
                printCurser(500, 70);
            }else if((!Main.v.player1) && (!Main.v.player1Turn)){
                printCurser(50, 70);
            }
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.PLAIN, 34));
            g2d.drawString("Player 1", 100, 50);
            g2d.drawString("Player 2", 550, 50);

            printPlayerTurn();
        }
    }

    public void printSetShips(){
        if(g2d != null){
            Map tmpMap = null;
            if(Main.v.player1){
                tmpMap = Main.v.maps[0];
            } else {
                tmpMap = Main.v.maps[1];
            }
            cls();

            printField(Color.DARK_GRAY,110,70);

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, 500, 110);
            g2d.fillRect(0, 0, 110, 800);
            printField(Color.BLUE,350,70);
            printShips(tmpMap, 350,70);
            printCurser(350,70);
            if(tmpMap.isShipHere(Main.v.curserX, Main.v.curserY)){
                printShipName(tmpMap,tmpMap.getShipId(Main.v.curserX, Main.v.curserY),800,200);
            }
            printPlayerTurn();

        }
    }

    public void printMenu(){
        if(Main.v.menuItems.length > 0){
            cls();
            g2d.setFont(new Font("Arial", Font.PLAIN, 54));
            g2d.setColor(Color.WHITE);
            g2d.drawString(Main.v.menuItems[0], (Width / 2) - 200, 180);
            g2d.setFont(new Font("Arial", Font.PLAIN, 34));

            for(int i = 1; i < Main.v.menuItems.length; i++){
                if(Main.v.selection == i - 1){
                g2d.drawString("-", (Width / 2) - 140, 200 + (i * 50));
                }
                g2d.drawString(Main.v.menuItems[i], (Width / 2) - 120, 200 + (i * 50));
            }
        }
    }

    private void cls(){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Width, Height);
    }
    
    private void printPlayerTurn(){
        String s = "";
        if(Main.v.player1Turn){
            s = "Player 1";
        }else{
            s = "Player 2";
        }

        if((Main.v.totalHits[0] >= 17) ||
                (Main.v.totalHits[1] >= 17)){
            s += " wins!";
        }else{
            s += "'s turn";
        }
        g2d.setFont(new Font("Arial", Font.PLAIN, 54));
        g2d.setColor(Color.WHITE);
        g2d.drawString(s, (Width / 2) - 300, Height - 10);
    }

    private void printField(Color c, int xLoc, int yLoc){
        for(int x = -1; x < 10; x++){
            for(int y = -1; y < 10; y++){

                int xPrint = getXPrint(x) + xLoc;
                int yPrint = getYPrint(y) + yLoc;

                //Draw the background..
                if((x >= 0) || (y >= 0)){
                    g2d.setColor(c);
                    g2d.fillRect(xPrint, yPrint, 38, 38);
                }

                //DRAW THE NUMBERS
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.PLAIN, 18));
                if(x < 0){
                    g2d.drawString((y + 1) + "", xPrint + 10, yPrint + 20);
                }
                g2d.setColor(Color.BLACK);
                if(y < 0){
                    g2d.drawString((char)(x + 65) + "", xPrint + 10, yPrint + 20);
                }
            }
        }
    }

    private void printShips(Map tmpMap, int xLoc, int yLoc){
        for(int x = -8; x < 14; x++){
            for(int y = -8; y < 14; y++){

                int xPrint = getXPrint(x) + xLoc;
                int yPrint = getYPrint(y) + yLoc;

                //If there is a ship in the right map....
                if(tmpMap.isShipHere(x, y)){
                    if((tmpMap.getShipId(x, y) == Main.v.selection) &&
                    (Main.v.selected)){
                        if((tmpMap.isShipColliding(Main.v.selection)) ||
                        (tmpMap.isShipOutOfArea(Main.v.selection))){
                            g2d.setColor(Color.RED);
                        }else{
                            g2d.setColor(Color.YELLOW);
                        }
                    }else{
                        g2d.setColor(Color.GREEN);
                    }
                    g2d.fillRect(xPrint + 4, yPrint + 4, 30, 30);
                }
            }
        }
    }

    private void printHits(Map tmpMap, int xLoc, int yLoc){
        for(int x = -1; x < 10; x++){
            for(int y = -1; y < 10; y++){

                int xPrint = getXPrint(x) + xLoc;
                int yPrint = getYPrint(y) + yLoc;

                //If there is a hit in the right map....
                if(tmpMap.isHitHere(x, y)){
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(xPrint, yPrint, 38, 38);
                    if(tmpMap.isShipHere(x, y)){
                        g2d.setColor(Color.RED);
                        g2d.fillRect(xPrint + 4, yPrint + 4, 30, 30);
                    }
                }
            }
        }
    }

    private void printCurser(int xLoc, int yLoc){

        int x = getXPrint(Main.v.curserX) + xLoc;
        int y = getYPrint(Main.v.curserY) + yLoc;
        g2d.setColor(Color.YELLOW);
        g2d.drawRect(x, y, 38, 38);
        g2d.drawRect(x + 1, y + 1, 36, 36);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x + 3, y + 3, 32, 32);
        g2d.drawOval(x + 4, y + 4, 30, 30);
        g2d.drawOval(x + 5, y + 5, 28, 28);
        g2d.drawLine(x, y + 18, x + 40, y + 18);
        g2d.drawLine(x, y + 19, x + 40, y + 19);
        g2d.drawLine(x, y + 20, x + 40, y + 20);
        g2d.drawLine(x + 18, y, x + 18, y + 40);
        g2d.drawLine(x + 19, y, x + 19, y + 40);
        g2d.drawLine(x + 20, y, x + 20, y + 40);
    }

    private void printShipName(Map tmpMap, int ID, int xLoc, int yLoc){
        g2d.setColor(Color.WHITE);
        g2d.drawString(tmpMap.getShip(ID).getName(), xLoc, yLoc);
    }
    
    private int getXPrint(int x){
        return (x * 40);
    }
    private int getYPrint(int y){
        return (y * 40) + 40;
    }

    void printSquare() {
        g2d.setColor(Color.RED);
        for(int a = 0; a < 8; a++){
            boolean bw = false;
            for(int i = 0; i < 256; i+= Math.pow(2, a) ){
                if(bw){
                    g2d.setColor(Color.WHITE);
                    bw = false;
                }else{
                    bw = true;
                    g2d.setColor(Color.BLACK);
                }
                g2d.fillRect(a*100,i + 30,100,1000);
            }
        }
    }
}
