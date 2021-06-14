package ru.devstend.practice.five.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import ru.devstend.practice.five.persist.dao.GenericDao;
import ru.devstend.practice.five.persist.entity.Student;

public class TestGenericDao {

    public static void main(String[] args) {
      GenericDao<Student, UUID> genericDao = new GenericDao<>(Student.class);

      Student student = new Student();
      student.setName("Kirill");
      student.setMark(5);

      // Вне транзакции данные не сохраняются, но находятся в IdentityMap
      genericDao.openCurrentSession();
      System.out.println("==========================TEST-1===========================");
      UUID uuid = genericDao.persist(student);
      System.out.println(uuid);
      Student std1 = genericDao.findById(uuid);
      System.out.println(std1);
      // при закрытии сессии данные пропадают
      genericDao.closeCurrentSession();

      // после окончания сессии данных нет
      genericDao.openCurrentSession();
      List<Student> all1 = genericDao.findAll();
      genericDao.closeCurrentSession();
      System.out.println("Общее количество студентов = " + all1.size());
      for (Student std : all1) {
        System.out.println(std);
      }

      // В рамках транзакции данные помещаются в базу
      genericDao.openCurrentSessionWithTransaction();
      System.out.println("==========================TEST-2===========================");
      List<UUID> uuids = new ArrayList<>();
      for (int i = 0; i < 1000; i++) {
        Student tmp = new Student();
        tmp.setName("Kirill" + i);
        tmp.setMark(i);
        uuids.add(genericDao.persist(tmp));
      }
      genericDao.closeCurrentSessionWithTransaction();

      // после окончания сессии данные есть
      genericDao.openCurrentSession();
      List<Student> all2 = genericDao.findAll();
      genericDao.closeCurrentSession();
      System.out.println("Общее количество студентов = " + all2.size());
      for (Student std : all2) {
        System.out.println(std);
      }

      // Удаляем студентов
      genericDao.openCurrentSessionWithTransaction();
      System.out.println("==========================TEST-3===========================");
        genericDao.deleteAll();
      genericDao.closeCurrentSessionWithTransaction();

      genericDao.openCurrentSession();
      List<Student> all3 = genericDao.findAll();
      genericDao.closeCurrentSession();
      System.out.println("Общее количество студентов = " + all3.size());

    }

}
