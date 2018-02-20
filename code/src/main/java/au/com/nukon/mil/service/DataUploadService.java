package au.com.nukon.mil.service;

import au.com.nukon.mil.domain.CustomerOrder;
import au.com.nukon.mil.repositories.CustomerOrderRepository;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataUploadService {

    private static String COMMA = ",";

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    public void uploadData(Path filePath) {
        List<CustomerOrder> inputList;
        try{
            File inputFile = new File(filePath.toString());
            InputStream inputFS = new FileInputStream(inputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
            customerOrderRepository.save(inputList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Function<String, CustomerOrder> mapToItem = (String line) -> {
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        CustomerOrder item = new CustomerOrder();
        item.setOrderID(new Long(NumberUtils.toLong(p[0])));
        item.setOutletID(p[1]);
        item.setFlowRate(NumberUtils.toLong(p[2]));
        return item;
    };
}
