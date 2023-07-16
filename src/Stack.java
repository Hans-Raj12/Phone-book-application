/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hans Raj
 */
public class Stack 
{
    String[] arr;
    int top;
    int capacity;
    
    Stack(int size)
    {
        arr = new String[size];
        capacity = size;
        top =-1;
    }
    public void push(String str)
    {
        if(!this.isFull())
        {
            arr[++top]=str;
        }
    }
    public String pop()
    {
        if(!this.isEmpty())
        {
            String temp = arr[top];
            top-=1;
            return temp;
        }
        else
            return null;
    }
    public boolean isFull()
    {
        return top == capacity-1;
    }
    public boolean isEmpty()
    {
        if(top<0)
            return true;
        else
            return false;
    }
}
 