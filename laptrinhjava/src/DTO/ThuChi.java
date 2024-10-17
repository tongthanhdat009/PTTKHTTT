package DTO;

public class ThuChi {
    private int thang;
    private int nam;
    private int giaTri;
    public ThuChi() {
        thang = -1;
        nam = -1;
        giaTri = -1;
    }
    public int getGiaTri() {
        return giaTri;
    }
    public int getNam() {
        return nam;
    }
    public int getThang() {
        return thang;
    }
    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }
    public void setNam(int nam) {
        this.nam = nam;
    }
    public void setThang(int thang) {
        this.thang = thang;
    }
}
