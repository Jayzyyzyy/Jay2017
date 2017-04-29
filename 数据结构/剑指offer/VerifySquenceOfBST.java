package 剑指offer;

/**
 * Created by Jay on 2017/3/24.
 */
public class VerifySquenceOfBST {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0) return false;

        return  Verify(sequence, 0, sequence.length-1);
    }

    public boolean Verify(int[] sequence, int start, int end){
        int root = sequence[end];
        int i = start;

        for(; i < end ;i++){
            if(sequence[i]>root) break;
        }

        int j = i;
        for(;j<end;j++){
            if(sequence[j]<root) return false;
        }

        boolean left = true;
        if(i > start){
            left = Verify(sequence, start, i-1);
        }

        boolean right = true;
        if(i < end-1 ){
            right = Verify(sequence, i, end-1);
        }
        return (left&&right);
    }
}
