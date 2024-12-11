package DTO;

public class DTOChiTietHoaDon {
    private String maHoaDon;
    private String maHangHoa;
    private String tenHangHoa;
    private int giaMotMon;
    private int soLuong;
    private String anh;
    public DTOChiTietHoaDon(String maHoaDon, String maHangHoa, String tenHangHoa, int giaMotMon,String anh, int soLuong) {
        setGiaMotMon(giaMotMon);
        setMaHangHoa(maHangHoa);
        setMaHoaDon(maHoaDon);
        setTenHangHoa(tenHangHoa);
        setAnh(anh);
        setSoLuong(soLuong);
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public void setGiaMotMon(int giaMotMon) {
        this.giaMotMon = giaMotMon;
    }
    public void setAnh(String anh) {
        this.anh = anh;
    }
    public String getAnh() {
        return anh;
    }
    public void setMaHangHoa(String maHangHoa) {
        this.maHangHoa = maHangHoa;
    }
    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }
    public int getGiaMotMon() {
        return giaMotMon;
    }
    public String getMaHangHoa() {
        return maHangHoa;
    }
    public String getMaHoaDon() {
        return maHoaDon;
    }
    public String getTenHangHoa() {
        return tenHangHoa;
    }
}
