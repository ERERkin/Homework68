package kg.Academy;

import kg.Academy.entities.Championship;
import kg.Academy.entities.Country;
import kg.Academy.entities.SportKind;
import kg.Academy.entities.Team;
import kg.Academy.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //------------------------------------------------------------------
//        Country country = new Country(1,"Italy");
//        create(country);
//        SportKind sportKind = new SportKind(1,"football");
//        SportKind sportKind2 = new SportKind(2,"VolleyBall");
//        create(sportKind);
//        create(sportKind2);
//        Championship championship = new Championship(1,"Italy football championship", sportKind, country);
//        Championship championship2 = new Championship(2,"Italy volleyball championship", sportKind2, country);
//        List<Championship> list = new ArrayList<>();
//        List<Championship> list2 = new ArrayList<>();
//        list.add(championship);
//        list2.add(championship2);
//        create(championship);
//        create(championship2);
//        for(int i = 1; i <= 10; i++){
//            create(new Team(i, "Team" + i, "a", "b", sportKind, list));
//        }
//        for(int i = 11; i <= 20; i++){
//            create(new Team(i, "Team" + i, "d", "r", sportKind2, list2));
//        }
        SportKind sportKind3 = new SportKind(1,"football");
        SportKind sportKind4 = new SportKind(2,"VolleyBall");
        List<Team> teams = getAllBySportKInd(sportKind3);
        teams.stream().forEach(team -> System.out.println(team.getName() + " " +
                        team.getChampionships().get(0).getName() + " " + team.getChampionships().get(0).getCountry().getName()));
//        teams.forEach(team -> System.out.println(team.getName() + " " +
//                team.getChampionships().get(0).getName() + " " + team.getChampionships().get(0).getCountry().getName()));
//        for(Team t : teams){
//            System.out.println(t);
//        }
        HibernateUtil.shutDown();
    }

    public static <T> void create(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
        System.out.println("Создали запись успешно");
    }

    @SuppressWarnings("unchecked")
    public static List<Team> getAllBySportKInd(SportKind sportKind) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Team> teams = session.createQuery("select t FROM Team t where t.sportKind = :sport_kind " +
                "order by t.sportKind.name")
                .setParameter("sport_kind", sportKind)
                .setMaxResults(10)
                .list();
        session.close();
        return teams;
    }
}
