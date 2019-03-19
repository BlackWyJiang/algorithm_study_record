package search.test;

import java.util.Iterator;
import java.util.Objects;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/15
// +----------------------------------------------------------------------
// | Time: 17:06
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------
public class TestKey implements Comparable<TestKey> {

    private int key;

    public TestKey(int key){

        this.key = key;
    }

    @Override
    public int compareTo(TestKey o) {
        if (o == null) return -1;
        return this.key - o.key;
    }

    @Override
    public boolean equals(Object o) {
       if (o instanceof TestKey){
           if (this.key == ((TestKey) o).key)return true;
       }
       return false;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }
}
