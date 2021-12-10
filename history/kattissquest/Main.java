/** Simple yet moderately fast I/O routines.
 *
 * Example usage:
 *
 * Kattio io = new Kattio(System.in, System.out);
 *
 * while (io.hasMoreTokens()) {
 *    int n = io.getInt();
 *    double d = io.getDouble();
 *    double ans = d*n;
 *
 *    io.println("Answer: " + ans);
 * }
 *
 * io.close();
 *
 *
 * Some notes:
 *
 * - When done, you should always do io.close() or io.flush() on the
 *   Kattio-instance, otherwise, you may lose output.
 *
 * - The getInt(), getDouble(), and getLong() methods will throw an
 *   exception if there is no more data in the input, so it is generally
 *   a good idea to use hasMoreTokens() to check for end-of-file.
 *
 * @author: Kattis
 */

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    BalancedBinaryTree<Quest> set = new BalancedBinaryTree<>();
    int n = sc.getInt();
    for (int i = 1; i <= n; i++) {
      String op = sc.getWord();
      int a;
      int b;
      if (op.equals("add")) {
        a = sc.getInt();
        b = sc.getInt();
        Quest q = new Quest(a, b, i);
        set.balancedInsert(q);
      } else {
        a = sc.getInt();
        long sum = query(set, a);
        sc.println(sum);
      }
      // sc.println(set);
//      set.PrintInOrder();
    }
    sc.close();
  }

  public static long query(BalancedBinaryTree<Quest> set, int e) {
    if (set.isEmpty()) return 0;
    Quest tmp = set.floor(new Quest(e+1, 0, 0));
//    System.out.println("inside query, tmp is " + tmp);
    if (tmp == null) return 0;
    long g = tmp.g;
    int f = e - tmp.e;
    // System.out.println(set.remove(tmp));
    set.balancedRemove(tmp);
    return g + query(set, f);
  } 
}

class Quest implements Comparable<Quest> {
  int e;
  int g;
  int i;

  public Quest(int e, int g, int i) {
    this.e = e;
    this.g = g;
    this.i = i;
  }

  public int compareTo(Quest q) {
    if (this.e > q.e) return 1;
    if (this.e < q.e) return -1;
    if (this.g > q.g) return 1;
    if (this.g < q.g) return -1;
    if (this.i < q.i) return 1;
    if (this.i > q.i) return -1;
    else return 0;
  }

  public String toString() {
    return "Quest : " + e + ", " + g + ", " + i + ".";
  }

  public boolean equals(Object o) {
    if (o instanceof Quest) {
      Quest tmp = (Quest) o;
      return this.e == tmp.e && this.g == tmp.g && this.i == tmp.i;
    }
    return false;
  } 
}

/*
public class Main {
  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int n = sc.getInt();
    int m = sc.getInt();
    int rank = 1;
    BalancedBinaryTree<Team> tree = new BalancedBinaryTree<>();
    Team[] teams = new Team[n + 1];
    for (int i = 0; i < m; i++) {
      int t = sc.getInt();
      int p = sc.getInt();
      if (t == 1) {
        if (teams[1] == null) {
          teams[1] = new Team(1, 1, p);
          tree = tree.iterateUntil(teams[1]);
          tree.balancedInsert(teams[1]);
        } else {
          teams[1] = teams[1].update(p);
          tree = tree.iterateUntil(teams[1]);
          tree.balancedInsert(teams[1]);
        }
        rank = tree.findRank(teams[1]);
        sc.println(rank);
      } else {
        if (teams[t] == null) {
          teams[t] = new Team(t, 1, p);
          if (teams[1] == null || teams[t].compareTo(teams[1]) < 0) {
            tree.balancedInsert(teams[t]);
            rank++;
            sc.println(rank);
          } else {
            sc.println(rank);
          }
        } else {
          Team old = teams[t];
          teams[t] = old.update(p);
          //System.out.printf("i am inside t != 1 case, t is %d, old is %s, old.update(p) is %s, teams[1] is %s\n", t, old, old.update(p), teams[1]);
          if (teams[1] == null || old.compareTo(teams[1]) < 0) tree.balancedRemove(old);
          if (teams[1] == null || teams[t].compareTo(teams[1]) < 0) {
            tree.balancedInsert(teams[t]);
            rank++;
            sc.println(rank);
          } else {
            sc.println(rank);
          }
        }
      }
      //tree.PrintInOrder();
    }
    sc.close();
  }
}
*/
/*
class SpecialTree implements BalancedBinaryTree<Team> {

  public void insert1(Team key) {
    insert1(root, key);
  }
  public void insert1(Node<Team> curr, Team key) {
    while (curr != null) {
      if (key.compareTo(curr.value) < 0) {
        curr.right = null;
        tmp = curr;
        curr = curr.left;
      } else {
        tmp = curr;
        curr = curr.right;
      }
    }
    if (key.compareTo())
  }
}
*/
class Team implements Comparable<Team>{
  int i;
  int q;
  long p; 

