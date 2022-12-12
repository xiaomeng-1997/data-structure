package com.study.linkedlist;

/**
 * 约瑟夫问题
 *
 * @author meng
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(20);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(2, 3, 20);
    }
}

class CircleSingleLinkedList {

    /**
     * 创建first节点，没有编号
     */
    private Boy first = null;

    /**
     * 初始化，一共有几个小孩
     *
     * @param nums 孩子数量
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("num 数值不对");
            return;
        }

        // 定义辅助变量
        Boy curBoy = null;
        // 循环创建节点
        for (int i = 1; i <= nums; i++) {
            // 按编号创建
            Boy boy = new Boy(i);
            // 如果是第一个
            if (i == 1) {
                first = boy;
                // 构成环状
                first.setNext(first);
                curBoy = first;
            } else {
                boy.setNext(first);
                curBoy.setNext(boy);
                curBoy = boy;
            }
        }
    }

    /**
     * 显示所有孩子
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        Boy helper = first;
        // 找出最后一个节点
        while (true) {
            System.out.printf("当前的孩子编号是: %d\n", helper.getNo());
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
    }

    /**
     * 计算小孩出圈顺序
     *
     * @param startNo  表示从第几个孩子数
     * @param countNum 表示数几下
     * @param nums     表示最初有几个孩子在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("输入参数有误");
            return;
        }
        // 指向最后一个孩子节点
        Boy helper = first;

        // 找到最后一个孩子节点
        while (helper.getNext() != first) {
            // 说明helper是最后一个节点
            helper = helper.getNext();
        }
        // 小孩报数钱先让发 first 和 helper 后移 startNo - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 当小孩报数时，让 first 和 helper 同时后移 countNum - 1 次，然后出圈
        while (helper != first) {
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("出圈孩子编号是：%d\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈里的孩子编号是：%d\n", first.getNo());
    }


}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}