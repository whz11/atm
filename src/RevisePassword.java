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

class RevisePassword extends JDialog implements ActionListener {



    private static final long serialVersionUID = 1L;
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();
    JLabel label = new JLabel("请输入旧密码和新密码：");
    JPasswordField txtPwd1 = new JPasswordField(15);
    JPasswordField txtPwd2 = new JPasswordField(15);
    JPasswordField txtPwd3 = new JPasswordField(15);
    JButton ok = new JButton("确定");
    JButton cancel = new JButton("取消");

    public RevisePassword() {
        setModal(true);
        setBackground(Color.LIGHT_GRAY);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(6, 1));
        label.setFont(new Font("Sans Serif", Font.BOLD, 15));
        p2.add(label);
        p3.add(new JLabel("旧  密  码:"));
        p3.add(txtPwd1);
        p4.add(new JLabel("新  密  码:"));
        p4.add(txtPwd2);
        p5.add(new JLabel("确认新密码:"));
        p5.add(txtPwd3);
        p6.add(ok);
        p6.add(cancel);
        ok.addActionListener(this);
        cancel.addActionListener(this);
        txtPwd1.addActionListener(this);
        txtPwd2.addActionListener(this);
        txtPwd3.addActionListener(this);
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
        setTitle("修改密码窗口");
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok || e.getSource() == txtPwd2) {
            String password1 = new String(txtPwd1.getPassword());
            String password2 = new String(txtPwd2.getPassword());
            String password3 = new String(txtPwd2.getPassword());
            DbOperate dbOperate = new DbOperate();

            if (password2.equals(password3) && (password1.equals(Login.idPassword))) {
                dbOperate.revise(Login.id, password2);
                JOptionPane.showMessageDialog(null,
                        "修改成功！");
                dispose();
                new Login();
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误！",
                        "警告", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == cancel) {
            dispose();
            System.exit(0);
        }
    }
/*
    public static void main(String[] args) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        Font font = new Font("JFrame", Font.PLAIN, 14);
        Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if
                (UIManager.get(key) instanceof Font){
                UIManager.put(key, font);
            }
        }
        new BankSystem();
    }

 */

}