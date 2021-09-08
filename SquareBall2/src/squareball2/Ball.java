package squareball2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;

public class Ball {

    private static final int DIAMETER = 35;
    private BufferedImage bola;
    int x = 400;
    int y = 100;
    double xa = 0;
    double ya = 0;
    double speedx = 0;
    double speedy = 0;
    int attriction = 3;
    //double currentAngle ;

    boolean aceleracao = false;

    Jogo jogo;
    Square square;
    Inimigo1 t1;
    Wall wall;

    public Ball(Jogo jogo) throws IOException {
        this.jogo = jogo;
        //this.currentAngle = 0;
        bola = ImageIO.read(new File("Imagens/ball.bmp"));
        this.speedx = speedx;
        this.speedy = speedy;
    }

    public Ball(Jogo jogo, BufferedImage player) {
        this.jogo = jogo;
        //this.currentAngle = currentAngle;
        this.bola = bola;
        this.wall = wall;
    }

    void move() {
        xa = speedx;
        ya = speedy;
        x = x + (int) xa;
        y = y + (int) ya;

        if (speedx > 0 && collisionW() == false) {
            speedx = speedx - 0.06;
        }
        if (speedx < 0 && collisionW() == false) {
            speedx = speedx + 0.06;
        }
        if (speedy > 0 && collisionW() == false) {
            speedy = speedy - 0.06;
        }
        if (speedy < 0 && collisionW() == false) {
            speedy = speedy + 0.06;
        }

    }

    /* boolean changeDirection = true;
            
            
                if(square.direita == true){
                    speed += 5;
                    xa = speed*jogo.angle;
                }
                if(square.esquerda == true){
                    speed = 5;
                    xa -= speed*square.currentAngle;
                }
                if(square.cima == true){
                    speed = 5;
                    ya -= speed*square.currentAngle;
                }
                if(square.baixo == true) {
                    speed = 5;
                    ya += speed*square.currentAngle;
                }
                if(square.cima == false && square.baixo == false && 
                   square.direita == false && square.esquerda == false ){
                    xa = xa*-1;
                    ya = ya*-1;
                }
            
            if(collision()){
                speed += 3;
                }    
            
            if(speed > 0){
                aceleracao = true;
            }
            
            if(aceleracao == true){
                
                x = x + (int)xa;
                y = y + (int)ya;
                
                speed -= 0.1;
                xa = speed;
                
                
                if (x - xa == 0 ){
                    xa = -speed;
                }
                else if (x + xa > jogo.getWidth() - DIAMETER){
                        xa = -speed;
                }
                else if (y - ya == 0){
                        ya = -speed;
                }
                else if (y + ya > jogo.getHeight() - DIAMETER ){
                        ya = -speed;
                }
            
            }
            
            if (x + xa < 0 ){
                    xa = speed;
            }
            else if (x + xa > jogo.getWidth() - DIAMETER){
                    xa = -speed;
            }
            else if (y + ya < 0){
                    ya = speed;
            }
            else if (y + ya > jogo.getHeight() - DIAMETER ){
                    ya = -speed;
            }
            
            
            
            if (collisionW()){
                xa = xa*-1;

            } else 
                    changeDirection = false;*/
    public boolean collision() {
        
        return jogo.square.getBounds().intersects(getBounds());
    }

    public boolean collisionW() {
        return jogo.wall.getBounds().intersects(getBounds());
    }

    public void desenha(Graphics2D g) {
        g.drawImage(this.bola, x, y, this.DIAMETER, this.DIAMETER, null);
        //g.rotate(Math.toRadians(45), this.x + bola.getWidth()/2, this.y + bola.getHeight()/2);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
