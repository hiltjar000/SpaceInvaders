import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Jared H on 2/27/2017.
 */
public class Game extends JPanel implements ActionListener, KeyListener, MouseListener{
    Timer timer;
    ArrayList<Entity> entities;
    final int TITLE_SIZE = 64, TEXT_SIZE = 28;
    private int mousePosX, mousePosY;
    boolean mouseClicked = false;

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


        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){
                super.mouseMoved(e);

                mousePosX = e.getX();
                mousePosY = e.getY();
            }
        });
        addMouseListener(this);
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.run();

    }

    private void init(){
        entities = new ArrayList<Entity>();
        entities.add(new Player(Color.WHITE, getWidth()/2, getHeight()*3/4, 40, 40, this));
        entities.add(new Group(Color.GREEN, Alien.getD(), this, 20));


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
                move();
                collision();
                fire();

            }

        }
        repaint();
    }

    public void fire(){
        if(mouseClicked){
            mouseClicked = false;
            if (entities.get(0).getReloaded() == true) {
                entities.get(0).reload();
                entities.add(new Bullet(entities.get(0).getX() + entities.get(0).getW() / 2 - Bullet.getWid() / 2, entities.get(0).getY(), this));
            }
        }
    }

    public void move(){
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).move();
        }
    }

    public void collision(){



    }


    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLUE);
        for(int i = 0; i < 2; i++){
            g.drawOval(mousePosX+4+i, mousePosY+4+i, 16-2*i, 16-2*i);
        }

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
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){Stats.setSpacePressed(false);}
        if(e.getKeyCode() == KeyEvent.VK_P){
            Stats.setpPressed(false);
            if (Stats.isGame()) {
                Stats.setpReleased(true);
            }
        }
    }
    
    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public int getMousePosX() {return mousePosX;}
    public int getMousePosY() {return mousePosY;}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (Stats.isGame()) {
            mouseClicked = true;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), null));
    }
}
