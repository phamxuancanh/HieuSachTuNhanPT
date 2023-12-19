package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.TaiKhoanDao;
import entity.TaiKhoan;
import service.TaiKhoanService;

public class TaiKhoanServiceImpl extends UnicastRemoteObject implements TaiKhoanService{


	private TaiKhoanDao taiKhoanDao;
	
	public TaiKhoanServiceImpl() throws RemoteException {
		taiKhoanDao = new TaiKhoanDao();
	}
	@Override
	public List<TaiKhoan> timTatCaTaiKhoan() throws RemoteException{
		// TODO Auto-generated method stub
		return taiKhoanDao.timTatCaTaiKhoan();
	}

	@Override
	public boolean themTaiKhoan(TaiKhoan tk) throws RemoteException{
		// TODO Auto-generated method stub
		return taiKhoanDao.themTaiKhoan(tk);
	}

	@Override
	public TaiKhoan timTaiKhoanTheoMaNV(String maNV) throws RemoteException{
		// TODO Auto-generated method stub
		return taiKhoanDao.timTaiKhoanTheoMaNV(maNV);
	}

	@Override
	public boolean doiMatKhau(String passMoi, String maNV) throws RemoteException{
		// TODO Auto-generated method stub
		return taiKhoanDao.doiMatKhau(passMoi, maNV);
	}

}
