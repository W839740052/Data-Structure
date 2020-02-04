package com.wyh.set;

public class AVLSet<E extends Comparable<E>> implements Set<E> {

    private AVLTree<E, Object> avl;//这里不需要值，下面遇到了之后直接赋值null

    public AVLSet(){
        avl = new AVLTree<>();
    }

    @Override
    public int getSize(){
        return avl.getSize();
    }

    @Override
    public boolean isEmpty(){
        return avl.isEmpty();
    }

    @Override
    public void add(E e){
        avl.add(e, null);
    }

    @Override
    public boolean contains(E e){
        return avl.contains(e);
    }

    @Override
    public void remove(E e){
        avl.remove(e);
    }
}
