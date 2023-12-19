package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhaCungCap;

public interface NhaCungCapService extends Remote{
	public List<NhaCungCap>  layDanhSachNhaCungCap() throws RemoteException;
	public NhaCungCap layNhaCungCapBangMa(String ma) throws RemoteException;
	public NhaCungCap layNhaCungCapBangTen(String ten) throws RemoteException;
	public boolean taoNhaCungCap(NhaCungCap nhaCungCap) throws RemoteException;
}
