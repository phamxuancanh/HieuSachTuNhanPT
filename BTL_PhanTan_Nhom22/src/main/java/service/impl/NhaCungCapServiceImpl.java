package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.NhaCungCapDao;
import entity.NhaCungCap;
import service.NhaCungCapService;

public class NhaCungCapServiceImpl extends UnicastRemoteObject implements NhaCungCapService{
	
	private NhaCungCapDao nhaCungCapDao;
	
	public NhaCungCapServiceImpl() throws RemoteException {
		nhaCungCapDao = new NhaCungCapDao();
	}

	@Override
	public List<NhaCungCap> layDanhSachNhaCungCap() throws RemoteException {
		// TODO Auto-generated method stub
		return nhaCungCapDao.layDanhSachNhaCungCap();
	}

	@Override
	public NhaCungCap layNhaCungCapBangMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return nhaCungCapDao.layNhaCungCapBangMa(ma);
	}

	@Override
	public NhaCungCap layNhaCungCapBangTen(String ten) throws RemoteException {
		// TODO Auto-generated method stub
		return nhaCungCapDao.layNhaCungCapTheoTen(ten);
	}

	@Override
	public boolean taoNhaCungCap(NhaCungCap nhaCungCap) throws RemoteException {
		// TODO Auto-generated method stub
		return nhaCungCapDao.taoNhaCungCap(nhaCungCap);
	}
	

	
	
}
