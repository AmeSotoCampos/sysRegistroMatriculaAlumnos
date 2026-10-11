/**
Descripción: FrmPrincipal.java, es la clase principal del sistema de registro y matricula de alumnos, que contiene el menú principal y los paneles correspondientes a cada módulo del sistema.
@author America Soto Campos
@version 1.0
@since 4/10/2026
*/

package com.sysRegistroMatriculaAlumnos.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class FrmPrincipal extends JFrame implements ActionListener {
	
    private static final long serialVersionUID = 1L;

    // Colores 
    private static final Color C_PRIMARIO = LoginFrame.C_PRIMARIO;
    private static final Color C_HEADER   = LoginFrame.C_HEADER;
    private static final Color C_FONDO    = LoginFrame.C_FONDO;

 
    public static final String K_ALUMNO                = "Alumno";
    public static final String K_CURSO                 = "Curso";
    public static final String K_MATRICULA             = "Matricula";
    public static final String K_RETIRO                = "Retiro";
    public static final String K_ALUMNOCURSO           = "Alumnocurso";
    public static final String K_MATRICULARETIRO       = "matricularetiro";
    public static final String K_MATRICULAPENDIENTE    = "matriculapendiente";
    public static final String K_MATRICULAVIGENTE      = "matriculavigente";
    public static final String K_MATRICULADOSPORCURSO  = "matriculadoxcurso";
    
    
    //  Panel principal 
    private JPanel panelMain;
    
    //  Menú 
    private JMenu menuMantenimiento;
    private JMenu menuRetiro;
    private JMenu menuRegistro;
    private JMenu menuConsulta;
    private JMenu menuReporte;

    // Menú items 
    private JMenuItem itemAlumno;
    private JMenuItem itemCurso;
    private JMenuItem itemMatricula;
    private JMenuItem itemRetiro;
    private JMenuItem itemAlumnocursos;
    private JMenuItem itemMatricularetiros;
    private JMenuItem itemAlumnosMatriculaPendiente;
    private JMenuItem itemAlumnosMatriculaVigente;
    private JMenuItem itemAlumnosMatriculadosXcurso;
   
 

    // Botones header top
    private JButton btnCerrarSesion;
    private JButton btnSalir;

    public FrmPrincipal() {
    	setBackground(new Color(0, 0, 153));
    	setSize(1366, 768); 
    	
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cibertec.com — Sistema de Registro y Matricula de Alumnos v2.0");

        JPanel contentpane = new JPanel(new BorderLayout());
        contentpane.addMouseWheelListener(new MouseWheelListener() {
        	public void mouseWheelMoved(MouseWheelEvent e) {
        	}
        });
        contentpane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentpane);

        contentpane.add(header(), BorderLayout.NORTH);
        contentpane.add(footer(), BorderLayout.SOUTH);

        panelMain = new JPanel(new CardLayout());
        contentpane.add(panelMain, BorderLayout.CENTER);

      
        panelMain.add(new PnlAlumno(),              K_ALUMNO);
        panelMain.add(new PnlCurso(),               K_CURSO);
        panelMain.add(new PnlMatricula(),           K_MATRICULA);
        panelMain.add(new PnlRetiro(),              K_RETIRO);
        panelMain.add(new PnlAlumnocurso(),         K_ALUMNOCURSO);
        panelMain.add(new PnlMatricularetiro(),     K_MATRICULARETIRO);
        panelMain.add(new Pnlmatriculapendiente(),  K_MATRICULAPENDIENTE);
        panelMain.add(new PnlMatriculavigente(),    K_MATRICULAVIGENTE);
        panelMain.add(new PnlMatriculadosxcurso(),  K_MATRICULADOSPORCURSO);
 
        mostrar(K_ALUMNO);
        
        
    }

    private JPanel header() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(C_HEADER);
        header.setPreferredSize(new Dimension(0, 170));

        // top bar
        JPanel barraTop = new JPanel();
        barraTop.setBackground(C_HEADER);
        barraTop.setPreferredSize(new Dimension(0, 50));
        barraTop.setLayout(new BoxLayout(barraTop, BoxLayout.X_AXIS));
        barraTop.setBorder(new EmptyBorder(0, 10, 0, 10));

        JLabel lblLogo = new JLabel(" Adiministrador");
        try {
            ImageIcon iconHomeOrig = new ImageIcon(getClass().getResource("/icons/school_24.png"));
            Image iconHomeEsc = iconHomeOrig.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(iconHomeEsc));
        } catch (Exception e) {}
        
        lblLogo.setFont(new Font("Consolas", Font.BOLD, 24));
        lblLogo.setForeground(Color.WHITE);

        JLabel lblUsuario = new JLabel("Usuario: admin");
        lblUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));
        lblUsuario.setForeground(new Color(0xBBDEFB));
        try {
            ImageIcon iconUsuarioO = new ImageIcon(getClass().getResource("/icons/person_24.png"));
            Image iconUsuarioS = iconUsuarioO.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblUsuario.setIcon(new ImageIcon(iconUsuarioS));
        } catch (Exception e) {}

        // Botón Cerrar Sesión 
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(0x1A237E));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Calibri", Font.BOLD, 14));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.addActionListener(this);

        // Botón Salir vuelve a Login
        btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(0xB71C1C));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Calibri", Font.BOLD, 14));
        btnSalir.setFocusPainted(false);
        try {
            ImageIcon iconSalir = new ImageIcon(getClass().getResource("/icons/exit_24.png"));
            btnSalir.setIcon(iconSalir);
        } catch (Exception e) {}
        btnSalir.addActionListener(this);

        barraTop.add(lblLogo);
        barraTop.add(Box.createHorizontalGlue());
        barraTop.add(lblUsuario);
        barraTop.add(Box.createHorizontalStrut(14));
        barraTop.add(btnCerrarSesion);
        barraTop.add(Box.createHorizontalStrut(8));
        barraTop.add(btnSalir);
        header.add(barraTop, BorderLayout.NORTH);

        // centro - header
        JPanel panelcentro = new JPanel();
        panelcentro.setBackground(new Color(0x0A3880));
        panelcentro.setPreferredSize(new Dimension(0, 72));
        panelcentro.setLayout(new BoxLayout(panelcentro, BoxLayout.X_AXIS));
        panelcentro.setBorder(new EmptyBorder(0, 20, 0, 20));
        
        JLabel lp1 = new JLabel("Descuento hasta " + "% por volumen");
        lp1.setForeground(Color.WHITE);
        lp1.setFont(new Font("Calibri", Font.BOLD, 14));
        try {
            ImageIcon iconDesctOrig = new ImageIcon(getClass().getResource("/icons/percent_24.png"));
            Image iconDesctScld = iconDesctOrig.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            lp1.setIcon(new ImageIcon(iconDesctScld));
        } catch (Exception e) {}
        JLabel sep1 = new JLabel("  |  "); sep1.setForeground(new Color(0x5C8FD6));
        
        JLabel lp2 = new JLabel("Obsequios por cada compra");
        lp2.setForeground(Color.WHITE);
        lp2.setFont(new Font("Calibri", Font.BOLD, 14));
        try {
            ImageIcon iconObsOrig = new ImageIcon(getClass().getResource("/icons/present_24.png"));
            Image iconObstEscld = iconObsOrig.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            lp2.setIcon(new ImageIcon(iconObstEscld));
        } catch (Exception e) {}
        JLabel sep2 = new JLabel("  |  "); sep2.setForeground(new Color(0x5C8FD6));
        
        JLabel lp3 = new JLabel("Los mejores smartphones — Precios desde S/ 299");
        lp3.setForeground(Color.WHITE);
        lp3.setFont(new Font("Calibri", Font.BOLD, 14));
        try {
            ImageIcon iconCelOrig = new ImageIcon(getClass().getResource("/icons/speaker_phone_24.png"));
            Image iconCelEsc = iconCelOrig.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            lp3.setIcon(new ImageIcon(iconCelEsc));
        } catch (Exception e) {}

        panelcentro.add(lp1);
        panelcentro.add(sep1);
        panelcentro.add(lp2);
        panelcentro.add(sep2);
        panelcentro.add(lp3);
        
        header.add(panelcentro, BorderLayout.CENTER);
        
        
        // menubar dentro de panelNav
        JPanel panelNav = new JPanel(new BorderLayout());
        panelNav.setBackground(C_PRIMARIO);
        panelNav.setPreferredSize(new Dimension(0, 46));
        header.add(panelNav, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(C_PRIMARIO);
        menuBar.setPreferredSize(new Dimension(20, 46));
        menuBar.setBorder(new EmptyBorder(0, 20, 0, 20));
        panelNav.add(menuBar, BorderLayout.SOUTH);

     // Módulo Mantenimiento
        menuMantenimiento = new JMenu(" Mantenimiento");
        menuMantenimiento.setBorder(new EmptyBorder(0, 0, 0, 25));
        itemAlumno = new JMenuItem("Alumno       ");
        itemAlumno.setForeground(new Color(255, 255, 255));
        itemAlumno.setBackground(new Color(220, 20, 60));
        itemAlumno.setBorder(new EmptyBorder(10, 40, 10, 0));
        itemCurso = new JMenuItem("Curso");
        itemCurso.setForeground(new Color(255, 255, 255));
        itemCurso.setBackground(new Color(220, 20, 60));
        itemCurso.setBorder(new EmptyBorder(10, 40, 10, 40));
        menuMantenimiento.add(itemAlumno);
        menuMantenimiento.add(itemCurso);
        
        menuMantenimiento.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuMantenimiento.setForeground(Color.WHITE);
        menuMantenimiento.setBackground(new Color(230, 81, 0));
        menuMantenimiento.setOpaque(false);
        
        // icon
        ImageIcon iconMant = new ImageIcon(
        	    getClass().getResource("/icons/handyman_24.png")
        	);

        menuMantenimiento.setIcon(iconMant);
        // fin icon
        
        ImageIcon iconAlum = new ImageIcon(
        	    getClass().getResource("/icons/student_24_FFFFFF.png")
        	);
        itemAlumno.setIcon(iconAlum);
        
        ImageIcon iconCurs = new ImageIcon(
        	    getClass().getResource("/icons/book_white_24.png")
        	);
        itemCurso.setIcon(iconCurs);
        	
        itemAlumno.setFont(new Font("Calibri", Font.PLAIN, 14));
        itemAlumno.addActionListener(this);
        itemCurso.setFont(new Font("Calibri", Font.PLAIN, 14));
        itemCurso.addActionListener(this);

        // Módulo Registro
        menuRegistro = new JMenu(" Registro");
        menuRegistro.setBorder(new EmptyBorder(0, 25, 0, 25));
        itemMatricula = new JMenuItem("Matrícula");
        itemMatricula.setBorder(new EmptyBorder(10, 40, 10, 40));
        itemMatricula.setForeground(new Color(255, 255, 255));
        itemMatricula.setBackground(new Color(220, 20, 60));
        itemRetiro = new JMenuItem("Retiro");
        itemRetiro.setBorder(new EmptyBorder(10, 40, 10, 40));
        itemRetiro.setBackground(new Color(220, 20, 60));
        itemRetiro.setForeground(new Color(255, 255, 255));
        menuRegistro.add(itemMatricula);
        menuRegistro.add(itemRetiro); 
        
        menuRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuRegistro.setForeground(Color.WHITE);
        menuRegistro.setBackground(C_PRIMARIO);
        menuRegistro.setOpaque(false);
        
        // icon  
        ImageIcon iconVent = new ImageIcon(
        	    getClass().getResource("/icons/register_24.png")
        	);
        menuRegistro.setIcon(iconVent);
        
        
        ImageIcon iconMat = new ImageIcon(
        	    getClass().getResource("/icons/matricula_24_FFFFFF.png")
        	);
        itemMatricula.setIcon(iconMat);
        
        ImageIcon iconRet = new ImageIcon(
        	    getClass().getResource("/icons/retiro_24_FFFFFF.png")
        	);
        itemRetiro.setIcon(iconRet);
        
        
        itemMatricula.setFont(new Font("Calibri", Font.PLAIN, 14));
        itemMatricula.addActionListener(this);
        itemRetiro.setFont(new Font("Calibri", Font.PLAIN, 14));
        itemRetiro.addActionListener(this);
        
        // Módulo de Consulta
        menuConsulta = new JMenu("Consulta");
        menuConsulta.setBorder(new EmptyBorder(0, 25, 0, 25));
        itemAlumnocursos = new JMenuItem("Alumnos y Cursos");
        itemAlumnocursos.setBorder(new EmptyBorder(10, 40, 10, 40));
        itemAlumnocursos.setForeground(new Color(255, 255, 255));
        itemAlumnocursos.setBackground(new Color(220, 20, 60));
        itemMatricularetiros = new JMenuItem("Matrícula y Retiros");
        itemMatricularetiros.setBorder(new EmptyBorder(10, 40, 10, 40));
        itemMatricularetiros.setForeground(new Color(255, 255, 255));
        itemMatricularetiros.setBackground(new Color(220, 20, 60));
        menuConsulta.add(itemAlumnocursos);
        menuConsulta.add(itemMatricularetiros);
        
        menuConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuConsulta.setForeground(Color.WHITE);
        menuConsulta.setBackground(C_PRIMARIO);
        menuConsulta.setOpaque(false);
        
        // icon  
        ImageIcon iconConfig = new ImageIcon(
        	    getClass().getResource("/icons/searchDcto_24.png")
        	);
        menuConsulta.setIcon(iconConfig);
        
        ImageIcon iconAlCur = new ImageIcon(
        	    getClass().getResource("/icons/buscAlum_24_FFFFFF.png")
        	);
        itemAlumnocursos.setIcon(iconAlCur);
        
        ImageIcon iconMatRet = new ImageIcon(
        	    getClass().getResource("/icons/document_search_24_FFFFFF.png")
        	);
        itemMatricularetiros.setIcon(iconMatRet);
        
        itemAlumnocursos.setFont(new Font("Calibri", Font.PLAIN, 14));
        itemAlumnocursos.addActionListener(this);
        itemMatricularetiros.setFont(new Font("Calibri", Font.PLAIN, 14));
        itemMatricularetiros.addActionListener(this);
        
        // Módulo de Reporte
        menuReporte = new JMenu(" Reportes");
        menuReporte.setBorder(new EmptyBorder(0, 25, 0, 25));
        itemAlumnosMatriculaPendiente = new JMenuItem("Matrícula Pendiente"); 
        itemAlumnosMatriculaPendiente.setBorder(new EmptyBorder(10, 40, 10, 40));
        itemAlumnosMatriculaPendiente.setBackground(new Color(220, 20, 60));
        itemAlumnosMatriculaPendiente.setForeground(new Color(255, 255, 255));
        itemAlumnosMatriculaVigente = new JMenuItem("Matrícula Vigente");
        itemAlumnosMatriculaVigente.setBorder(new EmptyBorder(10, 40, 10, 40));
        itemAlumnosMatriculaVigente.setBackground(new Color(220, 20, 60));
        itemAlumnosMatriculaVigente.setForeground(new Color(255, 255, 255));
        itemAlumnosMatriculadosXcurso = new JMenuItem("Matriculados por curso");
        itemAlumnosMatriculadosXcurso.setBorder(new EmptyBorder(10, 40, 10, 40));
        itemAlumnosMatriculadosXcurso.setBackground(new Color(220, 20, 60));
        itemAlumnosMatriculadosXcurso.setForeground(new Color(255, 255, 255));
        menuReporte.add(itemAlumnosMatriculaPendiente);
        menuReporte.add(itemAlumnosMatriculaVigente);
        menuReporte.add(itemAlumnosMatriculadosXcurso);
        
        // icon  
        ImageIcon iconAyuda = new ImageIcon(
        	    getClass().getResource("/icons/report_24.png")
        	);
        menuReporte.setIcon(iconAyuda);
        
        ImageIcon icon1 = new ImageIcon(
        	    getClass().getResource("/icons/matriPend_24_FFFFFF.png")
        	);
        itemAlumnosMatriculaPendiente.setIcon(icon1);
        
        ImageIcon icon2 = new ImageIcon(
        	    getClass().getResource("/icons/matriVig_24_FFFFFF.png")
        	);
        itemAlumnosMatriculaVigente.setIcon(icon2);
        
        ImageIcon icon3 = new ImageIcon(
        	    getClass().getResource("/icons/matriXcurso_24dp_FFFFFF.png")
        	);
        itemAlumnosMatriculadosXcurso.setIcon(icon3);
        
        menuReporte.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuReporte.setForeground(Color.WHITE);
        menuReporte.setBackground(C_PRIMARIO);
        menuReporte.setOpaque(false);
        
        
 
        itemAlumnosMatriculaPendiente.setFont(new Font("Calibri", Font.PLAIN, 14));
        itemAlumnosMatriculaPendiente.addActionListener(this);
        itemAlumnosMatriculaVigente.setFont(new Font("Calibri", Font.PLAIN, 14));
        itemAlumnosMatriculaVigente.addActionListener(this);
        
        itemAlumnosMatriculadosXcurso.setFont(new Font("Calibri", Font.PLAIN, 14));
        itemAlumnosMatriculadosXcurso.addActionListener(this);

        menuBar.add(menuMantenimiento);
        menuBar.add(menuRegistro);
        menuBar.add(menuConsulta);
        menuBar.add(menuReporte);
        return header;
    }
    
    private JPanel footer() {
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(0, 52));
        footer.setBackground(C_PRIMARIO);
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBorder(new EmptyBorder(6, 20, 6, 20));
    	
        JLabel l1 = new JLabel("Cobra Soluciones.Java  |  EduSystem.com — Sistema de Registro y Matricula v2.0");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Calibri", Font.BOLD, 13));
        
        JLabel l2 = new JLabel("soporte: 800 600 2000  |  CIBERTEC — Algoritmia y Estructura de Datos 2026");
        l2.setForeground(new Color(0xBBDEFB));
        l2.setFont(new Font("Calibri", Font.PLAIN, 11));
        
        footer.add(l1);
        footer.add(l2);
        return footer;
    }

    public void mostrar(String key) {
        ((CardLayout) panelMain.getLayout()).show(panelMain, key);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmPrincipal frame = new FrmPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if      (src == btnCerrarSesion)   { new LoginFrame().setVisible(true); dispose(); }
        else if (src == btnSalir)          { System.exit(0); }
        else if (src == itemAlumno)      mostrar(K_ALUMNO);
        else if (src == itemCurso)        mostrar(K_CURSO);
        else if (src == itemMatricula) mostrar(K_MATRICULA);
        else if (src == itemRetiro)     mostrar(K_RETIRO);
        else if (src == itemAlumnocursos)     mostrar(K_ALUMNOCURSO);
        else if (src == itemMatricularetiros) mostrar(K_MATRICULARETIRO);
        else if (src == itemAlumnosMatriculaPendiente)         mostrar(K_MATRICULAPENDIENTE);
        else if (src == itemAlumnosMatriculaVigente)         mostrar(K_MATRICULAVIGENTE);
        else if (src == itemAlumnosMatriculadosXcurso)         mostrar(K_MATRICULADOSPORCURSO);
    }
}

