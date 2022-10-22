import junit.framework.TestCase;
import model.IPSController;
import model.Patient;
import priorityQueue.*;
import queue.*;

public class PriorityQueueTest extends TestCase{

    private PriorityQueue<Patient, Integer> priorityQueue;
    private IPSController controller;
    private Queue<Patient> queue;

    public void setupStage1(){
        priorityQueue = new PriorityQueue<>(5);
        controller = new IPSController();
        queue = new Queue<>(5);
    } // Stage 1

    public void setupStage2(){
        priorityQueue = new PriorityQueue<>(0);
        controller = new IPSController();
    }

    // INSERT TESTS
    public void testInsert(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",2);
        Patient patient4 = new Patient("4","jose",'M',"11/11/2004",1);
        Patient patient5 = new Patient("5","jose",'M',"11/11/2004",5);

        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient2, patient2.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient3, patient3.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient4, patient4.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient5, patient5.getPriority()));

        assertEquals(5, priorityQueue.occupedSize());
        assertEquals(5, priorityQueue.maximum().getElement().getPriority());
    }

    public void testInsert2(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",3);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",1);

        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient2, patient2.getPriority()));

        assertEquals(2, priorityQueue.occupedSize());
        assertEquals(3, priorityQueue.maximum().getElement().getPriority());

        setupStage2();
        assertFalse(priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority())));
    }

    public void testInsert3(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",2);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",3);
        Patient patient4 = new Patient("4","jose",'M',"11/11/2004",5);
        Patient patient5 = new Patient("5","jose",'M',"11/11/2004",1);

        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient2, patient2.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient3, patient3.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient4, patient4.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient5, patient5.getPriority()));

        assertEquals(5, priorityQueue.occupedSize());
        assertEquals(5, priorityQueue.maximum().getElement().getPriority());

        priorityQueue.delete(0);

        assertEquals(4, priorityQueue.maximum().getElement().getPriority());
    }

    // MAXIMUM and MINIMUM TESTS
    public void testMaximumMinimum(){
        setupStage2();
        assertNull(priorityQueue.maximum());
        assertNull(priorityQueue.minimum());
    }

    public void testMaximumMinimum2(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",2);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",24);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",3);
        Patient patient4 = new Patient("4","jose",'M',"11/11/2004",11);
        Patient patient5 = new Patient("5","jose",'M',"11/11/2004",1);

        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient2, patient2.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient3, patient3.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient4, patient4.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient5, patient5.getPriority()));

        assertEquals(1, priorityQueue.minimum().getElement().getPriority());
        assertEquals(24, priorityQueue.maximum().getElement().getPriority());
    }

    public void testMaximumMinimum3(){

        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",2);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",3);
        Patient patient4 = new Patient("4","jose",'M',"11/11/2004",29);
        Patient patient5 = new Patient("5","jose",'M',"11/11/2004",1);

        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient2, patient2.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient3, patient3.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient4, patient4.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient5, patient5.getPriority()));

        assertEquals(5, priorityQueue.occupedSize());

        assertEquals(1, priorityQueue.minimum().getElement().getPriority());
        assertEquals(29, priorityQueue.maximum().getElement().getPriority());

        priorityQueue.delete(4);
        priorityQueue.delete(0);

        assertEquals(3, priorityQueue.occupedSize());
        assertEquals(2, priorityQueue.minimum().getElement().getPriority());
        assertEquals(4, priorityQueue.maximum().getElement().getPriority());
    }

    public void testExtractmaxExtractmin(){

        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",2);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",4);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",3);
        Patient patient4 = new Patient("4","jose",'M',"11/11/2004",29);
        Patient patient5 = new Patient("5","jose",'M',"11/11/2004",1);

        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient2, patient2.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient3, patient3.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient4, patient4.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient5, patient5.getPriority()));

        assertEquals("4", priorityQueue.extractMax().getId());
        assertEquals("5", priorityQueue.extractMin().getId());
    }

    public void testExtractmaxExtractmin2(){

        setupStage2();

        assertNull(priorityQueue.extractMax());
        assertNull(priorityQueue.extractMin());
    }

    public void testExtractmaxExtractmin3(){

        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",2);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",2);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",3);
        Patient patient4 = new Patient("4","jose",'M',"11/11/2004",1);
        Patient patient5 = new Patient("5","jose",'M',"11/11/2004",1);

        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient2, patient2.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient3, patient3.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient4, patient4.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient5, patient5.getPriority()));

        assertEquals("3", priorityQueue.extractMax().getId());
        assertEquals("5", priorityQueue.extractMin().getId());
    }

    public void testIncreaseDecreaseKey(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",8);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",10);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",7);
        Patient patient4 = new Patient("4","jose",'M',"11/11/2004",12);
        Patient patient5 = new Patient("5","jose",'M',"11/11/2004",1);

        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient2, patient2.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient3, patient3.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient4, patient4.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient5, patient5.getPriority()));

        priorityQueue.increaseKey(patient5, 2);
        assertSame(2, priorityQueue.getPriorityQueue()[4].getKey());

        priorityQueue.decreaseKey(patient3, 1);
        assertSame(1, priorityQueue.getPriorityQueue()[2].getKey());
    }

    public void testIncreaseDecreaseKey2(){
        setupStage2();

        assertFalse(controller.increaseKey("1111",5));
        assertFalse(controller.decreaseKey("98765",1));
    }

    public void testIncreaseDecreaseKey3(){
        setupStage1();

        Patient patient1 = new Patient("1","jose",'M',"11/11/2004",2);
        Patient patient2 = new Patient("2","jose",'M',"11/11/2004",5);
        Patient patient3 = new Patient("3","jose",'M',"11/11/2004",3);
        Patient patient4 = new Patient("4","jose",'M',"11/11/2004",4);
        Patient patient5 = new Patient("5","jose",'M',"11/11/2004",1);

        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient1, patient1.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient2, patient2.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient3, patient3.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient4, patient4.getPriority()));
        priorityQueue.insert(new PriorityQueueNode<Patient, Integer>(patient5, patient5.getPriority()));

        assertEquals(5, priorityQueue.occupedSize());
        assertFalse(priorityQueue.increaseKey(patient5, 0));

        priorityQueue.increaseKey(patient3, 6);

        assertEquals(patient3,priorityQueue.maximum().getElement());
    }

}
