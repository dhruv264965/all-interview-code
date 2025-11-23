package LinkedList;

import org.w3c.dom.Node;

import java.util.LinkedList;

public class Implementation {
    public static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
        }

    }
    public static class LinkedList{
        Node head=null;
        Node tail=null;
        void insertAtEnd(int val){
            Node temp=new Node(val);
            if(head==null){ //Even LL is empty
                head=temp;
                tail=temp;
            }
            else {
                tail.next=temp;
                tail=temp;
            }
        }
        void insertAtBegonning(int val){
            Node temp=new Node(val);
            if(head==null){
                head=temp;
                tail=temp;
            }
            else{
                temp.next=head;
                head=temp;
            }
        }
        void display(){
            Node temp=head;
            while(temp!=null){
                System.out.print(temp.data+" ");
                temp=temp.next;
            }
        }
        int length(){
            int count =0;
            Node temp=head;
            while(temp!=head){
                count++;
                temp=temp.next;
            }
            return count;
        }
    }
    public static void main(String[] args) {
           LinkedList ll =new LinkedList();
        System.out.println("for End");
           ll.insertAtEnd(2);
           ll.display();
         System.out.println();
           ll.insertAtEnd(3);
           ll.insertAtEnd(5);
           ll.display();
        System.out.println();
        System.out.println(ll.length());
        System.out.println(" for begining");
        ll.insertAtBegonning(88);
       // ll.display();
        ll.insertAtBegonning(47);
        ll.display();
    }


}
