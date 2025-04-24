//import java.util.Scanner;
//import java.util.ArrayList;
//
//public class BankRunner {
//
//    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//        ArrayList<User> users = new ArrayList<>();
//
//        System.out.println("Welcome to your personal banking system!");
//        System.out.println("----------------------------------------------------");
//        System.out.println("ADMIN CREDENTIALS");
//        System.out.println("Admin username: admin1");
//        System.out.println("Admin password: 8976");
//        System.out.println("IMPORTANT: You must create a new user account!");
//        System.out.println("-----------------------------------------------------");
//
//        boolean play = true;
//        while (play) {
//            System.out.println("MAIN MENU");
//            System.out.println("1. Create A New Account");
//            System.out.println("2. Create Admin Account");
//            System.out.println("3. Enter User Mode");
//            System.out.println("4. Exit Banking System");
//            System.out.println("-----------------------------------------------------");
//            System.out.print("Choose an option: ");
//            String mode = s.nextLine();
//
//            int pin = 0;
//            String username = null;
//            if (mode.equals("1")) {
//                System.out.print("Enter a username: ");
//                username = s.nextLine();
//                boolean pinValid = false;
//
//                while (!pinValid) {
//                    System.out.println("-----------------------------------------------------");
//                    System.out.println("PIN MENU");
//                    System.out.println("How would you like to create your pin? ");
//                    System.out.println("1. Create your own pin");
//                    System.out.println("2. Randomly generate a pin");
//                    System.out.println("-----------------------------------------------------");
//                    System.out.print("Choose an option: ");
//                    String answer = s.nextLine();
//
//                    if (answer.equals("1")) {
//                        System.out.print("Enter a 4-digit pin: ");
//                        pin = s.nextInt();
//                        s.nextLine();
//                        if (String.valueOf(pin).length() == 4) {
//                            pinValid = true;
//                        } else {
//                            System.out.println("Error! The pin must be exactly 4 digits.");
//                        }
//                    }
//                    else if (answer.equals("2")) {
//                        pin = (int) (10000 * Math.random() + 1);
//                        pinValid = true;
//                        System.out.println("Your randomized pin is: " + pin);
//                    }
//                    else {
//                        System.out.println("Invalid option. Try again.");
//                    }
//                    account1 = new User(username, pin);
//                }
//                System.out.println("Account created successfully for username: " + username);
//                System.out.println("-----------------------------------------------------");
//            }
//
//            else if (mode.equals("2")) {
//                System.out.print("Please enter admin username: ");
//                String admin = s.nextLine();
//                boolean verify = false;
//                if (!admin1.verifyAdmin(admin)) {
//                    System.out.println("Incorrect username.");
//                }
//                else {
//                    System.out.print("Please enter the admin pin: ");
//                    String adminPin = s.nextLine();
//                    verify = admin1.verifyAdmin(admin) && admin1.verifyPin(Integer.parseInt(adminPin));
//                }
//
//                while (verify) {
//                    System.out.println();
//                    System.out.println("-----------------------------------------------------");
//                    System.out.println("ADMIN MENU");
//                    System.out.println("1. Update Pin");
//                    System.out.println("2. Update Balance");
//                    System.out.println("3. Delete Account");
//                    System.out.println("4. Exit");
//                    System.out.println("-----------------------------------------------------");
//                    System.out.print("Choose an option: ");
//                    String answer = s.nextLine();
//
//                    if (answer.equals("1")) {
//                        System.out.print("Please enter your username to update pin: ");
//                        String username1 = s.nextLine();
//                        boolean userVer = account1.verifyUser(username1);
//
//                        if (userVer) {
//                            System.out.print("Enter the new pin: ");
//                            int newPin = s.nextInt();
//                            s.nextLine();
//                            admin1.updatePin(account1, newPin);
//                            admin1.updatePin(account1, newPin);
//                        }
//                        else {
//                            System.out.println("User not found.");
//                        }
//                    }
//                    else if (answer.equals("2")) {
//                        System.out.print("Please enter your username to update balance: ");
//                        String usernameBal = s.nextLine();
//                        boolean userVer = account1.verifyUser(usernameBal);
//
//                        if (userVer) {
//                            System.out.print("Enter the new balance for the user: ");
//                            int newBalance = s.nextInt();
//                            s.nextLine();
//                            admin1.updateBalance(account1, newBalance);
//                        }
//                        else {
//                            System.out.println("User not found.");
//                        }
//                    }
//                    else if (answer.equals("3")) {
//                        if (account1 != null) {
//                            account1 = null;
//                            System.out.println("User account deleted successfully.");
//                        }
//                        else {
//                            System.out.println("You cannot delete an account that has not been created.");
//                        }
//                    }
//                    else if (answer.equals("4")) {
//                        System.out.println("Exiting Admin Menu...");
//                        verify = false;
//                    }
//                    else {
//                        System.out.println("Invalid option. Please try again.");
//                    }
//                }
//                System.out.println("----------------------------------------------------");
//            }
//
//            else if (mode.equals("3")) {
//                System.out.print("Please enter your username: ");
//                String usernameCheck = s.nextLine();
//                boolean verifyUser = account1.verifyUser(usernameCheck);
//
//                if (verifyUser) {
//                    System.out.print("Please enter your pin: ");
//                    String pin1 = s.nextLine();
//                    boolean verifyPin = account1.verifyPin(Integer.parseInt(pin1));
//
//                    if (verifyPin) {
//                        System.out.println("Welcome, " + usernameCheck + "!");
//
//                        boolean run = true;
//                        while (run) {
//                            System.out.println("-----------------------------------------------------");
//                            System.out.println("USER MENU");
//                            System.out.println("1. Withdraw");
//                            System.out.println("2. Deposit");
//                            System.out.println("3. Balance");
//                            System.out.println("4. Exit User Menu");
//                            System.out.println("-----------------------------------------------------");
//                            System.out.print("Choose an option: ");
//                            String answer = s.nextLine();
//
//                            if (answer.equalsIgnoreCase("1")) {
//                                System.out.print("Please enter your withdraw amount: ");
//                                int withdrawAmt = s.nextInt();
//                                s.nextLine();
//                                System.out.println(account1.withdraw(withdrawAmt));
//                            }
//                            if (answer.equalsIgnoreCase("2")) {
//                                System.out.print("Please enter your deposit amount: ");
//                                int depositAmt = s.nextInt();
//                                s.nextLine();
//                                System.out.println(account1.deposit(depositAmt));
//                            }
//                            if (answer.equalsIgnoreCase("3")) {
//                                System.out.println(account1.balance());
//                            }
//                            if (answer.equalsIgnoreCase("4")) {
//                                System.out.println("Exiting User Menu...");
//                                System.out.println("-----------------------------------------------------");
//                                run = false;
//                            }
//                        }
//                    }
//                    if (!verifyPin) {
//                        System.out.println("Error! The pin is incorrect.");
//                    }
//                }
//                else {
//                    System.out.println("That account does not exist!");
//                }
//            }
//            else if (mode.equals("4")) {
//                play = false;
//                System.out.println("Thank you for using our banking system. Goodbye!");
//            }
//        }
//    }
//}