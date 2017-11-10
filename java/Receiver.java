/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benjamin
 */
import java.net.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;


public class Receiver {
    int port = 54235;
    byte []buf = new byte[2048];  
    MulticastSocket ms;
    InetAddress group;
    int count_rcv;
    DatagramPacket recv;
    public void Set(){
        try {
            group = InetAddress.getByName("239.30.30.30");
        }
        catch (Exception e) {
            System.out.println("Problem Converting IP Addr "+e.getMessage());
        }
        try {
            ms = new MulticastSocket(port);
        } 
        catch (Exception e) {
            System.out.println("Problem Creating Multicast Socket "+e.getMessage());
        }
        try {
            ms.joinGroup(group);
        } 
        catch (Exception e) {
            System.out.println("Problem Joining Group "+e.getMessage());
        }
    }
    
    
    public void Receive(){
        while(true){
            try {
                    recv = new DatagramPacket(buf, buf.length);
                    ms.receive(recv);
                    count_rcv++;
                    System.out.println(new String(recv.getData()));
            }
            catch (Exception e) {
                System.out.println("Error Receiving "+e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Receiver");  
        Receiver r = new Receiver();
        r.Set();
        r.Receive();
    }
}


