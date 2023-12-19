package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class NhanVienDao {
	EntityManager em;
	
	public NhanVienDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	public boolean themNhanVien(NhanVien nhanVien) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(nhanVien);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
	public List<NhanVien> timTatCaNhanVien() {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from NhanVien", NhanVien.class);
			List<NhanVien> listNV = query.getResultList();
			tr.commit();
			return listNV;
		}
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public NhanVien timNhanVienTheoMa(String maNV) {
		return em.find(NhanVien.class, maNV);
	}
	

	public void suaNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(nv);
			tr.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
	}
	
	public List<NhanVien> timNhanVienTheoTen(String tenNV) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from NhanVien where hoTenNhanVien like concat ('%', ?, '%')", NhanVien.class);
            query.setParameter(1,tenNV);
			List<NhanVien> listNV = query.getResultList();
			tr.commit();
			return listNV;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public List<NhanVien> timNhanVienTheoSDT(String sdt) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from NhanVien where sDT like concat ('%', ?, '%')", NhanVien.class);
            query.setParameter(1,sdt);
			List<NhanVien> listNV = query.getResultList();
			tr.commit();
			return listNV;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public List<NhanVien> timNhanVienTheoTenVaSDT(String tenNV, String sdt) {
			EntityTransaction tr = em.getTransaction();
			try {
				tr.begin();
				Query query = em.createNativeQuery("select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%') or sdt LIKE CONCAT('%', ?, '%')", NhanVien.class);
	            query.setParameter(1,tenNV);
				query.setParameter(2,sdt);
				List<NhanVien> listNV = query.getResultList();
				tr.commit();
				return listNV;
			}catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}
			return null;
	}

	public NhanVien timNhanVienTheoEmail(String email) {

		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select * from NhanVien where email = ?", NhanVien.class);
            query.setParameter(1,email);
			NhanVien nv = (NhanVien) query.getSingleResult();
			tr.commit();
			return nv;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;

	}

	public boolean capNhatOTP(String email, String OTP) {
			EntityTransaction tr = em.getTransaction();
			try {
				tr.begin();
				Query query = em.createNativeQuery("update NhanVien set OTP = ?, hanOTP = ? where email = ?", NhanVien.class);
	            query.setParameter(1,OTP);
	            query.setParameter(2,new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000));
	            query.setParameter(3,email);
				query.executeUpdate();
				tr.commit();
				return true;
			}catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}
			return false;
	}
	
	public String layOTP() {
		String OTP = String.format("%06d", new Random().nextInt(999999));
		return OTP;
	}
	public Timestamp layHanOTP() {
		return new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);
	}
}
