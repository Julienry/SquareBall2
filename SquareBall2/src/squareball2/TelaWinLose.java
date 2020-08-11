package squareball2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TelaWinLose {

    private BufferedImage win;
    private BufferedImage lose;
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 671;

    private Jogo jogo;
    private int x = 0;
    private int y = 0;

    public TelaWinLose(Jogo jogo) throws IOException {
        win = ImageIO.read(new File("imgvitoria.bmp"));
        lose = ImageIO.read(new File("imgderrota.bmp"));
        this.x = x;
        this.y = y;

        this.jogo = jogo;
    }

    public TelaWinLose(Jogo jogo, BufferedImage win) {
        this.jogo = jogo;
        this.win = win;
        this.lose = lose;
    }

    public void desenhaWin(Graphics2D g) {
        g.drawImage(this.win, x, y, this.WIDTH, this.HEIGHT, null);

    }

    public void desenhaLose(Graphics2D g) {
        g.drawImage(this.lose, x, y, this.WIDTH, this.HEIGHT, null);
    }
}
