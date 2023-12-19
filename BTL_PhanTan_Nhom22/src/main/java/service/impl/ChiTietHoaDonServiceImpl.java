package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.ChiTietHoaDonDao;
import entity.ChiTietHoaDon;
import service.ChiTietHoaDonService;

public class ChiTietHoaDonServiceImpl extends UnicastRemoteObject implements ChiTietHoaDonService{
	
	private ChiTietHoaDonDao chiTietHoaDonDao;
	
	public ChiTietHoaDonServiceImpl() throws RemoteException {
		chiTietHoaDonDao = new ChiTietHoaDonDao();
	}

	@Override
	public boolean themCTHD(ChiTietHoaDon chiTietHoaDon) throws RemoteException {
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.themCTHD(chiTietHoaDon);
	}

	@Override
	public List<ChiTietHoaDon> timChiTietHoaDonTheoMaHD(String maHD) throws RemoteException {
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.timChiTietHoaDonTheoMaHD(maHD);
	}
	
	
}
