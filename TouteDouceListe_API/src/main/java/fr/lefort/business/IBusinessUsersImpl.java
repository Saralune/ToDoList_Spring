package fr.lefort.business;

import fr.lefort.dao.UsersRepository;
import fr.lefort.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBusinessUsersImpl implements IBusiness<Users> {

  @Autowired
  UsersRepository usersRepository;

/*  public Optional<Users> getUserByMail(String mail) throws Exception {
//		Users userReceived =
//		Users user = new Users(userReceived.getId(), userReceived.getMail(), userReceived.getPassword(),
//				userReceived.getActive());
   ///////////// return usersRepository.findByMail(mail);
    return Optional.of(new Users());
  }*/

  @Override
  public void saveOrUpdateOne(Users users) throws Exception {
    usersRepository.save(users);
  }

  @Override
  public void deleteOne(Users users) throws Exception {
    usersRepository.delete(users);
  }

  @Override
  public List<Users> findAll() {
    return null;
  }

  @Override
  public Optional<Users> readOneById(Long id) {
    return usersRepository.findById(id);
  }

  public Optional<Users> getUserByMail(String mail) throws Exception {
//		Users userReceived =
//		Users user = new Users(userReceived.getId(), userReceived.getMail(), userReceived.getPassword(),
//				userReceived.getActive());
    return usersRepository.findByMail(mail);
  }
}
