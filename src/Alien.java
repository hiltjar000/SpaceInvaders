import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Alien extends Group{\

    public Alien(Color color, int x, int y, int d, Game game, int num){
        super(color, x, y, d, d, game);

        dx = 5;
        dy = 0;
    }

    public void paint(Graphics g){

        g.setColor(color);
        g.fillOval(x, y, w, h);

    }
    @Override
    public void move(){
        double nextLeft = aliens.get(0).getX()+dx;
        double nextRight = aliens.get(aliens.size()-1).getX()+w;
        double nextTop = y + dy;
        double nextBottom = y + h + dy;

        if (this == aliens.get(0)){
            if (nextLeft <= 0 || nextRight > game.getWidth()) {
                x+=5;
                dx*=-1;
                y +=6;
            }
        }
        else {
            if (nextLeft <= 0 || nextRight > game.getWidth()) {
                dx *= -1;
                y += 6;
            }
        }
        x+=dx;


    }




}
