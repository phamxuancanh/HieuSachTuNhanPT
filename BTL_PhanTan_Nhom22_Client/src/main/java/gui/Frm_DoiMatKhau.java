package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.NhanVien;
import entity.TaiKhoan;
import service.TaiKhoanService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Frm_DoiMatKhau extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JLabel lblDoiMatKhau;
	private JLabel lblOldPass;
	private JLabel lblNewPass;
	private JLabel lblNewPassConfirm;
	private JButton btnDoi;
	private JButton btnThoat;
	private JPasswordField passwordFieldCu;
	private JPasswordField passwordFieldMoi;
	private JPasswordField passwordFieldXacNhan;
	private TaiKhoanService taiKhoanService;
	private NhanVien nv;
	private JLabel lblTenNV;

	private int port;
	private String host;

	
	public Frm_DoiMatKhau(int port, String host) throws SQLException, MalformedURLException, RemoteException, NotBoundException {
		this.port = port;
		this.host = host;
		taiKhoanService = (TaiKhoanService) Naming.lookup("rmi://" + host + ":" + port + "/taiKhoanService");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 337);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDoiMatKhau = new JLabel("ĐỔI MẬT KHẨU");
		lblDoiMatKhau.setForeground(new Color(255, 0, 0));
		lblDoiMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDoiMatKhau.setBounds(0, 11, 525, 64);
		contentPane.add(lblDoiMatKhau);

		lblOldPass = new JLabel("Nhập mật khẩu cũ:");
		lblOldPass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOldPass.setBounds(24, 89, 177, 14);
		contentPane.add(lblOldPass);

		lblNewPass = new JLabel("Nhập mật khẩu mới:");
		lblNewPass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewPass.setBounds(24, 147, 177, 23);
		contentPane.add(lblNewPass);

		lblNewPassConfirm = new JLabel("Xác nhận mật khẩu mới:");
		lblNewPassConfirm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewPassConfirm.setBounds(24, 218, 177, 14);
		contentPane.add(lblNewPassConfirm);

		btnDoi = new JButton("Đổi");
		btnDoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDoi.setBounds(112, 264, 89, 23);
		contentPane.add(btnDoi);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThoat.setBounds(300, 264, 89, 23);
		contentPane.add(btnThoat);

		passwordFieldCu = new JPasswordField();
		passwordFieldCu.setBounds(201, 86, 292, 20);
		contentPane.add(passwordFieldCu);

		passwordFieldMoi = new JPasswordField();
		passwordFieldMoi.setBounds(201, 149, 292, 20);
		contentPane.add(passwordFieldMoi);

		passwordFieldXacNhan = new JPasswordField();
		passwordFieldXacNhan.setBounds(201, 215, 292, 20);
		contentPane.add(passwordFieldXacNhan);

		btnDoi.addActionListener(this);
		btnThoat.addActionListener(this);

		lblTenNV = new JLabel();
		contentPane.add(lblTenNV);

		// System.out.println(taiKhoan);


	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		if (o.equals(btnThoat)) {
			this.setVisible(false);
		}
		if (o.equals(btnDoi)) {
			FrmLogin dangNhap = null;
			try {
				dangNhap = new FrmLogin(port, host);
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
			TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
			char[] pfCu = passwordFieldCu.getPassword();
			String valueCU = new String(pfCu);

			char[] pfMoi = passwordFieldMoi.getPassword();
			String valueMoi = new String(pfMoi);

			char[] pfXacNhan = passwordFieldXacNhan.getPassword();
			String valueXacNhan = new String(pfXacNhan);

			String passCu = taiKhoan.getMatKhau();
			if (passCu.equals(valueCU)) {
				if (valueMoi.equals(valueXacNhan)) {
					try {
						taiKhoanService.doiMatKhau(valueMoi, taiKhoan.getNhanVien().getMaNhanVien());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Mật khẩu cũ nhập không đúng");
			}
		}
	}

}
