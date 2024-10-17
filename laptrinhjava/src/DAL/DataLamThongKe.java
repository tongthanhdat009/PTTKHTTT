package DAL;
import java.sql.*;
public class DataLamThongKe{
    private Connection con;
    private String dbUrl ="jdbc:sqlserver://localhost:1433;databaseName=main;encrypt=true;trustServerCertificate=true;";
    private String userName = "sa"; String password= "123456";
    public DataLamThongKe()
    {
        try{
            con = DriverManager.getConnection(dbUrl, userName, password);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(Exception e){
            System.out.println(e);   
        }
    }
    public int timDoanhThuTheoThangCuaCoSo(String maCoSo, int thang, int nam) {
        String truyVan= "SELECT MaCoSo ,SUM(Gia) AS DoanhThu FROM ChiTietHoaDon CTHD, HoaDon HD "+
                        "WHERE CTHD.MaHD = HD.MaHD AND MONTH(NgayXuatHD) = ? AND YEAR(NgayXuatHD) = ? AND MaCoSo = ? "+
                        "Group by MaCoSo";
        int tong = -1;
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setInt(1, thang);
            stmt.setInt(2, nam);
            stmt.setString(3, maCoSo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) tong = rs.getInt("DoanhThu");
            else tong = 0;
        } catch (Exception e) {
            System.out.println(e);  
        }
        return tong;
    }
    public String timTenCoSo(String maCoSo) {
        String truyVan = "SELECT * FROM CoSo WHERE MaCoSo = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, maCoSo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return rs.getString("TenCoSo");
            return "Mã cơ sở không tồn tại";
        } catch (Exception e) {
            System.out.println(e);  
        }
        return "Lỗi";
    }
    public int timDoanhThuTheoNamCuaCoSo(String maCoSo, int nam) {
        String truyVan= "SELECT MaCoSo ,SUM(Gia) AS DoanhThu FROM ChiTietHoaDon CTHD, HoaDon HD "+
                        "WHERE CTHD.MaHD = HD.MaHD AND YEAR(NgayXuatHD) = ? AND MaCoSo = ? "+
                        "Group by MaCoSo";
        int tong = -1;
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setInt(1, nam);
            stmt.setString(2, maCoSo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) tong = rs.getInt("DoanhThu");
            else tong = 0;
        } catch (Exception e) {
            System.out.println(e);  
        }
        return tong;
    }
    public int timChiPhiTheoThangCuaCoSo(String maCoSo, int thang, int nam) {
        String truyVan = "SELECT MaCoSo, SUM(GiaNhap * SoLuong) AS CHI FROM NhanVien NV, PhieuNhap PN, ChiTietPhieuNhap CTPN " +
                        "WHERE PN.MaNV = NV.MaNV AND CTPN.MaPhieuNhap = PN.MaPhieuNhap AND MONTH(NgayNhap) = ? AND YEAR(NgayNhap) = ? AND NV.MaCoSo = ? " +
                        "GROUP BY MaCoSo";
        System.out.println(truyVan);
        int tong = -1;
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setInt(1, thang);
            stmt.setInt(2, nam);
            stmt.setString(3, maCoSo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) tong = rs.getInt("Chi");
            else tong = 0;
        } catch (Exception e) {
            System.out.println(e);  
        }
        return tong;
    }
    public int timChiPhiTheoNamCuaCoSo(String maCoSo, int nam) {
        String truyVan = "SELECT MaCoSo, SUM(GiaNhap * SoLuong) AS CHI FROM NhanVien NV, PhieuNhap PN, ChiTietPhieuNhap CTPN " +
                        "WHERE PN.MaNV = NV.MaNV AND CTPN.MaPhieuNhap = PN.MaPhieuNhap AND YEAR(NgayNhap) = ? AND NV.MaCoSo = ? " +
                        "GROUP BY MaCoSo";
        int tong = -1;
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setInt(1, nam);
            stmt.setString(2, maCoSo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) tong = rs.getInt("Chi");
            else tong = 0;
        } catch (Exception e) {
            System.out.println(e);  
        }
        return tong;
    }
}