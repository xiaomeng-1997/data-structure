package com.study.queue;

import java.util.Scanner;

/**
 * @author meng
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列获取数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int date = scanner.nextInt();
                    queue.addQueue(date);
                    break;
                case 'g':

                    try {
                        System.out.println(queue.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println(queue.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                default:
                    scanner.close();
                    break;
            }
        }
        System.out.println("已退出");
    }
}

class ArrayQueue {
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 存放数据的数组
     */
    private int[] arr;
    /**
     * 队列大小
     */
    private final int maxSize;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    /**
     * 判断是否为空
     */
    public Boolean isEmpty() {
        return rear == front;
    }

    /**
     * 判断是否已满
     */
    public Boolean isFull() {
        return rear == maxSize - 1;
    }


    /**
     * 队列增加数据
     *
     * @param date 添加的数据
     */
    public void addQueue(int date) {
        // 判断数组是否已经装满
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[++rear] = date;

    }


    /**
     * 出队列
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }

        int date = arr[++front];
        arr[front] = 0;
        return date;

    }

    /**
     * 显示队列所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    /**
     * @return 队列头部元素
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[front + 1];
    }

}