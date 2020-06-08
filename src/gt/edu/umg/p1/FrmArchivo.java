package gt.edu.umg.p1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FrmArchivo extends JFrame {
	
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_2;
	private DefaultTableModel tableModel;
	private JTextField nombres;
	private JTextField apellidos;
	private JTextField fecNac;
	private JTextField correo;
	private JTextField txtTel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmArchivo frame = new FrmArchivo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int getSumaAscii(String linea) {
		int total = 0;
		for (int i = 0; i < linea.length(); i++) {
			char c = linea.charAt(i);
			int ascii = (int) c;
			total += ascii;
		}
		int id = (total % 50);
		return id;
	}
	
	private boolean verificarColision(int id) {
		boolean existe = false;
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			String valor = tableModel.getValueAt(i, 0).toString();
			if (id == Integer.parseInt(valor)) {
				existe = true;
				break;
			}
		}
		return existe;
	}

	public FrmArchivo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Carnet");
		tableModel.addColumn("Nombres");
		tableModel.addColumn("Apellidos");
		tableModel.addColumn("Fecha de Nacimiento");
		tableModel.addColumn("Teléfono");
		tableModel.addColumn("Correo");
		table = new JTable();
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setBounds(59, 257, 460, 86);
		table.setModel(tableModel);
		contentPane.add(table);
		
		
		JLabel lblNewLabel = new JLabel("Bienvenido a la App de registros en Base de Datos.!");
		lblNewLabel.setForeground(SystemColor.activeCaptionText);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(48, 11, 499, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u00BFQu\u00E9 desea hacer?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(34, 53, 149, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Agregar un registro:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(325, 92, 128, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Agregar una nueva entidad:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(293, 191, 178, 22);
		contentPane.add(lblNewLabel_3);
		
		
		
		JButton btnIniciar1 = new JButton("AGREGAR");
		btnIniciar1.addActionListener(new ActionListener() {
			private JLabel txtApellidos;
			private JLabel txtNombres;
			private JLabel txtFechaNacimiento;
			private JLabel txtTelefono;
			private JLabel txtCorreo;

			public void actionPerformed(ActionEvent e) {
				txtNombres = null;
				int carnet = getSumaAscii(txtNombres.getText().trim());
				if (verificarColision(carnet)) {
					JOptionPane.showMessageDialog(null, "El Dato ya existe en la lista", "Duplicidad de informacion", JOptionPane.ERROR_MESSAGE);
				} else {
					DatosAlumno da = new DatosAlumno();
					int carnet1 = 0;
					da.setCarnet(carnet1);
					da.setNombres(txtNombres.getText().trim());
					da.setApellidos(txtApellidos.getText().trim());
					Date fechaNacimiento = null;
					da.setFechaNacimiento(fechaNacimiento);
					int telefono = 0;
					da.setTelefono(telefono);
					JLabel txtCorreo=null;
					da.setCorreo(txtCorreo.getText().trim());
					tableModel.addRow(new Object[] {da.getCarnet(), da.getNombres(), da.getApellidos(), da.getFechaNacimiento(), da.getTelefono(), da.getCorreo(),});
					txtNombres.setText("");
					txtApellidos.setText("");
					txtFechaNacimiento.setText("");
					txtTelefono.setText("");
					txtCorreo.setText("");
				}
			}
		});
		
		
		btnIniciar1.setBackground(Color.WHITE);
		btnIniciar1.setForeground(Color.BLUE);
		btnIniciar1.setBounds(463, 94, 89, 23);
		contentPane.add(btnIniciar1);
		
		JButton btnIniciar2 = new JButton("AGREGAR");
		btnIniciar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					}
		
			
		});
		
		
		btnIniciar2.setBackground(Color.WHITE);
		btnIniciar2.setForeground(Color.BLUE);
		btnIniciar2.setBounds(481, 193, 89, 23);
		contentPane.add(btnIniciar2);
		
		table_2 = new JTable();
		table_2.setBounds(59, 354, 460, 86);
		contentPane.add(table_2);
		
		JLabel lblNewLabel_4 = new JLabel("Ingrese nombres");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(34, 119, 102, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ingrese Apellidos");
		lblNewLabel_5.setBounds(34, 144, 89, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_6.setBounds(34, 169, 102, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Ingrese Telefono");
		lblNewLabel_7.setBounds(29, 197, 94, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Ingrese Correo");
		lblNewLabel_8.setBounds(34, 232, 83, 14);
		contentPane.add(lblNewLabel_8);
		
		nombres = new JTextField();
		nombres.setBounds(131, 116, 86, 20);
		contentPane.add(nombres);
		nombres.setColumns(10);
		
		apellidos = new JTextField();
		apellidos.setBounds(131, 141, 86, 20);
		contentPane.add(apellidos);
		apellidos.setColumns(10);
		
		fecNac = new JTextField();
		fecNac.setBounds(141, 166, 86, 20);
		contentPane.add(fecNac);
		fecNac.setColumns(10);
		
		correo = new JTextField();
		correo.setBounds(131, 227, 86, 20);
		contentPane.add(correo);
		correo.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(131, 194, 86, 20);
		contentPane.add(txtTel);
		txtTel.setColumns(10);
	}
}
