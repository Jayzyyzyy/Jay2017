package Nowcoder.day726;

import java.util.Scanner;
import java.util.Stack;

/**
 * 保卫方案
 */
public class Problem_04_MountainsAndFlames {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int size = in.nextInt();
			int[] arr = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = in.nextInt();
			}
			System.out.println(communications(arr));
		}
	}
	//index的下一个索引(循环)
	public static int nextIndex(int size, int i) {
		return i < (size - 1) ? (i + 1) : 0;
	}
	//求C n 2
	public static long getInternalSum(int n) {
		return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
	}

	public static class Pair {
		public int value;
		public int times;

		public Pair(int value) {
			this.value = value;
			this.times = 1;
		}
	}

	public static long communications(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int size = arr.length;
		int maxIndex = 0; //最大值位置
		for (int i = 0; i < size; i++) {
			maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
		}
		int value = arr[maxIndex];
		int index = nextIndex(size, maxIndex);
		long res = 0L;
		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(value));
		while (index != maxIndex) { //环形链表
			value = arr[index];
			while (!stack.isEmpty() && stack.peek().value < value) {
				int times = stack.pop().times;
//				res += getInternalSum(times) + times;
//				res += stack.isEmpty() ? 0 : times;
				res += getInternalSum(times) + times*2; //结算，栈顶有最大值
			}
			if (!stack.isEmpty() && stack.peek().value == value) { //合并
				stack.peek().times++;
			} else {
				stack.push(new Pair(value)); //新建记录
			}
			index = nextIndex(size, index); //求取循环链表下一个位置
		}
		//遍历完毕，栈中还有元素
		while (!stack.isEmpty()) {
			int times = stack.pop().times;
			res += getInternalSum(times); //内部对数
			if (!stack.isEmpty()) { //栈中还有至少一个pair
				res += times;
				if (stack.size() > 1) { //栈中还有至少2个pair
					res += times;
				} else {//当前弹出的是倒数第二个元素
					res += stack.peek().times > 1 ? times : 0;
				}
			}
		}
		return res;
	}
}