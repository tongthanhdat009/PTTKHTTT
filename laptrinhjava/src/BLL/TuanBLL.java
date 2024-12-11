package BLL;

import DAL.DataHangHoa;
import DAL.DataHoaDon;
import DTO.DTOChiTietHoaDon;
import DTO.HoaDon;
import DTO.ThongTinChiTietHangHoa;
import java.util.ArrayList;

public class TuanBLL {
    private DataHangHoa dataHangHoa;
    private DataHoaDon dataHoaDon;
    public TuanBLL()
    {
        dataHangHoa = new DataHangHoa();
        dataHoaDon = new DataHoaDon();
    }
    public String layThongTinChiTietHangHoa(String maHangHoa, String maCoSo)
    {
        return dataHangHoa.timThongTinChiTietHangHoa(maHangHoa, maCoSo);
    }
    public ArrayList<ThongTinChiTietHangHoa> timDSHangBan(String ten, String maCoSo, String loai)
    {
        if(ten.equals("")) ten = "NULL";
        if(maCoSo.equals("")) maCoSo = "NULL";
        if(loai.equals("") || loai.equals("Tất cả")) loai = "NULL";
        return dataHangHoa.timDSHangBan(ten, maCoSo, loai);
    }
    public String themVaoGioHang(String maHangHoa, String IDTaiKhoan, String maCoSo, int soLuongMua) {
        String kiemTraTonTaiGioHang = dataHoaDon.kiemTraDaCoGioHangTaiCoSo(IDTaiKhoan, maCoSo);
        if(kiemTraTonTaiGioHang.equals("Chưa tồn tại")) {
            String kiemTraTaoGioHang = dataHoaDon.taoGioHangTaiCoSo(IDTaiKhoan, maCoSo);
            if(kiemTraTaoGioHang.equals("Thành công")) {
                String idHoaDon = dataHoaDon.timIDGioHang(IDTaiKhoan, maCoSo);
                dataHoaDon.themVaoGioHang(idHoaDon,maHangHoa,soLuongMua);
                return "Thành công";
            } else return kiemTraTaoGioHang;
        } 
        else {
            String kiemTraHangDaCoTrongGioChua = dataHoaDon.kiemTraTrongGioHangDaCoHangChua(IDTaiKhoan, maCoSo, maHangHoa);
            String idHoaDon = dataHoaDon.timIDGioHang(IDTaiKhoan, maCoSo);
            if(kiemTraHangDaCoTrongGioChua.equals("Tồn tại")) {
                dataHoaDon.themSoLuongHangTrongGioHang(idHoaDon,maHangHoa,soLuongMua);
                return "Thành công";
            }
            else {
                dataHoaDon.themMonHangVaoGioHang(idHoaDon,maHangHoa,soLuongMua);
                return "Thành công";
            }
        }
    }
    
