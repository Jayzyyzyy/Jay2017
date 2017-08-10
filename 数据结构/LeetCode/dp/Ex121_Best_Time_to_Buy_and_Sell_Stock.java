package LeetCode.dp;

public class Ex121_Best_Time_to_Buy_and_Sell_Stock {
    public int maxProfit(int[] prices) {
        /*int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if(prices[i] < prices[j]){
                    int s = prices[j]-prices[i];
                    max = Math.max(s, max);
                }
            }
        }
        return max;*/
        //[7, 1, 5, 3, 6, 4]
        if(prices == null || prices.length <= 1) return 0;

        int maxProfit = 0; //目前的最大收益
        int soFarMin = prices[0]; //目前的最小数
        for(int i= 1; i< prices.length ;i++){
            if(prices[i] > soFarMin){
                maxProfit = Math.max(maxProfit, prices[i] - soFarMin);
            }else{
                soFarMin = prices[i];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(new Ex121_Best_Time_to_Buy_and_Sell_Stock().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new Ex121_Best_Time_to_Buy_and_Sell_Stock().maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}
