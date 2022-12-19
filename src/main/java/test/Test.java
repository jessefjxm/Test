package test;

public class Test {
    // 所有可能的移动方向，“后”可以向全部方向，“车”可以移动前四个方向，“象”可以移动后四个方向
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    int[][][] board;

    public static void main(String[] args) {
        System.out.println(new Test().countCombinations(new String[]{"rook"}, new int[][]{{1, 1}}));
    }

    public int countCombinations(String[] pieces, int[][] positions) {
        // 没有技巧，就是纯暴力模拟
        board = new int[positions.length][8][8];
        return count_recursive(pieces, positions, 0); // 从第一个棋子的初始位置开始考虑
    }

    // 考虑第i个棋子，在前i-1个棋子位置已经确定的情况下，可以移动的组合数量
    public int count_recursive(String[] pieces, int[][] positions, int i) {
        if (i >= pieces.length)
            return 1; // 如果后面没有其它棋子了，那么当前组合的数量只有1

        int x = positions[i][0] - 1, y = positions[i][1] - 1, res = 0; // 起始位置开始考虑
        for (int d = 0; d < 8; d++) { // 遍历各个方向
            if ((d < 4 && pieces[i].equals("bishop")) || (d >= 4 && pieces[i].equals("rook")))
                continue;

            boolean blocked = false;
            for (int step = res == 0 ? 1 : 2; !blocked; step++) { // 因为起始位置在8个方向中只能考虑一次，所以我们用res作为旗帜，只有第一次移动时会考虑停留在初始位置
                int nx = x + (step - 1) * dirs[d][0]; // 新坐标
                int ny = y + (step - 1) * dirs[d][1];

                if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) // 棋盘外了，无效移动，放弃这个方向
                    break;

                boolean canStop = true;
                for (int j = 0; j < i; j++) { // 检查前i-1个棋子的在当前位置的情况，判断我们是否可以选择停留或继续前进
                    // 只有当前i-1个棋子都没有停留，并且没有棋子会在之后经过，第i个棋子才可以在当前位置停留
                    canStop = canStop && (board[j][nx][ny] >= 0) && (board[j][nx][ny] < step);
                    // 如果前i-1个棋子在当前时间之前停留在此位置，或者有棋子同时经过这里，第i个棋子就不可以继续前进了
                    blocked = blocked || ((board[j][nx][ny] < 0) && (-board[j][nx][ny] <= step)) || (board[j][nx][ny] == step);
                }

                if (!canStop) { // 如果可以停留
                    board[i][nx][ny] = -step; // 用负数标记当前位置为棋子i的停留位
                    res += count_recursive(pieces, positions, i + 1); // 考虑下一个棋子的移动的可能性
                }
                board[i][nx][ny] = step; // 选择继续移动
            }
            board[i] = new int[8][8]; // 当前方向考虑结束，清空棋盘，考虑下一个方向
        }
        return res; // 返回可能的总数
    }
}
