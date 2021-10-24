package com.hef.recursion;

import java.util.*;
/**
 * @Date 2021/10/24
 * @Author lifei
 */
public class RobotSim {

    public static void main(String[] args) {
        RobotSim robotSim = new RobotSim();
        int[] commands = {6,-1,-1,6};
        int[][] obstacles = {
//                {2,4}
        };
        int res = robotSim.robotSim(commands, obstacles);
        System.out.println(res);
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int k=0; k<obstacles.length; k++) {
            int[] a = obstacles[k];
            map.putIfAbsent(a[0], new HashSet<>());
            map.get(a[0]).add(a[1]);
        }
        int dir = 0;
        int x = 0, y = 0;
        int res = 0;
        for (int i=0; i<commands.length; i++) {
            if (commands[i]==-2 ) {
                if (dir==0) dir = 3;
                else dir -= 1;
                continue;
            }else if (commands[i]==-1) {
                if (dir==3) dir = 0;
                else dir += 1;
                continue;
            }

            int x2 = x, y2 = y;
            if (dir==0) {
                // +Y
                y2 = y + commands[i];
                if (map.containsKey(x)) {
                    for (Integer v: map.get(x)) {
                        if (v>=y && v<=y2) {
                            y2 = y-1;
                        }
                    }
                }
            }else if (dir==1) {
                // +X
                x2 = x + commands[i];
                for (int m=x; m<=x2; m++) {
                    if (map.containsKey(m) && map.get(m).contains(y)) {
                        x2 = m-1;
                    }
                }
            }else if (dir==2) {
                // -Y
                y2 = y - commands[i];
                if (map.containsKey(x)) {
                    for (Integer v: map.get(x)) {
                        if (v<=y && v>=y2) {
                            y2 = y+1;
                        }
                    }
                }
            }else if (dir==3) {
                // -X
                x2 = x - commands[i];
                for (int m=x2; m<=x; m++) {
                    if (map.containsKey(m) && map.get(m).contains(y)) {
                        x2 = m+1;
                    }
                }
            }
            res = Math.max(res, x2*x2 + y2* y2);
            x=x2;
            y=y2;
        }
        return res;
    }
}
