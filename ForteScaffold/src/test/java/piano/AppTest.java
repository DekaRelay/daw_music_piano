package piano;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.event.*;

public class AppTest {

    //Test that none of the classes instantiated are null
    @Test
    public void notNullConstructors(){
        assertNotNull(new PianoRoll(60,75,null,null));
        assertNotNull(new Pointer(48,59,null));
        assertNotNull(new PlayButton(5,5,null,null,null));
        assertNotNull(new StopButton(50,5,null,null));
        assertNotNull(new ClearButton(95,5,null,null));
        assertNotNull(new SaveLoad(140,5,null,null,null));
        assertNotNull(new ChangeInstrument(275,5,null,null,null,null,null,null,null));
        assertNotNull(new App());

    }

    @Test
    public void appConstruct(){
        App app = new App();
        // app.settings();
        // app.setup();
        // app.noLoop();
        // app.draw();
        // app.mouseX = 90;
        // app.mouseY = 180;
        // app.mousePressed();
        // assert app.piano.getBlocks().size() > 0;
        // app.mousePressed();
        // assert app.piano.getBlocks().size() == 0;
        // app.mouseX = 10;
        // app.mouseY = 10;
        // app.mousePressed();


        //assert app.noLoop();
        //MouseEvent e = new MouseEvent(app,1,1,0,530,150,1,false);
    }
}