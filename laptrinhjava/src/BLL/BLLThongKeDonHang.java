package BLL;
import DAL.DataLamThongKe;
import DTO.DTOThongKe;
import DTO.ThuChi;

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
            DTOThongKe temp = new DTOThongKe();
            temp.setMaCoSo(strMaCoSo);
            temp.setTenCoSo(tenCoSo);
            while(!(thang == thangKetThuc && nam == namKetThuc)) {
                ThuChi thuChi = new ThuChi();
                thuChi.setThang(thang);
                thuChi.setNam(nam);
                thuChi.setGiaTri(dataLamThongKe.timDoanhThuTheoThangCuaCoSo(strMaCoSo, thang, nam));
                temp.themThuChi(thuChi);
                thang+=1;
                if(thang > 12) {
                    thang = 1;
                    nam += 1;
                }
            }
            ThuChi thuChi = new ThuChi();
            thuChi.setThang(thang);
            thuChi.setNam(nam);
            thuChi.setGiaTri(dataLamThongKe.timDoanhThuTheoThangCuaCoSo(strMaCoSo, thang, nam));
            temp.themThuChi(thuChi);
            thongKe.add(temp);
        }
        return thongKe;
    }
    public ArrayList<DTOThongKe> thongKeDoanhThuTheoNam(ArrayList<String> maCoSo,int namBatDau, int namKetThuc) {
        ArrayList<DTOThongKe> thongKe = new ArrayList<>();
        for(int i=0;i<maCoSo.size();i++) {
            String strMaCoSo = maCoSo.get(i);
            String tenCoSo = dataLamThongKe.timTenCoSo(strMaCoSo);
            DTOThongKe temp = new DTOThongKe();
            temp.setMaCoSo(strMaCoSo);
            temp.setTenCoSo(tenCoSo);
            for(int j=namBatDau;j<=namKetThuc;j++) {
                ThuChi thuChi = new ThuChi();
                thuChi.setNam(j);
                thuChi.setGiaTri(dataLamThongKe.timDoanhThuTheoNamCuaCoSo(strMaCoSo, j));
                temp.themThuChi(thuChi);
            }
            thongKe.add(temp);
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
            DTOThongKe temp = new DTOThongKe();
            temp.setMaCoSo(strMaCoSo);
            temp.setTenCoSo(tenCoSo);
            while(!(thang == thangKetThuc && nam == namKetThuc)) {
                ThuChi thuChi = new ThuChi();
                thuChi.setThang(thang);
                thuChi.setNam(nam);
                thuChi.setGiaTri(dataLamThongKe.timChiPhiTheoThangCuaCoSo(strMaCoSo, thang, nam));
                temp.themThuChi(thuChi);
                thang+=1;
                if(thang > 12) {
                    thang = 1;
                    nam += 1;
                }
            }
            ThuChi thuChi = new ThuChi();
            thuChi.setThang(thang);
            thuChi.setNam(nam);
            thuChi.setGiaTri(dataLamThongKe.timChiPhiTheoThangCuaCoSo(strMaCoSo, thang, nam));
            temp.themThuChi(thuChi);
            thongKe.add(temp);
        }
        return thongKe;
    }
    public ArrayList<DTOThongKe> thongKeChiPhiTheoNam(ArrayList<String> maCoSo,int namBatDau, int namKetThuc) {
        ArrayList<DTOThongKe> thongKe = new ArrayList<>();
        for(int i=0;i<maCoSo.size();i++) {
            String strMaCoSo = maCoSo.get(i);
            String tenCoSo = dataLamThongKe.timTenCoSo(strMaCoSo);
            DTOThongKe temp = new DTOThongKe();
            temp.setMaCoSo(strMaCoSo);
            temp.setTenCoSo(tenCoSo);
            for(int j=namBatDau;j<=namKetThuc;j++) {
                ThuChi thuChi = new ThuChi();
                thuChi.setNam(j);
                thuChi.setGiaTri(dataLamThongKe.timChiPhiTheoNamCuaCoSo(strMaCoSo, j));
                temp.themThuChi(thuChi);
            }
            thongKe.add(temp);
        }
        return thongKe;
    }
    public ArrayList<DTOThongKe> thongKeChiPhi(ArrayList<String> maCoSo) {
        ArrayList<DTOThongKe> thongKe = new ArrayList<>();
        for(int i=0;i<maCoSo.size();i++) {
            String strMaCoSo = maCoSo.get(i);
            String tenCoSo = dataLamThongKe.timTenCoSo(strMaCoSo);
            DTOThongKe temp = new DTOThongKe();
            temp.setMaCoSo(strMaCoSo);
            temp.setTenCoSo(tenCoSo);
            ThuChi thuChi = new ThuChi();
            thuChi.setGiaTri(dataLamThongKe.timChiPhiCuaCoSo(strMaCoSo));
            temp.themThuChi(thuChi);
            thongKe.add(temp);
        }
        return thongKe;
    }
    public ArrayList<DTOThongKe> thongKeDoanhThu(ArrayList<String> maCoSo) {
        ArrayList<DTOThongKe> thongKe = new ArrayList<>();
        for(int i=0;i<maCoSo.size();i++) {
            String strMaCoSo = maCoSo.get(i);
            String tenCoSo = dataLamThongKe.timTenCoSo(strMaCoSo);
            DTOThongKe temp = new DTOThongKe();
            temp.setMaCoSo(strMaCoSo);
            temp.setTenCoSo(tenCoSo);
            ThuChi thuChi = new ThuChi();
            thuChi.setGiaTri(dataLamThongKe.timDoanhThuCuaCoSo(strMaCoSo));
            temp.themThuChi(thuChi);
            thongKe.add(temp);
        }
        return thongKe;
    }
    public static void main(String args[]) {
        BLLThongKeDonHang a = new BLLThongKeDonHang();
        ArrayList<DTOThongKe> thongKe = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        b.add("CS001");
        b.add("CS003");
        thongKe = a.thongKeDoanhThu(b);
        thongKe.get(1).xuat();
    }
}
