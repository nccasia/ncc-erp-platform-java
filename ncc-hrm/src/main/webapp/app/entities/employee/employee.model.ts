import dayjs from 'dayjs/esm';
import { EmployeeLevel } from 'app/entities/enumerations/employee-level.model';

export interface IEmployee {
  id?: number;
  name?: string;
  email?: string;
  dob?: dayjs.Dayjs;
  employeeLevel?: EmployeeLevel | null;
}

export class Employee implements IEmployee {
  constructor(
    public id?: number,
    public name?: string,
    public email?: string,
    public dob?: dayjs.Dayjs,
    public employeeLevel?: EmployeeLevel | null
  ) {}
}

export function getEmployeeIdentifier(employee: IEmployee): number | undefined {
  return employee.id;
}
