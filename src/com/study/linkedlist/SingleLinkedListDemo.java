package com.study.linkedlist;

import java.util.Queue;
import java.util.Stack;

/**
 * @author meng
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(node1);
//        singleLinkedList.add(node2);
//        singleLinkedList.add(node3);
//        singleLinkedList.add(node4);

        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node4);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.list();

        HeroNode node5 = new HeroNode(3, "无用", "小废物");
        singleLinkedList.update(node5);
        System.out.println("更新节点后：");
        singleLinkedList.list();

        singleLinkedList.del(1);
        System.out.println("删除节点后：");
        singleLinkedList.list();

        singleLinkedList.addByOrder(node1);
        System.out.println("添加节点后：");
        singleLinkedList.list();

        // 链表有效长度
        System.out.println("length = " + getLength(singleLinkedList.getHead()));

        singleLinkedList.list();
        int index = 2;
        System.out.printf("倒数第 %d 个, %s", index, findFastIndexNode(singleLinkedList.getHead(), index));

        singleLinkedList.list();
        System.out.println("反转链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();


        System.out.println("逆序打印");
        reversePrint(singleLinkedList.getHead());


        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "武松", "行者");
        HeroNode hero6 = new HeroNode(6, "鲁智深", "花和尚");
        HeroNode hero7 = new HeroNode(7, "李逵", "黑旋风");
        HeroNode hero8 = new HeroNode(8, "杨志", "青面兽");
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        SingleLinkedList singleLinkedList3 = new SingleLinkedList();
        singleLinkedList2.add(hero1);
        singleLinkedList2.add(hero3);
        singleLinkedList2.add(hero5);
        singleLinkedList2.add(hero8);
        singleLinkedList3.add(hero2);
        singleLinkedList3.add(hero4);
        singleLinkedList3.add(hero6);
        singleLinkedList3.add(hero7);

        System.out.println("1");
        singleLinkedList2.list();
        System.out.println("2");
        singleLinkedList3.list();
        System.out.println("合");
        HeroNode heroNode = mergeList(singleLinkedList2.getHead(), singleLinkedList3.getHead());
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(heroNode);
        linkedList.list();


    }

    /**
     * 获取链表有效数据长度
     *
     * @param head 链表
     * @return 长度
     */
    public static int getLength(HeroNode head) {

        if (head.next == null) {
            return 0;
        }
        // 统计链表长度
        int length = 0;
        // 不统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 获取倒数第 k 个节点
     *
     * @param head  链表头节点
     * @param index 倒数第 k 个
     * @return 节点
     */
    public static HeroNode findFastIndexNode(HeroNode head, int index) {

        if (head.next == null) {
            return null;
        }

        // 获取有效数据长度
        // 思路
        // 1. 先获取总长度 size
        // 2. 倒数第 index 个 也就是正数第 (size - index) 个
        // 3. 遍历链表 遍历到 (size - index)
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        // 拿到第一个节点
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }

        return cur;

    }

    /**
     * 将单链表进行反转
     *
     * @param head
     * @return
     */
    public static void reverseList(HeroNode head) {
        // 没有数据，或者只有一个数据，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 获取当前节点
        HeroNode cur = head.next;
        // 存放下一个节点
        HeroNode next = null;
        // 新节点，头插法反转链表
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (cur != null) {
            // 先保存下一个节点
            next = cur.next;
            // 当前节点指向新创建的链表的第一个节点
            cur.next = reverseHead.next;
            // 将 cur 连接到新的链表上
            reverseHead.next = cur;
            // 把存放的下一个节点变成当前节点
            cur = next;
        }
        // 把原来的头节点指向反转头节点的下一个节点
        head.next = reverseHead.next;
    }

    /**
     * 逆序打印
     *
     * @param head 头节点
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        HeroNode cur = head.next;
        // 使用 栈 ， 先进后出
        Stack<HeroNode> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并连个链表，有序
     *
     * @param target 第一个链表
     * @param source 第二个链表
     */
    public static HeroNode mergeList(HeroNode target, HeroNode source) {
        HeroNode mergeHead = new HeroNode(0, "", "");
        if (target.next == null && source.next == null) {
            return null;
        }
        if (target.next != null && source.next == null) {
            mergeHead.next = target.next;
            return mergeHead;
        }
        if (target.next == null) {
            mergeHead.next = source.next;
            return mergeHead;
        }
        // 思路
        // 1. 把 cur 节点有序放入 temp 节点中 ，一般是小放大
        // 2. 取 cur 节点对比 temp 中的每一个节点
        // 3. 如果 cur.no < temp.no 就放在前面，否则比较下一个
        HeroNode temp = target.next;
        HeroNode cur = source.next;
        HeroNode next = null;
        while (temp != null && cur != null) {
            if (temp.no <= cur.no) {
                next = temp.next;
                temp.next = mergeHead.next;
                mergeHead.next = temp;
                temp = next;
            } else {
                next = cur.next;
                cur.next = mergeHead.next;
                mergeHead.next = cur;
                cur = next;
            }
        }
        if (temp != null) {
            temp.next = mergeHead.next;
            mergeHead.next = temp;
        } else {
            cur.next = mergeHead.next;
            mergeHead.next = cur;
        }
        return mergeHead.next;
    }
    // h 1 2 4 5
    // h 3 6 8 9 11
    // 1 < 3   1 -> 3  h -> 1

}

class SingleLinkedList {

    /**
     * 初始化头节点
     */
    private final HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (temp.next != null) {
            // 找到最后一个节点
            // 没找到, 后移一下
            temp = temp.next;
        }
        // 最后一个节点指向新加入的节点
        temp.next = heroNode;
    }

    /**
     * 顺序添加，并且不能已存在编号不能覆盖
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        // 添加一个标识flag,编号是否存在
        boolean flag = Boolean.FALSE;

        // 找插入节点位置
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 位置找到了
            if (temp.next.no > heroNode.no) {
                break;
                // 编号已存在
            } else if (temp.next.no == heroNode.no) {
                flag = Boolean.TRUE;
                break;
            }
            // 后移
            temp = temp.next;
        }

        // 插入节点
        if (flag) {
            System.out.printf("编号%d已存在，不能再添加\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }


    }

    public void update(HeroNode newHeroNode) {
        HeroNode temp = head;
        // 判断是否可以修改节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == newHeroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }
        if (flag) {
            temp.next.name = newHeroNode.name;
            temp.next.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("更新节点 %d 不存在\n", newHeroNode.no);
        }
    }

    public void del(int no) {
        HeroNode temp = head;
        // 判断节点是否可以删除
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 判断节点是否存在
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 可以删除
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("删除节点 %d 失败\n", no);
        }
    }


    /**
     * 遍历链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        // 头节点的下一个节点，即第一个节点
        HeroNode temp = head.next;
        while (temp != null) {
            // 判断到节点是不是空的
            System.out.println(temp);
            // 将next后移
            temp = temp.next;
        }
    }


}

class HeroNode {
    public int no;

    public String name;

    public String nickName;

    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}