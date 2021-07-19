package piano;

import processing.core.PApplet;
import processing.core.PImage;

public class ChangeInstrument {

    private int changeX;
    private int changeY;

    private int[] instruments;

    private PImage changeLeft;
    private PImage changeRight;
    private PImage P;
    private PImage M;
    private PImage B;
    private PImage S;
    private PImage buttonBack;

    private PImage current;
    private int currentNum;
    
    /**
     * The ChangeInstrument constructor
     * Sets the current instrument image to P (piano) and the instrument value to 0
     * @param x coordinate for image draw purposes
     * @param y coordinate for image draw purposes
     * @param sprite1 changeLeft image sprite
     * @param sprite2 changeRight image sprite
     * @param sprite3 P image sprite
     * @param sprite4 M image sprite
     * @param sprite5 B image sprite
     * @param sprite6 S image sprite
     * @param sprite7 ButtonBack image sprite
     */
    public ChangeInstrument(int x, int y, PImage sprite1, PImage sprite2, PImage sprite3, PImage sprite4, PImage sprite5, PImage sprite6, PImage sprite7){
        this.changeX = x;
        this.changeY = y;

        this.changeLeft = sprite1;
        this.changeRight = sprite2;
        this.P = sprite3;
        this.M = sprite4;
        this.B = sprite5;
        this.S = sprite6;
        this.buttonBack = sprite7;

        this.current = this.P;
        this.currentNum = 0;

        //this.instruments = {0, 12, 105, 65};
        this.instruments = new int[4];
        this.instruments[0] = 0;
        this.instruments[1] = 12;
        this.instruments[2] = 105;
        this.instruments[3] = 65;

    }

    /**
     * Draws ChangeInstrument images
     * @param app PApplet parameter from the App class utilised for drawing the images
     */
    public void draw(PApplet app){
        app.image(this.buttonBack, this.changeX, this.changeY);
        app.image(this.changeLeft, this.changeX, this.changeY);
        app.image(this.buttonBack, this.changeX+45, this.changeY);
        app.image(this.current, this.changeX+45, this.changeY);
        app.image(this.buttonBack, this.changeX+90, this.changeY);
        app.image(this.changeRight, this.changeX+90, this.changeY);
    }

    /**
     * Function utilised for the right arrow button in which the instruments are cycled to the right
     * @param piano PianoRoll paramter used to set the current instrument in the piano grid
     * @return integer for testing purposes
     */
    public int changeRight(PianoRoll piano){
        if(this.currentNum == 0){
            this.current = this.M;
            piano.setInstrument(12);
            this.currentNum = 12;
            return 12;
        }else if(this.currentNum == 12){
            this.current = this.B;
            piano.setInstrument(105);
            this.currentNum = 105;
            return 105;
        }else if(this.currentNum == 105){
            this.current = this.S;
            piano.setInstrument(65);
            this.currentNum = 65;
            return 65;
        }else{
            this.current = this.P;
            piano.setInstrument(0);
            this.currentNum = 0;
            return 0;
        }

    }

    /**
     * Function used for the left arrow button in which the instruments are cycled to the left
     * @param piano PianoRoll parameter used to set the current instrument in the piano grid
     * @return integer for testing purposes
     */
    public int changeLeft(PianoRoll piano){
        if(this.currentNum == 0){
            this.current = this.S;
            piano.setInstrument(65);
            this.currentNum = 65;
            return 65;
        }else if(this.currentNum == 65){
            this.current = this.B;
            piano.setInstrument(105);
            this.currentNum = 105;
            return 105;
        }else if(this.currentNum == 105){
            this.current = this.M;
            piano.setInstrument(12);
            this.currentNum = 12;
            return 12;
        }else{
            this.current = this.P;
            piano.setInstrument(0);
            this.currentNum = 0;
            return 0;
        }
    }

    /**
     * Used to set the current instrument displayed to the num associated with the parameter value
     * @param piano PianoRoll parameter used to set the current instrument in the piano grid
     * @param num integer value of the instrument in the instrument bank
     * @return true if the num value is valid else return false
     */
    public boolean setCurrent(PianoRoll piano, int num){
        if(num == 0){
            this.current = this.P;
            piano.setInstrument(num);
            return true;
        }else if(num == 12){
            this.current = this.M;
            piano.setInstrument(num);
            return true;
        }else if(num == 105){
            this.current = this.B;
            piano.setInstrument(num);
            return true;
        }else if(num == 65){
            this.current = this.S;
            piano.setInstrument(num);
            return true;
        }else{
            return false;
        }
    }

}