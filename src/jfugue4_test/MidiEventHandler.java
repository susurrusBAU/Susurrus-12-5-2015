/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfugue4_test;

import java.util.ArrayList;
import java.util.List;
import org.jfugue.MusicStringParser;
import org.jfugue.Note;
import org.jfugue.ParserListenerAdapter;
import org.jfugue.Pattern;
import org.jfugue.Player;

/**
 *
 * @author P
 */
public class MidiEventHandler  extends ParserListenerAdapter{
    
    private List<org.jfugue.Instrument> instruments;
        int counter=0;
        public  MidiEventHandler()
        {
        instruments = new ArrayList<org.jfugue.Instrument>();
        }
        @Override
        public void noteEvent(Note note){
            
            MusicPlayerThread mp=new MusicPlayerThread(note);
            Thread t=new Thread(mp);
            t.start();
            
        }
        @Override
        public void instrumentEvent(org.jfugue.Instrument instrument)
        {
            System.out.println(instrument.toString());
        
        }

        public List<org.jfugue.Instrument> getInstrumentsUsed(Pattern pattern)
        {
        MusicStringParser parser = new MusicStringParser();
        parser.addParserListener(this);
        parser.parse(pattern);
        return instruments;
        } 
        
        public class MusicPlayerThread implements Runnable{
        private Note note;
        
        public MusicPlayerThread(Note n){
            note=n;
        }
        @Override
        public void run() {
            if(note.getDuration()==0.0){
            Player p=new Player();
            Pattern ptr=new Pattern();
            note.setDecimalDuration(0.3);
            ptr.add(note.getMusicString());
                System.out.println(note.getMusicString());
            p.play(ptr);
            }
        }
        
    }
} 

