import React, { useState } from "react";
import "./App.css";
//import axios from "axios";




export default function App() {
  const [text1, setText1] = useState("");
  const [text2, setText2] = useState("");
  const [hashLength, setHashLength] = useState("");
  const [result, setResult] = useState(null);

  const handleHash = async (algorithm) => {
  const response = await fetch("http://localhost:8081/api/hash/compare", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      input1: text1,
      input2: text2,
      algorithm: algorithm,
    }),
  });

  const data = await response.json();
  setResult(data);
  setHashLength(data.hashLength);
};

  return (
    <div className="container">
      <h1>HASH COMPARATOR</h1>

      <div className="form">
        <div className="row">
          <label>TEXT 1 :</label>
          <input value={text1} onChange={(e) => setText1(e.target.value)} />
        </div>

        <div className="row">
          <label>TEXT 2 :</label>
          <input value={text2} onChange={(e) => setText2(e.target.value)} />
        </div>

      </div>

      <div className="buttons">
        <button onClick={() => handleHash("SHA-1")}>SHA-1</button>
        <button onClick={() => handleHash("SHA-256")}>SHA-256</button>
        <button onClick={() => handleHash("SHA3-512")}>SHA3-512</button>
        <button onClick={() => handleHash("MD2")}>MD2</button>
        <button onClick={() => handleHash("MD5")}>MD5</button>
      </div>

   

      <div className="results">
        <div><span>Hash (1) :</span><p>{result?.hash1}</p></div>
        <div><span>Hash (2) :</span><p>{result?.hash2}</p></div>
        <div><span>Similarity (Hash) :</span><p>{result?.hashSimilarity}%</p></div>
        <div><span>Similarity (Text) :</span><p>{result?.inputSimilarity}%</p></div>
        
      </div>

      <div className="hash-bottom">
  <label>HASH LENGTH :</label>
  <div className="hash-output">
    {hashLength}
  </div>
</div>
    </div>
  );
}