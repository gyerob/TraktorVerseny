package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.Dimension;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7413022858513247454L;
	private JPanel contentPane;
	private ListaPanel listaPanel;
	private SzlalomPanel szlalomPanel;
	private GyorsulasPanel gyorsulasPanel;
	private PotkocsisPanel potkocsisPanel;
	private JTabbedPane menu;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setMinimumSize(new Dimension(450, 320));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		menu = new JTabbedPane(JTabbedPane.TOP);
		menu.setMinimumSize(new Dimension(420, 300));
		contentPane.add(menu, BorderLayout.CENTER);
		
		listaPanel = new ListaPanel();
		potkocsisPanel = new PotkocsisPanel();
		szlalomPanel = new SzlalomPanel();
		gyorsulasPanel = new GyorsulasPanel();
		
		menu.addTab("Lista", listaPanel);
		menu.addTab("Pótkocsis Tolatás", potkocsisPanel);
		menu.addTab("Szlalom", szlalomPanel);
		menu.addTab("Gyorsulas", gyorsulasPanel);
	}
}
