import java.awt.*;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Player extends Entity{

    public Player(Color color, int x, int y, int w,int h, Game game){
        super(color, x, y, w, h, game);

    }

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(getX(), getY(), getW(), getH());
    }
}
