import junit.framework.TestCase;
import org.junit.Test;
import hashtable.*;
import model.*;

public class HashtableTest extends TestCase{

    private Hashtable<String, Patient> hashtable;

    public void setupStage1(){
        hashtable = new Hashtable<>(100000);
    } // Stage 1

    @Test
    public void testAuxiliar(){
        setupStage1();
        IPSController controller = new IPSController();

        Patient patient = new Patient("12", "dylan", 'M', "11111",2);
        HashtableNode<String, Patient> hashtableNode = new HashtableNode<>(patient.getId(), patient);
        hashtable.insert(hashtableNode, hashtableNode.getKey());

        System.out.println("the size of the hash "+hashtable.getOccupiedSize());

        controller.entry(patient.getId());
        controller.undo();

        hashtable.insert(hashtableNode, hashtableNode.getKey());
        controller.entry(hashtableNode.getKey());
        controller.undo();

        Patient patient2 = new Patient("1", "dylan", 'M', "11111",2);
        HashtableNode<String, Patient> hashtableNode2 = new HashtableNode<>(patient2.getId(), patient2);
        hashtable.insert(hashtableNode2, hashtableNode2.getKey());
        controller.entry(hashtableNode2.getKey());

        assertEquals(hashtableNode2, hashtable.search("1"));
    }
}
