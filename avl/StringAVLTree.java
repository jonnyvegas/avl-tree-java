package avl;

/**
 * Name: Jonathan Villegas
 * Class: COMP282 T/TH 2-3:15
 * Assignment: Programming Assignment #2
 * Date: March 12, 2015
 * Description: This program will use the concept of an AVL tree to build
 * a binary search tree. The height of the left side and the right side of any
 * node cannot differ by more than 2 or -2. If, after
 * insertion or deletion the tree or node's heights differ
 * by more than 2, a rotation is required to make it balanced. 
 * It may be a single or double. All data is stored in the AVL tree,
 * a special kind of binary tree. This file contains the necessary methods
 * to insert and delete nodes using the proper rotations.
 */
public class StringAVLTree 
{
    //The root of the tree.
    private StringAVLNode root;
    
    /**
     * Constructor. Only one constructor in this case.
     */
    public StringAVLTree()
    {
        root = null;
    }
    
    /**
     * Gets the root of the tree.
     * @return root - the root of the tree.
     */
    public StringAVLNode getRoot()    
    {
        return root;
    }
    
    /**
     * Rotates a set of nodes to the right.
     * @param t the node to be rotated around.
     * @return The node that is the parent after the rotation
     * takes place.
     */
    private static StringAVLNode rotateRight(StringAVLNode t)
    {
        //There is no left child. Can't rotate.
        if(t.getLeft() == null)
        {
            System.out.println("Cannot rotate.");
        }
        //t.getLeft() != null. Perform the right rotation.
        else
        {
            StringAVLNode leftChild = t.getLeft();
            t.setLeft(leftChild.getRight());
            leftChild.setRight(t);
            t = leftChild; 
        }
        return t;
    }
    
    /**
     * Rotates a set of nodes to the left.
     * @param t - the node to be rotated around.
     * @return t - the node that is the new parent after the rotation.
     */
    private static StringAVLNode rotateLeft(StringAVLNode t)
    {
        //There is no right child. Cannot perform rotation.
        if(t.getRight() == null)
        {
            System.out.println("Cannot rotate.");
        }
        //t.getRight() != null. Perform the left rotation.
        else
        {
            StringAVLNode rightChild = t.getRight();
            t.setRight(rightChild.getLeft());
            rightChild.setLeft(t);
            t = rightChild;
        } 
        return t;
    }
    
    /**
     * Gets the height of the tree. Recursive driver.
     * @return theHeight - the height of the tree as an integer.
     */
    public int height()
    {
        return height(root);
    }
    
    /**
     * Gets the height of the tree. Recursive method.
     * @param t the node to check.
     * @return the height of the AVL tree.
     */
    private static int height(StringAVLNode t)
    {
        int theHeight;
        //The node is null. There is no height. Base case.
        if(t == null)
        {
            theHeight = 0;
        }
        //The node is not null. Recursively get the height of the
        //left side and the right side of the tree. Add one to account
        //for the root node.
        else
        {
            theHeight = 1 + Math.max(height(t.getLeft()), height(t.getRight()));
        }
        return theHeight;
    }
    
    /**
     * Returns the number of leaves in the AVL tree. A leaf
     * is defined as a node with no children.
     * Recursive driver.
     * @return A count of the number of leaves in the AVL tree.
     */
    public int leafCt()
    {
        return leafCt(root);
    }
    
    /**
     * Returns the number of leaves in the AVL tree.
     * A leaf is defined as a node with no children.
     * Recursive method.
     * @param t the node to check.
     * @return 
     */
    private static int leafCt(StringAVLNode t)
    {
        //No leaves yet.
        int numOfLeaves = 0;
        if(t == null)
        {
            //Do nothing. t is null. No leaves.
        }
        //If the left child and right child are null, we are at
        //a leaf.
        else if(t.getLeft() == null && t.getRight() == null)
        {
            numOfLeaves++;
        }
        //If not, recursively count the leaves on the left and right side.
        else
        {
            numOfLeaves = leafCt(t.getLeft()) + leafCt(t.getRight());
        }
        return numOfLeaves;
    }
    
    /**
     * Recursive driver. This gets the number of balanced nodes in the 
     * AVL tree. The balanced nodes have a balance of 0, which means
     * the height on the left of the node and the height on the right
     * of the node are equal.
     * @return The number of perfectly balanced nodes in the AVL tree.
     */
    public int balanced()
    {
        return balanced(root);
    }
    
    /**
     * Recursive method. This method returns the number of 
     * balanced nodes in the AVL tree. Balanced nodes have a balance of 0.
     * @param t - the node to check.
     * @return the number of balanced nodes in the AVL tree.
     */
    private static int balanced(StringAVLNode t)
    {
        int numOfBals = 0;
        if(t == null)
        {
            //Do nothing. There are no nodes.
        }
        //There are nodes to look at.
        else
        {
            //Check the current node. If the balance is 0, it is 
            //a balanced node.
            if(t.getBalance() == 0)
            {
                numOfBals = numOfBals + 1;
            }
            //If the left child isn't null, traverse the left side of the node
            //to see how many are balanced.
            if(t.getLeft() != null)
            {
                numOfBals = balanced(t.getLeft()) + numOfBals;
            }
            //If the right child isn't null, traverse the right side 
            //of the node to see how many are balanced.
            if(t.getRight() != null)
            {
                numOfBals = balanced(t.getRight()) + numOfBals;
            } 
        }
        return numOfBals;
    }
    
    /**
     * Recursive driver. Gets the successor of the node and returns
     * its value, which is a string. If the value doesn't exist,
     * or there is no successor, it returns null.
     * @param str The string to get the successor of.
     * @return the String, str's successor or null
     * if there is none or the value isn't in the tree.
     */
    public String successor(String str)
    {
        String theSucc = successor(find(root, str), str, root);
        return theSucc;
    }
    
    /**
     * Helper function. Finds the location of the node in the tree
     * to assist with successor. Returns the node with the value str
     * or null if it isn't in the tree.
     * @param t - the node to check.
     * @param str - the string to look for.
     * @return the node with the value of str or null if it doesn't exist.
     */
    private static StringAVLNode find(StringAVLNode t, String str)
    {
        StringAVLNode theNode;
        //If the tree is null, the value can't exist.
        if(t == null)
        {
            theNode = null;
        }
        //The tree isnt null.
        else
        {
            //If the node, t, has the same value as str, we are at the correct
            //node.
            if(t.getItem().compareToIgnoreCase(str) == 0)
            {
                theNode = t;
            }
            //We are not at the correct node.
            else
            {
                //Look on the left side for the value.
                StringAVLNode theLeft = find(t.getLeft(), str);
                //Loook on the right side for the value.
                StringAVLNode theRight = find(t.getRight(), str);
                //If theLeft node isn't null, we have the correct value.
                if(theLeft != null)
                {
                    theNode = theLeft;
                }
                //The right node isn't null. We have the correct value.
                else
                {
                    theNode = theRight;
                }
            }
        }
        return theNode;
    }
    
