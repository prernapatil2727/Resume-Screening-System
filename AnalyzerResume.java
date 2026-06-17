package service;
import ui.ResumeUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;



public class AnalyzerResume {
	    private static final List<String> SKILLS = Arrays.asList(
	            "java",
	            "python",
	            "sql",
	            "spring",
	            "html",
	            "css",
	            "javascript",
	            "mysql",
	            "power bi",
	            "figma",
	            "c++"
	    }
	    public String extractName(String path) {

	        String fileName = new File(path).getName();

	        fileName = fileName.replace(".pdf", "");
	        fileName = fileName.replace("_ATS_Resume_Final", "");
	        fileName = fileName.replace("_Resume", "");
	        fileName = fileName.replace("_CV", "");
	        fileName = fileName.replace("_", " ");
	        fileName = fileName.replace("-", " ");

	        return fileName.toUpperCase();
	    }

	    public String extractEmail(String text) {

	        Pattern pattern = Pattern.compile(
	                "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+");

	        Matcher matcher = pattern.matcher(text);

	        if (matcher.find()) {
	            return matcher.group();
	        }

	        return "Not Found";
	    }

	    public List<String> extractSkills(String text) {

	        List<String> foundSkills = new ArrayList<>();

	        String resume = text.toLowerCase();

	        for (String skill : SKILLS) {

	            if (resume.contains(skill.toLowerCase())) {
	                foundSkills.add(skill);
	            }
	        }

	        return foundSkills;
	    }
	    public int calculateScore(String text) {

	        int score = 0;

	        String resume = text.toLowerCase();

	        for (String skill : SKILLS) {

	            if (resume.contains(skill.toLowerCase())) {
	                score += 10;
	            }
	        }

	        return Math.min(score, 100);
	       
	    }
	}


	