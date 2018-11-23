import java.sql.*;

public class Stuff {
    private String stuff_id;
    private String name;
    private String position;
    private String contacts;
    private String address;
    private String age;
    private String dep_id;

    Stuff(String stuff_id,String name,String position,String contacts,String address,String age,String dep_id) {
        this.stuff_id = stuff_id; //default
        this.name = name;
        this.position = position;
        this.contacts = contacts;
        this.address = address;
        this.age = age;
        this.dep_id =dep_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getContacts() {
        return contacts;
    }

    public String getDep_id() {
        return dep_id;
    }

    public String getPosition() {
        return position;
    }

    public String getStuff_id() {
        return stuff_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setDep_id(String dep_id) {
        this.dep_id = dep_id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStuff_id(String stuff_id) {
        this.stuff_id = stuff_id;
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
    private static int insert(Stuff stuff) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into Stuff (stuff_id,name,position,contacts,address,age,dep_id) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, stuff.getStuff_id());
            pstmt.setString(2, stuff.getName());
            pstmt.setString(3, stuff.getPosition());
            pstmt.setString(4, stuff.getContacts());
            pstmt.setString(5, stuff.getAddress());
            pstmt.setString(6, stuff.getAge());
            pstmt.setString(7,stuff.getDep_id());
            i = pstmt.executeUpdate();
            if(i == 1) System.out.println("修改成功");
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    private static int update(Stuff stuff) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update Stuff set stuff_id=?,position=?,contacts=?,address=?,age=? where name=?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, stuff.getStuff_id());
            pstmt.setString(2, stuff.getName());
            pstmt.setString(3, stuff.getPosition());
            pstmt.setString(4, stuff.getContacts());
            pstmt.setString(5, stuff.getAddress());
            pstmt.setString(6, stuff.getAge());
            pstmt.setString(7,stuff.getDep_id());
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
        String sql = "select * from Stuff";
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
        String sql = "delete from Department where ManagerName='" + name + "'";
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
        Stuff.getAll();
        Stuff.insert(new Stuff("3", "xiao", "售货员","1582398777","渝北","18","2"));
        Stuff.getAll();
        Stuff.insert(new Stuff("4", "da", "理货员","15823987898","南岸","24","3"));
        Stuff.getAll();
    }
}
