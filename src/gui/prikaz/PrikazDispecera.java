package gui.prikaz;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import taksi_sluzba.TaksiSluzba;

public abstract class Prikaz extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JToolBar mainToolbar;
	protected JButton btnPlus;
	protected JButton btnMinus;
	protected JButton btnChange;
	protected JTable tabela;
	protected int selektovanRed;
	protected JScrollPane tableScroll;
	private TaksiSluzba taksiSluzba;

	public Prikaz(TaksiSluzba taksiSluzba) {
		this.taksiSluzba = taksiSluzba;
		setSize(700, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initOpcijeTabele();
	}

	public JTable getTabela() {
		return tabela;

	}

	public void initOpcijeTabele() {
		this.mainToolbar = new JToolBar();
		ImageIcon iconPlus = new ImageIcon(getClass().getResource("/img/add.gif"));
		btnPlus = new JButton(iconPlus);
		this.mainToolbar.add(btnPlus);
		ImageIcon iconMinus = new ImageIcon(getClass().getResource("/img/remove.gif"));
		btnMinus = new JButton(iconMinus);
		this.mainToolbar.add(btnMinus);
		ImageIcon iconChange = new ImageIcon(getClass().getResource("/img/edit.gif"));
		btnChange = new JButton(iconChange);
		this.mainToolbar.add(btnChange);

		add(this.mainToolbar, BorderLayout.NORTH);

	}

}
