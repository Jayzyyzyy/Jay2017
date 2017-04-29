package com.algo.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 单链表是否有环
 */
public class ListHasCircle {

    /**
     * 判断单链表是否有环，有环返回进入环的第一个节点的位置，否则返回null。
     * @param node
     * @return 时间复杂度为O(N)，空间复杂度为O(N)
     */
    public Node hasLoop(Node node){
        if(node == null || node.next == null) return null;

        Map<Node, Integer> map = new HashMap<>();

        while(node != null){
            if(map.containsKey(node)){
                return node;
            }
            map.put(node, 1);
            node= node.next;
        }
        return null;
    }

    /**
     * 快慢指针方法
     * @param head
     * @return
     */
    public Node hasLoop2(Node head){
        if(head == null || head.next == null) return null; //只有一个或者没有

        Node fast = head;
        Node slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;//走两步
            slow = slow.next; //走一步
            if(fast == slow) break;
        }
        //无环
        if(fast == null || fast.next == null){
            return null;
        }
        //有环
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    private static class Node{
        int value;
        Node next;
        Node(int value){
            this.value = value; //位置
        }
    }
}

