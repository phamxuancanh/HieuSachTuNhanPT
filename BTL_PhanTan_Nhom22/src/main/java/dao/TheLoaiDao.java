package dao;

import java.util.List;

import entity.NhaCungCap;
import entity.TacGia;
import entity.TheLoaiSach;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class TheLoaiDao {
	EntityManager em;
	
	public TheLoaiDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	public List<TheLoaiSach> timTatCaTheLoai(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from TheLoaiSach", TheLoaiSach.class);
			List<TheLoaiSach> listTheLoaiSach = query.getResultList();
			tr.commit();
			return listTheLoaiSach;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	public TheLoaiSach layTheLoaiSachBangMa(String ma) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from TheLoaiSach where maLoai like ?";
			Query query=  em.createNativeQuery(sql,TheLoaiSach.class);
			query.setParameter(1, ma);
			TheLoaiSach theLoaiSach= (TheLoaiSach) query.getSingleResult();
			tr.commit();
			return theLoaiSach;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public TheLoaiSach layTheoLoaiSachTheoTen (String ten) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from TheLoaiSach where tenLoai like ?";
			Query query=  em.createNativeQuery(sql,TheLoaiSach.class);
			query.setParameter(1, ten);
			TheLoaiSach theLoaiSach= (TheLoaiSach) query.getSingleResult();
			tr.commit();
			return theLoaiSach;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public boolean taoTheLoaiSach(TheLoaiSach theLoaiSach) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(theLoaiSach);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
}
