package mbdlabs.assigment.ass3;

import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Iterator;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Assignment 3:
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * <p>
 * https://leetcode.com/problems/two-sum/
 * <p>
 * What you might use from Vavr:
 * * Either (Left and Right)
 * * Stream
 * * Option
 */
public class TwoSumTest {


    /**
     * Imperative solution example
     */
    private static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    @Test
    void test_twoSum() {
        int[] actual = twoSum(new int[]{2, 7, 11, 15}, 9);
        int[] expected = {0, 1};
        assertThat(actual).isEqualTo(expected);
    }


    /**
     * TODO Implement twoSumFunctional in a functional style.
     * <p>
     * Hint - looks at:
     * * Stream
     * * zipWithIndex
     * * crossProduct
     *
     * <p>
     * return Left if there is no solution
     * return Right if there is a solution
     */
    private static Either<String, Tuple2<Integer, Integer>> twoSumFunctional(int[] nums, int target) {
        return null;
    }


    /**
     * TODO - make test pass
     */
    @Test
    void test_twoSumFunctional_left() {
        Either<String, Tuple2<Integer, Integer>> expected = Either.<String, Tuple2<Integer, Integer>>right(Tuple.of(0, 1));
        Either<String, Tuple2<Integer, Integer>> actual = twoSumFunctional(new int[]{2, 7, 11, 15}, 9);
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * TODO - make test pass
     */
    @Test
    void test_twoSumFunctional_right() {
        Either<String, Tuple2<Integer, Integer>> expected = Either.<String, Tuple2<Integer, Integer>>left("No two sum solution");
        Either<String, Tuple2<Integer, Integer>> actual = twoSumFunctional(new int[]{2, 7, 11, 15}, 10);
        assertThat(actual).isEqualTo(expected);
    }
}