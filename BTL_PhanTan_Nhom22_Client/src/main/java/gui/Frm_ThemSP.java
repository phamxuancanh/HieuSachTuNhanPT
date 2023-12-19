package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JYearChooser;

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

public class Frm_ThemSP extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> cbbLoai;
	private JComboBox<Object> cbbTacGiaorChatLieu;
	private JComboBox<Object> cbbNhaXBorXuatXu;
	private JComboBox<String> cbbNhaCungCap;
	private JComboBox<String> cbbMauSac;
	private JTextField txtMasp;
	private JLabel lblNewLabel_2;
	private JLabel lblTacGia;
	private JLabel lblNXB;
	private JLabel lblnamXB;
	private JTextField txtSoTrang;
	private JLabel lblSoTrang;
	private JYearChooser chooserNamXB;
	private JLabel lblNewLabel_7;
	private JTextField txtSoLuong;
	private JLabel lblImgSP;
	private JButton btnChooser;
	private JLabel lblNewLabel_9;
	private JTextField txtTenSp;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JTextField txtGiaNhap;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JTextField txtTrongLuong;
	private JButton btnLamMoi;
	private JButton btnHuy;
	private JButton btnThemSP;
	private int truyenFile =0;

	private ArrayList<TheLoaiSach> theLoaiSachs;
	private ArrayList<TacGia> tacGias;
	private ArrayList<NhaXuatBan> nhaXuatBans;
	private ArrayList<NhaCungCap> nhaCungCaps;
	private NhaCungCapService nhaCungCapService;
	private NhaXuatBanService nhaXuatBanService;
	private SachService sachService ;
	private TacGiaService tacGiaService;
	private TheLoaiService theLoaiService ;
	private JTextArea txtAreaGhiChu;
	private JComboBox<String> cbbDonVi;
	private JFileChooser filechoose;
	private String hinhAnh;
	private int port;
	private String host;
	@SuppressWarnings("deprecation")
	public Frm_ThemSP(int port, String host) throws MalformedURLException, RemoteException, NotBoundException {
		
		this.port = port;
		this.host = host;
		
		nhaCungCapService = (NhaCungCapService) Naming.lookup("rmi://" + host + ":" + port + "/nhaCungCapService");
		nhaXuatBanService = (NhaXuatBanService) Naming.lookup("rmi://" + host + ":" + port + "/nhaXuatBanService");
		sachService = (SachService) Naming.lookup("rmi://" + host + ":" + port + "/sachService");
		tacGiaService = (TacGiaService) Naming.lookup("rmi://" + host + ":" + port + "/tacGiaService");
		theLoaiService = (TheLoaiService) Naming.lookup("rmi://" + host + ":" + port + "/theLoaiService");
		setTitle("Thêm Sản Phẩm");
		setResizable(false);
		setSize(800, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã sản Phẩm:");
		lblNewLabel_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 89, 116, 23);
		getContentPane().add(lblNewLabel_1);

		cbbLoai = new JComboBox<Object>();
		cbbLoai.setBackground(new Color(255, 255, 255));
		cbbLoai.setBounds(125, 159, 240, 33);
		getContentPane().add(cbbLoai);

		cbbTacGiaorChatLieu = new JComboBox<Object>();
		cbbTacGiaorChatLieu.setBackground(Color.WHITE);
		cbbTacGiaorChatLieu.setBounds(125, 202, 240, 33);
		getContentPane().add(cbbTacGiaorChatLieu);

		cbbNhaXBorXuatXu = new JComboBox<Object>();
		cbbNhaXBorXuatXu.setBackground(Color.WHITE);
		cbbNhaXBorXuatXu.setBounds(125, 245, 240, 33);
		getContentPane().add(cbbNhaXBorXuatXu);

		cbbNhaCungCap = new JComboBox<String>();
		cbbNhaCungCap.setBackground(Color.WHITE);
		cbbNhaCungCap.setBounds(125, 364, 328, 33);
		getContentPane().add(cbbNhaCungCap);

		cbbMauSac = new JComboBox<String>();
		cbbMauSac.setBackground(Color.WHITE);
		cbbMauSac.setBounds(125, 288, 240, 33);
		getContentPane().add(cbbMauSac);
		cbbMauSac.hide();

		ButtonGroup group = new ButtonGroup();

		JLabel lblNewLabel = new JLabel("THÊM SẢN PHẨM");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 10, 766, 39);
		getContentPane().add(lblNewLabel);

		txtMasp = new JTextField();
		txtMasp.setText(taoMaTuDong());
		txtMasp.setEditable(false);
		txtMasp.setBorder(BorderFactory.createLineBorder(Color.cyan));
		txtMasp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMasp.setBounds(125, 89, 170, 23);
		getContentPane().add(txtMasp);
		txtMasp.setColumns(10);

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

		chooserNamXB = new JYearChooser();
		chooserNamXB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chooserNamXB.setBounds(125, 514, 100, 23);
		getContentPane().add(chooserNamXB);

		lblnamXB = new JLabel("Năm xuất bản:");
		lblnamXB.setForeground(new Color(72, 61, 139));
		lblnamXB.setFont(new Font("Arial", Font.BOLD, 16));
		lblnamXB.setBounds(10, 514, 116, 23);
		getContentPane().add(lblnamXB);

		txtSoTrang = new JTextField();
		txtSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoTrang.setColumns(10);
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

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(125, 331, 170, 23);
		getContentPane().add(txtSoLuong);

		lblImgSP = new JLabel("");
		lblImgSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSP.setBounds(472, 77, 224, 245);
		//lblImgSP.setIcon(setSizeImageIcon(new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg"),
				//lblImgSP.getWidth(), lblImgSP.getHeight()));
		lblImgSP.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(lblImgSP);

		btnChooser = new JButton("Chọn File");
		btnChooser.setBounds(535, 332, 109, 29);
		getContentPane().add(btnChooser);

		lblNewLabel_9 = new JLabel("Tên sản phẩm:");
		lblNewLabel_9.setForeground(new Color(72, 61, 139));
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_9.setBounds(10, 122, 116, 23);
		getContentPane().add(lblNewLabel_9);

		txtTenSp = new JTextField();
		txtTenSp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenSp.setColumns(10);
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

		txtGiaNhap = new JTextField();
		txtGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGiaNhap.setColumns(10);
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

		txtTrongLuong = new JTextField();
		txtTrongLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTrongLuong.setColumns(10);
		txtTrongLuong.setBounds(125, 480, 170, 23);
		getContentPane().add(txtTrongLuong);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 558, 766, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnThemSP = new JButton("Thêm sản phẩm");
		btnThemSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemSP.setBounds(316, 10, 132, 39);
		panel_1.add(btnThemSP);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHuy.setBounds(540, 10, 132, 39);
		panel_1.add(btnHuy);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLamMoi.setBounds(92, 10, 132, 39);
		panel_1.add(btnLamMoi);

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

		cbbDonVi = new JComboBox<String>();
		cbbDonVi.addItem("Cái");
		cbbDonVi.addItem("Cuốn");
		cbbDonVi.addItem("Chiếc");
		cbbDonVi.setBackground(Color.WHITE);
		cbbDonVi.setBounds(125, 438, 240, 33);
		getContentPane().add(cbbDonVi);
		btnChooser.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThemSP.addActionListener(this);
		btnHuy.addActionListener(this);
		
		
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
		for(TheLoaiSach tls: dsLoaiSach) {
			String TheLS = tls.getTenLoai();
			cbbLoai.addItem(TheLS);
		}
		
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if(object.equals(btnThemSP)) {
			if(validData()) {
			String ma =	txtMasp.getText().trim();
			String ten = txtTenSp.getText().trim();
			TheLoaiSach theloai = null;
			try {
				theloai = theLoaiService.layTheLoaiSachBangTen(cbbLoai.getSelectedItem().toString().trim());
			} catch (RemoteException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			TacGia tacGia = null;
			try {
				tacGia = tacGiaService.layTacGiaBangTen(cbbTacGiaorChatLieu.getSelectedItem().toString().trim());
			} catch (RemoteException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			NhaXuatBan nhaXuatBan = null;
			try {
				nhaXuatBan = nhaXuatBanService.layNhaXuatBanBangTen(cbbNhaXBorXuatXu.getSelectedItem().toString().trim());
			} catch (RemoteException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			int soTrang = Integer.parseInt(txtSoTrang.getText().trim());
			int  soLuong = Integer.parseInt(txtSoLuong.getText().trim());
			NhaCungCap nhaCungCap = null;
			try {
				nhaCungCap = nhaCungCapService.layNhaCungCapBangTen(cbbNhaCungCap.getSelectedItem().toString().trim());
			} catch (RemoteException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			long giaNhap =  Long.parseLong(txtGiaNhap.getText().trim());
			String donVi = cbbDonVi.getSelectedItem().toString().trim();
			Double trongLuong = Double.parseDouble(txtTrongLuong.getText().trim());
			int namXB = chooserNamXB.getValue();
			File f = filechoose.getSelectedFile();
			String hinhAnh = f.getName();
			String ghichu = txtAreaGhiChu.getText().trim();
			Sach sachMoi =new Sach(ma, ten, giaNhap, soLuong,trongLuong, ghichu, donVi,hinhAnh , namXB, soTrang, tacGia, nhaXuatBan, theloai, nhaCungCap);
			try {
				if(sachService.taoSach(sachMoi)) {
					File dirTo = null;
					String lib = "..\\BTL_PhanTan_Nhom22\\HinhAnhSP";
					if(checkExistFile(lib+"\\"+f.getName())) {
					try {
						dirTo = createFile(lib+"\\"+f.getName());
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					try {
						CopyFileToLib(f,dirTo);
						System.out.println("copy thành công");
						
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Copy không thành công");
					}}
					JOptionPane.showMessageDialog(this,
							"Thêm sách thành công");
					LamMoi();
				}else {
					JOptionPane.showMessageDialog(this,
							"Thêm sách không thành công");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}
		if(object.equals(btnChooser)) {
			filechoose = new JFileChooser();
			String lib = "..\\BTL_PhanTan_Nhom22\\HinhAnhSP";
			FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Hình Ảnh", "jpg","png");
			filechoose.setFileFilter(imageFilter);
			int x= filechoose.showDialog(this, "Chọn file");
			if(x == JFileChooser.APPROVE_OPTION) {
				File f = filechoose.getSelectedFile();
				 Image image = new ImageIcon(f.getAbsolutePath()).getImage();
				 Image newImage = image.getScaledInstance(224,245, Image.SCALE_DEFAULT);
				 ImageIcon icon = new ImageIcon(newImage);
				 lblImgSP.setIcon(icon);
				 truyenFile =1;
			}
		}
		
	}
	private boolean checkExistFile(String fullPath) {
		File f = new File(fullPath);
		if(f.exists()) {
			return false;
		}
		return true;
	}
	public File createFile(String fullPath) throws IOException {
	    File file = new File(fullPath);
	    file.getParentFile().mkdirs();
	    file.createNewFile();
	    return file;
	}



	
	private void CopyFileToLib(File f, File dirTo) throws IOException {
		InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(f);
            os = new FileOutputStream(dirTo);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
	}
	private void LamMoi() throws RemoteException {
		txtMasp.setText(taoMaTuDong());
		txtTenSp.setText("");
		txtSoTrang.setText("");
		txtSoLuong.setText("");
		txtGiaNhap.setText("");
		txtTrongLuong.setText("");
		txtAreaGhiChu.setText("");
		
		
	}
	private boolean validData() {
		if(txtTenSp.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this,
					"Chưa nhập tên!Vui lòng nhập tên!(Ví dụ:Anh)");
			txtTenSp.setText("");
			return false;
		}else {
			if(txtTenSp.getText().trim().matches("\\w")) {
				JOptionPane.showMessageDialog(this,
						"Tên không chính xác!Vui lòng nhập lại tên!(Ví dụ:Anh)");
				txtTenSp.setText("");
				return false;
			}
		}
		///////////////////////////////////////////////////////////////////
		if(txtSoTrang.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this,
					"Chưa nhập số trang!Vui lòng nhập số trang!(Ví dụ:1)");
			txtSoTrang.setText("");
			return false;
		}else {
			if(txtSoTrang.getText().trim().matches("\\d")) {
				JOptionPane.showMessageDialog(this,
						"Số trang không chính xác!Vui lòng nhập lại số trang!(Ví dụ:1)");
				txtSoTrang.setText("");
				return false;
			}
		}
		////////////////////////////////////////////////////////////////
		if(txtSoLuong.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this,
					"Chưa nhập số lượng!Vui lòng nhập số lượng!(Ví dụ:1)");
			txtSoLuong.setText("");
			return false;
		}else {
			if(txtSoLuong.getText().trim().matches("\\d")) {
				JOptionPane.showMessageDialog(this,
						"Số lượng không chính xác!Vui lòng nhập lại số lượng!(Ví dụ:1)");
				txtSoLuong.setText("");
				return false;
			}
		}
		//////////////////////////////////////////////////////////////
		if(txtGiaNhap.toString().trim().equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(this,
					"Chưa nhập giá!Vui lòng nhập giá!(Ví dụ:100000)");
			txtGiaNhap.setText("");
			return false;
		}else {
			if(txtGiaNhap.getText().trim().matches("\\d")) {
				JOptionPane.showMessageDialog(this,
						"Giá không chính xác!Vui lòng nhập lại gía!(Ví dụ:10000)");
				txtGiaNhap.setText("");
				return false;
			}
		}
		//////////////////////////////////////////////////////////////
		if(txtTrongLuong.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this,
					"Chưa nhập trọng lượng!Vui lòng nhập trọng lượng!(Ví dụ:1.5)");
			txtTrongLuong.setText("");
			return false;
		}else {
			if(txtTrongLuong.getText().trim().matches("\\d")) {
				JOptionPane.showMessageDialog(this,
						"Trọng lượng không chính xác!Vui lòng nhập lại trọng lượng!(Ví dụ:1.5)");
				txtTrongLuong.setText("");
				return false;
			}
		}
		///////////////////////////////////////////////////////////
		if(truyenFile == 0) {
			JOptionPane.showMessageDialog(this,
					"Chưa chọn hình ảnh!Vui lòng chọn hình ảnh");
			return false;
		}
		return true;
	}
	public String taoMaTuDong() throws RemoteException {
		String idPrefix = "SP";
		int length = 0 ;
		length = sachService.timTatCaSach().size();
		String maMoi = idPrefix+String.format("%05d", length +1);
		return maMoi;
	}
}
