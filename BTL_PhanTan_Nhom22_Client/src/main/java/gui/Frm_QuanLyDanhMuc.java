package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import service.NhanVienService;
import service.SachService;
import service.TacGiaService;
import service.TaiKhoanService;
import service.TheLoaiService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class Frm_QuanLyDanhMuc extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMa;
	private JTextField txtTen;
	private DefaultTableModel modelThuocTinh;
	private JTable tableThuocTinh;
	private JScrollPane scrollThuocTinh;
	private JLabel lblMa;
	private JLabel lblTen;
	private JRadioButton radTheLoai;
	private JRadioButton radNXBorXuatXu;
	private JRadioButton radTacGiaorChatLieu;
	private JRadioButton radNhaCungCap;
	private JRadioButton radMauSac;
	private JButton btnThem;
	private String loaiSanPham;

	private ArrayList<TheLoaiSach> theLoaiSachs;
	private ArrayList<NhaCungCap> nhaCungCaps;
	private ArrayList<NhaXuatBan> nhaXuatBans;
	private ArrayList<TacGia> tacGias;

	private JLabel lblTitle;
	private JButton btnHuy;
	private ButtonGroup group;
	private JTextField txtEmail;
	private JTextField txtSdt;
	private JLabel lblSdt;
	private JLabel lblEmail;
	private JTextField txtDiaChi;
	private JLabel lblDiaChi;
	
	private NhaCungCapService nhaCungCapService;
	private NhaXuatBanService nhaXuatBanService;
	private SachService sachService;
	private TacGiaService tacGiaService;
	private TheLoaiService theLoaiService;
	private int port;
	private String host;
	
	public Frm_QuanLyDanhMuc(String loai,int port, String host) throws MalformedURLException, RemoteException, NotBoundException {
		this.port = port;
		this.host = host;
		nhaCungCapService = (NhaCungCapService) Naming.lookup("rmi://" + host + ":" + port + "/nhaCungCapService");
		nhaXuatBanService = (NhaXuatBanService) Naming.lookup("rmi://" + host + ":" + port + "/nhaXuatBanService");
		sachService = (SachService) Naming.lookup("rmi://" + host + ":" + port + "/sachService");
		theLoaiService = (TheLoaiService) Naming.lookup("rmi://" + host + ":" + port + "/theLoaiService");
		tacGiaService = (TacGiaService) Naming.lookup("rmi://" + host + ":" + port + "/tacGiaService");
		
		loaiSanPham = loai;
		setResizable(false);
		setSize(1000, 600);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		txtMa = new JTextField();
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMa.setBounds(237, 54, 233, 28);
		getContentPane().add(txtMa);
		txtMa.setColumns(10);

		lblTitle = new JLabel("QUẢN LÝ DANH MỤC");
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 10, 966, 28);
		getContentPane().add(lblTitle);

		lblMa = new JLabel("Mã thể loại:");
		lblMa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMa.setBounds(20, 54, 218, 28);
		getContentPane().add(lblMa);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTen.setColumns(10);
		txtTen.setBounds(237, 95, 233, 28);
		getContentPane().add(txtTen);

		lblTen = new JLabel("Tên thể loại:");
		lblTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTen.setBounds(20, 95, 218, 28);
		getContentPane().add(lblTen);
		String[] cols = { "Mã", "Tên" };
		modelThuocTinh = new DefaultTableModel(cols, 0);
		tableThuocTinh = new JTable(modelThuocTinh);
		tableThuocTinh.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableThuocTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollThuocTinh = new JScrollPane(tableThuocTinh);
		scrollThuocTinh.setBounds(10, 256, 966, 307);

		tableThuocTinh.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableThuocTinh.setAutoCreateRowSorter(true);
		tableThuocTinh.setRowHeight(25);
