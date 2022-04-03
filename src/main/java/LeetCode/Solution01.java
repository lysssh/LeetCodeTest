package LeetCode;

import MyLeetCodeUtil.Node;

import java.util.*;

public class Solution01 {
    public Node copyRandomList(Node head) {
        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        cur = head;
        Node res = null;
        Node tail = null;
        while (cur != null) {
            if (res == null) {
                res = cur.next;
                tail = cur.next;
            } else {
                tail.next = cur.next;
                tail = tail.next;
            }
            cur.next = tail.next;
            tail.next = null;
            cur = cur.next;

        }
        return res;
    }


    public int[] missingRolls(int[] rolls, int mean, int n) {
        int sum = 0;
        for (int roll : rolls) {
            sum += roll;
        }
        sum = mean * (rolls.length + n) - sum;
        if (sum < n || sum > 6 * n) {
            return new int[0];
        }
        int count = sum / n;
        int num = sum % n;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = count + (num > 0 ? 1 : 0);
            num--;
        }
        return res;
    }


    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 == 1) {
            return new int[0];
        }
        Arrays.sort(changed);
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[changed.length / 2];
        int index = 0;
        for (int i = changed.length - 1; i >= 0; i--) {
            if (!map.containsKey(changed[i]) && changed[i] % 2 == 1) {
                return new int[0];
            }
            if (map.containsKey(changed[i])) {
                if (map.get(changed[i]) == 1) {
                    map.remove(changed[i]);
                } else {
                    map.put(changed[i], map.get(changed[i]) - 1);
                }
            } else {
                int num = changed[i] >> 1;
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
                if (index >= res.length) {
                    return new int[0];
                }
                res[index++] = num;
            }
        }
        return res;
    }

    int[] res;
    Map<Integer, Set<Integer>> parent;
    Set<Integer> smallestMissingValueSubtree;

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        smallestMissingValueSubtree = new HashSet<>();
        parent = new HashMap<>();
        res = new int[parents.length];
        Arrays.fill(res, 1);
        int x = -1;
        if (nums[0] == 1) {
            x = 0;
        }
        for (int i = 1; i < parents.length; i++) {
            if (nums[i] == 1) {
                x = i;
            }
            if (!parent.containsKey(parents[i])) {
                parent.put(parents[i], new HashSet<>());
            }
            parent.get(parents[i]).add(i);
        }

        for (int i = 2; x >= 0; x = parents[x]) {
            smallestMissingValueSubtree(x, nums);
            while (smallestMissingValueSubtree.contains(i)) {
                i++;
            }
            res[x] = i;
        }
        return res;
    }

    public void smallestMissingValueSubtree(int index, int[] nums) {
        smallestMissingValueSubtree.add(nums[index]);
        if (parent.containsKey(index)) {
            for (int child : parent.get(index)) {
                if (smallestMissingValueSubtree.contains(nums[child])) {
                    continue;
                }
                smallestMissingValueSubtree(child, nums);
            }
        }

    }


    public boolean hasAlternatingBits(int n) {
        int cur = -1;
        while (n != 0) {
            int u = n & 1;
            if ((u ^ cur) == 0) {
                return false;
            }
            cur = u;
            n >>= 1;
        }
        return true;
    }


    public int maxConsecutiveAnswers(String answerKey, int k) {
        if (k >= answerKey.length()) {
            return answerKey.length();
        }
        return Math.max(maxConsecutiveAnswers(answerKey, k, 'F'), maxConsecutiveAnswers(answerKey, k, 'T'));
    }

    public int maxConsecutiveAnswers(String answerKey, int k, char c) {
        int ans = 0;
        for (int left = 0, sum = 0, right = 0; right < answerKey.length(); right++) {
            sum += answerKey.charAt(right) != c ? 1 : 0;
            while (sum > k) {
                sum -= answerKey.charAt(left++) != c ? 1 : 0;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }


    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        Queue<Character> queue = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while (set.contains(c)) {
                set.remove(queue.poll());
            }
            set.add(s.charAt(i));
            queue.add(s.charAt(i));
            res = Math.max(res, queue.size());
        }
        return res;
    }

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        List<Integer> res = new ArrayList<>();
        int max = 0;
        int[] request = new int[k];
        TreeSet<Integer> set = new TreeSet<>();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < k; i++) {
            set.add(i);
        }
        for (int i = 0; i < arrival.length; i++) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek()[0] <= arrival[i]) {
                set.add(priorityQueue.poll()[1]);
            }

            if (set.size() == k) {
                continue;
            }
            Integer index = set.ceiling(i % k);
            if (index == null) {
                index = set.first();
            }
            request[index]++;
            max = Math.max(max, request[index]);
            priorityQueue.add(new int[]{arrival[i] + load[i], index});
            set.remove(index);
        }
        for (int i = 0; i < k; i++) {
            if (request[i] == max) {
                res.add(i);
            }
        }
        return res;
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int num = i;
            while (num > 0) {
                if (num % 10 == 0 || (i % (num % 10)) != 0) {
                    break;
                }
                num /= 10;

            }
            if (num == 0) {
                res.add(i);
            }
        }
        return res;
    }

    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> borrow = new HashMap<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            if (borrow.containsKey(num)) {
                int count = borrow.get(num);
                if (count == 1) {
                    borrow.remove(num);
                } else {
                    borrow.put(num, count - 1);
                }
            } else if (num < 0) {
                num *= 2;
                if (borrow.containsKey(num)) {
                    borrow.put(num, borrow.get(num) + 1);
                } else {
                    borrow.put(num, 1);
                }
            } else {
                if(num % 2 == 1) {
                    return false;
                }
                num /= 2;
                if (borrow.containsKey(num)) {
                    borrow.put(num, borrow.get(num) + 1);
                } else {
                    borrow.put(num, 1);
                }
            }
        }
        return borrow.isEmpty();
    }

    public int strongPasswordChecker(String password) {
        char[] cs = password.toCharArray();
        int n = cs.length;
        int A = 0, B = 0, C = 0;
        for (char c : cs) {
            if (c >= 'a' && c <= 'z') A = 1;
            else if (c >= '0' && c <= '9') B = 1;
            else C = 1;
        }
        int m = A + B + C;
        if (n < 6) {
            return Math.max(6 - n, 3 - m);
        } else if (n <= 20) {
            int tot = 0;
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && cs[j] == cs[i]) j++;
                int cnt = j - i;
                if (cnt >= 3) tot += cnt / 3;
                i = j;
            }
            return Math.max(tot, 3 - m);
        } else {
            int tot = 0;
            int[] remain = new int[3];
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && cs[j] == cs[i]) j++;
                int cnt = j - i;
                if (cnt >= 3) {
                    tot += cnt / 3; remain[cnt % 3]++;
                }
                i = j;
            }
            int base = n - 20, cur = base;
            for (int i = 0; i < 3; i++) {
                if (i == 2) remain[i] = tot;
                if (remain[i] != 0 && cur > 0) {
                    int t = Math.min(remain[i] * (i + 1), cur);
                    cur -= t; tot -= t / (i + 1);
                }
            }
            return base + Math.max(tot, 3 - m);
        }
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0;
        int r = letters.length-1;
        while (l < r) {
            int mid = l + ((r-l) >> 1);
            if(letters[mid]  > target) {
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return letters[r];
    }

    public static void main(String[] args) {
        Solution01 s1 = new Solution01();
        s1.nextGreatestLetter(new char[]{'c','f','j'},'a');
    }
}