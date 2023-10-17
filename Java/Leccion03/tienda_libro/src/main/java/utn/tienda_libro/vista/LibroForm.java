package utn.tienda_libro.vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.tienda_libro.servicio.LibroServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private DefaultTableModel tablaModeloLibros;
    

    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio= libroServicio;
        iniciarForma();
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

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
}