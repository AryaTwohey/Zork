package zork;

public class test {
    public static void main(String[] args) throws InterruptedException {
        
        String dots = "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
        String thankYouMessage = "                                                CONGRADULATIONS YOU HAVE ESCAPED DEATH DOLL";
        String friend = "                                                     You and your friend are now free";
        String creators = "A text-adventure game created by Arya, Arman, Lara & Muriel";
        String aryaRole = "Arya: Inventory, game class & combat";
        String armanRole = "Arman: Parser, Game Class & Combat";
        String laraRole = "Lara: Map Creation";
        String murielRole = "Muriel: Map Creation";

        System.out.println();
        System.out.println();


        for(int  i = 0; i < dots.length(); i++){
        System.out.printf("%c",dots.charAt(i) );
        Thread.sleep(5);
        }
        System.out.println();
        System.out.println();

        for(int i = 0; i < thankYouMessage.length(); i++ ){

            System.out.printf("%c", thankYouMessage.charAt(i));
            Thread.sleep(20);
    }       
        System.out.println();
        System.out.println();

        for(int i = 0; i < friend.length(); i++){

        System.out.printf("%c", friend.charAt(i));
        Thread.sleep(20);
    }
    System.out.println();
    System.out.println();

    for(int i = 0; i < dots.length(); i++){
        System.out.printf("%c", dots.charAt(i));
        Thread.sleep(5);
    }
    System.out.println();
    System.out.println();
    System.out.println();

    for(int i = 0; i < creators.length(); i++){

    System.out.printf("%c", creators.charAt(i));
    Thread.sleep(20);
    }
    System.out.println();
    System.out.println();

    for(int i = 0; i < aryaRole.length(); i++){
        System.out.printf("%c", aryaRole.charAt(i));
        Thread.sleep(20);
    }
    System.out.println();

    for(int i = 0; i < armanRole.length(); i++){
        System.out.printf("%c", armanRole.charAt(i));
        Thread.sleep(20);
    }

    System.out.println();

    for(int i = 0; i < laraRole.length(); i++){
    System.out.printf("%c", laraRole.charAt(i));
    Thread.sleep(20);
    }

    System.out.println();

    for(int i = 0; i < murielRole.length(); i++){
    System.out.printf("%c", murielRole.charAt(i));
    Thread.sleep(20);
    }
    System.out.println();
    System.out.println();

    for(int i = 0; i < dots.length(); i++){
        System.out.printf("%c", dots.charAt(i));
        Thread.sleep(5);
    }
        System.out.println();
        System.out.println();
}  
}
    

    

