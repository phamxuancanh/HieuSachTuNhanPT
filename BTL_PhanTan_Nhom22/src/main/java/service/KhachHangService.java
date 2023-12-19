package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import entity.KhachHang;

public interface KhachHangService extends Remote{
	public boolean themKhachHang(KhachHang kh) throws RemoteException;
	public List<KhachHang> timTatCaKhachHang() throws RemoteException;
	public KhachHang timKhachHangTheoMa(String maKH) throws RemoteException;
	public void suaKhachHang(KhachHang kh) throws RemoteException;
	public List<KhachHang> timKhachHangTheoTen(String tenKH) throws RemoteException;
	public List<KhachHang> timKhachHangTheoSDT(String sdt) throws RemoteException;
	public List<KhachHang> timKhachHangTheoTenVaSDT(String tenKH, String sdt) throws RemoteException;
	
	
	
	public Object getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc)throws RemoteException;
	public Object getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc)throws RemoteException;
	public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc)throws RemoteException;
	public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)throws RemoteException;
	public Object getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)throws RemoteException;
}