  public Team(int i, int q, long p) {
    this.i = i;
    this.q = q;
    this.p = p;
  }

  public Team update(int p) {
    return new Team(i, q + 1, this.p + p); 
  }

  public int compareTo(Team other) {
    if (this.q > other.q) return -1;
    if (this.q < other.q) return 1;
    if (this.p < other.p) return -1;
    if (this.p > other.p) return 1;
    if (this.i < other.i) return -1;
    if (this.i > other.i) return 1;
    return 0;
  }

  public String toString() {
    return "team " + i + " with q " + q + " and p " + p;
  }

  public boolean equals(Object o) {
    if (o instanceof Team) {
      @SuppressWarnings("unchecked")
      Team tmp = (Team) o;
      return this.i == tmp.i;
    }
    return false;
  }
}

class Node<T extends Comparable<T>> {
  T value;
  Node<T> parent;
  Node<T> left;
  Node<T> right;

  int height;
  int size;

  public Node(T key) {
    value = key;
    parent = null;
    left = null;
    right = null;
    size = 1;
    height = 0;
  }

  public int bf() {
    int lh = left == null ? -1 : left.height;
    int rh = right == null ? -1 : right.height;
    return lh - rh;
  }

  public Node<T> longerChild() {
    int lh = left == null ? -1 : left.height;
    int rh = right == null ? -1 : right.height;
    return lh > rh ? left : right;
  }
/*
  public void setRight(Node<T> node) {
    this.right = node;
    if (node != null) node.parent = this;
    updateHeight();
    updateSize();
  } 
*/

/*
  public void setLeft(Node<T> node) {
    this.left = node;
    if (node != null) node.parent = this;
    updateHeight();
    updateSize();
  } 
*/

  public void updateHeight() {
    int h;
    int lh = left == null ? -1 : left.height;
    int rh = right == null ? -1 : right.height;
    if (lh > rh) h = 1 + lh;
    else h = 1 + rh;
    //System.out.printf("i am %s, my left is %s, my right is %s, my parent is %s\n", this, this.left, this.right, this.parent);
    //System.out.printf("i am updating height, height is %d, lh is %d, rh is %d, h is %d\n", height, lh, rh, h);
    if (h != height) {
      height = h;
      if (this.parent != null) this.parent.updateHeight();
    }
  }

  public void updateSize() {
    int ls = left == null ? 0 : left.size;
    int rs = right == null ? 0 : right.size;
    int s = ls + rs + 1;
    if (s != size) {
      size = s;
      if (this.parent != null) this.parent.updateSize();
    }
  }

  public String toString() {
    return value.toString();
  }
}

class BinaryTree<T extends Comparable<T>> {
  Node<T> root;
  Node<T> tmp;

  public BinaryTree() {
    root = null;
    tmp = null;
  }

  public boolean isEmpty() {
    if (root == null) return true;
    return false;
  }

  public void setRoot(Node<T> node) {
    node.parent = null;
    root = node;
  }

  public Node<T> search(T key) {
    tmp = null;
    Node<T> curr = root;
    while (true) {
      if (curr == null) return curr;
      else if (curr.value.compareTo(key) == 0) {return curr;}
      else if (curr.value.compareTo(key) > 0) {tmp = curr; curr = curr.left;}
      else {tmp = curr; curr = curr.right;}
    }
  }

  public boolean insert(T key) {
    if (root == null) {root = new Node<>(key); return true;}
    Node<T> curr = search(key);
    Node<T> node = new Node<>(key);
    if (curr != null) return false; // key already exists
    if (tmp.value.compareTo(key) > 0) {
      tmp.left = node;
      node.parent = tmp;
    }
    else {
      tmp.right = node;
      node.parent = tmp;
    }
    tmp.updateHeight();
    tmp.updateSize();
    return true;
  }

