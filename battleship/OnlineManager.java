/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship;

/**
 *
 * @author spex
 */
import java.io.*;
import java.net.*;
public class OnlineManager {

    String host = "";
    final int port = 9001;
    InputStream is = null;
    OutputStream os = null;
    Socket socket = null;
    ServerSocket serverSocket = null;
    InetAddress ia = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    boolean isConnected = false;


    OnlineManager(){
        try{
            ia = InetAddress.getLocalHost();
        }catch(Exception e){
            System.exit(9001);
        }
        System.out.println(ia);
        System.out.println(ia.getCanonicalHostName());
        System.out.println(ia.getHostAddress());

    }

    public String getCurrentIp(){
        return ia.getHostAddress();
    }

    public void startServer(){

        try {
            System.out.println("Trying to create output socket on port: " + port);
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }
        System.out.println("Successfully created socket");
        socket = null;
        try {
            System.out.println("Trying/Waiting to connect to client....");
            socket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        connectStreams();
    }

    public void connectToHost(String host){
        try {
            System.out.println("Trying to connect to host: " + host + "...");
            socket = new Socket(host, port);
            System.out.println("Connection successful.");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:" + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to:" + host);
            System.exit(1);
        }
        connectStreams();
    }

    private void connectStreams(){
        try{
            is = socket.getInputStream();
            os = socket.getOutputStream();
            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);
        }catch (Exception e){
            System.err.println("Streams did not connect");
            System.exit(1);
        }
    }

    public void closeAll(){
        try{
            dis.close();
            dos.close();
            is.close();
            os.close();
        }catch(Exception e){
            System.err.println("Could not close inputs and outputs");
        }
    }
    
    public void sendXY(int x, int y){
        int combine = x + (y * 10);
        byte c = (byte) combine;
        try{
            dos.writeByte(c);
        }catch(Exception e){
            System.err.println("Could not send XY");
        }
    }
    
    public int[] getXY(){
        byte c;
        int x;
        int y;
        int[] tmp;
        tmp = new int[2];
        
        try{
            c = dis.readByte();
            x = c % 10;
            c -= x;
            y = c/10;
            tmp[0] = x;
            tmp[1] = y;
        }catch(Exception e){
            System.err.println("Could not read input of XY");
            System.exit(port);
        }
        return tmp;
        
    }

    public Map getMap(){
        Map tmpMap = new Map();
        int[] tmpByte;
        tmpByte = new int[5];

        try{
            System.out.println("Getting map....");

            for(int i = 0; i < 5; i++){

                tmpByte[i] = dis.readInt();
                
                if(tmpByte[i] == 200){
                    System.err.println("Returning null...");
                    return null;
                }
                


                System.out.println("Ship " + i + ": " + tmpByte[i]);
                
                tmpMap.getShip(i).setX(tmpByte[i] % 10);
                tmpByte[i] -= tmpByte[i] % 10;
                tmpByte[i] /= 10;
                tmpMap.getShip(i).setY(tmpByte[i] % 10);
                tmpByte[i] -= tmpByte[i] % 10;
                tmpByte[i] /= 10;
                if(tmpByte[i] == 0){
                    tmpMap.getShip(i).setVertical(false);
                }else{
                    tmpMap.getShip(i).setVertical(true);
                }
            }

            return tmpMap;
            
        }catch(Exception e){
            System.err.println("Could not read input map");
            System.exit(port);
        }
        return null;
    }

    public void sendDone(){
        System.err.println("Trying to send...");
        try{
            System.err.println("Sending done....");
            dos.writeByte(200);

        }catch(Exception e){
            System.err.println("Could not send 'done'");
            System.exit(port);
        }
        
    }

    public void sendMap(Map tmpMap){

        int[] tmpByte;
        tmpByte = new int[5];
        try{
            if(tmpMap == null){
                System.err.println("Sending null....");
                dos.writeByte(200);
            }else{
                System.out.println("Sending map....");
                for(int i = 0; i < 5; i++){
                    tmpByte[i] = 0;
                    if(tmpMap.getShip(i).getVertical()){
                        tmpByte[i] += 100;
                    }
                    tmpByte[i] += tmpMap.getShip(i).getY() * 10;
                    tmpByte[i] += tmpMap.getShip(i).getX();
                    
                    System.out.println("Ship " + i + ": " + tmpByte[i]);
                    dos.writeInt(tmpByte[i]);
                }
                
            }


        }catch(Exception e){
            System.err.println("Could not send the map");
            System.exit(port);
        }
    }

}
