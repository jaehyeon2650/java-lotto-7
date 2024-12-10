package lotto.exception;

public enum ErrorMessage {
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_RANGE("로또 번호는 1부터 45 사이의 숫자여야합니다."),
    INVALID_LOTTO_DUPLICATE("로또 번호는 중복이 되면 안됩니다.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
