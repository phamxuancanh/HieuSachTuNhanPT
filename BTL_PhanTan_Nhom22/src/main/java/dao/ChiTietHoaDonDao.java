package dao;

import java.util.List;

import entity.ChiTietHoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class ChiTietHoaDonDao {
	private EntityManager em;

	public ChiTietHoaDonDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	public boolean themCTHD(ChiTietHoaDon chiTietHoaDon) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSach], [donGia], [soLuong]) VALUES (?,?,?,?)");
			query.setParameter(1, chiTietHoaDon.getHoaDon().getMaHoaDon());
			query.setParameter(2, chiTietHoaDon.getSach().getMaSach());
			query.setParameter(3, chiTietHoaDon.getDonGia());
			query.setParameter(4, chiTietHoaDon.getSoLuong());
			query.executeUpdate();
			tr.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
	public List<ChiTietHoaDon> timChiTietHoaDonTheoMaHD(String maHD){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from ChiTietHoaDon where maHoaDon = ?",ChiTietHoaDon.class);
			query.setParameter(1, maHD);
			List<ChiTietHoaDon> listCTHD = query.getResultList();
			tr.commit();
			return listCTHD;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
}
