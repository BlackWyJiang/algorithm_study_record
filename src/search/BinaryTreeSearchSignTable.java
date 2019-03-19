package search;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/18
// +----------------------------------------------------------------------
// | Time: 16:48
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------

/**
 * 基于二叉查找树实现符号表功能
 *
 * 前提约定:一个节点的左子树小于父节点,右子树大于父节点
 *
 * 平均读写操作的时间复杂度都是对数级别o(logN)  具体是1.39lgN 此时指的是树比较平衡的情况下,如果树的节点往一个方向生长  则读写复杂度都为o(N)
 * 遇到这种情况  使用平衡二叉查找树进行平衡
 *
 *
 * @param <Key>
 * @param <Value>
 */
public class BinaryTreeSearchSignTable<Key extends Comparable<Key>, Value> implements SortedSignTable<Key, Value> {

    private Node root;


    @Override
    public Key min() {
        return min0(root).key;
    }

    private Node min0(Node node) {
        if (node == null) return null;
        return node.left == null ? node : min0(node.left);

    }

    @Override
    public Key max() {
        return max0(root).key;
    }

    private Node max0(Node node) {
        if (node == null) return null;
        return node.right == null ? node : max0(node.right);
    }

    @Override
    public Key floor(Key key) {
        Node node = floor0(root, key);

        return node == null ? null : node.key;
    }

    private Node floor0(Node node, Key key) {
        if (key == null) throw new IllegalArgumentException("key 不能为空");

        if (node == null) return null;

        int i = key.compareTo(node.key);
        if (i == 0) return node;
        else if (i < 0) return floor0(node.left, key);
        else {

            Node n = floor0(node.right, key);
            return n == null ? node : n;
        }
    }

    @Override
    public Key ceiling(Key key) {
        Node node = ceiling0(root, key);
        return node == null ? null : node.key;
    }

    private Node ceiling0(Node node, Key key) {
        if (key == null) throw new IllegalArgumentException("key 不能为空");

        if (node == null) return null;

        int i = key.compareTo(node.key);

        if (i == 0) return node;
        else if (i > 0) return ceiling0(node.right, key);
        else {
            Node n = ceiling0(node.left, key);
            return n == null ? node : n;
        }

    }


    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (key == null) throw new IllegalArgumentException("key 不能为空");
        if (node == null) return 0;

        int i = key.compareTo(node.key);
        if (i == 0) return size(node.left);
        else if (i > 0) return size(node.left) + rank(node.right, key) + 1;
        else return rank(node.left, key);

    }

    @Override
    public Key select(int i) {
        Node select = select(root, i);

        return select == null ? null : select.key;
    }

    private Node select(Node node, int i) {

        if (node == null) return null;

        int size = size(node.left);

        if (size == i) return node;
        else if (size > i) return select(node.left, i);
        else return select(node.right, i - size - 1);

    }

    @Override
    public Iterable<Key> keys() {
        if (isEmpty()) return new LinkedBlockingQueue<Key>();
        return keys(min(), max());

    }

    @Override
    public Iterable<Key> keys(Key low, Key high) {
        Queue<Key> queue = new LinkedBlockingQueue<Key>();

        keys(root, queue, low, high);

        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key low, Key high) {
        if (node == null) return;

        int lowCom = low.compareTo(node.key);
        int highCom = high.compareTo(node.key);

        if (lowCom < 0) keys(node.left, queue, low, high);
        if (lowCom <= 0 && highCom >= 0) queue.add(node.key);
        if (highCom > 0) keys(node.right, queue, low, high);


    }

    @Override
    public void put(Key key, Value value) {

        root = this.put0(root, key, value);
    }

    private Node put0(Node parent, Key key, Value value) {

        if (parent == null) {
            parent = new Node(key, value, null, null, 1);
            return parent;
        }
        Node node = parent;

        int i = key.compareTo(node.key);

        if (i == 0) {

            node.value = value;
            return node;
        } else if (i < 0)
            node.left = put0(node.left, key, value);
        else
            node.right = put0(node.right, key, value);

        node.size++;
        return node;

    }

    @Override
    public Value get(Key key) {
        return get0(root, key).value;
    }

    private Node get0(Node parent, Key key) {

        if (parent == null) return null;

        Node node = parent;

        int i = key.compareTo(node.key);

        if (i == 0) return node;

        if (i > 0) node = node.right;

        if (i < 0) node = node.left;

        return get0(node, key);

    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {

        return node == null ? 0 : node.size;
    }


    @Override
    public void delete(Key key) {

        delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        int i = key.compareTo(node.key);

        if (i < 0) node.left = delete(node.left, key);
        else if (i > 0) node.right = delete(node.right, key);
        else {
            //如果只有一个子节点直接返回
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node n = min0(node.right);

            n.right = deleteMin(node.right);
            n.left = node.left;

            node = n;


        }
        node.size = size(node.right) + size(node.left) + 1;
        return node;
    }

    @Override
    public void deleteMax() {

        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {

        if (node == null) return null;

        if (node.right == null) return node.left;

        node.right = deleteMax(node.right);
        node.size = size(node.right) + size(node.left) + 1;
        return node;


    }

    @Override
    public void deleteMin() {

        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {

        if (node == null) return null;

        if (node.left == null) return node.right;

        node.left = deleteMax(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;

    }

    private class Node {

        Key key;
        Value value;
        Node left;
        Node right;
        int size;

        private Node(Key key, Value value, Node left, Node right, int size) {

            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size = size;

        }
    }
}
