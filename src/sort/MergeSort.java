package sort;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/5
// +----------------------------------------------------------------------
// | Time: 18:11
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------
public class MergeSort extends AbstractSort {

    private Comparable[] auxiliary = null; //辅助数组,此处作为一个全局属性是因为在进行归并的时候每次都要创建

    /**
     * 归并排序:
     * 时间复杂度为 O(nlgn)  由于是基于递归的 空间复杂度是一个劣势为O(n)
     *
     * @param comparables
     */
    @Override
    public void sort(Comparable[] comparables) {

        auxiliary = new Comparable[comparables.length];

        //fromUp2DownSort(comparables,0,comparables.length - 1);
        fromDown2UpSort(comparables);
    }

    /**
     * 抽象出来在一定的范围内排序,(自上而下的递归归并)
     * 将大数组分成两边分别排序,然后递归,直到数组长度为1,
     * 此思想主要是将大问题分成小问题然后递归的解决
     * @param comparables
     * @param low
     * @param high
     */
    private void fromUp2DownSort(Comparable[] comparables, int low, int high) {

        if (low >= high) return;

        int mid = low + (high - low)/2;  //要排序数组的中间值

        //分别对分成的两边进行递归调用排序
        fromUp2DownSort(comparables,low,mid);
        fromUp2DownSort(comparables,mid+1,high);

        //将排序好的两边进行归并处理
        merge(comparables,low,mid,high);


    }


    /**
     * 自下而上的归并排序
     *
     * 先将数组分为长度为1的多个数组,然后相邻的进行合并,直到合并成一个数组
     *
     * 其思想主要是循序渐进的解决大问题
     *
     * 主要更适合链表结构的集合
     *
     * @param comparables
     */
    private void fromDown2UpSort(Comparable[] comparables){


        for (int size = 1; size < comparables.length; size += size ) {
            for (int i = 0; i < comparables.length - size; i += (size*2)) {
                merge(comparables,i,i+size-1,i+ 2*size-1 <comparables.length ? i+ 2*size-1  : comparables.length -1 );
            }


        }


    }

    /**
     * 对数组进行归并
     *
     * 将目标数组中的[low-mid]和[mid-high]进行归并
     *
     * @param comparables
     * @param low
     * @param mid
     * @param high
     */
    private void merge(Comparable[] comparables, int low, int mid, int high) {

        //首先将当前数组中的数据复制到辅助数组中,此时辅助数组是复用的
        for (int i = low; i <= high; i++) {
            auxiliary[i] = comparables[i];
        }

        //将辅助数组中的了两部分依次拿出来对比按大小放回原数组

        int j = low,k= mid + 1;//定义两个指针分别指向两段数组的起始位置

        for (int i = low; i <= high ; i++) {
            if (j > mid){
                comparables[i] = auxiliary[k++];
                continue;
            }

            if (k > high){
                comparables[i] = auxiliary[j++];
                continue;
            }

            if (auxiliary[j].compareTo(auxiliary[k]) < 0)
                comparables[i] = auxiliary[j++];
            else
                comparables[i] = auxiliary[k++];


        }



    }
}
