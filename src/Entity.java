import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Jared H on 2/27/2017.
 */
public abstract class Entity implements ActionListener{

    protected Game game;
    protected Color color;
    protected int x, y, w, h, dx, dy;
    Timer reload = new Timer(600, this);;
    protected Boolean reloaded;
    protected Entity shooter;

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

    public Rectangle getBounds(){
        return new Rectangle(x, y, w, h);
    }
    public abstract void paint(Graphics g);


    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}
    public int getW() {return w;}
    public int getH() {return h;}
    public int getDx() {return dx;}
    public void setDx(int dx) {this.dx = dx;}
    public int getDy() {return dy;}
    public void setDy(int dy) {this.dy = dy;}
    public Boolean getReloaded() {
        return reloaded;
    }
    public Color getColor() {return color;}
    public Entity getShooter() {return shooter;}
}
