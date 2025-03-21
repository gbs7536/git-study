// UserDto
package com.example.session.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String name;
    private Integer password;
}
