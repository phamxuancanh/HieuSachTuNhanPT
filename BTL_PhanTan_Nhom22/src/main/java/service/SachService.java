package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import entity.Sach;

public interface SachService extends Remote{
	public Sach timSachTheoMa(String maSach) throws RemoteException;
	public List<Sach> laySachBangMa(String ma)throws RemoteException;
	public boolean capNhatSach(Sach s) throws RemoteException;
	public List<Sach> timSachTheoTen(String ten) throws RemoteException;
	public List<Sach> timSachTheoGia(long gia) throws RemoteException;
	public List<Sach> timSachTheoKhoangGia(long giaTren,long giaDuoi) throws RemoteException;
	public List<Sach> timSachTheoTacGia (String tenTacGia) throws RemoteException;
	public List<Sach> timSachTheoNhaXuatBan (String tenNhaXuatBan) throws RemoteException;
	public List<Sach> timSachTheoNhaCungCap (String tenNhaCungCap) throws RemoteException;
	public List<Sach> timSachTheoTheLoaiSach (String theLoaiSach) throws RemoteException;
	public boolean xemSachDaHetHang (Sach sach) throws RemoteException;
	public String taoMaSachTuDong() throws RemoteException ;
	public List<Sach> phanTrangSach (int soTrang) throws RemoteException;
	public int laySoTrangCuaSach()  throws RemoteException;
	public boolean taoSach(Sach sach) throws RemoteException;
	public boolean capNhatSoLuongTon(Sach sach,int soLuongTon) throws RemoteException;
	public List<Sach> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException;
	public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws RemoteException;
	public int getSoLuongSachTon()throws RemoteException;
	public int getTongSoSachBanDuoc()throws RemoteException;
	public List<Sach> timTatCaSach() throws RemoteException;
}
