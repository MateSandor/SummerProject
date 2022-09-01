package szakmai_gyakolat;

import java.awt.Graphics;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Picture.getPictures().getHeadBg(), 0, 0, this);           
    }
}

