package myself.QQcopy.user;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import myself.QQcopy.util.closeUtil;

public class MyFrame extends JFrame {
    public static final int TEXTAREA_ROWS = 8;
    public static final int TEXTAREA_COLUMNS = 20;
    private JTextField userName = new JTextField();
    private JTextField textField = new JTextField();
    private JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
    private String msg = ": ";
    private File localChat;

    //private Socket socket = new Socket();

    public MyFrame(Socket socket) {

        JLabel usernameLabel = new JLabel("您的名字: ");
        JLabel userMsgLabel = new JLabel("请输入您的信息:");

        this.setSize(800, 600);
        this.setLocation(283, 84);
        Dialog dialog = new Dialog(this);
        dialog.setVisible(true);
        while (dialog.getUsername().equals("") || dialog.getUsername() == null) {
            AlertDialog alert = new AlertDialog(dialog);
            alert.setVisible(true);
            dialog.setVisible(true);
        }


        userName.setText(dialog.getUsername());
        userName.setBounds(100, 450, 150, 30);
        usernameLabel.setBounds(40, 450, 80, 30);
        userMsgLabel.setBounds(280,450,100,30);
        textField.setBounds(380,450,300,30);

        //添加本地聊天记录
        String local = "D:/QQcopy" + this.userName.getText() + ".txt";
        localChat = new File(local);
        createLocalChat();
        if (("D:/QQcopy" + this.userName.getText() + ".txt").equals(local)) {
            addLocalChat();
        }

        userName.setEnabled(false);

        //this.socket = socket;
        //JTextArea Name = new JTextArea();
        JPanel jPanel = new JPanel();
        //聊天显示界面
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton insertButton = new JButton("发送");

        scrollPane.setBounds(100,50,580,350);
        insertButton.setBounds(700,455,60,20);
        insertButton.setFocusable(false);

        jPanel.setLayout(null);
        jPanel.add(usernameLabel);
        jPanel.add(userMsgLabel);
        jPanel.add(userName);
        jPanel.add(textField);
        jPanel.add(scrollPane);
        jPanel.add(insertButton);
        textArea.setEditable(false);

        add(jPanel);

        textField.addKeyListener(new KeyAdapter() {
                                     public void keyPressed(KeyEvent e) {
                                         if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                             msg = getmyself();
                                             textArea.append(msg + "\r\n");
                                             new sendIO(socket, msg).send();
                                             textField.setText("");
                                         }
                                     }

                                     ;
                                 }
        );

        //发送聊天内容
        insertButton.addActionListener(event ->
        {
            this.msg = getmyself();
            textArea.append(msg + "\r\n");
            new sendIO(socket, msg).send();
            textField.setText("");
        });

        //关闭窗口时记录聊天内容到本地
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                writeToLocalChat(textArea.getText());
                System.exit(0);
            }
        });
    }

    public void setMsg(String msg) {
        textArea.append(msg + "\n");
    }

    public String getmyself() {
        if (userName.getText() != null || !userName.getText().equals(""))
            return userName.getText() + ": " + textField.getText();
        return null;
    }

    private void createLocalChat() {
        if (!localChat.exists()) {
            try {
                localChat.createNewFile();
            } catch (IOException e1) {
                System.out.println("本地聊天记录创建失败");
            }
        }
    }

    public void writeToLocalChat(String msg) {
        try {
            BufferedWriter wt = new BufferedWriter(new FileWriter(localChat));

            wt.write(msg);
            wt.flush();
            wt.close();
        } catch (IOException e) {
        }
    }

    private void addLocalChat() {
        if (localChat.exists()) {
            try {
                char[] flush = new char[1024];
                BufferedReader rd = new BufferedReader(new FileReader(localChat));
                int len = 0;

                while (-1 != (len = rd.read(flush))) {
                    this.textArea.append(new String(flush, 0, len));
                }
                rd.close();
            } catch (IOException e) {
            }
        }
    }

    private class sendIO {
        private DataOutputStream dos;
        private String console;

        public sendIO(Socket socket, String mg) {

            try {
                this.console = new String(mg);
                dos = new DataOutputStream(socket.getOutputStream());
            } catch (IOException a) {
                closeUtil.closeAll(dos);
            }
        }


        public void send() {
            String msg = new String(console);
            try {
                if (null != msg && !msg.equals("")) {
                    dos.writeUTF(msg);
                    dos.flush();
                }
            } catch (IOException e) {
                closeUtil.closeAll(dos);
            }
        }

    }
}
