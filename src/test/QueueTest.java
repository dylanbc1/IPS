import junit.framework.TestCase;
import org.junit.Test;
import queue.*;

public class QueueTest extends TestCase {

    private Queue<String> queue;

    public void setupStage1(){
        queue = new Queue<>(4);
    } // Stage 1

    // ADD TESTS
    @Test
    public void testAdd(){
        setupStage1();

        QueueNode<String> node = new QueueNode<String>("discretas");

        queue.offer(node);

        assertEquals("discretas", queue.getQueue()[0].getValue());
    } // test add 1

    @Test
    public void testAdd2(){
        setupStage1();

        QueueNode<String> node = new QueueNode<String>("discretas");
        QueueNode<String> node2 = new QueueNode<String>("discretas2");
        QueueNode<String> node3 = new QueueNode<String>("discretas3");
        QueueNode<String> node4 = new QueueNode<String>("discretas4");
        QueueNode<String> node5 = new QueueNode<String>("discretas5");

        queue.offer(node);
        queue.offer(node2);
        queue.offer(node3);
        queue.offer(node4);

        assertEquals(false, queue.offer(node5));
    } // test add 2

    @Test
    public void testAdd3(){
        setupStage1();

        QueueNode<String> node = new QueueNode<String>("discretas");
        QueueNode<String> node2 = new QueueNode<String>("discretas2");
        QueueNode<String> node3 = new QueueNode<String>("discretas3");

        queue.offer(node3);
        queue.offer(node2);
        queue.offer(node);

        assertEquals(3, queue.occupedSize());
    } // test add 3
    // ADD TESTS

    // PEEK TESTS
    @Test
    public void testPeek(){
        setupStage1();

        QueueNode<String> node = new QueueNode<String>("discretas");

        queue.offer(node);

        assertEquals("discretas", queue.peek());
    } // test Peek

    @Test
    public void testPeek2() {
        setupStage1();

        QueueNode<String> node = new QueueNode<String>("discretas");
        QueueNode<String> node2 = new QueueNode<String>("discretas2");
        QueueNode<String> node3 = new QueueNode<String>("discretas3");
        QueueNode<String> node4 = new QueueNode<String>("%#3");

        queue.offer(node);
        queue.offer(node2);
        queue.offer(node3);
        queue.offer(node4);

        assertEquals("discretas", queue.peek());
    } // test Peek 2

    @Test
    public void testPeek3() {
        setupStage1();

        assertNull(queue.peek());
    } // test Peek 3
    // PEEK TESTS

    // POLL TESTS
    @Test
    public void testPoll() {
        setupStage1();

        QueueNode<String> node = new QueueNode<String>("discretas");
        QueueNode<String> node2 = new QueueNode<String>("discretas2");
        QueueNode<String> node3 = new QueueNode<String>("discretas3");
        QueueNode<String> node4 = new QueueNode<String>("discretas4");

        queue.offer(node2);
        queue.offer(node3);
        queue.offer(node);
        queue.offer(node4);

        assertEquals(node2.getValue(), queue.poll());
        assertEquals(3, queue.occupedSize());
    } // test Poll

    @Test
    public void testPoll2() {
        setupStage1();

        QueueNode<String> node = new QueueNode<String>("discretas");

        queue.offer(node);

        assertEquals(node.getValue(), queue.poll());
        assertEquals(0, queue.occupedSize());
    } // test Poll 2

    @Test
    public void testPoll3() {
        setupStage1();

        assertNull(queue.poll());
        assertEquals(0, queue.occupedSize());
    } // test Poll 3
    // POLL TESTS

    // IS EMPTY TESTS
    @Test
    public void testIsEmpty() {
        setupStage1();

        QueueNode<String> node = new QueueNode<String>("discretas");
        QueueNode<String> node2 = new QueueNode<String>("discretas2");
        QueueNode<String> node3 = new QueueNode<String>("discretas3");
        QueueNode<String> node4 = new QueueNode<String>("discretas4");

        queue.offer(node2);
        queue.offer(node3);
        queue.offer(node);
        queue.offer(node4);

        assertFalse(queue.isEmpty());
    } // test Is Empty

    @Test
    public void testIsEmpty2() {
        setupStage1();

        assertTrue(queue.isEmpty());
    } // test Is Empty 2

    @Test
    public void testIsEmpty3() {
        setupStage1();

        QueueNode<String> node = new QueueNode<String>("discretas");

        queue.offer(node);

        assertFalse(queue.isEmpty());

        queue.poll();

        assertTrue(queue.isEmpty());
    } // test Is Empty 3
    // IS EMPTY TESTS
}
