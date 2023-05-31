package ru.ydubovitsky.engineerBlog.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    public String name;
    public String message;
    public String email;
    public String website;

}
