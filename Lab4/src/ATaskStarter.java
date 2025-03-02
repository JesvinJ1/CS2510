import tester.Tester;
import java.awt.Color;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.OverlayOffsetImage;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

// represents a group of tasks that have a title
class Group {
  String title;
  ILoTask tasks;

  Group(String t, ILoTask tasks) {
    this.title = t;
    this.tasks = tasks;
  }

  /* CLASS TEMPLATE
   * 
   * FIELDS:
   * ... this.title ...                      -- String
   * ... this.tasks ...                      -- ILoTask
   * 
   * METHODS:
   * ... this.rotate() ...                   -- Group
   * ... this.flip() ...                     -- Group
   * ... this.draw() ...                     -- WorldImage
   * 
   * METHODS ON/OF/FOR FIELDS:
   * ... this.tasks.draw() ...               -- WorldImage
   * ... this.tasks.flipFirst() ...          -- ILoTask
   * ... this.tasks.rotate() ...             -- ILoTask
   * ... this.tasks.moveToEnd(ITask) ...     -- ILoTask
   * 
   */

  // rotate the list of tasks in this group
  Group rotate() {
    return new Group(this.title, this.tasks.rotate());
  }

  // flip the first task to done or not done
  Group flip() {
    return new Group(this.title, this.tasks.flipFirst());
  }

  // draw the the current state of this group of tasks
  WorldImage draw() {
    return this.tasks.draw();
  }
  
  boolean haveWorkedWith(String partnerName) {
    return this.tasks.haveWorkedWith(partnerName);
  }
  
}


// represents the different kinds of class tasks
interface ITask {
  // flip the completeness of a task
  ITask flip();

  // draws this task as text
  WorldImage draw();
  
  // produces the number of days this task is overdue
  int daysOverDue(int num);
  
  // TODO
  boolean isOverdue(int today);
  
  // TODO
  double computePercentDeduction(int today);
  
  // TODO
  String haveWorkedWith(String group);
}

// represents a homework task
class HomeworkTask extends ATask {
  String partnerName;

  HomeworkTask(String description, boolean isDone, int dateCreated, String partnerName) {
    super (description, isDone, dateCreated);
    this.partnerName = partnerName;
  }

  /* CLASS TEMPLATE
   * 
   * FIELDS:
   * ... this.description ...                    -- String
   * ... this.isDone ...                         -- boolean
   * ... this.dateCreated ...                    -- int
   * ... this.partnerName ...                    -- String
   * 
   * METHODS:
   * ... this.flip() ...                         -- ITask
   * ... this.draw() ...                         -- WorldImage
   * ... this.drawCheckbox() ...                 -- WorldImage
   * ... this.daysOverDue(int num) ...                  -- int
   * ... this.computePercentDeduction(int today)      --double
   * METHODS ON/OF/FOR FIELDS:
   * ... none ...
   * 
   */

  // flip the completeness of this task
  public ITask flip() {
    return new HomeworkTask(this.description, !this.isDone, this.dateCreated, this.partnerName);
  }

  // draw this task as text
  public WorldImage draw() {
    WorldImage bg = new RectangleImage(300, 200, "solid", Color.cyan);
    bg = new OverlayImage(new TextImage(this.description, 20, Color.black), bg);
    bg = new OverlayOffsetImage(this.drawCheckbox(), 0, -60, bg);
    return bg;
  }


  @Override
  public int daysOverDue(int num) {
    if (this.isDone) {
      return 0;
    } else {
      return num - (1 + this.dateCreated);
    }
  }

  @Override
  public double computePercentDeduction(int today) {
    int overdueDays = this.daysOverDue(today);
    return overdueDays * 10.0;
  }

  // attempt at method
  public String haveWorkedWith(String group) {
    // TODO Auto-generated method stub
    return group;
  }
}

// represents a lab task
class LabTask extends ATask {
  
  String partnerName;

  LabTask(String description, boolean isDone, int dateCreated, String partnerName) {
    super(description, isDone, dateCreated);
    this.partnerName = partnerName;
  }

  /* CLASS TEMPLATE
   * 
   * FIELDS:
   * ... this.description ...                    -- String
   * ... this.isDone ...                         -- boolean
   * ... this.dateCreated ...                    -- int
   * ... this.partnerName ...                    -- String
   * 
   * METHODS:
   * ... this.flip() ...                         -- ITask
   * ... this.draw() ...                         -- WorldImage
   * ... this.drawCheckbox() ...                 -- WorldImage
   * ... this.daysOverDue(int num) ...           -- int
   * METHODS ON/OF/FOR FIELDS:
   * ... none ...
   * 
   */

