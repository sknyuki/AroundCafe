package com.example.demo.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExistsDto {
    private String memId;
    private String memNick;

    public ExistsDto(){

    }
    @Builder
    public ExistsDto(String memId, String memNick){
        this.memId = memId;
        this.memNick = memNick;
    }
}
