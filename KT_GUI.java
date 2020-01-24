import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class KT_GUI implements ActionListener{

	private JLabel lblkT = new JLabel("KNIGHT'S TOUR");
	private JLabel lblboardSize = new JLabel("Enter the size of the Chessboard:");
	private JSpinner numboardSize;
	private JRadioButton rbWD = new JRadioButton("Warnsdorf Algorithm");
	private JRadioButton rbBT = new JRadioButton("Backtracking Algorithm");
	private JButton btnStart = new JButton("START TOUR");
	private JFrame mainDialog;
	
	KT_GUI ()
	{
		mainDialog = new JFrame("Knight's Tour");
		mainDialog.setResizable(false);
		mainDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainDialog.setSize(427,320);
		
		mainDialog.add(lblkT);
		
		mainDialog.add(lblboardSize);
		
		SpinnerNumberModel limit = new SpinnerNumberModel(8,1,40,1);  
		numboardSize = new JSpinner(limit);
		mainDialog.add(numboardSize);
		
		
		
		ButtonGroup bOrW = new ButtonGroup();
		bOrW.add(rbBT); bOrW.add(rbWD);
		mainDialog.add(rbWD);	mainDialog.add(rbBT);
		
		mainDialog.add(btnStart);
		
		lblkT.setBounds(150,30, 200, 10);
		lblboardSize.setBounds(40,75,240,10);
		numboardSize.setBounds(240,72, 50, 20);
		rbBT.setBounds(220, 120, 160, 30);
		rbWD.setBounds(50, 120, 160, 30);
		btnStart.setBounds(150,210, 120, 30);
		
		btnStart.addActionListener(this);
		mainDialog.setLayout(null);
		mainDialog.setVisible(true);
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new KT_GUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int boardSize = (int)numboardSize.getValue();
		
		if(rbWD.isSelected())
		{
			new KT_Warnsdorf_GUI(boardSize);
		}	
		else if(rbBT.isSelected())
		{
			new KT_Backtracking_GUI(boardSize);
		}
	}

}
