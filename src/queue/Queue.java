package queue;

public class Queue<V> implements IQueue<V>{

    private QueueNode<V> queue[];
    private QueueNode<V> front;
    private QueueNode<V> back;

    public Queue(int size){
        queue = new QueueNode[size];
        front = null;
        back = null;
    } // Constructor

    public void delete(int index){

        if(isEmpty()){
            return;
        } else {
            queue[index] = null;

            for (int i = 0; i < queue.length-1; i++) {

                queue[i] = queue[i+1];
            }
        }
    } // delete

    @Override
    public boolean isEmpty() {
        if(queue[0]==null){
            return true;
        } else {
            return false;
        }
    } // Is empty

    @Override
    public V peek() {

        if(isEmpty()){
            return null;
        } else {
            front = queue[0];
            return queue[0].getValue();
        }
    } // Peek

    @Override
    public V poll() {

        V value;

        if(isEmpty()){
            return null;
        } else {
            value = queue[0].getValue();
            queue[0] = null;

            for (int i = 0; i < queue.length-1; i++) {
                queue[i] = queue[i+1];
            }
        }

        return value;
    } // Poll

    @Override
    public boolean offer(QueueNode<V> item) {

        for (int i = 0; i < queue.length; i++) {

            if(queue[i]==null){
                queue[i] = item;
                back = queue[i];
                return true;
            }
        }

        return false;
    } // Offer

    public int occupedSize(){

        int size = 0;

        for (int i = 0; i < queue.length; i++) {

            if(queue[i]!=null){
                size++;
            }

            if(queue[i]==null){
                break;
            }
        }

        return size;
    } // occuped size

    @Override
    public int size() {
        return queue.length;
    }

    public QueueNode<V>[] getQueue() {
        return queue;
    }

    public void setQueue(QueueNode<V>[] queue) {
        this.queue = queue;
    }

    public QueueNode<V> getFront() {
        return front;
    }

    public void setFront(QueueNode<V> front) {
        this.front = front;
    }

    public QueueNode<V> getBack() {
        return back;
    }

    public void setBack(QueueNode<V> back) {
        this.back = back;
    }
} // Queue
