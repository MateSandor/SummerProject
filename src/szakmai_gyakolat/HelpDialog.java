package szakmai_gyakolat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpDialog extends JDialog {

	private static HelpDialog helpDialog;
	
	private final JPanel contentPanel = new JPanel();
	private static boolean isHelpDialogCreated = false;

	public HelpDialog() {
		
		super(EditableFrame.getMyEditableFrame(), "Segítség", ModalityType.MODELESS);
		
		setSize(660, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setResizable(false);
		Controller.add_WindowListener_Help(this);	
		
		//MenuPanel for the menu
		MenuPanel menuPanel = new MenuPanel();
		menuPanel.setPreferredSize(new Dimension(550, 40));
		menuPanel.setBackground(Color.yellow);
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(menuPanel, BorderLayout.SOUTH);
				
		//JButton for "Ok" button
		JButton okButton = new JButton("Ok", new ImageIcon(Picture.getPictures().getBtnBg()));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setHelpDialogCreated(false);
			}
		});
		okButton.setPreferredSize(new Dimension(60, 30));
		okButton.setForeground(Color.WHITE);
		okButton.setFont(StartFrame.getBtnFont());
		okButton.setHorizontalTextPosition(JButton.CENTER);
		okButton.setVerticalTextPosition(JButton.CENTER);
		menuPanel.add(okButton);
			
		//JLabel for the Help image
		JLabel lblHelpImage = new JLabel("");
		lblHelpImage.setLocation(0, 0);
		lblHelpImage.setPreferredSize(new Dimension(600, 880));
		lblHelpImage.setIcon(new ImageIcon(Picture.getPictures().getHelp()));
		lblHelpImage.setVerticalAlignment(JLabel.TOP);
				
		//JScrollPane for the JLabel
		JScrollPane scrollHelpP = new JScrollPane(lblHelpImage);
		scrollHelpP.getVerticalScrollBar().setUnitIncrement(30);
		getContentPane().add(scrollHelpP);		
	}
	
	public static HelpDialog getHelpDialog() {
		return helpDialog;
	}

	public static boolean isHelpDialogCreated() {
		return isHelpDialogCreated;
	}
	public static void setHelpDialogCreated(boolean helpDialogCreated) {
		HelpDialog.isHelpDialogCreated = helpDialogCreated;
	}
	public static void setHelpDialog(HelpDialog helpDialog) {
		HelpDialog.helpDialog = helpDialog;
	}
}
