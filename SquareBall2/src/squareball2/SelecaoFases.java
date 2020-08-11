package squareball2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SelecaoFases {
    private BufferedImage telaS;    
    private BufferedImage seta;
    private BufferedImage fase1;
    private BufferedImage fase2;
    private BufferedImage fase3;
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 671;
    private static final int WIDTHs = 75;
    private static final int HEIGHTs = 40;
    private static final int WIDTHf = 210;
    private static final int HEIGHTf = 90;
    
    private Jogo jogo;
    public int x = 0;
    public int y = 0;

    public SelecaoFases(Jogo jogo) throws IOException {
        seta = ImageIO.read(new File("seta.bmp"));
        telaS = ImageIO.read(new File("selecaofases.bmp"));
        fase1 = ImageIO.read(new File("fase1.bmp"));
        fase2 = ImageIO.read(new File("fase2.bmp"));
        fase3 = ImageIO.read(new File("fase3.bmp"));
        this.x = x;
        this.y = y;
        this.jogo = jogo;
    }
    public SelecaoFases(Jogo jogo, BufferedImage telaS){
        this.jogo = jogo;
        this.telaS = telaS;
        this.seta = seta;
        this.fase1 = fase1;
        this.fase2 = fase2;
        this.fase3 = fase3;
        this.x =x;
        this.y = y;
    }
    
    public void desenha(Graphics2D g) {
        g.drawImage(this.telaS, 0, 0, this.WIDTH, this.HEIGHT, null);
    }
    public void desenhaSeta(Graphics2D g) {
        g.drawImage(this.seta, x, y, this.WIDTHs, this.HEIGHTs, null);
    }
    public void desenhaFase1(Graphics2D g) {
        g.drawImage(this.fase1, 450, 100, this.WIDTHf, this.HEIGHTf, null);
    }
    public void desenhaFase2(Graphics2D g) {
        g.drawImage(this.fase2, 450, 300, this.WIDTHf, this.HEIGHTf, null);
    }
    public void desenhaFase3(Graphics2D g) {
        g.drawImage(this.fase3, 450, 500, this.WIDTHf, this.HEIGHTf, null);
    }
}
