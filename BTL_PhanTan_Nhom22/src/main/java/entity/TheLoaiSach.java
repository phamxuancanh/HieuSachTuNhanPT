package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TheLoaiSach  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maLoai;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenLoai;

	@OneToMany(mappedBy = "theLoaiSach")
	private List<Sach> sach;
	
	public TheLoaiSach() {
		super();

	}

	public TheLoaiSach(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}

	public TheLoaiSach(String maLoai, String tenLoai, List<Sach> sach) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.sach = sach;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public List<Sach> getSach() {
		return sach;
	}

	public void setSach(List<Sach> sach) {
		this.sach = sach;
	}

	@Override
	public String toString() {
		return "TheLoaiSach [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}



	
	
}
