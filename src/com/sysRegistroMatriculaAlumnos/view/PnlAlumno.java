package com.sysRegistroMatriculaAlumnos.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;

public class PnlAlumno extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	// COLORES
	private static final Color C_PRIMARIO = LoginFrame.C_PRIMARIO;
	private static final Color C_HEADER = LoginFrame.C_HEADER;
	private static final Color C_FONDO = LoginFrame.C_FONDO;
	
	private JTextField textCodigoA;
	private JTextField textDNI;
	private JTextField teextNombre;
	private JTextField textApellido;
	private JTextField textEdad;
	private JTextField textCelular;
	private JTable table;


	// LABEL CONTADOR
		private JLabel lblContador;
		
	
	// GUI
	public PnlAlumno() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNORTH = new JPanel();
		panelNORTH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelNORTH.setPreferredSize(new Dimension(0, 300));
		panelNORTH.setBackground(Color.WHITE);
		
		
		TitledBorder titulo = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(C_PRIMARIO, 2),
				" Mantenedor de Alumnos ");

		titulo.setTitleFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setTitleColor(C_PRIMARIO);

		panelNORTH.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(10, 10, 10, 10),
						BorderFactory.createCompoundBorder(
								titulo,
								BorderFactory.createEmptyBorder(12, 14, 0, 14)
						)
				)
		);		add(panelNORTH, BorderLayout.NORTH);
		panelNORTH.setLayout(new GridLayout(1, 5, 16, 0));
		
		
		// COLUMNA 1
		JPanel panelColumna1 = new JPanel();
		panelColumna1.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna1);
		GridBagLayout gbl_panelColumna1 = new GridBagLayout();
		gbl_panelColumna1.columnWidths = new int[]{200, 0};
		gbl_panelColumna1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelColumna1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColumna1.setLayout(gbl_panelColumna1);
		
		// codigo
		JLabel lblCodigo = new JLabel("Codigo ");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.anchor = GridBagConstraints.WEST;
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 0);
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 0;
		panelColumna1.add(lblCodigo, gbc_lblCodigo);
		
		textCodigoA = new JTextField();
		textCodigoA.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textCodigoA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textCodigo = new GridBagConstraints();
		gbc_textCodigo.insets = new Insets(0, 0, 10, 0);
		gbc_textCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCodigo.gridx = 0;
		gbc_textCodigo.gridy = 1;
		panelColumna1.add(textCodigoA, gbc_textCodigo);
		textCodigoA.setColumns(10);
		
		// dni
		JLabel lblDNI = new JLabel("DNI ");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblDNI = new GridBagConstraints();
		gbc_lblDNI.anchor = GridBagConstraints.WEST;
		gbc_lblDNI.insets = new Insets(0, 0, 5, 0);
		gbc_lblDNI.gridx = 0;
		gbc_lblDNI.gridy = 2;
		panelColumna1.add(lblDNI, gbc_lblDNI);
		
		textDNI = new JTextField();
		textDNI.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textDNI = new GridBagConstraints();
		gbc_textDNI.insets = new Insets(0, 0, 5, 0);
		gbc_textDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDNI.gridx = 0;
		gbc_textDNI.gridy = 3;
		panelColumna1.add(textDNI, gbc_textDNI);
		textDNI.setColumns(10);
		
		
		// boton listar
		JButton btnListar = new JButton("Listar todos los registros");
		btnListar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnListar.setBackground(new Color(0x37474F));
		btnListar.setForeground(Color.WHITE);
		btnListar.setFocusPainted(false);
		btnListar.addActionListener(this);
		GridBagConstraints gbc_btnListar = new GridBagConstraints();
		gbc_btnListar.insets = new Insets(30, 0, 0, 0);
		gbc_btnListar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListar.gridx = 0;
		gbc_btnListar.gridy = 4;
		panelColumna1.add(btnListar, gbc_btnListar);
		
		
		// COLUMNA 2
		JPanel panelColumna2 = new JPanel();
		panelColumna2.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna2);
		GridBagLayout gbl_panelColumna2 = new GridBagLayout();
		gbl_panelColumna2.columnWidths = new int[]{0, 0};
		gbl_panelColumna2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelColumna2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColumna2.setLayout(gbl_panelColumna2);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panelColumna2.add(lblNombre, gbc_lblNombre);
		
		teextNombre = new JTextField();
		teextNombre.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		teextNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_teextNombre = new GridBagConstraints();
		gbc_teextNombre.insets = new Insets(0, 0, 10, 0);
		gbc_teextNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_teextNombre.gridx = 0;
		gbc_teextNombre.gridy = 1;
		panelColumna2.add(teextNombre, gbc_teextNombre);
		teextNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.WEST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 0);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 2;
		panelColumna2.add(lblApellido, gbc_lblApellido);
		
		textApellido = new JTextField();
		textApellido.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.insets = new Insets(0, 0, 5, 0);
		gbc_textApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido.gridx = 0;
		gbc_textApellido.gridy = 3;
		panelColumna2.add(textApellido, gbc_textApellido);
		textApellido.setColumns(10);
		
		JLabel lblContador = new JLabel("Total de alumnos: 0");
		lblContador.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(13, 25, 13, 0)));
		lblContador.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContador.setBackground(new Color(0x37474F));
		lblContador.setForeground(Color.WHITE);
		lblContador.setOpaque(true);
		GridBagConstraints gbc_lblContador = new GridBagConstraints();
		gbc_lblContador.fill = GridBagConstraints.BOTH;
		gbc_lblContador.insets = new Insets(30, 0, 0, 0);
		gbc_lblContador.gridx = 0;
		gbc_lblContador.gridy = 4;
		panelColumna2.add(lblContador, gbc_lblContador);
		
		ImageIcon iconcont = new ImageIcon(
				getClass().getResource("/icons/book_white_24.png")
		);
		lblContador.setIcon(iconcont);
		
		
		
		// COLUMNA 3
		JPanel panelColumna3 = new JPanel();
		panelColumna3.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna3);
		GridBagLayout gbl_panelColumna3 = new GridBagLayout();
		gbl_panelColumna3.columnWidths = new int[]{0, 0};
		gbl_panelColumna3.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelColumna3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColumna3.setLayout(gbl_panelColumna3);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblEdad = new GridBagConstraints();
		gbc_lblEdad.anchor = GridBagConstraints.WEST;
		gbc_lblEdad.insets = new Insets(0, 0, 5, 0);
		gbc_lblEdad.gridx = 0;
		gbc_lblEdad.gridy = 0;
		panelColumna3.add(lblEdad, gbc_lblEdad);
		
		textEdad = new JTextField();
		textEdad.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textEdad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textEdad = new GridBagConstraints();
		gbc_textEdad.insets = new Insets(0, 0, 10, 0);
		gbc_textEdad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEdad.gridx = 0;
		gbc_textEdad.gridy = 1;
		panelColumna3.add(textEdad, gbc_textEdad);
		textEdad.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCelular = new GridBagConstraints();
		gbc_lblCelular.anchor = GridBagConstraints.WEST;
		gbc_lblCelular.insets = new Insets(0, 0, 5, 0);
		gbc_lblCelular.gridx = 0;
		gbc_lblCelular.gridy = 2;
		panelColumna3.add(lblCelular, gbc_lblCelular);
		
		textCelular = new JTextField();
		textCelular.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textCelular.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textCelular = new GridBagConstraints();
		gbc_textCelular.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCelular.gridx = 0;
		gbc_textCelular.gridy = 3;
		panelColumna3.add(textCelular, gbc_textCelular);
		textCelular.setColumns(10);
		
		
		// COLUMNA 4
		JPanel panelColumna4 = new JPanel();
		panelColumna4.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna4);
		GridBagLayout gbl_panelColumna4 = new GridBagLayout();
		gbl_panelColumna4.columnWidths = new int[]{0, 0};
		gbl_panelColumna4.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelColumna4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna4.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColumna4.setLayout(gbl_panelColumna4);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.anchor = GridBagConstraints.WEST;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 0);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 0;
		panelColumna4.add(lblEstado, gbc_lblEstado);
		
		JComboBox<Object> comboBoxEstado = new JComboBox<>();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"0"}));
		comboBoxEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_comboBoxEstado = new GridBagConstraints();
		gbc_comboBoxEstado.insets = new Insets(0, 0, 23, 0);
		gbc_comboBoxEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstado.gridx = 0;
		gbc_comboBoxEstado.gridy = 1;
		panelColumna4.add(comboBoxEstado, gbc_comboBoxEstado);
		
		// boton cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBackground(new Color(0x546E7A));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFocusPainted(false);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 2;
		panelColumna4.add(btnCancelar, gbc_btnCancelar);
		
		
		// COLUMNA 5
		JPanel panelColumna5 = new JPanel();
		panelColumna5.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna5);
		GridBagLayout gbl_panelColumna5 = new GridBagLayout();
		gbl_panelColumna5.columnWidths = new int[]{0, 0};
		gbl_panelColumna5.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelColumna5.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColumna5.setLayout(gbl_panelColumna5);
		
		 //	boton Adicionar
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdicionar.setBackground(C_PRIMARIO);
		btnAdicionar.setForeground(Color.WHITE);
		btnAdicionar.setFocusPainted(false);
		GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
		gbc_btnAdicionar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdicionar.insets = new Insets(0, 0, 10, 0);
		gbc_btnAdicionar.gridx = 0;
		gbc_btnAdicionar.gridy = 0;
		panelColumna5.add(btnAdicionar, gbc_btnAdicionar);
		
		// boton consultar
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConsultar.setBackground(new Color(0x1B5E20));
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setFocusPainted(false);
		GridBagConstraints gbc_btnConsultar = new GridBagConstraints();
		gbc_btnConsultar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConsultar.insets = new Insets(0, 0, 10, 0);
		gbc_btnConsultar.gridx = 0;
		gbc_btnConsultar.gridy = 1;
		panelColumna5.add(btnConsultar, gbc_btnConsultar);
		
		// boton modificar
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModificar.setBackground(new Color(230, 81, 0));
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFocusPainted(false);
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModificar.insets = new Insets(0, 0, 10, 0);
		gbc_btnModificar.gridx = 0;
		gbc_btnModificar.gridy = 2;
		panelColumna5.add(btnModificar, gbc_btnModificar);
		
		
		// boton eliminar
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEliminar.setBackground(new Color(220, 20, 60));
		btnEliminar.setForeground(Color.WHITE);

		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.gridx = 0;
		gbc_btnEliminar.gridy = 3;
		panelColumna5.add(btnEliminar, gbc_btnEliminar);
		btnEliminar.setFocusPainted(false);
		JScrollPane jscrollPane = new JScrollPane();
		jscrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		jscrollPane.setBackground(C_FONDO);
		add(jscrollPane, BorderLayout.CENTER);
		
		// ICONS
		
		// ICONOS DE LOS BOTONES
		ImageIcon iconListar = new ImageIcon(
				getClass().getResource("/icons/list_alt_24.png")
		);
		btnListar.setIcon(iconListar);


		ImageIcon iconGuardar = new ImageIcon(
				getClass().getResource("/icons/save_24.png")
		);
		btnAdicionar.setIcon(iconGuardar);


		ImageIcon iconEliminar = new ImageIcon(
				getClass().getResource("/icons/delete_24.png")
		);
		btnEliminar.setIcon(iconEliminar);


		ImageIcon iconBuscar = new ImageIcon(
				getClass().getResource("/icons/search_24.png")
		);
		btnConsultar.setIcon(iconBuscar);


		ImageIcon iconEditar = new ImageIcon(
				getClass().getResource("/icons/edit_document_24.png")
		);
		btnModificar.setIcon(iconEditar);


		ImageIcon iconCancelar = new ImageIcon(
				getClass().getResource("/icons/cancel_24.png")
		);
		btnCancelar.setIcon(iconCancelar);



		// TABLA
		table = new JTable();
		table.setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION
		);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(C_HEADER);
		table.getTableHeader().setForeground(Color.WHITE);
		
		table.getTableHeader().setPreferredSize(new Dimension(0, 35));
		table.setRowHeight(25);
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(22);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", 
				"Nombres", 
				"Apellidos", 
				"DNI", 
				"Edad", 
				"Nro Celular",
				"Estado"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class,
				String.class,
				String.class,
				String.class, 
				Integer.class, 
				String.class,
				Integer.class,
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jscrollPane.setViewportView(table);

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
