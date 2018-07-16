package com.piotrslowinski.model.exporter;

import com.piotrslowinski.model.Token;
import com.piotrslowinski.model.User;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class CSVReportExporter implements ReportExporter {

    private static final String COMMA = ",";

    @Override
    public void exportToFile(User user) {

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Java\\report.csv")));
//            StringBuffer oneLine = new StringBuffer();
            List<Token> tokens = user.getTokens();


//            oneLine.append(user.getId() <= 0 ? "" : user.getId());
//            oneLine.append(COMMA);
//            oneLine.append(user.getFirstName().trim().length() == 0 ? "" : user.getFirstName());
//            oneLine.append(COMMA);
//            oneLine.append(user.getLastName().trim().length() == 0 ? "" : user.getLastName());
//            oneLine.append(COMMA);
//            oneLine.append(user.getEmail().trim().length() == 0 ? "" : user.getEmail());
//            oneLine.append(COMMA);


            if (!tokens.isEmpty()) {
                for (Token token : tokens) {
                    StringBuffer oneLine = new StringBuffer();
                    oneLine.append(token.getValue().trim().length() == 0 ? "" : token.getValue());
                    oneLine.append(COMMA);
                    oneLine.append(tokens.size() <=0 ? "" : tokens.size());
                    bw.write(oneLine.toString());
                    bw.newLine();
                }
            }

            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public byte[] createFile(User user) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        StringBuffer oneLine = new StringBuffer();

        oneLine.append(user.getId() <= 0 ? "" : user.getId());
        oneLine.append(COMMA);
        oneLine.append(user.getFirstName().trim().length() == 0 ? "" : user.getFirstName());
        oneLine.append(COMMA);
        oneLine.append(user.getLastName().trim().length() == 0 ? "" : user.getLastName());
        oneLine.append(COMMA);
        oneLine.append(user.getEmail().trim().length() == 0 ? "" : user.getEmail());
        oneLine.append(COMMA);
        try {
            stream.write(oneLine.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return stream.toByteArray();
    }
}
