package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.TheLoaiDao;
import entity.TheLoaiSach;
import service.TheLoaiService;

public class TheLoaiServiceImpl extends UnicastRemoteObject implements TheLoaiService{
	private TheLoaiDao theLoaiDao;
	
	public TheLoaiServiceImpl() throws RemoteException {
		theLoaiDao = new TheLoaiDao();
	}

	@Override
	public List<TheLoaiSach> timTatCaTheLoai() throws RemoteException{
		// TODO Auto-generated method stub
		return theLoaiDao.timTatCaTheLoai();
	}

	@Override
	public TheLoaiSach layTheLoaiSachBangMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		return theLoaiDao.layTheLoaiSachBangMa(ma);
	}

	@Override
	public TheLoaiSach layTheLoaiSachBangTen(String ten) throws RemoteException {
		// TODO Auto-generated method stub
		return theLoaiDao.layTheoLoaiSachTheoTen(ten);
	}

	@Override
	public boolean taoTheLoaiSach(TheLoaiSach theLoaiSach) throws RemoteException {
		// TODO Auto-generated method stub
		return theLoaiDao.taoTheLoaiSach(theLoaiSach);
	}

	@Override
	public List<TheLoaiSach> layToanBoDanhSachTheLoaiSach() throws RemoteException{
		// TODO Auto-generated method stub
		return theLoaiDao.timTatCaTheLoai();
	}
	

}
