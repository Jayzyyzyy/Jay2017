package NowcoderZuo.day724;

import java.util.HashMap;
import java.util.HashSet;

// 能否把一个正数数组分成4部分问题，每部分的累加和相等，分割点的值不算
public class Split4Parts {

	public static boolean canSplits1(int[] arr) {
		if (arr == null || arr.length < 7) {
			return false;
		}
		HashSet<String> set = new HashSet<String>(); //黑盒
		int allSum = 0; //所有数的和
		for (int i = 0; i < arr.length; i++) {
			allSum += arr[i];
		}
		int leftSum = arr[0];
		for (int i = 1; i < arr.length - 1; i++) {
			set.add(String.valueOf(leftSum) + "_" + String.valueOf(allSum - leftSum - arr[i])); //左和_右和
			leftSum += arr[i];
		}
		int l = 1; //分1
		int lsum = arr[0]; //左边部分的累加和
		int r = arr.length - 2; //分2
		int rsum = arr[arr.length - 1];//右边部分的累加和
		while (l < r - 3) {
			if (lsum == rsum) {
				String lkey = String.valueOf(lsum * 2 + arr[l]);
				String rkey = String.valueOf(rsum * 2 + arr[r]);
				if (set.contains(lkey + "_" + rkey)) {
					return true;
				}
				lsum += arr[l++];  //lsum += arr[r--];
			} else if (lsum < rsum) {
				lsum += arr[l++];
			} else {
				rsum += arr[r--];
			}
		}
		return false;
	}

	public static boolean canSplits2(int[] arr) {
		if (arr == null || arr.length < 7) {
			return false;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int sum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			map.put(sum, i);
			sum += arr[i];
		}
		int lsum = arr[0];
		for (int s1 = 1; s1 < arr.length - 5; s1++) {
			int checkSum = lsum * 2 + arr[s1];
			if (map.containsKey(checkSum)) {
				int s2 = map.get(checkSum);
				checkSum += lsum + arr[s2];
				if (map.containsKey(checkSum)) {
					int s3 = map.get(checkSum);
					if (checkSum + arr[s3] + lsum == sum) {
						return true;
					}
				}
			}
			lsum += arr[s1];
		}
		return false;
	}

	public static int[] generateRondomArray() {
		int[] res = new int[(int) (Math.random() * 10) + 7];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) (Math.random() * 10) + 1;
		}
		return res;
	}

	public static void main(String[] args) {
		int testTime = 3000000;
		boolean hasErr = false;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRondomArray();
			if (canSplits1(arr) ^ canSplits2(arr)) {
				hasErr = true;
				break;
			}
		}
		if (hasErr) {
			System.out.println("233333");
		} else {
			System.out.println("666666");
		}

	}
}
