package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Traktoros.Main;
import datastorage.DbConstants;

public class GyorsulasPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6836494167795442809L;
	private JTextField txtRajtszm;
	private JTextField textRajt;
	private JTextField Ido;
	private JTextField TextMP;
	private JTextField TextMS;
	private JTextField Round;
	private JButton listazgomb;
	private JButton toplistagomb;
	private JRadioButton korElso;
	private JButton top10gomb;
	private ResultsDialog gyorsulaseredmeny;
	private FinalsDialog gyorsulasFinals;
	private JButton mentesgomb;
	private Statement stmt;

	/**
	 * Create the panel.
	 */
	public GyorsulasPanel() {
		setPreferredSize(new Dimension(400, 235));
		setLayout(null);
		
		txtRajtszm = new JTextField();
		txtRajtszm.setEditable(false);
		txtRajtszm.setText("Rajtsz\u00E1m");
		txtRajtszm.setBounds(10, 12, 86, 20);
		add(txtRajtszm);
		txtRajtszm.setColumns(10);
		
		textRajt = new JTextField();
		textRajt.setBounds(106, 12, 86, 20);
		add(textRajt);
		textRajt.setColumns(10);
		
		Ido = new JTextField();
		Ido.setEditable(false);
		Ido.setText("Id\u0151");
		Ido.setBounds(10, 43, 86, 20);
		add(Ido);
		Ido.setColumns(10);
		
		TextMP = new JTextField();
		TextMP.setBounds(106, 43, 35, 20);
		add(TextMP);
		TextMP.setColumns(10);
		
		TextMS = new JTextField();
		TextMS.setBounds(157, 43, 35, 20);
		add(TextMS);
		TextMS.setColumns(10);
		
		Round = new JTextField();
		Round.setEditable(false);
		Round.setText("K\u00F6r");
		Round.setBounds(10, 74, 86, 20);
		add(Round);
		Round.setColumns(10);
		
		korElso = new JRadioButton("Els\u0151");
		korElso.setSelected(true);
		korElso.setBounds(106, 70, 86, 23);
		add(korElso);
		
		listazgomb = new JButton("List\u00E1z");
		listazgomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				listaz(false);
			}
		});
		listazgomb.setBounds(241, 116, 89, 23);
		add(listazgomb);
		
		toplistagomb = new JButton("Toplista");
		toplistagomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				listaz(true);
			}
		});
		toplistagomb.setBounds(241, 147, 89, 23);
		add(toplistagomb);
		
		top10gomb = new JButton("Top 10");
		top10gomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				listaztop10();
			}
		});
		top10gomb.setBounds(241, 178, 89, 23);
		add(top10gomb);
		
		mentesgomb = new JButton("Ment\u00E9s");
		mentesgomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ment();
			}
		});
		mentesgomb.setBounds(241, 82, 89, 23);
		add(mentesgomb);

	}
	
	private void ment() {
		String mp, ms;
		String insert;
		String legjobb = "";
		
		String select = "SELECT * FROM " 
				+ DbConstants.Drag.Database_Table
				+ " WHERE "
				+ DbConstants.Drag.Key_Rajtszam
				+ " == "
				+ textRajt.getText();
		
		try {
			stmt = Main.connectionData.createStatement();
			ResultSet rs = stmt.executeQuery(select);
			legjobb = rs.getString("legjobb");
			System.out.println(legjobb);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if (Integer.parseInt(TextMP.getText()) < 10) {
			mp = "0" + TextMP.getText();
		} else
			mp = TextMP.getText();
		if (Integer.parseInt(TextMS.getText()) < 10) {
			ms = TextMS.getText() + "00";
		} else if (Integer.parseInt(TextMS.getText()) < 100) {
			ms = TextMS.getText() + "0";
		} else
			ms = TextMS.getText();

		String ido = mp + ":" + ms;
		int hasonlit = ido.compareTo(legjobb);
		if (hasonlit < 0) legjobb = ido;
		System.out.println("ido, has és legjobb: " + ido + " " + hasonlit + " " + legjobb);

		if(korElso.isSelected()) {
			insert = "UPDATE " + DbConstants.Drag.Database_Table + " SET " 
					+ DbConstants.Drag.Key_Ido1 + " = '" + ido + "', "
					+ DbConstants.Drag.Key_LIdo + " = '" + legjobb
					+ "' WHERE "
					+ DbConstants.Drag.Key_Rajtszam + " == " + textRajt.getText() + ";";
		} else {
			insert = "UPDATE " + DbConstants.Drag.Database_Table + " SET " 
					+ DbConstants.Drag.Key_Ido2 + " = '" + ido  + "', "
					+ DbConstants.Drag.Key_LIdo + " = '" + legjobb
					+ "' WHERE "
					+ DbConstants.Drag.Key_Rajtszam + " == " + textRajt.getText() + ";";
		}

		try {
			stmt.executeUpdate(insert);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void listaz(boolean sorted) {
		String select = "SELECT * FROM " 
				+ DbConstants.Drag.Database_Table;
		String count = "SELECT COUNT(*) FROM "
				+ DbConstants.Drag.Database_Table;
		String selectsorted = "SELECT * FROM " 
				+ DbConstants.Drag.Database_Table 
				+ " ORDER BY "
				+ DbConstants.Drag.Key_LIdo;
		
		try {
			stmt = Main.connectionData.createStatement();
			ResultSet sor = stmt.executeQuery(count);
			int sorok = sor.getInt(1);
			
			if (!sorted) {
				ResultSet rs = stmt.executeQuery(select);
	
				if (gyorsulaseredmeny != null)
					gyorsulaseredmeny.setVisible(false);
				gyorsulaseredmeny = new ResultsDialog("gyorsulas", rs, sorok);
				gyorsulaseredmeny.setVisible(true);
			} else {
				ResultSet rs = stmt.executeQuery(selectsorted);
				
				if (gyorsulaseredmeny != null)
					gyorsulaseredmeny.setVisible(false);
				gyorsulaseredmeny = new ResultsDialog("gyorsulas", rs, sorok);
				gyorsulaseredmeny.setVisible(true);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void listaztop10() {
		gyorsulasFinals = new FinalsDialog("gyorsulas");
		gyorsulasFinals.setVisible(true);
	}
}
