package queue;
public class LinkQueue{

    public Node head;

    public Node rear;

    public int n;

    public LinkQueue(){
        head = new Node();
        rear = head;
        n = 0;
    }

    public void clear(){
        head.next = null;
        rear = head;
        n = 0;
    }

    public boolean is_empty(){
        if(n == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int length(){
        return n;
    }

    public String get_head() throws Exception{
        if(n == 0){
            throw new Exception("wrong");
        }
        return head.next.data;
    }

    public void enqueue(String a){
        Node node = new Node();
        node.data = a;
        rear.next = node;
        rear = node;
        n ++;
    }

    public String dequeue() throws Exception{
        if(n == 0){
            throw new Exception("wrong");
        }
        String a = head.next.data;
        head.next = head.next.next;
        n--;
        if(n == 0){
            rear = head;
        }
        return a;
    }
}