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


public class Source {
    int port = 54235;
    byte []buf = new byte[2048]; //Send Data
    byte []buf1 = new byte[2048]; //Receive Data
   // MulticastSocket ms;
    DatagramSocket ds;
    int count_sent,count_rcv;
    String str1;
    ArrayList<String> logs =new ArrayList<String>();
    String []received = new String[10];
    InetAddress group;
    DatagramPacket send;
    DatagramPacket recv;
    
    public void Set(){
        try {
            group = InetAddress.getByName("239.30.30.30");
        }
        catch (Exception e) {
            System.out.println("Problem Converting IP Addr "+e.getMessage());
        }
        try {
            ds = new DatagramSocket();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*
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
        }*/
    }
    
    
    public void Send(){
        while(true){
            str1 = ds.getLocalAddress().getHostName()+"    " + ZonedDateTime.now()+"   " + count_sent;
            buf = str1.getBytes();
            send = new DatagramPacket(buf,buf.length, group, port);
            try{
                ds.send(send);
                count_sent++;
            }
            catch(Exception e){
                System.out.println("Error Sending "+e.getMessage());
            }
            try{
                Thread.currentThread().sleep(2000);
            }
            catch(Exception e){
                System.out.println("Error with send Thread "+e.getMessage());
            }
        }
    }
    /*
    public void Receive(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    recv = new DatagramPacket(buf1, buf1.length);
                    ms.receive(recv);
                    count_rcv++;
                    logs.add(new String(recv.getData()));
                } catch (Exception e) {
                    System.out.println("Error Receiving "+e.getMessage());
                }
            }
        });
    }
    */
    public static void main(String[] args) {
        System.out.println("Source");       
        Source src = new Source();
        src.Set();
        src.Send();
    }
}
