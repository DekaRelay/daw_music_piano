/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package piano;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class PianoTest{
    
    //Test that the keys are played when the keys list is not empty and when its empty
    @Test
    public void pianoSoundTest() {
        PianoRoll piano = new PianoRoll(60,75,null,null);
        List<int[]> keys = new ArrayList<int[]>();

        assertFalse(piano.playSound(keys));

        for(int i = 0 ; i<14 ; i++){
            int[] velocity = {i,0};
            keys.add(velocity);
        }

        assertTrue(piano.playSound(keys));
    }

    //Make sure the correct branch is selected when the piano is ticking
    @Test
    public void pianoTickTest(){
        PianoRoll piano = new PianoRoll(60,75,null,null);
        List<int[]> blocks = new ArrayList<int[]>();

        int result = piano.tick(79);

        assert result == 0;

        // result = piano.tick(75);

        // assert piano.tick(150) == 1;



        for(int i=60;i<540;i+=15){
            for(int j=75;j<355;j+=20){
                int[] coord = {i+1,j+1};
                blocks.add(coord);                 
            }

        }

        piano.setBlocks(blocks);
        result = piano.tick(79);

        assert piano.tick(79) == 2;
    }

    //Test the check function and whether it detects a mouseclick in the specified piano grid area
    @Test
    public void pianoCheckTest(){
        PianoRoll piano = new PianoRoll(60,75,null,null);

        for(int i=61;i<540;i+=15){
            for(int j=76;j<355;j+=20){
                assertTrue(piano.check(i,j)); //Tests that every block is activateable
                
            }
        }
        assertTrue(piano.check(80,90)); //Tests that the block is deactivated
        
        assertFalse(piano.check(0,0));  //Tests that parameters not with the bounds are rejected
    }

    // Test whether the sound does turn off
    @Test
    public void soundOff(){
        PianoRoll piano = new PianoRoll(60,75,null,null);

        assertTrue(piano.soundOff());
        assertTrue(piano.reset());
    }

    
}
