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
        HashResponse response = new HashResponse();
        response.setHash1(hash1);
        response.setHash2(hash2);
        response.setAlgorithm(request.getAlgorithm());
        response.setSimilarity(similarity);

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
}
