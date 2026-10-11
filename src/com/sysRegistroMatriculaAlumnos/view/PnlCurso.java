/**
Descripción:clase que representa el panel de cursos en la interfaz gráfica del sistema de registro de matrícula de alumnos. Este panel permite al usuario gestionar los cursos, incluyendo la adición, consulta, modificación y eliminación de registros de cursos. Además, proporciona una tabla para mostrar los cursos existentes y un contador para indicar el total de alumnos.
@author America Soto Campos
@version 1.0
@since 7/10/2026
*/

package com.sysRegistroMatriculaAlumnos.view;

import com.sysRegistroMatriculaAlumnos.dao.*;
import com.sysRegistroMatriculaAlumnos.entity.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PnlCurso extends JPanel implements ActionListener {

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

	private JTextField textCodigoC, textCreditos, textHoras;
	private JComboBox<String> comboBoxCiclo, comboBoxAsignatura;

	private JLabel lblContador;

	
	private JButton btnListar, btnGuardar, btnEliminar;
	private JButton btnBuscar, btnEditar, btnCancelar, btnAceptar,btnVolver;

	private JTable table;
	private DefaultTableModel model;

	//=========================================================
	//================= INICIO CONSTRUCTOR GUI ================
	//=========================================================
	public PnlCurso() {
		setLayout(new BorderLayout(0, 0));
		
		setBackground(C_FONDO);
		
		JPanel panelNORTH = new JPanel();
		panelNORTH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelNORTH.setPreferredSize(new Dimension(0, 300));
		panelNORTH.setBackground(Color.WHITE);
		
		
		TitledBorder titulo = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(C_PRIMARIO, 2),
				" Mantenedor de Cursos ");

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
		
		
		//===============================================
		// COLUMNA 1
		//================================================
		JPanel panelColumna1 = new JPanel();
		panelColumna1.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna1);
		GridBagLayout gbl_panelColumna1 = new GridBagLayout();
		gbl_panelColumna1.columnWidths = new int[]{200, 0};
		gbl_panelColumna1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelColumna1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColumna1.setLayout(gbl_panelColumna1);
		
		// Codigo
		JLabel lblCodigoC = new JLabel("Codigo");
		lblCodigoC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCodigoC = new GridBagConstraints();
		gbc_lblCodigoC.anchor = GridBagConstraints.WEST;
		gbc_lblCodigoC.insets = new Insets(0, 0, 5, 0);
		gbc_lblCodigoC.gridx = 0;
		gbc_lblCodigoC.gridy = 0;
		panelColumna1.add(lblCodigoC, gbc_lblCodigoC);
		
		textCodigoC = new JTextField();
		textCodigoC.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textCodigoC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textCodigoC = new GridBagConstraints();
		gbc_textCodigoC.insets = new Insets(0, 0, 10, 0);
		gbc_textCodigoC.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCodigoC.gridx = 0;
		gbc_textCodigoC.gridy = 1;
		panelColumna1.add(textCodigoC, gbc_textCodigoC);
		textCodigoC.setColumns(10);
		
		// Creditos
		JLabel lblCreditos = new JLabel("Nro. Creditos *");
		lblCreditos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCreditos = new GridBagConstraints();
		gbc_lblCreditos.anchor = GridBagConstraints.WEST;
		gbc_lblCreditos.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreditos.gridx = 0;
		gbc_lblCreditos.gridy = 2;
		panelColumna1.add(lblCreditos, gbc_lblCreditos);
		
		textCreditos = new JTextField();
		textCreditos.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textCreditos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textCreditos = new GridBagConstraints();
		gbc_textCreditos.insets = new Insets(0, 0, 5, 0);
		gbc_textCreditos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCreditos.gridx = 0;
		gbc_textCreditos.gridy = 3;
		panelColumna1.add(textCreditos, gbc_textCreditos);
		textCreditos.setColumns(10);
		
		

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
		gbc_btnListar.insets = new Insets(33, 0, 0, 0);
		gbc_btnListar.gridx = 0;
		gbc_btnListar.gridy = 4;
		panelColumna1.add(btnListar, gbc_btnListar);
		

		
		
		//==================================================
		// COLUMNA 2
		//===================================================
		JPanel panelColumna2 = new JPanel();
		panelColumna2.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna2);
		GridBagLayout gbl_panelColumna2 = new GridBagLayout();
		gbl_panelColumna2.columnWidths = new int[]{0, 0};
		gbl_panelColumna2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelColumna2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColumna2.setLayout(gbl_panelColumna2);
		
		
		// Ciclo
		JLabel lblCiclo = new JLabel("Ciclo *");
		lblCiclo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCiclo = new GridBagConstraints();
		gbc_lblCiclo.anchor = GridBagConstraints.WEST;
		gbc_lblCiclo.insets = new Insets(0, 0, 5, 0);
		gbc_lblCiclo.gridx = 0;
		gbc_lblCiclo.gridy = 0;
		panelColumna2.add(lblCiclo, gbc_lblCiclo);
		
		comboBoxCiclo = new JComboBox<>();
		comboBoxCiclo.setBorder(new EmptyBorder(1, 1, 3, 1));
		comboBoxCiclo.setModel(new DefaultComboBoxModel(new String[] { "Primero", "Segundo", "Tercero", "Cuarto","Quinto" }));
		comboBoxCiclo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_comboBoxCiclo = new GridBagConstraints();
		gbc_comboBoxCiclo.insets = new Insets(0, 0, 10, 0);
		gbc_comboBoxCiclo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCiclo.gridx = 0;
		gbc_comboBoxCiclo.gridy = 1;
		panelColumna2.add(comboBoxCiclo, gbc_comboBoxCiclo);
		
		
		// Asignatura
		JLabel lblAsignatura = new JLabel("Asignatura *");
		lblAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblAsignatura = new GridBagConstraints();
		gbc_lblAsignatura.anchor = GridBagConstraints.WEST;
		gbc_lblAsignatura.insets = new Insets(0, 0, 5, 0);
		gbc_lblAsignatura.gridx = 0;
		gbc_lblAsignatura.gridy = 2;
		panelColumna2.add(lblAsignatura, gbc_lblAsignatura);
		
		comboBoxAsignatura = new JComboBox<>();
		comboBoxAsignatura.setBorder(new EmptyBorder(1, 1, 3, 1));
		comboBoxAsignatura.setModel(new DefaultComboBoxModel(new String[] { 
			"Programación", 
			"Bases de Datos", 
			"Algoritmos", 
			"Redes de Computadoras", 
			"Sistemas Operativos", 
			"Estructuras de Datos", 
			"Ingeniería de Software", 
			"Seguridad Informática", 
			"Programación Web", 
			"Inteligencia Artificial"
		}));
		comboBoxAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_comboBoxAsignatura = new GridBagConstraints();
		gbc_comboBoxAsignatura.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxAsignatura.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAsignatura.gridx = 0;
		gbc_comboBoxAsignatura.gridy = 3;
		panelColumna2.add(comboBoxAsignatura, gbc_comboBoxAsignatura);
		comboBoxAsignatura.setSelectedIndex(0);

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
		
		ImageIcon iconcont = new ImageIcon(
				getClass().getResource("/icons/book_white_24.png")
		);
		lblContador.setIcon(iconcont);
		
		//======================================================
		// COLUMNA 3
		//=======================================================
		JPanel panelColumna3 = new JPanel();
		panelColumna3.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna3);
		GridBagLayout gbl_panelColumna3 = new GridBagLayout();
		gbl_panelColumna3.columnWidths = new int[]{0, 0};
		gbl_panelColumna3.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelColumna3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColumna3.setLayout(gbl_panelColumna3);
		
		// Horas
		JLabel lblbHoras = new JLabel("Cant. Horas *");
		lblbHoras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblbHoras = new GridBagConstraints();
		gbc_lblbHoras.anchor = GridBagConstraints.WEST;
		gbc_lblbHoras.insets = new Insets(0, 0, 5, 0);
		gbc_lblbHoras.gridx = 0;
		gbc_lblbHoras.gridy = 0;
		panelColumna3.add(lblbHoras, gbc_lblbHoras);
		
		textHoras = new JTextField();
		textHoras.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textHoras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textHoras = new GridBagConstraints();
		gbc_textHoras.insets = new Insets(0, 0, 10, 0);
		gbc_textHoras.fill = GridBagConstraints.HORIZONTAL;
		gbc_textHoras.gridx = 0;
		gbc_textHoras.gridy = 1;
		panelColumna3.add(textHoras, gbc_textHoras);
		textHoras.setColumns(10);
		
		//=============================================================
		// COLUMNA 4
		//=============================================================
		JPanel panelColumna4 = new JPanel();
		panelColumna4.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna4);
		GridBagLayout gbl_panelColumna4 = new GridBagLayout();
		gbl_panelColumna4.columnWidths = new int[]{0, 0};
		gbl_panelColumna4.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelColumna4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna4.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelColumna4.setLayout(gbl_panelColumna4);		

		// boton cancelar
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBackground(new Color(0x546E7A));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFocusPainted(false);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.insets = new Insets(0, 0, 10, 0);
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 0;
		panelColumna4.add(btnCancelar, gbc_btnCancelar);
		
		// boton aceptar
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBackground(new Color(22, 160, 133));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFocusPainted(false);
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceptar.insets = new Insets(0, 0, 10, 0);
		gbc_btnAceptar.gridx = 0;
		gbc_btnAceptar.gridy = 1;
		panelColumna4.add(btnAceptar, gbc_btnAceptar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVolver.setBackground(new Color(59, 130, 196));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 10, 0);
		gbc_btnVolver.gridx = 0;
		gbc_btnVolver.gridy = 2;
		gbc_btnVolver.fill = GridBagConstraints.HORIZONTAL;
		panelColumna4.add(btnVolver, gbc_btnVolver);
		
		//===========================================
		// COLUMNA 5
		//=============================================
		JPanel panelColumna5 = new JPanel();
		panelColumna5.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna5);
		GridBagLayout gbl_panelColumna5 = new GridBagLayout();
		gbl_panelColumna5.columnWidths = new int[]{0, 0};
		gbl_panelColumna5.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelColumna5.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelColumna5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		// boton editar
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
		
		this.textCodigoC.setEnabled(false);
		this.comboBoxAsignatura.setEnabled(false);
		this.comboBoxCiclo.setEnabled(false);
		this.textCreditos.setEnabled(false);
		this.textHoras.setEnabled(false);
		
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
					"Asignatura", 
					"Ciclo", 
					"Creditos", 
					"Horas",
					"Fecha", 
					"Hora"
				})
		{
			Class<?>[] columnTypes = new Class<?>[] {
				Integer.class,
				String.class,
				Integer.class,
				Integer.class, 
				Integer.class,
				String.class,
				String.class
			};
			
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

		table.getColumnModel().getColumn(2).setCellRenderer(alineacionIzquierda);
		table.getColumnModel().getColumn(0).setCellRenderer(alineacionIzquierda);
		table.getColumnModel().getColumn(4).setCellRenderer(alineacionIzquierda);
		table.getColumnModel().getColumn(3).setCellRenderer(alineacionIzquierda);
		



		listar();
	}
	
	//=========================================================
	//================= FIN CONSTRUCTOR GUI ===================
	//=========================================================

	//DECLARACION GLOBAL
	CursoDAO dao = new CursoDAO("Cursos.txt");
	private boolean cursoCargadoParaModificar = false;
	private String fecha;
	private String hora;


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
	    
	    this.textCodigoC.setText("" + dao.codigoCorrelativo());
	    this.textCodigoC.setEnabled(false);
	    this.comboBoxAsignatura.setEnabled(true);
	    this.comboBoxCiclo.setEnabled(true);
	    this.textCreditos.setEnabled(true);
	    this.textHoras.setEnabled(true);
	    
	    this.comboBoxAsignatura.requestFocus();
	}	

	protected void actionPerformedBtnConsultar(ActionEvent e) {
		listar();

	}	
	

	protected void actionPerformedBtnModificar(ActionEvent e) {
	    modoOperacion = EDITAR;
	    cursoCargadoParaModificar = false;

	    limpiarCampos();

	    btnGuardar.setEnabled(false);
	    btnBuscar.setEnabled(false);
	    btnEliminar.setEnabled(false);
	    
	    textCodigoC.setEnabled(true);
	    textCodigoC.requestFocus();
	    
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
	    modoOperacion = ELIMINAR;
	    
	    limpiarCampos();
	    
	    btnGuardar.setEnabled(false);
	    btnBuscar.setEnabled(false);
	    btnEditar.setEnabled(false);
//	    btnEliminar.setEnabled(false);	    
	    
	    textCodigoC.setEnabled(true);
	    textCodigoC.requestFocus();
	}
	
	
	protected void actionPerformedBtnBuscar(ActionEvent e) {
	    modoOperacion = BUSCAR;
	    
	    limpiarCampos();
	    
	    btnGuardar.setEnabled(false);
//	    btnBuscar.setEnabled(false);
	    btnEditar.setEnabled(false);
	    btnEliminar.setEnabled(false);

	    textCodigoC.setEnabled(true);
	    textCodigoC.requestFocus();    
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
	    cursoCargadoParaModificar = false;
	    
	    limpiarCampos();
	    deshabilitarCampos();

	    btnGuardar.setEnabled(true);
	    btnBuscar.setEnabled(true);
	    btnEditar.setEnabled(true);
	    btnEliminar.setEnabled(true);
	}
	
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
	    if (modoOperacion == ADICIONAR) {
	        adicionarCurso();

	    } else if (modoOperacion == BUSCAR) {
	        buscar();

	    } else if (modoOperacion == EDITAR) {

	        if (cursoCargadoParaModificar == false) {

	            try {
	                int codigo = leerCodigo();
	                Curso x = dao.buscar(codigo);

	                if (x != null) {
	                    textCodigoC.setText("" + x.getCodCurso());
	                    comboBoxAsignatura.setSelectedItem(x.getAsignatura());
	                    comboBoxCiclo.setSelectedIndex(x.getCiclo() - 1);
	                    textCreditos.setText("" + x.getCréditos());
	                    textHoras.setText("" + x.getHoras());

	                    textCodigoC.setEnabled(false);

	                    comboBoxAsignatura.setEnabled(true);
	                    comboBoxCiclo.setEnabled(false);
	                    textCreditos.setEnabled(true);
	                    textHoras.setEnabled(true);

	                    cursoCargadoParaModificar = true;
	                    comboBoxAsignatura.requestFocus();

	                } else {
	                    error("El código " + codigo + " no existe", textCodigoC);
	                }

	            } catch (NumberFormatException e) {
	                error("Ingrese un código correcto", textCodigoC);
	            }

	        } else {
	            modificarCurso();
	        }
	    } else if (modoOperacion == ELIMINAR) {
	    	eliminarCurso();
	    }
	}
	
	
	
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
	            Curso x = dao.obtener(i);

	            Object[] fila = {
	                x.getCodCurso(),
	                x.getAsignatura(),
	                x.getCiclo(),
	                x.getCréditos(),
	                x.getHoras(),
					x.getFecha(),
					x.getHora(),
	            };

	            model.addRow(fila);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    lblContador.setText("Total de cursos: " + dao.tamaño());
	}

	void adicionarCurso() {

		int codigo = leerCodigo();
		String asignatura = leerAsignatura();

		if (asignatura.length() > 0) {

		    int ciclo;

		    try {
		        ciclo = leerCiclo();
		    } catch (Exception e) {
		        error("Ingrese Ciclo correcto", comboBoxCiclo);
		        return;
		    }

		    int creditos;

		    try {
		        creditos = leerCreditos();
		    } catch (Exception e) {
		        error("Ingrese Créditos correctos", textCreditos);
		        return;
		    }

		    int horas;

		    try {
		        horas = leerHoras();
		    } catch (Exception e) {
		        error("Ingrese Horas correctas", textHoras);
		        return;
		    }

		    actualizarFechaHoraSistema();

		    Curso nuevoCurso = new Curso(
		            codigo, asignatura, ciclo, creditos, horas, fecha, hora
		    );

		    try {
		        dao.adicionar(nuevoCurso);
		        dao.grabarCursos();

		        listar();

		        textCodigoC.setText("" + dao.codigoCorrelativo());
		        comboBoxAsignatura.setSelectedIndex(0);
		        comboBoxCiclo.setSelectedIndex(0);
		        textCreditos.setText("");
		        textHoras.setText("");

		        comboBoxAsignatura.requestFocus();

		        mensaje("Curso grabado correctamente");

		    } catch (Exception e) {
		        e.printStackTrace();
		        mensaje("Error al grabar el curso: " + e.getMessage());
		    }

		} else {
		    error("Ingrese Asignatura correcta", comboBoxAsignatura);
		}
	}
		
		void buscar() {
		    try {
		        int codigo = leerCodigo();
		        Curso x = dao.buscar(codigo);

		        if (x != null) {
		            comboBoxAsignatura.setSelectedItem(x.getAsignatura());
		            this.comboBoxCiclo.setSelectedIndex(x.getCiclo() - 1);
		            textCreditos.setText("" + x.getCréditos());
		            textHoras.setText("" + x.getHoras());

		            // Mostrar el código encontrado
		            textCodigoC.setText("" + x.getCodCurso());

		            // Deshabilitar todos los campos
		            textCodigoC.setEnabled(false);
		            comboBoxAsignatura.setEnabled(false);
		            comboBoxCiclo.setEnabled(false);
		            textCreditos.setEnabled(false);
		            textHoras.setEnabled(false);

		        } else {
		            error("El código " + codigo + " no existe", textCodigoC);
		            textCodigoC.setEnabled(true);
		        }

		    } catch (NumberFormatException e) {
		        error("Ingrese un código correcto", textCodigoC);
		        textCodigoC.setEnabled(true);
		    }
		}
		
		void eliminarCurso() {
		    try {
		        int codigo = leerCodigo();
		        Curso x = dao.buscar(codigo);

		        if (x != null) {
		            dao.eliminar(x);
		            dao.grabarCursos();
		            listar();

		            lblContador.setText("Total de cursos: " + dao.tamaño());

		            this.textCodigoC.setText("");
		            this.comboBoxAsignatura.setSelectedIndex(0);
		            this.comboBoxCiclo.setSelectedIndex(0);
		            this.textCreditos.setText("");
		            this.textHoras.setText("");
		            this.textCodigoC.requestFocus();

		        } else {
		            error("El código " + codigo + " no existe", this.textCodigoC);
		        }

		    } catch (Exception e) {
		        error("Ingrese CÓDIGO correcto", this.textCodigoC);
		    }
		}
		
		void modificarCurso() {
		    try {
		        Curso x = dao.buscar(leerCodigo());
		        String asignatura = leerAsignatura();

		        if (asignatura.length() > 0) {
		            try {
		                int ciclo = leerCiclo();

		                try {
		                    int creditos = leerCreditos();

		                    try {
		                        int horas = leerHoras();

		                        x.setAsignatura(asignatura);
		                        x.setCiclo(ciclo);
		                        x.setCréditos(creditos);
		                        x.setHoras(horas);

		                        dao.grabarCursos();

		                        listar();
		                        this.textCodigoC.requestFocus();

		                        mensaje("Curso modificado correctamente");

		                    } catch (Exception e) {
		                        error("Ingrese Horas correctas", textHoras);
		                    }
		                } catch (Exception e) {
		                    error("Ingrese Créditos correctos", textCreditos);
		                }
		            } catch (Exception e) {
		                error("Ingrese un Ciclo correcto", comboBoxCiclo);
		            }
		        } else {
		            error("Ingrese una ASIGNATURA correcta", comboBoxAsignatura);
		        }

		    } catch (Exception e) {
		        error("El número del CÓDIGO es incorrecto", textCodigoC);
		    }
		}
	// =========================================================
	// METODO DE APOYO - LEEN CAJA DE TEXTO
	//=========================================================
	
		int leerCodigo() {
		    return Integer.parseInt(textCodigoC.getText().trim());
		}

		String leerAsignatura() {
		    return (String) comboBoxAsignatura.getSelectedItem();
		}

		int leerCiclo() {
		    int ciclo = comboBoxCiclo.getSelectedIndex() + 1;

		    if (ciclo < 1 || ciclo > 5) {
		        error("Seleccione un ciclo correcto", comboBoxCiclo);
		        throw new IllegalArgumentException();
		    }

		    return ciclo;
		}

		int leerCreditos() {
		    return Integer.parseInt(textCreditos.getText().trim());
		}

		int leerHoras() {
		    return Integer.parseInt(textHoras.getText().trim());
		}

		void error(String s, Component componente) {
		    mensaje(s);

		    if (componente instanceof JTextField) {
		        ((JTextField) componente).setText("");
		    }

		    componente.requestFocus();
		}

		void mensaje(String s) {
		    JOptionPane.showMessageDialog(this, s);
		}

		void deshabilitarCampos() {
		    textCodigoC.setEnabled(false);
		    comboBoxAsignatura.setEnabled(false);
		    comboBoxCiclo.setEnabled(false);
		    textCreditos.setEnabled(false);
		    textHoras.setEnabled(false);
		}

		void limpiarCampos() {
		    textCodigoC.setText("");
		    comboBoxAsignatura.setSelectedIndex(0);
		    comboBoxCiclo.setSelectedIndex(0);
		    textCreditos.setText("");
		    textHoras.setText("");
		}

	
}

