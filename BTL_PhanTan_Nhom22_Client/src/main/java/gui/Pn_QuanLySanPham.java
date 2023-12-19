package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.Sach;
import entity.TacGia;
import entity.TheLoaiSach;
import service.NhaCungCapService;
import service.NhaXuatBanService;
import service.SachService;
import service.TacGiaService;
import service.TheLoaiService;

public class Pn_QuanLySanPham extends JPanel implements ActionListener, MouseListener {
	private final static int tenSach = 0;
	private final static int tenTacGia = 1;
	private final static int tenNhaXuatBan = 2;
	private final static int namXB = 3;
	private final static int soTrang = 4;
	private final static int tenTheLoai = 5;
	private final static int soLuongTon = 6;
	private final static int trongLuong = 7;
	private final static int tenNhaCungCap = 8;
	private final static int giaNhap = 9;
	private final static int ghiChu = 10;
	private final static int hinhAnh = 11;

	private final static int tenVanPhongPham = 0;
	private final static int tenLoaiVanPhongPham = 1;
	private final static int tenMauSac = 2;
	private final static int tenChatLieu = 3;
	private final static int tenXuatXu = 4;
	private final static int soLuongTonvpp = 5;
	private final static int trongLuongvpp = 6;
	private final static int tenNhaCungCapvpp = 7;
	private final static int giaNhapvpp = 8;
	private final static int ghiChuvpp = 9;
	private final static int hinhAnhvpp = 10;

	private NhaCungCapService nhaCungCapService;
	private NhaXuatBanService nhaXuatBanService;
	private SachService sachService;
	private TacGiaService tacGiaService;
	private TheLoaiService theLoaiService;

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelSanPham;
	private JTable tableSanPham;
	private JScrollPane scrollSanPham;
	private JButton btnThemSP;
	private JButton btnTImKiem;
	private JButton btnLamMoiDanhSach;
	private JButton btnCapNhat;
	private JComboBox<Object> cbbTacGiaorChatLieu;
	private JComboBox<Object> cbbNhaXBorXuatXu;
	private JComboBox<String> cbbNhaCungCap;
	private JPanel panel_1;
	private JLabel lblImageSP;
	private JLabel lblTenSP;
	private JLabel lblSoLuong;
	private JLabel lblGiaNhap;
	private JLabel lblGiaBan;
	private JLabel lblNhaCungCap;
	private JLabel lblTacGiaOrChatLieu;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JButton btnLamMoiLoc;
	private ImageIcon imageIcon;
	private JComboBox<Object> cbbLoai;
	private int mauTinHienHanh;
	private int tongSoMauTin;
	private ArrayList<TheLoaiSach> theLoaiSachs;
	private ArrayList<TacGia> tacGias;
	private ArrayList<NhaXuatBan> nhaXuatBans;
	private ArrayList<NhaCungCap> nhaCungCaps;

	private JButton btnQuanLyDanhMuc;

	private JFileChooser filechoose;
	private File file;
	private ArrayList<Sach> listSach;
	private int port;
	private String host;

