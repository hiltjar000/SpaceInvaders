import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Game extends JPanel implements ActionListener{
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
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();

    }

    public void init(){
        entities = new ArrayList<Entity>();
        entities.add(new Player(Color.GREEN, getWidth()/2, getHeight()*3/4, 40, 40, this));
    }
    public void run(){
        timer = new Timer(1000/60, this);
    }

    public void actionPerformed(ActionEvent e) {
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
}
