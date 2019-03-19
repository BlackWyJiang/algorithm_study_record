package search.test;

import search.BinaryArraySearchSignTable;
import search.BinaryTreeSearchSignTable;
import search.SortedSignTable;

import java.util.Iterator;
import java.util.Random;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/18
// +----------------------------------------------------------------------
// | Time: 11:34
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------
public class TestSearch {

    public static void main(String[] args) {

/**************          顺序查找测试                    *******************/
//        SignTable signTable = new SequentialSearchSignTable<>();
//        for (int i = 0; i < 10; i++) {
//            signTable.put(new TestKey(i),"value:"+i);
//        }
//
//        System.out.println(signTable.size());
//        System.out.println(signTable.toString0());
//
//        System.out.println(signTable.get(new TestKey(5)));
//
//        signTable.put(new TestKey(5),null);
//
//        System.out.println(signTable.size());
//        System.out.println(signTable.toString0());

        /*********       基于数组的二分查找测试           *********/
//        SortedSignTable<TestKey, String> signTable = new BinaryArraySearchSignTable<>(10);
//
//        Random random = new Random();
//
//        for (int i = 0; i < 5; i++) {
//            int j =random.nextInt(100);
//            signTable.put(new TestKey(j),"value"+j);
//        }
//
//        Iterable<TestKey> keys = signTable.keys(new TestKey(1), new TestKey(3));
//        Iterator<TestKey> iterator = keys.iterator();
//        while (iterator.hasNext()){
//
//            System.out.println(iterator.next());
//        }

//        signTable.deleteMax();
//        signTable.deleteMin();
//        signTable.deleteMax();
//        signTable.deleteMin();
//        signTable.deleteMin();
//        TestKey select = signTable.select(0);

//        int size = signTable.size(new TestKey(1), new TestKey(3));
//        TestKey ceiling = signTable.ceiling(new TestKey(10));
//        TestKey floor = signTable.floor(new TestKey(50));
//        boolean empty = signTable.isEmpty();
//
//
//        System.out.println(signTable.get(new TestKey(2)));
//        System.out.println(signTable.toString0());


        /*****************基于二叉查找树测试*****************/

        SortedSignTable<TestKey, String> signTable = new BinaryTreeSearchSignTable<>();

        Random random = new Random();
        for (int i = 1; i < 9 ; i+=2) {
            int j = random.nextInt(50);
            signTable.put(new TestKey(j),"value"+j);
        }

//        TestKey min = signTable.min();
//        System.out.println(min);
//
//        TestKey max = signTable.max();
//        System.out.println(max);

//        for (int i = 1; i < 9 ; i+=2) {
//
//            String s = signTable.get(new TestKey(i));
//            System.out.println(s);
//        }

//        TestKey floor = signTable.floor(new TestKey(4));
//        System.out.println(floor==null? null:floor);
//
//        TestKey ceiling = signTable.ceiling(new TestKey(9));
//        System.out.println(ceiling);
//
//        TestKey select = signTable.select(2);
//        System.out.println(select);
//
//        int rank = signTable.rank(new TestKey(3));
//        System.out.println(rank);

//        signTable.deleteMax();
//        signTable.deleteMin();

        //signTable.delete(new TestKey());
        int size = signTable.size();
        for (int i = 0; i <size ; i++) {
            System.out.println(signTable.select(i));
        }

        Iterable<TestKey> keys = signTable.keys();
        Iterator<TestKey> iterator = keys.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

//        signTable.delete(signTable.select(2));
//        int size1 = signTable.size();
//        for (int i = 0; i <size1 ; i++) {
//            System.out.println(signTable.select(i));
//        }

    }
}
