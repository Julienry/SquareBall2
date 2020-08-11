package squareball2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TelaInicial {
    private BufferedImage tela;    
    private BufferedImage seta;    
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 671;
    private static final int WIDTHs = 75;
    private static final int HEIGHTs = 40;
    
    //private int largura;
    //private int altura;    
    private Jogo jogo;
    private int x = 0;
    private int y = 0;
    public int xs = 645;
    public int ys = 445;
    public TelaInicial(Jogo jogo) throws IOException {
        seta = ImageIO.read(new File("seta.bmp"));
        tela = ImageIO.read(new File("telainicial2.bmp"));
        this.x = x;
        this.y = y;
        this.xs = xs;
        this.ys = ys;
        this.jogo = jogo;
    }
    public TelaInicial(Jogo jogo, BufferedImage tela){
        this.jogo = jogo;
        this.tela = tela;
        this.seta = seta;
    }
    public void desenha(Graphics2D g) {
        g.drawImage(this.tela, x, y, this.WIDTH, this.HEIGHT, null);

    }
     public void desenhaSeta(Graphics2D g) {
            g.drawImage(this.seta, xs, ys, this.WIDTHs, this.HEIGHTs, null);
    }
}
