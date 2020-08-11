package squareball2;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall {
    int x;
    int y;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private Jogo jogo;
    
    public Wall(Jogo jogo){
        this.jogo = jogo;
    }

    public void desenha(Graphics2D g){
        g.fillRect(x,y, WIDTH,HEIGHT);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
}
