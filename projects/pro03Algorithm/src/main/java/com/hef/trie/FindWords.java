package com.hef.trie;

import java.util.*;
/**
 * @Date 2021/10/4
 * @Author lifei
 */
public class FindWords {

    public static void main(String[] args) {
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        FindWords findWords = new FindWords();
        List<String> res = findWords.findWords(board, words);
        System.out.println(res);
    }

    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word: words) {
            trie.insert(word);
        }

        Set<String> resList = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                sb.append(board[i][j]);
                char c = board[i][j];
                board[i][j] = '#';
                dfs(board, i, j, trie, sb, resList);
                sb.deleteCharAt(sb.length()-1);
                board[i][j] = c;
            }
        }
        return new ArrayList<>(resList);
    }

    private void dfs(char[][] board, int i, int j, Trie trie, StringBuilder sb, Set<String> resList) {
        if (!trie.findPrefix(sb.toString())) {
            return;
        }
        if (trie.findWord(sb.toString())) {
            resList.add(sb.toString());
            return;
        }
        for (int k=0; k<dx.length; k++) {
            int x = i+dx[k], y = j + dy[k];
            if (x<0 || x>=board.length || y<0 || y>=board[0].length || board[x][y]=='#') {
                continue;
            }
            sb.append(board[x][y]);
            char c = board[x][y];
            board[x][y] = '#';
            dfs(board, x, y, trie, sb, resList);
            sb.deleteCharAt(sb.length()-1);
            board[x][y] = c;
        }

    }

    private class Trie{
        private Trie[] tries;
        private static final int R = 26;
        private boolean end;
        public Trie() {
            tries = new Trie[R];
        }

        public void insert(String word) {
            Trie x = this;
            for (int i=0; i<word.length(); i++) {
                if (!x.containsTrie(word.charAt(i))) {
                    x.setTrie(word.charAt(i), new Trie());
                }
                x = x.getTrie(word.charAt(i));
            }
            x.setEnd();
        }

        public boolean findPrefix(String prefix) {
            return searchPrefix(prefix)!=null;
        }

        public boolean findWord(String word) {
            Trie t = searchPrefix(word);
            return t!=null && t.isEnd();
        }

        private Trie searchPrefix(String prefix) {
            Trie x = this;
            for (int i=0; i<prefix.length(); i++) {
                if (x.containsTrie(prefix.charAt(i))) {
                    x = x.getTrie(prefix.charAt(i));
                }else {
                    return null;
                }
            }
            return x;
        }

        private boolean isEnd() {
            return end;
        }

        private void setEnd() {
            end = true;
        }

        private void setTrie(char c, Trie node) {
            tries[c-'a'] = node;
        }

        private boolean containsTrie(char c) {
            return getTrie(c)!=null;
        }

        private Trie getTrie(char c) {
            return tries[c-'a'];
        }
    }
}
