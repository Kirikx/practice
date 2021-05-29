package ru.devstend.practice.algoritms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
  private final int countV;
  private final LinkedList<Integer>[] adj;

  @SuppressWarnings("unchecked")
  public Graph(int countV) {
    this.countV = countV;
    this.adj = new LinkedList[countV];
    for (int i = 0; i < countV; i++) {
      this.adj[i] = new LinkedList<>();
    }
  }

  public void addEdge(int v, int w) {
    adj[v].add(w);
  }

  public void BFS(int s) {
    boolean[] visited = new boolean[countV];

    Queue<Integer> queue = new LinkedList<>();

    visited[s] = true;
    queue.add(s);
    while (queue.size() != 0) {
      s = queue.poll();
      System.out.print(s + " ");

      Iterator<Integer> iterator = adj[s].iterator();
      while (iterator.hasNext()) {
        int n = iterator.next();

        if (!visited[n]) {
          visited[n] = true;
          queue.add(n);
        }
      }
    }
  }

  private void DFSUtil(int v, boolean[] visited) {
    visited[v] = true;

    System.out.print(v + " ");

    Iterator<Integer> iterator = adj[v].iterator();
    while (iterator.hasNext()) {
      int n = iterator.next();
      if (!visited[n]) {
        DFSUtil(n, visited);
      }
    }
  }

  public void DFS(int v) {
    boolean[] visited = new boolean[countV];
    DFSUtil(v, visited);
  }

  public static void main(String[] args) {
    Graph g = new Graph(4);

    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);

    System.out.println("BFS");
    g.BFS(2);

    System.out.println();
    System.out.println("DFS");
    g.DFS(2);

  }

}
