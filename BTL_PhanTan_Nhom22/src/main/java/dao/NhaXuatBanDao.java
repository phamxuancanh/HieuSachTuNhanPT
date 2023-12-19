package dao;

import java.util.List;

import entity.NhaCungCap;
import entity.NhaXuatBan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class NhaXuatBanDao {
	EntityManager em;
	
	public NhaXuatBanDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	public List<NhaXuatBan>  layDanhSachNhaXuatBan(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from NhaXuatBan";
			Query query=  em.createNativeQuery(sql,NhaXuatBan.class);
			List<NhaXuatBan> dsNhaXuatBans = query.getResultList();
			tr.commit();
			return dsNhaXuatBans;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public NhaXuatBan layNhaXuatBanBangMa(String ma) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from NhaXuatBan where maNXB like ?";
			Query query=  em.createNativeQuery(sql,NhaXuatBan.class);
			query.setParameter(1, ma);
			NhaXuatBan nhaXuatBan= (NhaXuatBan) query.getSingleResult();
			tr.commit();
			return nhaXuatBan;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public NhaXuatBan layNhaXuatBanTheoTen (String ten) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql="select * from NhaXuatBan where tenNXB like N'"+ten+"'";
			Query query=  em.createNativeQuery(sql,NhaXuatBan.class);
			NhaXuatBan nhaXuatBan = (NhaXuatBan) query.getSingleResult();
			tr.commit();
			return nhaXuatBan;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	public boolean taoNhaXuatBan (NhaXuatBan nhaXuatBan) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(nhaXuatBan);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
}
