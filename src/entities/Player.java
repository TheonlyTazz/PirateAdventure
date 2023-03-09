package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean left, right, up, down;
    private boolean moving = false, attacking = false;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x,(int) y, (int) width, (int) height, null);
        drawHitBox(g);

    }
    private void setAnimation() {
        int startAni = playerAction;

        if(moving)
            playerAction = RUNNING;

        else playerAction = IDLE;
        if(attacking)
            playerAction = ATTACK_1;
        if(startAni != playerAction) resetAniTick();
    }
    private void resetAniTick(){
        aniTick = 0;
        aniIndex = 0;
    }
    private void updatePos(){
        moving = false;

        if(left && !right){
            x -= playerSpeed;
        }else if(right && !left) {
            x += playerSpeed;
            moving = true;
        }
        if(up && !down){
            y -= playerSpeed;
            moving = true;
        } else if(down && !up){
            y += playerSpeed;
            moving = true;
        }
    }

    private void loadAnimations() {

            BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[9][6];
            for(int j = 0; j < animations.length; j++){
                for(int i = 0; i < animations[j].length; i++){
                    animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
                }
            }

    }

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
    }
    private void updateAnimationTick() {


        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }



    }

    public void resetDirBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttack(boolean attacking) {
        this.attacking = attacking;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
