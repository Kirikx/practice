package ru.devstend.practice.one;


public class Person {

  private String firstName;
  private String lastName;
  private String middleName;
  private String country;
  private String address;
  private String phone;
  private int age;
  private String gender;

  private Person(final String firstName, final String lastName, final String middleName,
      final String country, final String address, final String phone, final int age,
      final String gender) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.country = country;
    this.address = address;
    this.phone = phone;
    this.age = age;
    this.gender = gender;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Person{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", country='" + country + '\'' +
        ", address='" + address + '\'' +
        ", phone='" + phone + '\'' +
        ", age=" + age +
        ", gender='" + gender + '\'' +
        '}';
  }

  public static Person.PersonBuilder builder() {
    return new Person.PersonBuilder();
  }

  public static class PersonBuilder {

    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    PersonBuilder() {
    }

    public Person.PersonBuilder firstName(final String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Person.PersonBuilder lastName(final String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Person.PersonBuilder middleName(final String middleName) {
      this.middleName = middleName;
      return this;
    }

    public Person.PersonBuilder country(final String country) {
      this.country = country;
      return this;
    }

    public Person.PersonBuilder address(final String address) {
      this.address = address;
      return this;
    }

    public Person.PersonBuilder phone(final String phone) {
      this.phone = phone;
      return this;
    }

    public Person.PersonBuilder age(final int age) {
      this.age = age;
      return this;
    }

    public Person.PersonBuilder gender(final String gender) {
      this.gender = gender;
      return this;
    }

    public Person build() {
      return new Person(this.firstName, this.lastName, this.middleName, this.country, this.address,
          this.phone, this.age, this.gender);
    }

    public String toString() {
      return "Person.PersonBuilder{"
          + "firstName=" + this.firstName
          + ", lastName=" + this.lastName
          + ", middleName=" + this.middleName
          + ", country=" + this.country
          + ", address=" + this.address
          + ", phone=" + this.phone
          + ", age=" + this.age
          + ", gender=" + this.gender
          + "}";
    }
  }
}
