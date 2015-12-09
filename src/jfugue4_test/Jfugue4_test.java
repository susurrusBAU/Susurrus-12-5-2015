/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfugue4_test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.jfugue.ChannelPressure;
import org.jfugue.Controller;
import org.jfugue.DeviceThatWillTransmitMidi;
import org.jfugue.KeySignature;
import org.jfugue.Layer;
import org.jfugue.Measure;
import org.jfugue.MusicStringParser;
import org.jfugue.Note;
import org.jfugue.ParserListener;
import org.jfugue.ParserListenerAdapter;
import org.jfugue.Pattern;
import org.jfugue.PitchBend;
import org.jfugue.Player;
import org.jfugue.PolyphonicPressure;
import org.jfugue.Tempo;
import org.jfugue.Time;
import org.jfugue.Voice;

/**
 *
 * @author P
 */
public class Jfugue4_test extends JFrame{
    private ArrayList<MidiDevice> devices = new ArrayList<MidiDevice>();
    public JTextArea jta=new JTextArea();
    private Graphics2D g;
    
    
    public Jfugue4_test() throws MidiUnavailableException, InterruptedException, FileNotFoundException, IOException{
        
        getContentPane().setLayout(new BorderLayout());
        //getContentPane().add(new JScrollPane(jta),BorderLayout.CENTER);
        setSize(800,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        
        for (int i = 0; i < infos.length; i++) {
            try {
            //device = MidiSystem.getMidiDevice(infos[i]);
            devices.add(MidiSystem.getMidiDevice(infos[i]));
            devices.get(i).open();
            //does the device have any transmitters?
            //if it does, add it to the device list
            System.out.println(infos[i]);

            //get all transmitters
            //List<Transmitter> transmitters = device.getTransmitters();
            List<Transmitter> transmitters = devices.get(i).getTransmitters();
            //and for each transmitter
            for(int j = 0; j<transmitters.size();j++) {
                //create a new receiver
                transmitters.get(j).setReceiver(
                        //using my own MidiInputReceiver
                        new MidiInputReceiver(devices.get(i).getDeviceInfo().toString())
                );
            }
            //open each device
            //devices.get(i).open();
            //if code gets this far without throwing an exception
            //print a success message
            if(devices.get(i).isOpen()){
                System.out.println(devices.get(i).getDeviceInfo()+" Was Opened");
                
            }
            }catch (MidiUnavailableException e) {System.out.println("Error");}
            
        }
        
        DeviceThatWillTransmitMidi dwm =new DeviceThatWillTransmitMidi(devices.get(1).getDeviceInfo()); 
        //dwm.listenForMillis(10000); 
        dwm.addParserListener(new GetInstrumentsUsedTool());
        
        DoStuff();
        dwm.startListening();
        
        //Pattern ptrn=dwm.getPatternFromListening();
        //System.out.println(ptrn.toString());
        boolean bool=true;
        //Thread.sleep(10000);
        while(bool);
        dwm.stopListening();
        
        // music = dwm.getSequenceFromListening();
        
        System.out.println("finish~~~~");
        Pattern ptrn=dwm.getPatternFromListening();
        
        Player plr=new Player();
        /*plr.play(ptrn);*/
        for(int i=0;i<ptrn.getTokens().length;i++){
            //System.out.println(ptrn.getTokens()[i]);
        }
         //GetInstrumentsUsedTool plh=new  GetInstrumentsUsedTool();
         //plh.getInstrumentsUsed(ptrn);
        //plr.saveMidi(ptrn, new File("C:\\Users\\P\\Desktop\\midiTest\\Test.mid"));
        plr.close();
        //stem.out.println(music.toString());Player 
    }
    public static void main(String[] args) throws MidiUnavailableException, InterruptedException, IOException {
        // TODO code application logic here
        Jfugue4_test j=new Jfugue4_test();
    }
    
    public class MidiInputReceiver implements Receiver {
        public String name;
        public MidiInputReceiver(String name) {
            this.name = name;
        }
        public void send(MidiMessage msg, long timeStamp) {
            System.out.println("midi received");
        }
        public void close() {}
    }
    int NoteCounter=1;
    
    public void ClearSheet(){
        g=(Graphics2D ) getGraphics ();
        g.setColor(Color.white);
        g.fillRect(0,0,900,900);
        NoteCounter=1;
        DoStuff();
    }
    public void DoStuff(){
        System.out.println("done stuff");
        g=(Graphics2D ) getGraphics ();
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
        //g.drawRect(100,100,200,200);
    }
    
    public void DrawNotes(char note,char pos){
        g=(Graphics2D ) getGraphics ();
        g.setColor(Color.black);
        if(note=='C' && pos=='4'){
            g.fillOval(NoteCounter*20,175,10,10);
            g.drawLine(NoteCounter*20-5, 180, NoteCounter*20+15, 180);
        }
        else if(note=='D'&& pos=='4')
            g.fillOval(NoteCounter*20,160,10,10);
        else if(note=='E'&& pos=='4')
            g.fillOval(NoteCounter*20,145,10,10);
        else if(note=='F'&& pos=='4')
            g.fillOval(NoteCounter*20,135,10,10);
        else if(note=='G'&& pos=='4')
            g.fillOval(NoteCounter*20,120,10,10);
        else if(note=='A'&& pos=='4')
            g.fillOval(NoteCounter*20,105,10,10);
        else if(note=='B'&& pos=='4')
            g.fillOval(NoteCounter*20,95,10,10);
        
//        if(note=='C' && pos=='5')
//            g.fillOval(NoteCounter*20,175,10,10);
//        else if(note=='D'&& pos=='5')
//            g.fillOval(NoteCounter*20,160,10,10);
//        else if(note=='E'&& pos=='5')
//            g.fillOval(NoteCounter*20,145,10,10);
//        else if(note=='F'&& pos=='5')
//            g.fillOval(NoteCounter*20,135,10,10);
//        else if(note=='G'&& pos=='5')
//            g.fillOval(NoteCounter*20,120,10,10);
        NoteCounter++;
        if(NoteCounter==39)
            ClearSheet();
    }
    
    public class NoteDetect implements ParserListener{

        @Override
        public void voiceEvent(Voice voice) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void tempoEvent(Tempo tempo) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void instrumentEvent(org.jfugue.Instrument i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void layerEvent(Layer layer) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void measureEvent(Measure msr) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void timeEvent(Time time) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keySignatureEvent(KeySignature ks) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void controllerEvent(Controller cntrlr) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void channelPressureEvent(ChannelPressure cp) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void polyphonicPressureEvent(PolyphonicPressure pp) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pitchBendEvent(PitchBend pb) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void noteEvent(Note note) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void sequentialNoteEvent(Note note) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void parallelNoteEvent(Note note) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    public class  GetInstrumentsUsedTool extends ParserListenerAdapter{
        private List<org.jfugue.Instrument> instruments;
        int counter=0;
        public  GetInstrumentsUsedTool()
        {
        instruments = new ArrayList<org.jfugue.Instrument>();
        }
        @Override
        public void noteEvent(Note note){
//            if(note.getDuration()==0.0){
//            org.jfugue.Note n;
//            n=note;
//            
//            jta.append(note.getVerifyString()+"\n");
//            
//            Player p=new Player();
//            //if(note.getDecimalDuration()<0.1f)
//                note.setDecimalDuration(0.2);
//            //jta.append(note.getDuration()+"<-----old------\n");
//            //jta.append(note.getDecimalDuration()+"<----------\n");
//           // note.setAttackVelocity(velocity);
//            
//            //jta.append(note.getDuration()+"<-----new------\n");
//            //p.play(note.getMusicString());
//            }
//            counter++;
            char NoteType=note.getMusicString().charAt(0);
            char NotePos=note.getMusicString().charAt(1);
            if(note.getDuration()==0.0)
                DrawNotes(NoteType,NotePos);
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
            
            p.play(ptr);
            }
        }
        
    }
}