  // flip the completeness of this task
  public ITask flip() {
    return new LabTask(this.description, !this.isDone, this.dateCreated, this.partnerName);
  }

  @Override
  public int daysOverDue(int num) {
    if (this.isDone) {
      return 0;
    } else {
      return num - (7 + this.dateCreated);
    }
  }

  @Override
  public double computePercentDeduction(int today) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String haveWorkedWith(String group) {
    // TODO Auto-generated method stub
    return null;
  }
}

// represents an in-class task
class InClassTask extends ATask {

  InClassTask(String d, boolean isDone, int dateCreated) {
    super(d, isDone, dateCreated);
  }

  /* CLASS TEMPLATE
   * 
   * FIELDS:
   * ... this.description ...                    -- String
   * ... this.isDone ...                         -- boolean
   * ... this.dateCreated ...                    -- int
   * 
   * METHODS:
   * ... this.flip() ...                         -- ITask
   * ... this.draw() ...                         -- WorldImage
   * ... this.drawCheckbox() ...                 -- WorldImage
   * ... this.daysOverDue(int num) ...           -- int
   * ... this.isOverdue(int today) ...           -- boolean
   * METHODS ON/OF/FOR FIELDS:
   * ... none ...
   * 
   */

  // flip the completeness of this task
  public ITask flip() {
    return new InClassTask(this.description, !this.isDone, this.dateCreated);
  }

  // produces the number of days this task is overdue
  public int daysOverDue(int num) {
    if (this.isDone) {
      return 0;
    } else {
      return num - (1 + this.dateCreated);
    }
  }

  @Override
  public boolean isOverdue(int today) {
    return !this.isDone && this.daysOverDue(today) > 0;
  }

  @Override
  public double computePercentDeduction(int today) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String haveWorkedWith(String group) {
    // TODO Auto-generated method stub
    return null;
  }
}

abstract class ATask implements ITask {
  
  String description;
  boolean isDone;
  int dateCreated;
  
  ATask(String d, boolean isDone, int dateCreated) {
    this.description = d;
    this.isDone = isDone;
    this.dateCreated = dateCreated;
  }
  
  /* CLASS TEMPLATE
   * 
   * FIELDS:
   * ... this.description ...                    -- String
   * ... this.isDone ...                         -- boolean
   * ... this.dateCreated ...                    -- int
   * ... this.daysOverDue(int num) ...           -- int
   * ... this.isOverdue(int today) ...           -- boolean
   * METHODS:
   * ... this.draw() ...
   * ... this.drawCheck() ...
   * METHODS ON/OF/FOR FIELDS:
   * ... none ...
   * 
   */
  
  // draw this task as text
  public WorldImage draw() {
    WorldImage bg = new RectangleImage(300, 200, "solid", Color.cyan);
    bg = new OverlayImage(new TextImage(this.description, 20, Color.black), bg);
    bg = new OverlayOffsetImage(this.drawCheckbox(), 0, -60, bg);
    return bg;
  }
  
  // draw the check box for this task
  public WorldImage drawCheckbox() {
    if (this.isDone) {
      return new RectangleImage(20, 20, "solid", Color.black);
    }
    else {
      return new RectangleImage(20, 20, "outline", Color.black);
    }
  }
  
  // produces the number of days this task is overdue
  public int daysOverDue(int num) {
    if (this.isDone) {
      return 0;
    } else {
      return num - (7 + this.dateCreated);
    }
  }
  
  // TODO
  public boolean isOverdue(int today) {
    return !this.isDone && this.daysOverDue(today) > 0;
  }
}


// represents a list of task
interface ILoTask {
  // draw the first task's description as text or "No tasks to do" for this ILoTask
  WorldImage draw();

  // flips the first task's completeness for this ILoTask
  ILoTask flipFirst();

  // moves the first task to the end of this ILoTask
  ILoTask rotate();

  // moves the given task to the end of this ILoTask
  ILoTask moveToEnd(ITask t);

}

// represents an empty list of task
class MtLoTask implements ILoTask {

  // empty constructor
  MtLoTask() {
  }

  /* CLASS TEMPLATE
   * 
   * FIELDS:
   * ... none ...
   * 
   * METHODS:
   * ... this.draw() ...               -- WorldImage
   * ... this.flipFirst() ...          -- ILoTask
   * ... this.rotate() ...             -- ILoTask
   * ... this.moveToEnd(ITask) ...     -- ILoTask
   * 
   * METHODS ON/OF/FOR FIELDS:
   * ... none ...
   * 
   */

  // render that there are no tasks to do since this list is empty
  public WorldImage draw() {
    return new TextImage("No tasks to do", 30, Color.black);
  }

