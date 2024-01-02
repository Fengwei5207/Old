import com.oldMan.dao.UserDao;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/2 17:59
 */
public class TestMain {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        System.out.println(userDao.loginUser("15354859343", "123456"));
    }
}
