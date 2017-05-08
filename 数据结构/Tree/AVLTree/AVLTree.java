package Tree.AVLTree;

/**
 *  AVL树
 */
public class AVLTree<T extends Comparable<? super T>> {
    //AVLNode
    static class AVLNode <T>{
        T key;  //值
        int height; //高度
        AVLNode<T> left; //左子树
        AVLNode<T> right;//右子树

        public AVLNode(T key, AVLNode<T> left, AVLNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1;
        }
    }

    private AVLNode<T> root; //根节点

    public AVLTree(){
        root = null;
    }

    /**
     * 某个节点的高度
     * @param node
     * @return
     */
    public int height(AVLNode<T> node){
        return node==null ? 0 : node.height;
    }

    /**
     * 根节点高度
     * @return
     */
    public int height(){
        return height(root);
    }

    /**
     * 求最大值
     * @param a
     * @param b
     * @return
     */
    public int max(int a, int b){
        return a < b ? b : a;
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(AVLNode<T> node){
        if(node != null){
            System.out.println(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 根节点前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }


    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(AVLNode<T> node){
        if(node != null){
            inOrder(node.left);
            System.out.println(node.key + " ");
            inOrder(node.right);
        }
    }

    /**
     * 根节点中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 后序遍历
     * @param node
     */
    public void postOrder(AVLNode<T> node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key + " ");
        }
    }

    /**
     * 根节点后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    /**
     * 查找(递归实现)
     * @param node 要查找的树
     * @param key 键值
     * @return 返回包含该值的AVLNode
     */
    public AVLNode<T> find(AVLNode<T> node, T key){
        if(node == null) return null;

        int cmp = key.compareTo(node.key);

        if(cmp < 0){
            return find(node.left, key);
        }else if(cmp > 0){
            return find(node.right, key);
        }else {
            return node;
        }
    }

    /**
     * 查找，非递归实现
     * @param node 要查找的树
     * @param key 键
     * @return 找到的节点
     */
    public AVLNode<T> loopFind(AVLNode<T> node, T key){
        AVLNode<T> current = node;

        while(current != null){
            int cmp = key.compareTo(current.key);

            if(cmp < 0){
                current = current.left;
            }else if(cmp > 0){
                current = current.right;
            }else {
                return current;
            }
        }
        return null;
    }

    /**
     * 最小值
     * @return min
     */
    public T minimum(){
        if(root == null) return null;

        return minimum(root).key;
    }

    private AVLNode<T> minimum(AVLNode<T> node){
        AVLNode<T> current = node;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    /**
     * 最大值
     * @return max
     */
    public T maximum(){
        if(root == null) return null;

        return maximum(root).key;
    }
    private AVLNode<T> maximum(AVLNode<T> node){
        AVLNode<T> current = node;
        while(current.right != null){
            current = current.right;
        }
        return current;
    }

    /**
     * 左左旋转LL
     * @param node 被破坏者节点，需要调整
     * @return 返回调整后的根节点
     */
    private AVLNode<T> LLRotatin(AVLNode<T> node){
        AVLNode<T> finalRoot = node.left; //左子为父
        node.left = finalRoot.right; //右孙变左孙
        finalRoot.right = node;  //父为右子

        //调整height
        node.height = max(height(node.left), height(node.right)) + 1;
        finalRoot.height = max(height(finalRoot.left), node.height) + 1;

        return finalRoot;
    }

    /**
     * 右右旋转RR
     * @param node 被破坏者节点，需要调整
     * @return 返回调整后的根节点
     */
    private AVLNode<T> RRRotatin(AVLNode<T> node){
        AVLNode<T> finalRoot = node.right; //右子为父
        node.right = finalRoot.left; //左孙变右孙
        finalRoot.left = node;  //父为左子

        //调整height
        node.height = max(height(node.left), height(node.right)) + 1;
        finalRoot.height = max(height(finalRoot.right), node.height) + 1;

        return finalRoot;
    }

    /**
     * LR旋转，先RR旋转，再LL旋转
     * @param node 被破坏者节点，需要调整
     * @return 返回调整后的根节点
     */
    private AVLNode<T> LRRotation(AVLNode<T> node){
        node.left = RRRotatin(node.left); //先右旋RR

        return LLRotatin(node); //再左旋LL
    }

    /**
     * RL旋转，先LL旋转，再RR旋转
     * @param node 被破坏者节点，需要调整
     * @return 返回调整后的根节点
     */
    private AVLNode<T> RLRotation(AVLNode<T> node){
        node.right = LLRotatin(node.right); //先左旋LL

        return RRRotatin(node); //再右旋RR
    }

    /**
     * AVL树插入key
     * @param key
     */
    public void insert(T key){
        root = insert(root, key);
    }
    /**
     * 节点插入AVL树，返回根节点
     * @param tree 根节点
     * @param key 键
     * @return 根节点
     */
    private AVLNode<T> insert(AVLNode<T> tree, T key){
        if(tree == null){
            //新建节点，递归返回条件
            tree = new AVLNode<T>(key, null, null);
        }else {
            int cmp = key.compareTo(tree.key);

            if(cmp < 0){ //插入左子树
                tree.left = insert(tree.left, key);
                //插入节点后，如AVL树失去平衡，则进行相应的调节
                if((height(tree.left)- height(tree.right)) == 2){
                    if(key.compareTo(tree.left.key) < 0){
                        tree = LLRotatin(tree); //LL旋转
                    }else {
                        tree = LRRotation(tree); //LR旋转
                    }
                }
            }else if(cmp > 0){ //插入右子树
                tree.right = insert(tree.right, key);
                if((height(tree.right)-height(tree.left)) == 2){
                   if(key.compareTo(tree.right.key) < 0){
                       tree = RLRotation(tree);
                   }else {
                       tree = RRRotatin(tree);
                   }
                }
            }else {
                System.out.println("键key已存在!!!");
            }
        }

        tree.height = max(height(tree.left), height(tree.right)) + 1; //更新树高
        return tree;
    }

    //删除Key节点
    public void delete(T key){
        AVLNode<T> toBeDelete = null;
        if((toBeDelete=find(root, key)) != null){
            root = delete(root, toBeDelete);
        }
    }

    /**
     * 删除节点，返回根节点
     * @param tree 根节点
     * @param toBeDelete 要删除的节点
     * @return 根节点
     */
    private AVLNode<T> delete(AVLNode<T> tree, AVLNode<T> toBeDelete){
        //根结点为空或者要删除的节点为空
        if(tree == null || toBeDelete == null) return null;

        int cmp = toBeDelete.key.compareTo(tree.key);
        if(cmp < 0){ //待删除节点在左子树中
            tree.left = delete(tree.left, toBeDelete);
            //删除节点后，AVL树可能失去平衡
            if((height(tree.right) - height(tree.left)) == 2){
                AVLNode<T> right = tree.right;
                if(height(right.left) > height(right.right)){
                    tree = RLRotation(tree);
                }else {
                    tree = RRRotatin(tree);
                }
            }
        }else if(cmp > 0){ //待删除节点在右子树中
            tree.right = delete(tree.right, toBeDelete);
            //AVL树在删除之后不平衡,调整
            if(height(tree.left) - height(tree.right) == 2){
                AVLNode<T> left = tree.left;
                if(height(left.left) > height(left.right)){
                    tree = LLRotatin(tree);
                }else {
                    tree = LRRotation(tree);
                }
            }
        }else {  //找到要删除的节点tree
            //tree左右孩子均非空
            if(tree.left != null & tree.right != null){
                /*
                 * 如果tree左子树比右子树高，则第一步先找出左子树中最大节点
                 * 第二步 将该节点的值赋给tree
                 * 第三步 删除该最大节点
                 * 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                   采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                 */
                if(height(tree.left) > height(tree.right)){
                    AVLNode<T> leftMax = maximum(tree.right);
                    tree.key = leftMax.key;
                    tree.left = delete(tree.left, leftMax);
                }else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    AVLNode<T> rightMin = minimum(tree.right);
                    tree.key = rightMin.key;
                    tree.right = delete(tree.right, rightMin);
                }
            }else {
                tree = tree.left!=null? tree.left : tree.right;
            }
        }
        //可能为null
        if(tree != null) tree.height = max(height(tree.left), height(tree.right)) + 1; //更新树高
        return tree;
    }

}
