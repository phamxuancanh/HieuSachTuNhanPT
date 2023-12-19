package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.TaiKhoan;

public interface TaiKhoanService extends Remote{
	public List<TaiKhoan> timTatCaTaiKhoan() throws RemoteException;
	public boolean themTaiKhoan(TaiKhoan tk) throws RemoteException;
	public TaiKhoan timTaiKhoanTheoMaNV(String maNV) throws RemoteException;
	public boolean doiMatKhau(String passMoi, String maNV) throws RemoteException;
}
