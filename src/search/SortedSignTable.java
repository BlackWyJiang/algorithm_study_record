package search;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/15
// +----------------------------------------------------------------------
// | Time: 15:49
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------

/**
 * 有序符号表,他的key是有序排列的,所以应该支持一些有序api  如最大键,最小键,,指定第几个键等
 *
 * 所以要求此时的key应为可比较的即实现了Comparable接口
 *
 */
public interface SortedSignTable<Key extends Comparable<Key>,Value> extends SignTable<Key,Value> {

    //最小的key
    Key min();

    //最大的key
    Key max();

    //小于等于指定key的最大键  类似于向下取整
    Key floor(Key key);

    //大于等于指定key的最大键  类似于向上取整
    Key ceiling(Key key);

    //小于key的数量
    int rank(Key key);

    //排名为i的键
    Key select(int i);

    //删除最大
    default void deleteMax(){

        delete(max());
    }

    //删除最大
    default void deleteMin(){
        delete(min());
    }

    //两个key之间的数量,包括low和high
    default int size(Key low,Key high){
        if (low.compareTo(high) > 0) return 0;

        if (contains(high)) return rank(high) - rank(low) +1;

        return rank(high) - rank(low);

    }

    //两个key之间的所有key,包括low和high
    Iterable<Key> keys(Key low,Key high);

    default Iterable<Key> keys(){
        return keys(min(),max());
    }


}
