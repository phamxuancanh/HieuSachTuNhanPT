package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class KhachHang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maKhachHang;
	@Column(columnDefinition = "nvarchar(255)")
	private String hoTenKhachHang;
	private String sDT;
	private boolean gioiTinh;
	@Column(columnDefinition = "nvarchar(255)")
	private String diaChi;

    @OneToMany(mappedBy = "khachHang")
    private List<HoaDon> hoaDon;

    
    
	public String getMaKhachHang() {
		return maKhachHang;
	}



	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}



	public String getHoTenKhachHang() {
		return hoTenKhachHang;
	}



	public void setHoTenKhachHang(String hoTenKhachHang) {
		this.hoTenKhachHang = hoTenKhachHang;
	}



	public String getsDT() {
		return sDT;
	}



	public void setsDT(String sDT) {
		this.sDT = sDT;
	}



	public boolean isGioiTinh() {
		return gioiTinh;
	}



	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}



	public String getDiaChi() {
		return diaChi;
	}



	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}



	public List<HoaDon> getHoaDon() {
		return hoaDon;
	}



	public void setHoaDon(List<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}

	

	public KhachHang(String maKhachHang, String hoTenKhachHang, String sDT, boolean gioiTinh, String diaChi) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTenKhachHang = hoTenKhachHang;
		this.sDT = sDT;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
	}



	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTenKhachHang=" + hoTenKhachHang + ", sDT=" + sDT
				+ ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + "]";
	}



	

}
