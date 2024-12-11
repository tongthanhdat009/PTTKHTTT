package DAL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import DTO.DTOChiTietHoaDon;
import DTO.HoaDon;
public class DataHoaDon {
    private Connection con;
    private String dbUrl ="jdbc:sqlserver://localhost:1433;databaseName=main;encrypt=true;trustServerCertificate=true;";
    private String userName = "sa"; String password= "123456";
    public DataHoaDon()
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbUrl, userName, password);
        }catch(Exception e){
            System.out.println(e);   
        }
    }
    public String layMa()
    {
        String truyVan = "SELECT * FROM HoaDon";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(truyVan);
            int max = 0;
            while(rs.next())
            {
                String ma = rs.getString("MaHD");
                ma=ma.trim();
                ma = ma.substring(2);
                if(max < Integer.parseInt(ma)) max = Integer.parseInt(ma);
            }
            max+=1;
            return "HD"+max;
        } catch (Exception e) {
            System.out.println(e);   
        }
        return "Loi";
    }
//     public boolean kiemTraDaDuyetChua(String maHoaDon)
//     {
//         String truyVan ="SELECT * FROM ChiTietHoaDon WHERE MaHD = '"+maHoaDon+"' AND TrangThai = N'Chưa duyệt'";
//         System.out.println(truyVan);
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(truyVan);
//             if(rs.next()) return false;
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return true;      
//     }
//     public ArrayList<HoaDonVaGia> layDSHoaDonVaGiaCua(String IDTaiKhoan)
//     {
//         ArrayList<HoaDonVaGia> ds = new ArrayList<>();
//         String truyVan ="  SELECT HoaDon.MaHD, HoaDon.NgayXuatHD,IDTaiKhoan ,SUM(Gia) AS Tong" + 
//                         "  FROM HoaDon, ChiTietHoaDon" + 
//                         "  WHERE HoaDon.MaHD = ChiTietHoaDon.MaHD AND IDTaiKhoan = '"+IDTaiKhoan+"'" + 
//                         "  Group by HoaDon.MaHD, NgayXuatHD, IDTaiKhoan";
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(truyVan);
//             while(rs.next())
//             {
//                 String maHD = rs.getString("MaHD").trim();
//                 Date ngay = rs.getDate("NgayXuatHD");
//                 String id = rs.getString("IDTaiKhoan");
//                 int tong = rs.getInt("Tong");
//                 if(kiemTraDaDuyetChua(maHD))
//                 ds.add(new HoaDonVaGia(maHD,ngay,id,"Đã duyệt",tong));
//                 else 
//                 ds.add(new HoaDonVaGia(maHD,ngay,id,"Chưa duyệt",tong));
//             }
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return ds;      
//     }
//     public boolean them(HoaDon hoaDon)
//     {
//         String truyVan = "INSERT INTO HoaDon (MaHD, NgayXuatHD, IDTaiKhoan) VALUES (?, ?, ?)";
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             PreparedStatement stmt = con.prepareStatement(truyVan);
//             stmt.setString(1, hoaDon.getMaHoaDon());
//             stmt.setDate(2, hoaDon.getNgayXuatHoaDon());
//             stmt.setString(3, hoaDon.getMaHoiVien());
//             if(stmt.executeUpdate() > 0) return true;
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return false;
//     }
//     public boolean kiemTraTonTai(String maHoaDon)
//     {
//         String truyVan = "SELECT * FROM HoaDon WHERE MaHD = ?";
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             PreparedStatement stmt = con.prepareStatement(truyVan);
//             stmt.setString(1, maHoaDon);
//             ResultSet rs = stmt.executeQuery();
//             if(rs.next()) return true;
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return false;
//     }
//     public ArrayList<ChiTietChiTietHoaDon> chiTietHoaDon(String IDTaiKhoan, String MaHoaDon)
//     {

