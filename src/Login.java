import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import DB.DbOperate;

class Login extends JDialog implements ActionListener {

    static String[] s = new String[4];
    static String id,idPassword,money;

    private static final long serialVersionUID = 1L;
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();
    JLabel label = new JLabel("请输入卡号和密码:");
    JTextField jTextField = new JTextField(15);
    JPasswordField jpasswordField = new JPasswordField(15);
    JButton ok = new JButton("确定");
    JButton cancel = new JButton("取消");

    public Login() {
        setModal(true);
        setBackground(Color.LIGHT_GRAY);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(6, 1));
        //label.setFont(new Font("Sans Serif", Font.BOLD, 15));
        p2.add(label);
        p3.add(new JLabel("用户名:"));
        p3.add(jTextField);
        p4.add(new JLabel("密    码:"));
        p4.add(jpasswordField);
        p5.add(ok);
        p5.add(cancel);
        ok.addActionListener(this);
        cancel.addActionListener(this);
        jTextField.addActionListener(this);
        jpasswordField.addActionListener(this);
        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        contentPane.add(p4);
        contentPane.add(p5);
        contentPane.add(p6);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
        setTitle("登录窗口");
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok || e.getSource() == jpasswordField) {
            String password = new String(jpasswordField.getPassword());

            DbOperate dbOperate = new DbOperate();
            s = dbOperate.login(jTextField.getText().trim());
            if(s==null){
                JOptionPane.showMessageDialog(null, "用户未注册");
            }
            id = s[0].trim();
            idPassword = s[1].trim();
            money = s[2].trim();

            if ((jTextField.getText().trim().equals(id))
                    && (password.equals(idPassword))) {
                JOptionPane.showMessageDialog(null, "欢迎普通用户，登录成功!");
                dispose();
                new Menu();
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新登录！",
                        "警告", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (e.getSource() == cancel)
        {
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        Font font = new Font("JFrame", Font.PLAIN, 14);
        Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if (UIManager.get(key) instanceof Font) {
                UIManager.put(key, font);
            }
        }
        new Login();
    }

}