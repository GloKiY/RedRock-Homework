import java.sql.*;

public class Department {
    private String Id;
    private String Name;
    private String ManagerName;
    private String ManagerContactInfomation;
    private String ManagerAddress;

    Department(String Id,String Name, String ManagerName, String ManagerContactInformation, String ManagerAdress) {
        this.Id = Id; //default
        this.Name = Name;
        this.ManagerName = ManagerName;
        this.ManagerContactInfomation = ManagerContactInformation;
        this.ManagerAddress = ManagerAdress;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public String getManagerContactInfomation() {
        return ManagerContactInfomation;
    }

    public String getManagerAddress() {
        return ManagerAddress;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }

    public void setManagerContactInfomation(String managerContactInfomation) {
        ManagerContactInfomation = managerContactInfomation;
    }

    public void setManagerAddress(String managerAdress) {
        ManagerAddress = managerAdress;
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
    private static int insert(Department department) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into Department (Id,Name,ManagerName,ManagerContactInformation,ManagerAddress) values(?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, department.getId());
            pstmt.setString(2, department.getName());
            pstmt.setString(3, department.getManagerName());
            pstmt.setString(4, department.getManagerContactInfomation());
            pstmt.setString(5, department.getManagerAddress());
            i = pstmt.executeUpdate();
            if(i == 1) System.out.println("修改成功");
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    private static int update(Department department) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update Department set Id=?,ManagerName=?,ManagerContactInformation=?,ManagerAddress=? where name=?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, department.getId());
            pstmt.setString(5, department.getName());
            pstmt.setString(2, department.getManagerName());
            pstmt.setString(3, department.getManagerContactInfomation());
            pstmt.setString(4, department.getManagerAddress());
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
        String sql = "select * from Department";
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
    private static int delete(String Managername) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from Department where ManagerName='" + Managername + "'";
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
        Department.getAll();
        Department.update(new Department("3","零食","nihao","35477","重邮"));
        Department.getAll();
    }
}
