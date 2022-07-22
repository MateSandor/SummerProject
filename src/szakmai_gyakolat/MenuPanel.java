package szakmai_gyakolat;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Picture.getPictures().getHeadBg(), 0, 0, this);           
    }
}

