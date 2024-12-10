package lotto.exception;

public class LottoException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR] ";

    private LottoException(ErrorMessage errorMessage) {
        super(PREFIX + errorMessage.getMessage());
    }

    public static LottoException from(ErrorMessage errorMessage) {
        return new LottoException(errorMessage);
    }
}
