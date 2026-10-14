import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayList<Alarm> alarms = new ArrayList<>();

    while (true) {
      System.out.println("[Alarm System]");
      System.out.println("1. Create new alarm");
      System.out.println("2. View all alarms");
      System.out.println("3. Quit");
      System.out.print("Enter option: ");

      String choice = input.nextLine();

      switch (choice) {
        case "1":
          System.out.print("Enter alarm name: ");
          String name = input.nextLine();

          System.out.print("Enter alarm timer in seconds: ");
          String timerStr = input.nextLine();

          int timerSeconds;
          try {
            timerSeconds = Integer.parseInt(timerStr);
            if (timerSeconds < 0) {
              System.out.println("Invalid timer: Timer must be a positive whole number.");
              break;
            }
          } catch (NumberFormatException e) {
            System.out.println("Invalid timer: Timer must be a whole number.");
            break;
          }

          Alarm newAlarm = new Alarm(name, timerSeconds);
          alarms.add(newAlarm);
          System.out.println(newAlarm.toString());
          newAlarm.start();
          break;

        case "2":
          System.out.println("Here are all the alarms still running:");
          boolean anyAlive = false;
          for (Alarm alarm : alarms) {
            if (alarm.isAlive()) {
              System.out.println(alarm.toString());
              anyAlive = true;
            }
          }
          if (!anyAlive) {
            System.out.println("No alarms are currently running.");
          }
          break;

        case "3":
          System.out.println("Stopping all alarms...");
          for (Alarm alarm : alarms) {
            if (alarm.isAlive()) {
              alarm.interrupt();
            }
          }
          System.out.println("All alarms have been stopped.");
          input.close();
          System.exit(0);
          break;

        default:
          System.out.println("Invalid option. Try again.");
      }
    }
  }
}
