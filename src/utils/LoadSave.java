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
    public static final String LEVEL_ONE_DATA = "Level/level_one_data_long.png";
    public static final String MENU_BUTTONS = "UI/button_atlas.png";
    public static final String MENU_BACKGROUND = "UI/menu_background.png";
    public static final String MENU_BACKGROUND_IMG = "UI/background_menu.png";
    public static final String PAUSE_BACKGROUND = "UI/pause_menu.png";
    public static final String SOUND_BUTTONS = "UI/sound_button.png";
    public static final String URM_BUTTONS = "UI/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "UI/volume_buttons.png";
    public static final String PLAYING_BG_IMG = "Level/playing_bg_img.png";
    public static final String BIG_CLOUDS = "Level/big_clouds.png";
    public static final String SMALL_CLOUDS = "Level/small_clouds.png";





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
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];


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
