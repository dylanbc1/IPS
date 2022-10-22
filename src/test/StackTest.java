import hashtable.Hashtable;
import hashtable.HashtableNode;
import junit.framework.TestCase;
import model.Action;
import model.IPSController;
import model.Patient;
import org.junit.Test;
import stack.*;

public class StackTest extends TestCase {

    private Stack<Action> stack;
    private IPSController controller;

    public void setupStage1(){
        stack = new Stack<>();
        controller = new IPSController();
    } // Stage 1

    @Test
    public void pushTest(){
        setupStage1();

        assertEquals(0,stack.getStack().size());
    }

}