import java.awt.*;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Player extends Entity{

    int speed = 5;
    public Player(Color color, int x, int y, int w,int h,Game game){
        super(color, x, y, w, h, game);
        reloaded = true;
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    @Override
    public void move(){

        double nextLeft = x + dx;
        double nextRight = x + w + dx;
        double nextTop = y + dy;
        double nextBottom = y + h+ dy;

        if(game.getMousePosX() > getX()+2){
            dx = speed;
        }
        else if(game.getMousePosX() < getX()-2){
            dx = -speed;
        }
        else{ dx = 0;}

        if(nextLeft > game.getWidth()&& dx > 0){
            dx = 0;
        }
        if(nextRight < 0 && dx < 0){
            dx = 0;
        }

        x+=dx;
        y+=dy;

    }



}
