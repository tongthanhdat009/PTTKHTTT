package BLL;
import DAL.DataLamThongKe;
import DTO.DTOThongKe;
import java.util.*;
public class BLLThongKeDonHang {
    private DataLamThongKe dataLamThongKe;
    public BLLThongKeDonHang() {
        dataLamThongKe = new DataLamThongKe();
    }
    public ArrayList<DTOThongKe> thongKeDoanhThuTheoThang(ArrayList<String> maCoSo,int thangBatDau, int namBatDau, int thangKetThuc, int namKetThuc) {
        ArrayList<DTOThongKe> thongKe = new ArrayList<>();
        for(int i=0;i<maCoSo.size();i++) {
            String strMaCoSo = maCoSo.get(i);
            String tenCoSo = dataLamThongKe.timTenCoSo(strMaCoSo);
            int thang = thangBatDau;
            int nam = namBatDau;
            while(!(thang == thangKetThuc && nam == namKetThuc)) {
                DTOThongKe temp = new DTOThongKe();
                temp.setMaCoSo(strMaCoSo);
                temp.setTenCoSo(tenCoSo);
                temp.setThang(thang);
                temp.setNam(nam);
                temp.setGiaTri(dataLamThongKe.timDoanhThuTheoThangCuaCoSo(strMaCoSo, thang, nam));
                thongKe.add(temp);
                thang+=1;
                if(thang > 12) {
                    thang = 1;
                    nam += 1;
                }
            }
            DTOThongKe temp = new DTOThongKe();
            temp.setMaCoSo(strMaCoSo);
            temp.setTenCoSo(tenCoSo);
            temp.setThang(thang);
            temp.setNam(nam);
            temp.setGiaTri(dataLamThongKe.timDoanhThuTheoThangCuaCoSo(strMaCoSo, thang, nam));
            thongKe.add(temp);
        }
        return thongKe;
    }
    public ArrayList<DTOThongKe> thongKeDoanhThuTheoNam(ArrayList<String> maCoSo,int namBatDau, int namKetThuc) {
        ArrayList<DTOThongKe> thongKe = new ArrayList<>();
        for(int i=0;i<maCoSo.size();i++) {
            String strMaCoSo = maCoSo.get(i);
            String tenCoSo = dataLamThongKe.timTenCoSo(strMaCoSo);
            for(int j=namBatDau;j<=namKetThuc;j++) {
                DTOThongKe temp = new DTOThongKe();
                temp.setMaCoSo(strMaCoSo);
                temp.setTenCoSo(tenCoSo);
                temp.setNam(j);
                temp.setGiaTri(dataLamThongKe.timDoanhThuTheoNamCuaCoSo(strMaCoSo, j));
                thongKe.add(temp);
            }
        }
        return thongKe;
    }
    public ArrayList<DTOThongKe> thongKeChiPhiTheoThang(ArrayList<String> maCoSo,int thangBatDau, int namBatDau, int thangKetThuc, int namKetThuc) {
        ArrayList<DTOThongKe> thongKe = new ArrayList<>();
        for(int i=0;i<maCoSo.size();i++) {
            String strMaCoSo = maCoSo.get(i);
            String tenCoSo = dataLamThongKe.timTenCoSo(strMaCoSo);
            int thang = thangBatDau;
            int nam = namBatDau;
            while(!(thang == thangKetThuc && nam == namKetThuc)) {
                DTOThongKe temp = new DTOThongKe();
                temp.setMaCoSo(strMaCoSo);
                temp.setTenCoSo(tenCoSo);
                temp.setThang(thang);
                temp.setNam(nam);
                temp.setGiaTri(dataLamThongKe.timChiPhiTheoThangCuaCoSo(strMaCoSo, thang, nam));
                thongKe.add(temp);
                thang+=1;
                if(thang > 12) {
                    thang = 1;
                    nam += 1;
                }
            }
            DTOThongKe temp = new DTOThongKe();
            temp.setMaCoSo(strMaCoSo);
            temp.setTenCoSo(tenCoSo);
            temp.setThang(thang);
            temp.setNam(nam);
            temp.setGiaTri(dataLamThongKe.timChiPhiTheoThangCuaCoSo(strMaCoSo, thang, nam));
            thongKe.add(temp);
        }
        return thongKe;
    }
    public ArrayList<DTOThongKe> thongKeChiPhiTheoNam(ArrayList<String> maCoSo,int namBatDau, int namKetThuc) {
        ArrayList<DTOThongKe> thongKe = new ArrayList<>();
        for(int i=0;i<maCoSo.size();i++) {
            String strMaCoSo = maCoSo.get(i);
            String tenCoSo = dataLamThongKe.timTenCoSo(strMaCoSo);
            for(int j=namBatDau;j<=namKetThuc;j++) {
                DTOThongKe temp = new DTOThongKe();
                temp.setMaCoSo(strMaCoSo);
                temp.setTenCoSo(tenCoSo);
                temp.setNam(j);
                temp.setGiaTri(dataLamThongKe.timChiPhiTheoNamCuaCoSo(strMaCoSo, j));
                thongKe.add(temp);
            }
        }
        return thongKe;
    }
}
