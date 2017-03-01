import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jared H on 2/27/2017.
 */
public abstract class Entity implements ActionListener{

    protected Game game;
    protected Color color;
    protected int x, y, w, h, dx, dy;
    Timer reload = new Timer(750, this);;
    Boolean reloaded;

    public Entity(Color color, int x, int y, int w, int h, Game game) {
        this.game = game;
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }

    public void move(){
        x+=dx;
        y+=dy;
    }

    public void reload(){
        reloaded = false;
        reload.start();
    }

    public void actionPerformed(ActionEvent e){
        reloaded = true;
        reload.stop();
    }


    public abstract void paint(Graphics g);
    public int getX() {return x;}
    public int getY() {return y;}
    public int getW() {return w;}
    public int getH() {return h;}

    public Boolean getReloaded() {
        return reloaded;
    }
}
