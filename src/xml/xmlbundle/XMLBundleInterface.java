package xml.xmlbundle;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface XMLBundleInterface {

    Map<String, File> getXMLFiles();

    boolean needsFile(String tag);

    void addFile(File file);

    Set<String> missingFiles();
}
