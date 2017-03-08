import java.awt.*;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Bullet extends Entity{
    public static final int wid = 15, height = 45;
    public double angle;
    public Bullet( int x, int y, Game game) {
        super(Color.ORANGE, x, y, wid, height, game);

        dx = 0;
        dy = -4;
    }
    public Bullet( int x, int y, Game game, double angle) {
        super(Color.ORANGE, x, y, wid, height, game);
        this.angle = angle;
        dx = 0;
        dy = -4;
    }

    public void paint(Graphics g){
        Graphics g2d = (Graphics2D)

        g.setColor(Color.GRAY);
        g.fillOval(x,y, w, h/3);
        g.setColor(color);
        g.fillRect(x, y+h/6, w, h/3);

    }

    public static int getWid() {
        return wid;
    }

    public static int getHeight() {
        return height;
    }
}
