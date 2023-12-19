package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entity.ChiTietHoaDon;
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

public class ThongKeDao {
	private EntityManager em;

	public ThongKeDao() {
		// TODO Auto-generated constructor stub
		em = MyEMFactory.getInstance().getEntityManagerFactory().createEntityManager();
	}

	public long getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            Query query = em.createNativeQuery("SELECT SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) \r\n"
                    + "    from ChiTietHoaDon   INNER JOIN\r\n" + " HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
                    + "WHERE  HoaDon.ngayLapHoaDon between  ? and ?", long.class);
            int dayBD = ngayBatDau.getDayOfMonth();
            int monthBD = ngayBatDau.getMonthValue();
            int yearBD = ngayBatDau.getYear();

            query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);
            int dayKT = ngayKetThuc.getDayOfMonth();
            int monthKT = ngayKetThuc.getMonthValue();
            int yearKT = ngayKetThuc.getYear();
            query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);

            long sum;
            if(query.getSingleResult() ==null) {
                sum=0;
            }
            else {
                sum = (long) query.getSingleResult();
            }
            tr.commit();
            return sum;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return 0;
    }

	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery(
					"SELECT COUNT(*)\r\n" + "from HoaDon\r\n" + "WHERE  HoaDon.ngayLapHoaDon between ? and ?");
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
//			Object[] object = (Object[]) query.getSingleResult();
//			int sl = Integer.parseInt(object[0].toString());
			int sl = (int) query.getSingleResult();
			tr.commit();
			return sl;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return 0;
	}

	public Object getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT top(1) sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
					+ "	FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "	KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "			WHERE  HoaDon.ngayLapHoaDon between  ? and ?\r\n"
					+ "	GROUP BY KhachHang.maKhachHang \r\n"
					+ "	order by sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) desc");
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			Object sum = (Object) query.getSingleResult();
			tr.commit();
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return 0;
	}

	public Object getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT NhanVien.maNhanVien\r\n" + "	FROM ChiTietHoaDon INNER JOIN\r\n"
					+ "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "	NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien\r\n"
					+ "			WHERE  HoaDon.ngayLapHoaDon between  ? and ?\r\n"
					+ "	GROUP BY NhanVien.maNhanVien\r\n"
					+ "	HAVING count(HoaDon.maHoaDon) >= all(SELECT count(HoaDon.maHoaDon) AS 'Tong so luong hoa don'\r\n"
					+ "	FROM     chitiethoadon INNER JOIN\r\n"
					+ "			HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "			NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien\r\n"
					+ "	WHERE  HoaDon.ngayLapHoaDon between  ? and ?\r\n" + "	GROUP BY NhanVien.maNhanVien)");
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			query.setParameter(3, yearBD + "-" + monthBD + "-" + dayBD);
			query.setParameter(4, yearKT + "-" + monthKT + "-" + dayKT);
			Object nv = (Object) query.getSingleResult();
			tr.commit();
			return nv;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	public Object getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT KhachHang.maKhachHang FROM ChiTietHoaDon INNER JOIN\r\n"
					+ "					HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "						KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "								WHERE  HoaDon.ngayLapHoaDon between ? and ?\r\n"
					+ "						GROUP BY KhachHang.maKhachHang\r\n"
					+ "						HAVING count(HoaDon.maHoaDon) >= all(SELECT count(HoaDon.maHoaDon) AS 'Tong so luong hoa don'\r\n"
					+ "						FROM     chitiethoadon INNER JOIN\r\n"
					+ "								HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "								KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "						WHERE  HoaDon.ngayLapHoaDon between  ? and ? GROUP BY KhachHang.maKhachHang)");
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			query.setParameter(3, yearBD + "-" + monthBD + "-" + dayBD);
			query.setParameter(4, yearKT + "-" + monthKT + "-" + dayKT);
			Object kh = (Object) query.getSingleResult();
			tr.commit();
			return kh;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery(
					"SELECT top(10) KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.sDT, KhachHang.gioiTinh, KhachHang.diaChi, sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
							+ "FROM     ChiTietHoaDon INNER JOIN\r\n"
							+ "HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
							+ "KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
							+ "WHERE  HoaDon.ngayLapHoaDon between  ? and ?\r\n"
							+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.sDT, KhachHang.gioiTinh, KhachHang.diaChi\r\n"
							+ "order by sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) desc");
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			List<Object[]> list = query.getResultList();
			List<KhachHang> listkh = new ArrayList<>();
			for (Object[] khg : list) {
				KhachHang kh = new KhachHang();
				kh.setMaKhachHang(khg[0].toString());
				kh.setHoTenKhachHang(khg[1].toString());
				kh.setsDT(khg[2].toString());
				kh.setGioiTinh(Boolean.parseBoolean(khg[3].toString()));
				kh.setDiaChi(khg[4].toString());
				listkh.add(kh);
			}
			tr.commit();
			return listkh;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT  count(HoaDon.maHoaDon)\r\n" + "	FROM     HoaDon INNER JOIN\r\n"
					+ "	KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n" + "	\r\n"
					+ "			WHERE  HoaDon.ngayLapHoaDon between  ? and ? and KhachHang.maKhachHang =?\r\n"
					+ "	GROUP BY KhachHang.maKhachHang");
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			query.setParameter(3, maKH);
			Object object = query.getSingleResult();
			int sl = Integer.parseInt(object.toString());
			tr.commit();
			return sl;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return 0;
	}

	public Object getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT  sum(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
					+ "	FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "	KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
					+ "			WHERE  HoaDon.ngayLapHoaDon between  ? and ? and KhachHang.maKhachHang =?\r\n"
					+ "	GROUP BY KhachHang.maKhachHang ");
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			query.setParameter(3, maKH);
			Object sum = (Object) query.getSingleResult();
			tr.commit();
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return 0;
	}

	public List<Sach> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery(
					"SELECT Sach.maSach, Sach.donViSanPham, Sach.ghiChu, Sach.giaNhap, Sach.hinhAnh, Sach.namXuatBan, Sach.soLuongTon, Sach.soTrang, Sach.tenSach, Sach.trongLuong, Sach.maNhaCungCap, Sach.maNhaXuatBan, \r\n"
					+ "					                Sach.maTacGia, Sach.maTheLoaiSach\r\n"
					+ "											FROM    ChiTietHoaDon INNER JOIN\r\n"
					+ "											HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "																                Sach ON ChiTietHoaDon.maSach = Sach.maSach\r\n"
					+ "															WHERE  HoaDon.ngayLapHoaDon BETWEEN  ? and ?\r\n"
					+ "															GROUP BY Sach.maSach, Sach.donViSanPham, Sach.ghiChu, Sach.giaNhap, Sach.hinhAnh, Sach.namXuatBan, Sach.soLuongTon, Sach.soTrang, Sach.tenSach, Sach.trongLuong, Sach.maNhaCungCap, Sach.maNhaXuatBan, \r\n"
					+ "									                       Sach.maTacGia, Sach.maTheLoaiSach\r\n"
					+ "															HAVING SUM(ChiTietHoaDon.soLuong) >= ALL(SELECT  SUM(ChiTietHoaDon.soLuong) AS 'TongSoLuongDaBan'\r\n"
					+ "															FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "													        HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
					+ "															                Sach ON ChiTietHoaDon.maSach = Sach.maSach\r\n"
					+ "																WHERE  HoaDon.ngayLapHoaDon BETWEEN    ? and ?\r\n"
					+ "																GROUP BY Sach.maSach, Sach.donViSanPham, Sach.ghiChu, Sach.giaNhap, Sach.namXuatBan, Sach.hinhAnh, Sach.soLuongTon, Sach.tenSach, Sach.soTrang, Sach.trongLuong, Sach.maNhaCungCap, Sach.maTacGia, \r\n"
					+ "							                         Sach.maTheLoaiSach,  Sach.maNhaXuatBan)");

			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			query.setParameter(3, yearBD + "-" + monthBD + "-" + dayBD);
			query.setParameter(4, yearKT + "-" + monthKT + "-" + dayKT);
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
				if (object[12] != null) {
					sach.setTacGia((TacGia) em.find(TacGia.class, object[12].toString()));
				} else {
					sach.setTacGia(new TacGia("Không có tác giả"));
				}

				sach.setTheLoaiSach((TheLoaiSach) em.find(TheLoaiSach.class, object[13].toString()));

				dsSach.add(sach);
			}
			tr.commit();
			return dsSach;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}

	public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select top(1) sum(ChiTietHoaDon.soLuong)\r\n"
					+ "from Sach inner join ChiTietHoaDon on Sach.maSach = ChiTietHoaDon.maSach inner join HoaDon on HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon\r\n"
					+ "where HoaDon.ngayLapHoaDon between  ? and ?\r\n" + "group by Sach.maSach");

			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			Object object = query.getSingleResult();
			int sl = Integer.parseInt(object.toString());
			tr.commit();
			return sl;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return 0;

	}
	public int getSoLuongSachTon(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT SUM(soLuongTon)\r\n" + "  FROM Sach");
			Object object = query.getSingleResult();
			int sl = Integer.parseInt(object.toString());
			tr.commit();
			return sl;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return 0;
	}
	public int getTongSoSachBanDuoc(){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT SUM(soLuong) FROM  ChiTietHoaDon ");
//			Object object =  query.getSingleResult();
//			int sl = Integer.parseInt(object.toString());
			Object object = query.getSingleResult();
			int sl = Integer.parseInt(object.toString());
			tr.commit();
			return sl;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return 0;
	}
	public Object getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV){
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
				+ "	from ChiTietHoaDon   INNER JOIN\r\n" + "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
				+ "WHERE  HoaDon.ngayLapHoaDon between  ? and ? and HoaDon.maNhanVien=?");
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			query.setParameter(3, maNV);
			Object sum = (Object) query.getSingleResult();
			tr.commit();
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return 0;
	}

	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("SELECT count(*)	from HoaDon  \r\n"
					+ "				WHERE  HoaDon.ngayLapHoaDon between ? and ? and HoaDon.maNhanVien=?");
			int dayBD = ngayBatDau.getDayOfMonth();
			int monthBD = ngayBatDau.getMonthValue();
			int yearBD = ngayBatDau.getYear();

			query.setParameter(1, yearBD + "-" + monthBD + "-" + dayBD);

			int dayKT = ngayKetThuc.getDayOfMonth();
			int monthKT = ngayKetThuc.getMonthValue();
			int yearKT = ngayKetThuc.getYear();

			query.setParameter(2, yearKT + "-" + monthKT + "-" + dayKT);
			query.setParameter(3, maNV);
			Object object = query.getSingleResult();
			int sl = Integer.parseInt(object.toString());
			tr.commit();
			return sl;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return 0;
	}

	public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select DISTINCT NhanVien.maNhanVien, NhanVien.hoTenNhanVien \r\n"
					+ "from NhanVien join HoaDon on NhanVien.maNhanVien = HoaDon.maNhanVien");

			List<Object[]> list = (List<Object[]>) query.getResultList();
			List<NhanVien> dsNV = new ArrayList<>();
			for (Object[] object : list) {
				NhanVien nv = new NhanVien();
				nv.setMaNhanVien(object[0].toString());
				nv.setHoTenNhanVien(object[1].toString());
				dsNV.add(nv);
			}
			tr.commit();
			return dsNV;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
