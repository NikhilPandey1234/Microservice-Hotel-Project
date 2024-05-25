package com.userSer.controller;

import com.userSer.dto.UserDTO;
import com.userSer.modal.User;
import com.userSer.services.UserService;
import com.userSer.util.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO userDTOs= userService.createUser(userDTO);
        return new ResponseEntity<>(userDTOs, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO){
        UserDTO userDtos = userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> user = userService.getAllUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    int retryCount =1;

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
    @Retry(name = "rating", fallbackMethod = "ratingFallBack")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId){
        logger.info("Retry count: {}", retryCount);
        retryCount++;
        UserDTO user = userService.getUSerById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*public ResponseEntity<UserDTO> ratingHotelFallBack(String userId, Exception ex){
 //       logger.info("Fallback is excuted because service is down : "+ex.getMessage());
     UserDTO user =  UserDTO.builder().
                userEmail("dummy@gmail.com").
                userName("Dummy").
                about("This user is created dummy because some services is down")
                .userId("1234567").
                 build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }*/

    public ResponseEntity<UserDTO> ratingFallBack(String userId, Throwable throwable) {
        // Log the fallback execution with the error message
        logger.info("Fallback is executed because service is down: " + throwable.getMessage());

        // Create a UserDTO object using the builder pattern
        UserDTO fallbackUser = UserDTO.builder()
                .userEmail("dummy@gmail.com")
                .userName("Fallback User")
                .about("This user is created as a fallback because some services are down")
                .userId(userId)
                .build();

        // Return the fallback UserDTO wrapped in a ResponseEntity with HTTP status OK
        return new ResponseEntity<>(fallbackUser, HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
        ApiResponse deleteUser = userService.deleteUser(userId);
        return new ResponseEntity<>(deleteUser, HttpStatus.OK);
    }
}
