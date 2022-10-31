package fr.lefort.business;

import fr.lefort.entities.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBusinessUsersImpl implements IBusiness<Users> {

  public Optional<Users> getUserByMail(String mail) throws Exception {
//		Users userReceived =
//		Users user = new Users(userReceived.getId(), userReceived.getMail(), userReceived.getPassword(),
//				userReceived.getActive());
   ///////////// return usersRepository.findByMail(mail);
    return Optional.of(new Users());
  }

  @Override
  public void saveOrUpdateOne(Users users) throws Exception {

  }

  @Override
  public void deleteOne(Users users) throws Exception {

  }

  @Override
  public List<Users> findAll() {
    return null;
  }

  @Override
  public Optional<Users> readOneById(Long id) {
    return Optional.empty();
  }
}
