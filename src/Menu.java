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
import javax.swing.JPanel;
import javax.swing.UIManager;

class Menu extends JDialog implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();

    JLabel label1 = new JLabel("欢迎使用银行管理系统");

    JButton deposit = new JButton("存款");
    JButton get = new JButton("取款");
    JButton revise = new JButton("修改密码");
    JButton transfer = new JButton("转账");
    JButton exit = new JButton("取回磁卡");

    public Menu() {
        setModal(true);
        setBackground(Color.LIGHT_GRAY);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        label1.setFont(new Font("Sans Serif", Font.BOLD, 20));

        p2.add(label1);
        p3.add(new JLabel("请选择操作："));
        p3.add(deposit);
        p3.add(get);
        p3.add(revise);
        p3.add(transfer);
        p3.add(exit);
        deposit.addActionListener(this);
        get.addActionListener(this);
        revise.addActionListener(this);
        transfer.addActionListener(this);
        exit.addActionListener(this);
        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
        setTitle("主操作窗口");
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deposit) {
            dispose();
            new Deposit();
        } else if (e.getSource() == get) {
            dispose();
            new Get();
        } else if (e.getSource() == revise) {
            dispose();
            new RevisePassword();
        }else if (e.getSource() == transfer) {
            dispose();
            new Transfer();
        } else if (e.getSource() == exit) {
            dispose();
            new Login();
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