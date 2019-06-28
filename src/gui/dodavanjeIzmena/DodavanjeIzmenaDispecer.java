package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entiteti.osoba.Dispecer;
import entiteti.osoba.Musterija;
import entiteti.osoba.Osoba;
import entiteti.osoba.Vozac;
import net.miginfocom.swing.MigLayout;
import taksi_sluzba.TaksiSluzba;
import utils.Tools;

public class LoginProzor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblWelcome;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JButton btnPotvrdi;
	private JButton btnCancel;
	private TaksiSluzba taksiSluzba;

	public LoginProzor(TaksiSluzba ts) {
		this.taksiSluzba = ts;
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initAction();
		pack();
		setLocationRelativeTo(null);
	}

	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);
		this.lblWelcome = new JLabel("Dobrodosli, prijavite se.");
		this.lblUsername = new JLabel("Korisnicko ime");
		this.txtUsername = new JTextField(30);
		this.lblPassword = new JLabel("Lozinka");
		this.txtPassword = new JPasswordField(30);
		this.btnPotvrdi = new JButton("Potvrdi");
		this.btnCancel = new JButton("Odustani");

		this.getRootPane().setDefaultButton(btnPotvrdi);

		add(lblWelcome, "span 2");
		add(lblUsername, "span 2");
		add(txtUsername, "span 2");
		add(lblPassword, "span 2");
		add(txtPassword, "span 2");

		add(btnPotvrdi, "split 2");
		add(btnCancel);

	}

	private void initAction() {

		btnPotvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText().trim();
				String password = txtPassword.getText().trim();
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {

					Osoba osoba = Tools.login(LoginProzor.this.taksiSluzba, username, password);

					if (osoba != null) {
						if (osoba instanceof Musterija && !((Musterija) osoba).getMobapp()) {
							JOptionPane.showMessageDialog(null, "Nemate pristup aplikaciji", "Upozorenje",
									JOptionPane.WARNING_MESSAGE);
						} else {
							taksiSluzba.setUlogovan(osoba);
							LoginProzor.this.setVisible(false);
							LoginProzor.this.dispose();
							if (osoba instanceof Vozac || osoba instanceof Musterija) {
								GlavniProzorVM glVM = new GlavniProzorVM(taksiSluzba);
								glVM.setVisible(true);
							} else if (osoba instanceof Dispecer) {
								GlavniProzorDispecer gl = new GlavniProzorDispecer(taksiSluzba);
								gl.setVisible(true);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Korisnicko ime i/ili lozinka su pogresni", "Greska",
								JOptionPane.WARNING_MESSAGE);

					}
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.setVisible(false);
				LoginProzor.this.dispose();

			}
		});
	}
}
