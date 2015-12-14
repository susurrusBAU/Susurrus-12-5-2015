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
     public representMidi(String s)
    {
        pos=new int[999];
        music=s;
        JFrame frame = new JFrame("TheFrame");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setVisible(true);
        Disect();
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        frame.paint(graphics2D);
        System.out.println(s);
        
    }
     
     void Disect(){
         for(int i=0;i<music.length();i++){
             if(music.charAt(i)=='C')
                 pos[i]=0;
             else if(music.charAt(i)=='D')
                 pos[i]=1;
             else if(music.charAt(i)=='E')
                 pos[i]=1;
             else if(music.charAt(i)=='F')
                 pos[i]=2;
             else if(music.charAt(i)=='G')
                 pos[i]=3;
         }
         count++;
     }
     
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
        
        
        
    }
     
     public static void main(String[] args)
    {
        new representMidi("");
    }
    
}
