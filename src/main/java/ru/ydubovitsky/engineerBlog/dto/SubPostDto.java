package ru.ydubovitsky.engineerBlog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubPostDto {

    private Long id;
    private String text;
    private String sourceCode;
    private String imageDescription;
    private byte[] byteImage;

}
