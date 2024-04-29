package dev.changuii.project.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface DataService {


    void storeToiletOpenAPIData() throws URISyntaxException;

    void readExcelData() throws IOException, URISyntaxException;
}
