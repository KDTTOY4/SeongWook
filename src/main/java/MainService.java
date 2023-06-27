import DBConnection.DBInitializer;
import Service.PlayerService;
import Service.TeamService;

import java.util.Scanner;

public class MainService {
    private TeamService teamService;
    private PlayerService playerService;
    private Scanner scanner;

    public MainService() {
        this.teamService = new TeamService();
        this.playerService = new PlayerService();
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
        System.out.println("===== 야구 팀 관리 프로그램 =====");
        System.out.println("1. 선수 등록");
        System.out.println("2. 종료");
        System.out.print("선택: ");
    }

    private String inputRequest() {
        return scanner.nextLine();
    }

    private String[] parseCommand(String request) {
        return request.split(" ");
    }

    private void executeCommand(String[] command) {
        String commandType = command[0];
        switch (commandType) {
            case "선수등록":
                if (command.length > 3) {
                    int teamId = Integer.parseInt(command[1]);
                    String name = command[2];
                    String position = command[3];
                    registerPlayer(teamId, name, position);
                } else {
                    System.out.println("유효하지 않은 명령어입니다. 팀 ID, 선수 이름, 포지션을 입력해주세요.");
                }
                break;
            case "종료":
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            default:
                System.out.println("유효하지 않은 명령어입니다.");
        }
    }

    private void registerPlayer(int teamId, String name, String position) {
        String result = playerService.registerPlayer(teamId, name, position);
        System.out.println(result);
    }

    public static void main(String[] args) {
        DBInitializer.createTables();

        MainService mainService = new MainService();
        mainService.run();
    }
}