package model;

import hashtable.Hashtable;
import hashtable.HashtableNode;
import priorityQueue.PriorityQueue;
import priorityQueue.PriorityQueueNode;
import queue.Queue;
import queue.QueueNode;
import stack.Stack;

public class IPSController {

    private Hashtable<String, Patient> hashtable;
    private Queue<Patient> queue;
    private PriorityQueue<Patient, Integer> priorityQueue;
    private Stack<Action> stack;

    public IPSController(){
        this.hashtable = new Hashtable<>(100000);
        this.queue = new Queue<>(100000);
        this.priorityQueue = new PriorityQueue<>(100000);
        this.stack = new Stack<>();
    }

    public Patient search(String id){
        if(hashtable.containsKey(id)){
            return hashtable.search(id);
        } else {
            return null;
        }
    } // search patient

    public boolean registerPatient(String id, String name, char genre, String birthDate, int priority){
        Patient patient = new Patient(id, name, genre, birthDate, priority);

        HashtableNode<String, Patient> hashtableNode = new HashtableNode<>(patient.getId(), patient);
        hashtable.insert(hashtableNode, hashtableNode.getKey());

        return true;
    } // register patient

    public boolean entry(String id){
        Patient patient = hashtable.search(id);
        stack.push(new Action("entry", patient));

        if(patient.getPriority()>0){
            priorityQueue.insert(new PriorityQueueNode<>(patient, patient.getPriority()));
            return true;
        } else {
            queue.offer(new QueueNode<>(patient));
            return true;
        }
    } // entry patient to queue

    public Patient out(){

        Patient patient;

        if(priorityQueue.isEmpty()){
            patient = queue.poll();
        } else {
            patient = priorityQueue.extractMax().getElement();
        }

        stack.push(new Action("out", patient));
        return patient;
    } // out

    ///// GET and SET /////
    public Hashtable<String, Patient> getHashtable() {
        return hashtable;
    }

    public void setHashtable(Hashtable<String, Patient> hashtable) {
        this.hashtable = hashtable;
    }

    public Queue<Patient> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Patient> queue) {
        this.queue = queue;
    }

    public PriorityQueue<Patient, Integer> getPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(PriorityQueue<Patient, Integer> priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    public Stack<Action> getStack() {
        return stack;
    }

    public void setStack(Stack<Action> stack) {
        this.stack = stack;
    }
}
