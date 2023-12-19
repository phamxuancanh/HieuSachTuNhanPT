package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class NhaXuatBan  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maNXB;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenNXB;
	
	@OneToMany(mappedBy = "nhaXuatBan")
	private List<Sach> sach;

	public NhaXuatBan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhaXuatBan(String maNXB, String tenNXB) {
		super();
		this.maNXB = maNXB;
		this.tenNXB = tenNXB;
	}

	public String getMaNXB() {
		return maNXB;
	}

	public void setMaNXB(String maNXB) {
		this.maNXB = maNXB;
	}

	public String getTenNXB() {
		return tenNXB;
	}

	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}

	@Override
	public String toString() {
		return "NhaXuatBan [maNXB=" + maNXB + ", tenNXB=" + tenNXB + "]";
	}
	
	
	
}