  // does not flip anything because this list is empty
  public ILoTask flipFirst() {
    return new MtLoTask();
  }

  // does not rotate anything because this list is empty
  public ILoTask rotate() {
    return new MtLoTask();
  }

  // adds the given task to this empty list
  // (since it's already at the end of the list)
  public ILoTask moveToEnd(ITask t) {
    /*
     * METHOD TEMPLATE: everything in the class template for ConsLoTask, plus
     *
     * PARAMETERS: 
     * ... t ...                                -- ITask
     *
     * METHODS ON/OF/FOR PARAMETERS: 
     * ... t.flip() ...                         -- ITask
     * ... t.draw() ...                         -- WorldImage
     * ... t.drawCheckbox() ...                 -- WorldImage
     * 
     */
    return new ConsLoTask(t, new MtLoTask());
  }
}

// represents a non-empty list of task
class ConsLoTask implements ILoTask {
  ITask first;
  ILoTask rest;

  ConsLoTask(ITask first, ILoTask rest) {
    this.first = first;
    this.rest = rest;
  }

  /* CLASS TEMPLATE
   * 
   * FIELDS:
   * ... this.first ...                                -- ITask
   * ... this.rest ...                                 -- ILoTask
   * 
   * METHODS:
   * ... this.draw() ...                               -- WorldImage
   * ... this.flipFirst() ...                          -- ILoTask
   * ... this.rotate() ...                             -- ILoTask
   * ... this.moveToEnd(ITask) ...                     -- ILoTask
   * 
   * METHODS ON/OF/FOR FIELDS:
   * ... this.first.flip() ...                         -- ITask
   * ... this.first.draw() ...                         -- WorldImage
   * ... this.first.drawCheckbox() ...                 -- WorldImage
   * 
   * ... this.rest.draw() ...                          -- WorldImage
   * ... this.rest.flipFirst() ...                     -- ILoTask
   * ... this.rest.rotate() ...                        -- ILoTask
   * ... this.rest.moveToEnd(ITask) ...                -- ILoTask
   * 
   */

  // draw the first task to do
  public WorldImage draw() {
    return this.first.draw();
  }

  // flips the first task's completeness in this non-empty list
  public ILoTask flipFirst() {
    return new ConsLoTask(this.first.flip(), this.rest);
  }

  // rotates the first task to the end of this non-empty list
  public ILoTask rotate() {
    return this.rest.moveToEnd(this.first);
  }

  // adds the given task to the end of this non-empty list
  public ILoTask moveToEnd(ITask t) {
    /*
     * METHOD TEMPLATE: everything in the class template for ConsLoTask, plus
     *
     * PARAMETERS: 
     * ... t ...                               -- ITask
     *
     * METHODS ON/OF/FOR PARAMETERS: 
     * ... t.flip() ...                        -- ITask
     * ... t.draw() ...                        -- WorldImage
     * ... t.drawCheckbox() ...                -- WorldImage
     * 
     */
    return new ConsLoTask(this.first, this.rest.moveToEnd(t));
  }
}

// represents a World program for a group of tasks
class TaskWorld extends World {
  Group taskGroup;

  TaskWorld(Group g) {
    this.taskGroup = g;
  }

  // renders the state of this TaskWorld
  public WorldScene makeScene() {
    WorldScene ws = new WorldScene(600, 400);
    return ws.placeImageXY(this.taskGroup.draw(), 300, 200);
  }

  // handles key events for this TaskWorld
  public World onKeyEvent(String key) {
    if (key.equals("right")) {
      return new TaskWorld(this.taskGroup.rotate());
    }
    else if (key.equals(" ")) {
      return new TaskWorld(this.taskGroup.flip());
    }
    else
      return this;
  }
}

class Examples {
  ITask homework1 = new HomeworkTask("Variables and Data Types", false, 14, "Megan");
  ITask homework2 = new HomeworkTask("Linked Lists and Conditionals", true, 15, "Steven");
  ITask homework3 = new HomeworkTask("Debugging", false, 16, "Megan");

  ITask lab1 = new LabTask("Calculator Program", false, 15, "Susan");
  ITask lab2 = new LabTask("Guess the Number Game", true, 17, "Susan");
  ITask lab3 = new LabTask("File Handling", false, 17, "Bob");

  ITask inClass1 = new InClassTask("Sorting Algorithm Simulation", false, 15);
  ITask inClass2 = new InClassTask("Flowchart Design", false, 17);
  ITask inClass3 = new InClassTask("Pair Programming Exercise", true, 16);

