package com.example.springdemo.controller;

import java.util.*;

public class Suanfa {

    static Map<String,String[]> map=new HashMap<>();
    static List<String> res=new ArrayList<>();
    static List<List<String>> res1=new ArrayList<>();
    static List<String> temp=new ArrayList<>();

    static List<String> strs=new ArrayList<>();
    static Set<Integer> set=new HashSet<>();
    static Set<Integer> set2=new HashSet<>();
    static Set<Integer> set3=new HashSet<>();
    static String str="";
    public static void main(String[] args) {

        //电话的排列组合
        //letterCombinations("23");
        //generateParenthesis(3);
        //partition("aab");
        solveNQueens(4);
    }

    public static List<List<String>> solveNQueens(int n) {
        for(int i=0;i<n;i++){
            str=str+".";
        }
        dfs(0,n);
        return res1;
    }

    public static void dfs(int cur ,int n){
        if(cur==n){
            if(set.size()==n){
                res1.add(new ArrayList<>(strs));
            }
            return;
        }
        for(int j=0;j<n;j++){
            //检查某一列被选过没,检查该列是不是在上一行的斜角
            if(set.contains(j) || set2.contains(cur-j) || set3.contains(cur+j)){
                //不能选就检查下一列
                continue;
            }else{
                //能选
                //记录选中的列
                set.add(j);
                //记录选中的斜
                set2.add(cur-j);
                set3.add(cur+j);
                //记录结果
                strs.add(new String(str.substring(0,j)+"Q"+str.substring(j+1,n)));
                //计算下一行
                dfs(cur+1,n);
                set.remove(j);
                set2.remove(cur-j);
                set3.remove(cur+j);
                strs.remove(strs.size()-1);
            }
        }
    }


    public static List<List<String>> partition(String s) {
        dfs(0,s);
        return res1;
    }

    public  static void dfs(int cur,String s){

        if(cur == s.length()){
            res1.add(new ArrayList<>(temp));
            return;
        }

        for(int i=cur;i<s.length();i++){
            //不是回文,判断下一个右边界
            if(!check(s,cur,i)){
                continue;
            }
            temp.add(s.substring(cur,i+1));
            //是回文，左边界收缩，判读子串有没有回文
            dfs(i+1,s);
            temp.remove(temp.size()-1);
        }
    }

    public static boolean check(String s,int left,int right){
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static List<String> generateParenthesis(int n) {
        String str="()";
        dfs(1,n,str,1,1);
        return res;
    }
    public static void dfs(int start, int n, String str, int left, int right){
        if(right==n && left==n){
            res.add(str);
            return;
        }
        String temp=str;
        if(left<n){
            //先尝试选左括号
            str=str.substring(0,str.length()-1)+"("+")";
            dfs(start+1,n,str,left+1,right);
        }

        if(right<n && right-1<left){
            //再尝试右括号
            str=temp;
            str=str.substring(0,str.length()-1)+")"+")";
            dfs(start+1,n,str,left,right+1);
        }
        str=temp;

    }


    public static List<String> letterCombinations(String digits) {
        if(digits.length()==0){
            return res;
        }
        map.put("2",new String[]{"a","b","c"});
        map.put("3",new String[]{"d","e","f"});
        map.put("4",new String[]{"g","h","i"});
        map.put("5",new String[]{"j","k","l"});
        map.put("6",new String[]{"m","n","o"});
        map.put("7",new String[]{"p","q","r","s"});
        map.put("8",new String[]{"t","u","v"});
        map.put("9",new String[]{"w","x","y","z"});

        dfs(0,digits,"");
        return res;
    }

    public static void dfs(int cur, String digits, String pre){
        if(pre.length()==digits.length() || cur==digits.length()){
            res.add(pre);
            return;
        }
        //选中当前数字
        String s=String.valueOf(digits.charAt(cur));

        String[] strings=map.get(s);
        //选中当前数字对应的字母集合的某个字母
        for(String str : strings){
            String temp=pre;
            pre=pre+str;
            //在下一个数字中选一个字母
            dfs(cur+1,digits,pre);
            pre=temp;
        }

    }
}
