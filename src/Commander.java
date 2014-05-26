import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by kruczjak on 26.05.14.
 */
public class Commander {
    private JPanel panel1;
    private JList<File> list1;
    private JList<File> list2;

    public Commander()  {

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
        // TODO: place custom component creation code here
        File file = new File("/");
        File [] files = file.listFiles();
        FileComparator fileComparator = new FileComparator();
        Arrays.sort(files, fileComparator);

        list1 = new JList<File>(files);
        list1.setCellRenderer(new FileRender());
        list2 = new JList<File>(files);
        list2.setCellRenderer(new FileRender());
    }
}
