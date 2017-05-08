package Sword2Offer;

/**
 * Created by Jay on 2017/3/24.
 */
public class HasSubTree {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean result = false;

        if(root1!=null && root2!=null){
            if(root1.val == root2.val){
                result = doesTree1HasTree2(root1, root2);
            }
            if(!result){
                result = HasSubtree(root1.left, root2);
            }
            if(!result){
                result = HasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    private boolean doesTree1HasTree2(TreeNode p1, TreeNode p2){
        if(p2 == null){
            return true;
        }
        if(p1 == null){
            return false;
        }

        if(p1.val != p2.val){
            return false;
        }
        return doesTree1HasTree2(p1.left, p2.left) && doesTree1HasTree2(p1.right, p2.right);
    }
}
