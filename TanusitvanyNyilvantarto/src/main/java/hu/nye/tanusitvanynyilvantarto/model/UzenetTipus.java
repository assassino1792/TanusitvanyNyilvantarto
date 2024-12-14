package hu.nye.tanusitvanynyilvantarto.model;

public enum UzenetTipus {
    WARNING("A tanúsítvány hamarosan le fog járni."),
    CRITICAL("A tanúsítvány lejárati ideje már kritikus."),
    EXPIRED("A tanúsítvány lejárt és inaktív a státusza.");

    private final String message;

    UzenetTipus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
