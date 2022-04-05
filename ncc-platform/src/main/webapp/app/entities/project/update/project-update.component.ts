import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IProject, Project } from '../project.model';
import { ProjectService } from '../service/project.service';
import { ProjectType } from 'app/entities/enumerations/project-type.model';

@Component({
  selector: 'jhi-project-update',
  templateUrl: './project-update.component.html',
})
export class ProjectUpdateComponent implements OnInit {
  isSaving = false;
  projectTypeValues = Object.keys(ProjectType);

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    startDate: [null, [Validators.required]],
    customer: [],
    projectType: [],
  });

  constructor(protected projectService: ProjectService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ project }) => {
      if (project.id === undefined) {
        const today = dayjs().startOf('day');
        project.startDate = today;
      }

      this.updateForm(project);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const project = this.createFromForm();
    if (project.id !== undefined) {
      this.subscribeToSaveResponse(this.projectService.update(project));
    } else {
      this.subscribeToSaveResponse(this.projectService.create(project));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProject>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(project: IProject): void {
    this.editForm.patchValue({
      id: project.id,
      name: project.name,
      startDate: project.startDate ? project.startDate.format(DATE_TIME_FORMAT) : null,
      customer: project.customer,
      projectType: project.projectType,
    });
  }

  protected createFromForm(): IProject {
    return {
      ...new Project(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? dayjs(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      customer: this.editForm.get(['customer'])!.value,
      projectType: this.editForm.get(['projectType'])!.value,
    };
  }
}
