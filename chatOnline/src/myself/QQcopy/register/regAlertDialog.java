package myself.QQcopy.register;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class regAlertDialog extends JDialog
{
	public regAlertDialog(JDialog owner, String wrongWay)
	{
		super(owner, "ERROR!", true);
		

		this.setLocation(600, 384);
		this.setSize(300, 100);
		add(new JLabel(wrongWay), BorderLayout.CENTER);
		
	}
}
