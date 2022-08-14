package com.example.blogappapis.payloads;


import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryDto {


    private Integer categoryId;
    @NotNull
    @Size(max = 10)
    private String categoryTitle;
    @NotNull
    @Size(min = 2)
    private String categoryDescription;


}
