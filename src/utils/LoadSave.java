package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class LoadSave {

    public static final String PLAYER_ATLAS = "Player/player_sprites.png";
    public static final String LEVEL_ATLAS = "Level/outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "Level/level_one_data.png";


    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+ fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            }   catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static int[][] getLevelData(){
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);

        for(int height = 0; height < img.getHeight(); height++)
            for (int width =0; width < img.getWidth(); width++){
                Color color = new Color(img.getRGB(width, height));
                int value = color.getRed();
                if(value >= 48)
                    value = 0;
                lvlData[height][width] = value;
            }
        return lvlData;
    }
}