    /**
     * Recursive method. Gets the successor of a String, str, in an AVL tree.
     * It returns this value, unless str isn't in the tree or it doesn't have
     * a successor.
     * @param t the node containing the value.
     * @param str the value of the String to look for the successor of.
     * @param heart the root of the tree.
     * @return 
     */
    private static String successor(StringAVLNode t, String str, StringAVLNode heart)
    {
        String theSucc = null;
        //The node wasn't found and is null. Do nothing.
        if(t == null)
        {
            //Do nothing if null.
        }
        //The node was found. Need to find successor.
        else
        {
            //If the right child is null, the successor is somewhere
            //in the tree.
            if(t.getRight() == null)
            {
                //point t to the root.
                t = heart;
                boolean done = false;
                //Keep going until there isn't a left right child 
                //and the successor isn't found.
                while(t.getLeft().getRight() != null && 
                        (str.compareToIgnoreCase(t.getRight().getItem()) != 0) && 
                        !done)
                {
                    //Go down the left side.
                    if(str.compareToIgnoreCase(t.getItem()) < 0)
                    {
                        //Heart points to t's parent.
                        heart = t;
                        //Go down left side.
                        t = t.getLeft();
                        //Make sure left child's right child isn't null
                        //so we can continue looking.
                        if(t.getLeft().getRight() != null)
                        {
                            //See if we have the item.
                            if(str.compareToIgnoreCase(t.getLeft().getRight().getItem()) == 0)
                            {
                                theSucc = t.getItem();
                                done = true;
                            }
                        }
                        //Make sure left child's right child isn't null
                        //so we can continue looking.
                        else if(t.getRight().getLeft() != null)
                        {
                            //Check for the item.
                            if(str.compareToIgnoreCase(
                                    t.getRight().getLeft().getItem()) == 0)
                            {
                                t = t.getRight();
                                theSucc = t.getItem();
                                done = true;
                            }
                        }
                        //See if the value is on the left.
                        else if(str.compareToIgnoreCase(
                                t.getLeft().getItem()) == 0)
                        {
                            theSucc = heart.getItem();
                            done = true;
                        }
                        //See if the value is on the right.
                        else if(str.compareToIgnoreCase(
                                t.getRight().getItem()) == 0)
                        {
                            theSucc = t.getItem();
                            done = true;
                        }
                        //There's nowhere to go. At successor.
                        else if(t.getLeft().getRight() == null)
                        {
                            theSucc = t.getItem();
                            done = true;
                        }
                        
                    }
                    //Need to check the right side.
                    else if(str.compareToIgnoreCase(t.getItem()) > 0)
                    {
                        //Heart points to t's parent.
                        heart = t;
                        //Go down right side.
                        t = t.getRight();
                        //Make sure we can keep going.
                        if(t.getLeft().getRight() != null)
                        {
                            //See if the item is the left child's right
                            //child.
                            if(str.compareToIgnoreCase(
                                    t.getLeft().getRight().getItem()) == 0)
                            {
                                theSucc = t.getItem();
                                done = true;
                            }
                        }
                        //Make sure we can look here.
//                        else if(t.getRight().getLeft() != null)
//                        {
//                            //See if the right child's left child
//                            //is the item.
//                            if(str.compareToIgnoreCase(
//                                    t.getRight().getLeft().getItem()) == 0)
//                            {
//                                t = t.getRight();
//                                theSucc = t.getItem();
//                                done = true;
//                            }
//                        }
                        //See if the item is on the left.
                        else if(str.compareToIgnoreCase(t.getLeft().getItem()) == 0)
                        {
                            theSucc = heart.getItem();
                            done = true;
                        }
                        //See if the item is on the right.
                        else if(str.compareToIgnoreCase(t.getRight().getItem()) == 0)
                        {
                            theSucc = t.getItem();
                            done = true;
                        }
                        
                    }
                }
                //If we can't go any further than left and right, we
                //are at successor.
                if(t.getLeft().getRight() == null)
                {
                    theSucc = t.getItem();
                }
            }
            
            //We can get the successor by going right, then left
            //until no more to get the successor.
            else//(t.getRight() != null)
            {
                t = t.getRight();
                //One step right could be successor.
                theSucc = t.getItem();
                //If the left isn't null...
                if(t.getLeft() != null)
                {
                    //...Keep going left until we can't anymore.
                    while(t.getLeft()!= null)
                    {
                        t = t.getLeft();
                    } 
                    //We are now at the successor.
                    theSucc = t.getItem();
                }
            }
        }
        return theSucc;
    }
    
    /**
     * Recursive driver. Inserts an item into the AVL tree if 
     * it does not exist yet.
     * @param str The String to be inserted into the AVL tree.
     */
    public void insert(String str)
    {
        root = insert(str, root);
    }
    
    /**
     * Recursive method. Inserts the String str into the AVL tree, unless
     * it is already in the tree.
     * @param str the String to be inserted into the tree.
     * @param t The node to start at and then see where to place the value.
     * @return The new root after the String is inserted.
     */
    private static StringAVLNode insert(String str, StringAVLNode t)
    {
        int oldBalance, newBalance;
        //t is null. t will now point to a new StringAVLNode.
        if(t == null)
        {
            t = new StringAVLNode(str);
        }
        //The value is in the tree already.
        else if(str.compareTo(t.getItem()) == 0)
        {
            System.out.println(str + " is already in the tree.");
        }
        //The value is less than the current item. The item
        //goes to the left of the current item.
        else if(str.compareTo(t.getItem()) < 0)
        {            
            //If the left child is null, the value goes here.
            if(t.getLeft() == null)
            {
                t.setLeft(new StringAVLNode(str));
                t.setBalance(t.getBalance() - 1);
            }
            //The left child isn't null.
            else
            {
                //Get the old balance before the insert.
                oldBalance = t.getLeft().getBalance();
                t.setLeft(insert(str,t.getLeft()));
                //Get the new balance after the insert.
                newBalance = t.getLeft().getBalance();
                //If the old balance is 0 and the newBalance is non-zero,
                //the height changed on the left.
                if(oldBalance == 0 && newBalance != 0)
                {
                    t.setBalance(t.getBalance() - 1);
                    //If the current node's balance is -2,
                    //it is too large on the left. Need a rotation.
                    if(t.getBalance() == -2)
                    {
                        //Single rotation.
                        if(newBalance == -1)
                        {
                            t = rotateRight(t);
                            t.setBalance(0);
                            t.getRight().setBalance(0);
                        }
                        //newBalance != -1. Double rotate.
                        else
                        {
                            //Special case 1. Double rotation left then right.
                            if(t.getLeft().getRight().getBalance() == -1)
                            {
                                t.setLeft(rotateLeft(t.getLeft()));
                                t = rotateRight(t);
                                t.getLeft().setBalance(0);
                                t.getRight().setBalance(1);
                                t.setBalance(0);
                            }
                            //Special case 2. Double rotation left then right.
                            else if(t.getLeft().getRight().getBalance() == 1)
                            {
                                t.setLeft(rotateLeft(t.getLeft()));
                                t = rotateRight(t);
                                t.getLeft().setBalance(-1);
                                t.getRight().setBalance(0);
                                t.setBalance(0);
                            }
                            //Special case 3. Double rotation left then right.
                            //t.getLeft().getRight().getBalance() == 0.
                            else
                            {
                                t.setLeft(rotateLeft(t.getLeft()));
                                t.getLeft().getLeft().setBalance(
                                t.getLeft().getLeft().getBalance() - 1);
                                t = rotateRight(t);
                                t.setBalance(0);
                                t.getRight().setBalance(0);
                            }
                        }
                    }
                }
            }    
        }
        //The String str belongs on the right side of t.getItem().
        else
        {
            //If the right child is null, we put the value on the right.
            if(t.getRight() == null)
            {
                t.setRight(new StringAVLNode(str));
                t.setBalance(t.getBalance() + 1);
            }
            //t.getRight() != null
            else
            {
                //Get the old balance.
                oldBalance = t.getRight().getBalance();
                t.setRight(insert(str,t.getRight()));
                //Get the new balance.
                newBalance = t.getRight().getBalance();
                //The old balance is 0 and the new balance is a non-zero
                if(oldBalance == 0 && newBalance != 0)
                {
                    //Grew on the right. Add one.
                    t.setBalance(t.getBalance() + 1);
                    //If the balance is 2, we need a rotation.
                    if(t.getBalance() == 2)
                    {
                        //Single rotation left.
                        if(newBalance == 1)
                        {
                            t = rotateLeft(t);
                            t.setBalance(0);
                            t.getLeft().setBalance(0);
                        }
                        //Double rotation. newBalance != -1.
                        else
                        {
                            //Special case 1. Double rotation right, then left.
                            if(t.getRight().getLeft().getBalance() == -1)
                            {
                                t.setRight(rotateRight(t.getRight()));
                                t = rotateLeft(t);
                                t.getLeft().setBalance(0);
                                t.getRight().setBalance(1);
                                t.setBalance(0);
                            }
                            //Special case 2. Double rotation right, then left.
                            else if(t.getRight().getLeft().getBalance() == 1)
                            {
                                t.setRight(rotateRight(t.getRight()));
                                t = rotateLeft(t);
                                t.getRight().setBalance(0);
                                t.getLeft().setBalance(-1);
                                t.setBalance(0);
                            }
                            //Special case 3. 
                            //t.getRight().getLeft().getBalance == 0.
                            //Double rotation right, then left.
                            else
                            {
                                t.setRight(rotateRight(t.getRight()));
                                t.getRight().getRight().setBalance(
                                t.getRight().getRight().getBalance() + 1);
                                t = rotateLeft(t);
                                t.setBalance(0);
                                t.getLeft().setBalance(0);
                            }
                        }
                    }
                }
            }
        }   
        return t;
    }
    
