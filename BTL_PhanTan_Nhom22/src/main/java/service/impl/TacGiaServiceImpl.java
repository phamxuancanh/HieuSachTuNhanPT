package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.TacGiaDao;
import entity.TacGia;
import service.TacGiaService;

public class TacGiaServiceImpl extends UnicastRemoteObject implements TacGiaService{
	private TacGiaDao tacGiaDao;
	
	public TacGiaServiceImpl() throws RemoteException {
		tacGiaDao= new TacGiaDao();
	}

	@Override
	public List<TacGia> layDanhSachTacGia() throws RemoteException {
		// TODO Auto-generated method stub
		return tacGiaDao.timTatCaTacGia();
	}

	@Override
	public TacGia layTacGiaBangMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return tacGiaDao.layTacGiaBangMa(ma);
	}

	@Override
	public TacGia layTacGiaBangTen(String ten) throws RemoteException {
		// TODO Auto-generated method stub
		return tacGiaDao.layTacGiaTheoTen(ten);
	}

	@Override
	public boolean taoTacGia(TacGia tacGia) throws RemoteException {
		// TODO Auto-generated method stub
		return tacGiaDao.taoTacGia(tacGia);
	}


}
