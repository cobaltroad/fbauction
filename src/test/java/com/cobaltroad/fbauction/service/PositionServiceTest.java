package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.enumeration.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URISyntaxException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PositionServiceTest {
    @Autowired
    PositionService service;

    @Test
    public void importCsv() throws IOException, URISyntaxException {
        service.importCsv("catchers.csv", Position.CATCHER);
        service.importCsv("firstbasemen.csv", Position.FIRST_BASEMAN);
        service.importCsv("secondbasemen.csv", Position.SECOND_BASEMAN);
        service.importCsv("shortstops.csv", Position.SHORTSTOP);
        service.importCsv("thirdbasemen.csv", Position.THIRD_BASEMAN);
        service.importCsv("outfielders.csv", Position.OUTFIELDER);
        service.importCsv("designatedhitters.csv", Position.DESIGNATED_HITTER);
    }
}
