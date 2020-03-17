import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class SolutionTest {

    private static Logger logger = LoggerFactory.getLogger(SolutionTest.class);

    @Test
    public void test() {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        Stream.iterate(0, i -> i + 1).limit(5).parallel().forEach(i -> map.put(i, i));
        logger.debug("{}", map);

        map.entrySet().clear();
        logger.debug("{}", map);
    }

    @Test
    public void test1() {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 30, 40, 5));

        logger.debug("{}", numbers);
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 30) {
                iterator.remove(); // ok!
            }
        }
        logger.debug("{}", numbers);

        iterator = numbers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 40) {
                numbers.remove(2); // exception
            }
        }
        logger.debug("{}", numbers);
    }

    @Test
    public void test2() {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 30, 40, 5));
        logger.debug("{}", numbers);

        for (Integer number : numbers) {
            numbers.remove(number);
        }
        logger.debug("{}", numbers);
    }

    @Test
    public void test3() {
        Map<Integer, Integer> map = new LinkedHashMap<>(16, .75f, true);
        Stream.iterate(0, i -> i + 1).limit(5).forEach(i -> map.put(i, i));
        logger.debug("{}", map);


    }

}
