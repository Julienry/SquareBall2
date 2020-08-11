package squareball2;

import IA.AStar;
import IA.Node;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import java.util.Stack;
import javax.sound.sampled.LineUnavailableException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, LineUnavailableException {
        JFrame frame = new JFrame("Square and Ball 2");

        Jogo jogo = new Jogo();
        frame.add(jogo);
        frame.setSize(1115, 710);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        
        while (true) {
            jogo.move();
            jogo.repaint();
            Thread.sleep(10);
            
        }
    }
}
