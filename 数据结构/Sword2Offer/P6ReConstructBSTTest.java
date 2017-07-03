package Sword2Offer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *  二叉树重建测试
 */
public class P6ReConstructBSTTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void construct() throws Exception {
        int[] preOrder = {1,2,4,7,3,5,6,8};
        int[] inOrder = {4,7,2,1,5,3,8,6};
        P6_ReConstructBST.Node n = P6_ReConstructBST.construct(preOrder,inOrder);
        P6_ReConstructBST.traveralByLevel(n);
    }

}