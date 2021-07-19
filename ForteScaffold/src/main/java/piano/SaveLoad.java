package piano;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class SaveLoad{
    
    private int saveLoadX;
    private int saveLoadY;

    private PImage save;
    private PImage load;
    private PImage backButton;

    /**
     * SaveLoad Constructor
     * @param x coordinate of images
     * @param y coordinate of images
     * @param sprite1 Save image sprite
     * @param sprite2 load image sprite
     * @param sprite3 back Button image sprite
     */
    public SaveLoad(int x, int y, PImage sprite1, PImage sprite2, PImage sprite3){

        this.saveLoadX = x;
        this.saveLoadY = y;

        this.save = sprite1;
        this.load = sprite2;
        this.backButton = sprite3;
    }

    /**
     * Draw the image sprites
     */
    public void draw(PApplet app){
        app.image(this.backButton,this.saveLoadX,this.saveLoadY);
        app.image(this.save,this.saveLoadX,this.saveLoadY);
        app.image(this.backButton,this.saveLoadX+45,this.saveLoadY);
        app.image(this.load,this.saveLoadX+45,this.saveLoadY);
    }

    /**
     * Saves the current blocks activated in the piano grid as well as the current instrument used into a txt save file
     * @param piano PianoRoll object parameter used to retrieve the current activated blocks
     * @return true if successful save operation
     * @throws IOException if theres an error in the save process
     */
    public boolean save(PianoRoll piano){
        List<int[]> blocks = piano.getBlocks();
        String fileName = "pianoSave.txt";
        //if(blocks.size() == 0){
            //return;
        //}
        String blockData = "";
        if(blocks.size() != 0){
            blockData = Integer.toString(blocks.get(0)[0]) + "," + Integer.toString(blocks.get(0)[1]);
            for(int i = 1 ; i<blocks.size() ; i++){
            
                blockData = blockData + "-" + Integer.toString(blocks.get(i)[0]) + "," + Integer.toString(blocks.get(i)[1]);
    
            }

            
        }

        blockData = blockData + "-" + Integer.toString(piano.returnInstrument());
        
        try{
            //File file = new File(fileName);
            FileWriter file = new FileWriter(fileName);

            file.write(blockData);

            file.close();
            return true;

        }catch(IOException e){
            System.out.println("save error");
            return false;

        }
    
    }

    /**
     * Loads the previous save state of the piano grid and the current instrument from the pianoSave.txt file 
     * @param piano PianoRoll object parameter used to set the loaded block coordinates into the blockXY 
     * @param change ChangeInstrument object parameter used to set the current instrument image in the save file
     * @return true if the load was a success
     * @throws IOException if theres an error in the load function
     */
    public boolean load(PianoRoll piano, ChangeInstrument change){
        String fileName = "pianoSave.txt";

        List<int[]> blocks = new ArrayList<int[]>();

        try{
            File file = new File(fileName);

            Scanner fileScanner = new Scanner(file);


            if(fileScanner.hasNextLine()){
                String[] nums = (fileScanner.nextLine()).split("-");
                for(int i = 0 ; i<(nums.length-1) ; i++){
                    String[] stringSplit = nums[i].split(",");
                    int[] coord = {Integer.parseInt(stringSplit[0]),Integer.parseInt(stringSplit[1])};
    
                    blocks.add(coord);
                }
                change.setCurrent(piano, Integer.parseInt(nums[nums.length-1]));
            }
            

            piano.setBlocks(blocks);

            fileScanner.close();
            return true;

        }catch (IOException e){
            return false;
        }
    }
}