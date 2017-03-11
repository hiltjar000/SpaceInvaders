import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by hiltjar000 on 3/2/2017.
 */
public class Group extends Entity implements ActionListener{

    ArrayList<Alien> aliens;
    Boolean shot = false;
    int index;

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
        if (shot){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.rotate(-Math.PI/4, aliens.get(index).getX()+aliens.get(index).getW()*3/2+5, aliens.get(index).getY()-10);
            g2d.setFont(new Font("Times New Roman", Font.BOLD, 24));
            g2d.setColor(Color.RED);
            game.printSimpleString("KILL", 0, aliens.get(index).getX()+aliens.get(index).getW()*3/2+5, aliens.get(index).getY()-10, g2d);
        }
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
        if(i < index){
            index--;
        }
        else if (i == index){
            shot = false;
        }
    }
    public int size(){
        return aliens.size();
    }

    public void actionPerformed(ActionEvent e) {
        shoot.stop();
        shoot = new Timer(3000+(int)(Math.random()*4000), this);
        index = (int)(Math.random()*(aliens.size()-1));
        Alien alien = aliens.get(index);
        game.getEntities().add(new Bullet(alien.getX() + alien.getW() / 2, alien.getY() + alien.getH() / 2, game, Math.PI, alien));
        shot = true;
        shoot.start();

    }

    public void stopTimer(){
        shoot.stop();
    }
}
