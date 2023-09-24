import java.util.*;

class random_no_genrate
{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Random random = new Random();
        int i=0;
        int j=0;
        
            // Generate a random number between 1 and 100
        int randomNumber = random.nextInt(10) + 1;

        // Print the random number
        while(j<=10){

        j++;
        System.out.println("ENTER THE GUSSING NO between 1 and 11: ");
        i=sc.nextInt();
        if(i==randomNumber){
            System.out.println("YOU WIN :"+randomNumber);
            break;
        }
        else{
            //System.out.println("YOU LOOSES");
            if(randomNumber>i){
                System.out.println("NO IS TOO SMALL  try again!");
            }
            else{
                System.out.println("NO IS TOO BIG  try again!");
            }
        }
        
        }
    }
        
    
    }
