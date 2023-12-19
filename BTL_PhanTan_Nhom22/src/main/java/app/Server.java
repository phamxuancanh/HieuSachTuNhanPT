package app;

import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;


import entity.NhaCungCap;
import service.ChiTietHoaDonService;
import service.HoaDonService;
import service.KhachHangService;
import service.NhaCungCapService;
import service.NhaXuatBanService;
import service.NhanVienService;
import service.SachService;
import service.TacGiaService;
import service.TaiKhoanService;
import service.TheLoaiService;
import service.impl.ChiTietHoaDonServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.NhaCungCapServiceImpl;
import service.impl.NhaXuatBanServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SachServiceImpl;
import service.impl.TacGiaServiceImpl;
import service.impl.TaiKhoanServiceImpl;
import service.impl.TheLoaiServiceImpl;


public class Server {
	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("removal")
		SecurityManager securityManager = new SecurityManager();
		if(securityManager == null) {
			System.getProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		
		
		
		Context context = new InitialContext();
		int port = 6642;
		String host = "BI";
		LocateRegistry.createRegistry(port);
		
		ChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonServiceImpl();
		context.bind("rmi://"+host+":"+port+"/chiTietHoaDonService", chiTietHoaDonService);
		
		HoaDonService hoaDonService = new HoaDonServiceImpl();
		context.bind("rmi://"+host+":"+port+"/hoaDonService", hoaDonService);
		
		KhachHangService khachHangService = new KhachHangServiceImpl();
		context.bind("rmi://"+host+":"+port+"/khachHangService", khachHangService);
		
		NhaCungCapService nhaCungCapService = new NhaCungCapServiceImpl();
		context.bind("rmi://"+host+":"+port+"/nhaCungCapService", nhaCungCapService);
		
		NhanVienService nhanVienService = new NhanVienServiceImpl();
		context.bind("rmi://"+host+":"+port+"/nhanVienService", nhanVienService);
		
		NhaXuatBanService nhaXuatBanService = new NhaXuatBanServiceImpl();
		context.bind("rmi://"+host+":"+port+"/nhaXuatBanService", nhaXuatBanService);
		
		SachService sachService = new SachServiceImpl();
		context.bind("rmi://"+host+":"+port+"/sachService", sachService);
		
		TacGiaService tacGiaService = new TacGiaServiceImpl();
		context.bind("rmi://"+host+":"+port+"/tacGiaService", tacGiaService);
		
		TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl();
		context.bind("rmi://"+host+":"+port+"/taiKhoanService", taiKhoanService);
		
		TheLoaiService theLoaiService = new TheLoaiServiceImpl();
		context.bind("rmi://"+host+":"+port+"/theLoaiService", theLoaiService);
		
		System.out.println("OK");
	}
}
