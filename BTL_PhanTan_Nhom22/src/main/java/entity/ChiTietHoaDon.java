package entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(ChiTietHoaDonPK.class)
public class ChiTietHoaDon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "maHoaDon")
	private HoaDon hoaDon;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "maSach")
	private Sach sach;
	private int soLuong;
	private long donGia;
	
	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(HoaDon hoaDon, Sach sach, int soLuong, long donGia) {
		super();
		this.hoaDon = hoaDon;
		this.sach = sach;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Sach getSach() {
		return sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [soLuong=" + soLuong + ", donGia=" + donGia + "]";
	}




	
	
}
