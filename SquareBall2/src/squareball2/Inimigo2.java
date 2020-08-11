package squareball2;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import IA.AStar;
import IA.Node;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class Inimigo2 {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    int x = 600;
    int xa = 0;
    int y = 350;
    int ya = 0;
    int speed = 0;
    private int largura;
    private int altura;
    private int srcx1; // variavel para animar sprite
    private Jogo jogo;
    boolean mvt = false;
    double currentAngle;
    boolean colisao;
    BufferedImage inimigo1;
    BufferedImage inimigoD;
    Wall wall;
    AStar estrela;
    Node[][] fasenode;
    Node initialNode;
    int cont;

    public Inimigo2(Jogo jogo, AStar estrela) throws IOException {
        this.x = x;
        this.y = y;
        this.jogo = jogo;
        inimigo1 = ImageIO.read(new File("inimigo2.bmp"));
        inimigoD = ImageIO.read(new File("inimigoD.bmp"));
        this.largura = 150;
        this.altura = 150;
        this.srcx1 = 0;
        this.colisao = true;
        this.wall = wall;
        this.estrela = estrela;
        initialNode = new Node(x / 50, y / 50);
        cont = 0;

    }

    public Inimigo2(int largura, int altura, Jogo jogo, BufferedImage inimigo1, AStar estrela) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.jogo = jogo;
        this.inimigo1 = inimigo1;
        this.estrela = estrela;
        this.colisao = colisao;
        this.wall = wall;
        this.initialNode = initialNode;
        this.cont = cont;
        //this.estrela.setBlocks(this.block1);
    }

    public void move() {
        int cont2 = 0;
        if (colisao == true) {

            List<Node> path = this.estrela.findPath();
           
            //System.out.println(path);
            boolean contador;
            for (Node node : path){
                x = node.getRow() * 50; 
                y = node.getCol() * 50;
                
            }
        
        }

        if (colisao == false) {
            x = 2000;
            xa = 0;
            speed = 0;
        }
    }

    public void desenha(Graphics2D g) {
        if (colisao == true) {
            g.drawImage(this.inimigo1, this.x, this.y, this.x + this.largura, this.y + this.altura,
                    this.srcx1, 0, this.srcx1 + this.largura, this.altura, null);
        }

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
