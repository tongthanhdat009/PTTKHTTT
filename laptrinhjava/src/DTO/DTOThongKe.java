package DTO;
import java.util.ArrayList;
public class DTOThongKe {
    private String maCoSo;
    private String tenCoSo;
    private ArrayList<ThuChi> thuChi;
    public DTOThongKe() {
        maCoSo = "";
        tenCoSo = "";
        thuChi = new ArrayList<>();
    }
    public String getMaCoSo() {
        return maCoSo;
    }
    public String getTenCoSo() {
        return tenCoSo;
    }
    public ArrayList<ThuChi> getThuChi() {
        return thuChi;
    }
    public void setMaCoSo(String maCoSo) {
        this.maCoSo = maCoSo;
    }
    public void setTenCoSo(String tenCoSo) {
        this.tenCoSo = tenCoSo;
    }
    public void setThuChi(ArrayList<ThuChi> thuChi) {
        this.thuChi = thuChi;
    }
    public void themThuChi(ThuChi ThuChi) {
        thuChi.add(ThuChi);
    }
    public void xuat(){
        for(int i=0;i<thuChi.size();i++)
        System.out.println(maCoSo+":"+tenCoSo+":"+thuChi.get(i).getThang()+":"+thuChi.get(i).getNam()+":"+thuChi.get(i).getGiaTri());
    }
}
