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

public class IPSController {

    private Hashtable<String, Patient> hashtable;
    private Queue<Patient> queueHematology;
    private PriorityQueue<Patient, Integer> priorityQueueHematology;
    private Queue<Patient> queueGeneralPurpose;
    private PriorityQueue<Patient, Integer> priorityQueueGeneralPurpose;
    private Stack<Action> stack;

    public IPSController(){
        this.hashtable = new Hashtable<>(100000);
        this.queueHematology = new Queue<>(100000);
        this.priorityQueueHematology = new PriorityQueue<>(100000);
        this.queueGeneralPurpose = new Queue<>(100000);
        this.priorityQueueGeneralPurpose = new PriorityQueue<>(100000);
        this.stack = new Stack<>();
    }

    public boolean editPatient(String id, int option, String change, int changePriority){
        if(hashtable.search(id)==null){
            return false;
        } else {
            switch (option) {
                case 1 -> {
                    hashtable.search(id).setName(change);
                    return true;
                }
                case 2 -> {
                    hashtable.search(id).setPriority(changePriority);
                    return true;
                }
            }
        }
        return false;
    } // edit patient

    public boolean increaseKey(String id, int newAmount){

        if(priorityQueueHematology.getPriorityQueue().length==0){
            return false;
        } else {
            for (int i = 0; i < priorityQueueHematology.getPriorityQueue().length; i++) { // n

                if(priorityQueueHematology.getPriorityQueue()[i]!=null){
                    if (priorityQueueHematology.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                            (id)) { // n-1
                        if(newAmount> priorityQueueHematology.getPriorityQueue()[i].getElement().getPriority()){
                            priorityQueueHematology.getPriorityQueue()[i].getElement().setPriority(newAmount);
                        }

                        return priorityQueueHematology.increaseKey(priorityQueueHematology.getPriorityQueue()[i].getElement(), newAmount); // 2(n - 1) - 1 = 2n - 2 - 1 = | 2n - 3 |
                    }
                }
            } // delete an inserted patient priority queue
            return false;
        }
    } // increase key

    public boolean increaseKeyGeneralPurpose(String id, int newAmount){

        if(priorityQueueGeneralPurpose.getPriorityQueue().length==0){
            return false;
        } else {
            for (int i = 0; i < priorityQueueGeneralPurpose.getPriorityQueue().length; i++) { // n

                if(priorityQueueGeneralPurpose.getPriorityQueue()[i]!=null){
                    if (priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                            (id)) { // n-1
                        if(newAmount> priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().getPriority()){
                            priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().setPriority(newAmount);
                        }

                        return priorityQueueGeneralPurpose.increaseKey(priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement(), newAmount); // 2(n - 1) - 1 = 2n - 2 - 1 = | 2n - 3 |
                    }
                }
            } // delete an inserted patient priority queue
            return false;
        }
    } // increase key

    public boolean decreaseKey(String id, int newAmount){

        if(priorityQueueHematology.getPriorityQueue().length==0){
            return false;
        } else {
            for (int i = 0; i < priorityQueueHematology.getPriorityQueue().length; i++) { // n

                if(priorityQueueHematology.getPriorityQueue()[i]!=null){

                    if (priorityQueueHematology.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                            (id)) { // n-1

                        if(newAmount< priorityQueueHematology.getPriorityQueue()[i].getElement().getPriority()){
                            priorityQueueHematology.getPriorityQueue()[i].getElement().setPriority(newAmount);
                        }
                        return priorityQueueHematology.decreaseKey(priorityQueueHematology.getPriorityQueue()[i].getElement(), newAmount); // 2(n - 1) - 1 = 2n - 2 - 1 = | 2n - 3 |
                    }
                }
            } // delete an inserted patient priority queue
            return false;
        }
    } // decrease key

