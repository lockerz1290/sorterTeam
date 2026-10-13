public enum PlayerGrade {
    MATEUSZ(1),
    RAFALW(1),
    ADRIAN(3),
    TOMEKW(1),
    LUKASZZ(3),
    MIREKO(2),
    MIREKB(1),
    GABRIELB(3),
    AGNIESZKA(4),
    MICHALJ(2),
    PAWELW(1),
    PIOTR(1),
    AREK(3),
    DOMINB(3),
    SLAWEKS(3),
    SLAWEKK(1),
    DOMIN(1),
    KRYSTIAN(3),
    KRYSTIANP(1),
    GRZESIEK(4),
    RAFALD(3),
    PSALA(2),
    FILIP(3),
    ANDRZEJ(1),
    PRZEMEK(1),
    BASIA(4),
    DAMIAN(3),
    DAMIANP(2),
    AGATA(4),
    MICHALB(3),
    MONIKA(4),
    KASIA(4),
    KUBA(2),
    TOMEKZ(3),
    KAROZ(3),
    NIEZNAJOMA(4),
    DAREK(1);

    private final int grade;

    PlayerGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}
