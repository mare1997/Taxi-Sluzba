package gui.dodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.prikaz.PrikazTaksiSluzba;
import net.miginfocom.swing.MigLayout;
import taksi_sluzba.TaksiSluzba;
import utils.Snimanje;
import utils.Tools;

public class TaksiSluzbaIzmena extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblPIB;
	private JTextField txtPIB;
	private JLabel lblNaziv;
	private JTextField txtNaziv;
	private JLabel lblAdresa;
	private JTextField txtAdresa;
	private JButton btnOk;
	private JButton btnCancel;

	private TaksiSluzba taksiSluzba;

	public TaksiSluzbaIzmena(TaksiSluzba ts) {
		this.taksiSluzba = ts;
		setTitle("Taski sluzba - Izmena");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		initPrikaz();
		initAkcija();
		pack();
		setLocationRelativeTo(null);
	}

	private void initPrikaz() {

		MigLayout layout = new MigLayout("wrap 1", "[]", "[][][][][][][]");
		setLayout(layout);

		lblPIB = new JLabel("PIB");
		txtPIB = new JTextField(20);
		lblNaziv = new JLabel("Naziv");
		txtNaziv = new JTextField(20);
		lblAdresa = new JLabel("Adresa");
		txtAdresa = new JTextField(20);
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");

		this.getRootPane().setDefaultButton(btnOk);

		txtPIB.setText(Integer.toString(this.taksiSluzba.getPib()));
		txtNaziv.setText(this.taksiSluzba.getNaziv());
		txtAdresa.setText(this.taksiSluzba.getAdresa());

		add(lblPIB);
		add(txtPIB);
		add(lblNaziv);
		add(txtNaziv);
		add(lblAdresa);
		add(txtAdresa);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}

	private void initAkcija() {

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtPIB.getText().equals("") || txtNaziv.getText().equals("") || txtAdresa.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke");

				} else {
					if (Tools.isNumeric(txtPIB.getText())) {
						taksiSluzba.setPib(Integer.parseInt(txtPIB.getText()));
						taksiSluzba.setNaziv(txtNaziv.getText());
						taksiSluzba.setAdresa(txtAdresa.getText());
						Snimanje.snimiSve(TaksiSluzbaIzmena.this.taksiSluzba);
						setVisible(false);
						PrikazTaksiSluzba pts = new PrikazTaksiSluzba(taksiSluzba);
						pts.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Uneli ste slovo za PIB broj");
					}
				}

			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int odgovor = JOptionPane.showConfirmDialog(null, "Da li zelite da nastavite", "Potvrda",
						JOptionPane.YES_NO_OPTION);
				if (odgovor == JOptionPane.YES_OPTION) {
					setVisible(false);
					dispose();
				}

			}
		});
	}
}
