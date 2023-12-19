package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;
import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;
import service.HoaDonService;
import service.KhachHangService;
import service.NhanVienService;
import service.SachService;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class Pn_ThongKeQuanLy extends JPanel implements MouseListener, ActionListener {
	private static DefaultCategoryDataset dataset;
	/**
	 * Create the panel.
	 * 
	 */

	private JScrollPane sp_top10KH;
	private JTable table_top10KH;
	private DefaultTableModel tableModel_top10KH;
	private JTabbedPane tabbedPaneThongKe;
	private JPanel panelThongKeDoanhThu;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JLabel lblLocTheo;
	private JDateChooser dateChooserFromDoanhThu;
	private JDateChooser dateChooserToDoanhThu;
	private JComboBox comboBoxTieuChiDoanhThu;
	private JButton btnLocDT;
	private JLabel lblTongSoHoaDon;
	private JLabel lblDoanhThu;
	private ChartPanel chartPanel;
	private JLabel lblconCount;
	private JLabel lblIconMoney;
	private JLabel lblGiaTriTongHoaDon;
	private JLabel lblGiaTriDoanhThu;
	private JPanel panelThongKeSanPham;
	private JPanel panelTongSoLuongSach;
	private JLabel lblTongSoLuongSach;
	private JPanel panelTongSoLuongVanPhongPham;
	private JLabel lblTongSoLuongSachTonKho;
	private JPanel panelTongSoSachLoi;
	private JLabel lblTongSoSachBanDuoc;
	private JPanel panelLocSPBanChay;
	private JPanel panelSachBanChayNhat;
	private JLabel lblSanPhamBanChayNhat;
	private JButton btnLocSP;
	private JLabel lblFromThongKeSP;
	private JDateChooser dateChooserFromThongKeSP;
	private JDateChooser dateChooserToThongKeSP;
	private JLabel lblToThongKeSP;
	private JLabel lblBoLocSPBanChay;
	private JPanel panelThongKeKhachHang;
	private JPanel panel_top10;
	private JLabel lblTitleDSKHThanThiet;
	private JPanel panel_loc;
	private JLabel lblFromThongKeKH;
	private JLabel lblToThongKeKH;
	private JDateChooser dateChooserFromKH;
	private JDateChooser dateChooserToKH;
	private JLabel lblTenKHMuaNhieuNhat;
	private JButton btnLocKH;
	private JLabel lblSoTienKhachDaMua;
	private JLabel txtTieuDe;

	private JLabel lblValueSoLuongSach;
	private JLabel lblValueTongSoSachTonKho;
	private JLabel lblValueTongSoSachBanDuoc;
	private List<NhanVien> dsNV;
	private List<NhanVien> dsNV2;

	private List<KhachHang> dsKH;
	private List<KhachHang> dsKH2;
	private NhanVien nv2;
	private JLabel valueTop1NV;
	private JLabel lblTop1NV;
	private JLabel lblTenKHValue;
	private JLabel lblSoTienDaMuaValue;
	private JLabel lblmaSPTop1;
	private JLabel lblloaiSpTop1;
	private JLabel lblGiaBanTop1;
	private JLabel lblSoLuongBanTop1;
	private JPanel panelSanPhamTop1;
	private JLabel lblHinhAnhTop1;
	private JLabel lblMaSP;
	private JLabel lblTenSPTop1;
	private JLabel lblTenSanPham;
	private JLabel lblTenSP;
	private JLabel lblGiaBan;
	private JLabel lblSoLuongDaBan;
	private JPanel panelThongKeNhanVien;
	private JButton btnLocSP_1;
	private JLabel lblFromThongKeSP_1;
	private JDateChooser dateChooserFromThongKeNV;
	private JDateChooser dateChooserToThongKeNV;
	private JLabel lblToThongKeSP_1;
	private DefaultTableModel tableModel_NV;
	private JTable table_NV;
	private Component sp_tableNV;
	private JButton btnLocNV;
	private JLabel titleLocKH;
	private HoaDonService hoaDonService ;
	private NhanVienService nhanVienService;
	private KhachHangService khachHangService;
	private SachService sachService;

	private List<Sach> dsS;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private int port;
	private String host;

	public static JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU", "Tháng", "Doanh thu", createDataset(),
				PlotOrientation.VERTICAL, false, false, false);
		return barChart;
	}

	private static CategoryDataset createDataset() {
		dataset = new DefaultCategoryDataset();
		dataset.addValue(0, "Doanh thu", "1");
		dataset.addValue(0, "Doanh thu", "2");
		dataset.addValue(0, "Doanh thu", "3");
		dataset.addValue(0, "Doanh thu", "4");
		dataset.addValue(0, "Doanh thu", "5");
		dataset.addValue(0, "Doanh thu", "6");
		dataset.addValue(0, "Doanh thu", "7");
		dataset.addValue(0, "Doanh thu", "8");
		dataset.addValue(0, "Doanh thu", "9");
		dataset.addValue(0, "Doanh thu", "10");
		dataset.addValue(0, "Doanh thu", "11");
		dataset.addValue(0, "Doanh thu", "12");
		// dataset.setValue(5000, "Doanh thu", "9");
		return dataset;
	}

	public Pn_ThongKeQuanLy(int port, String host) throws RemoteException, MalformedURLException, NotBoundException {
		this.port = port;
		this.host = host;
		khachHangService = (KhachHangService) Naming.lookup("rmi://" + host + ":" + port + "/khachHangService");
		nhanVienService = (NhanVienService) Naming.lookup("rmi://" + host + ":" + port + "/nhanVienService");
		hoaDonService = (HoaDonService) Naming.lookup("rmi://" + host + ":" + port + "/hoaDonService");
		sachService = (SachService) Naming.lookup("rmi://" + host + ":" + port + "/sachService");
		
		setLayout(null);
		setSize(1500, 700);
		tabbedPaneThongKe = new JTabbedPane(JTabbedPane.TOP);
		// tabbedPaneThongKe.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPaneThongKe.setBounds(27, 79, 1450, 610);
		add(tabbedPaneThongKe);

		panelThongKeDoanhThu = new JPanel();
		panelThongKeDoanhThu
				.setBorder(
						new TitledBorder(
								new TitledBorder(
										new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
												new Color(160, 160, 160)),
										"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
								"", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelThongKeDoanhThu.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê doanh thu", null, panelThongKeDoanhThu, null);
		panelThongKeDoanhThu.setLayout(null);
		panelThongKeDoanhThu.setSize(900, 500);

		lblFrom = new JLabel("Từ ngày: ");
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFrom.setBounds(10, 52, 77, 35);
		panelThongKeDoanhThu.add(lblFrom);

		lblTo = new JLabel("Đến ngày: ");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTo.setBounds(10, 133, 77, 35);
		panelThongKeDoanhThu.add(lblTo);

		lblLocTheo = new JLabel("Lọc theo: ");
		lblLocTheo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLocTheo.setBounds(10, 207, 77, 32);
		panelThongKeDoanhThu.add(lblLocTheo);

		dateChooserFromDoanhThu = new JDateChooser();
		dateChooserFromDoanhThu.setBounds(97, 58, 252, 35);
		panelThongKeDoanhThu.add(dateChooserFromDoanhThu);

		dateChooserToDoanhThu = new JDateChooser();
		dateChooserToDoanhThu.setBounds(97, 133, 252, 35);
		panelThongKeDoanhThu.add(dateChooserToDoanhThu);

		comboBoxTieuChiDoanhThu = new JComboBox();
		comboBoxTieuChiDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxTieuChiDoanhThu.setBounds(97, 208, 154, 32);
		comboBoxTieuChiDoanhThu.addItem("3 tháng gần nhất");
		comboBoxTieuChiDoanhThu.addItem("6 tháng gần nhất");
		comboBoxTieuChiDoanhThu.addItem("9 tháng gần nhất");
		panelThongKeDoanhThu.add(comboBoxTieuChiDoanhThu);

		btnLocDT = new JButton("Lọc");
		btnLocDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLocDT.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
		// btnLocDT.setBackground(new Color(192, 192, 192));
		btnLocDT.setBounds(261, 208, 88, 32);
		panelThongKeDoanhThu.add(btnLocDT);

		lblTongSoHoaDon = new JLabel("Tổng số hóa đơn: ");
		lblTongSoHoaDon.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongSoHoaDon.setBounds(10, 389, 182, 25);
		panelThongKeDoanhThu.add(lblTongSoHoaDon);

		lblDoanhThu = new JLabel("Doanh thu: ");
		lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDoanhThu.setBounds(10, 470, 154, 25);
		panelThongKeDoanhThu.add(lblDoanhThu);

		chartPanel = new ChartPanel(createChart());
		chartPanel.setBackground(new Color(0, 206, 209));
		chartPanel.setBounds(359, 33, 1065, 490);
		panelThongKeDoanhThu.add(chartPanel);

		lblconCount = new JLabel("");
		lblconCount.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/count.png")));
		lblconCount.setBounds(10, 414, 48, 45);
		panelThongKeDoanhThu.add(lblconCount);

		lblIconMoney = new JLabel("");
		lblIconMoney.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/money.png")));
		lblIconMoney.setBounds(10, 506, 48, 34);
		panelThongKeDoanhThu.add(lblIconMoney);

		lblGiaTriTongHoaDon = new JLabel("0");
		lblGiaTriTongHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiaTriTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGiaTriTongHoaDon.setBounds(56, 427, 257, 25);
		panelThongKeDoanhThu.add(lblGiaTriTongHoaDon);

		lblGiaTriDoanhThu = new JLabel("0");
		lblGiaTriDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiaTriDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGiaTriDoanhThu.setBounds(56, 506, 257, 25);
		panelThongKeDoanhThu.add(lblGiaTriDoanhThu);

		lblTop1NV = new JLabel("Nhân viên bán được nhiều nhất: ");
		lblTop1NV.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTop1NV.setBounds(10, 282, 281, 25);
		panelThongKeDoanhThu.add(lblTop1NV);

		valueTop1NV = new JLabel("");
		valueTop1NV.setFont(new Font("Tahoma", Font.BOLD, 16));
		valueTop1NV.setBounds(10, 319, 330, 25);
		panelThongKeDoanhThu.add(valueTop1NV);

		// ----------------------------------------------------
		panelThongKeKhachHang = new JPanel();
		panelThongKeKhachHang.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê khách hàng", null, panelThongKeKhachHang, null);
		panelThongKeKhachHang.setLayout(null);

		panel_top10 = new JPanel();
		panel_top10.setBackground(new Color(255, 222, 173));
		panel_top10.setBounds(10, 232, 1425, 306);
		panelThongKeKhachHang.add(panel_top10);
		panel_top10.setLayout(null);

		lblTitleDSKHThanThiet = new JLabel("Danh sách top 10 khách hàng thân thiết");
		lblTitleDSKHThanThiet.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleDSKHThanThiet.setBounds(0, 0, 1425, 34);
		panel_top10.add(lblTitleDSKHThanThiet);
		lblTitleDSKHThanThiet.setFont(new Font("Tahoma", Font.BOLD, 16));

		String header_top10KH[] = { "STT", "Mã khách hàng", "Tên khách hàng", "Số tiền đã mua", "Số hóa đơn đã mua" };
		tableModel_top10KH = new DefaultTableModel(header_top10KH, 0);

		table_top10KH = new JTable(tableModel_top10KH);
		table_top10KH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_top10KH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sp_top10KH = new JScrollPane(table_top10KH, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_top10KH.setBounds(10, 45, 1415, 244);
		panel_top10.add(sp_top10KH);
		table_top10KH.getColumnModel().getColumn(0).setPreferredWidth(20);

		table_top10KH.getColumnModel().getColumn(0).setPreferredWidth(20);
		panel_loc = new JPanel();
		panel_loc.setBackground(new Color(255, 222, 173));
		panel_loc.setBounds(10, 11, 733, 210);
		panelThongKeKhachHang.add(panel_loc);
		panel_loc.setLayout(null);

		titleLocKH = new JLabel("Lọc khách hàng mua nhiều nhất theo thời gian");
		titleLocKH.setFont(new Font("Tahoma", Font.BOLD, 16));
		titleLocKH.setHorizontalAlignment(SwingConstants.CENTER);
		titleLocKH.setBounds(0, 0, 733, 57);
		panel_loc.add(titleLocKH);

		lblFromThongKeKH = new JLabel("Từ: ");
		lblFromThongKeKH.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFromThongKeKH.setBounds(41, 85, 50, 25);
		panel_loc.add(lblFromThongKeKH);

		lblToThongKeKH = new JLabel("Đến: ");
		lblToThongKeKH.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblToThongKeKH.setBounds(41, 145, 50, 25);
		panel_loc.add(lblToThongKeKH);

		dateChooserFromKH = new JDateChooser();
		dateChooserFromKH.setBounds(200, 85, 300, 32);
		panel_loc.add(dateChooserFromKH);

		dateChooserToKH = new JDateChooser();
		dateChooserToKH.setBounds(195, 145, 305, 32);
		panel_loc.add(dateChooserToKH);

		btnLocKH = new JButton("Lọc");
		btnLocKH.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLocKH.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
		btnLocKH.setBounds(570, 110, 89, 32);
		panel_loc.add(btnLocKH);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 222, 173));
		panel.setForeground(new Color(255, 128, 128));
		panel.setBounds(774, 11, 661, 109);
		panelThongKeKhachHang.add(panel);
		panel.setLayout(null);
		
				lblTenKHMuaNhieuNhat = new JLabel("Tên Khách Hàng mua nhiều nhất: ");
				lblTenKHMuaNhieuNhat.setBounds(10, 11, 280, 32);
				panel.add(lblTenKHMuaNhieuNhat);
				lblTenKHMuaNhieuNhat.setFont(new Font("Tahoma", Font.BOLD, 16));
				
						lblTenKHValue = new JLabel("...");
						lblTenKHValue.setHorizontalAlignment(SwingConstants.CENTER);
						lblTenKHValue.setBounds(241, 63, 410, 35);
						panel.add(lblTenKHValue);
						lblTenKHValue.setFont(new Font("Tahoma", Font.BOLD, 20));
						
						panel_1 = new JPanel();
						panel_1.setBackground(new Color(255, 222, 173));
						panel_1.setForeground(new Color(255, 128, 128));
						panel_1.setBounds(774, 131, 661, 84);
						panelThongKeKhachHang.add(panel_1);
						panel_1.setLayout(null);
						
								lblSoTienDaMuaValue = new JLabel("0");
								lblSoTienDaMuaValue.setHorizontalAlignment(SwingConstants.CENTER);
								lblSoTienDaMuaValue.setBounds(241, 38, 410, 35);
								panel_1.add(lblSoTienDaMuaValue);
								lblSoTienDaMuaValue.setFont(new Font("Tahoma", Font.BOLD, 20));
								
										lblSoTienKhachDaMua = new JLabel("Số tiền đã mua: ");
										lblSoTienKhachDaMua.setBounds(10, 11, 250, 25);
										panel_1.add(lblSoTienKhachDaMua);
										lblSoTienKhachDaMua.setFont(new Font("Tahoma", Font.BOLD, 16));

		// -----------------------------------------------

		panelThongKeSanPham = new JPanel();
		panelThongKeSanPham.setBackground(new Color(72, 209, 204));
		tabbedPaneThongKe.addTab("Thống kê sản phẩm", null, panelThongKeSanPham, null);
		panelThongKeSanPham.setLayout(null);

		panelTongSoLuongSach = new JPanel();
		panelTongSoLuongSach.setBackground(new Color(255, 222, 173));
		panelTongSoLuongSach.setBounds(60, 29, 350, 152);
		panelThongKeSanPham.add(panelTongSoLuongSach);
		panelTongSoLuongSach.setLayout(null);

		lblTongSoLuongSach = new JLabel("    Tổng số lượng sách: \r\n");
		lblTongSoLuongSach.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/books.png")));
		lblTongSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoLuongSach.setBounds(0, 0, 350, 36);
		panelTongSoLuongSach.add(lblTongSoLuongSach);

		lblValueSoLuongSach = new JLabel("New label");
		lblValueSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValueSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueSoLuongSach.setBounds(0, 46, 350, 83);
		panelTongSoLuongSach.add(lblValueSoLuongSach);

		panelTongSoLuongVanPhongPham = new JPanel();
		panelTongSoLuongVanPhongPham.setBackground(new Color(255, 222, 173));
		panelTongSoLuongVanPhongPham.setBounds(1030, 29, 350, 152);
		panelThongKeSanPham.add(panelTongSoLuongVanPhongPham);
		panelTongSoLuongVanPhongPham.setLayout(null);

		lblTongSoLuongSachTonKho = new JLabel("Tổng số lượng sách tồn kho:");
		lblTongSoLuongSachTonKho.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/stationery.png")));
		lblTongSoLuongSachTonKho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoLuongSachTonKho.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoLuongSachTonKho.setBounds(0, 0, 350, 37);
		panelTongSoLuongVanPhongPham.add(lblTongSoLuongSachTonKho);

		lblValueTongSoSachTonKho = new JLabel("New label");
		lblValueTongSoSachTonKho.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueTongSoSachTonKho.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValueTongSoSachTonKho.setBounds(0, 48, 350, 93);
		panelTongSoLuongVanPhongPham.add(lblValueTongSoSachTonKho);

		panelTongSoSachLoi = new JPanel();
		panelTongSoSachLoi.setBackground(new Color(255, 222, 173));
		panelTongSoSachLoi.setBounds(560, 29, 350, 152);
		panelThongKeSanPham.add(panelTongSoSachLoi);
		panelTongSoSachLoi.setLayout(null);

		lblTongSoSachBanDuoc = new JLabel("    Tổng số sách bán được: ");
		lblTongSoSachBanDuoc.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/sachloi.png")));
		lblTongSoSachBanDuoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoSachBanDuoc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoSachBanDuoc.setBounds(0, 0, 350, 40);
		panelTongSoSachLoi.add(lblTongSoSachBanDuoc);

		lblValueTongSoSachBanDuoc = new JLabel("New label");
		lblValueTongSoSachBanDuoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueTongSoSachBanDuoc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValueTongSoSachBanDuoc.setBounds(0, 44, 350, 83);
		panelTongSoSachLoi.add(lblValueTongSoSachBanDuoc);

		panelLocSPBanChay = new JPanel();
		panelLocSPBanChay.setBackground(new Color(255, 222, 173));
		panelLocSPBanChay.setBounds(10, 196, 1425, 346);
		panelThongKeSanPham.add(panelLocSPBanChay);
		panelLocSPBanChay.setLayout(null);

		panelSachBanChayNhat = new JPanel();
		panelSachBanChayNhat.setBounds(654, 11, 761, 324);
		panelLocSPBanChay.add(panelSachBanChayNhat);
		panelSachBanChayNhat.setLayout(null);

		lblSanPhamBanChayNhat = new JLabel("    Sản phẩm bán chạy nhất: ");
		lblSanPhamBanChayNhat.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/sachchay.png")));
		lblSanPhamBanChayNhat.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPhamBanChayNhat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSanPhamBanChayNhat.setBounds(0, 0, 751, 42);
		panelSachBanChayNhat.add(lblSanPhamBanChayNhat);

		lblmaSPTop1 = new JLabel("...");
		lblmaSPTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblmaSPTop1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblmaSPTop1.setBounds(437, 80, 274, 25);
		panelSachBanChayNhat.add(lblmaSPTop1);

		lblloaiSpTop1 = new JLabel("...");
		lblloaiSpTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblloaiSpTop1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblloaiSpTop1.setBounds(437, 130, 274, 25);
		panelSachBanChayNhat.add(lblloaiSpTop1);

		lblGiaBanTop1 = new JLabel("...");
		lblGiaBanTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiaBanTop1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGiaBanTop1.setBounds(437, 230, 274, 25);
		panelSachBanChayNhat.add(lblGiaBanTop1);

		lblSoLuongBanTop1 = new JLabel("...");
		lblSoLuongBanTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuongBanTop1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoLuongBanTop1.setBounds(437, 280, 274, 25);
		panelSachBanChayNhat.add(lblSoLuongBanTop1);

		panelSanPhamTop1 = new JPanel();
		panelSanPhamTop1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSanPhamTop1.setBounds(10, 53, 159, 260);
		panelSachBanChayNhat.add(panelSanPhamTop1);
		panelSanPhamTop1.setLayout(null);

		lblHinhAnhTop1 = new JLabel("Hình ảnh");
		lblHinhAnhTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHinhAnhTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnhTop1.setBounds(0, 0, 159, 260);
		panelSanPhamTop1.add(lblHinhAnhTop1);

		lblMaSP = new JLabel("Mã sản phẩm: ");
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaSP.setBounds(205, 80, 222, 25);
		panelSachBanChayNhat.add(lblMaSP);

		lblTenSPTop1 = new JLabel("...");
		lblTenSPTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenSPTop1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenSPTop1.setBounds(437, 180, 274, 25);
		panelSachBanChayNhat.add(lblTenSPTop1);

		lblTenSanPham = new JLabel("Loại sản phẩm: ");
		lblTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenSanPham.setBounds(205, 130, 222, 25);
		panelSachBanChayNhat.add(lblTenSanPham);

		lblTenSP = new JLabel("Tên sản phẩm: ");
		lblTenSP.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenSP.setBounds(205, 180, 222, 25);
		panelSachBanChayNhat.add(lblTenSP);

		lblGiaBan = new JLabel("Giá bán: ");
		lblGiaBan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGiaBan.setBounds(205, 230, 89, 25);
		panelSachBanChayNhat.add(lblGiaBan);

		lblSoLuongDaBan = new JLabel("Số lượng đã bán: ");
		lblSoLuongDaBan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoLuongDaBan.setBounds(205, 280, 222, 25);
		panelSachBanChayNhat.add(lblSoLuongDaBan);

		btnLocSP = new JButton("Lọc");
		btnLocSP.setBounds(300, 259, 120, 35);
		panelLocSPBanChay.add(btnLocSP);
		btnLocSP.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
		btnLocSP.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblFromThongKeSP = new JLabel("Từ: ");
		lblFromThongKeSP.setBounds(110, 103, 35, 25);
		panelLocSPBanChay.add(lblFromThongKeSP);
		lblFromThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 16));

		dateChooserFromThongKeSP = new JDateChooser();
		dateChooserFromThongKeSP.setBounds(231, 96, 280, 33);
		panelLocSPBanChay.add(dateChooserFromThongKeSP);

		dateChooserToThongKeSP = new JDateChooser();
		dateChooserToThongKeSP.setBounds(231, 184, 280, 33);
		panelLocSPBanChay.add(dateChooserToThongKeSP);

		lblToThongKeSP = new JLabel("Đến: ");
		lblToThongKeSP.setBounds(111, 196, 46, 25);
		panelLocSPBanChay.add(lblToThongKeSP);
		lblToThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblBoLocSPBanChay = new JLabel("Sản phẩm bán chạy nhất");
		lblBoLocSPBanChay.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoLocSPBanChay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBoLocSPBanChay.setForeground(new Color(0, 0, 0));
		lblBoLocSPBanChay.setBackground(new Color(255, 0, 0));
		lblBoLocSPBanChay.setBounds(0, 33, 644, 26);
		panelLocSPBanChay.add(lblBoLocSPBanChay);

		panelThongKeNhanVien = new JPanel();
		panelThongKeNhanVien.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê nhân viên", null, panelThongKeNhanVien, null);
		panelThongKeNhanVien.setLayout(null);

		String header_NhanVien[] = { "STT", "Mã nhân viên", "Tên nhân viên", "Số tiền đã bán", "Số hóa đơn đã bán" };
		tableModel_NV = new DefaultTableModel(header_NhanVien, 0);

		table_NV = new JTable(tableModel_NV);
		sp_tableNV = new JScrollPane(table_NV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_tableNV.setBounds(10, 174, 1425, 356);
		panelThongKeNhanVien.add(sp_tableNV);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 222, 173));
		panel_2.setBounds(193, 90, 1045, 70);
		panelThongKeNhanVien.add(panel_2);
		panel_2.setLayout(null);
		
				dateChooserFromThongKeNV = new JDateChooser();
				dateChooserFromThongKeNV.setBounds(180, 18, 270, 35);
				panel_2.add(dateChooserFromThongKeNV);
				
						lblFromThongKeSP_1 = new JLabel("Từ ngày:");
						lblFromThongKeSP_1.setBounds(70, 18, 100, 35);
						panel_2.add(lblFromThongKeSP_1);
						lblFromThongKeSP_1.setFont(new Font("Tahoma", Font.BOLD, 13));
						
								dateChooserToThongKeNV = new JDateChooser();
								dateChooserToThongKeNV.setBounds(600, 18, 270, 35);
								panel_2.add(dateChooserToThongKeNV);
								
										lblToThongKeSP_1 = new JLabel("Đến ngày: ");
										lblToThongKeSP_1.setBounds(500, 18, 80, 35);
										panel_2.add(lblToThongKeSP_1);
										lblToThongKeSP_1.setFont(new Font("Tahoma", Font.BOLD, 13));
										
												btnLocNV = new JButton("Lọc");
												btnLocNV.setBounds(943, 18, 92, 35);
												panel_2.add(btnLocNV);
												btnLocNV.setFont(new Font("Tahoma", Font.BOLD, 13));
												
												panel_3 = new JPanel();
												panel_3.setBackground(new Color(255, 222, 173));
												panel_3.setBounds(10, 11, 1425, 62);
												panelThongKeNhanVien.add(panel_3);
												panel_3.setLayout(null);
												
												JLabel lblTitle = new JLabel("Tổng số hoá đơn của nhân viên bán được ");
												lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
												lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
												lblTitle.setBounds(10, 5, 1405, 46);
												panel_3.add(lblTitle);
												btnLocNV.addActionListener(this);
		btnLocSP.addActionListener(this);

		txtTieuDe = new JLabel("THỐNG KÊ");
		txtTieuDe.setForeground(new Color(0, 191, 255));
		txtTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		txtTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
		txtTieuDe.setBounds(0, 11, 1490, 51);
		add(txtTieuDe);

		btnLocDT.addActionListener(this);
		btnLocKH.addActionListener(this);
		btnLocSP.addActionListener(this);
