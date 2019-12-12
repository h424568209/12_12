import org.w3c.dom.ls.LSInput;

import java.util.List;

public class LeeCode {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    /**
     * 路径总和
     * 给定一个二叉树和目标和，判断该树中是否存在根节点到叶子节点的路径
     * 这条路径上所有节点的值相加等于目标和
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null )
            return false;
        if(root.left == null && root.right == null)
            return sum-root.val ==0;
        return hasPathSum(root.left,sum-root.val) ||hasPathSum(root.right ,sum - root.val);
    }
    /**
     *  二叉树的最大深度
     * */
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return 1+Math.max(maxDepth(root.right),maxDepth(root.right));
    }

    /**
     *  二叉树的最小深度
     *  如果根节点的左子树或者右子树为空的话是构不成字数的，而最小深度是要求根节点到子树的
     *  当左或者右子树为空的时候，是不满足要求的
     *  只有左子树和右子树都是空才满足要求
     * */
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right != null)
            return 1+minDepth(root.right);
        if(root.left!=null && root.right == null)
            return 1+minDepth(root.left);
        return 1+Math.min(minDepth(root.left),minDepth(root.right));
    }

    /**
     * 有序链表转换二叉搜索树
     * 每次找到链表的中心节点做根
     * @param head 头结点
     * @return 转换成的二叉搜索树
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;
        else if(head.next == null)
            return new TreeNode(head.val);
        ListNode pre  = head;
        ListNode  p = pre.next;
        ListNode q = p .next;
        while(q!=null && q.next!=null){
            pre = pre.next;
            p = p.next ;
            q = q.next.next;
        }
        pre.next = null;
        TreeNode root = new TreeNode(p.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(p.next);
        return root;
    }

    /**
     * 将有序的数组传换成二叉搜索树
     * 实质上就是二叉树中序遍历的逆过程
     * @param nums 数组
     * @return 二叉搜索树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        //左右等分建立左右子树，中间节点作为子树根节点，递归这个过程
        return nums==null? null: buildTree(nums,0,nums.length-1);
    }

    private TreeNode buildTree(int[] nums, int l, int r) {
        if(l>r){
            return null;
        }
        int mid = l+(r-l)<<1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums,l,mid-1);
        root.right = buildTree(nums,mid+1,r);
        return root;
    }
}
