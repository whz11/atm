package DB;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class DbOperate {


    String sql, sql1;
    ResultSet rs = null;
    private String id, password, history;
    private double money;
    String[] s = new String[4];

    public String[] login(String num) {
        DatabaseConn DB = new DatabaseConn();
        this.id = num;
        sql = "select * from login where id = "
                + Integer.parseInt(id.trim()) + " ";
        try {
            DB.OpenConn();
            rs = DB.executeQuery(sql);
            if (rs.next()) {
                s[0] = rs.getString("id");
                s[1] = rs.getString("password");
                s[2] = rs.getString("money");
            } else {
                s = null;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DB.closeStmt();
            DB.closeConn();
        }
        return s;
    }

    public double findMoney(String num) {
        DatabaseConn DB = new DatabaseConn();
        this.id = num;
        sql = "select * from login where id = "
                + Integer.parseInt(id.trim()) + " ";
        try {
            DB.OpenConn();
            rs = DB.executeQuery(sql);
            if (rs.next()) {
                money = rs.getDouble("money");
            } else {
                s = null;
            }
        } catch (Exception e) {

        } finally {
            DB.closeStmt();
            DB.closeConn();
        }
        return money;
    }


    public void updateMoney(double money, String num, String i) throws Exception {
        DatabaseConn DB = new DatabaseConn();
        this.id = num;
        int g = 1;
        if ("取款".equals(i)) {
            g = -1;
        }
        // System.out.println(money);
        double t = findMoney(id) + g * money;
        //  System.out.println(t);
        sql = "update login set money = " + t
                + " where id = " + Integer.parseInt(id.trim())
                + " ";
        //System.out.println(sql);

        DB.OpenConn();
        DB.executeUpdate(sql);

        DB.closeStmt();
        DB.closeConn();

    }


    public void deposit(double money, String num) throws Exception {
        DatabaseConn DB = new DatabaseConn();
        this.id = num;
        double t = findMoney(id);

        double temp = t + money;
        sql = "update login set money = " + temp
                + " where id = " + Integer.parseInt(id)
                + " ";

        DB.OpenConn();
        DB.executeUpdate(sql);

        DB.closeStmt();
        DB.closeConn();
    }



    public void revise(String num, String password) {
        DatabaseConn DB = new DatabaseConn();
        this.password = password;
        this.id = num;
        sql = "update login set password = " + password
                + " where id = " + Integer.parseInt(id.trim())
                + " ";
        //System.out.println(sql);
        try {

            DB.OpenConn();
            DB.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "修改失败，请重新输入密码点击确定！", "错误",
                    JOptionPane.ERROR_MESSAGE);

        } finally {
            DB.closeStmt();
            DB.closeConn();
        }

    }
}