//		tableThuocTinh.setBackground(Color.decode("#BEFFC0"));
		scrollThuocTinh.getViewport().setBackground(Color.WHITE);
		tableThuocTinh.getTableHeader().setPreferredSize(new Dimension(0, 40));
		getContentPane().add(scrollThuocTinh);

		radTheLoai = new JRadioButton("Thể loại sách");
		radTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radTheLoai.setBounds(525, 54, 211, 21);
		getContentPane().add(radTheLoai);

		radNXBorXuatXu = new JRadioButton("Nhà xuất bản");
		radNXBorXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radNXBorXuatXu.setBounds(525, 95, 157, 21);
		getContentPane().add(radNXBorXuatXu);

		radTacGiaorChatLieu = new JRadioButton("Tác giả");
		radTacGiaorChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radTacGiaorChatLieu.setBounds(738, 54, 91, 21);
		getContentPane().add(radTacGiaorChatLieu);

		radNhaCungCap = new JRadioButton("Nhà cung cấp");
		radNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radNhaCungCap.setBounds(738, 95, 141, 21);
		getContentPane().add(radNhaCungCap);

		radMauSac = new JRadioButton("Màu sắc");
		radMauSac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radMauSac.setBounds(870, 54, 91, 21);
		getContentPane().add(radMauSac);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(525, 133, 122, 28);
		getContentPane().add(btnThem);
		txtMa.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		txtTen.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		radTheLoai.setSelected(true);
		group = new ButtonGroup();
		group.add(radNXBorXuatXu);
		group.add(radTheLoai);
		group.add(radMauSac);
		group.add(radNhaCungCap);
		group.add(radTacGiaorChatLieu);

		btnHuy = new JButton("Thoát");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuy.setBounds(668, 133, 122, 28);
		getContentPane().add(btnHuy);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		txtEmail.setBounds(237, 171, 233, 28);
		getContentPane().add(txtEmail);

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(20, 171, 218, 28);
		getContentPane().add(lblEmail);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSdt.setColumns(10);
		txtSdt.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		txtSdt.setBounds(237, 133, 233, 28);
		getContentPane().add(txtSdt);

		lblSdt = new JLabel("Số điện thoại:");
		lblSdt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSdt.setBounds(20, 133, 218, 28);
		getContentPane().add(lblSdt);
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		txtDiaChi.setBounds(237, 209, 400, 28);
		getContentPane().add(txtDiaChi);

		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDiaChi.setBounds(20, 209, 218, 28);
		getContentPane().add(lblDiaChi);
		lblEmail.setVisible(false);
		lblSdt.setVisible(false);
		lblDiaChi.setVisible(false);
		txtEmail.setVisible(false);
		txtSdt.setVisible(false);
		txtDiaChi.setVisible(false);

		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);
		radNXBorXuatXu.addMouseListener(this);
		radTheLoai.addMouseListener(this);
		radMauSac.addMouseListener(this);
		radNhaCungCap.addMouseListener(this);
		radTacGiaorChatLieu.addMouseListener(this);
		tableThuocTinh.addMouseListener(this);
		radNXBorXuatXu.addActionListener(this);
		radTheLoai.addActionListener(this);
		radMauSac.addActionListener(this);
		radNhaCungCap.addActionListener(this);
		radTacGiaorChatLieu.addActionListener(this);
		CapNhatBangTheoDoiTuong(radTheLoai.getText());
	}

	private void CapNhatBangTheoDoiTuong(String text) throws RemoteException {
		if(text.equalsIgnoreCase(radTheLoai.getText())) {
			DefaultTableModel dm = (DefaultTableModel) tableThuocTinh.getModel();
			dm.getDataVector().removeAllElements();
			List<TheLoaiSach> dsTL = theLoaiService.layToanBoDanhSachTheLoaiSach();
			for (TheLoaiSach a : dsTL) {
				modelThuocTinh.addRow(new Object[] {
						a.getMaLoai(),a.getTenLoai()

				});
			}
		}
		if(text.equalsIgnoreCase(radNhaCungCap.getText())) {
			DefaultTableModel dm = (DefaultTableModel) tableThuocTinh.getModel();
			dm.getDataVector().removeAllElements();
			List<NhaCungCap> dsNCC = nhaCungCapService.layDanhSachNhaCungCap();
			for(NhaCungCap n: dsNCC) {
				modelThuocTinh.addRow(new Object[] {
						n.getMaNCC(),n.getTenNCC()

				});
			}
		}
		if(text.equalsIgnoreCase(radNXBorXuatXu.getText())) {
			DefaultTableModel dm = (DefaultTableModel) tableThuocTinh.getModel();
			dm.getDataVector().removeAllElements();
			List<NhaXuatBan> dsNXB = nhaXuatBanService.layDanhSachNhaXuatBan();
			for(NhaXuatBan n: dsNXB) {
				modelThuocTinh.addRow(new Object[] {
						n.getMaNXB(),n.getTenNXB()

				});
			}
		}
		if(text.equalsIgnoreCase(radTacGiaorChatLieu.getText())) {
			DefaultTableModel dm = (DefaultTableModel) tableThuocTinh.getModel();
			dm.getDataVector().removeAllElements();
			List<TacGia> dsTG = tacGiaService.layDanhSachTacGia();
			for(TacGia n: dsTG) {
				modelThuocTinh.addRow(new Object[] {
						n.getMaTacGia(),n.getTenTacGia()

				});
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(radNhaCungCap.isSelected()) {
			ThayDoiThuocTinhTheoDoiTuong(radNhaCungCap.getText());
			try {
				CapNhatBangTheoDoiTuong(radNhaCungCap.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(radNXBorXuatXu.isSelected()) {
			ThayDoiThuocTinhTheoDoiTuong(radNXBorXuatXu.getText());
			try {
				CapNhatBangTheoDoiTuong(radNXBorXuatXu.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		if(radTacGiaorChatLieu.isSelected()) {
			ThayDoiThuocTinhTheoDoiTuong(radTacGiaorChatLieu.getText());
			try {
				CapNhatBangTheoDoiTuong(radTacGiaorChatLieu.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(radTheLoai.isSelected()) {
			ThayDoiThuocTinhTheoDoiTuong(radTheLoai.getText());
			try {
				CapNhatBangTheoDoiTuong(radTheLoai.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	private void ThayDoiThuocTinhTheoDoiTuong(String text) {
		if(text.equalsIgnoreCase(radTheLoai.getText())) {
			lblMa.setText("Mã thể loại:");
			lblTen.setText("Tên thể loại:");
			lblEmail.setVisible(false);
			lblSdt.setVisible(false);
			lblDiaChi.setVisible(false);
			txtEmail.setVisible(false);
			txtSdt.setVisible(false);
			txtDiaChi.setVisible(false);
		}
		if(text.equalsIgnoreCase(radNhaCungCap.getText())) {
			lblMa.setText("Mã nhà cung cấp:");
			lblTen.setText("Tên nhà cung cấp:");
			lblEmail.setVisible(true);
			lblSdt.setVisible(true);
			lblDiaChi.setVisible(true);
			txtEmail.setVisible(true);
			txtSdt.setVisible(true);
			txtDiaChi.setVisible(true);
		}
		if(text.equalsIgnoreCase(radNXBorXuatXu.getText())) {
			lblMa.setText("Mã nhà xuất bản:");
			lblTen.setText("Tên nhà xuất bản:");
			lblEmail.setVisible(false);
			lblSdt.setVisible(false);
			lblDiaChi.setVisible(false);
			txtEmail.setVisible(false);
			txtSdt.setVisible(false);
			txtDiaChi.setVisible(false);
		}
		if(text.equalsIgnoreCase(radTacGiaorChatLieu.getText())) {
			lblMa.setText("Mã tác giả:");
			lblTen.setText("Tên tác giả:");
			lblEmail.setVisible(false);
			lblSdt.setVisible(false);
			lblDiaChi.setVisible(false);
			txtEmail.setVisible(false);
			txtSdt.setVisible(false);
			txtDiaChi.setVisible(false);
		}
		
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
		Object object = e.getSource();
		if(object.equals(btnThem)) {
			if(radTheLoai.isSelected()) {
				try {
					ThemDuLieuTheoDoiTuong(radTheLoai.getText());
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		
	}

	private void ThemDuLieuTheoDoiTuong(String text) throws HeadlessException, RemoteException {
		if(text.equalsIgnoreCase(radTheLoai.getText())) {
			if(validData(radTheLoai.getText())){
				String ma = txtMa.getText().trim();
				String ten = txtTen.getText().trim();
				TheLoaiSach theLoaiSach = new TheLoaiSach(ma, ten);
				if(theLoaiService.taoTheLoaiSach(theLoaiSach)) {
					JOptionPane.showMessageDialog(this, "Tạo thể loại sách thành công");
				}else {
					JOptionPane.showMessageDialog(this, "Tạo thể loại sách không thành công");
				}
			}
		}
		if(text.equalsIgnoreCase(radNhaCungCap.getText())) {
			if(validData(radNhaCungCap.getText())){
				String ma = txtMa.getText().trim();
				String ten = txtTen.getText().trim();
				String email = txtEmail.getText().trim();
				String sdt= txtSdt.getText().trim();
				String diaChi = txtDiaChi.getText().trim();
				NhaCungCap nhaCungCap = new NhaCungCap(ma, ten, diaChi, email, sdt);
				if(nhaCungCapService.taoNhaCungCap(nhaCungCap)) {
					JOptionPane.showMessageDialog(this, "Tạo nhà cung cấp  thành công");
				}else {
					JOptionPane.showMessageDialog(this, "Tạo nhà cung cấp không thành công");
				}
			}
		}
		if(text.equalsIgnoreCase(radNXBorXuatXu.getText())) {
			if(validData(radNXBorXuatXu.getText())){
				String ma = txtMa.getText().trim();
				String ten = txtTen.getText().trim();
				NhaXuatBan nhaXuatBan = new NhaXuatBan(ma, ten);
				if(nhaXuatBanService.taoNhaXuatBan(nhaXuatBan)) {
					JOptionPane.showMessageDialog(this, "Tạo nhà xuất bản thành công");
				}else {
					JOptionPane.showMessageDialog(this, "Tạo nhà xuất bản không thành công");
				}
			}
		}
		if(text.equalsIgnoreCase(radTacGiaorChatLieu.getText())) {
			if(validData(radTacGiaorChatLieu.getText())){
				String ma = txtMa.getText().trim();
				String ten = txtTen.getText().trim();
				TacGia tacGia= new TacGia(ma, ten);
				if(tacGiaService.taoTacGia(tacGia)) {
					JOptionPane.showMessageDialog(this, "Tạo tác giả thành công");
				}else {
					JOptionPane.showMessageDialog(this, "Tạo tác giả sách không thành công");
				}
			}
		}
	}

	private boolean validData(String text) {
		if(text.equalsIgnoreCase(radTheLoai.getText())) {
			if(txtTen.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập tên thể loại !Vui lòng nhập");
				return false;			
			}
		}
		if(text.equalsIgnoreCase(radNhaCungCap.getText())) {
			if(txtTen.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập tên nhà cung cấp !Vui lòng nhập");
				return false;
			}
			if(txtDiaChi.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ nhà cung cấp !Vui lòng nhập");
				return false;
			}
			if(txtEmail.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập email nhà cung cấp !Vui lòng nhập");
				return false;
			}else {	
				if(txtEmail.getText().trim().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng email  nhà cung cấp !");
					return false;
				}}
			
			if(txtSdt.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại nhà cung cấp !Vui lòng nhập");
				return false;
			}else {
				if(txtSdt.getText().trim().matches("^(84|0[3|5|7|8|9])+([0-9]{8})+$")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số điện thoại nhà cung cấp   nhà cung cấp");
					return false;
			}
		}}
		if(text.equalsIgnoreCase(radNXBorXuatXu.getText())) {
			if(txtTen.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập tên nhà xuất bản !Vui lòng nhập");
				return false;			
			}
		}
		if(text.equalsIgnoreCase(radTacGiaorChatLieu.getText())) {
			if(txtTen.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập tên tác giả !Vui lòng nhập");
				return false;			
			}
		}
		return true;
	}
}	

