package ru.ydubovitsky.engineerBlog.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
public class ImageDto {

    private Integer id;

    private String name;

    private byte[] byteImage;


}
