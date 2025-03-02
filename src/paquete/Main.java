package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    private JFrame ventana;
    private JPanel paneles;
    private CardLayout cardLayout;
    private JLabel label;
    private JButton botonContinuar; 
    private String texto = "Athena despierta en un bosque encantado, su mente es un vacío, su único recuerdo es su propio nombre." 
    +  " La brisa nocturna susurra secretos entre los árboles, y la luna ilumina senderos inciertos." 
    + " A su alrededor, la naturaleza parece viva, observándola. No hay señales de cómo llegó allí," 
    + " pero una sensación de urgencia la invade. Cada camino ante ella promete respuestas… o peligro." 
    + " Su destino depende de cada elección que tome en este mundo desconocido."; 
    private String[] palabras; 
    
    private String nuevoTexto = "Frente a Athena, dos senderos se abren en el bosque. Uno de ellos parece iluminado por luciérnagas,"
    							+ "mientras que el otro está cubierto por una neblina densa y misteriosa. ¿Cuál camino tomará?";
    private int indice = 0;
    private Timer timer;
    private JPanel decision1Panel;
    private JPanel arbolPanel;
    private JPanel rioPanel;
    private JPanel estpherPanel; 

    public static void main(String[] args) {
    	  SwingUtilities.invokeLater(() -> {
              Main juego = new Main();
              juego.Ventana(); // Inicializar la ventana correctamente
          });
    }
    
    public void TextoAnimado(){
    	 // Crear el panel con imagen de fondo
        JPanel panelTexto = new PanelConImagen("src/imagenes/fondo.jpg");
        panelTexto.setLayout(new BorderLayout());
        panelTexto.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Márgenes

        // Label para mostrar la narración con margen interno
        label = new JLabel("<html></html>", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        label.setForeground(Color.WHITE); // Texto blanco
        label.setVerticalAlignment(SwingConstants.CENTER);

        // Panel intermedio para centrar el texto
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setOpaque(false);
        panelCentro.add(label, BorderLayout.CENTER);

        // Botón "Continuar", inicialmente oculto
        botonContinuar = new JButton("CONTINUAR");
        botonContinuar.setBackground(Color.BLACK);
        botonContinuar.setForeground(Color.WHITE);
        botonContinuar.setPreferredSize(new Dimension(100, 30)); 
        botonContinuar.setFont(new Font("Serif", Font.BOLD, 20));
        botonContinuar.setFocusPainted(false);
        botonContinuar.setVisible(false); // Se ocultará hasta que termine la animación
        botonContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);

        //agregamos el actionListener para el boton continuar
        botonContinuar.addActionListener(new ActionListener(){
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			cardLayout.show(paneles, "DECISION_1");
                    // Modificación: Obtener el panel "DECISION_1" usando getComponent()
                    JPanel decision1Panel = (JPanel) paneles.getComponent(2); // "DECISION_1" es el tercer componente (índice 2)
                    Component[] components = decision1Panel.getComponents();
                    for (Component component : components) {
                        if (component instanceof JLabel) {
                            ((JLabel) component).setText("<html>Despiertas, exploras el bosque y encuentras 3 caminos, todos llevan a un destino, elige: </html>");
                            break; // Solo necesitamos actualizar el primer JLabel
                        }
                    }
        		}
        		});
    
        // Agregar elementos al panel
        panelTexto.add(panelCentro, BorderLayout.CENTER);
        panelTexto.add(botonContinuar, BorderLayout.SOUTH);

        // Agregar al CardLayout
        paneles.add(panelTexto, "TEXTO_ANIMADO");

        // Iniciar animación de texto al cambiar de pantalla
        iniciarAnimacion();
    }

    public void iniciarAnimacion() {
    	palabras = texto.split(" ");
        indice = 0;
        label.setText("<html></html>"); // Reiniciar el texto

        timer = new Timer(100, e -> { // Velocidad de 100ms por palabra
            if (indice < palabras.length) {
                label.setText("<html>" + label.getText().replace("<html>", "").replace("</html>", "") + " " + palabras[indice] + "</html>");
                indice++;
            } else {
                timer.stop();
                mostrarBotonContinuar();
            }
        });

        timer.start();
    }
    
    public void mostrarBotonContinuar() {

        // Ajustar el ancho del botón al tamaño del texto
        botonContinuar.setPreferredSize(new Dimension(label.getPreferredSize().width + 20, 40));
        botonContinuar.setVisible(true);

        // Revalidar el panel para aplicar cambios
        botonContinuar.getParent().revalidate();
        botonContinuar.getParent().repaint();
    }
    
    public void Ventana() {
        ventana = new JFrame("Ventana con Fondo");
        ventana.setSize(900, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        paneles = new JPanel(cardLayout);
        ventana.setLayout(new BorderLayout());

        // Crear el panel con la imagen de fondo
        JPanel panelFondo = new PanelConImagen("src/imagenes/fondo.jpg");
        panelFondo.setLayout(new BorderLayout());

        // Panel para el título y subtítulo
        JPanel panelTextos = new JPanel();
        panelTextos.setLayout(new BoxLayout(panelTextos, BoxLayout.Y_AXIS));
        panelTextos.setOpaque(false);
        panelTextos.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));

        // Crear el título
        JLabel titulo = new JLabel("FINN", JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 80));
        titulo.setForeground(Color.WHITE);

        // Crear el subtítulo
        JLabel subtitulo = new JLabel("Bienvenido al juego", JLabel.CENTER);
        subtitulo.setFont(new Font("Serif", Font.PLAIN | Font.ITALIC, 30));
        subtitulo.setForeground(Color.WHITE);

        // Crear el botón de inicio
        JButton botonIniciar = new JButton("INICIAR");
        botonIniciar.setBackground(Color.black); 
        botonIniciar.setForeground(Color.white); 
        botonIniciar.setFont(new Font("Serif", Font.BOLD, 23)); 
        botonIniciar.setFocusPainted(false);
        botonIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear la pantalla del juego
        JPanel juegoPanel = new PanelConImagen("src/imagenes/fondo.jpg");
        juegoPanel.setLayout(new BorderLayout());
     
        //PRIMER TEXTO O HISTORIA 
        
        // Agregar la acción al botón para cambiar de pantalla
        botonIniciar.addActionListener(e -> {
            TextoAnimado(); 
            cardLayout.show(paneles, "TEXTO_ANIMADO");
        });

        // Alinear los textos en el centro
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Espaciado entre los textos
        panelTextos.add(Box.createVerticalStrut(30));
        panelTextos.add(titulo);
        panelTextos.add(Box.createVerticalStrut(10));
        panelTextos.add(subtitulo);
        panelTextos.add(Box.createVerticalStrut(90));
        panelTextos.add(botonIniciar);

        // Agregar el panel de textos sobre el fondo
        panelFondo.add(panelTextos, BorderLayout.NORTH);

        // *** CREAR EL PANEL "DECISION_1" ***
        JPanel decision1Panel = new PanelConImagen("src/imagenes/fondo.jpg"); // Mismo fondo
        decision1Panel.setLayout(new BorderLayout());

        //Crear un Box para centrar el texto verticalmente
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalStrut(50)); 
        
        //Centrar el texto
        JLabel decision1Label = new JLabel("<html>Despiertas, exploras el bosque y encuentras 2 caminos,<br>todos llevan a un destino, elige sabiamente.</html>");
        decision1Label.setHorizontalAlignment(SwingConstants.CENTER);
        decision1Label.setForeground(Color.WHITE);
        decision1Label.setFont(new Font("Serif", Font.BOLD, 20)); 
        
        // Crear un panel intermedio para centrar el JLabel horizontalmente
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Usar FlowLayout para centrar
        labelPanel.setOpaque(false); // Hacer el panel transparente
        labelPanel.add(decision1Label);

        verticalBox.add(labelPanel); // Agregar el panel intermedio al Box
      
        //Crear botones
        JButton camino1Button = new JButton(" Camino 1: Seguir derecho "); 
        JButton camino2Button = new JButton("  Camino 2: Ir hacia el rio  "); 
        
        //Estilo de los botones
        estiloBoton(camino1Button); 
        estiloBoton(camino2Button); 
        
        //Agregar ActionListener al boton "Camino 1: Seguir derecho" 
        camino1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(paneles, "ARBOL_PANEL"); // Mostrar el panel del árbol parlante
            }
        });
        
     // ActionListener para el botón "Camino 1: Seguir derecho"
        ActionListener mostrarArbolPanelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(paneles, "ARBOL_PANEL");
            }
        };
        
        camino1Button.addActionListener(mostrarArbolPanelListener);
        
     // ActionListener para el botón "Camino 2: Ir hacia el rio"
        camino2Button.addActionListener(e -> cardLayout.show(paneles, "RIO_PANEL"));
        
        //Agregar botones al Box
        verticalBox.add(Box.createVerticalStrut(250)); // Espacio entre el texto y los botones
        verticalBox.add(camino1Button);
        verticalBox.add(Box.createVerticalStrut(25)); // Espacio entre los botones
        verticalBox.add(camino2Button);
        verticalBox.add(Box.createVerticalGlue()); // Relleno para empujar el contenido hacia arriba

        decision1Panel.add(verticalBox, BorderLayout.NORTH); // Agregar el Box al norte del panel

        //***CREAR EL PANEL "ARBOL_PANEL" ***
        arbolPanel = new PanelConImagen("src/imagenes/fondo.jpg"); // Mismo fondo
        arbolPanel.setLayout(new BorderLayout());

        // Crear un Box para centrar el texto verticalmente
        Box arbolBox = Box.createVerticalBox();
        arbolBox.add(Box.createVerticalStrut(50));

        // Centrar el texto
        JLabel arbolLabel = new JLabel("<html>Al seguir derecho encuentras un árbol parlante, su nombre ESTPHER, decide.</html>");
        arbolLabel.setHorizontalAlignment(SwingConstants.CENTER);
        arbolLabel.setForeground(Color.WHITE);
        arbolLabel.setFont(new Font("Serif", Font.BOLD, 20));

        //Crear un panel intermedio para centrar el JLabel horizontalmente
        JPanel arbolLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        arbolLabelPanel.setOpaque(false);
        arbolLabelPanel.add(arbolLabel);

        arbolBox.add(arbolLabelPanel);
        
        //Crear botones
        JButton arbolOpcion1Button = new JButton("Opcion 1: Ignorarlo y seguir adelante"); 
        JButton arbolOpcion2Button = new JButton("   Opcion 2: Hablar con ESTPHER   "); 
        
        //Estilo de los botones
        estiloBoton(arbolOpcion1Button); 
        estiloBoton(arbolOpcion2Button); 
        
        //ActionListener para el boton "Opcion 2: Hablar con ESTHEPHR"
        arbolOpcion2Button.addActionListener(e -> cardLayout.show(paneles, "ESTPHER_PANEL"));
        
        //Agregar botones al Box
        arbolBox.add(Box.createVerticalStrut(250)); // Espacio entre el texto y los botones
        arbolBox.add(arbolOpcion1Button);
        arbolBox.add(Box.createVerticalStrut(20)); // Espacio entre los botones
        arbolBox.add(arbolOpcion2Button);
        arbolBox.add(Box.createVerticalGlue());

        arbolPanel.add(arbolBox, BorderLayout.NORTH);

        //*** CREAR EL PANEL "RIO_PANEL" ***
        rioPanel = new PanelConImagen("src/imagenes/fondo.jpg"); // Mismo fondo
        rioPanel.setLayout(new BorderLayout());

        // Crear un Box para centrar el texto verticalmente
        Box rioBox = Box.createVerticalBox();
        rioBox.add(Box.createVerticalStrut(50));

        // Centrar el texto
        JLabel rioLabel = new JLabel("<html>Llegas a un maravilloso rio con agua cristalina, decide.</html>");
        rioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rioLabel.setForeground(Color.WHITE);
        rioLabel.setFont(new Font("Serif", Font.BOLD, 20));
        
        // Crear un panel intermedio para centrar el JLabel horizontalmente
        JPanel rioLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rioLabelPanel.setOpaque(false);
        rioLabelPanel.add(rioLabel);

        rioBox.add(rioLabelPanel);

        // Crear botones para el río
        JButton rioOpcion1Button = new JButton("Opción 1: Tomar agua del rio y seguir");
        JButton rioOpcion2Button = new JButton("  Opción 2: No tomar agua y seguir  ");

        // Estilo de los botones
        estiloBoton(rioOpcion1Button);
        estiloBoton(rioOpcion2Button);
        
     // Agregar el mismo ActionListener a los botones del río
        rioOpcion1Button.addActionListener(mostrarArbolPanelListener);
        rioOpcion2Button.addActionListener(mostrarArbolPanelListener);

        // Agregar botones al Box
        rioBox.add(Box.createVerticalStrut(250)); // Espacio entre el texto y los botones
        rioBox.add(rioOpcion1Button);
        rioBox.add(Box.createVerticalStrut(20)); // Espacio entre los botones
        rioBox.add(rioOpcion2Button);
        rioBox.add(Box.createVerticalGlue());

        rioPanel.add(rioBox, BorderLayout.NORTH); 
        
        
        
         
        // *** CREAR EL PANEL "ESTPHER_PANEL" ***
        estpherPanel = new PanelConImagen("src/imagenes/fondo.jpg");
        estpherPanel.setLayout(new BorderLayout());
        estpherPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Agregar margen general

        // Centrar el texto con JTextArea
        JTextArea estpherTextArea = new JTextArea("Soy Estpher, el guardián del umbral. Tu camino está manchado de sombras, Athena. Criaturas olvidadas aguardan en la penumbra, y los ecos de los caídos susurrarán en tu mente. ¿Seguirás adelante… sabiendo que algunos secretos jamás deben ser descubiertos?");
        estpherTextArea.setEditable(false);
        estpherTextArea.setOpaque(false);
        estpherTextArea.setForeground(Color.WHITE);
        estpherTextArea.setFont(new Font("Serif", Font.BOLD, 20));
        estpherTextArea.setLineWrap(true);
        estpherTextArea.setWrapStyleWord(true);

        // Ajustar el ancho del JTextArea
        estpherTextArea.setPreferredSize(new Dimension(600, estpherTextArea.getPreferredSize().height)); // Ajustar el ancho

        // Ajustar la altura del JTextArea para mostrar todo el párrafo
        estpherTextArea.setPreferredSize(new Dimension(600, estpherTextArea.getPreferredSize().height));

        // Crear un panel intermedio para centrar el JTextArea horizontalmente
        JPanel estpherTextAreaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        estpherTextAreaPanel.setOpaque(false);
        estpherTextAreaPanel.add(estpherTextArea);

        // Crear un Box para centrar el contenido verticalmente
        Box estpherBox = Box.createVerticalBox();
        estpherBox.add(Box.createVerticalStrut(50));
        estpherBox.add(estpherTextAreaPanel);
        
        

        // Crear el botón "Continuar"
        JButton continuarButton = new JButton("CONTINUAR");
        estiloBoton(continuarButton);

        // Agregar el botón "Continuar" al Box
        estpherBox.add(Box.createVerticalStrut(250));
        estpherBox.add(continuarButton);
        estpherBox.add(Box.createVerticalGlue());

        estpherPanel.add(estpherBox, BorderLayout.NORTH);
          
        // Agregar paneles al CardLayout
        paneles.add(panelFondo, "INICIO");
        paneles.add(juegoPanel, "JUEGO");
        paneles.add(decision1Panel, "DECISION_1"); 
        paneles.add(arbolPanel, "ARBOL_PANEL"); 
        paneles.add(rioPanel, "RIO_PANEL"); 
        paneles.add(estpherPanel, "ESTPHER_PANEL"); 

        ventana.add(paneles, BorderLayout.CENTER);
        ventana.setVisible(true);
    }

    private void estiloBoton(JButton boton) {
    	boton.setBackground(Color.BLACK);
    	boton.setForeground(Color.WHITE);
    	boton.setFont(new Font("Serif", Font.BOLD, 20));
    	boton.setFocusPainted(false); 
    	boton.setAlignmentX(Component.CENTER_ALIGNMENT);
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

