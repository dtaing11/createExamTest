package delft;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class GradeTracker {

	private Map<String, Course> courses;

	public GradeTracker() {
		this.courses = new HashMap<>();
	}

	public void addCourse(String courseName, List<Double> grades, List<Integer> weights) {
		if (grades.size() != weights.size()) {
			throw new IllegalArgumentException("Grades and weights lists must have the same size.");
		}

		Course course = new Course(courseName);
		for (int i = 0; i < grades.size(); i++) {
			course.addGrade(grades.get(i), weights.get(i));
		}
		courses.put(courseName, course);
	}

	public void addGrade(String courseName, double grade, int weight) {
		Course course = courses.get(courseName);
		if (course == null) {
			throw new IllegalArgumentException("Course not found: " + courseName);
		}
		course.addGrade(grade, weight);
	}

	public double calculateGPA() {
		double totalGPA = 0;
		int numCourses = 0;

		for (Course course : courses.values()) {
			totalGPA += course.calculateGPA();
			numCourses++;
		}

		return numCourses == 0 ? 0 : totalGPA / numCourses;
	}



	public List<String> getCoursesWithFewerThanFiveGrades() {
		List<String> result = new ArrayList<>();
		for (Course course : courses.values()) {
			if (!course.hasAtLeastFiveGrades()) {
				result.add(course.getCourseName());
			}
		}
		return result;
	}

	public boolean courseExists(String courseName) {
		return courses.containsKey(courseName);
	}

	public double calculateRequiredFinalForA(String courseName) {
		Course course = courses.get(courseName);
		if (course == null) {
			throw new IllegalArgumentException("Course not found: " + courseName);
		}
		return course.calculateRequiredFinalScoreForA();
	}




}
