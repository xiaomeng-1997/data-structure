package com.study.stack;

/**
 * @author meng
 */
public class Calculate {
    public static void main(String[] args) {
        String expression = "30+7+2*6-2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operatorStack = new ArrayStack2(10);
        // 用于扫描 expression
        int index = 0;
        int num1;
        int num2;
        int operator;
        int res;
        // 解决多位数运算
        StringBuilder keepNum = new StringBuilder();
        // 暂存 expression 的每个字符
        char ch = ' ';
        // 扫描 expression
        do {
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断是不是符号
            if (operatorStack.isOperator(ch)) {
                if (!operatorStack.isEmpty()) {
                    // 如果符号栈不为空，就进行比较，如果 当前的操作符 小于或者等于栈中的操作符
                    // pop出这个符号，进行运算，得到结果在入栈，然后在将 当前的操作符 入栈
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        res = numStack.cal(num1, num2, operator);
                        numStack.push(res);
                        operatorStack.push(ch);
                    } else {
                        operatorStack.push(ch);
                    }
                } else {
                    operatorStack.push(ch);
                }
            } else {
                // 不是 符号
                keepNum.append(ch);
                // 判断 index 是不是最后一位
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(String.valueOf(keepNum)));
                } else {
                    // 不能只获取一个字符,如果下一个字 符为运算符, 那就放入竖栈
                    if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(String.valueOf(keepNum)));
                        // 清空 keepNum
                        keepNum.delete(0, keepNum.length());
                    }
                }
            }
            index++;
        } while (index < expression.length());

        // 当表达式扫描完毕， 符号栈只剩下符号优先级最低的符号，此时 扫描 符号栈 进行计算
        while (!operatorStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            res = numStack.cal(num1, num2, operator);
            numStack.push(res);
        }
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d\n", expression, res2);
    }
}


/**
 * 用数组模拟栈
 */
class ArrayStack2 {
    /**
     * 栈的容量
     */
    public int maxSize;
    /**
     * 栈的数据
     */
    public int[] stack;
    /**
     * 栈顶
     */
    public int top = -1;

    /**
     * 初始化栈
     *
     * @param maxSize 栈的大小
     */
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈满
     *
     * @return true -- 满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 查看栈顶数据
     *
     * @return int
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 栈空
     *
     * @return true -- 空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法添加！！");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     *
     * @return value
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法取出数据！！");
        }
        int value = stack[top];
        stack[top] = 0;
        top--;
        return value;
    }


    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无法取出数据！！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    /**
     * 计算优先级
     *
     * @param operator 符号
     * @return *,/  = 1 ; +,- = 0 ; other = -1
     */
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;

        } else {
            return -1;
        }
    }


    /**
     * 判断是不是运算符
     *
     * @param val 运算符
     * @return is - true/not - false
     */
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }


    /**
     * 计算方法
     *
     * @param num1     栈顶数
     * @param num2     栈顶下一个
     * @param operator 运算符
     * @return 计算结果
     */
    public int cal(int num1, int num2, int operator) {
        int res = 0;
        switch (operator) {
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            default:
                break;
        }

        return res;
    }


}