  public Node<T> remove(T key) {
    Node<T> curr = search(key);
    if (curr.parent == null) {
      if (curr.left == null && curr.right == null) {
        root = null;
        return curr;
      }
      else if (curr.left == null && curr.right != null) {
        setRoot(curr.right); 
        return curr;
      }
      else if (curr.left != null && curr.right == null) {
        setRoot(curr.left);
        return curr;
      }
      else {
        Node<T> next = nextNode(curr);
        //System.out.println("inside remove, next is " + next);
        //System.out.println("info of next, left is " +  next.left + " right is " + next.right + " parent is " + next.parent);
        tmp = remove(next.value).parent;
        //System.out.println("inside remove, tmp is " + tmp);
        //next.setRight(curr.right);
        next.right = curr.right;
        if (curr.right != null) curr.right.parent = next;
        //next.setLeft(curr.left);
        next.left = curr.left;
        if (curr.left != null) curr.left.parent = next;
        setRoot(next);
        next.updateHeight();
        next.updateSize();
        return curr;
      }
    }
    else if (curr.left == null && curr.right == null) {
      if (curr.equals(curr.parent.left)) {
        //curr.parent.setLeft(null);
        curr.parent.left = null;
      }
      else {
        //curr.parent.setRight(null);
        curr.parent.right = null;
      }
      curr.parent.updateHeight();
      curr.parent.updateSize();
      return curr;
    } 
    else if (curr.left == null && curr.right != null) {
      if (curr.equals(curr.parent.left)) {
        //curr.parent.setLeft(curr.right);
        curr.parent.left = curr.right;
        curr.right.parent = curr.parent;
      }
      else {
        //curr.parent.setRight(curr.right);
        curr.parent.right = curr.right;
        curr.right.parent = curr.parent;
      }
      curr.parent.updateHeight();
      curr.parent.updateSize();
      return curr;
    }
    else if (curr.left != null && curr.right == null) {
      if (curr.equals(curr.parent.left)) {
        //curr.parent.setLeft(curr.left);
        curr.parent.left = curr.left;
        curr.left.parent = curr.parent;
      }
      else {
        //curr.parent.setRight(curr.left);
        curr.parent.right = curr.left;
        curr.left.parent = curr.parent;
      }
      curr.parent.updateHeight();
      curr.parent.updateSize();
      return curr;
    }
    else {
      Node<T> next = nextNode(curr);
      tmp = remove(next.value).parent;
      //next.setRight(curr.right);
      next.right = curr.right;
      curr.right.parent = next;
      //next.setLeft(curr.left);
      next.left = curr.left;
      curr.left.parent = next;
      if (curr.equals(curr.parent.left)) {
        //curr.parent.setLeft(next);
        curr.parent.left = next;
        next.parent = curr.parent;
      }
      else {
        //curr.parent.setRight(next);
        curr.parent.right = next;
        next.parent = curr.parent;
      }
      next.updateHeight();
      next.updateSize();
      return curr;
    }
  }

  public Node<T> nextNode(Node<T> start) {
    if (start.right != null) return endOfLeftChain(start.right);
    Node<T> parent = start.parent;
    while (parent != null && parent.right.equals(start)) {
      start = parent;
      parent = start.parent;
    } 
    return parent;
  }

  public Node<T> preNode(Node<T> start) {
    if (start.left != null) return endOfRightChain(start.left);
    Node<T> parent = start.parent;
    while (parent != null && parent.left.equals(start)) {
      start = parent;
      parent = start.parent;
    }
    return parent;
  }

  public Node<T> endOfLeftChain(Node<T> start) {
    Node<T> tmp = start;
    Node<T> curr = start.left;
    while (curr != null) {
      tmp = curr;
      curr = tmp.left;
    }
    return tmp;
  }

  public Node<T> smallest() {
    return endOfLeftChain(root);
  }

  public Node<T> endOfRightChain(Node<T> start) {
    Node<T> tmp = start;
    Node<T> curr = start.right;
    while (curr != null) {
      tmp = curr;
      curr = tmp.right;
    }
    return tmp;
  }

  public ArrayList<Node<T>> preOrder() {
    ArrayList<Node<T>> lst = new ArrayList<>();
    Stack<Node<T>> stack = new Stack<>();
    Node<T> curr = root;
    while (true) {
      preOrderHelper(curr, stack, lst);
      if (stack.empty()) break;
      curr = stack.pop();
    }
    return lst;
  }

  public ArrayList<Node<T>> inOrder() {
    ArrayList<Node<T>> lst = new ArrayList<>();
    Stack<Node<T>> stack = new Stack<>();
    Node<T> curr = root;
    while (true) {
      inOrderHelper(curr, stack);
      if (stack.empty()) break;
      curr = stack.pop();
      lst.add(curr);
      curr = curr.right;
    }
    return lst;
  }

  public BalancedBinaryTree<T> iterateUntil(T key) {
    BalancedBinaryTree<T> tree = new BalancedBinaryTree<>();
    Stack<Node<T>> stack = new Stack<>();
    Node<T> curr = root;
    while (true) {
      inOrderHelper(curr, stack);
      if (stack.empty()) break;
      curr = stack.pop();
      if (curr.value.compareTo(key) > 0) break;
      tree.balancedInsert(curr.value);
      curr = curr.right;
    }
    return tree;
  }

