/**
 * 
 * @author Nirupama
 * object of this class represents PersonFactory, which is an AbstractFactory
 * and can used for invoking the factory methods within the class
 *
 */

public class PersonFactory implements AbstractPersonFactory {

    /**
     * Constructor for PersonFactory
     */
    public PersonFactory() {

    }

    @Override
    public Admin makeAdmin() {
        return new Admin();
    }

    @Override
    public Professor makeProfessor() {
        return new Professor();
    }

    @Override
    public TeachingAssistant makeTeachingAssistant() {
        return new TeachingAssistant();
    }




}
