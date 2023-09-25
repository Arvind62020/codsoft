import java.io.*;
import java.util.Scanner;
public class word_conter {
    


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Word Counter");

        System.out.println("Choose any option: \n 1> Enter text\n 2> Provide a file");
        
        int n=sc.nextInt();
        sc.nextLine();
        
        String s="";
        if(n==1){
                System.out.println("Enter the text:");
                s = sc.nextLine();
                
     }
            else if(n==2){
                System.out.println("Enter the path to the file:");
                String filePath = sc.nextLine();
                try {
                    s = readTextFromFile(filePath);
                } catch (IOException e) {
                    System.err.println("Error reading the file: " + e.getMessage());
                    return;
                }
            }
           else{
                System.out.println("Invalid choice. Exiting.");
                return;
        }
       
           int wordCount = countWords(s);
            System.out.println("Total words: " + wordCount);
       
           System.out.println(s);
        
        

        sc.close();
    }

    private static int countWords(String text) {

        String[] words = text.split("[\\s\\p{Punct}]+"); // Split by whitespace or punctuation
        return words.length;
    }

    private static String readTextFromFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("F:\\college placement\\internship\\codesoft\\task 2\\radhe_kriskna.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
            }
        }
        return text.toString();
        
    }
}