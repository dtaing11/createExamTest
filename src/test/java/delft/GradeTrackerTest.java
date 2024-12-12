package delft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GradeTrackerTest {
    @Test
    public void testAddGrade() {
        Course course = new Course("Math");
        course.addGrade(95, 3);
        List<Double> expectedGrades = Arrays.asList(95.0);
        assertEquals(expectedGrades, course.getGrades());
    }

    @MethodSource("generator")
    @ParameterizedTest()
    public void testCalculateGPA(List<Double> grades, List<Integer> weights, double expectedGPA) {
        // Arrange
        GradeTracker tracker = new GradeTracker();
        tracker.addCourse("Math", grades, weights);

        // Act
        double actualGPA = tracker.calculateGPA();

        // Assert
        assertEquals(expectedGPA, actualGPA, 0.01);
    }
    private static List<Object[]> generator() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(97.0, 97.0, 97.0,97.0,97.0), Arrays.asList(3, 3, 3,3,3), 4.25}, // grades, weights, expected GPA
                {Arrays.asList(80.0, 80.0, 80.0,80.0,80.0), Arrays.asList(3, 3, 3,3,3), 2.7}, // another case
                {Arrays.asList(100.0, 90.0, 95.0,95.0,95.9), Arrays.asList(3, 3, 3,3,3), 4.0},  // another case
                {Arrays.asList(93.0, 93.0, 93.0,93.0,93.0), Arrays.asList(3, 3, 3,3,3), 4.0},  // another case
                {Arrays.asList(70.0, 70.0 ), Arrays.asList(3, 3), 1.7},
                {Arrays.asList(87.0, 87.0 ), Arrays.asList(3, 3), 3.3},
                {Arrays.asList(73.0, 73.0 ), Arrays.asList(3, 3), 2.0},
                {Arrays.asList(77.0, 77.0 ), Arrays.asList(3, 3), 2.3},
                {Arrays.asList(83.0, 83.0 ), Arrays.asList(3, 3), 3.0},
                {Arrays.asList(67.0, 67.0 ), Arrays.asList(3, 3), 1.3},
                {Arrays.asList(63.0, 63.0 ), Arrays.asList(3, 3), 1},
                {Arrays.asList(90.0, 90.0 ), Arrays.asList(3, 3), 3.7},
                {Arrays.asList(60.0, 60.0 ), Arrays.asList(3, 3), 0.7},
                {Arrays.asList(50.0  , 50.0 ), Arrays.asList(3, 3), 0.0},


