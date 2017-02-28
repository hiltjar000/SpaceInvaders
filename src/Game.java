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
    final int TITLE_SIZE = 64, TEXT_SIZE = 28;

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
        game.run();

    }

    private void init(){
        entities = new ArrayList<Entity>();
        entities.add(new Player(Color.BLUE, getWidth()/2, getHeight()*3/4, 40, 40, this));

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 7; j++){
                entities.add(new Alien(Color.GREEN, (int) (getWidth()/5.5+getWidth()/16*(1.5*j) + 20), getHeight()/10+getHeight()/12*i, 40, this));
            }
        }
    }
    private void run(){
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (Stats.isMenu()){
            if (Stats.isSpacePressed()){
                init();
                Stats.setGame(true);
                Stats.setMenu(false);
            }
        }

        if(Stats.isGame()) {
            if (Stats.ispReleased()) {
                Stats.togglePause();
                Stats.setpReleased(false);
            }
            if (!Stats.isPause()) {
                for (Entity ent : entities) {
                    ent.move();
                }
            }

        }
        repaint();
    }


    public void paint(Graphics g){
        super.paint(g);

        if(Stats.isMenu()){
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Times New Roman", Font.BOLD, TITLE_SIZE));
            printSimpleString("Space Invaders", 0, getWidth()/2, getHeight()/3, g);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.BOLD, TEXT_SIZE));
            printSimpleString("Press 'space' to fire", 0, getWidth()/2, getHeight()/3+TITLE_SIZE*3/4, g);
            g.setColor(Color.PINK);
            printSimpleString("Press 'space' to start", 0, getWidth()/2, getHeight()/3 + TITLE_SIZE*3/2, g);
        }

        if (Stats.isGame()) {
            if (entities != null) {
                for (Entity ent : entities) {
                    ent.paint(g);
                }
            }
        }
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d){

        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width/2 - stringLen/2;

        g2d.drawString(s, start + XPos, YPos);

    }

    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){Stats.setSpacePressed(true);}
        if(e.getKeyCode() == KeyEvent.VK_P){Stats.setpPressed(true);}
        if(e.getKeyCode() == KeyEvent.VK_W){Stats.setwPressed(true);}
        if(e.getKeyCode() == KeyEvent.VK_A){Stats.setaPressed(true);}
        if(e.getKeyCode() == KeyEvent.VK_S){Stats.setsPressed(true);}
        if(e.getKeyCode() == KeyEvent.VK_D){Stats.setdPressed(true);}
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){Stats.setSpacePressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_P){
            Stats.setpPressed(false);
            if (Stats.isGame()) {
                Stats.setpReleased(true);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_W){Stats.setwPressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_A){Stats.setaPressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_S){Stats.setsPressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_D){Stats.setdPressed(false);}
    }
    
    public ArrayList<Entity> getEntities(){
        return entities;
    }



}
