package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import datastorage.DbConstants;

import Traktoros.Main;

public class FinalsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6820882320475851265L;
	private final JPanel contentPanel = new JPanel();
	private JTextField selejt7;
	private JTextField selejt6;
	private JTextField selejt4;
	private JTextField selejt9;
	private JTextField selejt10;
	private JTextField selejt3;
	private JTextField selejt5;
	private JTextField selejt8;
	private JTextField negyed1;
	private JToggleButton negyed1gomb2;
	private JTextField negyed2;
	private JTextField negyed3;
	private JTextField negyed4;
	private JTextField elodonto1;
	private JTextField elodonto2;
	private JTextField selejt1;
	private JTextField selejt2;
	private JToggleButton elodonto1gomb1;
	private JToggleButton elodonto1gomb2;
	private JToggleButton negyed2gomb1;
	private JToggleButton negyed2gomb2;
	private JToggleButton selejt6gomb1;
	private JToggleButton selejt6gomb2;
	private JToggleButton selejt4gomb1;
	private JToggleButton selejt4gomb2;
	private JToggleButton selejt9gomb1;
	private JToggleButton selejt9gomb2;
	private JToggleButton selejt10gomb1;
	private JToggleButton selejt10gomb2;
	private JToggleButton selejt3gomb1;
	private JToggleButton selejt3gomb2;
	private JToggleButton selejt5gomb1;
	private JToggleButton selejt5gomb2;
	private JToggleButton selejt8gomb1;
	private JToggleButton selejt8gomb2;
	private JToggleButton negyed3gomb1;
	private JToggleButton negyed3gomb2;
	private JToggleButton negyed4gomb1;
	private JToggleButton negyed4gomb2;
	private JToggleButton selejt1gomb1;
	private JToggleButton selejt1gomb2;
	private JToggleButton selejt2gomb1;
	private JToggleButton selejt2gomb2;
	private JToggleButton elodonto2gomb1;
	private JToggleButton elodonto2gomb2;
	private JTextField donto1;
	private JTextField donto2;
	private JToggleButton donto1gomb1;
	private JToggleButton donto1gomb2;
	private JTextField gyoztes;
	private JToggleButton donto2gomb1;
	private JToggleButton donto2gomb2;
	private JTextField donto3;
	private JTextField donto4;
	private JToggleButton donto3gomb1;
	private JToggleButton donto3gomb2;
	private JToggleButton donto4gomb1;
	private JToggleButton donto4gomb2;
	private JTextField harmadik;
	private JToggleButton selejt7gomb1;
	private Component selejt7gomb2;
	private JToggleButton negyed1gomb1;
	private Statement stmt;
	
	private void feltolt(String tipus) {
		String leker = "";
		ResultSet sor = null;
		try {
			stmt = Main.connectionData.createStatement();
			
			if (tipus.equals("szlalom")){
				leker = "SELECT * FROM "
						+ DbConstants.Slalom.Database_Table
						+ " ORDER BY "
						+ DbConstants.Slalom.Key_VIdo
						+ " LIMIT 10";
				sor = stmt.executeQuery(leker);
			}
			if (tipus.equals("gyorsulas")) {
				leker = "SELECT * FROM "
						+ DbConstants.Drag.Database_Table
						+ " ORDER BY "
						+ DbConstants.Drag.Key_LIdo
						+ " LIMIT 10";
				sor = stmt.executeQuery(leker);
			}
			
			sor.next();
			selejt1.setText(sor.getString("nev"));
			sor.next();
			selejt2.setText(sor.getString("nev"));
			sor.next();
			selejt3.setText(sor.getString("nev"));
			sor.next();
			selejt4.setText(sor.getString("nev"));
			sor.next();
			selejt5.setText(sor.getString("nev"));
			sor.next();
			selejt6.setText(sor.getString("nev"));
			sor.next();
			selejt7.setText(sor.getString("nev"));
			sor.next();
			selejt8.setText(sor.getString("nev"));
			sor.next();
			selejt9.setText(sor.getString("nev"));
			sor.next();
			selejt10.setText(sor.getString("nev"));
			
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create the dialog.
	 */
	public FinalsDialog(String tipus) {
		setBounds(100, 100, 800, 446);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			selejt7 = new JTextField();
			selejt7.setText("7");
			GridBagConstraints gbc_selejt7 = new GridBagConstraints();
			gbc_selejt7.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt7.insets = new Insets(0, 0, 5, 5);
			gbc_selejt7.gridx = 0;
			gbc_selejt7.gridy = 0;
			contentPanel.add(selejt7, gbc_selejt7);
			selejt7.setColumns(10);
		}
		{
			selejt7gomb1 = new JToggleButton("");
			selejt7gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt7gomb1 = new GridBagConstraints();
			gbc_selejt7gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt7gomb1.gridx = 1;
			gbc_selejt7gomb1.gridy = 0;
			contentPanel.add(selejt7gomb1, gbc_selejt7gomb1);
		}
		{
			selejt7gomb2 = new JToggleButton("");
			selejt7gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt7gomb2 = new GridBagConstraints();
			gbc_selejt7gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt7gomb2.gridx = 2;
			gbc_selejt7gomb2.gridy = 0;
			contentPanel.add(selejt7gomb2, gbc_selejt7gomb2);
		}
		{
			negyed1 = new JTextField();
			GridBagConstraints gbc_negyed1 = new GridBagConstraints();
			gbc_negyed1.fill = GridBagConstraints.HORIZONTAL;
			gbc_negyed1.insets = new Insets(0, 0, 5, 5);
			gbc_negyed1.gridx = 3;
			gbc_negyed1.gridy = 1;
			contentPanel.add(negyed1, gbc_negyed1);
			negyed1.setColumns(10);
		}
		{
			negyed1gomb1 = new JToggleButton("");
			negyed1gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_negyed1gomb1 = new GridBagConstraints();
			gbc_negyed1gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_negyed1gomb1.gridx = 4;
			gbc_negyed1gomb1.gridy = 1;
			contentPanel.add(negyed1gomb1, gbc_negyed1gomb1);
		}
		{
			negyed1gomb2 = new JToggleButton("");
			negyed1gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_negyed1gomb2 = new GridBagConstraints();
			gbc_negyed1gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_negyed1gomb2.gridx = 5;
			gbc_negyed1gomb2.gridy = 1;
			contentPanel.add(negyed1gomb2, gbc_negyed1gomb2);
		}
		{
			selejt6 = new JTextField();
			selejt6.setText("6");
			GridBagConstraints gbc_selejt6 = new GridBagConstraints();
			gbc_selejt6.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt6.insets = new Insets(0, 0, 5, 5);
			gbc_selejt6.gridx = 0;
			gbc_selejt6.gridy = 2;
			contentPanel.add(selejt6, gbc_selejt6);
			selejt6.setColumns(10);
		}
		{
			selejt6gomb1 = new JToggleButton("");
			selejt6gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt6gomb1 = new GridBagConstraints();
			gbc_selejt6gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt6gomb1.gridx = 1;
			gbc_selejt6gomb1.gridy = 2;
			contentPanel.add(selejt6gomb1, gbc_selejt6gomb1);
		}
		{
			selejt6gomb2 = new JToggleButton("");
			selejt6gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt6gomb2 = new GridBagConstraints();
			gbc_selejt6gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt6gomb2.gridx = 2;
			gbc_selejt6gomb2.gridy = 2;
			contentPanel.add(selejt6gomb2, gbc_selejt6gomb2);
		}
		{
			elodonto1 = new JTextField();
			GridBagConstraints gbc_elodonto1 = new GridBagConstraints();
			gbc_elodonto1.fill = GridBagConstraints.HORIZONTAL;
			gbc_elodonto1.insets = new Insets(0, 0, 5, 5);
			gbc_elodonto1.gridx = 6;
			gbc_elodonto1.gridy = 3;
			contentPanel.add(elodonto1, gbc_elodonto1);
			elodonto1.setColumns(10);
		}
		{
			elodonto1gomb1 = new JToggleButton("");
			elodonto1gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_elodonto1gomb1 = new GridBagConstraints();
			gbc_elodonto1gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_elodonto1gomb1.gridx = 7;
			gbc_elodonto1gomb1.gridy = 3;
			contentPanel.add(elodonto1gomb1, gbc_elodonto1gomb1);
		}
		{
			elodonto1gomb2 = new JToggleButton("");
			elodonto1gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_elodonto1gomb2 = new GridBagConstraints();
			gbc_elodonto1gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_elodonto1gomb2.gridx = 8;
			gbc_elodonto1gomb2.gridy = 3;
			contentPanel.add(elodonto1gomb2, gbc_elodonto1gomb2);
		}
		{
			selejt4 = new JTextField();
			selejt4.setText("4");
			GridBagConstraints gbc_selejt4 = new GridBagConstraints();
			gbc_selejt4.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt4.insets = new Insets(0, 0, 5, 5);
			gbc_selejt4.gridx = 0;
			gbc_selejt4.gridy = 4;
			contentPanel.add(selejt4, gbc_selejt4);
			selejt4.setColumns(10);
		}
		{
			selejt4gomb1 = new JToggleButton("");
			selejt4gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt4gomb1 = new GridBagConstraints();
			gbc_selejt4gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt4gomb1.gridx = 1;
			gbc_selejt4gomb1.gridy = 4;
			contentPanel.add(selejt4gomb1, gbc_selejt4gomb1);
		}
		{
			selejt4gomb2 = new JToggleButton("");
			selejt4gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt4gomb2 = new GridBagConstraints();
			gbc_selejt4gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt4gomb2.gridx = 2;
			gbc_selejt4gomb2.gridy = 4;
			contentPanel.add(selejt4gomb2, gbc_selejt4gomb2);
		}
		{
			donto1 = new JTextField();
			GridBagConstraints gbc_donto1 = new GridBagConstraints();
			gbc_donto1.fill = GridBagConstraints.HORIZONTAL;
			gbc_donto1.insets = new Insets(0, 0, 5, 5);
			gbc_donto1.gridx = 9;
			gbc_donto1.gridy = 4;
			contentPanel.add(donto1, gbc_donto1);
			donto1.setColumns(10);
		}
		{
			donto1gomb1 = new JToggleButton("");
			donto1gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_donto1gomb1 = new GridBagConstraints();
			gbc_donto1gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_donto1gomb1.gridx = 10;
			gbc_donto1gomb1.gridy = 4;
			contentPanel.add(donto1gomb1, gbc_donto1gomb1);
		}
		{
			donto1gomb2 = new JToggleButton("");
			donto1gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_donto1gomb2 = new GridBagConstraints();
			gbc_donto1gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_donto1gomb2.gridx = 11;
			gbc_donto1gomb2.gridy = 4;
			contentPanel.add(donto1gomb2, gbc_donto1gomb2);
		}
		{
			negyed2 = new JTextField();
			GridBagConstraints gbc_negyed2 = new GridBagConstraints();
			gbc_negyed2.fill = GridBagConstraints.HORIZONTAL;
			gbc_negyed2.insets = new Insets(0, 0, 5, 5);
			gbc_negyed2.gridx = 3;
			gbc_negyed2.gridy = 5;
			contentPanel.add(negyed2, gbc_negyed2);
			negyed2.setColumns(10);
		}
		{
			negyed2gomb1 = new JToggleButton("");
			negyed2gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_negyed2gomb1 = new GridBagConstraints();
			gbc_negyed2gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_negyed2gomb1.gridx = 4;
			gbc_negyed2gomb1.gridy = 5;
			contentPanel.add(negyed2gomb1, gbc_negyed2gomb1);
		}
		{
			negyed2gomb2 = new JToggleButton("");
			negyed2gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_negyed2gomb2 = new GridBagConstraints();
			gbc_negyed2gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_negyed2gomb2.gridx = 5;
			gbc_negyed2gomb2.gridy = 5;
			contentPanel.add(negyed2gomb2, gbc_negyed2gomb2);
		}
		{
			selejt1 = new JTextField();
			selejt1.setBackground(Color.GREEN);
			selejt1.setText("1");
			GridBagConstraints gbc_selejt1 = new GridBagConstraints();
			gbc_selejt1.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt1.gridx = 6;
			gbc_selejt1.gridy = 5;
			contentPanel.add(selejt1, gbc_selejt1);
			selejt1.setColumns(10);
		}
		{
			selejt1gomb1 = new JToggleButton("");
			selejt1gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt1gomb1 = new GridBagConstraints();
			gbc_selejt1gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt1gomb1.gridx = 7;
			gbc_selejt1gomb1.gridy = 5;
			contentPanel.add(selejt1gomb1, gbc_selejt1gomb1);
		}
		{
			selejt1gomb2 = new JToggleButton("");
			selejt1gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt1gomb2 = new GridBagConstraints();
			gbc_selejt1gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt1gomb2.gridx = 8;
			gbc_selejt1gomb2.gridy = 5;
			contentPanel.add(selejt1gomb2, gbc_selejt1gomb2);
		}
		{
			selejt9 = new JTextField();
			selejt9.setText("9");
			GridBagConstraints gbc_selejt9 = new GridBagConstraints();
			gbc_selejt9.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt9.insets = new Insets(0, 0, 5, 5);
			gbc_selejt9.gridx = 0;
			gbc_selejt9.gridy = 6;
			contentPanel.add(selejt9, gbc_selejt9);
			selejt9.setColumns(10);
		}
		{
			selejt9gomb1 = new JToggleButton("");
			selejt9gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt9gomb1 = new GridBagConstraints();
			gbc_selejt9gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt9gomb1.gridx = 1;
			gbc_selejt9gomb1.gridy = 6;
			contentPanel.add(selejt9gomb1, gbc_selejt9gomb1);
		}
		{
			selejt9gomb2 = new JToggleButton("");
			selejt9gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt9gomb2 = new GridBagConstraints();
			gbc_selejt9gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt9gomb2.gridx = 2;
			gbc_selejt9gomb2.gridy = 6;
			contentPanel.add(selejt9gomb2, gbc_selejt9gomb2);
		}
		{
			gyoztes = new JTextField();
			gyoztes.setBackground(Color.RED);
			GridBagConstraints gbc_gyoztes = new GridBagConstraints();
			gbc_gyoztes.fill = GridBagConstraints.HORIZONTAL;
			gbc_gyoztes.insets = new Insets(0, 0, 5, 0);
			gbc_gyoztes.gridx = 12;
			gbc_gyoztes.gridy = 7;
			contentPanel.add(gyoztes, gbc_gyoztes);
			gyoztes.setColumns(10);
		}
		{
			selejt10 = new JTextField();
			selejt10.setText("10");
			GridBagConstraints gbc_selejt10 = new GridBagConstraints();
			gbc_selejt10.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt10.insets = new Insets(0, 0, 5, 5);
			gbc_selejt10.gridx = 0;
			gbc_selejt10.gridy = 8;
			contentPanel.add(selejt10, gbc_selejt10);
			selejt10.setColumns(10);
		}
		{
			selejt10gomb1 = new JToggleButton("");
			selejt10gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt10gomb1 = new GridBagConstraints();
			gbc_selejt10gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt10gomb1.gridx = 1;
			gbc_selejt10gomb1.gridy = 8;
			contentPanel.add(selejt10gomb1, gbc_selejt10gomb1);
		}
		{
			selejt10gomb2 = new JToggleButton("");
			selejt10gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt10gomb2 = new GridBagConstraints();
			gbc_selejt10gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt10gomb2.gridx = 2;
			gbc_selejt10gomb2.gridy = 8;
			contentPanel.add(selejt10gomb2, gbc_selejt10gomb2);
		}
		{
			negyed3 = new JTextField();
			GridBagConstraints gbc_negyed3 = new GridBagConstraints();
			gbc_negyed3.fill = GridBagConstraints.HORIZONTAL;
			gbc_negyed3.insets = new Insets(0, 0, 5, 5);
			gbc_negyed3.gridx = 3;
			gbc_negyed3.gridy = 9;
			contentPanel.add(negyed3, gbc_negyed3);
			negyed3.setColumns(10);
		}
		{
			negyed3gomb1 = new JToggleButton("");
			negyed3gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_negyed3gomb1 = new GridBagConstraints();
			gbc_negyed3gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_negyed3gomb1.gridx = 4;
			gbc_negyed3gomb1.gridy = 9;
			contentPanel.add(negyed3gomb1, gbc_negyed3gomb1);
		}
		{
			negyed3gomb2 = new JToggleButton("");
			negyed3gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_negyed3gomb2 = new GridBagConstraints();
			gbc_negyed3gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_negyed3gomb2.gridx = 5;
			gbc_negyed3gomb2.gridy = 9;
			contentPanel.add(negyed3gomb2, gbc_negyed3gomb2);
		}
		{
			selejt2 = new JTextField();
			selejt2.setBackground(Color.GREEN);
			selejt2.setText("2");
			GridBagConstraints gbc_selejt2 = new GridBagConstraints();
			gbc_selejt2.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt2.gridx = 6;
			gbc_selejt2.gridy = 9;
			contentPanel.add(selejt2, gbc_selejt2);
			selejt2.setColumns(10);
		}
		{
			selejt2gomb1 = new JToggleButton("");
			selejt2gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt2gomb1 = new GridBagConstraints();
			gbc_selejt2gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt2gomb1.gridx = 7;
			gbc_selejt2gomb1.gridy = 9;
			contentPanel.add(selejt2gomb1, gbc_selejt2gomb1);
		}
		{
			selejt2gomb2 = new JToggleButton("");
			selejt2gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt2gomb2 = new GridBagConstraints();
			gbc_selejt2gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt2gomb2.gridx = 8;
			gbc_selejt2gomb2.gridy = 9;
			contentPanel.add(selejt2gomb2, gbc_selejt2gomb2);
		}
		{
			selejt3 = new JTextField();
			selejt3.setText("3");
			GridBagConstraints gbc_selejt3 = new GridBagConstraints();
			gbc_selejt3.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt3.insets = new Insets(0, 0, 5, 5);
			gbc_selejt3.gridx = 0;
			gbc_selejt3.gridy = 10;
			contentPanel.add(selejt3, gbc_selejt3);
			selejt3.setColumns(10);
		}
		{
			selejt3gomb1 = new JToggleButton("");
			selejt3gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt3gomb1 = new GridBagConstraints();
			gbc_selejt3gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt3gomb1.gridx = 1;
			gbc_selejt3gomb1.gridy = 10;
			contentPanel.add(selejt3gomb1, gbc_selejt3gomb1);
		}
		{
			selejt3gomb2 = new JToggleButton("");
			selejt3gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt3gomb2 = new GridBagConstraints();
			gbc_selejt3gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt3gomb2.gridx = 2;
			gbc_selejt3gomb2.gridy = 10;
			contentPanel.add(selejt3gomb2, gbc_selejt3gomb2);
		}
		{
			donto2 = new JTextField();
			GridBagConstraints gbc_donto2 = new GridBagConstraints();
			gbc_donto2.fill = GridBagConstraints.HORIZONTAL;
			gbc_donto2.insets = new Insets(0, 0, 5, 5);
			gbc_donto2.gridx = 9;
			gbc_donto2.gridy = 10;
			contentPanel.add(donto2, gbc_donto2);
			donto2.setColumns(10);
		}
		{
			donto2gomb1 = new JToggleButton("");
			donto2gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_donto2gomb1 = new GridBagConstraints();
			gbc_donto2gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_donto2gomb1.gridx = 10;
			gbc_donto2gomb1.gridy = 10;
			contentPanel.add(donto2gomb1, gbc_donto2gomb1);
		}
		{
			donto2gomb2 = new JToggleButton("");
			donto2gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_donto2gomb2 = new GridBagConstraints();
			gbc_donto2gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_donto2gomb2.gridx = 11;
			gbc_donto2gomb2.gridy = 10;
			contentPanel.add(donto2gomb2, gbc_donto2gomb2);
		}
		{
			elodonto2 = new JTextField();
			GridBagConstraints gbc_elodonto2 = new GridBagConstraints();
			gbc_elodonto2.fill = GridBagConstraints.HORIZONTAL;
			gbc_elodonto2.insets = new Insets(0, 0, 5, 5);
			gbc_elodonto2.gridx = 6;
			gbc_elodonto2.gridy = 11;
			contentPanel.add(elodonto2, gbc_elodonto2);
			elodonto2.setColumns(10);
		}
		{
			elodonto2gomb1 = new JToggleButton("");
			elodonto2gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_elodonto2gomb1 = new GridBagConstraints();
			gbc_elodonto2gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_elodonto2gomb1.gridx = 7;
			gbc_elodonto2gomb1.gridy = 11;
			contentPanel.add(elodonto2gomb1, gbc_elodonto2gomb1);
		}
		{
			elodonto2gomb2 = new JToggleButton("");
			elodonto2gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_elodonto2gomb2 = new GridBagConstraints();
			gbc_elodonto2gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_elodonto2gomb2.gridx = 8;
			gbc_elodonto2gomb2.gridy = 11;
			contentPanel.add(elodonto2gomb2, gbc_elodonto2gomb2);
		}
		{
			selejt5 = new JTextField();
			selejt5.setText("5");
			GridBagConstraints gbc_selejt5 = new GridBagConstraints();
			gbc_selejt5.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt5.anchor = GridBagConstraints.NORTH;
			gbc_selejt5.insets = new Insets(0, 0, 5, 5);
			gbc_selejt5.gridx = 0;
			gbc_selejt5.gridy = 12;
			contentPanel.add(selejt5, gbc_selejt5);
			selejt5.setColumns(10);
		}
		{
			selejt5gomb1 = new JToggleButton("");
			selejt5gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt5gomb1 = new GridBagConstraints();
			gbc_selejt5gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt5gomb1.gridx = 1;
			gbc_selejt5gomb1.gridy = 12;
			contentPanel.add(selejt5gomb1, gbc_selejt5gomb1);
		}
		{
			selejt5gomb2 = new JToggleButton("");
			selejt5gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt5gomb2 = new GridBagConstraints();
			gbc_selejt5gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt5gomb2.gridx = 2;
			gbc_selejt5gomb2.gridy = 12;
			contentPanel.add(selejt5gomb2, gbc_selejt5gomb2);
		}
		{
			donto3 = new JTextField();
			GridBagConstraints gbc_donto3 = new GridBagConstraints();
			gbc_donto3.fill = GridBagConstraints.HORIZONTAL;
			gbc_donto3.insets = new Insets(0, 0, 5, 5);
			gbc_donto3.gridx = 9;
			gbc_donto3.gridy = 12;
			contentPanel.add(donto3, gbc_donto3);
			donto3.setColumns(10);
		}
		{
			donto3gomb1 = new JToggleButton("");
			donto3gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_donto3gomb1 = new GridBagConstraints();
			gbc_donto3gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_donto3gomb1.gridx = 10;
			gbc_donto3gomb1.gridy = 12;
			contentPanel.add(donto3gomb1, gbc_donto3gomb1);
		}
		{
			donto3gomb2 = new JToggleButton("");
			donto3gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_donto3gomb2 = new GridBagConstraints();
			gbc_donto3gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_donto3gomb2.gridx = 11;
			gbc_donto3gomb2.gridy = 12;
			contentPanel.add(donto3gomb2, gbc_donto3gomb2);
		}
		{
			negyed4 = new JTextField();
			GridBagConstraints gbc_negyed4 = new GridBagConstraints();
			gbc_negyed4.fill = GridBagConstraints.HORIZONTAL;
			gbc_negyed4.insets = new Insets(0, 0, 5, 5);
			gbc_negyed4.gridx = 3;
			gbc_negyed4.gridy = 13;
			contentPanel.add(negyed4, gbc_negyed4);
			negyed4.setColumns(10);
		}
		{
			negyed4gomb1 = new JToggleButton("");
			negyed4gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_negyed4gomb1 = new GridBagConstraints();
			gbc_negyed4gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_negyed4gomb1.gridx = 4;
			gbc_negyed4gomb1.gridy = 13;
			contentPanel.add(negyed4gomb1, gbc_negyed4gomb1);
		}
		{
			negyed4gomb2 = new JToggleButton("");
			negyed4gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_negyed4gomb2 = new GridBagConstraints();
			gbc_negyed4gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_negyed4gomb2.gridx = 5;
			gbc_negyed4gomb2.gridy = 13;
			contentPanel.add(negyed4gomb2, gbc_negyed4gomb2);
		}
		{
			harmadik = new JTextField();
			GridBagConstraints gbc_harmadik = new GridBagConstraints();
			gbc_harmadik.fill = GridBagConstraints.HORIZONTAL;
			gbc_harmadik.insets = new Insets(0, 0, 5, 0);
			gbc_harmadik.gridx = 12;
			gbc_harmadik.gridy = 13;
			contentPanel.add(harmadik, gbc_harmadik);
			harmadik.setColumns(10);
		}
		{
			selejt8 = new JTextField();
			selejt8.setText("8");
			GridBagConstraints gbc_selejt8 = new GridBagConstraints();
			gbc_selejt8.fill = GridBagConstraints.HORIZONTAL;
			gbc_selejt8.insets = new Insets(0, 0, 5, 5);
			gbc_selejt8.gridx = 0;
			gbc_selejt8.gridy = 14;
			contentPanel.add(selejt8, gbc_selejt8);
			selejt8.setColumns(10);
		}
		{
			selejt8gomb1 = new JToggleButton("");
			selejt8gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt8gomb1 = new GridBagConstraints();
			gbc_selejt8gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_selejt8gomb1.gridx = 1;
			gbc_selejt8gomb1.gridy = 14;
			contentPanel.add(selejt8gomb1, gbc_selejt8gomb1);
		}
		{
			selejt8gomb2 = new JToggleButton("");
			selejt8gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_selejt8gomb2 = new GridBagConstraints();
			gbc_selejt8gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_selejt8gomb2.gridx = 2;
			gbc_selejt8gomb2.gridy = 14;
			contentPanel.add(selejt8gomb2, gbc_selejt8gomb2);
		}
		{
			donto4 = new JTextField();
			GridBagConstraints gbc_donto4 = new GridBagConstraints();
			gbc_donto4.fill = GridBagConstraints.HORIZONTAL;
			gbc_donto4.insets = new Insets(0, 0, 5, 5);
			gbc_donto4.gridx = 9;
			gbc_donto4.gridy = 14;
			contentPanel.add(donto4, gbc_donto4);
			donto4.setColumns(10);
		}
		{
			donto4gomb1 = new JToggleButton("");
			donto4gomb1.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_donto4gomb1 = new GridBagConstraints();
			gbc_donto4gomb1.insets = new Insets(0, 0, 5, 5);
			gbc_donto4gomb1.gridx = 10;
			gbc_donto4gomb1.gridy = 14;
			contentPanel.add(donto4gomb1, gbc_donto4gomb1);
		}
		{
			donto4gomb2 = new JToggleButton("");
			donto4gomb2.setPreferredSize(new Dimension(10, 22));
			GridBagConstraints gbc_donto4gomb2 = new GridBagConstraints();
			gbc_donto4gomb2.insets = new Insets(0, 0, 5, 5);
			gbc_donto4gomb2.gridx = 11;
			gbc_donto4gomb2.gridy = 14;
			contentPanel.add(donto4gomb2, gbc_donto4gomb2);
		}
		
		feltolt(tipus);
	}
}
