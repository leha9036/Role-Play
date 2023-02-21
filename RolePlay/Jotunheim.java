package RolePlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jotunheim {
    private static BufferedReader br;
    private static FantasyCharacter player = null;
    private static Battle battle = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    20,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println(String.format("Спасти наш мир от Орды вызвался %s! ", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Торговец еще не приехал");
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. На поле боя");
        System.out.println("3. Выход");
    }

    private static void commitFight() {
        battle.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getHealthPoints()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

        private static FantasyCharacter createMonster () {
            int random = (int) (Math.random() * 10);
            if (random % 2 == 0) return new Orc(
                    "Орк",
                    50,
                    10,
                    10,
                    100,
                    20
            );
            else return new BloodElf(
                    "Эльф крови",
                    25,
                    20,
                    20,
                    100,
                    10
            );
        }

    public interface FightCallback {
        void fightWin();

        void fightLost();
    }
}