package squareball2;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Inimigo1 {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    int x = 0;
    int xa = 0;
    int y = 0;
    int ya = 0;
    int speed = 4;
    private int largura;
    private int altura;
    private int srcx1; // variavel para animar sprite
    private Jogo jogo;
    boolean mvt = false;
    double currentAngle;
    boolean colisao;
    private BufferedImage inimigo1;
    private Wall wall;

    public Inimigo1(Jogo jogo) throws IOException {
        this.x = x;
        this.y = y;
        this.jogo = jogo;
        inimigo1 = ImageIO.read(new File("Imagens/inimigo1.bmp"));
        this.largura = 150;
        this.altura  = 150;
        this.srcx1  = 0;
        this.colisao = true;
        this.wall = wall;
    }

    public Inimigo1(int largura, int altura, Jogo jogo, BufferedImage inimigo1) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.jogo = jogo;
        this.inimigo1 = inimigo1;
        this.colisao = colisao;
        this.wall = wall;
    }
    public void move() {
        if(colisao == true){    
            xa = speed;
            ya = speed;
            
            /*if (y + ya < 70){
                    ya = speed;
            }
            else if (y + ya > 550){
                    ya = -speed;
            }
            y = y + ya;*/
            if(collision()){
                colisao = false;
            }
            
            if(collisionW())
                ya = ya*-1;
	}
        if(colisao == false){
                x = 2000;
            }
    }
    public void desenha(Graphics2D g) {
        if(colisao == true)
            g.drawImage(this.inimigo1, this.x, this.y, this.x+this.largura, this.y+this.altura,
                    this.srcx1, 0, this.srcx1+this.largura,this.altura,null);
        
    }
    
    private boolean collision() {
        return jogo.ball.getBounds().intersects(getBounds());
    }
    
    public boolean collisionW() {
	return jogo.wall.getBounds().intersects(getBounds());
    }
    
    public Rectangle getBounds() {
            return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
}
