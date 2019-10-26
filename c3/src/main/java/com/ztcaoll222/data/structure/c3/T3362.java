package com.ztcaoll222.data.structure.c3;

import com.ztcaoll222.data.structure.base.entity.SeqElem;
import com.ztcaoll222.data.structure.c3.stack.SeqStack;

import java.util.Optional;

/**
 * 第三章栈和队列综合题
 *
 * @author ztcaoll222
 * Create time: 2019/10/26 7:57
 */
public class T3362 {
    private T3362() {
    }

    /**
     * 判断一段字符串中的 "([{}])" 是否匹配
     *
     * @param str 字符串
     * @return 是返回 true, 否则 false
     */
    public static boolean t1(String str) {
        SeqStack<Character> stack = new SeqStack<>(str.length());
        for (char c : str.toCharArray()) {
            Optional<SeqElem<Character>> elem;
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    elem = stack.pop();
                    if (elem.isEmpty() || !elem.get().getValue().equals('(')) {
                        return false;
                    }
                    break;
                case ']':
                    elem = stack.pop();
                    if (elem.isEmpty() || !elem.get().getValue().equals('[')) {
                        return false;
                    }
                    break;
                case '}':
                    elem = stack.pop();
                    if (elem.isEmpty() || !elem.get().getValue().equals('{')) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return stack.stackEmpty();
    }
}
