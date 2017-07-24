package NowcoderZuo.day720;
import java.util.Scanner;
import java.util.Stack;

public class MountainsAndFlames1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] arr = new int[size];
            for(int i = 0; i< size; i++){
                arr[i] = in.nextInt();
            }
            
            System.out.println(communications(arr));
            
        }
    }
    

    public static int nextIndex(int size, int i) {
		return i < (size - 1) ? (i + 1) : 0;
	}

	public static int getInternalSum(int n) {
		return n == 1 ? 0 : n * (n - 1) / 2;
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
		int maxIndex = 0;
		for (int i = 0; i < size; i++) {
			maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
		}
		int value = arr[maxIndex];
		int index = nextIndex(size, maxIndex);
		long res = 0L;
		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(value));
		while (index != maxIndex) {
			value = arr[index];
			while (!stack.isEmpty() && stack.peek().value < value) {
				int times = stack.pop().times;
				res += getInternalSum(times) + times;
				res += stack.isEmpty() ? 0 : times;
			}
			if (!stack.isEmpty() && stack.peek().value == value) {
				stack.peek().times++;
			} else {
				stack.push(new Pair(value));
			}
			index = nextIndex(size, index);
		}
		while(!stack.isEmpty()) {
			int times = stack.pop().times;
			res += getInternalSum(times);
			if(!stack.isEmpty()) {
				res += times;
				res += stack.lastElement().times > 1 ? times : 0;
			}
		}
		return res;
	}
}