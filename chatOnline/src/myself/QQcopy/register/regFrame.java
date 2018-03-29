package myself.QQcopy.register;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.*;

import myself.QQcopy.util.checkUser;

public class regFrame {
    private String userName;
    private String passWord;
    private String repassWord;
    private char[] passValue;
    private char[] repassValue;
    private JLabel userNameLabel = new JLabel("�û���: ");
    private JLabel passWordLabel = new JLabel("����:");
    private JLabel repassWordLabel = new JLabel("ȷ������");

    public regFrame(JDialog owner) {
        JTextField text = new JTextField();
        JPasswordField pwd = new JPasswordField();
        JPasswordField rePwd = new JPasswordField();
        JDialog regDialog = new JDialog(owner, "ע��");
        JPanel all = new JPanel();
        JPanel button = new JPanel();
        JButton submit = new JButton("�ύ");

        text.setBounds(130,20,150,30);
        pwd.setBounds(130,50,150,30);
        rePwd.setBounds(130,80,150,30);
        userNameLabel.setBounds(70,20,60,30);
        passWordLabel.setBounds(70,50,60,30);
        repassWordLabel.setBounds(70,80,60,30);


        submit.addActionListener(event ->
                {
                    this.userName = text.getText();
                    this.passValue = pwd.getPassword();
                    this.repassValue = rePwd.getPassword();
                    this.passWord = new String(passValue);
                    this.repassWord = new String(repassValue);

                    if (!userName.equals("") && !passWord.equals("") && !repassWord.equals("") && !checkUser.isExistInMySQL(userName)) {
                        if (passWord.equals(repassWord)) {
                            regDialog.setVisible(false);
                            //���ӱ������ݿ�,����ע��
                            //storeToMySQL(userName, passWord, new Timestamp(System.currentTimeMillis()));
                        } else new regAlertDialog(regDialog, "��ȷ����������").setVisible(true);
                    } else new regAlertDialog(regDialog, "�û��Ѵ��ڻ��û�������Ϊ��").setVisible(true);
                }
        );
        //text.setPreferredSize(new Dimension(300, 30));
        all.add(userNameLabel);
        all.add(text);
        all.add(passWordLabel);
        all.add(pwd);
        all.add(repassWordLabel);
        all.add(rePwd);
        all.setLayout(null);
        button.add(submit);

        regDialog.add(button, BorderLayout.SOUTH);
        regDialog.add(all, BorderLayout.CENTER);
        regDialog.setVisible(true);
        regDialog.setSize(400, 200);
        regDialog.setLocation(550, 350);
    }

    public void storeToMySQL(String name, String password, Timestamp stamp) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //�������ݿ�
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qqcopy?useSSL=true", "root", "*******");
            String sqlDDL = "insert into userData(username,userpassword,regtime) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sqlDDL);

            name = name.trim();
            password = password.trim();
            ps.setObject(1, name);
            ps.setObject(2, password);
            ps.setObject(3, stamp);
            ps.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
