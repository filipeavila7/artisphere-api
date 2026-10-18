package com.example.demo.user.dto;

import java.util.List;

public record RegisterSuggestions(
        boolean available,
        List<String> suggestions
) {
}
