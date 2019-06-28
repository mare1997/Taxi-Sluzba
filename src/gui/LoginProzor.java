package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import gui.prikaz.PrikazAutomobila;
import gui.prikaz.PrikazDispecera;
import gui.prikaz.PrikazMusterije;
import gui.prikaz.PrikazTaksiSluzba;
import gui.prikaz.PrikazVozaca;
import gui.prikaz.PrikazVoznjiDispecer;
import taksi_sluzba.TaksiSluzba;

public class GlavniProzorDispecer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar glavniMeni;
	private JMenu taksiSluzbaMeni;
	private JMenuItem taksiSluzbaItem;
	private JMenu zaposleniMeni;
	private JMenuItem dispeceriItem;
	private JMenuItem vozaciItem;
	private JMenu musterijeMeni;
	private JMenuItem musterijeItem;
	private JMenu automobiliMeni;
	private JMenuItem automobiliItem;
	private JMenu voznjaMeni;
	private JMenuItem voznjeItem;
	private TaksiSluzba taksiSluzba;
	private JMenu pomocMeni;
	private JMenuItem infoItem;
	private JMenuItem odjavaItem;

	public GlavniProzorDispecer(TaksiSluzba taksiSluzba) {
		this.taksiSluzba = taksiSluzba;
		setTitle("Taksi sluzba-" + this.taksiSluzba.getNaziv() + "  "
				+ taksiSluzba.getUlogovan().getClass().getSimpleName() + ": " + taksiSluzba.getUlogovan().getIme() + " "
				+ taksiSluzba.getUlogovan().getPrezime());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initMeni();
		initAkcija();

	}

	private void initAkcija() {

		taksiSluzbaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazTaksiSluzba pts = new PrikazTaksiSluzba(taksiSluzba);
				pts.setVisible(true);

			}
		});

		dispeceriItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazDispecera pd = new PrikazDispecera(taksiSluzba);
				pd.setVisible(true);

			}
		});

		vozaciItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazVozaca pv = new PrikazVozaca(taksiSluzba);
				pv.setVisible(true);
			}
		});

		musterijeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazMusterije pm = new PrikazMusterije(taksiSluzba);
				pm.setVisible(true);

			}
		});

		automobiliItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazAutomobila pa = new PrikazAutomobila(taksiSluzba);
				pa.setVisible(true);

			}
		});

		voznjeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazVoznjiDispecer pvD = new PrikazVoznjiDispecer(taksiSluzba);
				pvD.setVisible(true);
			}
		});

		infoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Taksi sluzba v1.0 \nSandra Stojanovic SF10/2016", "Info",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

		odjavaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				taksiSluzba.setUlogovan(null);
				LoginProzor lg = new LoginProzor(taksiSluzba);
				lg.setVisible(true);
				GlavniProzorDispecer.this.setVisible(false);
				GlavniProzorDispecer.this.dispose();

			}
		});
	}

	private void initMeni() {

		this.glavniMeni = new JMenuBar();
		this.taksiSluzbaMeni = new JMenu("Taksi sluzba");
		this.taksiSluzbaItem = new JMenuItem("Prikaz taksi sluzbe");
		this.zaposleniMeni = new JMenu("Zaposleni");
		this.dispeceriItem = new JMenuItem("Dispeceri");
		this.vozaciItem = new JMenuItem("Vozaci");
		this.musterijeMeni = new JMenu("Musterije");
		this.musterijeItem = new JMenuItem("Musterije");
		this.automobiliMeni = new JMenu("Automobili");
		this.automobiliItem = new JMenuItem("Automobili");
		this.voznjaMeni = new JMenu("Voznja");
		this.voznjeItem = new JMenuItem("Voznje");
		this.pomocMeni = new JMenu("Pomoc");
		this.infoItem = new JMenuItem("Info");
		this.odjavaItem = new JMenuItem("Odjava");

		this.taksiSluzbaMeni.add(taksiSluzbaItem);
		this.zaposleniMeni.add(dispeceriItem);
		this.zaposleniMeni.add(vozaciItem);
		this.musterijeMeni.add(musterijeItem);
		this.automobiliMeni.add(automobiliItem);
		this.voznjaMeni.add(voznjeItem);
		this.pomocMeni.add(infoItem);
		this.pomocMeni.add(odjavaItem);

		this.glavniMeni.add(taksiSluzbaMeni);
		this.glavniMeni.add(zaposleniMeni);
		this.glavniMeni.add(musterijeMeni);
		this.glavniMeni.add(automobiliMeni);
		this.glavniMeni.add(voznjaMeni);
		this.glavniMeni.add(pomocMeni);

		setJMenuBar(this.glavniMeni);

	}

}
