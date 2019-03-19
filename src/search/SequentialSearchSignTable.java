package search;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/15
// +----------------------------------------------------------------------
// | Time: 16:26
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------


import java.util.Iterator;

/**
 * 顺序查找的实现方式,(基于无序链表)
 *
 * get和put都是遍历所有链表节点  所以查找和插入的时间复杂度都是o(N)
 * 并且key是无序的,不支持有序的api操作
 * 适合小规模
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchSignTable<Key ,Value> implements SignTable<Key ,Value> {

    private Node first;

    private int size = 0;


    @Override
    public void put(Key key, Value value) {

        if (value == null){
            delete(key);
            return;
        }

        for (Node x = first;x!= null;x = x.next){
            if(key.equals(x.key)){//如果此key已经存在,直接替换
                x.value  = value;
                return;
            }
        }

        //如果遍历完还没有找到key,说明不存在,新建node,并作为起始节点
        first = new Node(key,value,first);
        size++;


    }

    @Override
    public Value get(Key key) {
        for (Node x = first;x!= null;x = x.next){
            if(key.equals(x.key)){//如果此key已经存在,直接返回
                return x.value;
            }
        }
        //如果遍历完还没有找到key,说明不存在返回null
        return null;
    }

    @Override
    public void delete(Key key) {

        if (first == null )return;
        if (key.equals(first.key)){
            first=null;
            size--;
            return;
        }

        for (Node pre = first;pre.next != null;pre = pre.next ){

            if (key.equals(pre.next.key)){
                pre.next = pre.next.next;
                size--;
                return;
            }

        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterable<Key> keys() {

        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new TestKeyIterator(first);
            }
        };
    }

    /**
     * 链表节点
     */
    private class Node{
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 迭代器
     * @param <>
     */
    private class TestKeyIterator implements Iterator {

        private Node next;
        private int curser=0;

        public TestKeyIterator(Node next) {
            this.next = next;
        }

        @Override
        public boolean hasNext() {
            return curser < size;
        }

        @Override
        public Object next() {
            Key k = next.key;
            if (hasNext()){
                next = next.next;
            }
            curser++;
            return k;
        }
    }

}

