import java.awt.*;

/**
 * Created by hiltjar000 on 3/2/2017.
 */
public class Group extends Entity{

    Alien alien;
    public Group(Color color, int d, Game game, int num){
        super(color, game.getWidth()/4, game.getHeight()/4, game.getWidth()/2, game.getHeight()/3, game);
        alien = new Alien(color, x, y, game);

        dx = 5;

    }

    @Override
    public void paint(Graphics g) {
        alien.paint(g);
    }

    @Override
    public void move(){
        double nextLeft = x+dx;
        double nextRight = x+w+dx;
        double nextTop = y + dy;
        double nextBottom = y + h + dy;


        if (nextLeft <= 0 || nextRight > game.getWidth()) {

            alien.setDx(alien.getDx()*-1);
            alien.setY(alien.getY()+6);
        }


        alien.move();

    }


}
