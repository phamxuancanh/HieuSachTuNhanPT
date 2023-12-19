package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

import dao.SachDao;
import dao.ThongKeDao;
import entity.Sach;
import service.SachService;

public class SachServiceImpl extends UnicastRemoteObject implements SachService{
	private SachDao sachDao;
	private ThongKeDao thongKeDao;
	public SachServiceImpl() throws RemoteException {
		sachDao = new SachDao();
		thongKeDao = new ThongKeDao();
	}



	@Override
	public Sach timSachTheoMa(String maSach) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.timSachTheoMa(maSach);
	}

	@Override
	public boolean capNhatSach(Sach s) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.capNhatSach(s);
	}

	@Override
	public List<Sach> timSachTheoTen(String ten) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.laySachBangTenSach(ten);
	}

	@Override
	public List<Sach> timSachTheoGia(long gia) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.timSachTheoGia(gia);
	}

	@Override
	public List<Sach> timSachTheoKhoangGia(long giaTren, long giaDuoi) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.timSachTheoKhoangGia(giaTren, giaDuoi);
	}

	@Override
	public List<Sach> timSachTheoTacGia(String tenTacGia) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.timSachTheoTenTacGia(tenTacGia);
	}

	@Override
	public List<Sach> timSachTheoNhaXuatBan(String tenNhaXuatBan) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.timSachTheoNhaXuatBan(tenNhaXuatBan);
	}

	@Override
	public List<Sach> timSachTheoNhaCungCap(String tenNhaCungCap) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.timSachTheoNhaCungCap(tenNhaCungCap);
	}

	@Override
	public List<Sach> timSachTheoTheLoaiSach(String theLoaiSach) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.timSachTheoTheLoaiSach(theLoaiSach);
	}

	@Override
	public boolean xemSachDaHetHang(Sach sach) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.xemSachDaHetHang(sach);
	}

	@Override
	public String taoMaSachTuDong() throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.taoMaSachTuDong();
	}

	@Override
	public List<Sach> phanTrangSach(int soTrang) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.phanTrangSach(soTrang);
	}

	@Override
	public int laySoTrangCuaSach() throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.laySoTrangCuaSach();
	}

	@Override
	public boolean taoSach(Sach sach) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.taoSach(sach);
	}

	@Override
	public boolean capNhatSoLuongTon(Sach sach, int soLuongTon) throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.capNhatSoLuongTon(sach, soLuongTon);
	}

	@Override
	public List<Sach> laySachBangMa(String ma)  throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.laySachBangMa(ma);
	}

	@Override
	public List<Sach> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getSanPhamBanNhieuNhatTheoNgayTuChon(ngayBatDau, ngayKetThuc);
	}

	@Override
	public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongBanCuaSanPhamChayNhat(ngayBatDau, ngayKetThuc);
	}

	@Override
	public int getSoLuongSachTon() throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongSachTon();
	}

	@Override
	public int getTongSoSachBanDuoc() throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getTongSoSachBanDuoc();
	}



	@Override
	public List<Sach> timTatCaSach() throws RemoteException {
		// TODO Auto-generated method stub
		return sachDao.timTatCaSach();
	}


	
	
}
