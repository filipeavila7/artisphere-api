package com.example.demo.user.dto;

import java.util.List;

public record RegisterSuggestions(
        boolean avaliable,

        List<String> suggestions
) {
}
