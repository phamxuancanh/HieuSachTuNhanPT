package dao;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import util.MyEMFactory;

public class TaiKhoanDao {
	EntityManager em;
	
	public TaiKhoanDao() {
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	public List<TaiKhoan> timTatCaTaiKhoan(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select *from TaiKhoan");
			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<TaiKhoan> dsTaiKhoan = new ArrayList<>();
			for (Object[] object : list) {
				TaiKhoan tk = new TaiKhoan();
				tk.setTenDangNhap(object[0].toString());
				tk.setMatKhau(object[1].toString());
				tk.setQuyen(Boolean.parseBoolean(object[2].toString()));
				tk.setNhanVien((NhanVien) em.find(NhanVien.class, object[3].toString()));
				dsTaiKhoan.add(tk);
			}
			tr.commit();
			return dsTaiKhoan;
		}
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
	
	public boolean themTaiKhoan(TaiKhoan tk) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(tk);
			tr.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
	public TaiKhoan timTaiKhoanTheoMaNV(String maNV){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT *FROM TaiKhoan where maNhanVien =?");
			TaiKhoan tk = (TaiKhoan) query.getSingleResult();
			tr.commit();
			return tk;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	public boolean doiMatKhau(String passMoi, String maNV) {
			EntityTransaction tr = em.getTransaction();
			try {
				tr.begin();
				Query query = em.createNativeQuery("update TaiKhoan set matKhau =? where maNhanVien=?");
				query.setParameter(1, passMoi);
				query.setParameter(2, maNV);
				query.executeUpdate();
				tr.commit();
				return true;
			}catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}
			return false;
	}
}
