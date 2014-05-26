import javax.swing.*;
import java.io.File;
import java.util.*;

/**
 * Created by kruczjak on 26.05.14.
 */
public class FileListModel extends AbstractListModel<File> {
    private List<File> files;
    private JTextField jTextField;

    public FileListModel(String path, JTextField jTextField) {
        this.jTextField = jTextField;
        makeFromPath(path);
    }

    @Override
    public int getSize() {
        return files.size();
    }

    @Override
    public File getElementAt(int i) {
        return files.get(i);
    }

    public void changePath()    {
        changePath(jTextField.getText());
    }

    public void changePath(String path) {
        makeFromPath(path);
        this.fireContentsChanged(this,0,files.size()-1);
    }

    private void makeFromPath(String path)  {
        File file = new File(path);
        File parent = file.getParentFile();
        File [] filesArray = file.listFiles();
        if (filesArray==null) return;
        files = new ArrayList<File>(Arrays.asList(filesArray));
        Collections.sort(files, new FileComparator());
        files.add(0,parent);
        jTextField.setText(path);
    }

}
