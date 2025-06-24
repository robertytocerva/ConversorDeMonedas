import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Ventana {
    Cenection cenection;
    private JPanel mainPanel;
    private JLabel Title;
    private JTextField eTxtMonedaBase;
    private JComboBox SelectMonedaBase;
    private JComboBox SelectMonedaDest;
    private JTextField eTxtMonedaDest;
    private JButton convertirButton;
    private JButton limpiarButton;

    public Ventana() {
        this.cenection = new Cenection();
        eTxtMonedaDest.setEditable(false);

        convertirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String monedaBase = SelectMonedaBase.getSelectedItem().toString();
                monedaBase = monedaBase.substring(monedaBase.length()-3);

                String monedaDest = SelectMonedaDest.getSelectedItem().toString();
                monedaDest = monedaDest.substring(monedaDest.length()-3);

                String cantidad = eTxtMonedaBase.getText().toString();

                if(!cantidad.isEmpty()){
                    try {
                        eTxtMonedaDest.setText(cenection.generarConeccion(monedaBase,monedaDest,cantidad));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Ingrese cantidad a convertir");
                }

                try {
                    String resultadoConversion = cenection.generarConeccion(monedaBase, monedaDest, cantidad);
                    eTxtMonedaDest.setText(resultadoConversion);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al convertir: "+ex.getMessage());
                }

            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eTxtMonedaDest.setText("");
                eTxtMonedaBase.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Convertidor de Divisas");
        frame.setContentPane(new Ventana().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
