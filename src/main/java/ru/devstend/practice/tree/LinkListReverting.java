package ru.devstend.practice.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LinkListReverting {

  private Node head;
  private int size;

  public LinkListReverting() {
    this.head = null;
    this.size = 0;
  }

  public void add(int value) {
    if (head == null) {
      head = new Node(value, null);
      ++size;
      return;
    }
    Node lastNode = head;
    for (int i = 0; i < size; i++) {
      if (lastNode.getNext() == null) {
        break;
      }
      lastNode = lastNode.getNext();
    }
    Node newNode = new Node(value, null);
    lastNode.setNext(newNode);
    ++size;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @ToString
  public static class Node {

    private int value;
    private Node next;
  }

  public static void main(String[] args) {
    LinkListReverting linkList = new LinkListReverting();
    linkList.add(1);
    linkList.add(2);
    linkList.add(3);
    linkList.add(4);
    linkList.add(5);
    System.out.println("Original linkList");
    System.out.println(linkList);

    System.out.println("Reverse linkList");
    linkList.reverseList();
    System.out.println(linkList);
  }

  // пространственная сложность O(1), временная O(n)
  private void reverseList() {
    if (size < 1) {
      return;
    }
    Node buff = null;
    Node currentNode = head;
    for (int i = 0; i < size; i++) {
      if (currentNode.getNext() == null) {
        currentNode.setNext(buff);
        head = currentNode;
        break;
      }
      Node oldBuff = buff;
      buff = currentNode;
      currentNode = currentNode.getNext();
      buff.setNext(oldBuff);
    }
  }

}
