package piano;

import processing.core.PApplet;
import processing.core.PImage;

public class Pointer {

    private int x;
    private int y;

    private boolean active;
    private int vel;

    private PImage sprite;

    /**
     * Pointer class constructor
     * The velocity of the pointer/cursor is set to 1
     * @param x coordinate of the pointer image
     * @param y coordinate of the pointer image
     * @param sprite pointer image sprite
     */
    public Pointer(int x,int y,PImage sprite){
        this.x = x;
        this.y = y;

        this.active = false;
        this.vel = 1;
        
        this.sprite = sprite;
    }

    /**
     * If the active variable is true then the x position of the cursor changes using the velocity variable
     * Hence the cursor moves when active
     */
    public void tick(){
        //handles logic
        if(this.active){
            this.x+=this.vel;
            
            if(this.x > 528){
                this.x = 48;
            }
        }

    }

    /**
     * The draw function that draws the pointer image as well as a red line 
     * @param app PApplet parameter fromthe App class
     */
    public void draw(PApplet app){
        //handles graphics
        app.image(this.sprite,this.x,this.y);
        app.line(this.x+12,75,this.x+12,355);
        app.strokeWeight(1);
        app.stroke(255,0,0);
    }

    /**
     * Deactivates the pointer/cursor movement
     */
    public void activate(){
        this.active = !this.active;
    }

    /**
     * Manual reset of the pointer/cursor and deactivation
     */
    public void reset(){
        this.x = 48;
        this.active = false;
    }

    /**
     * Returns the position of the 
     * @return
     */
    public int returnX(){
        return this.x;
    }

}