    public boolean decreaseKeyGeneralPurpose(String id, int newAmount){

        if(priorityQueueGeneralPurpose.getPriorityQueue().length==0 && queueGeneralPurpose.getQueue().length==0){
            return false;
        } else {
            for (int i = 0; i < priorityQueueGeneralPurpose.getPriorityQueue().length; i++) { // n

                if(priorityQueueGeneralPurpose.getPriorityQueue()[i]!=null){

                    if (priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                            (id)) { // n-1

                        if(newAmount< priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().getPriority()){
                            priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().setPriority(newAmount);
                        }
                        return priorityQueueGeneralPurpose.decreaseKey(priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement(), newAmount); // 2(n - 1) - 1 = 2n - 2 - 1 = | 2n - 3 |
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

        if(search(id)!=null){
            return false;
        } else {
            HashtableNode<String, Patient> hashtableNode = new HashtableNode<>(patient.getId(), patient);
            hashtable.insert(hashtableNode, hashtableNode.getKey());

            return true;
        }
    } // register patient

    public boolean entry(String id){
        Patient patient = hashtable.search(id);

        if(patient!=null){
            if(patient.isInQueue()){
                return false;
            }

            patient.setInQueue(true);
            stack.push(new Action("entry", patient, 1));

            if(patient.getPriority()>0){
                priorityQueueHematology.insert(new PriorityQueueNode<>(patient, patient.getPriority()));
                return true;
            } else {
                queueHematology.offer(new QueueNode<>(patient));
                return true;
            }
        } else {
            return false;
        }
    } // entry patient to queue

    public boolean entryGeneralPurpose(String id){
        Patient patient = hashtable.search(id);

        if(patient!=null){
            if(patient.isInQueue()){
                return false;
            }

            patient.setInQueue(true);
            stack.push(new Action("entry", patient, 2));

            if(patient.getPriority()>0){
                priorityQueueGeneralPurpose.insert(new PriorityQueueNode<>(patient, patient.getPriority()));
                return true;
            } else {
                queueGeneralPurpose.offer(new QueueNode<>(patient));
                return true;
            }
        } else {
            return false;
        }
    } // entry patient to queue

    public String out(){

        Patient patient;

        for (int i = 0; i < priorityQueueHematology.getPriorityQueue().length; i++) {
            if(priorityQueueHematology.getPriorityQueue()[i]!=null){
                if(priorityQueueHematology.getPriorityQueue()[i].getKey()==0){
                    QueueNode<Patient> queueNode = new QueueNode<>(priorityQueueHematology.getPriorityQueue()[i].getElement());
                    queueHematology.offer(queueNode);
                    priorityQueueHematology.delete(i);
                }
            }
        }

        for (int i = 0; i < queueHematology.size(); i++) {
            if(queueHematology.getQueue()[i]!=null){
                if(queueHematology.getQueue()[i].getValue().getPriority()>0){
                    PriorityQueueNode<Patient, Integer> priorityQueueNode = new PriorityQueueNode<>(queueHematology.getQueue()[i].getValue(), queueHematology.getQueue()[i].getValue().getPriority());
                    priorityQueueHematology.insert(priorityQueueNode);
                    queueHematology.delete(i);
                }
            }
        }

        if(priorityQueueHematology.isEmpty()){
            patient = queueHematology.poll();
            patient.setInQueue(false);
        } else {
            patient = priorityQueueHematology.extractMax();
            patient.setInQueue(false);
        }

        stack.push(new Action("out", patient, 1));

        if(patient!=null){
            return "- - ID: "+patient.getId()+
                    "\n- - NAME: "+patient.getName();
        } else {
            return null;
        }
    } // out

    public String outGeneralPurpose(){

        Patient patient;

        for (int i = 0; i < priorityQueueGeneralPurpose.getPriorityQueue().length; i++) {
            if(priorityQueueGeneralPurpose.getPriorityQueue()[i]!=null){
                if(priorityQueueGeneralPurpose.getPriorityQueue()[i].getKey()==0){
                    QueueNode<Patient> queueNode = new QueueNode<>(priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement());
                    queueGeneralPurpose.offer(queueNode);
                    priorityQueueGeneralPurpose.delete(i);
                }
            }
        }

        for (int i = 0; i < queueGeneralPurpose.size(); i++) {
            if(queueGeneralPurpose.getQueue()[i]!=null){
                if(queueGeneralPurpose.getQueue()[i].getValue().getPriority()>0){
                    PriorityQueueNode<Patient, Integer> priorityQueueNode = new PriorityQueueNode<>(queueGeneralPurpose.getQueue()[i].getValue(), queueHematology.getQueue()[i].getValue().getPriority());
                    priorityQueueGeneralPurpose.insert(priorityQueueNode);
                    queueGeneralPurpose.delete(i);
                }
            }
        }

        if(priorityQueueGeneralPurpose.isEmpty()){
            patient = queueGeneralPurpose.poll();
            patient.setInQueue(false);
        } else {
            patient = priorityQueueGeneralPurpose.extractMax();
            patient.setInQueue(false);
        }

        stack.push(new Action("out", patient, 2));

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

                    if(action.getLab()==1){
                        for (int i = 0; i < priorityQueueHematology.getPriorityQueue().length; i++) { // n

                            if (priorityQueueHematology.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                                    (action.getWhichPatient().getId())) { // n-1
                                priorityQueueHematology.getPriorityQueue()[i].getElement().setInQueue(false);
                                priorityQueueHematology.delete(i); // 2(n - 1) - 1 = 2n - 2 - 1 = | 2n - 3 |
                                return action.getWhichAction(); // n-1
                            }
                        } // delete an inserted patient priority queue
                    } else {
                        for (int i = 0; i < priorityQueueGeneralPurpose.getPriorityQueue().length; i++) { // n

                            if (priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().getId().equalsIgnoreCase
                                    (action.getWhichPatient().getId())) { // n-1
                                priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().setInQueue(false);
                                priorityQueueGeneralPurpose.delete(i); // 2(n - 1) - 1 = 2n - 2 - 1 = | 2n - 3 |
                                return action.getWhichAction(); // n-1
                            }
                        } // delete an inserted patient priority queue
                    }

                } else {

                    if(action.getLab()==1){
                        for (int i = 0; i < queueHematology.getQueue().length; i++) { // n

                            if (queueHematology.getQueue()[i].getValue().getId().equalsIgnoreCase
                                    (action.getWhichPatient().getId())) { // n - 1
                                queueHematology.getQueue()[i].getValue().setInQueue(false);
                                queueHematology.delete(i); // 2n - 3
                                return action.getWhichAction(); // n-1
                            }
                        } // delete an inserted patient queue
                    } else {
                        for (int i = 0; i < queueGeneralPurpose.getQueue().length; i++) { // n

                            if (queueGeneralPurpose.getQueue()[i].getValue().getId().equalsIgnoreCase
                                    (action.getWhichPatient().getId())) { // n - 1
                                queueGeneralPurpose.getQueue()[i].getValue().setInQueue(false);
                                queueGeneralPurpose.delete(i); // 2n - 3
                                return action.getWhichAction(); // n-1
                            }
                        } // delete an inserted patient queue
                    }
                }

            } else {
                if(action.getWhichPatient().getPriority()>0){ // 1

                    if(action.getLab()==1){
                        priorityQueueHematology.insert(new PriorityQueueNode<>(action.getWhichPatient(), action.getWhichPatient().getPriority())); // 2(n - 1)
                        // insert a deleted patient priority queue
                        action.getWhichPatient().setInQueue(true);
                        return action.getWhichAction(); // 1
                    } else {
                        priorityQueueGeneralPurpose.insert(new PriorityQueueNode<>(action.getWhichPatient(), action.getWhichPatient().getPriority())); // 2(n - 1)
                        // insert a deleted patient priority queue
                        action.getWhichPatient().setInQueue(true);
                        return action.getWhichAction(); // 1
                    }
                } else {

                    if(action.getLab()==1){
                        queueHematology.offer(new QueueNode<>(action.getWhichPatient())); // 5n - 3
                        // insert a deleted patient queue
                        action.getWhichPatient().setInQueue(true);
                        return action.getWhichAction(); // 1
                    } else {
                        queueGeneralPurpose.offer(new QueueNode<>(action.getWhichPatient())); // 5n - 3
                        // insert a deleted patient queue
                        action.getWhichPatient().setInQueue(true);
                        return action.getWhichAction(); // 1
                    }
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

        if(!priorityQueueHematology.isEmpty()){
            msg += "\n-> ID: "+ priorityQueueHematology.maximum().getElement().getId()+".\n" +
                    "-> NAME: "+ priorityQueueHematology.getPriorityQueue()[0].getElement().getName()+".\n"+
                    "-> PRIORITY: "+ priorityQueueHematology.getPriorityQueue()[0].getElement().getPriority()+"\n-------------------------";

                for (int i = 1; i < priorityQueueHematology.getPriorityQueue().length; i++) {

                    if(priorityQueueHematology.getPriorityQueue()[i]==null){
                        break;
                    }

                    msg += "\n-> ID: "+ priorityQueueHematology.getPriorityQueue()[i].getElement().getId()+".\n" +
                            "-> NAME: "+ priorityQueueHematology.getPriorityQueue()[i].getElement().getName()+".\n"+
                            "-> PRIORITY: "+ priorityQueueHematology.getPriorityQueue()[i].getElement().getPriority()+"\n-------------------------";
                }

        }

        if(!queueHematology.isEmpty()){
            for (int i = 0; i < queueHematology.getQueue().length; i++) {

                if(queueHematology.getQueue()[i]==null){
                    break;
                }

                msg += "\n-> ID: "+ queueHematology.getQueue()[i].getValue().getId()+".\n" +
                        "-> NAME: "+ queueHematology.getQueue()[i].getValue().getName()+".\n-------------------------";
            }
        }

        return msg;
    }

    public String queueOrderGeneralPurpose(){

        String msg = "";

        if(!priorityQueueGeneralPurpose.isEmpty()){
            msg += "\n-> ID: "+ priorityQueueGeneralPurpose.maximum().getElement().getId()+".\n" +
                    "-> NAME: "+ priorityQueueGeneralPurpose.getPriorityQueue()[0].getElement().getName()+".\n"+
                    "-> PRIORITY: "+ priorityQueueGeneralPurpose.getPriorityQueue()[0].getElement().getPriority()+"\n-------------------------";

            for (int i = 1; i < priorityQueueGeneralPurpose.getPriorityQueue().length; i++) {

                if(priorityQueueGeneralPurpose.getPriorityQueue()[i]==null){
                    break;
                }

                msg += "\n-> ID: "+ priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().getId()+".\n" +
                        "-> NAME: "+ priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().getName()+".\n"+
                        "-> PRIORITY: "+ priorityQueueGeneralPurpose.getPriorityQueue()[i].getElement().getPriority()+"\n-------------------------";
            }

        }

        if(!queueGeneralPurpose.isEmpty()){
            for (int i = 0; i < queueGeneralPurpose.getQueue().length; i++) {

                if(queueGeneralPurpose.getQueue()[i]==null){
                    break;
                }

                msg += "\n-> ID: "+ queueGeneralPurpose.getQueue()[i].getValue().getId()+".\n" +
                        "-> NAME: "+ queueGeneralPurpose.getQueue()[i].getValue().getName()+".\n-------------------------";
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
        return queueHematology;
    }

    public void setQueue(Queue<Patient> queue) {
        this.queueHematology = queue;
    }

    public PriorityQueue<Patient, Integer> getPriorityQueue() {
        return priorityQueueHematology;
    }

    public void setPriorityQueue(PriorityQueue<Patient, Integer> priorityQueue) {
        this.priorityQueueHematology = priorityQueue;
    }

    public Stack<Action> getStack() {
        return stack;
    }

    public void setStack(Stack<Action> stack) {
        this.stack = stack;
    }
}
