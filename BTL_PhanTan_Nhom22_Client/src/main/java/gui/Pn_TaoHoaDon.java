package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;
import entity.TacGia;
import entity.TaiKhoan;
import entity.TheLoaiSach;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import service.ChiTietHoaDonService;
import service.HoaDonService;
import service.KhachHangService;
import service.NhanVienService;
import service.SachService;
import service.TheLoaiService;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Pn_TaoHoaDon extends JPanel implements ActionListener, MouseListener {
	private static HashMap<String, ArrayList<Sach>> listHoaDonCho = new HashMap<String, ArrayList<Sach>>();
	private JTextField txtSDT;
	private JTextField txtTenKhachHang;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTable tableSach;
	private JTextField txtGiaBanSach;
	private JTextField txtSoLuongSach;
	private JTabbedPane tabSanPham;
	private JComboBox<Object> cbxTheLoai;
	private JComboBox<Object> cbxTacGia;
	private JButton btnThemKhachHang;
	private JButton btnRefresh;
	private JButton btnTimKhachHang;
	private JTable table;
	private JTable tableHoaDon;
	private JTextField txtMaHoaDon;
	private JTextField txtTongTienHD;
	private JTextField txtVAT;
	private JTextField txtNhanVien;
	private JTextField txtTienKhachDua;
	private JTextField txtTienThua;
	private JButton btnHuyHoaDon;
	private JButton btnThemHangCho;
	private JButton btnThanhToan;
	private JButton btnHangCho;
	private JButton btnXoa;
	private JButton btnLamMoiSach;
	private DefaultTableModel modelSach;
	private JScrollPane scrollSach;

	private List<Sach> dsSach;
	private DefaultTableModel modelHoaDon;
	private JScrollPane scrollHoaDon;

	private NhanVien nv;
	private JButton btnThemSach;

	private String sdt;
	private JButton btnLamMoiBang;
	private List<ChiTietHoaDon> dscthd;
	private String tenSP;
	private List<TacGia> listTacGia;

	private TheLoaiService theLoaiService;
	private KhachHangService khachHangService;
	private NhanVienService nhanVienService;
	private HoaDonService hoaDonService;
	private ChiTietHoaDonService chiTietHoaDonService;
	private SachService sachService;
	private Frm_HangCho frm_HangCho;
	private JTextField txtSoLuongXoa;
	private int port;
	private String host;
	private List<TheLoaiSach> listTheLoai;
	private JLabel lblMaHD;
	private JTextField txtMaSachTim;
	private JTextField txtTenSachTim;
	private Pn_QuanLyKhachHang pn_QuanLyKhachHang;
	private Pn_TaoHoaDon pn_TaoHoaDon;

	/**
	 * Create the panel.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */

	public Pn_TaoHoaDon(int port, String host) throws RemoteException, MalformedURLException, NotBoundException {

		this.port = port;
		this.host = host;
		theLoaiService = (TheLoaiService) Naming.lookup("rmi://" + host + ":" + port + "/theLoaiService");
		khachHangService = (KhachHangService) Naming.lookup("rmi://" + host + ":" + port + "/khachHangService");
		nhanVienService = (NhanVienService) Naming.lookup("rmi://" + host + ":" + port + "/nhanVienService");
		hoaDonService = (HoaDonService) Naming.lookup("rmi://" + host + ":" + port + "/hoaDonService");
		chiTietHoaDonService = (ChiTietHoaDonService) Naming
				.lookup("rmi://" + host + ":" + port + "/chiTietHoaDonService");
		sachService = (SachService) Naming.lookup("rmi://" + host + ":" + port + "/sachService");

		setBackground(new Color(0, 206, 209));
		setFont(new Font("Dialog", Font.BOLD, 16));
		setSize(1493, 650);
		setLayout(null);

		JPanel pnlMaHD = new JPanel();
		pnlMaHD.setBounds(10, 40, 191, 30);
		add(pnlMaHD);
		pnlMaHD.setLayout(null);

		JLabel lblMa = new JLabel("MÃ HOÁ ĐƠN:");
		lblMa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMa.setBounds(10, 0, 92, 30);
		pnlMaHD.add(lblMa);

		lblMaHD = new JLabel("");

		lblMaHD.setBounds(99, 0, 92, 30);
		pnlMaHD.add(lblMaHD);
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaHD.setForeground(new Color(255, 0, 0));
		lblMaHD.setText(auto_ID());

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBounds(0, 0, 1493, 30);
		add(pnlTitle);
		pnlTitle.setLayout(null);

		JLabel lblTitle = new JLabel("LẬP HOÁ ĐƠN");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 1493, 30);
		pnlTitle.add(lblTitle);

		tableSach = new JTable(modelSach);
		tableSach.setBounds(10, 307, 780, 316);
		String[] cols = { "STT", "Mã sách", "Tên sách", "Thể loại", "Tác giả", "Giá bán" };
		modelSach = new DefaultTableModel(cols, 0);
		tableSach.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableSach.setFont(new Font("Tahoma", Font.PLAIN, 12));

		scrollSach = new JScrollPane();
		scrollSach.setViewportView(tableSach = new JTable(modelSach));
		scrollSach.setBounds(10, 354, 820, 285);
		tableSach.getTableHeader().setBackground(Color.LIGHT_GRAY);
		tableSach.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableSach.setRowHeight(25);
		tableSach.setBackground(Color.WHITE);
		scrollSach.getViewport().setBackground(Color.WHITE);
		tableSach.getTableHeader().setPreferredSize(new Dimension(0, 20));
		add(scrollSach);
		tableSach.addMouseListener(this);

		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.setBounds(840, 40, 632, 599);
		add(pnlHoaDon);
		pnlHoaDon.setLayout(null);

		JLabel lblTitleHoaDon = new JLabel("HOÁ ĐƠN BÁN HÀNG");
		lblTitleHoaDon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleHoaDon.setBounds(0, 0, 632, 35);
		pnlHoaDon.add(lblTitleHoaDon);

		JLabel lblNgayLap = new JLabel("Ngày lập");
		lblNgayLap.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayLap.setBounds(286, 46, 90, 20);
		pnlHoaDon.add(lblNgayLap);

		JLabel lblNgayThang = new JLabel("");
		lblNgayThang.setText(auto_Date());
		lblNgayThang.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgayThang.setForeground(new Color(255, 0, 51));
		lblNgayThang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNgayThang.setBounds(386, 46, 107, 20);
		pnlHoaDon.add(lblNgayThang);

		txtSDT = new JTextField();
		txtSDT.setBounds(110, 110, 150, 20);
		pnlHoaDon.add(txtSDT);
		txtSDT.setColumns(10);

		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setBounds(10, 110, 90, 20);
		pnlHoaDon.add(lblSDT);
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setBounds(120, 150, 197, 20);
		pnlHoaDon.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setBounds(10, 150, 110, 20);
		pnlHoaDon.add(lblTenKhachHang);
		lblTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnThemKhachHang = new JButton("Thêm khách hàng");
		btnThemKhachHang.setBounds(450, 105, 172, 30);
		pnlHoaDon.add(btnThemKhachHang);
		
		btnThemKhachHang.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/add-user.png")));

		btnRefresh = new JButton("");
		btnRefresh.setBounds(270, 105, 30, 30);
		pnlHoaDon.add(btnRefresh);
		btnRefresh.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/refresh-button.png")));
		btnRefresh.setSelectedIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/refresh-button.png")));

		btnTimKhachHang = new JButton("Tìm");
		btnTimKhachHang.setBounds(340, 105, 100, 30);
		pnlHoaDon.add(btnTimKhachHang);
		btnTimKhachHang.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/loupe.png")));

		JLabel lblMaHoaDon = new JLabel("Mã hoá đơn:");
		lblMaHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaHoaDon.setBounds(10, 75, 90, 20);
		pnlHoaDon.add(lblMaHoaDon);

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setText(auto_ID());
		txtMaHoaDon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));

		txtMaHoaDon.setEditable(false);
		txtMaHoaDon.setForeground(Color.RED);
		txtMaHoaDon.setBounds(110, 75, 150, 20);
		pnlHoaDon.add(txtMaHoaDon);
		txtMaHoaDon.setColumns(10);

		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongTien.setBounds(10, 475, 75, 20);
		pnlHoaDon.add(lblTongTien);

		txtTongTienHD = new JTextField();
		txtTongTienHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTongTienHD.setEditable(false);
		txtTongTienHD.setBounds(130, 475, 130, 20);
		pnlHoaDon.add(txtTongTienHD);
		txtTongTienHD.setColumns(10);

		JLabel lblThanhTienHD = new JLabel("Thành tiền (10% VAT):");
		lblThanhTienHD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThanhTienHD.setBounds(292, 475, 150, 20);
		pnlHoaDon.add(lblThanhTienHD);

		txtVAT = new JTextField();
		txtVAT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtVAT.setEditable(false);
		txtVAT.setColumns(10);
		txtVAT.setBounds(452, 475, 150, 20);
		pnlHoaDon.add(txtVAT);

		JLabel lblTienKhachDua = new JLabel("Tiền khách đưa:");
		lblTienKhachDua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTienKhachDua.setBounds(10, 505, 110, 20);
		pnlHoaDon.add(lblTienKhachDua);

		JLabel lblNhanVien = new JLabel("Nhân viên:");
		lblNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhanVien.setBounds(290, 75, 90, 20);
		pnlHoaDon.add(lblNhanVien);

		txtNhanVien = new JTextField();
		txtNhanVien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtNhanVien.setEditable(false);
		txtNhanVien.setForeground(Color.RED);
		txtNhanVien.setColumns(10);
		txtNhanVien.setBounds(396, 75, 226, 20);

		pnlHoaDon.add(txtNhanVien);

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTienKhachDua.setColumns(10);
		txtTienKhachDua.setBounds(130, 505, 130, 20);
		pnlHoaDon.add(txtTienKhachDua);
		txtTienKhachDua.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				tinhTienThua();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		JLabel lblTienThua = new JLabel("Tiền thừa:");
		lblTienThua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTienThua.setBounds(292, 505, 110, 20);
		pnlHoaDon.add(lblTienThua);

		txtTienThua = new JTextField();
		txtTienThua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTienThua.setEditable(false);
		txtTienThua.setColumns(10);
		txtTienThua.setBounds(452, 506, 150, 20);
		pnlHoaDon.add(txtTienThua);

		btnHuyHoaDon = new JButton("Huỷ hoá đơn");
		btnHuyHoaDon.setBounds(73, 553, 130, 30);
		pnlHoaDon.add(btnHuyHoaDon);

		btnThemHangCho = new JButton("Thêm hàng chờ");
		btnThemHangCho.setBounds(246, 553, 130, 30);
		pnlHoaDon.add(btnThemHangCho);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBounds(409, 553, 130, 30);
		pnlHoaDon.add(btnThanhToan);

		btnHangCho = new JButton("Hàng chờ");
		btnHangCho.setBounds(340, 145, 100, 30);
		pnlHoaDon.add(btnHangCho);

		btnXoa = new JButton("Xoá");
		btnXoa.setBounds(450, 145, 89, 30);
		pnlHoaDon.add(btnXoa);

		scrollHoaDon = new JScrollPane();
		scrollHoaDon.setBounds(0, 191, 632, 264);
		pnlHoaDon.add(scrollHoaDon);
		String[] header = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Số lượng" };
		modelHoaDon = new DefaultTableModel(header, 0);
		scrollHoaDon.setViewportView(tableHoaDon = new JTable(modelHoaDon));
		tableHoaDon.getTableHeader().setBackground(Color.LIGHT_GRAY);
		tableHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableHoaDon.setRowHeight(25);
		tableHoaDon.setBackground(Color.WHITE);
		scrollHoaDon.getViewport().setBackground(Color.WHITE);
		tableHoaDon.getTableHeader().setPreferredSize(new Dimension(0, 20));
		btnTimKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JPanel pnSach = new JPanel();
		pnSach.setBounds(10, 77, 820, 178);
		pnSach.setLayout(null);

		JLabel lblMaSach = new JLabel("Mã sách");
		lblMaSach.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaSach.setBounds(10, 10, 75, 20);
		pnSach.add(lblMaSach);
		add(pnSach);

		txtMaSach = new JTextField();
		txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaSach.setBounds(170, 10, 250, 25);
		pnSach.add(txtMaSach);
		txtMaSach.setColumns(10);

		JLabel lblTenSach = new JLabel("Tên sách");
		lblTenSach.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenSach.setBounds(465, 10, 75, 20);
		pnSach.add(lblTenSach);

		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(550, 10, 250, 25);
		pnSach.add(txtTenSach);

		JLabel lblTacGia = new JLabel("Tác giả");
		lblTacGia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTacGia.setHorizontalAlignment(SwingConstants.LEFT);
		lblTacGia.setBounds(465, 60, 75, 20);
		pnSach.add(lblTacGia);

		cbxTacGia = new JComboBox();
		cbxTacGia.setFont(new Font("Tahoma", Font.PLAIN, 12));

		cbxTacGia.setEditable(true);
		cbxTacGia.setBounds(550, 60, 250, 25);
		pnSach.add(cbxTacGia);

		JLabel lblTheLoai = new JLabel("Thể loại");
		lblTheLoai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTheLoai.setHorizontalAlignment(SwingConstants.LEFT);
		lblTheLoai.setBounds(10, 60, 75, 20);
		pnSach.add(lblTheLoai);

		JLabel lblGiaBanSach = new JLabel("Giá bán");
		lblGiaBanSach.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaBanSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiaBanSach.setBounds(10, 110, 75, 20);
		pnSach.add(lblGiaBanSach);

		txtGiaBanSach = new JTextField();
		txtGiaBanSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtGiaBanSach.setEditable(false);
		txtGiaBanSach.setColumns(10);
		txtGiaBanSach.setBounds(170, 110, 250, 25);
		pnSach.add(txtGiaBanSach);

		cbxTheLoai = new JComboBox();
		cbxTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));

		cbxTheLoai.setEditable(true);
		cbxTheLoai.setBounds(170, 60, 250, 25);

		try {
			listTheLoai = theLoaiService.timTatCaTheLoai();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		cbxTheLoai.addItem("Tất cả");
		for (TheLoaiSach tls : listTheLoai) {
			cbxTheLoai.addItem(tls.getTenLoai());
		}

		pnSach.add(cbxTheLoai);

		btnLamMoiSach = new JButton("Làm mới");
		btnLamMoiSach.setBounds(710, 110, 90, 25);
		pnSach.add(btnLamMoiSach);

		btnThemSach = new JButton("Thêm");
		btnThemSach.setBounds(610, 110, 90, 25);
		pnSach.add(btnThemSach);

		txtSoLuongSach = new JTextField();
		txtSoLuongSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoLuongSach.setBounds(550, 110, 50, 25);
		pnSach.add(txtSoLuongSach);
		txtSoLuongSach.setColumns(10);

		JLabel lblSoLuongSach = new JLabel("Số lượng");
		lblSoLuongSach.setBounds(465, 105, 77, 25);
		pnSach.add(lblSoLuongSach);
		lblSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoLuongSach.setHorizontalAlignment(SwingConstants.LEFT);

		btnLamMoiBang = new JButton("Làm mới bảng");
		btnLamMoiBang.setBounds(170, 142, 130, 25);
		pnSach.add(btnLamMoiBang);
		btnLamMoiBang.addActionListener(this);

		tableSach.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableSach.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableSach.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableSach.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableSach.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableSach.getColumnModel().getColumn(5).setPreferredWidth(40);
		


		try {
			DocDuLieuSach();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		FrmLogin dangNhap = null;
		try {
			dangNhap = new FrmLogin(port, host);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
		// System.out.println(taiKhoan);

		nv = new NhanVien();
		nv = nhanVienService.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
		txtNhanVien.setText(nv.getHoTenNhanVien());
		pnlHoaDon.add(txtNhanVien);

		txtSoLuongXoa = new JTextField();
		txtSoLuongXoa.setBounds(549, 145, 73, 30);
		pnlHoaDon.add(txtSoLuongXoa);
		txtSoLuongXoa.setColumns(10);

		btnThemSach.addActionListener(this);
		btnHangCho.addActionListener(this);
		btnHuyHoaDon.addActionListener(this);
		btnLamMoiSach.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnThemHangCho.addActionListener(this);
		btnThemKhachHang.addActionListener(this);
		btnTimKhachHang.addActionListener(this);
		btnXoa.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 266, 820, 76);
		add(panel);
		panel.setLayout(null);
		
		txtMaSachTim = new JTextField();
		txtMaSachTim.setBounds(175, 26, 143, 30);
		panel.add(txtMaSachTim);
		txtMaSachTim.setColumns(10);
		
		txtTenSachTim = new JTextField();
		txtTenSachTim.setBounds(548, 26, 143, 30);
		panel.add(txtTenSachTim);
		txtTenSachTim.setColumns(10);
		

		txtTenSachTim.addKeyListener(new KeyListener() {

	        public void keyTyped(KeyEvent e) {
	            // TODO Auto-generated method stub

	        }

	        public void keyReleased(KeyEvent e) {
	            // TODO Auto-generated method stub
	            try {
	                filter();
	            } catch (RemoteException e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
	            }
	        }

	        public void keyPressed(KeyEvent e) {
	            // TODO Auto-generated method stub

	        }
	    });
		
		
		txtMaSachTim.addKeyListener(new KeyListener() {

	        public void keyTyped(KeyEvent e) {
	            // TODO Auto-generated method stub

	        }

	        public void keyReleased(KeyEvent e) {
	            // TODO Auto-generated method stub
	            try {
	                filter();
	            } catch (RemoteException e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
	            }
	        }

	        public void keyPressed(KeyEvent e) {
	            // TODO Auto-generated method stub

	        }
	    });
		
		JLabel lblNewLabel = new JLabel("Mã sách");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(43, 26, 88, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên sách");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(432, 26, 81, 30);
		panel.add(lblNewLabel_1);
	}
	
	public void filter() throws RemoteException {
	    clearTableSach(); 
	    String ma = txtMaSachTim.getText().toString().trim();
	    String ten = txtTenSachTim.getText().toString().trim();

	    List<Sach> list = sachService.timTatCaSach();
	    modelSach = (DefaultTableModel) tableSach.getModel();
	    int i = 0;
	    for (Sach sach : list) {
	        if (sach.getMaSach().toLowerCase().contains(ma.toLowerCase())
	                && sach.getTenSach().toLowerCase().contains(ten.toLowerCase())) {
	            Object[] rowData = {i++, sach.getMaSach(), sach.getTenSach(), sach.getTheLoaiSach().getTenLoai(), sach.getTacGia().getTenTacGia(), sach.tinhGiaBan() };
	            modelSach.addRow(rowData);
	        }

	    }
	
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(tableSach)) {
			tableHoaDon.clearSelection();
			int row = tableSach.getSelectedRow();
			modelSach = (DefaultTableModel) tableSach.getModel();
			txtMaSach.setText(modelSach.getValueAt(row, 1).toString());
			txtTenSach.setText(modelSach.getValueAt(row, 2).toString());
			cbxTheLoai.setSelectedItem(modelSach.getValueAt(row, 3).toString());
			cbxTacGia.setSelectedItem(modelSach.getValueAt(row, 4).toString());
			txtGiaBanSach.setText(modelSach.getValueAt(row, 5).toString());
		} else if (obj.equals(tableHoaDon)) {
			tableSach.clearSelection();
		}
	}

	public void DocDuLieuSach() throws Exception {
		clearTableSach();
		dsSach = sachService.timTatCaSach();
		String tacGia = "";
		int i = 1;
		for (Sach sach : dsSach) {
			if (sach.getTacGia().getTenTacGia() == null) {
				tacGia = "Không có tác giả";
			} else {
				tacGia = sach.getTacGia().getTenTacGia();
			}
			modelSach.addRow(new Object[] { i++, sach.getMaSach(), sach.getTenSach(),
					sach.getTheLoaiSach().getTenLoai(), tacGia, sach.tinhGiaBan() });
		}
	}

	public boolean tonTaiSanPhamTrongCTHD(Sach sach) {
		int soLuongS = 0;
		if (tableHoaDon.getRowCount() < 1) {
			return false;
		}
		for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
			if (tableHoaDon.getValueAt(i, 1).equals(sach.getMaSach())) {
				soLuongS = Integer.parseInt(txtSoLuongSach.getText());
				int tongSP = Integer.parseInt(tableHoaDon.getValueAt(i, 4).toString()) + soLuongS;
				tableHoaDon.setValueAt(tongSP, i, 4);
				return true;
			}
		}
		return false;
	}

	public void themSachVaoGioHang() throws SQLException {
		int soLuongS = Integer.parseInt(txtSoLuongSach.getText());
		dscthd = new ArrayList<ChiTietHoaDon>();
		modelSach = (DefaultTableModel) tableSach.getModel();
		int row = tableSach.getSelectedRow();
		String maS = modelSach.getValueAt(row, 1).toString();
		Sach s = null;
		try {
			s = sachService.timSachTheoMa(maS);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (s != null) {
			if (!tonTaiSanPhamTrongCTHD(s)) {
				modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
				modelHoaDon.addRow(new Object[] { modelHoaDon.getRowCount() + 1, s.getMaSach(), s.getTenSach(),
						s.tinhGiaBan(), soLuongS });
			}
		}
		txtSoLuongSach.setText("");
		tongTienHandler();
		tongTienVAT();
	}

	public void truSoLuongTrongGioHang() {
		int soLuongCanXoa = Integer.parseInt(txtSoLuongXoa.getText());
		if (tableHoaDon.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Phải chọn sản phẩm cần xóa");
			return;
		}
		int row = tableHoaDon.getSelectedRow();
		modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
		int tongSP = Integer.parseInt(tableHoaDon.getValueAt(row, 4).toString()) - soLuongCanXoa;
		tableHoaDon.setValueAt(tongSP, row, 4);
		if (tongSP == 0) {
			modelHoaDon.removeRow(row);
		}
	}

	public void themSoLuongSachVaoKho() throws RemoteException {
		int soLuongCanXoa = Integer.parseInt(txtSoLuongXoa.getText());
		modelSach = (DefaultTableModel) tableSach.getModel();
		modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
		int row = tableHoaDon.getSelectedRow();
		for (int i = 0; i < modelSach.getRowCount(); i++) {
			if (modelSach.getValueAt(i, 1).toString().equals(modelHoaDon.getValueAt(row, 1).toString())) {
				Sach s = sachService.timSachTheoMa(modelHoaDon.getValueAt(row, 1).toString());
				int soLuongBanDau = s.getSoLuongTon();
				s.setSoLuongTon(soLuongBanDau + soLuongCanXoa);
				sachService.capNhatSach(s);
			}
		}
	}

	public void themHoaDon() throws SQLException, MalformedURLException, RemoteException, NotBoundException {
		String mahd = txtMaHoaDon.getText();
		FrmLogin dangNhap = new FrmLogin(port, host);
		TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
		nv = nhanVienService.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
		KhachHang kh = khachHangService.timKhachHangTheoSDT(txtSDT.getText()).get(0);
		String sdt = txtSDT.getText();
		Date ngayLapHoaDon = new Date(System.currentTimeMillis());
		String ghiChu = "Không";
		Long tienKhachDua = Long.parseLong(txtTienKhachDua.getText().trim());
		Boolean tinhTrang = true;

		System.out.println(nv.getTaiKhoan().isQuyen());

		HoaDon hd = new HoaDon(mahd, nv, kh, ngayLapHoaDon, ghiChu, tienKhachDua, tinhTrang);
		System.out.println(hd);
		hoaDonService.themHoaDon(hd);
	}

	public void themCTHD() throws SQLException, RemoteException {
		HoaDon hd = hoaDonService.timHoaDonTheoMa(txtMaHoaDon.getText());
		List<ChiTietHoaDon> listCTHD = new ArrayList<ChiTietHoaDon>();
		for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
			String masp = tableHoaDon.getValueAt(i, 1).toString();
			Sach sach = sachService.timSachTheoMa(masp);
			int soLuong = Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString());
			long giaBan = Long.parseLong(modelHoaDon.getValueAt(i, 3).toString());
			ChiTietHoaDon cthd = new ChiTietHoaDon(hd, sach, soLuong, giaBan);
			listCTHD.add(cthd);
			if (chiTietHoaDonService.themCTHD(cthd) == false)
				return;
		}
	}

	public void tongTienHandler() {
		long tongTien = 0;

		for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
			long thanhTien = Long.parseLong(tableHoaDon.getValueAt(i, 3).toString())
					* Long.parseLong(tableHoaDon.getValueAt(i, 4).toString());
			tongTien += thanhTien;
		}

		txtTongTienHD.setText(tongTien + "");
	}

	public void tongTienVAT() {
		long thanhTienVAT = 0;
		thanhTienVAT = Long.parseLong(txtTongTienHD.getText().trim())
				+ Long.parseLong(txtTongTienHD.getText().trim()) * 1 / 10;

		txtVAT.setText(thanhTienVAT + "");

	}

	public void tinhTienThua() {
		long tienThua = 0;
		tienThua = Long.parseLong(txtTienKhachDua.getText().trim()) - Long.parseLong(txtVAT.getText().trim());
		txtTienThua.setText(tienThua + "");
	}

	public void clearTxtFieldsSach() {
		txtMaSach.setText("");
		txtTenSach.setText("");
		cbxTheLoai.setSelectedIndex(0);
		cbxTacGia.setSelectedIndex(0);
		txtGiaBanSach.setText("");
		txtSoLuongSach.setText("");
	}

	public void clearTxtFieldsSDT() {
		txtSDT.setText("");
		txtTenKhachHang.setText("");
	}

	public void tableDanhSachSachWithFilter() throws Exception {
//		tableSach.clearSelection();
//		clearTableSach();
		DefaultTableModel dtm = (DefaultTableModel) tableSach.getModel();
		String maSach = "";
		String tenSach = "";
		String theLoai = "";
		String tacgia = "";
		if (txtMaSach.getText().length() > 0) {
			maSach = txtMaSach.getText();
		}
		if (txtTenSach.getText().length() > 0) {
			tenSach = txtTenSach.getText();
		}

		theLoai = removeAccent(String.valueOf((cbxTheLoai.getSelectedItem())));

		tacgia = removeAccent(String.valueOf((cbxTacGia.getSelectedItem())));
		if (tacgia.equalsIgnoreCase(removeAccent("Tất cả")) || theLoai.equalsIgnoreCase(removeAccent("Tất cả"))) {
			clearTableSach();
			DocDuLieuSach();
			return;
		}
		List<Sach> listSach = sachService.timTatCaSach();
		String tacGia = "";
		int i = 1;
//		Object row
		for (Sach s : listSach) {
			if (removeAccent(s.getMaSach()).toLowerCase().contains(removeAccent(maSach).toLowerCase())
					&& removeAccent(s.getTenSach()).toLowerCase().contains(removeAccent(tenSach).toLowerCase())
					&& removeAccent(s.getTheLoaiSach().getTenLoai()).toLowerCase().contains(theLoai.toLowerCase())
					&& (removeAccent(
							s.getTacGia() == null ? "Không có tác giả" : removeAccent(s.getTacGia().getTenTacGia()))
							.toLowerCase().contains(tacgia.toLowerCase()))) {
				if (s.getTacGia() == null) {
					tacGia = "Không có tác giả";
					Object[] rowData = { i++, s.getMaSach(), s.getTenSach(), s.getTheLoaiSach().getTenLoai(), tacGia,
							s.tinhGiaBan() };
					clearTableSach();
					dtm.addRow(rowData);
				} else {
					Object[] rowData = { i++, s.getMaSach(), s.getTenSach(), s.getTheLoaiSach().getTenLoai(),
							s.getTacGia().getTenTacGia(), s.tinhGiaBan() };
					clearTableSach();
					dtm.addRow(rowData);
				}

			}
		}
	}

	public void clearTableSach() {
		DefaultTableModel dtm = (DefaultTableModel) tableSach.getModel();
		dtm.setRowCount(0);

	}

	public void clearTableCTHD() {
		DefaultTableModel dtm = (DefaultTableModel) tableHoaDon.getModel();
		dtm.setRowCount(0);
	}

	private static String removeAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	public void resetGDSauKhiThanhToan() throws RemoteException {
		txtMaHoaDon.setText(auto_ID());
		txtMaSach.setText("");
		txtGiaBanSach.setText("");
		txtTenSach.setText("");
		txtSoLuongSach.setText("");
		txtSDT.setText("");
		txtTenKhachHang.setText("");
		txtTongTienHD.setText("");
		txtVAT.setText("");
		txtTienKhachDua.setText("");
		txtTienThua.setText("");
		lblMaHD.setText(auto_ID());
	}

	public  String auto_ID() throws RemoteException {
		String idPrefix = "HD";
		LocalDate myObj = LocalDate.now();
		String ngayHD = String.valueOf(myObj.getDayOfMonth());
		int length = 0;
		length = hoaDonService.timTatCaHoaDon().size();
		String finalId = idPrefix + ngayHD + String.format("%05d", length + 1);
		return finalId;
	}

	public  String auto_Date() {
		LocalDate myObj = LocalDate.now();
		String ngay = String.valueOf(myObj.getDayOfMonth());
		String thang = String.valueOf(myObj.getMonthValue());
		String nam = String.valueOf(myObj.getYear());
		String finalDate = ngay + "-" + thang + "-" + nam;
		return finalDate;
	}

	public static HashMap<String, ArrayList<Sach>> getListHoaDonCho() {
		return listHoaDonCho;
	}

	public static void getListHoaDonCho(HashMap<String, ArrayList<Sach>> listHD) {
		listHoaDonCho = listHD;
	}

	public void openFrmHoaDonCho() {
		new Thread(() -> {
			ShareData shareData = new ShareData(listHoaDonCho);

			synchronized (shareData) {
				try {
					try {
						frm_HangCho = new Frm_HangCho(listHoaDonCho, shareData, port, host);
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
					frm_HangCho.setVisible(true);
					shareData.wait();
					listHoaDonCho = shareData.getListHoaDonCho();
					if (shareData.isThanhToan()) {
						modelHoaDon.setRowCount(0);
						String sdt = shareData.getSdtThanhToan();
						try {
							KhachHang khachHang = khachHangService.timKhachHangTheoSDT(sdt).get(0);
							txtSDT.setText(khachHang.getsDT());
							txtTenKhachHang.setText(khachHang.getHoTenKhachHang());
							ArrayList<Sach> listSanPham = new ArrayList<>();
							listSanPham = shareData.getListSanPhamThanhToanTiep();
							for (int i = 0; i < listSanPham.size(); i++) {
								Sach sach = null;

								sach = sachService.timSachTheoMa(listSanPham.get(i).getMaSach());

								if (sach != null) {
									Object[] o = { i + 1 + "", sach.getMaSach(), sach.getTenSach(),
											sach.tinhGiaBan() + "", listSanPham.get(i).getSoLuongTon() };
									modelHoaDon.addRow(o);
								}

							}
							tongTienHandler();
							tongTienVAT();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void setTongTienRong() {
		txtTongTienHD.setText("");
		txtVAT.setText("");
		txtTienKhachDua.setText("");
		txtTienThua.setText("");
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnThemKhachHang)) {
			try {
				new Frm_ThemKH(port, host).setVisible(true);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (obj.equals(btnLamMoiSach)) {
			clearTxtFieldsSach();
		} else if (obj.equals(btnRefresh)) {
			clearTxtFieldsSDT();
		} else if (obj.equals(btnTimKhachHang)) {
			List<KhachHang> listkh = null;
			sdt = txtSDT.getText().toString();
			try {
				listkh = khachHangService.timKhachHangTheoSDT(sdt);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			if(sdt.length()<=0) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại của khách hàng");
				return;
			}
			if (listkh.size() != 0) {
				txtTenKhachHang.setText(listkh.get(0).getHoTenKhachHang());
				return;
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng, vui lòng thêm khách hàng mới");
				try {
					new Frm_ThemKH(port, host).setVisible(true);
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
		} else if (obj.equals(btnLamMoiBang)) {
				try {
					clearTableSach();
					DocDuLieuSach();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		} else if (obj.equals(btnThemSach)) {
			try {
				themSachVaoGioHang();
				clearTableSach();
				DocDuLieuSach();
//				DocDuLieuHoaDon();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (obj.equals(btnThanhToan)) {

			tinhTienThua();
			if(Double.parseDouble(txtTienThua.getText().toString())>=0) {
				try {
					try {
						themHoaDon();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						themCTHD();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					xuatHoaDon(txtMaHoaDon.getText().toString());
					JOptionPane.showMessageDialog(this, "Thanh toán hoàn tất!");
					try {
						resetGDSauKhiThanhToan();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clearTableCTHD();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Khách đưa thiếu tiền, vui lòng kiểm tra lại");
				return;
			}
			
		} else if (obj.equals(btnXoa)) {
			truSoLuongTrongGioHang();
			try {
				themSoLuongSachVaoKho();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tongTienHandler();
			tongTienVAT();
		} else if (obj.equals(btnThemHangCho)) {
			// kiểm tra điều kiện
			if (txtTenKhachHang.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin khách hàng", "Báo lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (modelHoaDon.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// xử lý
			if (listHoaDonCho.containsKey(txtSDT.getText().trim())) {
				// đã có người dùng trong hàng chờ
				for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
					boolean kiemTraTrung = false;
					Sach sach = new Sach(modelHoaDon.getValueAt(i, 1).toString(),
							Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString()));
					for (int j = 0; j < listHoaDonCho.get(txtSDT.getText().trim()).size(); j++) {
						// trùng sản phẩm -> số lượng tăng
						if (sach.getMaSach().equals(listHoaDonCho.get(txtSDT.getText().trim()).get(j).getMaSach())) {
							int soLuong = listHoaDonCho.get(txtSDT.getText().trim()).get(j).getSoLuongTon();
							listHoaDonCho.get(txtSDT.getText().trim()).get(j)
									.setSoLuongTon(sach.getSoLuongTon() + soLuong);
							kiemTraTrung = true;
							break;
						}
					}
					if (!kiemTraTrung) {
						listHoaDonCho.get(txtSDT.getText().trim()).add(sach);
					}
				}
			} else {
				// chưa có người dùng trong hàng chờ
				ArrayList<Sach> listSanPhamCho = new ArrayList<>();
				for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
					Sach sach = new Sach(modelHoaDon.getValueAt(i, 1).toString(),
							Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString()));
					listSanPhamCho.add(sach);
				}
				listHoaDonCho.put(txtSDT.getText().trim(), listSanPhamCho);
			}
			clearTxtFieldsSDT();
			setTongTienRong();
			modelHoaDon.setRowCount(0);
			JOptionPane.showMessageDialog(null, "Thêm vào hàng chờ thành công!!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (obj.equals(btnHangCho)) {
			openFrmHoaDonCho();
		} else if(obj.equals(btnHuyHoaDon)) {
			clearTableCTHD();
			clearTxtFieldsSDT();
			setTongTienRong();
		}
	}
	
	public void xuatHoaDon(String maHD) {
        try {
            Hashtable map = new Hashtable();
            File file = new File("");
            String path = file.getAbsolutePath();
            JasperReport report = JasperCompileManager.compileReport(path + "//src//main//java//gui//HoaDon.jrxml");
            // System.out.println(report);
            map.put("maHD", maHD);
            JasperPrint p = JasperFillManager.fillReport(report, map, DBConnection());

            JasperViewer.viewReport(p, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    private Connection DBConnection() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLNHASACH;trustServerCertificate=true";

        String user = "sa";
        String pass = "20075851";
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
