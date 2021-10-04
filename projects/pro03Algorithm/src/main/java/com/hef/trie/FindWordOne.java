package com.hef.trie;

/**
 * @Date 2021/10/4
 * @Author lifei
 */
public class FindWordOne {

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCCED";
        FindWordOne findWordOne = new FindWordOne();
        boolean res = findWordOne.exist(board, word);
        System.out.println(res);
    }

    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};
    public boolean exist(char[][] board, String word) {
        // https://leetcode-cn.com/problems/word-search/
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                sb.append(board[i][j]);
                char c = board[i][j];
                board[i][j] = '#';
                boolean res = dfs(i, j, board, sb, word);
                board[i][j] = c;
                sb.deleteCharAt(sb.length()-1);
                if (res) {
                    return res;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, char[][] board, StringBuilder sb, String word) {
        if (!word.startsWith(sb.toString())) {
            return false;
        }
        if (word.equals(sb.toString())) {
            return true;
        }

        for (int k=0; k<dx.length; k++) {
            int x = i + dx[k], y = j + dy[k];
            if (x<0 || x>=board.length || y<0 || y>=board[0].length || board[x][y]=='#') {
                continue;
            }
            sb.append(board[x][y]);
            char c = board[x][y];
            board[x][y] = '#';
            boolean res = dfs(x, y, board, sb, word);
            board[x][y] = c;
            sb.deleteCharAt(sb.length()-1);
            if (res) {
                return res;
            }
        }
        return false;
    }
}
