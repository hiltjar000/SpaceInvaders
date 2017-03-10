import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import sun.security.jca.GetInstance;

import java.awt.*;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Bullet extends Entity{
    public static final int wid = 15, height = 45;
    public double angle;

    public Bullet(int x, int y, Game game, Entity ent) {
        super(Color.ORANGE, x, y, wid, height, game);
        shooter = ent;
        angle = 0;
        dx = 0;
        dy = -4;
    }
    public Bullet( int x, int y, Game game, double angle, Entity ent) {
        super(Color.ORANGE, x, y, wid, height, game);
        shooter = ent;
        this.angle = angle;
        dx = 0;
        dy = 4;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        if (angle != 0){
            g2d.rotate(angle, x+wid/2, y+height/2);
        }

        g2d.setColor(Color.GRAY);
        g2d.fillOval(x,y, w, h/3);
        g2d.setColor(color);
        g2d.fillRect(x, y+h/6, w, h/3);
        g2d.dispose();

    }

    public static int getWid() {
        return wid;
    }

    public static int getHeight() {
        return height;
    }
}
