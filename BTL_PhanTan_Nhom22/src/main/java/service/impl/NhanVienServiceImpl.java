package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import dao.NhanVienDao;
import dao.ThongKeDao;
import entity.NhanVien;
import service.NhanVienService;

public class NhanVienServiceImpl extends UnicastRemoteObject implements NhanVienService{
	private NhanVienDao nhanVienDao;
	private ThongKeDao thongKeDao;
	public NhanVienServiceImpl() throws RemoteException {
		nhanVienDao = new NhanVienDao();
		thongKeDao = new ThongKeDao();
	}
	@Override
	public boolean themNhanVien(NhanVien nhanVien) throws RemoteException{
		// TODO Auto-generated method stub
		return nhanVienDao.themNhanVien(nhanVien);
	}

	@Override
	public NhanVien timNhanVienTheoMa(String maNV) throws RemoteException{
		// TODO Auto-generated method stub
		return nhanVienDao.timNhanVienTheoMa(maNV);
	}

	@Override
	public List<NhanVien> timTatCaNhanVien() throws RemoteException{
		// TODO Auto-generated method stub
		return nhanVienDao.timTatCaNhanVien();
	}

	@Override
	public void suaNhanVien(NhanVien nv) throws RemoteException{
		// TODO Auto-generated method stub
		nhanVienDao.suaNhanVien(nv);
	}

	@Override
	public List<NhanVien> timNhanVienTheoTen(String tenNV) throws RemoteException{
		// TODO Auto-generated method stub
		return nhanVienDao.timNhanVienTheoTen(tenNV);
	}

	@Override
	public List<NhanVien> timNhanVienTheoSDT(String sdt) throws RemoteException{
		// TODO Auto-generated method stub
		return nhanVienDao.timNhanVienTheoSDT(sdt);
	}

	@Override
	public List<NhanVien> timNhanVienTheoTenVaSDT(String tenNV, String sdt) throws RemoteException{
		// TODO Auto-generated method stub
		return nhanVienDao.timNhanVienTheoTenVaSDT(tenNV, sdt);
	}

	@Override
	public NhanVien timNhanVienTheoEmail(String email) throws RemoteException{
		// TODO Auto-generated method stub
		return nhanVienDao.timNhanVienTheoEmail(email);
	}

	@Override
	public boolean capNhatOTP(String email, String OTP) throws RemoteException{
		// TODO Auto-generated method stub
		return nhanVienDao.capNhatOTP(email, OTP);
	}
	@Override
	public String layOTP() throws RemoteException {
		// TODO Auto-generated method stub
		return nhanVienDao.layOTP();
	}
	@Override
	public Timestamp layHanOTP() throws RemoteException {
		// TODO Auto-generated method stub
		return nhanVienDao.layHanOTP();
	}

	@Override
	public Object getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getNhanVienBanNhieuNhatTheoNgayTuChon(ngayBatDau, ngayKetThuc);
	}
	@Override
	public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getDoanhThuCuaNhanVien(ngayBatDau, ngayKetThuc);
	}

}
