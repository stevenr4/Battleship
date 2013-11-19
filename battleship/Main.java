
package battleship;

/**
 *
 * @author spex
 *
 * The main function for the battleship game.
 *
 */

import javax.swing.JFrame;//Gives a frame that we can draw to
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main extends JFrame  implements KeyListener{



    static public int i = 0;


    //All the variables in the game
    static public Variables v = new Variables();

    static private int Width = 900;
    static private int Height = 560;

    Random r = new Random();
    OnlineManager om = new OnlineManager();

    //The printer for the graphics in this game.
    static public Printer p = new Printer(Width, Height);

    public static void main(String[] args) {
        i++;
        System.out.println(i);
        System.out.println("Starting new Instance");
        Main m = new Main();
        m.init();
        m.run();
    }

    public void init(){



        System.out.println("Setting screen size to " + Width + ',' + Height);
        setSize(Width,Height);
        System.out.println("Making screen visible....");
        setVisible(true);
        addKeyListener(this);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
    }

    public void run(){
        System.out.println("Running....");
        mainMenu();
        System.exit(0);
    }

    public void waitKey(){
        v.keyPressed = false;
        boolean reallyPressed = false;
//        long i;
//        long danmit = 0;
        do{
//            i = System.currentTimeMillis();
//            do{
//                danmit++;
//                danmit /= 2;
//                danmit *= 4;
//                danmit /= 2;
//
//            }while(System.currentTimeMillis() < i + 100);
            if(Main.v.keyPressed){
                reallyPressed = true;
                System.out.print(reallyPressed);
            }
        }while(reallyPressed == false);
    }
    
    public void mainMenu(){
        boolean done = false;
        do{
            v.resetAll();
            v.menuItems = new String[4];
            v.menuItems[0] = "Battleship";
            v.menuItems[1] = "SinglePlayer";
            v.menuItems[2] = "MultiPlayer";
            v.menuItems[3] = "Quit";
            do{
                p.printMenu();
                repaint();

                waitKey();

                if((Main.v.key == Main.v.UP) || (Main.v.key == Main.v.W)){
                    if(Main.v.selection > 0){
                        Main.v.selection--;
                    }
                }
                if((Main.v.key == Main.v.DOWN) || (Main.v.key == Main.v.S)){
                    if(Main.v.selection < 2){
                        Main.v.selection++;
                    }
                }
                if(Main.v.key == Main.v.ENTER){
                }
            }while(Main.v.key != Main.v.ENTER);
            switch(v.selection){
                case 0:
                    singlePlayer();
                    break;
                case 1:
                    multiPlayerMenu();
                    break;
                case 2:
                    done = true;
            }
        }while(!done);
    }

    public void multiPlayerMenu(){
        boolean done = false;
        do{
            v.resetAll();
            v.menuItems = new String[5];
            v.menuItems[0] = "Battleship";
            v.menuItems[1] = "One Computer";
            v.menuItems[2] = "Join Game";
            v.menuItems[3] = "Host Game";
            v.menuItems[4] = "Back";
            do{
                p.printMenu();
                repaint();

                waitKey();

                if((Main.v.key == Main.v.UP) || (Main.v.key == Main.v.W)){
                    if(Main.v.selection > 0){
                        Main.v.selection--;
                    }
                }
                if((Main.v.key == Main.v.DOWN) || (Main.v.key == Main.v.S)){
                    if(Main.v.selection < 3){
                        Main.v.selection++;
                    }
                }
                if(Main.v.key == Main.v.ENTER){
                }
            }while(Main.v.key != Main.v.ENTER);
            switch(v.selection){
                case 0://Local game
                    multiPlayer();
                    break;
                case 1://Join game
                    joinGame();
                    //debugMenu();
                    onlineGame(false);
                    break;
                case 2://Host game
                    hostGame();
                    //debugMenu();
                    onlineGame(true);
                    break;
                case 3:
                    done = true;
            }
        }while(!done);
    }

    public void debugMenu(){
        boolean done = false;
        String output = "output";
        do{
            v.resetAll();
            v.menuItems = new String[5];
            v.menuItems[0] = "DebugMenu";
            v.menuItems[1] = "sendPackage";
            v.menuItems[2] = "waitForPackage";
            v.menuItems[3] = "closeUp";
            v.menuItems[4] = output;
            do{
                p.printMenu();
                repaint();

                waitKey();

                if((Main.v.key == Main.v.UP) || (Main.v.key == Main.v.W)){
                    if(Main.v.selection > 0){
                        Main.v.selection--;
                    }
                }
                if((Main.v.key == Main.v.DOWN) || (Main.v.key == Main.v.S)){
                    if(Main.v.selection < 2){
                        Main.v.selection++;
                    }
                }
                if(Main.v.key == Main.v.ENTER){
                }
            }while(Main.v.key != Main.v.ENTER);
            switch(v.selection){
                case 0:
                    om.sendXY(3, 4);
                    break;
                case 1:
                    int[] a = om.getXY();
                    output = "Got x (" + a[0] + ") and y (" + a[1] + ")";
                    break;
                case 2:
                    done = true;
            }
        }while(!done);
    }
    
    public void update(Graphics g){
        paint(g);
    }

    public void paint(Graphics g){
        p.refresh(g);
    }

    //Key actions
    public void keyReleased(KeyEvent k){}
    public void keyTyped(KeyEvent k){}
    public void keyPressed(KeyEvent k){
        v.key = k.getKeyCode();
        System.out.println(v.key);
        v.keyPressed = true;
    }



    public void placeShips(Map tmpMap){
        Main.v.curserX = 0;
        Main.v.curserY = 0;
        Main.v.selected = false;
        if(Main.v.player1){
            Main.v.currentMap = 0;
        }else{
            Main.v.currentMap = 1;
        }

        boolean done = false;
        do{
            p.printSetShips();
            repaint();

            waitKey();

            Main.v.keyPressed = false;

            System.out.println(Main.v.curserY);

            if((Main.v.key == Main.v.UP) || (Main.v.key == Main.v.W)){
                if(Main.v.curserY > 0){
                    if(v.selected){
                        tmpMap.getShip(v.selection).incY(-1);
                        Main.v.curserY--;
                    }else{
                        Main.v.curserY--;
                    }
                }
            }
            if((Main.v.key == Main.v.DOWN) || (Main.v.key == Main.v.S)){
                if(Main.v.curserY < 9){
                    if(v.selected){
                        tmpMap.getShip(v.selection).incY(1);
                        Main.v.curserY++;
                    }else{
                        Main.v.curserY++;
                    }
                }
            }
            if((Main.v.key == Main.v.LEFT) || (Main.v.key == Main.v.A)){

                if(Main.v.curserX > -6){
                    if(v.selected){
                        tmpMap.getShip(v.selection).incX(-1);
                        Main.v.curserX--;
                    }else{
                        Main.v.curserX--;
                    }
                }
            }
            if((Main.v.key == Main.v.RIGHT) || (Main.v.key == Main.v.D)){

                if(Main.v.curserX < 9){
                    if(v.selected){
                        tmpMap.getShip(v.selection).incX(1);
                        Main.v.curserX++;
                    }else{
                        Main.v.curserX++;
                    }
                }
            }
            if(Main.v.key == Main.v.ENTER){
                if(v.selected){
                    if((tmpMap.isShipColliding(Main.v.selection)) ||
                    (tmpMap.isShipOutOfArea(v.selection))){

                    }else{
                        v.selected = false;
                    }
                }else{
                    if(tmpMap.isShipHere(v.curserX, v.curserY)){
                        v.selected = true;
                        v.selection = tmpMap.getShipId(v.curserX, v.curserY);
                    }
                }
            }
            if(Main.v.key == Main.v.SPACE){
                if(v.selected){
                    tmpMap.getShip(Main.v.selection).changeVertical();
                    for(int i = 0; i < 5; i++){
                        if(tmpMap.getShip(v.selection).occupiesSpace(v.curserX, v.curserY)){
                            break;
                        }
                        if(Main.v.maps[Main.v.currentMap].getShip(Main.v.selection).getVertical()){
                            tmpMap.getShip(v.selection).incX(1);
                            tmpMap.getShip(v.selection).incY(-1);
                        }else{
                            tmpMap.getShip(v.selection).incX(-1);
                            tmpMap.getShip(v.selection).incY(1);
                        }
                    }
                }
            }
            if(Main.v.key == Main.v.ESC){
                boolean out = false;
                for(int i = 0; i < 5; i++){
                    if(tmpMap.isShipOutOfArea(i)){
                        out = true;
                    }
                }
                if(out == false){
                    done = true;
                }
            }

        }while(done == false);
    }

    public void attackPlayer(){

        Map tmpMap = null;
        if(v.player1){
            tmpMap = v.maps[1];
        }else{
            tmpMap = v.maps[0];
        }

        boolean done = false;
        do{
            p.printGame();
            repaint();
            waitKey();

            Main.v.keyPressed = false;

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

                if(Main.v.curserX > 0){
                    Main.v.curserX--;
                }
            }
            if((Main.v.key == Main.v.RIGHT) || (Main.v.key == Main.v.D)){

                if(Main.v.curserX < 9){
                    Main.v.curserX++;
                }
            }
            if((Main.v.key == Main.v.ENTER) || (Main.v.key == Main.v.SPACE)){
                if(tmpMap.isHitHere(v.curserX, v.curserY)){

                }else{
                    tmpMap.setHit(v.curserX, v.curserY, true);
                    if(tmpMap.isShipHere(v.curserX, v.curserY)){
                        int player;
                        if(Main.v.player1){
                            player = 0;
                        }else{
                            player = 1;
                        }
                        v.totalHits[player]++;
                        if(v.totalHits[player] == 17){
                            break;
                        }
                    }
                    done = true;
                }
            }
        }while(!done);
    }

    public void aiAttack(int difficulty){
        switch(difficulty){
            case 0:
                int tmpx, tmpy;
                do{
                    tmpx = r.nextInt() % 10;
                    tmpy = r.nextInt() % 10;
                }while((Main.v.maps[v.currentMap].isHitHere(tmpx, tmpy)) ||
                        (tmpx < 0) || (tmpy < 0));
                Main.v.maps[v.currentMap].setHit(tmpx, tmpy, true);

                if(Main.v.maps[v.currentMap].isShipHere(tmpx, tmpy)){
                    v.totalHits[1]++;
                }
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }

    public void singlePlayer(){
        Main.v.resetAll();
        Main.v.showShips[0] = true;
        Main.v.selected = false;
        System.out.println("PLACING SHIPS....");
        placeShips(Main.v.maps[0]);
        System.out.println("Populating enemy map...");
        populateEnemyMap(2);
        v.curserX = 0;
        v.curserY = 0;
        
        do{
            p.printGame();
            repaint();
            System.out.println("Attacking player");
            attackPlayer();
            System.out.println("Attacking back..");
            aiAttack(0);
        }while((v.totalHits[0] < 17) && (v.totalHits[1] < 17));
        p.printGame();
        repaint();
        waitKey();
    }

    public void multiPlayer(){
        Main.v.resetAll();
        System.out.println("Placing player 1's ship");
        placeShips(Main.v.maps[0]);
        System.out.println("Switching player and placing ships");

        
        Main.v.menuItems = new String[1];
        Main.v.menuItems[0] = "Press space to switch...";
        Main.v.keyPressed = false;
        boolean spaceHit = false;
        do{
            p.printMenu();
            repaint();
            waitKey();
            if(Main.v.key == Main.v.SPACE){
                spaceHit = true;
            }else{
                Main.v.keyPressed = false;
            }
        }while(!spaceHit);

        Main.v.player1 = !Main.v.player1;
        Main.v.player1Turn = !Main.v.player1Turn;

        placeShips(Main.v.maps[1]);

        do{
            Main.v.player1 = !Main.v.player1;
            Main.v.player1Turn = !Main.v.player1Turn;
            attackPlayer();


        }while((v.totalHits[0] < 17) && (v.totalHits[1] < 17));
        p.printGame();
        repaint();
        waitKey();
    }

    public void populateEnemyMap(int difficulty){
        Map tmpMap = Main.v.maps[(v.currentMap + 1) % 2];
        switch (difficulty){
            case 0:
                break;
            case 1:
                for (int i = 0; i < 5; i++) {
                    do {
                        tmpMap.getShip(i).setX(r.nextInt() % 10);
                        tmpMap.getShip(i).setY(r.nextInt() % 10);
                        tmpMap.getShip(i).setVertical(r.nextBoolean());
                    } while ((tmpMap.isShipColliding(i))
                            || (tmpMap.isShipOutOfArea(i)));
                }
                break;
            case 2:
                boolean inMiddle;
                for(int i = 0; i < 5; i++) {
                    do {
                        inMiddle = false;
                        tmpMap.getShip(i).setX(r.nextInt() % 10);
                        tmpMap.getShip(i).setY(r.nextInt() % 10);
                        tmpMap.getShip(i).setVertical(r.nextBoolean());

                        for(int a = 0; a < tmpMap.getShip(i).getLength(); a++){
                            if(tmpMap.getShip(i).getVertical()){
                                if(((tmpMap.getShip(i).getX() - 5) * (tmpMap.getShip(i).getX() - 5)) +
                                        ((tmpMap.getShip(i).getY() + a - 5) * (tmpMap.getShip(i).getY() + a - 5)) <
                                        12){
                                    inMiddle = true;
                                }
                            }else{
                                if(((tmpMap.getShip(i).getX() + a - 5) * (tmpMap.getShip(i).getX() + a - 5)) +
                                        ((tmpMap.getShip(i).getY() - 5) * (tmpMap.getShip(i).getY() - 5)) <
                                        12){
                                    inMiddle = true;
                                }
                            }
                        }
                    } while ((tmpMap.isShipColliding(i)) ||
                            (tmpMap.isShipOutOfArea(i)) ||
                            (inMiddle));
                }
                break;
        }
    }

    public boolean joinGame(){
        System.out.println("Going to try to join a game, getting address...");
        String input = "";
        Main.v.resetAll();
        boolean done = false;
        Main.v.menuItems = new String[2];
        Main.v.menuItems[0] = "Enter the IP:";
        do{
            Main.v.menuItems[1] = input;
            p.printMenu();
            repaint();
            waitKey();
            if(((Main.v.key >= 48) && (Main.v.key <= 57)) || //If it is a number..
                    (Main.v.key == 46)){ //If it is a '.'
                input += (char)Main.v.key;
            }
            if(Main.v.key == 8){//If it is 'DELETE
                if(input.length() > 0){
                    input = input.substring(0, input.length() - 1);
                }
            }
            if(Main.v.key == Main.v.ESC){
                return false;
            }
            if(Main.v.key == Main.v.ENTER){
                Main.v.menuItems[0] = "Trying to connect to:";
                p.printMenu();
                repaint();
                om.connectToHost(input);
                done = true;
            }
        }while(done == false);

        return true;
    }

    public boolean hostGame(){
        Main.v.resetAll();
        Main.v.menuItems = new String[2];
        Main.v.menuItems[0] = "Here is your IP:";
        Main.v.menuItems[1] = om.getCurrentIp();

        p.printMenu();
        repaint();
        om.startServer();
        return true;
    }

    public void onlineGame(boolean playerOne){

        Main.v.resetAll();
        if(playerOne){
            Main.v.player1Turn = true;
            Main.v.player1 = true;
            Main.v.currentMap = 0;
            Main.v.showShips[0] = true;
        } else{
            Main.v.player1Turn = false;
            Main.v.player1 = false;
            Main.v.currentMap = 1;
            Main.v.showShips[1] = true;
        }
        
        
        //////////////////////////////////////////////////////////////////////////
        
        System.out.println("Placing Your ships");
        
        if(playerOne){
            placeShips(Main.v.maps[0]);
            om.sendMap(Main.v.maps[0]);
            
            Main.v.menuItems = new String[1];
            Main.v.menuItems[0] = "Waiting for player 2....";
            p.printMenu();
            repaint();
            System.out.println("waiting for player 2's to place ships");
            
            Main.v.maps[1] = om.getMap();
        }else{
            placeShips(Main.v.maps[1]);



            Main.v.menuItems = new String[1];
            Main.v.menuItems[0] = "Waiting for player 2....";
            p.printMenu();
            repaint();
            System.out.println("waiting for player 2's to place ships");


            Main.v.maps[0] = om.getMap();
            om.sendMap(Main.v.maps[1]);
        }
        
//        Main.v.player1Turn = true;
//        if(playerOne){
//            System.out.println("Placing player 1's ships");
//            placeShips(Main.v.maps[0]);
//            om.sendMap(Main.v.maps[0]);
//            //sendMapAndCheck(Main.v.maps[0]);
//        }else{
//            Main.v.menuItems = new String[1];
//            Main.v.menuItems[0] = "Connected! Waiting...";
//            p.printMenu();
//            repaint();
//            System.out.println("waiting for player 1's to place ships");
//            //Main.v.maps[0] = getMapAndCheck();
//            Main.v.maps[0] = om.getMap();
//        }
//        System.out.println("Switching player and placing ships");
//
//        
//        Main.v.player1Turn = false;
//        if(!playerOne){
//            System.out.println("Placing player 2's ships");
//            placeShips(Main.v.maps[1]);
//            om.sendMap(Main.v.maps[1]);
//            //sendMapAndCheck(Main.v.maps[1]);
//        }else{
//            Main.v.menuItems = new String[1];
//            Main.v.menuItems[0] = "Waiting for player 2";
//            p.printMenu();
//            repaint();
//            System.out.println("waiting for player 2's to place ships");
//            //Main.v.maps[1] = getMapAndCheck();
//            Main.v.maps[1] = om.getMap();
//        }
        

        int[] xy = new int[2];
        do{
            Main.v.player1Turn = true;
            p.printGame();
            repaint();
            
            if(playerOne){
                System.out.println("Player 1 attacking...");
                attackPlayer();
                om.sendXY(Main.v.curserX, Main.v.curserY);
            }else{
                System.out.println("waiting for player 1 to attack");
                xy = om.getXY();
                Main.v.maps[1].setHit(xy[0], xy[1], true);
                if(Main.v.maps[1].isShipHere(xy[0], xy[1])){
                    Main.v.totalHits[0]++;
                }
            }


            Main.v.player1Turn = false;
            p.printGame();
            repaint();
            //did player 1 win?
            if(v.totalHits[0] < 17){
                if(!playerOne){
                    System.out.println("Player 2 attacking...");
                    attackPlayer();
                    om.sendXY(Main.v.curserX, Main.v.curserY);
                }else{
                    System.out.println("waiting for player 2 to attack");
                    xy = om.getXY();
                    Main.v.maps[0].setHit(xy[0], xy[1], true);
                    if(Main.v.maps[0].isShipHere(xy[0], xy[1])){
                        Main.v.totalHits[1]++;
                    }
                }
            }
        }while((v.totalHits[0] < 17) && (v.totalHits[1] < 17));

        om.closeAll();
        endGame();
        
    }

    public void sendMapAndCheck(Map mapToCheck){
        Main.v.menuItems = new String[1];
        Main.v.menuItems[0] = "Verifying....";
        p.printMenu();
        repaint();
        boolean matched = true;
        Map tmpMap;
        do{
            om.sendMap(mapToCheck);
            matched = true;
            tmpMap = om.getMap();
            System.out.print("Checking.... " + matched);
            for(int i = 0; i < 5; i++){
                if((tmpMap.getShip(i).getX() == mapToCheck.getShip(i).getX()) &&
                        (tmpMap.getShip(i).getY() == mapToCheck.getShip(i).getY()) &&
                        (tmpMap.getShip(i).getVertical() == mapToCheck.getShip(i).getVertical()) &&
                        (tmpMap.getShip(i).getLength() == mapToCheck.getShip(i).getLength())){
                    System.out.println(i + "goood.");
                }else{
                    System.err.println("A ship did not match up! : " + i);
                    matched = false;
                }
            }
            System.out.println("Maps match: " + matched);
        }while(matched == false);
        

        Main.v.menuItems = new String[1];
        Main.v.menuItems[0] = "Got it!";
        p.printMenu();
        repaint();
        tmpMap.getShip(0).setLength(0);
        om.sendMap(tmpMap);
        
    }

    public Map getMapAndCheck(){
        Map tmpMap, tmpMapTest;
        tmpMapTest = om.getMap();
        tmpMap = tmpMapTest;
        Main.v.menuItems = new String[1];
        Main.v.menuItems[0] = "Verifying....";
        p.printMenu();
        repaint();
        System.out.println("Player sent ships, verifying...");
        do{

            System.out.println("Sending map back....");
            om.sendMap(tmpMap);
            System.out.println("Getting map... ");
            tmpMapTest = om.getMap();
            System.out.print("Got map...");
            
            if(tmpMapTest.getShip(0).getLength() == 0){
                System.out.println("Map matched!");
            }else{
                System.out.println("Map did not match...");
                tmpMap = tmpMapTest;
            }
        }while(tmpMapTest != null);
        Main.v.menuItems = new String[1];
        Main.v.menuItems[0] = "Got it!";
        p.printMenu();
        repaint();
        System.out.println("got it!");
        return tmpMap;
    }

    public void endGame(){
        p.printGame();
        repaint();
        waitKey();
    }
}
