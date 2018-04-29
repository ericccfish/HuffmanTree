import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by chenjianfeng on 2018/1/25.
 */

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val){
        this.val = val;
    }
}

public class HuffmanTree {

    public TreeNode[] getTwoMinimun(LinkedList<TreeNode> forest){
        if(forest.size()==1)
            return new TreeNode[0];
        TreeNode[] res = new TreeNode[2];
        TreeNode m1 = forest.get(0);
        TreeNode m2 = forest.get(1);
        for(int i=2; i<forest.size(); i++){
            if(m1.val<m2.val){
                if(forest.get(i).val<m2.val)
                    m2 = forest.get(i);
            }
            else{
                if(forest.get(i).val<m1.val)
                    m1 = forest.get(i);
            }
        }
        res[0] = m1;
        res[1] = m2;
        forest.remove(m1);
        forest.remove(m2);
        return res;
    }

    public TreeNode creatHuffmanTree(LinkedList<TreeNode> forest){
        if(forest.size()==0)
            return null;
        if(forest.size()==1)
            return forest.remove(0);
        TreeNode[] twoNode = getTwoMinimun(forest);
        System.out.println(twoNode[0].val + " ::: " + twoNode[1].val);
        TreeNode root = new TreeNode(twoNode[0].val+twoNode[1].val);
        root.left = twoNode[0];
        root.right = twoNode[1];
        forest.add(root);
        return creatHuffmanTree(forest);
    }

    public void preOrderTraverse(TreeNode root){
        if(root!=null){
            System.out.println(root.val);
            preOrderTraverse(root.left);
            preOrderTraverse(root.right);
        }
    }

    public void printCode(TreeNode root, ArrayList<Integer> code){
        if(root==null)
            return;
        if(root.left==null && root.right==null){
            System.out.print(root.val + " >>> ");
            for(int i=0; i<code.size(); i++)
                System.out.print(code.get(i) + "");
            System.out.println();
            return;
        }
        code.add(1);
        printCode(root.left, code);
        code.remove(code.size() - 1);
        code.add(0);
        printCode(root.right, code);
        code.remove(code.size() - 1);
    }

    public static void main(String[] args) throws Exception{
        int[] arr = {3,6,8,12,2,7,5,17};
        LinkedList<TreeNode> forest = new LinkedList<TreeNode>();
        for(int i=0; i<arr.length; i++)
            forest.add(new TreeNode(arr[i]));
        HuffmanTree hft = new HuffmanTree();
        TreeNode root = hft.creatHuffmanTree(forest);
        hft.preOrderTraverse(root);
        hft.printCode(root, new ArrayList<Integer>());
    }

}
