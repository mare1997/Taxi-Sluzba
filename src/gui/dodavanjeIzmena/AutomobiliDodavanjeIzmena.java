package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import entiteti.osoba.Musterija;
import entiteti.osoba.Vozac;
import gui.prikaz.PrikazVoznjiMusterija;
import gui.prikaz.PrikazVoznjiVozac;
import taksi_sluzba.TaksiSluzba;

public class GlavniProzorVM extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuBar glavniMeniVozac;
	private JMenu voznjaMeni;
	private JMenuItem voznjeItem;
	private TaksiSluzba taksiSluzba;
	private JMenu pomocMeni;
	private JMenuItem infoItem;
	private JMenuItem odjavaItem;

	public GlavniProzorVM(TaksiSluzba taksiSluzba) {
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

		voznjeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (taksiSluzba.getUlogovan() instanceof Vozac) {
					PrikazVoznjiVozac pvV = new PrikazVoznjiVozac(taksiSluzba);
					pvV.setVisible(true);
				} else if (taksiSluzba.getUlogovan() instanceof Musterija) {
					PrikazVoznjiMusterija pvM = new PrikazVoznjiMusterija(taksiSluzba);
					pvM.setVisible(true);
				}

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
				GlavniProzorVM.this.setVisible(false);
				GlavniProzorVM.this.dispose();

			}
		});
	}

	private void initMeni() {

		this.glavniMeniVozac = new JMenuBar();
		this.voznjaMeni = new JMenu("Voznja");
		this.voznjeItem = new JMenuItem("Voznje");
		this.pomocMeni = new JMenu("Pomoc");
		this.infoItem = new JMenuItem("Info");
		this.odjavaItem = new JMenuItem("Odjava");

		this.glavniMeniVozac.add(voznjaMeni);
		this.glavniMeniVozac.add(pomocMeni);
		this.voznjaMeni.add(voznjeItem);
		this.pomocMeni.add(infoItem);
		this.pomocMeni.add(odjavaItem);

		setJMenuBar(this.glavniMeniVozac);
	}
}
