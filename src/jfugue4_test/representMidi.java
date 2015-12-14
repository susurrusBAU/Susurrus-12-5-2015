/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfugue4_test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author P
 */
public class representMidi extends JPanel{
    String music;
    int[] pos;
    int count=0;
    private Graphics2D g;
     public representMidi(String s)
    {
        pos=new int[999];
        music=s;
        JFrame frame = new JFrame("TheFrame");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setVisible(true);
        
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        
        frame.paint(graphics2D);
        System.out.println(s);
        
    }
     
//     void Disect(){
//         pos=new int[999];
//         for(int i=0;i<music.length();i++){
//             if(music.charAt(i)=='C'){
//                 System.out.println("Hitted a "+music.charAt(i));
//                 pos[i]=0;
//                 count++;
//             }else if(music.charAt(i)=='D'){
//                 System.out.println("Hitted a "+music.charAt(i));
//                 pos[i]=0;
//                 count++;
//             }else if(music.charAt(i)=='E'){
//                 System.out.println("Hitted a "+music.charAt(i));
//                 pos[i]=2;
//                 count++;
//             }else if(music.charAt(i)=='F'){
//                 System.out.println("Hitted a "+music.charAt(i));
//                 pos[i]=3;
//                 count++;
//             }else if(music.charAt(i)=='G'){
//                 System.out.println("Hitted a "+music.charAt(i));
//                 pos[i]=4;
//                 count++;
//             }
//         }
//         
//     }
     
     protected void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0,900,900);
        g.setColor(Color.black);
        g.drawLine(0,50,800,50);
        g.drawLine(0,75,800,75);
        g.drawLine(0,100,800,100);
        g.drawLine(0,125,800,125);
        g.drawLine(0,150,800,150);
        
        g.drawLine(95, 0, 95, 200);
        g.drawLine(175, 0, 175, 200);
        g.drawLine(255, 0, 255, 200);
        g.drawLine(335, 0, 335, 200);
        g.drawLine(415, 0, 415, 200);
        g.drawLine(495, 0, 495, 200);
        g.drawLine(575, 0, 575, 200);
        g.drawLine(655, 0, 655, 200);
        g.drawLine(735, 0, 735, 200);
        
//        System.out.println("count="+count);
//        for(int i=0;i<count;i++){
//            System.out.println("herp derp"+pos[i]);
//            if(pos[i]==0){
//                g.fillOval(i*20,175,10,10);
//                g.drawLine(i*20-5, 180, i*20+15, 180);
//            }else if(pos[i]==4){
//                g.fillOval(i*20,120,10,10);
//            }
//        }
        
        for(int i=0;i<music.length();i++){
             if(music.charAt(i)=='C'){
                 System.out.println("Hitted a "+music.charAt(i));
                 pos[i]=0;
                 
                 g.fillOval(count*20,175,10,10);
                 g.drawLine(count*20-5, 180, count*20+15, 180);
                 count++;
             }else if(music.charAt(i)=='D'){
                 System.out.println("Hitted a "+music.charAt(i));
                 pos[i]=0;
                 count++;
             }else if(music.charAt(i)=='E'){
                 System.out.println("Hitted a "+music.charAt(i));
                 pos[i]=2;
                 count++;
             }else if(music.charAt(i)=='F'){
                 System.out.println("Hitted a "+music.charAt(i));
                 pos[i]=3;
                 count++;
             }else if(music.charAt(i)=='G'){
                 System.out.println("Hitted a "+music.charAt(i));
                 pos[i]=4;
                 
                 g.fillOval(count*20,120,10,10);
                 count++;
             }
         }
    }
     
     public void DoStuff(){
        System.out.println("done stuff");
        g=(Graphics2D ) getGraphics ();
        
     }
     
     public static void main(String[] args)
    {
        new representMidi("");
    }
    
}
