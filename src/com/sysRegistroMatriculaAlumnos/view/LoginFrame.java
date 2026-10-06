package com.sysRegistroMatriculaAlumnos.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;


    static final Color C_PRIMARIO = new Color(0x0D47A1);
    static final Color C_HEADER   = new Color(0x1565C0);
    static final Color C_FONDO    = new Color(0xE3F0FF);

    private JTextField     textUsuario;
    private JPasswordField passwordField;
    private JButton        btnIngresar;

   
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try { 
                LoginFrame frame = new LoginFrame();                                       
                frame.setVisible(true); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            }
        });
    }

    public LoginFrame() {
        setTitle("Sistema de Registro y Matrícula V1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa nativa
        getContentPane().setLayout(new BorderLayout());

        // HEADER
        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setPreferredSize(new Dimension(0, 80));
        panelHeader.setBackground(C_HEADER);
        panelHeader.setBorder(new EmptyBorder(0, 30, 0, 30));

        JLabel lblLogo = new JLabel("Cibertec Matrículas");
        lblLogo.setFont(new Font("Consolas", Font.BOLD, 30));
        lblLogo.setForeground(Color.WHITE);

        JLabel lblSlogan = new JLabel("Gestión Académica v1.0");
        lblSlogan.setFont(new Font("Calibri", Font.PLAIN, 15));
        lblSlogan.setForeground(new Color(0xBBDEFB));

        panelHeader.add(lblLogo,   BorderLayout.WEST);
        panelHeader.add(lblSlogan, BorderLayout.EAST);
        getContentPane().add(panelHeader, BorderLayout.NORTH);

        // FOOTER 
        JPanel panelFooter = new JPanel();
        panelFooter.setPreferredSize(new Dimension(0, 50));
        panelFooter.setBackground(C_PRIMARIO);
        panelFooter.setLayout(new BoxLayout(panelFooter, BoxLayout.Y_AXIS));
        panelFooter.setBorder(new EmptyBorder(5, 20, 5, 20));

        JLabel lblEmpresa = new JLabel("Área de Desarrollo  |  Soporte Técnico Académico");
        lblEmpresa.setForeground(new Color(0xBBDEFB));
        lblEmpresa.setFont(new Font("Calibri", Font.PLAIN, 13));
        panelFooter.add(lblEmpresa);
        getContentPane().add(panelFooter, BorderLayout.SOUTH);

        // MAIN 
        JPanel panelMain = new JPanel(new GridBagLayout());
        panelMain.setBackground(C_FONDO);
        getContentPane().add(panelMain, BorderLayout.CENTER);

        // FORMULARIO 
        JPanel panelForm = new JPanel(new BorderLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setPreferredSize(new Dimension(400, 250));
        panelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(C_PRIMARIO, 2),
            new EmptyBorder(25, 40, 25, 40)
        ));

        JLabel lblTitulo = new JLabel("Iniciar Sesión", JLabel.CENTER);
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 22));
        lblTitulo.setForeground(C_PRIMARIO);
        lblTitulo.setBorder(new EmptyBorder(0, 0, 18, 0));
        panelForm.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 14));
        panelCampos.setBackground(Color.WHITE);

        JLabel lblU = new JLabel("Usuario:");
        lblU.setFont(new Font("Calibri", Font.PLAIN, 15));
        textUsuario = new JTextField();
        textUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));

        JLabel lblC = new JLabel("Contraseña:");
        lblC.setFont(new Font("Calibri", Font.PLAIN, 15));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Calibri", Font.PLAIN, 14));

        panelCampos.add(lblU);
        panelCampos.add(textUsuario);
        panelCampos.add(lblC);
        panelCampos.add(passwordField);
        panelCampos.add(new JLabel());

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(C_PRIMARIO);
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Calibri", Font.BOLD, 14));
        btnIngresar.setFocusPainted(false);
        btnIngresar.addActionListener(this);
        panelCampos.add(btnIngresar);

        panelForm.add(panelCampos, BorderLayout.CENTER);
        panelMain.add(panelForm);

        
        getRootPane().setDefaultButton(btnIngresar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresar) {
            String usuario = textUsuario.getText().trim();
            String clave   = new String(passwordField.getPassword()).trim();

            if (usuario.equals("admin") && clave.equals("1234")) {
                
            
                FrmPrincipal sistema = new FrmPrincipal();
                sistema.setVisible(true);
                
              
                dispose(); 
                
            } else {
                JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña incorrectos.",
                    "Acceso denegado", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
                passwordField.requestFocus();
            }
        }
    }
}
