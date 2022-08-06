package szakmai_gyakolat;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoneditablePanel extends JPanel {
	
	private static int defaultAmount = 0;
	private static Integer[] maxElement;
	
	private JLabel lblImage;
	private JCheckBox chkBox;
	private JComboBox amount;
	private JLabel lblAmount;
	
	public NoneditablePanel(int inputMaxElement) {
		
		maxElement = new Integer[inputMaxElement];
		for(int i = 0; i <maxElement.length; i++) {
			maxElement[i] = i+1;
		}
		
		amount = new JComboBox(maxElement);
		
		this.setSize(227, 379);
		this.setLayout(null);
		
		//JLabel for card image
		lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 227, 353);
		lblImage.setVisible(true);
		this.add(lblImage);
		
		//JCheckBox for selection
		chkBox = new JCheckBox("");
		chkBox.setBounds(-4,-1,17,14);;
		chkBox.setVisible(true);
		lblImage.add(chkBox);
		
		//JLabel for "db" label
		lblAmount = new JLabel("db:");
		lblAmount.setBounds(60, 354, 25, 25);
		lblAmount.setVisible(false);
		this.add(lblAmount);
		
		amount.setBounds(93, 354, 41, 25); // magical numbers by the other programmer
		amount.setVisible(false);
		this.add(amount);
	}

	public JLabel getLblImage() {
		return lblImage;
	}
	
	public void setLblImage(JLabel lblImage) {
		this.lblImage = lblImage;
	}
	
	public JCheckBox getChkBox() {
		return chkBox;
	}
	
	public void setChkBox(JCheckBox chkBox) {
		this.chkBox = chkBox;
	}
	
	public JComboBox getComboBox() {
		return amount;
	}
	
	public void setComboBox(JComboBox comboBox) {
		this.amount = comboBox;
	}
	
	public JLabel getLblAmount() {
		return lblAmount;
	}
	
	public static int getDefa() {
		return defaultAmount;
	}	
}