    /**
     * Recursive driver. Deletes a value from the tree or
     * does nothing if the value isn't in the tree.
     * @param str The String to delete in the tree.
     */
    public void delete(String str) 
    {
        root = delete(root, str);
    }

    /**
     * Recursive method. Deletes a value from the tree. If the 
     * value is not found in the AVL tree, nothing is done.
     * @param t The SringAVLNode to start looking at.
     * @param str The String to delete.
     * @return the root of the tree after the value has been deleted.
     */
    private StringAVLNode delete(StringAVLNode t, String str) 
    {
        int oldBalance = 10, newBalance;
        //If the value is null, do nothing.
        if (t == null)
        {

        }

        else if (str.compareToIgnoreCase(t.getItem()) < 0) 
        {
            //Value isn't in the tree.
            if (t.getLeft() == null)
            {
                //System.out.println("Not in tree.");
            }
            //The value is in the tree.
            else
            {
                //Get the old balance.
                oldBalance = t.getLeft().getBalance();
                t.setLeft(delete(t.getLeft(), str));
            }
            //get the new balance.
            //If the left child is null, special case. Child
            //is null after deletion.
            if (t.getLeft() == null)
            {
                newBalance = 3;
            }
            //The left child isn't null. Get the new balance from the node.
            //t.getLeft() != null.
            else
            {
                newBalance = t.getLeft().getBalance();
            }
            //If a non-zero changed to a zero or we
            //have a special case that a value was still deleted, we need to
            //Add to the balance, because it was removed form the
            //left.
            if((oldBalance != 0 && newBalance == 0) || oldBalance == 0 && newBalance == 3
                    || oldBalance == -1 && newBalance == 3)
            {   
                //Add one to the balance.
                t.setBalance(t.getBalance() + 1);
                //If the balance changed to 2, we need to rotate.
                if(t.getBalance() == 2)
                {
                    //Single roation left.
                    if (t.getRight().getBalance() == 1) 
                    {
                        t.setBalance(0);
                        t = rotateLeft(t);
                        t.setBalance(t.getBalance() - 1);
                    }
                    //Single rotation left.
                    else if(t.getRight().getBalance() == 0)
                    {
                        t.setBalance(t.getBalance() - 1);
                        t = rotateLeft(t);
                        t.setBalance(t.getBalance() - 1);
                    }
                    //t.getRight().getBalance() == -1.
                    else 
                    { // double rotation case.
                        //Special case 1.
                        if(t.getRight().getLeft().getBalance() == -1)
                        {
                            t.setRight(rotateRight(t.getRight()));
                            t = rotateLeft(t);
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(1);
                            t.setBalance(0);
                        }
                        //Special case 2.
                        else if(t.getRight().getLeft().getBalance() == 1)
                        {
                            t.setRight(rotateRight(t.getRight()));
                            t = rotateLeft(t);
                            t.getRight().setBalance(0);
                            t.getLeft().setBalance(-1);
                            t.setBalance(0);
                        }
                        //Special case 3.
                        //t.getRight.getLeft().getBalance() == 0.
                        else
                        {
                            t.setRight(rotateRight(t.getRight()));
                            t.getRight().getRight().setBalance(
                            t.getRight().getRight().getBalance() + 1);
                            t = rotateLeft(t);
                            t.setBalance(0);
                            t.getLeft().setBalance(0);
                        }
                    }
                }
            }
        }
        //The item to be deleted is on the right.
        else if (str.compareToIgnoreCase(t.getItem()) > 0)
        {
            //If the right child is null, the value isn't in the tree.
            if (t.getRight() == null)
            {
                //System.out.println("Not in tree.");
            }
            //The right child isn't null. The value is in the tree.
            //t.getRight() != null.
            else
            {
                //Get the old balance.
                oldBalance = t.getRight().getBalance();
                t.setRight(delete(t.getRight(), str));
            }
            //If the deletion made the right child null, this is a 
            //special case.
            if (t.getRight() == null)
            {
                newBalance = -3;
            }
            //The right child isn't null after it's deleted.
            //Get the new balance based on the right child's balance.
            else
            {
                newBalance = t.getRight().getBalance();
            }
            //If a non-zero changed to a zero or there was a special case,
            //and the right child was still deleted. Subtract one from the
            //balance.
            if((oldBalance != 0 && newBalance == 0) || oldBalance == 0 && newBalance == -3
                    || oldBalance == 1 && newBalance == -3)
            {
                //Subtract one from the balance.
                t.setBalance(t.getBalance() - 1);
                //If we changed to a -2, we need to rotate.
                if(t.getBalance() == -2)
                {
                    //Single rotation case 1.
                    if (t.getLeft().getBalance() == -1) 
                    {
                        t.setBalance(0);
                        t = rotateRight(t);
                        t.setBalance(t.getBalance() + 1);
                    }
                    //Single rotation case 2.
                    else if(t.getLeft().getBalance() == 0)
                    {
                        t.setBalance(t.getBalance() + 1);
                        t = rotateRight(t);
                        t.setBalance(t.getBalance() + 1);
                    }
                    //t.getRight().getBalance() == 1.
                    else 
                    { 
                        //Double rotation required.
                        //Special case 1.
                        if(t.getLeft().getRight().getBalance() == -1)
                        {
                            t.setLeft(rotateLeft(t.getLeft()));
                            t = rotateRight(t);
                            t.getLeft().setBalance(0);
                            t.getRight().setBalance(1);
                            t.setBalance(0);
                        }
                        //Special case 2.
                        else if(t.getLeft().getRight().getBalance() == 1)
                        {
                            t.setLeft(rotateLeft(t.getLeft()));
                            t = rotateRight(t);
                            t.getLeft().setBalance(-1);
                            t.getRight().setBalance(0);
                            t.setBalance(0);
                        }
                        //Special case 3.
                        //t.getLeft().getRight().getBalance() == 0
                        else
                        {
                            t.setLeft(rotateLeft(t.getLeft()));
                            t.getLeft().getLeft().setBalance(
                            t.getLeft().getLeft().getBalance() - 1);
                            t = rotateRight(t);
                            t.setBalance(0);
                            t.getRight().setBalance(0);
                        }
                    }
                }
            }
        }
        //str.compareToIgnoreCase(t.getItem()) == 0.
        // t is the node to be deleted.
        else
        {
            //First easy case. Right child is null.
            if(t.getRight() == null)
            {
                t = t.getLeft();
            }
            //Second easy case. Left child is null.
            else if(t.getLeft() == null)
            {
                t = t.getRight();
            }
            //There are two children. Need to get a replacement node.
            else 
            { 
                //Get the old balance.
                oldBalance = t.getLeft().getBalance();
                //Find the replacement node.
                t = replace(t, null, t.getLeft());
                //If the replace cause the left child to be null, special case.
                if(t.getLeft() == null)
                {
                    newBalance = 3;
                }
                //The left child isn't null. get the new balance from it.
                //t.getLeft() != null.
                else
                {
                    newBalance = t.getLeft().getBalance();
                }
                
                //If a non-zero changed to a zero or there is a special case.
                if(oldBalance != 0 && newBalance == 0 || newBalance == 3)
                {
                    //Replacement taken from left. Add one to balance.
                    t.setBalance(t.getBalance() + 1);
                    //If the balance is 2, a rotation is needed.
                    if(t.getBalance() == 2)
                    {
                        //Single rotation case 1.
                        if (t.getRight().getBalance() == 1) 
                        {
                            t.setBalance(0);
                            t = rotateLeft(t);
                            t.setBalance(t.getBalance() - 1);
                        }
                        //Single rotation case 2.
                        else if(t.getRight().getBalance() == 0)
                        {
                            t.setBalance(t.getBalance() - 1);
                            t = rotateLeft(t);
                            t.setBalance(t.getBalance() - 1);
                        }
                        //Need a double rotation.
                        //t.getRight.getBalance() == -1
                        else 
                        { 
                            //Case 1.
                            if(t.getRight().getLeft().getBalance() == -1)
                            {
                                t.setRight(rotateRight(t.getRight()));
                                t = rotateLeft(t);
                                t.getLeft().setBalance(0);
                                t.getRight().setBalance(1);
                                t.setBalance(0);
                            }
                            //Case 2.
                            else if(t.getRight().getLeft().getBalance() == 1)
                            {
                                t.setRight(rotateRight(t.getRight()));
                                t = rotateLeft(t);
                                t.getRight().setBalance(0);
                                t.getLeft().setBalance(-1);
                                t.setBalance(0);
                            }
                            //Case 3.
                            //t.getRight().getLeft().getBalance() == 0
                            else
                            {
                                t.setRight(rotateRight(t.getRight()));
                                t.getRight().getRight().setBalance(
                                t.getRight().getRight().getBalance() + 1);
                                t = rotateLeft(t);
                                t.setBalance(0);
                                t.getLeft().setBalance(0);
                            }
                        }
                    }
                }
                //Special case if the balance changed due to a removal.
                else if(newBalance == -2)
                {
                    //Single rotation cases.
                    //Case 1.
                    if (t.getLeft().getLeft().getBalance() == -1) 
                    {
                        t.getLeft().setBalance(0);
                        t.setLeft(rotateRight(t.getLeft()));
                        t.getLeft().setBalance(t.getLeft().getBalance() + 1);
                    }
                    //Case 2.
                    else if(t.getLeft().getLeft().getBalance() == 0)
                    {
                        t.getLeft().setBalance(t.getLeft().getBalance() + 1);
                        t.setLeft(rotateRight(t.getLeft()));
                        t.getLeft().setBalance(t.getLeft().getBalance() + 1);
                    }
                    //Double rotation needed.
                    //t.getLeft().getLeft().getBalance() == 1
                    else
                    {
                        //Case 1.
                        if(t.getLeft().getLeft().getRight().getBalance() == -1)
                        {
                            t.getLeft().setLeft(rotateLeft(t.getLeft().getLeft()));
                            t = rotateRight(t);
                            t.getLeft().getLeft().setBalance(0);
                            t.getLeft().getRight().setBalance(1);
                            t.getLeft().setBalance(0);
                        }
                        //Case 2.
                        else if(t.getLeft().getLeft().getRight().getBalance() == 1)
                        {
                            t.getLeft().setLeft(rotateLeft(t.getLeft().getLeft()));
                            t.setLeft(rotateRight(t.getLeft()));
                            t.getLeft().getLeft().setBalance(-1);
                            t.getLeft().getRight().setBalance(0);
                            t.getLeft().setBalance(0);
                        }
                        //Case 3.
                        //t.getLeft().getLeft().getRight().getBalance() == 0.
                        else
                        {
                            t.getLeft().setLeft(rotateLeft(t.getLeft().getLeft()));
                            t.getLeft().getLeft().getLeft().setBalance(
                            t.getLeft().getLeft().getLeft().getBalance() - 1);
                            t.setLeft(rotateRight(t.getLeft()));
                            t.getLeft().setBalance(0);
                            t.getLeft().getRight().setBalance(0);
                        }
                        
                    t.setBalance(t.getBalance() + 1);
                    }
                }
            }
        }
        return t;
    }

/**
 * Recursive method. Replaces a node with a replacement node that gets passed.
 * @param t the node to begin at.
 * @param prev the node previous to t.
 * @param replacement the node to replace t.
 * @return the node after replacement.
 */
private static StringAVLNode replace(StringAVLNode t, StringAVLNode prev,
    StringAVLNode replacement) 
{
    //We are at the node to replace the deleted node.
    if (replacement.getRight() == null) 
    { 
        //Replacement node is not the child.
        if (prev != null) 
        {
            //The replacement's left child isn't null.
            if(replacement.getLeft() != null)
            {
                prev.setRight(replacement.getLeft());
            }
            //replacement.getLeft() == null. Simply null out the right.
            else
            {
                prev.setRight(null);
            }
            replacement.setLeft(t.getLeft());
        }
        //Do these steps whether or not replacement node is the child.
        //Move replacement node.
        replacement.setRight(t.getRight());
        replacement.setBalance(t.getBalance());
        t = replacement;
    }
    //We are not at the node to replace the deleted node.
    else 
    {
        int newBalance;
        //Get the old balance before replacement.
        int oldBalance = replacement.getRight().getBalance();
        t = replace(t, replacement, replacement.getRight());
        //If replacement caused right child to be null, special case.
        if(replacement.getRight() == null)
        {
            newBalance = 3;
        }
        //replacement.getRight() != null.
        else
        {
            newBalance = replacement.getRight().getBalance();
        }
        
        //If a non-zero changed to a 0 or there is a special case, we removed
        //a node.
        if(oldBalance != 0 && newBalance == 0 || newBalance == 3)
        {
            //Subtract balance by 1.
            replacement.setBalance(replacement.getBalance() - 1);
            //If we get to -2, need a rotation.
            if(t.getBalance() == -2)
            {
                //Single rotation cases.
                //Case 1.
                if (t.getLeft().getBalance() == -1) 
                {
                    t.setBalance(0);
                    t = rotateRight(t);
                    t.setBalance(t.getBalance() + 1);
                }
                //Case 2.
                else if(t.getLeft().getBalance() == 0)
                {
                    t.setBalance(t.getBalance() + 1);
                    t = rotateRight(t);
                    t.setBalance(t.getBalance() + 1);
                }
                //Double rotation case.
                //t.getRight().getBalance() == 1.
                else 
                { 
                    //Case 1.
                    if(t.getLeft().getRight().getBalance() == -1)
                    {
                        t.setLeft(rotateLeft(t.getLeft()));
                        t = rotateRight(t);
                        t.getLeft().setBalance(0);
                        t.getRight().setBalance(1);
                        t.setBalance(0);
                    }
                    //Case 2.
                    else if(t.getLeft().getRight().getBalance() == 1)
                    {
                        t.setLeft(rotateLeft(t.getLeft()));
                        t = rotateRight(t);
                        t.getLeft().setBalance(-1);
                        t.getRight().setBalance(0);
                        t.setBalance(0);
                    }
                    //Case 3.
                    //t.getLeft().getRight().getBalance() == 0.
                    else
                    {
                        t.setLeft(rotateLeft(t.getLeft()));
                        t.getLeft().getLeft().setBalance(
                        t.getLeft().getLeft().getBalance() - 1);
                        t = rotateRight(t);
                        t.setBalance(0);
                        t.getRight().setBalance(0);
                    }
                }
            }
        }
        //A special case in case something was removed after replacement.
        else if(newBalance == -2)
        {
            //Single rotation cases.
            //Case 1
            if (replacement.getRight().getLeft().getBalance() == -1) 
            {
                replacement.getRight().setBalance(0);
                replacement.setRight(rotateRight(replacement.getRight()));
                replacement.getRight().setBalance(
                        replacement.getRight().getBalance() + 1);
            }
            //Case 2.
            else if(replacement.getRight().getLeft().getBalance() == 0)
            {
                replacement.getRight().setBalance(
                        replacement.getRight().getBalance() + 1);
                replacement.setRight(rotateRight(replacement.getRight()));
                replacement.getRight().setBalance(
                        replacement.getRight().getBalance() + 1);
            }
            //Double rotation required.
            //replacement.getRight().getLeft().getBalance() == -1
            else 
            { 
                //Case 1.
                if(replacement.getRight().getLeft().getRight().getBalance() == -1)
                {
                    replacement.getRight().setLeft(
                            rotateLeft(replacement.getRight().getLeft()));
                    replacement.setRight(rotateRight(replacement.getRight()));
                    replacement.getRight().getLeft().setBalance(0);
                    replacement.getRight().getRight().setBalance(1);
                    replacement.getRight().setBalance(0);
                }
                //Case 2.
                else if(replacement.getRight().getLeft().getRight().getBalance() == 1)
                {
                    replacement.getRight().setLeft(
                            rotateLeft(replacement.getRight().getLeft()));
                    replacement.setRight(rotateRight(replacement.getRight()));
                    replacement.getRight().getLeft().setBalance(-1);
                    replacement.getRight().getRight().setBalance(0);
                    replacement.getRight().setBalance(0);
                }
                //Case 3.
                //replacement.getRight().getLeft().getRight().getBalance() == 0
                else
                {
                    replacement.getRight().setLeft(
                            rotateLeft(replacement.getRight().getLeft()));
                    replacement.getRight().getLeft().getLeft().setBalance(
                    replacement.getRight().getLeft().getLeft().getBalance() - 1);
                    replacement.setRight(rotateRight(replacement.getRight()));
                    replacement.getRight().setBalance(0);
                    t.getRight().setBalance(0);
                }
            }
        }
    }
    return t;
}

/**
 * A method that returns the author of this program.
 * @return A String with the author's name, Jonathan Villegas.
 */
public static String myName()
{
    return "Jonathan Villegas";
}
    String[][] makeGrid(int depth) 
	{
			
		int height = (int) (2*(Math.pow(2, depth-1) -1 + depth));
		int width = (int) Math.pow(2, depth) -1;
		String[][] rtn = new String[height][width];
			
		return rtn;
	}
	 