//         ArrayList<ChiTietChiTietHoaDon> ds = new ArrayList<>();
//         String truyVan = "SELECT * FROM HoaDon HD, ChiTietHoaDon CTHD, HoiVien HV, HangHoa HH WHERE HD.MaHD = CTHD.MaHD AND HD.IDTaiKhoan = '"+IDTaiKhoan+"' AND HV.IDTaiKhoan = '"+IDTaiKhoan+"' AND CTHD.MaHH = HH.MaHangHoa AND HD.MaHD = '"+MaHoaDon+"'";
//         System.out.println(truyVan);
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(truyVan);
//             while(rs.next())
//             ds.add(new ChiTietChiTietHoaDon(
//                     rs.getString("MaCoSo"),
//                     rs.getDate("NgayXuatHD"), // Sử dụng java.sql.Date
//                     rs.getString("MaHV"),
//                     rs.getString("HoTenHV"),
//                     rs.getString("TenLoaiHangHoa"),
//                     rs.getInt("SoLuongHang"),
//                     rs.getInt("Gia"),
//                     rs.getString("TrangThai")
//                 ));
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return ds;
//     }
//     public ArrayList<ChiTietChiTietHoaDon> chiTietHoaDonCuaCoSo(String IDTaiKhoan, String MaHoaDon, String maCoSo)
//     {

//         ArrayList<ChiTietChiTietHoaDon> ds = new ArrayList<>();
//         String truyVan = "SELECT * FROM HoaDon HD, ChiTietHoaDon CTHD, HoiVien HV, HangHoa HH WHERE CTHD.MaCoSo = '"+maCoSo+"' AND HD.MaHD = CTHD.MaHD AND HD.IDTaiKhoan = '"+IDTaiKhoan+"' AND HV.IDTaiKhoan = '"+IDTaiKhoan+"' AND CTHD.MaHH = HH.MaHangHoa AND HD.MaHD = '"+MaHoaDon+"'";
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(truyVan);
//             while(rs.next())
//             ds.add(new ChiTietChiTietHoaDon(
//                     rs.getString("MaCoSo"),
//                     rs.getDate("NgayXuatHD"), // Sử dụng java.sql.Date
//                     rs.getString("MaHV"),
//                     rs.getString("HoTenHV"),
//                     rs.getString("TenLoaiHangHoa"),
//                     rs.getInt("SoLuongHang"),
//                     rs.getInt("Gia"),
//                     rs.getString("TrangThai")
//                 ));
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return ds;
//     }
//     public ArrayList<HoaDonVaGia> layDSHoaDon(String trangThai)
//     {
//         ArrayList<HoaDonVaGia> ds = new ArrayList<>();
//         String truyVan = "  SELECT HoaDon.MaHD, HoaDon.NgayXuatHD,IDTaiKhoan, TrangThai, SUM(Gia) AS Tong" + 
//                         "  FROM HoaDon, ChiTietHoaDon" + 
//                         "  WHERE HoaDon.MaHD = ChiTietHoaDon.MaHD" + 
//                         "  Group by HoaDon.MaHD, NgayXuatHD, TrangThai, IDTaiKhoan";
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(truyVan);
//             while(rs.next())
//             ds.add(new HoaDonVaGia(rs.getString("MaHD"),rs.getDate("NgayXuatHD"), rs.getString("IDTaiKhoan"), rs.getString("TrangThai"),rs.getInt("Tong")));
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return ds;  
//     }
//     public ArrayList<HoaDonVaGia> layDSHoaDonCuaCoSo(String maCoSo,String trangThai)
//     {
//         ArrayList<HoaDonVaGia> ds = new ArrayList<>();
//         String truyVan = " SELECT HoaDon.MaHD, HoaDon.NgayXuatHD, IDTaiKhoan, SUM(Gia) AS Tong" + 
//                         "  FROM HoaDon, ChiTietHoaDon" + 
//                         "  WHERE HoaDon.MaHD = ChiTietHoaDon.MaHD AND MaCoSo = '"+maCoSo+"'" + 
//                         "  Group by HoaDon.MaHD, NgayXuatHD, TrangThai, IDTaiKhoan";
//                         System.out.println(truyVan);
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(truyVan);
//             while(rs.next())
//             {
//                 String maHD = rs.getString("MaHD").trim();
//                 Date ngay = rs.getDate("NgayXuatHD");
//                 String id = rs.getString("IDTaiKhoan");
//                 int tong = rs.getInt("Tong");
// //                if(kiemTraDaDuyetOCoSoChua(maHD,maCoSo))
// //                	ds.add(new HoaDonVaGia(maHD,ngay,id,"Đã duyệt",tong));
// //                else 
// //                	ds.add(new HoaDonVaGia(maHD,ngay,id,"Chưa duyệt",tong));
//                 if(trangThai.equals("Đã duyệt")) {
//                 	if(kiemTraDaDuyetOCoSoChua(maHD,maCoSo)) {
//                 		ds.add(new HoaDonVaGia(maHD,ngay,id,"Đã duyệt",tong));
//                 	}
//                 }
//                 else if(kiemTraDaDuyetOCoSoChua(maHD,maCoSo) == false) {
//                 	ds.add(new HoaDonVaGia(maHD,ngay,id,"Chưa duyệt",tong));                	
//                 }
                
