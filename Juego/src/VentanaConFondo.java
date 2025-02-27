import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConFondo {
    public static void main(String[] args) {
        new VentanaConFondo().crearVentanaConFondo();
    }

    public void crearVentanaConFondo() {
        JFrame ventana = new JFrame("Ventana con Fondo");
        ventana.setSize(900, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        // Crear el panel con la imagen de fondo
        JPanel panelFondo = new PanelConImagen("src/imagenes/fondo.jpg");
        panelFondo.setLayout(new BorderLayout());

        // Panel para el título y subtítulo
        JPanel panelTextos = new JPanel();
        panelTextos.setLayout(new BoxLayout(panelTextos, BoxLayout.Y_AXIS));
        panelTextos.setOpaque(false); //hacer transparente el fondo del panel
        panelTextos.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));

        // Crear el título
        JLabel titulo = new JLabel("FINN", JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 80)); // Fuente similar a Arsenal SC
        titulo.setForeground(Color.WHITE);

        // Crear el subtítulo
        JLabel subtitulo = new JLabel("Bienvenido al juego", JLabel.CENTER);
        subtitulo.setFont(new Font("Serif", Font.PLAIN | Font.ITALIC, 30));
        subtitulo.setForeground(Color.WHITE);

        //Crear el boton de inicio
        JButton botonIniciar = new JButton("INICIAR");
        botonIniciar.setFont(new Font("Serif", Font.BOLD, 18));
        botonIniciar.setForeground(Color.WHITE);
        //botonIniciar.setBackground(new Color(0, 102, 204));
        botonIniciar.setOpaque(false);
        botonIniciar.setFocusPainted(false);
        botonIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Accion para abrir una nueva ventana
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNuevaVentana();
            }
        });

        // Alinear los textos en el centro
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Espaciado entre los textos
        panelTextos.add(Box.createVerticalStrut(30));
        panelTextos.add(titulo);
        panelTextos.add(Box.createVerticalStrut(10)); // Espacio arriba del título
        panelTextos.add(subtitulo);
        panelTextos.add(Box.createVerticalStrut(90)); // Espacio entre título y subtítulo
        panelTextos.add(botonIniciar);

        // Agregar el panel de textos arriba del panel de fondo
        panelFondo.add(panelTextos, BorderLayout.NORTH);

        // Agregar el panel de fondo a la ventana
        ventana.setContentPane(panelFondo);
        ventana.setVisible(true);
    }

    //Funcion para abrir una nueva ventana con el mismo fondo
    public void abrirNuevaVentana(){
        JFrame nuevaVentana = new JFrame("Nueva ventana");
        nuevaVentana.setSize(900, 600);
        nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuevaVentana.setLocationRelativeTo(null);

        //panel con la misma imagen de fondo
        JPanel panelFondo = new PanelConImagen("src/imagenes/fondo.jpg");
        panelFondo.setLayout(new BorderLayout());

        nuevaVentana.setContentPane(panelFondo);
        nuevaVentana.setVisible(true);
    }

    // Clase interna para manejar la imagen de fondo
    class PanelConImagen extends JPanel {
        private Image imagen;

        public PanelConImagen(String rutaImagen) {
            try {
                imagen = ImageIO.read(new File(rutaImagen));
            } catch (IOException e) {
                System.out.println("Error al cargar la imagen de fondo.");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
