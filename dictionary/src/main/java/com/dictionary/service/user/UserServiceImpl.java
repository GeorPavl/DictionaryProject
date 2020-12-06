package com.dictionary.service.user;

import com.dictionary.dto.DictionaryDTO;
import com.dictionary.dto.UserDTO;
import com.dictionary.model.Dictionary;
import com.dictionary.model.User;
import com.dictionary.repository.UserRepository;
import com.dictionary.service.dictionary.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    /**
     *  INJECT REPOSITORIES
     *  AND SERVICES
     *  **/

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * Convert Entity - DTO
     *  dtoToEntity
     *  writeUserDTO
     *  writeDictionaries
     * */

    @Override
    public User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        if (userDTO.getId() != null){
            user.setId(userDTO.getId());
        }
        if (userDTO.getUsername() != null){
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getPassword() != null){
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getEmail() != null){
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getCreateTime() != null){
            user.setCreateTime(userDTO.getCreateTime());
        }
        if (userDTO.getUpdateTime() != null){
            user.setUpdateTime(userDTO.getUpdateTime());
        }
        if (userDTO.getDictionaries() != null){
            List<Dictionary> dictionaries = new ArrayList<>();
            for (DictionaryDTO tempDictionaryDTO : userDTO.getDictionaries()){
                dictionaries.add(dictionaryService.dtoToEntity(tempDictionaryDTO));
            }
            user.setDictionaries(dictionaries);
        }
        return user;
    }

    @Override
    public UserDTO writeUserDTO(User newUser){
        UserDTO result = new UserDTO();
        result.setId(newUser.getId());
        result.setUsername(newUser.getUsername());
        result.setPassword(newUser.getPassword());
        result.setEmail(newUser.getEmail());
        result.setCreateTime(newUser.getCreateTime());
        result.setUpdateTime(newUser.getUpdateTime());
        if (newUser.getDictionaries() != null){
            writeDictionaries(result,newUser);
        }
        return result;
    }

    private void writeDictionaries(UserDTO result, User newUser){
        List newDictionaries = new ArrayList();
        Iterator dictionaryIt = newUser.getDictionaries().iterator();
        while (dictionaryIt.hasNext()){
            DictionaryDTO newDictionaryDTO = dictionaryService.writeDictionaryDTO((Dictionary) dictionaryIt.next());
            newDictionaries.add(newDictionaryDTO);
        }
        result.setDictionaries(newDictionaries);
    }

    /**
     * DEFINING CRUD METHODS:
     *  findAll()
     *  findUser()
     *  createUser()
     *  updateUser()
     *  deleteUser()
     * **/

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User tempUser : userRepository.findAll()){
            userDTOS.add(writeUserDTO(tempUser));
        }
        return userDTOS;
    }

    @Override
    public UserDTO findUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("Did not find userId: " + userId);
        }
        return writeUserDTO(optionalUser.get());
    }

    @Override
    public UserDTO createUser(UserDTO source){
        User user = new User(source.getUsername(),source.getEmail(),source.getPassword());
        if (source.getDictionaries() != null){
            createDictionaries(source.getDictionaries(), user);
        }
        return writeUserDTO(userRepository.save(user));
    }

    public void createDictionaries(List<DictionaryDTO> dictionaries, User user){
        for (DictionaryDTO tempDictionaryDTO : dictionaries){
            Dictionary newDictionary = new Dictionary(tempDictionaryDTO.getLanguage(), user);
            user.addDictionary(newDictionary);
        }
    }

    @Override
    public UserDTO updateUser(UserDTO source){
        User current = dtoToEntity(findUser(source.getId()));
        if (current == null){
            throw new RuntimeException("Did not find userId: " + source.getId());
        }
        if (source.getUsername() != null){
            current.setUsername(source.getUsername());
        }
        if (source.getPassword() != null){
            current.setPassword(source.getPassword());
        }
        if (source.getCreateTime() != null){
            current.setCreateTime(source.getCreateTime());
        }
        if (source.getUpdateTime() != null){
            current.setUpdateTime(source.getUpdateTime());
        }
        return writeUserDTO(userRepository.save(current));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(dtoToEntity(findUser(userId)));
    }
}






