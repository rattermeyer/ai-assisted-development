package com.opitzconsulting.demo.copilot.javademo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PalindromeTest {
    // Can you implement a method that checks if a string is a palindrome candidate
    // based on the test below?
    public static boolean isPalindromeCandidate(String s) {
        return false;
    }

    @Test
    @DisplayName("Is palindrome candidate")
    void palindromeCandidate() {
        assertThat(isPalindromeCandidate("")).isFalse();
        assertThat(isPalindromeCandidate("a")).isTrue();
        assertThat(isPalindromeCandidate("ab")).isFalse();
        assertThat(isPalindromeCandidate("aab")).isTrue();
        assertThat(isPalindromeCandidate("aabb")).isTrue();
        assertThat(isPalindromeCandidate("aabbc")).isTrue();
    }
}
