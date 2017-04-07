package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "014";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        String courseUrl = "https://ohtustats2017.herokuapp.com/courses/1.json";
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();
        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course course = mapper.fromJson(courseBodyText, Course.class);
        
        System.out.println("Kurssi: " + course.getName() + ", " + course.getTerm());
        System.out.println("");
        
        System.out.println("opiskelijanumero: " + studentNr);
        System.out.println("");
        
        int totalHours = 0;
        int totalExercises = 0;
        
        for (Submission submission : subs) {
            submission.setCourse(course);
            submission.set();
            System.out.println(submission);
            totalHours += submission.getHours();
            totalExercises += submission.getTotalDoneExercises();
        }
        System.out.println("");
        System.out.println("yhteensä: " + totalExercises + " tehtävää " + totalHours + " tuntia");
    }
}