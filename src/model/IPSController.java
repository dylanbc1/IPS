package model;

import hashtable.Hashtable;
import hashtable.HashtableNode;
import priorityQueue.PriorityQueue;
import priorityQueue.PriorityQueueNode;
import queue.Queue;
import queue.QueueNode;
import stack.Stack;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    public boolean increaseKey(String id, int newAmount){

        if(priorityQueue.getPriorityQueue().length==0){
            return false;
        } else {
            for (int i = 0; i < priorityQueue.getPriorityQueue().length; i++) { // n

                if(priorityQueue.getPriorityQueue()[i]!=null){
                    if (priorityQueue.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                            (id)) { // n-1
                        if(newAmount>priorityQueue.getPriorityQueue()[i].getElement().getPriority()){
                            priorityQueue.getPriorityQueue()[i].getElement().setPriority(newAmount);
                        }

                        return priorityQueue.increaseKey(priorityQueue.getPriorityQueue()[i].getElement(), newAmount); // 2(n - 1) - 1 = 2n - 2 - 1 = | 2n - 3 |
                    }
                }
            } // delete an inserted patient priority queue
            return false;
        }
    } // increase key

    public boolean decreaseKey(String id, int newAmount){

        if(priorityQueue.getPriorityQueue().length==0){
            return false;
        } else {
            for (int i = 0; i < priorityQueue.getPriorityQueue().length; i++) { // n

                if(priorityQueue.getPriorityQueue()[i]!=null){

                    if (priorityQueue.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                            (id)) { // n-1

                        if(newAmount<priorityQueue.getPriorityQueue()[i].getElement().getPriority()){
                            priorityQueue.getPriorityQueue()[i].getElement().setPriority(newAmount);
                        }
                        return priorityQueue.decreaseKey(priorityQueue.getPriorityQueue()[i].getElement(), newAmount); // 2(n - 1) - 1 = 2n - 2 - 1 = | 2n - 3 |
                    }
                }
            } // delete an inserted patient priority queue
            return false;
        }
    } // decrease key

    public String save(){
        String text = hashtable.toString();

        File file = new File("ips.temp");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes(StandardCharsets.UTF_8));
            fos.close();
            return "";

        } catch (FileNotFoundException fnfe){
            return fnfe.getMessage();
        }catch (IOException ioException){
            return ioException.getMessage();
        }
    } // save

    public String load(){
        File file = new File("ips.temp");

        try{
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;

            while( (line = reader.readLine()) != null){
                String[] parts = line.split("-");
                Patient p = new Patient(parts[0], parts[1], parts[2].charAt(0), parts[3], Integer.parseInt(parts[4]));
                HashtableNode<String, Patient> node = new HashtableNode<>(p.getId(), p);
                hashtable.insert(node, node.getKey());
            }
            fis.close();
            return "";

        } catch (FileNotFoundException fnfe){
            return fnfe.getMessage();
        } catch (IOException ioException){
            return ioException.getMessage();
        }
    } // load

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

        for (int i = 0; i < priorityQueue.getPriorityQueue().length; i++) {
            if(priorityQueue.getPriorityQueue()[i]!=null){
                if(priorityQueue.getPriorityQueue()[i].getKey()==0){
                    QueueNode<Patient> queueNode = new QueueNode(priorityQueue.getPriorityQueue()[i].getElement());
                    queue.offer(queueNode);
                    priorityQueue.delete(i);
                }
            }
        }

        for (int i = 0; i < queue.size(); i++) {
            if(queue.getQueue()[i]!=null){
                if(queue.getQueue()[i].getValue().getPriority()>0){
                    PriorityQueueNode<Patient, Integer> priorityQueueNode = new PriorityQueueNode<>(queue.getQueue()[i].getValue(), queue.getQueue()[i].getValue().getPriority());
                    priorityQueue.insert(priorityQueueNode);
                    queue.delete(i);
                }
            }
        }

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

        Action action = stack.pop().getValue(); //

        if(action!=null){ // 1
            if(action.getWhichAction().equalsIgnoreCase("entry")){ // 1

                if(action.getWhichPatient().getPriority()>0){ // 1

                    for (int i = 0; i < priorityQueue.getPriorityQueue().length; i++) { // n

                        if (priorityQueue.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                                (action.getWhichPatient().getId())) { // n-1
                            priorityQueue.delete(i); // 2(n - 1) - 1 = 2n - 2 - 1 = | 2n - 3 |
                            return action.getWhichAction(); // n-1
                        }
                    } // delete an inserted patient priority queue

                } else {

                    for (int i = 0; i < queue.getQueue().length; i++) { // n

                        if (queue.getQueue()[i].getValue().getId().equalsIgnoreCase
                                (action.getWhichPatient().getId())) { // n - 1
                            queue.delete(i); // 2n - 3
                            return action.getWhichAction(); // n-1
                        }
                    } // delete an inserted patient queue
                }

            } else {
                if(action.getWhichPatient().getPriority()>0){ // 1
                    priorityQueue.insert(new PriorityQueueNode<>(action.getWhichPatient(), action.getWhichPatient().getPriority())); // 2(n - 1)
                    // insert a deleted patient priority queue
                    return action.getWhichAction(); // 1
                } else {
                    queue.offer(new QueueNode<>(action.getWhichPatient())); // 5n - 3
                    // insert a deleted patient queue
                    return action.getWhichAction(); // 1
                }
            }
        } // (sin los else)
        // TOTAL TEMPORAL = 1 + 1 + 1 + n + (n - 1) + (2n - 3) + (n - 1) + n + (n - 1) + (2n - 3) + (n - 1) + 1 + 2(n - 1) + 1 + (5n - 3) + 1
        // =
        // = | 13n - 3 |

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
