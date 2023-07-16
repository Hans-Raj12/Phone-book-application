
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

class Node {

    String name;
    String number;
    
    Node left;
    Node right;

    Node(String name, String number) {
        this.name = name;
        this.number = number;
    }
    public String getNode()
    {
        return name+": "+number;
    }
    
}
public class BinarySearchTree {
      Node root;
      Stack stack;
    public static java.util.List<Node> sortedList = new java.util.ArrayList<Node>();
    BinarySearchTree()
    {
        root = null;
    }
    public void insert(String name,String number)
    {
        Node newNode = new Node(name,number);
        if(root == null)
        {
            root = newNode;
            return;            
        }
        
        Node temp = root;
        while(true)
        {
            if(name.compareTo(temp.name)<0)
            {
                if(temp.left!=null)
                    temp =temp.left;
                else
                {
                    temp.left = newNode;
                    break;
                }
            }
            else if(name.compareTo(temp.name)>0)
            {
                if(temp.right!=null)
                    temp =temp.right;
                else
                {
                    temp.right = newNode;
                    break;
                }
            }
            else
                break;
        }
    }
    private void inOrder(Node root) {
        if(root!=null)
        {
           inOrder(root.left);
           //System.out.print(""+root.name+" "+root.number + " ");
           inOrder(root.right);
            
        }
    }
    
    // CALL THIS METHOD WHENEVER NEW CONTACTS ARE ADDED, THEN ACCESS THE 
    // sortedList FOR SORTED ITEMS
    public void sortContacts() 
    {
        sort(root);
        file(root);
    }
    
    
    
    private void sort(Node node) {
             if(node == null)
                return;
           
            sort(node.left);
            sortedList.add(node);
            sort(node.right);
        
    }
    public void file(Node root)
    {
        try
        {
            FileWriter fw = new FileWriter("SortedList.txt");
            Writer output = new BufferedWriter(fw);
            int size = sortedList.size();
            for(int i=0; i<size; i++)
            {
                Node node=sortedList.get(i);
                
                output.write(node.name+"\n");
                output.write(node.number+"\n");
            }
            output.close();
            
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public  void in()
    {
        inOrder(root);
    }
    private Node search(Node root,String n)
    {
        if(root==null)
            return null;
        else if(n.equals(root.name))
            return root;
        else if(n.compareTo(root.name)<0)
            return search(root.left,n);
        else 
            return search(root.right,n);
    }
    public Node SearchCO(String Name)
    {
        Node node = search(root,Name);
        int stackSize=10;
        stack = new Stack(stackSize);
        if(!stack.isFull())
        {
            stack.push(node.name);
        }
        else
        {
            stack.pop();
            stack.push(node.name); 
        }
       
        try
        {
            FileWriter file = new FileWriter("Recents.txt",true);
            String data="";
            int lines=0;
            for(int i=0 ;i<stackSize; i++)
            {
                if(!stack.isEmpty())
                {
                    data+=stack.pop()+"\n";
                }
                
                 
            }
            file.write(data);
            file.flush();
            file.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        return node;
    }
    public void remove(String name)
    {
        root=remove(name,this.root);
    }
    public Node remove(String name,Node node)
    {
        
        if(node==null)
        {
            return null;
        }
        if(name.compareTo(node.name)==0)
        {
            if(node.left==null)
                return node.right;
            else if(node.right==null)
                return node.left;
            else
            {
                node.name=getRightmost(node.left);
                node.left=remove(node.name,node.left);
            }
        }
        else
        {
            if(name.compareTo(node.name)<0)
                node.left=remove(name,node.left);
            else
                node.right=remove(name,node.right);
        }
        
        return node;
    }
     public String getRightmost(Node node)
    {
        assert(node!=null);
        Node right = node.right;
        if(right==null)
            return node.name;
        else
            return getRightmost(right);
    }
    
}

