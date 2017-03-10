import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by hiltjar000 on 3/2/2017.
 */
public class Group extends Entity implements ActionListener{

    ArrayList<Alien> aliens;

    Timer shoot = new Timer(3000+(int)(Math.random()*4000), this);
    public Group(Color color, int d, Game game, int num){
        super(color, game.getWidth()/4, game.getHeight()/8, game.getWidth()/2, game.getHeight()/3, game);
        aliens = new ArrayList<Alien>();
        add(num);

        dx = aliens.get(0).getDx();
        dy = 10;
        shoot.start();
    }

    public void add(int num){
        for ( int j = 0; j < 4; j++){
            for (int i = 0; i < num/4; i++){
                aliens.add(new Alien(color, x+i*((Alien.getD()+w)/Math.round(num/4)), y+j*(h/4), game));
            }
        }
        x = aliens.get(0).getX();
        y = aliens.get(0).getY();
        w = aliens.get(aliens.size()-1).getX()+Alien.getD()-x;
        h = aliens.get(aliens.size()-1).getY()+Alien.getD()-y;

    }

    @Override
    public void paint(Graphics g) {
        //g.setColor(Color.BLUE);
        //g.fillRect(x, y, w, h);
        for (Alien ali: aliens)
            ali.paint(g);
    }

    @Override
    public void move(){
        double nextLeft = x+dx;
        double nextRight = x+w+dx;
        double nextTop = y + dy;
        double nextBottom = y + h + dy;


        if (nextLeft <= 0 || nextRight > game.getWidth()) {
            dx *=-1;
            for (Alien ali: aliens) {
                ali.setDx(ali.getDx() * -1);
                ali.setY(ali.getY() + dy);
            }
            y+=dy;
        }

        for (Alien ali: aliens) {
            ali.move();
        }

        x+=dx;
    }


    public Alien get(int i){
        return aliens.get(i);
    }

    public void remove(int i){
        aliens.remove(i);
    }
    public int size(){
        return aliens.size();
    }

    public void actionPerformed(ActionEvent e) {
        shoot.stop();
        shoot = new Timer(3000+(int)(Math.random()*4000), this);
        int index = (int)(Math.random()*(aliens.size()-1));
        Alien alien = aliens.get(index);
        game.getEntities().add(new Bullet(alien.getX() + alien.getW() / 2, alien.getY() + alien.getH() / 2, game, Math.PI, alien));
        shoot.start();

    }

    public void stopTimer(){
        shoot.stop();
    }
}
