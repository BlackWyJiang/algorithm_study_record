package sort.test;

import sort.PriorityQueue;

import java.util.Random;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/14
// +----------------------------------------------------------------------
// | Time: 15:31
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------
public class PriorityQueueTest {

    public static void main(String[] args) {

        Integer[] test = new Integer[5];

        Random random = new Random();

        PriorityQueue<Integer> queue = new PriorityQueue<>(test.length);

        for (int i = 0; i < test.length +1; i++) {
            queue.insert(random.nextInt(100));
        }

        for (int i = 0; i < test.length+1; i++) {
            System.out.println(queue.getMax());
        }
    }
}
