package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Traktoros.Main;
import datastorage.DbConstants;

public class PotkocsisPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 283862046441080756L;
	private JTextField txtRajt;
	private JTextField textRajt;
	private JTextField txtIdo;
	private JTextField txtHiba;
	private JTextField txtVIdo;
	private JTextField textVido;
	private JTextField textHiba;
	private JTextField TextP;
	private JTextField TextMP;
	private JTextField TextMS;
	private JButton toplistagomb;
	private JButton listazgomb;
	private JButton mentesgomb;
	ResultsDialog potkocsiseredmeny;
	private Statement stmt;

	/**
	 * Create the panel.
	 */
	public PotkocsisPanel() {
		setPreferredSize(new Dimension(400, 235));
		setLayout(null);

		txtRajt = new JTextField();
		txtRajt.setEditable(false);
		txtRajt.setText("Rajtsz\u00E1m:");
		txtRajt.setBounds(10, 11, 86, 20);
		add(txtRajt);
		txtRajt.setColumns(10);

		textRajt = new JTextField();
		textRajt.setSize(new Dimension(80, 20));
		textRajt.setPreferredSize(new Dimension(100, 20));
		textRajt.setBounds(106, 11, 86, 20);
		add(textRajt);
		textRajt.setColumns(10);

		txtIdo = new JTextField();
		txtIdo.setText("Id\u0151:");
		txtIdo.setEditable(false);
		txtIdo.setBounds(10, 42, 86, 20);
		add(txtIdo);
		txtIdo.setColumns(10);

		txtHiba = new JTextField();
		txtHiba.setText("Hibapont:");
		txtHiba.setEditable(false);
		txtHiba.setBounds(10, 73, 86, 20);
		add(txtHiba);
		txtHiba.setColumns(10);

		txtVIdo = new JTextField();
		txtVIdo.setEditable(false);
		txtVIdo.setText("V\u00E9gleges Id\u0151:");
		txtVIdo.setBounds(10, 104, 86, 20);
		add(txtVIdo);
		txtVIdo.setColumns(10);

		textVido = new JTextField();
		textVido.setBounds(106, 104, 86, 20);
		add(textVido);
		textVido.setColumns(10);

		textHiba = new JTextField();
		textHiba.setBounds(106, 73, 86, 20);
		add(textHiba);
		textHiba.setColumns(10);

		TextP = new JTextField();
		TextP.setBounds(106, 42, 22, 20);
		add(TextP);
		TextP.setColumns(10);

		TextMP = new JTextField();
		TextMP.setBounds(138, 42, 22, 20);
		add(TextMP);
		TextMP.setColumns(10);

		TextMS = new JTextField();
		TextMS.setBounds(170, 42, 22, 20);
		add(TextMS);
		TextMS.setColumns(10);

		listazgomb = new JButton("List\u00E1z");
		listazgomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaz(false);
			}
		});
		listazgomb.setBounds(253, 140, 89, 23);
		add(listazgomb);

		toplistagomb = new JButton("Toplista");
		toplistagomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaz(true);
			}
		});
		toplistagomb.setBounds(253, 174, 89, 23);
		add(toplistagomb);
		
		mentesgomb = new JButton("Ment\u00E9s");
		mentesgomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ment();
			}
		});
		mentesgomb.setBounds(253, 103, 89, 23);
		add(mentesgomb);
	}

	private void ment() {
		String perc, mp, ms;

		if (Integer.parseInt(TextP.getText()) < 10) {
			perc = "0" + TextP.getText();
		} else
			perc = TextP.getText();
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

		String ido = perc + ":" + mp + ":" + ms;

		textVido.setText(perc
				+ ":"
				+ (Integer.parseInt(mp) + Integer.parseInt(textHiba.getText()) * 5)
				+ ":"
				+ ms);

		String insert = "UPDATE " + DbConstants.Trailer.Database_Table + " SET " 
				+ DbConstants.Trailer.Key_Ido + " = '" + ido 
				+ "' , "
				+ DbConstants.Trailer.Key_Hiba + " = '" + textHiba.getText()
				+ "' , " + DbConstants.Trailer.Key_VIdo + " = '" + textVido.getText() 
				+ "' WHERE "
				+ DbConstants.Trailer.Key_Rajtszam + " == " + textRajt.getText() + ";";

		try {
			stmt = Main.connectionData.createStatement();

			stmt.executeUpdate(insert);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void listaz(boolean sorted) {
		String select = "SELECT * FROM " 
				+ DbConstants.Trailer.Database_Table;
		String count = "SELECT COUNT(*) FROM "
				+ DbConstants.Trailer.Database_Table;
		String selectsorted = "SELECT * FROM " 
				+ DbConstants.Trailer.Database_Table 
				+ " ORDER BY "
				+ DbConstants.Trailer.Key_VIdo;
		
		try {
			stmt = Main.connectionData.createStatement();
			ResultSet sor = stmt.executeQuery(count);
			int sorok = sor.getInt(1);
			
			if (!sorted) {
				ResultSet rs = stmt.executeQuery(select);
	
				if (potkocsiseredmeny != null)
					potkocsiseredmeny.setVisible(false);
				potkocsiseredmeny = new ResultsDialog("potkocsis", rs, sorok);
				potkocsiseredmeny.setVisible(true);
			} else {
				ResultSet rs = stmt.executeQuery(selectsorted);
				
				if (potkocsiseredmeny != null)
					potkocsiseredmeny.setVisible(false);
				potkocsiseredmeny = new ResultsDialog("potkocsis", rs, sorok);
				potkocsiseredmeny.setVisible(true);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
