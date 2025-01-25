package hu.nye.tanusitvanynyilvantarto.model;

public enum UzenetTipus {
    Figyelmeztetés("A tanúsítvány hamarosan le fog járni."),
    Kritikus("A tanúsítvány lejárati ideje már kritikus."),
    Lejárt("A tanúsítvány lejárt és inaktív a státusza.");

    private final String message;

    UzenetTipus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
