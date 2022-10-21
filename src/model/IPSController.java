package model;

import hashtable.Hashtable;
import hashtable.HashtableNode;
import priorityQueue.PriorityQueue;
import priorityQueue.PriorityQueueNode;
import queue.Queue;
import queue.QueueNode;
import stack.Stack;

import java.util.Collections;

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

        if(patient.isInQueue()){
            return false;
        }

        patient.setInQueue(true);
        stack.push(new Action("entry", patient));

        if(patient!=null){
            if(patient.getPriority()>0){
                priorityQueue.insert(new PriorityQueueNode<>(patient, patient.getPriority()));
                return true;
            } else {
                queue.offer(new QueueNode<>(patient));
                return true;
            }
        } else {
            return false;
        }
    } // entry patient to queue

    public String out(){

        Patient patient;

        if(priorityQueue.isEmpty()){
            patient = queue.poll();
            patient.setInQueue(false);
        } else {
            patient = priorityQueue.extractMax();
            patient.setInQueue(false);
        }

        stack.push(new Action("out", patient));

        if(patient!=null){
            return "- - ID: "+patient.getId()+
                    "\n- - NAME: "+patient.getName();
        } else {
            return null;
        }
    } // out

    public String undo(){

        Action action = stack.pop().getValue();

        if(action!=null){
            if(action.getWhichAction().equalsIgnoreCase("entry")){

                if(action.getWhichPatient().getPriority()>0){

                    for (int i = 0; i < priorityQueue.getPriorityQueue().length; i++) {

                        if (priorityQueue.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                                (action.getWhichPatient().getId())) {
                            priorityQueue.delete(i);
                            return action.getWhichAction();
                        }
                    } // delete an inserted patient priority queue

                } else {

                    for (int i = 0; i < queue.getQueue().length; i++) {

                        if (queue.getQueue()[i].getValue().getId().equalsIgnoreCase
                                (action.getWhichPatient().getId())) {
                            queue.delete(i);
                            return action.getWhichAction();
                        }
                    } // delete an inserted patient queue
                }

            } else {
                if(action.getWhichPatient().getPriority()>0){
                    priorityQueue.insert(new PriorityQueueNode<>(action.getWhichPatient(), action.getWhichPatient().getPriority()));
                    // insert a deleted patient priority queue
                    return action.getWhichAction();
                } else {
                    queue.offer(new QueueNode<>(action.getWhichPatient()));
                    // insert a deleted patient queue
                    return action.getWhichAction();
                }
            }
        }

        return "";
    } // undo

    public String queueOrder(){

        String msg = "";

        if(!priorityQueue.isEmpty()){
            msg += "\n-> ID: "+priorityQueue.maximum().getElement().getId()+".\n" +
                    "-> NAME: "+priorityQueue.getPriorityQueue()[0].getElement().getName()+".\n"+
                    "-> PRIORITY: "+priorityQueue.getPriorityQueue()[0].getElement().getPriority()+"\n-------------------------";

                for (int i = 1; i < priorityQueue.getPriorityQueue().length; i++) {

                    if(priorityQueue.getPriorityQueue()[i]==null){
                        break;
                    }

                    msg += "\n-> ID: "+priorityQueue.getPriorityQueue()[i].getElement().getId()+".\n" +
                            "-> NAME: "+priorityQueue.getPriorityQueue()[i].getElement().getName()+".\n"+
                            "-> PRIORITY: "+priorityQueue.getPriorityQueue()[i].getElement().getPriority()+"\n-------------------------";
                }

        }

        if(!queue.isEmpty()){
            for (int i = 0; i < queue.getQueue().length; i++) {

                if(queue.getQueue()[i]==null){
                    break;
                }

                msg += "\n-> ID: "+queue.getQueue()[i].getValue().getId()+".\n" +
                        "-> NAME: "+queue.getQueue()[i].getValue().getName()+".\n-------------------------";
            }
        }

        return msg;
    }

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