//		setChart();
	
		if (sachService.getSoLuongSachTon() > 0) {
			lblValueSoLuongSach.setText(String.valueOf(sachService.getSoLuongSachTon()));
		}
		if(sachService.getTongSoSachBanDuoc() > 0) {
			lblValueTongSoSachBanDuoc.setText(String.valueOf(sachService.getTongSoSachBanDuoc()));
		}
		int tongSach = sachService.getSoLuongSachTon();
		int tongBan = sachService.getTongSoSachBanDuoc();
		int tonKho = tongSach - tongBan;
		lblValueTongSoSachTonKho.setText(String.valueOf(tonKho));
	}

	public void setBieuDoVe0() {
		dataset.setValue(0, "Doanh thu", "1");
		dataset.setValue(0, "Doanh thu", "2");
		dataset.setValue(0, "Doanh thu", "3");
		dataset.setValue(0, "Doanh thu", "4");
		dataset.setValue(0, "Doanh thu", "5");
		dataset.setValue(0, "Doanh thu", "6");
		dataset.setValue(0, "Doanh thu", "7");
		dataset.setValue(0, "Doanh thu", "8");
		dataset.setValue(0, "Doanh thu", "9");
		dataset.setValue(0, "Doanh thu", "10");
		dataset.setValue(0, "Doanh thu", "11");
		dataset.setValue(0, "Doanh thu", "12");
	}

	public void setChart() throws RemoteException {
		int count = 0;
		LocalDate nowMinus = null;
		LocalDate now = LocalDate.now();
		if (comboBoxTieuChiDoanhThu.getSelectedIndex() == 0) {
			count = 3;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				dataset.setValue(hoaDonService.getDoanhThu(nowMinus, now), "Doanh thu",
						String.valueOf(nowMinus.getMonthValue()));
				now = nowMinus;
				count--;
			}
		}

		else if (comboBoxTieuChiDoanhThu.getSelectedIndex() == 1) {
			count = 6;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				dataset.setValue(hoaDonService.getDoanhThu(nowMinus, now), "Doanh thu",
						String.valueOf(nowMinus.getMonthValue()));
				now = nowMinus;
				count--;
			}
		}

		else {
			count = 9;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				dataset.setValue(hoaDonService.getDoanhThu(nowMinus, now), "Doanh thu",
						String.valueOf(nowMinus.getMonthValue()));
				now = nowMinus;
				count--;
			}
		}

	}

	public LocalDate getNgayFromJDateChooser(JDateChooser ngay) {
		if (ngay.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày");
			return null;
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String dayKT = sdf2.format(ngay.getDate());
		String dateKT[] = dayKT.split("-");
		int namKT = Integer.parseInt(dateKT[0]);
		int thangKT = Integer.parseInt(dateKT[1]);
		int ngayKT = Integer.parseInt(dateKT[2]);
		LocalDate lcDateKT = LocalDate.of(namKT, thangKT, ngayKT);
		return lcDateKT;
	}

	public void xoaHetDuLieuTableTop10KH() {
		DefaultTableModel dtm = (DefaultTableModel) table_top10KH.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void DocDuLieuTuArrayListTop10VaoModel() throws Exception {
		dsKH = new ArrayList<>();
		
		// if (getNgayFromJDateChooser(dateChooserFromKH) != null &&
		// getNgayFromJDateChooser(dateChooserToKH) != null) {
		dsKH = khachHangService.getTop10KHThanThiet(getNgayFromJDateChooser(dateChooserFromKH),
				getNgayFromJDateChooser(dateChooserToKH));
		int i = 1;
		for (KhachHang kh : dsKH) {
			// System.out.println(kh.getHoTenKhachHang());
			tableModel_top10KH.addRow(new Object[] { i++, kh.getMaKhachHang(),
					khachHangService.timKhachHangTheoMa(kh.getMaKhachHang()).getHoTenKhachHang(),
					khachHangService.getTongTienCuaKhachHangTheoMa(getNgayFromJDateChooser(dateChooserFromKH),
							getNgayFromJDateChooser(dateChooserToKH), kh.getMaKhachHang()),
					khachHangService.getSoLuongHoaDonCuaKhachHangTheoMa(getNgayFromJDateChooser(dateChooserFromKH),
							getNgayFromJDateChooser(dateChooserToKH), kh.getMaKhachHang()) });
		}
		// }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnLocDT)) {
			if (getNgayFromJDateChooser(dateChooserFromDoanhThu) != null
					&& getNgayFromJDateChooser(dateChooserToDoanhThu) != null) {
				try {
					if (nhanVienService.getNhanVienBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
							getNgayFromJDateChooser(dateChooserToDoanhThu)) != null) {
						String nv = (String) nhanVienService.getNhanVienBanNhieuNhatTheoNgayTuChon(
								getNgayFromJDateChooser(dateChooserFromDoanhThu),
								getNgayFromJDateChooser(dateChooserToDoanhThu));
						valueTop1NV.setText(nhanVienService.timNhanVienTheoMa(nv).getHoTenNhanVien());
						setBieuDoVe0();
						try {
							setChart();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try {
				if (hoaDonService.getDoanhThu(getNgayFromJDateChooser(dateChooserFromDoanhThu),
						getNgayFromJDateChooser(dateChooserToDoanhThu)) > 0) {
					lblGiaTriDoanhThu
							.setText(String.valueOf(hoaDonService.getDoanhThu(getNgayFromJDateChooser(dateChooserFromDoanhThu),
									getNgayFromJDateChooser(dateChooserToDoanhThu))) + "đ");
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (hoaDonService.getSoLuongHoaDon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
						getNgayFromJDateChooser(dateChooserToDoanhThu)) > 0) {
					lblGiaTriTongHoaDon.setText(
							String.valueOf(hoaDonService.getSoLuongHoaDon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
									getNgayFromJDateChooser(dateChooserToDoanhThu))));
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (obj.equals(btnLocKH)) {
			if (getNgayFromJDateChooser(dateChooserFromKH) != null
					&& getNgayFromJDateChooser(dateChooserToKH) != null) {
				try {
					if (khachHangService.getKhachHangMuaNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromKH),
							getNgayFromJDateChooser(dateChooserToKH)) != null) {
						String kh = (String) khachHangService.getKhachHangMuaNhieuNhatTheoNgayTuChon(
								getNgayFromJDateChooser(dateChooserFromKH), getNgayFromJDateChooser(dateChooserToKH));
						lblTenKHValue.setText(khachHangService.timKhachHangTheoMa(kh).getHoTenKhachHang());
						lblSoTienDaMuaValue.setText(String.valueOf(khachHangService.getTongTienCuaKhachHangTop1(
								getNgayFromJDateChooser(dateChooserFromKH), getNgayFromJDateChooser(dateChooserToKH)))
								+ "đ");

					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			xoaHetDuLieuTableTop10KH();
			try {
				DocDuLieuTuArrayListTop10VaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (obj.equals(btnLocSP)) {
			if (getNgayFromJDateChooser(dateChooserFromThongKeSP) != null
					&& getNgayFromJDateChooser(dateChooserToThongKeSP) != null) {
				try {
					if (sachService.getSanPhamBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromThongKeSP),
							getNgayFromJDateChooser(dateChooserToThongKeSP)) != null) {
						dsS = sachService.getSanPhamBanNhieuNhatTheoNgayTuChon(
								getNgayFromJDateChooser(dateChooserFromThongKeSP),
								getNgayFromJDateChooser(dateChooserToThongKeSP));
						for (Sach s : dsS) {
							File file = new File("");
							lblmaSPTop1.setText(sachService.timSachTheoMa(s.getMaSach()).getMaSach());

							lblloaiSpTop1.setText(sachService.timSachTheoMa(s.getMaSach()).getTheLoaiSach().getTenLoai());
							lblGiaBanTop1.setText(String
									.valueOf(sachService.timSachTheoMa(s.getMaSach()).getGiaNhap()
											+ sachService.timSachTheoMa(s.getMaSach()).getGiaNhap() * 10 / 100)
									+ "đ");

							lblSoLuongBanTop1.setText(String.valueOf(sachService.getSoLuongBanCuaSanPhamChayNhat(
									getNgayFromJDateChooser(dateChooserFromThongKeSP),
									getNgayFromJDateChooser(dateChooserToThongKeSP))));

							lblTenSPTop1.setText(sachService.timSachTheoMa(s.getMaSach()).getTenSach());
							String hinhAnh = file.getAbsolutePath() + "\\hinhAnhHieuSach\\" + s.getHinhAnh();
							lblHinhAnhTop1.setIcon(setSizeImageIconString(hinhAnh, lblHinhAnhTop1.getWidth(), lblHinhAnhTop1.getHeight()));

						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (obj.equals(btnLocNV)) {
			if (getNgayFromJDateChooser(dateChooserFromThongKeNV) != null
					&& getNgayFromJDateChooser(dateChooserFromThongKeNV) != null) {
				try {
					if (nhanVienService.getDoanhThuCuaNhanVien(getNgayFromJDateChooser(dateChooserFromThongKeNV),
							getNgayFromJDateChooser(dateChooserToThongKeNV)) != null) {
						dsNV = nhanVienService.getDoanhThuCuaNhanVien(getNgayFromJDateChooser(dateChooserFromThongKeNV),
								getNgayFromJDateChooser(dateChooserToThongKeNV));

						int i = 1;
						for (NhanVien nv : dsNV) {
							tableModel_NV.addRow(new Object[] { i++, nv.getMaNhanVien(), nv.getHoTenNhanVien(),
									hoaDonService.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromThongKeNV),
											getNgayFromJDateChooser(dateChooserToThongKeNV), nv.getMaNhanVien()),
									hoaDonService.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromThongKeNV),
											getNgayFromJDateChooser(dateChooserToThongKeNV), nv.getMaNhanVien()) });
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}
	public ImageIcon setSizeImageIconString(String s, int width, int height) {
		ImageIcon image = new ImageIcon(s);
		Image imageSet = image.getImage();
		imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		image = new ImageIcon(imageSet);
		return image;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
