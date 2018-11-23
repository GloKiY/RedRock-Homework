import java.sql.*;

public class Merchant {
    private String name;
    private String contacts;
    private String address;

    Merchant(String name,String contacts,String address) {
        this.address =address;
        this.contacts = contacts;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getContacts() {
        return contacts;
    }

    public String getAddress() {
        return address;
    }

    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/homework?"+ "useUnicode=true&characterEncoding=UTF8";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    private static int insert(Merchant merchant) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into Merchant (name,contacts,address) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,merchant.getName());
            pstmt.setString(2,merchant.getContacts());
            pstmt.setString(3,merchant.getAddress());
            i = pstmt.executeUpdate();
            if(i == 1) System.out.println("修改成功");
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    private static int update(Merchant merchant) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update Merchant set contact=?,address=? where name=?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(3,merchant.getName());
            pstmt.setString(1,merchant.getContacts());
            pstmt.setString(2,merchant.getAddress());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    private static Integer getAll() {
        Connection conn = getConn();
        String sql = "select * from Merchant";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static int delete(String name) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from Goods where ManagerName='" + name + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            if(i==1)   System.out.println("删除成功");
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public static void main(String args[]) {
        Merchant.getAll();
    }
}
