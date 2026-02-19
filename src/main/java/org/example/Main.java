package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "task.txt";
    Task task = new Task(1, "Таск", "оватмрмрвыра?");

    public static void main(String[] args) throws IOException {
        List<String> tasks = loadTasks();

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Меню: \n" +
                    "1. Показать задачи \n" +
                    "2. Добавить задачу \n" +
                    "3. Удалить задачу \n" +
                    "4. Редактирование задачи \n" +
                    "0. Выход");

            String choice = input.nextLine();
            int number;
            try {
                number = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Неверный выбор!");
                continue;
            }

            if (number == 0) {
                break;
            } else if (number == 1) {
                showTasks(tasks);
            } else if (number == 2) {
                addTask(tasks, input);
            } else if (number == 3) {
                deleteTask(tasks, input);
            } else if (number == 4) {
                chaneTask(input, tasks);
            } else {
                System.out.println("Неверный выбор!");
            }
        }
    }
    private static List<String> loadTasks() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
        }
        return tasks;
    }

    private static void saveTasks(List<String> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (String task : tasks) {
                writer.write(task + "\n");
            }
        }
    }
    public static void addTask(List<String> tasks, Scanner input) throws IOException {
        System.out.println("Введите задачу:");
        String task = input.nextLine();
        if (!task.trim().isEmpty()) {
            tasks.add(task);
            saveTasks(tasks);
            System.out.println("Задача добавлена.");
        } else {
            System.out.println("Задача не может быть пустой.");
        }
    }

    public static void showTasks(List<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Нет задач.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
    public static void deleteTask(List<String> tasks, Scanner input) throws IOException {
        if (tasks.isEmpty()) {
            System.out.println("Нет задач для удаления.");
            return;
        }
        showTasks(tasks);
        System.out.println("Введите номер задачи для удаления:");
        String delChoice = input.nextLine();
        try {
            int num = Integer.parseInt(delChoice);
            if (num > 0 && num <= tasks.size()) {
                tasks.remove(num - 1);
                saveTasks(tasks);
                System.out.println("Задача удалена.");
            } else {
                System.out.println("Неверный номер.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод.");
        }
    }
    public  static void chaneTask(Scanner input,  List <String> tasks)throws IOException{
        showTasks(tasks);
        System.out.println("Введите номер задачи для замена:");
        String delChoice = input.nextLine();
        try {
            int num = Integer.parseInt(delChoice);
            if (num > 0 && num <= tasks.size()) {
                tasks.remove(num - 1);
            } else {
                System.out.println("Неверный номер.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод.");
        }
        System.out.println("Введите новую задачу:");
        String task = input.nextLine();
        if (!task.trim().isEmpty()) {
            tasks.add(task);
            saveTasks(tasks);
            System.out.println("Задача добавлена.");
        } else {
            System.out.println("Задача не может быть пустой.");
        }
    }
}
