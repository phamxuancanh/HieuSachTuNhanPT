package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import entity.Sach;


public class Frm_XemChiTietSanPham extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel txtLoai;
	private JLabel txtTacGiaorChatLieu;
	private JLabel txtNhaXBorXuatXu;
	private JLabel txtNhaCungCap;
	private JLabel txtMauSac;
	private JLabel txtMasp;
	private JLabel lblNewLabel_2;
	private JLabel lblTacGia;
	private JLabel lblNXB;
	private JLabel lblnamXB;
	private JLabel txtSoTrang;
	private JLabel lblSoTrang;
	private JLabel chooserNamXB;
	private JLabel lblNewLabel_7;
	private JLabel txtSoLuong;
	private JLabel lblImgSP;
	private JLabel lblNewLabel_9;
	private JLabel txtTenSp;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel txtGiaNhap;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel txtTrongLuong;
	private JButton btnThoat;

	private JTextArea txtAreaGhiChu;
	private JLabel txtDonVi;
	String loaiSanPham;
	Sach sach;
	private int port;
	private String host;
	String maSanPham;

	@SuppressWarnings("deprecation")
	public Frm_XemChiTietSanPham(String maSanPham, String loaiSanPham,int port, String host) {

		this.port = port;
		this.host = host;
		this.loaiSanPham = loaiSanPham;
		this.maSanPham = maSanPham;
		setTitle("Thêm Sản Phẩm");
		setResizable(false);
		setSize(800, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã Sản Phẩm:");
		lblNewLabel_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 89, 116, 23);
		getContentPane().add(lblNewLabel_1);

		txtLoai = new JLabel();
		txtLoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLoai.setBackground(new Color(255, 255, 255));
		txtLoai.setBounds(125, 159, 240, 33);
		getContentPane().add(txtLoai);

		txtTacGiaorChatLieu = new JLabel();
		txtTacGiaorChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTacGiaorChatLieu.setBackground(Color.WHITE);
		txtTacGiaorChatLieu.setBounds(125, 202, 240, 33);
		getContentPane().add(txtTacGiaorChatLieu);

		txtNhaXBorXuatXu = new JLabel();
		txtNhaXBorXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNhaXBorXuatXu.setBackground(Color.WHITE);
		txtNhaXBorXuatXu.setBounds(125, 245, 240, 33);
		getContentPane().add(txtNhaXBorXuatXu);

		txtNhaCungCap = new JLabel();
		txtNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNhaCungCap.setBackground(Color.WHITE);
		txtNhaCungCap.setBounds(125, 364, 328, 33);
		getContentPane().add(txtNhaCungCap);

		txtMauSac = new JLabel();
		txtMauSac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMauSac.setBackground(Color.WHITE);
		txtMauSac.setBounds(125, 288, 240, 33);
		getContentPane().add(txtMauSac);
		txtMauSac.hide();

		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 285, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CHI TIẾT SẢN PHẨM");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 10, 766, 39);
		getContentPane().add(lblNewLabel);

		txtMasp = new JLabel(maSanPham);
		txtMasp.setBorder(BorderFactory.createLineBorder(Color.cyan));
		txtMasp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMasp.setBounds(125, 89, 170, 23);
		getContentPane().add(txtMasp);

		lblNewLabel_2 = new JLabel("Thể loại:");
		lblNewLabel_2.setForeground(new Color(72, 61, 139));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 154, 116, 38);
		getContentPane().add(lblNewLabel_2);

		lblTacGia = new JLabel("Tác giả:");
		lblTacGia.setForeground(new Color(72, 61, 139));
		lblTacGia.setFont(new Font("Arial", Font.BOLD, 16));
		lblTacGia.setBounds(10, 197, 116, 38);
		getContentPane().add(lblTacGia);

		lblNXB = new JLabel("Nhà xuất bản:");
		lblNXB.setForeground(new Color(72, 61, 139));
		lblNXB.setFont(new Font("Arial", Font.BOLD, 16));
		lblNXB.setBounds(10, 240, 116, 38);
		getContentPane().add(lblNXB);

		chooserNamXB = new JLabel();
		chooserNamXB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chooserNamXB.setBounds(125, 514, 100, 23);
		getContentPane().add(chooserNamXB);

		lblnamXB = new JLabel("Năm xuất bản:");
		lblnamXB.setForeground(new Color(72, 61, 139));
		lblnamXB.setFont(new Font("Arial", Font.BOLD, 16));
		lblnamXB.setBounds(10, 514, 116, 23);
		getContentPane().add(lblnamXB);

		txtSoTrang = new JLabel();
		txtSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(txtSoTrang);

		lblSoTrang = new JLabel("Số trang:");
		lblSoTrang.setForeground(new Color(72, 61, 139));
		lblSoTrang.setFont(new Font("Arial", Font.BOLD, 16));
		lblSoTrang.setBounds(10, 290, 116, 23);
		getContentPane().add(lblSoTrang);

		lblNewLabel_7 = new JLabel("Số lượng:");
		lblNewLabel_7.setForeground(new Color(72, 61, 139));
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_7.setBounds(10, 331, 116, 23);
		getContentPane().add(lblNewLabel_7);

		txtSoLuong = new JLabel();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtSoLuong.setBounds(125, 331, 170, 23);
		getContentPane().add(txtSoLuong);

		lblImgSP = new JLabel("");
		lblImgSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSP.setIcon(new ImageIcon(Frm_ThemSP.class.getResource("/gui/icon/user.png")));
		lblImgSP.setBounds(472, 77, 224, 245);
		lblImgSP.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(lblImgSP);

		lblNewLabel_9 = new JLabel("Tên sản phẩm:");
		lblNewLabel_9.setForeground(new Color(72, 61, 139));
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_9.setBounds(10, 122, 116, 23);
		getContentPane().add(lblNewLabel_9);

		txtTenSp = new JLabel();
		txtTenSp.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtTenSp.setBounds(125, 122, 328, 23);
		getContentPane().add(txtTenSp);

		lblNewLabel_10 = new JLabel("Nhà cung cấp:");
		lblNewLabel_10.setForeground(new Color(72, 61, 139));
		lblNewLabel_10.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_10.setBounds(10, 359, 116, 38);
		getContentPane().add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("Giá nhập:");
		lblNewLabel_11.setForeground(new Color(72, 61, 139));
		lblNewLabel_11.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_11.setBounds(10, 405, 116, 23);
		getContentPane().add(lblNewLabel_11);

		txtGiaNhap = new JLabel();
		txtGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtGiaNhap.setBounds(125, 405, 170, 23);
		getContentPane().add(txtGiaNhap);

		lblNewLabel_12 = new JLabel("Đơn vị sp:");
		lblNewLabel_12.setForeground(new Color(72, 61, 139));
		lblNewLabel_12.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_12.setBounds(10, 438, 116, 23);
		getContentPane().add(lblNewLabel_12);

		lblNewLabel_13 = new JLabel("Trọng lượng:");
		lblNewLabel_13.setForeground(new Color(72, 61, 139));
		lblNewLabel_13.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_13.setBounds(10, 481, 116, 23);
		getContentPane().add(lblNewLabel_13);

		txtTrongLuong = new JLabel();
		txtTrongLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTrongLuong.setBounds(125, 480, 170, 23);
		getContentPane().add(txtTrongLuong);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 558, 766, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(316, 10, 132, 39);
		panel_1.add(btnThoat);

		txtAreaGhiChu = new JTextArea();
		txtAreaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtAreaGhiChu.setBounds(491, 400, 285, 158);
		txtAreaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txtAreaGhiChu);

		JLabel lblNewLabel_6_1 = new JLabel("Ghi chú(Mô tả):");
		lblNewLabel_6_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_6_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_6_1.setBounds(490, 374, 127, 23);
		getContentPane().add(lblNewLabel_6_1);

		txtDonVi = new JLabel();
		txtDonVi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDonVi.setBackground(Color.WHITE);
		txtDonVi.setBounds(125, 438, 240, 33);
		getContentPane().add(txtDonVi);
		txtAreaGhiChu.setEditable(false);


		btnThoat.addActionListener(this);
	}

	

	public ImageIcon setSizeImageIcon(ImageIcon icon, int width, int height) {
		ImageIcon image = icon;
		Image imageSet = image.getImage();
		imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		image = new ImageIcon(imageSet);
		return image;
	}



	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
