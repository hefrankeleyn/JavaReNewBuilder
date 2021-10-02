package com.hef.dp;
import java.util.*;
/**
 * @Date 2021/10/2
 * @Author lifei
 */
public class UniquePathsIII {

    public static void main(String[] args) {
        UniquePathsIII uniquePaths = new UniquePathsIII();
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        int res = uniquePaths.uniquePathsIII(grid);
        System.out.println(res);
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    public int uniquePathsIII(int[][] grid) {
        int sx = 0, sy = 0;
        int ex = 0, ey = 0;
        int emptyNum = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                if (grid[i][j]==0) emptyNum++;
                else if (grid[i][j]==1) {
                    sx = i;
                    sy = j;
                }else if (grid[i][j]==2) {
                    ex = i;
                    ey = j;
                }
            }
        }
        List<HashSet<String>> res = new ArrayList<>();
        dfs(sx, sy, grid, 1, emptyNum+2, new HashSet<String>(), res);
        return res.size();
    }

    private void dfs(int sx, int sy, int[][] grid, int num, int emptyNum, HashSet<String> visitedSet, List<HashSet<String>> res) {
        if (grid[sx][sy]==-1 || visitedSet.contains(sx+""+sy)) {
            return;
        }
        if (grid[sx][sy]==2 && num==emptyNum) {
            res.add(visitedSet);
            return;
        }
        visitedSet.add(sx+""+sy);
        for (int k=0; k<dx.length; k++) {
            int x = sx+dx[k], y = sy+dy[k];
            if (x<0 || x>=grid.length || y<0 || y>=grid[0].length) {
                continue;
            }
            dfs(x, y, grid, num+1, emptyNum, visitedSet, res);
        }
        visitedSet.remove(sx+""+sy);
    }
}
