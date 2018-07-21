/**
 * 
 * @author Nirupama
 *Interface AbstractPersonFactory
 */
public interface AbstractPersonFactory {

    Admin makeAdmin();

    TeachingAssistant makeTeachingAssistant();

    Professor makeProfessor();

}
