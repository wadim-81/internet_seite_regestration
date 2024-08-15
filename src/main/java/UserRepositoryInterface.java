public interface UserRepositoryInterface {
    void save(User user);
    boolean exists (String username);
    boolean validate(String username,String password);
    User findByUsername(String username);


}
