package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import entity.NhanVien;

public interface NhanVienService extends Remote{
	public boolean themNhanVien(NhanVien nhanVien) throws RemoteException;
	public NhanVien timNhanVienTheoMa(String maNV) throws RemoteException;
	public List<NhanVien> timTatCaNhanVien() throws RemoteException;
	public void suaNhanVien(NhanVien nv) throws RemoteException;
	public List<NhanVien> timNhanVienTheoTen(String tenNV) throws RemoteException;
	public List<NhanVien> timNhanVienTheoSDT(String sdt) throws RemoteException;
	public List<NhanVien> timNhanVienTheoTenVaSDT(String tenNV, String sdt) throws RemoteException;
	public NhanVien timNhanVienTheoEmail(String email) throws RemoteException;
	public boolean capNhatOTP(String email, String OTP) throws RemoteException;
	public String layOTP() throws RemoteException;
	public Timestamp layHanOTP() throws RemoteException;
	public Object getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException;
	public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException;
}
