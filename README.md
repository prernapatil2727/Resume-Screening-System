
# Resume Screening System

A Java-based application that automatically analyzes and ranks candidate resumes based on job requirements using keyword matching and AI-powered resume analysis.

## 📌 Project Overview

The Resume Screening System helps recruiters and placement coordinators quickly evaluate multiple resumes by extracting text from PDF files, matching skills with job descriptions, generating scores, and ranking candidates.

The system also integrates Google's Gemini API (or OpenAI ChatGPT API) to provide intelligent feedback and detailed resume analysis.

---

## ✨ Features

* 📄 Upload resumes in PDF format
* 🔍 Extract text from resumes using Apache PDFBox
* 🎯 Match candidate skills with job requirements
* 📊 Generate candidate scores automatically
* 🏆 Rank candidates based on matching scores
* 📥 Download screening reports
* 🤖 AI-powered resume analysis using Gemini API or ChatGPT API

---

## 🛠️ Technologies Used

* Java
* Swing / JavaFX (Frontend)
* Apache PDFBox
* Gemini API / OpenAI API
* Maven
* Git & GitHub

---

## ⚙️ How It Works

1. Upload one or more candidate resumes in PDF format.
2. Enter the job description or required skills.
3. The system extracts text from each resume.
4. Keywords are matched against the job requirements.
5. Scores are generated based on matching criteria.
6. Candidates are ranked automatically.
7. AI generates personalized resume insights.
8. Export the final report.

---

## 🤖 Gemini API Integration

1. Create an API key from Google AI Studio.
2. Store the API key securely.
3. Send extracted resume text to the Gemini API.
4. Receive AI-generated feedback and recommendations.

Example prompts:

* Analyze this resume for a Java Developer role.
* Identify missing technical skills.
* Suggest improvements to increase ATS compatibility.

---

## 🚀 Future Enhancements

* Support for DOCX resumes
* Advanced NLP-based skill extraction
* Resume similarity detection
* Dashboard with analytics
* Email notifications
* Database integration

---

## 👩‍💻 Author

Prerna Patil

---

## 📜 License

This project is developed for educational purposes.