//             }
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return ds;  
//     }
//     public boolean kiemTraDaDuyetOCoSoChua(String maHoaDon, String maCoSo)
//     {
//         String truyVan ="SELECT * FROM ChiTietHoaDon WHERE MaHD = '"+maHoaDon+"' AND MaCoSo = '"+maCoSo+"' AND TrangThai = N'Chưa duyệt'";
//         System.out.println(truyVan);
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery(truyVan);
//             if(rs.next()) return false;
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return true;      
//     }
//     public boolean duyetHoaDonCuaCoSo(String maHoaDon, String maCoSo)
//     {
//         String truyVan = "UPDATE ChiTietHoaDon SET TrangThai = N'Đã duyệt' WHERE MaCoSo = '" + maCoSo+"' AND MaHD = '"+maHoaDon+"'";
//         System.out.println(truyVan);
//         try {
//             con = DriverManager.getConnection(dbUrl, userName, password);
//             Statement statement = con.createStatement();
//             if(statement.executeUpdate(truyVan) > 0) return true;
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//         return false;
//     }
    public String timKiemGioHangDaTonTaiMonHang(String idTaiKhoan, String idCoSo, String idHangHoa) {
        String truyVan = "SELECT * FROM HoaDon HD, ChiTietHoaDon CTHD WHERE TrangThai = N'Giỏ hàng' AND IDTaiKhoan = ? AND MaHH = ? AND MaCoSo = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idTaiKhoan);
            stmt.setString(2, idHangHoa);
            stmt.setString(3, idCoSo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return "Tồn tại";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Chưa tồn tại";
    }
    public String kiemTraDaCoGioHangTaiCoSo(String idTaiKhoan, String maCoSo) {
        String truyVan = "SELECT * FROM HoaDon WHERE IDTaiKhoan = ? AND MaCoSo = ? AND TrangThai = N'Giỏ hàng'";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idTaiKhoan);
            stmt.setString(2, maCoSo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return "Tồn tại";
        } catch (Exception e) {
            System.out.println("kiemTraDaCoGioHangTaiCoSo"+e);
        }
        return "Chưa tồn tại";
    }
    public String taoGioHangTaiCoSo(String idTaiKhoan, String maCoSo) {
        String ma = layMa();
        String truyVan = "INSERT INTO HoaDon (MaHD, MaCoSo, IDTaiKhoan, NgayXuatHD, NgayDuyet, TrangThai) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, ma);
            stmt.setString(2, maCoSo);
            stmt.setString(3, idTaiKhoan);
            stmt.setNull(4, java.sql.Types.DATE);
            stmt.setNull(5, java.sql.Types.DATE);
            stmt.setString(6,"Giỏ hàng");
            if(stmt.executeUpdate() > 0) return "Thành công";
        } catch (Exception e) {
            System.out.println("taoGioHangTaiCoSo"+e);
        }
        return "Thất bại";
    }
    public String timIDGioHang(String idTaiKhoan, String maCoSo) {
        String truyVan = "SELECT * FROM HoaDon WHERE IDTaiKhoan = ? AND MaCoSo = ? AND TrangThai = N'Giỏ hàng'";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idTaiKhoan);
            stmt.setString(2, maCoSo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return rs.getString("MaHD");
        } catch (Exception e) {
            System.out.println("timIDGioHang"+e);
        }
        return "Không tìm thấy";
    }
    public void themVaoGioHang(String idHoaDon, String idHH, int soLuong) {
        String truyVan = "INSERT INTO ChiTietHoaDon (MaHD, MaHH, SoLuongHang, Gia) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idHoaDon);
            stmt.setString(2, idHH);
            stmt.setInt(3, soLuong);
            stmt.setInt(4, 0);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("themVaoGioHang"+e);
        }
    }
    public String kiemTraTrongGioHangDaCoHangChua(String idTaiKhoan, String idCoSo, String idHangHoa) {
        String truyVan = "SELECT * FROM HoaDon HD, ChiTietHoaDon CTHD WHERE HD.IDTaiKhoan = ? AND HD.MaCoSo = ? AND CTHD.MaHH = ? AND TrangThai = N'Giỏ hàng'";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idTaiKhoan);
            stmt.setString(2, idCoSo);
            stmt.setString(3, idHangHoa);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return "Tồn tại";
        } catch (Exception e) {
            System.out.println("kiemTraTrongGioHangDaCoHangChua"+e);
        }
        return "Chưa tồn tại";
    }
    public void themSoLuongHangTrongGioHang(String IDHoaDon, String idHH, int soLuongThem) {
        String truyVan = "UPDATE ChiTietHoaDon SET SoLuongHang = SoLuongHang + ? WHERE MaHD = ? AND MaHH = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setInt(1, soLuongThem);
            stmt.setString(2, IDHoaDon);
            stmt.setString(3, idHH);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("themSoLuongHangTrongGioHang"+e);
        }
    }
    public void themMonHangVaoGioHang(String IDHoaDon, String idMonHang,int soLuong) {
        String truyVan = "INSERT INTO ChiTietHoaDon (MaHD, MaHH, SoLuongHang, Gia) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, IDHoaDon);
            stmt.setString(2, idMonHang);
            stmt.setInt(3, soLuong);
            stmt.setInt(4, 0);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("themMonHangVaoGioHang"+e);
        }
    }
    public ArrayList<DTOChiTietHoaDon> layChiTietGioHang(String idHoaDon) {
        ArrayList<DTOChiTietHoaDon> ds = new ArrayList<>();
        String truyVan = "SELECT * FROM ChiTietHoaDon CTHD, HangHoa HH WHERE CTHD.MaHD = ? AND HH.MaHangHoa = CTHD.MaHH";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idHoaDon);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                DTOChiTietHoaDon a = new DTOChiTietHoaDon(
                    rs.getString("MaHD")
                    ,rs.getString("MaHH")
                    ,rs.getString("TenLoaiHangHoa")
                    ,rs.getInt("Gia")
                    ,rs.getString("HinhAnh")
                    ,rs.getInt("SoLuongHang"));
                ds.add(a);
            }
        } catch (Exception e) {
            System.out.println("layChiTietGioHang"+e);
        }
        return ds;
    }
    public ArrayList<DTOChiTietHoaDon> layChiTietHoaDon(String idHoaDon) {
        ArrayList<DTOChiTietHoaDon> ds = new ArrayList<>();
        String truyVan = "SELECT * FROM ChiTietHoaDon CTHD, HangHoa HH WHERE CTHD.MaHD = ? AND HH.MaHangHoa = CTHD.MaHH";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idHoaDon);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                DTOChiTietHoaDon a = new DTOChiTietHoaDon(
                    rs.getString("MaHD")
                    ,rs.getString("MaHH")
                    ,rs.getString("TenLoaiHangHoa")
                    ,rs.getInt("Gia")
                    ,rs.getString("HinhAnh")
                    ,rs.getInt("SoLuongHang"));
                ds.add(a);
            }
        } catch (Exception e) {
            System.out.println("layChiTietGioHang"+e);
        }
        return ds;
    } 
    public ArrayList<HoaDon> layDSGioHang(String IDTaiKhoan)
    {
        ArrayList<HoaDon> ds = new ArrayList<>();
        String truyVan = "SELECT * FROM HoaDon, HoiVien, CoSo WHERE HoaDon.IDTaiKhoan = ? AND TrangThai = N'Giỏ Hàng' AND HoiVien.IDTaiKhoan = HoaDon.IDTaiKhoan AND HoaDon.MaCoSo = CoSo.MaCoSo";
        try {
            con = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, IDTaiKhoan);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                HoaDon a = new HoaDon(
                rs.getString("MaHD")
                , rs.getString("MaHV")
                , rs.getDate("NgayXuatHD")
                , rs.getDate("NgayDuyet")
                , rs.getString("MaCoSo")
                , "Giỏ Hàng"
                , rs.getString("HoTenHV")
                , rs.getString("TenCoSo"));
                ds.add(a);
            }
        } catch (Exception e) { 
           System.out.println("layDSGioHang"+e);
        }
        return ds;
    }
    public int layGiaTienMonHang(String maCoSo, String idHH) {
        String truyVan = "SELECT * FROM HangHoaOCoSo WHERE MaHangHoa = ? AND MaCoSo = ? ";
        try {
            System.out.print(idHH);
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idHH);
            stmt.setString(2, maCoSo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return rs.getInt("GiaBan");
        } catch (Exception e) {
            System.out.println("layGiaTienMonHang"+e);
        }
        return 0;
    }
    public void xoaChiTietGioHang(String idGioHang, String idHH) {
        String truyVan = "DELETE ChiTietHoaDon WHERE MaHD = ? AND MaHH = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idGioHang);
            stmt.setString(2, idHH);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("xoaChiTietGioHang"+e);
        }
    }
    public String kiemTraCoGioHangKhong(String idGioHang) {
        String truyVan = "SELECT * FROM ChiTietHoaDon WHERE MaHD = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idGioHang);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return "Có";
        } catch (Exception e) {
            System.out.println("kiemTraCoGioHangKhong"+e);
        }
        return "Không";
    }
    public void xoaChiTietGioHang(String idHH) {
        String truyVan = "DELETE HoaDon WHERE MaHD = ? ";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idHH);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("xoaChiTietGioHang"+e);
        }
    }
    public String thanhToanGioHang(String idTK) {
        String truyVan = "UPDATE HoaDon SET TrangThai = N'Chưa duyệt', NgayXuatHD = ? WHERE IDTaiKhoan = ? AND TrangThai = N'Giỏ hàng'";
        try {
            // Lấy ngày hiện tại
            LocalDate today = LocalDate.now();
            Date ngayXuatHD = Date.valueOf(today); // Chuyển đổi sang kiểu java.sql.Date

            // Chuẩn bị truy vấn
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setDate(1, ngayXuatHD); // Gán ngày xuất hóa đơn
            stmt.setString(2, idTK);    // Gán ID tài khoản
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) return "Thành công";
        } catch (Exception e) {
            return "Có lỗi xảy ra trong quá trình thanh toán. " + e;
        }
        return "Lỗi ";
    }
    public String capNhatGiaGioHang(String IDTK) {
        String truyVan = "UPDATE ChiTietHoaDon\r\n" + //
                        "SET ChiTietHoaDon.Gia = HangHoaOCoSo.GiaBan\r\n" + //
                        "From HangHoaOCoSo, ChiTietHoaDon, HoaDon\r\n" + //
                        "WHERE ChiTietHoaDon.MaHH = HangHoaOCoSo.MaHangHoa AND ChiTietHoaDon.MaHD = HoaDon.MaHD AND HoaDon.IDTaiKhoan = ? AND HoaDon.TrangThai = N'Giỏ hàng'";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, IDTK);
            if(stmt.executeUpdate()> 0) return "Thành công";
        } catch (Exception e) {
            return "capNhatGiaGioHang" + e;
        }
        return "Lỗi capNhatGiaGioHang";               
    }
    public String duyetDonHang(String idHoaDon) {
        String truyVan = "UPDATE HoaDon SET TrangThai = N'Đã duyệt', NgayDuyet = ? WHERE MaHD = ?";
        try {
            // Lấy ngày hiện tại
            LocalDate today = LocalDate.now();
            Date ngayXuatHD = Date.valueOf(today); // Chuyển đổi sang kiểu java.sql.Date
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setDate(1, ngayXuatHD); // Gán ngày xuất hóa đơn
            stmt.setString(2, idHoaDon);    // Gán ID tài khoản
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) return "Thành công";
        } catch (Exception e) {
            return "duyetDonHang "+e;
        }
        return "Lỗi ";
    }
    public ArrayList<HoaDon> layDanhSachDonHangCuaTaiKhoan(String idTK) {
        String truyVan = "SELECT * FROM HoaDon, HoiVien, CoSo WHERE HoaDon.IDTaiKhoan = ? AND TrangThai != N'Giỏ Hàng' AND HoiVien.IDTaiKhoan = HoaDon.IDTaiKhoan AND HoaDon.MaCoSo = CoSo.MaCoSo";
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idTK);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                HoaDon a = new HoaDon(
                    rs.getString("MaHD")
                    , rs.getString("MaHV")
                    , rs.getDate("NgayXuatHD")
                    , rs.getDate("NgayDuyet")
                    , rs.getString("MaCoSo")
                    , rs.getString("TrangThai")
                    , rs.getString("HoTenHV")
                    , rs.getString("TenCoSo"));
                dsHoaDon.add(a);
            }
        } catch (Exception e) {
            System.out.println("layDanhSachDonHangCuaTaiKhoan"+e);
        }
        return dsHoaDon;
    }
    public ArrayList<HoaDon> layDanhSachHoaDonChuaDuyetTaiCoSo(String maCoSo) {
        String truyVan = "SELECT * FROM HoaDon, HoiVien, CoSo WHERE HoaDon.maCoSo = ? AND TrangThai = N'Chưa duyệt' AND HoiVien.IDTaiKhoan = HoaDon.IDTaiKhoan AND HoaDon.MaCoSo = CoSo.MaCoSo";
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, maCoSo);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                HoaDon a = new HoaDon(
                    rs.getString("MaHD")
                    , rs.getString("MaHV")
                    , rs.getDate("NgayXuatHD")
                    , rs.getDate("NgayDuyet")
                    , rs.getString("MaCoSo")
                    , rs.getString("TrangThai")
                    , rs.getString("HoTenHV")
                    , rs.getString("TenCoSo"));
                dsHoaDon.add(a);
            }
        } catch (Exception e) {
            System.out.println("layDanhSachDonHangCuaTaiKhoan"+e);
        }
        return dsHoaDon;
    }
    public ArrayList<HoaDon> layDanhSachHoaDonDaXuLyTaiCoSo(String maCoSo) {
        String truyVan = "SELECT * FROM HoaDon, HoiVien, CoSo WHERE HoaDon.maCoSo = ? AND TrangThai != N'Chưa duyệt' AND TrangThai != N'Giỏ hàng' AND HoiVien.IDTaiKhoan = HoaDon.IDTaiKhoan AND HoaDon.MaCoSo = CoSo.MaCoSo";
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, maCoSo);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                HoaDon a = new HoaDon(
                    rs.getString("MaHD")
                    , rs.getString("MaHV")
                    , rs.getDate("NgayXuatHD")
                    , rs.getDate("NgayDuyet")
                    , rs.getString("MaCoSo")
                    , rs.getString("TrangThai")
                    , rs.getString("HoTenHV")
                    , rs.getString("TenCoSo"));
                dsHoaDon.add(a);
            }
        } catch (Exception e) {
            System.out.println("layDanhSachDonHangCuaTaiKhoan"+e);
        }
        return dsHoaDon;
    }
    public int laySoLuongHangTaiCoSo(String idHH, String idCoSo) {
        String truyVan = "SELECT * FROM HangHoaOCoSo WHERE MaCoSo = ? AND MaHangHoa = ?";
        int soLuong = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setString(1, idCoSo);
            stmt.setString(2, idHH);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) soLuong = rs.getInt("SoLuong");
        } catch (Exception e) {
            System.out.println("laySoLuongHangTaiCoSo"+e);
        }
        return soLuong;
    }
    public String huyDon(String idHD) {
        String truyVan = "UPDATE HoaDon Set TrangThai = N'Đã hủy', NgayDuyet = ? WHERE MaHD = ?";
        try {
            LocalDate today = LocalDate.now();
            Date ngayXuatHD = Date.valueOf(today); // Chuyển đổi sang kiểu java.sql.Date
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setDate(2, ngayXuatHD);
            stmt.setString(2, idHD);
            if(stmt.executeUpdate() > 0) return "Thành công";
        } catch (Exception e) {
            System.out.println("huyDon"+e);
        }
        return "Thất bại";
    }
    public String duyetDon(String idHD) {
        String truyVan = "UPDATE HoaDon Set TrangThai = N'Đã duyệt', NgayDuyet = ? WHERE MaHD = ?";
        try {
            LocalDate today = LocalDate.now();
            Date ngayXuatHD = Date.valueOf(today); // Chuyển đổi sang kiểu java.sql.Date
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setDate(1, ngayXuatHD);
            stmt.setString(2, idHD);
            if(stmt.executeUpdate() > 0) return "Thành công";
        } catch (Exception e) {
            System.out.println("duyetDon"+e);
        }
        return "Thất bại";
    }
    public String giamSoLuongHangTaiCoSo(String idHH, String idCs, int soLuong) {
        String truyVan = "UPDATE HangHoaOCoSo SET SoLuong = SoLuong - ? WHERE MaHangHoa = ? AND MaCoSo = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(truyVan);
            stmt.setInt(1, soLuong);
            stmt.setString(2, idHH);
            stmt.setString(3, idCs);
            if(stmt.executeUpdate() > 0) return "Thành công";
        } catch (Exception e) {
            System.out.println("giamSoLuongHangTaiCoSo"+e);
        }
        return "Thất bại giamSoLuongHangTaiCoSo";
    }
}
