package computationalDemo;

import java.util.Stack;

/**
 *给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


public class Solution {
    // 创建一个栈
    Stack<Character> stack=new Stack<>();
    public boolean isValid(String s){
        // 遍历这个字符串
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            // 如果该元素为朝向右边的括号，就存入栈中
            if(c=='('||c=='{'||c=='['){
                stack.push(c);
            }else{
                // 如果不是可以直接看该栈是否为空，若为空直接返回false
                if(stack.isEmpty()){
                    return false;
                }
                char topChar=stack.pop();
                if(c==')'&&topChar!='(')
                    return false;
                if(c=='}'&&topChar!='{')
                    return false;
                if(c==']'&&topChar!='{')
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
