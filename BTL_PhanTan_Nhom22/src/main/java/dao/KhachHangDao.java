package dao;

import java.util.List;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class KhachHangDao {
	EntityManager em ;
	
	public KhachHangDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	public boolean themKhachHang(KhachHang kh) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(kh);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
	public List<KhachHang> timTatCaKhachHang() {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select * from KhachHang", KhachHang.class);
			List<KhachHang> listKH = query.getResultList();
			tr.commit();
			return listKH;
		}
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public KhachHang timKhachHangTheoMa(String maKH) {
		return em.find(KhachHang.class, maKH);
	}
	

	public void suaKhachHang(KhachHang kh) {
		// TODO Auto-generated method stub
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(kh);
			tr.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
	}
	
	public List<KhachHang> timKhachHangTheoTen(String tenKH) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from KhachHang where hotenKhachHang like concat ('%', ?, '%')", KhachHang.class);
            query.setParameter(1,tenKH);
			List<KhachHang> listKH = query.getResultList();
			tr.commit();
			return listKH;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public List<KhachHang> timKhachHangTheoSDT(String sdt) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from KhachHang where sDT like concat ('%', ?, '%')", KhachHang.class);
            query.setParameter(1,sdt);
			List<KhachHang> listKH = query.getResultList();
			tr.commit();
			return listKH;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public List<KhachHang> timKhachHangTheoTenVaSDT(String tenKH, String sdt) {
			EntityTransaction tr = em.getTransaction();
			try {
				tr.begin();
				Query query = em.createNativeQuery("select * from KhachHang where hotenKhachHang LIKE CONCAT('%', ?, '%') or sDT LIKE CONCAT('%', ?, '%')", KhachHang.class);
	            query.setParameter(1,tenKH);
				query.setParameter(2,sdt);
				List<KhachHang> listKH = query.getResultList();
				tr.commit();
				return listKH;
			}catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}
			return null;
	}
}

