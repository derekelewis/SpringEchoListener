package com.iimassociates.imageplex.springecholistener;

import org.dcm4che3.conf.api.ConfigurationException;
import org.dcm4che3.conf.api.DicomConfiguration;
import org.dcm4che3.conf.dicom.DicomConfigurationBuilder;
import org.dcm4che3.net.DeviceService;
import org.dcm4che3.net.service.BasicCEchoSCP;
import org.dcm4che3.net.service.DicomServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ListenerBean extends DeviceService {

    private DicomConfiguration conf;
    private DicomServiceRegistry serviceRegistry;

    private String config;

    @Autowired
    public ListenerBean(@Value("${config}") String config) {
        try {
            this.config = config;
            conf = buildConfiguration();
            serviceRegistry = new DicomServiceRegistry();
            serviceRegistry.addDicomService(new BasicCEchoSCP());
            device = conf.findDevice("echoscp");
            device.setDimseRQHandler(serviceRegistry);
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DicomConfiguration buildConfiguration() throws ConfigurationException {
        DicomConfigurationBuilder builder
                = DicomConfigurationBuilder
                .newJsonConfigurationBuilder(config);
        return builder.build();
    }
}
