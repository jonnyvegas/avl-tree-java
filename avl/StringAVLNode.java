/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author Jonny
 */
public class StringAVLNode 
{
    private String val;
    private int balance;
    private StringAVLNode left, right;
    /**
     * Constructor for StringAVLNode
     * @param str the String that the node should have.
     */
    public StringAVLNode(String str)
    {
        this.val = str;
        this.left = null;
        this.right = null;
        this.balance = 0;
    }
    
    /**
     * Gets the balance of the node.
     * @return the balance of the node.
     */
    public int getBalance()
    {
        return balance;
    }
    
    /**
     * Sets the balance of the node.
     * @param bal the balance to set the node to.
     */
    public void setBalance(int bal)
    {
        this.balance = bal;
    }
    
    /**
     * Gets the String item from the node.
     * @return the String value of the item in the node.
     */
    public String getItem()
    {
        return val;
    }
    
    /**
     * Gets the left child of the node.
     * @return the left child of the node.
     */
    public StringAVLNode getLeft()
    {
        return left;
    }
    
    /**
     * Sets the left child of the node.
     * @param pt the node to be set to the left child.
     */
    public void setLeft(StringAVLNode pt)
    {
        this.left = pt;
    }
    
    /**
     * Gets the right child of the node.
     * @return the right child of the node.
     */
    public StringAVLNode getRight()
    {
        return right;
    }
    
    /**
     * Sets the right child of the node.
     * @param pt the right child for the node.
     */
    public void setRight(StringAVLNode pt) 
    {
        this.right = pt;
    }
    int getDepth() 
    {
            return  getDepth(this);
    }

    private static int getDepth(StringAVLNode nodeIn)
    {
            int depth;
            if (nodeIn==null) depth = 0;
            else depth = 1+ Math.max(getDepth(nodeIn.getLeft()), getDepth(nodeIn.getRight()));
            return depth;
    }

    // this will give the first three characters of a String, 
     //precedded by spaces if string is shorter than 3
    String getFirstThreeChar()
    {
            String rtn = "";
            if (val.length()>=3) rtn = val.substring(0,3);
            else if (val.length()==2) rtn = " " + val;
            else if (val.length()==1) rtn = "  " + val;
            else if (val.length()==0) rtn = "   ";
            return rtn;
    }
}
