package interfazUsuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import accesoDatos.I_Acceso_Datos;
import controlador.Controlador;
import entidades.Alumno;
import entidades.Titulacion;


public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;
	private I_Acceso_Datos modeloPrincipal;
	private JTable table;
	private JTextField phDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField phTelefono;
	private JTextField phNacionalidad;
	private JTextField txtNombreMod;
	private JTextField txtApellidoMod;
	private JTextField txtTelefonoMod;
	private JTextField txtNacionalidadMod;

	private TableRowSorter trOrden;
	private JTextField jtxtBuscarDni;
	private JTextField txtImportar;
	private JTextField txtfExportar;
	private JTextField txtNombreCurso;
	private JTextField txtDescripcionCurso;
	private JTextField txtTitulacion;
	//private TextPrompt txtTitulacion;
	private JTextField txtTitulacionMod;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblAadirAlumno = new JLabel("A\u00F1adir Alumno");
		lblAadirAlumno.setFont(new Font("Tahoma", Font.BOLD, 15));

		phDni = new JTextField();
		TextPrompt placeholder = new TextPrompt("DNI", phDni);
		phDni.setColumns(10);
		placeholder.changeAlpha(0.50f);
		placeholder.changeStyle(Font.ITALIC);

		txtNombre = new JTextField();
		TextPrompt phNombreAlumno = new TextPrompt("Nombre", txtNombre);
		txtNombre.setColumns(10);
		phNombreAlumno.changeAlpha(0.50f);
		phNombreAlumno.changeStyle(Font.ITALIC);

		txtApellido = new JTextField();
		TextPrompt phApellido = new TextPrompt("Apellido", txtApellido);
		txtApellido.setColumns(10);
		phApellido.changeAlpha(0.50f);
		phApellido.changeStyle(Font.ITALIC);

		phTelefono = new JTextField();
		TextPrompt placeholder4 = new TextPrompt("Telefono", phTelefono);
		phTelefono.setColumns(10);
		placeholder4.changeAlpha(0.50f);
		placeholder4.changeStyle(Font.ITALIC);

		phNacionalidad = new JTextField();
		TextPrompt placeholder5 = new TextPrompt("Nacionalidad", phNacionalidad);
		phNacionalidad.setColumns(10);
		placeholder5.changeAlpha(0.50f);
		placeholder5.changeStyle(Font.ITALIC);

		JButton btnAnadirAlumno = new JButton("A\u00F1adir");
		btnAnadirAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controlador.nuevoAlumno();
			}
		});

		JLabel lblModificarDatos = new JLabel("Modificar Datos");
		lblModificarDatos.setFont(new Font("Tahoma", Font.BOLD, 15));

		txtNombreMod = new JTextField();
		txtNombreMod.setColumns(10);

		txtApellidoMod = new JTextField();
		txtApellidoMod.setColumns(10);

		txtTelefonoMod = new JTextField();
		txtTelefonoMod.setColumns(10);

		txtNacionalidadMod = new JTextField();
		txtNacionalidadMod.setColumns(10);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controlador.actualizarAlumno();
			}
		});

		JSeparator separator = new JSeparator();

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controlador.eliminarUno();
			}
		});

		JButton btnNewButton = new JButton("Examinar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creamos el objeto JFileChooser
				JFileChooser fc = new JFileChooser();

				// Indicamos lo que podemos seleccionar
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				// Creamos el filtro

				// Abrimos la ventana, guardamos la opcion seleccionada por el
				// usuario
				int seleccion = fc.showOpenDialog(contentPane);

				// Si el usuario, pincha en aceptar
				if (seleccion == JFileChooser.APPROVE_OPTION) {

					// Seleccionamos el fichero
					File fichero = fc.getSelectedFile();

					// Ecribe la ruta del fichero seleccionado en el campo de
					// texto
					txtImportar.setText(fichero.getAbsolutePath());

				}

			}
		});

		JButton btnEliminarTodos = new JButton("Eliminar Todos");
		btnEliminarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controlador.eliminarTodos();
			}
		});

		jtxtBuscarDni = new JTextField();
		jtxtBuscarDni.setColumns(10);
		jtxtBuscarDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				jtxtBuscarDni.addKeyListener(new KeyAdapter() {
					public void keyReleased(final KeyEvent arg0) {

						filtroDni();

					}

					public void filtroDni() {
						trOrden.setRowFilter(RowFilter.regexFilter(jtxtBuscarDni.getText(), 0));

					}
				});
				trOrden = new TableRowSorter(table.getModel());
				table.setRowSorter(trOrden);
			}
		});

		JLabel lblBuscadorDni = new JLabel("Buscador DNI:");

		JButton btnSubirFicher = new JButton("Exportar");
		btnSubirFicher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String tipoReceptor = controlador.tipoAcceso();
				//controlador.tipoExportar(tipoReceptor);
			}
		});

		txtImportar = new JTextField();
		txtImportar.setColumns(10);

		JLabel lblImportarDesde = new JLabel("Importar desde:");

		JButton button = new JButton("Examinar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Creamos el objeto JFileChooser
				JFileChooser fc = new JFileChooser();

				// Indicamos lo que podemos seleccionar
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				// Creamos el filtro

				// Abrimos la ventana, guardamos la opcion seleccionada por el
				// usuario
				int seleccion = fc.showOpenDialog(contentPane);

				// Si el usuario, pincha en aceptar
				if (seleccion == JFileChooser.APPROVE_OPTION) {

					// Seleccionamos el fichero
					File fichero = fc.getSelectedFile();

					// Ecribe la ruta del fichero seleccionado en el campo de
					// texto
					txtfExportar.setText(fichero.getAbsolutePath());

				}

			}
		});

		txtfExportar = new JTextField();
		txtfExportar.setColumns(10);

		JLabel lblExportarA = new JLabel("Exportar a:");
		
		JButton btnExportar = new JButton("Exportar");
		
		JLabel lblAadirCurso = new JLabel("Añadir curso");
		lblAadirCurso.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		
		
		txtNombreCurso = new JTextField();
		TextPrompt placeholderCurso = new TextPrompt("Nombre", txtNombreCurso);
		txtNombreCurso.setColumns(10);
		placeholderCurso.changeAlpha(0.50f);
		placeholderCurso.changeStyle(Font.ITALIC);
		
		
		txtDescripcionCurso = new JTextField();
		TextPrompt placeholderDescripcion = new TextPrompt("Descripcion", txtDescripcionCurso);
		txtDescripcionCurso.setColumns(10);
		placeholderDescripcion.changeAlpha(0.50f);
		placeholderDescripcion.changeStyle(Font.ITALIC);
		
		JButton button_1 = new JButton("Añadir");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.nuevoCurso();
			}
		});
		
	//ASI NO!