// another case

        });
    }

    @Test
    public void addGrade()
    {
        GradeTracker tracker = new GradeTracker();
        List<Double> grades = Arrays.asList(90.0, 85.0, 95.0);
        List<Integer> weights = Arrays.asList(3, 2, 4);
        tracker.addCourse("Math", grades, weights);

        tracker.addGrade("Math", 90.0,3);
    }
    @Test
    public void CalculateForA()
    {
        GradeTracker tracker = new GradeTracker();
        List<Double> grades = Arrays.asList(90.0, 85.0, 95.0,96.0,90.0);
        List<Integer> weights = Arrays.asList(3, 2, 4,3,3);
        tracker.addCourse("Math", grades, weights);
        assertEquals (88.13333333333334,tracker.calculateRequiredFinalForA("Math"));

    }

    @Test
    public void getCourseAButNoCourse()
    {
        GradeTracker tracker = new GradeTracker();
        assertThrows(Throwable.class, () -> {
            tracker.calculateRequiredFinalForA("Math");
        });
    }



    @Test
    public void testCalculateCurrentGrade() {
        Course course = new Course("Math");
        course.addGrade(95, 3);
        course.addGrade(85, 2);
        assertEquals(91.0, course.calculateCurrentGrade(), 0.01);
    }

    @Test
    public void testHasAtLeastFiveGrades() {
        Course course = new Course("Math");
        for (int i = 0; i < 5; i++) {
            course.addGrade(90, 1);
        }
        assertTrue(course.hasAtLeastFiveGrades());
    }

    @Test
    public void testGetNumberOfGrades() {
        Course course = new Course("Math");
        course.addGrade(95, 3);
        assertEquals(1, course.getNumberOfGrades());
    }

    @Test
    public void testCalculateRequiredFinalScoreForA() {
        Course course = new Course("Math");
        for (int i = 0; i < 5; i++) {
            course.addGrade(80, 1);
        }
        assertEquals(100, course.calculateRequiredFinalScoreForA(), 0.01);
    }

    @Test
    public void testCalculateGPA() {
        Course course = new Course("Math");
        course.addGrade(95, 3);
        assertEquals(4.0, course.calculateGPA(), 0.01);
    }

    @Test
    public void addEmptyCourse()
    {
        Course course = new Course("");
        assertEquals("", course.getCourseName());
    }

    @Test public void testAddCourse_WithValidData() {
        GradeTracker tracker = new GradeTracker();
        List<Double> grades = Arrays.asList(90.0, 85.0, 95.0);
        List<Integer> weights = Arrays.asList(3, 2, 4);
        tracker.addCourse("Math", grades, weights);
        assertTrue(tracker.courseExists("Math"));
        assertTrue(tracker.getCoursesWithFewerThanFiveGrades().size() != 0 );

    }

    @Test
    public void testMultipleCourse()
    {
        GradeTracker tracker = new GradeTracker();
        List<Double> grades = Arrays.asList(90.0, 85.0, 95.0);
        List<Integer> weights = Arrays.asList(3, 2, 4);
        List<Double> grades1 = Arrays.asList(90.0, 85.0, 95.0,78.9,70.9,80.9);
        List<Integer> weights1 = Arrays.asList(3, 2, 4,1,3,6);
        List<Double> grades2 = Arrays.asList(102.0, 234.0, 95.0);
        List<Integer> weights2 = Arrays.asList(3, 2, 4);
        tracker.addCourse("Math", grades, weights);
        tracker.addCourse("Science", grades1, weights1);
        tracker.addCourse("comp", grades2, weights2);
        assertTrue(tracker.courseExists("comp"));
        assertTrue(tracker.getCoursesWithFewerThanFiveGrades().size() == 2);

    }

    @Test
    public void WeightandGradeDifferent()
    {
        GradeTracker tracker = new GradeTracker();
        List<Double> grades1 = Arrays.asList(90.0, 85.0, 95.0,78.9,70.9,80.9);
        List<Integer> weights1 = Arrays.asList(3, 2);
        assertThrows(Throwable.class, () -> {
            tracker.addCourse("Math", grades1, weights1);
        });
    }
    @Test
    public void testAddGradeWithNegativeGrade() {
        Course course = new Course("Math");
        // Add invalid negative grade
      course.addGrade(-10, 3);
      course.getGrades();
      List<Double> expectedGrades = Arrays.asList(-10.0);
      assertEquals(expectedGrades,course.getGrades());
    }

    @Test
    public void testAddCourse_EmptyGradesAndWeights() {
        GradeTracker tracker = new GradeTracker();
        List<Double> grades = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();

        tracker.addCourse("Math", grades, weights);
        // Make sure the course is added successfully even with empty grades/weights
        assertTrue(tracker.courseExists("Math"));
        assertTrue(tracker.getCoursesWithFewerThanFiveGrades().contains("Math"));
    }



    @Test
    public void testCalculateRequiredFinalForA() {
        GradeTracker tracker = new GradeTracker();

        List<Double> grades = Arrays.asList(80.0, 85.0, 70.0);
        List<Integer> weights = Arrays.asList(3, 3, 3);

        // Add a course
        tracker.addCourse("Math", grades, weights);

        assertThrows(Throwable.class, () -> {
            tracker.calculateRequiredFinalForA("Math");
        });
    }


    @Test
    public void testCourseExists() {
        GradeTracker tracker = new GradeTracker();

        List<Double> grades = Arrays.asList(90.0, 85.0, 95.0);
        List<Integer> weights = Arrays.asList(3, 2, 4);

        // Add a course
        tracker.addCourse("Math", grades, weights);

        // Verify that the course exists
        assertTrue(tracker.courseExists("Math"));

        // Verify that a non-existing course returns false
        assertFalse(tracker.courseExists("History"));
    }







    @Test
    public void testConvertPercentageToGPA() {
        Course course = new Course("Math");
        course.addGrade(85, 3);
        assertEquals(3.0, course.calculateGPA(), 0.01);
    }
    @Test public void testCalculateCurrentGrade_NoGrades() { Course course = new Course("Math"); assertEquals(0, course.calculateCurrentGrade(), 0.01); }
    @Test public void testAddGrade_NegativeWeight() { Course course = new Course("Math"); course.addGrade(95, -3); List<Double> expectedGrades = Arrays.asList(95.0); assertEquals(expectedGrades, course.getGrades()); assertEquals(95.0, course.calculateCurrentGrade(), 0.01); }
    @Test public void testCalculateRequiredFinalScoreForA_ExactlyFiveGrades() { Course course = new Course("Math"); for (int i = 0; i < 5; i++) { course.addGrade(85, 1); } assertEquals(95, course.calculateRequiredFinalScoreForA(), 0.01); }
    @Test public void testCalculateRequiredFinalScoreForA_LessThanFiveGrades() { Course course = new Course("Math"); for (int i = 0; i < 4; i++) { course.addGrade(85, 1); } assertThrows(IllegalStateException.class, course::calculateRequiredFinalScoreForA); }
    @Test public void testAddGrade_CourseNotFound() { GradeTracker tracker = new GradeTracker(); assertThrows(IllegalArgumentException.class, () -> tracker.addGrade("NonExistentCourse", 90, 3)); }
    @Test public void testCalculateGPA_NoCourses() { GradeTracker tracker = new GradeTracker(); assertEquals(0, tracker.calculateGPA(), 0.01); }
}

