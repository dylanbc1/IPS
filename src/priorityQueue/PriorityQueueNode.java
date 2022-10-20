package priorityQueue;

public class PriorityQueueNode<E, K>{

    private K key;
    private E element;

    public PriorityQueueNode(E element, K key){
        this.element = element;
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
}
