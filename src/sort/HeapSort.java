package sort;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/14
// +----------------------------------------------------------------------
// | Time: 18:25
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------

/**
 * 堆排序
 * 原理: 将数组初始化成二叉堆结构,然后取堆顶与结束节点进行交换,依次类推,便得到一个有序数组,此过程不占用额外的空间
 * 时间复杂度为o(NlgN),空间复杂度为o(1)
 */
public class HeapSort extends AbstractSort {
    @Override
    public void sort(Comparable[] comparables) {
        //先将数组初始化成一个二叉堆结构

        int n = comparables.length - 1;//结束节点
        int end = n;//子节点
        int p = this.getParentByChild(end);//父节点

        while (p >=0 ){

            sink(comparables,p,n);
            if (p == 0) break;
            end = p;
            p = this.getParentByChild(end);

        }

        while (n > 0){

            exchange(comparables,0,n--);
            sink(comparables,0,n);
        }

    }




    private void sink(Comparable[] cs,int from ,int end){

        if (from < 0 || end >= cs.length) throw new IndexOutOfBoundsException("超出了范围...");

        while ( from * 2 + 1 < end ){

            int j = from * 2 + 1;

            if (isLess(cs,j,j+1)) j++;
            if ( ! isLess(cs,from,j)) break;

            exchange(cs,from,j);
            from = j;

        }

    }

    private int getParentByChild(int child){

        if (child % 2 == 0) return child / 2 -1;
        return child / 2;
    }
}
