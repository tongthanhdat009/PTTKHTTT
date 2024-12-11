package DTO;
import java.sql.*;
public class HoaDon {
    private String maHoaDon;
    private String maTaiKhoan;
    private Date ngayXuatHoaDon;
    private Date ngayDuyetHoaDon;
    private String maCoSo;
    private String trangThai;
    private String tenHoiVien;
    private String tenCoSo;
    public HoaDon(String maHoaDon, String maTaiKhoan, Date ngayXuatHoaDon, Date ngayDuyetHoaDon, String maCoSo, String TrangThai,String tenHoiVien, String tenCoSo)
    {
        setMaCoSo(maCoSo);
        setMaHoaDon(maHoaDon);
        setMaTaiKhoan(maTaiKhoan);
        setNgayDuyetHoaDon(ngayDuyetHoaDon);
        setNgayXuatHoaDon(ngayXuatHoaDon);
        setTrangThai(TrangThai);
        setTenHoiVien(tenHoiVien);
        setTenCoSo(tenCoSo);
    }
    public void setTenCoSo(String tenCoSo) {
        this.tenCoSo = tenCoSo;
    }
    public void setTenHoiVien(String tenHoiVien) {
        this.tenHoiVien = tenHoiVien;
    }
    public String getTenCoSo() {
        return tenCoSo;
    }
    public String getTenHoiVien() {
        return tenHoiVien;
    }
    public void setMaCoSo(String maCoSo) {
        this.maCoSo = maCoSo;
    }
    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }
    public void setNgayDuyetHoaDon(Date ngayDuyetHoaDon) {
        this.ngayDuyetHoaDon = ngayDuyetHoaDon;
    }
    public void setNgayXuatHoaDon(Date ngayXuatHoaDon) {
        this.ngayXuatHoaDon = ngayXuatHoaDon;
    }
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public String getMaCoSo() {
        return maCoSo;
    }
    public String getMaHoaDon() {
        return maHoaDon;
    }
    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }
    public Date getNgayDuyetHoaDon() {
        return ngayDuyetHoaDon;
    }
    public Date getNgayXuatHoaDon() {
        return ngayXuatHoaDon;
    }
    public String getTrangThai() {
        return trangThai;
    }
}
