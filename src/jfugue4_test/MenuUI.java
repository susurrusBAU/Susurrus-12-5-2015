/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfugue4_test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author P
 */
public class MenuUI extends JFrame implements ActionListener{
    private JPanel panel1=new JPanel();
    private JButton RecordButton=new JButton("Record");
    
    public MenuUI(){
        getContentPane().setLayout(new BorderLayout());
        panel1.setLayout(new FlowLayout(FlowLayout.RIGHT,20,20));
        getContentPane().add(panel1,BorderLayout.NORTH);
        panel1.add(RecordButton);
        RecordButton.addActionListener(this);
        setSize(600,500);
        this.setTitle("Susurrus");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) throws MidiUnavailableException  {
        new MenuUI();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==RecordButton){
            ;
        }
    }
    
    public MenuUI getThis(){
        return this;
    }
    
}
