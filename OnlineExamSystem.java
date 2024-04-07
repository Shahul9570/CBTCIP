import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class MCQs {
    private String question;
    private String[] options;
    private int correctOption;

    public MCQs(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption - 1; // Subtract 1 to convert to 0-based indexing
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class OnlineExamSystem {
    private User currentUser;
    private MCQs[] mcqs;
    private int[] userAnswers;
    private int totalQuestions;
    private int timeLimit;
    private Timer timer;
    private boolean examStarted;
    private int currentQuestionIndex; 

    public OnlineExamSystem(User user, MCQs[] mcqs, int timeLimit) {
        this.currentUser = user;
        this.mcqs = mcqs;
        this.totalQuestions = mcqs.length;
        this.userAnswers = new int[totalQuestions];
        this.timeLimit = timeLimit;
        this.examStarted = false;
        this.currentQuestionIndex = 0; 
    }

    public void login() {
        System.out.println("Logged in as: " + currentUser.getUsername());
    }

    public void updateProfile() {
        System.out.println("Profile updated successfully.");
    }

    public void changePassword() {
        System.out.println("Password changed successfully.");
    }

    public void selectAnswer(int questionNumber, int selectedOption) {
        if (examStarted) {
            if (questionNumber >= 0 && questionNumber < totalQuestions) {
                userAnswers[questionNumber] = selectedOption;
                System.out.println("Answer for question " + (questionNumber + 1) + " selected.");
                currentQuestionIndex++;
                if (currentQuestionIndex < totalQuestions) {
                    displayNextQuestion();
                } else {
                    submitExam();
                }
            } else {
                System.out.println("Invalid question number.");
            }
        } else {
            System.out.println("Exam has not started yet.");
        }
    }

    public void startExam() {
        if (!examStarted) {
            examStarted = true;
            System.out.println("Exam Started. Time Limit: " + timeLimit + " minutes");

            timer = new Timer();
            timer.schedule(new TimerTask() {
                int minutesPassed = 0;

                @Override
                public void run() {
                    minutesPassed++;
                    if (minutesPassed >= timeLimit) {
                        autoSubmit();
                    } else {
                        System.out.println("Time Left: " + (timeLimit - minutesPassed) + " minutes");
                    }
                }
            }, 60000, 60000); 
            displayNextQuestion();
        } else {
            System.out.println("Exam has already started.");
        }
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            MCQs question = mcqs[currentQuestionIndex];
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + question.getQuestion());
            String[] options = question.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println(options[j]);
            }
            acceptUserInput(); 
        }
    }

    public void autoSubmit() {
        System.out.println("Time's up! Submitting the exam...");
        submitExam();
    }

    public void submitExam() {
        int score = calculateScore();
        System.out.println("Exam submitted successfully.");
        System.out.println("Your score: " + score + "/" + totalQuestions);
        closeSession();
    }

    private int calculateScore() {
        int score = 0;
        for (int i = 0; i < totalQuestions; i++) {
            if (userAnswers[i] == mcqs[i].getCorrectOption()) {
                score++;
            }
        }
        return score;
    }

    public void closeSession() {
        timer.cancel();
        System.out.println("Session closed.");
        examStarted = false;
    }

    public void logout() {
        System.out.println("Logged out successfully.");
    }

    private void acceptUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your answer for question " + (currentQuestionIndex + 1) + ": ");
        String selectedOption = scanner.next();
        int optionIndex = convertOptionToInt(selectedOption);
        selectAnswer(currentQuestionIndex, optionIndex);
    }

    private int convertOptionToInt(String option) {
        return option.toUpperCase().charAt(0) - 'A';
    }

    public static void main(String[] args) {
        User user = new User("username", "password");
        MCQs[] questions = {
            new MCQs("What is 2 + 2?", new String[]{"A) 3", "B) 4", "C) 5", "D) 6"}, 2),
            new MCQs("What is the capital of France?", new String[]{"A) London", "B) Paris", "C) Berlin", "D) Rome"}, 2),
            new MCQs("What is 5 * 7?", new String[]{"A) 25", "B) 30", "C) 35", "D) 40"}, 3),
            new MCQs("What is the square root of 144?", new String[]{"A) 12", "B) 14", "C) 16", "D) 18"}, 1),
            new MCQs("What is the chemical symbol for water?", new String[]{"A) Wa", "B) H2O", "C) Wt", "D) H2"}, 2),
            new MCQs("Which planet is known as the Red Planet?", new String[]{"A) Venus", "B) Mars", "C) Jupiter", "D) Saturn"}, 2),
            new MCQs("Who was the first President of the United States?", new String[]{"A) Abraham Lincoln", "B) George Washington", "C) Thomas Jefferson", "D) John Adams"}, 2),
            new MCQs("Which year did World War II end?", new String[]{"A) 1943", "B) 1945", "C) 1947", "D) 1950"}, 2),
            new MCQs("What is the capital of Australia?", new String[]{"A) Sydney", "B) Melbourne", "C) Canberra", "D) Brisbane"}, 3),
            new MCQs("Which country is known as the Land of the Rising Sun?", new String[]{"A) China", "B) Japan", "C) South Korea", "D) India"}, 2),
            new MCQs("Which ocean is the largest?", new String[]{"A) Pacific Ocean", "B) Atlantic Ocean", "C) Indian Ocean", "D) Arctic Ocean"}, 0),
            new MCQs("What is the tallest mountain in the world?", new String[]{"A) K2", "B) Mount Everest", "C) Kanchenjunga", "D) Mount Kilimanjaro"}, 1)
        };

        OnlineExamSystem examSystem = new OnlineExamSystem(user, questions, 1); // 1 minute time limit for testing
        examSystem.login();
        examSystem.startExam();
    }
}