  ILoTask mt = new MtLoTask();
  ILoTask mondayToDoList = new ConsLoTask(this.homework1, new ConsLoTask(this.lab1, this.mt));
  ILoTask mondayToDoListRotated = new ConsLoTask(this.lab1, new ConsLoTask(this.homework1, this.mt));
  ILoTask tuesdayToDoList = new ConsLoTask(this.homework2,
      new ConsLoTask(this.lab2, new ConsLoTask(this.inClass1, this.mt)));
  ILoTask wednesdayToDoList = new ConsLoTask(this.homework3, new ConsLoTask(this.inClass3, this.mt));

  Group mondayTaskGroup = new Group("Monday Plan", this.mondayToDoList);
  Group tuesdayTaskGroup = new Group("Tuesday Plan", this.tuesdayToDoList);
  Group wednesdayTaskGroup = new Group("Wednesday Plan", this.wednesdayToDoList);
  Group none = new Group("finished everything", this.mt);
  
  
  // tests for daysOverDue
  boolean testDaysOverDue(Tester t) {
    return t.checkExpect(this.homework1.daysOverDue(14), -1)
        && t.checkExpect(this.lab1.daysOverDue(10), -12)
        && t.checkExpect(this.inClass1.daysOverDue(5), -11);
  }
  
  // tests for isOverdue
  boolean testIsOverDue(Tester t) {
    return t.checkExpect(this.homework1.isOverdue(0), false)
        && t.checkExpect(this.homework1.isOverdue(30), true)
        && t.checkExpect(this.lab1.isOverdue(25), true)
        && t.checkExpect(this.inClass1.isOverdue(17), true);
  }
  
  // tests for computePercentDeduction
  boolean testComputePercentDeduction(Tester t) {
    return t.checkExpect(this.homework1.computePercentDeduction(10), -50.0)
        && t.checkExpect(this.lab1.computePercentDeduction(1), 0.0)
        && t.checkExpect(this.inClass1.computePercentDeduction(0), 0.0);
  }
  // test ITask.flip()
  boolean testITaskFlip(Tester t) {
    return t.checkExpect(this.homework1.flip(), new HomeworkTask("Variables and Data Types", true, 14, "Megan"))
        && t.checkExpect(this.lab1.flip(), new LabTask("Calculator Program", true, 15, "Susan"))
        && t.checkExpect(this.inClass1.flip(), new InClassTask("Sorting Algorithm Simulation", true, 15))
        && t.checkExpect(this.homework1.flip().flip(), this.homework1);
  }

  // test Group.flip()
  boolean testGroupFlip(Tester t) {
    return t.checkExpect(this.none.flip(), this.none)
        && t.checkExpect(this.mondayTaskGroup.flip(), 
            new Group("Monday Plan", 
                new ConsLoTask(new HomeworkTask("Variables and Data Types", true, 14, "Megan"),
                    new ConsLoTask(this.lab1, this.mt))))
        && t.checkExpect(this.tuesdayTaskGroup.flip().flip(), this.tuesdayTaskGroup);
  }

  // test ILoTask.flipFirst()
  boolean testILoTaskFlipFirst(Tester t) {
    return t.checkExpect(this.mt.flipFirst(), this.mt)
        && t.checkExpect(this.mondayToDoListRotated.flipFirst(), 
            new ConsLoTask(new LabTask("Calculator Program", true, 15, "Susan"), 
                new ConsLoTask(this.homework1, this.mt)));
  }

  // test Group.rotate()
  boolean testGroupRotate(Tester t) {
    return t.checkExpect(this.none.rotate(), this.none)
        && t.checkExpect(this.mondayTaskGroup.rotate(), new Group("Monday Plan", this.mondayToDoListRotated));
  }

  // test ILoTask.rotate()
  boolean testILoTaskRotate(Tester t) {
    return t.checkExpect(this.mt.rotate(), this.mt)
        && t.checkExpect(this.mondayToDoList.rotate(), this.mondayToDoListRotated)
        && t.checkExpect(this.mondayToDoListRotated.rotate(), this.mondayToDoList);
  }

  // test ILoTask.moveToEnd()
  boolean testILoTaskMoveToEnd(Tester t) {
    return t.checkExpect(this.mt.moveToEnd(this.lab3), new ConsLoTask(this.lab3, new MtLoTask()))
        && t.checkExpect(new ConsLoTask(this.homework3, this.mt).moveToEnd(this.inClass3), this.wednesdayToDoList);
  }

  // big bang
  boolean testBigBang(Tester t) {
    TaskWorld world = new TaskWorld(this.mondayTaskGroup);
    int worldWidth = 600;
    int worldHeight = 400;
    double tickRate = .1;
    return world.bigBang(worldWidth, worldHeight, tickRate);
  }
}

