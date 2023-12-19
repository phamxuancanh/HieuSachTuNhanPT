package gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Pn_TrangChu extends JPanel {

	/**
	 * Create the panel.
	 */
	private int port;
	private String host;
	public Pn_TrangChu(int port, String host) {
		this.port = port;
		this.host = host;
		setSize(1500, 700);
		setLayout(null);

		JLabel lblBackGround = new JLabel("");
		lblBackGround.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblBackGround.setIcon(new ImageIcon(Pn_TrangChu.class.getResource("/gui/icon/background.jpg")));
		lblBackGround.setBackground(new Color(210, 105, 30));
		lblBackGround.setBounds(0, 0, 936, 700);
		add(lblBackGround);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(935, 0, 592, 700);
		add(panel);
		panel.setLayout(null);

		JLabel lblTenHieuSach = new JLabel("HIỆU SÁCH N.A.P");
		lblTenHieuSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenHieuSach.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTenHieuSach.setBounds(0, 29, 565, 106);
		panel.add(lblTenHieuSach);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Pn_TrangChu.class.getResource("/gui/icon/nap-removebg-preview.png")));
		lblLogo.setBounds(30, 353, 479, 311);
		panel.add(lblLogo);

		JLabel lblCanh = new JLabel("Phạm Xuân Cảnh");
		lblCanh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCanh.setBounds(54, 202, 213, 27);
		panel.add(lblCanh);

		JLabel lblHai = new JLabel("Lê Thanh Hải");
		lblHai.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHai.setBounds(54, 266, 213, 27);
		panel.add(lblHai);

		JLabel lblAnh = new JLabel("Nguyễn Việt Anh");
		lblAnh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAnh.setBounds(54, 144, 213, 27);
		panel.add(lblAnh);

	}
}
