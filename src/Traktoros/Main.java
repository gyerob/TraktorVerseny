package Traktoros;

import java.sql.Connection;

import network.StartNetwork;
import view.MainFrame;
import datastorage.DbConnect;

public class Main {
	public static Connection connectionData;
	public static Connection connectionGy;
	public static Connection connectionSz;
	public static StartNetwork sn;

	public Main() {
		connectionData = null;
	}

	public static void main(String args[]) {
		DbConnect conn = new DbConnect();
		connectionData = conn.getDataConnection();
		connectionGy = conn.getGyConnection();
		connectionSz = conn.getSzConnection();

		sn = new StartNetwork();
		sn.start();
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
}
