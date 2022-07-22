package szakmai_gyakolat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Picture {
	
	private static final int numberOfEditable = 5;
	private static final int numberOfNoneditable = 13;
	
	private static Picture pictures;
	
	private BufferedImage[] editables = new BufferedImage[numberOfEditable];
	private BufferedImage[] nonEditables = new BufferedImage[numberOfNoneditable];
	private BufferedImage btnBg;
	private BufferedImage editorBg;
	private BufferedImage startBg;
	private BufferedImage headBg;
	private BufferedImage help;
	private BufferedImage inform;

	private BufferedImage backLila_press;
	private BufferedImage backTurkiz_press;
	private BufferedImage backFekete_press;
	
	public Picture() {

		try {
			
			for (int i = 0; i < editables.length; i++) {
				editables[i] = ImageIO.read(getClass().getClassLoader().getResource("path" + i + ".jpg"));
			}
			for (int i = 0; i < nonEditables.length; i++) {
				nonEditables[i] = ImageIO.read(getClass().getClassLoader().getResource("noEdit"+ i +".jpg"));
			}
			
			btnBg = ImageIO.read(getClass().getClassLoader().getResource("startPanelButtons.jpg"));
			editorBg = ImageIO.read(getClass().getClassLoader().getResource("oldRotated.jpg"));
			startBg = ImageIO.read(getClass().getClassLoader().getResource("startPanel.jpg"));
			headBg = ImageIO.read(getClass().getClassLoader().getResource("head.jpg"));
			help = ImageIO.read(getClass().getClassLoader().getResource("help.jpg"));

			
			backFekete_press = ImageIO.read(getClass().getClassLoader().getResource("backFekete_press.jpg"));
			backLila_press = ImageIO.read(getClass().getClassLoader().getResource("backLila_press.jpg"));
			backTurkiz_press = ImageIO.read(getClass().getClassLoader().getResource("backTurkiz_press.jpg"));
			
			
		} catch (IOException ioe) { 
			JOptionPane.showMessageDialog(null, "Rossz bemenet", "imgRead", 0);
		}

	}
	
	public static void setPictures(Picture pictures) {
		Picture.pictures = pictures;
	}
	
	public static Picture getPictures() {
		return pictures;
	}
	
	public static int getNumberOfNoneditable() {
		return numberOfNoneditable;
	}

	public BufferedImage getBtnBg() {
		return btnBg;
	}
	
	public BufferedImage getBigPaperBg() {
		return editorBg;
	}
	
	public BufferedImage getStartBg() {
		return startBg;
	}
	
	public BufferedImage getHeadBg() {
		return headBg;
	}
	
	public BufferedImage[] getEditables() {
		return editables;
	}
	
	public BufferedImage getHelp() {
		return help;
	}
	
	public BufferedImage getInform() {
		return inform;
	}

	public BufferedImage[] getNoEdits() {
		return nonEditables;
	}

	public BufferedImage getBackLila_press() {
		return backLila_press;
	}
	
	public BufferedImage getBackTurkiz_press() {
		return backTurkiz_press;
	}
	
	public BufferedImage getBackFekete_press() {
		return backFekete_press;
	}
	
	
}
