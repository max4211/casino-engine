package xml.xmlbundle;

import UI.Validation.XMLFile;
import xml.xmlreader.interfaces.XMLValidatorInterface;

import java.io.File;
import java.util.*;

public class XMLBundle implements XMLBundleInterface {

    private Map<XMLFile, File> myXMLFiles;

    public XMLBundle() {
        this.myXMLFiles = new HashMap<>();
    }

    @Override
    public Map<XMLFile, File> getXMLFiles() {
        return this.myXMLFiles;
    }

    @Override
    public boolean needsFile(XMLFile tag) {
        return !(this.myXMLFiles.containsKey(tag));
    }

    @Override
    public boolean isComplete() {
        return this.missingFiles().size() == 0;
    }

    @Override
    public void addFile(File file) {
        XMLFile tag = XMLFile.valueOf(XMLValidatorInterface.getMetaTag(file));
        this.myXMLFiles.put(tag, file);
    }

    @Override
    public Set<XMLFile> missingFiles() {
        Set<XMLFile> set = createFileSet();
        for (XMLFile key: this.myXMLFiles.keySet())
            set.remove(key);
        return set;
    }

    private Set<XMLFile> createFileSet() {
        return EnumSet.allOf(XMLFile.class);
    }

}
