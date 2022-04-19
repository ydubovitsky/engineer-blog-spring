package ru.ydubovitsky.engineerBlog.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ydubovitsky.engineerBlog.dto.PostDto;
import ru.ydubovitsky.engineerBlog.payload.request.PostRequest;

import java.io.IOException;
import java.util.stream.IntStream;

public class PostRequestMapper {

    public static PostDto postRequestToPostDto(PostRequest postRequest) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PostDto postDto = mapper.readValue(postRequest.getNewPost(), PostDto.class);
            postDto.setByteImage(postRequest.getFiles().get(0).getBytes());

            //TODO Переделать маппер
            IntStream.range(0, postDto.getSubPosts().size())
                    .forEach(idx -> {
                        try {
                            postDto.getSubPosts().get(idx).setByteImage(postRequest.getFiles().get(idx + 1).getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            return postDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
