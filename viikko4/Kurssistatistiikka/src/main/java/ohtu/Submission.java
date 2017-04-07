package ohtu;

public class Submission {
    private String student_number;
    private int week;
    private int hours;
    private boolean a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21;
    private boolean[] exercises;
    private int totalDoneExercises;
    private Course course;

    public void set() {
        setExercisesToArray();
        setTotalDoneExercises();
    }
    public void setCourse(Course course) {
     this.course = course;   
    }
    
    public int getHours() {
        return hours;
    }

    public String getStudent_number() {
        return student_number;
    }
    
    public int getTotalDoneExercises() {
        return totalDoneExercises;
    }
    
    private void setExercisesToArray() {
        exercises = new boolean[]{a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21};
    }
    
    private void setTotalDoneExercises() {
        totalDoneExercises = 0;
        for (boolean exercise : exercises) {
            if (exercise) {
                totalDoneExercises++;
            }
        }
    }
    
    private String doneExercisesToString() {
        String done = "";
        for (int i = 0; i < exercises.length; i++) {
            if (exercises[i]) {
                done += i + 1 + " "; 
            }
        }
        return done.trim();
    }
    
    private int maxExercises() {
        return course.getMaxExercises(week);
    }
    
    @Override
    public String toString() {
        return "viikko " + week + ": tehtyjä tehtäviä yhteensä: " + totalDoneExercises + " (maksimi " + maxExercises() + "), aikaa kului " + hours + " tuntia, tehdyt tehtävät: " + doneExercisesToString();
    }
    
}