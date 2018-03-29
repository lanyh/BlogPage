package myself.QQcopy.user;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import myself.QQcopy.register.regFrame;
import myself.QQcopy.util.checkUser;


public class Dialog extends JDialog
{
	private String username;
	private char[] passvalue;
	private String password;
	
	public Dialog(JFrame owner)
	{
		super(owner, "µÇÂ½ÁÄÌì ", true);
		
		this.setLocation(600, 384);
		this.setSize(300, 130);
		
		JPanel name = new JPanel();
		JTextField text = new JTextField();
		text.setPreferredSize(new Dimension(110, 50));
		JPasswordField pwd = new JPasswordField();
		name.add(new JLabel("ÓÃ»§Ãû: ", SwingConstants.RIGHT));
		name.add(text);
		name.add(new JLabel("ÃÜÂë: ", SwingConstants.RIGHT));
		name.add(pwd);
		name.setLayout(new GridLayout(2, 2));
		
		add(name, BorderLayout.LINE_START);
		JButton login = new JButton("µÇÂ½");
		JButton regist = new JButton("×¢²á");
		text.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				passvalue = pwd.getPassword();
				password = new String (passvalue);
				if(e.getKeyCode() == KeyEvent.VK_ENTER && checkUser.check(text.getText(), password))
				{
					setVisible(false);username = text.getText();
				}
			}
		});
		pwd.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				passvalue = pwd.getPassword();
				password = new String (passvalue);
				if(e.getKeyCode() == KeyEvent.VK_ENTER && checkUser.check(text.getText(), password))
				{
					setVisible(false);username = text.getText();
				}
			}
		});
		login.addActionListener(event ->
		{
			passvalue = pwd.getPassword();
			password = new String (passvalue);
			if(checkUser.check(text.getText(), password))
				{setVisible(false);username = text.getText();}
			else new AlertDialog(this).setVisible(true);
		});
		regist.addActionListener(event -> 
		{
			new regFrame(this);
		});
		
		JPanel button = new JPanel();
		button.add(login);
		button.add(regist);
		button.setLayout(new GridLayout(1, 1));
		add(button, BorderLayout.SOUTH);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	
	public String getUsername()
	{
		return username;
	}
}
