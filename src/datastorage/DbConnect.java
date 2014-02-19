package datastorage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
	private Connection connectiondata;
	private Connection connectiongy;
	private Connection connectionsz;
	private Statement stmt;

	public DbConnect() {
		connectiondata = null;
		connectiongy = null;
		connectionsz = null;
		stmt = null;
	}

	private void imageLoad(Statement st) throws SQLException {
		File imgfolder = new File("Images");
		String[] lista = imgfolder.list();

		if (lista != null) {
			for (int i = 0; i < lista.length; i++) {
				String[] fnev = lista[i].split("\\.");
				String sql = "INSERT INTO " + DbConstants.Kepek.Database_Table
						+ " (" + DbConstants.Kepek.Key_Nev + " , "
						+ DbConstants.Kepek.Key_Ext + ") VALUES ('" + fnev[0]
						+ "' , '" + fnev[1] + "');";
				stmt.executeUpdate(sql);
			}
		}
	}

	private void createTable() throws SQLException {
		stmt = connectiondata.createStatement();

		stmt.executeUpdate(DbConstants.Racer.Database_Create);
		stmt.executeUpdate(DbConstants.Trailer.Database_Create);
		stmt.executeUpdate(DbConstants.Slalom.Database_Create);
		stmt.executeUpdate(DbConstants.Drag.Database_Create);
		stmt.executeUpdate(DbConstants.Kepek.Database_Create);
		ResultSet rskep = stmt.executeQuery("SELECT COUNT(*) FROM "
				+ DbConstants.Kepek.Database_Table);
		if (rskep.getInt(1) == 0) {
			imageLoad(stmt);
		}

		stmt = connectiongy.createStatement();
		stmt.executeUpdate(DbConstants.DragTop10.Database_Create1);
		stmt.executeUpdate(DbConstants.DragTop10.Database_Create2);
		stmt.executeUpdate(DbConstants.DragTop10.Database_Create3);
		stmt.executeUpdate(DbConstants.DragTop10.Database_Create4);
		stmt.executeUpdate(DbConstants.DragTop10.Database_Create5);
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM "
				+ DbConstants.DragTop10.Database_Table1);
		if (rs.getInt(1) == 0) {
			for (int i = 0; i < 8; i++) {
				stmt.executeUpdate("INSERT INTO "
						+ DbConstants.DragTop10.Database_Table1 + " ("
						+ DbConstants.DragTop10.Key_Nev + ", "
						+ DbConstants.DragTop10.Key_Rajtszam + ", "
						+ DbConstants.DragTop10.Key_Nyert
						+ ") VALUES ('0', '0', '0');");
				stmt.executeUpdate("INSERT INTO "
						+ DbConstants.DragTop10.Database_Table5 + " ("
						+ DbConstants.DragTop10.Key_Nev + ", "
						+ DbConstants.DragTop10.Key_Rajtszam
						+ ") VALUES ('0', '0');");
				if (i % 2 == 0) {
					stmt.executeUpdate("INSERT INTO "
							+ DbConstants.DragTop10.Database_Table2 + " ("
							+ DbConstants.DragTop10.Key_Nev + ", "
							+ DbConstants.DragTop10.Key_Rajtszam + ", "
							+ DbConstants.DragTop10.Key_Nyert
							+ ") VALUES ('0', '0', '0');");
					stmt.executeUpdate("INSERT INTO "
							+ DbConstants.DragTop10.Database_Table3 + " ("
							+ DbConstants.DragTop10.Key_Nev + ", "
							+ DbConstants.DragTop10.Key_Rajtszam + ", "
							+ DbConstants.DragTop10.Key_Nyert
							+ ") VALUES ('0', '0', '0');");
					stmt.executeUpdate("INSERT INTO "
							+ DbConstants.DragTop10.Database_Table4 + " ("
							+ DbConstants.DragTop10.Key_Nev + ", "
							+ DbConstants.DragTop10.Key_Rajtszam + ", "
							+ DbConstants.DragTop10.Key_Nyert
							+ ") VALUES ('0', '0', '0');");
				}
			}
			stmt.executeUpdate("INSERT INTO "
					+ DbConstants.DragTop10.Database_Table5 + " ("
					+ DbConstants.DragTop10.Key_Nev + ", "
					+ DbConstants.DragTop10.Key_Rajtszam
					+ ") VALUES ('0', '0');");
			stmt.executeUpdate("INSERT INTO "
					+ DbConstants.DragTop10.Database_Table5 + " ("
					+ DbConstants.DragTop10.Key_Nev + ", "
					+ DbConstants.DragTop10.Key_Rajtszam
					+ ") VALUES ('0', '0');");
		}

		stmt = connectionsz.createStatement();
		stmt.executeUpdate(DbConstants.SlalomTop10.Database_Create1);
		stmt.executeUpdate(DbConstants.SlalomTop10.Database_Create2);
		stmt.executeUpdate(DbConstants.SlalomTop10.Database_Create3);
		stmt.executeUpdate(DbConstants.SlalomTop10.Database_Create4);
		stmt.executeUpdate(DbConstants.SlalomTop10.Database_Create5);
		ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM "
				+ DbConstants.SlalomTop10.Database_Table1);
		if (rs1.getInt(1) == 0) {
			for (int i = 0; i < 8; i++) {
				stmt.executeUpdate("INSERT INTO "
						+ DbConstants.SlalomTop10.Database_Table1 + " ("
						+ DbConstants.SlalomTop10.Key_Nev + ", "
						+ DbConstants.SlalomTop10.Key_Rajtszam + ", "
						+ DbConstants.SlalomTop10.Key_Nyert
						+ ") VALUES ('0', '0', '0');");
				stmt.executeUpdate("INSERT INTO "
						+ DbConstants.SlalomTop10.Database_Table5 + " ("
						+ DbConstants.SlalomTop10.Key_Nev + ", "
						+ DbConstants.SlalomTop10.Key_Rajtszam
						+ ") VALUES ('0', '0');");
				if (i % 2 == 0) {
					stmt.executeUpdate("INSERT INTO "
							+ DbConstants.SlalomTop10.Database_Table2 + " ("
							+ DbConstants.SlalomTop10.Key_Nev + ", "
							+ DbConstants.SlalomTop10.Key_Rajtszam + ", "
							+ DbConstants.SlalomTop10.Key_Nyert
							+ ") VALUES ('0', '0', '0');");
					stmt.executeUpdate("INSERT INTO "
							+ DbConstants.SlalomTop10.Database_Table3 + " ("
							+ DbConstants.SlalomTop10.Key_Nev + ", "
							+ DbConstants.SlalomTop10.Key_Rajtszam + ", "
							+ DbConstants.SlalomTop10.Key_Nyert
							+ ") VALUES ('0', '0', '0');");
					stmt.executeUpdate("INSERT INTO "
							+ DbConstants.SlalomTop10.Database_Table4 + " ("
							+ DbConstants.SlalomTop10.Key_Nev + ", "
							+ DbConstants.SlalomTop10.Key_Rajtszam + ", "
							+ DbConstants.SlalomTop10.Key_Nyert
							+ ") VALUES ('0', '0', '0');");
				}
			}
			stmt.executeUpdate("INSERT INTO "
					+ DbConstants.SlalomTop10.Database_Table5 + " ("
					+ DbConstants.SlalomTop10.Key_Nev + ", "
					+ DbConstants.SlalomTop10.Key_Rajtszam
					+ ") VALUES ('0', '0');");
			stmt.executeUpdate("INSERT INTO "
					+ DbConstants.SlalomTop10.Database_Table5 + " ("
					+ DbConstants.SlalomTop10.Key_Nev + ", "
					+ DbConstants.SlalomTop10.Key_Rajtszam
					+ ") VALUES ('0', '0');");
		}

		stmt.close();
	}

	public void connect() {
		if (!isDataConnected()) {
			try {
				Class.forName(DbConstants.DatabaseDriver);
				connectiondata = DriverManager
						.getConnection(DbConstants.DatabaseType
								+ DbConstants.Database_Name);
				connectiongy = DriverManager
						.getConnection(DbConstants.DatabaseType
								+ DbConstants.Database_NameGy);
				connectionsz = DriverManager
						.getConnection(DbConstants.DatabaseType
								+ DbConstants.Database_NameSz);
				createTable();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		try {
			if (isDataConnected()) {
				connectiondata.close();
			}
			if (isGyConnected()) {
				connectiongy.close();
			}
			if (isSzConnected()) {
				connectionsz.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isDataConnected() {
		try {
			return (connectiondata != null) && !connectiondata.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isGyConnected() {
		try {
			return (connectiongy != null) && !connectiongy.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isSzConnected() {
		try {
			return (connectionsz != null) && !connectionsz.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Connection getDataConnection() {
		if (!isDataConnected()) {
			connect();
		}
		return connectiondata;
	}

	public Connection getGyConnection() {
		if (!isGyConnected()) {
			connect();
		}
		return connectiongy;
	}

	public Connection getSzConnection() {
		if (!isSzConnected()) {
			connect();
		}
		return connectionsz;
	}
}
