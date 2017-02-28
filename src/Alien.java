import java.awt.*;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Alien extends Entity{

    public Alien(Color color, int x, int y, int d, Game game){
        super(color, x, y, d, d, game);
    }

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.drawOval(x, y, w, h);

    }
    public void move(){
        double nextLeft = x + dx;
        double nextRight = x + w + dx;
        double nextTop = y + dy;
        double nextBottom = y + h + dy;


        if (nextLeft <= 1 || nextRight > game.getWidth()) {
            dy*=-1;
        }
        x+=dx;
        y+=dy;


    }



}
