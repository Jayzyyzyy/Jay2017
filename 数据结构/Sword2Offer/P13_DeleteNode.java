package Sword2Offer;

/**
 *  在O(1)时间内删除链表节点
 */
public class P13_DeleteNode {
    public void deleteNode(Node head, Node toBeDelete){
        if(head == null || toBeDelete == null){
            return;
        }
        if(head == toBeDelete){ //只有一个节点
            head = null;
            toBeDelete = null;
        }else if(toBeDelete.next == null){ //删除的是尾节点，遍历O(n)
            Node current = head;
            while(current.next != toBeDelete){
                current = current.next;
            }
            current.next = null;
            toBeDelete = null;
        }else {   //O(1)时间的删除操作
            Node next = toBeDelete.next; //待删除节点的下一个节点
            toBeDelete.value = next.value; //复制next节点的value到toBeDelete的节点中
            toBeDelete.next = next.next; //删除next节点
        }
    }

    static class Node{
        int value;
        Node next;
    }
}
