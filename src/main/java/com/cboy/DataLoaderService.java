package com.cboy;

//import org.springframework.ai.reader.ExtractedTextFormatter;
//import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
//import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
//import org.springframework.ai.transformer.splitter.TokenTextSplitter;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DataLoaderService {

//    @Value("classpath:/data/xxx.pdf")
//    private Resource resource;
//
//    @Autowired
//    private VectorStore vectorStore;
//
//    public void load() {
//        PagePdfDocumentReader pdfDocumentReader = new PagePdfDocumentReader(this.resource,
//                PdfDocumentReaderConfig.builder()
//                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
//                                .withNumberOfBottomTextLinesToDelete(3)
//                                .withNumberOfTopPagesToSkipBeforeDelete(1)
//                                .build())
//                        .withPagesPerDocument(1)
//                        .build());
//        var tokenTextSplitter = new TokenTextSplitter();
//        this.vectorStore.accept(tokenTextSplitter.apply(pdfDocumentReader.get()));
//    }
}
