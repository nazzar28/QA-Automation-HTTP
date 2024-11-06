package org.example;

public class Main {
    public static void main(String[] args){
        TestBackendAPIApplicationPost testBackendAPIApplicationPost = new TestBackendAPIApplicationPost("195.38.164.139", 5551, "/api/tutorials");

        testBackendAPIApplicationPost.testFirst();
        testBackendAPIApplicationPost.testSecond();

        String resultTest = testBackendAPIApplicationPost.getAnswerFromBackend();
        System.out.println("resultTest : " + resultTest);
    }
}