	 private String[][] subGrid(StringAVLNode subRoot, int depth)
	{	
		 
		int height = (int)(2*(Math.pow(2, depth-1) -1 + depth));
			
		int width = (int)Math.pow(2, depth) -1;
		int down = (int) (2*(Math.pow(2, depth-2))+2);
		String[][] grid = new String[height][width];
			
		for(int row = 0; row<height;row++)
			for (int col=0; col<width;col++)
				grid[row][col] = "   ";
			
		grid[0][width/2] = subRoot.getFirstThreeChar();
		
		if (subRoot.getBalance()>=1)
			grid[1][width/2] = " +"+subRoot.getBalance();
		else if (subRoot.getBalance()==0)
			grid[1][width/2] = "  0";
		else if (subRoot.getBalance()<=-1)
			grid[1][width/2] = " "+subRoot.getBalance();
			
		int numOfDash = (int) Math.pow(2,  depth-2);
		if (subRoot.getLeft()!=null)
		{
			for (int i=0;i<numOfDash;i++)
				grid[2*(numOfDash-i)][i+width/4+1]="/  ";
		}
			
		if (subRoot.getRight()!=null)
		{
			for (int i=0;i<numOfDash;i++)
				grid[2*(i+1)][i+width/2+1]=" \\ ";
		}
		if (subRoot.getLeft()!=null) 
		{
			String[][] tempLF = subGrid(subRoot.getLeft(), depth-1);		
			for (int row=0;row<tempLF.length;row++)
				for (int col=0; col<tempLF[0].length;col++)
					grid[row+down][col] = tempLF[row][col];
		}
		if (subRoot.getRight()!=null) 
		{
			String[][] tempRT = subGrid(subRoot.getRight(), depth-1);	
			for (int row=0;row<tempRT.length;row++)
				for (int col=0; col<tempRT[0].length;col++)
					grid[row+down][col+1+width/2] = tempRT[row][col];
		}
		return grid;
	}
		
