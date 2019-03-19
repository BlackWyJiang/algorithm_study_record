package search;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/15
// +----------------------------------------------------------------------
// | Time: 15:49
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------


import java.util.Iterator;

/**
 * 符号表,一种储存键值对的抽象数据结构,定义了一些常用方法,最基本的get() put() delete()
 */
public interface SignTable<Key,Value> {

    //插入或者修改其中元素,如果插入null  则删除
    void put(Key key,Value value);

    //根据key获取元素,没有则返回空
    Value get(Key key);

    //表中元素的数量
    int size();

    //表中所有的key  迭代器返回
    Iterable<Key> keys();

    //默认实现  删除key方式
    default void delete(Key key){

        put(key,null);
    }

    //默认实现  当前表中是否包含某个key
    default boolean contains(Key key){

        return get(key) == null;
    }

    //默认实现 表是否为空
    default boolean isEmpty(){
        return size() == 0;
    }

default String toString0(){
    StringBuilder stringBuilder = new StringBuilder();

    Iterable<Key> keys = keys();
    Iterator<Key> iterator = keys.iterator();

    while (iterator.hasNext()){
        Key next = iterator.next();
        stringBuilder.append(next.toString()).append("=>").append(get(next)).append("\r\n");
    }

    return stringBuilder.toString();
}


}
