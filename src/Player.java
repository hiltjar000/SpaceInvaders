import java.awt.*;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Player extends Entity{

    int speed = 5;
    public Player(Color color, int x, int y, int w,int h,Game game){
        super(color, x, y, w, h, game);

    }

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(x, y, w, h);
    }

    @Override
    public void move(){

        double nextLeft = x + dx;
        double nextRight = x + w + dx;
        double nextTop = y + dy;
        double nextBottom = y + h+ dy;


        if (Stats.iswPressed()){dy = -speed;}
        else if (Stats.issPressed()){dy = speed;}
        else{dy = 0;}

        if (nextLeft >= 0) {
            if (Stats.isaPressed()) {

                dx = -speed;
            }
        }
        else{dx = 0;}

        if (nextRight <=game.getWidth()) {
            if (Stats.isdPressed()) {
                dx = speed;
            }
        }
        else{dx = 0;}

        if (!Stats.isaPressed() && !Stats.isdPressed()){
            dx = 0;
        }

        x+=dx;
        y+=dy;

    }



}
