package dao;

import java.util.List;

import entity.TacGia;
import entity.TheLoaiSach;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class TacGiaDao {
	EntityManager em;
	
	public TacGiaDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	public List<TacGia>  layDanhSachTacGia(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from TacGia";
			Query query=  em.createNativeQuery(sql,TacGia.class);
			List<TacGia> dsTacGias = query.getResultList();
			tr.commit();
			return dsTacGias;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<TacGia> timTatCaTacGia(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from TacGia", TacGia.class);
			List<TacGia> listTacGia = query.getResultList();
			tr.commit();
			return listTacGia;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public TacGia layTacGiaBangMa(String ma) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from TacGia where maTacGia like ?";
			Query query=  em.createNativeQuery(sql,TacGia.class);
			query.setParameter(1, ma);
			TacGia tacGia= (TacGia) query.getSingleResult();
			tr.commit();
			return tacGia;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public TacGia layTacGiaTheoTen (String ten) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from TacGia where tenTacGia like ?";
			Query query=  em.createNativeQuery(sql,TacGia.class);
			query.setParameter(1, ten);
			TacGia tacGia= (TacGia) query.getSingleResult();
			tr.commit();
			return tacGia;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public boolean taoTacGia (TacGia tacGia) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(tacGia);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
}
