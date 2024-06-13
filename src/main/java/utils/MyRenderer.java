package utils;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import view.Tarjeta;

// TODO
class MyRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        if (value instanceof Tarjeta) {
            Tarjeta tarjeta = (Tarjeta) value;
            setText(null); // Limpiar el texto predeterminado
            
            // Configurar el renderizado según la tarjeta
            //setIcon(tarjeta.getIcon());
            setBackground(tarjeta.getBackground());
            setForeground(tarjeta.getForeground());
            // Puedes ajustar otros atributos según tus necesidades
            
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
        }
        
        return this;
    }
}