package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Sach implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maSach;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenSach;
	private long giaNhap;
	private int soLuongTon;
	private double trongLuong;
	@Column(columnDefinition = "nvarchar(255)")
	private String ghiChu;
	@Column(columnDefinition = "nvarchar(255)")
	private String donViSanPham;
	private String hinhAnh;
	private int namXuatBan;
	private int soTrang;
	
	@OneToMany(mappedBy = "sach")
	private List<ChiTietHoaDon> hoaDon;
	
	@ManyToOne
	@JoinColumn(name = "maTacGia")
	private TacGia tacGia;
	
	@ManyToOne
	@JoinColumn(name = "maNhaXuatBan")
	private NhaXuatBan nhaXuatBan;
	
	@ManyToOne
	@JoinColumn(name = "maTheLoaiSach")
	private TheLoaiSach theLoaiSach;
	
	@ManyToOne
	@JoinColumn(name = "maNhaCungCap")
	private NhaCungCap nhaCungCap;
	

	
	
	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public long getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(long giaNhap) {
		this.giaNhap = giaNhap;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public double getTrongLuong() {
		return trongLuong;
	}

	public void setTrongLuong(double trongLuong) {
		this.trongLuong = trongLuong;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getDonViSanPham() {
		return donViSanPham;
	}

	public void setDonViSanPham(String donViSanPham) {
		this.donViSanPham = donViSanPham;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public int getNamXuatBan() {
		return namXuatBan;
	}

	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	public TacGia getTacGia() {
		return tacGia;
	}

	public void setTacGia(TacGia tacGia) {
		this.tacGia = tacGia;
	}

	public NhaXuatBan getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(NhaXuatBan nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public TheLoaiSach getTheLoaiSach() {
		return theLoaiSach;
	}

	public void setTheLoaiSach(TheLoaiSach theLoaiSach) {
		this.theLoaiSach = theLoaiSach;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	
	public long tinhGiaBan() {
		return getGiaNhap() + getGiaNhap() * 20 / 100;
	}


	public Sach() {
		super();
	}
	
	

	public Sach(String maSach, int soLuongTon) {
		super();
		this.maSach = maSach;
		this.soLuongTon = soLuongTon;
	}

	public Sach(String maSach, String tenSach, long giaNhap, int soLuongTon, double trongLuong,
			String ghiChu, String donViSanPham, String hinhAnh, int namXuatBan, int soTrang, TacGia tacGia,
			NhaXuatBan nhaXuatBan, TheLoaiSach theLoaiSach, NhaCungCap nhaCungCap) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.giaNhap = giaNhap;
		this.soLuongTon = soLuongTon;
		this.trongLuong = trongLuong;
		this.ghiChu = ghiChu;
		this.donViSanPham = donViSanPham;
		this.hinhAnh = hinhAnh;
		this.namXuatBan = namXuatBan;
		this.soTrang = soTrang;
		this.tacGia = tacGia;
		this.nhaXuatBan = nhaXuatBan;
		this.theLoaiSach = theLoaiSach;
		this.nhaCungCap = nhaCungCap;

	}

	@Override
	public String toString() {
		return "Sach [maSach=" + maSach + ", tenSach=" + tenSach + ", giaNhap=" + giaNhap + ", soLuongTon=" + soLuongTon
				+ ", trongLuong=" + trongLuong + ", ghiChu=" + ghiChu + ", donViSanPham=" + donViSanPham + ", hinhAnh="
				+ hinhAnh + ", namXuatBan=" + namXuatBan + ", soTrang=" + soTrang + "]";
	}


	
	



	
	
}
