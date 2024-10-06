/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citd.UI;

import com.citd.DAO.HocVienDAO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Stroke;
import java.awt.SystemColor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import com.citd.uitls.XImage;

/**
 *
 * @author Admin
 */
public class BieuDo {
    private JFrame frame;

    private DefaultPieDataset dataset = new DefaultPieDataset();
    // Create a set of charts
    private JFreeChart jfreeChart;
    // Create a set of panels that can show charts
    private ChartPanel chartPanel;
    // Create a panel container
    private JPanel panel;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BieuDo window = new BieuDo();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public BieuDo() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 513, 362); //set size frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        //Set gia tr cho PieChart
        int yeu = HocVienDAO.selectSoSVGioi();
        int trungBinh = HocVienDAO.selectSoSVTrungBinh();
        int kha = HocVienDAO.selectSoSVKha();
        int gioi = HocVienDAO.selectSoSVGioi();
        int xuatSac = HocVienDAO.selectSoSVXuatSac();
        
        
        dataset.setValue("Yếu", yeu);
        dataset.setValue("Trung Bình", trungBinh);
        dataset.setValue("Khá", kha);
        dataset.setValue("Giỏi", gioi);
        dataset.setValue("Xuất Sắc", xuatSac);
        
        jfreeChart = ChartFactory.createPieChart3D("BIỂU ĐỒ XẾP LOẠI HỌC SINH", dataset, true, true, false);
        jfreeChart.setBackgroundPaint(new Color(229, 255, 204));
        
        //jfreeChart.se
        PiePlot3D plot = (PiePlot3D) jfreeChart.getPlot();
        plot.setForegroundAlpha((float) 0.6f);
        //plot.setSectionPaint("Không đạt ", new Color(255, 0, 0)); //Set color cho PieChart
        //plot.setSectionPaint("Đạt", new Color(0, 255, 0)); //Set color cho PieChart
        //plot.setBackgroundPaint(new Color(255, 255, 255)); //Set background cho PieChart
        plot.setBackgroundPaint(new Color(204, 229, 255));
        plot.setOutlineStroke(new BasicStroke(3.0f));
        
        
        //panel Container chartPanel
        panel = new JPanel();
        panel.setBackground(new Color(229, 255, 204));
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        // add chartPanel PieChart vao panel
        chartPanel = new ChartPanel(jfreeChart);
        chartPanel.setBounds(29, 36, 442, 232); //set size PieChart
        panel.add(chartPanel);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setZoomAroundAnchor(true);
        chartPanel.setBackground(SystemColor.menu);
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(XImage.getAppIcon());
    }
}
