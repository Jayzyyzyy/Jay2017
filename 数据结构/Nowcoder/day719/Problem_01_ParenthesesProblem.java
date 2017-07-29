package Nowcoder.day719;

/**
 1、已知一个字符串都是由左括号(和右括号)组成，判断该字符串是否是有效的括号组合。

 例子：
 有效的括号组合:()(),(()),(()())  //以左括号开头
 无效的括号组合:(,()),((),()(()


 2、题目进阶：
 已知一个字符串都是由左括号(和右括号)组成，返回最长有效括号子串的长度。

 */
public class Problem_01_ParenthesesProblem {
	/**
	 * 一个变量state，遇到左括号++，遇到右括号--，遍历中途state小于0，返回false;否则遍历结束，
	 * 如果state为0，返回true，不然返回false
	 * @param str
	 * @return
	 */
	public static boolean isValid(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		char[] chas = str.toCharArray();
		int status = 0;
		for (int i = 0; i < chas.length; i++) {
			if (chas[i] != ')' && chas[i] != '(') { //这一步可以不加
				return false;
			}
			if (chas[i] == ')' && --status < 0) {
				return false;
			}
			if (chas[i] == '(') {
				status++;
			}
		}
		return status == 0;
	}

	//动态规划，以某个字符结尾的情况下，得到最长有效括号子串的长度
	public static int maxLength(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chas = str.toCharArray();
		int[] dp = new int[chas.length]; //状态数组
		int pre = 0;
		int res = 0;
		for (int i = 1; i < chas.length; i++) {
			if (chas[i] == ')') {
				pre = i - dp[i - 1] - 1;
				if (pre >= 0 && chas[pre] == '(') {
					dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}

	public static void main(String[] args) {
		String str1 = "((())())";
		System.out.println(isValid(str1));
		System.out.println(maxLength(str1));

		String str2 = "(())(()(()))";
		System.out.println(isValid(str2));
		System.out.println(maxLength(str2));

		String str3 = "()(()()(";
		System.out.println(isValid(str3));
		System.out.println(maxLength(str3));

	}
}