	public Pn_QuanLySanPham(int port, String host) throws Exception {
		this.port = port;
		this.host = host;

		nhaCungCapService = (NhaCungCapService) Naming.lookup("rmi://" + host + ":" + port + "/nhaCungCapService");
		nhaXuatBanService = (NhaXuatBanService) Naming.lookup("rmi://" + host + ":" + port + "/nhaXuatBanService");
		sachService = (SachService) Naming.lookup("rmi://" + host + ":" + port + "/sachService");
		tacGiaService = (TacGiaService) Naming.lookup("rmi://" + host + ":" + port + "/tacGiaService");
		theLoaiService = (TheLoaiService) Naming.lookup("rmi://" + host + ":" + port + "/theLoaiService");
		setSize(1400, 650);
		setBackground(new Color(0, 206, 209));
		setLayout(null);
//		JSlider slider = new JSlider(50,500);
//		slider.setPaintLabels(true);
//		slider.setPaintLabels(true);
//		slider.setLabelTable(slider.createStandardLabels(50));
		JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
//		lblTitle.setForeground(new Color(0, 206, 209));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTitle.setBounds(418, 10, 601, 44);
		add(lblTitle);

		JPanel pnLoc = new JPanel();
		pnLoc.setBackground(new Color(255, 255, 255));
		pnLoc.setBorder(new LineBorder(new Color(58, 176, 242), 2));
		pnLoc.setBounds(10, 64, 260, 549);
		add(pnLoc);
		pnLoc.setLayout(null);
		txtMaSP = new JTextField();
		txtMaSP.setForeground(Color.GRAY);
		txtMaSP.setText("Mã sản phẩm");
		txtMaSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaSP.setBounds(10, 21, 240, 37);
		pnLoc.add(txtMaSP);
		txtMaSP.setColumns(10);
		txtMaSP.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMaSP.getText().equals("Mã sản phẩm")) {
					txtMaSP.setText("");
					txtMaSP.setForeground(Color.BLACK);
					txtMaSP.setHorizontalAlignment(SwingConstants.LEFT);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMaSP.getText().isEmpty()) {
					txtMaSP.setForeground(Color.GRAY);
					txtMaSP.setText("Mã sản phẩm");
					txtMaSP.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
		});

		txtTenSP = new JTextField();
		txtTenSP.setForeground(Color.GRAY);
		txtTenSP.setText("Tên sản phẩm");
		txtTenSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(10, 87, 240, 37);
		pnLoc.add(txtTenSP);
		txtTenSP.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTenSP.getText().equals("Tên sản phẩm")) {
					txtTenSP.setText("");
					txtTenSP.setForeground(Color.BLACK);
					txtTenSP.setHorizontalAlignment(SwingConstants.LEFT);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTenSP.getText().isEmpty()) {
					txtTenSP.setForeground(Color.GRAY);
					txtTenSP.setText("Tên sản phẩm");
					txtTenSP.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
		});

		btnLamMoiLoc = new JButton("Làm mới");
		btnLamMoiLoc.setHorizontalAlignment(SwingConstants.LEFT);
		// imageIcon =
		// setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/refresh-button.png"),
		// 13, 13);
		btnLamMoiLoc.setIcon(imageIcon);
		btnLamMoiLoc.setBackground(new Color(88, 86, 214));
		btnLamMoiLoc.setForeground(new Color(255, 255, 255));
		btnLamMoiLoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoiLoc.setBounds(10, 506, 109, 33);
		pnLoc.add(btnLamMoiLoc);

		btnTImKiem = new JButton("Tìm kiếm");
		btnTImKiem.setHorizontalAlignment(SwingConstants.LEFT);
		btnTImKiem.setForeground(Color.BLACK);
		btnTImKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnTImKiem.setBounds(135, 506, 115, 33);
		// imageIcon =
		// setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/loupe.png"),
		// 13, 13);
		btnTImKiem.setIcon(imageIcon);
		pnLoc.add(btnTImKiem);

		ButtonGroup G = new ButtonGroup();

		cbbLoai = new JComboBox<Object>();
		cbbLoai.setBackground(new Color(255, 255, 255));
		cbbLoai.setBounds(10, 184, 240, 33);
		pnLoc.add(cbbLoai);

		String[] gia = { "Tất cả giá", "Dưới 50000", "50000 đến 120000", "120000 đến 250000", "Trên 250000" };

		cbbTacGiaorChatLieu = new JComboBox<Object>();
		cbbTacGiaorChatLieu.setBackground(Color.WHITE);
		cbbTacGiaorChatLieu.setBounds(10, 259, 240, 33);
		pnLoc.add(cbbTacGiaorChatLieu);

		cbbNhaXBorXuatXu = new JComboBox<Object>();
		cbbNhaXBorXuatXu.setBackground(Color.WHITE);
		cbbNhaXBorXuatXu.setBounds(10, 338, 240, 33);
		pnLoc.add(cbbNhaXBorXuatXu);

		cbbNhaCungCap = new JComboBox<String>();
		cbbNhaCungCap.setBackground(Color.WHITE);
		cbbNhaCungCap.setBounds(10, 420, 240, 33);
		pnLoc.add(cbbNhaCungCap);

		String[] cols = { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Nhà cung cấp", "Số trang",
				"Thể loại", "Tác giả", "Nhà xuất bản" };
		modelSanPham = new DefaultTableModel(cols, 0);
		tableSanPham = new JTable(modelSanPham);
		tableSanPham.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));

		scrollSanPham = new JScrollPane(tableSanPham);
		scrollSanPham.setBounds(280, 223, 1096, 390);

		tableSanPham.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableSanPham.setAutoCreateRowSorter(true);
		tableSanPham.setRowHeight(25);
		tableSanPham.setBackground(Color.decode("#BEFFC0"));
		scrollSanPham.getViewport().setBackground(Color.WHITE);
		tableSanPham.getTableHeader().setPreferredSize(new Dimension(0, 40));
		add(scrollSanPham);

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(new Color(58, 176, 242));
		pnTimKiem.setBorder(new LineBorder(new Color(58, 176, 242), 2));
		pnTimKiem.setBounds(10, 10, 260, 64);
		add(pnTimKiem);
		pnTimKiem.setLayout(null);
		JLabel lblTimKiem = new JLabel("Bộ lọc");
		lblTimKiem.setBounds(47, 0, 146, 54);
		pnTimKiem.add(lblTimKiem);
		lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTimKiem.setForeground(new Color(255, 255, 255));
		lblTimKiem.setBackground(new Color(58, 176, 242));

		btnThemSP = new JButton("Thêm sản phẩm");
		btnThemSP.setHorizontalAlignment(SwingConstants.LEFT);
		btnThemSP.setIcon(setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/add.png"), 16, 16));
		btnThemSP.setBounds(815, 173, 176, 40);
		add(btnThemSP);
		btnThemSP.setBackground(new Color(66, 178, 26));
		btnThemSP.setForeground(new Color(255, 255, 255));
		btnThemSP.setFont(new Font("Dialog", Font.BOLD, 16));

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat
				.setIcon(setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/maintenance.png"), 25, 25));
		btnCapNhat.setHorizontalAlignment(SwingConstants.LEFT);
		btnCapNhat.setBounds(1001, 123, 176, 40);
		add(btnCapNhat);
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCapNhat.setBackground(Color.decode("#EDD927"));

		btnQuanLyDanhMuc = new JButton("Quản lý danh mục");
		btnQuanLyDanhMuc.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuanLyDanhMuc.setBounds(1001, 173, 176, 40);
		add(btnQuanLyDanhMuc);
		btnQuanLyDanhMuc.setFont(new Font("Dialog", Font.BOLD, 16));

		btnLamMoiDanhSach = new JButton("Làm mới bảng");
		btnLamMoiDanhSach.setHorizontalAlignment(SwingConstants.LEFT);
		btnLamMoiDanhSach.setIcon(
				setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/refresh-button.png"), 25, 25));
		btnLamMoiDanhSach.setBounds(815, 123, 176, 40);
		add(btnLamMoiDanhSach);
		btnLamMoiDanhSach.setForeground(Color.WHITE);
		btnLamMoiDanhSach.setFont(new Font("Dialog", Font.BOLD, 16));
		btnLamMoiDanhSach.setBackground(Color.decode("#1959BA"));

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(419, 54, 386, 159);
		add(panel_1);
		panel_1.setLayout(null);

		lblTenSP = new JLabel("Tên sản phẩm: ");
		lblTenSP.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenSP.setBounds(0, 11, 386, 13);
		panel_1.add(lblTenSP);

		lblSoLuong = new JLabel("Số Lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSoLuong.setBounds(0, 35, 386, 13);
		panel_1.add(lblSoLuong);

		lblGiaNhap = new JLabel("Đơn giá:");
		lblGiaNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaNhap.setBounds(0, 59, 386, 13);
		panel_1.add(lblGiaNhap);

		lblGiaBan = new JLabel("Nhà xuất bản: ");
		lblGiaBan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaBan.setBounds(0, 83, 386, 13);
		panel_1.add(lblGiaBan);

		lblNhaCungCap = new JLabel("Nhà cung cấp: ");
		lblNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNhaCungCap.setBounds(0, 107, 386, 13);
		panel_1.add(lblNhaCungCap);

		lblTacGiaOrChatLieu = new JLabel("Tác giả: ");
		lblTacGiaOrChatLieu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTacGiaOrChatLieu.setBounds(0, 131, 386, 13);
		panel_1.add(lblTacGiaOrChatLieu);

		lblImageSP = new JLabel("");
		lblImageSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageSP.setBounds(280, 54, 140, 163);
		lblImageSP.setIcon(setSizeImageIconString("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg",
				lblImageSP.getWidth(), lblImageSP.getHeight()));
		add(lblImageSP);

		// load du lieu

		tongSoMauTin = tableSanPham.getRowCount();

		// Đăng ký lắng nghe
		btnLamMoiDanhSach.addActionListener(this);
		btnQuanLyDanhMuc.addActionListener(this);
		btnLamMoiLoc.addActionListener(this);
		btnThemSP.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnTImKiem.addActionListener(this);
		tableSanPham.addMouseListener(this);

		cbbLoai.addItem("Trống");
		cbbNhaCungCap.addItem("Trống");
		cbbNhaXBorXuatXu.addItem("Trống");
		cbbTacGiaorChatLieu.addItem("Trống");
		
		JLabel lblNewLabel = new JLabel("Thể loại");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 145, 99, 28);
		pnLoc.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tác giả");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 228, 99, 20);
		pnLoc.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nhà xuất bản");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 303, 124, 24);
		pnLoc.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nhà cung cấp");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 382, 124, 27);
		pnLoc.add(lblNewLabel_3);

		List<Sach> dsSach = sachService.phanTrangSach(1);
		CapNhatDulieuMoiVaoTable(dsSach);
		CapNhatComboBox();

	}

	private void CapNhatComboBox() throws RemoteException {
		List<TacGia> dsTacGia = tacGiaService.layDanhSachTacGia();
		List<NhaCungCap> dsNhaCungCap = nhaCungCapService.layDanhSachNhaCungCap();
		List<NhaXuatBan> dsNhaXuatBan = nhaXuatBanService.layDanhSachNhaXuatBan();
		List<TheLoaiSach> dsLoaiSach = theLoaiService.layToanBoDanhSachTheLoaiSach();
		for (TacGia tg : dsTacGia) {
			String tacGia = tg.getTenTacGia();
			cbbTacGiaorChatLieu.addItem(tacGia);
		}

		for (NhaCungCap ncc : dsNhaCungCap) {
			String nhaCC = ncc.getTenNCC();
			cbbNhaCungCap.addItem(nhaCC);
		}

		for (NhaXuatBan nxb : dsNhaXuatBan) {
			String nhaXB = nxb.getTenNXB();
			cbbNhaXBorXuatXu.addItem(nhaXB);
		}
		for (TheLoaiSach tls : dsLoaiSach) {
			String TheLS = tls.getTenLoai();
			cbbLoai.addItem(TheLS);
		}

	}

	private void CapNhatDulieuMoiVaoTable(List<Sach> dsSach) throws RemoteException {
		DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
		dm.getDataVector().removeAllElements();
		dsSach = sachService.timTatCaSach();
		for (Sach a : dsSach) {
			String tacGia;
			if (a.getTacGia().getTenTacGia() == null) {
				tacGia = "Chưa cập nhật";
			} else {
				tacGia = a.getTacGia().getTenTacGia();
			}

			String nhaCC;
			if (a.getNhaXuatBan() == null) {
				nhaCC = "Chưa cập nhật";
			} else {
				nhaCC = a.getNhaCungCap().getTenNCC();
			}
			String nhaXB;
			if (a.getNhaXuatBan() == null) {
				nhaXB = "Chưa cập nhật";
			} else {
				nhaXB = a.getNhaXuatBan().getTenNXB();
			}

			String theLoai;
			if (a.getTheLoaiSach() == null) {
				theLoai = "Chưa cập nhật";
			} else {
				theLoai = a.getTheLoaiSach().getTenLoai();
			}

			modelSanPham.addRow(new Object[] { a.getMaSach(), a.getTenSach(), a.getSoLuongTon(), a.getGiaNhap(), nhaCC,
					a.getSoTrang(), theLoai, tacGia, nhaXB

			});
		}

	}

	public ImageIcon setSizeImageIconURL(URL url, int width, int height) {
		ImageIcon image = new ImageIcon(url);
		Image imageSet = image.getImage();
		imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		image = new ImageIcon(imageSet);
		return image;
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
		int row = tableSanPham.getSelectedRow();

		Sach sach = null;
		try {
			sach = sachService.laySachBangMa(modelSanPham.getValueAt(row, 0).toString()).get(0);
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//System.out.println(sach.getHinhAnh());
		lblTenSP.setText("Tên sản phẩm:" + modelSanPham.getValueAt(row, 1).toString());
		lblSoLuong.setText("Số lượng:" + modelSanPham.getValueAt(row, 2).toString());
		lblGiaNhap.setText("Đơn giá:" + modelSanPham.getValueAt(row, 3).toString());
		lblGiaBan.setText("Nhà xuất bản:" + modelSanPham.getValueAt(row, 8).toString());
		lblNhaCungCap.setText("Nhà cung cấp:" + modelSanPham.getValueAt(row, 4).toString());
		lblTacGiaOrChatLieu.setText("Tác giả:" + modelSanPham.getValueAt(row, 7).toString());
		if (CapNhatHinhAnh(sach) != null) {
			lblImageSP.setIcon(CapNhatHinhAnh(sach));
		} else {
			try {
				lblImageSP.setIcon(CapNhatHinhAnh(sachService.laySachBangMa("SP00002").get(0)));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private Icon CapNhatHinhAnh(Sach sach) {
		String lib = "..\\BTL_PhanTan_Nhom22\\HinhAnhSP";
		Image image = new ImageIcon(lib + "\\" + sach.getHinhAnh()).getImage();
		Image newImage = image.getScaledInstance(140, 163, Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(newImage);
		return icon;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnTImKiem)) {
			try {
				CapNhatTimKiem();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (obj.equals(btnLamMoiLoc)) {
			try {
				LamMoi();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (obj.equals(btnThemSP)) {
			try {
				new Frm_ThemSP(port, host).setVisible(true);
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
		}
		if (obj.equals(btnQuanLyDanhMuc)) {
			try {
				new Frm_QuanLyDanhMuc("", port, host).setVisible(true);
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
		}
		if (obj.equals(btnCapNhat)) {
			int row = tableSanPham.getSelectedRow();
			if (row > -1) {
				String ma = modelSanPham.getValueAt(row, 0).toString();
				try {
					new Frm_CapNhatSP(ma, "", port, host).setVisible(true);
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
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để cập nhật");
			}

		}
//		if (obj.equals(btnTruoc)) {
//			int trangHT = Integer.parseInt(lblMauTin.getText().substring(0, 1));
//			if (trangHT > 1) {
//				List<Sach> dsSachMoi = null;
//				try {
//					dsSachMoi = sachService.phanTrangSach(--trangHT);
//				} catch (RemoteException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				try {
//					CapNhatDulieuMoiVaoTable(dsSachMoi);
//				} catch (RemoteException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				try {
//					lblMauTin.setText(trangHT + "\\" + sachService.laySoTrangCuaSach());
//				} catch (RemoteException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			} else {
//				JOptionPane.showMessageDialog(this, "Đã ở trang đầu");
//			}
//		}
//		if (obj.equals(btnSau)) {
//			int trangHT = Integer.parseInt(lblMauTin.getText().substring(0, 1));
//			System.out.println(trangHT);
//			try {
//				if (trangHT < sachService.laySoTrangCuaSach()) {
//					List<Sach> dsSachMoi = sachService.phanTrangSach(++trangHT);
//					CapNhatDulieuMoiVaoTable(dsSachMoi);
//					lblMauTin.setText(trangHT + "\\" + sachService.laySoTrangCuaSach());
//				} else {
//					JOptionPane.showMessageDialog(this, "Đã ở trang cuối");
//				}
//			} catch (HeadlessException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (RemoteException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}

		else if (obj.equals(btnLamMoiDanhSach)) {
			try {
				CapNhatDulieuMoiVaoTable(sachService.phanTrangSach(1));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void LamMoi() throws RemoteException {
		CapNhatComboBox();

		CapNhatDulieuMoiVaoTable(sachService.phanTrangSach(1));
		txtMaSP.setText("");
		txtTenSP.setText("");
		cbbLoai.setSelectedItem("Trống");
		cbbNhaCungCap.setSelectedItem("Trống");
		cbbNhaXBorXuatXu.setSelectedItem("Trống");
		cbbTacGiaorChatLieu.setSelectedItem("Trống");
	}

	private void CapNhatTimKiem() throws RemoteException {
		String ma;
		if (txtMaSP.getText().trim().equalsIgnoreCase("Mã sản phẩm")) {
			ma = "";
		} else {
			ma = txtMaSP.getText().trim();
		}
		String ten;
		if (txtTenSP.getText().trim().equalsIgnoreCase("Tên sản phẩm")) {
			ten = "";
		} else {
			ten = txtTenSP.getText().trim();
		}
		String tenTG = cbbTacGiaorChatLieu.getSelectedItem().toString();
		//System.out.println(tenTG);
		String tenNCC = cbbNhaCungCap.getSelectedItem().toString();
		//System.out.println(tenNCC);
		String tenNXB = cbbNhaXBorXuatXu.getSelectedItem().toString();
	//	System.out.println(tenNXB);
		String theLoaiSach = cbbLoai.getSelectedItem().toString();
		//System.out.println(theLoaiSach);

		// System.out.println(theLoaiSach);
		if (theLoaiSach.equalsIgnoreCase("Trống"))
			theLoaiSach = "";
		if (tenTG.equalsIgnoreCase("Trống"))
			tenTG = "";
		if (tenNCC.equalsIgnoreCase("Trống"))
			tenNCC = "";
		if (tenNXB.equalsIgnoreCase("Trống"))
			tenNXB = "";

		clearTableSach();
		try {
			List<Sach> list = sachService.timTatCaSach();
			for (Sach sach : list) {
				String tacGia;
				if (sach.getTacGia().getTenTacGia() == null) {
					tacGia = "Chưa cập nhật";
				} else {
					tacGia = sach.getTacGia().getTenTacGia();
				}
				
				String tenTGDaLay = "";
				if (sach.getTacGia().getTenTacGia() == null) {
					tenTGDaLay = "";
				} else {
					tenTGDaLay = sach.getTacGia().getTenTacGia();
				}
				if (sach.getTheLoaiSach().getTenLoai().toLowerCase().contains(theLoaiSach.toLowerCase())
						&& tenTGDaLay.toLowerCase().contains(tenTG.toLowerCase())
						&& sach.getNhaCungCap().getTenNCC().toLowerCase().contains(tenNCC.toLowerCase())
						&& sach.getNhaXuatBan().getTenNXB().toLowerCase().contains(tenNXB.toLowerCase())
						&& sach.getTenSach().toLowerCase().contains(ten.toLowerCase())
						&& sach.getMaSach().toLowerCase().contains(ma.toLowerCase())) {
					Object[] rowData = { sach.getMaSach(), sach.getTenSach(), sach.getSoLuongTon(), sach.getGiaNhap(),
							sach.getNhaCungCap().getTenNCC(), sach.getSoTrang(), sach.getTheLoaiSach().getTenLoai(),
							tacGia, sach.getNhaXuatBan().getTenNXB() };
					modelSanPham.addRow(rowData);
				}
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void getERR_TimKiemSach(String str) throws RemoteException {
		JOptionPane.showMessageDialog(this,
				"Không tìm thấy quyển sách nào phù hợp với " + str + " đã chọn .\nHãy chọn một " + str + " khác.");

		CapNhatDulieuMoiVaoTable(sachService.phanTrangSach(1));
	}

	public void clearTableSach() {
		DefaultTableModel dtm = (DefaultTableModel) tableSanPham.getModel();
		dtm.setRowCount(0);

	}
}
