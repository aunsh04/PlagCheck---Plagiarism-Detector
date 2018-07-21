/**
 * 
 * @author Nirupama
 * AbstractAlgorithm is ConcreteFactory class which implements AbstractAlgorithmFactory
 *
 */
public class AlgorithmFactory implements AbstractAlgorithmFactory {
    /**
     * Constructor of AlgorithmFactory class
     */
    public AlgorithmFactory() {

    }

    public BasicAlgorithm makeBasicAlgorithm() {
        return new BasicAlgorithm();
    }

    public SequenceDetectionAlgorithm makeSequenceDetectionAlgorithm() {
        return new SequenceDetectionAlgorithm();
    }

    public GeneralizationAlgorithm makeGeneralizationAlgorithm() {
        return new GeneralizationAlgorithm();
    }

}
