public static class XMLGenerator {

    /**
     * Convert the file into a Document (DOM model)
     * Store as an instance variable to use upon interface calls
     */
    static Document createDocumentBuilder(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            throw e;
        }
    }

}