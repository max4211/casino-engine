public class HandComparator implements HandEvaluatorInterface {

    /**
     * Compares hands, returns an integer value of which is greater
     * @param hand1
     * @param hand2
     * @return
     */
    public int compare(Hand hand1, Hand hand2) {
        compareHierarchy();
        compareSame();
    }

    private int compareHierarchy(Hand h1, Hand h2) {
        return 0;
    }

    private int compareSame(Hand h1, Hand h2) {
        return 0;
    }
}