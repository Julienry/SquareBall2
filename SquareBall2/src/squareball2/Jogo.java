package squareball2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.Point;
import static java.awt.image.ImageObserver.ABORT;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import IA.AStar;
import IA.Node;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;
import java.io.*;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.*;
import sun.audio.*;

@SuppressWarnings("serial")
public class Jogo extends JPanel /*implements MouseListener, MouseMotionListener*/ {

    URL url;

    String verify, putData;

    FileWriter fw = new FileWriter("save.txt");
    BufferedWriter bw = new BufferedWriter(fw);
    FileReader fr = new FileReader("save.txt");
    BufferedReader br = new BufferedReader(fr);
    String linha = br.readLine();

    TelaInicial telainicial = new TelaInicial(this);
    TelaInicial seta = new TelaInicial(this);
    SelecaoFases sf = new SelecaoFases(this);
    SelecaoFases setaf = new SelecaoFases(this);
    SelecaoFases sf1 = new SelecaoFases(this);
    SelecaoFases sf2 = new SelecaoFases(this);
    SelecaoFases sf3 = new SelecaoFases(this);
    Ball ball = new Ball(this);
    Square square = new Square(this);
    //fase 1
    Inimigo1 t11 = new Inimigo1(this);
    Inimigo1 t12 = new Inimigo1(this);
    Inimigo1 t13 = new Inimigo1(this);

    //fase 2
    Inimigo1 t111 = new Inimigo1(this);
    Inimigo1 t112 = new Inimigo1(this);
    Inimigo1 t113 = new Inimigo1(this);
    Inimigo1 t114 = new Inimigo1(this);

    //fase 3
    Inimigo1 t1111 = new Inimigo1(this);
    Inimigo1 t1112 = new Inimigo1(this);
    Inimigo1 t1113 = new Inimigo1(this);
    Inimigo1 t1114 = new Inimigo1(this);
    Inimigo1 t1115 = new Inimigo1(this);

    Wall wall = new Wall(this);

    private BufferedImage player;
    private BufferedImage bola;
    private BufferedImage inimigot1;
    private BufferedImage telai;
    private BufferedImage setai;
    private BufferedImage selecaof;
    private BufferedImage f1;
    private BufferedImage f2;
    private BufferedImage f3;
    private BufferedImage fseta;
    
    private int space = 50;

    private boolean jogar = true;
    private boolean sair = false;

    private boolean selecao = false;

    private boolean menu = true;
    private boolean iniciar = false;
    private boolean rotar = false;
    double angle = square.currentAngle;
    private AffineTransform tx;
    private AffineTransformOp op;
    private boolean bolar = false;
    private boolean chutar = false;

    public int cont = 0;

    boolean save1;
    boolean save2;
    boolean save3;

    boolean faset1 = true;
    boolean faset2 = false;
    boolean faset3 = false;

    boolean fasets1 = false;
    boolean fasets2 = false;
    boolean fasets3 = false;

    boolean fase1 = false;
    boolean fase2 = false;
    boolean fase3 = false;

