/**
Descripción:clase que representa el panel de gestión de retiros de matrículas de alumnos en un sistema de registro académico. Esta clase proporciona una interfaz gráfica para realizar operaciones como adicionar, buscar, modificar y eliminar registros de retiros, así como listar todos los retiros existentes. Además, permite gestionar la relación entre los retiros y las matrículas correspondientes, asegurando que se cumplan las reglas de negocio relacionadas con el estado de los alumnos.
@author Edwin Huamanñahui Aedo
@version 1.0
@since 7/10/2026
*/
package com.sysRegistroMatriculaAlumnos.view;

import com.sysRegistroMatriculaAlumnos.dao.AlumnoDAO;
import com.sysRegistroMatriculaAlumnos.dao.CursoDAO;
import com.sysRegistroMatriculaAlumnos.dao.MatriculaDAO;
import com.sysRegistroMatriculaAlumnos.dao.RetiroDAO;
import com.sysRegistroMatriculaAlumnos.entity.Alumno;
import com.sysRegistroMatriculaAlumnos.entity.Curso;
import com.sysRegistroMatriculaAlumnos.entity.Matricula;
import com.sysRegistroMatriculaAlumnos.entity.Retiro;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PnlRetiro extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// ESTADOS DE OPERACIÓN
	private int modoOperacion = 0;
	private static final int NINGUNO   = 0;
	private static final int ADICIONAR = 1;
	private static final int BUSCAR    = 2;
	private static final int EDITAR    = 3;
	private static final int ELIMINAR  = 4;

	// COLORES DEL SISTEMA
	private static final Color C_PRIMARIO = LoginFrame.C_PRIMARIO;
	private static final Color C_HEADER   = LoginFrame.C_HEADER;
	private static final Color C_FONDO    = LoginFrame.C_FONDO;

	// CAMPOS DE TEXTO
	private JTextField textNumRetiro;
	private JTextField textNumMatricula;
	private JTextField textCodCurso;
	private JTextField textFecha;

	private JLabel lblContador;

	// BOTONES
	private JButton btnListar;
	private JButton btnGuardar;
	private JButton btnBuscar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JButton btnVolver;

	// TABLA
	private JTable table;
	private DefaultTableModel model;

	// OBJETOS DAO
	private RetiroDAO daoRetiro       = new RetiroDAO("Retiros.txt");
	private MatriculaDAO daoMatricula = new MatriculaDAO("Matriculas.txt");
	private AlumnoDAO daoAlumno       = new AlumnoDAO("Alumnos.txt");
	private CursoDAO daoCurso         = new CursoDAO("Cursos.txt");

	private boolean retiroCargadoParaModificar = false;
	private JTextField textHora;

	public PnlRetiro() {
		setLayout(new BorderLayout(0, 0));
		setBackground(C_FONDO);

		JPanel panelNORTH = new JPanel();
		panelNORTH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelNORTH.setPreferredSize(new Dimension(0, 300));
		panelNORTH.setBackground(Color.WHITE);

		TitledBorder titulo = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(C_PRIMARIO, 2),
				" Mantenedor de Retiro ");
		titulo.setTitleFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setTitleColor(C_PRIMARIO);

		panelNORTH.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createCompoundBorder(titulo, BorderFactory.createEmptyBorder(12, 14, 0, 14))));
		add(panelNORTH, BorderLayout.NORTH);
		panelNORTH.setLayout(new GridLayout(1, 5, 16, 0));

		// ================= COLUMNA 1 =================
		JPanel panelColumna1 = new JPanel();
		panelColumna1.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna1);
		GridBagLayout gbl_panelColumna1 = new GridBagLayout();
		gbl_panelColumna1.columnWidths = new int[] { 200, 0 };
		gbl_panelColumna1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelColumna1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelColumna1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelColumna1.setLayout(gbl_panelColumna1);

		JLabel lblNumRetiro = new JLabel("N° Retiro");
		lblNumRetiro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNumRetiro = new GridBagConstraints();
		gbc_lblNumRetiro.anchor = GridBagConstraints.WEST;
		gbc_lblNumRetiro.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumRetiro.gridx = 0;
		gbc_lblNumRetiro.gridy = 0;
		panelColumna1.add(lblNumRetiro, gbc_lblNumRetiro);

		textNumRetiro = new JTextField();
		textNumRetiro.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textNumRetiro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textNumRetiro = new GridBagConstraints();
		gbc_textNumRetiro.insets = new Insets(0, 0, 10, 0);
		gbc_textNumRetiro.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNumRetiro.gridx = 0;
		gbc_textNumRetiro.gridy = 1;
		panelColumna1.add(textNumRetiro, gbc_textNumRetiro);
		textNumRetiro.setColumns(10);

		JLabel lblNumMatricula = new JLabel("N° Matrícula");
		lblNumMatricula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNumMatricula = new GridBagConstraints();
		gbc_lblNumMatricula.anchor = GridBagConstraints.WEST;
		gbc_lblNumMatricula.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumMatricula.gridx = 0;
		gbc_lblNumMatricula.gridy = 2;
		panelColumna1.add(lblNumMatricula, gbc_lblNumMatricula);

		textNumMatricula = new JTextField();
		textNumMatricula.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textNumMatricula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textNumMatricula = new GridBagConstraints();
		gbc_textNumMatricula.insets = new Insets(0, 0, 5, 0);
		gbc_textNumMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNumMatricula.gridx = 0;
		gbc_textNumMatricula.gridy = 3;
		panelColumna1.add(textNumMatricula, gbc_textNumMatricula);
		textNumMatricula.setColumns(10);

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

		// ================= COLUMNA 2 (ALINEADA) =================
		JPanel panelColumna2 = new JPanel();
		panelColumna2.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna2);
		GridBagLayout gbl_panelColumna2 = new GridBagLayout();
		gbl_panelColumna2.columnWidths = new int[] { 0, 0 };
		gbl_panelColumna2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelColumna2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelColumna2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelColumna2.setLayout(gbl_panelColumna2);

		JLabel lblCodCurso = new JLabel("Cód. Curso");
		lblCodCurso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCodCurso = new GridBagConstraints();
		gbc_lblCodCurso.anchor = GridBagConstraints.WEST;
		gbc_lblCodCurso.insets = new Insets(0, 0, 5, 0);
		gbc_lblCodCurso.gridx = 0;
		gbc_lblCodCurso.gridy = 0;
		panelColumna2.add(lblCodCurso, gbc_lblCodCurso);

		textCodCurso = new JTextField();
		textCodCurso.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textCodCurso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textCodCurso = new GridBagConstraints();
		gbc_textCodCurso.insets = new Insets(0, 0, 10, 0);
		gbc_textCodCurso.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCodCurso.gridx = 0;
		gbc_textCodCurso.gridy = 1;
		panelColumna2.add(textCodCurso, gbc_textCodCurso);
		textCodCurso.setColumns(10);

		// Espaciador para nivelar el contador exactamente con btnListar
		Component spacerColumna2 = Box.createVerticalStrut(58);
		GridBagConstraints gbc_spacerColumna2 = new GridBagConstraints();
		gbc_spacerColumna2.gridx = 0;
		gbc_spacerColumna2.gridy = 2;
		gbc_spacerColumna2.gridheight = 2;
		panelColumna2.add(spacerColumna2, gbc_spacerColumna2);

		lblContador = new JLabel("Total de retiros: 0");
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

		 // ================= COLUMNA 3 (FECHA Y HORA CORREGIDA) =================
        JPanel panelColumna3 = new JPanel();
        panelColumna3.setBackground(Color.WHITE);
        panelNORTH.add(panelColumna3);
        GridBagLayout gbl_panelColumna3 = new GridBagLayout();
        gbl_panelColumna3.columnWidths = new int[] { 0, 0 };
        gbl_panelColumna3.rowHeights = new int[] { 0, 0, 0, 0, 0 };
        gbl_panelColumna3.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_panelColumna3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        panelColumna3.setLayout(gbl_panelColumna3);

        JLabel lblFecha = new JLabel("Fecha (Sistema)");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblFecha = new GridBagConstraints();
        gbc_lblFecha.anchor = GridBagConstraints.WEST;
        gbc_lblFecha.insets = new Insets(0, 0, 5, 0);
        gbc_lblFecha.gridx = 0;
        gbc_lblFecha.gridy = 0;
        panelColumna3.add(lblFecha, gbc_lblFecha);

        textFecha = new JTextField();
        textFecha.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
        textFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_textFecha = new GridBagConstraints();
        gbc_textFecha.insets = new Insets(0, 0, 10, 0);
        gbc_textFecha.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFecha.gridx = 0;
        gbc_textFecha.gridy = 1;
        panelColumna3.add(textFecha, gbc_textFecha);
        textFecha.setColumns(10);

        JLabel lblHora = new JLabel("Hora (Sistema)");
        lblHora.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_lblHora = new GridBagConstraints();
        gbc_lblHora.anchor = GridBagConstraints.WEST;
        gbc_lblHora.insets = new Insets(0, 0, 5, 0);
        gbc_lblHora.gridx = 0;
        gbc_lblHora.gridy = 2;
        panelColumna3.add(lblHora, gbc_lblHora);

        textHora = new JTextField();
        textHora.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
        textHora.setFont(new Font("Tahoma", Font.PLAIN, 13));
        GridBagConstraints gbc_textHora = new GridBagConstraints();
        gbc_textHora.insets = new Insets(0, 0, 5, 0);
        gbc_textHora.fill = GridBagConstraints.HORIZONTAL;
        gbc_textHora.gridx = 0;
        gbc_textHora.gridy = 3;
        panelColumna3.add(textHora, gbc_textHora);
        textHora.setColumns(10);

		// ================= COLUMNA 4 =================
		JPanel panelColumna4 = new JPanel();
		panelColumna4.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna4);
		GridBagLayout gbl_panelColumna4 = new GridBagLayout();
		gbl_panelColumna4.columnWidths = new int[] { 0, 0 };
		gbl_panelColumna4.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelColumna4.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelColumna4.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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
				gbc_btnCancelar.insets = new Insets(0, 0, 0, 0);
				gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnCancelar.gridx = 0;
				gbc_btnCancelar.gridy = 0;
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
				gbc_btnAceptar.gridy = 1;
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
				gbc_btnVolver.gridy = 2;
				gbc_btnVolver.fill = GridBagConstraints.HORIZONTAL;
				panelColumna4.add(btnVolver, gbc_btnVolver);

		// ================= COLUMNA 5 =================
		JPanel panelColumna5 = new JPanel();
		panelColumna5.setBackground(Color.WHITE);
		panelNORTH.add(panelColumna5);
		GridBagLayout gbl_panelColumna5 = new GridBagLayout();
		gbl_panelColumna5.columnWidths = new int[] { 0, 0 };
		gbl_panelColumna5.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelColumna5.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelColumna5.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelColumna5.setLayout(gbl_panelColumna5);

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

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBorder(new EmptyBorder(12, 0, 12, 0));
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEliminar.setBackground(new Color(220, 20, 60));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFocusPainted(false);
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminar.gridx = 0;
		gbc_btnEliminar.gridy = 3;
		panelColumna5.add(btnEliminar, gbc_btnEliminar);

		// ICONOS
		try {
			btnListar.setIcon(new ImageIcon(getClass().getResource("/icons/list_alt_24.png")));
			btnGuardar.setIcon(new ImageIcon(getClass().getResource("/icons/save_24.png")));
			btnEliminar.setIcon(new ImageIcon(getClass().getResource("/icons/delete_24.png")));
			btnBuscar.setIcon(new ImageIcon(getClass().getResource("/icons/search_24.png")));
			btnEditar.setIcon(new ImageIcon(getClass().getResource("/icons/edit_document_24.png")));
			btnCancelar.setIcon(new ImageIcon(getClass().getResource("/icons/cancel_24.png")));
			lblContador.setIcon(new ImageIcon(getClass().getResource("/icons/book_white_24.png")));
		} catch (Exception e) {}

		// TABLA CON COLUMNA DE ESTADO
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(C_HEADER);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setPreferredSize(new Dimension(0, 35));
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		DefaultTableModel dataModel = new DefaultTableModel(new Object[][] {},
				new String[] { "N° Retiro", "N° Matrícula", "Cód. Alumno", "Cód. Curso", "Fecha", "Hora", "Estado" }) {
			Class<?>[] columnTypes = new Class<?>[] { 
				Integer.class, Integer.class, Integer.class, Integer.class, String.class, String.class, String.class 
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

		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < 7; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centro);
		}

		deshabilitarCampos();
		listar();
	}

	// ================================================================
	// ============ CONTROL DE EVENTOS ================================
	// ================================================================

	@Override
	public void actionPerformed(ActionEvent e) {
		if      (e.getSource() == btnGuardar)  actionPerformedBtnAdicionar(e);
		else if (e.getSource() == btnBuscar)   actionPerformedBtnBuscar(e);
		else if (e.getSource() == btnEditar)   actionPerformedBtnModificar(e);
		else if (e.getSource() == btnEliminar) actionPerformedBtnEliminar(e);
		else if (e.getSource() == btnListar)   listar();
		else if (e.getSource() == btnAceptar)  actionPerformedBtnAceptar(e);
		else if (e.getSource() == btnCancelar) actionPerformedBtnCancelar(e);
		else if (e.getSource() == btnVolver)   actionPerformedBtnVolver(e);
	}

	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		modoOperacion = ADICIONAR;
		limpiarCampos();

		btnGuardar.setEnabled(false);
		btnBuscar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnEliminar.setEnabled(false);

		daoRetiro.leerRetiros();
		daoMatricula.leerMatriculas();
		daoAlumno.leerAlumnos();

		textNumRetiro.setText("" + daoRetiro.codigoCorrelativo());
		actualizarFechaHoraSistema();

		textNumRetiro.setEnabled(false);
		textFecha.setEnabled(false);
		textHora.setEnabled(false);
		textCodCurso.setEnabled(false);

		textNumMatricula.setEnabled(true);
		textNumMatricula.requestFocus();
	}

	protected void actionPerformedBtnBuscar(ActionEvent e) {
		modoOperacion = BUSCAR;
		limpiarCampos();

		btnGuardar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnEliminar.setEnabled(false);

		textNumRetiro.setEnabled(true);
		textNumRetiro.requestFocus();
	}

	protected void actionPerformedBtnModificar(ActionEvent e) {
		modoOperacion = EDITAR;
		retiroCargadoParaModificar = false;
		limpiarCampos();

		btnGuardar.setEnabled(false);
		btnBuscar.setEnabled(false);
		btnEliminar.setEnabled(false);

		textNumRetiro.setEnabled(true);
		textNumRetiro.requestFocus();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		modoOperacion = ELIMINAR;
		limpiarCampos();

		btnGuardar.setEnabled(false);
		btnBuscar.setEnabled(false);
		btnEditar.setEnabled(false);

		textNumRetiro.setEnabled(true);
		textNumRetiro.requestFocus();
	}

	protected void actionPerformedBtnAceptar(ActionEvent e) {
		if (modoOperacion == ADICIONAR) {
			adicionarRetiro();
		} else if (modoOperacion == BUSCAR) {
			buscarRetiro();
		} else if (modoOperacion == EDITAR) {
			if (!retiroCargadoParaModificar) {
				cargarRetiroParaModificar();
			} else {
				modificarCursoMatriculaDesactivada();
			}
		} else if (modoOperacion == ELIMINAR) {
			eliminarRetiro();
		}
	}

	protected void actionPerformedBtnCancelar(ActionEvent e) {
		modoOperacion = NINGUNO;
		retiroCargadoParaModificar = false;
		limpiarCampos();
		deshabilitarCampos();

		btnGuardar.setEnabled(true);
		btnBuscar.setEnabled(true);
		btnEditar.setEnabled(true);
		btnEliminar.setEnabled(true);
	}

	protected void actionPerformedBtnVolver(ActionEvent e) {
		actionPerformedBtnCancelar(e);
		listar();
	}

	// ================================================================
	// ============ REGLAS DE NEGOCIO =================================
	// ================================================================

	private void actualizarFechaHoraSistema() {
		LocalDateTime ahora = LocalDateTime.now();
		textFecha.setText(ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		textHora.setText(ahora.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	}

	void listar() {
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		try {
			daoRetiro.leerRetiros();
			daoMatricula.leerMatriculas();
			daoAlumno.leerAlumnos();

			for (int i = 0; i < daoRetiro.tamaño(); i++) {
				Retiro r = daoRetiro.obtener(i);
				Matricula m = daoMatricula.buscar(r.getNumMatricula());

				Object codAlumno = (m != null) ? m.getCodAlumno() : "-";
				Object codCurso  = (m != null) ? m.getCodCurso() : "-";

				String estadoTexto = "Retirado";
				if (m != null) {
					Alumno a = daoAlumno.buscar(m.getCodAlumno());
					if (a != null) {
						if (a.getEstado() == 2) estadoTexto = "Retirado (2)";
						else if (a.getEstado() == 1) estadoTexto = "Matriculado (1)";
						else estadoTexto = "Registrado (0)";
					}
				}

				model.addRow(new Object[] {
						r.getNumRetiro(),
						r.getNumMatricula(),
						codAlumno,
						codCurso,
						r.getFecha(),
						r.getHora(),
						estadoTexto
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblContador.setText("Total de retiros: " + daoRetiro.tamaño());
	}

	void adicionarRetiro() {
		try {
			daoRetiro.leerRetiros();
			daoMatricula.leerMatriculas();
			daoAlumno.leerAlumnos();

			int numRetiro = Integer.parseInt(textNumRetiro.getText().trim());
			int numMatricula = Integer.parseInt(textNumMatricula.getText().trim());

			Matricula matricula = daoMatricula.buscar(numMatricula);
			if (matricula == null) {
				error("El número de matrícula no existe", textNumMatricula);
				return;
			}

			if (daoRetiro.buscarPorMatricula(numMatricula) != null) {
				error("La matrícula N° " + numMatricula + " ya se encuentra retirada", textNumMatricula);
				return;
			}

			Alumno alumno = daoAlumno.buscar(matricula.getCodAlumno());
			if (alumno == null) {
				error("El alumno vinculado a esta matrícula no existe", textNumMatricula);
				return;
			}

			if (alumno.getEstado() == 2) {
				error("El alumno ya tiene condición de RETIRADO", textNumMatricula);
				return;
			}

			actualizarFechaHoraSistema();
			String fecha = textFecha.getText().trim();
			String hora = textHora.getText().trim();

			Retiro nuevoRetiro = new Retiro(numRetiro, numMatricula, fecha, hora);
			daoRetiro.adicionar(nuevoRetiro);
			daoRetiro.grabarRetiros();

			// Regla: Desactivar temporalmente fijando el estado del alumno en 2 (retirado)
			alumno.setEstado(2);
			daoAlumno.grabarAlumnos();

			listar();
			mensaje("Retiro registrado con éxito. Estado del alumno cambiado a Retirado (2).");

			limpiarCampos();
			textNumRetiro.setText("" + daoRetiro.codigoCorrelativo());
			actualizarFechaHoraSistema();
			textNumMatricula.requestFocus();

		} catch (NumberFormatException e) {
			error("Ingrese un número de matrícula válido", textNumMatricula);
		} catch (Exception e) {
			mensaje("Error al procesar el retiro: " + e.getMessage());
		}
	}

	void buscarRetiro() {
		try {
			daoRetiro.leerRetiros();
			daoMatricula.leerMatriculas();

			int numRetiro = Integer.parseInt(textNumRetiro.getText().trim());
			Retiro r = daoRetiro.buscar(numRetiro);

			if (r != null) {
				textNumMatricula.setText("" + r.getNumMatricula());
				textFecha.setText(r.getFecha());
				textHora.setText(r.getHora());

				Matricula m = daoMatricula.buscar(r.getNumMatricula());
				if (m != null) {
					textCodCurso.setText("" + m.getCodCurso());
				}

				deshabilitarCampos();
			} else {
				error("El número de retiro " + numRetiro + " no existe", textNumRetiro);
			}
		} catch (Exception e) {
			error("Ingrese un número de retiro válido", textNumRetiro);
		}
	}

	void cargarRetiroParaModificar() {
		try {
			daoRetiro.leerRetiros();
			daoMatricula.leerMatriculas();

			int numRetiro = Integer.parseInt(textNumRetiro.getText().trim());
			Retiro r = daoRetiro.buscar(numRetiro);

			if (r != null) {
				textNumMatricula.setText("" + r.getNumMatricula());
				textFecha.setText(r.getFecha());
				textHora.setText(r.getHora());

				Matricula m = daoMatricula.buscar(r.getNumMatricula());
				if (m != null) {
					textCodCurso.setText("" + m.getCodCurso());
				}

				// Regla: Sólo puede cambiar el curso de una matrícula desactivada
				textNumRetiro.setEnabled(false);
				textNumMatricula.setEnabled(false);
				textFecha.setEnabled(false);
				textHora.setEnabled(false);

				textCodCurso.setEnabled(true);
				textCodCurso.requestFocus();

				retiroCargadoParaModificar = true;
			} else {
				error("El número de retiro no existe", textNumRetiro);
			}
		} catch (Exception e) {
			error("Ingrese un número de retiro válido", textNumRetiro);
		}
	}

	void modificarCursoMatriculaDesactivada() {
		try {
			daoCurso.leerCursos();
			daoMatricula.leerMatriculas();

			int nuevoCodCurso = Integer.parseInt(textCodCurso.getText().trim());

			Curso curso = daoCurso.buscar(nuevoCodCurso);
			if (curso == null) {
				error("El código del curso no existe", textCodCurso);
				return;
			}

			int numMatricula = Integer.parseInt(textNumMatricula.getText().trim());
			Matricula m = daoMatricula.buscar(numMatricula);

			if (m != null) {
				m.setCodCurso(nuevoCodCurso);
				daoMatricula.grabarMatriculas();

				listar();
				mensaje("Curso de la matrícula desactivada actualizado exitosamente.");
				actionPerformedBtnCancelar(null);
			} else {
				error("No se encontró la matrícula vinculada", textNumMatricula);
			}

		} catch (Exception e) {
			error("Ingrese un código de curso correcto", textCodCurso);
		}
	}

	void eliminarRetiro() {
		try {
			daoRetiro.leerRetiros();
			daoMatricula.leerMatriculas();
			daoAlumno.leerAlumnos();

			int numRetiro = Integer.parseInt(textNumRetiro.getText().trim());
			Retiro r = daoRetiro.buscar(numRetiro);

			if (r == null) {
				error("El número de retiro no existe", textNumRetiro);
				return;
			}

			Matricula m = daoMatricula.buscar(r.getNumMatricula());
			if (m == null) {
				error("Matrícula asociada no encontrada", textNumRetiro);
				return;
			}

			Alumno alumno = daoAlumno.buscar(m.getCodAlumno());

			// Regla: Puede cancelar un retiro sólo si el estado del alumno es 2 (retirado)
			if (alumno == null || alumno.getEstado() != 2) {
				mensaje("No se puede cancelar el retiro: El alumno no se encuentra en estado RETIRADO (2).");
				return;
			}

			// Regla: Cancelación física previa confirmación
			int confirmacion = JOptionPane.showConfirmDialog(
					this,
					"¿Está seguro de CANCELAR físicamente el retiro N° " + numRetiro + "?",
					"Confirmación de Cancelación de Retiro",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);

			if (confirmacion == JOptionPane.YES_OPTION) {
				daoRetiro.eliminar(r);
				daoRetiro.grabarRetiros();

				// Al cancelar el retiro, el alumno vuelve al estado 1 (Matriculado)
				alumno.setEstado(1);
				daoAlumno.grabarAlumnos();

				listar();
				mensaje("Retiro cancelado físicamente. El alumno vuelve a estar Matriculado (1).");
				actionPerformedBtnCancelar(null);
			}

		} catch (Exception e) {
			error("Ingrese un número de retiro válido", textNumRetiro);
		}
	}

	// ================================================================
	// ============ MÉTODOS DE APOYO =================================
	// ================================================================

	void deshabilitarCampos() {
		textNumRetiro.setEnabled(false);
		textNumMatricula.setEnabled(false);
		textCodCurso.setEnabled(false);
		textFecha.setEnabled(false);
	}

	void limpiarCampos() {
		textNumRetiro.setText("");
		textNumMatricula.setText("");
		textCodCurso.setText("");
		textFecha.setText("");
		textHora.setText("");
	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}
}