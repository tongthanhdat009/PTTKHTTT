package GUI.CONTROLLER;

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

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class thongKeChiPhiTheoNam extends JPanel {
    private static final long serialVersionUID = 1L;
    private BLLThongKeDonHang bllThongKeDonHang;

    public thongKeChiPhiTheoNam(ArrayList<String> maCoSo, int namBatDau, int namKetThuc) {
        this.setBounds(0, 0, 1200, 776);
        bllThongKeDonHang = new BLLThongKeDonHang();
        
        // Lấy dữ liệu doanh thu theo năm
        ArrayList<DTOThongKe> dsThongKe = bllThongKeDonHang.thongKeChiPhiTheoNam(maCoSo, namBatDau, namKetThuc);
        DefaultCategoryDataset dataset = createDataset(dsThongKe, namBatDau, namKetThuc);

        // Tạo biểu đồ đường
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Chi Phí theo năm", // Tiêu đề biểu đồ
                "Năm", // Trục X
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

    private DefaultCategoryDataset createDataset(ArrayList<DTOThongKe> dsThongKe, int namBatDau, int namKetThuc) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (DTOThongKe thongKe : dsThongKe) {
            String tenCoSo = thongKe.getTenCoSo(); // Tên cơ sở
            for (int j = 0; j < thongKe.getThuChi().size(); j++) {
                int nam = thongKe.getThuChi().get(j).getNam(); // Lấy năm từ đối tượng ThuChi
                int doanhThu = thongKe.getThuChi().get(j).getGiaTri(); // Lấy doanh thu theo năm
                
                // Chỉ thêm dữ liệu nếu năm nằm trong khoảng từ namBatDau đến namKetThuc
                if (nam >= namBatDau && nam <= namKetThuc) {
                    dataset.addValue(doanhThu, tenCoSo, String.valueOf(nam));
                }
            }
        }
        return dataset;
    }

}
