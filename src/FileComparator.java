import java.io.File;
import java.util.Comparator;

/**
 * Created by kruczjak on 26.05.14.
 */
public class FileComparator implements Comparator<File> {

    @Override
    public int compare(File file, File file2) {
        if (!file.isFile() && file2.isFile()) return -1;
        if (file.isFile() && !file2.isFile()) return 1;

        return file.getName().compareTo(file2.getName());

    }
}
