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

public class SzlalomPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7140813557775013263L;
	private JTextField Rajtszam;
	private JTextField Ido;
	private JTextField Hiba;
	private JTextField Vido;
	private JTextField textRajt;
	private JTextField textHiba;
	private JTextField textVido;
	private JTextField TextP;
	private JTextField TextMP;
	private JTextField TextMS;
	private JButton mentesgomb;
	private JButton toplistagomb;
	private JButton top10gomb;
	private JButton listazgomb;
	private ResultsDialog szlalomeredmeny;
	private FinalsDialog szlalomFinals;
	private Statement stmt;

	/**
	 * Create the panel.
	 */
	public SzlalomPanel() {
		setPreferredSize(new Dimension(400, 235));
		setLayout(null);
		
		Rajtszam = new JTextField();
		Rajtszam.setEditable(false);
		Rajtszam.setText("Rajtsz\u00E1m:");
		Rajtszam.setBounds(10, 11, 86, 20);
		add(Rajtszam);
		Rajtszam.setColumns(10);
		
		Ido = new JTextField();
		Ido.setEditable(false);
		Ido.setText("Id\u0151:");
		Ido.setBounds(10, 42, 86, 20);
		add(Ido);
		Ido.setColumns(10);
		
		Hiba = new JTextField();
		Hiba.setEditable(false);
		Hiba.setText("Hibapont:");
		Hiba.setBounds(10, 73, 86, 20);
		add(Hiba);
		Hiba.setColumns(10);
		
		Vido = new JTextField();
		Vido.setEditable(false);
		Vido.setText("V\u00E9gleges Id\u0151:");
		Vido.setBounds(10, 104, 86, 20);
		add(Vido);
		Vido.setColumns(10);
		
		textRajt = new JTextField();
		textRajt.setBounds(106, 11, 86, 20);
		add(textRajt);
		textRajt.setColumns(10);
		
		textHiba = new JTextField();
		textHiba.setBounds(106, 73, 86, 20);
		add(textHiba);
		textHiba.setColumns(10);
		
		textVido = new JTextField();
		textVido.setEditable(false);
		textVido.setBounds(106, 104, 86, 20);
		add(textVido);
		textVido.setColumns(10);
		
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
		listazgomb.setBounds(256, 126, 89, 23);
		add(listazgomb);
		
		toplistagomb = new JButton("Toplista");
		toplistagomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaz(true);
			}
		});
		toplistagomb.setBounds(256, 160, 89, 23);
		add(toplistagomb);
		
		top10gomb = new JButton("Top 10");
		top10gomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaztop10();
			}
		});
		top10gomb.setBounds(256, 194, 89, 23);
		add(top10gomb);
		
		mentesgomb = new JButton("Ment\u00E9s");
		mentesgomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ment();
			}
		});
		mentesgomb.setBounds(256, 92, 89, 23);
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

		String insert = "UPDATE " 
				+ DbConstants.Slalom.Database_Table + " SET " 
				+ DbConstants.Slalom.Key_Ido + " = '" + ido + "' , "
				+ DbConstants.Slalom.Key_Hiba + " = '" + textHiba.getText() + "' , " 
				+ DbConstants.Slalom.Key_VIdo + " = '" + textVido.getText() 
				+ "' WHERE "
				+ DbConstants.Slalom.Key_Rajtszam + " == " + textRajt.getText() + ";";

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
				+ DbConstants.Slalom.Database_Table;
		String count = "SELECT COUNT(*) FROM "
				+ DbConstants.Slalom.Database_Table;
		String selectsorted = "SELECT * FROM " 
				+ DbConstants.Slalom.Database_Table 
				+ " ORDER BY "
				+ DbConstants.Slalom.Key_VIdo;
		
		try {
			stmt = Main.connectionData.createStatement();
			ResultSet sor = stmt.executeQuery(count);
			int sorok = sor.getInt(1);
			
			if (!sorted) {
				ResultSet rs = stmt.executeQuery(select);
	
				if (szlalomeredmeny != null)
					szlalomeredmeny.setVisible(false);
				szlalomeredmeny = new ResultsDialog("szlalom", rs, sorok);
				szlalomeredmeny.setVisible(true);
			} else {
				ResultSet rs = stmt.executeQuery(selectsorted);
				
				if (szlalomeredmeny != null)
					szlalomeredmeny.setVisible(false);
				szlalomeredmeny = new ResultsDialog("szlalom", rs, sorok);
				szlalomeredmeny.setVisible(true);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void listaztop10() {
		szlalomFinals = new FinalsDialog("szlalom");
		szlalomFinals.setVisible(true);
	}
}
