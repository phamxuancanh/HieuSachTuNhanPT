package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.ChiTietHoaDon;

public interface ChiTietHoaDonService extends Remote{
	public boolean themCTHD(ChiTietHoaDon chiTietHoaDon) throws RemoteException;
	public List<ChiTietHoaDon> timChiTietHoaDonTheoMaHD(String maHD) throws RemoteException;
}
