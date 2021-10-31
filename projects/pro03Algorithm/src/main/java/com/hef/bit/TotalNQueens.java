package com.hef.bit;

/**
 * @Date 2021/10/31
 * @Author lifei
 */
public class TotalNQueens {

    public static void main(String[] args) {
        TotalNQueens totalNQueens = new TotalNQueens();
        int n = 4;
        int res = totalNQueens.totalNQueens(n);
        System.out.println(res);
    }

    private int count;
    public int totalNQueens(int n) {
        solve(0, n, 0, 0, 0);
        return count;
    }

    private void solve(int row, int n, int lies, int pies, int nas) {
        if (row>=n) {
            count += 1;
            return;
        }
        // 可以放皇后的位置
        int p = (~(lies|pies|nas)) & ((1<<n)-1);
        if (p!=0) {
            // 取最低位的1
            int k = p & (-p);
            // 打掉最低位的1, 意味着放上皇后
            p = p & (p-1);
            solve(row+1, n, lies|k, (pies|k)<<1, (nas|k)>>1);
        }
    }
}
