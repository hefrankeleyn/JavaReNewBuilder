package com.hef.recursion;

/**
 * @Date 2021/10/24
 * @Author lifei
 */
public class SearchMatrix {

    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        int target = 3;
        boolean res = searchMatrix.searchMatrix(matrix, target);
        System.out.println(res);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int mlow=0, mhigh = m-1;
        int mid = mlow + (mhigh-mlow)/2;
        while (mlow<=mhigh) {
            if (matrix[mid][0]==target) {
                break;
            }else if (matrix[mid][0]>target) {
                mhigh = mid - 1;
            }else {
                mlow = mid + 1;
            }
            mid = mlow + (mhigh - mlow)/2;
        }
        if (matrix[mid][0]>target) {
            if (mid>0) mid--;
            else return false;
        }
        int n = matrix[mid].length;
        int nlow = 0, nhigh = n-1;
        while(nlow<=nhigh) {
            int mid2 = nlow + (nhigh-nlow)/2;
            if (matrix[mid][mid2]==target) return true;
            else if (matrix[mid][mid2]>target) {
                nhigh = mid2-1;
            }else {
                nlow = mid2+1;
            }
        }
        return false;
    }
}
