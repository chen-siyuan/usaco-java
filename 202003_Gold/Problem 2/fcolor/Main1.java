import java.util.*;
import java.io.*;

public class Main {

    public static class Node {

        private Set<Integer> vals, rers, reds;

        public Node() {
            vals = new HashSet<>();
            rers = new HashSet<>();
            reds = new HashSet<>();
        }

        public Node(int val) {
            this();
            vals.add(val);
        }

    }

    public static void union(int[] parents, int a, int b) {
        parents[find(parents, b)] = find(parents, a);
    }

    public static int find(int[] parents, int a) {
        if(parents[a] == a) return a;
        return (parents[a] = find(parents, parents[a]));
    }

    public static void main(String[] args) {

        int n = 0;
        int m = 0;
        int[] parents = null;
        Map<Integer, Node> map = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader("fcolor.in"))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            parents = new int[n];
            for(int i=0; i < n; i++) {
                parents[i] = i;
                map.put(i, new Node(i));
            }

            for(int i=0; i < m; i++) {

                st = new StringTokenizer(br.readLine());

                int admired = Integer.parseInt(st.nextToken()) - 1;
                int admirer = Integer.parseInt(st.nextToken()) - 1;

                map.get(admired).rers.add(admirer);
                map.get(admirer).reds.add(admired);
                
            }

        } catch(Exception e) {
            System.out.println(e);
        }

        boolean flag;
        do {

            flag = false;
            for(int i=0; i < n; i++) {

                Node node = map.get(i);
                if(node != null && node.rers.size() > 1) {

                    flag = true;

                    Node temp = null;
                    Integer val = null;

                    for(int j: node.rers) if(val == null) {
                        val = find(parents, j);
                        temp = map.get(val);
                    } else {

                        Node rer = map.get(find(parents, j));

                        temp.vals.addAll(rer.vals);
                        temp.rers.addAll(rer.rers);
                        for(int k: rer.rers) {
                            Set<Integer> kSet = map.get(find(parents, k)).reds;
                            kSet.remove()
                        }

                        temp.reds.addAll(rer.reds);


                        map.put(j, null);
                        union(parents, val, j);

                    }

                    node.rers.clear();
                    node.rers.add(val);

                }

            }

        } while(flag);

        System.out.println(Arrays.toString(parents));

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fcolor.out")))) {
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
