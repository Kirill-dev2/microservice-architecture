package com.otus.controller;

import com.otus.api.UserApi;
import com.otus.mapper.UserMapper;
import com.otus.model.UserDto;
import com.otus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController implements UserApi {
  private final UserRepository userRepository;
  private final UserMapper mapper;

  @Override
  public ResponseEntity<Void> createUser(UserDto userDto) {
    userRepository.save(mapper.from(userDto));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> deleteUser(Long userId) {
    userRepository.deleteById(userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<UserDto> findUserById(Long userId) {
    var user = userRepository.findById(userId);
    return user.map(value -> ResponseEntity.ok(mapper.from(value)))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  public ResponseEntity<Void> updateUser(Long userId, UserDto userDto) {
    var user = userRepository.findById(userId);

    if (user.isPresent()) {
      var updateUser = mapper.from(userDto);
      updateUser.setId(userId);
      userRepository.save(updateUser);
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
