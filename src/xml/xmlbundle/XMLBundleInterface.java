package xml.xmlbundle;

import UI.Validation.XMLFileType;

import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * All publicly available calls on an XMLBundle object
 * Used by master validator to ensure all files have been assembled
 * @author Max Smith
 */
public interface XMLBundleInterface {

    /**
     * Get a map of all xml files (key is file type, value is file)
     * @return map of all validated xml fiels
     */
    Map<XMLFileType, File> getXMLFiles();

    /**
     * Query whether the bundle needs a file of a specific tag
     * @param tag is the XMLFIleType tag (e.g. Deck)
     * @return boolean of whether the tag has been validated or not
     */
    boolean needsFile(XMLFileType tag);

    /**
     * Verify whether or not all tags have been assembled
     * @return true if all files have been assembled
     */
    boolean isComplete();

    /**
     * Add file to the map of XMLFiles
     * @param file is the file to add (value)
     * @param tag is the tag (key)
     */
    void addFile(File file, XMLFileType tag);

    /**
     * Fetch a set of all missing file type tags
     * @return the set of all missing file tpye tags
     */
    Set<XMLFileType> getMissingFiles();
}
