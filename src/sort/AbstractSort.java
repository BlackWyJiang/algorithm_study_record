package sort;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/5
// +----------------------------------------------------------------------
// | Time: 16:30
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------
public abstract class AbstractSort {


    /**
     * 判断是否是有序状态
     */
    public  boolean isSorted(Comparable[] comparables){

        for (int i = 1; i < comparables.length; i++) {
            if (comparables[i].compareTo(comparables[i - 1]) < 0 ) return false;

        }
        return true;
    }

    /**
     * 交换位置
     */
    protected  void exchange(Comparable[] c,int i,int j){
        if (i == j) return;
        Comparable temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    /**
     * 比较大小  i位置是否小于j位置
     */
    protected  boolean isLess(Comparable[] c,int i,int j){
        return c[i].compareTo(c[j]) < 0;
    }

    public abstract  void sort(Comparable[] comparables);
}
