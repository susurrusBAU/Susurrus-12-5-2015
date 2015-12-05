/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfugue4_test;

import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import org.jfugue.DeviceThatWillTransmitMidi;

/**
 *
 * @author P
 */
public class MidiConnector {
    private ArrayList<MidiDevice> devices = new ArrayList<MidiDevice>();
    private MidiDevice.Info[] infos;
    DeviceThatWillTransmitMidi dwm;
    public MidiConnector() throws MidiUnavailableException{
        infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            devices.add(MidiSystem.getMidiDevice(infos[i]));
            List<Transmitter> transmitters = devices.get(i).getTransmitters();
        for(int j = 0; j<transmitters.size();j++) {
                //create a new receiver
                transmitters.get(j).setReceiver(
                        new MidiInputReceiver(devices.get(i).getDeviceInfo().toString())
                );
            }
        }
        
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
    public String[] getMidiDevices(){
        
        String toRet[] = new String[infos.length];
        for(int i=0;i<infos.length;i++){
            toRet[i]=infos[i].getName().toString();
        }
        return toRet;
    }
    
    public String getMidiDeviceAt(int i){
        return infos[i].getName();
    }
    
    public int NumberOfDevices(){
        
        return infos.length;
    }
    
    public boolean ConnectToDevice(int i) throws MidiUnavailableException{
        dwm =new DeviceThatWillTransmitMidi(devices.get(i).getDeviceInfo()); 
        dwm.addParserListener(new MidiEventHandler());
        return true;
    }
}
