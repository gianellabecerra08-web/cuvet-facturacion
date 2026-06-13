package cuvet.view;

import javax.swing.*;
import java.awt.*;

/**
 * Vista del módulo MedicamentoView.
 * @author Medina Peña, Danae Alexandra (2211055)
 */
public class MedicamentoView extends JPanel {
    public MedicamentoView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel lbl = new JLabel("Módulo MedicamentoView — En construcción");
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbl, BorderLayout.CENTER);
    }
}
