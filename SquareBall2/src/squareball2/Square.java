package squareball2;

import IA.AStar;
import IA.Node;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Square {

    private static final int Y_tela = 500;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    int x = 250;
    int xa = 0;
    int y = 100;
    int ya = 0;
    private int largura;
    private int altura;
    private int srcx1; // variavel para animar sprite
    private Jogo jogo;
    private Wall wall;
    boolean mvt = false;
    double currentAngle;
    boolean esquerda, direita, cima, baixo, parado;
    private BufferedImage player;
    private BufferedImage player2;
    private Inimigo1 t1;
    private boolean vida = true;
    AStar estrela;
    Node finalNode;

    public Square(Jogo jogo) throws IOException {
        this.vida = vida;
        player = ImageIO.read(new File("square.bmp"));
        player2 = ImageIO.read(new File("squarev1.bmp"));
        this.x = x;
        this.y = y;
        this.jogo = jogo;
        this.largura = player.getWidth();
        this.altura = player.getHeight();
        this.srcx1 = 0;
        this.currentAngle = 0;
        this.t1 = t1;
        this.wall = wall;
        this.estrela = estrela;
        finalNode = new Node(x / 50, y / 50);

    }

    public Square(int largura, int altura, Jogo jogo, BufferedImage player) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.jogo = jogo;
        this.player = player;
        this.currentAngle = currentAngle;
        this.t1 = t1;
        this.wall = wall;
        this.estrela = estrela;
        this.finalNode = finalNode;
        this.estrela.setFinalNode(this.finalNode);
        this.vida = vida;
    }

    public void move() {
        if (x + xa > 0 && x + xa < jogo.getWidth() - WIDTH) {
            x = x + xa;
        }
        if (y + ya > 0 && y + ya < jogo.getHeight() - WIDTH) {
            y = y + ya;
        }
        //this.finalNode = new Node(x / 50, y / 50);
    }

    public void desenha(Graphics2D g) {
        
            g.drawImage(this.player, this.x, this.y, this.x + this.largura, this.y + this.altura,
                    this.srcx1, 0, this.srcx1 + this.largura, this.altura, null);
         
    }

    public void teclaLiberada(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerda = false;
            mvt = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direita = false;
            mvt = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            cima = false;
            mvt = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            baixo = false;
            mvt = false;
        }

    }

    public void teclaPressionada(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            esquerda = true;
            mvt = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direita = true;
            mvt = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            cima = true;
            mvt = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            baixo = true;
            mvt = true;
        }
        if (esquerda == true) {
            x = x - 10;
        }
        if (direita == true) {
            this.srcx1 = (this.srcx1 + this.largura) % this.player.getWidth();
            x = x + 10;
        }
        if (cima == true) {
            y = y - 10;
        }
        if (baixo == true) {
            y = y + 10;
        }
        if (esquerda == false && direita == false && cima == false && baixo == false) {
            parado = true;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public boolean collision() {
        this.vida = false;
        return jogo.t11.getBounds().intersects(getBounds()) || jogo.t12.getBounds().intersects(getBounds())
                || jogo.t13.getBounds().intersects(getBounds()) || jogo.t111.getBounds().intersects(getBounds())
                || jogo.t112.getBounds().intersects(getBounds()) || jogo.t113.getBounds().intersects(getBounds())
                || jogo.t114.getBounds().intersects(getBounds()) || jogo.t1111.getBounds().intersects(getBounds())
                || jogo.t1112.getBounds().intersects(getBounds()) || jogo.t1113.getBounds().intersects(getBounds())
                || jogo.t1114.getBounds().intersects(getBounds()) || jogo.t1115.getBounds().intersects(getBounds());
    }

    public boolean collisionW() {
        return jogo.wall.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getTopY_tela() {
        return Y_tela - HEIGHT;
    }

}
