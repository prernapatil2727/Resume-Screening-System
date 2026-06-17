package model;
import java.util.List;

public class Candidate {

	
	    private String name;
	    private String email;
	    private String resumeText;
	    private int score;
	    private List<String> skills;

	    public Candidate(String name, String resumeText) {

	        this.name = name;
	        this.resumeText = resumeText;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getResumeText() {
	        return resumeText;
	    }

	    public int getScore() {
	        return score;
	    }

	    public void setScore(int score) {
	        this.score = score;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public List<String> getSkills() {
	        return skills;
	    }

	    public void setSkills(List<String> skills) {
	        this.skills = skills;
	    }
	   
	}