package szakmai_gyakolat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class InformDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static InformDialog informDialog;

	public InformDialog() {
		
		super(StartFrame.getMyStartFrame(), "Tájékoztató", ModalityType.APPLICATION_MODAL);
		
		setSize(560,490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(StartFrame.getMyStartFrame());
		setResizable(false);
	
		//MenuPanel for the menu
		MenuPanel menuPanel = new MenuPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(menuPanel, BorderLayout.SOUTH);
		
		//JButton for the "Ok" button
		JButton okButton = new JButton("Ok", new ImageIcon(Picture.getPictures().getBtnBg()));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setPreferredSize(new Dimension(60, 30));
		okButton.setForeground(Color.WHITE);
		okButton.setFont(StartFrame.getBtnFont());
		okButton.setHorizontalTextPosition(JButton.CENTER);
		okButton.setVerticalTextPosition(JButton.CENTER);
		menuPanel.add(okButton);
		
		//JLabel for the Inform image
		JLabel lblInformImage = new JLabel("");
		lblInformImage.setLocation(0, 0);
		lblInformImage.setPreferredSize(new Dimension(536, 1020));
		lblInformImage.setIcon(new ImageIcon(Picture.getPictures().getInform()));
		lblInformImage.setVerticalAlignment(JLabel.TOP);
		add(lblInformImage);
		
		//JScrollPane for the JLabel
		JScrollPane scrollInform = new JScrollPane(lblInformImage);
		scrollInform.getVerticalScrollBar().setUnitIncrement(30);
		getContentPane().add(scrollInform);
	}

	public static InformDialog getInformDialog() {
		return informDialog;
	}

	public static void setInformDialog(InformDialog informDialog) {
		InformDialog.informDialog = informDialog;
	}
}
