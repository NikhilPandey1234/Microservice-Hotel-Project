package com.userSer.services.impl;

import com.userSer.dto.Hotel;
import com.userSer.dto.UserDTO;
import com.userSer.exceptions.ResourceNotFoundException;
import com.userSer.dto.Rating;
import com.userSer.external.HotelService;
import com.userSer.modal.User;
import com.userSer.repository.UserRepository;
import com.userSer.services.UserService;
import com.userSer.util.ApiResponse;
import com.userSer.util.ModelMappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMappers modelMappers;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private  WebClient webClient;

    @Autowired
    private HotelService hotelService;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMappers.userDtoToUserEntity(userDTO);
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User savedUser = userRepository.save(user);
        return modelMappers.userEntityToUserDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found With This id: "+userId));
        user.setUserName(userDTO.getUserName());
        user.setUserEmail(userDTO.getUserEmail());
        user.setAbout(userDTO.getAbout());
        User updateUser = userRepository.save(user);
        return modelMappers.userEntityToUserDto(updateUser);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll()
                .stream().map(user -> modelMappers.userEntityToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUSerById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found With This id: "+userId));
       Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICES/api/rating/users/"+user.getUserId(), Rating[].class);
        logger.info("{}", ratingOfUser);
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICES/api/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
          //  logger.info("response status code "+forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
//        List<Rating> ratings;
//        try {
//            ratings = webClient.get()
//                    .uri("http://localhost:8083/api/rating/users/"+user.getUserId(), ArrayList.class)
//                    .retrieve()
//                    .bodyToFlux(Rating.class)
//                    .collectList()
//                    .block();
//            logger.info("Ratings for user {}: {}", userId, ratings);
//        } catch (WebClientResponseException.NotFound exception) {
//            logger.error("User not found with id: {}", userId);
//            throw new ResourceNotFoundException("User not found with id: " + userId);
//        }
        return modelMappers.userEntityToUserDto(user);
    }

    @Override
    public ApiResponse deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found With This id: "+userId));
        LocalDateTime dateTime = LocalDateTime.now();
        userRepository.delete(user);
        return new ApiResponse("User Deleted Successfully",true, dateTime.toLocalDate(), dateTime.toLocalTime());

    }
}
