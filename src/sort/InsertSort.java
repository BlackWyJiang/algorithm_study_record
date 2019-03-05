package sort;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/5
// +----------------------------------------------------------------------
// | Time: 16:35
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------
public class InsertSort extends AbstractSort {

    /**
     * 插入排序
     * 保证插入的位置之前的是有序的,然后拿要插入的的元素逐一对比,直到合适的位置
     *
     * 特点:  在目标数组有序度越高,性能就越高,所以适用于部分有序数组的排序
     *
     * @param comparables 实现了Comparable接口的集合
     */
    @Override
    public void sort(Comparable[] comparables) {

        int len = comparables.length;

        for (int i = 0; i < len; i++) {

            for (int j = i; j > 0 && comparables[j].compareTo(comparables[j - 1]) < 0; j--) {
                exchange(comparables,j,j-1);
            }
        }

    }
}
