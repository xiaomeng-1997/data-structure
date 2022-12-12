package com.study.queue;

import java.util.Scanner;

/**
 * @author meng
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
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
                    try {
                        queue.addQueue(date);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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

class CircleArrayQueue {
    /**
     * 队列头 队列第一个元素
     */
    private int front;
    /**
     * 队列尾 队列最后一个元素
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

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
    }

    public Boolean isEmpty() {
        return front == rear;
    }

    public Boolean isFull() {
        return (rear + 1) % maxSize == front;
    }


    public void addQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        arr[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * @return 队列头部元素
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[front];
    }

    public void showQueue() {
        System.out.println(front + "  " + rear);
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }

    }

    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

}