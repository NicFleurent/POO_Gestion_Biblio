package pkSwing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame {
	
	private JFrame frame;
	private JPanel panel;
	private JTextField textField;
	private JLabel label;
	
	public MainFrame() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		this.frame.setTitle("JTextField Demo");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setSize(800, 500);
		frame.setLayout(new BorderLayout(10, 10));
		this.frame.setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon("mugi.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		frame.setIconImage(icon.getImage());
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//-------- Start of JTextField -----------------
		textField = createJTextField();
		panel.add(textField);
		
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER);
		
	}

	private JTextField createJTextField() {
		JTextField textField = new JTextField(10);
		
		textField.setFont(new Font("Arial", Font.BOLD, 24));
		
		textField.setForeground(Color.BLUE);
		
		textField.setBackground(Color.YELLOW);
		
		textField.setToolTipText("Enter some text");
		
		textField.setMargin(new Insets(5, 10, 5, 10));
		
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				label.setText(textField.getText());
				textField.setText("Enter some more text");
				
			}
			
		});
		
		return textField;
	}

	public void show() {
		this.frame.setVisible(true);
	}

}
