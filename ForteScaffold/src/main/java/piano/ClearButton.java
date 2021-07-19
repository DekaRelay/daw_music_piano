package piano;

import processing.core.PApplet;
import processing.core.PImage;

public class ClearButton{
    
    private int clearX;
    private int clearY;

    private PImage clear;
    private PImage buttonBack;

    /**
     * ClearButton constructor
     * @param x coordinate of the image
     * @param y coordinate of the image
     * @param sprite1 clear image sprite
     * @param sprite2 buttonBack image sprite
     */
    public ClearButton(int x, int y, PImage sprite1, PImage sprite2){
        
        this.clearX = x;
        this.clearY = y;

        this.clear = sprite1;
        this.buttonBack = sprite2;

    }

    /**
     * Function that clears the activated blocks in the piano grid
     * @param piano PianoRoll object parameter used to call its reset function
     */
    public void clear(PianoRoll piano){
        piano.reset();
    }

    /**
     * Draw function that draws the PImage variables
     * @param app PApplet paramter from the App class
     */
    public void draw(PApplet app){
        app.image(this.buttonBack,this.clearX,this.clearY);
        app.image(this.clear,this.clearX,this.clearY);
    }
}