public class QueueArr{
    Integer[] queue;
    int first;
    int last;
    int size;
    int elementCounter; // number of the elements in the queue

    public QueueArr(int size){
        this.size = size;
        this.queue = new Integer[size];
        first = 0;
        last = 0;
        elementCounter = 0;
    }

    // true if no elements is in the queue
    public boolean isEmpty(){
        return (elementCounter == 0);
    }

    // expand the size of array to double
    public void expand(){
        Integer[] copy = new Integer[this.size*2];
        int c = 0;  // positionen i andra arrayn där vi ska skriva saker

        if(elementCounter == this.size){
            for(int i = first; i < size; i++){
                copy[c] = queue[i];
                c++;
            }
            for(int i = 0; i < first; i++){
                copy[c] = queue[i];
                c++;
            }
        }
        this.size = this.size*2;
        this.first = 0;
        this.last = c;
        queue = copy;
    }

    // shrink the size of array to half
    public void shrink(){

        Integer[] copy = new Integer[this.size/2];
        int c = 0;  // positionen i andra arrayn där vi ska skriva saker

            for(int i = first; i < size; i++){
                if(queue[i] == null)
                    break;
                copy[c] = queue[i];
                c++;
            }
            if(last < first){
                for(int i = 0; i < last; i++){
                    copy[c] = queue[i];
                    c++;
                }
            }
        this.size = this.size/2;
        this.first = 0;
        this.last = c;
        queue = copy;
    }

    // add an element to the end 
    public void enqueue(Integer item){
        // if queue is full
        if(elementCounter == this.size)
            expand();

        // item added to the end
        queue[last] = item;
        // moving the last pointer
        last = last + 1 % this.size;
        elementCounter++;
    }

    // remove element at the first
    public Integer dequeue(){
        // queue is empty
        if(isEmpty())
            return null;
        
        Integer ret = queue[first];
        queue[first] = null;
        //moving the first pointer to next item
        first = (first + 1) % size;
        elementCounter--;
        // if queue is quite empty
        if(elementCounter <= (this.size/4)){
            shrink();
        }
        return ret;
    }


    public void print(){
        System.out.println("The current queue is: ");
        for(int i = 0; i < this.size; i ++){
            System.out.printf("%d\n", queue[i]);
        }
    }


    public static void main(String[] args){
        QueueArr q = new QueueArr(5);
        
        System.out.println("The primary queue with 5 elements");
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        q.print();

        System.out.println("the queue is full now, trying to add new elements, queue expands to 10");
        
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.enqueue(9);
        q.enqueue(10);
        q.print();
        System.out.println("the queue is full now, trying to add new elements, queue expands to 20");
        
        q.enqueue(11);
        q.enqueue(12);
        q.enqueue(13);
        q.enqueue(14);
        q.enqueue(15);
        q.enqueue(16);
        q.print();
        System.out.println("removing 11 elements, reach a size to a quarter and queue shrinks to 10");
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        System.out.println("elements 1 to 11 has been removed removed");
        q.print();

        System.out.println("17 and 18 enqueued");
        q.enqueue(17);
        q.enqueue(18);
        q.print();
        System.out.println("dequeue 12,13,14");
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.print();
    }
}

// interface queue med enqueue och dequeue för benchmarking