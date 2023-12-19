package gui;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import entity.KhachHang;
import entity.NhanVien;
import service.KhachHangService;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class Pn_QuanLyKhachHang extends JPanel implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelKhachHang;
	private JTable tableKhachHang;
	private JScrollPane scrollKhachHang;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtSDT;
	private JComboBox cbxDiaChi;
	private JButton btnLamMoiDanhSach;
	private JButton btnThemKhachHang;
	private JButton btnCapNhat;
	private JComboBox<Object> cbxGioiTinh;
	private List<KhachHang> dsKhachHang;

	private JButton btnRefresh;
	private JLabel lblDiaChi;
	private JButton btnFind;
	
	private JButton btnLuu;
	private KhachHangService khachHangService;
	private int port;
	private String host;

	public Pn_QuanLyKhachHang(int port, String host) throws MalformedURLException, RemoteException, NotBoundException {
		
		this.port = port;
		this.host = host;
		khachHangService = (KhachHangService) Naming.lookup("rmi://" + host + ":" + port + "/khachHangService");
		setBackground(new Color(0, 206, 209));
		setFont(new Font("Dialog", Font.BOLD, 16));
		setSize(1480, 630);
		// setLocation(null);
		setLayout(null);

		JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.DARK_GRAY);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTitle.setBounds(10, 11, 1460, 55);
		add(lblTitle);

		JPanel pnLoc = new JPanel();
		pnLoc.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnLoc.setBackground(new Color(255, 255, 255));
		pnLoc.setBounds(20, 140, 418, 465);
		add(pnLoc);
		pnLoc.setLayout(null);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKhachHang.setBounds(141, 50, 267, 33);
		pnLoc.add(txtMaKhachHang);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setForeground(new Color(0, 0, 0));

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenKhachHang.setForeground(new Color(0, 0, 0));
		txtTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(141, 120, 267, 33);
		pnLoc.add(txtTenKhachHang);

		txtSDT = new JTextField();
		txtSDT.setHorizontalAlignment(SwingConstants.CENTER);
		txtSDT.setForeground(new Color(0, 0, 0));
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		txtSDT.setBounds(141, 190, 267, 33);
		pnLoc.add(txtSDT);
		String[] tinh = { "Thủ Đức", "Quận 1", "Quận 2", "Quận 3 ", "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8",
				"Quận 9", "Quận 10", "Quận 11", "Quận 12", "Gò Vấp", "Tân Bình", "Bình Tân", "Bình Thạnh", "Phú Nhuận",
				"Tân Phú", "Bình Chánh", "Cần Giờ", "Củ Chi", "Hóc Môn", "Nhà Bè" };
		cbxDiaChi = new JComboBox<Object>(tinh);
		cbxDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxDiaChi.setModel(new DefaultComboBoxModel(new String[] { "Thủ Đức", "Quận 1", "Quận 2", "Quận 3 ", "Quận 4",
				"Quận 5", "Quận 6", "Quận 7", "Quận 8", "Quận 9", "Quận 10", "Quận 11", "Quận 12", "Gò Vấp", "Tân Bình",
				"Bình Tân", "Bình Thạnh", "Phú Nhuận", "Tân Phú", "Bình Chánh", "Cần Giờ", "Củ Chi", "Hóc Môn",
				"Nhà Bè" }));
		cbxDiaChi.setBounds(141, 330, 267, 33);
		pnLoc.add(cbxDiaChi);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGioiTinh.setBounds(10, 260, 121, 33);
		pnLoc.add(lblGioiTinh);

		JLabel lblMaKhachHang = new JLabel("Mã khách hàng:");
		lblMaKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaKhachHang.setBounds(10, 50, 121, 33);
		pnLoc.add(lblMaKhachHang);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKhachHang.setBounds(10, 120, 121, 33);
		pnLoc.add(lblTenKhachHang);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT.setBounds(10, 190, 121, 33);
		pnLoc.add(lblSDT);

		cbxGioiTinh = new JComboBox<Object>(new Object[] {});
		cbxGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cbxGioiTinh.setSelectedIndex(0);
		cbxGioiTinh.setBounds(141, 260, 267, 33);
		pnLoc.add(cbxGioiTinh);

		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaChi.setBounds(10, 330, 121, 33);
		pnLoc.add(lblDiaChi);

		btnRefresh = new JButton("Làm mới");
		btnRefresh.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/refresh-button.png")));
		btnRefresh.setForeground(Color.BLACK);
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRefresh.setBounds(10, 400, 190, 40);
		pnLoc.add(btnRefresh);

		btnFind = new JButton("Tìm");
		btnFind.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/loupe.png")));
		btnFind.setForeground(Color.BLACK);
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFind.setBounds(218, 400, 190, 40);
		pnLoc.add(btnFind);

		String[] cols = { "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Giới tính", "Địa chỉ" };
		modelKhachHang = new DefaultTableModel(cols, 0);
		tableKhachHang = new JTable(modelKhachHang);
		tableKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));

		scrollKhachHang = new JScrollPane(tableKhachHang);
		scrollKhachHang.setBounds(448, 77, 1022, 477);
		tableKhachHang.getTableHeader().setBackground(Color.LIGHT_GRAY);
		tableKhachHang.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 17));
		tableKhachHang.setRowHeight(25);
		tableKhachHang.setBackground(Color.WHITE);
		scrollKhachHang.getViewport().setBackground(Color.WHITE);
		tableKhachHang.getTableHeader().setPreferredSize(new Dimension(0, 40));
		add(scrollKhachHang);

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(Color.LIGHT_GRAY);
		pnTimKiem.setBorder(null);
		pnTimKiem.setBounds(20, 77, 418, 64);
		add(pnTimKiem);
		pnTimKiem.setLayout(null);
		JLabel lblTimKiem = new JLabel("Tìm kiếm thông tin khách hàng");
		lblTimKiem.setBounds(0, 0, 418, 64);
		pnTimKiem.add(lblTimKiem);
		lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTimKiem.setForeground(Color.BLACK);
		lblTimKiem.setBackground(Color.LIGHT_GRAY);

		btnLamMoiDanhSach = new JButton("Làm mới bảng");
		btnLamMoiDanhSach.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/refresh-button.png")));
		btnLamMoiDanhSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLamMoiDanhSach.setForeground(Color.BLACK);
		btnLamMoiDanhSach.setFont(new Font("Dialog", Font.BOLD, 16));
		btnLamMoiDanhSach.setBounds(1225, 565, 230, 40);
		add(btnLamMoiDanhSach);

		btnThemKhachHang = new JButton("Thêm khách hàng");
		btnThemKhachHang.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/add-user.png")));
		btnThemKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThemKhachHang.setForeground(Color.BLACK);
		btnThemKhachHang.setFont(new Font("Dialog", Font.BOLD, 16));
		btnThemKhachHang.setBounds(715, 565, 230, 40);
		add(btnThemKhachHang);

		btnCapNhat = new JButton("Cập nhật khách hàng");
		btnCapNhat.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/icons-update.png")));
		btnCapNhat.setForeground(Color.BLACK);
		btnCapNhat.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCapNhat.setBounds(970, 565, 235, 40);
		add(btnCapNhat);

		btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/diskette.png")));
		btnLuu.setFont(new Font("Dialog", Font.BOLD, 16));
		btnLuu.setBounds(465, 565, 230, 40);
		add(btnLuu);
		
		
		btnLuu.setEnabled(false);
		tableKhachHang.addMouseListener(this);
		btnThemKhachHang.addActionListener(this);
		btnLamMoiDanhSach.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnFind.addActionListener(this);
		btnLuu.addActionListener(this);
		btnCapNhat.addActionListener(this);
		moKhoaTextfields(false);
		try {
			DocDuLieuTuArrayListVaoModel();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnThemKhachHang)) {
			if (btnThemKhachHang.getText().equalsIgnoreCase("Thêm khách hàng")) {
				moKhoaTextfields(true);
				moKhoaControls(false);
				clearTxtfields();
				btnThemKhachHang.setEnabled(true);
				btnLuu.setEnabled(true);
				btnRefresh.setEnabled(true);
				btnThemKhachHang.setText("Huỷ");
				txtMaKhachHang.setEditable(false);
				try {
					txtMaKhachHang.setText(auto_ID());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (btnThemKhachHang.getText().equalsIgnoreCase("Huỷ")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				clearTxtfields();
				btnFind.setText("Tìm");
				btnThemKhachHang.setText("Thêm khách hàng");
			}
		} else if (obj.equals(btnRefresh)) {
			clearTxtfields();
		} else if (obj.equals(btnLuu)) {
			if (btnLuu.getText().equalsIgnoreCase("Lưu") && btnThemKhachHang.getText().equalsIgnoreCase("Huỷ")) {
				KhachHang kh = revertKhachHangFromTextfields();
				try {

					try {
						if (khachHangService.themKhachHang(kh) == true) {
							updateTableData(kh);
							JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
							btnThemKhachHang.setText("Thêm khách hàng");
							btnLuu.setEnabled(false);
							moKhoaControls(true);
							moKhoaTextfields(false);
						} else {

//						int er = KhachHangServiceImpl.errors;
//						System.out.println(er);
//						if (er == 1) {
//							JOptionPane.showMessageDialog(this, "Ten  Khach Hang Phai Theo Mau : Nguyen Van Anh");
//							txtTenKhachHang.setText("");
//							txtTenKhachHang.requestFocus();
//						} else if (er == 2) {
//							JOptionPane.showMessageDialog(this, "Số điện thoại cần có 10 chữ số ví dụ: 0384600357");
//							txtSDT.setText("");
//							txtSDT.requestFocus();
//						}
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, "Trùng mã");
				}
			}	else if ((btnLuu.getText().equalsIgnoreCase("Lưu") && btnCapNhat.getText().equalsIgnoreCase("Huỷ"))) {
				KhachHang kh = revertKhachHangFromTextfields();
				try {
					// if(validData()) {
						JOptionPane.showMessageDialog(this, "Sửa thành công 1 khách hàng");
						editOnRow();
						moKhoaTextfields(false);
						moKhoaControls(true);
						clearTxtfields();
						btnLuu.setEnabled(false);
						btnCapNhat.setText("Cập nhật khách hàng");

				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (btnLuu.getText().equalsIgnoreCase("Huỷ")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				clearTxtfields();
				btnLuu.setEnabled(false);
				btnLuu.setText("Lưu");
				btnFind.setText("Tìm");
			}
		} else if (obj.equals(btnFind)) {
			if (btnFind.getText().equalsIgnoreCase("Tìm")) {
				try {
					DocDuLieuTuArrayListVaoModel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clearTxtfields();
				btnThemKhachHang.setEnabled(false);
				btnCapNhat.setEnabled(false);
				btnLuu.setEnabled(true);
				btnLuu.setText("Huỷ");
				btnFind.setText("Tìm kiếm");
				txtSDT.setEditable(true);
				txtMaKhachHang.setEditable(false);
				txtTenKhachHang.setEditable(true);
			} else if (btnFind.getText().equalsIgnoreCase("Tìm kiếm")) {
				// tableKhachHang.setColumnSelectionInterval(-1, -1);
				int i = 0;
				List<KhachHang> kh = new ArrayList<KhachHang>();
				String sdt = txtSDT.getText();
				String tenTim = txtTenKhachHang.getText();
				if (!sdt.isEmpty() && tenTim.isEmpty()) {
					if (!sdt.isEmpty() && tenTim.isEmpty()) {
						try {
							kh = khachHangService.timKhachHangTheoSDT(sdt);
						} catch (RemoteException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						if (kh != null) {
							xoaHetDuLieu();
							for (KhachHang khh : kh) {
								try {
									dsKhachHang = khachHangService.timTatCaKhachHang();
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								modelKhachHang.addRow(new Object[] { ++i, khh.getMaKhachHang(),
										khh.getHoTenKhachHang(), khh.getsDT(),
										khh.isGioiTinh() == true ? "Nam" : "Nữ", khh.getDiaChi() });
							}
							txtSDT.setEditable(false);
							txtTenKhachHang.setEditable(false);
//								JOptionPane.showMessageDialog(this, "Đã tìm thấy");
							btnFind.setText("Tìm");
							btnLuu.setText("Lưu");
							btnLuu.setEnabled(false);
							moKhoaControls(true);
							moKhoaTextfields(false);

						} else {
							JOptionPane.showMessageDialog(this, "Không tìm thấy");
							revertKhachHangFromTextfields();
							clearTxtfields();
							try {
								DocDuLieuTuArrayListVaoModel();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							btnFind.setText("Tìm");
							btnLuu.setText("Lưu");
							btnLuu.setEnabled(false);
							moKhoaControls(true);
							moKhoaTextfields(false);
						}
					}
				} else if (sdt.isEmpty() && !tenTim.isEmpty()) {
					if (!tenTim.isEmpty() && sdt.isEmpty()) {

						try {
							kh = khachHangService.timKhachHangTheoTen(tenTim);
						} catch (RemoteException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						if (kh.size() > 0) {
							xoaHetDuLieu();
							for (KhachHang khh : kh) {
								try {
									dsKhachHang = khachHangService.timTatCaKhachHang();
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								modelKhachHang.addRow(new Object[] { ++i, khh.getMaKhachHang(),
										khh.getHoTenKhachHang(), khh.getsDT(),
										khh.isGioiTinh() == true ? "Nam" : "Nữ", khh.getDiaChi() });
							}
							txtSDT.setEditable(false);
							txtTenKhachHang.setEditable(false);
							JOptionPane.showMessageDialog(this, "Đã tìm thấy");
							btnFind.setText("Tìm");
							btnLuu.setText("Lưu");
							btnLuu.setEnabled(false);
							moKhoaControls(true);
							moKhoaTextfields(false);

						} else {
							JOptionPane.showMessageDialog(this, "Không tìm thấy");
							revertKhachHangFromTextfields();
							try {
								DocDuLieuTuArrayListVaoModel();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							clearTxtfields();
							btnFind.setText("Tìm");
							btnLuu.setText("Lưu");
							btnLuu.setEnabled(false);
							moKhoaControls(true);
							moKhoaTextfields(false);
						}
					}
				} else if (!sdt.isEmpty() && !tenTim.isEmpty()) {
					List<KhachHang> kh1 = new ArrayList<KhachHang>();
					try {
						kh1 = khachHangService.timKhachHangTheoTenVaSDT(tenTim, sdt);
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
					if (kh1 != null) {
						xoaHetDuLieu();
						for (KhachHang khh : kh1) {
							try {
								dsKhachHang = khachHangService.timTatCaKhachHang();
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							modelKhachHang.addRow(new Object[] { ++i, khh.getMaKhachHang(), khh.getHoTenKhachHang(),
									khh.getsDT(), khh.isGioiTinh() == true ? "Nam" : "Nữ", khh.getDiaChi() });
						}
						txtSDT.setEditable(false);
						txtTenKhachHang.setEditable(false);
						JOptionPane.showMessageDialog(this, "Đã tìm thấy");
						btnFind.setText("Tìm");
						btnLuu.setText("Lưu");
						btnLuu.setEnabled(false);
						moKhoaControls(true);
						moKhoaTextfields(false);
					} else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy");
						revertKhachHangFromTextfields();
						try {
							DocDuLieuTuArrayListVaoModel();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						clearTxtfields();
						btnFind.setText("Tìm");
						btnLuu.setText("Lưu");
						btnLuu.setEnabled(false);
						moKhoaControls(true);
						moKhoaTextfields(false);
					}

				} else if (sdt.isEmpty() && tenTim.isEmpty()) {
					xoaHetDuLieu();
					try {
						DocDuLieuTuArrayListVaoModel();
						JOptionPane.showMessageDialog(this,
								"Vui lòng nhập tên khách hàng hoặc số điện thoại để tìm kiếm");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		} else if (obj.equals(btnLamMoiDanhSach)) {
			xoaHetDuLieu();
			try {
				DocDuLieuTuArrayListVaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (obj.equals(btnCapNhat)) {
			if (tableKhachHang.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Phải chọn dòng trước khi sửa");
			} else {
				if (btnCapNhat.getText().equalsIgnoreCase("Cập nhật khách hàng")) {
					moKhoaTextfields(true);
					moKhoaControls(false);
					txtMaKhachHang.setEditable(false);
					btnLuu.setEnabled(true);
					btnCapNhat.setEnabled(true);
					btnRefresh.setEnabled(true);
					btnCapNhat.setText("Huỷ");
				} else if (btnCapNhat.getText().equalsIgnoreCase("Huỷ")) {
					moKhoaTextfields(false);
					moKhoaControls(true);
					clearTxtfields();
					txtMaKhachHang.setEnabled(false);
					btnLuu.setEnabled(false);
					btnCapNhat.setText("Cập nhật khách hàng");
				}
			}
		}
	}

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableKhachHang.getSelectedRow();
		txtMaKhachHang.setText(modelKhachHang.getValueAt(row, 1).toString());
		txtTenKhachHang.setText(modelKhachHang.getValueAt(row, 2).toString());
		txtSDT.setText(modelKhachHang.getValueAt(row, 3).toString());
		cbxGioiTinh.setSelectedIndex(modelKhachHang.getValueAt(row, 4).toString().equalsIgnoreCase("Nam") ? 0 : 1);
		cbxDiaChi.setSelectedItem(modelKhachHang.getValueAt(row, 5).toString());
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
	public KhachHang revertKhachHangFromTextfields() {
		String maKH = txtMaKhachHang.getText();
		String tenKH = txtTenKhachHang.getText();
		String diaChi = cbxDiaChi.getSelectedItem().toString();
		String sdt = txtSDT.getText();
		boolean gioiTinh = cbxGioiTinh.getSelectedItem().toString() == "Nam" ? true : false;
		KhachHang kh = new KhachHang(maKH, tenKH, sdt, gioiTinh, diaChi);
		return kh;
	}

	public void xoaHetDuLieu() {
		DefaultTableModel dtm = (DefaultTableModel) tableKhachHang.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void DocDuLieuTuArrayListVaoModel() throws Exception {
		dsKhachHang = khachHangService.timTatCaKhachHang();
		int i = 1;
		for (KhachHang kh : dsKhachHang) {

			modelKhachHang.addRow(new Object[] { i++, kh.getMaKhachHang(), kh.getHoTenKhachHang(), kh.getsDT(),
					 kh.isGioiTinh() == true ? "Nam" : "Nữ", kh.getDiaChi() });
		}
	}

	private void updateTableData(KhachHang kh) throws SQLException, RemoteException {
		dsKhachHang = khachHangService.timTatCaKhachHang();
		int i = dsKhachHang.size();
		modelKhachHang.addRow(new Object[] { i++, kh.getMaKhachHang(), kh.getHoTenKhachHang(), kh.getsDT(),
				 kh.isGioiTinh() == true ? "Nam" : "Nữ", kh.getDiaChi() });
	}

	public void editOnRow() {
		int row = tableKhachHang.getSelectedRow();
		KhachHang kh = revertKhachHangFromTextfields();

		tableKhachHang.setValueAt(kh.getMaKhachHang(), row, 1);
		tableKhachHang.setValueAt(kh.getHoTenKhachHang(), row, 2);
		tableKhachHang.setValueAt(kh.getsDT(), row, 3);
		tableKhachHang.setValueAt( kh.isGioiTinh() == true ? "Nam" : "Nữ", row, 4);
		tableKhachHang.setValueAt(kh.getDiaChi(), row, 5);
	}
	private void moKhoaControls(boolean b) {
		btnCapNhat.setEnabled(b);
		btnFind.setEnabled(b);
		btnLamMoiDanhSach.setEnabled(b);
		btnRefresh.setEnabled(b);
		btnThemKhachHang.setEnabled(b);
	}

	private void moKhoaTextfields(boolean b) {
		txtMaKhachHang.setEditable(b);
		txtTenKhachHang.setEditable(b);
		txtSDT.setEditable(b);
		cbxDiaChi.setEditable(b);
		cbxGioiTinh.setEditable(b);
	}

	private void clearTxtfields() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtSDT.setText("");
		cbxDiaChi.setSelectedIndex(0);
		cbxGioiTinh.setSelectedIndex(0);
	}
	public  String auto_ID() throws RemoteException {
		String idPrefix = "KH";
		LocalDate myObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
		String formattedString = myObj.format(formatter);
		int length = 0;
		length = khachHangService.timTatCaKhachHang().size();
		String finalId = idPrefix + formattedString + String.format("%04d", length + 1);
		return finalId;
	}
}
