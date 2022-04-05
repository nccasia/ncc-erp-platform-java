import dayjs from 'dayjs/esm';
import { ProjectType } from 'app/entities/enumerations/project-type.model';

export interface IProject {
  id?: number;
  name?: string;
  startDate?: dayjs.Dayjs;
  customer?: string | null;
  projectType?: ProjectType | null;
}

export class Project implements IProject {
  constructor(
    public id?: number,
    public name?: string,
    public startDate?: dayjs.Dayjs,
    public customer?: string | null,
    public projectType?: ProjectType | null
  ) {}
}

export function getProjectIdentifier(project: IProject): number | undefined {
  return project.id;
}
