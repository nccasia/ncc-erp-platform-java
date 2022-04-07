package asia.ncc.erp.hrm.service;

import asia.ncc.erp.hrm.domain.Employee;
import asia.ncc.erp.hrm.repository.EmployeeRepository;
import asia.ncc.erp.hrm.service.dto.EmployeeDTO;
import asia.ncc.erp.hrm.service.mapper.EmployeeMapper;
import java.util.Optional;
import java.util.Random;

import asia.ncc.erp.hrm.service.mapper.KafkaEmployeeMapper;
import asia.ncc.erp.platformsdk.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Employee}.
 */
@Service
@Transactional
public class EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;
    private final KafkaEmployeeMapper kafkaEmployeeMapper;

    private final PlatformService platformService;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, KafkaEmployeeMapper kafkaEmployeeMapper, PlatformService platformService) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.kafkaEmployeeMapper = kafkaEmployeeMapper;
        this.platformService = platformService;
    }

    /**
     * Save a employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        log.debug("Request to save Employee : {}", employeeDTO);
        Employee employee = employeeMapper.toEntity(employeeDTO);
        //employee = employeeRepository.save(employee);
        platformService.saveEmployee(kafkaEmployeeMapper.toEntity(employeeDTO));
        employeeDTO.setId(new Random().nextLong());
        return employeeDTO;
    }

    /**
     * Partially update a employee.
     *
     * @param employeeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EmployeeDTO> partialUpdate(EmployeeDTO employeeDTO) {
        log.debug("Request to partially update Employee : {}", employeeDTO);

        return employeeRepository
            .findById(employeeDTO.getId())
            .map(existingEmployee -> {
                employeeMapper.partialUpdate(existingEmployee, employeeDTO);

                return existingEmployee;
            })
            .map(employeeRepository::save)
            .map(employeeMapper::toDto);
    }

    /**
     * Get all the employees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Employees");
        return employeeRepository.findAll(pageable).map(employeeMapper::toDto);
    }

    /**
     * Get one employee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> findOne(Long id) {
        log.debug("Request to get Employee : {}", id);
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    /**
     * Delete the employee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Employee : {}", id);
        employeeRepository.deleteById(id);
    }
}
