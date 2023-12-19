package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhaXuatBan;

public interface NhaXuatBanService extends Remote{
	public List<NhaXuatBan> layDanhSachNhaXuatBan () throws RemoteException;
	public NhaXuatBan layNhaXuatBanBangMa (String ma) throws RemoteException;
	public NhaXuatBan layNhaXuatBanBangTen (String ten) throws RemoteException;
	public boolean taoNhaXuatBan(NhaXuatBan nhaXuatBan) throws RemoteException;
}
