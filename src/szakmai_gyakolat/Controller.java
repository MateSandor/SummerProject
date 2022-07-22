package szakmai_gyakolat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.FormatFlagsConversionMismatchException;

import javax.imageio.ImageIO;
import javax.script.ScriptContext;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.scilab.forge.jlatexmath.LaTeXAtom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.cache.JLaTeXMathCache;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import szakmai_gyakolat.NoneditablePanel;

public class Controller {
	public Controller() {}
	
	private static int  posX = 0, posY = 0;
	private static boolean selected = false;
	private static int checkedEditables;
	private static boolean selectedMax = false;
	private static int numberOfFile;
	
	/**
	 * Converts a String to TeXIcon then to BufferedImage
	 * @param a String
	 * @return BufferedImage
	 */
	public static BufferedImage texToImg(String input) {
		input = "\\textcolor{black}\\textbf{$$" + "\\lim_{n\\to\\infty}" + input +"$$}";
		TeXFormula formula = null;
		try {
			formula = new TeXFormula(input);
		} catch (org.scilab.forge.jlatexmath.ParseException pe) {
			JOptionPane.showMessageDialog(null, "Rossz bemenet", "LaTeX", 1);
		}
		TeXIcon ti = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		BufferedImage b = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);
		
		return b;
	}
	
	/**
	 * Creates a screenshot from the EditPanel and gives it BufferedImage type
	 * @param EditablePanel
	 * @return BufferedImage
	 */
	public static BufferedImage getScreenshot(EditablePanel panel) {
        panel.getChkBox().setVisible(false);
		BufferedImage bi = new BufferedImage(panel.getWidth(), 353, BufferedImage.TYPE_INT_ARGB);
        panel.paint(bi.getGraphics());
        panel.getChkBox().setVisible(true);
        
        return bi;
    }
	
	public static BufferedImage rotate45_ccw(BufferedImage inputImage) {
		double angle = -0.85521133348;
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = inputImage.getWidth();
	    int h = inputImage.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
	    GraphicsConfiguration gc = getDefaultConfiguration();
	    BufferedImage returnImage = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = returnImage.createGraphics();
	    g.translate((neww - w) / 2, (newh - h) / 2);
	    g.rotate(angle, w / 2, h / 2);
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //arra szolg�l, hogy ne legyen elmosott a sz�veg
	    g.drawRenderedImage(inputImage, null);
	    g.dispose();
	    return returnImage;
	}
	private static GraphicsConfiguration getDefaultConfiguration() {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    return gd.getDefaultConfiguration();
	}
	/**
	 * adds actionlistener to the textField of the EditPanel which convert its content to image then rotate it counter clockwise
	 * @param EditablePanel
	 */
	public static void setAction_to_textField_rotate(EditablePanel panel) {
		panel.getTextField().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.getLblTex().setIcon(new ImageIcon(rotate45_ccw(texToImg(panel.getTextField().getText()))));
			}
		});
	}
	
	/**
	 * adds actionlistener to the textField of the panel which convert its content to image
	 * @param EditablePanel
	 */
	public static void setAction_too_textField_noRotate(EditablePanel panel) {
		panel.getTextField().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.getLblTex().setIcon(new ImageIcon(texToImg(panel.getTextField().getText())));
			}
		});
	}
		
	/**
	 * select/deselect all checkboxes on EditPanel[]
	 * @param JButton
	 * @param EditPanel[]
	 */
	public static void check_uncheck_all(JButton button, EditablePanel[] panels) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected == false){
					for (int i = 0; i < panels.length; i++) {
						panels[i].getChkBox().setSelected(true);
						selected = true;
					}
					return;
				}
				if (selected == true){
					for (int i = 0; i < panels.length; i++) {
						panels[i].getChkBox().setSelected(false);
						selected = false;
					}
					return;
				}
			}
		});
	}
	
	/**
	 * Adds windowListener to HelpDialog
	 * @param HelpDialog
	 */
	public static void add_WindowListener_Help(HelpDialog dialog) {
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				dialog.setHelpDialogCreated(false);
		    }
		});
	}
	
	/**
	 * Adds windows listener to EditFrame and NoEditFrame
	 * @param JFrame
	 */
	public static void add_WindowListener_EditableFrame_NoneditableFrame(JFrame frame) {
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	StartFrame.getMyStartFrame().setVisible(true);
		    }
		});
	}
	
	/**
	 * Adds drag & drop option to StartFrame
	 * @param StartFrame
	 */
	public static void add_DragNdrop_Frame(StartFrame frame) {
		frame.addMouseListener(new MouseAdapter() {
		   public void mousePressed(MouseEvent e) {
		      posX = e.getX();
		      posY = e.getY();
		   }
		});
		frame.addMouseMotionListener(new MouseAdapter() {
		     public void mouseDragged(MouseEvent evt) {		
				frame.setLocation (evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);					
		     }
		});
	}
	
	/**
	 * Counts the checked elements of EditPanel[] then returns with the number 
	 * @param EditPanel[]
	 * @return int, number of checked EditPanel
	 */
	public static int countCheckedEditables(EditablePanel[] panels) {
		int imgCount = 0;
		for (int i = 0; i < panels.length; i++) {
			if (panels[i].getChkBox().isSelected() == true) {
				imgCount ++;
				checkedEditables++;
			}
		}
		return imgCount;
	}
	
	/**
	 * Counts the checked elements of NoEditPanel[] then returns with the number 
	 * @param NoEditPanel[]
	 * @return int, number of checked NoEditPanel
	 */
	public static int countCheckedNoneditables(NoneditablePanel[] panels) {
		int imgCount = 0;
		for (int i = 0; i < panels.length; i++) {
			if (panels[i].getChkBox().isSelected() == true) {
				imgCount ++;
			}
		}
		return imgCount;
	}
	
	/**
	 * Adds action performed to checkBox of NoEditPanel
	 * @param NoneditablePanel
	 */
	public static void setChkBoxActionToNoneditablePanel(NoneditablePanel panel) {
		panel.getChkBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.getChkBox().isSelected() == true){
					panel.getLblAmount().setVisible(true);
					panel.getComboBox().setVisible(true);
				}
				else {
					panel.getLblAmount().setVisible(false);
					panel.getComboBox().setVisible(false);
					panel.getComboBox().setSelectedItem(NoneditablePanel.getDefa());
				}
			}
		});
	}
	
	/**
	 * Sets actionperformed event to btnMax which select all NoEditPanel with maximum amount, on next click: deselect them and sets the amount to 0
	 * @param JButton
	 * @param NoEditPanel[]
	 */
	public static void setActiPerfToNoneditable_btnMax(JButton btn, NoneditablePanel[] panels) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedMax == false){
					for (int i = 0; i < panels.length; i++) {
						panels[i].getChkBox().setSelected(true);
						panels[i].getLblAmount().setVisible(true);
						panels[i].getComboBox().setVisible(true);
						panels[i].getComboBox().setSelectedIndex(panels[i].getComboBox().getItemCount() - 1);
						selectedMax = true;
					}
					return;
				}
				if (selectedMax == true){
					for (int i = 0; i < panels.length; i++) {
						panels[i].getChkBox().setSelected(false);
						panels[i].getLblAmount().setVisible(false);
						panels[i].getComboBox().setVisible(false);
						panels[i].getComboBox().setSelectedIndex(NoneditablePanel.getDefa());
						selectedMax = false;
					}
					return;
				}
			}
		});
	}
	
	/**
	 * Adds actionPerformed event to JButton that saves the selected Edited card fronts with backs to pdf
	 * @param JButton
	 * @param array of EditPanel
	 */
	public static void setActiPerfToSaveEditables(JButton button, EditablePanel[] panels) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int returnVal;
				boolean isFileExists;
				boolean isFileExists2;
				int chosen;
				JFileChooser fc = new JFileChooser();
				File file;
			
				if (if_notSelectedEditablePanel_and_clickSave(panels)) {
					return;
				}
				
				else if (EditableFrame.getMyEditableFrame().getJrbPress().isSelected()){
					fc.setFileFilter(new FileNameExtensionFilter("PDF (*.pdf)", "pdf")); 	
					returnVal = fc.showSaveDialog(EditableFrame.getMyEditableFrame());
					file = fc.getSelectedFile();
					isFileExists = new File(file +"_szerk._nyomda.pdf").exists();
					isFileExists2 = new File(file +"_szerk2._nyomda.pdf").exists();
					
					BufferedImage[] bufferedFronts1 = createAndFillSelectedEditableFronts_Press(panels);
					com.itextpdf.text.Image[] fronts1 = bufferedArrayToImage(bufferedFronts1);
					BufferedImage[] bufferedFronts2 = createAndFillSelectedEditableFronts2_Press(panels);
					com.itextpdf.text.Image[] fronts2 = bufferedArrayToImage(bufferedFronts2);
				
					if ((isFileExists || isFileExists2) && returnVal == JFileChooser.APPROVE_OPTION && file != null) {
						chosen = JOptionPane.showConfirmDialog(EditableFrame.getMyEditableFrame(), "Ilyen nev� f�jl m�r l�teszik. Szeretn�d fel�l�rni?","Figyelem!", JOptionPane.YES_NO_OPTION);
						if (chosen == JOptionPane.YES_OPTION) {
							fc.approveSelection();
							FileManager.writePdfPress(fronts1, file + "_szerk._nyomda");
							FileManager.writePdfPress(fronts2, file + "_szerk2._nyomda");
							JOptionPane.showMessageDialog(EditableFrame.getMyEditableFrame(), "Sikeres ment�s!", "Ut�irat", 1);
						}
					}
					else if (returnVal == JFileChooser.CANCEL_OPTION) {
						return;
					}
					else if (file != null) {
						FileManager.writePdfPress(fronts1, file + "_szerk._nyomda");
						FileManager.writePdfPress(fronts2, file + "_szerk2._nyomda");
						JOptionPane.showMessageDialog(EditableFrame.getMyEditableFrame(), "Sikeres ment�s!", "Ut�irat", 1);
					}
					
				}
			}
		});
	}
	
	/**
	 * Adds actionPerformed event to JButton that saves the selected NoEdit card fronts with backs to pdf
	 * @param JButton
	 * @param array of NoEditPanel
	 */
	public static void setActiPerfToNoneditable_btnSaveNoEdits(JButton btn, NoneditablePanel[] panels) {
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int returnVal;
				boolean isFileExists;
				int chosen;
				JFileChooser fc = new JFileChooser();
				File file;

				setSumOfNoneditables(panels);
				
				if (if_notSelectedNoneditablePanel_and_clickSave(panels)) {
					return;
				}
				
				else if (if_selectedNoneditablePanel_with_0sum_and_clickSave(panels)) {
					return;
				}
				
				
				else if (NoneditableFrame.getMyNoneditableFrame().getJrbPress().isSelected()) {
					fc.setFileFilter(new FileNameExtensionFilter("PDF (*.pdf)", "pdf")); 	
					returnVal = fc.showSaveDialog(NoneditableFrame.getMyNoneditableFrame());
					file = fc.getSelectedFile();
					isFileExists = new File(file +"_akcio_nyomda.pdf").exists();
					
					BufferedImage[] bufferedActions = createAndFillSelectedActions();
					BufferedImage[] bufferedTreasures = createAndFillSelectedBlues();

					if (isFileExists && returnVal == JFileChooser.APPROVE_OPTION && file != null){
						chosen = JOptionPane.showConfirmDialog(EditableFrame.getMyEditableFrame(), "Ilyen nev� f�jl m�r l�teszik. Szeretn�d fel�l�rni?","Figyelem!", JOptionPane.YES_NO_OPTION);
						if (chosen == JOptionPane.YES_OPTION) {
							fc.approveSelection();
							numberOfFile = 0;
							if (NoneditableFrame.getSumAction() > 0) {
								com.itextpdf.text.Image[] actions = bufferedArrayToImage(bufferedActions); 
								FileManager.writePdfPress(actions, file + "_akcio_nyomda");
								numberOfFile++;
							}
							if (NoneditableFrame.getSumBlue() > 0) {
								com.itextpdf.text.Image[] treasures = bufferedArrayToImage(bufferedTreasures); 
								FileManager.writePdfPress(treasures, file + "_szerencseproba_nyomda");
								numberOfFile++;
							}
							JOptionPane.showMessageDialog(EditableFrame.getMyEditableFrame(), "Sikeres ment�s!\n L�trehozott f�jl(ok) sz�ma: " + numberOfFile, "Ut�irat", 1);
						}
					}
					else if (returnVal == JFileChooser.CANCEL_OPTION) {
						return;
					}
					else if (file != null) {
						numberOfFile = 0;
						if (NoneditableFrame.getSumAction() > 0) {
							com.itextpdf.text.Image[] actions = bufferedArrayToImage(bufferedActions); 
							FileManager.writePdfPress(actions, file + "_akcio_nyomda");
							numberOfFile++;
						}
						if (NoneditableFrame.getSumBlue() > 0) {
							com.itextpdf.text.Image[] treasures = bufferedArrayToImage(bufferedTreasures); 
							FileManager.writePdfPress(treasures, file + "_szerencseproba_nyomda");
							numberOfFile++;
						}
						JOptionPane.showMessageDialog(EditableFrame.getMyEditableFrame(), "Sikeres ment�s!\n L�trehozott f�jl(ok) sz�ma: " + numberOfFile, "Ut�irat", 1);
					}
				}
			}
		});
	}
	
	/**
	 * Converts BufferedImage[] to com.itextpdf.text.Image[]
	 * @param array of BufferedImage
	 * @return array of com.itextpdf.text.Image
	 */
	public static com.itextpdf.text.Image[] bufferedArrayToImage(BufferedImage[] inputs) {
		com.itextpdf.text.Image[] output = new com.itextpdf.text.Image[inputs.length];
		try {
			for (int i = 0; i < inputs.length; i++) {
				output[i] = com.itextpdf.text.Image.getInstance(inputs[i], null);
			}
		} catch (BadElementException bee) {
			bee.getMessage();
		}
		catch (IOException ioe) {
			ioe.getMessage();
		}
		return output;
	}
	
	/**
	 * Adds the selected amount number to each type of cards red from comboBoxes of NoEditPanel[]
	 * @param array of NoEditPanel
 	 */
	public static void setSumOfNoneditables(NoneditablePanel[] panels) {
		for (int i = 0; i < panels.length; i++) {
			if (i == 0) NoneditableFrame.setClover((int)panels[i].getComboBox().getSelectedItem());
			if (i == 1) NoneditableFrame.setArrowDown((int)panels[i].getComboBox().getSelectedItem());
			if (i == 2) NoneditableFrame.setArrowUp((int)panels[i].getComboBox().getSelectedItem());
			if (i == 3) NoneditableFrame.setOneX((int)panels[i].getComboBox().getSelectedItem());
			if (i == 4) NoneditableFrame.setTwoX((int)panels[i].getComboBox().getSelectedItem());
			if (i == 5) NoneditableFrame.setMinusOne((int)panels[i].getComboBox().getSelectedItem());
			if (i == 6) NoneditableFrame.setPlusTwo((int)panels[i].getComboBox().getSelectedItem());
			if (i == 7) NoneditableFrame.setPlusThree((int)panels[i].getComboBox().getSelectedItem());
			if (i == 8) NoneditableFrame.setTurn((int)panels[i].getComboBox().getSelectedItem());			
			if (i == 9) NoneditableFrame.setBlue_1((int)panels[i].getComboBox().getSelectedItem());
			if (i == 10) NoneditableFrame.setBlue_2((int)panels[i].getComboBox().getSelectedItem());
			if (i == 11) NoneditableFrame.setBlue_3((int)panels[i].getComboBox().getSelectedItem());
			if (i == 12) NoneditableFrame.setBlue_4((int)panels[i].getComboBox().getSelectedItem());
		}		
	}
	
	/**
	 * Creates then fills then returns with BufferedImage[] which contains selected noeditable fronts
	 * @return array of BufferedImage which contains selected noeditable fronts
	 */
	public static BufferedImage[] createAndFillSelectedNoneditableFronts() {
		BufferedImage[] bufferedFronts = new BufferedImage[NoneditableFrame.getSumAll()];
		int idx = 0;
		for (int sy = 0; sy < NoneditableFrame.getClover(); sy++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[0]; idx++; }						
		for (int sn = 0; sn < NoneditableFrame.getArrowDown(); sn++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[1]; idx++; }
		for (int cy = 0; cy < NoneditableFrame.getArrowUp(); cy++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[2]; idx++; }
		for (int cn = 0; cn < NoneditableFrame.getOneX(); cn++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[3]; idx++; }
		for (int fy = 0; fy < NoneditableFrame.getTwoX(); fy++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[4]; idx++; }		
		for (int fn = 0; fn < NoneditableFrame.getMinusOne(); fn++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[5]; idx++; }	
		for (int soc = 0; soc < NoneditableFrame.getPlusTwo(); soc++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[6]; idx++; }	
		for (int sof = 0; sof < NoneditableFrame.getPlusThree(); sof++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[7]; idx++; }	
		for (int foc = 0; foc < NoneditableFrame.getTurn(); foc++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[8]; idx++; }
		for (int r1 = 0; r1 < NoneditableFrame.getBlue_1(); r1++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[9]; idx++; }	
		for (int r2 = 0; r2 < NoneditableFrame.getBlue_2(); r2++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[10]; idx++; }	
		for (int r3 = 0; r3 < NoneditableFrame.getBlue_3(); r3++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[11]; idx++; }	
		for (int r4 = 0; r4 < NoneditableFrame.getBlue_4(); r4++) { bufferedFronts[idx] = Picture.getPictures().getNoEdits()[12]; idx++; }	
		
		return bufferedFronts;
	}
	
	/**
	 * Creates then fills then returns with BufferedImage[] which contains selected noeditable Action fronts
	 * @return array of BufferedImage which contains selected noeditable Action fronts
	 */
	public static BufferedImage[] createAndFillSelectedActions() {
		BufferedImage[] bufferedActions = new BufferedImage[NoneditableFrame.getSumAction()+1];
		int idx = 0;
		if (NoneditableFrame.getSumAction() > 0) {
			for (int sy = 0; sy < NoneditableFrame.getClover(); sy++) { bufferedActions[idx] = Picture.getPictures().getNoEdits()[0]; idx++; }						
			for (int sn = 0; sn < NoneditableFrame.getArrowDown(); sn++) { bufferedActions[idx] = Picture.getPictures().getNoEdits()[1]; idx++; }
			for (int cy = 0; cy < NoneditableFrame.getArrowUp(); cy++) { bufferedActions[idx] = Picture.getPictures().getNoEdits()[2]; idx++; }
			for (int cn = 0; cn < NoneditableFrame.getOneX(); cn++) { bufferedActions[idx] = Picture.getPictures().getNoEdits()[3]; idx++; }
			for (int fy = 0; fy < NoneditableFrame.getTwoX(); fy++) { bufferedActions[idx] = Picture.getPictures().getNoEdits()[4]; idx++; }		
			for (int fn = 0; fn < NoneditableFrame.getMinusOne(); fn++) { bufferedActions[idx] = Picture.getPictures().getNoEdits()[5]; idx++; }	
			for (int soc = 0; soc < NoneditableFrame.getPlusTwo(); soc++) { bufferedActions[idx] = Picture.getPictures().getNoEdits()[6]; idx++; }	
			for (int sof = 0; sof < NoneditableFrame.getPlusThree(); sof++) { bufferedActions[idx] = Picture.getPictures().getNoEdits()[7]; idx++; }	
			for (int foc = 0; foc < NoneditableFrame.getTurn(); foc++) { bufferedActions[idx] = Picture.getPictures().getNoEdits()[8]; idx++; }
		

			bufferedActions[idx] = Picture.getPictures().getBackLila_press();
		}
		
		return bufferedActions;
	}
	
	/**
	 * Creates then fills then returns with BufferedImage[] which contains selected noeditable Treasure fronts
	 * @return array of BufferedImage which contains selected noeditable Treasure fronts
	 */
	public static BufferedImage[] createAndFillSelectedBlues() {
		BufferedImage[] bufferedTreasures = new BufferedImage[NoneditableFrame.getSumBlue()+1];
		int idx = 0;
		if (NoneditableFrame.getSumBlue() > 0) {
			for (int r1 = 0; r1 < NoneditableFrame.getBlue_1(); r1++) { bufferedTreasures[idx] = Picture.getPictures().getNoEdits()[9]; idx++; }	
			for (int r2 = 0; r2 < NoneditableFrame.getBlue_2(); r2++) { bufferedTreasures[idx] = Picture.getPictures().getNoEdits()[10]; idx++; }	
			for (int r3 = 0; r3 < NoneditableFrame.getBlue_3(); r3++) { bufferedTreasures[idx] = Picture.getPictures().getNoEdits()[11]; idx++; }	
			for (int r4 = 0; r4 < NoneditableFrame.getBlue_4(); r4++) { bufferedTreasures[idx] = Picture.getPictures().getNoEdits()[12]; idx++; }
			bufferedTreasures[idx] = Picture.getPictures().getBackFekete_press();
		}
		return bufferedTreasures;
	}
	
	/**
	 * Displays a message when 0 EditPanel selected and click save button
	 * @param EditPanel[] array
	 * @return true when 0 selected, false when at least 1 selected
	 */
	public static boolean if_notSelectedEditablePanel_and_clickSave(EditablePanel[] panels) {
		if (countCheckedEditables(panels) == 0) {
			JOptionPane.showMessageDialog(NoneditableFrame.getMyNoneditableFrame(), "Nincs kijel�lt lap.", "Figyelem!", 1);
			return true;
		}
		return false;
	}
	
	/**
	 * Displays a message when 0 NoEditPanel selected and click save button
	 * @param NoEditPanel[] array
	 * @return true when 0 selected, false when at least 1 selected
	 */
	public static boolean if_notSelectedNoneditablePanel_and_clickSave(NoneditablePanel[] panels) {
		if (countCheckedNoneditables(panels) == 0) {
			JOptionPane.showMessageDialog(NoneditableFrame.getMyNoneditableFrame(), "Nincs kijel�lt lap.", "Figyelem!", 1);
			return true;
		}
		return false;
	}
	
	/**
	 * Displays a message when the selected amount is 0 and click save button
	 * @param NoEditPanel[] array
	 * @return true when  selected amount is 0, false when the selected amount is minimum 1
	 */
	public static boolean if_selectedNoneditablePanel_with_0sum_and_clickSave(NoneditablePanel[] panels) {
		if (NoneditableFrame.getSumAll() == 0) {
			JOptionPane.showMessageDialog(NoneditableFrame.getMyNoneditableFrame(), "�sszes mennyis�g = 0.", "Figyelem!", 1);
			return true;
		}
		return false;
	}
	
	/**
	 * Creates then fills then returns with BufferedImage[] which contains selected editable fronts (A4 version)
	 * @param EditPanel[]
	 * @return array of BufferedImage which contains selected editable fronts
	 */
	public static BufferedImage[] createAndFillSelectedEditableFronts_A4(EditablePanel[] panels) {
		BufferedImage[] bufferedFronts = new BufferedImage[countCheckedEditables(panels)];
		int idx = 0;
		for (int i = 0; i < panels.length; i++)
			if (panels[i].getChkBox().isSelected() == true) {
				bufferedFronts[idx] = getScreenshot(panels[i]);
				idx++;	
			}
		return bufferedFronts;
	}
	
	/**
	 * Creates then fills then returns with BufferedImage[] which contains selected editable fronts (Press version)
	 * @param EditPanel[]
	 * @return array of BufferedImage which contains selected editable fronts
	 */
	public static BufferedImage[] createAndFillSelectedEditableFronts_Press(EditablePanel[] panels) {
		BufferedImage[] bufferedFronts = new BufferedImage[80+1];
		int idx = 0;
		for (int i = 0; i < 80; i++)
			if (panels[i].getChkBox().isSelected() == true) {
				bufferedFronts[idx] = getScreenshot(panels[i]);
				idx++;	
			}
		bufferedFronts[idx] = Picture.getPictures().getBackLila_press();
		return bufferedFronts;
	}
	public static BufferedImage[] createAndFillSelectedEditableFronts2_Press(EditablePanel[] panels) {
		BufferedImage[] bufferedFronts = new BufferedImage[40+1]; //countCheckedEditables(panels)+1
		int idx = 0;
		for (int i = 80; i < 120; i++)
			if (panels[i].getChkBox().isSelected() == true) {
				bufferedFronts[idx] = getScreenshot(panels[i]);
				idx++;	
			}
		bufferedFronts[idx] = Picture.getPictures().getBackTurkiz_press();
		return bufferedFronts;
	}
	
	
}
