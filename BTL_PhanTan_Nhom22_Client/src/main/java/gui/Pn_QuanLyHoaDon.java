package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import entity.ChiTietHoaDon;
import entity.HoaDon;

import entity.KhachHang;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import service.ChiTietHoaDonService;
import service.HoaDonService;
import service.KhachHangService;
import service.SachService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.mail.Flags.Flag;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Pn_QuanLyHoaDon extends JPanel implements ActionListener, MouseListener {
	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelHoaDon;
	private JTable tableHoaDon;
	private JScrollPane scrollHoaDon;
	private JTextField txtMaHoaDon;
	private JTextField txtNgayLap;
	private JTextField txtTenNhanVien;
	private JTextField txtTenNVTim;
	private List<HoaDon> dsHoaDon;
	private List<KhachHang> dsKhachHang;
	private Frm_XemChiTietHoaDon frm_XemChiTietHoaDon;
	private JButton btnRefresh;
	private JButton btnFind;
	private JButton btnXemChiTiet;
	ButtonGroup G;
	private JTextField txtTenKHTim;
	private JLabel lblMHan_1;
	private JTextField txtMahoaDonTim;
	private JLabel lblSDT_1;
	private JTextField txtSDTTim;
	private HoaDonService hoaDonService;
	private KhachHangService khachHangService;
	private ChiTietHoaDonService chiTietHoaDonService;
	private SachService sachService;

	private List<ChiTietHoaDon> dscthd;
	private int port;
	private String host;
	private JButton btnIn;

	public Pn_QuanLyHoaDon(int port, String host) throws MalformedURLException, RemoteException, NotBoundException {

		this.port = port;
		this.host = host;
		hoaDonService = (HoaDonService) Naming.lookup("rmi://" + host + ":" + port + "/hoaDonService");
		khachHangService = (KhachHangService) Naming.lookup("rmi://" + host + ":" + port + "/khachHangService");
		chiTietHoaDonService = (ChiTietHoaDonService) Naming
				.lookup("rmi://" + host + ":" + port + "/chiTietHoaDonService");
		sachService = (SachService) Naming.lookup("rmi://" + host + ":" + port + "/sachService");

		setBackground(new Color(0, 206, 209));
		setFont(new Font("Dialog", Font.BOLD, 16));
		setSize(1480, 630);
		setLayout(null);

		JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.DARK_GRAY);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTitle.setBounds(10, 11, 1460, 55);
		add(lblTitle);

		JPanel pnLoc = new JPanel();
		pnLoc.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnLoc.setBackground(new Color(255, 255, 255));
		pnLoc.setBounds(10, 58, 418, 496);
		add(pnLoc);
		pnLoc.setLayout(null);

		txtTenNVTim = new JTextField();
		txtTenNVTim.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenNVTim.setForeground(new Color(0, 0, 0));
		txtTenNVTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenNVTim.setColumns(10);
		txtTenNVTim.setBounds(141, 207, 267, 33);
		pnLoc.add(txtTenNVTim);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKhachHang.setBounds(10, 341, 121, 33);
		pnLoc.add(lblTenKhachHang);

		JLabel lblSDT = new JLabel("Tên nhân viên:");
		lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT.setBounds(10, 207, 121, 33);
		pnLoc.add(lblSDT);

		txtTenKHTim = new JTextField();
		txtTenKHTim.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenKHTim.setForeground(Color.BLACK);
		txtTenKHTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKHTim.setColumns(10);
		txtTenKHTim.setBounds(141, 341, 267, 33);
		pnLoc.add(txtTenKHTim);

		JPanel pnTimKiem_1 = new JPanel();
		pnTimKiem_1.setLayout(null);
		pnTimKiem_1.setBorder(null);
		pnTimKiem_1.setBackground(Color.LIGHT_GRAY);
		pnTimKiem_1.setBounds(0, 0, 418, 46);
		pnLoc.add(pnTimKiem_1);

		JLabel lblTimKiem_1 = new JLabel("Tìm kiếm thông tin hóa đơn");
		lblTimKiem_1.setBounds(0, 0, 418, 46);
		pnTimKiem_1.add(lblTimKiem_1);
		lblTimKiem_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem_1.setForeground(Color.BLACK);
		lblTimKiem_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTimKiem_1.setBackground(Color.LIGHT_GRAY);

		lblMHan_1 = new JLabel("Mã hóa đơn:");
		lblMHan_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMHan_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMHan_1.setBounds(10, 139, 121, 33);
		pnLoc.add(lblMHan_1);

		txtMahoaDonTim = new JTextField();
		txtMahoaDonTim.setHorizontalAlignment(SwingConstants.CENTER);
		txtMahoaDonTim.setForeground(Color.BLACK);
		txtMahoaDonTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMahoaDonTim.setColumns(10);
		txtMahoaDonTim.setBounds(141, 139, 267, 33);
		pnLoc.add(txtMahoaDonTim);

		lblSDT_1 = new JLabel("Số điện thoại KH:");
		lblSDT_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT_1.setBounds(10, 272, 121, 33);
		pnLoc.add(lblSDT_1);

		txtSDTTim = new JTextField();
		txtSDTTim.setHorizontalAlignment(SwingConstants.CENTER);
		txtSDTTim.setForeground(Color.BLACK);
		txtSDTTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDTTim.setColumns(10);
		txtSDTTim.setBounds(141, 272, 267, 33);
		pnLoc.add(txtSDTTim);

		G = new ButtonGroup();

		String[] cols = { "STT", "Mã hóa đơn", "Nhân viên lâp", "Ngày lập", "Tên khách hàng", "Thành tiền" };
		modelHoaDon = new DefaultTableModel(cols, 0);
		tableHoaDon = new JTable(modelHoaDon);
		tableHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));

		scrollHoaDon = new JScrollPane(tableHoaDon);
		scrollHoaDon.setBounds(448, 76, 1022, 478);
		tableHoaDon.getTableHeader().setBackground(Color.LIGHT_GRAY);
		tableHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 17));
		tableHoaDon.setRowHeight(25);
		tableHoaDon.setBackground(Color.WHITE);
		scrollHoaDon.getViewport().setBackground(Color.WHITE);
		tableHoaDon.getTableHeader().setPreferredSize(new Dimension(0, 40));
		add(scrollHoaDon);
		try {
			DocDuLieuTuArrayListVaoModel();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		btnRefresh = new JButton("Làm mới");
		btnRefresh.setBounds(859, 566, 190, 40);
		add(btnRefresh);
		btnRefresh.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/refresh-button.png")));
		btnRefresh.setForeground(Color.BLACK);
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnFind = new JButton("Tìm");
		btnFind.setBounds(104, 566, 205, 40);
		add(btnFind);
		btnFind.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/loupe.png")));
		btnFind.setForeground(Color.BLACK);
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setForeground(Color.BLACK);
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemChiTiet.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/diskette.png")));
		btnXemChiTiet.setBounds(518, 566, 205, 40);
		add(btnXemChiTiet);

		// add action listener
		btnFind.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnXemChiTiet.addActionListener(this);
		tableHoaDon.addMouseListener(this);

		btnIn = new JButton("In");
		btnIn.setForeground(Color.BLACK);
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIn.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/diskette.png")));
		btnIn.setBounds(1204, 566, 205, 40);
		btnIn.addActionListener(this);
		add(btnIn);
		TableColumnModel columnMode = tableHoaDon.getColumnModel();
		columnMode.getColumn(0).setPreferredWidth(10);

//		SET FORCUS
		txtMahoaDonTim.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				txtSDTTim.setEditable(false);
				txtTenKHTim.setEditable(false);
				txtTenNVTim.setEditable(false);
			}

			public void focusLost(FocusEvent e) {
				txtSDTTim.setEditable(true);
				txtTenKHTim.setEditable(true);
				txtTenNVTim.setEditable(true);
			}
		});

		txtSDTTim.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				txtMahoaDonTim.setEditable(false);
				txtTenKHTim.setEditable(false);
				txtTenNVTim.setEditable(false);
			}

			public void focusLost(FocusEvent e) {
				txtMahoaDonTim.setEditable(true);
				txtTenKHTim.setEditable(true);
				txtTenNVTim.setEditable(true);
			}
		});
		txtTenKHTim.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				txtMahoaDonTim.setEditable(false);
				txtSDTTim.setEditable(false);
				txtTenNVTim.setEditable(false);
			}

			public void focusLost(FocusEvent e) {
				txtMahoaDonTim.setEditable(true);
				txtSDTTim.setEditable(true);
				txtTenNVTim.setEditable(true);
			}
		});
		txtTenNVTim.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				txtMahoaDonTim.setEditable(false);
				txtSDTTim.setEditable(false);
				txtTenKHTim.setEditable(false);
			}

			public void focusLost(FocusEvent e) {
				txtMahoaDonTim.setEditable(true);
				txtSDTTim.setEditable(true);
				txtTenKHTim.setEditable(true);
			}
		});
	}

	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
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

		if (obj.equals(btnXemChiTiet)) {
			int row = tableHoaDon.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào!!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				String maHoaDon = modelHoaDon.getValueAt(row, 1).toString();
				System.out.println(maHoaDon);
				String tenNhanVien = modelHoaDon.getValueAt(row, 2).toString();
				String ngayLap = modelHoaDon.getValueAt(row, 3).toString();
				String tenKhachHang = modelHoaDon.getValueAt(row, 4).toString();
				String tienKhachDua = "";
				String ghiChu = "";
				String tongTienHoaDon = "";
				HoaDon hd = null;
				try {
					hd = hoaDonService.timHoaDonTheoMa(maHoaDon);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tienKhachDua = hd.getTienKhachDua() + "";
				try {
					tongTienHoaDon = tongTienHoaDon(maHoaDon) + "";
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				ghiChu = hd.getGhiChu();
				try {
					new Frm_XemChiTietHoaDon(maHoaDon, tenNhanVien, ngayLap, tenKhachHang, tienKhachDua, tongTienHoaDon,
							ghiChu, port, host).setVisible(true);
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (obj.equals(btnFind)) {

			if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
					&& txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm");
			} else if (!txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
					&& txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
				String maTim = txtMahoaDonTim.getText().trim();
				try {
					DocDuLieuTimKiemTuArrayListVaoModelTheoMa(maTim);
					xoaTrang();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
					&& txtSDTTim.getText().equals("") && !txtTenNVTim.getText().equals("")) {
				String tenTim = txtTenNVTim.getText().trim();
				try {
					DocDuLieuTimKiemTuArrayListVaoModelTheoTen(tenTim);
					xoaTrang();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
					&& !txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
				String sdtTim = txtSDTTim.getText().trim();
				try {
					DocDuLieuTimKiemTuArrayListVaoModelTheoSDT(sdtTim);
					xoaTrang();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (txtMahoaDonTim.getText().equals("") && !txtTenKHTim.getText().equals("")
					&& txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
				String tenKHTim = txtTenKHTim.getText().trim();
				try {
					DocDuLieuTimKiemTuArrayListVaoModelTheoTenKH(tenKHTim);
					xoaTrang();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		if (obj.equals(btnRefresh)) {
			xoaHetDuLieu();
			try {
				DocDuLieuTuArrayListVaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(obj.equals(btnIn)) {
			if(tableHoaDon.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(this, "Phải chọn hóa đơn cần in");
			}
			else {
				int row = tableHoaDon.getSelectedRow();
				String maHD =  tableHoaDon.getValueAt(row, 1).toString();
				xuatHoaDon(maHD);
			}
		}

	}

	public void setColumnName(String st1, String st2) {
		JTableHeader HEADER = tableHoaDon.getTableHeader();
		TableColumnModel TMC = HEADER.getColumnModel();
		TableColumn TC = TMC.getColumn(1);
		TC.setHeaderValue(st1);
		TableColumn TC2 = TMC.getColumn(5);
		TC2.setHeaderValue(st2);
		HEADER.repaint();
		tableHoaDon.getTableHeader().repaint();
	}

	public long tongTienHoaDon(String maHoaDon) throws RemoteException {
		long tongTien = 0;
		List<ChiTietHoaDon> listCTHD = chiTietHoaDonService.timChiTietHoaDonTheoMaHD(maHoaDon);
		for (ChiTietHoaDon chiTietHoaDon : listCTHD) {
			tongTien += (chiTietHoaDon.getDonGia() * chiTietHoaDon.getSoLuong());
		}
		return tongTien;
	}

	public void DocDuLieuTuArrayListVaoModel() throws Exception {
		dsHoaDon = hoaDonService.timTatCaHoaDon();
		int i = 1;
		for (HoaDon hoaDon : dsHoaDon) {
			modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
					hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
					tongTienHoaDon(hoaDon.getMaHoaDon()) });
		}

	}

	public void DocDuLieuTimKiemTuArrayListVaoModelTheoMa(String maHoaDon) throws Exception {
		HoaDon hoaDon = hoaDonService.timHoaDonTheoMa(maHoaDon);
		if (hoaDon == null) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
					hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
					tongTienHoaDon(hoaDon.getMaHoaDon()) });
		}
	}

	public void DocDuLieuTimKiemTuArrayListVaoModelTheoTen(String tenNV) throws Exception {
		dsHoaDon = hoaDonService.timHoaDonTheoTenNV(tenNV);
		if (dsHoaDon.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDon hoaDon : dsHoaDon) {
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
						hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
						tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
		}

	}

	public void DocDuLieuTimKiemTuArrayListVaoModelTheoTenKH(String tenKH) throws Exception {
		dsHoaDon = hoaDonService.timHoaDonTheoTenKH(tenKH);
		if (dsHoaDon.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDon hoaDon : dsHoaDon) {
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
						hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
						tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
		}

	}

	public void DocDuLieuTimKiemTuArrayListVaoModelTheoSDT(String sdt) throws Exception {
		dsHoaDon = hoaDonService.timHoaDonTheoSDT(sdt);
		if (dsHoaDon.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDon hoaDon : dsHoaDon) {
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
						hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
						tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
		}

	}

	public void xoaHetDuLieu() {
		DefaultTableModel dtm = (DefaultTableModel) tableHoaDon.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void xoaTrang() {
		txtMahoaDonTim.setText("");
		txtTenKHTim.setText("");
		txtSDTTim.setText("");
		txtTenNVTim.setText("");
	}
	
	public void xuatHoaDon(String maHD) {
        try {
            Hashtable map = new Hashtable();
            File file = new File("");
            String path = file.getAbsolutePath();
            JasperReport report = JasperCompileManager.compileReport(path + "//src//main//java//gui//HoaDon.jrxml");
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
