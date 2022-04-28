
package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame{
    String[] thoiGian;
    int[] soHoaDon;
    double[] doanhThu;

    public LineChart(String[] thoiGian, int[] soHoaDon, double[] doanhThu) {
        super("Biểu Đồ Thống Kê");
        this.thoiGian = thoiGian;
        this.soHoaDon = soHoaDon;
        this.doanhThu = doanhThu;
    }

    public JFreeChart getLineChart() {
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Biều Đồ Đường Thống Kê Hóa Đơn Đã Bán",
                "Tháng/Năm", "Số Hóa Đơn Bán Ra",
                createDataset(),
                PlotOrientation.VERTICAL,
                false, false, false);

        return lineChart;
    }

    public JFreeChart getLine() {
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Biều Đồ Đường Thống Kê Doanh Thu",
                "Tháng/Năm", "Doanh Thu Hóa Đơn (VNĐ)",
                createData(),
                PlotOrientation.VERTICAL,
                false, false, false);

        return lineChart;
    }

    private DefaultCategoryDataset createData() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i=0; i<thoiGian.length;i++ ) {
            
            if(thoiGian[i]!=null && doanhThu[i]!=0){
                dataset.addValue(doanhThu[i], "Doanh Thu", thoiGian[i]);
            }
        }
        return dataset;
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i=0; i<thoiGian.length;i++ ) {
            
            if(thoiGian[i]!=null && soHoaDon[i]!=0){
                dataset.addValue(soHoaDon[i], "Hóa Đơn", thoiGian[i]);
            }
        }
        return dataset;
    }
}
