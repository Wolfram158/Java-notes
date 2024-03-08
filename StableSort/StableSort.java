import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class StableSort {
    TreeMap<Integer, ArrayList<String>> state;
    final Pattern addMinusSeparated = Pattern.compile("\\s*add\\s+-\\s+(10{9}|[0-9]{1,9})\\s+\\S+\\s*");
    final Pattern add = Pattern.compile("\\s*add\\s+-?(10{9}|[0-9]{1,9})\\s+\\S+\\s*");
    final Pattern removeMinusSeparated = Pattern.compile("\\s*remove\\s+-\\s+(10{9}|[0-9]{1,9})\\s*");
    final Pattern remove = Pattern.compile("\\s*remove\\s+-?(10{9}|[0-9]{1,9})\\s*");
    final Pattern print = Pattern.compile("\\s*print\\s*");

    public StableSort() {
        state = new TreeMap<>();
    }

    public void interpret(String command) {
        final String[] words = command.trim().split("\\s+");
        if (addMinusSeparated.matcher(command).matches()) {
            final int index = -Integer.parseInt(words[2]);
            final ArrayList<String> al = state.getOrDefault(index, new ArrayList<>());
            al.add(words[3]);
            state.put(index, al);
        } else if (add.matcher(command).matches()) {
            final int index = Integer.parseInt(words[1]);
            final ArrayList<String> al = state.getOrDefault(index, new ArrayList<>());
            al.add(words[2]);
            state.put(index, al);
        } else if (removeMinusSeparated.matcher(command).matches()) {
            final int index = -Integer.parseInt(words[2]);
            state.remove(index);
        } else if (remove.matcher(command).matches()) {
            final int index = Integer.parseInt(words[1]);
            state.remove(index);
        } else if (print.matcher(command).matches()) {
            state.forEach((key, list) -> list.forEach(str -> System.out.printf("%s %s%n", key, str)));
        } else {
            System.out.printf("%s%s%s%n", "Command ", Arrays.deepToString(words), " was ignored.");
        }
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            final String command = scanner.nextLine();
            interpret(command);
        }
    }

    public void start(final Path path) {
        try (final BufferedReader bf = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String command;
            while ((command = bf.readLine()) != null) {
                interpret(command);
            }
        } catch (final IOException e) {
            System.out.println("IOException occurred while reading file.");
        }
    }
}