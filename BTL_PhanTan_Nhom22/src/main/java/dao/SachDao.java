package dao;

import java.util.ArrayList;
import java.util.List;

import entity.HoaDon;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.Sach;
import entity.TacGia;
import entity.TheLoaiSach;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class SachDao {
	EntityManager em;
	private NhaCungCapDao nhaCungCapDao;
	private TacGiaDao tacGiaDao;
	private NhaXuatBanDao nhaXuatBanDao;
	private TheLoaiDao theLoaiDao;
	public SachDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
		nhaCungCapDao = new NhaCungCapDao();
		tacGiaDao = new TacGiaDao();
		nhaXuatBanDao = new NhaXuatBanDao();
		theLoaiDao = new TheLoaiDao();
	}


	
	public List<Sach> timTatCaSach(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select * from Sach");
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSach = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				dsSach.add(sach);
			}
			tr.commit();
			return dsSach;

		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public Sach timSachTheoMa(String maSach) {
		return em.find(Sach.class, maSach);
	}
	public List<Sach> laySachBangMa(String ma) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from Sach where maSach = ?";
			Query query=  em.createNativeQuery(sql);
			query.setParameter(1, ma);
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSachs  = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				
				dsSachs.add(sach);
			}
			tr.commit();
			return dsSachs;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public List<Sach> laySachBangTenSach(String ten) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from Sach where tenSach like N'%"+ten+"%'";
			Query query=  em.createNativeQuery(sql);
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSachs  = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				
				dsSachs.add(sach);
			}
			tr.commit();
			return dsSachs;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	public List<Sach> timSachTheoGia(long gia){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from Sach where giaNhap = ?";
			Query query=  em.createNativeQuery(sql);
			query.setParameter(1, gia);
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSachs  = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				
				dsSachs.add(sach);
			}
			tr.commit();
			return dsSachs;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	public List<Sach> timSachTheoKhoangGia(long giaTren,long giaDuoi){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from Sach where giaNhap <= ? and giaNhap >= ?";
			Query query=  em.createNativeQuery(sql);
			query.setParameter(1, giaTren);
			query.setParameter(1, giaDuoi);
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSachs  = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				
				dsSachs.add(sach);
			}
			tr.commit();
			return dsSachs;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
		
	}
	public List<Sach> timSachTheoTenTacGia (String tenTacGia){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select s.* from Sach s inner join TacGia ON s.maTacGia =  TacGia.maTacGia where TacGia.tenTacGia like N'"+tenTacGia+"'";
			Query query=  em.createNativeQuery(sql);
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSachs  = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				
				dsSachs.add(sach);
			}
			tr.commit();
			return dsSachs;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
		
	}
	public List<Sach> timSachTheoNhaXuatBan (String tenNhaXuatBan){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select s.* from Sach s inner join NhaXuatBan ON s.maNhaXuatBan =  NhaXuatBan.maNXB where NhaXuatBan.tenNXB like N'"+tenNhaXuatBan+"'";
			Query query=  em.createNativeQuery(sql);
			query.setParameter(1, tenNhaXuatBan);
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSachs  = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				
				dsSachs.add(sach);
			}
			tr.commit();
			return dsSachs;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	public List<Sach> timSachTheoNhaCungCap (String tenNhaCungCap){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select s.* from Sach s inner join NhaCungCap ON s.maNhaCungCap =  NhaCungCap.maNCC where NhaCungCap.tenNCC like N'"+tenNhaCungCap+"'";
			Query query=  em.createNativeQuery(sql);
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSachs  = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				
				dsSachs.add(sach);
			}
			tr.commit();
			return dsSachs;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	public List<Sach> timSachTheoTheLoaiSach (String theLoaiSach){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select s.* from Sach s inner join TheLoaiSach ON s.maTheLoaiSach = TheLoaiSach.maLoai where TheLoaiSach.tenLoai like N'"+theLoaiSach+"'";
			Query query=  em.createNativeQuery(sql);
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSachs  = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				
				dsSachs.add(sach);
			}
			tr.commit();
			return dsSachs;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	public boolean xemSachDaHetHang (Sach sach) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from Sach  where maSach like '?' and  soLuongTon = 0 ";
			Query query=  em.createNativeQuery(sql);
			query.setParameter(1, sach.getMaSach());
			Sach sach2 = (Sach) query.getSingleResult();
			tr.commit();
			if(sach2 != null)
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public boolean capNhatSoLuongTon (Sach sach,int soLuongTon) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
//			Query query = em.createNativeQuery("UPDATE Sach SET soLuongTon = "+soLuongTon+" WHERE maSach like '"+sach.getMaSach()+"'");
//			query.executeUpdate();
			em.find(Sach.class, sach.getMaSach());
			sach.setSoLuongTon(soLuongTon);
			em.merge(sach);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	public boolean capNhatSach (Sach sach) {
		EntityTransaction tr = em.getTransaction();
		
		try {
			tr.begin(); 
			em.find(Sach.class, sach.getMaSach());
			em.merge(sach);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
		
	}
	public boolean taoSach(Sach sach) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(sach);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	public List<Sach> phanTrangSach (int soTrang){
		EntityTransaction tr = em.getTransaction();
		try {
			if(soTrang == 1) {
				tr.begin();
				String sql="select top 10 * from Sach";
				Query query=  em.createNativeQuery(sql);
				List<Object[]> list = (List<Object[]>) query.getResultList();
				List<Sach> dsSachs  = new ArrayList<>();
				for (Object[] object : list) {
					Sach sach = new Sach();
					sach.setMaSach(object[0].toString());
					sach.setDonViSanPham(object[1].toString());
					sach.setGhiChu(object[2].toString());
					sach.setGiaNhap(Long.parseLong(object[3].toString()));
					sach.setHinhAnh(object[4].toString());
					sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
					sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
					sach.setSoTrang(Integer.parseInt(object[7].toString()));
					sach.setTenSach(object[8].toString());
					sach.setTrongLuong(Double.parseDouble(object[9].toString()));
					sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
					sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
					if(object[12] != null) {
					sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
					}
					else {
						sach.setTacGia(new TacGia("Không có tác giả"));
					}
					
					sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
					
					dsSachs.add(sach);
				}
				tr.commit();
				return dsSachs;
			}else {
			tr.begin();
			String sql="select top "+soTrang*10+" * from Sach where maSach NOT IN (select top "+(soTrang -1)*10+" maSach from Sach)";
			Query query=  em.createNativeQuery(sql);
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<Sach> dsSachs  = new ArrayList<>();
			for (Object[] object : list) {
				Sach sach = new Sach();
				sach.setMaSach(object[0].toString());
				sach.setDonViSanPham(object[1].toString());
				sach.setGhiChu(object[2].toString());
				sach.setGiaNhap(Long.parseLong(object[3].toString()));
				sach.setHinhAnh(object[4].toString());
				sach.setNamXuatBan(Integer.parseInt(object[5].toString()));
				sach.setSoLuongTon(Integer.parseInt(object[6].toString()));
				sach.setSoTrang(Integer.parseInt(object[7].toString()));
				sach.setTenSach(object[8].toString());
				sach.setTrongLuong(Double.parseDouble(object[9].toString()));
				sach.setNhaCungCap((NhaCungCap) em.find(NhaCungCap.class, object[10].toString()));
				sach.setNhaXuatBan((NhaXuatBan) em.find(NhaXuatBan.class, object[11].toString()));
				if(object[12] != null) {
				sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				}
				else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}
				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));
				
				dsSachs.add(sach);
			}
			tr.commit();
			return dsSachs;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	public String taoMaSachTuDong() {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="DECLARE @lastval INT \r\n"
					+ "	SET @lastval = (select MAX(Cast(right(maSach,5) as int))  from Sach) \r\n"
					+ "	if @lastval is null set @lastval = 0 \r\n"
					+ "	select 'SP' + right('00000' + convert(varchar(10),(@lastval + 1)),5)";
			Query query=  em.createNativeQuery(sql,String.class);
			String ma = (String) query.getSingleResult();
			tr.commit();
			return ma;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	public int laySoTrangCuaSach() {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="SELECT COUNT(maSach)\r\n"
					+ "FROM Sach";
			Query query=  em.createNativeQuery(sql,int.class);
			int soSach = (int) query.getSingleResult();
			
			
			tr.commit();
			int soTrang = soSach /10;
			if(soSach % 10 >0)
				soTrang ++;
			return soTrang;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return 1;
	}
	
}
