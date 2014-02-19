package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Traktoros.Main;
import datastorage.DbConstants;

public class ListaPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2478148312472324582L;
	private JTextField txtRajt;
	private JTextField textRajt;
	private JTextField txtNev;
	private JTextField textNev;
	private JTextField txtVaros;
	private JTextField textVaros;
	private JRadioButton btnNem;
	private JCheckBox chkPotk;
	private JCheckBox chkSzlalom;
	private JCheckBox chkGyors;
	private JButton btnMent;
	private JButton btnListaz;
	private ResultsDialog listaeredmeny;
	private Statement stmt;

	/**
	 * Create the panel.
	 */
	public ListaPanel() {
		setPreferredSize(new Dimension(400, 235));
		setLayout(null);

		txtRajt = new JTextField();
		txtRajt.setEditable(false);
		txtRajt.setBounds(10, 27, 86, 20);
		txtRajt.setText("Rajtsz\u00E1m");
		add(txtRajt);
		txtRajt.setColumns(10);

		textRajt = new JTextField();
		textRajt.setBounds(106, 27, 86, 20);
		add(textRajt);
		textRajt.setColumns(10);

		txtNev = new JTextField();
		txtNev.setEditable(false);
		txtNev.setText("N\u00E9v");
		txtNev.setBounds(10, 58, 86, 20);
		add(txtNev);
		txtNev.setColumns(10);

		textNev = new JTextField();
		textNev.setBounds(106, 58, 86, 20);
		add(textNev);
		textNev.setColumns(10);

		txtVaros = new JTextField();
		txtVaros.setEditable(false);
		txtVaros.setText("V\u00E1ros");
		txtVaros.setBounds(10, 89, 86, 20);
		add(txtVaros);
		txtVaros.setColumns(10);

		textVaros = new JTextField();
		textVaros.setBounds(106, 89, 86, 20);
		add(textVaros);
		textVaros.setColumns(10);

		btnNem = new JRadioButton("N\u0151");
		btnNem.setBounds(198, 57, 67, 23);
		add(btnNem);

		chkPotk = new JCheckBox("P\u00F3tkocsis Tolat\u00E1s");
		chkPotk.setBounds(10, 149, 162, 23);
		add(chkPotk);

		chkSzlalom = new JCheckBox("Szlalom");
		chkSzlalom.setBounds(10, 175, 97, 23);
		add(chkSzlalom);

		chkGyors = new JCheckBox("Gyorsul\u00E1s");
		chkGyors.setBounds(10, 205, 97, 23);
		add(chkGyors);

		btnMent = new JButton("Ment\u00E9s");
		btnMent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ment();
			}
		});
		btnMent.setBounds(198, 149, 89, 23);
		add(btnMent);

		btnListaz = new JButton("List\u00E1z");
		btnListaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaz();
			}
		});
		btnListaz.setBounds(198, 175, 89, 23);
		add(btnListaz);

	}

	private void ment() {
		String neme, potk, szl, gy;
		String insertplista, insertszlista, insertgylista;
		insertplista = insertszlista = insertgylista = "";

		if (btnNem.isSelected()) {
			neme = "No";
		} else
			neme = "Ferfi";
		if (chkPotk.isSelected()) {
			potk = "true";
		} else
			potk = "false";
		if (chkSzlalom.isSelected()) {
			szl = "true";
		} else
			szl = "false";
		if (chkGyors.isSelected()) {
			gy = "true";
		} else
			gy = "false";

		String insert = "INSERT INTO " + DbConstants.Racer.Database_Table
				+ " (" + DbConstants.Racer.Key_Rajtszam + ", "
				+ DbConstants.Racer.Key_Name + ", "
				+ DbConstants.Racer.Key_Varos + ", "
				+ DbConstants.Racer.Key_Nem + ", "
				+ DbConstants.Racer.Key_Potkocsis + ", "
				+ DbConstants.Racer.Key_Szlalom + ", "
				+ DbConstants.Racer.Key_Gyorsulas + ") VALUES ('"
				+ textRajt.getText() + "', '" + textNev.getText() + "', '"
				+ textVaros.getText() + "', '" + neme + "', '" + potk + "', '"
				+ szl + "', '" + gy + "');";

		if (chkPotk.isSelected()) {
			insertplista = "INSERT INTO " + DbConstants.Trailer.Database_Table
					+ " (" + DbConstants.Trailer.Key_Rajtszam + ", "
					+ DbConstants.Trailer.Key_Name + ", "
					+ DbConstants.Trailer.Key_Ido + ", "
					+ DbConstants.Trailer.Key_Hiba + ", "
					+ DbConstants.Trailer.Key_VIdo + ") VALUES ('"
					+ textRajt.getText() + "', '" + textNev.getText() + "', '"
					+ "99:99:999" + "', '" + "99" + "', '" + "99:99:999" + "');";
		}

		if (chkSzlalom.isSelected()) {
			insertszlista = "INSERT INTO " + DbConstants.Slalom.Database_Table
					+ " (" + DbConstants.Slalom.Key_Rajtszam + ", "
					+ DbConstants.Slalom.Key_Name + ", "
					+ DbConstants.Slalom.Key_Ido + ", "
					+ DbConstants.Slalom.Key_Hiba + ", "
					+ DbConstants.Slalom.Key_VIdo + ") VALUES ('"
					+ textRajt.getText() + "', '" + textNev.getText() + "', '"
					+ "99:99:999" + "', '" + "99" + "', '" + "99:99:999" + "');";
		}

		if (chkGyors.isSelected()) {
			insertgylista = "INSERT INTO "
					+ DbConstants.Drag.Database_Table + " ("
					+ DbConstants.Drag.Key_Rajtszam + ", "
					+ DbConstants.Drag.Key_Name + ", "
					+ DbConstants.Drag.Key_Ido1 + ", "
					+ DbConstants.Drag.Key_Ido2 + ", "
					+ DbConstants.Drag.Key_LIdo + ") VALUES ('"
					+ textRajt.getText() + "', '" + textNev.getText() + "', '"
					+ "99:999" + "', '" + "99:999" + "', '" + "99:999" + "');";
		}

		try {
			stmt = Main.connectionData.createStatement();
			stmt.executeUpdate(insert);

			if (chkPotk.isSelected())
				stmt.executeUpdate(insertplista);
			if (chkSzlalom.isSelected())
				stmt.executeUpdate(insertszlista);
			if (chkGyors.isSelected())
				stmt.executeUpdate(insertgylista);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void listaz() {
		String select = "SELECT * FROM " + DbConstants.Racer.Database_Table;
		String count = "SELECT COUNT(*) FROM "
				+ DbConstants.Racer.Database_Table;

		try {
			stmt = Main.connectionData.createStatement();
			ResultSet sor = stmt.executeQuery(count);
			int sorok = sor.getInt(1);
			
			ResultSet rs = stmt.executeQuery(select);

			if (listaeredmeny != null)
				listaeredmeny.setVisible(false);
			listaeredmeny = new ResultsDialog("lista", rs, sorok);
			listaeredmeny.setVisible(true);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
