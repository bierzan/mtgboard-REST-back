package com.brzn.mtgboard.user;

import lombok.Getter;

public class UserToken {
    @Getter
    private String userId;
    @Getter
    private String mtgAuthToken;

    public UserToken() {
    }

    public UserToken(String userId, String mtgAuthToken) {
        this.userId = userId;
        this.mtgAuthToken = mtgAuthToken;
    }
}


