package piano;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

    private PianoRoll piano;
    private Pointer pointer;
    private PlayButton play;
    private StopButton stop;
    private SaveLoad saveLoad;
    private ClearButton clear;
    private ChangeInstrument change;

    private PImage banner;
    private PImage middleBanner;
    private PImage keyboard;
    private PImage drumLine;

    public App() {
        // Initialise variables here
    }

    public void settings() {
        // Don't touch
        size(540, 355);
    }

    public void setup() {
        frameRate(60);
        // Load images and variables to be used and instantiated
        this.piano = new PianoRoll(60,75,this.loadImage("src/main/resources/grid.png"),this.loadImage("src/main/resources/block.png"));
        this.pointer = new Pointer(48,59,this.loadImage("src/main/resources/pointer.png"));
        this.play = new PlayButton(5,5,this.loadImage("src/main/resources/play.png"),this.loadImage("src/main/resources/pause.png"),this.loadImage("src/main/resources/buttonBack.png"));
        this.stop = new StopButton(50,5,this.loadImage("src/main/resources/stop.png"),this.loadImage("src/main/resources/buttonBack.png"));
        this.clear = new ClearButton(95,5,this.loadImage("src/main/resources/additional/reset.png"), this.loadImage("src/main/resources/buttonBack.png"));
        this.saveLoad = new SaveLoad(140,5,this.loadImage("src/main/resources/additional/save.png"), this.loadImage("src/main/resources/additional/load.png"), this.loadImage("src/main/resources/buttonBack.png"));
        this.change = new ChangeInstrument(275,5,this.loadImage("src/main/resources/additional/prev.png"), this.loadImage("src/main/resources/additional/next.png"), this.loadImage("src/main/resources/additional/P.png"), this.loadImage("src/main/resources/additional/M.png"), this.loadImage("src/main/resources/additional/B.png"), this.loadImage("src/main/resources/additional/S.png"), this.loadImage("src/main/resources/buttonBack.png"));

        this.banner = this.loadImage("src/main/resources/banner.png");
        this.keyboard = this.loadImage("src/main/resources/keyboard.png");
        this.middleBanner = this.loadImage("src/main/resources/middleBanner.png");
        this.drumLine = this.loadImage("src/main/resources/drumGrid.png");
        
    }

    public void draw() {
        // Draw the program and process logic with tick functions
        this.pointer.tick();
        this.piano.tick(this.pointer.returnX()+12);
        
        this.rect(0,0,550,365);
        this.image(this.middleBanner,0,0);
        this.image(this.banner,0,0);
        this.image(this.keyboard,0,75);
        this.image(this.drumLine,0,335);

        this.play.draw(this);
        this.stop.draw(this);
        this.piano.draw(this);
        this.pointer.draw(this);
        this.clear.draw(this);
        this.saveLoad.draw(this);
        this.change.draw(this);
    }

    public void mousePressed(){
        if(mouseX > 5 && mouseX < 45 && mouseY > 5 && mouseY < 45){ //Play/pause button
            this.play.playPause(this.piano);
            this.pointer.activate();
        }else if(mouseX > 50 && mouseX < 90 && mouseY > 5 && mouseY < 45){  //Stop button
            this.stop.stop(this.pointer, this.play, this.piano);
        }else if(mouseX > 60 && mouseX < 540 && mouseY > 75 && mouseY < 355){  //Piano roll grid
            int x = mouseX;
            int y = mouseY;
            this.piano.check(x,y);
        }else if(mouseX > 95 && mouseX < 135 && mouseY > 5 && mouseY < 45){  //Clear button
            this.clear.clear(this.piano);
            this.stop.stop(this.pointer, this.play, this.piano);
        }else if(mouseX > 140 && mouseX < 180 && mouseY > 5 && mouseY < 45){  //Save button
            this.saveLoad.save(this.piano);
            this.stop.stop(this.pointer, this.play, this.piano);
        }else if(mouseX > 185 && mouseX < 225 && mouseY > 5 && mouseY < 45){  //Load button
            this.saveLoad.load(this.piano, this.change);
            this.stop.stop(this.pointer, this.play, this.piano);
        }else if(mouseX > 275 && mouseX < 315 && mouseY > 5 && mouseY < 45){  //Change instrument to the left button
            this.change.changeLeft(this.piano);
        }else if(mouseX > 365 && mouseX < 405 && mouseY > 5 && mouseY < 45){  //Change instrument to the right button
            this.change.changeRight(this.piano);
        }
    }

    public static void main(String[] args) {
        // Don't touch this
        PApplet.main("piano.App");
    }
}
