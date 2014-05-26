import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private JTextField textField1;
    private JTextField textField2;
    private FileListModel model1;
    private FileListModel model2;

    public Commander()  {
        init();
    }

    private void init() {
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2)   {
                    processClick(model1,list1.locationToIndex(e.getPoint()),1);
                }
            }
        });
        list2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2)   {
                    processClick(model2,list2.locationToIndex(e.getPoint()),2);
                }
            }
        });

        list1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER)  {
                    processClick(model1,list1.getSelectedIndex(),1);
                }
            }
        });
        list2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER)  {
                    processClick(model2,list2.getSelectedIndex(),2);
                }
            }
        });
    }

    private void processClick(FileListModel model, int index, int i) {
        File clickedFile = model.getElementAt(index);

        if (clickedFile.isDirectory())  {
            model.changePath(clickedFile.getPath());
        } else {
            if (i==1) {
                copyFile(clickedFile, textField2.getText() + "/" + clickedFile.getName());
                model2.changePath();
            } else {
                copyFile(clickedFile, textField1.getText() + "/" + clickedFile.getName());
                model1.changePath();
            }
        }
    }

    private void copyFile(File clickedFile, String path) {
        try {
            Files.copy(clickedFile.toPath(), new File(path).toPath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),"Błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
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
        textField1 = new JTextField();
        textField2 = new JTextField();

        model1 = new FileListModel(System.getProperty("user.dir"), textField1);
        model2 = new FileListModel(System.getProperty("user.dir"), textField2);

        list1 = new JList<File>(model1);
        list1.setCellRenderer(new FileRender());
        list2 = new JList<File>(model2);
        list2.setCellRenderer(new FileRender());
    }
}
