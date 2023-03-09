package entities;

import java.awt.*;

public abstract class Entity {
    protected float x, y, width, height;
    protected Rectangle hitbox;

    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitbox();

    }

    protected void drawHitBox(Graphics g) {
        // Shows Hitbox#
        g.setColor(Color.pink) ;
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void initHitbox() {
        hitbox = new Rectangle((int) x, (int) y, (int) width, (int) height);
    }
    protected void updateHitbox() {
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
}
