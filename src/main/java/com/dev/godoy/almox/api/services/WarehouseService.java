package com.dev.godoy.almox.api.services;

import com.dev.godoy.almox.api.dtos.WarehouseDto;
import com.dev.godoy.almox.api.models.Warehouse;
import com.dev.godoy.almox.api.repositories.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;

public class WarehouseService {

    private WarehouseRepository repository;

    public WarehouseService(WarehouseRepository repository) {
        this.repository = repository;
    }

    public List<WarehouseDto> findAll() {
        List<WarehouseDto> result = new ArrayList<>();
        for (Warehouse warehouse : repository.findAll()) {
            result.add(warehouse.toDto());
        }
        return result;
    }

    public WarehouseDto findByNumber(int number) {
        return repository.findByNumber(number).toDto();
    }

    public WarehouseDto save(WarehouseDto dto) {
        Warehouse warehouse = repository.save(dto.toWarehouse());
        return warehouse.toDto();
    }

    public void update(int number, WarehouseDto dto) {
        repository.update(number, dto.toWarehouse());
    }

    public void delete(int number) {
        repository.delete(number);
    }
}
