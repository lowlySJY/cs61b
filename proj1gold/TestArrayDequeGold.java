import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    private void randomAdd(StudentArrayDeque<Integer> SAD1, ArrayDequeSolution<Integer> ADS1, Integer i) {
        double numberBetweenZeroAndOne = StdRandom.uniform();
        if (numberBetweenZeroAndOne < 0.5) {
            SAD1.addLast(i);
            ADS1.addLast(i);
        } else {
            SAD1.addFirst(i);
            ADS1.addFirst(i);
        }
    }

    private void randomRemove(StudentArrayDeque<Integer> SAD1, ArrayDequeSolution<Integer> ADS1) {
        double numberBetweenZeroAndOne = StdRandom.uniform();
        Integer s1;
        Integer a1;
        if (numberBetweenZeroAndOne < 0.5) {
            s1 = SAD1.removeLast();
            a1 = ADS1.removeLast();
        } else {
            s1 = SAD1.removeFirst();
            a1 = ADS1.removeFirst();
        }

        assertEquals(s1, a1);
    }


    @Test
    public void testTask1() {
        StudentArrayDeque<Integer> SAD1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ADS1 = new ArrayDequeSolution<>();

        for(Integer i = 0; i < 100; i += 1) {
            if(SAD1.isEmpty()) {
                randomAdd(SAD1, ADS1, i);
            } else {
                double random = StdRandom.uniform();
                if(random < 0.5) {
                    randomAdd(SAD1, ADS1, i);
                } else {
                    randomRemove(SAD1, ADS1);
                }
            }
        }
    }
}
