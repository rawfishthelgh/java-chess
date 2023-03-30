package chess.view;

import chess.view.command.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InputView {

    private static final int COMMAND_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final int COLUMN_INDEX = 0;
    private static final int SOURCE_COORDINATE_INDEX = 1;
    private static final int DESTINATION_COORDINATE_INDEX = 2;

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private InputView() {
        throw new IllegalStateException("인스턴스를 생성할 수 없는 객체입니다.");
    }

    public static List<String> inputCommand() {
        try {
            String inputCommand = bufferedReader.readLine();
            validateInputCommand(inputCommand);
            return Arrays.stream(inputCommand.split(" ")).collect(Collectors.toList());
        } catch (IOException e) {
            return inputCommand();
        }
    }

    private static void validateInputCommand(String inputCommand) {
        validateBlank(inputCommand);
        validateStartOrEndCommandForm(inputCommand);
    }

    private static void validateBlank(String inputCommand) {
        if (Objects.isNull(inputCommand) || inputCommand.isBlank()) {
            throw new IllegalArgumentException("빈 값 또는 null 값을 입력할 수 없습니다.");
        }
    }

    private static void validateStartOrEndCommandForm(String inputCommand) {
        String[] splitedInputCommand = inputCommand.split(" ");
        if (isCorrectCommand(splitedInputCommand[0])) {
            throw new IllegalArgumentException("start, end, move, status 명령만 입력할 수 있습니다.");
        }

        if ("move".equals(splitedInputCommand[0]) && (splitedInputCommand.length != 3)) {
            throw new IllegalArgumentException("move 명령은 출발 좌표와 도착 좌표를 입력해야 합니다.");

        }
    }

    private static boolean isCorrectCommand(String command) {
        return !List.of("start", "end", "move","status").contains(command);
    }

    public static <T> T repeat(Supplier<T> inputProcess) {
        try {
            return inputProcess.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return repeat(inputProcess);
        }
    }

    public static Command extractCommand(List<String> input) {
        String inputCommand = input.get(COMMAND_INDEX);
        return Command.of(inputCommand);
    }

    public static List<String> extractSource(List<String> sourceCoordinates) {
        return Arrays.stream(sourceCoordinates.get(SOURCE_COORDINATE_INDEX).split("")).collect(Collectors.toList());
    }

    public static List<String> extractDestination(List<String> destinationCoordinates) {
        return Arrays.stream(destinationCoordinates.get(DESTINATION_COORDINATE_INDEX).split(""))
            .collect(Collectors.toList());
    }

    public static String extractColumn(List<String> coordinate) {
        return coordinate.get(COLUMN_INDEX);
    }

    public static String extractRow(List<String> coordinate) {
        return coordinate.get(ROW_INDEX);
    }

    public static Command inputRestartOrStart() {
        System.out.println("\n> 이전 경기가 진행 중입니다");
        System.out.println("> 이어서 진행하려면 restart, 새 게임을 시작하려면 start를 입력하세요\n");
        try {
            String inputCommand = bufferedReader.readLine();
            validateRestartCommand(inputCommand);
            return Command.of(inputCommand);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return inputRestartOrStart();
        }
    }

    private static void validateRestartCommand(String inputCommand) {
        if (!List.of("start", "restart").contains(inputCommand)){
            throw new IllegalArgumentException("start, restart만 입력 가능합니다");
        }
    }
}
