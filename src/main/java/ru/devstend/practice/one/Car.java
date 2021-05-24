package ru.devstend.practice.one;

import java.util.Map;

interface Moveable { // Устаревшее употребление слова, рекомендуется применять 'Movable'
  void move();
}

interface Stopable {
  void stop();
}

abstract class Car { // добавить модификатор доступа
  public Engine engine;     // изменить модификатор на private
  private String color;
  private String name;

  protected void start() {
    System.out.println("Car starting");
  }

  abstract void open(); // добавить модификатор доступа

  public Engine getEngine() { // двигатель можно задать в конструкторе, врятли его будут изменять на ходу
    return engine;
  }

  public void setEngine(Engine engine) {  // см. комментарий выше
    this.engine = engine;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

class LightWeightCar extends Car implements Moveable{ // возможно забыли имплементировать интерфейс Stopable, хотя может этой реализации этого и не нужно

  @Override    // лишняя аннолация, компилятор не поймет что переопределяется
  void open() { // лучше добавить модификатор в явном виде
    System.out.println("Car is open");
  }

  @Override
  public void move() {
    System.out.println("Car is moving");
  }

}
0 // лишний символ, будет ошибка при компиляции
class Lorry extends Car, Moveable, Stopable { // множественное наследование запрещено, д.б. 'implements Moveable, Stopable'; Странное название

  public void move(){
    System.out.println("Car is moving");
  }

  public void stop(){
    System.out.println("Car is stop");
  }

  // нет переопределения абстрактного метода open()
}

