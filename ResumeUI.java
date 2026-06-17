package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Candidate;
import service.AnalyzerResume;
import service.PDFExtractor;
import service.RankingService;
import service.ServiceReport;
import javax.swing.border.EmptyBorder;

public class ResumeUI extends JFrame {
	
	    private JTextArea area;

	    private String path = "";

	    private ArrayList<Candidate> candidates =
	            new ArrayList<>();

	    public ResumeUI() {

	        setTitle(" Resume Screening System");

	        setSize(900, 650);

	        setLocationRelativeTo(null);

	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	        setContentPane(new GradientPanel());

	        setLayout(new GridBagLayout());

	        JPanel card = new JPanel();

	        card.setPreferredSize(new Dimension(600, 500));

	        card.setBackground(Color.WHITE);

	        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

	        card.setBorder(new EmptyBorder(30, 40, 30, 40));
	        JLabel title = new JLabel(" RESUME ANALYZER");

	        title.setAlignmentX(Component.CENTER_ALIGNMENT);

	        title.setFont(new Font("Segoe UI", Font.BOLD, 30));

	        title.setForeground(new Color(33, 150, 243));

	        JLabel subtitle = new JLabel(
	                "Upload resumes and rank candidates instantly");

	        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

	        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));

	        subtitle.setForeground(Color.GRAY);

	        area = new JTextArea(10, 30);

	        area.setEditable(false);

	        area.setFont(new Font("Segoe UI", Font.PLAIN, 15));

	        area.setBorder(BorderFactory.createEmptyBorder(
	                10, 10, 10, 10));

	        JScrollPane scrollPane = new JScrollPane(area);

	        scrollPane.setMaximumSize(
	                new Dimension(500, 220));
	        JButton upload = createButton("Upload PDF");

	        JButton analyze = createButton("Analyze Resume");

	        JButton report = createButton("Download Report");

	        upload.setAlignmentX(Component.CENTER_ALIGNMENT);
	        analyze.setAlignmentX(Component.CENTER_ALIGNMENT);
	        report.setAlignmentX(Component.CENTER_ALIGNMENT);

	        card.add(Box.createVerticalStrut(10));
	        card.add(title);
	        card.add(Box.createVerticalStrut(10));
	        card.add(subtitle);
	        card.add(Box.createVerticalStrut(30));
	        card.add(scrollPane);
	        card.add(Box.createVerticalStrut(25));
	        card.add(upload);
	        card.add(Box.createVerticalStrut(15));
	        card.add(analyze);
	        card.add(Box.createVerticalStrut(15));
	        card.add(report);

	        add(card);

	        upload.addActionListener(e -> uploadResume());

	        analyze.addActionListener(e -> analyzeResume());

	        report.addActionListener(e -> downloadReport());

	        setVisible(true);
	    }

	    private JButton createButton(String text) {

	        JButton button = new JButton(text);

	        button.setFont(new Font("Segoe UI", Font.BOLD, 16));

	        button.setForeground(Color.WHITE);

	        button.setBackground(new Color(33, 150, 243));

	        button.setFocusPainted(false);

	        button.setBorder(BorderFactory.createEmptyBorder(
	                12, 25, 12, 25));

	        button.setMaximumSize(new Dimension(220, 45));

	        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

	        return button;
	    }

	    private void uploadResume() {

	        JFileChooser chooser = new JFileChooser();

	        chooser.setFileFilter(
	                new FileNameExtensionFilter(
	                        "PDF Files", "pdf"));

	        int result = chooser.showOpenDialog(this);

	        if (result == JFileChooser.APPROVE_OPTION) {

	            File file = chooser.getSelectedFile();

	            path = file.getAbsolutePath();

	            area.setText(
	                    "✓ Resume Uploaded Successfully\n\n"
	                            + file.getName());
	        }
	    }

	    private void analyzeResume() {
	    	String aiAnalysis = "";


	        if (path.isEmpty()) {
	        	 

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Upload Resume First");
	            

	            return;
	        }

	        PDFExtractor pdf = new PDFExtractor();

	        String text = pdf.extractText(path);

	        AnalyzerResume analyzer = new AnalyzerResume();

	        String name = analyzer.extractName(path);

	        String email = analyzer.extractEmail(text);

	        int score = analyzer.calculateScore(text);
	        

	        Candidate candidate = new Candidate(name, text);

	        candidate.setEmail(email);

	        candidate.setSkills(
	                analyzer.extractSkills(text));

	        candidate.setScore(score);

	        candidates.add(candidate);

	        new RankingService().rank(candidates);

	        StringBuilder result = new StringBuilder();

	        result.append("Name: ")
	                .append(candidate.getName())
	                .append("\n");

	        result.append("Email: ")
	                .append(candidate.getEmail())
	                .append("\n\n");

	        result.append("Skills:\n");

	        for (String skill : candidate.getSkills()) {

	            result.append("• ")
	                    .append(skill)
	                    .append("\n");
	        }

	        result.append("\nScore: ")
	                .append(candidate.getScore())
	                .append("/100\n\n");

	        result.append("====== CANDIDATE RANKING ======\n");

	        int rank = 1;

	        for (Candidate c : candidates) {

	            result.append(rank++)
	                    .append(". ")
	                    .append(c.getName())
	                    .append(" - ")
	                    .append(c.getScore())
	                    .append("/100\n");
	        }

	        JOptionPane.showMessageDialog(
	                this,
	                result.toString(),
	                "Resume Screening Result",
	                JOptionPane.INFORMATION_MESSAGE);
	    }

	    private void downloadReport() {

	        try {

	            new ServiceReport()
	                    .generateReport(candidates);

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Report Created Successfully");

	        } catch (Exception ex) {

	            JOptionPane.showMessageDialog(
	                    this,
	                    ex.getMessage());
	        }
	    }
	    private void showResultWindow(Candidate candidate) {

	        JDialog dialog = new JDialog(this,
	                "Resume Analysis Result", true);

	        dialog.setSize(650, 500);

	        dialog.setLocationRelativeTo(this);

	        JPanel panel = new JPanel();

	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	        panel.setBorder(BorderFactory.createEmptyBorder(
	                20, 20, 20, 20));

	        panel.setBackground(Color.WHITE);

	        JLabel heading = new JLabel("RESUME ANALYSIS");

	        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));

	        heading.setForeground(new Color(33, 150, 243));

	        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

	        JLabel name = new JLabel("Name: " + candidate.getName());

	        name.setFont(new Font("Segoe UI", Font.BOLD, 20));

	        name.setAlignmentX(Component.CENTER_ALIGNMENT);

	        JLabel email = new JLabel("Email: " + candidate.getEmail());

	        email.setFont(new Font("Segoe UI", Font.PLAIN, 16));

	        email.setAlignmentX(Component.CENTER_ALIGNMENT);

	        JLabel score = new JLabel(
	                "Score: " + candidate.getScore() + "/100");

	        score.setFont(new Font("Segoe UI", Font.BOLD, 22));

	        score.setForeground(new Color(76, 175, 80));

	        score.setAlignmentX(Component.CENTER_ALIGNMENT);

	        JLabel status = new JLabel(
	                candidate.getScore() >= 60
	                        ? "SHORTLISTED"
	                        : "REJECTED");

	        status.setFont(new Font("Segoe UI", Font.BOLD, 18));

	        status.setForeground(
	                candidate.getScore() >= 60
	                        ? new Color(0, 150, 0)
	                        : Color.RED);

	        status.setAlignmentX(Component.CENTER_ALIGNMENT);

	        JTextArea skillsArea = new JTextArea();

	        skillsArea.setEditable(false);

	        skillsArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));

	        skillsArea.setBorder(
	                BorderFactory.createTitledBorder("Skills"));

	        for (String skill : candidate.getSkills()) {

	            skillsArea.append("• " + skill + "\n");
	        }

	        panel.add(heading);
	        panel.add(Box.createVerticalStrut(20));

	        panel.add(name);
	        panel.add(Box.createVerticalStrut(10));

	        panel.add(email);
	        panel.add(Box.createVerticalStrut(20));

	        panel.add(score);
	        panel.add(Box.createVerticalStrut(10));

	        panel.add(status);
	        panel.add(Box.createVerticalStrut(20));

	        panel.add(new JScrollPane(skillsArea));

	        dialog.add(panel);

	        dialog.setVisible(true);
	    }
	    class GradientPanel extends JPanel {

	        @Override
	        protected void paintComponent(Graphics g) {

	            super.paintComponent(g);

	            Graphics2D g2d = (Graphics2D) g;

	            g2d.setRenderingHint(
	                    RenderingHints.KEY_RENDERING,
	                    RenderingHints.VALUE_RENDER_QUALITY);

	            int w = getWidth();

	            int h = getHeight();

	            GradientPaint gp = new GradientPaint(
	                    0, 0,
	                    new Color(33, 150, 243),
	                    0, h,
	                    new Color(156, 39, 176));

	            g2d.setPaint(gp);

	            g2d.fillRect(0, 0, w, h);
	        }
	    }
	  

	   
	}
	    