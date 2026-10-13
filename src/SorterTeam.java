import java.util.*;
import java.util.stream.Collectors;

public class SorterTeam {

    private static final String input = """
        MATEUSZ
        GABRIELB
        SLAWEKS
        PSALA
        DOMINB
        ANDRZEJ
        AGNIESZKA
        ADRIAN
        AREK
        FILIP
        MICHALB
        AGATA
        MIREKO
        DAREK
        GRZESIEK
        MIREKB
        DAMIAN
        DOMIN
        """;

    public static void main(String[] args) {
        Random rnd = new Random();

        List<PlayerGrade> people = Arrays.stream(input.split("\\R"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(PlayerGrade::valueOf)
                .collect(Collectors.toList());

        List<List<PlayerGrade>> teams = createBalancedTeams(people, 3, 7, rnd);

        for (int i = 0; i < teams.size(); i++) {
            List<PlayerGrade> t = teams.get(i);
            System.out.println("TEAM " + (i + 1) + " (" + t.size() + "): " + t);
        }
        System.out.println("Result to send");
        teams.forEach(Collections::shuffle);
        for (int i = 0; i < teams.size(); i++) {
            List<PlayerGrade> t = teams.get(i);
            System.out.println("TEAM " + (i + 1) + " (" + t.size() + "): " + t);
        }

    }

    private static List<List<PlayerGrade>> createBalancedTeams(List<PlayerGrade> people, int teamCount, int teamSize, Random rnd) {
        Map<Integer, List<PlayerGrade>> byGrade = people.stream()
                .collect(Collectors.groupingBy(PlayerGrade::getGrade));

        List<List<PlayerGrade>> teams = new ArrayList<>();
        for (int i = 0; i < teamCount; i++) teams.add(new ArrayList<>());

        byGrade.values().forEach(list -> Collections.shuffle(list, rnd));

        for (int grade = 1; grade <= 4; grade++) {
            List<PlayerGrade> group = byGrade.getOrDefault(grade, Collections.emptyList());
            for (PlayerGrade p : group) {
                int finalGrade = grade;
                List<PlayerGrade> best = Collections.min(teams, Comparator
                        .comparingInt((List<PlayerGrade> t) -> t.size())
                        .thenComparingInt(t -> countGrade(t, finalGrade))
                );

                if (best.size() >= teamSize) {
                    Optional<List<PlayerGrade>> opt = teams.stream().filter(t -> t.size() < teamSize).findFirst();
                    if (opt.isPresent()) {
                        opt.get().add(p);
                    } else {
                        throw new IllegalStateException("Brak miejsca w żadnej drużynie przy próbie przypisania " + p);
                    }
                } else {
                    best.add(p);
                }
            }
        }

/*        for (List<PlayerGrade> t : teams) {
            if (t.size() != teamSize) {
                throw new IllegalStateException("Nieprawidłowy rozmiar drużyny: " + t.size());
            }
        }*/

        return teams;
    }

    private static int countGrade(List<PlayerGrade> team, int grade) {
        return (int) team.stream().filter(p -> p.getGrade() == grade).count();
    }

}
