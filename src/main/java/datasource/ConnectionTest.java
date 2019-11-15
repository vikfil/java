package datasource;

import model.*;
import repository.*;

import java.sql.SQLException;
import java.util.List;

public class ConnectionTest {
    public static void main(String[] args) {
    // String m = Week.MONDAY.getValue();
        System.out.println(Week.valueOf("monday".toUpperCase()));
        //   try{
//            ScheduleDataAccess schedule = new ScheduleDataAccess();
//            ClassroomRepository classroomRepository = new ClassroomRepository();
//            GroupRepository groupRepository = new GroupRepository();
//            LectorRepository  lectorRepository = new LectorRepository();
//            SubjectRepository subjectRepository = new SubjectRepository();

//            Subject subjectHistory = subjectRepository.addSubject(new Subject("History"));
//            Subject subjectMath = subjectRepository.addSubject(new Subject("Math"));
//           Group groupOne = groupRepository.addGroup(new Group("1", "one"));
//            Group groupTwo = groupRepository.addGroup(new Group("2", "two"));
//            Lector lectorIgor = lectorRepository.addLector(new Lector("Igor", "Mykolayovich"));
//            Lector lectorVictor = lectorRepository.addLector(new Lector("Viktor", "Petrovich"));
//            Classroom classroom13 = classroomRepository.addClassroom(new Classroom("13"));
//            Classroom classroom1 = classroomRepository.addClassroom(new Classroom("1"));

    //        Subject subjectHistory = SubjectRepository.subjectById(14);
//      /      Subject subjectMath = subjectRepository.selectSubjectById(15);
        //          Group groupOne = groupRepository.selectGroupById(15);
//            Group groupTwo = groupRepository.selectGroupById(16);
        //        Lector lectorIgor = lectorRepository.selectLectorById(14);
//            Lector lectorViktor = lectorRepository.selectLectorById(15);
//            Classroom classroom13 = classroomRepository.selectClassroomById(13);
//            Classroom classroom1 = classroomRepository.selectClassroomById(14);


       //  Lesson lessonFirst = new Lesson(Week.MONDAY, 1, subjectHistory, groupOne, lectorIgor, classroom13);
        // System.out.println("Expected true: - " + schedule.addLesson(lessonFirst));

//            Lesson lessonEqualsFirst = new Lesson(Week.MONDAY, 1, subjectHistory, groupOne, lectorIgor, classroom13);
        //           System.out.println("Expected false: - " + schedule.addLesson(lessonEqualsFirst));
//
        //    Lesson anotherLessonNumber = new Lesson(Week.MONDAY, 2, subjectHistory, groupOne, lectorIgor, classroom13);
        //     System.out.println("Expected true: - " + schedule.addLesson(anotherLessonNumber));
////
        //      Lesson anotherSubject = new Lesson(Week.MONDAY, 1, subjectMath, groupOne, lectorIgor, classroom13);
        //         System.out.println("Expected false: - " + schedule.addLesson(anotherSubject));
//
        //    Lesson anotherGroup = new Lesson(Week.MONDAY, 1, subjectHistory, groupTwo, lectorIgor, classroom13);
        //     System.out.println("Expected true: - " + schedule.addLesson(anotherGroup));
        //       schedule.addLesson(anotherGroup);
//
        //      Lesson anotherLector = new Lesson(Week.MONDAY, 1, subjectHistory, groupOne, lectorViktor, classroom13);
        //     System.out.println("Expected false: - " + schedule.addLesson(anotherLector));
//
        //        Lesson anotherClassroom = new Lesson(Week.MONDAY, 1, subjectHistory, groupOne, lectorIgor, classroom1);
//            System.out.println("Expected false: - " + schedule.addLesson(anotherClassroom));
//
        //    Lesson iniqualitySubjectLectorClassroomGroup = new Lesson(Week.MONDAY, 1, subjectMath, groupTwo, lectorViktor, classroom1);
        //   System.out.println("Expected false: - " + schedule.addLesson(iniqualitySubjectLectorClassroomGroup));
        //      schedule.addLesson(iniqualitySubjectLectorClassroomGroup);
//
        //  Lesson sameGroupWithiniqualitySubjectLectorClassroom = new Lesson(Week.MONDAY, 1, subjectMath, groupOne, lectorViktor, classroom1);
        //  System.out.println("Expected false: - " + schedule.addLesson(sameGroupWithiniqualitySubjectLectorClassroom));
//


//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}