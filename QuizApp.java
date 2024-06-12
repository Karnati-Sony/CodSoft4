import java.util.*;

public class QuizApp {

    public static void main(String[] args) {
        List<Question> questions = initializeQuestions();
        int totalQuestions = questions.size();
        int score = 0;

        System.out.println("Welcome to the Quiz Application!");

        try (Scanner scan= new Scanner(System.in)) {
            for (int i = 0; i < totalQuestions; i++) {
                Question currentQuestion = questions.get(i);
                System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestion());
                System.out.println("Options:");
                for (int j = 0; j < currentQuestion.getOptions().size(); j++) {
                    System.out.println((j + 1) + ". " + currentQuestion.getOptions().get(j));
                }

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("\nTime's up!");
                        timer.cancel();
                    }
                };

                timer.schedule(task, 5000); 

                System.out.print("Enter your choice (1-" + currentQuestion.getOptions().size() + "): ");
                int choice = scan.nextInt();

                timer.cancel();

                if (choice == currentQuestion.getCorrectOption()) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer is: " + currentQuestion.getOptions().get(currentQuestion.getCorrectOption() - 1) + "\n");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (NoSuchElementException e) {
            System.out.println("Input not found.");
        }
if(score==totalQuestions){
        System.out.println("Awesome! You have completed the quiz.");
}
else{
    System.out.println("Quiz completed.");
}
        System.out.println("Your score: " + score + "/" + totalQuestions);
    }

    private static List<Question> initializeQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("In which year did Rohit Sharma make his international debut for India?",
                Arrays.asList("2014", "2007", "2020", "2006"), 2));
        questions.add(new Question("Which IPL team did Rohit Sharma captain to four IPL titles?",
                Arrays.asList("Rajasthan Royals", "Delhi Capitals", "Sunrisers Hyderabad", "Mumbai Indians"), 4));
        questions.add(new Question("What is the nickname often used to refer to Rohit Sharma due to his ability to score big runs?",
                Arrays.asList("Captain Cool", "Boom", "Hitman", "Mr.360"), 3));
        return questions;
    }
}

class Question {
    private final String question;
    private final List<String> options;
    private final int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}
