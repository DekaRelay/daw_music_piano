package piano;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.sound.midi.*;

public class PianoRoll{
    
    private int x;
    private int y;
    
    private PImage grid;
    private PImage block;
    
    private Synthesizer synth;
    private MidiChannel[] midiChannel;
    private Instrument[] instrument;
    
    private Instrument currentInstrument;
    private int currentInstrumentNum;
    
    private List<int[]> blockXY;
    
    /**
     * Constructor for PianoRoll 
     * Synthesizer and midichannels are initialised as well as default instrument set to piano(0)
     * @param x coordinate reference for drawing images
     * @param y coordinate reference for drawing images
     * @param grid image for the grid outline
     * @param block image for the each of the blocks
     * @throws MidiUnavailableException if a midi problem occurs during intitialisation
     */
    public PianoRoll(int x,int y,PImage grid,PImage block){
        this.x = x;
        this.y = y;
        
        this.blockXY = new ArrayList<int[]>();

        this.grid = grid;
        this.block = block;

        try{
            this.synth = MidiSystem.getSynthesizer();
            this.synth.open();
            this.midiChannel = this.synth.getChannels();
            this.instrument = this.synth.getDefaultSoundbank().getInstruments();
            this.synth.loadAllInstruments(this.synth.getDefaultSoundbank());
            this.currentInstrument = this.instrument[0];
            this.currentInstrumentNum = 0;
        }catch(MidiUnavailableException Exception){
            System.out.println("uh oh");
        }

    }

    /**
     * Returns whether the contents of an array are contained in a specific array in a list object with array types
     * @param list object consisting of int[] types
     * @param array of type int[]
     * @return true if contents array is within a specified array in list
     */
    public boolean arrayListContains(List<int[]> list, int[] array) {
        
        for (int i = 0; i < list.size(); i++){
            if (Arrays.equals(list.get(i), array)) return true;
        }
        return false;
    }

    /**
     * Removes an array from a list with array types provided the contents are equal
     * @param list object consisting of int[] types
     * @param array of type int[]
     * @return true if the removal is a success
     */
    public boolean arrayListRemove(List<int[]> list, int[] array) {

        int index = -1;
        for (int i = 0; i < list.size(); i++){
            if (Arrays.equals(list.get(i), array)){
                index = i;
            }
        }
        if(index!=-1){
            list.remove(index);
            return true;
        }else{
            return false;
        }
        
    }

    /**
     * Checks the coordinates of the mouse click within range of the piano grid
     * Adds blocks if there is no activated block within the coordinate range
     * Removes blocks if they already exist in the coordinate range
     * @param x value of the mouse
     * @param y value of the mouse
     * @return true if the block can be removed or added
     */
    public boolean check(int x, int y){

        
        for(int i=60;i<540;i+=15){
            for(int j=75;j<355;j+=20){

                if(x > i && x < i+15 && y > j && y < j+20){
                    int[] coord = {i+1,j+1};
                    if(this.arrayListContains(this.blockXY,coord)){
                        this.arrayListRemove(this.blockXY,coord);
                        //remove from piano sequence
                        return true;
                        
                    }else{
                        this.blockXY.add(coord);
                        //add to piano sequence
                        return true;
                        
                    }
                }
                
            }
        }
        return false;
        
        
    }
    
