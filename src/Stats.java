/**
 * Created by Jared H on 2/27/2017.
 */
public class Stats {
    private static boolean menu = true, game = false, pause = false, win = false, lose = false;
    private static boolean spacePressed = false, pPressed = false, pReleased = false, mouseClicked = false, mousePressed = false;

    public static boolean isMenu() {return menu;}
    public static void setMenu(boolean menu) {Stats.menu = menu;}
    public static boolean isGame() {return game;}
    public static void setGame(boolean game) {Stats.game = game;}
    public static void toggleGame(){
        if (game){
            game = false;
        }
        else{game = true;}
    }
    public static boolean isPause() {return pause;}
    public static void setPause(boolean pause) {Stats.pause = pause;}
    public static void togglePause(){
        if (pause){
            pause = false;
        }
        else{pause = true;}
    }

    public static boolean isLose() {
        return lose;
    }

    public static void setLose(boolean lose) {
        Stats.lose = lose;
    }

    public static boolean isWin() {
        return win;
    }
    public static void setWin(boolean win) {
        Stats.win = win;
    }

    public static boolean isSpacePressed() {return spacePressed;}
    public static void setSpacePressed(boolean spacePressed) {Stats.spacePressed = spacePressed;}
    public static boolean ispPressed() {return pPressed;}
    public static void setpPressed(boolean pPressed) {Stats.pPressed = pPressed;}
    public static boolean ispReleased() {return pReleased;}
    public static void setpReleased(boolean pReleased) {Stats.pReleased = pReleased;}

    public static boolean isMouseClicked() {
        return mouseClicked;
    }

    public static void setMouseClicked(boolean mouseClicked) {
        Stats.mouseClicked = mouseClicked;
    }

    public static boolean isMousePressed() {
        return mousePressed;
    }

    public static void setMousePressed(boolean mousePressed) {
        Stats.mousePressed = mousePressed;
    }
}
