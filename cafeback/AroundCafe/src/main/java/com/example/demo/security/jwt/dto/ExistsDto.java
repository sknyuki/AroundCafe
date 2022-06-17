package com.example.demo.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExistsDto {
    private final String memId;
    private final String memNick;

    @Builder
    public ExistsDto(String memId, String memNick){
        this.memId = memId;
        this.memNick = memNick;
    }
}
