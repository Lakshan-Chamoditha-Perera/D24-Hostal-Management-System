package com.example.hms.service;

import com.example.hms.service.custom.impl.ReservationServiceImpl;
import com.example.hms.service.custom.impl.RoomServiceImpl;
import com.example.hms.service.custom.impl.StudentServiceImpl;
import com.example.hms.service.custom.impl.UserServiceImpl;
import com.example.hms.service.util.ServiceType;
import com.example.hms.service.util.exception.ServiceNotFountException;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getServiceFactory() {
        return serviceFactory == null ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public  <T extends SuperService> T getService(ServiceType type) throws ServiceNotFountException {
        switch (type) {
            case StudentService:
                return (T) new StudentServiceImpl();
            case RoomService:
                return (T) new RoomServiceImpl();
            case ReservationService:
                return (T) new ReservationServiceImpl();
            case UserService:
                return (T) new UserServiceImpl();
            default:
                throw new ServiceNotFountException("Service not found");
        }
    }
}
