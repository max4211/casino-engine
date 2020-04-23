package xml.xmlbundle;

import UI.Validation.XMLFile;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface XMLBundleInterface {

    Map<XMLFile, File> getXMLFiles();

    boolean needsFile(XMLFile tag);

    boolean isComplete();

    void addFile(File file);

    Set<XMLFile> missingFiles();
}
