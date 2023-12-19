package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

import dao.HoaDonDao;
import dao.ThongKeDao;
import entity.HoaDon;
import service.HoaDonService;

public class HoaDonServiceImpl extends UnicastRemoteObject implements HoaDonService{
	private HoaDonDao hoaDonDao;
	private ThongKeDao thongKeDao;

	public HoaDonServiceImpl() throws RemoteException{
		hoaDonDao = new HoaDonDao();
		thongKeDao = new ThongKeDao();
	}
	@Override
	public boolean themHoaDon(HoaDon hd) throws RemoteException{
		// TODO Auto-generated method stub
		return hoaDonDao.themHoaDon(hd);
	}

	@Override
	public HoaDon timHoaDonTheoMa(String maHD) throws RemoteException{
		// TODO Auto-generated method stub
		return hoaDonDao.timHoaDonTheoMa(maHD);
	}

	@Override
	public List<HoaDon> timTatCaHoaDon() throws RemoteException{
		// TODO Auto-generated method stub
		return hoaDonDao.timTatCaHoaDon();
	}
	@Override
	public List<HoaDon> timHoaDonTheoTenNV(String tenNV) throws RemoteException{
		// TODO Auto-generated method stub
		return hoaDonDao.timHoaDonTheoTenNV(tenNV);
	}
	@Override
	public List<HoaDon> timHoaDonTheoSDT(String sdt) throws RemoteException{
		// TODO Auto-generated method stub
		return hoaDonDao.timHoaDonTheoSDT(sdt);
	}
	@Override
	public List<HoaDon> timHoaDonTheoTenKH(String tenKH) throws RemoteException{
		// TODO Auto-generated method stub
		return hoaDonDao.timHoaDonTheoTenKH(tenKH);
	}
	@Override
	public long getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getDoanhThu(ngayBatDau, ngayKetThuc);
	}
	@Override
	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongHoaDon(ngayBatDau, ngayKetThuc);
	}
	@Override
	public Object getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)
			throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getDoanhThuTheoMaNhanVien(ngayBatDau, ngayKetThuc, maNV);
	}
	@Override
	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)
			throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongHoaDonTheoMaNV(ngayBatDau, ngayKetThuc, maNV);
	}
	
	
}
