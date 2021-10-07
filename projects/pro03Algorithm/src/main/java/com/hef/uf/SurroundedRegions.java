package com.hef.uf;

import java.util.*;
/**
 * @Date 2021/10/7
 * @Author lifei
 */
public class SurroundedRegions {

    public static void main(String[] args) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        char[][] board = {
                {'O','O','O','O','X','X'},
                {'O','O','O','O','O','O'},
                {'O','X','O','X','O','O'},
                {'O','X','O','O','X','O'},
                {'O','X','O','X','O','O'},
                {'O','X','O','O','O','O'}};
        surroundedRegions.solve(board);
        System.out.println(board);
    }

    private boolean flag = true;
    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j]=='X') continue;
                flag = true;
                List<int[]> xyList = new ArrayList<>();
                dfs(i, j, board, xyList);
                if (!flag) {
                    for (int[] a : xyList) {
                        board[a[0]][a[1]]='O';
                    }
                }
            }
        }
    }

    private void dfs(int i, int j, char[][] board, List<int[]> xyList) {
        if (board[i][j]=='X' || flag==false) return;
        if (i==0 || i==board.length-1 || j==0 || j==board[0].length-1 ) {
            flag = false;
            return;
        }
        board[i][j] = 'X';
        xyList.add(new int[]{i, j});
        for (int k=0; k<dx.length; k++) {
            int x = i + dx[k], y = j + dy[k];
            if (x<0 || x>=board.length || y<0 || y>=board[0].length || board[x][y]=='X') continue;
            dfs(x, y, board, xyList);
        }
    }
}
