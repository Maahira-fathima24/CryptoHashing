package com.example.cryptohash.Model;

import lombok.Data;

@Data
public class HashResponse {
    public String hash1;
    public String hash2;
    public Double hashSimilarity;
    public String algorithm;
    public Double inputSimilarity;
    public Integer hashLength;

}
