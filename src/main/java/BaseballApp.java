
import DBConnection.DBInitializer;
import Service.TeamService;

import java.util.Scanner;

public class BaseballApp {
    public static void main(String[] args) {
        DBInitializer.createTables();

        BaseballApp app = new BaseballApp();
        app.run();
    }
    private TeamService teamService;
    private Scanner scanner;

    public BaseballApp() {
        this.teamService = new TeamService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerTeam();
                    break;
                case 2:
                    printTeamList();
                    break;
                case 3:
                    System.out.println("End");
                    return;
                default:
                    System.out.println("Error");
            }
        }
    }

    private void printMenu() {
        System.out.println("===== Baseball Team Management =====");
        System.out.println("1. Team Registration");
        System.out.println("2. Team List");
        System.out.println("3. Exit");
        System.out.print("Select an option: ");
    }

    private void registerTeam() {
        System.out.println("===== Team Registration =====");
        System.out.print("Stadium ID: ");
        int stadiumId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Team Name: ");
        String teamName = scanner.nextLine();

        teamService.registerTeam(stadiumId, teamName);
        System.out.println("Team is registered.");
    }

    private void printTeamList() {
        teamService.printTeamList();
    }

}
