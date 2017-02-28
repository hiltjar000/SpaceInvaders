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

    public abstract void move();

    public abstract void paint(Graphics g);
}
