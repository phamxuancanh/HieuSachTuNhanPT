package dao;

import java.util.List;

import entity.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class NhaCungCapDao {
	EntityManager em;
	
	public NhaCungCapDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	public List<NhaCungCap>  layDanhSachNhaCungCap(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from NhaCungCap";
			Query query=  em.createNativeQuery(sql,NhaCungCap.class);
			List<NhaCungCap> dsNhaCungCaps = query.getResultList();
			tr.commit();
			return dsNhaCungCaps;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public NhaCungCap layNhaCungCapBangMa(String ma) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from NhaCungCap where maNCC like ?";
			Query query=  em.createNativeQuery(sql,NhaCungCap.class);
			query.setParameter(1, ma);
			NhaCungCap nhaCungCap = (NhaCungCap) query.getSingleResult();
			tr.commit();
			return nhaCungCap;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public NhaCungCap layNhaCungCapTheoTen (String ten) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from NhaCungCap where tenNCC like ?";
			Query query=  em.createNativeQuery(sql,NhaCungCap.class);
			query.setParameter(1, ten);
			NhaCungCap nhaCungCap = (NhaCungCap) query.getSingleResult();
			tr.commit();
			return nhaCungCap;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public boolean taoNhaCungCap (NhaCungCap nhaCungCap) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(nhaCungCap);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
}
