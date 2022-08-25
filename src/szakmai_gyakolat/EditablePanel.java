package szakmai_gyakolat;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditablePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblImage;
	private JLabel lblTex;
	private JTextField textField;
	private JCheckBox chkBox;
	
//	private JLabel lblTexNorth;
//	private JLabel lblTexEast;
//	private JLabel lblTexSouth;
//	private JLabel lblTexWest;
//	private String[] directions = { "Észak", "Kelet", "Dél", "Nyugat" };
//	private JComboBox directionList;
	
	
	
	public EditablePanel() {
		
		//setSize(227, 379); // Other dev's magical number
		setSize(675,1050);
		setLayout(null);
		//setBackground(new Color(255, 212, 179));
		
		//JLabel for card
		lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 675, 1050);
		lblImage.setVisible(true);
		this.add(lblImage);
		
		
		//JLabel for LaTeX of Path cards
		lblTex = new JLabel();
		lblTex.setVisible(true);
		lblTex.setHorizontalAlignment(SwingConstants.CENTER);
		lblTex.setVerticalAlignment(SwingConstants.CENTER);
		lblImage.add(lblTex);
		
		//JTextField for input
		textField = new JTextField();
		textField.setBounds(0, 1050, 675, 25);
		textField.setVisible(true);
		this.add(textField);
			
		//JCheckBox for select
		chkBox = new JCheckBox("");
		chkBox.setBounds(-4,-1,17,14);
		chkBox.setVisible(true);
		chkBox.setFocusable(false);
		lblImage.add(chkBox);
	}
		
		/* This feature was removed earlier
		
		//JLabel for LaTeX of Start card (North)
		lblTexNorth = new JLabel();
		lblTexNorth.setBounds(227/3, 12, 227/3+5, 123);
		lblTexNorth.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexNorth.setVerticalAlignment(SwingConstants.TOP);
		lblTexNorth.setVisible(false);
		lblImage.add(lblTexNorth);
		
		//JLabel for LaTeX of Start card (East)
		lblTexEast = new JLabel();
		lblTexEast.setBounds(227/2, 135, 227/2-12, 82);
		lblTexEast.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTexEast.setVerticalAlignment(SwingConstants.CENTER);
		lblTexEast.setVisible(false);
		lblImage.add(lblTexEast);
		
		//JLabel for LaTeX of Start card (South)
		lblTexSouth = new JLabel();
		lblTexSouth.setBounds(227/3, 217, 227/3+5, 124);
		lblTexSouth.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexSouth.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTexSouth.setVisible(false);
		lblImage.add(lblTexSouth);
		
		//JLabel for LaTeX of Start card (West)
		lblTexWest = new JLabel();
		lblTexWest.setBounds(12, 135, 227/2-12, 82);
		lblTexWest.setHorizontalAlignment(SwingConstants.LEFT);
		lblTexWest.setVerticalAlignment(SwingConstants.CENTER);
		lblTexWest.setVisible(false);
		lblImage.add(lblTexWest);
		
		//JComboBox for select directions
		directionList = new JComboBox(directions);
		directionList.setSelectedItem(directions[0]);
		directionList.setBounds(162, 354, 65, 25);
		directionList.setVisible(false);
		this.add(directionList);
	}
	
	public JLabel getLblTexNorth() {
		return lblTexNorth;
	}

	public void setLblTexNorth(JLabel lblTexNorth) {
		this.lblTexNorth = lblTexNorth;
	}


	public JLabel getLblTexEast() {
		return lblTexEast;
	}

	public void setLblTexEast(JLabel lblTexEast) {
		this.lblTexEast = lblTexEast;
	}

	public JLabel getLblTexSouth() {
		return lblTexSouth;
	}

	public void setLblTexSouth(JLabel lblTexSouth) {
		this.lblTexSouth = lblTexSouth;
	}

	public JLabel getLblTexWest() {
		return lblTexWest;
	}

	public void setLblTexWest(JLabel lblTexWest) {
		this.lblTexWest = lblTexWest;
	}

	
	public String[] getDirections() {
		return directions;
	}

	public void setDirections(String[] directions) {
		this.directions = directions;
	}

	public JComboBox getDirectionsList() {
		return directionList;
	}

	public void setDirectionsList(JComboBox directionsList) {
		this.directionList = directionsList;
	}

*/
	
	public JLabel getLblImage() {
		return lblImage;
	}
	
	public void setLblImage(JLabel lblImage) {
		this.lblImage = lblImage;
	}
	
	public JLabel getLblTex() {
		return lblTex;
	}
	
	public void setLblTex(JLabel lblTex) {
		this.lblTex = lblTex;
	}
	
	public JTextField getTextField() {
		return textField;
	}
	
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
	public JCheckBox getChkBox() {
		return chkBox;
	}
	
	public void setChkBox(JCheckBox chkBox) {
		this.chkBox = chkBox;
	}


}

	