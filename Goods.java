import java.sql.*;

public class Goods {
    private String goods_id;
    private String name;
    private String cost;
    private String price;
    private String dep_id;

    Goods(String goods_id,String name, String cost, String price, String dep_id) {
        this.goods_id = goods_id; //default
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.dep_id = dep_id;
    }

    public String getDep_id() {
        return dep_id;
    }

    public String getName() {
        return name;
    }

    public String getCost() {
        return cost;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public String getPrice() {
        return price;
    }

    public void setDep_id(String dep_id) {
        this.dep_id = dep_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setPrice(String price) {
        this.price = price;
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
    private static int insert(Goods goods) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into Goods (goods_id,name,cost,price,dep_id) values(?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, goods.getGoods_id());
            pstmt.setString(2, goods.getName());
            pstmt.setString(3, goods.getCost());
            pstmt.setString(4, goods.getPrice());
            pstmt.setString(5, goods.getDep_id());
            i = pstmt.executeUpdate();
            if(i == 1) System.out.println("修改成功");
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    private static int update(Goods goods) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update Goods set goods_id=?,cost=?,price=?,dep_id=? where name=?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, goods.getGoods_id());
            pstmt.setString(5, goods.getName());
            pstmt.setString(2, goods.getCost());
            pstmt.setString(3, goods.getPrice());
            pstmt.setString(4, goods.getDep_id());
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
        String sql = "select * from Goods";
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
    private static Integer search() {
        Connection conn = getConn();
        String sql = "SELECT * FROM Goods WHERE price like '2%'";
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
        Goods.getAll();
        System.out.println("价格为20+,200+或2000+的商品如下：");
        Goods.search();
    }
}
