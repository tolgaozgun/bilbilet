package edu.bilkent.bilbilet.service.fare;

import java.util.List;

import edu.bilkent.bilbilet.model.VehicleSeatConfig;

public interface IVehicleSeatConfigService {
    public VehicleSeatConfig createConfig(VehicleSeatConfig toCreate) throws Exception;
    public VehicleSeatConfig updateConfig(VehicleSeatConfig toUpdate) throws Exception;
    public VehicleSeatConfig updateConfigById(VehicleSeatConfig toUpdate, int toUpdateId) throws Exception;
    public boolean deleteConfigById(int toDeleteId) throws Exception;
    public VehicleSeatConfig getConfigByVehicleId(int vehicleId) throws Exception;
}
