package com.piotrslowinski.model.exporter;

import com.piotrslowinski.model.User;

public interface ReportExporter {

   void exportToFile(User user);

   byte[] createFile(User user);

}
