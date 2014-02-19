package view;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import datastorage.DbConstants;

public class ResultsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8468136363703203562L;
	private final JPanel contentPanel = new JPanel();
	private JTable resultsTable;
	private DefaultTableModel tableModel;
	private String[] oszlopok;
	private ResultSet results;
	private int sorokszama;

	private void setModel(String model) {
		if (model.equals("lista")) {
			oszlopok = new String[] {"Rajtszám", "Név", "Város", "Nem", "Pótkocsis", "Szlalom", "Gyorsulás"};
			tableModel = new DefaultTableModel(oszlopok, sorokszama);
			int i = 0;
			try {
				while(results.next()) {
					tableModel.setValueAt(results.getInt(DbConstants.Racer.Key_Rajtszam), i, 0);
					tableModel.setValueAt(results.getString(DbConstants.Racer.Key_Name), i, 1);
					tableModel.setValueAt(results.getString(DbConstants.Racer.Key_Varos), i, 2);
					tableModel.setValueAt(results.getString(DbConstants.Racer.Key_Nem), i, 3);
					tableModel.setValueAt(results.getString(DbConstants.Racer.Key_Potkocsis), i, 4);
					tableModel.setValueAt(results.getString(DbConstants.Racer.Key_Szlalom), i, 5);
					tableModel.setValueAt(results.getString(DbConstants.Racer.Key_Gyorsulas), i, 6);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (model.equals("potkocsis")) {
			oszlopok = new String[] {"Rajtszám", "Név", "Idõ", "Hiba", "Végleges Idõ" };
			tableModel = new DefaultTableModel(oszlopok, sorokszama);
			int i = 0;
			try {
				while(results.next()) {
					tableModel.setValueAt(results.getInt(DbConstants.Trailer.Key_Rajtszam), i, 0);
					tableModel.setValueAt(results.getString(DbConstants.Trailer.Key_Name), i, 1);
					tableModel.setValueAt(results.getString(DbConstants.Trailer.Key_Ido), i, 2);
					tableModel.setValueAt(results.getString(DbConstants.Trailer.Key_Hiba), i, 3);
					tableModel.setValueAt(results.getString(DbConstants.Trailer.Key_VIdo), i, 4);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (model.equals("szlalom")) {
			oszlopok = new String[] {"Rajtszám", "Név", "Idõ", "Hiba", "Végleges Idõ" };
			tableModel = new DefaultTableModel(oszlopok, sorokszama);
			int i = 0;
			try {
				while(results.next()) {
					tableModel.setValueAt(results.getInt(DbConstants.Slalom.Key_Rajtszam), i, 0);
					tableModel.setValueAt(results.getString(DbConstants.Slalom.Key_Name), i, 1);
					tableModel.setValueAt(results.getString(DbConstants.Slalom.Key_Ido), i, 2);
					tableModel.setValueAt(results.getString(DbConstants.Slalom.Key_Hiba), i, 3);
					tableModel.setValueAt(results.getString(DbConstants.Slalom.Key_VIdo), i, 4);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (model.equals("gyorsulas")) {
			oszlopok = new String[] {"Rajtszám", "Név", "Idõ 1", "Idõ 2", "Végleges Idõ" };
			tableModel = new DefaultTableModel(oszlopok, sorokszama);
			int i = 0;
			try {
				while(results.next()) {
					tableModel.setValueAt(results.getInt(DbConstants.Drag.Key_Rajtszam), i, 0);
					tableModel.setValueAt(results.getString(DbConstants.Drag.Key_Name), i, 1);
					tableModel.setValueAt(results.getString(DbConstants.Drag.Key_Ido1), i, 2);
					tableModel.setValueAt(results.getString(DbConstants.Drag.Key_Ido2), i, 3);
					tableModel.setValueAt(results.getString(DbConstants.Drag.Key_LIdo), i, 4);
					i++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResultsDialog(String tipus, ResultSet rs, int sorok) {
		setBounds(100, 100, 666, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 650, 261);
		contentPanel.add(scrollPane);
		resultsTable = new JTable();
		scrollPane.setViewportView(resultsTable);
		
		results = rs;
		sorokszama = sorok;
		
		setModel(tipus);
		resultsTable.setModel(tableModel);
		
		
	}

}
