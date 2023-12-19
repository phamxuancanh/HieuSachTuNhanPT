package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.NhaXuatBanDao;
import entity.NhaXuatBan;
import service.NhaXuatBanService;

public class NhaXuatBanServiceImpl extends UnicastRemoteObject implements NhaXuatBanService{
	private NhaXuatBanDao nhaXuatBanDao;
	
	public NhaXuatBanServiceImpl() throws RemoteException {
		nhaXuatBanDao = new NhaXuatBanDao();
	}

	@Override
	public List<NhaXuatBan> layDanhSachNhaXuatBan() throws RemoteException {
		// TODO Auto-generated method stub
		return nhaXuatBanDao.layDanhSachNhaXuatBan();
	}

	@Override
	public NhaXuatBan layNhaXuatBanBangMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return nhaXuatBanDao.layNhaXuatBanBangMa(ma);
	}

	@Override
	public NhaXuatBan layNhaXuatBanBangTen(String ten) throws RemoteException {
		// TODO Auto-generated method stub
		return nhaXuatBanDao.layNhaXuatBanTheoTen(ten);
	}

	@Override
	public boolean taoNhaXuatBan(NhaXuatBan nhaXuatBan) throws RemoteException {
		// TODO Auto-generated method stub
		return nhaXuatBanDao.taoNhaXuatBan(nhaXuatBan);
	}
}
