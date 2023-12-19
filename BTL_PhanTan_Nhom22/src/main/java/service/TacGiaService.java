package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.TacGia;

public interface TacGiaService extends Remote{
	public List<TacGia> layDanhSachTacGia () throws RemoteException;
	public TacGia layTacGiaBangMa(String ma) throws RemoteException;
	public TacGia layTacGiaBangTen(String ten) throws RemoteException;
	public boolean taoTacGia(TacGia tacGia) throws RemoteException;
}
