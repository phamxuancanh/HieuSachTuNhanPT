package gui;

import java.awt.Font;
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
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import com.toedter.calendar.JDayChooser;

import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;
import entity.TaiKhoan;
import service.ChiTietHoaDonService;
import service.HoaDonService;
import service.NhanVienService;
import service.SachService;

import javax.swing.border.LineBorder;

public class Pn_ThongKeNhanVien extends JPanel implements ActionListener, MouseListener {

	/**
	 * Create the panel.
	 */
	private static DefaultCategoryDataset dataset;
	private JTabbedPane tabbedPaneThongKe;
	private JPanel panelThongKeSanPham;
	private JPanel panelTongSoLuongSach;
	private JLabel lblTongSoLuongSach;
	private JPanel panelTongSoLuongVanPhongPham;
	private JLabel lblTongSoSachTonKho;
	private JPanel panelTongSoSachLoi;
	private JLabel lblTongSoSachBanDuoc;
	private JPanel panelLocSPBanChay;
	private JPanel panel_ThongKeDoanhThuBanThan;
	private ChartPanel chartPanel;
	private JLabel lblTongHoaDonBanDuoc;
	private JLabel lblTongDoanhThu;
	private JLabel lblTo;
	private JDateChooser dateChooserFromDoanhThu;
	private JLabel lblFrom;
	private JComboBox comboBoxTieuChiDoanhThu;
	private JDateChooser dateChooserToDoanhThu;
	private JLabel txtTieuDe;
	private JButton btnLocDoanhThuNV;
	// private DefaultCategoryDataset dataset;

	private List<NhanVien> dsNV;
	private List<NhanVien> dsNV2;

	private List<KhachHang> dsKH;
	private List<KhachHang> dsKH2;
	private JLabel lblValueSoLuongSach;
	private JLabel lblValueTongSoSachBanDuoc;
	private JLabel lblValueTongSoSachTonKho;
	private JLabel lblGiaTriDoanhThu;
	private JLabel lblGiaTriTongHoaDon;

	private NhanVien nv;
	private String tenNV;
	private String maNV;
	private HoaDonService hoaDonService;
	private NhanVienService nhanVienService;
	private SachService sachService;
	