    /**
     * Plays sound of a column in the grid depending on the arrays presented in the list i
     * Sets the instrument using the currentInstrument value
     * Uses Midichannel[0] to play the primary instrument notes
     * Uses Midichannel[10] to play the drum line
     * @param i List of type int[] holding values of velocity and key number
     * @return true if the size of i == 14 and the sounds are played
     */
    public boolean playSound(List<int[]> i){
        
        
        if(i.size() == 14){
            midiChannel[0].programChange(this.currentInstrument.getPatch().getProgram());
            midiChannel[10].programChange(this.instrument[115].getPatch().getProgram());
            //for(int j = 0;j < i.size();j++){
                //midiChannel[0].noteOn(72-i.get(j)[0],i.get(j)[1]);
            //}
            
            midiChannel[0].noteOn(72-i.get(0)[0],i.get(0)[1]);
            midiChannel[0].noteOn(72-i.get(1)[0],i.get(1)[1]);
            midiChannel[0].noteOn(72-i.get(2)[0],i.get(2)[1]);
            midiChannel[0].noteOn(72-i.get(3)[0],i.get(3)[1]);
            midiChannel[0].noteOn(72-i.get(4)[0],i.get(4)[1]);
            midiChannel[0].noteOn(72-i.get(5)[0],i.get(5)[1]);
            midiChannel[0].noteOn(72-i.get(6)[0],i.get(6)[1]);
            midiChannel[0].noteOn(72-i.get(7)[0],i.get(7)[1]);
            midiChannel[0].noteOn(72-i.get(8)[0],i.get(8)[1]);
            midiChannel[0].noteOn(72-i.get(9)[0],i.get(9)[1]);
            midiChannel[0].noteOn(72-i.get(10)[0],i.get(10)[1]);
            midiChannel[0].noteOn(72-i.get(11)[0],i.get(11)[1]);
            midiChannel[0].noteOn(72-i.get(12)[0],i.get(12)[1]);
            midiChannel[10].noteOn(72-i.get(13)[0],i.get(13)[1]);
            return true;
        }else{
            return false;
        }
        

    }

    /**
     * Processes when to play a sound depending on the Pointer x value 
     * if the blockXY list is empty then the function returns 0
     * if the (x value - 60) is divisible by 15 then the sounds are turned off by lowering the velocity to 0, and then returns 1
     * else the sounds are played using the default velocity of 95 depending on whether there is a block in the specified column 
     * @param x value of the Pointer class 
     * @return an integer between 0 - 2 depending on which if branch is followed
     */
    public int tick(int x){
        
        List<int[]> keys = new ArrayList<int[]>();
        
        if(this.blockXY.size() == 0){
            return 0;
        }else if((x - 60)%15 == 0){
            for(int i = 0 ; i<14 ; i++){
                
                int[] velocity = {i,0};
                keys.add(velocity);
            }
            this.playSound(keys);
            return 1;
        
        }else{
            int[] coord = {x,75};
            int edit = 0;

            for(int j = 75 ; j<355 ; j+=20){
                
                coord[1] = j+1;
                edit = (j-75)/20;

                if(this.arrayListContains(this.blockXY,coord)){

                    int[] velocity = {edit,95};
                    keys.add(velocity);
                }else{

                    int[] velocity = {edit,0};
                    keys.add(velocity);
                }
            }

            if(keys.size()>0){

                this.playSound(keys);
                
            }
            return 2;
        }
        
        
    }

    /**
     * An alternative method to turn the sound off using either the stop function or pause
     * @return true when the process is completed
     */
    public boolean soundOff(){
        List<int[]> keys = new ArrayList<int[]>();
        for(int i = 0 ; i<14 ; i++){
                
            int[] velocity = {i,0};
            keys.add(velocity);
        }
        this.playSound(keys);
        return true;
    }

    /**
     * Resets the blockXY list to an empty arraylist<int[]> 
     * @return true when action complete
     */
    public boolean reset(){
        this.blockXY = new ArrayList<int[]>();
        return true;
    }

    /**
     * Return the blockXY list
     * @return the blockXY list<int[]>
     */
    public List<int[]> getBlocks(){
        return this.blockXY;
    }

    /**
     * Set the blockXY list to the blocks parameter
     * @param blocks
     */
    public void setBlocks(List<int[]> blocks){
        this.blockXY = blocks;
    }

    /**
     * Set the current instrument to the integer value given in the parameter
     * @param num integer value of the instrument in the instrument bank
     */
    public void setInstrument(int num){
        this.currentInstrument = this.instrument[num];
        this.currentInstrumentNum = num;
    }

    /**
     * Return the instrument value of the current instrument
     * @return integer
     */
    public int returnInstrument(){
        return this.currentInstrumentNum;
    }

    /**
     * Draws the image of piano roll and grid as well as whatever blocks are active in their respective positions
     * A for loop is utilised to draw each of the blocks within the the blockXY list
     * @param app PApplet parameter from the App class 
     */
    public void draw(PApplet app){
        app.image(this.grid,this.x,this.y);

        for(int i = 0;i<this.blockXY.size();i++){
            int[] coord = new int[2];
            coord=this.blockXY.get(i);
            app.image(this.block,coord[0],coord[1]);

        }

    }
}