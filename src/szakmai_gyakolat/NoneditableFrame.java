package szakmai_gyakolat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class NoneditableFrame extends JFrame {

	private static NoneditableFrame myNoEditFrame;
	private JRadioButton jrbA4;
	private JRadioButton jrbPrint;
	
	private static int clover = 0;
	private static int arrowDown = 0;
	private static int arrowUp = 0;
	private static int oneX = 0;
	private static int twoX = 0;
	private static int minusOne = 0;
	private static int plusTwo = 0;
	private static int plusThree = 0;
	private static int turn = 0;
	
	private static int blue_1 = 0;
	private static int blue_2 = 0;
	private static int blue_3 = 0;
	private static int blue_4 = 0;
	
	public NoneditableFrame() {
		
		setPreferredSize(new Dimension(1020, 574));
		setMinimumSize(new Dimension(300, 450));
		setTitle("További lapok");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		Controller.add_WindowListener_EditableFrame_NoneditableFrame(this);
		
		//JPanel for the 20 NoneditablePanel
		JPanel bigPanel = new JPanel();
		bigPanel.setPreferredSize(new Dimension(978, 1690));
		bigPanel.setBackground(Color.white);
		bigPanel.setLayout(null);
		bigPanel.setOpaque(false);
		
		//Colored background panel
		PaperPanel fixPanel = new PaperPanel();
		fixPanel.setPreferredSize(new Dimension(978,1700));
		getContentPane().add(fixPanel);
				
		//JScrollPane for fixPanel
		JScrollPane scrollPane = new JScrollPane(fixPanel);
		scrollPane.setVisible(true);
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
		fixPanel.add(bigPanel);
		getContentPane().add(scrollPane);
		
		//MenuPanel for the menu
		MenuPanel menuPanel = new MenuPanel();
		menuPanel.setPreferredSize(new Dimension(50,40));
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		getContentPane().add(menuPanel, BorderLayout.NORTH);
		
		//Action cards label for bigPanel
		JLabel lblActions = new JLabel("Akciólapok");
		lblActions.setBounds(389, 0, 200, 80);
		lblActions.setFont(StartFrame.getNoEditListFont());
		lblActions.setHorizontalAlignment(JLabel.CENTER);
		lblActions.setVerticalAlignment(JLabel.CENTER);
		bigPanel.add(lblActions);

		//Check/uncheck all button for menuPanel
		JButton btnMax = new JButton("Teljes mennyiség", new ImageIcon(Picture.getPictures().getBtnBg()));
		btnMax.setForeground(Color.WHITE);
		btnMax.setFont(StartFrame.getBtnFont());
		btnMax.setHorizontalTextPosition(JButton.CENTER);
		btnMax.setVerticalTextPosition(JButton.CENTER);
		btnMax.setPreferredSize(new Dimension(160, 33));
		menuPanel.add(btnMax, FlowLayout.LEFT);
		
		//Save as pdf button for menuPanel
		JButton btnSaveNoEdits = new JButton("Jelöltek mentése", new ImageIcon(Picture.getPictures().getBtnBg()));
		btnSaveNoEdits.setForeground(Color.WHITE);
		btnSaveNoEdits.setFont(StartFrame.getBtnFont());
		btnSaveNoEdits.setHorizontalTextPosition(JButton.CENTER);
		btnSaveNoEdits.setVerticalTextPosition(JButton.CENTER);
		btnSaveNoEdits.setPreferredSize(new Dimension(160, 33));
		menuPanel.add(btnSaveNoEdits);
		
		/* removing jrb dependencies
		
		//Button panel for 2 JRadioButtons 
		JButton jrbBtn =  new JButton("" ,new ImageIcon(Picture.getPictures().getBtnBg()));
		jrbBtn.setPreferredSize(new Dimension(100, 33));
		jrbBtn.setLayout(null);
		jrbBtn.setOpaque(false);
		menuPanel.add(jrbBtn);

		//JRBBUTTON
		jrbPrint = new JRadioButton("Nyomda");
		jrbPrint.setFont(StartFrame.getBtnFont());
		jrbPrint.setForeground(Color.WHITE);
		jrbPrint.setBounds(0, 6, 100, 20);
		jrbPrint.setOpaque(false);
		jrbBtn.add(jrbPrint);
		*/
		NoneditablePanel[] noneditablePanels = new NoneditablePanel[Picture.getNumberOfNoneditable()];
		
		//Fills the array
		for(int i = 0; i < noneditablePanels.length; i++) {
			if (i == 0) {
				noneditablePanels[i] = new NoneditablePanel(5);
			}
			else if (i == 1 || i == 2) {
				noneditablePanels[i] = new NoneditablePanel(5);
			}
			else if (i == 3 || i == 4) {
				noneditablePanels[i] = new NoneditablePanel(5);
			}
			else if (i == 5 || i == 6 || i == 7 || i == 8) {
				noneditablePanels[i] = new NoneditablePanel(5);
			}
			else if (i == 9 || i == 10 || i == 11 || i == 12 ) {
				noneditablePanels[i] = new NoneditablePanel(5);
			}
		}
		
		//Panel decoration
		for (int i = 0; i < noneditablePanels.length; i++) {
			noneditablePanels[i].setOpaque(false);
		}
		
		//Adds images to the lblImage of panels
		for (int i = 0; i < noneditablePanels.length; i++) {
			noneditablePanels[i].getLblImage().setIcon(new ImageIcon(Picture.getPictures().getNoEdits()[i]));
		}
		
		//Adds actionPerformed to all NoEditPanels
		for (int i = 0; i < noneditablePanels.length; i++) {
			Controller.setChkBoxActionToNoneditablePanel(noneditablePanels[i]);
		}
			
		//Locates and adds action cards to bigPanel
		noneditablePanels[0].setLocation(20, 80); bigPanel.add(noneditablePanels[0]);
		noneditablePanels[1].setLocation(20+237, 80); bigPanel.add(noneditablePanels[1]);
		noneditablePanels[2].setLocation(20+2*237, 80); bigPanel.add(noneditablePanels[2]);
		noneditablePanels[3].setLocation(20+3*237, 80); bigPanel.add(noneditablePanels[3]);
		noneditablePanels[4].setLocation(20, 80+389); bigPanel.add(noneditablePanels[4]);
		noneditablePanels[5].setLocation(20+237, 80+389); bigPanel.add(noneditablePanels[5]);
		noneditablePanels[6].setLocation(20+2*237, 80+389); bigPanel.add(noneditablePanels[6]);
		noneditablePanels[7].setLocation(20+3*237, 80+389); bigPanel.add(noneditablePanels[7]);
		noneditablePanels[8].setLocation(376, 80+2*389); bigPanel.add(noneditablePanels[8]);
		
		//Locates and adds cards with blue bg to bigPanel
		noneditablePanels[9].setLocation(20, 80+3*389); bigPanel.add(noneditablePanels[9]);
		noneditablePanels[10].setLocation(20+237, 80+3*389); bigPanel.add(noneditablePanels[10]);
		noneditablePanels[11].setLocation(20+2*237, 80+3*389); bigPanel.add(noneditablePanels[11]);
		noneditablePanels[12].setLocation(20+3*237, 80+3*389); bigPanel.add(noneditablePanels[12]);
	
		//Adds action event to btnMax
		Controller.setActiPerfToNoneditable_btnMax(btnMax, noneditablePanels);
		
		//Adds action event to btnSaveNoEdits
		Controller.setActiPerfToNoneditable_btnSaveNoEdits(btnSaveNoEdits, noneditablePanels);
	}

	public static NoneditableFrame getMyNoneditableFrame() {
		return myNoEditFrame;
	}

	public static void setMyNoneditableFrame(NoneditableFrame myNoneditableFrame) {
		NoneditableFrame.myNoEditFrame = myNoneditableFrame;
	}

	public static int getClover() {
		return clover;
	}

	public static void setClover(int clover) {
		NoneditableFrame.clover = clover;
	}

	public static int getArrowDown() {
		return arrowDown;
	}

	public static void setArrowDown(int arrowDown) {
		NoneditableFrame.arrowDown = arrowDown;
	}

	public static int getArrowUp() {
		return arrowUp;
	}

	public static void setArrowUp(int arrowUp) {
		NoneditableFrame.arrowUp = arrowUp;
	}

	public static int getOneX() {
		return oneX;
	}

	public static void setOneX(int oneX) {
		NoneditableFrame.oneX = oneX;
	}

	public static int getTwoX() {
		return twoX;
	}

	public static void setTwoX(int twoX) {
		NoneditableFrame.twoX = twoX;
	}

	public static int getMinusOne() {
		return minusOne;
	}

	public static void setMinusOne(int minusOne) {
		NoneditableFrame.minusOne = minusOne;
	}

	public static int getPlusTwo() {
		return plusTwo;
	}

	public static void setPlusTwo(int plusTwo) {
		NoneditableFrame.plusTwo = plusTwo;
	}

	public static int getPlusThree() {
		return plusThree;
	}

	public static void setPlusThree(int plusThree) {
		NoneditableFrame.plusThree = plusThree;
	}

	public static int getTurn() {
		return turn;
	}

	public static void setTurn(int turn) {
		NoneditableFrame.turn = turn;
	}


	public static int getSumAction() {
		return clover + arrowDown + arrowUp + oneX + twoX + minusOne + plusTwo + plusThree + turn;
	}

	public static int getBlue_1() {
		return blue_1;
	}

	public static void setBlue_1(int blue_1) {
		NoneditableFrame.blue_1 = blue_1;
	}

	public static int getBlue_2() {
		return blue_2;
	}

	public static void setBlue_2(int blue_2) {
		NoneditableFrame.blue_2 = blue_2;
	}

	public static int getBlue_3() {
		return blue_3;
	}

	public static void setBlue_3(int blue_3) {
		NoneditableFrame.blue_3 = blue_3;
	}
	
	public static int getBlue_4() {
		return blue_4;
	}

	public static void setBlue_4(int blue_4) {
		NoneditableFrame.blue_4 = blue_4;
	}

	public static int getSumBlue() {
		return blue_1 + blue_2 + blue_3 + blue_4;
	}

	public static int getSumAll() {
		return getSumAction() + getSumBlue();
	}

	public JRadioButton getJrbA4() {
		return jrbA4;
	}

	public JRadioButton getJrbPress() {
		return jrbPrint;
	}
}
