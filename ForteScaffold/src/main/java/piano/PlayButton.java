package piano;

import processing.core.PApplet;
import processing.core.PImage;

public class PlayButton {

    private int playX;
    private int playY;

    private boolean paused;

    private PImage play;
    private PImage pause;
    private PImage current;
    private PImage buttonBack;

    /**
     * PlayButton constructor
     * Sets the current image sprite to the play sprite
     * @param x coordinate for the image
     * @param y coordinate for the image
     * @param sprite1 play image sprite
     * @param sprite2 pause image sprite
     * @param sprite3 buttonBack image sprite
     */
    public PlayButton(int x, int y, PImage sprite1, PImage sprite2, PImage sprite3){
        this.playX = x;
        this.playY = y;

        this.paused = true;
        
        this.play = sprite1;
        this.pause = sprite2;
        this.buttonBack = sprite3;

        this.current = this.play;
    }

    /**
     * Draw the current image
     * @param app PApplet parameter from the App class
     */
    public void draw(PApplet app){
        app.image(this.buttonBack,this.playX,this.playY);
        app.image(this.current,this.playX,this.playY);
    }

    /**
     * Function that changes the current image to the opposite image when clicked on
     * when paused the sound is turned off
     * @param piano PianoRoll object parameter used to turn off the sound 
     */
    public void playPause(PianoRoll piano){
        this.paused = !this.paused;

        if(this.current == this.play){
            this.current = this.pause;
        }else if(this.current == this.pause){
            this.current = this.play;
            piano.soundOff();
        }

        
    }

    /**
     * Used to change the current image to stop/pause forcefully 
     */
    public void playStop(){
        this.current = this.play;

    }
}