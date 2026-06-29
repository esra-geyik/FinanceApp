package Service;

import Repository.FileRepository;
import Util.HashUtil;

import java.util.List;

public class AuthService {

    private final FileRepository repo = new FileRepository();

    public boolean register(String user, String pass) {

        List<String> users = repo.loadUsers();

        for (String u : users) {
            String[] parts = u.split("\\|");

            if (parts[0].equalsIgnoreCase(user)) {
                System.out.println("Bu kullanıcı adı zaten kayıtlı!");
                return false;
            }
        }

        repo.saveUser(new model.User(user, HashUtil.hash(pass)));
        System.out.println("Kayıt başarılı.");
        return true;
    }

    public boolean login(String user, String pass) {

        List<String> users = repo.loadUsers();
        String hash = HashUtil.hash(pass);

        for (String u : users) {
            String[] parts = u.split("\\|");

            if (parts[0].equalsIgnoreCase(user)
                    && parts[1].equals(hash)) {
                return true;
            }
        }

        return false;
    }
}
