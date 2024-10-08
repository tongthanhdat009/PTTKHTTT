package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import DTO.CoSo;
import DTO.DSCoSo;
public class DataCoSo {
    private Connection con;
    private String dbUrl ="jdbc:sqlserver://localhost:1433;databaseName=main;encrypt=true;trustServerCertificate=true;";
    private String userName = "sa"; String password= "123456";
    public ArrayList<String> tenCot = new ArrayList<String>();

    public DataCoSo() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Khởi tạo kết nối cơ sở dữ liệu
            con = DriverManager.getConnection(dbUrl, userName, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy driver cơ sở dữ liệu: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }
    
    public DSCoSo layDSCoSo()
    {
        String truyVan = "SELECT * FROM CoSo";
        DSCoSo ds = new DSCoSo();
        try {
            con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(truyVan);
            while(rs.next())
            ds.them(new CoSo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6)));
        } catch (Exception e) {
            System.out.println(e);
        }
        return ds;
    }
    public DSCoSo timKiemCoSo(String tenCoSo)
    {
        String truyVan = "SELECT * FROM CoSo WHERE TenCoSo = ? ";
        DSCoSo ds = new DSCoSo();
        try {
            con = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1,tenCoSo);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            ds.them(new CoSo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6)));
        } catch (Exception e) {
            System.out.println(e);
        }
        return ds;
    }
    public Vector<String> DSMaCoSo()
    {
        Vector<String> s = new Vector<>();
        String truyVan = "SELECT MaCoSo FROM CoSo";
        try {
            con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(truyVan);
            while(rs.next())
            s.add(rs.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }
    
    public ArrayList<String> DSMaCoSoARR()
    {
        ArrayList<String> s = new ArrayList<String>();
        String truyVan = "SELECT MaCoSo FROM CoSo";
        try {
            con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(truyVan);
            while(rs.next())
            s.add(rs.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }

    public ArrayList<String> layTenCotCoSo(){
        try {
            con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CoSo");
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i=1; i<=rsmd.getColumnCount();i++){
                this.tenCot.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return this.tenCot; 
    }

    public int layMaCoSoMoi(){
        try{
            con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MaCoSo FROM CoSo");
            int max = 0;
            while(rs.next()){
                String maCS = rs.getString("MaCoSo");
                maCS=maCS.substring(2);
                if(Integer.parseInt(maCS) > max){
                    max = Integer.parseInt(maCS);
                }
            }
            return max;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public boolean themCoSo(CoSo coSo){
        try{
            con = DriverManager.getConnection(dbUrl, userName, password);
            String sql = "INSERT INTO CoSo (MaCoSo, TenCoSo, DiaChi, ThoiGianHoatDong, SoDienThoai, DoanhThu) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,coSo.getMaCoSo());            
            preparedStatement.setString(2,coSo.getTenCoSo());            
            preparedStatement.setString(3,coSo.getDiaChi());            
            preparedStatement.setString(4,coSo.getThoiGianHoatDong());            
            preparedStatement.setString(5,coSo.getSDT());            
            preparedStatement.setInt(6,0);            
            if (preparedStatement.executeUpdate() > 0)  return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaCS(String maCS){
        String truyVan = "DELETE FROM CoSo WHERE MaCoSo = ?";
        try{
            con = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement preparedStatement = con.prepareStatement(truyVan);
            preparedStatement.setString(1, maCS);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected>0)return true;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean timKiemCS(String maCS)
    {
        String truyVan = "SELECT * FROM CoSo Where MaCoSo = ?";
        if(maCS.equals("")){
            return false;
        }
        try{
            con = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement statement = con.prepareStatement(truyVan);
            statement.setString(1, maCS);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean suaThongTinCS(CoSo cs){
        //trả về 1 sửa thành công, 0 thất bại
        String truyVan = "UPDATE CoSo SET MaCoSo = ?, TenCoSo = ?, DiaChi = ?, ThoiGianHoatDong = ?, SoDienThoai = ?, DoanhThu = ? FROM CoSo Where MaCoSo = ? ";
        try {
            con = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement statement = con.prepareStatement(truyVan);
            statement.setString(1, cs.getMaCoSo().toUpperCase());
            statement.setString(2, cs.getTenCoSo());
            statement.setString(3, cs.getDiaChi());
            statement.setString(4, cs.getThoiGianHoatDong());
            statement.setString(5, cs.getSDT());
            statement.setInt(6, cs.getDoanhThu());
            statement.setString(7, cs.getMaCoSo());
            int rowsAffected = statement.executeUpdate();
            if(rowsAffected>0) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public DSCoSo timKiem(CoSo a) //Chưa test
    {
        ArrayList<String> ds = new ArrayList<String>();
        DSCoSo dsCS = new DSCoSo();
        String truyVan = "SELECT * FROM CoSo Where ";
        if(!a.getMaCoSo().equals(""))
        {
            truyVan+= "MaCoSo = ? AND ";
            ds.add(a.getMaCoSo());
        } 
        if(!a.getSDT().equals(""))
        {
            truyVan+="SoDienThoai = ? AND ";
            ds.add(a.getSDT());
        } 
        if(!a.getTenCoSo().equals(""))
        {
            truyVan+="TenCoSo = ? AND ";
            ds.add(a.getTenCoSo());
        } 
        if(!a.getDiaChi().equals(""))
        {
            truyVan+="DiaChi = ? AND ";
            ds.add(a.getDiaChi());
        } 
        if(!a.getThoiGianHoatDong().equals(""))
        {
            truyVan+="ThoiGianHoatDong = ? AND ";
            ds.add(a.getThoiGianHoatDong());
        } 
        truyVan = truyVan.trim();
        if (truyVan.endsWith("AND")) {
            // Xóa "AND" cuối cùng bằng cách cắt chuỗi từ đầu đến vị trí cuối cùng của "AND"
            truyVan = truyVan.substring(0, truyVan.lastIndexOf("AND")).trim();
        }
        try
        {
            con = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement statement = con.prepareStatement(truyVan);
            for(int i=0;i<ds.size();i++)
                statement.setString(i+1, ds.get(i));
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                dsCS.them(new CoSo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return dsCS;
    }

    public String getTenCoSo(String maCoSo) {
        String tenCoSo = null;
        try {
            // Tạo câu lệnh SQL để lấy tên cơ sở từ mã cơ sở
            String query = "SELECT TenCoSo FROM CoSo WHERE MaCoSo = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, maCoSo);
    
            // Thực hiện truy vấn
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tenCoSo = rs.getString("TenCoSo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenCoSo;
    }
}
