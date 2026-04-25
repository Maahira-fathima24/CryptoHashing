package com.example.cryptohash.Service;

import com.example.cryptohash.Model.HashRequest;
import com.example.cryptohash.Model.HashResponse;
import com.example.cryptohash.Util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class HashService {
    public HashResponse compare(HashRequest request){

        String hash1 = HashUtil.generateString(request.getInput1() , request.getAlgorithm());
        String hash2 = HashUtil.generateString(request.getInput2(), request.getAlgorithm());
        Double similarity = calculatesimilarity(hash1 , hash2);
        double inputSimilarity = calculateInputSimilarity(
                request.getInput1(), request.getInput2()
        );
        Integer hashLength = hashLength(hash1);
        HashResponse response = new HashResponse();
        response.setHash1(hash1);
        response.setHash2(hash2);
        response.setAlgorithm(request.getAlgorithm());
        response.setHashSimilarity(similarity);
        response.setInputSimilarity(inputSimilarity);
        response.setHashLength(hashLength);

        if (request.getInput1() == null || request.getInput2() == null) {
            throw new RuntimeException("Inputs cannot be null");
        }

        return response;
    }

    public Double calculatesimilarity(String h1 , String h2){
        int length = Math.min(h1.length() , h2.length());
        int match = 0;
        for(int i=0 ; i<length ; i++){
            if(h1.charAt(i) == h2.charAt(i)){
                match ++;
            }

        }
        return (match * 100.0 ) /length;
    }
    private int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {

                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1], // replace
                            Math.min(
                                    dp[i - 1][j], // delete
                                    dp[i][j - 1]  // insert
                            )
                    );
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
    private double calculateInputSimilarity(String s1, String s2) {
        int distance = levenshteinDistance(s1, s2);
        int maxLength = Math.max(s1.length(), s2.length());

        if (maxLength == 0) return 100.0;

        return (1 - (double) distance / maxLength) * 100;
    }
    public int hashLength(String h1) {
        return h1.length();
    }
}
