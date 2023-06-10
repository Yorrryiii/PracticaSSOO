package Interface;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {
    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("File Explorer");
        setSize(2500, 1500);
        setLayout(new BorderLayout());

        // Panel derecho (68%)
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.WHITE);
        add(panelDerecho, BorderLayout.EAST);
        panelDerecho.setPreferredSize(new Dimension(getWidth() * 68 / 100, getHeight()));

        // Panel para los bloques y el fragmento de texto
        JPanel panelBloques = new JPanel(new BorderLayout());
        panelBloques.setBackground(Color.WHITE);
        panelDerecho.add(panelBloques, BorderLayout.CENTER);

        // Etiqueta "SECCION CLUSTERS" en la parte superior de la columna derecha
        JLabel labelSeccionClusters = new JLabel("SECCION CLUSTERS");
        labelSeccionClusters.setFont(new Font("Arial", Font.BOLD, 25));
        labelSeccionClusters.setHorizontalAlignment(SwingConstants.CENTER);
        labelSeccionClusters.setOpaque(true);
        labelSeccionClusters.setBackground(Color.decode("#FFE167"));
        panelBloques.add(labelSeccionClusters, BorderLayout.NORTH);

        // Panel para los bloques
        JPanel panelMatriz = new JPanel(new GridLayout(5, 5, 10, 10));
        panelMatriz.setBackground(Color.WHITE);
        panelBloques.add(panelMatriz, BorderLayout.CENTER);

        // Crear bloques (casillas)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JPanel bloque = new JPanel();
                bloque.setPreferredSize(new Dimension(100, 100));
                bloque.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panelMatriz.add(bloque);
            }
        }

        // Panel izquierdo (30%)
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBackground(Color.BLUE);
        add(panelIzquierdo, BorderLayout.WEST);
        panelIzquierdo.setPreferredSize(new Dimension(getWidth() * 3 / 10, getHeight()));

        // Primera fila de la columna izquierda
        JPanel panelFila1 = new JPanel(new BorderLayout());
        panelIzquierdo.add(panelFila1, BorderLayout.NORTH);

        // Etiqueta "OPCIONES"
        JLabel labelOpciones = new JLabel("OPCIONES");
        labelOpciones.setFont(new Font("Arial", Font.BOLD, 25));
        labelOpciones.setHorizontalAlignment(SwingConstants.CENTER);
        labelOpciones.setOpaque(true);
        labelOpciones.setBackground(Color.decode("#FFE167"));
        panelFila1.add(labelOpciones, BorderLayout.NORTH);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 3, 10, 10));
        panelBotones.setBackground(Color.BLUE);

        // Espacio entre botones
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botones
        JButton btnCrear = new JButton("CREAR");
        JButton btnBorrar = new JButton("BORRAR");
        JButton btnCopiar = new JButton("COPIAR");
        JButton btnMover = new JButton("MOVER");
        JButton btnLimpiar = new JButton("LIMPIAR");
        JButton btnNuevo = new JButton("NUEVO");

        // Ajustar tamaño de los botones (el doble de grandes)
        Dimension buttonSize = new Dimension(btnCrear.getPreferredSize().width * 2,
                btnCrear.getPreferredSize().height * 2);
        btnCrear.setPreferredSize(buttonSize);
        btnBorrar.setPreferredSize(buttonSize);
        btnCopiar.setPreferredSize(buttonSize);
        btnMover.setPreferredSize(buttonSize);
        btnLimpiar.setPreferredSize(buttonSize);
        btnNuevo.setPreferredSize(buttonSize);

        panelBotones.add(btnCrear);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnCopiar);
        panelBotones.add(btnMover);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnNuevo);

        JScrollPane scrollPane = new JScrollPane(panelBotones); // Envolver el panel de botones en un JScrollPane
        panelFila1.add(scrollPane, BorderLayout.CENTER);

        // Segunda fila de la columna izquierda (contenido de la antigua columna central)
        JPanel panelFila2 = new JPanel(new BorderLayout());
        panelIzquierdo.add(panelFila2, BorderLayout.CENTER);

        // Etiqueta "Directorio Raiz"
        JLabel labelDirectorioRaiz = new JLabel("DIRECTORIO RAIZ");
        labelDirectorioRaiz.setFont(new Font("Arial", Font.BOLD, 25));
        labelDirectorioRaiz.setHorizontalAlignment(SwingConstants.CENTER);
        labelDirectorioRaiz.setOpaque(true);
        labelDirectorioRaiz.setBackground(Color.decode("#FFE167"));
        panelFila2.add(labelDirectorioRaiz, BorderLayout.NORTH);

        // Crear la tabla
        String[] columnNames = { "NOMBRE", "TIPO", "Ci" };
        Object[][] data = new Object[20][3];
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        panelFila2.add(tableScrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
