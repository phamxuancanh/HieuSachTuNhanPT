package util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MyEMFactory {
	private static MyEMFactory instance;
	private EntityManagerFactory entityManagerFactory;
	
	private MyEMFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BTL_PhanTan_Nhom22");
	}

	public static MyEMFactory getInstance() {
		if(instance == null)
			instance = new MyEMFactory();
		return instance;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
