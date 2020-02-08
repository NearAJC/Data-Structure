package com.itnear.structure.tree.union_find;

import java.util.Random;

/**
 * 测试类
 */
public class TestUtil {
    private static double testUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 10000;
        int m = 10000;

        QuickFind qf = new QuickFind(size);
        System.out.println("QuickFind : " + testUF(qf, m) + " s");

        QuickUnion qu = new QuickUnion(size);
        System.out.println("QuickUnion : " + testUF(qu, m) + " s");

        QuickUnion1 qu1 = new QuickUnion1(size);
        System.out.println("QuickUnion1 : " + testUF(qu1, m) + " s");
    }
}