	 public String toString()
	{
		String rtn = "";
		String[][] grid = subGrid(root, root.getDepth());

			
		for(int row = 0; row<grid.length;row++) 
		{
			for (int col = 0; col < grid[0].length; col++)
				rtn+=grid[row][col];
			rtn+="\n";
		}
		return rtn;
        }
    public static void main(String[] args) 
    {
//        StringAVLTree tree = new StringAVLTree();
//      
//        tree.insert("aa");
//        tree.insert("b");
//        tree.insert("c");
//        tree.insert("d");
//        tree.insert("e");
//        tree.insert("f");
//        tree.insert("g");
//        tree.insert("h");
//        tree.insert("i");
//        tree.insert("j");
//        tree.insert("k");
//        tree.insert("l");
//        tree.insert("m");
//        tree.insert("n");
//        tree.insert("o");
//        tree.insert("p");
//        tree.insert("q");
//        tree.insert("r");
//        tree.insert("s");
//        tree.insert("t");
//        tree.insert("u");
//        tree.insert("v");
//        tree.insert("w");
//        tree.insert("x");
//        tree.insert("y");
//        tree.insert("z");
//        tree.insert("a");
//        tree.insert("bb");
//        tree.insert("cc");
//        tree.insert("dd");
//        tree.insert("ee");
//        tree.insert("ff");
//        tree.insert("gg");
//        tree.insert("hh");
//        tree.insert("ii");
//        tree.insert("jj");
//        tree.insert("kk");
//        tree.insert("ll");
//        tree.insert("mm");
//        tree.insert("nn");
//        tree.insert("oo");
//        tree.insert("pp");
//        tree.insert("qq");
//        tree.insert("rr");
//        tree.insert("ss");
//        tree.insert("tt");
//        tree.insert("uu");
//        tree.insert("vv");
//        tree.insert("ww");
//        tree.insert("xx");
//        tree.insert("yy");
//        tree.insert("zz");
//        System.out.println(tree);
//        tree.delete("mouse");
//        tree.delete("dolphin");
//        tree.delete("horse");
//        tree.delete("falcon");
//        tree.delete("cat");
//        tree.delete("dog");
//        tree.delete("moose");
//        tree.delete("crab");
//        System.out.println(tree);
//        tree.delete("whale");
//        tree.delete("goldfish");
//        tree.delete("otter");
//        tree.delete("cub");
//        tree.delete("cow");
//        System.out.println(tree);
//        
//        tree.insert("mld");
//        tree.insert("cer");
//        tree.insert("sjv");
//        tree.insert("bpj");
//        tree.insert("gxc");
//        tree.insert("njv");
//        tree.insert("xeh");
//        tree.insert("blp");
//        tree.insert("bxa");
//        tree.insert("cst");
//        tree.insert("lov");
//        tree.insert("nfn");
//        tree.insert("pmt");
//        tree.insert("vkg");
//        tree.insert("znv");
//        tree.insert("cfw");
//        tree.insert("fcl");
//        tree.insert("pin");
//        tree.insert("sjw");
//        tree.insert("scs");
//        tree.insert("yif");
//        tree.insert("zue");
//        tree.insert("yzm");
//        System.out.println(tree);
//        tree.delete("mld");
//        tree.delete("cfw");
//        tree.delete("lion");
//        tree.delete("pmt");
//        tree.delete("cst");
//        tree.delete("znv");
//        tree.delete("nfn");
//        tree.delete("blp");
//        tree.delete("bxa");
//        tree.delete("njv");
//        tree.delete("cer");
//        tree.delete("pelican");
//        tree.delete("beaver");
//        tree.delete("dolphin");
//        tree.delete("mouse");
//        tree.delete("lobster");
//        tree.delete("eagle");
//        System.out.println(tree);
//        int someNum = tree.balanced();
//        System.out.println("num bals: " + someNum);
//        String theSucc = tree.successor("fcl");
//        System.out.println("the succ is: " + theSucc);
//        theSucc = tree.successor("walrus");
//        System.out.println("the succ is: " + theSucc);
//        theSucc = tree.successor("gol");
//        System.out.println("the succ is: " + theSucc);
//        theSucc = tree.successor("fcl");
//        System.out.println("the succ is: " + theSucc);
//        theSucc = tree.successor("fcl");
//        System.out.println("the succ is: " + theSucc);
//        theSucc = tree.successor("fcl");
//        System.out.println("the succ is: " + theSucc);
//        theSucc = tree.successor("fcl");
//        System.out.println("the succ is: " + theSucc);
//        theSucc = tree.successor("fcl");
//        System.out.println("the succ is: " + theSucc);
//        theSucc = tree.successor("fcl");
//        System.out.println("the succ is: " + theSucc);
//        theSucc = tree.successor("fcl");
//        System.out.println("the succ is: " + theSucc);
        //System.out.println("the succ is: " + theSucc);

//        tree.insert("bea");
//        tree.insert("fis");
//        tree.insert("hor");
//        System.out.println(tree);
//
//        tree.delete("dog");
//        System.out.println(tree);
//        StringAVLTreeXtra t = new StringAVLTreeXtra();
//		String str;
//		int ct, ran = 87, line = 1, ansct=0, num;
//		char action, letter;
//		String s = "oimaoinaoioaoipaoiqaoilaoikaoikdikgikfikeoI20oI99onI30odhcrodicjodieiodkhxodiododzzzodnzzo";
//		s += "dourodnrmodmldodlovodgxcodfclodsjvodpmtodaaao";
//		String ans[] = {
//				"0 0 0",
//				"(ma)(0)1 1 1",
//				"(ma(na))(1(0))1 2 1",
//				"((ma)na(oa))((0)0(0))2 2 3",
//				"((ma)na(oa(pa)))((0)1(1(0)))2 3 2",
//				"((ma)na((oa)pa(qa)))((0)1((0)0(0)))3 3 4",
//				"(((la)ma)na((oa)pa(qa)))(((0)-1)0((0)0(0)))3 3 5",
//				"(((ka)la(ma))na((oa)pa(qa)))(((0)0(0))0((0)0(0)))4 3 7",
//				"(((ka)kd((ke)kf))kg((la(ma))na((oa)pa(qa))))(((0)1((0)-1))0((1(0))0((0)0(0))))5 4 8",
//				"((((((aqu)cdf)ejc((hdo)ka(kae)))kd((ke)kf))kg(((la)lhx(lzc))ma(mlh(mrg))))na((((naj)oa(orq))pa(pln(pqr)))qa(((qiq(sgb))tvb(uem))uwp(yfo(zif)))))((((((0)-1)0((0)0(0)))-1((0)-1))-1(((0)0(0))0(1(0))))0((((0)0(0))0(1(0)))1(((1(0))-1(0))-1(1(0)))))13 6 20",
//				"(((((((aka(apa))aqs(aqu(axb)))cdf(((cff)chj)clw((cug)cxs(ddg))))dkr((dpx)dsx((dvd)dyp(edc))))ejc((((epa)esn((fgq)fwu))gbg((giz(gly))gma(gws)))hdo((((hkx)hqv)iry((ive)ixt(ixy)))jgz((joh)jyc))))ka((((kae(kao))kd((ke)kf))kg((((kjj)kpc)la(lds))lev(lhx(lne))))loj(((lsc)lzc)ma(((mfc)mlh)mrg(mrj(mwq))))))na((((((naj)net(nmn))nnq((nps)nvq))oa(((ocx)ogx((onb)orq(otq)))pa((pln)pph(pqr(pzv)))))qa((((qei)qez(qiq))qpx((qrs)qsm((qyc)rfn)))rku(((rmq)rpr((rxu)seh))sgb(((shl)shv(ssz))tdl(tje(tqf))))))tvb((((ucx)uel((uem)uio(uje)))uwp((((vco)vik(vlx))vod((vzc)wdk(wkp)))wmh((wua)xic)))xjt((((xmp)xvh(xvz))yan(ybh))yfo(((yil)ynt(ysk))zfc(zif(zqc)))))))(((((((1(0))0(1(0)))0(((0)-1)0((0)0(0))))-1((0)1((0)0(0))))0((((0)1((0)-1))0((1(0))-1(0)))0((((0)-1)0((0)0(0)))-1((0)-1))))0((((1(0))0((0)-1))1((((0)-1)-1(0))-1(1(0))))-1(((0)-1)1(((0)-1)0(1(0))))))0((((((0)0(0))0((0)-1))1(((0)1((0)0(0)))0((0)1(1(0)))))0((((0)0(0))1((0)1((0)-1)))0(((0)1((0)-1))0(((0)0(0))0(1(0))))))0((((0)1((0)0(0)))1((((0)0(0))0((0)0(0)))-1((0)-1)))-1((((0)0(0))-1(0))0(((0)0(0))0(1(0)))))))55 8 88",
//				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc(((hcr)icj)iei((iod)khx(lov))))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))0(((0)-1)0((0)0(0))))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))14 6 23",
//				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc((icj)iei((iod)khx(lov))))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))0((0)1((0)0(0))))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))14 6 22",
//				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc((iei(iod))khx(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))0((1(0))-1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))13 6 20",
//				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc((iod)khx(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))-1((0)0(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))13 6 20",
//				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc(iod(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))-1(1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))12 6 18",
//				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))12 6 17",
//				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))12 6 17",
//				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))12 6 17",
//				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld((((nfn)njv)nrm((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1((((0)-1)0((0)-1))1(((0)0(0))1((1(0))-1(0)))))11 6 15",
//				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld(((nfn)njv((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1(((0)1((0)-1))1(((0)0(0))1((1(0))-1(0)))))11 6 14",
//				"((((blp)bpj(bxa))cer((cfw)cst((fcl)gxc)))lov(((nfn)njv((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1((0)1((0)-1)))1(((0)1((0)-1))1(((0)0(0))1((1(0))-1(0)))))10 6 12",
//				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc((nfn)njv((pih)pmt)))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))0((0)0(0)))0((0)1((0)-1)))0(((0)0(0))1((1(0))-1(0))))10 5 16",
//				"(((((blp)bpj(bxa))cer((cfw)cst))fcl((nfn)njv((pih)pmt)))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))0((0)-1))0((0)1((0)-1)))0(((0)0(0))1((1(0))-1(0))))9 5 14",
//				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)njv((pih)pmt)))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))-1(0))0((0)1((0)-1)))0(((0)0(0))1((1(0))-1(0))))9 5 13",
//				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)njv(pih)))pmt(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))-1(0))-1((0)0(0)))0(((0)0(0))1((1(0))-1(0))))9 5 13",
//				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)njv))pih(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))-1(0))-1((0)-1))0(((0)0(0))1((1(0))-1(0))))8 5 11",
//				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)njv))pih(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))-1(0))-1((0)-1))0(((0)0(0))1((1(0))-1(0))))8 5 11",
//				""
//		};
//
//		do {
//			action = s.charAt(0);
//			if (action == 'i') {   // insert
//				str = s.substring(1,3);
//				s = s.substring(3, s.length());
//				t.insert(str);
//                                //System.out.println("inserting " + str);
//			} else if (action == 'd') {  // delete
//				str = s.substring(1,4);
//				s = s.substring(4, s.length());
//				t.delete(str);
//                               // System.out.println("deleting " + str);
//			} else if (action == 'n') {  // new tree -- wipe out the tree and start over
//				s = s.substring(1, s.length());
//				t = new StringAVLTreeXtra();
//			} else if (action == 'I') {
//	            num = (s.charAt(1) - '0') * 10 + s.charAt(2) - '0';
//	            s = s.substring(3, s.length());
//	            for (ct = 1; ct <= num; ct++) {
//	               ran = (ran * 101 + 103) % 1000003;
//	               str= String.valueOf((char) (ran%26+'a'));
//	               ran = (ran * 101 + 103) % 1000003;
//	               str+= String.valueOf((char) (ran%26+'a'));
//	               ran = (ran * 101 + 103) % 1000003;
//	               str+= String.valueOf((char) (ran%26+'a'));
//	               t.insert(str);
//                       //System.out.println("inserting " + str);
//	            }
//	        }
//			else {  // no other choice, then compare
//				s = s.substring(1, s.length());
//				System.out.print(line++ + ". ");
//				if (t.toString2().compareTo(ans[ansct]) == 0)
//					System.out.println(" Answers match.   ");
//				else {
//					System.out.print("   *** NO MATCH ***   ");
//					System.out.println(t.toString2());
//				}
////				t.display();
//				ansct++;
//			}
//		} while (s.length() != 0);
	
        //int num = tree.leafCt();
        //System.out.println("Num of leaves: " + num);
        StringAVLTreeXtra t = new StringAVLTreeXtra();
		String str;
		int ct, ran = 87, ransave=0, line = 1, ansct=0, num;
		boolean delete = false;
		char action, letter;
		String s = "oimaoinaoioaoipaoiqaoilaoikaoikdikgikfikeoinaikgikaiqaioaoI20oI99onI30os30dhcrodicjodieiodkhxodiododzzzodnzzo";
		s += "dourodnrmodmldodlovodgxcodfclodsjvodpmtodaaadzzzdmmmojnggjngzdpihojtttdxehdwcsdvkgonI99D5000oD9999oD9999D9999o";
		String ans[] = {
				"0 0 0",
				"(ma)(0)1 1 1",
				"(ma(na))(1(0))1 2 1",
				"((ma)na(oa))((0)0(0))2 2 3",
				"((ma)na(oa(pa)))((0)1(1(0)))2 3 2",
				"((ma)na((oa)pa(qa)))((0)1((0)0(0)))3 3 4",
				"(((la)ma)na((oa)pa(qa)))(((0)-1)0((0)0(0)))3 3 5",
				"(((ka)la(ma))na((oa)pa(qa)))(((0)0(0))0((0)0(0)))4 3 7",
				"(((ka)kd((ke)kf))kg((la(ma))na((oa)pa(qa))))(((0)1((0)-1))0((1(0))0((0)0(0))))5 4 8",
				"(((ka)kd((ke)kf))kg((la(ma))na((oa)pa(qa))))(((0)1((0)-1))0((1(0))0((0)0(0))))5 4 8",
				"((((((aqu)cdf)ejc((hdo)ka(kae)))kd((ke)kf))kg(((la)lhx(lzc))ma(mlh(mrg))))na((((naj)oa(orq))pa(pln(pqr)))qa(((qiq(sgb))tvb(uem))uwp(yfo(zif)))))((((((0)-1)0((0)0(0)))-1((0)-1))-1(((0)0(0))0(1(0))))0((((0)0(0))0(1(0)))1(((1(0))-1(0))-1(1(0)))))13 6 20",
				"(((((((aka(apa))aqs(aqu(axb)))cdf(((cff)chj)clw((cug)cxs(ddg))))dkr((dpx)dsx((dvd)dyp(edc))))ejc((((epa)esn((fgq)fwu))gbg((giz(gly))gma(gws)))hdo((((hkx)hqv)iry((ive)ixt(ixy)))jgz((joh)jyc))))ka((((kae(kao))kd((ke)kf))kg((((kjj)kpc)la(lds))lev(lhx(lne))))loj(((lsc)lzc)ma(((mfc)mlh)mrg(mrj(mwq))))))na((((((naj)net(nmn))nnq((nps)nvq))oa(((ocx)ogx((onb)orq(otq)))pa((pln)pph(pqr(pzv)))))qa((((qei)qez(qiq))qpx((qrs)qsm((qyc)rfn)))rku(((rmq)rpr((rxu)seh))sgb(((shl)shv(ssz))tdl(tje(tqf))))))tvb((((ucx)uel((uem)uio(uje)))uwp((((vco)vik(vlx))vod((vzc)wdk(wkp)))wmh((wua)xic)))xjt((((xmp)xvh(xvz))yan(ybh))yfo(((yil)ynt(ysk))zfc(zif(zqc)))))))(((((((1(0))0(1(0)))0(((0)-1)0((0)0(0))))-1((0)1((0)0(0))))0((((0)1((0)-1))0((1(0))-1(0)))0((((0)-1)0((0)0(0)))-1((0)-1))))0((((1(0))0((0)-1))1((((0)-1)-1(0))-1(1(0))))-1(((0)-1)1(((0)-1)0(1(0))))))0((((((0)0(0))0((0)-1))1(((0)1((0)0(0)))0((0)1(1(0)))))0((((0)0(0))1((0)1((0)-1)))0(((0)1((0)-1))0(((0)0(0))0(1(0))))))0((((0)1((0)0(0)))1((((0)0(0))0((0)0(0)))-1((0)-1)))-1((((0)0(0))-1(0))0(((0)0(0))0(1(0)))))))55 8 88",
				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc(((hcr)icj)iei((iod)khx(lov))))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))0(((0)-1)0((0)0(0))))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))14 6 23",
				"hcr nfn yif zue bpj fcl lov nrm mld iod gxc sjw cfw bxa yzm xeh pih NULL sjv vkg our njv iei znv cer khx wcs icj pmt cst NULL NULL NULL NULL NULL ",
				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc((icj)iei((iod)khx(lov))))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))0((0)1((0)0(0))))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))14 6 22",
				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc((iei(iod))khx(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))0((1(0))-1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))13 6 20",
				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc((iod)khx(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))-1((0)0(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))13 6 20",
				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc(iod(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))(((((0)0(0))0((0)0(0)))-1(1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))12 6 18",
				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))12 6 17",
				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))12 6 17",
				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld((((nfn)njv(nrm))our((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1((((0)0(0))0((0)-1))1(((0)0(0))1((1(0))-1(0)))))12 6 17",
				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld((((nfn)njv)nrm((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1((((0)-1)0((0)-1))1(((0)0(0))1((1(0))-1(0)))))11 6 15",
				"((((blp)bpj(bxa))cer(((cfw)cst(fcl))gxc(lov)))mld(((nfn)njv((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1(((0)0(0))-1(0)))1(((0)1((0)-1))1(((0)0(0))1((1(0))-1(0)))))11 6 14",
				"((((blp)bpj(bxa))cer((cfw)cst((fcl)gxc)))lov(((nfn)njv((pih)pmt))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue)))))((((0)0(0))1((0)1((0)-1)))1(((0)1((0)-1))1(((0)0(0))1((1(0))-1(0)))))10 6 12",
				"(((((blp)bpj(bxa))cer((cfw)cst(fcl)))gxc((nfn)njv((pih)pmt)))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))0((0)0(0)))0((0)1((0)-1)))0(((0)0(0))1((1(0))-1(0))))10 5 16",
				"(((((blp)bpj(bxa))cer((cfw)cst))fcl((nfn)njv((pih)pmt)))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))0((0)-1))0((0)1((0)-1)))0(((0)0(0))1((1(0))-1(0))))9 5 14",
				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)njv((pih)pmt)))sjv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))-1(0))0((0)1((0)-1)))0(((0)0(0))1((1(0))-1(0))))9 5 13",
				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)njv(pih)))pmt(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))-1(0))-1((0)0(0)))0(((0)0(0))1((1(0))-1(0))))9 5 13",
				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)njv))pih(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))-1(0))-1((0)-1))0(((0)0(0))1((1(0))-1(0))))8 5 11",
				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)njv))pih(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))-1(0))-1((0)-1))0(((0)0(0))1((1(0))-1(0))))8 5 11",
				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)ngg(ngz)))njv(((sjw)vkg(wcs))xeh((yif(yzm))znv(zue))))(((((0)0(0))-1(0))-1((0)0(0)))0(((0)0(0))1((1(0))-1(0))))9 5 13",
				"(((((blp)bpj(bxa))cer(cfw))cst((nfn)ngg(ngz)))njv(((sjw)ttt)yif((yzm)znv(zue))))(((((0)0(0))-1(0))-1((0)0(0)))-1(((0)-1)0((0)0(0))))8 5 12",
				"((((((all)art((azx)bcf))bgf((bmx)cmv((cxk)dec(dnx))))drg(((dub(duv))dxd((edn)egv(equ)))eqv(etj(fnu))))ftz((((gcq)gjo)gkn((gnw)got(gve)))hfk(((hgv)hpe(hwz))ijs(izb))))jtq((((((jul)kab)kaq(kqh(kti)))kyy(((ldu)lpm(ltc))mpv(mxx(nbe))))ncj((((ndv)ngg)nhl(npp(nto)))nux((otl)ozu(pmc))))pyk((((pyv)qgk((qsv)rij))rjs((rsk)sau(ugq)))vef(((vsa)vxo(wgm))whg((wsz)xja((xrs)yyi(zrg)))))))((((((0)1((0)-1))0((0)1((0)0(0))))0(((1(0))0((0)0(0)))-1(1(0))))-1((((0)-1)0((0)0(0)))0(((0)0(0))-1(0))))0((((((0)-1)0(1(0)))0(((0)0(0))0(1(0))))0((((0)-1)0(1(0)))-1((0)0(0))))0((((0)1((0)-1))-1((0)0(0)))0(((0)0(0))1((0)1((0)0(0)))))))33 7 55",
				"(((((bgf(bmx))cmv(dec(dnx)))drg(((dub)dxd)egv(equ(etj))))ftz(((gcq)gnw(got))hfk((hwz)ijs(izb))))jtq((((kaq(kti))kyy(((ldu)lpm)mpv(mxx)))nbe(((ndv)ngg)nhl((nto)nux)))pmc(((qsv(rij))rjs((rsk)sau))vef((whg(wsz))xja((xrs)yyi)))))(((((1(0))0(1(0)))0(((0)-1)0(1(0))))-1(((0)0(0))0((0)0(0))))1((((1(0))1(((0)-1)-1(0)))-1(((0)-1)0((0)-1)))-1(((1(0))0((0)-1))0((1(0))0((0)-1)))))17 7 27",
				"((((dub)gcq)gnw((kaq)kti((ldu)lpm)))mpv(((ndv)ngg(nhl))nto(((qsv)rij)sau(whg))))((((0)-1)1((0)1((0)-1)))0(((0)0(0))1(((0)-1)-1(0))))7 5 9"
		};

		do {
			action = s.charAt(0);
			if (action == 'i') {   // insert
				str = s.substring(1,3);
				s = s.substring(3, s.length());
				t.insert(str);
			} else if (action == 'j') {   // insert
				str = s.substring(1,4);
				s = s.substring(4, s.length());
				t.insert(str);
			} else if (action == 'd') {  // delete
				if ( !delete ) {
					delete = true;
					System.out.println("Deletes begin.");
				}
				str = s.substring(1,4);
				s = s.substring(4, s.length());
				t.delete(str);
			} else if (action == 'n') {  // new tree -- wipe out the tree and start over
				s = s.substring(1, s.length());
				t = new StringAVLTreeXtra();
			} else if (action == 'I') {
				ransave = ran;
	            num = (s.charAt(1) - '0') * 10 + s.charAt(2) - '0';
	            s = s.substring(3, s.length());
	            for (ct = 1; ct <= num; ct++) {
	               ran = (ran * 101 + 103) % 1000003;
	               str= String.valueOf((char) (ran%26+'a'));
	               ran = (ran * 101 + 103) % 1000003;
	               str+= String.valueOf((char) (ran%26+'a'));
	               ran = (ran * 101 + 103) % 1000003;
	               str+= String.valueOf((char) (ran%26+'a'));
	               t.insert(str);
	            }
			} else if (action == 'D') {
				ransave = ran;
	            num = (s.charAt(1) - '0') * 1000 + (s.charAt(2) - '0')*100 + (s.charAt(3) - '0')*10 + s.charAt(4) - '0';
	            s = s.substring(5, s.length());
	            for (ct = 1; ct <= num; ct++) {
	               ran = (ran * 101 + 103) % 1000003;
	               str= String.valueOf((char) (ran%26+'a'));
	               ran = (ran * 101 + 103) % 1000003;
	               str+= String.valueOf((char) (ran%26+'a'));
	               ran = (ran * 101 + 103) % 1000003;
	               str+= String.valueOf((char) (ran%26+'a'));
	               t.delete(str);
	            }
			} else if (action == 's') {
				String res = new String(), succ;
				ran = ransave;
	            num = (s.charAt(1) - '0') * 10 + s.charAt(2) - '0';
	            s = s.substring(3, s.length());
	            for (ct = 1; ct <= num; ct++) {
	               ran = (ran * 101 + 103) % 1000003;
	               str= String.valueOf((char) (ran%26+'a'));
	               ran = (ran * 101 + 103) % 1000003;
	               str+= String.valueOf((char) (ran%26+'a'));
	               ran = (ran * 101 + 103) % 1000003;
	               str+= String.valueOf((char) (ran%26+'a'));
	               succ = t.successor(str);
	               if (succ != null)
	            	   res += succ + " ";
	               else
	            	   res += "NULL ";
	            }
	               succ = t.successor("aaa");
	               if (succ != null)
	            	   res += succ + " ";
	               else
	            	   res += "NULL ";
	               succ = t.successor("ccc");
	               if (succ != null)
	            	   res += succ + " ";
	               else
	            	   res += "NULL ";
	               succ = t.successor("nnn");
	               if (succ != null)
	            	   res += succ + " ";
	               else
	            	   res += "NULL ";
	               succ = t.successor("vvv");
	               if (succ != null)
	            	   res += succ + " ";
	               else
	            	   res += "NULL ";
	               succ = t.successor("zzz");
	               if (succ != null)
	            	   res += succ + " ";
	               else
	            	   res += "NULL ";
				if (res.compareTo(ans[ansct]) == 0)
					System.out.println("     Answers match in successor.   ");
				else {
					System.out.println("   *** NO MATCH IN SUCCESSOR ***   ");
					System.out.println(res);
				}
//				t.display(); System.out.println();
				ansct++;
	        } else {  // no other choice, then compare
				s = s.substring(1, s.length());
				System.out.print(line++ + ". ");
				if (t.toString2().compareTo(ans[ansct]) == 0) {
					System.out.print(" Answers match.   ");
					if ( line % 4 == 1)
						System.out.println();
				}
				else {
					System.out.println("   *** NO MATCH ***   ");
					System.out.println(t.toString2());
				}
//				t.display(); System.out.println();
				ansct++;
			}
		} while (s.length() != 0);
		System.out.println("Programmed by: " + StringAVLTree.myName());
	}

    }

