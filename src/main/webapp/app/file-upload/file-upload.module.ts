import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FileUploadComponent } from './file-upload.component';
import { RouterModule } from '@angular/router';
import { FILE_UPLOAD_ROUTE } from './file-upload-route';
import { SharedModule } from 'app/shared/shared.module';

@NgModule({
  declarations: [FileUploadComponent],
  imports: [CommonModule, SharedModule, RouterModule.forChild([FILE_UPLOAD_ROUTE])],
})
export class FileUploadModule {}
