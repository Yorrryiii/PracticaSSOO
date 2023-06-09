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

        // Panel derecho (50%)
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.YELLOW);
        add(panelDerecho, BorderLayout.EAST);
        panelDerecho.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));

        // Panel central (30%)
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.GREEN);
        add(panelCentral, BorderLayout.CENTER);
        panelCentral.setPreferredSize(new Dimension(getWidth() * 3 / 10, getHeight()));

        // Crear la tabla
        String[] columnNames = {"NOMBRE", "TIPO", "Ci"};
        Object[][] data = new Object[20][3];
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panelCentral.add(scrollPane, BorderLayout.CENTER);

        // Panel izquierdo (20%)
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBackground(Color.BLUE);
        add(panelIzquierdo, BorderLayout.WEST);
        panelIzquierdo.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));

        // Etiqueta "OPCIONES"
        JLabel labelOpciones = new JLabel("OPCIONES");
        labelOpciones.setFont(new Font("Arial", Font.BOLD, 20));
        labelOpciones.setHorizontalAlignment(SwingConstants.CENTER);
        panelIzquierdo.add(labelOpciones, BorderLayout.NORTH);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelIzquierdo.add(panelBotones, BorderLayout.CENTER);

        // Botones
        JButton btnCrear = new JButton("CREAR");
        JButton btnBorrar = new JButton("BORRAR");
        JButton btnCopiar = new JButton("COPIAR");
        JButton btnMover = new JButton("MOVER");
        JButton btnLimpiar = new JButton("LIMPIAR");
        JButton btnNuevo = new JButton("NUEVO");

        // Ajustar tamaño de los botones
        Dimension buttonSize = new Dimension(btnCrear.getPreferredSize().width * 3, btnCrear.getPreferredSize().height);
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

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
