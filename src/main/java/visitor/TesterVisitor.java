package visitor;

import java.util.*;
import java.text.SimpleDateFormat;

public class TesterVisitor {
    public static void main(String[] args) throws VisitorNotFoundException, HouseNotFoundException {
        VisitorTrackingService service = new VisitorTrackingService();

        // Add houses
        for (int i = 1; i <= 5; i++) {
            House house = new House(i, "Contact Name " + i, "Contact Number " + i);
            service.getHousesMap().put(i, house);
        }

        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("Welcome to Visitor Tracking System");
            System.out.println("1. Add Visitor");
            System.out.println("2. Approve Visitor");
            System.out.println("3. Print Visitors for the Day");
            System.out.println("4. Print Visitors Pending Approval");
            System.out.println("5. Print Unapproved Visitors Who Left");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Visitor Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Visitor Contact Number: ");
                    String contactNumber = scanner.nextLine();
                    System.out.print("Enter House Number: ");
                    int houseNo = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Visitor visitor = new Visitor(service.getVisitorsMap().size() + 1, name, contactNumber, houseNo);
                    service.addVisitor(visitor);

                    System.out.println("Visitor added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Visitor ID to approve: ");
                    int visitorId = scanner.nextInt();
                    service.approve(visitorId);

                    System.out.println("Visitor approved successfully.");
                    break;

                case 3:
                    Date today = new Date();
                    VisitorList visitorsDayReport = service.getVisitorsDay(today);
                    System.out.println("Visitors for the day (" + dateFormat.format(today) + "):");
                    for (Visitor v : visitorsDayReport.getVisitors()) {
                        System.out.println(v);
                    }
                    break;

                case 4:
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    Date dateFrom = parseDate(scanner.nextLine());
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    Date dateTo = parseDate(scanner.nextLine());

                    VisitorList pendingApprovalReport = service.getVisitorsPendingApproval(dateFrom, dateTo);
                    System.out.println("Visitors pending approval between " + dateFormat.format(dateFrom) +
                            " and " + dateFormat.format(dateTo) + ":");
                    for (Visitor v : pendingApprovalReport.getVisitors()) {
                        System.out.println(v);
                    }
                    break;

                case 5:
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    dateFrom = parseDate(scanner.nextLine());
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    dateTo = parseDate(scanner.nextLine());

                    VisitorList unapprovedLeftReport = service.getVisitorsUnApprovedLeft(dateFrom, dateTo);
                    System.out.println("Unapproved visitors who left between " + dateFormat.format(dateFrom) +
                            " and " + dateFormat.format(dateTo) + ":");
                    for (Visitor v : unapprovedLeftReport.getVisitors()) {
                        System.out.println(v);
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }
}
