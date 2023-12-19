package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.NhanVien;
import entity.TaiKhoan;
import service.NhanVienService;
import service.TaiKhoanService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.JPasswordField;
import java.awt.Color;

public class Frm_QuenMatKhau extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtOTP;
	private JPasswordField passwordFieldPassMoi;
	private JPasswordField passwordFieldConfirm;
	private JButton btnNhanOTP;
	private JButton btnKiemTra;
	private JLabel lblConfirm;
	private JLabel lblNewPass;
	private JLabel lblOTP;
	private JLabel lblEmail;
	private NhanVienService nhanVienService;
	private TaiKhoanService taiKhoanService;
	private JButton btnXong;
	private JLabel lblTitile;
	private int port;
	private String host;
	private String OTP;

	public Frm_QuenMatKhau(int port, String host) throws MalformedURLException, RemoteException, NotBoundException {
		this.port = port;
		this.host = host;
		
		nhanVienService = (NhanVienService) Naming.lookup("rmi://" + host + ":" + port + "/nhanVienService");
		taiKhoanService = (TaiKhoanService) Naming.lookup("rmi://" + host + ":" + port + "/taiKhoanService");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 466);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(51, 109, 46, 19);
		contentPane.add(lblEmail);
		setLocationRelativeTo(null);
		txtEmail = new JTextField();
		txtEmail.setBounds(256, 108, 138, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		lblOTP = new JLabel("Nhập mã OTP: ");
		lblOTP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOTP.setBounds(51, 167, 114, 19);
		contentPane.add(lblOTP);

		txtOTP = new JTextField();
		txtOTP.setBounds(256, 168, 138, 20);
		contentPane.add(txtOTP);
		txtOTP.setColumns(10);

		lblNewPass = new JLabel("Nhập mật khẩu mới: ");
		lblNewPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPass.setBounds(52, 219, 164, 43);
		contentPane.add(lblNewPass);

		lblConfirm = new JLabel("Xác nhận: ");
		lblConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirm.setBounds(51, 287, 80, 23);
		contentPane.add(lblConfirm);

		btnKiemTra = new JButton("Kiểm tra");
		btnKiemTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnKiemTra.setBounds(434, 167, 102, 23);
		contentPane.add(btnKiemTra);

		btnXong = new JButton("Đổi mật khẩu");
		btnXong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXong.setBounds(223, 355, 138, 35);
		contentPane.add(btnXong);

		lblTitile = new JLabel("QUÊN MẬT KHẨU");
		lblTitile.setForeground(new Color(255, 69, 0));
		lblTitile.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitile.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitile.setBounds(0, 11, 586, 65);
		contentPane.add(lblTitile);

		btnNhanOTP = new JButton("Nhận OTP");
		btnNhanOTP.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNhanOTP.setBounds(434, 107, 102, 23);
		contentPane.add(btnNhanOTP);

		passwordFieldPassMoi = new JPasswordField();
		passwordFieldPassMoi.setBounds(256, 232, 267, 20);
		contentPane.add(passwordFieldPassMoi);

		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.setBounds(256, 286, 267, 20);
		contentPane.add(passwordFieldConfirm);

		btnNhanOTP.addActionListener(this);
		btnKiemTra.addActionListener(this);
		btnXong.addActionListener(this);
		passwordFieldPassMoi.setEditable(false);
		passwordFieldConfirm.setEditable(false);
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
		Object obj = e.getSource();
		if (obj.equals(btnNhanOTP)) {
			try {
				if (!kiemTraEmail())
					return;
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sendEmail();
		}
		if (obj.equals(btnKiemTra)) {
			try {
				checkOTP();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (obj.equals(btnXong)) {
			try {
				doiPass();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private boolean kiemTraEmail() throws HeadlessException, RemoteException {
		if (nhanVienService.timNhanVienTheoEmail(txtEmail.getText()) == null) {
			JOptionPane.showMessageDialog(null, "Không có email nhân viên");
			return false;
		}
		return true;
	}

	private void sendEmail() {

		try {
			String fromEmail = "canhmail292@gmail.com";
			String password = "ebrhrjedapdtovof";

			String toEmail = txtEmail.getText();
			// String toEmail = "pxcpaze@gmail.com";
			String fromName = "HIEU SACH N.A.P";
			String subject = "Khoi phuc mat khau";
			String body = "Noi dung email";
			//String OTP = String.format("%06d", new Random().nextInt(999999));
			OTP = nhanVienService.layOTP();
			Timestamp hetHanOTP = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);
			if (nhanVienService.capNhatOTP(toEmail,OTP) == false) {
				System.out.println("ko cap nhat dc");
				return;
			}

			body = "Mã OTP: " + OTP+ "  Lưu ý: OTP hết hạn sau 5 phút";
			
			// cac thong so gmail
			Properties config = new Properties();
			config.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
			config.put("mail.smtp.port", "587"); // TLS Port
			config.put("mail.smtp.auth", "true"); // enable authentication
			config.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

			// dangnhapgmail
			Authenticator authenticator = new Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(fromEmail, password);
				}
			};

			Session session = Session.getInstance(config, authenticator);

			MimeMessage mail = new MimeMessage(session);

			InternetAddress sender = new InternetAddress(fromEmail, fromName, "utf-8");

			mail.setFrom(sender);
			mail.setReplyTo(InternetAddress.parse(fromEmail, false));
			mail.setSubject(subject, "utf-8");
			mail.setText(body, "utf-8");
			mail.setSentDate(new Date());
			mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			Transport.send(mail);
			JOptionPane.showMessageDialog(null, "Đã gửi OTP. Hãy kiểm tra email");
			txtEmail.setEnabled(false);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		catch (Exception e) {
			throw new RuntimeException();
		}
	}

	private void enableMatKhauMoi() {
		passwordFieldPassMoi.setEnabled(true);
		passwordFieldConfirm.setEnabled(true);
		btnXong.setEnabled(true);
	}

	private void checkOTP() throws RemoteException {
		NhanVien nhanVien = nhanVienService.timNhanVienTheoEmail(txtEmail.getText());
		
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		if (OTP.equals(txtOTP.getText().toString().trim())) {
			if (nhanVienService.layHanOTP().getTime() - currentTime.getTime() < 0) {
				JOptionPane.showMessageDialog(this, "OTP đã hết hạn");
				return;
			}

			JOptionPane.showMessageDialog(this, "OTP hợp lệ. Nhập mật khẩu mới");
			enableMatKhauMoi();
			txtOTP.setEnabled(false);
			passwordFieldPassMoi.setEditable(true);
			passwordFieldConfirm.setEditable(true);
		} else {
			JOptionPane.showMessageDialog(this, "OTP sai");

		}
	}

	private void resetForm() {
		txtEmail.setEnabled(true);
		txtOTP.setEnabled(true);
		passwordFieldPassMoi.setEnabled(false);
		passwordFieldConfirm.setEnabled(false);
		btnXong.setEnabled(false);
		clearInput();
	}

	private void clearInput() {
		txtEmail.setText("");
		txtOTP.setText("");
		passwordFieldPassMoi.setText("");
		passwordFieldConfirm.setText("");
	}

	private void doiPass() throws RemoteException {
		char[] pfMoi = passwordFieldPassMoi.getPassword();

		String valueMoi = new String(pfMoi);

		char[] pfXacNhan = passwordFieldConfirm.getPassword();
		String valueXacNhan = new String(pfXacNhan);

		if (valueMoi.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới");
			return;
		}

		if (valueXacNhan.trim().equals(valueMoi.trim())) {
			NhanVien nv = nhanVienService.timNhanVienTheoEmail(txtEmail.getText());
			if (nv == null)
				return;

			if (taiKhoanService.doiMatKhau(valueMoi, nv.getMaNhanVien()) == false)
				return;
			else {
				JOptionPane.showMessageDialog(null, "Đổi mật khẩu mới thành công");
				resetForm();
				this.setVisible(false);
				return;
			}
		}

		JOptionPane.showMessageDialog(null, "Mã OTP không hợp lệ.");
	}

}
