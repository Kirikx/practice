package ru.devstend.practice.one.shape;

import java.awt.Point;

abstract class Shape {

  private final Point point;

  public Shape(Point point) {
    this.point = point;
  }

  public Point getPoint() {
    return point;
  }

  abstract double area();

  abstract double perimeter();
}

class Triangle extends Shape {

  private final double sideA;
  private final double sideB;
  private final double sideC;

  public Triangle(Point point, double sideA, double sideB, double sideC) {
    super(point);
    this.sideA = sideA;
    this.sideB = sideB;
    this.sideC = sideC;
  }

  @Override
  double area() {
    double p = perimeter();
    return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
  }

  @Override
  double perimeter() {
    return (sideA + sideB + sideC) / 2;
  }
}

class Rectangle extends Shape {

  private final double w;
  private final double h;

  public Rectangle(Point point, double width, double height) {
    super(point);
    w = width;
    h = height;
  }

  public double area() {
    return w * h;
  }

  public double perimeter() {
    return 2.0 * w + 2.0 * h;
  }

  public double getWidth() {
    return w;
  }

  public double getHeight() {
    return h;
  }
}

class Square extends Rectangle {

  public Square(Point point, double side) {
    super(point, side, side);
  }

  public double getSide() {
    return getWidth();
  }
}

class Circle extends Shape {

  private final double r;

  public Circle(Point point, double radius) {
    super(point);
    r = radius;
  }

  public double area() {
    return Math.PI * r * r;
  }

  public double perimeter() {
    return Math.PI * 2.0 * r;
  }

  public double getRadius() {
    return r;
  }
}

