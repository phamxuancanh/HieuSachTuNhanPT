package app;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import gui.FrmLogin;

public class Client {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		int port = 6642;
		String host = "BI";
		FrmLogin frmLogin = new FrmLogin(port, host);
		frmLogin.setVisible(true);
	}
}