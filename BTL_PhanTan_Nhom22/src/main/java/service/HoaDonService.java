package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import entity.HoaDon;

public interface HoaDonService extends Remote{
	public boolean themHoaDon(HoaDon hd) throws RemoteException;
	public HoaDon timHoaDonTheoMa(String maHD) throws RemoteException;
	public List<HoaDon> timTatCaHoaDon() throws RemoteException;
	public List<HoaDon> timHoaDonTheoTenNV(String tenNV) throws RemoteException;
	public List<HoaDon> timHoaDonTheoSDT(String sdt) throws RemoteException;
	public List<HoaDon> timHoaDonTheoTenKH(String tenKH) throws RemoteException;
	public long getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc)throws RemoteException;
	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc)throws RemoteException;
	public Object getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)throws RemoteException;
	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)throws RemoteException;
}
