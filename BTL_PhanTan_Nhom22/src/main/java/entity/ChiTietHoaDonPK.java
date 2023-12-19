package entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ChiTietHoaDonPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sach;
	private String hoaDon;
	
	public ChiTietHoaDonPK() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(hoaDon, sach);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDonPK other = (ChiTietHoaDonPK) obj;
		return Objects.equals(hoaDon, other.hoaDon) && Objects.equals(sach, other.sach);
	}

	public String getSach() {
		return sach;
	}

	public void setSach(String sach) {
		this.sach = sach;
	}

	public String getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(String hoaDon) {
		this.hoaDon = hoaDon;
	}

	public ChiTietHoaDonPK(String sach, String hoaDon) {
		super();
		this.sach = sach;
		this.hoaDon = hoaDon;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDonPK [sach=" + sach + ", hoaDon=" + hoaDon + "]";
	}
	
	
}
