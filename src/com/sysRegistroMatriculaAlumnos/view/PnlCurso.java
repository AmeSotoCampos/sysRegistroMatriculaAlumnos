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

public class PnlCurso extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	// COLORES
	private static final Color C_PRIMARIO = LoginFrame.C_PRIMARIO;
	private static final Color C_HEADER = LoginFrame.C_HEADER;
	private static final Color C_FONDO = LoginFrame.C_FONDO;


	
	private JTable table;
	private JTextField textCodigoC;
	private JTextField textAsignatura;
	private JTextField textCiclo;
	private JTextField textCreditos;
	private JTextField textHoras;
	private JTextField textEstado;
	
	private JLabel lblContador;

	public PnlCurso() {
		setLayout(new BorderLayout(0, 0));
		
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
		
		// Asignatura
		JLabel lblAsignatura = new JLabel("Asignatura");
		lblAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblAsignatura = new GridBagConstraints();
		gbc_lblAsignatura.anchor = GridBagConstraints.WEST;
		gbc_lblAsignatura.insets = new Insets(0, 0, 5, 0);
		gbc_lblAsignatura.gridx = 0;
		gbc_lblAsignatura.gridy = 2;
		panelColumna1.add(lblAsignatura, gbc_lblAsignatura);
		
		textAsignatura = new JTextField();
		textAsignatura.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textAsignatura = new GridBagConstraints();
		gbc_textAsignatura.insets = new Insets(0, 0, 5, 0);
		gbc_textAsignatura.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAsignatura.gridx = 0;
		gbc_textAsignatura.gridy = 3;
		panelColumna1.add(textAsignatura, gbc_textAsignatura);
		textAsignatura.setColumns(10);
		
		
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
		
		
		// Ciclo
		JLabel lblCiclo = new JLabel("Ciclo");
		lblCiclo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCiclo = new GridBagConstraints();
		gbc_lblCiclo.anchor = GridBagConstraints.WEST;
		gbc_lblCiclo.insets = new Insets(0, 0, 5, 0);
		gbc_lblCiclo.gridx = 0;
		gbc_lblCiclo.gridy = 0;
		panelColumna2.add(lblCiclo, gbc_lblCiclo);
		
		textCiclo = new JTextField();
		textCiclo.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textCiclo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textCiclo = new GridBagConstraints();
		gbc_textCiclo.insets = new Insets(0, 0, 10, 0);
		gbc_textCiclo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCiclo.gridx = 0;
		gbc_textCiclo.gridy = 1;
		panelColumna2.add(textCiclo, gbc_textCiclo);
		textCiclo.setColumns(10);
		
		// Creditos
		JLabel lblCreditos = new JLabel("Nro. Creditos");
		lblCreditos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCreditos = new GridBagConstraints();
		gbc_lblCreditos.anchor = GridBagConstraints.WEST;
		gbc_lblCreditos.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreditos.gridx = 0;
		gbc_lblCreditos.gridy = 2;
		panelColumna2.add(lblCreditos, gbc_lblCreditos);
		
		textCreditos = new JTextField();
		textCreditos.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textCreditos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textCreditos = new GridBagConstraints();
		gbc_textCreditos.insets = new Insets(0, 0, 5, 0);
		gbc_textCreditos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCreditos.gridx = 0;
		gbc_textCreditos.gridy = 3;
		panelColumna2.add(textCreditos, gbc_textCreditos);
		textCreditos.setColumns(10);
		
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
				getClass().getResource("/icons/people__24_FFFFFF.png")
		);
		lblContador.setIcon(iconcont);
		
		
		// COLUMNA 3
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
		JLabel lblbHoras = new JLabel("Cant. Horas");
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
		
		
		// estado
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.anchor = GridBagConstraints.WEST;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 0);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 2;
		panelColumna3.add(lblEstado, gbc_lblEstado);
		
		textEstado = new JTextField();
		textEstado.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 0)));
		textEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_textEstado = new GridBagConstraints();
		gbc_textEstado.insets = new Insets(0, 0, 5, 0);
		gbc_textEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEstado.gridx = 0;
		gbc_textEstado.gridy = 3;
		panelColumna3.add(textEstado, gbc_textEstado);
		textEstado.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("aqui estoy");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		panelColumna3.add(lblNewLabel, gbc_lblNewLabel);
		
	
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
				"Asignatura", 
				"Ciclo", 
				"Creditos", 
				"Horas", 
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
