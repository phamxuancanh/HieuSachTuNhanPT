package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.KhachHang;
import service.KhachHangService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Frm_ThemKH extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtSDT;
	private JComboBox cbxGioiTinh;
	private JComboBox cbxDiaChi;
	private JButton btnThemKhachHang;
	private JButton btnLamMoi;
	private JButton btnHuy;
	private int port;
	private String host;
	private KhachHangService khachHangService;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Frm_ThemKH frame = new Frm_ThemKH();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Frm_ThemKH(int port, String host) throws MalformedURLException, RemoteException, NotBoundException  {
		this.port = port;
		this.host = host;
		khachHangService = (KhachHangService) Naming.lookup("rmi://" + host + ":" + port + "/khachHangService");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("THÊM KHÁCH HÀNG");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 414, 32);
		contentPane.add(lblTitle);
		
		JLabel lblMaKhachHang = new JLabel("Mã khách hàng:");
		lblMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaKhachHang.setBounds(20, 55, 140, 25);
		contentPane.add(lblMaKhachHang);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setBounds(184, 55, 240, 25);
		contentPane.add(txtMaKhachHang);
		txtMaKhachHang.setColumns(10);
		
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenKhachHang.setBounds(20, 100, 140, 25);
		contentPane.add(lblTenKhachHang);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(184, 100, 240, 25);
		contentPane.add(txtTenKhachHang);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSDT.setBounds(20, 145, 140, 25);
		contentPane.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(184, 145, 240, 25);
		contentPane.add(txtSDT);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGioiTinh.setBounds(20, 190, 114, 25);
		contentPane.add(lblGioiTinh);
		
		cbxGioiTinh = new JComboBox();
		cbxGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cbxGioiTinh.setBounds(184, 190, 240, 25);
		contentPane.add(cbxGioiTinh);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDiaChi.setBounds(20, 235, 100, 25);
		contentPane.add(lblDiaChi);
		
		cbxDiaChi = new JComboBox();
		cbxDiaChi.setBounds(184, 235, 240, 25);
		cbxDiaChi.setModel(new DefaultComboBoxModel(new String[] { "Thủ Đức", "Quận 1", "Quận 2", "Quận 3 ", "Quận 4",
				"Quận 5", "Quận 6", "Quận 7", "Quận 8", "Quận 9", "Quận 10", "Quận 11", "Quận 12", "Gò Vấp", "Tân Bình",
				"Bình Tân", "Bình Thạnh", "Phú Nhuận", "Tân Phú", "Bình Chánh", "Cần Giờ", "Củ Chi", "Hóc Môn",
				"Nhà Bè" }));
		contentPane.add(cbxDiaChi);
		
		btnThemKhachHang = new JButton("Thêm");
		btnThemKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemKhachHang.setBounds(20, 300, 100, 35);
		contentPane.add(btnThemKhachHang);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLamMoi.setBounds(158, 300, 110, 35);
		contentPane.add(btnLamMoi);
		
		btnHuy = new JButton("Huỷ");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHuy.setBounds(314, 300, 110, 35);
		contentPane.add(btnHuy);
		btnThemKhachHang.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoi.addActionListener(this);
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setText(auto_ID());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(btnThemKhachHang)) {
			if(validData()) {
				System.out.println("a");
				KhachHang kh = revertKhachHangFromTextfields();
				try {
					if(khachHangService.themKhachHang(kh)==true) {
						JOptionPane.showMessageDialog(this, "Thêm thành công 1 khách hàng, vui lòng tiếp tục thanh toán");
						this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(this, "Thêm thất bại");
						this.setVisible(false);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(obj.equals(btnLamMoi)) {
			clearTxtfields();
		}
		else if(obj.equals(btnHuy)) {
			this.setVisible(false);
		}
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
	private void clearTxtfields() {
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
	public boolean validData() {
		String makh = txtMaKhachHang.getText();
		String tenkh =txtTenKhachHang.getText();
		String diaChi = cbxDiaChi.getSelectedItem().toString();
		String sdt = txtSDT.getText();
		boolean gioiTinh = cbxGioiTinh.getSelectedItem().toString() == "Nam" ? true : false;
		
		if(!(tenkh.length()>0 && tenkh.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊ"
				+ "ỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]*"))) {
			JOptionPane.showMessageDialog(this, "Tên khách hàng không hợp lệ");
			txtTenKhachHang.setText("");
			txtTenKhachHang.requestFocus();
			return false;
		}
		if(!(sdt.length()>0 && sdt.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
			txtSDT.setText("");
			txtSDT.requestFocus();
			return false;
		}
		return true;
	}
}
