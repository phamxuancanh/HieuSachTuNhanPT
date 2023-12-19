package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class NhaCungCap  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maNCC;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenNCC;
	@Column(columnDefinition = "nvarchar(255)")
	private String diaChi;
	private String email;
	private String sDT;
	
	@OneToMany(mappedBy = "nhaCungCap")
	private List<Sach> sach;

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public NhaCungCap(String maNCC, String tenNCC, String diaChi, String email, String sDT) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.email = email;
		this.sDT = sDT;
	}

	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", email=" + email
				+ ", sDT=" + sDT + "]";
	}
	
	
}
