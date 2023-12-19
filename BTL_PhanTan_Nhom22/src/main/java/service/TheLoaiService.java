package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.TheLoaiSach;

public interface TheLoaiService extends Remote{
	public List<TheLoaiSach> timTatCaTheLoai() throws RemoteException;
	public List<TheLoaiSach> layToanBoDanhSachTheLoaiSach()throws RemoteException;
	public TheLoaiSach layTheLoaiSachBangMa (String ma) throws RemoteException;
	public TheLoaiSach layTheLoaiSachBangTen (String ten) throws RemoteException;
	public boolean taoTheLoaiSach (TheLoaiSach theLoaiSach) throws RemoteException;
}
