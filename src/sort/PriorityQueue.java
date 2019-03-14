package sort;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/14
// +----------------------------------------------------------------------
// | Time: 14:42
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------

/**
 * 优先队列的实现方式  基于二叉堆
 * <p>
 * 事实上底层数组并不是真的有序的,只是基于堆的特性,每次得到最大(小)值,结果是有序的,getMax()可以理解为消费队列内容
 *
 * 插入和获取最高优先的时间复杂度都为o(logN)
 */
public class PriorityQueue<T extends Comparable<T>> {

    private T[] queue;//用一个数组表示此二叉堆
    private int n = 0;//当前二叉堆最后面的元素

    public PriorityQueue(int maxSize) {
        queue = (T[]) new Comparable[maxSize + 1];
    }

    /**
     * 向队列中插入元素
     * 第0个位置空出来以便于计算父子节点
     *
     * @param t
     */
    public void insert(T t) {
        if (n >= queue.length -1 ) throw new IndexOutOfBoundsException("队列已满");
        queue[++n] = t;
        swim(n);//将新插入的元素上浮到合适的位置

    }

    public boolean isEmpty() {
        return n == 0;
    }

    public T getMax() {
        if (isEmpty()) {
            queue[n + 1] = null;
            throw new IndexOutOfBoundsException("已经到头");
        }
        T max = (T) queue[1];
        exchange(queue, 1, n--);
        queue[n + 1] = null;
        sink(1);
        return max;
    }

    /**
     * 将第i个元素上浮到合适的位置
     *
     * @param i
     */
    private void swim(int i) {

        while (i > 1 && isLess(queue[i / 2], queue[i])) {// i / 2 父节点位置

            exchange(queue, i, i / 2);

            i /= 2;

        }

    }

    /**
     * 将第i个元素下沉到合适的位置
     *
     * @param i
     */
    private void sink(int i) {

        while (i * 2 < n) {

            int j = i * 2;
            if (isLess(queue[j], queue[j + 1])) j++;

            if (isLess(queue[j], queue[i])) break;

            exchange(queue, i, j);

            i = j;
        }


    }


    /**
     * 执行交换操作
     *
     * @param cs
     * @param i
     * @param j
     */
    private void exchange(Comparable[] cs, int i, int j) {

        Comparable temp;
        temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;

    }

    /**
     * c1是否小于c2
     *
     * @param c1
     * @param c2
     * @return
     */
    private boolean isLess(Comparable c1, Comparable c2) {

        return c1.compareTo(c2) < 0;
    }

}
