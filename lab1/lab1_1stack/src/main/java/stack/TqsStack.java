package stack;

import java.util.LinkedList;

public class TqsStack<T> implements Stack<T>{
    private LinkedList<T> stack = null;
    private int max_size = -1;

    public TqsStack(){
        stack = new LinkedList<T>();
    }

    public TqsStack(int size){
        stack = new LinkedList<T>();
        this.max_size = size;
    }

    @Override
    public void push(T item){
        if(max_size != -1 && stack.size() >= max_size){
            throw new IllegalStateException();
        }
        stack.push(item);
    }

    @Override
    public T pop(){
        if(stack.isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        return stack.pop();
    }

    @Override
    public T peek(){
        if(stack.isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        return stack.peek();
    }

    @Override
    public int size(){
        return stack.size();
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

}
