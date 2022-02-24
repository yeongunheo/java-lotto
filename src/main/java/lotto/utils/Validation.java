package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.LottoNumber;
import lotto.domain.Money;

public class Validation {

    public static final Character LOTTO_DELIMITER = ',';
    public static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 중복된 숫자가 존재합니다.";
    public static final String ERROR_INTEGER_RANGE = "[ERROR] 1~45 사이의 수가 아닙니다.";
    public static final String ERROR_NOT_INTEGER = "[ERROR] 양의 정수가 아닙니다.";
    public static final String ERROR_CREATE_LOTTO = "[ERROR] 형식에 맞게 입력해주세요.(ex, 1, 2, 3, 4, 5, 6)";
    public static final String ERROR_WRONG_INPUT_MONEY = "[ERROR] 올바른 구매 값을 입력해주세요. 로또가격: " + Money.BASIC_LOTTO_MONEY + "원";

    public static void checkDuplicateNumber(final List<Integer> numbers) {
        if (numbers.size() != Set.copyOf(numbers).size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    public static void checkInputLottoWinningNumbers(String numbers) {
        checkDelimiterCount(numbers);
        checkCreateLottoWinningNumbers(numbers);
        checkNotInteger(numbers);
        checkIntegerRange(numbers);
        checkDuplicateNumber(numbers);
    }

    private static void checkIntegerRange(String numbers) {
        String[] values = numbers.split(LottoWinningNumbers.LOTTO_STRING_DELIMITER);
        if (!Arrays.stream(values)
                .allMatch(s ->
                        1 <= Integer.parseInt(s) && Integer.parseInt(s) <= 45)) {
            throw new IllegalArgumentException(ERROR_INTEGER_RANGE);
        }
    }

    private static void checkNotInteger(String numbers) {
        String[] values = numbers.split(LottoWinningNumbers.LOTTO_STRING_DELIMITER);
        try {
            Arrays.stream(values)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }

    private static void checkCreateLottoWinningNumbers(String numbers) {
        try {
            numbers.split(LottoWinningNumbers.LOTTO_STRING_DELIMITER);
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_CREATE_LOTTO);
        }
    }

    private static void checkDelimiterCount(String numbers) {
        if (numbers.chars()
                .filter(c -> c == LOTTO_DELIMITER)
                .count() != LottoNumber.LOTTO_SELECT_NUMBER - 1) {
            throw new IllegalArgumentException(ERROR_CREATE_LOTTO);
        }
    }

    public static void checkDuplicateNumber(String numbers) {
        String[] values = numbers.split(LottoWinningNumbers.LOTTO_STRING_DELIMITER);
        if (LottoNumber.LOTTO_SELECT_NUMBER != Set.copyOf(Arrays.asList(values)).size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    public static void checkInputMoney(String money) {
        checkValidateInt(money);
        checkDivideMoney(Integer.parseInt(money));
    }

    public static void checkDivideMoney(int money) {
        if (!(money >= Money.BASIC_LOTTO_MONEY && money % Money.BASIC_LOTTO_MONEY == 0)) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_MONEY);
        }
    }

    public static void checkValidateInt(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_MONEY);
        }
    }
}