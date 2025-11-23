package LinkedList;

public class BasicLL {
    public  static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
        }

    }
    public  static void display(Node head){
        while (head!=null){
            System.out.print(head.data+" ");
            head=head.next;
        }
    }
    public  static void displayrecursive (Node head){
        if(head==null) return;
        System.out.print(head.data+ " ");
        displayreverse(head.next);
    }
    public  static void displayreverse(Node head){
        if(head==null) return;
        displayreverse(head.next);
        System.out.print(head.data+ " ");
    }
    public static void length(Node head){
        int count =0;
        while(head!=null){
           count++;
           head=head.next;

        }
        System.out.println(count);
    }
    public static Node insertAtEnd(Node head,int val){
        Node temp = new Node(val);
        {
            Node t = head; // Traverse to the end of the list
            while (t.next != null) {  // tail is not given so hame tail finding karne ke liye jo loop ham lga rahe hai last tak aane ke liye
                                        //uske ek pahele tak ruk jayega
                // if(t!=null) likhenge then null pahuchne ke baad break hoga hame uske pahele he rokna hai
                t = t.next;
            }
            t.next = temp; // Link the new node at the end
        }

        return head; // Return the updated head
    }


    public static void main(String[] args) {
        Node a=new Node(2);
        Node b=new Node(5);
        Node c=new Node(6);
        Node d=new Node(7);
        System.out.println(a.data); //2
        a.next=b;
        System.out.println(b.data);///5
        System.out.println(a.next.data);//5
        b.next=c;
        c.next=d;
        System.out.println(c.next.data); //7
        //System.out.println();
      //  Node temp=a;
//        for(int i=1;i<=4;i++){
//            System.out.print(temp.data+" ");
//            temp=temp.next;
//        }
        System.out.println(" Display the list");
//        while (temp!=null){
//            System.out.print(temp.data+" ");
//            temp=temp.next;
//        }
        display(a);
        System.out.println();
        System.out.println("recursive display ");
        displayrecursive(a);
        System.out.println();
        System.out.println("reverse display");
        displayreverse(a);
        System.out.println();
        System.out.println("Length of linked list");
        length(a);
        System.out.println();
        insertAtEnd(a,10);
        display(a);


    }
}
