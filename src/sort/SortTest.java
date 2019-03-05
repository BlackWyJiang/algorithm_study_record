package sort;

import java.util.*;

// +----------------------------------------------------------------------
// | ProjectName: algorithm
// +----------------------------------------------------------------------
// | Date: 2019/3/4
// +----------------------------------------------------------------------
// | Time: 17:42
// +----------------------------------------------------------------------
public class SortTest {

    /**
     * 判断是否是有序状态
     */
    public static boolean isSorted(Comparable[] comparables){

        for (int i = 1; i < comparables.length; i++) {
            if (comparables[i].compareTo(comparables[i - 1]) < 0 ) return false;

        }
        return true;
    }

    /**
     * 交换位置
     */
    private static void exchange(Comparable[] c,int i,int j){
        if (i == j) return;
        Comparable temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }


    /**
     * 选择排序
     *
     * 在整个集合里面先找最小的元素,放到首位,然后再剩下的里面继续找最小的元素放在前面.依次类推
     *
     * 特点:
     * (1)与初始数组无关:即不管初始数组的有序程度如何,排序时间是一样的
     * (2)数据移动最少: 移动次数跟数组长度是线性关系
     *
     * @param c 实现了Comparable接口的集合
     */
    public static void selectSort(Comparable[] c ){

        int len = c.length;

        for (int i = 0; i < len; i++) {

            int min = i;

            for (int j = i + 1; j < len; j++) {//查询最小项

                if (c[min].compareTo(c[j]) > 0){ //(n-1)+(n-2)+...+1 = n^2 / 2 次比较
                    min = j;
                }
            }

            //跟最小的交换位置
            exchange(c,i,min);  //最多n-1次交换
        }

    }


    /**
     * 插入排序
     * 保证插入的位置之前的是有序的,然后拿要插入的的元素逐一对比,直到合适的位置
     *
     * 特点:  在目标数组有序度越高,性能就越高,所以适用于部分有序数组的排序
     *
     * @param comparables 实现了Comparable接口的集合
     */
    public static void  insertSort(Comparable[] comparables){
        int len = comparables.length;

        for (int i = 0; i < len; i++) {

            for (int j = i; j > 0 && comparables[j].compareTo(comparables[j - 1]) < 0; j--) {
                exchange(comparables,j,j-1);
            }
        }
    }


    /**
     * 希尔排序
     * 原理就是将大数组分割成小数组,开始的分割跨度比较大,一般是分割后的小数组只有两个元素,然后进行插入排序,
     * 渐渐的缩小跨度,分割后的数组的元素渐渐增多,但是由于之前已经执行过排序,此时分割后的数组的有序度较高,使得插入排序性能较高,
     * 等跨度缩小到1时,此时执行的的是对整个数组进行的插入排序,但是当前的数组的有序度超级高  性能也超级高
     *
     * 极适合乱序大数组排序场景
     *
     * @param comparables
     */
    public static void shellSort(Comparable[] comparables){

        int len = comparables.length;
        int h = 1;

        //指定首次跨度大小
        while (h < len/3) h = h * 3 + 1;


        while (h >= 1){
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && comparables[j].compareTo(comparables[j - h]) < 0; j -= h) {
                    exchange(comparables,j,j-h);
                }
            }
            h /= 3;
        }
    }



    public static void main(String[] args) {

        Integer[] test = new Integer[10000000];

        Random random = new Random();
        for (int i = 0; i < test.length; i++) {
            test[i] = random.nextInt(500000000);
        }
       // System.out.println(Arrays.toString(test));

        long start = System.currentTimeMillis();
        //快速排序
        //selectSort(test);

        //普通插入排序
        //insertSort(test);

        shellSort(test);

        System.out.println("times: " + (System.currentTimeMillis() - start));
       // System.out.println(Arrays.toString(test));

        System.out.println(isSorted(test));
    }

}
