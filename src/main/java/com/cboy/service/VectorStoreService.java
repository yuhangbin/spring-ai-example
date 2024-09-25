package com.cboy.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VectorStoreService {

    @Autowired
    private VectorStore vectorStore;

    public void add() {
        List<Document> docs = List.of(
                new Document("Proper tuber planting involves site selection, timing, and care. Choose well-drained soil and adequate sun exposure. Plant in spring, with eyes facing upward at a depth two to three times the tuber's height. Ensure 4-12 inch spacing based on tuber size. Adequate moisture is needed, but avoid overwatering. Mulching helps preserve moisture and prevent weeds.", Map.of("author", "A", "type", "post")),
                new Document("Successful oil painting requires patience, proper equipment, and technique. Prepare a primed canvas, sketch lightly, and use high-quality brushes and oils. Paint 'fat over lean' to prevent cracking. Allow each layer to dry before applying the next. Clean brushes often and work in a well-ventilated space.", Map.of("author", "A")),
                new Document("For a natural lawn, select the right grass type for your climate. Water 1 to 1.5 inches per week, avoid overwatering, and use organic fertilizers. Regular aeration helps root growth and prevents compaction. Practice natural pest control and overseeding to maintain a dense lawn.", Map.of("author", "B", "type", "post")) );
        vectorStore.add(docs);
    }

    public void delete() {
        vectorStore.delete(List.of("id"));
    }

    public List<Document> topK(int k) {
        return vectorStore.similaritySearch(
                SearchRequest
                        .query("learn how to grow things")
                        .withTopK(k)
        );
    }


}
