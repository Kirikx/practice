package ru.devstend.practice.two;

import java.util.Arrays;

public class ArrayListCustom implements ListCustom {

  private int size;
  private int[] elements;

  public ArrayListCustom(ArrayListCustom list) {
    this.size = list.size;
    this.elements = list.elements;
  }

  public ArrayListCustom() {
    this.elements = new int[0];
    this.size = 0;
  }

  public ArrayListCustom(int size) {
    if (size < 0) {
      throw new IllegalArgumentException();
    }
    this.elements = new int[size];
    this.size = size;
  }

  public ArrayListCustom(int[] elements) {
    this.size = elements.length;
    this.elements = elements;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void add(int value) {
    if (size == 0) {
      elements = new int[]{value};
      ++size;
      return;
    }
    elements = Arrays.copyOf(elements, (size + 1));
    elements[size] = value;
    ++size;
  }

  @Override
  public void add(int index, int value) {
    checkIndex(index);
    elements[index] = value;
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  @Override
  public int get(int index) {
    checkIndex(index);
    return elements[index];
  }

  @Override
  public void remove(int index) {
    checkIndex(index);
    int[] newArray = new int[size-1];
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (i == index) continue;
      newArray[count] = elements[i];
      ++count;
    }
    elements = newArray;
    --size;
  }

  @Override
  public void removeByValue(int value) {
    int[] newArray = new int[size-1];
    int count = 0;
    boolean deleted = false;
    for (int i = 0; i < size; i++) {
      if (!deleted && elements[i] == value) {
        deleted = true;
        continue;
      }
      newArray[count] = elements[i];
      ++count;
    }
    elements = newArray;
    --size;
  }

  @Override
  public String toString() {
    return Arrays.toString(elements);
  }

  public static void main(String[] args) {
    ArrayListCustom list = new ArrayListCustom();
    printListInfo(list);

    list.add(1);
    list.add(10);
    list.add(100);
    printListInfo(list);

    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));

    list.remove(1);
    printListInfo(list);

    list.add(500);
    list.add(1, 10);
    printListInfo(list);

    list.remove(0);
    printListInfo(list);
    list.remove(0);
    printListInfo(list);
    list.remove(0);
    printListInfo(list);

    list.add(11);
    list.add(111);
    list.add(1111);
    printListInfo(list);

    ArrayListCustom list2 = new ArrayListCustom(list);

    list.remove(2);
    printListInfo(list);
    list.remove(1);
    printListInfo(list);
    list.remove(0);
    printListInfo(list);

    System.out.println("Delete by value");
    list2.removeByValue(111);
    printListInfo(list2);
    list2.removeByValue(1111);
    printListInfo(list2);
    list2.removeByValue(11);
    printListInfo(list2);
  }

  private static void printListInfo(ArrayListCustom list) {
    System.out.println("Size = " + list.size());
    System.out.println("isEmpty = " + list.isEmpty());
    System.out.println("toString = " + list);
    System.out.println("");
  }
}
