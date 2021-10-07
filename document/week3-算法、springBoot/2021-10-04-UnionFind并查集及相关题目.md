# Union-Find 并查集

[toc]

## 一、Union-Find的实现

### 1.0 UF

```
public interface UF {

    /**
     * 朋友圈的个数
     * @return
     */
    int count();

    /**
     * 将p和 q 归为同一个朋友圈
     * @param p
     * @param q
     */
    void union(int p, int q);

    /**
     * 查找p所在的朋友圈
     * @param p
     * @return
     */
    int find(int p);

    /**
     * 判断 p 和 q 是否在相同的朋友圈
     * @param p
     * @param q
     * @return
     */
    boolean connected(int p, int q);

}
```



### 1.1 第一种实现：Quick-Find

```
public class QuickFind implements UF{

    private int count;
    private int[] id;

    public QuickFind(int N) {
        this.count = N;
        this.id = new int[N];
        for (int i=0; i<N; i++) {
            this.id[i] = i;
        }
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid==qid) return;

        for (int i=0; i<id.length; i++) {
            if (id[i]==pid) {
                id[i] = qid;
            }
        }
        count--;
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p)==find(q);
    }
    
}
```

### 1.2 第二种实现：Quick-Union

极端情况下会连成一条线

```
/**
 * @Date 2021/10/4
 * @Author lifei
 */
public class QuickUnionUF implements UF{
    
    private int count;
    private int[] id;
    public QuickUnionUF(int N) {
        this.count = N;
        this.id = new int[N];
        for (int i=0; i<N; i++) {
            id[i] = i;
        }
    }
    
    
    @Override
    public int count() {
        return count;
    }

    @Override
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        
        if (pid==qid) return;
        id[pid] = qid;
        count--;
    }

    @Override
    public int find(int p) {
        while (p!=id[p]) p = id[p];
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p)==find(q);
    }
}
```

### 1.3 第三种实现：Weighted-Quick-Union

```
public class WeightedQuickUnionUF implements UF{
    
    private int count;
    private int[] id;
    private int[] sz;
    
    public WeightedQuickUnionUF(int count) {
        this.count = count;
        this.id = new int[count];
        this.sz = new int[count];
        for (int i=0; i<count; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    @Override
    public int count() {
        return count;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        
        if (pRoot == qRoot) return;
        
        if (sz[pRoot]>sz[qRoot]) { id[qRoot] = pRoot; sz[pRoot] += sz[qRoot];}
        else { id[pRoot] = qRoot; sz[qRoot] += sz[pRoot]; }
        
        count--;
    }

    @Override
    public int find(int p) {
        while (p!=id[p]) p = id[p];
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p)==find(q);
    }
}
```

## 二、经典题目

### 2.1 [547. 省份数量](https://leetcode-cn.com/problems/number-of-provinces/)

#### 解法一：并查集

```
    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        for (int i=0; i<isConnected.length; i++) {
            for (int j=0; j<isConnected.length; j++) {
                if (isConnected[i][j]==1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count();
    }

    class WeightedQuickUnionUF {
        private int[] id;
        private int[] sz;
        private int count;

        public WeightedQuickUnionUF(int N) {
            this.count = N;
            this.id = new int[N];
            this.sz = new int[N];
            for (int i=0; i<N; i++) {
                this.id[i] = i;
                this.sz[i] = 1;
            }
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            while(p!=id[p]) p = id[p];
            return p;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot==qRoot) return;

            if (sz[pRoot]>sz[qRoot]) { id[qRoot] = pRoot; sz[pRoot] += sz[pRoot]; }
            else                     { id[pRoot] = qRoot; sz[qRoot] += sz[pRoot]; }
            count--;
        }

        public boolean connected(int p, int q) {
            return find(p)==find(q);
        }
    }
```

### 2.2 [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)

#### 解法一：并查集

```
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        WeightedQuickUnion uf = new WeightedQuickUnion(m*n);
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int zeroNum = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j]=='0') zeroNum++;
                int p = i * n + j;
                for (int k=0; k<dx.length; k++) {
                    int x = i+dx[k], y = j + dy[k];
                    if (x<0 || x>=m || y<0 || y>=n) continue;
                    int q = x * n + y;
                    if (grid[i][j]=='1' && grid[x][y]=='1') {
                        uf.union(p, q);
                    }
                }
            }
        }
        return uf.count()-zeroNum;
    }

    private class WeightedQuickUnion{
        private int count;
        private int[] id;
        private int[] sz;

        public WeightedQuickUnion(int count) {
            this.count = count;
            this.id = new int[count];
            this.sz = new int[count];
            for (int i=0; i<count; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            while(p!=id[p]) p = id[p];
            return p;
        }

        public void union(int p, int q) {
            int proot = find(p);
            int qroot = find(q);

            if (proot == qroot) return;
            if (sz[proot]>sz[qroot]) { id[qroot] = proot; sz[proot] += sz[qroot];  }
            else                     { id[proot] = qroot; sz[qroot] += sz[proot];  }
            count--;
        }


    }
```



### 2.3 [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)

#### 解法一：DFS

```
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
```

#### 解法二：并查集

```
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(m*n+1);
        int f = m*n;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j]=='X') continue;
                int p  = i * n + j;
                if (i==0 || i==m-1 || j==0 || j==n-1) {
                    uf.union(p, f);
                }else {
                    for (int k=0; k<dx.length; k++) {
                        int x = i + dx[k], y = j + dy[k];
                        if (x<0 || x>=m || y<0 || y>=n || board[x][y]=='X') continue;
                        int q = x * n + y;
                        uf.union(p, q);
                    }
                }
            }
        }
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                int p = i * n + j;
                if (uf.isConnected(p, f)) {
                    board[i][j] = 'O';
                }else {
                    board[i][j] = 'X';
                }
            }
        }
    }


    private class WeightedQuickUnionUF {
        private int[] id;
        private int[] sz;
        private int count;

        public WeightedQuickUnionUF(int count) {
            this.count = count;
            this.id = new int[count];
            this.sz = new int[count];
            for (int i=0; i<count; i++) {
                this.id[i] = i;
                this.sz[i] = 1;
            }
        }

        public int find(int p) {
            while(p!=id[p]) p = id[p];
            return p;
        }

        public void union(int p, int q) {
            int proot = find(p);
            int qroot = find(q);

            if (proot==qroot) return;
            
            if (sz[proot]>sz[qroot]) {id[qroot] = proot; sz[proot] += sz[qroot];}
            else                     {id[proot] = qroot; sz[qroot] += sz[proot];}
            count--;
        }

        public int count() {
            return count;
        }

        public boolean isConnected(int p, int q) {
            return find(p)==find(q);
        }
    }
```

