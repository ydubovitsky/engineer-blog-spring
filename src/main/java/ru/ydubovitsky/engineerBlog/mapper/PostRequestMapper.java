package ru.ydubovitsky.engineerBlog.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.payload.request.PostRequest;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class PostRequestMapper {

    public static PostDto postRequestToPostDto(PostRequest postRequest) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PostDto postDto = mapper.readValue(postRequest.getNewPost(), PostDto.class);
            if(Objects.nonNull(postRequest.getFile())) {
                postDto.setByteImage(postRequest.getFile().getBytes());
            }
            return postDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
