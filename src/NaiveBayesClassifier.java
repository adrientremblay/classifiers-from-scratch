import java.util.ArrayList;
import java.util.HashMap;

public class NaiveBayesClassifier {
    HashMap<String, Float> priorHypothesisProbabilityMap = new HashMap<String, Float>();
    HashMap<String, ArrayList<HashMap<String, Float>>> conditionalHypothesisProbabilityMap = new HashMap<String, ArrayList<HashMap<String, Float>>>();

    public NaiveBayesClassifier(String[][] trainingData) {
        estimateProbabilitiesFromTrainingData(trainingData);
    }

    /**
     *
     * @param trainingData in the format e1, E2, E3, .., En, H
     */
    private void estimateProbabilitiesFromTrainingData(String[][] trainingData) {
        // re-calculating P(H) and by consequence also counting the entries for each hypothesis
        for (String[] vector : trainingData) {
            String hypothesis = vector[trainingData.length - 1];

            if (!priorHypothesisProbabilityMap.containsKey(hypothesis))
                priorHypothesisProbabilityMap.put(hypothesis, 1F / trainingData.length);
            else
                priorHypothesisProbabilityMap.put(hypothesis, priorHypothesisProbabilityMap.get(hypothesis) + (1F / trainingData.length));
        }

        // now that we have counted the hypotheses, we can calculate the conditional probabilities
        for(String[] vector : trainingData) {
            String hypothesis = vector[trainingData.length - 1];
            int hypothesisCount = (int) (priorHypothesisProbabilityMap.get(hypothesis) * trainingData.length);
            if (conditionalHypothesisProbabilityMap.containsKey(hypothesis)) {
                conditionalHypothesisProbabilityMap.put(hypothesis, new ArrayList<HashMap<String, Float>>());
            }

            ArrayList<HashMap<String, Float>> evidenceList = conditionalHypothesisProbabilityMap.get(hypothesis);
            for (int i = 0 ; i < vector.length - 2 ; i++) {
                if (i == evidenceList.size())
                    evidenceList.add(new HashMap<String, Float>());

                HashMap<String, Float> conditionMap = evidenceList.get(i);
                String condition = vector[i];

                if (!conditionMap.containsKey(condition))
                    conditionMap.put(condition, 1F / hypothesisCount);
                else
                    conditionMap.put(condition, conditionMap.get(condition) + (1F / hypothesisCount));
            }
        }
    }

    /**
     *
     * @return the most likely hypothesis as a string
     */
    public String printEstimate() {

    }

    public static void main(String args[]) {
        ArrayList<Integer> test = new ArrayList<>();
    }
}