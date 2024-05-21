package SauceLab.AndroidApk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class {@link TerminalCommandExecutor} holds methods to execute terminal commands.
 */
public class TerminalCommandExecutor {

    public static String[] executeCommands(String command) {
        List<String> output = new ArrayList<>();
        try {
            // Start the process
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", command);
            Process process = processBuilder.start();

            // Read the output
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.add(line);
                }
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Error: Command exited with code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // Convert the list to an array
        return output.toArray(new String[0]);
    }

    public static void main(String[] args) {
        // Example usage
        String[] output = executeCommands("ls -la");
        for (String line : output) {
            System.out.println(line);
        }
    }
}

