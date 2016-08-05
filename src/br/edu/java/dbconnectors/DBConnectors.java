package br.edu.java.dbconnectors;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * DBConnector - Solução que contém os drivers e estruturas de conexão
 * necessárias para conectar o Java aos principais bancos de dados utilizados no
 * mercado. Estão disponíveis drivers para: Oracle, MySQL, Firebird e PostGreSQL
 * 
 * @author Rômulo Garcia
 * @since 26/03/2014
 * 
 */

public class DBConnectors extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3763902585359834981L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnOracle;
	private JRadioButton rdbtnMysql;
	private JRadioButton rdbtnPostgres;
	private JRadioButton rdbtnFirebird;
	private JLabel lblHost;
	private JLabel lblPorta;
	private JLabel lblUsurio;
	private JLabel lblSenha;
	private JTextField txtHost;
	private JTextField txtUsuario;
	private JPasswordField pwdSenha;
	private JLabel lblSelecioneO;
	private JSpinner spnPorta;
	private JLabel lblBase;
	private JPanel panel_1;
	private JTextField txtBase;
	private JButton btnSelecionarBase;

	/**
	 * Abre a tela de conexão.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DBConnectors frame = new DBConnectors();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a janela.
	 */
	public DBConnectors() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblSelecioneOBanco = new JLabel("Conexão ao Banco:");
		lblSelecioneOBanco.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSelecioneOBanco, BorderLayout.NORTH);

		JButton btnOk = new JButton("Conectar");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConstants.setHost(txtHost.getText());
				DBConstants.setPorta(Integer.parseInt(spnPorta.getValue().toString()));
				DBConstants.setUsuario(txtUsuario.getText());
				DBConstants.setSenha(String.copyValueOf(pwdSenha.getPassword()));
				DBConstants.setBase(txtBase.getText());
				if (rdbtnOracle.isSelected()) {
					new DBOracle();
				} else if (rdbtnMysql.isSelected()) {
					new DBMySQL();
				} else if (rdbtnPostgres.isSelected()) {
					new DBPostGres();
				} else {
					new BDFirebird();
				}
			}
		});
		contentPane.add(btnOk, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		lblHost = new JLabel("Host:");
		panel.add(lblHost, "2, 2, right, default");

		txtHost = new JTextField();
		panel.add(txtHost, "4, 2, fill, top");
		txtHost.setColumns(10);

		lblPorta = new JLabel("Porta:");
		lblPorta.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblPorta, "2, 4");

		spnPorta = new JSpinner(new SpinnerNumberModel(0, 0, 65535, 1));
		spnPorta.setEditor(new JSpinner.NumberEditor(spnPorta, "######"));
		panel.add(spnPorta, "4, 4");

		lblUsurio = new JLabel("Usuário:");
		panel.add(lblUsurio, "2, 6, right, default");

		txtUsuario = new JTextField();
		panel.add(txtUsuario, "4, 6, fill, top");
		txtUsuario.setColumns(10);

		lblSenha = new JLabel("Senha:");
		panel.add(lblSenha, "2, 8, right, default");

		pwdSenha = new JPasswordField();
		panel.add(pwdSenha, "4, 8, fill, default");

		lblBase = new JLabel("Base:");
		panel.add(lblBase, "2, 10, right, default");

		panel_1 = new JPanel();
		panel.add(panel_1, "4, 10");
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		txtBase = new JTextField();
		txtBase.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(txtBase);
		txtBase.setColumns(10);

		btnSelecionarBase = new JButton("Selecionar Base (Firebird)");
		btnSelecionarBase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfcSelecionar = new JFileChooser();
				FileNameExtensionFilter fnefExtensoes = new FileNameExtensionFilter(
						"Arquivos de banco Firefird (*.fdb)", "fdb");
				jfcSelecionar.setFileFilter(fnefExtensoes);
				jfcSelecionar.setMultiSelectionEnabled(false);
				jfcSelecionar.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfcSelecionar.showOpenDialog(null);
				txtBase.setText(jfcSelecionar.getSelectedFile().getAbsoluteFile().toString());
			}
		});
		panel_1.add(btnSelecionarBase);

		rdbtnOracle = new JRadioButton("Oracle");
		buttonGroup.add(rdbtnOracle);
		panel.add(rdbtnOracle, "4, 12");

		lblSelecioneO = new JLabel("Banco:");
		lblSelecioneO.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblSelecioneO, "2, 14");

		rdbtnMysql = new JRadioButton("MySQL");
		buttonGroup.add(rdbtnMysql);
		panel.add(rdbtnMysql, "4, 14");

		rdbtnPostgres = new JRadioButton("PostGres");
		buttonGroup.add(rdbtnPostgres);
		panel.add(rdbtnPostgres, "4, 16");

		rdbtnFirebird = new JRadioButton("Firebird");
		buttonGroup.add(rdbtnFirebird);
		panel.add(rdbtnFirebird, "4, 18");
		setVisible(true);
	}

}
