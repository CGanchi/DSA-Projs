
public class DNode<T> {
	private Comparable<T> element;
	private DNode<T> next;
	private DNode<T> prev;
	
	DNode(Comparable<T> elt, DNode<T> next, DNode<T> prev){
		element = elt;
		this.next = next;
		this.prev = prev;
	}
	
	DNode(Comparable<T> elt){
		element = elt; 
		next = null;
		prev = null;
	}
	
	
	public DNode<T> getNext() { return next; }
	
	public DNode<T> getPrev() { return prev; }
	
	public Comparable<T> getElt() { return element; }
	
	public void setNext(DNode<T> nxt) { next = nxt; }
	public void setPrev(DNode<T> prev) { this.prev = prev; }
	public void setElt(Comparable<T> elt) { element = elt; }
	public String toString() { return element.toString(); }
	
}
