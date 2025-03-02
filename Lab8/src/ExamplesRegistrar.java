import tester.*;

// represents as course
class Course {

  String name;
  Instructor prof;
  IList<Student> students;

  Course(String name, Instructor prof) {
    if (prof == null) {
      throw new IllegalArgumentException
      ("No Course can be constructed without an Instructor available to teach it");
    }
    this.name = name;
    this.prof = prof;
    this.students = new MtList<>();
  }

  // adds student to list of students for that course & students list of courses
  void addStudent(Student student) {
    if (!this.students.contains(student)) {
      this.students = new ConsList<>(student, this.students);
      student.courses = new ConsList<>(this, student.courses);
    }
  }

}

// represents an instructor
class Instructor{
  String name;
  IList<Course> courses;

  Instructor(String name) {
    this.name = name;
    this.courses = new MtList<>();
  }
}

// represents a student
class Student {

  String name;
  int ID;
  IList<Course> courses;

  Student(String name, int ID) {
    this.name = name;
    this.ID = ID;
    this.courses = new MtList<>();
  }

  // enrolls a student in a given course
  void enroll(Course c) {
    c.addStudent(this);
  }

  // helper to see if student is enrolled in a class
  boolean isEnrolledIn(Course c) {
    return this.courses.contains(c);
  }

  // checks if given student is in any of the same classes as this student
  boolean classmates(Student c) {
    return this.courses.classmateHelper(c.courses);
  }

  // checks if given student is in more than 1 of this instructor's courses
  boolean dejavu(Student c) {
    return this.courses.duplicateInstructor();
  }

}

//represents a generic list
interface IList<T> {

  // checks if item is in the list
  boolean contains(T item);

  // checks if the given course is in this course
  boolean classmateHelper(IList<T> item);

  // counts the amount of courses that are shared w/ professor
  boolean countSharedInstructors(Instructor prof);

  // checks if the instructor is in the list
  boolean duplicateInstructor();

}

//represents a generic empty list
class MtList<T> implements IList<T> {

  MtList() {}

  // checks if item is in the list
  public boolean contains(T item) {
    return false;
  }

  // checks if the given course is in this course
  public boolean classmateHelper(IList<T> item) {
    return false;
  }

  // counts the amount of courses that are shared
  public boolean countSharedInstructors(Instructor prof) {
    return false;
  }

  // checks if the instructor is in the list
  public boolean duplicateInstructor() {
    return false;
  }

}

//represents a generic non-empty list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  // checks if item is in the list
  public boolean contains(T item) {
    return first.equals(item) || rest.contains(item);
  }

  // checks if the given course is in this course
  public boolean classmateHelper(IList<T> item) {
    return item.contains(this.first) || item.classmateHelper(this.rest);
  }

  // counts the amount of courses that are shared
  public boolean countSharedInstructors(Instructor prof) {
    // TODO
    return false;
  }

  // checks if the instructor is in the list
  public boolean duplicateInstructor() {
    // TODO
    return false;
  }
}

class ExamplesRegistrar {
  Student student1 = new Student("Bill", 001);
  Student student2 = new Student("John", 002);
  Student student3 = new Student("James", 003);
  Student student4 = new Student("Thomas", 004);
  Student student5 = new Student("Noah", 005);

  Instructor instructor1 = new Instructor("Prof 1");
  Instructor instructor2 = new Instructor("Prof 2");

  Course course1 = new Course("Fundies 1", instructor1);
  Course course2 = new Course("Fundies 2", instructor1);
  Course course3 = new Course("Fundies 3", instructor2);
  Course course4 = new Course("Fundies 4", instructor2);
 
  void createFakeCourse() {
    new Course("Fake Course", null);
  }

  void testConstructor(Tester t) {
    t.checkExpect(student2.name, "John");
    t.checkExpect(student2.ID, 002);

    t.checkExpect(course1.name, "Fundies 1");
    t.checkExpect(course1.prof, instructor1);

    t.checkException(
        new IllegalArgumentException(
            "No Course can be constructed without an Instructor available to teach it"),
        this, "createFakeCourse");
  }

  // tests for enroll method
  void testEnroll(Tester t) {

    this.student1 = new Student("Bill", 001);
    this.student2 = new Student("John", 002);
    this.student3 = new Student("James", 003);
    this.student4 = new Student("Thomas", 004);
    this.student5 = new Student("Noah", 005);

    this.instructor1 = new Instructor("Prof 1");
    this.instructor2 = new Instructor("Prof 2");

    this.course1 = new Course("Fundies 1", instructor1);
    this.course2 = new Course("Fundies 2", instructor1);
    this.course3 = new Course("Fundies 3", instructor2);
    this.course4 = new Course("Fundies 4", instructor2);


    // before the students get enrolled in the class
    t.checkExpect(student1.courses.contains(course1), false);
    t.checkExpect(course1.students.contains(student1), false);
    t.checkExpect(student2.courses.contains(course2), false);
    t.checkExpect(course2.students.contains(student2), false);
    t.checkExpect(student3.courses.contains(course4), false);

    student1.enroll(course1);
    student1.enroll(course1);
    student2.enroll(course2);
    student3.enroll(course1);
    student3.enroll(course4);

    // after the students get enrolled in the class
    t.checkExpect(student1.courses.contains(course1), true);
    t.checkExpect(course1.students.contains(student1), true);
    t.checkExpect(student2.courses.contains(course2), true);
    t.checkExpect(course2.students.contains(student2), true);
    t.checkExpect(student3.courses.contains(course4), true);
  }

  // tests for classmates method
  void testClassmates(Tester t) {

    this.student1 = new Student("Bill", 001);
    this.student2 = new Student("John", 002);
    this.student3 = new Student("James", 003);
    this.student5 = new Student("Noah", 005);

    this.instructor1 = new Instructor("Prof 1");
    this.instructor2 = new Instructor("Prof 2");

    this.course1 = new Course("Fundies 1", instructor1);
    this.course2 = new Course("Fundies 2", instructor1);
    this.course3 = new Course("Fundies 3", instructor2);
    this.course4 = new Course("Fundies 4", instructor2);

    // before the students get enrolled in the class
    t.checkExpect(student1.classmates(student3), false);

    student1.enroll(course1);
    student3.enroll(course1);

    // after the students get enrolled in the class
    t.checkExpect(student1.classmates(student3), true);

    // new student (not enrolled)
    this.student4 = new Student("Thomas", 004);
    t.checkExpect(student4.classmates(student1), false);
  }

  // tests for dejavu method
  void testDejavu(Tester t) {

    this.student3 = new Student("James", 003);
    this.student5 = new Student("Noah", 005);

    this.course1 = new Course("Fundies 1", instructor1);
    this.course2 = new Course("Fundies 2", instructor1);
    this.course3 = new Course("Fundies 3", instructor2);

    // before the students get enrolled in the class
    t.checkExpect(student3.dejavu(student3), true);
    t.checkExpect(student1.dejavu(student1), true);

    student3.enroll(course1);
    student3.enroll(course2);
    student1.enroll(course3);
    student1.enroll(course4);

    // after the students get enrolled in the class
    t.checkExpect(student3.dejavu(student3), true);
    t.checkExpect(student1.dejavu(student1), true);
  }
}
