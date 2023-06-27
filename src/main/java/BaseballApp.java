import DBConnection.DBInitializer;
import Service.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BaseballApp {
    private TeamService teamService;
    private PlayerService playerService;
    private OutPlayerService outPlayerService;
    private PositionService positionService;
    private Scanner scanner;

    public BaseballApp() {
        this.teamService = new TeamService();
        this.playerService = new PlayerService();
        this.outPlayerService = new OutPlayerService();
        this.positionService = new PositionService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            String request = inputRequest();
            String[] command = parseCommand(request);
            executeCommand(command);
        }
    }

    private void printMenu() {
        System.out.println("===== Baseball Team Management =====");
        System.out.println("1. Register Stadium");
        System.out.println("2. Show Stadium List");
        System.out.println("3. Register Team");
        System.out.println("4. Show Team List");
        System.out.println("5. Register Player");
        System.out.println("6. Show Player List by Team");
        System.out.println("7. Register Out Player");
        System.out.println("8. Show Out Player List");
        System.out.println("9. Show Team Baseball Players by Position");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private String inputRequest() {
        System.out.print("Enter your request: ");
        return scanner.nextLine();
    }

    private String[] parseCommand(String request) {
        return request.split(" ");
    }

    private void executeCommand(String[] command) {
        String commandType = command[0];
        switch (commandType) {
            case "1":
                registerStadium();
                break;
            case "2":
                showStadiumList();
                break;
            case "3":
                registerTeam();
                break;
            case "4":
                showTeamList();
                break;
            case "5":
                registerPlayer();
                break;
            case "6":
                showPlayerListByTeam();
                break;
            case "7":
                registerOutPlayer();
                break;
            case "8":
                showOutPlayerList();
                break;
            case "9":
                showPositionList();
                break;
            case "0":
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command.");
        }
    }

    private void registerStadium() {
        System.out.println("===== Stadium Registration =====");
        System.out.print("Stadium Name: ");
        String name = scanner.nextLine();
        String result = teamService.registerStadium(name);
        System.out.println(result);
    }

    private void showStadiumList() {
        System.out.println("===== Stadium List =====");
        List<StadiumDto> stadiumList = teamService.getStadiumList();
        for (StadiumDto stadium : stadiumList) {
            System.out.println("Stadium ID: " + stadium.getId() + ", Name: " + stadium.getName());
        }
    }

    private void registerTeam() {
        System.out.println("===== Team Registration =====");
        System.out.print("Stadium ID: ");
        int stadiumId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Team Name: ");
        String name = scanner.nextLine();
        String result = teamService.registerTeam(stadiumId, name);
        System.out.println(result);
    }

    private void showTeamList() {
        System.out.println("===== Team List =====");
        List<TeamDto> teamList = teamService.getTeamList();
        for (TeamDto team : teamList) {
            System.out.println("Team ID: " + team.getId() + ", Name: " + team.getName());
        }
    }

    private void registerPlayer() {
        System.out.println("===== Player Registration =====");
        System.out.print("Team ID: ");
        int teamId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Player Name: ");
        String name = scanner.nextLine();
        System.out.print("Player Position: ");
        String position = scanner.nextLine();
        String result = playerService.registerPlayer(teamId, name, position);
        System.out.println(result);
    }

    private void showPlayerListByTeam() {
        System.out.println("===== Player List by Team =====");
        System.out.print("Team ID: ");
        int teamId = scanner.nextInt();
        scanner.nextLine();
        List<PlayerDto> playerList = playerService.getPlayerList(teamId);
        for (PlayerDto player : playerList) {
            System.out.println("Player ID: " + player.getId() + ", Name: " + player.getName() + ", Position: " + player.getPosition());
        }
    }

    private void registerOutPlayer() {
        System.out.println("===== Out Player Registration =====");
        System.out.print("Player ID: ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Reason: ");
        String reason = scanner.nextLine();
        String result = outPlayerService.registerOutPlayer(playerId, reason);
        System.out.println(result);
    }

    private void showOutPlayerList() {
        System.out.println("===== Out Player List =====");
        List<OutPlayerDto> outPlayerList = outPlayerService.getOutPlayerList();
        for (OutPlayerDto outPlayer : outPlayerList) {
            System.out.println("Player ID: " + outPlayer.getPlayerId() + ", Reason: " + outPlayer.getReason());
        }
    }

    private void showPositionList() {
        System.out.println("===== Position List =====");
        List<PositionResponseDto> positionList = positionService.showPositionList();
        for (PositionResponseDto position : positionList) {
            System.out.println("Position: " + position.getPosition() + ", Team: " + position.getTeam());
        }
    }

    public static void main(String[] args) {
        DBInitializer.createTables();

        BaseballApp app = new BaseballApp();
        app.run();
    }
}
