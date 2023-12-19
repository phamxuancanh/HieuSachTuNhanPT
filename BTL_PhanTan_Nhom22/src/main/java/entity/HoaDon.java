package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import dao.HoaDonDao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class HoaDon  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String maHoaDon;

	@ManyToOne
	@JoinColumn(name = "maNhanVien")
	private NhanVien nhanVien;

	@ManyToOne
	@JoinColumn(name = "maKhachHang")
	private KhachHang khachHang;

	private Date ngayLapHoaDon;
	@Column(columnDefinition = "nvarchar(255)")
	private String ghiChu;
	private double tienKhachDua;
	private boolean tinhTrang;

	@OneToMany(mappedBy = "hoaDon")
	private List<ChiTietHoaDon> sach;

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Date getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(Date ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public double getTienKhachDua() {
		return tienKhachDua;
	}

	public void setTienKhachDua(double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public HoaDon(String maHoaDon, NhanVien nhanVien, KhachHang khachHang, Date ngayLapHoaDon, String ghiChu,
			double tienKhachDua, boolean tinhTrang) {
		super();
		this.maHoaDon = maHoaDon;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.ghiChu = ghiChu;
		this.tienKhachDua = tienKhachDua;
		this.tinhTrang = tinhTrang;
	}
	
//	public static String auto_ID() {
//		HoaDonDao hoadon_dao = new HoaDonDao();
//		String idPrefix = "HD";
//		LocalDate myObj = LocalDate.now();
//		String ngayHD = String.valueOf(myObj.getDayOfMonth());
//		int length = 0;
//		length = hoadon_dao.timTatCaHoaDon().size();
//		String finalId = idPrefix + ngayHD + String.format("%05d", length + 1);
//		return finalId;
//	}
//
//	public static String auto_Date() {
//		LocalDate myObj = LocalDate.now();
//		String ngay = String.valueOf(myObj.getDayOfMonth());
//		String thang = String.valueOf(myObj.getMonthValue());
//		String nam = String.valueOf(myObj.getYear());
//		String finalDate = ngay + "-" + thang + "-" + nam;
//		return finalDate;
//	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLapHoaDon=" + ngayLapHoaDon + ", ghiChu=" + ghiChu
				+ ", tienKhachDua=" + tienKhachDua + ", tinhTrang=" + tinhTrang + "]";
	}
	


}
