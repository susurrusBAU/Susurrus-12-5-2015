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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author P
 */
public class UI extends JFrame implements ActionListener{
    MidiConnector midcon;
    JComboBox combo=new JComboBox();
    JButton ConnectToMidiButton=new JButton("Connect Device");
    JPanel panel1=new JPanel();
    String text="test";
    public UI() throws MidiUnavailableException{
        getContentPane().setLayout(new BorderLayout());
        panel1.setLayout(new FlowLayout(FlowLayout.RIGHT,20,20));
        getContentPane().add(panel1,BorderLayout.NORTH);
        
        midcon=new MidiConnector();
        for(int i=0;i<midcon.NumberOfDevices();i++){
            combo.addItem(midcon.getMidiDeviceAt(i));
        }
        
        panel1.add(combo);
        panel1.add(ConnectToMidiButton);
        ConnectToMidiButton.addActionListener(this);
        setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) throws MidiUnavailableException  {
        new UI();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ConnectToMidiButton){
            try {
                midcon.ConnectToDevice(combo.getSelectedIndex());
            } catch (MidiUnavailableException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
