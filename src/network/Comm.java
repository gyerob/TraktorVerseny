package network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Traktoros.Main;

import com.google.gson.Gson;

import data.Drag;
import data.Racer;
import data.Slalom;
import data.Trailer;
import datastorage.DbConstants;

public class Comm extends Thread {
	private Socket socket;
	private boolean running = true;
	private BufferedReader input;
	private PrintWriter output;
	private Gson gson;
	private String sql = null;
	private Statement stmt;

	public Comm(int port) throws IOException {
		new ServerSocket(port);
	}

	public Comm(Socket s) {
		socket = s;
		gson = new Gson();
	}

	public void run() {
		running = true;
		try {

			input = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "UTF-8"));
			output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);

			String read = null;

			while (running) {
				read = input.readLine();
				if (read != null) {
					System.out.println(read);

					if (read.contains("updatealldb")) {
						updateDB();
					}
					if (read.contains("sendracer")) {
						getRacer(input.readLine());
					}
					if (read.contains("sendtrailer")) {
						getTrailer(input.readLine());
					}
					if (read.contains("sendslalom")) {
						getSlalom(input.readLine());
					}
					if (read.contains("senddrag")) {
						getDrag(input.readLine());
					}
					if (read.contains("getracerid")) {

						sendRacer(read);
					}
					if (read.contains("gettrailerid")) {

						sendTrailer(read);
					}
					if (read.contains("getslalomid")) {

						sendSlalom(read);
					}
					if (read.contains("getdragid")) {

						sendDrag(read);
					}
					if (read.contains("deleteracer")) {
						deleteRacer(read);
					}
					if (read.contains("deletetrailer")) {
						deleteTrailer(read);
					}
					if (read.contains("deleteslalom")) {
						deleteSlalom(read);
					}
					if (read.contains("deletedrag")) {
						deleteDrag(read);
					}
					if (read.contains("countimage")) {
						countImage();
					}
					if (read.contains("getimage")) {
						try {
							sendImage(read);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (read.contains("postimage")) {
						getImage();
					}

				}
			}
			socket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			System.out.println("Socket timed out!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		running = false;
		this.interrupt();
	}

	private void updateRacer() {
		String count = null;
		try {
			count = "SELECT COUNT(*) FROM " + DbConstants.Racer.Database_Table;
			sql = "SELECT * FROM " + DbConstants.Racer.Database_Table;
			System.out.println(count);
			System.out.println(sql);
			stmt = Main.connectionData.createStatement();
			ResultSet sor = stmt.executeQuery(count);
			int sorok = sor.getInt(1);

			output.println(sorok);

			ResultSet lista = stmt.executeQuery(sql);
			while (lista.next()) {
				String nev, varos, nem, pot, szlalom, gyors;
				int rajt;

				nev = lista.getString(DbConstants.Racer.Key_Name);
				rajt = lista.getInt(DbConstants.Racer.Key_Rajtszam);
				varos = lista.getString(DbConstants.Racer.Key_Varos);
				nem = lista.getString(DbConstants.Racer.Key_Nem);
				pot = lista.getString(DbConstants.Racer.Key_Potkocsis);
				szlalom = lista.getString(DbConstants.Racer.Key_Szlalom);
				gyors = lista.getString(DbConstants.Racer.Key_Gyorsulas);

				System.out.println(nev + " " + rajt + " " + varos + " " + nem
						+ " " + pot + " " + szlalom + " " + gyors);

				Racer r = new Racer(nev, rajt, varos, nem, pot, szlalom, gyors);
				String json = gson.toJson(r);
				output.println(json);
			}
			int jonnek = Integer.parseInt(input.readLine());
			for (int j = 0; j < jonnek; j++) {
				String json = input.readLine();
				Racer r = gson.fromJson(json, Racer.class);
				System.out.println(json);

				stmt = Main.connectionData.createStatement();

				sql = "SELECT * FROM " + DbConstants.Racer.Database_Table
						+ " WHERE " + DbConstants.Racer.Key_Rajtszam + " = "
						+ r.getNumber();
				ResultSet result = stmt.executeQuery(sql);
				int rowcount = 0;
				while (result.next()) {
					rowcount++;
				}

				if (rowcount == 0) {
					sql = "INSERT INTO " + DbConstants.Racer.Database_Table
							+ " (" + DbConstants.Racer.Key_Rajtszam + ", "
							+ DbConstants.Racer.Key_Name + ", "
							+ DbConstants.Racer.Key_Varos + ", "
							+ DbConstants.Racer.Key_Nem + ", "
							+ DbConstants.Racer.Key_Potkocsis + ", "
							+ DbConstants.Racer.Key_Szlalom + ", "
							+ DbConstants.Racer.Key_Gyorsulas + ") VALUES ('"
							+ r.getNumber() + "', '" + r.getName() + "', '"
							+ r.getTown() + "', '" + r.getSex() + "', '"
							+ r.getTrailer() + "', '" + r.getSlalom() + "', '"
							+ r.getDrag() + "');";
				} else {
					sql = "UPDATE " + DbConstants.Racer.Database_Table
							+ " SET " + DbConstants.Racer.Key_Name + " = '"
							+ r.getName() + "', " + DbConstants.Racer.Key_Varos
							+ " = '" + r.getTown() + "', "
							+ DbConstants.Racer.Key_Nem + " = '" + r.getSex()
							+ "', " + DbConstants.Racer.Key_Potkocsis + " = '"
							+ r.getTrailer() + "', "
							+ DbConstants.Racer.Key_Szlalom + " = '"
							+ r.getSlalom() + "', "
							+ DbConstants.Racer.Key_Gyorsulas + " = '"
							+ r.getDrag() + "' WHERE "
							+ DbConstants.Racer.Key_Rajtszam + " = "
							+ r.getNumber();
				}
				stmt.executeUpdate(sql);
				stmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateTrailer() {
		String count = null;
		try {
			count = "SELECT COUNT(*) FROM "
					+ DbConstants.Trailer.Database_Table;
			sql = "SELECT * FROM " + DbConstants.Trailer.Database_Table;
			System.out.println(count);
			System.out.println(sql);
			stmt = Main.connectionData.createStatement();
			ResultSet sor = stmt.executeQuery(count);
			int sorok = sor.getInt(1);

			output.println(sorok);

			ResultSet lista = stmt.executeQuery(sql);
			while (lista.next()) {
				String nev, ido;
				int rajt, hiba;

				nev = lista.getString(DbConstants.Trailer.Key_Name);
				rajt = lista.getInt(DbConstants.Trailer.Key_Rajtszam);
				ido = lista.getString(DbConstants.Trailer.Key_Ido);
				hiba = lista.getInt(DbConstants.Trailer.Key_Hiba);

				System.out.println(nev + " " + rajt + " " + ido + " " + hiba);

				Trailer r = new Trailer(nev, rajt, ido, hiba);
				r.setTimes();
				String json = gson.toJson(r);
				output.println(json);
			}
			int jonnek = Integer.parseInt(input.readLine());
			for (int j = 0; j < jonnek; j++) {
				String json = input.readLine();
				Trailer r = gson.fromJson(json, Trailer.class);
				r.setTimes();
				System.out.println(json);

				stmt = Main.connectionData.createStatement();
				sql = "SELECT * FROM " + DbConstants.Trailer.Database_Table
						+ " WHERE " + DbConstants.Trailer.Key_Rajtszam + " = "
						+ r.getNumber();
				ResultSet result = stmt.executeQuery(sql);
				int rowcount = 0;
				while (result.next()) {
					rowcount++;
				}

				if (rowcount == 0) {
					sql = "INSERT INTO " + DbConstants.Trailer.Database_Table
							+ " (" + DbConstants.Trailer.Key_Rajtszam + ", "
							+ DbConstants.Trailer.Key_Name + ", "
							+ DbConstants.Trailer.Key_Ido + ", "
							+ DbConstants.Trailer.Key_Hiba + ", "
							+ DbConstants.Trailer.Key_VIdo + ") VALUES ('"
							+ r.getNumber() + "', '" + r.getName() + "', '"
							+ r.getIdo() + "', '" + r.getHiba() + "', '"
							+ r.getVido() + "');";
				} else {
					sql = "UPDATE " + DbConstants.Trailer.Database_Table
							+ " SET " + DbConstants.Trailer.Key_Name + " = '"
							+ r.getName() + "', " + DbConstants.Trailer.Key_Ido
							+ " = '" + r.getIdo() + "', "
							+ DbConstants.Trailer.Key_Hiba + " = '"
							+ r.getHiba() + "', "
							+ DbConstants.Trailer.Key_VIdo + " = '"
							+ r.getVido() + "' WHERE "
							+ DbConstants.Trailer.Key_Rajtszam + " = "
							+ r.getNumber();
				}
				stmt.executeUpdate(sql);
				stmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateSlalom() {
		String count = null;
		try {
			count = "SELECT COUNT(*) FROM " + DbConstants.Slalom.Database_Table;
			sql = "SELECT * FROM " + DbConstants.Slalom.Database_Table;
			System.out.println(count);
			System.out.println(sql);
			stmt = Main.connectionData.createStatement();
			ResultSet sor = stmt.executeQuery(count);
			int sorok = sor.getInt(1);

			output.println(sorok);

			ResultSet lista = stmt.executeQuery(sql);
			while (lista.next()) {
				String nev, ido;
				int rajt, hiba;

				nev = lista.getString(DbConstants.Slalom.Key_Name);
				rajt = lista.getInt(DbConstants.Slalom.Key_Rajtszam);
				ido = lista.getString(DbConstants.Slalom.Key_Ido);
				hiba = lista.getInt(DbConstants.Slalom.Key_Hiba);

				System.out.println(nev + " " + rajt + " " + ido + " " + hiba);

				Slalom r = new Slalom(nev, rajt, ido, hiba);
				r.setTimes();
				String json = gson.toJson(r);
				output.println(json);
			}
			int jonnek = Integer.parseInt(input.readLine());
			for (int j = 0; j < jonnek; j++) {
				String json = input.readLine();
				Slalom r = gson.fromJson(json, Slalom.class);
				r.setTimes();
				System.out.println(json);

				stmt = Main.connectionData.createStatement();
				sql = "SELECT * FROM " + DbConstants.Slalom.Database_Table
						+ " WHERE " + DbConstants.Slalom.Key_Rajtszam + " = "
						+ r.getNumber();
				ResultSet result = stmt.executeQuery(sql);
				int rowcount = 0;
				while (result.next()) {
					rowcount++;
				}

				if (rowcount == 0) {
					sql = "INSERT INTO " + DbConstants.Slalom.Database_Table
							+ " (" + DbConstants.Slalom.Key_Rajtszam + ", "
							+ DbConstants.Slalom.Key_Name + ", "
							+ DbConstants.Slalom.Key_Ido + ", "
							+ DbConstants.Slalom.Key_Hiba + ", "
							+ DbConstants.Slalom.Key_VIdo + ") VALUES ('"
							+ r.getNumber() + "', '" + r.getName() + "', '"
							+ r.getIdo() + "', '" + r.getHiba() + "', '"
							+ r.getVido() + "');";
				} else {
					sql = "UPDATE " + DbConstants.Slalom.Database_Table
							+ " SET " + DbConstants.Slalom.Key_Name + " = '"
							+ r.getName() + "', " + DbConstants.Slalom.Key_Ido
							+ " = '" + r.getIdo() + "', "
							+ DbConstants.Slalom.Key_Hiba + " = '"
							+ r.getHiba() + "' WHERE "
							+ DbConstants.Slalom.Key_Rajtszam + " = "
							+ r.getNumber();
				}
				stmt.executeUpdate(sql);
				stmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateDrag() {
		String count = null;
		try {
			count = "SELECT COUNT(*) FROM " + DbConstants.Drag.Database_Table;
			sql = "SELECT * FROM " + DbConstants.Drag.Database_Table;
			System.out.println(count);
			System.out.println(sql);
			stmt = Main.connectionData.createStatement();
			ResultSet sor = stmt.executeQuery(count);
			int sorok = sor.getInt(1);

			output.println(sorok);

			ResultSet lista = stmt.executeQuery(sql);
			while (lista.next()) {
				String nev, ido1, ido2;
				int rajt;

				nev = lista.getString(DbConstants.Drag.Key_Name);
				rajt = lista.getInt(DbConstants.Drag.Key_Rajtszam);
				ido1 = lista.getString(DbConstants.Drag.Key_Ido1);
				ido2 = lista.getString(DbConstants.Drag.Key_Ido2);

				System.out.println(nev + " " + rajt + " " + ido1 + " " + ido2);

				Drag r = new Drag(nev, rajt, ido1, ido2);
				r.setTimes();
				String json = gson.toJson(r);
				output.println(json);
			}
			int jonnek = Integer.parseInt(input.readLine());
			for (int j = 0; j < jonnek; j++) {
				String json = input.readLine();
				Drag r = gson.fromJson(json, Drag.class);
				r.setTimes();
				System.out.println(json);

				stmt = Main.connectionData.createStatement();
				sql = "SELECT * FROM " + DbConstants.Drag.Database_Table
						+ " WHERE " + DbConstants.Drag.Key_Rajtszam + " = "
						+ r.getNumber();
				ResultSet result = stmt.executeQuery(sql);
				int rowcount = 0;
				while (result.next()) {
					rowcount++;
				}

				if (rowcount == 0) {
					sql = "INSERT INTO " + DbConstants.Drag.Database_Table
							+ " (" + DbConstants.Drag.Key_Rajtszam + ", "
							+ DbConstants.Drag.Key_Name + ", "
							+ DbConstants.Drag.Key_Ido1 + ", "
							+ DbConstants.Drag.Key_Ido2 + ", "
							+ DbConstants.Drag.Key_LIdo + ") VALUES ('"
							+ r.getNumber() + "', '" + r.getName() + "', '"
							+ r.getIdo1() + "', '" + r.getIdo2() + "', '"
							+ r.getLegjobbido() + "');";
				} else {
					sql = "UPDATE " + DbConstants.Drag.Database_Table + " SET "
							+ DbConstants.Drag.Key_Name + " = '" + r.getName()
							+ "', " + DbConstants.Drag.Key_Ido1 + " = '"
							+ r.getIdo1() + "', " + DbConstants.Drag.Key_Ido2
							+ " = '" + r.getIdo2() + "', "
							+ DbConstants.Drag.Key_LIdo + " = '"
							+ r.getLegjobbido() + "' WHERE "
							+ DbConstants.Drag.Key_Rajtszam + " = "
							+ r.getNumber();
				}
				stmt.executeUpdate(sql);
				stmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateDB() {

		updateRacer();
		updateTrailer();
		updateSlalom();
		updateDrag();

		running = false;
	}

	private void deleteDrag(String parancs) {
		String[] command = parancs.split(":");
		int id = Integer.parseInt(command[1]);
		sql = "DELETE FROM " + DbConstants.Drag.Database_Table + " WHERE "
				+ DbConstants.Drag.Key_Rajtszam + " = " + id;
		try {
			stmt = Main.connectionData.createStatement();

			stmt.executeUpdate(sql);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	private void deleteSlalom(String parancs) {
		String[] command = parancs.split(":");
		int id = Integer.parseInt(command[1]);
		sql = "DELETE FROM " + DbConstants.Slalom.Database_Table + " WHERE "
				+ DbConstants.Slalom.Key_Rajtszam + " = " + id;
		try {
			stmt = Main.connectionData.createStatement();

			stmt.executeUpdate(sql);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	private void deleteTrailer(String parancs) {
		String[] command = parancs.split(":");
		int id = Integer.parseInt(command[1]);
		sql = "DELETE FROM " + DbConstants.Trailer.Database_Table + " WHERE "
				+ DbConstants.Trailer.Key_Rajtszam + " = " + id;
		try {
			stmt = Main.connectionData.createStatement();

			stmt.executeUpdate(sql);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	private void deleteRacer(String parancs) {
		String[] command = parancs.split(":");
		int id = Integer.parseInt(command[1]);
		sql = "DELETE FROM " + DbConstants.Racer.Database_Table + " WHERE "
				+ DbConstants.Racer.Key_Rajtszam + " = " + id;
		try {
			stmt = Main.connectionData.createStatement();

			stmt.executeUpdate(sql);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void getRacer(String json) {
		System.out.println(json);
		Racer r = gson.fromJson(json, Racer.class);
		System.out.println(r.getNumber() + " " + r.getName() + " "
				+ r.getTown() + " " + r.getSex() + " " + r.getTrailer() + " "
				+ r.getSlalom() + " " + r.getDrag());

		try {
			stmt = Main.connectionData.createStatement();

			sql = "INSERT INTO " + DbConstants.Racer.Database_Table + " ("
					+ DbConstants.Racer.Key_Rajtszam + ", "
					+ DbConstants.Racer.Key_Name + ", "
					+ DbConstants.Racer.Key_Varos + ", "
					+ DbConstants.Racer.Key_Nem + ", "
					+ DbConstants.Racer.Key_Potkocsis + ", "
					+ DbConstants.Racer.Key_Szlalom + ", "
					+ DbConstants.Racer.Key_Gyorsulas + ") VALUES ('"
					+ r.getNumber() + "', '" + r.getName() + "', '"
					+ r.getTown() + "', '" + r.getSex() + "', '"
					+ r.getTrailer() + "', '" + r.getSlalom() + "', '"
					+ r.getDrag() + "');";
			;
			stmt.executeUpdate(sql);
			if (r.getTrailer()) {
				sql = "INSERT INTO " + DbConstants.Trailer.Database_Table
						+ " (" + DbConstants.Trailer.Key_Rajtszam + ", "
						+ DbConstants.Trailer.Key_Name + ", "
						+ DbConstants.Trailer.Key_Ido + ", "
						+ DbConstants.Trailer.Key_Hiba + ", "
						+ DbConstants.Trailer.Key_VIdo + ") VALUES ('"
						+ r.getNumber() + "', '" + r.getName() + "', '"
						+ "99:99:999" + "', '" + "99" + "', '" + "99:99:999"
						+ "');";
				stmt.executeUpdate(sql);
			}
			if (r.getSlalom()) {
				sql = "INSERT INTO " + DbConstants.Slalom.Database_Table + " ("
						+ DbConstants.Slalom.Key_Rajtszam + ", "
						+ DbConstants.Slalom.Key_Name + ", "
						+ DbConstants.Slalom.Key_Ido + ", "
						+ DbConstants.Slalom.Key_Hiba + ", "
						+ DbConstants.Slalom.Key_VIdo + ") VALUES ('"
						+ r.getNumber() + "', '" + r.getName() + "', '"
						+ "99:99:999" + "', '" + "99" + "', '" + "99:99:999"
						+ "');";
				stmt.executeUpdate(sql);
			}
			if (r.getDrag()) {
				sql = "INSERT INTO " + DbConstants.Drag.Database_Table + " ("
						+ DbConstants.Drag.Key_Rajtszam + ", "
						+ DbConstants.Drag.Key_Name + ", "
						+ DbConstants.Drag.Key_Ido1 + ", "
						+ DbConstants.Drag.Key_Ido2 + ", "
						+ DbConstants.Drag.Key_LIdo + ") VALUES ('"
						+ r.getNumber() + "', '" + r.getName() + "', '"
						+ "99:999" + "', '" + "99:999" + "', '" + "99:999"
						+ "');";
				stmt.executeUpdate(sql);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void getTrailer(String json) {
		System.out.println(json);
		Trailer t = gson.fromJson(json, Trailer.class);
		System.out.println(t.getNumber() + " " + t.getName() + " " + t.getIdo()
				+ " " + t.getHiba() + " " + t.getVido());

		sql = "UPDATE " + DbConstants.Trailer.Database_Table + " SET "
				+ DbConstants.Trailer.Key_Ido + " = '" + t.getIdo() + "' , "
				+ DbConstants.Trailer.Key_Hiba + " = '" + t.getHiba() + "' , "
				+ DbConstants.Trailer.Key_VIdo + " = '" + t.getVido()
				+ "' WHERE " + DbConstants.Trailer.Key_Rajtszam + " == "
				+ t.getNumber() + ";";

		try {
			stmt = Main.connectionData.createStatement();

			stmt.executeUpdate(sql);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void getSlalom(String json) {
		System.out.println(json);
		Slalom s = gson.fromJson(json, Slalom.class);
		System.out.println(s.getNumber() + " " + s.getName() + " " + s.getIdo()
				+ " " + s.getHiba() + " " + s.getVido());

		sql = "UPDATE " + DbConstants.Slalom.Database_Table + " SET "
				+ DbConstants.Slalom.Key_Ido + " = '" + s.getIdo() + "' , "
				+ DbConstants.Slalom.Key_Hiba + " = '" + s.getHiba() + "' , "
				+ DbConstants.Slalom.Key_VIdo + " = '" + s.getVido()
				+ "' WHERE " + DbConstants.Slalom.Key_Rajtszam + " == "
				+ s.getNumber() + ";";
		try {
			stmt = Main.connectionData.createStatement();

			stmt.executeUpdate(sql);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void getDrag(String json) {
		System.out.println(json);
		Drag d = gson.fromJson(json, Drag.class);
		System.out.println(d.getNumber() + " " + d.getName() + " "
				+ d.getIdo1() + " " + d.getIdo2() + " " + d.getLegjobbido());

		sql = "UPDATE " + DbConstants.Drag.Database_Table + " SET "
				+ DbConstants.Drag.Key_Ido1 + " = '" + d.getIdo1() + "', "
				+ DbConstants.Drag.Key_Ido2 + " = '" + d.getIdo2() + "', "
				+ DbConstants.Drag.Key_LIdo + " = '" + d.getLegjobbido()
				+ "' WHERE " + DbConstants.Drag.Key_Rajtszam + " == "
				+ d.getNumber() + ";";

		try {
			stmt = Main.connectionData.createStatement();

			stmt.executeUpdate(sql);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void sendRacer(String parancs) {
		String[] command = parancs.split(":");
		int id = Integer.parseInt(command[1]);
		System.out.println("id: " + id);
		sql = "SELECT * FROM " + DbConstants.Racer.Database_Table + " WHERE "
				+ DbConstants.Racer.Key_Rajtszam + " = " + id;
		System.out.println(sql);
		try {
			stmt = Main.connectionData.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String nev, varos, nem, pot, szlalom, gyors;
			int rajt;

			nev = rs.getString(DbConstants.Racer.Key_Name);
			rajt = rs.getInt(DbConstants.Racer.Key_Rajtszam);
			varos = rs.getString(DbConstants.Racer.Key_Varos);
			nem = rs.getString(DbConstants.Racer.Key_Nem);
			pot = rs.getString(DbConstants.Racer.Key_Potkocsis);
			szlalom = rs.getString(DbConstants.Racer.Key_Szlalom);
			gyors = rs.getString(DbConstants.Racer.Key_Gyorsulas);

			System.out.println(nev + " " + rajt + " " + varos + " " + nem + " "
					+ pot + " " + szlalom + " " + gyors);

			Racer r = new Racer(nev, rajt, varos, nem, pot, szlalom, gyors);

			String json = gson.toJson(r);
			System.out.println(json);
			output.write(json);
			output.flush();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void sendTrailer(String parancs) {
		String[] command = parancs.split(":");
		int id = Integer.parseInt(command[1]);
		sql = "SELECT * FROM " + DbConstants.Trailer.Database_Table + " WHERE "
				+ DbConstants.Trailer.Key_Rajtszam + " = " + id;
		try {
			stmt = Main.connectionData.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String nev, ido, hiba;
			int rajt;

			nev = rs.getString(DbConstants.Drag.Key_Name);
			rajt = rs.getInt(DbConstants.Drag.Key_Rajtszam);
			ido = rs.getString(DbConstants.Drag.Key_Ido1);
			hiba = rs.getString(DbConstants.Drag.Key_Ido2);

			Drag d = new Drag(nev, rajt, ido, hiba);
			d.setTimes();

			String json = gson.toJson(d);

			output.write(json);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void sendSlalom(String parancs) {
		String[] command = parancs.split(":");
		int id = Integer.parseInt(command[1]);
		sql = "SELECT * FROM " + DbConstants.Slalom.Database_Table + " WHERE "
				+ DbConstants.Slalom.Key_Rajtszam + " = " + id;
		try {
			stmt = Main.connectionData.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String nev, ido2;
			int rajt;

			nev = rs.getString(DbConstants.Drag.Key_Name);
			rajt = rs.getInt(DbConstants.Drag.Key_Rajtszam);
			rs.getString(DbConstants.Drag.Key_Ido1);
			ido2 = rs.getString(DbConstants.Drag.Key_Ido2);

			Drag d = new Drag(nev, rajt, ido2, ido2);
			d.setTimes();

			String json = gson.toJson(d);

			output.write(json);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void sendDrag(String parancs) {
		String[] command = parancs.split(":");
		int id = Integer.parseInt(command[1]);

		sql = "SELECT * FROM " + DbConstants.Drag.Database_Table + " WHERE "
				+ DbConstants.Drag.Key_Rajtszam + " = " + id;
		try {
			stmt = Main.connectionData.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String nev, ido1, ido2;
			int rajt;

			nev = rs.getString(DbConstants.Drag.Key_Name);
			rajt = rs.getInt(DbConstants.Drag.Key_Rajtszam);
			ido1 = rs.getString(DbConstants.Drag.Key_Ido1);
			ido2 = rs.getString(DbConstants.Drag.Key_Ido2);

			Drag d = new Drag(nev, rajt, ido1, ido2);
			d.setTimes();

			String json = gson.toJson(d);

			output.write(json);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void countImage() {
		String count = "SELECT COUNT(*) FROM "
				+ DbConstants.Kepek.Database_Table;
		sql = "SELECT * FROM " + DbConstants.Kepek.Database_Table;
		try {
			stmt = Main.connectionData.createStatement();
			ResultSet sor = stmt.executeQuery(count);
			int sorok = sor.getInt(1);

			ResultSet lista = stmt.executeQuery(sql);
			String ids = "";
			while (lista.next()) {
				int id = lista.getInt(DbConstants.Key_ID);
				ids = ids.concat(Integer.toString(id) + ",");
			}
			ids = ids.substring(0, ids.length() - 1);

			output.println(sorok);
			output.println(ids);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void sendImage(String parancs) throws Exception {
		String[] command = parancs.split(":");
		int id = Integer.parseInt(command[1]);

		try {
			stmt = Main.connectionData.createStatement();
			sql = "SELECT * FROM " + DbConstants.Kepek.Database_Table
					+ " WHERE " + DbConstants.Key_ID + " = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			String filenev = rs.getString(DbConstants.Kepek.Key_Nev) + "."
					+ rs.getString(DbConstants.Kepek.Key_Ext);

			File img = new File("Images" + File.separator + filenev);
			FileInputStream fis = new FileInputStream(img);

			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());

			long fileSize = img.length();
			long completed = 0;
			int step = 150000;

			oos.writeObject("SENDING_FILE|" + img.getName() + "|" + fileSize);

			byte[] buffer = new byte[step];
			while (completed <= fileSize) {
				fis.read(buffer);
				oos.write(buffer);
				completed += step;
			}
			oos.writeObject("SEND_COMPLETE");
			fis.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void getImage() {
		try {
			stmt = Main.connectionData.createStatement();
			sql = "SELECT * FROM " + DbConstants.Kepek.Database_Table
					+ " ORDER BY " + DbConstants.Key_ID;

			ResultSet rs = stmt.executeQuery(sql);
			int lastrowid = 0;
			while (rs.next()) {
				lastrowid = rs.getInt(DbConstants.Key_ID) + 1;
			}
			int utolsoelotti = lastrowid - 1;
			File pictocopy = new File("Images" + File.separator + utolsoelotti
					+ ".jpg");
			File kep = new File("Images" + File.separator + lastrowid + ".jpg");
			kep.createNewFile();

			FileInputStream fromcopy = new FileInputStream(pictocopy);
			FileOutputStream tocopy = new FileOutputStream(kep);

			byte[] buf = new byte[1024];
			int len;
			while ((len = fromcopy.read(buf)) > 0) {
				tocopy.write(buf, 0, len);
			}
			fromcopy.close();
			tocopy.close();

			FileOutputStream fos = new FileOutputStream(kep);
			BufferedInputStream dis = new BufferedInputStream(
					socket.getInputStream());

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = dis.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
			}
			fos.close();

			sql = "INSERT INTO " + DbConstants.Kepek.Database_Table + " ("
					+ DbConstants.Kepek.Key_Nev + ", "
					+ DbConstants.Kepek.Key_Ext + ") VALUES ('" + lastrowid
					+ "', '" + "jpg" + "');";
			stmt.executeUpdate(sql);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			running = false;
		}
	}
}
