/**
Descripción:clase PnlAlumno.java, es la clase que representa el panel de gestión de alumnos en el sistema de registro y matricula de alumnos. Contiene los componentes gráficos y la lógica para interactuar con los datos de los alumnos.
@author America Soto Campos
@version 1.0
@since 7/10/2026
*/

package com.sysRegistroMatriculaAlumnos.view;

import com.sysRegistroMatriculaAlumnos.dao.AlumnoDAO;
import com.sysRegistroMatriculaAlumnos.entity.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PnlAlumno extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	// MODO OPERACION
	private int modoOperacion = 0;

	private static final int NINGUNO = 0;
	private static final int ADICIONAR = 1;
	private static final int BUSCAR = 2;
	private static final int EDITAR = 3;
	private static final int ELIMINAR = 4;
	
	// COLORES - MATERIAL DESING
	private static final Color C_PRIMARIO = LoginFrame.C_PRIMARIO;
	private static final Color C_HEADER = LoginFrame.C_HEADER;
	private static final Color C_FONDO = LoginFrame.C_FONDO;

	private JTextField textCodigoA, textDNI, textNombres, textApellidos, textEdad, textCelular;
	private JComboBox<String> comboBoxEstado;
	
	private JLabel lblContador;

	
	private JButton btnListar, btnGuardar, btnEliminar;
	private JButton btnBuscar, btnEditar, btnCancelar,btnAceptar,btnVolver;  	


	private JTable table;
	private DefaultTableModel model;

	//=========================================================
	//================= INICIO CONSTRUCTOR GUI ================
	//=========================================================
	public PnlAlumno() {
		setLayout(new BorderLayout(0, 0));
		
		setBackground(C_FONDO);

		JPanel panelNORTH = new JPanel();
		panelNORTH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelNORTH.setPreferredSize(new Dimension(0, 300));
		panelNORTH.setBackground(Color.WHITE);

		TitledBorder titulo = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(C_PRIMARIO, 2),
				" Mantenedor de Alumnos ");

		titulo.setTitleFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setTitleColor(C_PRIMARIO);

		panelNORTH.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createCompoundBorder(titulo, BorderFactory.createEmptyBorder(12, 14, 0, 14))));
		add(panelNORTH, BorderLayout.NORTH);
		panelNORTH.setLayout(new GridLayout(1, 5, 16, 0));
		
		
		//===============================================
		// COLUMNA 1
		//================================================
		JPanel panelColumna1 = new JPanel();
		panelColumna1.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna1);
		GridBagLayout gbl_panelColumna1 = new GridBagLayout();
		gbl_panelColumna1.columnWidths = new int[] { 200, 0 };
		gbl_panelColumna1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelColumna1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelColumna1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelColumna1.setLayout(gbl_panelColumna1);

		// codigo
		JLabel lblCodigo = new JLabel("Codigo *");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.anchor = GridBagConstraints.WEST;
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 0);
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 0;
		panelColumna1.add(lblCodigo, gbc_lblCodigo);

		textCodigoA = new JTextField();
		textCodigoA.setText("" + dao.codigoCorrelativo());
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
		JLabel lblDNI = new JLabel("DNI *");
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
		btnListar = new JButton("Listar todos los registros");
		btnListar.setHorizontalAlignment(SwingConstants.LEFT);
		btnListar.addActionListener(this);
		btnListar.setBorder(new EmptyBorder(14, 20, 14, 0));
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnListar.setBackground(new Color(0x37474F));
		btnListar.setForeground(Color.WHITE);
		btnListar.setFocusPainted(false);
		GridBagConstraints gbc_btnListar = new GridBagConstraints();
		gbc_btnListar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListar.insets = new Insets(30, 0, 0, 0);
		gbc_btnListar.gridx = 0;
		gbc_btnListar.gridy = 4;
		panelColumna1.add(btnListar, gbc_btnListar);

		//===============================================
		// COLUMNA 2
		//===============================================
		JPanel panelColumna2 = new JPanel();
		panelColumna2.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna2);
		GridBagLayout gbl_panelColumna2 = new GridBagLayout();
		gbl_panelColumna2.columnWidths = new int[] { 0, 0 };
		gbl_panelColumna2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelColumna2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelColumna2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelColumna2.setLayout(gbl_panelColumna2);

		JLabel lblNombre = new JLabel("Nombre*");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panelColumna2.add(lblNombre, gbc_lblNombre);

		textNombres = new JTextField();
		textNombres.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textNombres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_teextNombre = new GridBagConstraints();
		gbc_teextNombre.insets = new Insets(0, 0, 10, 0);
		gbc_teextNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_teextNombre.gridx = 0;
		gbc_teextNombre.gridy = 1;
		panelColumna2.add(textNombres, gbc_teextNombre);
		textNombres.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido*");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.WEST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 0);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 2;
		panelColumna2.add(lblApellido, gbc_lblApellido);

		textApellidos = new JTextField();
		textApellidos.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textApellidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.insets = new Insets(0, 0, 5, 0);
		gbc_textApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido.gridx = 0;
		gbc_textApellido.gridy = 3;
		panelColumna2.add(textApellidos, gbc_textApellido);
		textApellidos.setColumns(10);

		lblContador = new JLabel("Total de alumnos: 0");
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

		lblContador.setText("Total de alumnos: " + dao.tamaño());

		ImageIcon iconcont = new ImageIcon(getClass().getResource("/icons/people__24_FFFFFF.png"));
		lblContador.setIcon(iconcont);

		//===========================================================
		// COLUMNA 
		//=========================================================
		JPanel panelColumna3 = new JPanel();
		panelColumna3.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna3);
		GridBagLayout gbl_panelColumna3 = new GridBagLayout();
		gbl_panelColumna3.columnWidths = new int[] { 0, 0 };
		gbl_panelColumna3.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelColumna3.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelColumna3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelColumna3.setLayout(gbl_panelColumna3);

		JLabel lblEdad = new JLabel("Edad*");
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

		JLabel lblCelular = new JLabel("Celular*");
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
		

		//=======================================
		// COLUMNA 4
		//=======================================
		JPanel panelColumna4 = new JPanel();
		panelColumna4.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna4);
		GridBagLayout gbl_panelColumna4 = new GridBagLayout();
		gbl_panelColumna4.columnWidths = new int[] { 0, 0 };
		gbl_panelColumna4.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelColumna4.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelColumna4.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelColumna4.setLayout(gbl_panelColumna4);

		JLabel lblEstado = new JLabel("Estado registrado por defecto");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.anchor = GridBagConstraints.WEST;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 0);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 0;
		panelColumna4.add(lblEstado, gbc_lblEstado);

		comboBoxEstado = new JComboBox<>();
		comboBoxEstado.setBorder(new EmptyBorder(0, 0, 0,0));
		comboBoxEstado.setEditable(false);
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Registrado", "Matriculado", "Retirado" }));
		comboBoxEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_comboBoxEstado = new GridBagConstraints();
		gbc_comboBoxEstado.insets = new Insets(0, 0, 10, 0);
		gbc_comboBoxEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstado.gridx = 0;
		gbc_comboBoxEstado.gridy = 1;
		panelColumna4.add(comboBoxEstado, gbc_comboBoxEstado);

		
		// boton cancelar
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBackground(new Color(0x546E7A));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFocusPainted(false);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 0);
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 3;
		panelColumna4.add(btnCancelar, gbc_btnCancelar);

		// boton aceptar
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBorder(new EmptyBorder(16, 0, 16, 0));
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBackground(new Color(22, 160, 133));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFocusPainted(false);
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceptar.insets = new Insets(10, 0, 10, 0);
		gbc_btnAceptar.gridx = 0;
		gbc_btnAceptar.gridy = 4;
		panelColumna4.add(btnAceptar, gbc_btnAceptar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setBorder(new EmptyBorder(16, 0, 16, 0));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVolver.setBackground(new Color(59, 130, 196));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 10, 0);
		gbc_btnVolver.gridx = 0;
		gbc_btnVolver.gridy = 5;
		gbc_btnVolver.fill = GridBagConstraints.HORIZONTAL;
		panelColumna4.add(btnVolver, gbc_btnVolver);

