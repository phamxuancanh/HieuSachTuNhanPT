package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class NhanVien  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maNhanVien;
	@Column(columnDefinition = "nvarchar(255)")
	private String hoTenNhanVien;
	private Date ngaySinh;
	private String cCCD;
	@Column(columnDefinition = "nvarchar(255)")
	private String diaChi;
	private String sDT;
	@Column(columnDefinition = "nvarchar(255)")
	private boolean gioiTinh;
	private String email;
	@Column(columnDefinition = "nvarchar(255)")
	private boolean chucVu; 
	private boolean caLamViec;
	private String hinhAnh;
    private String OTP;
    private Timestamp hanOTP;
	
    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "nhanVien")
    private TaiKhoan taiKhoan;
	
    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDon> hoaDon;
    


	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTenNhanVien() {
		return hoTenNhanVien;
	}

	public void setHoTenNhanVien(String hoTenNhanVien) {
		this.hoTenNhanVien = hoTenNhanVien;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getcCCD() {
		return cCCD;
	}

	public void setcCCD(String cCCD) {
		this.cCCD = cCCD;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isChucVu() {
		return chucVu;
	}

	public void setChucVu(boolean chucVu) {
		this.chucVu = chucVu;
	}

	public boolean isCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(boolean caLamViec) {
		this.caLamViec = caLamViec;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public Timestamp getHanOTP() {
		return hanOTP;
	}

	public void setHanOTP(Timestamp hanOTP) {
		this.hanOTP = hanOTP;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}


	
	public NhanVien(String maNhanVien, String hoTenNhanVien, Date ngaySinh, String cCCD, String diaChi, String sDT,
			boolean gioiTinh, String email, boolean chucVu, boolean caLamViec, String hinhAnh, String oTP,
			Timestamp hanOTP) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTenNhanVien = hoTenNhanVien;
		this.ngaySinh = ngaySinh;
		this.cCCD = cCCD;
		this.diaChi = diaChi;
		this.sDT = sDT;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.chucVu = chucVu;
		this.caLamViec = caLamViec;
		this.hinhAnh = hinhAnh;
		OTP = oTP;
		this.hanOTP = hanOTP;
	}

	public NhanVien() {
		super();
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTenNhanVien=" + hoTenNhanVien + ", ngaySinh=" + ngaySinh
				+ ", cCCD=" + cCCD + ", diaChi=" + diaChi + ", sDT=" + sDT + ", gioiTinh=" + gioiTinh + ", email="
				+ email + ", chucVu=" + chucVu + ", caLamViec=" + caLamViec + ", hinhAnh=" + hinhAnh + ", OTP=" + OTP
				+ ", hanOTP=" + hanOTP + "]";
	}


	
	
}
