package ru.devstend.practice.two;


import java.util.Arrays;

public class LinkedListCustom implements ListCustom {
  private int size = 0;
  private final Node header;

  public LinkedListCustom() {
    header = new Node(0, null, null);
    header.prev = header;
    header.next = header;
  }

  private static class Node {
    int value;
    Node next;
    Node prev;

    Node (int value, Node next, Node prev) {
      this.value = value;
      this.next = next;
      this.prev = prev;
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void add(int val) {
    Node newElem = new Node(val, header, header.prev);
    newElem.prev.next = newElem;
    newElem.next.prev = newElem;
    size++;
  }

  @Override
  public void add(int index, int value) {
    getNodeByIndex(index).value = value;
  }

  @Override
  public int get(int index) {
    Node node = getNodeByIndex(index);
    return node.value;
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  private Node getNodeByIndex(int index) {
    checkIndex(index);
    Node node = header.next;
    for (int i = 0; index > i; i++) {
      node = node.next;
    }
    return node;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void remove(int index) {
    Node node = getNodeByIndex(index);
    removeNode(node);
  }

  private void removeNode(Node node){
    Node prev = node.prev;
    prev.next = node.next;
    if (node.next != null) {
      Node next = node.next;
      next.prev = prev;
    }
    --size;
  }

  @Override
  public void removeByValue(int value) { // Not the original name of the method. Made to fit
      for (int i = 0; i < size; i++) {
        Node el;
        if ((el = getNodeByIndex(i)).value == value) {
          removeNode(el);
          return;
        }
      }
  }

  @Override
  public String toString() {
    int[] array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = get(i);
    }
    return Arrays.toString(array);
  }

  public static void main(String[] args) {
    LinkedListCustom list = new LinkedListCustom();
    assert(list.isEmpty());
    list.add(1);
    list.add(10);
    list.add(100);
    assert(!list.isEmpty());

    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));
    System.out.println(list);

    list.remove(1);
    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list);

    list.removeByValue(1);
    System.out.println(list.get(0));
    System.out.println(list);

    System.out.println(list.size());
  }
}
