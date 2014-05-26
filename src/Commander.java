import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by kruczjak on 26.05.14.
 */
public class Commander {
    private JPanel panel1;
    private JList<File> list1;
    private JList<File> list2;
    private FileListModel model1;
    private FileListModel model2;

    public Commander()  {
        init();
    }

    private void init() {
        
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Commander");
        frame.setContentPane(new Commander().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(640,480));
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        model1 = new FileListModel(System.getProperty("user.dir"));
        model2 = new FileListModel(System.getProperty("user.dir"));

        list1 = new JList<File>(model1);
        list1.setCellRenderer(new FileRender());
        list2 = new JList<File>(model2);
        list2.setCellRenderer(new FileRender());
    }
}
