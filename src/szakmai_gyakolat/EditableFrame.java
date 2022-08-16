package szakmai_gyakolat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.ToolTipUI;
import javax.swing.plaf.basic.BasicToolTipUI;
import javax.swing.JButton;

public class EditableFrame extends JFrame {
	
	private static EditableFrame myEditableFrame;
	
	private static final int numberOfEditablePanel = 80;
	private BufferedImage buttonsBg;
	private EditablePanel[] editablePanels;
	//private JRadioButton jrbA4;
	//private JRadioButton jrbPrint;
	
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
		fixPanel.setPreferredSize(new Dimension(978,(int) Math.floor(98.416*numberOfEditablePanel))); //Size was determined by other programmer, I converted it to function with numberOfEditablePanel
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
		
		//Save to txt button
		JButton buttonSaveAsTxt = new JButton("TXT mentés", new ImageIcon(Picture.getPictures().getBtnBg()));
		buttonSaveAsTxt.setForeground(Color.WHITE);
		buttonSaveAsTxt.setFont(StartFrame.getBtnFont());
		buttonSaveAsTxt.setHorizontalTextPosition(JButton.CENTER);
		buttonSaveAsTxt.setVerticalTextPosition(JButton.CENTER);
		buttonSaveAsTxt.setPreferredSize(new Dimension(140, 33));
		buttonSaveAsTxt.setToolTipText("Ide kattintva le tudja tölteni az eddig szerkesztett kártyákat txt formában.");
		menuPanel.add(buttonSaveAsTxt, FlowLayout.LEFT);
		
		//Load from txt button
		JButton buttonLoadTxt = new JButton("TXT betöltés", new ImageIcon(Picture.getPictures().getBtnBg()));
		buttonLoadTxt.setForeground(Color.WHITE);
		buttonLoadTxt.setFont(StartFrame.getBtnFont());
		buttonLoadTxt.setHorizontalTextPosition(JButton.CENTER);
		buttonLoadTxt.setVerticalTextPosition(JButton.CENTER);
		buttonLoadTxt.setPreferredSize(new Dimension(140, 33));
		buttonLoadTxt.setToolTipText("Ide kattintva fel tudja tölteni egy .txt fájlban szerkesztett káryták szövegeit");
		menuPanel.add(buttonLoadTxt);
		
		//Check/uncheck all button
		JButton buttonCheckAll = new JButton("Összes kijelölése", new ImageIcon(Picture.getPictures().getBtnBg()));
		buttonCheckAll.setForeground(Color.WHITE);
		buttonCheckAll.setFont(StartFrame.getBtnFont());
		buttonCheckAll.setHorizontalTextPosition(JButton.CENTER);
		buttonCheckAll.setVerticalTextPosition(JButton.CENTER);
		buttonCheckAll.setPreferredSize(new Dimension(140, 33));
		menuPanel.add(buttonCheckAll);
		
		//Save as PDP button
		JButton buttonSaveAsPdf = new JButton("Kijelöltek mentése", new ImageIcon(Picture.getPictures().getBtnBg()));
		buttonSaveAsPdf.setForeground(Color.WHITE);
		buttonSaveAsPdf.setFont(StartFrame.getBtnFont());
		buttonSaveAsPdf.setHorizontalTextPosition(JButton.CENTER);
		buttonSaveAsPdf.setVerticalTextPosition(JButton.CENTER);
		buttonSaveAsPdf.setPreferredSize(new Dimension(160, 33));
		menuPanel.add(buttonSaveAsPdf);
		
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
		JButton buttonHelp = new JButton("Segítség!", new ImageIcon(Picture.getPictures().getBtnBg()));
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
		
		//positions and adds the panels from the array (4 x numberOfEditablePanel)
		
		int countedEditablePanel = 0;
		int k = 0;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 4 && countedEditablePanel < numberOfEditablePanel; j++) {
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
			editablePanels[i].getTextField().setUI(new HintTextFieldUI("Szöveg helye", true));
			editablePanels[i].getTextField().setToolTipText("Enter lenyomásával jelenik meg a kártyán a szöveg.");
		}
		
		//sets actionevent to the textfields of the panels
		for (int i = 0; i < numberOfEditablePanel; i++) {
			if (i >= 0 && i < numberOfEditablePanel) Controller.setAction_to_textField_rotate(editablePanels[i]);
		}
	
		//adds images to the imageLabel of the 41 panels
		for (int i = 0; i < 20; i++) {
			editablePanels[i].getLblImage().setIcon(new ImageIcon((Picture.getPictures().getEditables()[0]).getScaledInstance(227, 353, Image.SCALE_SMOOTH))); 
		}
		for (int i = 20; i < 40; i++) {
			editablePanels[i].getLblImage().setIcon(new ImageIcon((Picture.getPictures().getEditables()[1]).getScaledInstance(227, 353, Image.SCALE_SMOOTH))); 
		}
		for (int i = 40; i < 60; i++) {
			editablePanels[i].getLblImage().setIcon(new ImageIcon((Picture.getPictures().getEditables()[2]).getScaledInstance(227, 353, Image.SCALE_SMOOTH))); 
		}
		for (int i = 60; i < 80; i++) {
			editablePanels[i].getLblImage().setIcon(new ImageIcon((Picture.getPictures().getEditables()[3]).getScaledInstance(227, 353, Image.SCALE_SMOOTH)));
		}
		//for (int i = 80; i < 120; i++) {
		//	editablePanels[i].getLblImage().setIcon(new ImageIcon(Picture.getPictures().getEditables()[4])); 
		//}
			
		//sets bounds of label for LaTeX (0-17)
		for (int i = 0 ; i < numberOfEditablePanel; i++) {
			editablePanels[i].getLblTex().setBounds(12, 12, 210, 329); // 227/3, 12, 227/3 + 5, 329 volt
		}

		Controller.check_uncheck_all(buttonCheckAll, editablePanels);
		Controller.setActiPerfToSaveEditables(buttonSaveAsPdf, editablePanels);
		Controller.saveAsTxt(buttonSaveAsTxt, editablePanels);
		Controller.loadFromTxt(buttonLoadTxt, editablePanels);
			
	}
	
	//public static JButton getBtnHelp() {
	//	return buttonHelp;
	//}
	
	public static EditableFrame getMyEditableFrame() {
		return myEditableFrame;
	}

	public static void setMyEditableFrame(EditableFrame myEditableFrame) {
		EditableFrame.myEditableFrame = myEditableFrame;
	}

	public EditablePanel[] getEditPanels() {
		return editablePanels;
	}
	
	//public JRadioButton getJrbPress() {
	//	return jrbPrint;
	//}
	
}
