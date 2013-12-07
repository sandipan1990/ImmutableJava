import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
/**
* The Queue class represents an immutable first-in-first-out (FIFO) queue of objects.
* @param <E>
*/
public class PersistentQueue<E> {
private ImmutableList<E> queue;

/**
* requires default constructor.
*/
public PersistentQueue() {

//fedfwefg4wegf
// modify this constructor if necessary, but do not remove default constructor
    
	queue = new ImmutableList(-1,null,null,null);
   
}

private PersistentQueue(ImmutableList<E> queue)
{
	this.queue=queue;
}

// add other constructors if necessary
/**
* Returns the queue that adds an item into the tail of this queue without modifying this queue.
* <pre>
* e.g.
* When this queue represents the queue (2, 1, 2, 2, 6) and we enqueue the value 4 into this queue,
* this method returns a new queue (2, 1, 2, 2, 6, 4)
* and this object still represents the queue (2, 1, 2, 2, 6) .
* </pre>
* If the element e is null, throws IllegalArgumentException.
* @param e
* @return
* @throws IllegalArgumentException
*/
public PersistentQueue<E> enqueue(E e) {
// TODO: make this method faster
if (e == null) {
throw new IllegalArgumentException();
}
ImmutableList<E> new_q;
/*
if(queue.tail==this)
{

new_q=new ImmutableList<E>(e,this,this,null);
System.out.println("Created");

}
else
{

	*/
	new_q=new ImmutableList<E>(e,this.queue.tail,this,null);
	PersistentQueue<E> newq=new PersistentQueue<E>(new_q);

	this.queue.next=newq;
if(this.queue.next.queue.tail==null)
		this.queue.next.queue.tail=this.queue.next;
    
//}
	return  this.queue.next;
}
/**
* Returns the queue that removes the object at the head of this queue without modifying this queue.
* <pre>
* e.g.
* When this queue represents the queue (7, 1, 3, 3, 5, 1) ,
* this method returns a new queue (1, 3, 3, 5, 1)
* and this object still represents the queue (7, 1, 3, 3, 5, 1) .
* </pre>
* If this queue is empty, throws java.util.NoSuchElementException.
* @return
* @throws java.util.NoSuchElementException
*/
public PersistentQueue<E> dequeue() {
// TODO: make this method faster
if (queue.isEmpty()) {
throw new NoSuchElementException();
}
ImmutableList<E> new_q=new ImmutableList(-1,this,this,null);
new_q.tail=findTail(this);
new_q.value=this.queue.value;
new_q.prev=this.queue.prev;

PersistentQueue<E> pq=new PersistentQueue<E>(new_q);

return pq;
}
public PersistentQueue<E> findTail(PersistentQueue<E> org) 
{
	return org.queue.tail.queue.next;
	
	
}
/**
* Looks at the object which is the head of this queue without removing it from the queue.
* <pre>
* e.g.
* When this queue represents the queue (7, 1, 3, 3, 5, 1),
* this method returns 7 and this object still represents the queue (7, 1, 3, 3, 5, 1)
* </pre>
* If the queue is empty, throws java.util.NoSuchElementException.
* @return
* @throws java.util.NoSuchElementException
*/
public E peek() {
// modify this method if needed
	E toreturn;
if (this.queue.isEmpty()) {
throw new NoSuchElementException();
}
PersistentQueue<E> pq=this.queue.tail;
if(pq==null||pq.queue.prev==this.queue.prev.queue.next)
	throw new NoSuchElementException();
return this.queue.tail.queue.value;
}
/**
* Returns the number of objects in this queue.
* @return
*/
public int size() {
// modify this method if necessary
	
	PersistentQueue<E> pq=this.queue.tail;
	int count=1;
	if(pq==null||pq.queue.prev==this.queue.prev.queue.next)
		return 0;
	
	while(pq.hashCode()!=this.queue.prev.queue.next.hashCode())
	{
		count++;
		pq=pq.queue.next;
	}
	return count;
	
}
public void print() {
	// modify this method if necessary
		
		PersistentQueue<E> pq=this.queue.tail;
		int count=1;
		if(pq==null||pq.queue.prev==this.queue.prev.queue.next)
			return ;
		
		while(pq.hashCode()!=this.queue.prev.queue.next.hashCode())
		{
			System.out.println(pq.queue.value);
			pq=pq.queue.next;
		}
		System.out.println(pq.queue.value);
		
		
		
	}
public PersistentQueue<E> append(PersistentQueue<E> queue)
{
	ImmutableList im=new ImmutableList(-1,null, null, null);
	
	PersistentQueue<E> pq=new PersistentQueue<E>(im);
	PersistentQueue<E> first=this.queue.tail;
	PersistentQueue<E> second;
	 second=queue.queue.tail;
    
	while(first.hashCode()!=this.hashCode())
	{
		//System.out.println(first.queue.value);
		pq=pq.enqueue(first.queue.value);
		
		first=first.queue.next;
	}
	//System.out.println(first.queue.value);
	pq=pq.enqueue(first.queue.value);
	pq.queue.next=second;
    ImmutableList il=new ImmutableList(-1,null, null, null);
	
	PersistentQueue<E>save=new PersistentQueue<E>(il);
	save.queue.tail=pq.queue.tail;
	 
	save.queue.prev=queue.queue.prev;
    
	
	return save;
}
public PersistentQueue<E> poll()
{
	PersistentQueue<E> ptail=this.queue.tail;
	int val=(Integer) ptail.queue.value;
	System.out.println("Polled Value"+val);
	return this.dequeue() ;
}

}
class ImmutableList<T>{
    @SuppressWarnings("unchecked")
    //private final static ImmutableList NIL = new ImmutableList(-1,null, null,null);

    public T value;
    public  PersistentQueue<T> tail=null;
    public  PersistentQueue<T> prev=null;
    public PersistentQueue<T> next=null;
    public boolean last=false;


    public ImmutableList(T value, PersistentQueue<T> tail,PersistentQueue<T> prev,PersistentQueue<T> next) {
        this.value = value;
        this.tail = tail;
        this.prev=prev;
        this.next=next;
    }

    public boolean isEmpty() {
    	boolean flag=false;
    	if(tail==null  && prev==null)
             flag=true;
		return flag;
    }

   

  

   
  
  

   
    }

