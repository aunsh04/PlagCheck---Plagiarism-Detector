/**
 * 
 * @author Nirupama
 * Interface AbstractAlgorithmFactory
 *
 */
public interface AbstractAlgorithmFactory {

    BasicAlgorithm makeBasicAlgorithm();

    SequenceDetectionAlgorithm makeSequenceDetectionAlgorithm();

    GeneralizationAlgorithm makeGeneralizationAlgorithm();

}
