package Nowcoder.day726;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Stack;

public class ReverseStackRecursively {
    public static int[] reverseStackRecursively(int[] stack, int top) {
        if(stack == null || top == 0) return stack;
        int num = last(stack, top);
        int[] result = reverseStackRecursively(stack, --top);
        result[top] = num;
        return result;
    }

    public static int last(int[] stack, int top){
        int num = stack[--top];
        if(top == 0){
            return num;
        }else{
            int temp = last(stack, top);
            stack[--top] = num;
            return temp;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if(stack == null || stack.isEmpty()) return ;
        int num = getLastElement(stack);
        reverse(stack);
        stack.push(num);
    }

    public static int getLastElement(Stack<Integer> stack){
        int num = stack.pop();
        if(stack.isEmpty()){
            return num;
        }else {
            int temp = getLastElement(stack);
            stack.push(num);
            return temp;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        for (int i : stack) {
            System.out.print(i + " ");
        }
        System.out.println();
        ReverseStackRecursively.reverse(stack);
        for (int i : stack) {
            System.out.print(i + " ");
        }

        System.out.println("----------");
        int[] a = new int[]{1,2,3,4,5};
        a = ReverseStackRecursively.reverseStackRecursively(a,a.length);
        for (int i : a) {
            System.out.print(i + " ");
        }

    }
}
