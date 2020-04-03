public class XMLParser implements XMLParserInterface {

    private static final Document myDocument;

    /**
     * Construct the XMLParser object to be ready to parse tags
     * @param file
     */
    public XMLParser(File file) {
        createDocumentBuilder(file);
    }

    /**
     * Convert the file into a Document (DOM model)
     * Store as an instance variable to use upon interface calls
     */
    private static void createDocumentBuilder(file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            this.myDocument = doc;
        } catch (Exception e) {
            throw e;
        }
    }

}