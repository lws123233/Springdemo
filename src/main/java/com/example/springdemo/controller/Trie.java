package com.example.springdemo.controller;


import java.util.*;

public class Trie {
    private Map<String,Trie> children;
    private boolean isEnd;

    public Trie(){
        children=new HashMap<String,Trie>();
        isEnd=false;
    }

    public void insert(String word) {
        Trie node=this;
        for(int i=0;i<word.length();i++){
            String s=String.valueOf(word.charAt(i));
            if(null == node.children.get(s)){
                node.children.putIfAbsent(s,new Trie());
            }
            node=node.children.get(s);
        }
        node.isEnd=true;
    }

    public List<String> searchPre(String pre){
        Trie node=this;
        List<String> res=new ArrayList<>();
        for(int i=0;i<pre.length();i++){
            String s=String.valueOf(pre.charAt(i));
            if(node.children.get(s)!=null){
                node=node.children.get(s);
            }else{
                return null;
            }
        }
        dfs(node,pre,res);
        return res;
    }

    private void dfs(Trie node,String pre,List<String> res){
        Iterator<Map.Entry<String, Trie>> iterator = node.children.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String, Trie> next = iterator.next();
            pre=pre+next.getKey();
            if(next.getValue().isEnd) {
                res.add(pre);
            }
            dfs(next.getValue(),pre,res);

        }
    }

}
