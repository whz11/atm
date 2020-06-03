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
import javax.swing.JTextField;
import javax.swing.UIManager;

import DB.DbOperate;

class  Transfer extends JDialog implements ActionListener
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JPanel p3=new JPanel();
    JPanel p4=new JPanel();

    JTextField card_num =new JTextField(15);
    JTextField money =new JTextField(15);
    JTextField balance = new JTextField(15);
  //  JButton search = new JButton("查询余额");
    JButton ok=new JButton("确定");
    JButton cancel=new JButton("返回");

    public Transfer(){
        setModal(true);
        setBackground(Color.LIGHT_GRAY);
        Container contentPane=this.getContentPane();
        contentPane.setLayout(new GridLayout(4,1));
        balance.setEditable(false);
        p1.add(new JLabel("请输入对方的账号 ："));
        p1.add(card_num);
        p2.add(new JLabel(" 请输入转账的金额："));
        p2.add(money);
        p3.add(new JLabel("           您当前可用余额为："));
        p3.add(balance);
        p3.add(new JLabel("元!"));
        p4.add(ok);
        p4.add(cancel);
     //   p4.add(search);
     //   search.addActionListener(this);
        ok.addActionListener(this);
        cancel.addActionListener(this);
        contentPane.add(p1);
        contentPane.add(p2);
        contentPane.add(p3);
        contentPane.add(p4);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,300);
        Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width-500)/2,(screen.height-300)/2);
        setTitle("转账窗口");
        setResizable(false);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        DbOperate dbOperate = new DbOperate();
        if(e.getSource()==ok)
        {
            double t = dbOperate.findMoney(Login.id);

            if(Double.parseDouble(money.getText().trim()) <= t)
            {
                try {
                    dbOperate.updateMoney(Double.parseDouble(money.getText().trim()), Login.id, "取款");
                    dbOperate.updateMoney(Double.parseDouble(money.getText().trim()), card_num.getText().trim(), "存款");
                    JOptionPane.showMessageDialog(null, "转账成功！");
                    t = dbOperate.findMoney(Login.id);
                    balance.setText(String.valueOf(t));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"转账失败，您卡上余额不足！","警告",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource()==cancel)
        {
            dispose();
            new Menu();
        }

    }
/*
    public static void main(String[] args)
    {
        JDialog.setDefaultLookAndFeelDecorated(true);
        Font font=new Font("JFrame",Font.PLAIN,14);
        Enumeration keys=UIManager.getLookAndFeelDefaults().keys();
        while(keys.hasMoreElements())
        {
            Object key=keys.nextElement();
            if(UIManager.get(key) instanceof Font)UIManager.put(key,font);
        }
        new BankSystem();
    }

 */

}