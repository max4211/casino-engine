package xml.xmlbundle;

import UI.Validation.XMLFileType;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface XMLBundleInterface {

    Map<XMLFileType, File> getXMLFiles();

    boolean needsFile(XMLFileType tag);

    boolean isComplete();

    void addFile(File file, XMLFileType tag);

    Set<XMLFileType> getMissingFiles();
}
