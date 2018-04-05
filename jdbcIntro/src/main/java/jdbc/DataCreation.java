package jdbc;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class DataCreation {

    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";
    static String dbName = "test";
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) throws SQLException {

        List firstNamesList = List.of("Yuliya", "Aliaksei", "Sveta", "Petya", "Masha", "Sasha", "Tonya", "Ann",
                "Senya", "Vasya", "Masya", "Kate", "Julia", "Tolya", "Kolya", "Lena", "Dunya", "Tosya", "Sonya", "Tanya",
                "Nastya", "Natalya", "Lusya", "Andrei", "Slava", "Lida", "Olga", "Diana", "Alesia", "Mila"); //30
        List surnamesList = List.of("Smith", "Adams", "Ivanov", "Petrov", "Black", "Jonson", "Williams", "Brown", "Davis",
                "Murphy", "Lam", "Martin", "Roy", "Tremblay", "Lee", "Gagnon", "Thomas", "Hernandez", "Moore", "Jackson",
                "Thompson", "White", "Lopez", "Harris", "Clark", "Lewis", "Robinson", "Walker", "Perez", "Young"); //30

        Connection connection = getConnection();

        try {
            Integer usersCount = firstNamesList.size() * surnamesList.size();
            Map<Integer, Set<Integer>> friendsMap = new HashMap<>();
            Random random = new Random();
            int postCount = 300000;

            Statement st = connection.createStatement();

            log.info("1. Create simple database with tables:\nUsers (id, name, surname, birthdate), " +
                    "\nFriendships (userid1, userid2, timestamp), \nPosts (id, userId, text, timestamp), " +
                    "\nLikes (postid, userid, timestamp).\n");

            st.execute("CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(30), " +
                    "surname VARCHAR(30), birthdate DATE, PRIMARY KEY (id))");
            log.info("Table 'users' was created");

            st.execute("CREATE TABLE IF NOT EXISTS friendships (userid1 INT NOT NULL, userid2 INT NOT NULL, timestamp TIMESTAMP, " +
                    "PRIMARY KEY (userid1, userid2), FOREIGN KEY (userid1) REFERENCES users (id), FOREIGN KEY (userid2) REFERENCES users (id))");
            log.info("Table 'friendships' was created");

            st.execute("CREATE TABLE IF NOT EXISTS posts (id INT NOT NULL AUTO_INCREMENT, userid INT NOT NULL, text VARCHAR(1000), " +
                    "timestamp TIMESTAMP, PRIMARY KEY (id), FOREIGN KEY (userid) REFERENCES users (id))");
            log.info("Table 'posts' was created");

            st.execute("CREATE TABLE IF NOT EXISTS likes (postid INT NOT NULL, userid INT NOT NULL, timestamp TIMESTAMP, " +
                    "PRIMARY KEY (postid, userid), FOREIGN KEY (postid) REFERENCES posts (id), FOREIGN KEY (userid) REFERENCES users (id))");
            log.info("Table 'likes' was created");


            log.info("\n2. Fill tables with data which are make sense (> 1 000 users, > 70 000 friendships, > 300 000 likes in 2017)" +
                    "- use dictionaries in memory or prepared tables in database to generate data for filling\n");

            //Filling table 'users'
            for (int myID = 1; myID <= usersCount; myID++) {
                st.execute(
                        "insert into " + dbName + ".users (name, surname, birthdate)" +
                                " values ('"
                                + firstNamesList.get(random.nextInt(firstNamesList.size())) + "', '"
                                + surnamesList.get(random.nextInt(surnamesList.size())) + "', '"
                                + LocalDateTime.now()
                                .minusYears(random.nextInt(60 - 14) + 14)
                                .minusMonths(random.nextInt(12))
                                .minusDays(random.nextInt(30)).toString()
                                + "');");

                HashSet<Integer> friends = new HashSet<>();
                for (int j = 1; j < random.nextInt(usersCount / 4 - usersCount / 12) + usersCount / 12; j++) {
                    int newFriendID = random.nextInt(usersCount);
                    if (newFriendID != myID) {
                        friends.add(newFriendID);
                    }
                }
                friendsMap.put(myID, friends);
            }

            //Filling table 'friendships'
            friendsMap.forEach((me, myFriends) -> {
                myFriends.forEach(myFriend -> {
                    String outDate = LocalDateTime.now()
                            .minusMonths(random.nextInt(12))
                            .minusDays(random.nextInt(30)).toString();
                    try {
                        st.execute(
                                "insert into " + dbName + ".friendships (userid1, userid2, timestamp)" +
                                        " values (" + me + "," + myFriend + ",'"
                                        + outDate +
                                        "')");
                        st.execute(
                                "insert into " + dbName + ".friendships (userid1, userid2, timestamp)" +
                                        " values (" + myFriend + "," + me + ",'"
                                        + outDate +
                                        "')");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });

            //Filling table 'posts'
            for (int i = 1; i <= postCount; i++) {
                int userID = random.nextInt(usersCount);
                try {
                    String outDate = LocalDateTime.now()
                            .minusMonths(random.nextInt(12))
                            .minusDays(random.nextInt(30)).toString();
                    st.execute(
                            "insert into " + dbName + ".posts (id, userId, text, timestamp)" +
                                    " values (" + i + ", "
                                    + userID + ", '"
                                    + RandomStringUtils.random(500, true, false) + "', '"
                                    + outDate
                                    + "');");
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            //Filling table 'likes'
            for (int i = 1; i <= postCount; i++) {
                int userID = random.nextInt(usersCount);
                int postID = random.nextInt(postCount);

                try {
                    st.execute(
                            "insert into " + dbName + ".likes (postid, userid, timestamp)" +
                                    " values ("
                                    + postID + ", "
                                    + userID + ", '"
                                    + LocalDateTime.now()
                                    .minusMonths(random.nextInt(12))
                                    .minusDays(random.nextInt(30))
                                    + "');");
                } catch (Throwable t) {
                    System.out.println("userID: " + userID + " / postID: " + postID);
                }
            }

            log.info("\n3. Program should print out all names (only distinct) of users who has more " +
                    "when 100 friends and 100 likes in March 2018. ");
            printQueryResult(st);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void printQueryResult(Statement st) throws SQLException {
        ResultSet rs = st.executeQuery("select users.name, users.surname, users.id, count(friendships.userid2)\n" +
                "from users\n" +
                "inner join friendships on users.id=friendships.userid1\n" +
                "group by users.name, users.surname, users.id\n" +
                "having count(friendships.userid2) > 100");

        while (rs.next()) {
            log.info(rs.getRow() + ". " + rs.getString("name") + "\t" + rs.getString("surname")
                    + rs.getString("id") + "\t" + rs.getString("count(friendships.userid2)"));
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
