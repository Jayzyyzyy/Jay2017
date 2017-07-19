package Sword2Offer;

import java.util.Stack;

public class P21_StackWithMin {
    Stack<Integer> data = new Stack<Integer>();
    Stack<Integer> min = new Stack<Integer>();

    public void push(int node) {
        data.push(node);
        if(!min.isEmpty()){
            int temp = min.peek();
            if(temp > node){
                min.push(node);
            }else {
                min.push(temp);
            }
        }else {
            min.push(node);
        }
    }

    public void pop() {
        if(!data.isEmpty()){
            data.pop();
            min.pop();
        }
    }

    public int top() {
        if(!data.isEmpty()){
            return data.peek();
        }
        throw new RuntimeException("no elements");
    }

    public int min() {
        if(!min.isEmpty()){
            return min.peek();
        }
        throw new RuntimeException("no elements");
    }
}
