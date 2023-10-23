package utn.tienda_libro.vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.tienda_libro.modelo.Libro;
import utn.tienda_libro.servicio.LibroServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField idTexto;
    private JButton Agregar;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JTextField libroTexto;
    private JTextField autorTextoTextField;
    private JTextField precioTextoTextField;
    private JTextField existenciaTextoTextField;
    private DefaultTableModel tablaModeloLibros;


    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio= libroServicio;
        iniciarForma();
        Agregar.addActionListener(e -> agregarLibro());
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarLibroSeleccionado();
            }
        });
        modificarButton.addActionListener(e -> modificarLibro());
        eliminarButton.addActionListener(e -> eliminarLibro());
    }

    private void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        // para obtener la dimension de la ventana
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()/2);
        int y = (tamanioPantalla.height - getHeight()/2);
        setLocation(x,y);

    }

    private void agregarLibro(){
        // Leer los valores del formulario
        if (libroTexto.getText().equals("")) {
            mostrarMensaje("Ingrese el nombre del Libro");
            libroTexto.requestFocusInWindow();
            return;
        }
        var nombreLibro = libroTexto.getText();
        var autor = autorTextoTextField.getText();
        var precio = Double.parseDouble(precioTextoTextField.getText());
        var existencias = Integer.parseInt(existenciaTextoTextField.getText());
        // Creamos el onjeto Libro
        var libro = new Libro(null, nombreLibro, autor, precio, existencias);
        //libro.setNombreLibro(nombreLibro);
        //libro.setAutor(autor);
        //libro.setPrecio(precio);
        //libro.setExistencias(existencias);
        this.libroServicio.guardarLibro(libro);
        mostrarMensaje("Se agrego el libro");
        limpiarFormulario();
        listarLibros();
    }

    private void cargarLibroSeleccionado(){
        // Los indices de las columnas inician en 0
        var renglon = tablaLibros.getSelectedRow();
        if(renglon != -1){
           String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
           idTexto.setText(idLibro);

           String nombreLibro = tablaLibros.getModel().getValueAt(renglon, 1).toString();
           libroTexto.setText(nombreLibro);

           String autor = tablaLibros.getModel().getValueAt(renglon, 2).toString();
           autorTextoTextField.setText(autor);

           String precio = tablaLibros.getModel().getValueAt(renglon, 3).toString();
           precioTextoTextField.setText(precio);

           String existencias = tablaLibros.getModel().getValueAt(renglon, 4).toString();
           existenciaTextoTextField.setText(existencias);
        }
    }

    private void modificarLibro(){
        if (this.idTexto.equals("")) {
            mostrarMensaje("Debes seleccionar un registro en la tabla");
        }
        else{
            // verificamos que nombre del libro no sea nulo
            if (libroTexto.getText().equals("")) {
                mostrarMensaje("Digite el nombre del libro");
                libroTexto.requestFocusInWindow();
                return;
            }
            // llenamos el objeto libro a actualizar
            int idlibro = Integer.parseInt(idTexto.getText());
            var nombreLibro = libroTexto.getText();
            var autor = autorTextoTextField.getText();
            var precio = Double.parseDouble(precioTextoTextField.getText());
            var existencias = Integer.parseInt(existenciaTextoTextField.getText());
            var libro = new Libro(idlibro, nombreLibro, autor, precio, existencias);
            libroServicio.guardarLibro(libro);
            mostrarMensaje("Se modifico el libro");
            limpiarFormulario();
            listarLibros();
        }
    }

    private void eliminarLibro(){
        var renglon = tablaLibros.getSelectedRow();
        if(renglon != -1){
            String idLibro =
                    tablaLibros.getModel().getValueAt(renglon, 0).toString();
            var libro = new Libro();
            libro.setIdLibro(Integer.parseInt(idLibro));
            libroServicio.eliminarLibro(libro);
            mostrarMensaje("Libro "+idLibro+" eliminado");
            limpiarFormulario();
            listarLibros();
        }else{
            mostrarMensaje("No se ha seleccionado ningún libro de la tabla a eliminar");
        }
    }

    private void limpiarFormulario(){
        libroTexto.setText("");
        autorTextoTextField.setText("");
        precioTextoTextField.setText("");
        existenciaTextoTextField.setText("");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void createUIComponents() {
        idTexto = new JTextField("");
        idTexto.setVisible(false);
        this.tablaModeloLibros = new DefaultTableModel(0,5){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] cabecera = {"Id", "Libro", "Auto", "Precio", "Existencia"};
        this.tablaModeloLibros.setColumnIdentifiers(cabecera);
        // instanciar el objeto de JTable
        this.tablaLibros = new JTable(tablaModeloLibros);
        // evitamos que se seleccionen varios registros
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarLibros();
    }

    private void listarLibros(){
        //Limpiar la tabla
        tablaModeloLibros.setRowCount(0);
        //Obtener los libros de la BD
        var libros = libroServicio.listarLibros();
        //Iteramos cada libro
        libros.forEach((libro) ->{//Función Lambda
            //Creamos cada registro para agregarlos a la tabla
            Object [] reglonLibro = {
                    libro.getIdLibro(),
                    libro.getNombreLibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias()
            };
            this.tablaModeloLibros.addRow(reglonLibro);
        });
    }
}
