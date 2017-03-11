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
    Group aliens;
    final int TITLE_SIZE = 64, TEXT_SIZE = 28;
    private int mousePosX, mousePosY, lives;

    private int numAliens = 20;

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
            @Override
            public void mouseDragged(MouseEvent e){
                super.mouseDragged(e);
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
        lives = 3;
        entities = new ArrayList<Entity>();
        aliens = new Group(Color.GREEN, Alien.getD(), this, numAliens);
        entities.add(new Player(Color.WHITE, getWidth()/2, getHeight()*3/4, 40, 40, this));
        entities.add(aliens);

    }
    private void run(){
        lives = 3;
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (Stats.isMenu() || Stats.isWin() || Stats.isLose()){
            if (Stats.isSpacePressed()){
                init();
                Stats.setLose(false);

                Stats.setGame(true);
                Stats.setMenu(false);
                Stats.setWin(false);
            }
        }

        else if(Stats.isGame()) {
            if (lives < 1){
                lose();
                return;
            }

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

    public void lose(){
        Stats.setLose(true);
        Stats.setGame(false);
        entities = null;
        aliens = null;

    }

    public void fire(){
        if (Stats.isGame()) {
            if (Stats.isMousePressed()) {
                if (entities.get(0).getReloaded() == true) {
                    entities.get(0).reload();
                    entities.add(new Bullet(entities.get(0).getX() + entities.get(0).getW() / 2 - Bullet.getWid() / 2, entities.get(0).getY(), this, entities.get(0)));
                }
            }
        }
    }

    public void move(){
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).move();
        }
    }

    public void collision(){


            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof Bullet) {
                    if (entities.get(i).getShooter() instanceof Player){
                        for (int j = 0; j < aliens.size(); j++) {
                            if (entities.get(i).getBounds().intersects(aliens.get(j).getBounds())) {
                                entities.remove(i);
                                aliens.remove(j);
                                return;
                            }
                        }
                    }
                    else if (entities.get(i).getShooter() instanceof Alien){
                        if (entities.get(i).getBounds().intersects(entities.get(0).getBounds())) {
                            entities.remove(i);
                            lives--;
                            reset();
                        }
                    }
                }
                else if(entities.get(i) instanceof Player){
                    for (int j = 0; j < aliens.size(); j++) {
                        if (entities.get(i).getBounds().intersects(aliens.get(j).getBounds())){
                            lives--;
                            reset();
                        }
                    }
                }
            }

        if (aliens.size() == 0){
            win();
        }

    }

    public void reset(){
        aliens.stopTimer();
        entities = new ArrayList<Entity>();
        aliens = new Group(Color.GREEN, Alien.getD(), this, numAliens);
        entities.add(new Player(Color.WHITE, getWidth()/2, getHeight()*3/4, 40, 40, this));
        entities.add(aliens);
    }


    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLUE);
        for(int i = 0; i < 2; i++){
            g.drawOval(mousePosX+4+i, mousePosY+4+i, 16-2*i, 16-2*i);
        }
        if(Stats.isMousePressed()) {
            for (int i = 0; i < 2; i++) {
                g.drawOval(mousePosX + 6 + i, mousePosY + 6 + i, 12 - 2 * i, 12 - 2 * i);
            }
        }

        if(Stats.isMenu()){
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Times New Roman", Font.BOLD, TITLE_SIZE));
            printSimpleString("Space Invaders", 0, getWidth()/2, getHeight()/3, g);
            g.setFont(new Font("Times New Roman", Font.BOLD, TEXT_SIZE));
            g.setColor(Color.CYAN);
            printSimpleString("You have three lives.", 0, getWidth()/2, getHeight()/3+TITLE_SIZE*3/4, g);
            g.setColor(Color.WHITE);
            printSimpleString("Click to fire, kill all the aliens", 0, getWidth()/2, getHeight()/3+TITLE_SIZE*3/2, g);
            g.setColor(Color.PINK);
            printSimpleString("Press 'space' to start", 0, getWidth()/2, getHeight()/3 + TITLE_SIZE*9/4, g);
            g.setColor(Color.GREEN);
            printSimpleString("Press 'P' to pause", 0, getWidth()/2, getHeight()/3 + (int)(TITLE_SIZE*12/4), g);
        }

        if (Stats.isGame()) {
            if(Stats.isPause()){
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Times New Roman", Font.BOLD, TEXT_SIZE));
                printSimpleString("Press 'P' to unpause", 0, getWidth()/2, getHeight()/10, g);
            }
            else{
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Times New Roman", Font.BOLD, TEXT_SIZE));
                printSimpleString("Press 'P' to pause", 0, getWidth()/2, getHeight()/10, g);
            }
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.BOLD, TEXT_SIZE));
            printSimpleString("Lives: " + lives, 0, getWidth()/14, TEXT_SIZE, g);

            if (entities != null) {
                for (Entity ent : entities) {
                    ent.paint(g);
                }
            }
        }

        if (Stats.isWin()){
            g.setColor(Color.CYAN);
            g.setFont(new Font("Times New Roman", Font.BOLD, TITLE_SIZE));
            printSimpleString("YOU WIN!!!", 0, getWidth()/2, getHeight()/2, g);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.BOLD, TEXT_SIZE));
            printSimpleString("Press 'space' to play again", 0, getWidth()/2, getHeight()/2 + TITLE_SIZE, g);
        }

        if (Stats.isLose()){
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman", Font.BOLD, TITLE_SIZE));
            printSimpleString("YOU LOSE!!!", 0, getWidth()/2, getHeight()/2, g);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.BOLD, TEXT_SIZE));
            printSimpleString("Press 'space' to play again", 0, getWidth()/2, getHeight()/2 + TITLE_SIZE, g);
        }
    }
    public void win(){
        aliens.stopTimer();
        Stats.setWin(true);
        Stats.setGame(false);
        entities = null;
        aliens = null;
    }

    public void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d){

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
        Stats.setMousePressed(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Stats.setMousePressed(false);

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
