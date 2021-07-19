package piano;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
// import processing.core.PApplet;
// import processing.core.PImage;

public class OtherTest { 
    //Test the save and load functions
    @Test
    public void saveload(){
        PianoRoll piano = new PianoRoll(60,85,null,null);
        ChangeInstrument change = new ChangeInstrument(275,5,null,null,null,null,null,null,null);
        List<int[]> blocks = new ArrayList<int[]>();

        for(int i=60;i<540;i+=15){
            for(int j=75;j<355;j+=20){
                int[] coord = {i+1,j+1};
                blocks.add(coord);                 
            }

        }

        piano.setBlocks(blocks);

        SaveLoad save = new SaveLoad(140,5,null,null,null);

        assertTrue(save.save(piano));

        assertTrue(save.load(piano,change));

        assert piano.getBlocks().size() > 0;

    }

    //Test the change instrument function and make sure it is changing to the right instrument
    @Test
    public void ChangeInstrumentTest(){
        ChangeInstrument change = new ChangeInstrument(275,5,null,null,null,null,null,null,null);
        PianoRoll piano = new PianoRoll(60,75,null,null);

        assertTrue(change.setCurrent(piano, 65));   //Test Saxaphone
        assertTrue(change.setCurrent(piano, 12));  //Test Marimba
        assertTrue(change.setCurrent(piano, 105)); //Test Banjo
        assertTrue(change.setCurrent(piano, 0));  //Test Piano
        assertFalse(change.setCurrent(piano, -1)); //Test invalid instrument

        //Test changeLeft
        assert change.changeLeft(piano) == 65;
        assert change.changeLeft(piano) == 105;
        assert change.changeLeft(piano) == 12;
        assert change.changeLeft(piano) == 0;

        //Test changeRight
        assert change.changeRight(piano) == 12;
        assert change.changeRight(piano) == 105;
        assert change.changeRight(piano) == 65;
        assert change.changeRight(piano) == 0;


    }



}