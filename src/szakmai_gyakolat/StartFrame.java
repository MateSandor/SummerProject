package szakmai_gyakolat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import szakmai_gyakolat.EditableFrame;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;

public class StartFrame extends JFrame {
	
	private static StartFrame myStartFrame;
	
	private BufferedImage buttonsBg;	
	private JPanel contentPane;
	
	private static Font btnFont = new Font("Lucida Calligraphy", Font.BOLD, 12);
	private static Font noEditListFont = new Font("Lucida Calligraphy", Font.BOLD, 20);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Picture.setPictures(new Picture());
					
					myStartFrame = new StartFrame();
					myStartFrame.setVisible(true);
					
					EditableFrame.setMyEditableFrame(new EditableFrame());
					EditableFrame.getMyEditableFrame().setVisible(false);
					
					NoneditableFrame.setMyNoneditableFrame(new NoneditableFrame());
					NoneditableFrame.getMyNoneditableFrame().setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public StartFrame() {
		setUndecorated(true);
		setSize(350, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 229, 204));
		Controller.add_DragNdrop_Frame(this);
		
		//Background
		StartPanel startPanel = new StartPanel();
		getContentPane().add(startPanel);
		startPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		startPanel.setLayout(null);
		
		//Editable cards option
		JButton btnEditCards = new JButton("Lapszerkesztés", new ImageIcon(Picture.getPictures().getBtnBg()));
		btnEditCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EditableFrame.getMyEditableFrame().setVisible(true);
			}
		});
		btnEditCards.setForeground(Color.WHITE);
		btnEditCards.setFont(btnFont);
		btnEditCards.setBounds(80, 160, 190, 40);
		btnEditCards.setHorizontalTextPosition(JButton.CENTER);
		btnEditCards.setVerticalTextPosition(JButton.CENTER);
		startPanel.add(btnEditCards);
		
		//Noneditable cards option
		JButton btnNoEdit = new JButton("További lapok",new ImageIcon(Picture.getPictures().getBtnBg()));
		btnNoEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				NoneditableFrame.getMyNoneditableFrame().setVisible(true);	
			}
		});
		btnNoEdit.setForeground(Color.WHITE);
		btnNoEdit.setFont(btnFont);
		btnNoEdit.setHorizontalTextPosition(JButton.CENTER);
		btnNoEdit.setVerticalTextPosition(JButton.CENTER);
		btnNoEdit.setBounds(80, 210, 190, 40);
		startPanel.add(btnNoEdit);
		
		//Exit option
		JButton btnExit = new JButton("Kilépés",new ImageIcon(Picture.getPictures().getBtnBg()));
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(btnFont);
		btnExit.setHorizontalTextPosition(JButton.CENTER);
		btnExit.setVerticalTextPosition(JButton.CENTER);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(80, 337, 190, 40);
		startPanel.add(btnExit);
	}

	public static StartFrame getMyStartFrame() {
		return myStartFrame;
	}
	public static void setMyStartFrame(StartFrame frame) {
		StartFrame.myStartFrame = frame;
	}
	public static Font getBtnFont() {
		return btnFont;
	}

	public static Font getNoEditListFont() {
		return noEditListFont;
	}
}
