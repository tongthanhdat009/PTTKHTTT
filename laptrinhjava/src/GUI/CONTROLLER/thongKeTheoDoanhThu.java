package GUI.CONTROLLER;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import BLL.BLLThongKeDonHang;
import DTO.DTOThongKe;
import DTO.ThuChi;

public class thongKeTheoDoanhThu extends JPanel {
    private BLLThongKeDonHang bllThongKeDonHang;

    public thongKeTheoDoanhThu(ArrayList<String> maCoSo) {
        this.setBounds(0, 0, 1200, 776);
        bllThongKeDonHang = new BLLThongKeDonHang();
        ArrayList<DTOThongKe> dsThongKe = bllThongKeDonHang.thongKeDoanhThu(maCoSo);
        DefaultPieDataset dataset = createDataset(dsThongKe);

        // Tạo biểu đồ tròn
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Doanh thu theo cơ sở", // Tiêu đề biểu đồ
                dataset, // Dữ liệu cho biểu đồ
                true, // Hiển thị chú thích
                true, // Hiển thị tooltips
                false // Không cần URL
        );

        // Tạo panel chứa biểu đồ và thêm vào JPanel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        this.add(chartPanel);
    }

    private DefaultPieDataset createDataset(ArrayList<DTOThongKe> dsThongKe) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (DTOThongKe thongKe : dsThongKe) {
            String tenCoSo = thongKe.getTenCoSo();
            double tongGiaTri = 0;
            for (ThuChi thuChi : thongKe.getThuChi()) {
                tongGiaTri += thuChi.getGiaTri();
            }
            dataset.setValue(tenCoSo, tongGiaTri); // Thêm tổng doanh thu của từng cơ sở vào dataset
        }
        return dataset;
    }
}
