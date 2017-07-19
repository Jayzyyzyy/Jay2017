package Sword2Offer;

public class P22_IsPopOrder {
    public boolean isPopOrder(int [] pushA,int [] popA) {
        if(pushA.length == 1) return pushA[0] == popA[0];

        for (int i = 0; i < popA.length -2; i++) {
            if(popA[i] > popA[i+2] && popA[i+2] > popA[i+1]){
                return false;
            }
        }
        return true;
    }
}
