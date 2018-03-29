package myself.QQcopy.user;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class AlertDialog extends JDialog
{
	public AlertDialog(JDialog owner)
	{
		super(owner, "ERROR!", true);
		

		this.setLocation(600, 384);
		this.setSize(300, 100);
		add(new JLabel("用户不存在或者用户名与密码错误!"), BorderLayout.CENTER);
		
	}
}
