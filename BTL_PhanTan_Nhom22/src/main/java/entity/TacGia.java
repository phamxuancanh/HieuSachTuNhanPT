package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TacGia  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maTacGia;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenTacGia;
	
	@OneToMany(mappedBy = "tacGia")
	private List<Sach> sach;
	
	public TacGia() {
		super();
	}

	public TacGia(String maTacGia) {
		super();
		this.maTacGia = maTacGia;
	}


	public TacGia(String maTacGia, String tenTacGia) {
		super();
		this.maTacGia = maTacGia;
		this.tenTacGia = tenTacGia;
	}

	public String getMaTacGia() {
		return maTacGia;
	}

	public void setMaTacGia(String maTacGia) {
		this.maTacGia = maTacGia;
	}

	public String getTenTacGia() {
		return tenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}

	public List<Sach> getSach() {
		return sach;
	}

	public void setSach(List<Sach> sach) {
		this.sach = sach;
	}

	@Override
	public String toString() {
		return "TacGia [maTacGia=" + maTacGia + ", tenTacGia=" + tenTacGia + "]";
	}


	
	
}