    public ArrayList<HoaDon> layDSGioHang(String IDTaiKhoan)
    {
        return dataHoaDon.layDSGioHang(IDTaiKhoan);
    }
    public ArrayList<DTOChiTietHoaDon> layChiTietGioHang(String idHoaDon) {
        return dataHoaDon.layChiTietGioHang(idHoaDon); 
    }
    public int tinhGiaTienChiTietGioHang(String idCoSo, String idHH, int soLuong) {
        int gia = dataHoaDon.layGiaTienMonHang(idCoSo,idHH);
        return gia*soLuong;
    }
    public int layGiaTienMonHang(String idCs, String idHH) {
        return dataHoaDon.layGiaTienMonHang(idCs,idHH);
    }
    public void xoaChiTietGioHang(String idHoaDon, String idHH) {
        dataHoaDon.xoaChiTietGioHang(idHoaDon, idHH);
        if(dataHoaDon.kiemTraCoGioHangKhong(idHH).equals("Không")) {
            dataHoaDon.xoaChiTietGioHang(idHoaDon);
        }
    }
    public String thanhToanGioHang(String idTK) {
        String s = dataHoaDon.capNhatGiaGioHang(idTK);
        if(!s.equals("Thành công")) return s; 
        return dataHoaDon.thanhToanGioHang(idTK);
    }
    public ArrayList<HoaDon> layDanhSachHoaDonCuaTaiKhoan(String idtk) {
        return dataHoaDon.layDanhSachDonHangCuaTaiKhoan(idtk);
    }
    public ArrayList<DTOChiTietHoaDon> layChiTietHoaDon(String idhd) {
        return dataHoaDon.layChiTietHoaDon(idhd);
    }
    public ArrayList<HoaDon> layDanhSachHoaDonChuaDuyetCuaCoSo(String maCoSo) {
        return dataHoaDon.layDanhSachHoaDonChuaDuyetTaiCoSo(maCoSo);
    }
    public ArrayList<HoaDon> layDanhSachHoaDonDaXuLyCuaCoSo(String maCoSo) {
        return dataHoaDon.layDanhSachHoaDonDaXuLyTaiCoSo(maCoSo);
    }
    public int laySoLuongHangTaiCoSo(String idHH, String idCs) {
        return dataHoaDon.laySoLuongHangTaiCoSo(idHH, idCs);
    }
    public String huyDon(String idHoaDon) {
        return dataHoaDon.huyDon(idHoaDon);
    }
    public String duyetDon(String idHoaDon, ArrayList<DTOChiTietHoaDon> ds, String maCoSo) {
        for(int i=0;i<ds.size();i++) {
            String s = dataHoaDon.giamSoLuongHangTaiCoSo(ds.get(i).getMaHangHoa(), maCoSo, ds.get(i).getSoLuong());
            if(!s.equals("Thành công")) return s;
        }
        return dataHoaDon.duyetDon(idHoaDon);
    }
    // public String xoaGioHang(String IDTaiKhoan, String maHangHoa, String maCoSo, int soLuongHangHoa)
    // {
    //     if(dataHangHoa.xoaGioHang(maHangHoa, IDTaiKhoan, maCoSo))
    //     {
    //         int soLuongHienTai = dataHangHoa.timSoLuongHangHoaCoSo(maHangHoa, maCoSo);
    //         if(dataHangHoa.suaSoLuongHangHoaOCoSo(maHangHoa, maCoSo, soLuongHangHoa +soLuongHienTai)) return "Thành công";
    //         else return "Lỗi sửa số lượng hhcs";
    //     }
    //     return "Lỗi xóa giỏ hàng";
    // }
    // public String thanhToan(String IDTaiKhoan)
    // {
    //     String maHoaDon = dataHoaDon.layMa();
    //     if(maHoaDon.equals("Loi")) return "Lỗi không sinh được mã hóa đơn";
    //     LocalDate today = LocalDate.now();
    //     Date todayDate = Date.valueOf(today);
    //     if(dataHoaDon.them(new HoaDon(maHoaDon, todayDate, IDTaiKhoan)))
    //     {
    //         ArrayList<GioHang> ds = new ArrayList<>();
    //         ds = dataHangHoa.layDSGioHang(IDTaiKhoan);
    //         boolean flag = true;
    //         for(int i=0;i<ds.size();i++)
    //         {
    //             flag = dataHoaDonChiTiet.them(new DTOChiTietHoaDon(ds.get(i).getSoLuong(), maHoaDon, ds.get(i).getMaHangHoa(), ds.get(i).getGia() * ds.get(i).getSoLuong(), ds.get(i).getMaCoSo(),"Chưa duyệt"));
    //             if(flag == false) return "Lỗi thêm chi tiết hóa đơn";
    //         }
    //         if(flag ==  true && dataHangHoa.xoaGioHangCua(IDTaiKhoan)) return "Thanh toán thành công";
    //         else return "Lỗi xóa giỏ hàng";
            
    //     }
    //     return "Lỗi thêm hóa đơn";
    // }
    // public ArrayList<HoaDonVaGia> layDSHoaDonCua(String IDTaiKhoan)
    // {
    //     return dataHoaDon.layDSHoaDonVaGiaCua(IDTaiKhoan);
    // }
    // public ArrayList<ChiTietChiTietHoaDon> layDSChiTietHoaDonCua(String IDTaiKhoan, String maHD)
    // {
    //     return dataHoaDon.chiTietHoaDon(IDTaiKhoan,maHD);
    // }
    // public ArrayList<HoaDonVaGia> layDSHDCuaCoSo(String maCoSo, String trangThai)
    // {
    //     return dataHoaDon.layDSHoaDonCuaCoSo(maCoSo, trangThai);
    // }
    // public ArrayList<ChiTietChiTietHoaDon> layDSChiTietHoaDonCuaCoSo(String IDTaiKhoan, String maHD, String maCoSo)
    // {
    //     return dataHoaDon.chiTietHoaDonCuaCoSo(IDTaiKhoan,maHD,maCoSo);
    // }
    // public boolean duyetHoaDonCuaCoSo(String maHoaDon, String maCoSo)
    // {
    //     return dataHoaDon.duyetHoaDonCuaCoSo(maHoaDon, maCoSo);
    // }

}
