import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Alien extends Entity{
    private static final int d = 20;

    public Alien(Color color, int x, int y,  Game game){
        super(color, x, y, d, d, game);

    }

    public void paint(Graphics g){

        g.setColor(color);
        g.fillOval(x, y, w, h);

    }
    @Override
    public void move(){
        x+=dx;
    }


    public static int getD() {return d;}
}
