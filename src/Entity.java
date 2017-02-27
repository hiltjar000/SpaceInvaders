import java.awt.*;

/**
 * Created by Jared H on 2/27/2017.
 */
public abstract class Entity {

    protected Game game;
    protected Color color;
    protected int x, y, w, h, dx, dy;

    public Entity(Color color, int x, int y, int w, int h, Game game) {
        this.game = game;
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }

    public void move(){
        double nextLeft = x + dx;
        double nextRight = x + w + dx;
        double nextTop = y + dy;
        double nextBottom = y + h+ dy;

        if(nextTop <=0 || nextBottom > game.getHeight()) {
            dy *= -1;
        }
        if (nextLeft <= 0 || nextRight > game.getWidth()) {
            dx*=-1;
        }
        x+=dx;
        y+=dy;
    }

    public abstract void paint(Graphics g);
}