    public int level1[][]
            = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    public int level2[][]
            = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    public int level3[][]
            = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1,},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1,},
            {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    JFrame frame;

    public Jogo() throws IOException, LineUnavailableException {
        this.setBackground(Color.white); // seta que o fundo da janela eh branco
        player = ImageIO.read(new File("Imagens/square.bmp"));
        bola = ImageIO.read(new File("Imagens/ball.bmp"));
        telai = ImageIO.read(new File("Imagens/telainicial.bmp"));
        setai = ImageIO.read(new File("Imagens/seta.bmp"));
        fseta = ImageIO.read(new File("Imagens/seta.bmp"));
        selecaof = ImageIO.read(new File("Imagens/selecaofases.bmp"));
        f1 = ImageIO.read(new File("Imagens/fase1.bmp"));
        f2 = ImageIO.read(new File("Imagens/fase2.bmp"));
        f2 = ImageIO.read(new File("Imagens/fase3.bmp"));
        inimigot1 = ImageIO.read(new File("Imagens/inimigo1.bmp"));
        this.iniciar = iniciar;
        this.selecao = selecao;
        this.rotar = rotar;
        this.bolar = bolar;
        this.tx = tx;
        this.op = op;
        this.angle = angle;
        this.space = space;
        this.level1 = level1;
        this.cont = cont;

        //AudioPlayer.player.start(musica);
        addKeyListener(
                new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e
            ) {
                square.keyTyped(e);

            }

            @Override
            public void keyReleased(KeyEvent e
            ) {
                square.teclaLiberada(e);
                if (iniciar == true) {
                    if (e.getKeyCode() == KeyEvent.VK_X) {
                        chutar = false;
                    }

                }
            }

            @Override
            public void keyPressed(KeyEvent e
            ) {
                square.teclaPressionada(e);
                if (menu == true) {
                    if (jogar == true) {
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            System.out.println("jogar");
                            jogar = false;
                            seta.xs = 620;
                            seta.ys = 535;
                            sair = true;
                        }
                    }
                    if (sair == true) {
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            System.out.println("sair");
                            sair = false;
                            seta.xs = 645;
                            seta.ys = 445;
                            jogar = true;

                        }
                    }

                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (jogar == true) {
                            try {
                                salvar();
                            } catch (IOException ex) {
                                Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            selecao = true;
                            setaf.x = 700;
                            setaf.y = 120;

                            //fase1 = true;
                        }

                    }

                    if (selecao == true) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            if (fasets3 == true) {
                                iniciar = true;
                                fase1 = false;
                                fase2 = false;
                                fase3 = true;

                                faset1 = false;
                                faset2 = false;
                                faset3 = false;
                                fasets1 = false;
                                fasets2 = false;
                                fasets3 = false;
                                selecao = false;
                                menu = false;

                                square.x = 450;
                                square.y = 220;
                                ball.x = 550;
                                ball.y = 220;
                                ball.speedx = 0;
                                ball.speedy = 0;
                                t1111.x = 960;
                                t1111.y = 350;
                                t1112.x = 700;
                                t1112.y = 60;
                                t1113.x = 700;
                                t1113.y = 550;
                                t1114.x = 70;
                                t1114.y = 350;
                                t1115.x = 550;
                                t1115.y = 350;
                            }

                            if (fasets1 == true) {
                                iniciar = true;
                                fase1 = true;
                                fase2 = false;
                                fase3 = false;

                                faset1 = false;
                                faset2 = false;
                                faset3 = false;
                                fasets1 = false;
                                fasets2 = false;
                                fasets3 = false;
                                selecao = false;
                                menu = false;

                                //fase 1
                                t11.x = 800;
                                t11.y = 350;
                                t12.x = 700;
                                t12.y = 60;
                                t13.x = 700;
                                t13.y = 550;
                            }
                            if (fasets2 == true) {
                                iniciar = true;
                                fase1 = false;
                                fase2 = true;
                                fase3 = false;

                                faset1 = false;
                                faset2 = false;
                                faset3 = false;
                                fasets1 = false;
                                fasets2 = false;
                                fasets3 = false;
                                selecao = false;
                                menu = false;
                                square.x = 450;
                                square.y = 220;
                                ball.x = 550;
                                ball.y = 220;
                                ball.speedx = 0;
                                ball.speedy = 0;
                                t111.x = 900;
                                t111.y = 350;
                                t112.x = 700;
                                t112.y = 60;
                                t113.x = 700;
                                t113.y = 550;
                                t114.x = 100;
                                t114.y = 350;
                            }

                        }

                        if (faset1 == true) {
                            setaf.x = 700;
                            setaf.y = 120;
                            fasets1 = true;
                        }
                        if (faset2 == true) {
                            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                                setaf.x = 700;
                                setaf.y = 320;
                                fasets2 = true;

                            }
                            if (e.getKeyCode() == KeyEvent.VK_UP) {
                                setaf.x = 700;
                                setaf.y = 120;
                                fasets1 = true;

                            }
                        }
                        if (faset3 == true) {

                            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                                if (setaf.y == 320) {
                                    setaf.x = 700;
                                    setaf.y = 520;
                                    fasets3 = true;
                                }
                                if (setaf.y == 120) {
                                    setaf.x = 700;
                                    setaf.y = 320;
                                    fasets2 = true;
                                }

                            }
                            if (e.getKeyCode() == KeyEvent.VK_UP) {
                                if (setaf.y == 320) {
                                    setaf.x = 700;
                                    setaf.y = 120;
                                    fasets1 = true;
                                }
                                if (setaf.y == 520) {
                                    setaf.x = 700;
                                    setaf.y = 320;
                                    fasets2 = true;
                                }
                            }
                        }

                    }
                }
                if (iniciar == true) {

                    if (e.getKeyCode() == KeyEvent.VK_C) {
                        angle += 0.3;
                        rotar = true;
                    }
                }
                if (iniciar == true) {
                    if (e.getKeyCode() == KeyEvent.VK_Z) {
                        angle -= 0.3;
                        rotar = true;
                    }
                }
                if (iniciar == true) {
                    if (e.getKeyCode() == KeyEvent.VK_X) {
                        chutar = true;
                    }

                }

            }
        }
        );

        setFocusable(
                true);

    }

    public void move() {
        if (iniciar == true) {
            if (fase1 == true) {

                t11.move();
                if (t11.y + t11.ya < 70) {
                    t11.speed = -t11.speed;
                } else if (t11.y + t11.ya > 550) {
                    t11.speed = -t11.speed;
                }
                t11.y = t11.y + t11.ya;

                t12.move();
                if (t12.x + t12.xa > 800) {
                    t12.speed = -t12.speed;
                } else if (t12.x + t12.xa < 450) {
                    t12.speed = -t12.speed;
                }
                t12.x = t12.x + t12.xa;

                t13.move();
                if (t13.x + t13.xa > 800) {
                    t13.speed = -t13.speed;
                } else if (t13.x + t13.xa < 450) {
                    t13.speed = -t13.speed;
                }
                t13.x = t13.x + t13.xa;
                if (t11.colisao == false && t12.colisao == false && t13.colisao == false) {

                    fase1 = false;
                    fase2 = true;
                    square.x = 450;
                    square.y = 220;
                    ball.x = 550;
                    ball.y = 220;
                    ball.speedx = 0;
                    ball.speedy = 0;
                    t111.x = 900;
                    t111.y = 350;
                    t112.x = 700;
                    t112.y = 60;
                    t113.x = 700;
                    t113.y = 550;
                    t114.x = 100;
                    t114.y = 350;
                    try {
                        salvar();

                    } catch (IOException ex) {
                        Logger.getLogger(Jogo.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }

                ball.move();
                square.move();
                int cont1 = 0;
                boolean t2move = true;
                if (t2move == true) {
                    this.cont = this.cont + 50;
                    if (this.cont == 500) {

                        //t2.move();
                        this.cont = 0;
                        this.square.finalNode = new Node(square.x / 50, square.y / 50);

                    }
                }
            }

            if (fase2 == true) {

                t111.move();
                if (t111.y + t111.ya < 70) {
                    t111.speed = -t111.speed;
                } else if (t111.y + t111.ya > 550) {
                    t111.speed = -t111.speed;
                }
                t111.y = t111.y + t111.ya;

                t112.move();
                if (t112.x + t112.xa > 900) {
                    t112.speed = -t112.speed;
                } else if (t112.x + t112.xa < 150) {
                    t112.speed = -t112.speed;
                }
                t112.x = t112.x + t112.xa;

                t113.move();
                if (t113.x + t113.xa > 900) {
                    t113.speed = -t113.speed;
                } else if (t113.x + t113.xa < 150) {
                    t113.speed = -t113.speed;
                }
                t113.x = t113.x + t113.xa;

                t114.move();
                if (t114.y + t114.ya < 70) {
                    t114.speed = -t114.speed;
                } else if (t114.y + t114.ya > 550) {
                    t114.speed = -t114.speed;
                }
                t114.y = t114.y + t114.ya;

                if (t111.colisao == false && t112.colisao == false && t113.colisao == false && t114.colisao == false) {

                    fase2 = false;
                    fase3 = true;
                    square.x = 450;
                    square.y = 220;
                    ball.x = 550;
                    ball.y = 220;
                    ball.speedx = 0;
                    ball.speedy = 0;
                    t1111.x = 960;
                    t1111.y = 350;
                    t1112.x = 700;
                    t1112.y = 60;
                    t1113.x = 700;
                    t1113.y = 550;
                    t1114.x = 70;
                    t1114.y = 350;
                    t1115.x = 550;
                    t1115.y = 350;
                    try {
                        salvar();

                    } catch (IOException ex) {
                        Logger.getLogger(Jogo.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }

                ball.move();
                square.move();
                int cont1 = 0;
                boolean t2move = true;
                if (t2move == true) {
                    this.cont = this.cont + 50;
                    if (this.cont == 500) {

                        //t2.move();
                        this.cont = 0;
                        this.square.finalNode = new Node(square.x / 50, square.y / 50);

                    }
                }
            }
            if (fase3 == true) {
                if(ball.x < 0 || ball.x > 1110){
                    ball.x = 550;
                    ball.y = 220;
                }
                if(ball.y < 0 || ball.y > 700){
                    ball.x = 550;
                    ball.y = 220;
                }
                
                t1111.move();
                if (t1111.y + t1111.ya < 70) {
                    t1111.speed = -t1111.speed;
                } else if (t1111.y + t1111.ya > 550) {
                    t1111.speed = -t1111.speed;
                }
                t1111.y = t1111.y + t1111.ya;

                t1112.move();
                if (t1112.x + t1112.xa > 900) {
                    t1112.speed = -t1112.speed;
                } else if (t1112.x + t1112.xa < 150) {
                    t1112.speed = -t1112.speed;
                }
                t1112.x = t1112.x + t1112.xa;

                t1113.move();
                if (t1113.x + t1113.xa > 900) {
                    t1113.speed = -t1113.speed;
                } else if (t1113.x + t1113.xa < 150) {
                    t1113.speed = -t1113.speed;
                }
                t1113.x = t1113.x + t1113.xa;

                t1114.move();
                if (t1114.y + t1114.ya < 70) {
                    t1114.speed = -t1114.speed;
                } else if (t1114.y + t1114.ya > 550) {
                    t1114.speed = -t1114.speed;
                }
                t1114.y = t1114.y + t1114.ya;

                t1115.move();
                if (t1115.x + t1115.xa > 800) {
                    t1115.speed = -t1115.speed;
                } else if (t1115.x + t1115.xa < 250) {
                    t1115.speed = -t1115.speed;
                }
                t1115.x = t1115.x + t1115.xa;

                ball.move();
                square.move();
                int cont1 = 0;
                boolean t2move = true;
                if (t2move == true) {
                    this.cont = this.cont + 50;
                    if (this.cont == 500) {

                        //t2.move();
                        this.cont = 0;
                        this.square.finalNode = new Node(square.x / 50, square.y / 50);

                    }
                }
                if (t1111.colisao == false && t1112.colisao == false && t1113.colisao == false && t1114.colisao == false && t1115.colisao == false) {
                    menu = true;
                    iniciar = false;

                }
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D quadra = (Graphics2D) g;
        Graphics2D gball = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        quadra.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gball.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (menu == true) {
            telainicial.desenha(g2d);
            seta.desenhaSeta(g2d);

        }
        if (selecao == true) {
            sf.desenha(g2d);
            setaf.desenhaSeta(g2d);
            if (faset1 == true) {
                sf1.desenhaFase1(g2d);
            }
            if (faset2 == true) {
                sf1.desenhaFase1(g2d);
                sf2.desenhaFase2(g2d);
            }
            if (faset3 == true) {
                sf1.desenhaFase1(g2d);
                sf2.desenhaFase2(g2d);
                sf3.desenhaFase3(g2d);
            }
        }

        if (fase1 == true) {
            //wall.desenha(g2d);
            criar(g);

            //desenha o inimigo
            t11.desenha(g2d);
            t12.desenha(g2d);
            t13.desenha(g2d);

            //desenha a bola
            ball.desenha(gball);

            //desenha o quadrado
            if (rotar == true) {
                quadra.rotate(angle, square.x + player.getWidth() / 2, square.y + player.getHeight() / 2);
                square.desenha(quadra);
            } else {
                square.desenha(quadra);
            }

            //colisão do quadrado com inimigo
            if (square.collision()) {
                square.x = 250;
                square.y = 500;
            }

            //colisão do quadrado com a parede
            //g2d.setFont(new Font("Verdana", Font.BOLD, 30));
            //g2d.drawString(String.valueOf(getScore()), 10, 30);
        }
        if (fase2 == true) {
            //wall.desenha(g2d);
            criar(g);

            //desenha o inimigo
            t111.desenha(g2d);
            t112.desenha(g2d);
            t113.desenha(g2d);
            t114.desenha(g2d);

            //desenha a bola
            ball.desenha(gball);

            //desenha o quadrado
            if (rotar == true) {
                quadra.rotate(angle, square.x + player.getWidth() / 2, square.y + player.getHeight() / 2);
                square.desenha(quadra);
            } else {
                square.desenha(quadra);
            }

            //colisão do quadrado com inimigo
            if (square.collision()) {
                square.x = 450;
                square.y = 220;
            }

            //colisão do quadrado com a parede
            //g2d.setFont(new Font("Verdana", Font.BOLD, 30));
            //g2d.drawString(String.valueOf(getScore()), 10, 30);
        }

        if (fase3 == true) {
            //wall.desenha(g2d);
            criar(g);

            //desenha o inimigo
            t1111.desenha(g2d);
            t1112.desenha(g2d);
            t1113.desenha(g2d);
            t1114.desenha(g2d);
            t1115.desenha(g2d);

            //desenha a bola
            ball.desenha(gball);

            //desenha o quadrado
            if (rotar == true) {
                quadra.rotate(angle, square.x + player.getWidth() / 2, square.y + player.getHeight() / 2);
                square.desenha(quadra);
            } else {
                square.desenha(quadra);
            }

            //colisão do quadrado com inimigo
            if (square.collision()) {
                square.x = 450;
                square.y = 220;
            }
            //colisão do quadrado com a parede
            //g2d.setFont(new Font("Verdana", Font.BOLD, 30));
            //g2d.drawString(String.valueOf(getScore()), 10, 30);
        }

    }

    public void criar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.green);
        if (fase1 == true) {
            int x = 0;
            int y = -15;

            for (int i = 0; i < this.level1.length; i++) { // linha
                for (int j = 0; j < this.level1[0].length; j++) { // coluna
                    if (this.level1[i][j] == 1) {
                        wall.x = x;
                        wall.y = y;
                        wall.desenha(g2d);

                    }
                    x += 50;

                    //começo da jogabilidade
                    if (square.collisionW()) {
                        if (square.direita) {
                            square.x = square.x - 6;
                        }
                        if (square.esquerda) {
                            square.x = square.x + 6;
                        }
                        if (square.baixo) {
                            square.y = square.y - 7;
                        }
                        if (square.cima) {
                            square.y = square.y + 7;
                        }
                    }

                    /*if (ball.collisionW()) {
                        ball.speedx = ball.speedx * -1;
                        ball.speedy = ball.speedy * -1;

                    }*/
                    if (ball.collisionW() && ball.speedy > 0) {
                        ball.y = ball.y - 4;
                        //ball.speedx = ball.speedx * -1;
                        ball.speedy = ball.speedy * -1;
                    }
                    if (ball.collisionW() && ball.speedy < 0) {
                        ball.y = ball.y + 4;
                        //ball.speedx = ball.speedx * -1;
                        ball.speedy = ball.speedy * -1;
                        //ball.speedy = ball.speedy * -1;
                    }
                    if (ball.collisionW() && ball.speedx > 0) {
                        ball.x = ball.x - 4;
                        //ball.speedx = ball.speedx * -1;
                        ball.speedx = ball.speedx * -1;
                    }
                    if (ball.collisionW() && ball.speedx < 0) {
                        ball.x = ball.x + 4;
                        ball.speedx = ball.speedx * -1;
                        //ball.speedy = ball.speedy * -1;
                    }

                    if (ball.collision()) {
                        if (square.parado) {
                            if (ball.speedy > 0) {

                                //ball.speedx = ball.speedx * -1;
                                ball.speedy = ball.speedy * -1;
                            }
                            if (ball.speedy < 0) {

                                //ball.speedx = ball.speedx * -1;
                                ball.speedy = ball.speedy * -1;
                                //ball.speedy = ball.speedy * -1;
                            }
                            if (ball.speedx > 0) {

                                //ball.speedx = ball.speedx * -1;
                                ball.speedx = ball.speedx * -1;
                            }
                            if (ball.speedx < 0) {

                                ball.speedx = ball.speedx * -1;
                                //ball.speedy = ball.speedy * -1;
                            }
                        }

                        if (square.esquerda) {
                            if (chutar == true) {
                                ball.speedx = 10;
                                //ball.speedx += 0.02;
                                ball.speedx = -ball.speedx;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x - 1;
                                }
                            } else {
                                ball.speedx = 6;
                                //ball.speedx += 0.02;
                                ball.speedx = -ball.speedx;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x - 1;
                                }
                            }
                        }
                        if (square.direita) {
                            if (chutar == true) {
                                ball.speedx = 10;
                                //ball.speedx = ball.speedx*-1;
                                ball.x = ball.x + 1;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x + 1;
                                }

                            } else {
                                ball.speedx = 6;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x + 1;
                                }
                            }
                        }
                        if (square.cima) {
                            if (chutar == true) {
                                ball.speedy = 10;
                                //ball.speedx += 0.02;
                                ball.speedy = -ball.speedy;
                                if (ball.collisionW()) {
                                    ball.speedy = ball.speedy * -1;
                                    ball.y = ball.y - 1;
                                }
                            } else {
                                ball.speedy = 6;
                                //ball.speedx += 0.02;
                                ball.speedy = -ball.speedy;
                                if (ball.collisionW()) {
                                    ball.speedy = ball.speedy * -1;
                                    ball.y = ball.y - 1;
                                }
                            }

                        }
                        if (square.baixo) {
                            if (chutar == true) {
                                ball.speedy = 10;
                                //ball.speedx = ball.speedx*-1;
                                ball.y = ball.y + 1;

                            } else {
                                ball.speedy = 6;
                                if (ball.collisionW()) {
                                    ball.speedy = ball.speedy * -1;
                                    ball.y = ball.y + 1;
                                }
                            }

                        }

                    }
                }
                //fim da jogabilidade

                x = 0;
                y += 50;
            }
        }
        if (fase2 == true) {
            g2d.setColor(Color.blue);
            int x = 0;
            int y = -15;

            for (int i = 0; i < this.level2.length; i++) { // linha
                for (int j = 0; j < this.level2[0].length; j++) { // coluna
                    if (this.level2[i][j] == 1) {
                        wall.x = x;
                        wall.y = y;
                        wall.desenha(g2d);

                    }
                    x += 50;

                    //começo da jogabilidade
                    if (square.collisionW()) {
                        if (square.direita) {
                            square.x = square.x - 6;
                        }
                        if (square.esquerda) {
                            square.x = square.x + 6;
                        }
                        if (square.baixo) {
                            square.y = square.y - 7;
                        }
                        if (square.cima) {
                            square.y = square.y + 7;
                        }
                    }

                    /*if (ball.collisionW()) {
                        ball.speedx = ball.speedx * -1;
                        ball.speedy = ball.speedy * -1;

                    }*/
                    if (ball.collisionW() && ball.speedy > 0) {
                        ball.y = ball.y - 4;
                        //ball.speedx = ball.speedx * -1;
                        ball.speedy = ball.speedy * -1;
                    }
                    if (ball.collisionW() && ball.speedy < 0) {
                        ball.y = ball.y + 4;
                        //ball.speedx = ball.speedx * -1;
                        ball.speedy = ball.speedy * -1;
                        //ball.speedy = ball.speedy * -1;
                    }
                    if (ball.collisionW() && ball.speedx > 0) {
                        ball.x = ball.x - 4;
                        //ball.speedx = ball.speedx * -1;
                        ball.speedx = ball.speedx * -1;
                    }
                    if (ball.collisionW() && ball.speedx < 0) {
                        ball.x = ball.x + 4;
                        ball.speedx = ball.speedx * -1;
                        //ball.speedy = ball.speedy * -1;
                    }

                    if (ball.collision()) {
                        if (square.parado) {
                            if (ball.speedy > 0) {

                                //ball.speedx = ball.speedx * -1;
                                ball.speedy = ball.speedy * -1;
                            }
                            if (ball.speedy < 0) {

                                //ball.speedx = ball.speedx * -1;
                                ball.speedy = ball.speedy * -1;
                                //ball.speedy = ball.speedy * -1;
                            }
                            if (ball.speedx > 0) {

                                //ball.speedx = ball.speedx * -1;
                                ball.speedx = ball.speedx * -1;
                            }
                            if (ball.speedx < 0) {

                                ball.speedx = ball.speedx * -1;
                                //ball.speedy = ball.speedy * -1;
                            }
                        }

                        if (square.esquerda) {
                            if (chutar == true) {
                                ball.speedx = 10;
                                //ball.speedx += 0.02;
                                ball.speedx = -ball.speedx;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x - 1;
                                }
                            } else {
                                ball.speedx = 6;
                                //ball.speedx += 0.02;
                                ball.speedx = -ball.speedx;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x - 1;
                                }
                            }
                        }
                        if (square.direita) {
                            if (chutar == true) {
                                ball.speedx = 10;
                                //ball.speedx = ball.speedx*-1;
                                ball.x = ball.x + 1;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x + 1;
                                }

                            } else {
                                ball.speedx = 6;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x + 1;
                                }
                            }
                        }
                        if (square.cima) {
                            if (chutar == true) {
                                ball.speedy = 10;
                                //ball.speedx += 0.02;
                                ball.speedy = -ball.speedy;
                                if (ball.collisionW()) {
                                    ball.speedy = ball.speedy * -1;
                                    ball.y = ball.y - 1;
                                }
                            } else {
                                ball.speedy = 6;
                                //ball.speedx += 0.02;
                                ball.speedy = -ball.speedy;
                                if (ball.collisionW()) {
                                    ball.speedy = ball.speedy * -1;
                                    ball.y = ball.y - 1;
                                }
                            }

                        }
                        if (square.baixo) {
                            if (chutar == true) {
                                ball.speedy = 10;
                                //ball.speedx = ball.speedx*-1;
                                ball.y = ball.y + 1;

                            } else {
                                ball.speedy = 6;
                                if (ball.collisionW()) {
                                    ball.speedy = ball.speedy * -1;
                                    ball.y = ball.y + 1;
                                }
                            }

                        }

                    }
                }
                //fim da jogabilidade

                x = 0;
                y += 50;
            }
        }

        if (fase3 == true) {
            g2d.setColor(Color.yellow);
            int x = 0;
            int y = -15;

            for (int i = 0; i < this.level3.length; i++) { // linha
                for (int j = 0; j < this.level3[0].length; j++) { // coluna
                    if (this.level3[i][j] == 1) {
                        wall.x = x;
                        wall.y = y;
                        wall.desenha(g2d);

                    }
                    x += 50;

                    //começo da jogabilidade
                    if (square.collisionW()) {
                        if (square.direita) {
                            square.x = square.x - 6;
                        }
                        if (square.esquerda) {
                            square.x = square.x + 6;
                        }
                        if (square.baixo) {
                            square.y = square.y - 7;
                        }
                        if (square.cima) {
                            square.y = square.y + 7;
                        }
                    }

                    /*if (ball.collisionW()) {
                        ball.speedx = ball.speedx * -1;
                        ball.speedy = ball.speedy * -1;

                    }*/
                    if (ball.collisionW() && ball.speedy > 0) {
                        ball.y = ball.y - 4;
                        //ball.speedx = ball.speedx * -1;
                        ball.speedy = ball.speedy * -1;
                    }
                    if (ball.collisionW() && ball.speedy < 0) {
                        ball.y = ball.y + 4;
                        //ball.speedx = ball.speedx * -1;
                        ball.speedy = ball.speedy * -1;
                        //ball.speedy = ball.speedy * -1;
                    }
                    if (ball.collisionW() && ball.speedx > 0) {
                        ball.x = ball.x - 4;
                        //ball.speedx = ball.speedx * -1;
                        ball.speedx = ball.speedx * -1;
                    }
                    if (ball.collisionW() && ball.speedx < 0) {
                        ball.x = ball.x + 4;
                        ball.speedx = ball.speedx * -1;
                        //ball.speedy = ball.speedy * -1;
                    }

                    if (ball.collision()) {
                        if (square.parado) {
                            if (ball.speedy > 0) {

                                //ball.speedx = ball.speedx * -1;
                                ball.speedy = ball.speedy * -1;
                            }
                            if (ball.speedy < 0) {

                                //ball.speedx = ball.speedx * -1;
                                ball.speedy = ball.speedy * -1;
                                //ball.speedy = ball.speedy * -1;
                            }
                            if (ball.speedx > 0) {

                                //ball.speedx = ball.speedx * -1;
                                ball.speedx = ball.speedx * -1;
                            }
                            if (ball.speedx < 0) {

                                ball.speedx = ball.speedx * -1;
                                //ball.speedy = ball.speedy * -1;
                            }
                        }

                        if (square.esquerda) {
                            if (chutar == true) {
                                ball.speedx = 10;
                                //ball.speedx += 0.02;
                                ball.speedx = -ball.speedx;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x - 1;
                                }
                            } else {
                                ball.speedx = 6;
                                //ball.speedx += 0.02;
                                ball.speedx = -ball.speedx;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x - 1;
                                }
                            }
                        }
                        if (square.direita) {
                            if (chutar == true) {
                                ball.speedx = 10;
                                //ball.speedx = ball.speedx*-1;
                                ball.x = ball.x + 1;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x + 1;
                                }

                            } else {
                                ball.speedx = 6;
                                if (ball.collisionW()) {
                                    ball.speedx = ball.speedx * -1;
                                    ball.x = ball.x + 1;
                                }
                            }
                        }
                        if (square.cima) {
                            if (chutar == true) {
                                ball.speedy = 10;
                                //ball.speedx += 0.02;
                                ball.speedy = -ball.speedy;
                                if (ball.collisionW()) {
                                    ball.speedy = ball.speedy * -1;
                                    ball.y = ball.y - 1;
                                }
                            } else {
                                ball.speedy = 6;
                                //ball.speedx += 0.02;
                                ball.speedy = -ball.speedy;
                                if (ball.collisionW()) {
                                    ball.speedy = ball.speedy * -1;
                                    ball.y = ball.y - 1;
                                }
                            }

                        }
                        if (square.baixo) {
                            if (chutar == true) {
                                ball.speedy = 10;
                                //ball.speedx = ball.speedx*-1;
                                ball.y = ball.y + 1;

                            } else {
                                ball.speedy = 6;
                                if (ball.collisionW()) {
                                    ball.speedy = ball.speedy * -1;
                                    ball.y = ball.y + 1;
                                }
                            }

                        }

                    }
                }
                //fim da jogabilidade

                x = 0;
                y += 50;
            }
        }
    }

    public void salvar() throws IOException {    
        /*String linha = br.readLine();
        if (linha == null) {
            bw.write("1");
            bw.close();
        }

        while ((verify = br.readLine()) != null) {
            if (verify != null) {
                if (fase1 == true) {
                    if (verify != "2" && verify != "3") {
                        bw.write("1");
                        bw.close();
                    }
                }
                if (fase2 == true) {
                    if (verify != "3") {
                        bw.write("2");
                        bw.close();
                    }
                }
                if (fase3 == true) {
                    if (verify == "3" || verify == "2") {
                        bw.write("3");
                        bw.close();
                    }
                }
            }
        }*/
    }

    public void gameOver() {
        System.exit(ABORT);
    }
}
