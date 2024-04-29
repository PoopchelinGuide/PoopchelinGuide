package dev.changuii.project.service.impl;

import dev.changuii.project.dao.GarbageBinDAO;
import dev.changuii.project.dao.ToiletDAO;
import dev.changuii.project.entity.GarbageBinEntity;
import dev.changuii.project.entity.ToiletEntity;
import dev.changuii.project.service.DataService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private final ToiletDAO toiletDAO;
    private final GarbageBinDAO garbageBinDAO;

    public DataServiceImpl(
            @Autowired ToiletDAO toiletDAO,
            @Autowired GarbageBinDAO garbageBinDAO
            ){
        this.toiletDAO = toiletDAO;
        this.garbageBinDAO = garbageBinDAO;
    }

    @Override
    public void storeToiletOpenAPIData() throws URISyntaxException {
        int page1 = 1;
        int page2 = 1000;

        String openAPIKey = "63776a527472686c37354d626e557a";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> http = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        for(int i = 0; i < 5; i ++) {
            URI uri = new URI("http://openapi.seoul.go.kr:8088/" +
                    openAPIKey+"/json/SearchPublicToiletPOIService/" + page1 + "/" + page2);
            ResponseEntity<LinkedHashMap> response = restTemplate.exchange(uri, HttpMethod.GET, http, LinkedHashMap.class);

            List<LinkedHashMap> data = (List<LinkedHashMap>) ((LinkedHashMap) response.getBody().get("SearchPublicToiletPOIService")).get("row");


            for (LinkedHashMap d : data) {
                ToiletEntity toilet = ToiletEntity.builder()
                        .coordinateX((Double) d.get("X_WGS84"))
                        .coordinateY((Double) d.get("Y_WGS84"))
                        .name((String) d.get("FNAME")).build();
                this.toiletDAO.createToilet(toilet);
            }
            page1 += 1000;
            page2 += 1000;
        }

    }
    @Override
    public void readExcelData() throws IOException, URISyntaxException {
        String ncpId =  "rnl7q9733x";
        String ncpKey = "iiK2zg20DDqp6yoVsdnryVn7DG4SkYz7ehaxL1aO";
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode")
                .queryParam("query", "서울특별시")
                .build();

        URI uri = uriComponents.toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-NCP-APIGW-API-KEY-ID",ncpId);
        headers.add("X-NCP-APIGW-API-KEY",ncpKey);

        InputStream inputStream = new ClassPathResource("garbage.xlsx").getInputStream(); // 데이터를 입력받기위한 InputStream 생성
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream); //Excel 파일을 읽을 때 사용하기 위한 XSSFWorkbook
        Sheet sheet = workbook.getSheetAt(0); // 엑셀 시트 지정, 메인 시트값은 0번

        for (Row row : sheet) { //시트의 행을 모두 봄
            if (row.getRowNum() < 6) continue; // 헤더 스킵
            if (row.getRowNum() > 3182) break;

            ResponseEntity<LinkedHashMap> response = restTemplate.exchange(uri + row.getCell(2).getStringCellValue(),
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    LinkedHashMap.class);
            try {
                LinkedHashMap result = (LinkedHashMap) ((List) response.getBody().get("addresses")).get(0);

                GarbageBinEntity garbageBinEntity = GarbageBinEntity.builder(). // 빌더로 속성 값 설정
                        coordinateX(Double.parseDouble(result.get("x").toString())).
                        coordinateY(Double.parseDouble(result.get("y").toString())).
                        address(row.getCell(2).getStringCellValue()).
                        name(row.getCell(3).getStringCellValue()).
                        type(row.getCell(5).getStringCellValue()).build();

                garbageBinDAO.createGarbageBin(garbageBinEntity);
            }
            catch(IndexOutOfBoundsException e){}
        }

        workbook.close(); //리소스 해제

    }
}
