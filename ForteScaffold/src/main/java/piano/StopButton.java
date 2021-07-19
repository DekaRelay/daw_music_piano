package piano;

import processing.core.PApplet;
import processing.core.PImage;

public class StopButton {

    private int stopX;
    private int stopY;

    private PImage stop;
    private PImage buttonBack;

    /**
     * Stop Button constructor
     * @param x coordinate of the image
     * @param y coordinate of image
     * @param stop the Stop image sprite
     * @param buttonBack the buttonback image sprite
     */
    public StopButton(int x, int y, PImage stop, PImage buttonBack){
        this.stopX = x;
        this.stopY = y;

        this.stop = stop;
        this.buttonBack = buttonBack;
    }

    /**
     * draws the image sprites
     * @param app PApplet parameter from the App class
     */
    public void draw(PApplet app){
        app.image(this.buttonBack,this.stopX,this.stopY);
        app.image(this.stop,this.stopX,this.stopY);

    }

    /**
     * Stops playback and changes the current sprites to appropriate images
     * @param pointer Pointer class parameter used to reset the pointer/cursor
     * @param play Play Class parameter used to change the pause image sprite to play
     * @param piano PianoRoll object parameter used to turn the sound off
     */
    public void stop(Pointer pointer, PlayButton play, PianoRoll piano){
        pointer.reset();
        play.playStop();
        piano.soundOff();
    }
}