	private List<Sach> dsS;
	private JPanel panelSachBanChayNhat;
	private JLabel lblSanPhamBanChayNhat;
	private JLabel lblMaSPTop1;
	private JLabel lblTheLoaiSPTop1;
	private JLabel lblGiaSPTop1;
	private JLabel lblSoLuongSPTop1;
	private JPanel panelSanPhamTop1;
	private JLabel lblHinhAnhTop1;
	private JLabel lblMaSP;
	private JLabel lblTenSPTop1;
	private JLabel lblTheLoai;
	private JLabel lblTenSP;
	private JLabel lblGiaBan;
	private JLabel lblSoLuongDaBan;
	private JLabel lblBoLocSPBanChay;
	private JDateChooser dateChooserFromThongKeSP;
	private JLabel lblFromThongKeSP;
	private JLabel lblToThongKeSP;
	private JDateChooser dateChooserToThongKeSP;
	private JButton btnLocSanPham;
	private JLabel lblTieuChi;
	private int port;
	private String host;
	private File file;
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
		return dataset;
	}

	public Pn_ThongKeNhanVien(int port, String host)  throws RemoteException, MalformedURLException, NotBoundException{
		this.port = port;
		this.host = host;
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

		panelThongKeSanPham = new JPanel();
		panelThongKeSanPham.setBackground(new Color(0, 206, 209));
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
		lblValueSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblValueSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueSoLuongSach.setBounds(0, 47, 350, 86);
		panelTongSoLuongSach.add(lblValueSoLuongSach);

		panelTongSoLuongVanPhongPham = new JPanel();
		panelTongSoLuongVanPhongPham.setBackground(new Color(255, 222, 173));
		panelTongSoLuongVanPhongPham.setBounds(1030, 29, 350, 152);
		panelThongKeSanPham.add(panelTongSoLuongVanPhongPham);
		panelTongSoLuongVanPhongPham.setLayout(null);

		lblTongSoSachTonKho = new JLabel("Tổng số sách tồn kho:");
		lblTongSoSachTonKho.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/stationery.png")));
		lblTongSoSachTonKho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoSachTonKho.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoSachTonKho.setBounds(0, 0, 350, 37);
		panelTongSoLuongVanPhongPham.add(lblTongSoSachTonKho);

		lblValueTongSoSachTonKho = new JLabel("New label");
		lblValueTongSoSachTonKho.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueTongSoSachTonKho.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblValueTongSoSachTonKho.setBounds(0, 48, 350, 86);
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
		lblValueTongSoSachBanDuoc.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblValueTongSoSachBanDuoc.setBounds(0, 47, 350, 86);
		panelTongSoSachLoi.add(lblValueTongSoSachBanDuoc);

		panelLocSPBanChay = new JPanel();
		panelLocSPBanChay.setBackground(new Color(255, 222, 173));
		panelLocSPBanChay.setBounds(10, 196, 1425, 346);
		panelThongKeSanPham.add(panelLocSPBanChay);
		panelLocSPBanChay.setLayout(null);
		
		panelSachBanChayNhat = new JPanel();
		panelSachBanChayNhat.setLayout(null);
		panelSachBanChayNhat.setBounds(654, 11, 761, 324);
		panelLocSPBanChay.add(panelSachBanChayNhat);
		
		lblSanPhamBanChayNhat = new JLabel("    Sản phẩm bán chạy nhất: ");
		lblSanPhamBanChayNhat.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPhamBanChayNhat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSanPhamBanChayNhat.setBounds(0, 0, 751, 42);
		panelSachBanChayNhat.add(lblSanPhamBanChayNhat);
		
		lblMaSPTop1 = new JLabel("...");
		lblMaSPTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaSPTop1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaSPTop1.setBounds(437, 80, 274, 25);
		panelSachBanChayNhat.add(lblMaSPTop1);
		
		lblTheLoaiSPTop1 = new JLabel("...");
		lblTheLoaiSPTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheLoaiSPTop1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTheLoaiSPTop1.setBounds(437, 130, 274, 25);
		panelSachBanChayNhat.add(lblTheLoaiSPTop1);
		
		lblGiaSPTop1 = new JLabel("...");
		lblGiaSPTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiaSPTop1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGiaSPTop1.setBounds(437, 230, 274, 25);
		panelSachBanChayNhat.add(lblGiaSPTop1);
		
		lblSoLuongSPTop1 = new JLabel("...");
		lblSoLuongSPTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuongSPTop1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoLuongSPTop1.setBounds(437, 280, 274, 25);
		panelSachBanChayNhat.add(lblSoLuongSPTop1);
		
		panelSanPhamTop1 = new JPanel();
		panelSanPhamTop1.setLayout(null);
		panelSanPhamTop1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSanPhamTop1.setBounds(10, 53, 159, 260);
		panelSachBanChayNhat.add(panelSanPhamTop1);
		
		lblHinhAnhTop1 = new JLabel("Hình ảnh");
		lblHinhAnhTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnhTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		
		lblTheLoai = new JLabel("Thể loại:");
		lblTheLoai.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTheLoai.setBounds(205, 130, 222, 25);
		panelSachBanChayNhat.add(lblTheLoai);
		
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
		
		lblBoLocSPBanChay = new JLabel("Sản phẩm bán chạy nhất");
		lblBoLocSPBanChay.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoLocSPBanChay.setForeground(Color.BLACK);
		lblBoLocSPBanChay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBoLocSPBanChay.setBackground(Color.RED);
		lblBoLocSPBanChay.setBounds(10, 11, 644, 26);
		panelLocSPBanChay.add(lblBoLocSPBanChay);
		
		dateChooserFromThongKeSP = new JDateChooser();
		dateChooserFromThongKeSP.setBounds(241, 74, 280, 33);
		panelLocSPBanChay.add(dateChooserFromThongKeSP);
		
		lblFromThongKeSP = new JLabel("Từ: ");
		lblFromThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFromThongKeSP.setBounds(120, 74, 35, 25);
		panelLocSPBanChay.add(lblFromThongKeSP);
		
		lblToThongKeSP = new JLabel("Đến: ");
		lblToThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblToThongKeSP.setBounds(121, 174, 46, 25);
		panelLocSPBanChay.add(lblToThongKeSP);
		
		dateChooserToThongKeSP = new JDateChooser();
		dateChooserToThongKeSP.setBounds(241, 174, 280, 33);
		panelLocSPBanChay.add(dateChooserToThongKeSP);
		
		btnLocSanPham = new JButton("Lọc");
		btnLocSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLocSanPham.setBounds(310, 237, 120, 35);
		panelLocSPBanChay.add(btnLocSanPham);

		panel_ThongKeDoanhThuBanThan = new JPanel();
		panel_ThongKeDoanhThuBanThan.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê doanh thu", null, panel_ThongKeDoanhThuBanThan, null);
		panel_ThongKeDoanhThuBanThan.setLayout(null);

		chartPanel = new ChartPanel(createChart());
		chartPanel.setBackground(new Color(0, 206, 209));
		chartPanel.setBounds(359, 33, 1065, 490);
		panel_ThongKeDoanhThuBanThan.add(chartPanel);
		lblTongDoanhThu = new JLabel("Tổng doanh thu: ");
		lblTongDoanhThu.setIcon(new ImageIcon(Pn_ThongKeNhanVien.class.getResource("/gui/icon/count.png")));
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongDoanhThu.setBounds(44, 316, 209, 28);
		panel_ThongKeDoanhThuBanThan.add(lblTongDoanhThu);

		lblTongHoaDonBanDuoc = new JLabel("Tổng hóa đơn bán được: ");
		lblTongHoaDonBanDuoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongHoaDonBanDuoc.setIcon(new ImageIcon(Pn_ThongKeNhanVien.class.getResource("/gui/icon/money.png")));
		lblTongHoaDonBanDuoc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongHoaDonBanDuoc.setBounds(32, 423, 254, 28);
		panel_ThongKeDoanhThuBanThan.add(lblTongHoaDonBanDuoc);

		lblTo = new JLabel("Đến:  ");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTo.setBounds(32, 127, 72, 28);
		panel_ThongKeDoanhThuBanThan.add(lblTo);

		lblFrom = new JLabel("Từ:  ");
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFrom.setBounds(32, 44, 64, 28);
		panel_ThongKeDoanhThuBanThan.add(lblFrom);

		dateChooserFromDoanhThu = new JDateChooser();
		dateChooserFromDoanhThu.setBounds(106, 56, 169, 20);
		panel_ThongKeDoanhThuBanThan.add(dateChooserFromDoanhThu);

		dateChooserToDoanhThu = new JDateChooser();
		dateChooserToDoanhThu.setBounds(106, 135, 169, 20);
		panel_ThongKeDoanhThuBanThan.add(dateChooserToDoanhThu);

		comboBoxTieuChiDoanhThu = new JComboBox();
		comboBoxTieuChiDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxTieuChiDoanhThu.setBounds(121, 212, 154, 35);
		comboBoxTieuChiDoanhThu.addItem("3 tháng gần nhất");
		comboBoxTieuChiDoanhThu.addItem("6 tháng gần nhất");
		comboBoxTieuChiDoanhThu.addItem("9 tháng gần nhất");
		panel_ThongKeDoanhThuBanThan.add(comboBoxTieuChiDoanhThu);

		btnLocDoanhThuNV = new JButton("Lọc");
		btnLocDoanhThuNV.setIcon(new ImageIcon(Pn_ThongKeNhanVien.class.getResource("/gui/icon/filter.png")));
		btnLocDoanhThuNV.setBounds(106, 270, 89, 35);
		panel_ThongKeDoanhThuBanThan.add(btnLocDoanhThuNV);

		lblGiaTriDoanhThu = new JLabel("0");
		lblGiaTriDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriDoanhThu.setBounds(44, 373, 231, 28);
		panel_ThongKeDoanhThuBanThan.add(lblGiaTriDoanhThu);

		lblGiaTriTongHoaDon = new JLabel("0");
		lblGiaTriTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriTongHoaDon.setBounds(44, 487, 209, 28);
		panel_ThongKeDoanhThuBanThan.add(lblGiaTriTongHoaDon);
		
		lblTieuChi = new JLabel("Lọc theo:");
		lblTieuChi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTieuChi.setBounds(10, 208, 94, 38);
		panel_ThongKeDoanhThuBanThan.add(lblTieuChi);

		txtTieuDe = new JLabel("THỐNG KÊ");
		txtTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		txtTieuDe.setForeground(new Color(0, 191, 255));
		txtTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
		txtTieuDe.setBounds(0, 11, 1500, 51);
		add(txtTieuDe);

		btnLocDoanhThuNV.addActionListener(this);
		btnLocSanPham.addActionListener(this);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnLocDoanhThuNV)) {
//			if (getNgayFromJDateChooser(dateChooserFromDoanhThu) != null
//					&& getNgayFromJDateChooser(dateChooserToDoanhThu) != null) {
//				if (iNhanVien.getNhanVienBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
//						getNgayFromJDateChooser(dateChooserToDoanhThu)) != null) {
//					String nv = (String) iNhanVien.getNhanVienBanNhieuNhatTheoNgayTuChon(
//							getNgayFromJDateChooser(dateChooserFromDoanhThu),
//							getNgayFromJDateChooser(dateChooserToDoanhThu));
//					valueTop1NV.setText(iNhanVien.timNhanVienTheoMa(nv).getHoTenNhanVien());
//				}
//			}
			try {
				FrmLogin dangNhap = new FrmLogin(port, host);
				TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
				nv = new NhanVien();
				nv = nhanVienService.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
				
				if (hoaDonService.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromDoanhThu),
						getNgayFromJDateChooser(dateChooserToDoanhThu), nv.getMaNhanVien())!=null) {
					lblGiaTriDoanhThu
							.setText(String.valueOf(hoaDonService.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromDoanhThu),
									getNgayFromJDateChooser(dateChooserToDoanhThu), nv.getMaNhanVien())) + "đ");
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				FrmLogin dangNhap = new FrmLogin(port, host);
				TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
				nv = new NhanVien();
				nv = nhanVienService.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
				if (hoaDonService.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromDoanhThu),
						getNgayFromJDateChooser(dateChooserToDoanhThu), nv.getMaNhanVien()) > 0) {
					lblGiaTriTongHoaDon.setText(
							String.valueOf(hoaDonService.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromDoanhThu),
									getNgayFromJDateChooser(dateChooserToDoanhThu), nv.getMaNhanVien())));
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setBieuDoVe0();
			try {
				setChart();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (obj.equals(btnLocSanPham)) {
			if (getNgayFromJDateChooser(dateChooserFromThongKeSP) != null
					&& getNgayFromJDateChooser(dateChooserToThongKeSP) != null) {
				try {
					if (sachService.getSanPhamBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromThongKeSP),
							getNgayFromJDateChooser(dateChooserToThongKeSP)) != null) {
						dsS = sachService.getSanPhamBanNhieuNhatTheoNgayTuChon(
								getNgayFromJDateChooser(dateChooserFromThongKeSP),
								getNgayFromJDateChooser(dateChooserToThongKeSP));
						for (Sach s : dsS) {
							lblMaSPTop1.setText(sachService.timSachTheoMa(s.getMaSach()).getMaSach());

							lblTheLoaiSPTop1.setText(sachService.timSachTheoMa(s.getMaSach()).getTheLoaiSach().getTenLoai());
							lblGiaSPTop1.setText(String
									.valueOf(sachService.timSachTheoMa(s.getMaSach()).getGiaNhap()
											+ sachService.timSachTheoMa(s.getMaSach()).getGiaNhap() * 10 / 100)
									+ "đ");

							lblSoLuongSPTop1.setText(String.valueOf(sachService.getSoLuongBanCuaSanPhamChayNhat(
									getNgayFromJDateChooser(dateChooserFromThongKeSP),
									getNgayFromJDateChooser(dateChooserToThongKeSP))));

							lblTenSPTop1.setText(sachService.timSachTheoMa(s.getMaSach()).getTenSach());
							File file = new File("");
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
	}
	public void setChart() throws RemoteException, MalformedURLException, NotBoundException {
		FrmLogin dangNhap = new FrmLogin(port, host);
		TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
		nv = new NhanVien();
		nv = nhanVienService.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
		int count = 0;
		LocalDate nowMinus = null;
		LocalDate now = LocalDate.now();
		if (comboBoxTieuChiDoanhThu.getSelectedIndex() == 0) {
			count = 3;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				dataset.setValue((Number) hoaDonService.getDoanhThuTheoMaNhanVien(nowMinus, now, nv.getMaNhanVien()), "Doanh thu",
						String.valueOf(nowMinus.getMonthValue()));
				now = nowMinus;
				count--;
			}
		}

		else if (comboBoxTieuChiDoanhThu.getSelectedIndex() == 1) {
			count = 6;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				dataset.setValue((Number) hoaDonService.getDoanhThuTheoMaNhanVien(nowMinus, now, nv.getMaNhanVien()), "Doanh thu",
						String.valueOf(nowMinus.getMonthValue()));
				now = nowMinus;
				count--;
			}
		}

		else {
			count = 9;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				dataset.setValue((Number) hoaDonService.getDoanhThuTheoMaNhanVien(nowMinus, now, nv.getMaNhanVien()), "Doanh thu",
						String.valueOf(nowMinus.getMonthValue()));
				now = nowMinus;
				count--;
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
}
