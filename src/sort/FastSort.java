package sort;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/6
// +----------------------------------------------------------------------
// | Time: 15:13
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------
public class FastSort extends AbstractSort {

    /**
     * 快速排序
     * 取任意元素作为比较对象,遍历整个数组,将比较对象放入一个左边比之小,右边比之大的位置,以此递归,最终形成一个有序数组
     * @param comparables
     */
    @Override
    public void sort(Comparable[] comparables) {

        //sort (comparables,0,comparables.length-1);
        quick3Sort(comparables,0,comparables.length-1);

    }

    /**
     * 基本的快速排序方式
     * @param comparables
     * @param low
     * @param high
     */
    private void sort(Comparable[] comparables, int low, int high) {
        if (low >= high) return;

        int partitionPoint = partition( comparables, low,  high);

        sort(comparables,low,partitionPoint - 1);
        sort(comparables,partitionPoint + 1,high);

    }

    /**
     * 寻找切分点
     * @param comparables
     * @param low
     * @param high
     * @return
     */
    private int partition(Comparable[] comparables, int low, int high) {

        //随机或者取一个切分元素
        Comparable c = comparables[low];

        int i = low, j = high + 1;//两个指针

        while (true){
            while (comparables[++i].compareTo(c) < 0) if (i == high) break;
            while (comparables[--j].compareTo(c) > 0) if (i == low ) break;

            if (i >= j) break;
            exchange(comparables,i,j);
        }
        exchange(comparables,low,j);

        return j;

    }


    /**
     * 三向切分的快速排序
     *
     * @param comparables
     * @param low
     * @param high
     */
    private void quick3Sort(Comparable[] comparables, int low, int high){

        if (high <= low) return;

        //把目标数组分成大于某个元素的和小于某个元素的两块

        int little = low, big = high;

        Comparable c = comparables[low];

        int i = low + 1;

        while (i <= big){
            int cmp = comparables[i].compareTo(c);

            if (cmp < 0 ) exchange(comparables,little++,i++);
            else if (cmp > 0 ) exchange(comparables,big--,i);
            else i++;
        }

        quick3Sort(comparables,low,little-1);
        quick3Sort(comparables,little+1,high);




    }
}