//		textField = new JTextField();
//		txtTitulacion = new TextPrompt("Titulacion", textField);
//		txtTitulacion.setColumns(10);
//		placeholderCurso.changeAlpha(0.50f);
//		placeholderCurso.changeStyle(Font.ITALIC);
		
	
		
		txtTitulacion = new JTextField();
		TextPrompt placeholderTitulacion = new TextPrompt("Titulacion", txtTitulacion);
		txtTitulacion.setColumns(10);
		placeholderTitulacion.changeAlpha(0.50f);
		placeholderTitulacion.changeStyle(Font.ITALIC);
		
		txtTitulacionMod = new JTextField();
		txtTitulacionMod.setColumns(10);
		
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(525, Short.MAX_VALUE)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtDescripcionCurso, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblImportarDesde, 0, 0, Short.MAX_VALUE)
														.addGap(18)
														.addComponent(txtImportar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblBuscadorDni, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
														.addGap(24)
														.addComponent(jtxtBuscarDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblExportarA, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(txtfExportar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(button, Alignment.TRAILING)
												.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
											.addGap(1)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(btnSubirFicher)
												.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
											.addGap(12)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblAadirCurso, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtNombreCurso, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(lblAadirAlumno))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(phDni, 242, 254, Short.MAX_VALUE)
										.addComponent(txtNombre, 242, 254, Short.MAX_VALUE)
										.addComponent(txtApellido, 242, 254, Short.MAX_VALUE)
										.addComponent(phTelefono, 242, 254, Short.MAX_VALUE)
										.addComponent(phNacionalidad, 242, 254, Short.MAX_VALUE)
										.addComponent(lblModificarDatos, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
										.addComponent(txtNombreMod, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtApellidoMod, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTelefonoMod, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(151))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtNacionalidadMod, Alignment.LEADING)
											.addComponent(txtTitulacionMod, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnEliminarTodos, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))))
								.addComponent(txtTitulacion, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAnadirAlumno, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAadirAlumno)
							.addGap(2)
							.addComponent(phDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(phTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(phNacionalidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTitulacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAnadirAlumno)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblModificarDatos, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(txtNombreMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtApellidoMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtTelefonoMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtNacionalidadMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTitulacionMod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnModificar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAadirCurso, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNombreCurso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDescripcionCurso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_1)))
					.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnEliminarTodos)
								.addComponent(txtImportar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton)
								.addComponent(lblImportarDesde)
								.addComponent(btnExportar))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtxtBuscarDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBuscadorDni))
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button)
								.addComponent(txtfExportar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblExportarA)
								.addComponent(btnSubirFicher)
								.addComponent(btnEliminar))
							.addGap(79))))
		);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				txtNombreMod.setText((String) table.getValueAt(table.getSelectedRow(), 2));
				txtApellidoMod.setText((String) table.getValueAt(table.getSelectedRow(), 3));
				txtTelefonoMod.setText((String) table.getValueAt(table.getSelectedRow(), 4));
				txtNacionalidadMod.setText((String) table.getValueAt(table.getSelectedRow(), 5));
				txtTitulacionMod.setText((String) table.getValueAt(table.getSelectedRow(), 6));
			}
		});

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	public JTable getTabla() {
		return table;
	}

	public void onLoadTable() {
		// controlador.MostrarTabla();
	}

	public TableModel getTablaInfo() {
		return table.getModel();
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloPrincipal(I_Acceso_Datos modeloPrincipal) {
		this.modeloPrincipal = modeloPrincipal;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public String getTxtDni() {
		return phDni.getText();
	}

	public String getTxtDniMod() {
		return phDni.getText();
	}

	public String getTxtNombre() {
		return txtNombre.getText();
	}

	public String getTxtApellido() {
		return txtApellido.getText();
	}

	public String getTxtTelefono() {
		return phTelefono.getText();
	}

	public String getTxtNacionalidad() {
		return phNacionalidad.getText();
	}
	
	public String getTxtTitulacion() {
		return txtTitulacion.getText();
	}


	public String getTxtNombreMod() {
		return txtNombreMod.getText();
	}

	public void setTxtNombreMod(JTextField txtNombreMod) {
		this.txtNombreMod = txtNombreMod;
	}

	public String getTxtApellidoMod() {
		return txtApellidoMod.getText();
	}

	public void setTxtApellidoMod(JTextField txtApellidoMod) {
		this.txtApellidoMod = txtApellidoMod;
	}

	public String getTxtTelefonoMod() {
		return txtTelefonoMod.getText();
	}

	public void setTxtTelefonoMod(JTextField txtTelefonoMod) {
		this.txtTelefonoMod = txtTelefonoMod;
	}

	public String getTxtNacionalidadMod() {
		return txtNacionalidadMod.getText();
	}

	public String getTxtNombreCurso() {
		return txtNombreCurso.getText();
	}

	public void setTxtNombreCurso(JTextField txtNombreCurso) {
		this.txtNombreCurso = txtNombreCurso;
	}

	public String getTxtDescripcionCurso() {
		return txtDescripcionCurso.getText();
	}

	public void setTxtDescripcionCurso(JTextField txtDescripcionCurso) {
		this.txtDescripcionCurso = txtDescripcionCurso;
	}

	public void setTxtNacionalidadMod(JTextField txtNacionalidadMod) {
		this.txtNacionalidadMod = txtNacionalidadMod;
	}


	public void crearTabla(HashMap<String, Alumno> recogerAlumnos) {
		

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		model.addColumn("Cod");
		model.addColumn("Dni");
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Telefono");
		model.addColumn("Nacionalidad");
		model.addColumn("Titulacion");
		for(String key:recogerAlumnos.keySet()){
			
			
			Alumno alumnoAux = recogerAlumnos.get(key);
			Titulacion titulacionAux = alumnoAux.getTitulacionAlumno();
			String nombreAux = titulacionAux.getNombre();
			
			model.addRow(new String[] { String.valueOf(recogerAlumnos.get(key).getCod()),
					recogerAlumnos.get(key).getDni(),
					recogerAlumnos.get(key).getNombre(),
					recogerAlumnos.get(key).getApellido(),
					String.valueOf(recogerAlumnos.get(key).getTelefono()),
					recogerAlumnos.get(key).getNacionalidad(),
					recogerAlumnos.get(key).getTitulacionAlumno().getNombre()
			});
			
			this.getTable().setModel(model);
		}
	}
	
//	public void actualizarTabla(HashMap<String, Alumno> recogerAlumnos){
//		controlador.actualizarTabla(recogerAlumnos);
//	}

	public String getTxtTitulacionMod() {
		return txtTitulacionMod.getText();
	}

	public void setTxtTitulacionMod(JTextField txtTitulacionMod) {
		this.txtTitulacionMod = txtTitulacionMod;
	}

	

	
}
