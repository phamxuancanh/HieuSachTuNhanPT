package service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

import dao.KhachHangDao;
import dao.ThongKeDao;
import entity.KhachHang;
import service.KhachHangService;

public class KhachHangServiceImpl extends UnicastRemoteObject implements KhachHangService{
	private KhachHangDao khdao;
	private ThongKeDao thongKeDao;
	public static int errors = 0;
	public static int errorsThem = 0;
	
	public KhachHangServiceImpl() throws RemoteException{
		// TODO Auto-generated constructor stub
		khdao = new KhachHangDao();
		thongKeDao = new ThongKeDao();
	}

	@Override
	public boolean themKhachHang(KhachHang kh) throws RemoteException{
		// TODO Auto-generated method stub
		if (!(kh.getHoTenKhachHang().length() > 0 && kh.getHoTenKhachHang()
				.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊ"
						+ "ỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]*"))) {
			errors = 1;
			return false;
		} else if (!(kh.getsDT().length() > 0 && kh.getsDT().matches("^[0-9]{10}$"))) {
			errors = 2;
			return false;
		} else {
			return khdao.themKhachHang(kh);
		}
	}

	@Override
	public List<KhachHang> timTatCaKhachHang() throws RemoteException{
		// TODO Auto-generated method stub
		return khdao.timTatCaKhachHang();
	}

	@Override
	public KhachHang timKhachHangTheoMa(String maKH) throws RemoteException{
		// TODO Auto-generated method stub
		return khdao.timKhachHangTheoMa(maKH);
	}

	@Override
	public void suaKhachHang(KhachHang kh) throws RemoteException{
		// TODO Auto-generated method stub
		khdao.suaKhachHang(kh);
	}

	@Override
	public List<KhachHang> timKhachHangTheoTen(String tenKH) throws RemoteException{
		// TODO Auto-generated method stub
		return khdao.timKhachHangTheoTen(tenKH);
	}

	@Override
	public List<KhachHang> timKhachHangTheoSDT(String sdt) throws RemoteException{
		// TODO Auto-generated method stub
		return khdao.timKhachHangTheoSDT(sdt);
	}

	@Override
	public List<KhachHang> timKhachHangTheoTenVaSDT(String tenKH, String sdt) throws RemoteException{
		// TODO Auto-generated method stub
		return khdao.timKhachHangTheoTenVaSDT(tenKH, sdt);
	}

	@Override
	public Object getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc)
			throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getKhachHangMuaNhieuNhatTheoNgayTuChon(ngayBatDau, ngayKetThuc);
	}

	@Override
	public Object getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getTongTienCuaKhachHangTop1(ngayBatDau, ngayKetThuc);
	}

	@Override
	public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getTop10KHThanThiet(ngayBatDau, ngayKetThuc);
	}

	@Override
	public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)
			throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongHoaDonCuaKhachHangTheoMa(ngayBatDau, ngayKetThuc, maKH);
	}

	@Override
	public Object getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)
			throws RemoteException {
		// TODO Auto-generated method stub
		return thongKeDao.getTongTienCuaKhachHangTheoMa(ngayBatDau, ngayKetThuc, maKH);
	}

}
