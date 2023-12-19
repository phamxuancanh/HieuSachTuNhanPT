package gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import entity.NhanVien;
import entity.TaiKhoan;
import service.NhanVienService;
import service.TaiKhoanService;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class Pn_QuanLyNhanVien extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaNhanVien;
	private JTextField txtTenNV;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JScrollPane sp_tableNhanVien;
	private JTable table_NhanVien;
	private DefaultTableModel tableModel_NhanVien;
	private JPanel panel_Right;
	private JPanel panel_TitleBoLoc;
	private JLabel lblNewLabel;
	private JLabel lblTenNhanVien;
	private JLabel lblMaNhanVien;
	private JLabel lblSDT;
	private JLabel lblGioiTinh;
	private JLabel lblCaLam;
	private JLabel lblChucVu;
	private JLabel lblDiaChi;
	private JComboBox comboBoxGioiTinh;
	private JComboBox comboBoxCaLam;
	private JComboBox comboBoxChucVu;
	private JButton btnThemNV;
	private JButton btnSuaNV;
	private JButton btnTimKiem;
	private JButton btnLamMoi;
	private JButton btnLamMoiBang;
	private JPanel panelTop;
	private JLabel lblTitle;
	private JButton btnLuu;
	private List<NhanVien> dsNhanVien;
	private JLabel lblCCCD;
	private JTextField txtCCCD;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblNgaySinh;
	private JButton btnChonAnh;
	private JDateChooser dateChooserNgaySinh;

	private JPanel pnlHinhAnh;
	private File file = null;
	private JLabel lblHinhAnh;
	private JPanel panelTim;
	private JLabel lblMaNhanVien_1;
	private JTextField txtSDTTim;
	private JTextField txtTenTim;
	private JFileChooser filechoose;

	private NhanVienService nhanVienService;
	private TaiKhoanService taiKhoanService;
	private int port;
	private String host;

	public Pn_QuanLyNhanVien(int port, String host) throws MalformedURLException, RemoteException, NotBoundException {

		this.port = port;
		this.host = host;
		
		nhanVienService = (NhanVienService) Naming.lookup("rmi://" + host + ":" + port + "/nhanVienService");
		taiKhoanService = (TaiKhoanService) Naming.lookup("rmi://" + host + ":" + port + "/taiKhoanService");
		setBackground(new Color(0, 206, 209));
		setFont(new Font("Dialog", Font.BOLD, 16));
		setSize(1400, 682);
		setLayout(null);

		panel_Right = new JPanel();
		panel_Right.setBounds(22, 11, 544, 647);
		add(panel_Right);
		panel_Right.setLayout(null);

		panel_TitleBoLoc = new JPanel();
		panel_TitleBoLoc.setBackground(new Color(128, 128, 128));
		panel_TitleBoLoc.setBounds(0, 0, 544, 40);
		panel_Right.add(panel_TitleBoLoc);
		panel_TitleBoLoc.setLayout(null);

		lblNewLabel = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblNewLabel.setBackground(new Color(128, 128, 128));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 0, 544, 40);
		panel_TitleBoLoc.add(lblNewLabel);

		lblTenNhanVien = new JLabel("Tên nhân viên: ");
		lblTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenNhanVien.setBounds(10, 205, 93, 14);
		panel_Right.add(lblTenNhanVien);

		lblMaNhanVien = new JLabel("Mã nhân viên: ");
		lblMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaNhanVien.setBounds(10, 170, 93, 14);
		panel_Right.add(lblMaNhanVien);

		lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSDT.setBounds(10, 249, 104, 14);
		panel_Right.add(lblSDT);

		lblGioiTinh = new JLabel("Giới tính: ");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGioiTinh.setBounds(10, 294, 93, 14);
		panel_Right.add(lblGioiTinh);

		lblCaLam = new JLabel("Ca làm: ");
		lblCaLam.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCaLam.setBounds(204, 327, 46, 14);
		panel_Right.add(lblCaLam);

		lblChucVu = new JLabel("Chức vụ: ");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChucVu.setBounds(10, 327, 76, 14);
		panel_Right.add(lblChucVu);

		lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiaChi.setBounds(10, 364, 71, 14);
		panel_Right.add(lblDiaChi);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(102, 167, 147, 20);
		panel_Right.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		txtTenNV = new JTextField();
		txtTenNV.setBounds(102, 202, 147, 20);
		panel_Right.add(txtTenNV);
		txtTenNV.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setBounds(102, 246, 147, 20);
		panel_Right.add(txtSDT);
		txtSDT.setColumns(10);

		comboBoxGioiTinh = new JComboBox();
		comboBoxGioiTinh.setBounds(102, 290, 86, 22);
		comboBoxGioiTinh.addItem("Nam");
		comboBoxGioiTinh.addItem("Nữ");
		panel_Right.add(comboBoxGioiTinh);

		comboBoxCaLam = new JComboBox();
		comboBoxCaLam.setBounds(260, 323, 86, 22);
		comboBoxCaLam.addItem("Sáng");
		comboBoxCaLam.addItem("Chiều");
		panel_Right.add(comboBoxCaLam);

		comboBoxChucVu = new JComboBox();
		comboBoxChucVu.setBounds(102, 323, 86, 22);
		comboBoxChucVu.addItem("Quản lý");
		comboBoxChucVu.addItem("Nhân viên");
		panel_Right.add(comboBoxChucVu);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(91, 360, 433, 23);
		panel_Right.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		btnThemNV = new JButton("   Thêm ");
		btnThemNV.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/add-user.png")));
		btnThemNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnThemNV.setBounds(212, 532, 117, 32);
		panel_Right.add(btnThemNV);

		btnSuaNV = new JButton("   Sửa");
		btnSuaNV.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/contract.png")));
		btnSuaNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSuaNV.setBounds(44, 532, 117, 32);
		panel_Right.add(btnSuaNV);

		btnTimKiem = new JButton("   Tìm");
		btnTimKiem.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/loupe.png")));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTimKiem.setBounds(376, 532, 117, 32);
		panel_Right.add(btnTimKiem);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/refresh-button.png")));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLamMoi.setBounds(44, 593, 117, 32);
		panel_Right.add(btnLamMoi);

		btnLuu = new JButton("   Lưu");
		btnLuu.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/diskette.png")));
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLuu.setBounds(212, 593, 117, 32);
		panel_Right.add(btnLuu);

		lblCCCD = new JLabel("Căn cước công dân: ");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCCCD.setBounds(266, 170, 111, 14);
		panel_Right.add(lblCCCD);

		txtCCCD = new JTextField();
		txtCCCD.setBounds(387, 167, 147, 20);
		panel_Right.add(txtCCCD);
		txtCCCD.setColumns(10);

		lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(266, 205, 46, 14);
		panel_Right.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(387, 202, 147, 20);
		panel_Right.add(txtEmail);
		txtEmail.setColumns(10);

		lblNgaySinh = new JLabel("Ngày sinh: ");
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgaySinh.setBounds(266, 249, 76, 14);
		panel_Right.add(lblNgaySinh);

		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setBounds(387, 249, 147, 20);
		panel_Right.add(dateChooserNgaySinh);

		btnChonAnh = new JButton("Chọn ảnh");
		btnChonAnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChonAnh.setBounds(213, 87, 89, 32);
		panel_Right.add(btnChonAnh);

		pnlHinhAnh = new JPanel();
		pnlHinhAnh.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlHinhAnh.setBounds(10, 55, 153, 101);
		panel_Right.add(pnlHinhAnh);
		pnlHinhAnh.setLayout(null);

		lblHinhAnh = new JLabel("hình ảnh");
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setVerticalAlignment(SwingConstants.TOP);
		lblHinhAnh.setBounds(0, 0, 153, 101);

		pnlHinhAnh.add(lblHinhAnh);

		panelTim = new JPanel();
		panelTim.setBorder(
				new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTim.setBounds(44, 401, 449, 120);
		panel_Right.add(panelTim);
		panelTim.setLayout(null);

		lblMaNhanVien_1 = new JLabel("Số điện thoại: ");
		lblMaNhanVien_1.setBounds(44, 34, 81, 14);
		lblMaNhanVien_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelTim.add(lblMaNhanVien_1);

		txtSDTTim = new JTextField();
		txtSDTTim.setColumns(10);
		txtSDTTim.setBounds(206, 31, 147, 20);
		panelTim.add(txtSDTTim);

		txtTenTim = new JTextField();
		txtTenTim.setColumns(10);
		txtTenTim.setBounds(206, 74, 147, 20);
		panelTim.add(txtTenTim);

		JLabel lblTenNhanVien_1 = new JLabel("Tên nhân viên: ");
		lblTenNhanVien_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenNhanVien_1.setBounds(44, 77, 93, 14);
		panelTim.add(lblTenNhanVien_1);

		panelTop = new JPanel();
		panelTop.setBounds(589, 42, 811, 616);
		add(panelTop);

		lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setBounds(0, 0, 1400, 31);
		add(lblTitle);

		panelTop.setLayout(null);
		String header_NhanVien[] = { "STT", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính",
				"Ca làm", "Chức vụ", "Địa chỉ" };
		tableModel_NhanVien = new DefaultTableModel(header_NhanVien, 0);
		table_NhanVien = new JTable(tableModel_NhanVien);
		sp_tableNhanVien = new JScrollPane(table_NhanVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_tableNhanVien.setBounds(10, 34, 791, 551);
		table_NhanVien.setAutoCreateRowSorter(true);
		panelTop.add(sp_tableNhanVien);

		table_NhanVien.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_NhanVien.getColumnModel().getColumn(1).setPreferredWidth(70);
		table_NhanVien.getColumnModel().getColumn(2).setPreferredWidth(120);
		table_NhanVien.getColumnModel().getColumn(3).setPreferredWidth(70);
		table_NhanVien.getColumnModel().getColumn(4).setPreferredWidth(70);
		table_NhanVien.getColumnModel().getColumn(5).setPreferredWidth(40);
		table_NhanVien.getColumnModel().getColumn(6).setPreferredWidth(40);
		table_NhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
		table_NhanVien.getColumnModel().getColumn(8).setPreferredWidth(220);

		table_NhanVien.addMouseListener(this);

		try {
			DocDuLieuTuArrayListVaoModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		moKhoaTextfields(false);
		btnLuu.setEnabled(false);
		txtSDTTim.setEditable(true);
		txtTenTim.setEditable(true);

		btnLamMoiBang = new JButton("   Làm mới bảng");
		btnLamMoiBang.setBounds(376, 590, 117, 39);
		panel_Right.add(btnLamMoiBang);
		btnLamMoiBang.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/refresh-button.png")));
		btnLamMoiBang.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLamMoiBang.addActionListener(this);
		btnThemNV.addActionListener(this);
		btnChonAnh.addActionListener(this);
		btnSuaNV.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTimKiem.addActionListener(this);

	}

//-------------------------------------------------//

	private void moKhoaControls(boolean b) {
		btnThemNV.setEnabled(b);
		btnSuaNV.setEnabled(b);
		btnTimKiem.setEnabled(b);
		btnLuu.setEnabled(b);
	}

	private void moKhoaTextfields(boolean b) {
		txtMaNhanVien.setEditable(b);
		txtTenNV.setEditable(b);
		txtDiaChi.setEditable(b);
		txtSDT.setEditable(b);
		comboBoxChucVu.setEditable(b);
		comboBoxCaLam.setEditable(b);
		txtSDTTim.setEditable(b);
		txtTenTim.setEditable(b);
		txtCCCD.setEditable(b);
		txtEmail.setEditable(b);

	}

	private void clearTxtfields() {
		txtMaNhanVien.setText("");
		txtTenNV.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		comboBoxCaLam.setSelectedIndex(0);
		comboBoxChucVu.setSelectedIndex(0);
		txtSDTTim.setText("");
		txtTenTim.setText("");
		txtCCCD.setText("");
		txtEmail.setText("");
	}

	public ImageIcon ResizeImage(String imgPath) {
		ImageIcon myImage = new ImageIcon(imgPath);
		Image img = myImage.getImage();
		Image newImg = img.getScaledInstance(pnlHinhAnh.getWidth(), pnlHinhAnh.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);

		return image;
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

	public NhanVien revertNhanVienFromTextfields() {
		String maNV = txtMaNhanVien.getText();
		String tenNV = txtTenNV.getText();
		Date ngaySinh = dateChooserNgaySinh.getDate();
		String cccd = txtCCCD.getText();
		String diaChi = txtDiaChi.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		boolean gioiTinh = comboBoxGioiTinh.getSelectedItem().toString() == "Nam" ? true : false;
		boolean chucVu = comboBoxChucVu.getSelectedItem().toString() == "Quản lý" ? true : false;
		boolean caLam = comboBoxCaLam.getSelectedItem().toString() == "Sáng" ? true : false;

		String hinhAnh = "";
		if (file != null) {
			hinhAnh = file.getAbsolutePath();
		}

		NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, cccd, diaChi, sdt, gioiTinh, email, chucVu, caLam, hinhAnh,
				null, null);
		return nv;
	}

	public void xoaHetDuLieu() {
		DefaultTableModel dtm = (DefaultTableModel) table_NhanVien.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void DocDuLieuTuArrayListVaoModel() throws Exception {
		dsNhanVien = nhanVienService.timTatCaNhanVien();
		int i = 1;
		for (NhanVien nv : dsNhanVien) {

			tableModel_NhanVien.addRow(new Object[] { i++, nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.getNgaySinh(),
					nv.getsDT(), nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.isCaLamViec() == true ? "Sáng" : "Chiều",
					nv.isChucVu() == true ? "Quản lý" : "Nhân viên", nv.getDiaChi() });
		}
	}

	private void updateTableData(NhanVien nv) throws SQLException, RemoteException {
		dsNhanVien = nhanVienService.timTatCaNhanVien();
		int i = dsNhanVien.size();
		tableModel_NhanVien.addRow(new Object[] { i++, nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.getNgaySinh(),
				nv.getsDT(), nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.isCaLamViec() == true ? "Sáng" : "Chiều",
				nv.isChucVu() == true ? "Quản lý" : "Nhân viên", nv.getDiaChi() });
	}

	public void editOnRow() {
		int row = table_NhanVien.getSelectedRow();
		NhanVien nv = revertNhanVienFromTextfields();

		table_NhanVien.setValueAt(nv.getMaNhanVien(), row, 1);
		table_NhanVien.setValueAt(nv.getHoTenNhanVien(), row, 2);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String ngay = format.format(nv.getNgaySinh());
		// Date date = new Date(ngay);
		table_NhanVien.setValueAt(ngay, row, 3);

		table_NhanVien.setValueAt(nv.getsDT(), row, 4);
		table_NhanVien.setValueAt(nv.isGioiTinh() == true ? "Nam" : "Nữ", row, 5);
		table_NhanVien.setValueAt(nv.isCaLamViec() == true ? "Sáng" : "Chiều", row, 6);
		table_NhanVien.setValueAt(nv.isChucVu() == true ? "Quản lí" : "Nhân viên", row, 7);
		table_NhanVien.setValueAt(nv.getDiaChi(), row, 8);

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(table_NhanVien)) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			int row = table_NhanVien.getSelectedRow();
			try {
				DefaultTableModel model = (DefaultTableModel) table_NhanVien.getModel();
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(row, 3).toString());

				dateChooserNgaySinh.setDate(date);

				dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd");
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("sai");
			}
			txtMaNhanVien.setText(tableModel_NhanVien.getValueAt(row, 1).toString());
			NhanVien nv = null;
			File file = new File("");

			try {
				nv = nhanVienService.timNhanVienTheoMa(txtMaNhanVien.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			txtCCCD.setText(nv.getcCCD());
			txtEmail.setText(nv.getEmail());
			String hinhAnh = file.getAbsolutePath() + "\\hinhAnhHieuSach\\" + nv.getHinhAnh();
			lblHinhAnh.setIcon(setSizeImageIconString(hinhAnh, lblHinhAnh.getWidth(), lblHinhAnh.getHeight()));

			// lblHinhAnh.setText("111111111");

			txtTenNV.setText(tableModel_NhanVien.getValueAt(row, 2).toString());
			txtSDT.setText(tableModel_NhanVien.getValueAt(row, 4).toString());
			comboBoxGioiTinh.setSelectedIndex(
					tableModel_NhanVien.getValueAt(row, 5).toString().equalsIgnoreCase("Nam") ? 0 : 1);
			comboBoxCaLam.setSelectedIndex(
					tableModel_NhanVien.getValueAt(row, 6).toString().equalsIgnoreCase("Sáng") ? 0 : 1);
			comboBoxChucVu.setSelectedIndex(
					tableModel_NhanVien.getValueAt(row, 7).toString().equalsIgnoreCase("Quản lý") ? 0 : 1);
			txtDiaChi.setText(tableModel_NhanVien.getValueAt(row, 8).toString());
		}
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
		Object o = e.getSource();
		if (o.equals(btnThemNV)) {
			if (btnThemNV.getText().equalsIgnoreCase("   Thêm ")) {

				comboBoxChucVu.setSelectedItem(null);
				comboBoxChucVu.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if (e.getStateChange() == ItemEvent.SELECTED) {

							try {
								dsNhanVien = nhanVienService.timTatCaNhanVien();
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							String maNVMoi = "";
							if (comboBoxChucVu.getSelectedItem().toString().equals("Quản lý")) {
								maNVMoi = "QL";
							} else {
								maNVMoi = "NV";
							}
							LocalDate myObj = LocalDate.now();
							String ngayMaNV = String.valueOf(myObj.getDayOfMonth());
							String thangMaNV = String.valueOf(myObj.getMonthValue());
							// String namMaNV = String.valueOf(myObj.getYear());
							String soLuong = "";
							if (dsNhanVien.size() < 10) {
								soLuong = "0" + (dsNhanVien.size() + 1);
							} else if (dsNhanVien.size() < 100) {
								soLuong = "" + (dsNhanVien.size() + 1);
							}
							maNVMoi = maNVMoi + ngayMaNV + thangMaNV + soLuong;
							txtMaNhanVien.setText(maNVMoi);
						}
					}
				});
				moKhoaTextfields(true);
				moKhoaControls(false);
				txtSDTTim.setEditable(false);
				txtTenTim.setEditable(false);
				btnLuu.setEnabled(true);
				btnThemNV.setEnabled(true);
				clearTxtfields();
				btnThemNV.setText("Hủy");
				txtMaNhanVien.setEditable(false);

			} else if (btnThemNV.getText().equalsIgnoreCase("Hủy")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				clearTxtfields();
				btnLuu.setEnabled(false);
				btnThemNV.setText("   Thêm ");
			}
		}

		else if (o.equals(btnLuu) && btnThemNV.getText().equalsIgnoreCase("Hủy")) {
			// if (validData()) {
			NhanVien nv = revertNhanVienFromTextfields();
			FrmThemTaiKhoan frmThemTK = null;
			try {
				frmThemTK = new FrmThemTaiKhoan(nv, port, host);
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (NotBoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			frmThemTK.setVisible(true);
			try {

				try {
					if (nhanVienService.themNhanVien(nv) == true) {
						updateTableData(nv);
						// JOptionPane.showMessageDialog(this, "Thêm thành công 1 nhân viên");
						moKhoaControls(true);
						moKhoaTextfields(false);
						btnThemNV.setText("   Thêm ");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Trùng mã");
			}
		} else if (o.equals(btnLuu) && btnSuaNV.getText().equalsIgnoreCase("Hủy")) {
			NhanVien nv = revertNhanVienFromTextfields();
			try {
				try {
					nhanVienService.suaNhanVien(nv);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Sửa thành công 1 nhân viên");
				moKhoaControls(true);
				moKhoaTextfields(false);
				editOnRow();
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (o.equals(btnSuaNV)) {
			if (table_NhanVien.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Phải chọn dòng trước khi sửa");
			} else {
				if (btnSuaNV.getText().equalsIgnoreCase("   Sửa")) {
					moKhoaTextfields(true);
					txtSDTTim.setEditable(false);
					txtTenTim.setEditable(false);
					txtMaNhanVien.setEditable(false);
					moKhoaControls(false);
					btnLuu.setEnabled(true);
					btnSuaNV.setEnabled(true);
					btnSuaNV.setText("Hủy");
				} else if (btnSuaNV.getText().equalsIgnoreCase("Hủy")) {
					moKhoaTextfields(false);
					moKhoaControls(true);
					clearTxtfields();
					btnLuu.setEnabled(false);
					btnSuaNV.setText("   Sửa");

				}
			}
		} else if (o.equals(btnTimKiem) && btnTimKiem.getText().equalsIgnoreCase("   Tìm")) {
			txtSDTTim.setEditable(true);
			txtTenTim.setEditable(true);
			moKhoaControls(false);
			btnTimKiem.setEnabled(true);
			NhanVien nv = new NhanVien();
			String sdtTim = txtSDTTim.getText();
			String tenTim = txtTenTim.getText();

			if (!sdtTim.isEmpty() && tenTim.isEmpty()) {

				try {
					dsNhanVien = nhanVienService.timNhanVienTheoSDT(sdtTim);
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				if (dsNhanVien.size() != 0) {

					xoaHetDuLieu();
					for (NhanVien nhanVien : dsNhanVien) {
						try {
							try {
								updateTableData(nhanVien);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							moKhoaControls(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			} else if (sdtTim.isEmpty() && !tenTim.isEmpty()) {
				try {
					dsNhanVien = nhanVienService.timNhanVienTheoTen(tenTim);
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				if (dsNhanVien.size() != 0) {
					xoaHetDuLieu();
					for (NhanVien nhanVien : dsNhanVien) {
						try {
							try {
								updateTableData(nhanVien);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							moKhoaControls(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			} else if (!sdtTim.isEmpty() && !tenTim.isEmpty()) {
				try {
					dsNhanVien = nhanVienService.timNhanVienTheoTenVaSDT(tenTim, sdtTim);
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (dsNhanVien.size() != 0) {

					xoaHetDuLieu();
					for (NhanVien nhanVien : dsNhanVien) {
						try {
							try {
								updateTableData(nhanVien);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							moKhoaControls(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			}

			else if (sdtTim.isEmpty() && tenTim.isEmpty()) {
				xoaHetDuLieu();
				try {
					DocDuLieuTuArrayListVaoModel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm ");
			}

		} else if (o.equals(btnChonAnh)) {
			filechoose = new JFileChooser("D:\\hinhAnhHieuSach");
			FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinh anh", "jpg", "png");
			filechoose.setFileFilter(imageFilter);
			filechoose.setMultiSelectionEnabled(false);

			int x = filechoose.showDialog(this, "Chọn Ảnh");
			if (x == JFileChooser.APPROVE_OPTION) {
				file = filechoose.getSelectedFile();
				lblHinhAnh.setText("");
				lblHinhAnh.setIcon(ResizeImage(file.getAbsolutePath()));
				// System.out.println(file.getAbsolutePath());
			}

		} else if (o.equals(btnLamMoi)) {
			clearTxtfields();
		} else if (o.equals(btnLamMoiBang)) {
			xoaHetDuLieu();
			try {
				DocDuLieuTuArrayListVaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
