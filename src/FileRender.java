import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

/**
 * Created by kruczjak on 26.05.14.
 */
public class FileRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        JLabel l = (JLabel)c;
        File f = (File)value;

        if (index!=0)
            l.setText(f.getName());
        else if (f!=null)
            l.setText("..");

        l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));
        return l;
    }
}
