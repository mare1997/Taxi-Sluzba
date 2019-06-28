package gui.prikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import gui.dodavanjeIzmena.TaksiSluzbaIzmena;
import taksi_sluzba.TaksiSluzba;

public class PrikazTaksiSluzba extends Prikaz {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaksiSluzba taksiSluzba;

	public PrikazTaksiSluzba(TaksiSluzba taksiSluzba) {
		super(taksiSluzba);
		this.taksiSluzba = taksiSluzba;
		setTitle("Prikaz Taksi sluzbe");
		popuniTabelu(taksiSluzba);
		initAkcija();

	}

	private void popuniTabelu(TaksiSluzba ts) {

		String[] zaglavlje = { "PIB", "Naziv", "Adresa taksi sluzbe" };
		Object[][] podaci = new Object[1][3];
		podaci[0][0] = ts.getPib();
		podaci[0][1] = ts.getNaziv();
		podaci[0][2] = ts.getAdresa();

		DefaultTableModel model = new DefaultTableModel(podaci, zaglavlje);
		tabela = new JTable(model);
		tabela.setCellSelectionEnabled(false);
		tabela.setRowSelectionAllowed(true);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setDefaultEditor(Object.class, null);

		tableScroll = new JScrollPane(tabela);
		add(tableScroll, BorderLayout.CENTER);

	}

	private void initAkcija() {
		btnPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Trenutno nije moguce dodati novu taksi sluzbu");

			}
		});

		btnChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TaksiSluzbaIzmena tsi = new TaksiSluzbaIzmena(taksiSluzba);
				setVisible(false);
				tsi.setVisible(true);

			}
		});

		btnMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Trenutno nije moguce obriati taksi sluzbu");

			}
		});
	}

}
