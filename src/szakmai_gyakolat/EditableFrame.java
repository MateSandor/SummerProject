package szakmai_gyakolat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.itextpdf.text.Image;
import com.itextpdf.text.log.SysoCounter;

import szakmai_gyakolat.EditablePanel;

import javax.swing.SwingConstants;

public class EditableFrame extends JFrame {
	
	private static EditableFrame myEditableFrame;
	
	private static final int numberOfEditablePanel = 120;
	private BufferedImage buttonsBg;
	private static JButton buttonHelp;
	private EditablePanel[] editablePanels;
	//private JRadioButton jrbA4;
	private JRadioButton jrbPrint;
	
	public EditableFrame() {
		
		setPreferredSize(new Dimension(1020, 574));
		setMinimumSize(new Dimension(300, 450));
		setTitle("Lapszerkesztő");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		Controller.add_WindowListener_EditableFrame_NoneditableFrame(this);
		
		//Colored background panel
		PaperPanel fixPanel = new PaperPanel();
		fixPanel.setPreferredSize(new Dimension(978, 11810));
		getContentPane().add(fixPanel);
		
		//Scrollpane for fixpanel
		JScrollPane scrollPane = new JScrollPane(fixPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
		getContentPane().add(scrollPane);
		
		//the 41 panels lie on it
		JPanel bigPanel = new JPanel();
		bigPanel.setBackground(Color.cyan);
		bigPanel.setPreferredSize(new Dimension(978, 11800));
		bigPanel.setLayout(null);
		bigPanel.setOpaque(false);
		fixPanel.add(bigPanel);
		
		//Colored menu panel
		MenuPanel menuPanel = new MenuPanel();
		menuPanel.setBackground(Color.yellow);
		menuPanel.setPreferredSize(new Dimension(50,40));
		getContentPane().add(menuPanel, BorderLayout.NORTH);
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		//Check/uncheck all button
		JButton btnChkAll = new JButton("Összes kijelölése", new ImageIcon(Picture.getPictures().getBtnBg()));
		btnChkAll.setForeground(Color.WHITE);
		btnChkAll.setFont(StartFrame.getBtnFont());
		btnChkAll.setHorizontalTextPosition(JButton.CENTER);
		btnChkAll.setVerticalTextPosition(JButton.CENTER);
		btnChkAll.setPreferredSize(new Dimension(140, 33));
		menuPanel.add(btnChkAll, FlowLayout.LEFT);
		
		//Save as pdf button
		JButton btnSavePathFront = new JButton("Kijelöltek mentése", new ImageIcon(Picture.getPictures().getBtnBg()));
		btnSavePathFront.setForeground(Color.WHITE);
		btnSavePathFront.setFont(StartFrame.getBtnFont());
		btnSavePathFront.setHorizontalTextPosition(JButton.CENTER);
		btnSavePathFront.setVerticalTextPosition(JButton.CENTER);
		btnSavePathFront.setPreferredSize(new Dimension(160, 33));
		menuPanel.add(btnSavePathFront);
		
		/* Removing jrb completely
		
		//JRadio Panel
		JButton jrbBtn =  new JButton("" ,new ImageIcon(Picture.getPictures().getBtnBg()));
		jrbBtn.setPreferredSize(new Dimension(100, 33));
		jrbBtn.setLayout(null);
		jrbBtn.setOpaque(false);
		menuPanel.add(jrbBtn);
		
		//JRadioButton
		jrbPrint = new JRadioButton("Nyomda");
		jrbPrint.setFont(StartFrame.getBtnFont());
		jrbPrint.setForeground(Color.WHITE);
		jrbPrint.setBounds(0, 6, 100, 20);
		jrbPrint.setOpaque(false);
		jrbBtn.add(jrbPrint);
		 */
		
		//Help button
		buttonHelp = new JButton("Segítség!", new ImageIcon(Picture.getPictures().getBtnBg()));
		buttonHelp.setOpaque(false);
		buttonHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (HelpDialog.isHelpDialogCreated() == false) {
					HelpDialog.setHelpDialog(new HelpDialog());
					HelpDialog.getHelpDialog().setVisible(true);
					HelpDialog.setHelpDialogCreated(true);	
				}
				else{
					return;
				}
			}
		});
		buttonHelp.setForeground(Color.WHITE);
		buttonHelp.setFont(StartFrame.getBtnFont());
		buttonHelp.setHorizontalTextPosition(JButton.CENTER);
		buttonHelp.setVerticalTextPosition(JButton.CENTER);
		buttonHelp.setPreferredSize(new Dimension(140, 33));
		menuPanel.add(buttonHelp);
		
		//Path cards label on bigPanel
		JLabel lblPaths = new JLabel("Lapok szerkesztése");
		lblPaths.setBounds(389, 0, 210, 80);
		lblPaths.setFont(StartFrame.getNoEditListFont());
		lblPaths.setHorizontalAlignment(JLabel.CENTER);
		lblPaths.setVerticalAlignment(JLabel.CENTER);
		bigPanel.add(lblPaths);

		//creates an array for the 41 panels
		editablePanels = new EditablePanel[numberOfEditablePanel];
		
		//fills the array
		for (int i = 0; i < editablePanels.length; i++) {
			editablePanels[i] = new EditablePanel();
		}
		
		//positions and adds the 40 panels from the array (4x10)
		
		int countedEditablePanel = 0;
		int k = 0;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 4 && countedEditablePanel < 120; j++) {
				editablePanels[k].setLocation(20 + j*237, 80 + i*389);
				bigPanel.add(editablePanels[k]);
				k++;
				countedEditablePanel++;
			}
		}

		
		//Panel decoration
		for (int i = 0; i < editablePanels.length; i++) {
			editablePanels[i].setOpaque(false);
		}
		
		//JTextField decoration
		for (int i = 0; i < editablePanels.length; i++) {
			editablePanels[i].getTextField().setOpaque(false);
			editablePanels[i].getTextField().setFont(new Font("Consolas", Font.BOLD, 14));
			editablePanels[i].getTextField().setUI(new HintTextFieldUI("Ide írj...", true));
		}
		
		//sets actionevent to the textfields of the panels
		for (int i = 0; i < 120; i++) {
			if (i >= 0 && i < 120) Controller.setAction_to_textField_rotate(editablePanels[i]);
		}
	
		//adds images to the imageLabel of the 41 panels
		for (int i = 0; i < 20; i++) {
			editablePanels[i].getLblImage().setIcon(new ImageIcon(Picture.getPictures().getEditables()[0])); 
		}
		for (int i = 20; i < 40; i++) {
			editablePanels[i].getLblImage().setIcon(new ImageIcon(Picture.getPictures().getEditables()[1])); 
		}
		for (int i = 40; i < 60; i++) {
			editablePanels[i].getLblImage().setIcon(new ImageIcon(Picture.getPictures().getEditables()[2])); 
		}
		for (int i = 60; i < 80; i++) {
			editablePanels[i].getLblImage().setIcon(new ImageIcon(Picture.getPictures().getEditables()[3])); 
		}
		for (int i = 80; i < 120; i++) {
			editablePanels[i].getLblImage().setIcon(new ImageIcon(Picture.getPictures().getEditables()[4])); 
		}
			
			//sets bounds of label for LaTeX (0-17)
			for (int i = 0 ; i < 120; i++) {
				editablePanels[i].getLblTex().setBounds(12, 12, 210, 329); // 227/3, 12, 227/3 + 5, 329 volt
			}

			Controller.check_uncheck_all(btnChkAll, editablePanels);
			Controller.setActiPerfToSaveEditables(btnSavePathFront, editablePanels);
			
	}
	
	public static JButton getBtnHelp() {
		return buttonHelp;
	}
	
	public static EditableFrame getMyEditableFrame() {
		return myEditableFrame;
	}

	public static void setMyEditableFrame(EditableFrame myEditableFrame) {
		EditableFrame.myEditableFrame = myEditableFrame;
	}

	public EditablePanel[] getEditPanels() {
		return editablePanels;
	}
	
	public JRadioButton getJrbPress() {
		return jrbPrint;
	}
	
}
