package xml.xmlbundle;

import UI.Validation.XMLFileType;

import java.io.File;
import java.util.*;

public class XMLBundle implements XMLBundleInterface {

    private Map<XMLFileType, File> myXMLFiles;

    public XMLBundle() {
        this.myXMLFiles = new HashMap<>();
    }

    @Override
    public Map<XMLFileType, File> getXMLFiles() {
        return this.myXMLFiles;
    }

    @Override
    public boolean needsFile(XMLFileType tag) {
        return !(this.myXMLFiles.containsKey(tag));
    }

    @Override
    public boolean isComplete() {
        return this.getMissingFiles().size() == 0;
    }

    @Override
    public void addFile(File file, XMLFileType tag) {
        this.myXMLFiles.put(tag, file);
    }

    @Override
    public Set<XMLFileType> getMissingFiles() {
        Set<XMLFileType> set = createFileSet();
        for (XMLFileType key: this.myXMLFiles.keySet())
            set.remove(key);
        return set;
    }

    private Set<XMLFileType> createFileSet() {
        return EnumSet.allOf(XMLFileType.class);
    }

}