  public void PrintInOrder() {
    Stack<Node<T>> stack = new Stack<>();
    Node<T> curr = root;
    while (true) {
      inOrderHelper(curr, stack);
      if (stack.empty()) break;
      curr = stack.pop();
      //System.out.println("curr: " + curr + ", left: " + curr.left + ", right: " + curr.right + ", parent: " + curr.parent);
      System.out.printf("curr: %s, left: %s, right: %s, parent: %s, size: %d, height: %d, bf: %d\n", curr, curr.left, curr.right, curr.parent, curr.size, curr.height, curr.bf());
      curr = curr.right;
    }
    System.out.println();
  }

  public void preOrderHelper(Node<T> start, Stack<Node<T>> stack, ArrayList<Node<T>> lst) {
    while (start != null) {
      lst.add(start);
      stack.push(start.right);
      start = start.left;
    }
  }

  public void inOrderHelper(Node<T> start, Stack<Node<T>> stack) {
    while (start != null) {
      stack.push(start);
      start = start.left;
    }
  }

  public T floor(T key) {
    search(key);
    Node<T> curr = tmp;
 //   System.out.println(curr);
    if (curr.value.compareTo(key) > 0) {
      Node<T> res = preNode(curr);
      return res == null ? null : res.value;
    }
    else return curr.value;
  }

  public String toString() {
    return inOrder().toString();
  }

  public int findRank(T key) {
    return findRank(root, key);
  }

  public int findRank(Node<T> curr, T key) {
    //System.out.printf("im find rank of %s, and key is %s\n", curr, key);
    if (curr.value.compareTo(key) == 0) return curr.left == null ? 1 : curr.left.size + 1;
    else if (key.compareTo(curr.value) < 0) return findRank(curr.left, key);
    else return curr.left == null ? 1 + findRank(curr.right, key) : curr.left.size + 1 + findRank(curr.right, key);
  }
}

class BalancedBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
  public BalancedBinaryTree() {
    super();
  }

  public boolean checkbf(Node<T> node) {
    int bf = node.bf();
    return bf > -2 && bf < 2;
  }

  public boolean balancedInsert(T key) {
    boolean res = insert(key);
    Node<T> curr = tmp;
    while (curr != null) {
      if (!checkbf(curr)) {
        restore(curr);
        break;
      }
      curr = curr.parent;
    }
    return res;
  }

  public Node<T> balancedRemove(T key) {
    Node<T> res = remove(key);
    Node<T> curr = tmp;
    while (curr != null) {
      if (!checkbf(curr)) restore(curr);
      curr = curr.parent;
    }
    return res;
  }

  public void restore(Node<T> g) {
    Node<T> p;
    if (g.bf() == -2) {
      p = g.right;
      if (p.bf() == -1 || p.bf() == 0) {
        rotateLeft(g);
      } else {
        rotateRight(p);
        rotateLeft(g);
      }
    } else {
      p = g.left;
      if (p.bf() == 1 || p.bf() == 0) {
        rotateRight(g);
      } else {
        rotateLeft(p);
        rotateRight(g);
      }
    }
  }

  public void rotateLeft(Node<T> g) {
    //System.out.println("before a left roatation: \n");
    //PrintInOrder();
    Node<T> p = g.right;
    Node<T> parent = g.parent;
    //g.setRight(p.left);
    g.right = p.left;
    if (p.left != null) p.left.parent = g;
    //p.setLeft(g);
    p.left = g;
    g.parent = p;
    if (parent == null) {
      p.parent = null;
      root = p;
    } else {
      if (g.equals(parent.left)) {
        //parent.setLeft(p);
        parent.left = p;
        p.parent = parent;
      }
      else {
        //parent.setRight(p);
        parent.right = p;
        p.parent = parent;
      }
    }
    g.updateHeight();
    g.updateSize();
    //System.out.println("after a left rotation: \n");
    //PrintInOrder();
  }

  public void rotateRight(Node<T> g) {
    //System.out.println("before a right roatation: \n");
    //PrintInOrder();
    Node<T> p = g.left;
    Node<T> parent = g.parent;
    //g.setLeft(p.right);
    g.left = p.right;
    if (p.right != null) p.right.parent = g;
    //p.setRight(g);
    p.right = g;
    g.parent = p;
    if (parent == null) {
      p.parent = null;
      root = p;
    } else {
      if (g.equals(parent.left)) {
        //parent.setLeft(p);
        parent.left = p;
        p.parent = parent;
      }
      else {
        //parent.setRight(p);
        parent.right = p;
        p.parent = parent;
      }
    }
    g.updateHeight();
    g.updateSize();
    //System.out.println("after a left rotation: \n");
    //PrintInOrder();
  }
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
