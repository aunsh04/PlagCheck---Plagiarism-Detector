import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Nirupama
 * Object of this class represents CalculatePlagiarism
 */
public class CalculatePlagiarism {
    /**
     * Constructor of CalculatePlagiarism class
     */
    public CalculatePlagiarism() {

    }

    public void generateAST() {

    }

    public List<PlagiarismCase> getCases() {
        List<PlagiarismCase> calculatedCaseList = new ArrayList<>();


        // calculate plagiarism and represent each combination of students as a case with
        // steps - 1. Basic Algorithm
        //         2. Sequence Detection Algorithm
        //         3. Generalization Algorithm

        AlgorithmFactory algorithmFactory = new AlgorithmFactory();
        BasicAlgorithm basicAlgorithm = algorithmFactory.makeBasicAlgorithm();
        SequenceDetectionAlgorithm sequenceDetectionAlgorithm = algorithmFactory.makeSequenceDetectionAlgorithm();
        GeneralizationAlgorithm generalizationAlgorithm = algorithmFactory.makeGeneralizationAlgorithm();

        // add each case to calculatedCaseList

        return calculatedCaseList;
    }

    public Map<AST,Integer> findSimilarities() {
        Map<AST,Integer> similarityMap = new HashMap<>();

        // compute similarity for subtrees and place in buckets

        return similarityMap;
    }


}
