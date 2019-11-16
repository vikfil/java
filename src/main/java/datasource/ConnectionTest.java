//package datasource;
//
//import model.*;
//import repository.*;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class ConnectionTest {
//    public static void main(String[] args) {
//    // String m = Week.MONDAY.getValue();
////        System.out.println(Week.valueOf("monday".toUpperCase()));
//         try{
////                Lector lectorIgor = LectorRepository.lectorById(14);
////                List<Lesson> list = ScheduleDataAccess.getScheduleForLector(lectorIgor);
////             System.out.println(list);
////            Lector lectorViktor = lectorRepository.selectLectorById(15);
////            Classroom classroom13 = classroomRepository.selectClassroomById(13);
////            Classroom classroom1 = classroomRepository.selectClassroomById(14);
//
//
//       //  Lesson lessonFirst = new Lesson(Week.MONDAY, 1, subjectHistory, groupOne, lectorIgor, classroom13);
//        // System.out.println("Expected true: - " + schedule.addLesson(lessonFirst));
//
////            Lesson lessonEqualsFirst = new Lesson(Week.MONDAY, 1, subjectHistory, groupOne, lectorIgor, classroom13);
//        //           System.out.println("Expected false: - " + schedule.addLesson(lessonEqualsFirst));
////
//        //    Lesson anotherLessonNumber = new Lesson(Week.MONDAY, 2, subjectHistory, groupOne, lectorIgor, classroom13);
//        //     System.out.println("Expected true: - " + schedule.addLesson(anotherLessonNumber));
//////
//        //      Lesson anotherSubject = new Lesson(Week.MONDAY, 1, subjectMath, groupOne, lectorIgor, classroom13);
//        //         System.out.println("Expected false: - " + schedule.addLesson(anotherSubject));
////
//        //    Lesson anotherGroup = new Lesson(Week.MONDAY, 1, subjectHistory, groupTwo, lectorIgor, classroom13);
//        //     System.out.println("Expected true: - " + schedule.addLesson(anotherGroup));
//        //       schedule.addLesson(anotherGroup);
////
//        //      Lesson anotherLector = new Lesson(Week.MONDAY, 1, subjectHistory, groupOne, lectorViktor, classroom13);
//        //     System.out.println("Expected false: - " + schedule.addLesson(anotherLector));
////
//        //        Lesson anotherClassroom = new Lesson(Week.MONDAY, 1, subjectHistory, groupOne, lectorIgor, classroom1);
////            System.out.println("Expected false: - " + schedule.addLesson(anotherClassroom));
////
//        //    Lesson iniqualitySubjectLectorClassroomGroup = new Lesson(Week.MONDAY, 1, subjectMath, groupTwo, lectorViktor, classroom1);
//        //   System.out.println("Expected false: - " + schedule.addLesson(iniqualitySubjectLectorClassroomGroup));
//        //      schedule.addLesson(iniqualitySubjectLectorClassroomGroup);
////
//        //  Lesson sameGroupWithiniqualitySubjectLectorClassroom = new Lesson(Week.MONDAY, 1, subjectMath, groupOne, lectorViktor, classroom1);
//        //  System.out.println("Expected false: - " + schedule.addLesson(sameGroupWithiniqualitySubjectLectorClassroom));
////
//
//
//        } catch (SQLException | ClassNotFoundException e) {
////            e.printStackTrace();
//}
//
//    }
//}