package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class HoaDonDao {
	private EntityManager em;
	
	public HoaDonDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	public boolean themHoaDon(HoaDon hd) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			
			Query query = em.createNativeQuery("INSERT [dbo].[HoaDon] ([maHoaDon], [ghiChu], [ngayLapHoaDon], [tienKhachDua], [tinhTrang],[maKhachHang],[maNhanVien])"
					+ " VALUES (?,?,?,?,?,?,?)");
			query.setParameter(1, hd.getMaHoaDon());
			query.setParameter(2, hd.getGhiChu());
			query.setParameter(3, hd.getNgayLapHoaDon());
			query.setParameter(4, hd.getTienKhachDua());
			query.setParameter(5, hd.isTinhTrang());
			query.setParameter(6, hd.getKhachHang().getMaKhachHang());
			query.setParameter(7, hd.getNhanVien().getMaNhanVien());
			query.executeUpdate();
			
			//em.persist(hd);
			tr.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
	public HoaDon timHoaDonTheoMa(String maHD) {
		return em.find(HoaDon.class, maHD);
	}
	
	public List<HoaDon> timTatCaHoaDon(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from HoaDon");
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<HoaDon> dsHoaDon = new ArrayList<>();
			for (Object[] object : list) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(object[0].toString());
				hd.setGhiChu(object[1].toString());
				hd.setNgayLapHoaDon(new SimpleDateFormat("yyyy-MM-dd").parse(object[2].toString()));
				hd.setTienKhachDua(Double.parseDouble(object[3].toString()));
				hd.setTinhTrang(Boolean.parseBoolean(object[4].toString()));
				hd.setKhachHang((KhachHang) em.find(KhachHang.class, object[5].toString()));
				hd.setNhanVien((NhanVien) em.find(NhanVien.class, object[6].toString()));
				dsHoaDon.add(hd);
			}
			tr.commit();
			return dsHoaDon;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	
	public List<HoaDon> timHoaDonTheoTenNV(String tenNV) {		
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select HoaDon.maHoaDon, HoaDon.ghiChu, HoaDon.ngayLapHoaDon, HoaDon.tienKhachDua, HoaDon.tinhTrang, HoaDon.maKhachHang, HoaDon.maNhanVien\r\n"
					+ "from HoaDon inner join NhanVien on NhanVien.maNhanVien = HoaDon.maNhanVien where NhanVien.hotenNhanVien like N'%" + tenNV + "%'");
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<HoaDon> dsHoaDon = new ArrayList<>();
			for (Object[] object : list) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(object[0].toString());
				hd.setGhiChu(object[1].toString());
				hd.setNgayLapHoaDon(new SimpleDateFormat("yyyy-MM-dd").parse(object[2].toString()));
				hd.setTienKhachDua(Double.parseDouble(object[3].toString()));
				hd.setTinhTrang(Boolean.parseBoolean(object[4].toString()));
				hd.setKhachHang((KhachHang) em.find(KhachHang.class, object[5].toString()));
				hd.setNhanVien((NhanVien) em.find(NhanVien.class, object[6].toString()));
				dsHoaDon.add(hd);
			}
			tr.commit();
			return dsHoaDon;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	
	public List<HoaDon> timHoaDonTheoSDT(String sdt){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select HoaDon.maHoaDon, HoaDon.ghiChu, HoaDon.ngayLapHoaDon, HoaDon.tienKhachDua, HoaDon.tinhTrang, HoaDon.maKhachHang, HoaDon.maNhanVien\r\n"
					+ "from HoaDon inner join KhachHang on KhachHang.maKhachHang = HoaDon.maKhachHang where KhachHang.sDT like N'%" + sdt + "%'");
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<HoaDon> dsHoaDon = new ArrayList<>();
			for (Object[] object : list) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(object[0].toString());
				hd.setGhiChu(object[1].toString());
				hd.setNgayLapHoaDon(new SimpleDateFormat("yyyy-MM-dd").parse(object[2].toString()));
				hd.setTienKhachDua(Double.parseDouble(object[3].toString()));
				hd.setTinhTrang(Boolean.parseBoolean(object[4].toString()));
				hd.setKhachHang((KhachHang) em.find(KhachHang.class, object[5].toString()));
				hd.setNhanVien((NhanVien) em.find(NhanVien.class, object[6].toString()));
				dsHoaDon.add(hd);
			}
			tr.commit();
			return dsHoaDon;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	public List<HoaDon> timHoaDonTheoTenKH(String tenKH) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select HoaDon.maHoaDon, HoaDon.ghiChu, HoaDon.ngayLapHoaDon, HoaDon.tienKhachDua, HoaDon.tinhTrang, HoaDon.maKhachHang, HoaDon.maNhanVien\r\n"
					+ "from HoaDon inner join KhachHang on KhachHang.maKhachHang = HoaDon.maKhachHang where KhachHang.hoTenKhachHang like N'%" + tenKH + "%'");
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<HoaDon> dsHoaDon = new ArrayList<>();
			for (Object[] object : list) {
				HoaDon hd = new HoaDon();
				hd.setMaHoaDon(object[0].toString());
				hd.setGhiChu(object[1].toString());
				hd.setNgayLapHoaDon(new SimpleDateFormat("yyyy-MM-dd").parse(object[2].toString()));
				hd.setTienKhachDua(Double.parseDouble(object[3].toString()));
				hd.setTinhTrang(Boolean.parseBoolean(object[4].toString()));
				hd.setKhachHang((KhachHang) em.find(KhachHang.class, object[5].toString()));
				hd.setNhanVien((NhanVien) em.find(NhanVien.class, object[6].toString()));
				dsHoaDon.add(hd);
			}
			tr.commit();
			return dsHoaDon;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
}
