package com.iimassociates.imageplex.springecholistener;

import org.dcm4che3.net.ApplicationEntity;
import org.dcm4che3.net.Connection;
import org.dcm4che3.net.Device;
import org.dcm4che3.net.DeviceService;
import org.dcm4che3.net.TransferCapability;
import static org.dcm4che3.net.TransferCapability.Role.SCP;
import org.dcm4che3.net.service.BasicCEchoSCP;
import org.dcm4che3.net.service.DicomServiceRegistry;
import org.springframework.stereotype.Component;

@Component
public class ListenerBean extends DeviceService {

    public ListenerBean() {
        Device device = new Device("dcm4chee-arc");
        DicomServiceRegistry serviceRegistry = new DicomServiceRegistry();
        serviceRegistry.addDicomService(new BasicCEchoSCP());
        device.setDimseRQHandler(serviceRegistry);
        Connection conn = new Connection("localhost", "localhost", 7000);
        device.addConnection(conn);
        ApplicationEntity applicationEntity = new ApplicationEntity("ECHOSCP");
        applicationEntity.setAssociationAcceptor(true);
        applicationEntity.setAssociationInitiator(false);
        TransferCapability tc  = new TransferCapability("Verification SOP Class SCP", "1.2.840.10008.1.1", SCP, "1.2.840.10008.1.2");
        applicationEntity.addTransferCapability(tc);
        applicationEntity.addConnection(conn);
        device.addApplicationEntity(applicationEntity);
        setDevice(device);
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
