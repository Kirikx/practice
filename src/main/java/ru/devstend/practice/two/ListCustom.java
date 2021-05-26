package ru.devstend.practice.two;


public interface ListCustom {

  void add(int value);
  void add(int index, int value);
  int get(int index);
  boolean isEmpty();
  int size();
  void remove(int index);
  void removeByValue(int value);
}
