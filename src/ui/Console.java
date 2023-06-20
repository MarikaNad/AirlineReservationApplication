package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Console {

    public static Scanner scanner = new Scanner(System.in);

   static Pattern textPattern = Pattern.compile("(.)*(\\w)(.)*");
   static Pattern emailPattern = Pattern.compile("^(.+)@(.+)\\.com$");

    static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    // static String date = simpleDateFormat.format(new Date());

    static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy" HH:mm:ss")

    public static String readText(String promt) {
        String value;

//        LocalDateTime ldt = LocalDateTime.of(date, time);

        while (true) {
            System.out.print(promt);
            try {
                value = scanner.next();

                if (!textPattern.matcher(value).find()) {
                    System.out.println("Please enter details using letters or numbers only");
                } else {
                    break;
                }
            } catch (Exception ex) {
                scanner.next();
            }
        }
        return value;
    }


    public static int readNumber(String promt, int min, int max) {
        Scanner person = new Scanner(System.in);
        int value;
        while (true) {
            System.out.print(promt);
            value = person.nextInt();
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Enter value between " + min + " and " + max);
        }
        return value;
    }

    public static double readDouble(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.println(prompt);

            try {
                value = scanner.nextDouble();
            } catch (Exception ex) {
                // ignore the exception
                // ignore the users input the Scanner has choked on
                scanner.next();
                continue;
            }

            if (value >= min && value <= max) {
                break;
            }
        }
        return value;
    }

    public static String readEmail(String promt) {
        String value;

        while (true) {
            System.out.print(promt);
            try {
                value = scanner.next();

                if (!emailPattern.matcher(value).find()) {
                    System.out.println("Please enter correct email address - ex: name@gmail.com");
                } else {
                    break;
                }
            } catch (Exception ex) {
                scanner.next();
            }
        }
        return value;
    }

    public static boolean readQuery(String promt) {
        String value;

        while (true) {
            System.out.print(promt);
            try {
//                value = scanner.nextLine();
                value = scanner.next().trim();

                if (value.equalsIgnoreCase("y") || value.equalsIgnoreCase("yes")) {
                    return true;
                } else if (value.equalsIgnoreCase("n") || value.equalsIgnoreCase("no")) {
                    return false;
                }
            } catch (Exception ex) {
                scanner.next();
            }
        }
    }

    public static Date readDate(String prompt) {
        Date value;

        while (true) {
            System.out.print(prompt);

            String javaDate = scanner.next().trim();

            try {
                // Set lenient to false to enforce strict date parsing
                DATE_FORMAT.setLenient(false);
                value = DATE_FORMAT.parse(javaDate);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date format");
            }
        }
        return value;
    }

    public static LocalTime readTime(String prompt) {
        LocalTime value;

        while (true) {
            System.out.print(prompt);

            String javaTime = scanner.next().trim();

            try {
                value = LocalTime.parse(javaTime, TIME_FORMAT);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format");
            }
        }
        return value;
    }
}
