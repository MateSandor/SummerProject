package szakmai_gyakolat;

import java.awt.Graphics;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
  
	@Override
	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
		g.drawImage(Picture.getPictures().getStartBg(), 0, 0, this);
	}
}