import junit.framework.TestCase;
import model.Action;
import model.Patient;
import org.junit.Assert;
import org.junit.Test;
import stack.Stack;

public class StackTest extends TestCase{

    public Stack<Action> stack;

    public void setupStage1(){
        stack = new Stack<>();
    }

    @Test
    public void testPush(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);
        Action action3 = new Action("entry", patient3, 2);

        assertEquals(0, stack.getStack().size());

        stack.push(action1);
        stack.push(action2);
        stack.push(action3);

        assertEquals(3, stack.getStack().size());
    }

    public void testPush2(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);

        stack.push(action1);
        stack.push(action2);

        assertEquals(2, stack.getStack().size());

        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);
        Action action3 = new Action("entry", patient3, 2);

        stack.push(action3);

        assertEquals(3, stack.getStack().size());
    }

    public void testPush3(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);

        stack.push(action1);
        stack.push(action2);

        assertEquals(action2, stack.top().getValue());
    }

    @Test
    public void testPop(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);
        Action action3 = new Action("entry", patient3, 2);

        stack.push(action1);
        stack.push(action2);
        stack.push(action3);

        assertEquals(3, stack.getStack().size());
        assertEquals(action3, stack.pop().getValue());
        assertEquals(2, stack.getStack().size());
    }

    @Test
    public void testPop2(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);
        Action action3 = new Action("entry", patient3, 2);

        stack.push(action1);
        stack.push(action2);
        stack.push(action3);

        assertEquals(3, stack.getStack().size());
        assertEquals(action3, stack.pop().getValue());
        assertEquals(action2, stack.pop().getValue());
        assertEquals(action1, stack.pop().getValue());
        assertEquals(0, stack.getStack().size());
        assertNull(stack.pop());
    }

    @Test
    public void testPop3(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);
        Action action3 = new Action("entry", patient3, 2);

        stack.push(action1);
        stack.push(action2);
        stack.push(action3);

        assertEquals(action3, stack.pop().getValue());
        assertEquals(action2, stack.top().getValue());
    }

    @Test
    public void testTop(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);
        Action action3 = new Action("entry", patient3, 2);

        stack.push(action1);
        stack.push(action2);
        stack.push(action3);

        assertEquals(action3, stack.top().getValue());
    }

    @Test
    public void testTop2(){
        setupStage1();

        assertNull(stack.top());
    }

    @Test
    public void testTop3(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);
        Action action3 = new Action("entry", patient3, 2);

        stack.push(action1);
        stack.push(action2);
        stack.push(action3);

        stack.pop();
        stack.pop();

        assertEquals(action1, stack.top().getValue());
    }

    @Test
    public void testIsEmpty(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);
        Action action3 = new Action("entry", patient3, 2);

        stack.push(action1);
        stack.push(action2);
        stack.push(action3);

        assertFalse(stack.isEmpty());
    }

    @Test
    public void testIsEmpty2(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);
        Action action3 = new Action("entry", patient3, 2);

        stack.push(action1);
        stack.push(action2);
        stack.push(action3);

        stack.pop();
        stack.pop();
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmpty3(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);

        Action action1 = new Action("entry", patient1, 1);
        Action action2 = new Action("out", patient2, 1);
        Action action3 = new Action("entry", patient3, 2);

        stack.push(action1);
        stack.push(action2);
        stack.push(action3);

        stack.pop();
        stack.pop();

        assertFalse(stack.isEmpty());
    }
}