//		
//		btnGuardar = new JButton("Adicionar");
//		btnGuardar.addActionListener(this);
//		btnGuardar.setBorder(new EmptyBorder(12, 0, 12, 0));
//		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		btnGuardar.setBackground(C_PRIMARIO);
//		btnGuardar.setForeground(Color.WHITE);
//		btnGuardar.setFocusPainted(false);
//		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
//		gbc_btnGuardar.fill = GridBagConstraints.HORIZONTAL;
//		gbc_btnGuardar.insets = new Insets(0, 0, 10, 0);
//		gbc_btnGuardar.gridx = 0;
//		gbc_btnGuardar.gridy = 0;
//		panelColumna5.add(btnGuardar, gbc_btnGuardar);
		//=================================================
		// COLUMNA 5
		//==================================================
		JPanel panelColumna5 = new JPanel();
		panelColumna5.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna5);
		GridBagLayout gbl_panelColumna5 = new GridBagLayout();
		gbl_panelColumna5.columnWidths = new int[] { 0, 0 };
		gbl_panelColumna5.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelColumna5.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelColumna5.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelColumna5.setLayout(gbl_panelColumna5);

		// boton guardar
		btnGuardar = new JButton("Adicionar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGuardar.setBackground(C_PRIMARIO);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFocusPainted(false);
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardar.insets = new Insets(0, 0, 10, 0);
		gbc_btnGuardar.gridx = 0;
		gbc_btnGuardar.gridy = 0;
		panelColumna5.add(btnGuardar, gbc_btnGuardar);
		
		// boton buscar
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBuscar.setBackground(new Color(0x1B5E20));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFocusPainted(false);
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 10, 0);
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 1;
		panelColumna5.add(btnBuscar, gbc_btnBuscar);

		// boton modificar
		btnEditar = new JButton("Modificar");
		btnEditar.addActionListener(this);
		btnEditar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEditar.setBackground(new Color(230, 81, 0));
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFocusPainted(false);
		GridBagConstraints gbc_btnEditar = new GridBagConstraints();
		gbc_btnEditar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEditar.insets = new Insets(0, 0, 10, 0);
		gbc_btnEditar.gridx = 0;
		gbc_btnEditar.gridy = 2;
		panelColumna5.add(btnEditar, gbc_btnEditar);

		// boton eliminar
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 13));
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

		
		// =========================================================================
		// ICONOS DE LOS BOTONES
		//==========================================================================
		ImageIcon iconListar = new ImageIcon(getClass().getResource("/icons/list_alt_24.png"));
		btnListar.setIcon(iconListar);

		ImageIcon iconGuardar = new ImageIcon(getClass().getResource("/icons/save_24.png"));
		btnGuardar.setIcon(iconGuardar);

		ImageIcon iconEliminar = new ImageIcon(getClass().getResource("/icons/delete_24.png"));
		btnEliminar.setIcon(iconEliminar);

		ImageIcon iconBuscar = new ImageIcon(getClass().getResource("/icons/search_24.png"));
		btnBuscar.setIcon(iconBuscar);

		ImageIcon iconEditar = new ImageIcon(getClass().getResource("/icons/edit_document_24.png"));
		btnEditar.setIcon(iconEditar);

		ImageIcon iconCancelar = new ImageIcon(getClass().getResource("/icons/cancel_24.png"));
		btnCancelar.setIcon(iconCancelar);

		textCodigoA.setEnabled(false);
		textNombres.setEnabled(false);
		textApellidos.setEnabled(false);
		textDNI.setEnabled(false);
		textEdad.setEnabled(false);
		textCelular.setEnabled(false);
		comboBoxEstado.setEnabled(false);
				
		deshabilitarCampos();

		// =========================================================================
		// TABLA
		//==========================================================================
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(C_HEADER);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setPreferredSize(new Dimension(0, 35));
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		DefaultTableModel dataModel = new DefaultTableModel(
				new Object[][] {},
				new String[] { 
						"Codigo", 
						"Nombres", 
						"Apellidos", 
						"DNI", "Edad", 
						"Nro Celular", 
						"Estado",
						"Fecha",
						"Hora"
						}) 
		{
			Class<?>[] columnTypes = new Class<?>[] { 
				Integer.class, 
				String.class, 
				String.class, 
				String.class,
					Integer.class, 
					Integer.class, 
					Integer.class, 
					String.class, 
					String.class,
					String.class, 
					String.class};

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};

		table.setModel(dataModel);

		table.setBackground(Color.WHITE);
		table.setGridColor(new Color(224, 230, 232));

		JScrollPane scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.setBorder(new EmptyBorder(10, 10, 10, 10));

		add(scroll, BorderLayout.CENTER);

		DefaultTableCellRenderer alineacionIzquierda = new DefaultTableCellRenderer();
		alineacionIzquierda.setHorizontalAlignment(SwingConstants.LEFT);

		
		table.getColumnModel().getColumn(0).setCellRenderer(alineacionIzquierda);
		table.getColumnModel().getColumn(4).setCellRenderer(alineacionIzquierda);
		table.getColumnModel().getColumn(5).setCellRenderer(alineacionIzquierda);
		table.getColumnModel().getColumn(6).setCellRenderer(alineacionIzquierda);
		

		listar();
	}
	
	// ================================================================
	// ============ FIN CONSTRUCTOR GUI ==============================
	// ================================================================
	//================================================================
	
	//DECLARACION GLOBAL
	AlumnoDAO dao = new AlumnoDAO("Alumnos.txt");
	private boolean alumnoCargadoParaModificar = false;
	private String fecha;
	private String hora;
	private Component verticalStrut;


	// ================================================================
	// METODOS REDIRECCION A BOTONES DE PROCESO =======================
	// ================================================================

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
	    if (arg0.getSource() == btnBuscar) {
	        actionPerformedBtnBuscar(arg0);
	    }
	    else if (arg0.getSource() == btnEliminar) {
	        actionPerformedBtnEliminar(arg0);
	    }
	    else if (arg0.getSource() == btnEditar) {
	        actionPerformedBtnModificar(arg0);
	    }
	    else if (arg0.getSource() == btnListar) {
	        actionPerformedBtnConsultar(arg0);
	    }
	    else if (arg0.getSource() == btnGuardar) {
	        actionPerformedBtnAdicionar(arg0);
	    }
	    else if (arg0.getSource() == btnAceptar) {
	        actionPerformedBtnAceptar(arg0);
	    }
	    else if (arg0.getSource() == btnCancelar) {
	        actionPerformedBtnCancelar(arg0);
	    }
	    else if (arg0.getSource() == btnVolver) {
	        actionPerformedBtnVolver(arg0);
	    }
	}
	
	
	// ========================================================================================================
	// ============ METODOS DE TIPO ACTION EVENT ACTIVAN LOS METODOS QUE CORRESPONDE A SUS ACCIONES ==========
	// =======================================================================================================

	protected void actionPerformedBtnAdicionar(ActionEvent e) {	
	    modoOperacion = ADICIONAR;

	    limpiarCampos();

//	    btnGuardar.setEnabled(false);
	    btnBuscar.setEnabled(false);
	    btnEditar.setEnabled(false);
	    btnEliminar.setEnabled(false);

	    textCodigoA.setText("" + dao.codigoCorrelativo());
	    textCodigoA.setEnabled(false);
	    textNombres.setEnabled(true);
	    textApellidos.setEnabled(true);
	    textDNI.setEnabled(true);
	    textEdad.setEnabled(true);
	    textCelular.setEnabled(true);
	    comboBoxEstado.setEnabled(true);

	    textDNI.requestFocus();
	}

	protected void actionPerformedBtnConsultar(ActionEvent e) {
		listar();

	}

	protected void actionPerformedBtnModificar(ActionEvent e) {
	    modoOperacion = EDITAR;
	    alumnoCargadoParaModificar = false;

	    limpiarCampos();

	    btnGuardar.setEnabled(false);
	    btnBuscar.setEnabled(false);
	    btnEliminar.setEnabled(false);

	    textCodigoA.setEnabled(true);
	    textCodigoA.requestFocus();

	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
	    modoOperacion = ELIMINAR;

	    limpiarCampos();

	    btnGuardar.setEnabled(false);
	    btnBuscar.setEnabled(false);
	    btnEditar.setEnabled(false);
//	    btnEliminar.setEnabled(false);

	    textCodigoA.setEnabled(true);
	    textCodigoA.requestFocus();
	}

	protected void actionPerformedBtnBuscar(ActionEvent e) {
	    modoOperacion = BUSCAR;

	    limpiarCampos();

	    btnGuardar.setEnabled(false);
//	    btnBuscar.setEnabled(false);
	    btnEditar.setEnabled(false);
	    btnEliminar.setEnabled(false);

	    textCodigoA.setEnabled(true);
	    textCodigoA.requestFocus();

	}
	
	protected void actionPerformedBtnVolver(ActionEvent arg0) {
	    modoOperacion = NINGUNO;

	    limpiarCampos();
	    deshabilitarCampos();

	    btnGuardar.setEnabled(true);
	    btnBuscar.setEnabled(true);
	    btnEliminar.setEnabled(true);
	    btnEditar.setEnabled(true);
	    btnListar.setEnabled(true);
	    btnAceptar.setEnabled(true);
	    btnVolver.setEnabled(true);
	
	}
	

	protected void actionPerformedBtnCancelar(ActionEvent e) {
	    modoOperacion = NINGUNO;
	    alumnoCargadoParaModificar = false;
	
	    limpiarCampos();
	    deshabilitarCampos();
	
	    btnGuardar.setEnabled(true);
	    btnBuscar.setEnabled(true);
	    btnEditar.setEnabled(true);
	    btnEliminar.setEnabled(true);
	}

	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		 if (modoOperacion == ADICIONAR) {
		        adicionarAlumno();

		    } else if (modoOperacion == BUSCAR) {
		        buscar();

		    } else if (modoOperacion == EDITAR) {

		        if (alumnoCargadoParaModificar == false) {

		          
		            try {
		                int codigo = leerCodigo();
		                Alumno x = dao.buscar(codigo);

		                if (x != null) {
		                    textCodigoA.setText("" + x.getCodAlumno());
		                    textNombres.setText(x.getNombres());
		                    textApellidos.setText(x.getApellidos());
		                    textDNI.setText(x.getDni());
		                    textEdad.setText("" + x.getEdad());
		                    textCelular.setText("" + x.getCelular());
		                    comboBoxEstado.setSelectedIndex(x.getEstado());

		              
		                    textCodigoA.setEnabled(false);

		           
		                    textNombres.setEnabled(true);
		                    textApellidos.setEnabled(true);
		                    textDNI.setEnabled(true);
		                    textEdad.setEnabled(true);
		                    textCelular.setEnabled(true);
		                    comboBoxEstado.setEnabled(true);

		                    alumnoCargadoParaModificar = true;
		                    textNombres.requestFocus();

		                } else {
		                    error("El código " + codigo + " no existe", textCodigoA);
		                }

		            } catch (NumberFormatException e) {
		                error("Ingrese un código correcto", textCodigoA);
		            }

		        } else {

		            modificarAlumno();
		        }

		    } else if (modoOperacion == ELIMINAR) {
		        eliminarAlumno();
		    }
	}

	
	
	// ================================================================
	// ============ METODOS DE LOGICA =================================
	// ================================================================
	
	private void actualizarFechaHoraSistema() {
		LocalDateTime ahora = LocalDateTime.now();
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		fecha = ahora.format(formatoFecha);
		hora = ahora.format(formatoHora);
	}
	
	
	void listar() {
	    model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

	    try {
	        for (int i = 0; i < dao.tamaño(); i++) {
	            Alumno x = dao.obtener(i);

	            Object[] fila = {
	                x.getCodAlumno(),
	                x.getNombres(),
	                x.getApellidos(),
	                x.getDni(),
	                x.getEdad(),
	                x.getCelular(),
	                x.getEstado(),
					x.getFecha(),
					x.getHora(),
	            };

	            model.addRow(fila);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    lblContador.setText("Total de alumnos: " + dao.tamaño());
	}

	void adicionarAlumno() {

	    int codigo = leerCodigo();
	    String nombres = leerNombres();
	    String apellidos = leerApellidos();

	    if (nombres.length() > 0) {

	        String dni = leerDNI();

	        if (dao.buscar(dni) == null) {

	            int edad;

	            try {
	                edad = leerEdad();
	            } catch (Exception e) {
	                error("Ingrese Edad correcta", textEdad);
	                return;
	            }

	            int celular;

	            try {
	                celular = leerCelular();
	            } catch (Exception e) {
	                error("Ingrese número de celular correcto", textCelular);
	                return;
	            }

	            int estado = 0;

	            actualizarFechaHoraSistema();

	            Alumno nuevoAlumno = new Alumno(
	                    codigo, nombres, apellidos, dni,
	                    edad, celular, estado, fecha, hora
	            );

	            try {
	                dao.adicionar(nuevoAlumno);
	                dao.grabarAlumnos();

	                listar();

	                textCodigoA.setText("" + dao.codigoCorrelativo());
	                textNombres.setText("");
	                textApellidos.setText("");
	                textDNI.setText("");
	                textEdad.setText("");
	                textCelular.setText("");

	                comboBoxEstado.setSelectedIndex(0);
	                comboBoxEstado.setEnabled(false);

	                textNombres.requestFocus();

	                mensaje("Alumno grabado correctamente");

	            } catch (Exception e) {
	                e.printStackTrace();
	                mensaje("Error al grabar el alumno: " + e.getMessage());
	            }

	            
	        } else {
	            error("El DNI ya existe", textDNI);
	        }

	    } else {
	        error("Ingrese Nombre correcto", textNombres);
	    }
	}
	
	

	void buscar() {
	    try {
	        int codigo = leerCodigo();
	        Alumno x = dao.buscar(codigo);

	        if (x != null) {
	            textNombres.setText(x.getNombres());
	            textApellidos.setText(x.getApellidos());
	            textDNI.setText(x.getDni());
	            textEdad.setText("" + x.getEdad());
	            textCelular.setText("" + x.getCelular());
	            comboBoxEstado.setSelectedIndex(x.getEstado());

	            // Mostrar el código encontrado
	            textCodigoA.setText("" + x.getCodAlumno());

	            // Deshabilitar todos los campos
	            textCodigoA.setEnabled(false);
	            textNombres.setEnabled(false);
	            textApellidos.setEnabled(false);
	            textDNI.setEnabled(false);
	            textEdad.setEnabled(false);
	            textCelular.setEnabled(false);
	            comboBoxEstado.setEnabled(false);

	        } else {
	            error("El código " + codigo + " no existe", textCodigoA);
	            textCodigoA.setEnabled(true);
	        }

	    } catch (NumberFormatException e) {
	        error("Ingrese un código correcto", textCodigoA);
	        textCodigoA.setEnabled(true);
	    }
	}


	void eliminarAlumno() {
		try {
			int codigo = leerCodigo();
			Alumno x = dao.buscar(codigo);

			if (x != null) {
				dao.eliminar(x);
				dao.grabarAlumnos();
				listar();
				
				lblContador.setText("Total de alumnos: " + dao.tamaño());
				
				this.textCodigoA.setText("");
				this.textNombres.setText("");
				this.textApellidos.setText("");
				this.textDNI.setText("");
				this.textEdad.setText("");
				this.textCelular.setText("");
				this.textCodigoA.requestFocus();

			} else
				error("E código " + codigo + "no existe", this.textCodigoA);

		} catch (Exception e) {
			error("Ingrese CODIGO correcto", this.textCodigoA);
		}
	}

	void modificarAlumno() {
		try {
			Alumno x = dao.buscar(leerCodigo());
			String nombre = leerNombres();

			if (nombre.length() > 0) {
				String dni = leerDNI();
				Alumno y = dao.buscar(dni);

				if (y == null || x.equals(y)) {
					try {
						String apellido = leerApellidos();

						try {
							int estado = leerPosEstado();
							int edad = leerEdad();

							try {
								int celular = leerCelular();

								x.setNombres(nombre);
								x.setApellidos(apellido);
								x.setDni(dni);
								x.setEdad(edad);
								x.setCelular(celular);
								x.setEstado(estado);
								dao.grabarAlumnos();

								listar();
								this.textCodigoA.requestFocus();

							} catch (Exception e) {
								error("Ingrese N° de telefono correcto ", textCelular);
							}
						} catch (Exception e) {
							error("Ingrese una Edad correcta ", textEdad);
						}
					} catch (Exception e) {
						error("Ingrese apellidos correctos", textApellidos);
					}
				} else {
					error("ingrese DNI correct", textDNI);
				}
			} else {
				error("Ingrese  NOMBREs correcto ", textNombres);
			}
		} catch (Exception e) {
			error("El numero del CÓDIGO es incorrecto", textCodigoA);
		}
	}

	
	// =========================================================
	// METODO DE APOYO - LEEN CAJA DE TEXTO
	//=========================================================
		int leerCodigo() {
			return Integer.parseInt(textCodigoA.getText().trim());
		}

		String leerNombres() {
			return textNombres.getText().trim();
		}

		String leerApellidos() {
			return textApellidos.getText().trim();
		}

		String leerDNI() {
			return textDNI.getText().trim();
		}

		int leerEdad() {
			int edad = Integer.parseInt(textEdad.getText().trim());

			if (edad <= 18 || edad >= 90) {
			    error("La edad debe ser mayor que 18 y menor que 90", textEdad);
			    throw new IllegalArgumentException();
			}

			return edad;
			
			
		}

		int leerCelular() {
			return Integer.parseInt(textCelular.getText().trim());
		}

		int leerPosEstado() {
			return comboBoxEstado.getSelectedIndex();
		}

		String enTextoEstado(int i) {
			return (String) comboBoxEstado.getItemAt(i);
		}
		
		void error(String s, JTextField txt) {
			mensaje(s);
			txt.setText("");
			txt.requestFocus();
		}

	
		void mensaje(String s) {
			JOptionPane.showMessageDialog(this, s);
		}
		
		
		void deshabilitarCampos() {
		    textCodigoA.setEnabled(false);
		    textNombres.setEnabled(false);
		    textApellidos.setEnabled(false);
		    textDNI.setEnabled(false);
		    textEdad.setEnabled(false);
		    textCelular.setEnabled(false);
		    comboBoxEstado.setEnabled(false);
		}
		
		void limpiarCampos() {
		    textCodigoA.setText("");
		    textNombres.setText("");
		    textApellidos.setText("");
		    textDNI.setText("");
		    textEdad.setText("");
		    textCelular.setText("");
		    comboBoxEstado.setSelectedIndex(0);
		}
		
}

