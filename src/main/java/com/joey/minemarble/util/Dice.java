package com.joey.minemarble.util;

public class Dice {
    public int count;
    public int maxNum;
    public int minNum;

    public Dice(int count, int maxNum, int minNum) {
        this.count = count;
        this.maxNum = maxNum;
        this.minNum = minNum;
    }

    public int roll() {
        int sum = 0;

        for (int i = 0; i < count; i+=1) {
            int num = (int) (Math.random() * (maxNum - minNum + 1) + minNum);
            sum += num;
        }

        return sum;
    }
}
