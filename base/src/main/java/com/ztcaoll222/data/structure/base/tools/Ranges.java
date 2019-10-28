package com.ztcaoll222.data.structure.base.tools;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author ztcaoll222
 * Create time: 2019/10/28 16:28
 */
public class Ranges {
    private Ranges() {
    }

    private static final boolean DEFAULT_SYNC = true;

    private static final int DEFAULT_STEP = 1;

    /**
     * the {@code Supplier} of generated elements
     *
     * @param start 计数从 start 开始
     * @param end   计数到 stop 结束
     * @param step  步长
     */
    private static Supplier<Integer> generateSeed(final Integer start, final Integer end, final Integer step) {
        return new Supplier<>() {
            private AtomicInteger next = new AtomicInteger(start);

            @Override
            public Integer get() {
                return next.getAndUpdate(cur -> {
                    var nw = cur + step;
                    if (nw > end) {
                        return end;
                    } else {
                        return nw;
                    }
                });
            }
        };
    }

    private static Stream<Integer> rangeInt(Supplier<Integer> seed, Integer length, boolean sync) {
        return sync ? Stream.generate(seed).limit(length) : Stream.generate(seed).parallel().limit(length);
    }

    /**
     * 生成某个范围内的数组列表
     *
     * @param start 计数从 start 开始
     * @param end   计数到 stop 结束
     * @param step  步长
     * @param sync  是否为同步
     *              <p>
     *              {@<code>
     *              >> rangeInt(0, 35, 20, false).forEach(System.out : : println)
     *              <p>
     *              >> 0, 20
     *              </code>}
     */
    public static Stream<Integer> rangeInt(final Integer start, final Integer end, final Integer step, boolean sync) {
        return rangeInt(generateSeed(start, end, step), (int) Math.ceil((end - start) * 1.0 / step), sync);
    }

    /**
     * 生成同步的某个范围内的数组列表
     *
     * @param start 计数从 start 开始
     * @param end   计数到 stop 结束
     * @param step  步长
     *              <p>
     *              {@<code>
     *              >> rangeInt(0, 35, 20).forEach(System.out : : println)
     *              <p>
     *              >> 0, 20
     *              </code>}
     */
    public static Stream<Integer> rangeInt(final Integer start, final Integer end, final Integer step) {
        return rangeInt(generateSeed(start, end, step), (int) Math.ceil((end - start) * 1.0 / step), DEFAULT_SYNC);
    }

    /**
     * 生成同步的步长为 1 的某个范围内的数组列表
     *
     * @param start 计数从 start 开始
     * @param end   计数到 stop 结束
     *              <p>
     *              {@<code>
     *              >> rangeInt(0, 35).forEach(System.out : : println)
     *              <p>
     *              >> 0, 1, 2....
     *              </code>}
     */
    public static Stream<Integer> rangeInt(final Integer start, final Integer end) {
        return rangeInt(start, end, DEFAULT_STEP, DEFAULT_SYNC);
    }
}
