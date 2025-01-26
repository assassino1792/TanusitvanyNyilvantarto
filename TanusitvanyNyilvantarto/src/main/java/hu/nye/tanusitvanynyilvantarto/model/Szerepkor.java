package hu.nye.tanusitvanynyilvantarto.model;

public enum Szerepkor {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String szerepkorNev;

    Szerepkor(String roleName) {
        this.szerepkorNev = roleName;
    }

    public String szerepkorNev() {
        return szerepkorNev;
    }
}
