//package ARRAY;
//
//import java.util.Arrays;
//
//public class ReverseString {
//    public static void main(String[] args) {
//
//        public static List<Integer> countRotations ( int nCogwheels, List<List<Integer>>connections){
//            // 1. Build Adjacency List
//            List<List<Integer>> adj = new ArrayList<>();
//            for (int i = 0; i < nCogwheels; i++) {
//                adj.add(new ArrayList<>());
//            }
//            for (List<Integer> conn : connections) {
//                int u = conn.get(0);
//                int v = conn.get(1);
//                adj.get(u).add(v);
//                adj.get(v).add(u);
//            }
//
//            // 2. Array to store directions: 0 = unvisited, 1 = Clockwise, -1 = Counter-Clockwise
//            int[] directions = new int[nCogwheels];
//            Queue<Integer> queue = new LinkedList<>();
//
//            // Start with gear 0 rotating Clockwise (1)
//            directions[0] = 1;
//            queue.add(0);
//
//            while (!queue.isEmpty()) {
//                int current = queue.poll();
//
//                for (int neighbor : adj.get(current)) {
//                    if (directions[neighbor] == 0) {
//                        // Assign opposite direction
//                        directions[neighbor] = -directions[current];
//                        queue.add(neighbor);
//                    } else if (directions[neighbor] == directions[current]) {
//                        // Deadlock detected: two connected gears trying to move the same way
//                        return Arrays.asList(-1, -1);
//                    }
//                }
//            }
//
//            // 3. Count results
//            int clockwiseCount = 0;
//            int counterClockwiseCount = 0;
//            for (int dir : directions) {
//                if (dir == 1) clockwiseCount++;
//                else if (dir == -1) counterClockwiseCount++;
//            }
//
//            return Arrays.asList(clockwiseCount, counterClockwiseCount);
//        }
//    }
//}
//}
