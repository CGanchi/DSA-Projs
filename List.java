//Camron Ganchi
//Data Structures and Algorithms
//December 5th
//Ms. Tompsett
//Doubly Linked List

public class List<T> {
    //things I want to keep track of
    private DNode<T> start;
    private DNode<T> end;
    private int size;


    //list is empty upon creation
    public List(){
        start = null;
        end = null;
        size = 0;
    }

    //look iterate the list pos times and then return the current elt
    public Comparable<T> get(int pos) throws Exception{
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        DNode<T> current = start;
        for (int i = 0; i < pos; i++) {
            current = current.getNext();
        }
        return current.getElt();
    }

    //iterate through the list until you find o and return its index (-1 if not found)
    public int find(Comparable<T> o){
        DNode<T> current = start;
        int index = 0;
        while (current != null) {
            if (current.getElt().equals(o)) return index;
            current = current.getNext();
            index++;
        }
        return -1;
    }

    //just access and return the length
    public int length(){return size;}

    //Use string concatenation and iteration through the list to print out the list
    public String toString(){
        String result = "";
        DNode<T> current = start;
        while (current != null) {
            result += current.getElt() + " ";
            current = current.getNext();
        }
        return result.trim();
    }

    //iterate through the list pos times and set that element to o
    public boolean set(Comparable<T> o, int pos) throws Exception{
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        DNode<T> current = start;
        for (int i = 0; i < pos; i++) {
            current = current.getNext();
        }
        current.setElt(o);
        return true;
    }

    //just add o to the end of the list (it becomes the new end) by connecting
    // it to the original end (increment size)
    public void add(Comparable<T> o){
        DNode<T> newNode = new DNode<>(o);
        if (start == null) {
            start = newNode;
            end = newNode;
        } else {
            end.setNext(newNode);
            newNode.setPrev(end);
            end = newNode;
        }
        size++;
    }

    //insert a new Node at position pos. Deal with cases where pos == 0
    // and where the user is just asking for the add method, then
    // deal with cases where those arent true: iterate through the
    // list until pos-1 then insert the new Node at pos by connecting
    // forwards and backwards with the original pos-1 and pos.
    public void insert(Comparable<T> o, int pos) throws Exception{
        if (pos < 0 || pos > size) throw new IndexOutOfBoundsException();
        DNode<T> newNode = new DNode<>(o);
        if (pos == 0) {
            newNode.setNext(start);
            if (start != null) start.setPrev(newNode);
            start = newNode;
            if (end == null) end = newNode;
        } else if (pos == size) {
            add(o);
        } else {
            DNode<T> current = start;
            for (int i = 0; i < pos - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            newNode.setPrev(current);
            if (current.getNext() != null) current.getNext().setPrev(newNode);
            current.setNext(newNode);
        }
        size++;
    }

    // see comments in the method
    public Comparable<T> remove(int pos) throws Exception{
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException(); //checking that pos is valid input
        DNode<T> current; //using this to save the Node we are removing
        if (pos == 0) { //shifting start to the right
            current = start;
            start = start.getNext();
            if (start != null) start.setPrev(null);
            if (current == end) end = null;
        } else if (pos == size - 1) { //chopping off the last Node
            current = end;
            end = end.getPrev();
            if (end != null) end.setNext(null);
        } else { //iterate till current is the Node at pos and connect the Nodes
            // on its left and right together
            current = start;
            for (int i = 0; i < pos; i++) {
                current = current.getNext();
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
        size--; //decrement size
        return current.getElt();
    }

    // see comments in the method
    public boolean remove(Comparable<T> o){
        DNode<T> current = start;
        while (current != null) { //looking through list to find o
            if (current.getElt().equals(o)) { //if we find o then we can take the same
                // steps as the remove method above (just without the need for a for loop)
                if (current == start) {
                    start = start.getNext();
                    if (start != null) start.setPrev(null);
                    if (current == end) end = null;
                } else if (current == end) {
                    end = end.getPrev();
                    if (end != null) end.setNext(null);
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                size--; //decrement size after removing
                return true; //signal that we removed it
            }
            current = current.getNext(); //if not we take the next Node and try again
        }
        return false; // Signal that we did not remove it because it was not found
    }

    //just make the list empty
    public void clear(){
        start = null;
        end = null;
        size = 0;
    }

    //bubble sort
    public void sort(){
        if (size <= 1) return;
        for (DNode<T> i = start; i != null; i = i.getNext()) {
            for (DNode<T> j = i.getNext(); j != null; j = j.getNext()) {
                if (i.getElt().compareTo((T) j.getElt()) > 0) {
                    Comparable<T> temp = i.getElt();
                    i.setElt(j.getElt());
                    j.setElt(temp);
                }
            }
        }
    }
}
