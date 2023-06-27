package com.fastcampus;

import com.fastcampus.dbconnection.DBInitializer;
import com.fastcampus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class BaseballApp {
  private final MainService mainService;
  private final Scanner scanner;

  public BaseballApp(@Autowired MainService mainService) {
    this.mainService = mainService;
    this.scanner = new Scanner(System.in);
  }

  public static void main(String[] args) {
    DBInitializer.createTables();

    SpringApplication.run(BaseballApp.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void run() {
    while (true) {
      String[] args = parseCommand(inputRequest());

      if ("EXIT".equals(args[0].toLowerCase())) break;

      // TODO : Request Validation (사용자 요청 유효성 검사)

      mainService.execute(args);
    }
  }

  private String inputRequest() {
    System.out.print("어떤 기능을 요청하시겠습니까? \n : ");
    return scanner.nextLine();
  }

  private String[] parseCommand(String request) {
    String[] args =
        Arrays.stream(request.split("[?|&]"))
            .map(arg -> arg.contains("=") ? arg.split("=")[1] : arg)
            .toArray(String[]::new);

    System.out.println(Arrays.toString(args));

    return args;
  }
}
