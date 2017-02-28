import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Game extends JPanel implements ActionListener, KeyListener{
    Timer timer;
    ArrayList<Entity> entities;

    public Game(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("SPACE INVADERS");
        setPreferredSize(new Dimension(800,800));
        setBackground(Color.black);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();

    }

    private void init(){
        entities = new ArrayList<Entity>();
        entities.add(new Player(Color.GREEN, getWidth()/2, getHeight()*3/4, 40, 40, this));
    }
    private void run(){
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        entities.get(0).move();
        repaint();
    }


    public void paint(Graphics g){
        super.paint(g);
        if (entities != null) {
            for (Entity ent : entities) {
                ent.paint(g);
            }
        }
    }


    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){Stats.setSpacePressed(true);}
        if(e.getKeyCode() == KeyEvent.VK_W){Stats.setwPressed(true);}
        if(e.getKeyCode() == KeyEvent.VK_A){Stats.setaPressed(true);}
        if(e.getKeyCode() == KeyEvent.VK_S){Stats.setsPressed(true);}
        if(e.getKeyCode() == KeyEvent.VK_D){Stats.setdPressed(true);}
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){Stats.setSpacePressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_P){Stats.setpPressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_W){Stats.setwPressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_A){Stats.setaPressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_S){Stats.setsPressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_D){Stats.setdPressed(false);}
    }
    
    public ArrayList<Entity> getEntities(){
        return entities;
    }



}
