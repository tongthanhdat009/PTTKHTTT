package DTO;

public class DTOThongKe {
    private String maCoSo;
    private String tenCoSo;
    private int thang;
    private int nam;
    private int giaTri;
    public DTOThongKe() {
        maCoSo = "";
        tenCoSo = "";
        thang = -1;
        nam = -1;
        giaTri = -1;
    }
    public void setMaCoSo(String maCoSo) {
        this.maCoSo = maCoSo;
    }
    public void setNam(int nam) {
        this.nam = nam;
    }
    public void setTenCoSo(String tenCoSo) {
        this.tenCoSo = tenCoSo;
    }
    public void setThang(int thang) {
        this.thang = thang;
    }
    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }
    public int getGiaTri() {
        return giaTri;
    }
    public String getMaCoSo() {
        return maCoSo;
    }
    public int getNam() {
        return nam;
    }
    public String getTenCoSo() {
        return tenCoSo;
    }
    public int getThang() {
        return thang;
    }
    public String toString() {
        return  maCoSo+":"+tenCoSo+":"+thang+":"+nam+":"+giaTri;
    }
}
