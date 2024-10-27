package GUI.CONTROLLER;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import BLL.BLLThongKeDonHang;
import DTO.DTOThongKe;
import DTO.ThuChi;

public class thongKeDoanhThuTheoThang extends JPanel {
    private static final long serialVersionUID = 1L;
    private BLLThongKeDonHang bllThongKeDonHang;

    public thongKeDoanhThuTheoThang(ArrayList<String> maCoSo, int thangBatDau, int namBatDau, int thangKetThuc, int namKetThuc) {
        this.setBounds(0, 0, 1200, 776);
        bllThongKeDonHang = new BLLThongKeDonHang();
        
        // Lấy dữ liệu chi phí theo tháng
        ArrayList<DTOThongKe> dsThongKe = bllThongKeDonHang.thongKeDoanhThuTheoThang(maCoSo, thangBatDau, namBatDau, thangKetThuc, namKetThuc);
        DefaultCategoryDataset dataset = createDataset(dsThongKe, thangBatDau, namBatDau, thangKetThuc, namKetThuc);

        // Tạo biểu đồ đường
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Doanh thu theo tháng", // Tiêu đề biểu đồ
                "Tháng/Năm", // Trục X
                "Doanh thu (đồng)", // Trục Y
                dataset, // Dữ liệu cho biểu đồ
                PlotOrientation.VERTICAL, // Hướng biểu đồ
                true, // Hiển thị chú thích
                true, // Hiển thị tooltips
                false // Không cần URL
        );

        // Định dạng trục y để không hiện số E
        CategoryPlot plot = lineChart.getCategoryPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));
        
        // Tùy chỉnh đường trong biểu đồ
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true); // Hiển thị các điểm dữ liệu
        plot.setRenderer(renderer);

        // Tạo panel chứa biểu đồ và thêm vào JPanel
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        this.add(chartPanel);
    }

    private DefaultCategoryDataset createDataset(ArrayList<DTOThongKe> dsThongKe, int thangBatDau, int namBatDau, int thangKetThuc, int namKetThuc) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (DTOThongKe thongKe : dsThongKe) {
            String tenCoSo = thongKe.getTenCoSo(); // Tên cơ sở
            for (ThuChi thuChi : thongKe.getThuChi()) {
                int nam = thuChi.getNam();
                int thang = thuChi.getThang();
                int chiPhi = thuChi.getGiaTri(); 

                // Chỉ thêm dữ liệu nếu nằm trong khoảng từ thangBatDau/namBatDau đến thangKetThuc/namKetThuc
                if ((nam > namBatDau || (nam == namBatDau && thang >= thangBatDau)) &&
                    (nam < namKetThuc || (nam == namKetThuc && thang <= thangKetThuc))) {
                    String thangNam = String.format("%02d/%d", thang, nam); // Định dạng Tháng/Năm
                    dataset.addValue(chiPhi, tenCoSo, thangNam);
                }
            }
        }
        return dataset;
    }
}
