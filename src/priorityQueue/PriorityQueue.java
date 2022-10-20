package priorityQueue;

public class PriorityQueue<E, K> implements IPriorityQueue<E, K> {

    private PriorityQueueNode<E, K>[] priorityQueue;

    public PriorityQueue(int size){
        this.priorityQueue = new PriorityQueueNode[size];
    }

    public PriorityQueueNode<E, K>[] getPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(PriorityQueueNode<E, K>[] priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    @Override
    public boolean insert(PriorityQueueNode<E, K> node) {

        for (int i = 0; i < priorityQueue.length; i++) {

            if(priorityQueue[i]==null){
                priorityQueue[i] = node;
                return true;
            }
        }

        return false;
    }

    @Override
    public PriorityQueueNode<E, K> maximum() {
        quickSortMajorMinor(0, priorityQueue.length-1);
        return priorityQueue[0];
    }

    @Override
    public PriorityQueueNode<E, K> minimum() {
        quickSortMinorMajor(0, priorityQueue.length-1);
        return priorityQueue[0];
    }

    @Override
    public PriorityQueueNode<E, K> extractMax() {
        quickSortMajorMinor(0, priorityQueue.length-1);

        if(isEmpty()){
            return null;
        } else {
            priorityQueue[0] = null;

            for (int i = 0; i < priorityQueue.length-1; i++) {
                priorityQueue[i] = priorityQueue[i+1];
            }
        }

        return priorityQueue[0];
    }

    @Override
    public PriorityQueueNode<E, K> extractMin() {
        quickSortMinorMajor(0, priorityQueue.length-1);

        if(isEmpty()){
            return null;
        } else {
            priorityQueue[0] = null;

            for (int i = 0; i < priorityQueue.length-1; i++) {
                priorityQueue[i] = priorityQueue[i+1];
            }
        }

        return priorityQueue[0];
    }

    public void quickSortMajorMinor(int inicio, int fin) {

        for (int i = 0; i < priorityQueue.length; i++) {
            if(priorityQueue[i]==null){
                fin = i-1;
                break;
            }
        }

        if (inicio >= fin) return;

        String pivote = (String) priorityQueue[inicio].getKey();
        int elemIzq = inicio + 1;
        int elemDer = fin;

        String keyIzq = (String) priorityQueue[elemIzq].getKey();
        String keyDer = (String) priorityQueue[elemDer].getKey();


        while (elemIzq <= elemDer) {
            while (elemIzq <= fin && ( keyIzq.compareTo(pivote) >= 0 ) ) {
                elemIzq++;
            }
            while (elemDer > inicio && (keyDer.compareTo(pivote) < 0) ) {
                elemDer--;
            }
            if (elemIzq < elemDer) {
                PriorityQueueNode<E, K> temp = priorityQueue[elemIzq];
                priorityQueue[elemIzq] = priorityQueue[elemDer];
                priorityQueue[elemDer] = temp;
            }
        }

        if (elemDer > inicio) {
            PriorityQueueNode<E, K> temp = priorityQueue[inicio];
            priorityQueue[inicio] = priorityQueue[elemDer];
            priorityQueue[elemDer] = temp;
        }
        quickSortMajorMinor(inicio, elemDer - 1);
        quickSortMajorMinor(elemDer + 1, fin);
    } // quicksort for priority queue (major to minor)

    public void quickSortMinorMajor(int inicio, int fin) {

        for (int i = 0; i < priorityQueue.length; i++) {
            if(priorityQueue[i]==null){
                fin = i-1;
                break;
            }
        }

        if (inicio >= fin) return;

        String pivote = (String) priorityQueue[inicio].getKey();
        int elemIzq = inicio + 1;
        int elemDer = fin;

        String keyIzq = (String) priorityQueue[elemIzq].getKey();
        String keyDer = (String) priorityQueue[elemDer].getKey();


        while (elemIzq <= elemDer) {
            while (elemIzq <= fin && ( keyIzq.compareTo(pivote) < 0 ) ) {
                elemIzq++;
            }
            while (elemDer > inicio && (keyDer.compareTo(pivote) >= 0) ) {
                elemDer--;
            }
            if (elemIzq < elemDer) {
                PriorityQueueNode<E, K> temp = priorityQueue[elemIzq];
                priorityQueue[elemIzq] = priorityQueue[elemDer];
                priorityQueue[elemDer] = temp;
            }
        }

        if (elemDer > inicio) {
            PriorityQueueNode<E, K> temp = priorityQueue[inicio];
            priorityQueue[inicio] = priorityQueue[elemDer];
            priorityQueue[elemDer] = temp;
        }
        quickSortMinorMajor(inicio, elemDer - 1);
        quickSortMinorMajor(elemDer + 1, fin);
    } // quicksort for priority queue (minor to major)


    @Override
    public boolean increaseKey(E element, K key) {

        for (int i = 0; i < priorityQueue.length; i++) {

            if(priorityQueue[i].getElement().equals(element)){

                String elementKey = (String) priorityQueue[i].getKey();
                String increaseKey = (String) key;

                if(elementKey.compareToIgnoreCase(increaseKey) >= 0){
                    return false;
                } else {
                    priorityQueue[i].setKey(key);
                    return true;
                }
            }
        }

        return false;
    } // increase key

    @Override
    public boolean decreaseKey(E element, K key) {

        for (int i = 0; i < priorityQueue.length; i++) {

            if(priorityQueue[i].getElement().equals(element)){

                String elementKey = (String) priorityQueue[i].getKey();
                String increaseKey = (String) key;

                if(elementKey.compareToIgnoreCase(increaseKey) <= 0){
                    return false;
                } else {
                    priorityQueue[i].setKey(key);
                    return true;
                }
            }
        }

        return false;
    } // decrease key

    public boolean isEmpty() {
        if(priorityQueue[0]==null){
            return true;
        } else {
            return false;
        }
    } // Is empty

    public int occupedSize(){

        int size = 0;

        for (int i = 0; i < priorityQueue.length; i++) {

            if(priorityQueue[i]!=null){
                size++;
            }

            if(priorityQueue[i]==null){
                break;
            }
        }

        return size;
    } // occuped size
}
